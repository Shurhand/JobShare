package converters;

import domain.Trabajo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TrabajoToStringConverter implements Converter<Trabajo, String> {
   
   @Override
   public String convert(Trabajo trabajo) {
      String res;
      
      if (trabajo == null)
         res = null;
      else
         res = String.valueOf(trabajo.getId());
      
      return res;
      
   }
}
