package converters;

import domain.Valoracion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ValoracionRepository;

@Component
@Transactional
public class StringToValoracionConverter implements Converter<String, Valoracion> {
   @Autowired
   ValoracionRepository valoracionRepository;
   
   @Override
   public Valoracion convert(String text) {
      Valoracion res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = valoracionRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}

