package converters;

import domain.Usuario;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.UsuarioRepository;

@Component
@Transactional
public class StringToUsuarioConverter implements Converter<String, Usuario> {
   @Autowired
   UsuarioRepository usuarioRepository;
   
   @Override
   public Usuario convert(String text) {
      Usuario res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = usuarioRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
   
}
