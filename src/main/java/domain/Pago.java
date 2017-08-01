package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Data
public class Pago extends  DomainEntity {
   
   @NotBlank
   @SafeHtml
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private LocalDate fechaCreacion;
   
}
