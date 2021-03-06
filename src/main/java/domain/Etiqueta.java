package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.Set;

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
   @Column(unique = true)
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
   private Set<Peticion> peticiones;
   
   @ManyToMany
   @JsonManagedReference
   public Set<Peticion> getPeticiones() {
      return peticiones;
   }
   
   public void setPeticiones(Set<Peticion> peticiones) {
      this.peticiones = peticiones;
   }
   
}
