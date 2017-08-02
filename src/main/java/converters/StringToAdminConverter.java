package converters;

import domain.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AdminRepository;

@Component
@Transactional
public class StringToAdminConverter implements Converter<String, Admin> {
   @Autowired
   AdminRepository adminRepository;
   
   @Override
   public Admin convert(String text) {
      Admin res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = adminRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
}

