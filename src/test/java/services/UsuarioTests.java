package services;

import domain.Actor;
import domain.Peticion;
import domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;
import security.Autoridad;
import security.Cuenta;
import security.LoginService;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class UsuarioTests extends AbstractTest {
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private LoginService loginService;
   @Autowired
   private ProfesionalService profesionalService;
   @Autowired
   private ItemService itemService;
   @Autowired
   private EtiquetaService etiquetaService;
   @Autowired
   private PeticionService peticionService;
   @Autowired
   private ValoracionService valoracionService;
   
   //============================================================
   // | Requisito funcional: 1. Registrarse en la aplicación	  |
   // | 				            		                        	  |
   // ===========================================================
   
   //Test positivo registrándose correctamente
   @Test
   public void testRegistro1() {
      
      Usuario usuario = usuarioService.create();
      Cuenta cuenta = new Cuenta();
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      
      usuario.setNombre("Jon");
      usuario.setApellidos("Nieve");
      usuario.setCp("41010");
      usuario.setDNI("28860272X");
      usuario.setTelefono("768123123");
      usuario.setEmail("Jon@gmail.com");
      usuario.setFoto(null);
      usuario.setProvincia("Invernalia");
      usuario.setDescripcion("Guardia de la Noche");
      
      cuenta.setIsActivated(true);
      cuenta.setIsGoogle(false);
      cuenta.setUsername("jonNieve");
      String password = md5PassWordEncoder.encodePassword("valarMorgulis", null);
      cuenta.setPassword(password);
      
      Collection<Autoridad> auths = new ArrayList<>();
      Autoridad auth = new Autoridad();
      auth.setAuthority("USUARIO");
      auths.add(auth);
      
      cuenta.setAuthorities(auths);
      
      usuario.setCuenta(cuenta);
      
      usuarioService.save(usuario);
   }
   
   //Test negativo. Intentando registrar un DNI no válido
   @Test(expected = IllegalArgumentException.class)
   public void testRegistro2() {
      
      Usuario usuario = usuarioService.create();
      Cuenta cuenta = new Cuenta();
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      
      usuario.setNombre("Jon");
      usuario.setApellidos("Nieve");
      usuario.setCp("41010");
      usuario.setDNI("asdasdasd");
      usuario.setTelefono("768123123");
      usuario.setEmail("Jon@gmail.com");
      usuario.setFoto(null);
      usuario.setProvincia("Invernalia");
      usuario.setDescripcion("Guardia de la Noche");
      
      cuenta.setIsActivated(true);
      cuenta.setIsGoogle(false);
      cuenta.setUsername("jonNieve");
      String password = md5PassWordEncoder.encodePassword("valarMorgulis", null);
      cuenta.setPassword(password);
      
      Collection<Autoridad> auths = new ArrayList<>();
      Autoridad auth = new Autoridad();
      auth.setAuthority("USUARIO");
      auths.add(auth);
      
      cuenta.setAuthorities(auths);
      
      usuario.setCuenta(cuenta);
      
      Assert.isTrue(actorService.checkDni(usuario.getDNI()));
      
      usuarioService.save(usuario);
   }
   
   //Test negativo. Intentando guardar un correo que ya existe.
   @Test(expected = IllegalArgumentException.class)
   public void testRegistro3() {
      
      Usuario usuario = usuarioService.create();
      Cuenta cuenta = new Cuenta();
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      
      usuario.setNombre("Jon");
      usuario.setApellidos("Nieve");
      usuario.setCp("41010");
      usuario.setDNI("28860272X");
      usuario.setTelefono("768123123");
      usuario.setEmail("BruceLee@gmail.com");
      usuario.setFoto(null);
      usuario.setProvincia("Invernalia");
      usuario.setDescripcion("Guardia de la Noche");
      
      cuenta.setIsActivated(true);
      cuenta.setIsGoogle(false);
      cuenta.setUsername("jonNieve");
      String password = md5PassWordEncoder.encodePassword("valarMorgulis", null);
      cuenta.setPassword(password);
      
      Collection<Autoridad> auths = new ArrayList<>();
      Autoridad auth = new Autoridad();
      auth.setAuthority("USUARIO");
      auths.add(auth);
      
      cuenta.setAuthorities(auths);
      
      usuario.setCuenta(cuenta);
      
      Actor actor = actorService.findActorPorEmail("BruceLee@gmail.com");
      
      Assert.isNull(actor);
      
      usuarioService.save(usuario);
   }
   
   //============================================================
   // | Requisito funcional: 2. Iniciar sesión              	  |
   // | 				            		                        	  |
   // ===========================================================
   
   //Test positivo registrandose y luego iniciando sesión
   @Test
   public void testIniciarSesion1() {
      
      Usuario usuario = usuarioService.create();
      Cuenta cuenta = new Cuenta();
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      
      usuario.setNombre("Jon");
      usuario.setApellidos("Nieve");
      usuario.setCp("41010");
      usuario.setDNI("28860272X");
      usuario.setTelefono("768123123");
      usuario.setEmail("Jon@gmail.com");
      usuario.setFoto(null);
      usuario.setProvincia("Invernalia");
      usuario.setDescripcion("Guardia de la Noche");
      
      cuenta.setIsActivated(true);
      cuenta.setIsGoogle(false);
      cuenta.setUsername("jonNieve");
      String password = md5PassWordEncoder.encodePassword("valarMorgulis", null);
      cuenta.setPassword(password);
      
      Collection<Autoridad> auths = new ArrayList<>();
      Autoridad auth = new Autoridad();
      auth.setAuthority("USUARIO");
      auths.add(auth);
      
      cuenta.setAuthorities(auths);
      
      usuario.setCuenta(cuenta);
      
      usuarioService.save(usuario);
      
      authenticate("jonNieve");
   }
   
   //============================================================
   // | Requisito funcional: 6. Crear petición               	  |
   // | 				            		                        	  |
   // ===========================================================
   
   //Test positivo creando petición
   @Test
   public void testCrearPeticion1() {
      authenticate("usuario1");
      Peticion peticion = peticionService.create();
      peticion.setDescripcion("Descripcion");
      peticion.setTitulo("Titulo");
      peticion.setProvincia("Provincia");
      peticion.setFechaCaducidad(LocalDate.now().plusMonths(1));
      
      peticionService.save(peticion);
   }
   
   //Test negativo. Creando petición con fecha de caducidad anterior a la actual
   @Test(expected = IllegalArgumentException.class)
   public void testCrearPeticion2() {
      authenticate("usuario1");
      Peticion peticion = peticionService.create();
      peticion.setDescripcion("Descripcion");
      peticion.setTitulo("Titulo");
      peticion.setProvincia("Provincia");
      peticion.setFechaCaducidad(LocalDate.now().minusMonths(1));
      
      Assert.isTrue(peticionService.checkFechaCaducidad(peticion.getFechaCaducidad()));
      peticionService.save(peticion);
   }
   
   //Test negativo. Creando petición con fecha de caducidad nula
   @Test(expected = IllegalArgumentException.class)
   public void testCrearPeticion3() {
      authenticate("usuario1");
      Peticion peticion = peticionService.create();
      peticion.setDescripcion("Descripcion");
      peticion.setTitulo("Titulo");
      peticion.setProvincia("Provincia");
      peticion.setFechaCaducidad(null);
      
      Assert.notNull(peticion.getFechaCaducidad());
      peticionService.save(peticion);
   }
   
   //============================================================
   // | Requisito funcional: 4. Modificar petición           	  |
   // | 				            		                        	  |
   // ===========================================================
   
   
}
