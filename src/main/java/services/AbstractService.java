package services;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public interface AbstractService<T> {
   T create();
   
   void save(@NotNull T t);
   
   void saveAll(@NotNull Iterable<T> ts);
   
   T saveWithReturn(@NotNull T t);
   
   void delete(@NotNull T t);
   
   void deleteAll(@NotNull Iterable<T> ts);
   
   Collection<T> findAll();
   
   T findOne(@NotNull @Min(1) Integer tID);
   
   
}
