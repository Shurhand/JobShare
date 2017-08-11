package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdminService;
import services.ProfesionalService;
import services.UsuarioService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/profesional")
public class IndexActorController extends AbstractController {
   @Autowired
   private ActorService actorService;
   @Autowired
   private AdminService adminService;
   @Autowired
   private ProfesionalService profesionalService;
   @Autowired
   private UsuarioService usuarioService;
   
   @GetMapping("/perfil")
   public ModelAndView verPerfil() throws JsonProcessingException {
      ModelAndView res;
      ObjectMapper mapper = new ObjectMapper();
      
      Collection<Estudio> estudios = new ArrayList<>();
      Collection<Trabajo> trabajos = new ArrayList<>();
      Profesional profesional = profesionalService.findProfesional();
   
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      mapper.setDateFormat(df);
      
      trabajos = profesional.getTrabajos();
      estudios = profesional.getEstudios();
      valoraciones = profesional.getValoraciones();
       
      res = new ModelAndView("profesional/perfil");
      res.addObject("profesional", profesional);
      res.addObject("estudios", mapper.writeValueAsString(estudios));
      res.addObject("trabajos", mapper.writeValueAsString(trabajos));
      res.addObject("valoraciones", valoraciones);
      
      
      return res;
      
   }
   
         // =========== Perfil =============

      @GetMapping("/modificarPerfil")
      public ModelAndView modificarPerfil() {
         ModelAndView result;
         Profesional profesional = profesionalService.findUsuario();

         result = crearEditarModeloPerfil(profesional);

         return result;
      }
      
 @PostMapping(value = "/modificarPerfil", params = "saveForm")
   public ModelAndView save(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult binding) {
   
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModeloPerfil(usuarioForm);
         errores = profesionalService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! usuarioService.checkPassword(usuarioForm)) {
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
               result = new ModelAndView("redirect:/profesional/perfil.do");
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
      
      res = new ModelAndView("profesional/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
 
      
      return res;
   }
   
}
