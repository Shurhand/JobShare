package controllers.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.AbstractController;
import domain.Profesional;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AdminService;
import services.ProfesionalService;
import services.UsuarioService;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("usuario/admin")
public class UsuarioAdminController extends AbstractController {
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private ProfesionalService profesionalService;
   @Autowired
   private AdminService adminService;
   
   @GetMapping(value = "/listaUsuarios")
   public ModelAndView list() throws JsonProcessingException {
      ModelAndView res;
   
      Set<Usuario> usuarios = new HashSet<>(usuarioService.findAll());
      Set<Profesional> profesionales = new HashSet<>(profesionalService.findAll());
   
      usuarios.addAll(profesionales);
      
      ObjectMapper mapper = new ObjectMapper();
      
      res = new ModelAndView("usuario/admin/listaUsuarios");
      res.addObject("usuarios", mapper.writeValueAsString(usuarios));
      res.addObject("requestURI", "usuario/admin/listaUsuarios.do");
      return res;
   }
   
   @GetMapping(value = "/bloquear")
   public ModelAndView bloquear(@RequestParam @NotNull int userID) throws JsonProcessingException {
      
      adminService.bloquear(usuarioService.findOne(userID));
      
      return this.list();
   }
}
