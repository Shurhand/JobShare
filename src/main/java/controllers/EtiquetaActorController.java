package controllers;

import domain.Etiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.EtiquetaService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/etiqueta/actor")
public class EtiquetaActorController extends AbstractController {
   @Autowired
   private EtiquetaService etiquetaService;
   
   @GetMapping("/crear")
   public ModelAndView crear() {
      ModelAndView res;
      Etiqueta etiqueta = etiquetaService.create();
      
      res = new ModelAndView("etiqueta/actor/crear");
      res.addObject("etiqueta", etiqueta);
      
      return res;
   }
   
   @PostMapping(value = "/crear", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Etiqueta etiqueta, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      Collection<String> nombreEtiquetas = etiquetaService.getNombreEtiquetas();
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(etiqueta);
         errores = etiquetaService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (nombreEtiquetas.contains(etiqueta.getNombre())) {
               hayError = true;
               erroresCheck.add("etiqueta.error.duplicado");
               errores.add("nombre");
            }
            if (! hayError) {
               etiquetaService.save(etiqueta);
               result = new ModelAndView("redirect:/etiqueta/lista.do");
            } else {
               result = crearEditarModelo(etiqueta);
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(etiqueta);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   protected ModelAndView crearEditarModelo(Etiqueta etiqueta) {
      ModelAndView res;
      
      res = new ModelAndView("etiqueta/actor/crear");
      res.addObject("etiqueta", etiqueta);
      
      return res;
   }
}
