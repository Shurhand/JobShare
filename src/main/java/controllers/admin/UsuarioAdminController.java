package controllers.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.AbstractController;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.UsuarioService;

import java.util.Collection;

@Controller
@RequestMapping("usuario/admin")
public class UsuarioAdminController extends AbstractController {
   @Autowired
   private UsuarioService usuarioService;
   
   @GetMapping(value = "/listaUsuarios")
   public ModelAndView list() throws JsonProcessingException {
      ModelAndView res;
      
      Collection<Usuario> usuarios;
      usuarios = usuarioService.findAll();
      
      ObjectMapper mapper = new ObjectMapper();
      
      res = new ModelAndView("usuario/admin/listaUsuarios");
      res.addObject("usuarios", mapper.writeValueAsString(usuarios));
      res.addObject("requestURI", "usuario/admin/listaUsuarios.do");
      return res;
   }
}
