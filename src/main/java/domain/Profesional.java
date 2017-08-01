package domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Profesional extends Usuario {
   
   // Relaciones
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesional")
   private Collection<Oferta> ofertas;
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesional")
   private Collection<Trabajo> trabajos;
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesional")
   private Collection<Estudio> estudios;
}
