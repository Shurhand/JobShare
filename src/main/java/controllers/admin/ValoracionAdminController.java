package controllers.admin;

import controllers.AbstractController;
import domain.Valoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ValoracionService;

@Controller
@RequestMapping("/valoracion/admin")
public class ValoracionAdminController extends AbstractController {
   @Autowired
   private ValoracionService valoracionService;
   
   @GetMapping("/borrar")
   public ModelAndView borrar(@RequestParam int valoracionID, @RequestParam int actorID) {
      ModelAndView res;
      
      Valoracion valoracion = valoracionService.findOne(valoracionID);
      valoracionService.delete(valoracion);
      
      res = new ModelAndView("redirect:/actor/verPerfil.do?actorID=" + actorID);
      return res;
   }
   
}