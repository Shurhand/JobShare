package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)

public class Profesional extends Usuario {
   // Relaciones
   private Collection<Oferta> ofertas;
   private Collection<Trabajo> trabajos;
   private Collection<Estudio> estudios;
   
   public Profesional() {
      super();
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesional")
   @JsonManagedReference
   public Collection<Oferta> getOfertas() {
      return ofertas;
   }
   
   public void setOfertas(Collection<Oferta> ofertas) {
      this.ofertas = ofertas;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesional")
   @JsonManagedReference
   public Collection<Trabajo> getTrabajos() {
      return trabajos;
   }
   
   public void setTrabajos(Collection<Trabajo> trabajos) {
      this.trabajos = trabajos;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesional")
   @JsonManagedReference
   public Collection<Estudio> getEstudios() {
      return estudios;
   }
   
   public void setEstudios(Collection<Estudio> estudios) {
      this.estudios = estudios;
   }
   
   @Transient
   public Double getValoracionTotal() {
      return getOfertas().stream().filter(x -> x.getValoracion() != null).mapToDouble(x -> x.getValoracion().getPuntuacion()).average().orElse(0.0);
   }
   
   @Transient
   public int getIDOfertaDelItem(Collection<Oferta> ofertas) {
      int id = 0;
      for (Oferta o : ofertas) {
         if (this.getOfertas().contains(o)) {
            id = o.getId();
            break;
         }
         
      }
      return id;
   }
}
