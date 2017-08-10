package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)

public class Usuario extends Actor {
   public Usuario() {
      super();
   }
   
   // Relaciones
   private Collection<Peticion> peticiones;
   private Collection<Pago> pagos;
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   public Collection<Peticion> getPeticiones() {
      return peticiones;
   }
   
   public void setPeticiones(Collection<Peticion> peticiones) {
      this.peticiones = peticiones;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   public Collection<Pago> getPagos() {
      return pagos;
   }
   
   public void setPagos(Collection<Pago> pagos) {
      this.pagos = pagos;
   }
   
   
}
