package converters;

import domain.Profesional;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProfesionalToStringConverter implements Converter<Profesional, String> {
   
   @Override
   public String convert(Profesional profesional) {
      String res;
      
      if (profesional == null)
         res = null;
      else
         res = String.valueOf(profesional.getId());
      
      return res;
      
   }
}