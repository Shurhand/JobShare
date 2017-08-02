package domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Oferta extends DomainEntity {
   
   @NotNull
   @SafeHtml
   @Range(min = 1, max = 10000)
   private Double precio;
   
   @NotBlank
   @SafeHtml
   private String comentario;
   
   @NotNull
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
   @NotNull
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCaducidad;
   
   private URL foto;
   
   @NotNull
   @Enumerated
   private Estado estado;
   
   // Relaciones
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   private Profesional profesional;
   
   @NotNull
   @Valid
   @OneToOne(optional = true)
   private Valoracion valoracion;
   
   @NotNull
   @Valid
   @OneToOne(optional = false)
   private Item item;
   
}
