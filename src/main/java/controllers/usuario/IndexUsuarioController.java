package controllers.usuario;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import controllers.AbstractController;
import domain.Usuario;
import forms.GoogleForm;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.ActorService;
import services.ProfesionalService;
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
   @Autowired
   private ProfesionalService profesionalService;
   
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
   
      actorService.addNombre(res);
      
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
      res.addObject("usuario", usuario);
   
      actorService.addNombre(res);
      
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
   
   @GetMapping(value = "/convertirse")
   public ModelAndView convertirse() {
      ModelAndView res;
      try {
         Usuario usuario = usuarioService.findUsuario();
         Usuario copiaUsuario = usuarioService.findUsuario();
         usuarioService.delete(usuario);
         usuarioService.convertirse(copiaUsuario);
         res = new ModelAndView("redirect:/");
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops.getLocalizedMessage());
      }

      return res;
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
   
   // Perfil de Google
   
   @GetMapping("/modificarPerfilGoogle")
   public ModelAndView modificarPerfilGoogle() {
      ModelAndView res;
      Usuario usuario = usuarioService.findUsuario();
      GoogleForm googleForm = actorService.convertirActorGoogle(usuario);
      
      
      res = new ModelAndView("usuario/modificarPerfilGoogle");
      res.addObject("googleForm", googleForm);
      res.addObject("usuario", usuario);
      
      actorService.addNombre(res);
      
      return res;
   }
   
   @PostMapping(value = "/modificarPerfilGoogle", params = "googleForm")
   public ModelAndView savePerfilGoogle(@Valid @ModelAttribute GoogleForm googleForm, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      Usuario usuario = usuarioService.findUsuario();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allDNIs.remove(usuario.getDNI());
      
      if (binding.hasErrors()) {
         result = crearEditarModeloPerfilGoogle(googleForm);
         errores = usuarioService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! actorService.checkDni(googleForm.getDNI())) {
               hayError = true;
               erroresCheck.add("usuario.error.dniIncorrecto");
               errores.add("DNI");
            }
            if (allDNIs.contains(googleForm.getDNI())) {
               hayError = true;
               erroresCheck.add("usuario.error.dniDuplicado");
               errores.add("DNI");
            }
            if (! hayError) {
               usuarioService.modificarPerfilGoogle(googleForm);
               result = new ModelAndView("redirect:/usuario/perfil.do");
            } else {
               result = crearEditarModeloPerfilGoogle(googleForm);
            }
         } catch (Throwable oops) {
            result = crearEditarModeloPerfilGoogle(googleForm);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   
   
   
   @PostMapping("/googleToken")
   public ModelAndView googleLogin(@RequestParam String idTokenString) throws GeneralSecurityException, IOException {
      ModelAndView res = null;
      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory).setAudience(Collections.singletonList("48702837365-ji2jahi3jk0c1ug8472ri0ljesoc461h.apps.googleusercontent.com")).build();
      
      GoogleIdToken idToken = verifier.verify(idTokenString);
      if (idToken != null) {
         Payload payload = idToken.getPayload();
         Usuario usuario = usuarioService.findUsuarioDeGoogle(payload.getSubject().toString());
   
         if (usuario != null) {
            usuarioService.logUsuario(usuario);
            res = new ModelAndView("redirect:/");
         } else {
            String pictureUrl = (String) payload.get("picture");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            
            GoogleForm googleForm = new GoogleForm();
            googleForm.setSubject(payload.getSubject());
            googleForm.setEmailVerified(payload.getEmailVerified());
            googleForm.setEmail(payload.getEmail());
            googleForm.setPictureUrl(pictureUrl);
            googleForm.setFamilyName(familyName);
            googleForm.setGivenName(givenName);
            googleForm.setIdTokenString(idTokenString);
            res = completarRegistroGoogleCreacion(googleForm);
   
         }
      }
      return res;
   }
   
   @GetMapping("/registroGoogle")
   public ModelAndView completarRegistroGoogleCreacion(GoogleForm googleForm) {
      ModelAndView res;
      res = crearEditarModeloGoogle(googleForm);
      
      return res;
   }
   
   @PostMapping(value = "/registroGoogle", params = "googleForm")
   public ModelAndView completarRegistroGoogle(@Valid @ModelAttribute GoogleForm googleForm, BindingResult binding) {
      ModelAndView res = null;
      Usuario usuario;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         res = crearEditarModeloGoogle(googleForm);
         errores = usuarioService.getListaErrores(binding);
         res.addObject("errores", errores);
      } else {
         try {
            if (! actorService.checkDni(googleForm.getDNI())) {
               hayError = true;
               erroresCheck.add("usuario.error.dniIncorrecto");
               errores.add("DNI");
            }
            if (actorService.findActorPorDNI(googleForm.getDNI()) != null) {
               hayError = true;
               erroresCheck.add("usuario.error.dniDuplicado");
               errores.add("DNI");
            }
            if (actorService.findActorPorEmail(googleForm.getEmail()) != null) {
               hayError = true;
               erroresCheck.add("usuario.error.emailDuplicado");
               errores.add("email");
            }
            if (! hayError) {
               usuario = usuarioService.registrarUsuarioGoogle(googleForm);
               if (usuario != null) {
                  usuarioService.logUsuario(usuario);
                  res = new ModelAndView("redirect:/");
               }
            } else {
               res = crearEditarModeloGoogle(googleForm);
            }
         } catch (Throwable oops) {
            res = crearEditarModeloGoogle(googleForm);
            erroresCheck.add("errorInesperado");
         } finally {
            res.addObject("errores", errores);
            res.addObject("erroresCheck", erroresCheck);
         }
      }
      return res;
      
   }
   
   
   // =========== Ancillary Methods ===========
   protected ModelAndView crearEditarModeloPerfil(UsuarioForm usuarioForm) {
      ModelAndView res;
   
      Collection<String> provincias = usuarioService.getListaProvincias();
      Credenciales credenciales = new Credenciales();
      Usuario usuario = usuarioService.findUsuario();
   
      res = new ModelAndView("usuario/modificarPerfil");
      res.addObject("usuarioForm", usuarioForm);
      res.addObject("credenciales", credenciales);
      res.addObject("provincias", provincias);
      res.addObject("usuario", usuario);
      actorService.addNombre(res);
   
      return res;
   }
   
   protected ModelAndView crearEditarModeloPerfilGoogle(GoogleForm googleForm) {
      ModelAndView res;
      
      Usuario usuario = usuarioService.findUsuario();
      
      res = new ModelAndView("usuario/modificarPerfilGoogle");
      res.addObject("googleForm", googleForm);
      res.addObject("usuario", usuario);
      actorService.addNombre(res);
      
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
      actorService.addNombre(res);
   
   
      return res;
   }
   
   protected ModelAndView crearEditarModeloGoogle(GoogleForm googleForm) {
      ModelAndView res;
      
      res = new ModelAndView("usuario/createGoogle");
      res.addObject("googleForm", googleForm);
      
      
      return res;
   }
   
   
}
