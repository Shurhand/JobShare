package controllers.usuario;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import controllers.AbstractController;
import domain.Usuario;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.ActorService;
import services.UsuarioService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class IndexUsuarioController extends AbstractController {
   public IndexUsuarioController() {
      super();
   }
   
   private static final JacksonFactory jacksonFactory = new JacksonFactory();
   
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
   
   //
   @PostMapping("/googleToken")
   public String googleLogin(@RequestParam String idTokenString) throws GeneralSecurityException, IOException {
      
      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory).setAudience(Collections.singletonList("48702837365-ji2jahi3jk0c1ug8472ri0ljesoc461h.apps.googleusercontent.com")).build();
      
      
      GoogleIdToken idToken = verifier.verify(idTokenString);
      if (idToken != null) {
         Payload payload = idToken.getPayload();
         
         // Print user identifier
         String userId = payload.getSubject();
         System.out.println("User ID: " + userId);
         
         // Get profile information from payload
         String email = payload.getEmail();
         boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
         String name = (String) payload.get("name");
         String pictureUrl = (String) payload.get("picture");
         String locale = (String) payload.get("locale");
         String familyName = (String) payload.get("family_name");
         String givenName = (String) payload.get("given_name");
         
         
         // Use or store profile information
         // ...
         
      } else {
         System.out.println("Invalid ID token.");
      }
      return "redirect:/usuario/registroGoogle";
   }

//   @PostMapping("/registroGoogle")
//   public String doPost2(HttpServletRequest request, HttpServletResponse response) {
//      System.out.println("He entrado");
//      System.out.println("He entrado");
//      System.out.println("He entrado");
//      System.out.println("He entrado");
//
//      return "redirect:/usuario/registroGoogle";
//   }
   
   
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
