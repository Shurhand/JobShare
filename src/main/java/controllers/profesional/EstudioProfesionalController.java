package controllers.profesional;

import controllers.AbstractController;
import domain.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.ActorService;
import services.EstudioService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("estudio/profesional")
public class EstudioProfesionalController extends AbstractController {
   @Autowired
   private EstudioService estudioService;
   @Autowired
   private ActorService actorService;
   // =========== Edition =============
   
   @GetMapping("/crear")
   public ModelAndView create() {
      
      ModelAndView res;
      Estudio estudio = estudioService.create();
      
      res = crearEditarModelo(estudio);
      
      return res;
   }
   
   @GetMapping("/editar")
   public ModelAndView edit(@RequestParam int estudioID) {
      
      ModelAndView result;
      Estudio estudio = estudioService.findOne(estudioID);
      
      result = crearEditarModelo(estudio);
      return result;
   }
   
   @PostMapping(value = "/editar", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Estudio estudio, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(estudio);
         errores = estudioService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (estudioService.checkFechaFin(estudio.getFechaFin())) {
               hayError = true;
               erroresCheck.add("estudio.error.fechaFinFutura");
               errores.add("fechaFin");
            }
            if (estudioService.checkFechas(estudio.getFechaInicio(), estudio.getFechaFin())) {
               hayError = true;
               erroresCheck.add("estudio.error.fechasInvalidas");
               errores.add("fechaInicio");
               errores.add("fechaFin");
            }
            if (! hayError) {
               estudioService.save(estudio);
               result = new ModelAndView("redirect:/profesional/perfil.do");
            } else {
               result = crearEditarModelo(estudio);
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(estudio);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   @GetMapping("/borrar")
   public ModelAndView delete(@RequestParam int estudioID) {
      ModelAndView result;
      
      Estudio estudio = estudioService.findOne(estudioID);
      estudioService.delete(estudio);
   
      result = new ModelAndView("redirect:/profesional/perfil.do");
      
      return result;
   }
   
   protected ModelAndView crearEditarModelo(Estudio estudio) {
      ModelAndView res;
      
      Credenciales credenciales = new Credenciales();
      String vista = estudio.getId() != 0 ? "estudio/profesional/editar" : "estudio/profesional/crear";
   
      res = new ModelAndView(vista);
      res.addObject("estudio", estudio);
      res.addObject("credenciales", credenciales);
      actorService.addNombre(res);
      
      return res;
   }
   
}
