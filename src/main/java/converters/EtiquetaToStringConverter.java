package converters;

import domain.Etiqueta;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EtiquetaToStringConverter implements Converter<Etiqueta, String> {
   
   @Override
   public String convert(Etiqueta etiqueta) {
      String res;
      
      if (etiqueta == null)
         res = null;
      else
         res = String.valueOf(etiqueta.getId());
      
      return res;
      
   }
}