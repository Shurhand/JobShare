package controllers.usuario;

import controllers.AbstractController;
import domain.Item;
import domain.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.Credenciales;
import services.ItemService;
import services.PeticionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item/usuario")
public class ItemUsuarioController extends AbstractController {
   @Autowired
   private ItemService itemService;
   @Autowired
   private PeticionService peticionService;
   
   @GetMapping("/crear")
   public ModelAndView create(@RequestParam int peticionID) {
      
      ModelAndView res;
      Item item = itemService.create();
      
      res = crearEditarModelo(item, peticionID);
      
      return res;
   }
   
   @GetMapping("/editar")
   public ModelAndView edit(@RequestParam int peticionID, @RequestParam int itemID) {
      
      ModelAndView result;
      Item item = itemService.findOne(itemID);
      
      result = crearEditarModelo(item, peticionID);
      
      return result;
   }
   
   @PostMapping(value = "/editar", params = "save")
   public ModelAndView save(@RequestParam int peticionID, @Valid @ModelAttribute Item item, BindingResult binding) {
      
      ModelAndView result = null;
      List<String> errores = new ArrayList<>();
      List<String> erroresCheck = new ArrayList<>();
      boolean hayError = false;
      
      if (binding.hasErrors()) {
         result = crearEditarModelo(item, peticionID);
         errores = itemService.getListaErrores(binding);
         result.addObject("errores", errores);
      } else {
         try {
            if (! itemService.checkDentroDelLimitePeticion(item)) {
               hayError = true;
               erroresCheck.add("item.error.demasiados");
            }
            if (! hayError) {
               itemService.save(item);
               result = new ModelAndView("redirect:/peticion/ver.do?peticionID=" + item.getPeticion().getId());
            } else {
               result = crearEditarModelo(item, peticionID);
            }
         } catch (Throwable oops) {
            result = crearEditarModelo(item, peticionID);
            erroresCheck.add("errorInesperado");
         } finally {
            result.addObject("errores", errores);
            result.addObject("erroresCheck", erroresCheck);
         }
      }
      return result;
      
   }
   
   @GetMapping("/borrar")
   public ModelAndView delete(@RequestParam int itemID) {
      ModelAndView result;
      
      Item item = itemService.findOne(itemID);
      itemService.delete(item);
      
      result = new ModelAndView("redirect:/peticion/ver.do?peticionID=" + item.getPeticion().getId());
      
      return result;
   }
   
   protected ModelAndView crearEditarModelo(Item item, int peticionID) {
      ModelAndView res;
      
      Credenciales credenciales = new Credenciales();
      String vista = item.getId() != 0 ? "item/usuario/editar" : "item/usuario/crear";
      Peticion peticion = peticionService.findOne(peticionID);
      
      item.setPeticion(peticion);
      res = new ModelAndView(vista);
      res.addObject("item", item);
      res.addObject("peticion", peticion);
      res.addObject("credenciales", credenciales);
      
      return res;
   }
}
