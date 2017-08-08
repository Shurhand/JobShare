package forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.net.URL;

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
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   @NotBlank
   @SafeHtml
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   @SafeHtml
   @Size(min = 5, max = 32)
   public String getUsername() {
      return username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   @SafeHtml
   @Size(min = 5, max = 32)
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   @SafeHtml
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
   
   public URL getFoto() {
      return foto;
   }
   
   public void setFoto(URL foto) {
      this.foto = foto;
   }
}
