package converters;

import domain.Etiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.EtiquetaService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class StringArrayToCollectionEtiquetas implements Converter<String[], Set<Etiqueta>> {
   @Autowired
   private EtiquetaService etiquetaService;
   
   @Override
   public Set<Etiqueta> convert(String[] array) {
      Set<Etiqueta> etiquetas = new HashSet<>();
      try {
         etiquetas = Arrays.stream(array).skip(1).map(x -> Integer.valueOf(x)).map(x -> etiquetaService.findOne(x)).collect(Collectors.toSet());
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      
      return etiquetas;
   }
}
