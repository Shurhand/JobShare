package converters;

import domain.Profesional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProfesionalRepository;

@Component
@Transactional
public class StringToProfesionalConverter implements Converter<String, Profesional> {
   @Autowired
   ProfesionalRepository workRepository;
   
   @Override
   public Profesional convert(String text) {
      Profesional res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = workRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}
