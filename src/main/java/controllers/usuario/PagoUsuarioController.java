package controllers.usuario;

import controllers.AbstractController;
import domain.Estado;
import domain.Oferta;
import domain.Usuario;
import forms.PagoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ItemService;
import services.OfertaService;
import services.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("pago/usuario")
public class PagoUsuarioController extends AbstractController {
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private ItemService itemService;
   @Autowired
   private OfertaService ofertaService;
   
   @PostMapping("/pagar")
   public ModelAndView pagar(@RequestParam int peticionID, @Valid @ModelAttribute PagoForm pagoForm, BindingResult binding) {
      pagoForm.getOfertas().removeIf(Objects::isNull);
   
      Collection<Oferta> ofertas = this.getOfertasDeString(pagoForm.getOfertas());
   
      String urlReturn = "http://localhost:8080/pago/usuario/pagoCorrecto.do?";
      for (Oferta o : ofertas) {
         urlReturn += "ofertaID=" + o.getId() + "&";
      }
      urlReturn = urlReturn.substring(0, urlReturn.length() - 1);
   
      Double precioTotal = ofertas.stream().mapToDouble(x -> x.getPrecio()).sum();
      String precioTotalString = precioTotal.toString();
      precioTotalString = precioTotalString.endsWith("0") ? precioTotalString.substring(0, precioTotalString.length() - 2) : precioTotalString;
   
      ModelAndView res = new ModelAndView("pago/usuario/pagar");
      res.addObject("ofertas", ofertas);
      res.addObject("precioTotal", precioTotalString);
      res.addObject("peticionID", peticionID);
      res.addObject("urlReturn", urlReturn);
 
      return res;
   }
   
   @GetMapping("/pagoCorrecto")
   public ModelAndView procesarIPN(@RequestParam("ofertaID") List<Integer> ofertasID, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ModelAndView vista;
      Usuario usuario = usuarioService.findUsuario();

//      Map<String, String> res = nvp(req);
//      Set<String> ofertasString = res.entrySet().stream().filter(entry -> entry.getKey().startsWith("item_name")).map(Map.Entry::getValue).collect(Collectors.toSet());
//
      Collection<Oferta> ofertas = this.getOfertasDeIntegers(ofertasID);
      for (Oferta o : ofertas) {
         if (usuario.getPeticiones().contains(o.getItem().getPeticion())) {
            o.setEstado(Estado.CONTRATADA);
            ofertaService.save(o);
         }
      }
//      ofertas.forEach(x -> x.setEstado(Estado.CONTRATADA));
//      ofertas.forEach(x -> ofertaService.save(x));
      
      vista = new ModelAndView("pago/usuario/pagoCorrecto");
      
      return vista;
   }
   
   private Collection<Oferta> getOfertasDeString(Collection<String> strings) {
      Collection<Oferta> ofertas = new ArrayList<>();
      for (String o : strings) {
         Oferta oferta = ofertaService.findOne(Integer.valueOf(o));
         ofertas.add(oferta);
      }
      
      return ofertas;
   }
   
   private Collection<Oferta> getOfertasDeIntegers(Collection<Integer> integers) {
      Collection<Oferta> ofertas = new ArrayList<>();
      for (Integer i : integers) {
         Oferta oferta = ofertaService.findOne(i);
         ofertas.add(oferta);
      }
      
      return ofertas;
   }
   
   // Creación del map de contenidos
   protected Map<String, String> nvp(HttpServletRequest req) {
      Map<String, String[]> params = req.getParameterMap();
      Map<String, String> nvp = new HashMap<>();
      for (Map.Entry<String, String[]> entry : params.entrySet()) {
         String value = "";
         for (int i = 0; i < entry.getValue().length; i++) {
            value += entry.getValue()[i];
            if (i < entry.getValue().length - 1) value += ",";
         }
         nvp.put(entry.getKey(), value);
      }
      return nvp;
   }
}
