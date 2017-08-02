package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.FIELD)
@Data
public class Etiqueta extends DomainEntity {
   
   @NotBlank
   @SafeHtml
   private String nombre;
   
   private boolean activada;
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
   // Relaciones
   
   @ManyToMany
   private Collection<Item> items;
}
