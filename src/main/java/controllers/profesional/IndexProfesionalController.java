package controllers.profesional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.AbstractController;
import domain.Estudio;
import domain.Profesional;
import domain.Trabajo;
import domain.Valoracion;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profesional")
public class IndexProfesionalController extends AbstractController {
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
      Collection<Valoracion> valoraciones = new ArrayList<>();
      Profesional profesional = profesionalService.findProfesional();
   
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      mapper.setDateFormat(df);
      
      trabajos = profesional.getTrabajos();
      estudios = profesional.getEstudios();
      valoraciones = profesional.getOfertas().stream().filter(x -> x.getValoracion() != null).map(x -> x.getValoracion()).collect(Collectors.toList());
   
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
      ModelAndView res;
      Profesional profesional = profesionalService.findProfesional();
      UsuarioForm usuarioForm = actorService.convertirActor(profesional);
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("profesional/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      
      return res;
   }
   
   @PostMapping(value = "/modificarPerfil", params = "saveForm")
   public ModelAndView save(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult binding) {
      
      ModelAndView result = null;
      Profesional profesional = profesionalService.findProfesional();
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      Collection<String> allUsernames = actorService.getAllUsernames();
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allUsernames.remove(profesional.getCuenta().getUsername());
      allEmails.remove(profesional.getEmail());
      allDNIs.remove(profesional.getDNI());
      
      if (binding.hasErrors()) {
         result = crearEditarModeloPerfil(usuarioForm);
         errores = profesionalService.getListaErrores(binding);
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
               profesionalService.modificarPerfil(usuarioForm);
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
