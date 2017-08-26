package forms;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class GoogleForm {
   public GoogleForm() {
      super();
   }
   
   private String telefono;
   private String DNI;
   private String cp;
   private String provincia;
   private String descripcion;
   private Payload payload;
   private String idTokenString;
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getTelefono() {
      return telefono;
   }
   
   public void setTelefono(String telefono) {
      this.telefono = telefono;
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
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getDescripcion() {
      return descripcion;
   }
   
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
   
   @NotNull
   public Payload getPayload() {
      return payload;
   }
   
   public void setPayload(Payload payload) {
      this.payload = payload;
   }
   
   @NotNull
   public String getIdTokenString() {
      return idTokenString;
   }
   
   public void setIdTokenString(String idTokenString) {
      this.idTokenString = idTokenString;
   }
}