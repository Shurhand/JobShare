
package controllers;

import forms.BuscaForm;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractController {
   
   // Panic handler ----------------------------------------------------------
   
   @ExceptionHandler(Throwable.class)
   public ModelAndView panic(Throwable oops) {
      ModelAndView result;
      
      result = new ModelAndView("misc/panic");
      result.addObject("name", ClassUtils.getShortName(oops.getClass()));
      result.addObject("exception", oops.getMessage());
      result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));
      
      return result;
   }
   
   protected BuscaForm limpiarComas(BuscaForm buscaForm) {
      if (buscaForm == null) buscaForm = new BuscaForm();
      
      String palabrasClave = buscaForm.getPalabraClave() != null ? buscaForm.getPalabraClave().replaceAll(",", "") : "";
      String provincia = buscaForm.getProvincia() != null ? buscaForm.getProvincia().replaceAll(",", "") : "";
      String presupuesto = buscaForm.getPresupuesto() != null ? buscaForm.getPresupuesto().replaceAll(",", "") : "";
      String fechaCaducidad = buscaForm.getFechaCaducidad() != null ? buscaForm.getFechaCaducidad().replaceAll(",", "") : "";
      
      buscaForm.setPalabraClave(palabrasClave);
      buscaForm.setProvincia(provincia);
      buscaForm.setPresupuesto(presupuesto);
      buscaForm.setFechaCaducidad(fechaCaducidad);
      
      return buscaForm;
   }
   
}
