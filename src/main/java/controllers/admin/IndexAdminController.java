package controllers.admin;

import controllers.AbstractController;
import domain.Admin;
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
import services.AdminService;
import services.ProfesionalService;
import services.UsuarioService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class IndexAdminController extends AbstractController {
   @Autowired
   private ActorService actorService;
   @Autowired
   private AdminService adminService;
   @Autowired
   private ProfesionalService profesionalService;
   @Autowired
   private UsuarioService usuarioService;
   
   @GetMapping("/perfil")
   public ModelAndView verPerfil() {
      ModelAndView res;
      
      Admin admin = adminService.findAdmin();
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("admin/perfil");
      res.addObject("admin", admin);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      actorService.addNombre(res);
      
      return res;
      
   }
   
   // =========== Perfil =============
   
   @GetMapping("/modificarPerfil")
   public ModelAndView modificarPerfil() {
      ModelAndView res;
      Admin admin = adminService.findAdmin();
      UsuarioForm usuarioForm = actorService.convertirActor(admin);
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("admin/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      actorService.addNombre(res);
      
      return res;
   }
   
   @PostMapping(value = "/modificarPerfil", params = "saveForm")
   public ModelAndView save(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult binding) {
      
      ModelAndView result = null;
      Admin admin = adminService.findAdmin();
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      Collection<String> allUsernames = actorService.getAllUsernames();
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allUsernames.remove(admin.getCuenta().getUsername());
      allEmails.remove(admin.getEmail());
      allDNIs.remove(admin.getDNI());
      
      if (binding.hasErrors()) {
         result = crearEditarModeloPerfil(usuarioForm);
         errores = adminService.getListaErrores(binding);
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
               adminService.modificarPerfil(usuarioForm);
               result = new ModelAndView("redirect:/admin/perfil.do");
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
   
   // =========== Ancillary Methods ===========
   protected ModelAndView crearEditarModeloPerfil(UsuarioForm usuarioForm) {
      ModelAndView res;
      
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
   
      res = new ModelAndView("admin/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      actorService.addNombre(res);
   
      return res;
   }
   
}
