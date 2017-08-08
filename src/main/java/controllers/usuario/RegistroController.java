package controllers.usuario;

import controllers.AbstractController;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.UsuarioService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class RegistroController extends AbstractController {
   
   // =============== Services =======
   @Autowired
   private UsuarioService usuarioService;
   
   // =========== Creation ===========
   
   @GetMapping("/registro")
   public ModelAndView create() {
      ModelAndView res;
      
      UsuarioForm usuario = new UsuarioForm();
      
      res = createEditModelAndView(usuario);
      return res;
   }
   
   // =========== Edition =============

//   @GetMapping("/edit")
//   public ModelAndView edit(@RequestParam int usuarioId) {
//
//      ModelAndView result;
//      UsuarioForm usuario;
//
//      usuario = usuarioService.findOne(usuarioId);
//      Assert.notNull(usuario);
//      result = createEditModelAndView(usuario);
//
//      return result;
//   }
   
   @PostMapping(value = "/registro", params = "saveForm")
   public ModelAndView save(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult binding) {
      
      ModelAndView result;
      
      if (binding.hasErrors()) {
         result = createEditModelAndView(usuarioForm);
         List<String> errores = usuarioService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            usuarioService.registrarUsuario(usuarioForm);
            result = new ModelAndView("redirect:/");
         } catch (Throwable oops) {
            result = createEditModelAndView(usuarioForm);
           
         }
      }
      return result;
      
   }

//   @PostMapping(value = "/edit", params = "delete")
//   public ModelAndView delete(UsuarioForm usuarioForm, BindingResult binding) {
//
//      ModelAndView result;
//
//      try {
//         usuarioService.delete(usuarioForm);
//         result = new ModelAndView("redirect:list.do");
//      } catch (Throwable oops) {
//         result = createEditModelAndView(usuarioForm, "usuario.commit.error");
//      }
//      return result;
//
//   }
   
   // =========== Ancillary Methods ===========
   protected ModelAndView createEditModelAndView(UsuarioForm usuario) {
      ModelAndView res;
      
      res = createEditModelAndView(usuario, null);
      
      return res;
   }
   
   protected ModelAndView createEditModelAndView(UsuarioForm usuarioForm, String message) {
      ModelAndView res;
      
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("usuario/create");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("message", message);
      
      return res;
   }
}
