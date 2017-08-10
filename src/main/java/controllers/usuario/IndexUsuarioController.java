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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class IndexUsuarioController extends AbstractController {
   public IndexUsuarioController() {
      super();
   }
   
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
   
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      
      if (binding.hasErrors()) {
         result = createEditModelAndView(usuarioForm);
         errores = usuarioService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
//            if(!usuarioForm.getPassword().equals(usuarioForm.getConfirmarPassword())){
//               erroresCheck.add("usuario.coincidenciaPasswords");
//               errores.add("password");
//               errores.add("confirmarPassword");
//            }
            usuarioService.registrarUsuario(usuarioForm);
            result = new ModelAndView("redirect:/");
         } catch (Throwable oops) {
            result = createEditModelAndView(usuarioForm);
   
            if (oops.getLocalizedMessage().equals("usuario.coincidenciaPasswords")) {
               erroresCheck.add(oops.getLocalizedMessage());
               errores.add("password");
               errores.add("confirmarPassword");
            }
            if (oops.getLocalizedMessage().contains("ConstraintViolationException")) {
               erroresCheck.add("usuario.duplicado");
            }
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   @GetMapping(value = "/convertirse")
   public ModelAndView convertirse() {
      ModelAndView res;
      usuarioService.convertirse();
      
      res = new ModelAndView("redirect:/");
      return res;
   }
   
   
   // =========== Ancillary Methods ===========
   protected ModelAndView createEditModelAndView(UsuarioForm usuario) {
      ModelAndView res;
      
      res = createEditModelAndView(usuario, null);
      
      return res;
   }
   
   protected ModelAndView createEditModelAndView(UsuarioForm usuarioForm, String message) {
      ModelAndView res;
   
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("usuario/create");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      
      return res;
   }
}
