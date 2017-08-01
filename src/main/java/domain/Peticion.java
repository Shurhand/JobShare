package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Peticion extends DomainEntity {
   
   @NotBlank
   @SafeHtml
   private String titulo;
   
   @NotBlank
   @SafeHtml
   private String descripcion;
   
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
   @OneToOne(optional = false)
   private Usuario usuario;
   
   @NotNull
   @NotEmpty
   @Valid
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "peticion")
   private Collection<Item> items;
 
}
