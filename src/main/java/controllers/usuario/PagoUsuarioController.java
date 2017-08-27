package controllers.usuario;

import controllers.AbstractController;
import forms.PagoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.UsuarioService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("pago/usuario")
public class PagoUsuarioController extends AbstractController {
   @Autowired
   private UsuarioService usuarioService;
   
   @PostMapping("/pagar")
   public ModelAndView pagar(HttpServletRequest request, @Valid @ModelAttribute PagoForm pagoForm, BindingResult binding) {
      
      ModelAndView res = null;
      System.out.println("ENTRANDO EN PAGO");
      
      return res;
   }
}
