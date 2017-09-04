package services;

import domain.Etiqueta;
import domain.Usuario;
import domain.Valoracion;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"})
@Transactional
@Commit
public class AdminTests extends AbstractTest {
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private AdminService adminService;
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
   // | Requisito funcional: 16. Eliminar etiqueta            	  |
   // | 				            		                        	  |
   // ===========================================================
   
   // Test positivo eliminando una etiqueta
   @Test
   public void testEliminarEtiqueta1() {
      authenticate("admin");
      Etiqueta etiqueta = etiquetaService.findOne(51);
      etiquetaService.activar(etiqueta);
   }
   
   // Test negativo. Intentando eliminar una etiqueta como usuario
   @Test(expected = IllegalArgumentException.class)
   public void testEliminarEtiqueta2() {
      authenticate("usuario1");
      Etiqueta etiqueta = etiquetaService.findOne(51);
      boolean esAdmin = actorService.isAdmin();
      Assert.isTrue(esAdmin);
      
      etiquetaService.activar(etiqueta);
   }
   
   // Test negativo. Intentando eliminar una etiqueta como profesional
   @Test(expected = IllegalArgumentException.class)
   public void testEliminarEtiqueta3() {
      authenticate("profesional1");
      Etiqueta etiqueta = etiquetaService.findOne(51);
      boolean esAdmin = actorService.isAdmin();
      Assert.isTrue(esAdmin);
      
      etiquetaService.activar(etiqueta);
   }
   
   //============================================================
   // | Requisito funcional: 17. Bloquear usuario            	  |
   // | 				            		                        	  |
   // ===========================================================
   
   // Test positivo bloqueando usuario
   @Test
   public void testBloquearUsuario1() {
      authenticate("admin");
      Usuario usuario = usuarioService.findOne(7);
      adminService.bloquear(usuario);
   }
   
   // Test negativo bloqueando usuario como usuario
   @Test(expected = IllegalArgumentException.class)
   public void testBloquearUsuario2() {
      authenticate("usuario1");
      Usuario usuario = usuarioService.findOne(7);
      boolean esAdmin = actorService.isAdmin();
      Assert.isTrue(esAdmin);
      adminService.bloquear(usuario);
   }
   
   // Test negativo bloqueando usuario como profesional
   @Test(expected = IllegalArgumentException.class)
   public void testBloquearUsuario3() {
      authenticate("profesional1");
      Usuario usuario = usuarioService.findOne(7);
      boolean esAdmin = actorService.isAdmin();
      Assert.isTrue(esAdmin);
      adminService.bloquear(usuario);
   }
   
   //============================================================
   // | Requisito funcional: 19. Eliminar valoración          	  |
   // | 				            		                        	  |
   // ===========================================================
   
   //Test positivo eliminando valoración
   @Test
   public void testEliminarValoracion1() {
      authenticate("admin");
      Valoracion valoracion = valoracionService.findOne(24);
      valoracionService.delete(valoracion);
   }
   
   //Test negativo eliminando valoración como usuario
   @Test(expected = IllegalArgumentException.class)
   public void testEliminarValoracion2() {
      authenticate("usuario1");
      Valoracion valoracion = valoracionService.findOne(24);
      boolean esAdmin = actorService.isAdmin();
      Assert.isTrue(esAdmin);
      valoracionService.delete(valoracion);
   }
   
   //Test negativo eliminando valoración como profesional
   @Test(expected = IllegalArgumentException.class)
   public void testEliminarValoracion3() {
      authenticate("profesional1");
      Valoracion valoracion = valoracionService.findOne(24);
      boolean esAdmin = actorService.isAdmin();
      Assert.isTrue(esAdmin);
      valoracionService.delete(valoracion);
   }
}
