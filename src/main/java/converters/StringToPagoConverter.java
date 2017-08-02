package converters;

import domain.Pago;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.PagoRepository;

@Component
@Transactional
public class StringToPagoConverter implements Converter<String, Pago> {
   @Autowired
   PagoRepository pagoRepository;
   
   @Override
   public Pago convert(String text) {
      Pago res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = pagoRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}

