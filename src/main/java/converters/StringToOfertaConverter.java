package converters;

import domain.Oferta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.OfertaRepository;

@Component
@Transactional
public class StringToOfertaConverter implements Converter<String, Oferta> {
   @Autowired
   OfertaRepository ofertaRepository;
   
   @Override
   public Oferta convert(String text) {
      Oferta res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = ofertaRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
   
}
