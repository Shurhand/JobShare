/* DatabaseUtilities.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package utilities.internal;

import domain.DomainEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.jdbc.Work;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import utilities.DatabaseConfig;

import javax.persistence.*;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;

public class DatabaseUtil {
	
	public DatabaseUtil() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Due to a bug in Hibernate 4.3.0.Final, the old Hibernate persistence provider's selected
		// by default, which causes a deprecation warning to be output to the console. That means that
		// we shouldn't use Persistence to create the entity manager factory.
		// entityManagerFactory = Persistence.createEntityManagerFactory(PersistenceUnit);
		entityManagerFactory = Persistence.createEntityManagerFactory(DatabaseConfig.PersistenceUnit);
		persistenceProvider = new HibernatePersistenceProvider();
//		entityManagerFactory = persistenceProvider.createEntityManagerFactory(DatabaseConfig.PersistenceUnit, null);
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		
		properties = entityManagerFactory.getProperties();
		databaseUrl = findProperty("hibernate.connection.url");
		databaseName = StringUtils.substringAfterLast(databaseUrl, "/");
		databaseDialectName = findProperty("hibernate.dialect");
		databaseDialect = (Dialect)ReflectHelper.classForName(databaseDialectName).newInstance();
		
//		configuration = buildConfiguration();
		metadataSources = buildMetadata();
		
	}
	
	private HibernatePersistenceProvider persistenceProvider;	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Map<String, Object> properties;
	private String databaseUrl;
	private String databaseName;
	private String databaseDialectName;
	private Dialect databaseDialect;
	private Configuration configuration;
	private EntityTransaction entityTransaction;
	private MetadataSources metadataSources;
		
	public HibernatePersistenceProvider getPersistenceProvider() {
		return persistenceProvider;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getDatabaseDialectName() {
		return databaseDialectName;
	}

	public Dialect getDatabaseDialect() {
		return databaseDialect;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public EntityTransaction getEntityTransaction() {
		return entityTransaction;
	}
	
	public void recreateDatabase() throws Throwable {
		List<String> databaseScript;
		List<String> schemaScript;
		String[] statements;
		
		databaseScript = new ArrayList<String>();
		databaseScript.add(String.format("drop database `jobshare`"));
		databaseScript.add(String.format("create database `jobshare`"));
		databaseScript.add(String.format("use `JobShare`"));
		executeScript(databaseScript);

		SchemaExport schemaExport = new SchemaExport();
		
		schemaExport.setHaltOnError(false);
		schemaExport.setDelimiter(";");
		schemaExport.setFormat(true);
		schemaExport.setOutputFile("D:\\Script.sql");
		schemaExport.create(EnumSet.of(TargetType.DATABASE), metadataSources.buildMetadata());
		



//		schemaScript = new ArrayList<String>();
//		schemaScript.add(String.format("use `%s`", databaseName));
////		statements = configuration.generateSchemaCreationScript(databaseDialect);
//		schemaScript.addAll(Arrays.asList(statements));
//		System.out.println("la lista de es: " + schemaScript);
//		executeScript(schemaScript);
	}

	private void executeScript(final List<String> script) {
		Session session;
		session = entityManager.unwrap(Session.class);		
 		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				Statement statement;
				
				statement = connection.createStatement();
				for (String line : script) {
					statement.execute(line);					
				}
				connection.commit();
			}
		});
	}

	public void openTransaction() {
		entityTransaction.begin();
	}

	public void commitTransaction() {
		entityTransaction.commit();
	}
	
	public void rollbackTransaction() {
		entityTransaction.rollback();
	}
	
	public void persist(DomainEntity entity) {
		entityManager.persist(entity);
		entityManager.flush();
	}

	public void close() {
		if (entityTransaction.isActive())
			entityTransaction.rollback();
		if (entityManager.isOpen())
			entityManager.close();
		if (entityManagerFactory.isOpen())
			entityManagerFactory.close();
	}
	
	
	private MetadataSources buildMetadata() throws SQLException {
//		MetadataSources metadata;
		Metamodel metamodel;
		Collection<EntityType<?>> entities;
		Collection<EmbeddableType<?>> embeddables;
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobShare", "newuser", "pepe");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(properties);
		StandardServiceRegistry standar = builder.build();
	
//		metadataSources = new MetadataSources(
//			new StandardServiceRegistryBuilder()
//					.applySetting("hibernate.dialect", databaseDialect)
//					.applySetting("javax.persistence.schema-generation-connection", connection)
//					.build());
		metadataSources = new MetadataSources(standar);
	
		metamodel = entityManagerFactory.getMetamodel();
		
		entities = metamodel.getEntities();
		for (EntityType<?> entity : entities)
			metadataSources.addAnnotatedClass(entity.getJavaType());
		
		embeddables = metamodel.getEmbeddables();
		for (EmbeddableType<?> embeddable : embeddables)
			metadataSources.addAnnotatedClass(embeddable.getJavaType());
		
		return metadataSources;
	}
	
	private String findProperty(String property) {
		String result;		
		Object value;

		value = properties.get(property);
		if (value == null)
			throw new RuntimeException(String.format("Property `%s' not found", property));
		if (!(value instanceof String))
			throw new RuntimeException(String.format("Property `%s' is not a string", property));
		result = (String) value;
		if (StringUtils.isBlank(result))
			throw new RuntimeException(String.format("Property `%s' is blank", property));

		return result;
	}

	@SuppressWarnings("unused")
	private void printProperties(Map<String, Object> properties) {
		for (Entry<String, Object> entry : properties.entrySet())
			System.out.println(String.format("%s=`%s'", entry.getKey(), entry.getValue()));
	}

	public int executeUpdate(String line) {
		int result;
		Query query;

		query = entityManager.createQuery(line);
		result = query.executeUpdate();
		
		return result;
	}
	
	public List<?> executeSelect(String line) {
		List<?> result;
		Query query;
		
		query = entityManager.createQuery(line);		
		result = (List<?>)query.getResultList();
		
		return result;
	}

}
