package controllers;

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

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/actor")
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
   public ModelAndView verPerfil() {
      ModelAndView res;
      
      Collection<Estudio> estudios = new ArrayList<>();
      Collection<Trabajo> trabajos = new ArrayList<>();
      Actor actor = actorService.findPrincipal();
      Usuario usuario = usuarioService.findUsuario();
      Profesional profesional = profesionalService.findProfesional();
      
      if (profesional != null) {
         trabajos = profesional.getTrabajos();
         estudios = profesional.getEstudios();
      }
      
      res = new ModelAndView("actor/perfil");
      res.addObject("actor", actor);
      if (usuario != null) res.addObject("usuario", usuario);
      if (usuario != null) {
         res.addObject("profesional", profesional);
         res.addObject("estudios", estudios);
         res.addObject("trabajos", trabajos);
      }
      
      return res;
      
   }
   
}
