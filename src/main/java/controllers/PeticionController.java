package controllers;

import domain.Actor;
import domain.Oferta;
import domain.Peticion;
import domain.Profesional;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

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
   @Autowired
   private OfertaService ofertaService;
   
   @PostMapping(value = "/busqueda", params = "buscar")
   public ModelAndView buscar(@Valid @ModelAttribute BuscaForm buscaForm) {
      ModelAndView res;
      Collection<Peticion> peticiones = peticionService.getPeticionesBuscadas(buscaForm);
      
      res = new ModelAndView("peticion/busqueda");
      res.addObject("peticiones", peticiones);
      
      return res;
   }
   
   
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
      Collection<Oferta> todasOfertas = ofertaService.findAll();
      Collection<Oferta> ofertas = new ArrayList<>();
      for (Oferta o : todasOfertas) {
      
      }
      
      res = new ModelAndView("peticion/ver");
      res.addObject("peticion", peticion);
      res.addObject("usuario", peticion.getUsuario());
      res.addObject("credenciales", credenciales);
      res.addObject("todasOfertas", todasOfertas);
      res.addObject("actorAutenticado", actorAutenticado);
      
      return res;
   }
}
