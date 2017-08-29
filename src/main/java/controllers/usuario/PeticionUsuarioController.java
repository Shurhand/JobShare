package controllers.usuario;

import controllers.AbstractController;
import domain.Etiqueta;
import domain.Peticion;
import domain.Usuario;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.EtiquetaService;
import services.PeticionService;
import services.UsuarioService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

@Controller
@RequestMapping("/peticion/usuario/")
public class PeticionUsuarioController extends AbstractController {
   public PeticionUsuarioController() {
      super();
   }
   
   // =============== Services =======
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private PeticionService peticionService;
   @Autowired
   private EtiquetaService etiquetaService;
   @Autowired
   private ActorService actorService;
   
   @GetMapping("/misPeticiones")
   public ModelAndView misPeticiones() {
      ModelAndView res;
      Usuario usuario = usuarioService.findUsuario();
      BuscaForm buscaForm = new BuscaForm();
      Collection<Peticion> peticiones = peticionService.getPeticionesActivasPorUsuario(usuario);
   
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("peticion/usuario/misPeticiones");
      res.addObject("peticiones", peticiones);
      res.addObject("buscaForm", buscaForm);
      res.addObject("usuario", usuario);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      return res;
   }
   
   @GetMapping("/buscarMisPeticiones")
   public ModelAndView buscarMisPeticiones(@Valid @ModelAttribute BuscaForm buscaForm, BindingResult bindingResult) {
      this.limpiarComas(buscaForm);
      ModelAndView res;
   
      Usuario usuario = usuarioService.findUsuario();
      Collection<Peticion> peticiones = new ArrayList<>();
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
   
      if (bindingResult.hasErrors()) {
         res = new ModelAndView("peticion/usuario/misPeticiones");
         res.addObject("peticiones", peticiones);
         res.addObject("todasEtiquetas", todasEtiquetas);
      } else {
         peticiones = peticionService.getMisPeticionesBuscadas(buscaForm);
         res = new ModelAndView("peticion/usuario/misPeticiones");
         res.addObject("peticiones", peticiones);
         res.addObject("todasEtiquetas", todasEtiquetas);
      }
   
      res.addObject("usuario", usuario);
      actorService.addNombre(res);
   
      return res;
   }
   
   @GetMapping("/buscarMisPeticionesCaducadas")
   public ModelAndView buscarMisPeticionesCaducadas(@Valid @ModelAttribute BuscaForm buscaForm, BindingResult bindingResult) {
      this.limpiarComas(buscaForm);
      ModelAndView res;
      Usuario usuario = usuarioService.findUsuario();
      Collection<Peticion> peticiones = new ArrayList<>();
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
   
      if (bindingResult.hasErrors()) {
         res = new ModelAndView("peticion/usuario/misPeticionesCaducadas");
         res.addObject("peticiones", peticiones);
         res.addObject("todasEtiquetas", todasEtiquetas);
      } else {
         peticiones = peticionService.getMisPeticionesBuscadasCaducadas(buscaForm);
         res = new ModelAndView("peticion/usuario/misPeticionesCaducadas");
         res.addObject("peticiones", peticiones);
         res.addObject("todasEtiquetas", todasEtiquetas);
      }
      res.addObject("usuario", usuario);
      actorService.addNombre(res);
   
      return res;
   }
   
   @GetMapping("/misPeticionesCaducadas")
   public ModelAndView misPeticionesCaducadas() {
      ModelAndView res;
      BuscaForm buscaForm = new BuscaForm();
      Usuario usuario = usuarioService.findUsuario();
      Collection<Peticion> peticiones = peticionService.getPeticionesCaducadasPorUsuario(usuario);
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      res = new ModelAndView("peticion/usuario/misPeticionesCaducadas");
      res.addObject("peticiones", peticiones);
      res.addObject("buscaForm", buscaForm);
      res.addObject("usuario", usuario);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      return res;
   }
   
   // =========== Edition =============

   @GetMapping("/crear")
   public ModelAndView crear() {
   
      ModelAndView res;
   
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      Peticion peticion = peticionService.create();

      res = new ModelAndView("peticion/usuario/crear");
      res.addObject("peticion", peticion);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
   
      return res;
   }
   
   @GetMapping("/editar")
   public ModelAndView editar(@RequestParam int peticionID) {
      
      ModelAndView result;
      Peticion peticion = peticionService.findOne(peticionID);
   
      result = crearEditarModelo(peticion);
      return result;
   }
   
   @PostMapping(value = "/editar", params = "save")
   public ModelAndView save(@Valid @ModelAttribute Peticion peticion, BindingResult binding) {
   
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(peticion);
         errores = peticionService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! peticionService.checkFechaCaducidad(peticion.getFechaCaducidad())) {
               hayError = true;
               erroresCheck.add("peticion.error.fechaCaducidad");
               errores.add("fechaCaducidad");
            }
            if (! hayError) {
               peticionService.save(peticion);
               result = new ModelAndView("redirect:/peticion/ver.do?peticionID=" + peticion.getId());
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(peticion);
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
    @GetMapping("/borrar")
    public ModelAndView delete(@RequestParam int peticionID) {
        ModelAndView result;
   
       Peticion peticion = peticionService.findOne(peticionID);
       peticionService.delete(peticion);
   
       result = new ModelAndView("redirect:/peticion/usuario/buscarMisPeticiones.do");

        return result;
   }
   
   private ModelAndView crearEditarModelo(Peticion peticion) {
      ModelAndView res;
      SortedSet<Etiqueta> todasEtiquetas = etiquetaService.getEtiquetasActivadasOrdenadas();
      
      String vista = peticion.getId() != 0 ? "peticion/usuario/editar" : "peticion/usuario/crear";
   
      res = new ModelAndView(vista);
      res.addObject("peticion", peticion);
      res.addObject("todasEtiquetas", todasEtiquetas);
      actorService.addNombre(res);
      
      return res;
   }
}
