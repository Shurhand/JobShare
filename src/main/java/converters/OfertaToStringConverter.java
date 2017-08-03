package converters;

import domain.Oferta;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OfertaToStringConverter implements Converter<Oferta, String> {
   @Override
   public String convert(Oferta oferta) {
      String res;
      
      if (oferta == null)
         res = null;
      else
         res = String.valueOf(oferta.getId());
      
      return res;
      
   }
}