package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import security.Rol;

@Component
@Transactional
public class RolToStringConverter implements Converter<Rol, String> {
   
   @Override
   public String convert(Rol authority) {
      String result;
      
      if (authority == null)
         result = null;
      else
         result = String.valueOf(authority.getAuthority());
      
      
      return result;
   }
   
}