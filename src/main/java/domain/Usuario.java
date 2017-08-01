package domain;

import lombok.Data;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Usuario extends Actor {

   // Relaciones

   private Collection<Peticion> peticiones;
   private Collection<Pago> pagos;

}
