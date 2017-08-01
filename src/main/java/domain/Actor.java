package domain;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;
import security.Cuenta;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.net.URL;

@Entity
@Access(AccessType.PROPERTY)
@Data
public abstract class Actor extends DomainEntity {
   
   // Atributos
   
   @NotBlank
   @SafeHtml
   public String nombre;
   
   @NotBlank
   @SafeHtml
   public String telefono;
   
   @NotBlank
   @SafeHtml
   public String apellidos;
   
   @NotBlank
   @SafeHtml
   @Pattern(regexp = "^(\\d{8}([A-Z]|[a-z]{1}))$")
   @Column(unique = true, updatable = false)
   public String DNI;
   
   @NotBlank
   @SafeHtml
   public String cp;
   
   @NotBlank
   @SafeHtml
   @Email
   public String email;
   
   @NotBlank
   @SafeHtml
   public String localidad;
   
   @NotBlank
   @SafeHtml
   public URL picture;

// Relaciones
   
   @NotNull
   @Valid
   @OneToOne(cascade = CascadeType.ALL, optional = false)
   private Cuenta cuenta;

}