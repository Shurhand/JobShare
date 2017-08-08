
package security;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class Credenciales {
   
   // Constructors -----------------------------------------------------------
   // Attributes -------------------------------------------------------------
   private String username;
   private String password;
   
   public Credenciales() {
      super();
   }
   
   @NotBlank
   @Size(min = 5, max = 32)
   public String getUsername() {
      return username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   @NotBlank
   @Size(min = 5, max = 32)
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
}
