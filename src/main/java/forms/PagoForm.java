package forms;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.Collection;

public class PagoForm {
   private Collection<String> items;
   
   public PagoForm() {
      super();
      items = new ArrayList<>();
   }
   
   @ElementCollection
   public Collection<String> getItems() {
      return items;
   }
   
   public void setItems(Collection<String> items) {
      this.items = items;
   }
}