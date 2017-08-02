package converters;

import domain.Etiqueta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.EtiquetaRepository;

@Component
@Transactional
public class StringToEtiquetaConverter implements Converter<String, Etiqueta> {
   @Autowired
   EtiquetaRepository etiquetaRepositoryRepository;
   
   @Override
   public Etiqueta convert(String text) {
      Etiqueta res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = etiquetaRepositoryRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}

