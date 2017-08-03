package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)

public class Etiqueta extends DomainEntity {
   private String nombre;
   private boolean activada;
   private LocalDate fechaCreacion;
   
   @NotBlank
   @SafeHtml
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
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaCreacion() {
      return fechaCreacion;
   }
   
   public void setFechaCreacion(LocalDate fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }
   
   // Relaciones
   private Collection<Item> items;
   
   @ManyToMany
   public Collection<Item> getItems() {
      return items;
   }
   
   public void setItems(Collection<Item> items) {
      this.items = items;
   }
}
