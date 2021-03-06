package converters;

import domain.Admin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AdminToStringConverter implements Converter<Admin, String> {
   @Override
   public String convert(Admin admin) {
      String res;
      
      if (admin == null)
         res = null;
      else
         res = String.valueOf(admin.getId());
      
      return res;
      
   }
}

