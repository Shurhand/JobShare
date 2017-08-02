package converters;

import domain.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ItemToStringConverter implements Converter<Item, String> {
   
   @Override
   public String convert(Item item) {
      String res;
      
      if (item == null)
         res = null;
      else
         res = String.valueOf(item.getId());
      
      return res;
      
   }
}
