/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {
   
   // Constructors -----------------------------------------------------------
   
   public WelcomeController() {
      super();
   }
   
   // Index ------------------------------------------------------------------
   
   @RequestMapping(value = "/index")
   public ModelAndView index(@RequestParam(required = false, defaultValue = "JobShare") String name, @Valid @ModelAttribute Credenciales credenciales, BindingResult bindingResult, @RequestParam(required = false) boolean showError) {
      ModelAndView result;
      SimpleDateFormat formatter;
      String moment;
      Assert.notNull(credenciales);
      Assert.notNull(bindingResult);
      
      formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      moment = formatter.format(new Date());
      
      result = new ModelAndView("welcome/index");
      result.addObject("name", name);
      result.addObject("moment", moment);
      result.addObject("credentiales", credenciales);
      result.addObject("showError", showError);
      
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
      
      
      return result;
   }
   
   
}

//	@RequestMapping("/index")
//	public ModelAndView login(
//			@Valid @ModelAttribute Credenciales credenciales,
//			BindingResult bindingResult,
//			@RequestParam(required = false) boolean showError) {
//		Assert.notNull(credenciales);
//		Assert.notNull(bindingResult);
//		
//		ModelAndView result;
//
//		result = new ModelAndView("security/login");
//		result.addObject("credenciales", credenciales);
//		result.addObject("showError", showError);
//
//		return result;
//	}
//	
//	// LoginFailure -----------------------------------------------------------
//
//	@RequestMapping("/loginFailure")
//	public ModelAndView failure() {
//		ModelAndView result;
//
//		result = new ModelAndView("redirect:login.do?showError=true");
//
//		return result;
//	}
//}