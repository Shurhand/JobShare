package controllers.usuario;

import controllers.AbstractController;
import domain.Peticion;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.PeticionService;
import services.UsuarioService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/peticion/usuario/")
public class PeticionUsuarioController extends AbstractController {
   public PeticionUsuarioController() {
      super();
   }
   
   // =============== Services =======
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private PeticionService peticionService;
   
   // =========== Creation ===========
   
   @GetMapping("/misPeticiones")
   public ModelAndView miPeticiones() {
      ModelAndView res;
      Usuario usuario = usuarioService.findUsuario();
      Collection<Peticion> peticiones = peticionService.getPeticionesPorUsuario(usuario);

      res = new ModelAndView("peticion/usuario/misPeticiones");
      res.addObject("peticiones", peticiones);
      res.addObject("usuario", usuario);
      
      return res;
   }
   
   // =========== Edition =============

   @GetMapping("/crear")
   public ModelAndView crear() {
   
      ModelAndView res;
      Peticion peticion = peticionService.create();

      res = new ModelAndView("peticion/usuario/crear");
      res.addObject("peticion", peticion);
   
      return res;
   }
   
   @GetMapping("/editar")
   public ModelAndView editar(@RequestParam int peticionID) {
      
      ModelAndView result;
      Peticion peticion = peticionService.findOne(peticionID);
      
      result = createEditModelAndView(peticion);
      return result;
   }
   
   @PostMapping(value = "/editar", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Peticion peticion, BindingResult binding) {
   
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      
      if (binding.hasErrors()) {
         result = createEditModelAndView(peticion);
         errores = peticionService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
//            if(!peticion.getPassword().equals(peticion.getConfirmarPassword())){
//               erroresCheck.add("usuario.coincidenciaPasswords");
//               errores.add("password");
//               errores.add("confirmarPassword");
//            }
            peticionService.save(peticion);
            result = new ModelAndView("redirect:/peticion/usuario/misPeticiones.do");
         } catch (Throwable oops) {
            result = createEditModelAndView(peticion);
   
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
   
    @GetMapping("/borrar")
    public ModelAndView delete(@RequestParam int peticionID) {
        ModelAndView result;
   
       Peticion peticion = peticionService.findOne(peticionID);
       peticionService.delete(peticion);
   
       result = new ModelAndView("redirect:/peticion/usuario/MisPeticiones.do");

        return result;
   }
   
   
   // =========== Ancillary Methods ===========
   protected ModelAndView createEditModelAndView(Peticion usuario) {
      ModelAndView res;
      
      res = createEditModelAndView(usuario, null);
      
      return res;
   }
   
   protected ModelAndView createEditModelAndView(Peticion peticion, String message) {
      ModelAndView res;
      
      Credenciales credenciales = new Credenciales();
      
      res = new ModelAndView("peticion/usuario/editar");
      res.addObject("peticion", peticion);
      res.addObject("credenciales", credenciales);
      
      return res;
   }
}
