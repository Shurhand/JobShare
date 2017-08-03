package domain;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)

public class Pago extends DomainEntity {
   private LocalDate fechaCreacion;
   
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
   private Usuario usuario;
   private Collection<Item> items;
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   public Usuario getUsuario() {
      return usuario;
   }
   
   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "pago")
   public Collection<Item> getItems() {
      return items;
   }
   
   public void setItems(Collection<Item> items) {
      this.items = items;
   }
}
