package domain;

import org.hibernate.validator.constraints.*;
import security.Cuenta;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;

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
   private String descripcion;
   private String foto;
   
   public Actor() {
      super();
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 50)
   public String getNombre() {
      return nombre;
   }
   
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 20)
   public String getTelefono() {
      return telefono;
   }
   
   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 50)
   public String getApellidos() {
      return apellidos;
   }
   
   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Pattern(regexp = "^(\\d{8}([A-Z]|[a-z]{1}))$")
   @Column(unique = true, updatable = false)
   public String getDNI() {
      return DNI;
   }
   
   public void setDNI(String DNI) {
      this.DNI = DNI;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 10)
   public String getCp() {
      return cp;
   }
   
   public void setCp(String cp) {
      this.cp = cp;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Email
   @Column(unique = true)
   @Length(max = 80)
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 50)
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   @Length(max = 250)
   @URL
   @SafeHtml
   public String getFoto() {
      return foto;
   }
   
   public void setFoto(String picture) {
      this.foto = picture;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getDescripcion() {
      return descripcion;
   }
   
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
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
   
   @Transient
   public boolean tieneYaOfertas(Collection<Oferta> ofertasPropias, Collection<Oferta> ofertasAComparar) {
      return Collections.disjoint(ofertasPropias, ofertasAComparar);
      
   }
   

}