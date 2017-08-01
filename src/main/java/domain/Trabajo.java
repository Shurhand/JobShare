package domain;

import lombok.Data;
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
public class Trabajo extends  DomainEntity {
   
   @NotBlank
   @SafeHtml
   private String empresa;
   
   @NotBlank
   @SafeHtml
   private String puesto;
   
   @NotNull
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaInicio;
   
   @NotNull
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaFin;
   
   // Relaciones
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   private Profesional profesional;
}
