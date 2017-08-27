package controllers.profesional;

import controllers.AbstractController;
import domain.*;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/oferta/profesional")
public class OfertaProfesionalController extends AbstractController {
   // =============== Services =======
   @Autowired
   private ProfesionalService profesionalService;
   @Autowired
   private OfertaService ofertaService;
   @Autowired
   private EtiquetaService etiquetaService;
   @Autowired
   private ItemService itemService;
   @Autowired
   private ActorService actorService;
   
   @GetMapping("/misOfertas")
   public ModelAndView misOfertas() {
      ModelAndView res;
      Profesional profesional = profesionalService.findProfesional();
      BuscaForm buscaForm = new BuscaForm();
      Set<Peticion> peticiones = ofertaService.getPeticionesPorOfertasActivasPorProfesional();
      
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("oferta/profesional/misOfertas");
      res.addObject("peticiones", peticiones);
      res.addObject("buscaForm", buscaForm);
      res.addObject("profesional", profesional);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      return res;
   }
   
   @GetMapping("/misOfertasContratadas")
   public ModelAndView misOfertasContratadas() {
      ModelAndView res;
      Profesional profesional = profesionalService.findProfesional();
      BuscaForm buscaForm = new BuscaForm();
      Set<Peticion> peticiones = ofertaService.getPeticionesPorOfertasContratadasPorProfesional();
      
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("oferta/profesional/misOfertasContratadas");
      res.addObject("peticiones", peticiones);
      res.addObject("buscaForm", buscaForm);
      res.addObject("profesional", profesional);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      return res;
   }
   
   @GetMapping("/buscarMisOfertas")
   public ModelAndView buscarMisofertas(@Valid @ModelAttribute BuscaForm buscaForm) {
      ModelAndView res;
      
      Collection<Peticion> ofertas = ofertaService.getMisOfertasActivas(buscaForm);
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("oferta/profesional/misOfertas");
      res.addObject("peticiones", ofertas);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      
      return res;
   }
   
   @GetMapping("/buscarMisOfertasContratadas")
   public ModelAndView buscarMisofertasContratadas(@Valid @ModelAttribute BuscaForm buscaForm) {
      ModelAndView res;
      
      Collection<Peticion> ofertas = ofertaService.getMisOfertasContratadas(buscaForm);
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("oferta/profesional/misOfertasContratadas");
      res.addObject("peticiones", ofertas);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      
      return res;
   }


//    =========== Edition =============

   @GetMapping("/crear")
   public ModelAndView crear(@RequestParam int itemID) {
   
      ModelAndView res;
      Item item = itemService.findOne(itemID);
      Oferta oferta = ofertaService.create();
      oferta.setItem(item);
      
      res = new ModelAndView("oferta/profesional/crear");
      res.addObject("oferta", oferta);
      res.addObject("item", item);
      actorService.addNombre(res);
   
      return res;
   }
   
   @GetMapping("/editar")
   public ModelAndView editar(@RequestParam int ofertaID) {
      
      ModelAndView result;
      Oferta oferta = ofertaService.findOne(ofertaID);
      Item item = oferta.getItem();
      
      result = crearEditarModelo(oferta);
      result.addObject("item", item);
      return result;
   }
   
   @PostMapping(value = "/editar", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Oferta oferta, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(oferta);
         errores = ofertaService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! hayError) {
               ofertaService.save(oferta);
               result = new ModelAndView("redirect:/peticion/ver.do?peticionID=" + oferta.getItem().getPeticion().getId());
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(oferta);
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   @GetMapping("/borrar")
   public ModelAndView delete(@RequestParam int ofertaID) {
      ModelAndView result;
      
      Oferta oferta = ofertaService.findOne(ofertaID);
      ofertaService.delete(oferta);
      
      result = new ModelAndView("redirect:/peticion/ver.do?peticionID=" + oferta.getItem().getPeticion().getId());
      return result;
   }
   
   private ModelAndView crearEditarModelo(Oferta oferta) {
      ModelAndView res;
      
      String vista = oferta.getId() != 0 ? "oferta/profesional/editar" : "oferta/profesional/crear";
      
      res = new ModelAndView(vista);
      res.addObject("oferta", oferta);
      res.addObject("item", oferta.getItem());
      actorService.addNombre(res);

      
      return res;
   }
}
