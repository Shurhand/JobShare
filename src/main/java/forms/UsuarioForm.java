package forms;

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioForm {
   public UsuarioForm() {
      super();
   }
   
   private String nombre;
   private String telefono;
   private String apellidos;
   private String DNI;
   private String cp;
   private String email;
   private String provincia;
   private String username;
   private String password;
   private String confirmarPassword;
   private boolean checkTerminos;
   private String foto;
   private String descripcion;
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getNombre() {
      return nombre;
   }
   
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getTelefono() {
      return telefono;
   }
   
   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getApellidos() {
      return apellidos;
   }
   
   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Pattern(regexp = "^(\\d{8}([A-Z]|[a-z]{1}))$")
   public String getDNI() {
      return DNI;
   }
   
   public void setDNI(String DNI) {
      this.DNI = DNI;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getCp() {
      return cp;
   }
   
   public void setCp(String cp) {
      this.cp = cp;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Email
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Size(min = 5, max = 32)
   public String getUsername() {
      return username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Size(min = 5, max = 32)
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Size(min = 5, max = 32)
   public String getConfirmarPassword() {
      return confirmarPassword;
   }
   
   public void setConfirmarPassword(String confirmarPassword) {
      this.confirmarPassword = confirmarPassword;
   }
   
   @AssertTrue
   public boolean isCheckTerminos() {
      return checkTerminos;
   }
   
   public void setCheckTerminos(boolean checkTerminos) {
      this.checkTerminos = checkTerminos;
   }
   
   @SafeHtml
   @Length(max = 250)
   @URL
   public String getFoto() {
      return foto;
   }
   
   public void setFoto(String foto) {
      this.foto = foto;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getDescripcion() {
      return descripcion;
   }
   
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
}