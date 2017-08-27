
package controllers;

import domain.Etiqueta;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import services.ActorService;
import services.EtiquetaService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {
   @Autowired
   private LoginService loginService;
   @Autowired
   private EtiquetaService etiquetaService;
   @Autowired
   private ActorService actorService;
   // Constructors -----------------------------------------------------------
   
   public WelcomeController() {
      super();
   }
   
   // Index ------------------------------------------------------------------
   
   @RequestMapping(value = "/index")
   public ModelAndView index(@RequestParam(required = false, defaultValue = "JobShare") String name, @Valid @ModelAttribute BuscaForm buscaForm, BindingResult bindingResult, @RequestParam(required = false) boolean showError) {
      ModelAndView result;
      SimpleDateFormat formatter;
      String moment;
      Assert.notNull(buscaForm);
      Assert.notNull(bindingResult);
   
      Comparator<Etiqueta> comparator = Comparator.comparing(x -> x.getNombre());
      SortedSet<Etiqueta> todasEtiquetas = new TreeSet<>(comparator);
      todasEtiquetas.addAll(etiquetaService.getEtiquetasActivas());
      
      formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      moment = formatter.format(new Date());
      
      result = new ModelAndView("welcome/index");
      result.addObject("name", name);
      result.addObject("todasEtiquetas", todasEtiquetas);
      result.addObject("moment", moment);
      result.addObject("buscaForm", buscaForm);
      result.addObject("showError", showError);
      actorService.addNombre(result);
      
      return result;
   }
   
   @RequestMapping(value = "/about")
   public ModelAndView about() {
      ModelAndView result;
      SimpleDateFormat formatter;
      String moment;
      
      formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      moment = formatter.format(new Date());
      
      result = new ModelAndView("welcome/index/about");
      result.addObject("moment", moment);
      actorService.addNombre(result);
      
      
      return result;
   }
   
   @RequestMapping(value = "/tips")
   public ModelAndView tips() {
      ModelAndView result;
      SimpleDateFormat formatter;
      String moment;
      
      formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      moment = formatter.format(new Date());
      
      result = new ModelAndView("welcome/index/tips");
      result.addObject("moment", moment);
      actorService.addNombre(result);
      
      
      return result;
   }
   
}
