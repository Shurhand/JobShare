package domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Valoracion extends DomainEntity {
   
   @NotNull
   @SafeHtml
   @Range(min = 1, max = 5)
   private Double puntuacion;
   
   @NotBlank
   @SafeHtml
   private String comentario;
   
   @NotNull
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
   // Relaciones
   
   @NotNull
   @Valid
   @OneToOne(optional = false)
   private Oferta oferta;
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   private Profesional profesional;
}
