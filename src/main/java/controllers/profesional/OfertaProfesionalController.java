package controllers.profesional;

import controllers.AbstractController;
import domain.Etiqueta;
import domain.Oferta;
import domain.Peticion;
import domain.Profesional;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.EtiquetaService;
import services.OfertaService;
import services.ProfesionalService;

import java.util.Collection;
import java.util.SortedSet;

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
   
   @GetMapping("/misOfertas")
   public ModelAndView misOfertas() {
      ModelAndView res;
      Profesional profesional = profesionalService.findProfesional();
      BuscaForm buscaForm = new BuscaForm();
      Collection<Peticion> peticiones = ofertaService.getPeticionesPorOfertasPorProfesional(profesional);
      
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("oferta/profesional/misOfertas");
      res.addObject("peticiones", peticiones);
      res.addObject("buscaForm", buscaForm);
      res.addObject("profesional", profesional);
      res.addObject("todasEtiquetas", todasEtiquetas);
      
      return res;
   }
   
   @GetMapping("/misOfertasContratadas")
   public ModelAndView misOfertasContratadas() {
      ModelAndView res;
      Profesional profesional = profesionalService.findProfesional();
      BuscaForm buscaForm = new BuscaForm();
      Collection<Peticion> peticiones = ofertaService.getPeticionesPorOfertasContratadasPorProfesional(profesional);
      
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("oferta/profesional/misOfertasContratadas");
      res.addObject("peticiones", peticiones);
      res.addObject("buscaForm", buscaForm);
      res.addObject("profesional", profesional);
      res.addObject("todasEtiquetas", todasEtiquetas);
      
      return res;
   }

//   @GetMapping("/buscarMisofertas")
//   public ModelAndView buscarMisofertas(@Valid @ModelAttribute BuscaForm buscaForm) {
//      ModelAndView res;
//
//      Collection<Oferta> ofertas = ofertaService.getMisofertasBuscadas(buscaForm);
//      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
//
//      String action = "oferta/profesional/buscarMisofertas.do";
//
//      res = new ModelAndView("oferta/profesional/buscar");
//      res.addObject("ofertas", ofertas);
//      res.addObject("todasEtiquetas", todasEtiquetas);
//      res.addObject("action", action);
//
//      return res;
//   }
//
//   @GetMapping("/buscarMisofertasCaducadas")
//   public ModelAndView buscarMisofertasCaducadas(@Valid @ModelAttribute BuscaForm buscaForm) {
//      ModelAndView res;
//
//      Collection<Oferta> ofertas = ofertaService.getMisofertasBuscadasCaducadas(buscaForm);
//      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
//
//      String action = "oferta/profesional/buscarMisofertasCaducadas.do";
//
//      res = new ModelAndView("oferta/profesional/buscarCaducadas");
//      res.addObject("ofertas", ofertas);
//      res.addObject("todasEtiquetas", todasEtiquetas);
//      res.addObject("action", action);
//
//
//      return res;
//   }
   
   
   // =========== Edition =============

//   @GetMapping("/crear")
//   public ModelAndView crear() {
//
//      ModelAndView res;
//
//      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
//
//      Oferta oferta = ofertaService.create();
//
//      res = new ModelAndView("oferta/profesional/crear");
//      res.addObject("oferta", oferta);
//      res.addObject("todasEtiquetas", todasEtiquetas);
//
//      return res;
//   }
//
//   @GetMapping("/editar")
//   public ModelAndView editar(@RequestParam int ofertaID) {
//
//      ModelAndView result;
//      Oferta oferta = ofertaService.findOne(ofertaID);
//
//      result = crearEditarModelo(oferta);
//      return result;
//   }
//
//   @PostMapping(value = "/editar", params = "save")
//   public ModelAndView save(@Valid @ModelAttribute Oferta oferta, BindingResult binding) {
//
//      ModelAndView result = null;
//      List<String> errores = new ArrayList<>();
//      List<String> erroresCheck = new ArrayList<>();
//      boolean hayError = false;
//
//      if (binding.hasErrors()) {
//         result = crearEditarModelo(oferta);
//         errores = ofertaService.getListaErrores(binding);
//         result.addObject("errores", errores);
//      } else {
//         try {
//            if (! ofertaService.checkFechaCaducidad(oferta.getFechaCaducidad())) {
//               hayError = true;
//               erroresCheck.add("oferta.error.fechaCaducidad");
//               errores.add("fechaCaducidad");
//            }
//            if (! hayError) {
//               ofertaService.save(oferta);
//               result = new ModelAndView("redirect:/oferta/ver.do?ofertaID=" + oferta.getId());
//            }
//         } catch (Throwable oops) {
//            result = crearEditarModelo(oferta);
//         } finally {
//            result.addObject("errores", errores);
//            result.addObject("erroresCheck", erroresCheck);
//         }
//      }
//      return result;
//
//   }
//
//   @GetMapping("/borrar")
//   public ModelAndView delete(@RequestParam int ofertaID) {
//      ModelAndView result;
//
//      Oferta oferta = ofertaService.findOne(ofertaID);
//      ofertaService.delete(oferta);
//
//      result = new ModelAndView("redirect:/oferta/profesional/misOfertas.do");
//
//      return result;
//   }
   
   private ModelAndView crearEditarModelo(Oferta oferta) {
      ModelAndView res;
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      String vista = oferta.getId() != 0 ? "oferta/profesional/editar" : "oferta/profesional/crear";
      
      res = new ModelAndView(vista);
      res.addObject("oferta", oferta);
      res.addObject("todasEtiquetas", todasEtiquetas);
      
      return res;
   }
}
