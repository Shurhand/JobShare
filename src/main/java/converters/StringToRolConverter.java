package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import security.Autoridad;

@Component
@Transactional
public class StringToRolConverter implements Converter<String, Autoridad> {
   
   @Override
   public Autoridad convert(String text) {
      Autoridad result;
      try {
         result = new Autoridad();
         result.setAuthority(text);
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      
      return result;
   }
   
}
