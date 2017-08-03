/* Authority.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */
package security;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Embeddable
@Access(AccessType.PROPERTY)
public class Autoridad implements GrantedAuthority {
   // Values -----------------------------------------------------------------
   public static final String ADMIN = "ADMIN";
   public static final String USUARIO = "USUARIO";
   public static final String PROFESIONAL = "PROFESIONAL";
   // Constructors -----------------------------------------------------------
   private static final long serialVersionUID = 1L;
   // Attributes -------------------------------------------------------------
   private String authority;
   
   public Autoridad() {
      super();
   }
   
   public static Collection<Autoridad> listAuthorities() {
      Collection<Autoridad> result;
      Autoridad authority;
   
      result = new ArrayList<>();
      
      authority = new Autoridad();
      authority.setAuthority(ADMIN);
      result.add(authority);
      
      authority = new Autoridad();
      authority.setAuthority(USUARIO);
      result.add(authority);
      
      authority = new Autoridad();
      authority.setAuthority(PROFESIONAL);
      result.add(authority);
      
      return result;
   }
   
   @NotBlank
   @Pattern(regexp = "^" + ADMIN + "|" + USUARIO + "|" + PROFESIONAL + "$")
   @Override
   public String getAuthority() {
      return authority;
   }
   
   public void setAuthority(String authority) {
      this.authority = authority;
   }
   
   // Equality ---------------------------------------------------------------
   
   @Override
   public int hashCode() {
      return this.getAuthority().hashCode();
   }
   
   @Override
   public boolean equals(Object other) {
      boolean result;
      
      if (this == other)
         result = true;
      else if (other == null)
         result = false;
      else if (! this.getClass().isInstance(other))
         result = false;
      else
         result = (this.getAuthority().equals(((Autoridad) other).getAuthority()));
      
      return result;
   }
   
}
