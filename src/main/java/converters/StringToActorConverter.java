package converters;

import domain.Actor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {
   @Autowired
   ActorRepository actorRepository;
   
   @Override
   public Actor convert(String text) {
      Actor res;
      int id;
      try {
         if (StringUtils.isEmpty(text))
            res = null;
         else {
            id = Integer.valueOf(text);
            res = actorRepository.findOne(id);
         }
      } catch (Throwable oops) {
         throw new IllegalArgumentException(oops);
      }
      return res;
   }
   
}
