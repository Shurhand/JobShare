package converters;

import domain.Estudio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.EstudioRepository;

@Component
@Transactional
public class StringToEstudioConverter implements Converter<String, Estudio> {
   @Autowired
   EstudioRepository estudioRepository;
   
   @Override
   public Estudio convert(String text) {
      Estudio res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = estudioRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}


