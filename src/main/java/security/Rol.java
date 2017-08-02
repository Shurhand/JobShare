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
public class Rol implements GrantedAuthority {
   
   // Constructors -----------------------------------------------------------
   
   private static final long serialVersionUID = 1L;
   
   public Rol() {
      super();
   }
   
   // Values -----------------------------------------------------------------
   
   public static final String ADMIN = "ADMIN";
   public static final String USUARIO = "USUARIO";
   public static final String PROFESIONAL = "PROFESIONAL";
   
   
   // Attributes -------------------------------------------------------------
   
   private String authority;
   
   @NotBlank
   @Pattern(regexp = "^" + ADMIN + "|" + USUARIO + "|" + PROFESIONAL + "$")
   @Override
   public String getAuthority() {
      return authority;
   }
   
   public void setAuthority(String authority) {
      this.authority = authority;
   }
   
   public static Collection<Rol> listAuthorities() {
      Collection<Rol> result;
      Rol autoridad;
   
      result = new ArrayList<>();
      
      autoridad = new Rol();
      autoridad.setAuthority(ADMIN);
      result.add(autoridad);
      
      autoridad = new Rol();
      autoridad.setAuthority(USUARIO);
      result.add(autoridad);
      
      autoridad = new Rol();
      autoridad.setAuthority(PROFESIONAL);
      result.add(autoridad);
      
      return result;
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
         result = (this.getAuthority().equals(((Rol) other).getAuthority()));
      
      return result;
   }
   
}
