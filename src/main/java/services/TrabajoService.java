package services;

import domain.Trabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TrabajoRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class TrabajoService extends AbstractServiceImpl implements AbstractService<Trabajo> {
   @Autowired
   private TrabajoRepository trabajoRepository;
   
   @Override
   public Trabajo create() {
      Trabajo trabajo = new Trabajo();
      return trabajo;
   }
   
   @Override
   public void save(@NotNull Trabajo trabajo) {
      trabajoRepository.save(trabajo);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Trabajo> trabajos) {
      trabajoRepository.save(trabajos);
   }
   
   @Override
   public Trabajo saveWithReturn(@NotNull Trabajo trabajo) {
      return trabajoRepository.save(trabajo);
   }
   
   @Override
   public void delete(@NotNull Trabajo trabajo) {
      trabajoRepository.delete(trabajo);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Trabajo> trabajos) {
      trabajoRepository.delete(trabajos);
   }
   
   @Override
   public Collection<Trabajo> findAll() {
      return null;
   }
   
   @Override
   @NotNull
   public Trabajo findOne(@NotNull @Min(1) Integer trabajoID) {
      return trabajoRepository.findOne(trabajoID);
   }
}
