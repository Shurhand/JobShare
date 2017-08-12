package controllers.profesional;

import controllers.AbstractController;
import domain.Trabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.TrabajoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("trabajo/profesional")
public class TrabajoProfesionalController extends AbstractController {
   @Autowired
   private TrabajoService trabajoService;
   
   // =========== Edition =============
   
   @GetMapping("/crear")
   public ModelAndView create() {
      
      ModelAndView res;
      Trabajo trabajo = trabajoService.create();
      
      res = crearEditarModelo(trabajo);
      
      return res;
   }
   
   @GetMapping("/editar")
   public ModelAndView edit(@RequestParam int trabajoID) {
      
      ModelAndView result;
      Trabajo trabajo = trabajoService.findOne(trabajoID);
      
      result = crearEditarModelo(trabajo);
      return result;
   }
   
   @PostMapping(value = "/editar", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Trabajo trabajo, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(trabajo);
         errores = trabajoService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! trabajoService.checkFechaFin(trabajo.getFechaFin())) {
               hayError = true;
               erroresCheck.add("trabajo.error.fechaFinFutura");
               errores.add("fechaFin");
            }
            if (! trabajoService.checkFechas(trabajo.getFechaInicio(), trabajo.getFechaFin())) {
               hayError = true;
               erroresCheck.add("trabajo.error.fechasInvalidas");
               errores.add("fechaInicio");
               errores.add("fechaFin");
            }
            if (! hayError) {
               trabajoService.save(trabajo);
               result = new ModelAndView("redirect:/profesional/perfil.do");
            } else {
               result = crearEditarModelo(trabajo);
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(trabajo);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   @GetMapping("/borrar")
   public ModelAndView delete(@RequestParam int trabajoID) {
      ModelAndView result;
      
      Trabajo trabajo = trabajoService.findOne(trabajoID);
      trabajoService.delete(trabajo);
   
      result = new ModelAndView("redirect:/profesional/perfil.do");
      
      return result;
   }
   
   protected ModelAndView crearEditarModelo(Trabajo trabajo) {
      ModelAndView res;
      
      Credenciales credenciales = new Credenciales();
      String vista = trabajo.getId() != 0 ? "trabajo/profesional/editar" : "trabajo/profesional/crear";
   
      res = new ModelAndView(vista);
      res.addObject("trabajo", trabajo);
      res.addObject("credenciales", credenciales);
      
      return res;
   }
   
}
