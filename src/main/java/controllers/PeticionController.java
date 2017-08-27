package controllers;

import domain.*;
import forms.BuscaForm;
import forms.PagoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.SortedSet;

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
   @Autowired
   private EtiquetaService etiquetaService;
   
   @GetMapping("/buscar")
   public ModelAndView buscar(@Valid @ModelAttribute BuscaForm buscaForm) {
      ModelAndView res;
   
      Actor actorAutenticado = null;
      
      Collection<Peticion> peticiones = peticionService.getPeticionesBuscadas(buscaForm);
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
   
      
      res = new ModelAndView("peticion/buscar");
      res.addObject("peticiones", peticiones);
      res.addObject("todasEtiquetas", todasEtiquetas);
   
      actorService.addNombre(res);
      
      return res;
   }
   
   
   @GetMapping("/ver")
   public ModelAndView verPeticion(@RequestParam int peticionID) {
      ModelAndView res;
      Credenciales credenciales = new Credenciales();
      Actor actorAutenticado = null;
      PagoForm pagoForm = new PagoForm();
      Profesional profesional;
      Profesional profesionalAutenticado = null;
      
      Peticion peticion = peticionService.findOne(peticionID);
   
      if (actorService.isProfesional()) {
         profesionalAutenticado = profesionalService.findProfesional();
      }
      Collection<Oferta> todasOfertas = ofertaService.findAll();
   
      todasOfertas.stream().filter(x -> ! x.getItem().getOfertas().contains(x)).forEach(x -> x.getItem().getOfertas().add(x));
      profesional = profesionalService.findProfesionalPorCuenta(peticion.getUsuario().getCuenta());
   
   
      res = new ModelAndView("peticion/ver");
      res.addObject("peticion", peticion);
      res.addObject("usuario", peticion.getUsuario());
      res.addObject("credenciales", credenciales);
      res.addObject("todasOfertas", todasOfertas);
      res.addObject("pagoForm", pagoForm);
      res.addObject("profesional", profesional);
      res.addObject("profesionalAutenticado", profesionalAutenticado);
      actorService.addNombre(res);
      
      return res;
   }
}
