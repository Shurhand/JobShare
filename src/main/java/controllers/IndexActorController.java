package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.ActorService;
import services.AdminService;
import services.ProfesionalService;
import services.UsuarioService;

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
   
   // @GetMapping("verPefil")
   // public ModelAndView verPerfil(@RequestParam int actorID) {
       // ModelAndView res;
       
       // Actor actor = actorService.findOne(actorID);
       // res = new ModelAndView
   // }
}
