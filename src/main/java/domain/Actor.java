package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import security.Cuenta;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.net.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {
   // Atributos
   private String nombre;
   private String telefono;
   private String apellidos;
   private String DNI;
   private String cp;
   private String email;
   private String provincia;
   private URL foto;
   
   @NotBlank
   @SafeHtml
   public String getNombre() {
      return nombre;
   }
   
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   

   @SafeHtml
   public String getTelefono() {
      return telefono;
   }
   
   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }
   
   @NotBlank
   @SafeHtml
   public String getApellidos() {
      return apellidos;
   }
   
   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }
   
   
   @NotBlank
   @SafeHtml
   @Pattern(regexp = "^(\\d{8}([A-Z]|[a-z]{1}))$")
   @Column(unique = true, updatable = false)
   public String getDNI() {
      return DNI;
   }
   
   public void setDNI(String DNI) {
      this.DNI = DNI;
   }
   

   @SafeHtml
   public String getCp() {
      return cp;
   }
   
   public void setCp(String cp) {
      this.cp = cp;
   }
   
   @NotBlank
   @SafeHtml
   @Email
   @Column(unique = true)
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   

   @SafeHtml
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   public URL getFoto() {
      return foto;
   }
   
   public void setFoto(URL picture) {
      this.foto = picture;
   }
   
   // Relaciones
   private Cuenta cuenta;
   
   @NotNull
   @Valid
   @OneToOne(cascade = CascadeType.ALL, optional = false)
   public Cuenta getCuenta() {
      return cuenta;
   }
   
   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }
}