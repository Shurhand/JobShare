package forms;

import org.hibernate.validator.constraints.*;

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
   private String idTokenString;
   // Del payload
   private boolean emailVerified;
   private String email;
   private String subject;
   private String pictureUrl;
   private String givenName;
   private String familyName;
   
   
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
   public String getIdTokenString() {
      return idTokenString;
   }
   
   public void setIdTokenString(String idTokenString) {
      this.idTokenString = idTokenString;
   }
   
   public boolean isEmailVerified() {
      return emailVerified;
   }
   
   public void setEmailVerified(boolean emailVerified) {
      this.emailVerified = emailVerified;
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
   public String getSubject() {
      return subject;
   }
   
   public void setSubject(String subject) {
      this.subject = subject;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @URL
   public String getPictureUrl() {
      return pictureUrl;
   }
   
   public void setPictureUrl(String pictureUrl) {
      this.pictureUrl = pictureUrl;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getGivenName() {
      return givenName;
   }
   
   public void setGivenName(String givenName) {
      this.givenName = givenName;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getFamilyName() {
      return familyName;
   }
   
   public void setFamilyName(String familyName) {
      this.familyName = familyName;
   }
}