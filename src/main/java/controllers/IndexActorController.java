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
   public ModelAndView verPerfil() throws JsonProcessingException {
      ModelAndView res;
      ObjectMapper mapper = new ObjectMapper();
      
      Collection<Estudio> estudios = new ArrayList<>();
      Collection<Trabajo> trabajos = new ArrayList<>();
      Actor actor = actorService.findPrincipal();
      Usuario usuario = usuarioService.findUsuario();
      Profesional profesional = profesionalService.findProfesional();
   
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      mapper.setDateFormat(df);
      
      if (profesional != null) {
         trabajos = profesional.getTrabajos();
         estudios = profesional.getEstudios();
      }
      
      res = new ModelAndView("actor/perfil");
      res.addObject("actor", actor);
      if (usuario != null) res.addObject("usuario", usuario);
      if (profesional != null) {
         res.addObject("profesional", profesional);
         res.addObject("estudios", mapper.writeValueAsString(estudios));
         res.addObject("trabajos", mapper.writeValueAsString(trabajos));
      }
      
      return res;
      
   }
   
}
