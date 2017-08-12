package converters;

import domain.Usuario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UsuarioToStringConverter implements Converter<Usuario, String> {
   @Override
   public String convert(Usuario usuario) {
      String res;
      
      if (usuario == null) res = null;
      else res = String.valueOf(usuario.getId());
      
      return res;
      
   }
}
