package domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.FIELD)
@Data
public class Usuario extends Actor {
   
   // Relaciones
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   private Collection<Peticion> peticiones;
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   private Collection<Pago> pagos;
   
}
