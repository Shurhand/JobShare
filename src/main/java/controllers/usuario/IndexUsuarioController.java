package controllers.usuario;

import controllers.AbstractController;
import domain.Usuario;
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
import services.ActorService;
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
   @Autowired
   private ActorService actorService;
   
   @GetMapping("/perfil")
   public ModelAndView verPerfil() {
      ModelAndView res;
      
      Usuario usuario = usuarioService.findUsuario();
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("usuario/perfil");
      res.addObject("usuario", usuario);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      
      return res;
      
   }
   
   // =========== Perfil =============
   
   @GetMapping("/modificarPerfil")
   public ModelAndView modificarPerfil() {
      ModelAndView res;
      Usuario usuario = usuarioService.findUsuario();
      UsuarioForm usuarioForm = actorService.convertirActor(usuario);
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("usuario/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      
      return res;
   }
   
   @PostMapping(value = "/modificarPerfil", params = "saveForm")
   public ModelAndView savePerfil(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      Usuario usuario = usuarioService.findUsuario();
      Collection<String> allUsernames = actorService.getAllUsernames();
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allUsernames.remove(usuario.getCuenta().getUsername());
      allEmails.remove(usuario.getEmail());
      allDNIs.remove(usuario.getDNI());
      
      if (binding.hasErrors()) {
         result = crearEditarModeloPerfil(usuarioForm);
         errores = usuarioService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! actorService.checkPassword(usuarioForm)) {
               hayError = true;
               erroresCheck.add("usuario.error.coincidenciaPasswords");
               errores.add("password");
               errores.add("confirmarPassword");
            }
            if (! actorService.checkDni(usuarioForm.getDNI())) {
               hayError = true;
               erroresCheck.add("usuario.error.dniIncorrecto");
               errores.add("DNI");
            }
            if (usuarioForm.getProvincia().equals("-----")) {
               hayError = true;
               erroresCheck.add("usuario.error.escogeProvincia");
               errores.add("provincia");
            }
            if (allUsernames.contains(usuarioForm.getUsername())) {
               hayError = true;
               erroresCheck.add("usuario.error.usernameDuplicado");
               errores.add("username");
            }
            if (allEmails.contains(usuarioForm.getEmail())) {
               hayError = true;
               erroresCheck.add("usuario.error.emailDuplicado");
               errores.add("email");
            }
            if (allDNIs.contains(usuarioForm.getDNI())) {
               hayError = true;
               erroresCheck.add("usuario.error.dniDuplicado");
               errores.add("DNI");
            }
            if (! hayError) {
               usuarioService.modificarPerfil(usuarioForm);
               result = new ModelAndView("redirect:/usuario/perfil.do");
            } else {
               result = crearEditarModeloPerfil(usuarioForm);
            }
         } catch (Throwable oops) {
            result = crearEditarModeloPerfil(usuarioForm);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   // =========== Creación ===========
   
   @GetMapping("/registro")
   public ModelAndView create() {
      ModelAndView res;
      
      UsuarioForm usuario = new UsuarioForm();
   
      res = crearEditarModelo(usuario);
      return res;
   }
   
   // =========== Registro ===========
   @PostMapping(value = "/registro", params = "saveForm")
   public ModelAndView save(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult binding) {
   
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(usuarioForm);
         errores = usuarioService.getListaErrores(binding);
         if (! usuarioForm.isCheckTerminos()) {
            erroresCheck.add("usuario.error.aceptaTerminos");
         }
         result.addObject("errores", errores);
      } else {
         try {
            if (! actorService.checkPassword(usuarioForm)) {
               hayError = true;
               erroresCheck.add("usuario.error.coincidenciaPasswords");
               errores.add("password");
               errores.add("confirmarPassword");
            }
            if (actorService.checkDni(usuarioForm.getDNI())) {
               hayError = true;
               erroresCheck.add("usuario.error.dniIncorrecto");
               errores.add("DNI");
            }
            if (usuarioForm.getProvincia().equals("-----")) {
               hayError = true;
               erroresCheck.add("usuario.error.escogeProvincia");
               errores.add("provincia");
            }
            if (actorService.findActorPorUsername(usuarioForm.getUsername()) != null) {
               hayError = true;
               erroresCheck.add("usuario.error.usernameDuplicado");
               errores.add("username");
            }
            if (actorService.findActorPorEmail(usuarioForm.getEmail()) != null) {
               hayError = true;
               erroresCheck.add("usuario.error.emailDuplicado");
               errores.add("email");
            }
            if (actorService.findActorPorDNI(usuarioForm.getDNI()) != null) {
               hayError = true;
               erroresCheck.add("usuario.error.dniDuplicado");
               errores.add("DNI");
            }
            if (! hayError) {
               usuarioService.registrarUsuario(usuarioForm);
               result = new ModelAndView("redirect:/usuario/perfil.do");
            } else {
               result = crearEditarModelo(usuarioForm);
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(usuarioForm);
            erroresCheck.add("errorInesperado");
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
   protected ModelAndView crearEditarModeloPerfil(UsuarioForm usuarioForm) {
      ModelAndView res;
   
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
   
      res = new ModelAndView("usuario/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
 
      
      return res;
   }
   
   protected ModelAndView crearEditarModelo(UsuarioForm usuarioForm) {
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
