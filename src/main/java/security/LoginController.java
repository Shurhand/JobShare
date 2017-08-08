
package security;

import controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/security")
public class LoginController extends AbstractController {
   // Supporting services ----------------------------------------------------
   @Autowired
   LoginService service;
   
   // Constructors -----------------------------------------------------------
   
   public LoginController() {
      super();
   }
   
   // Login ------------------------------------------------------------------
   
   @RequestMapping("/login")
   public ModelAndView login(
                               @Valid @ModelAttribute Credenciales credenciales,
                               BindingResult bindingResult,
                               @RequestParam(required = false) boolean showError) {
      Assert.notNull(credenciales);
      Assert.notNull(bindingResult);
      
      ModelAndView result;
      
      result = new ModelAndView("welcome/index");
      result.addObject("credenciales", credenciales);
      result.addObject("showError", showError);
      
      return result;
   }
   
   // LoginFailure -----------------------------------------------------------
   
   @RequestMapping("/loginFailure")
   public ModelAndView failure(@Valid @ModelAttribute Credenciales credenciales,
                               BindingResult bindingResult,
                               @RequestParam(required = false) boolean showError) {
      ModelAndView result;
      Assert.notNull(credenciales);
      Assert.notNull(bindingResult);
      
      result = new ModelAndView("redirect:login.do?showError=true");
      result.addObject("credenciales", credenciales);
      
      return result;
   }
   
}