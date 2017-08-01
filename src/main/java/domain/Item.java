package domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Item extends  DomainEntity {
   
   @NotBlank
   @SafeHtml
   private String nombre;
   
   @NotBlank
   @SafeHtml
   private String descripcion;
   
   @NotNull
   @SafeHtml
   @Range(min = 1, max = 10000)
   private Double presupuesto;
   
   @NotNull
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
   @NotNull
   @Enumerated
   private Estado estado;
   
   // Relaciones
   
   @NotNull
   @NotEmpty
   @ManyToMany(cascade = { CascadeType.DETACH,CascadeType.MERGE,
   CascadeType.PERSIST, CascadeType.REFRESH }, mappedBy = "items")
   private Collection<Etiqueta> etiquetas;
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   private Peticion peticion;
   
}
