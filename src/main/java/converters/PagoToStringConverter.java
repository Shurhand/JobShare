package converters;

import domain.Pago;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PagoToStringConverter implements Converter<Pago, String> {
   
   @Override
   public String convert(Pago pago) {
      String res;
      
      if (pago == null)
         res = null;
      else
         res = String.valueOf(pago.getId());
      
      return res;
      
   }
}
