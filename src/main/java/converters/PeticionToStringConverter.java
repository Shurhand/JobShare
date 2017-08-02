package converters;

import domain.Peticion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class PeticionToStringConverter implements Converter<Peticion, String> {
   
   @Override
   public String convert(Peticion peticion) {
      String res;
      
      if (peticion == null)
         res = null;
      else
         res = String.valueOf(peticion.getId());
      
      return res;
      
   }
}
