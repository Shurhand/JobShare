package forms;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.Collection;

public class PagoForm {
   private Collection<String> ofertas;
   
   public PagoForm() {
      super();
      ofertas = new ArrayList<>();
   }
   
   @ElementCollection
   public Collection<String> getOfertas() {
      return ofertas;
   }
   
   public void setOfertas(Collection<String> ofertas) {
      this.ofertas = ofertas;
   }
}