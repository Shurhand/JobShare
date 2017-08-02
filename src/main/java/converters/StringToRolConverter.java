package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import security.Rol;

@Component
@Transactional
public class StringToRolConverter implements Converter<String, Rol> {
   
   @Override
   public Rol convert(String text) {
      Rol result;
      try {
         result = new Rol();
         result.setAuthority(text);
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      
      return result;
   }
   
}
