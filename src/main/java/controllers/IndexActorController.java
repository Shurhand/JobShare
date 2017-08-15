package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdminService;
import services.ProfesionalService;
import services.UsuarioService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

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
   
   @GetMapping("/verPerfil")
   public ModelAndView verPerfil(@RequestParam int actorID) throws JsonProcessingException {
      ModelAndView res;
      String vista;
      Actor actor = actorService.findOne(actorID);
      Actor actorAutenticado = null;
      Profesional profesional = profesionalService.findProfesionalPorCuenta(actor.getCuenta());
      Usuario usuario = usuarioService.findUsuarioPorCuenta(actor.getCuenta());
      Admin admin = adminService.findUsuarioPorCuenta(actor.getCuenta());
      if (profesional != null) {
         vista = "actor/verPerfilProfesional";
      } else if (usuario != null) {
         vista = "actor/verPerfilUsuario";
      } else {
         vista = "actor/verPerfilAdmin";
      }
      if (! actorService.isAnonimo()) {
         actorAutenticado = actorService.findPrincipal();
      }
      
      
      res = new ModelAndView(vista);
      res.addObject("profesional", profesional);
      res.addObject("usuario", usuario);
      res.addObject("admin", admin);
      res.addObject("actorAutenticado", actorAutenticado);
      
      if (profesional != null) {
         Collection<Estudio> estudios = new ArrayList<>();
         Collection<Trabajo> trabajos = new ArrayList<>();
         Collection<Valoracion> valoraciones = new ArrayList<>();
         ObjectMapper mapper = new ObjectMapper();
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         mapper.setDateFormat(df);
         
         trabajos = profesional.getTrabajos();
         estudios = profesional.getEstudios();
         valoraciones = profesional.getOfertas().stream().filter(x -> x.getValoracion() != null).map(x -> x.getValoracion()).collect(Collectors.toList());
         res.addObject("estudios", mapper.writeValueAsString(estudios));
         res.addObject("trabajos", mapper.writeValueAsString(trabajos));
         res.addObject("valoraciones", valoraciones);
      }
      
      return res;
   }
}
