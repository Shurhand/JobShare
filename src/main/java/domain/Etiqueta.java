package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Etiqueta extends DomainEntity {
   
   @NotBlank
   @SafeHtml
   private String nombre;
   
   private boolean activada;
   
   @NotNull
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
   // Relaciones
   
   @ManyToMany
   private Collection<Item> items;
}
