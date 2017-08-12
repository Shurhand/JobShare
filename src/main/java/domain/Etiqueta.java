package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)

public class Etiqueta extends DomainEntity {
   private String nombre;
   private boolean activada;
   
   public Etiqueta() {
      super();
      
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 20)
   public String getNombre() {
      return nombre;
   }
   
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   
   public boolean isActivada() {
      return activada;
   }
   
   public void setActivada(boolean activada) {
      this.activada = activada;
   }
   
   // Relaciones
   private Collection<Peticion> peticiones;
   
   
   @ManyToMany
   @JsonManagedReference
   public Collection<Peticion> getPeticiones() {
      return peticiones;
   }
   
   public void setPeticiones(Collection<Peticion> peticiones) {
      this.peticiones = peticiones;
   }
}
