package services;

import domain.Item;
import domain.Oferta;
import domain.Profesional;
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
public class ProfesionalTests extends AbstractTest {
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
   
   //============================================================
   // | Requisito funcional: 12. Ofrecer presupuesto        	  |
   // | 				            		                        	  |
   // ===========================================================
   
   //Test positivo ofreciendo presupuesto
   @Test
   public void testOfrecerPresupuesto1() {
      authenticate("profesional1");
      Item item = itemService.findOne(32);
      Oferta oferta = ofertaService.create();
      oferta.setPrecio(10.);
      oferta.setComentario("Comentario");
      oferta.setItem(item);
      
      ofertaService.save(oferta);
   }
   
   //Test negativo. Intentando ofrecer mas del presupuesto del item
   @Test(expected = IllegalArgumentException.class)
   public void testOfrecerPresupuesto2() {
      authenticate("profesional1");
      Item item = itemService.findOne(32);
      Oferta oferta = ofertaService.create();
      oferta.setPrecio(2500.);
      oferta.setComentario("Comentario");
      oferta.setItem(item);
      
      Assert.isTrue(oferta.getPrecio() <= item.getPresupuesto());
      
      ofertaService.save(oferta);
   }
   
   //Test negativo. Intentando realizar un presupuesto negativo
   @Test(expected = IllegalArgumentException.class)
   public void testOfrecerPresupuesto3() {
      authenticate("profesional1");
      Item item = itemService.findOne(32);
      Oferta oferta = ofertaService.create();
      oferta.setPrecio(- 1.);
      oferta.setComentario("Comentario");
      oferta.setItem(item);
      
      Assert.isTrue(oferta.getPrecio() >= 1.);
      
      ofertaService.save(oferta);
   }
   
   //============================================================
   // | Requisito funcional: 13. Eliminar oferta              	  |
   // | 				            		                        	  |
   // ===========================================================
   
   //Test positivo eliminando ofertas propias
   @Test
   public void testEliminarOferta1() {
      authenticate("profesional1");
      Profesional profesional = profesionalService.findProfesional();
      profesional.getOfertas().forEach(x -> ofertaService.delete(x));
   }
   
   //Test negativo eliminando oferta que no es propia
   @Test(expected = IllegalArgumentException.class)
   public void testEliminarOferta2() {
      authenticate("profesional1");
      Profesional profesional = profesionalService.findProfesional();
      Oferta oferta = ofertaService.findOne(25);
      
      Assert.isTrue(profesional.equals(oferta.getProfesional()));
      
      ofertaService.delete(oferta);
   }
}
