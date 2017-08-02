package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Access(AccessType.FIELD)
@Data
public class Estudio extends DomainEntity {
   
   @NotBlank
   @SafeHtml
   private String centro;
   
   @NotBlank
   @SafeHtml
   private String titulación;
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaInicio;
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaFin;
   
   // Relaciones
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   private Profesional profesional;
}
