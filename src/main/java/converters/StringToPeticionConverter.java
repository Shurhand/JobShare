package converters;

import domain.Peticion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.PeticionRepository;

@Component
@Transactional
public class StringToPeticionConverter implements Converter<String, Peticion> {
   @Autowired
   PeticionRepository peticionRepository;
   
   @Override
   public Peticion convert(String text) {
      Peticion res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = peticionRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}

