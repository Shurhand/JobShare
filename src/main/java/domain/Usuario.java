package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
   private Collection<Valoracion> valoraciones;
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   @JsonManagedReference
   public Collection<Peticion> getPeticiones() {
      return peticiones;
   }
   
   public void setPeticiones(Collection<Peticion> peticiones) {
      this.peticiones = peticiones;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   @JsonManagedReference
   public Collection<Pago> getPagos() {
      return pagos;
   }
   
   public void setPagos(Collection<Pago> pagos) {
      this.pagos = pagos;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
   @JsonManagedReference
   public Collection<Valoracion> getValoraciones() {
      return valoraciones;
   }
   
   public void setValoraciones(Collection<Valoracion> valoraciones) {
      this.valoraciones = valoraciones;
   }
   
   @Transient
   public boolean puedeValorarAProfesional(Oferta oferta) {
      boolean res = false;
      if (oferta.getEstado().equals(Estado.CONTRATADA) && this.getPeticiones().contains(oferta.getItem().getPeticion())) {
         if (oferta.getValoracion() != null) {
            if (! this.getValoraciones().contains(oferta.getValoracion())) {
               res = true;
            }
         } else if (oferta.getValoracion() == null) {
            res = true;
         }
      }
   
      return res;
   }
   
   @Transient
   public boolean yaHaValoradoAProfesional(Oferta oferta) {
      return this.valoraciones.contains(oferta.getValoracion());
   }
}
