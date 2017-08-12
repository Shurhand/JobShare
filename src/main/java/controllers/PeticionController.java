package controllers;

import domain.Actor;
import domain.Peticion;
import domain.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Autoridad;
import security.Credenciales;
import services.ActorService;
import services.PeticionService;
import services.ProfesionalService;
import services.UsuarioService;

@Controller
@RequestMapping("/peticion")
public class PeticionController extends AbstractController {
   // =============== Services =======
   @Autowired
   private PeticionService peticionService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private ProfesionalService profesionalService;
   
   @GetMapping("/ver")
   public ModelAndView verPeticion(@RequestParam int peticionID) {
      ModelAndView res;
      Credenciales credenciales = new Credenciales();
      Actor actorAutenticado = null;
      Profesional profesional = null;
      
      Peticion peticion = peticionService.findOne(peticionID);
      if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
         actorAutenticado = actorService.findPrincipal();
      }
      if (peticion.getUsuario().getCuenta().getAuthorities().contains(Autoridad.PROFESIONAL)) {
         profesional = profesionalService.findProfesionalPorCuenta(peticion.getUsuario().getCuenta());
      }
      
      res = new ModelAndView("peticion/ver");
      res.addObject("peticion", peticion);
      res.addObject("usuario", peticion.getUsuario());
      res.addObject("profesional", profesional);
      res.addObject("credenciales", credenciales);
      res.addObject("actorAutenticado", actorAutenticado);
      
      return res;
   }
}
