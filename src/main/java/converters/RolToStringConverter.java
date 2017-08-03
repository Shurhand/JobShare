package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import security.Autoridad;

@Component
@Transactional
public class RolToStringConverter implements Converter<Autoridad, String> {
   @Override
   public String convert(Autoridad autoridad) {
      String result;
   
      if (autoridad == null)
         result = null;
      else
         result = String.valueOf(autoridad.getAuthority());
      
      
      return result;
   }
   
}