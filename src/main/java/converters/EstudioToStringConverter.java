package converters;

import domain.Estudio;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EstudioToStringConverter implements Converter<Estudio, String> {
   
   @Override
   public String convert(Estudio estudio) {
      String res;
      
      if (estudio == null)
         res = null;
      else
         res = String.valueOf(estudio.getId());
      
      return res;
      
   }
}