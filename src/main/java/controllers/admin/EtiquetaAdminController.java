package controllers.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.AbstractController;
import domain.Etiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.ActorService;
import services.EtiquetaService;

import java.util.Collection;

@Controller
@RequestMapping("/etiqueta/admin")
public class EtiquetaAdminController extends AbstractController {
   @Autowired
   private EtiquetaService etiquetaService;
   @Autowired
   private ActorService actorService;
   
   @GetMapping("/activar")
   public ModelAndView activar(@RequestParam int etiquetaID) {
      ModelAndView res;
      
      Etiqueta etiqueta = etiquetaService.findOne(etiquetaID);
      etiquetaService.activar(etiqueta);
      
      res = new ModelAndView("redirect:/etiqueta/admin/lista.do");
      return res;
   }
   
   @GetMapping("/lista")
   public ModelAndView etiquetas() throws JsonProcessingException {
      ModelAndView res;
      Credenciales credenciales = new Credenciales();
      ObjectMapper mapper = new ObjectMapper();
      
      Collection<Etiqueta> etiquetas = etiquetaService.findAll();
      
      
      res = new ModelAndView("etiqueta/admin/lista");
      res.addObject("etiquetas", mapper.writeValueAsString(etiquetas));
      res.addObject("credenciales", credenciales);
      actorService.addNombre(res);
      return res;
   }
}
