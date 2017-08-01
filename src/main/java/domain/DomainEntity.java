/* DomainEntity.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import javax.persistence.*;


@Access(AccessType.PROPERTY)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DomainEntity {

	// Constructors -----------------------------------------------------------

	public DomainEntity() {
		super();
	}

	// Identification ---------------------------------------------------------

	private long id;
	private int version;
	private long hibernate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
//	@GenericGenerator(name = "int", strategy = "int")
//	@TableGenerator(table = "SEQUENCES", name = "HibernateGenerator")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getHibernate(){
		return hibernate;
	}
	
	public void setHibernate(long sequence){
	this.hibernate = sequence;
	}
	// Equality ---------------------------------------------------------------

	@Override
	public int hashCode() {
		return (int) (this.getId() * 31);
	}

	@Override
	public boolean equals(Object other) {
		boolean result;

		if (this == other)
			result = true;
		else if (other == null)
			result = false;
		else if (other instanceof Integer)
			result = (this.getId() == (Integer) other);
		else if (!this.getClass().isInstance(other))
			result = false;
		else
			result = (this.getId() == ((DomainEntity) other).getId());

		return result;
	}

}
