package controllers.usuario;

import controllers.AbstractController;
import domain.Oferta;
import domain.Valoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.OfertaService;
import services.ValoracionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/valoracion/usuario")
public class ValoracionUsuarioController extends AbstractController {
   @Autowired
   private ValoracionService valoracionService;
   @Autowired
   private ActorService actorService;
   @Autowired
   private OfertaService ofertaService;
   
   @GetMapping("/valorar")
   public ModelAndView crearValoracion(@RequestParam int ofertaID) {
      ModelAndView res;
      Oferta oferta = ofertaService.findOne(ofertaID);
      Valoracion valoracion = valoracionService.create();
      valoracion.setOferta(oferta);
      
      res = new ModelAndView("valoracion/usuario/valorar");
      res.addObject("valoracion", valoracion);
      
      return res;
      
   }
   
   @PostMapping(value = "/valorar", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Valoracion valoracion, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(valoracion);
         errores = valoracionService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! hayError) {
               valoracionService.save(valoracion);
               result = new ModelAndView("redirect:/actor/verPerfil.do?actorID=" + valoracion.getOferta().getProfesional().getId());
            } else {
               result = crearEditarModelo(valoracion);
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(valoracion);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
   }
   
   protected ModelAndView crearEditarModelo(Valoracion valoracion) {
      ModelAndView res;
      
      res = new ModelAndView("valoracion/usuario/valorar");
      res.addObject("valoracion", valoracion);
      actorService.addNombre(res);
      
      return res;
   }
}
