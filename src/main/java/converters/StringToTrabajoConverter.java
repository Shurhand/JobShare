package converters;

import domain.Trabajo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.TrabajoRepository;

@Component
@Transactional
public class StringToTrabajoConverter implements Converter<String, Trabajo> {
   @Autowired
   TrabajoRepository trabajoRepository;
   
   @Override
   public Trabajo convert(String text) {
      Trabajo res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = trabajoRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}
