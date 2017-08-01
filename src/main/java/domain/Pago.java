package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Pago extends DomainEntity {
   
   @NotBlank
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
   // Relaciones
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   private Usuario usuario;
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "pago")
   private Collection<Item> items;
   
}
