package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import security.LoginService;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"})
@Transactional
@Commit
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
   @Autowired
   private OfertaService ofertaService;
   @Autowired
   private PagoService pagoService;
   
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
   
   // Test positivo modificando petición del sistema
   @Test
   public void testModificarPeticion1() {
      authenticate("usuario1");
      Peticion peticion = peticionService.findOne(21);
      peticion.setProvincia("Cambio de provincia");
      
      peticionService.save(peticion);
      
   }
   
   // Test negativo. Intentando modificar una petición que no es del usuario
   @Test(expected = IllegalArgumentException.class)
   public void testModificarPeticion2() {
      authenticate("usuario1");
      Peticion peticion = peticionService.findOne(15);
      
      Assert.isTrue(usuarioService.findUsuario().equals(peticion.getUsuario()));
      
      peticion.setProvincia("Cambio de provincia");
      
      peticionService.save(peticion);
      
   }
   
   // Test negativo. Intentando modificar una petición colocando una fecha de Caducidad anterior a la actual
   @Test(expected = IllegalArgumentException.class)
   public void testModificarPeticion3() {
      authenticate("usuario1");
      Peticion peticion = peticionService.findOne(21);
      
      LocalDate fechaModificada = LocalDate.now().minusMonths(3);
      Assert.isTrue(fechaModificada.isAfter(LocalDate.now()));
      peticion.setFechaCaducidad(fechaModificada);
      
      peticionService.save(peticion);
      
   }
   
   //============================================================
   // | Requisito funcional: 5. Borrar petición               	  |
   // | 				            		                        	  |
   // ===========================================================
   
   // Test positivo borrando una petición.
   @Test
   public void testBorrarPeticion1() {
      authenticate("usuario1");
      Peticion peticion = peticionService.findOne(21);
      
      peticionService.delete(peticion);
   }
   
   // Test negativo intentando borrar una peticion que no es suya
   @Test(expected = IllegalArgumentException.class)
   public void testBorrarPeticion2() {
      authenticate("usuario1");
      Peticion peticion = peticionService.findOne(15);
      Assert.isTrue(usuarioService.findUsuario().equals(peticion.getUsuario()));
      peticionService.delete(peticion);
   }
   
   //============================================================
   // | Requisito funcional: 7. Modificar el perfil          	  |
   // | 				            		                        	  |
   // ===========================================================
   
   // Test positivo modificando el perfil
   @Test
   public void testMoidificarPerfil1() {
      authenticate("usuario1");
      Usuario usuario = usuarioService.findUsuario();
      
      usuario.setDescripcion("Nueva descripcion");
      
      usuarioService.save(usuario);
   }
   
   // Test negativo. Intentando modificar colocar una provincia con espacios en blanco
   @Test(expected = IllegalArgumentException.class)
   public void testMoidificarPerfil2() {
      authenticate("usuario1");
      Usuario usuario = usuarioService.findUsuario();
      
      usuario.setProvincia("");
      Assert.hasText(usuario.getProvincia());
      usuarioService.save(usuario);
      
   }
   
   // Test negativo. Cambiando de contraseña pero no coinciden
   @Test(expected = IllegalArgumentException.class)
   public void testMoidificarPerfil3() {
      authenticate("usuario1");
      Usuario usuario = usuarioService.findUsuario();
      String contrasenya1 = "Nueva";
      String contrasenya2 = "asdads";
      
      usuario.getCuenta().setPassword(contrasenya1);
      
      Assert.isTrue(contrasenya1.equals(contrasenya2));
      
      usuarioService.save(usuario);
      
   }
   
   //============================================================
   // | Requisito funcional: 8. Valorar usuario              	  |
   // | 				            		                        	  |
   // ===========================================================
   
   // Test positivo valorando a un profesional por su oferta
   @Test
   public void testValorar1() {
      authenticate("usuario1");
      
      Valoracion valoracion = valoracionService.create();
      valoracion.setPuntuacion(5.0);
      valoracion.setComentario("Comentario");
      
      Oferta ofertaAValorar = ofertaService.findOne(30);
      valoracion.setOferta(ofertaAValorar);
      
      valoracionService.save(valoracion);
      
   }
   
   // Test negativo. Intentando valorar una oferta que no esta contratada
   @Test(expected = IllegalArgumentException.class)
   public void testValorar2() {
      authenticate("usuario1");
      
      Valoracion valoracion = valoracionService.create();
      valoracion.setPuntuacion(5.0);
      valoracion.setComentario("Comentario");
      
      Oferta ofertaAValorar = ofertaService.findOne(27);
      Assert.isTrue(ofertaAValorar.getEstado().equals(Estado.CONTRATADA));
      
      valoracion.setOferta(ofertaAValorar);
      ofertaAValorar.setValoracion(valoracion);
      
      ofertaService.save(ofertaAValorar);
      
   }
   
   // Test negativo. Intentando valorar una oferta que no esta en su petición
   @Test(expected = IllegalArgumentException.class)
   public void testValorar3() {
      authenticate("usuario1");
      
      Valoracion valoracion = valoracionService.create();
      valoracion.setPuntuacion(5.0);
      valoracion.setComentario("Comentario");
      
      Oferta ofertaAValorar = ofertaService.findOne(17);
      valoracion.setOferta(ofertaAValorar);
      
      Assert.isTrue(usuarioService.findUsuario().getPeticiones().contains(ofertaAValorar.getItem().getPeticion()));
      
      valoracionService.save(valoracion);
      
   }
   
   //============================================================
   // | Requisito funcional: 10. Contratar presupuesto        	  |
   // | 				            		                        	  |
   // ===========================================================
   
   // Test positivo contratando presupuesto
   @Test
   public void testContratar1() {
      authenticate("usuario1");
      
      Oferta ofertaAContratar = ofertaService.findOne(28);
      ofertaAContratar.setEstado(Estado.CONTRATADA);
      
      ofertaService.save(ofertaAContratar);
      
      Pago pago = pagoService.create();
      pago.getItems().add(ofertaAContratar.getItem());
      
      pagoService.save(pago);
      
   }
   
   // Test negativo. Intentando contratar una oferta que no esta en una de sus peticiones
   @Test(expected = IllegalArgumentException.class)
   public void testContratar2() {
      authenticate("usuario1");
      
      Oferta ofertaAContratar = ofertaService.findOne(17);
      Assert.isTrue(usuarioService.findUsuario().getPeticiones().contains(ofertaAContratar.getItem().getPeticion()));
      
      ofertaAContratar.setEstado(Estado.CONTRATADA);
      
      ofertaService.save(ofertaAContratar);
      
      Pago pago = pagoService.create();
      pago.getItems().add(ofertaAContratar.getItem());
      
      pagoService.save(pago);
      
   }
   
   // Test negativo. Intentando contratar una oferta que ya está contratada
   @Test(expected = IllegalArgumentException.class)
   public void testContratar3() {
      authenticate("usuario1");
      
      Oferta ofertaAContratar = ofertaService.findOne(23);
      Assert.isTrue(ofertaAContratar.getEstado().equals(Estado.ACTIVA));
      
      ofertaAContratar.setEstado(Estado.CONTRATADA);
      
      ofertaService.save(ofertaAContratar);
      
      Pago pago = pagoService.create();
      pago.getItems().add(ofertaAContratar.getItem());
      
      pagoService.save(pago);
      
   }
   

   
}
