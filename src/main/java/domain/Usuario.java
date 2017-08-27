package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

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
   public boolean puedeValorarAProfesional(Profesional profesional) {
      return ! this.valoraciones.contains(profesional.getOfertas().stream().filter(x -> x.getEstado().equals(Estado.CONTRATADA)).map(x -> x.getValoracion()).collect(Collectors.toList()));
   }
}
