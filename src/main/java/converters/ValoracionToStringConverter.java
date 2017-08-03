package converters;

import domain.Valoracion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ValoracionToStringConverter implements Converter<Valoracion, String> {
   @Override
   public String convert(Valoracion valoracion) {
      String res;
      
      if (valoracion == null)
         res = null;
      else
         res = String.valueOf(valoracion.getId());
      
      return res;
      
   }
}
