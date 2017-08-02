package services;

import domain.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProfesionalRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class ProfesionalService implements AbstractService<Profesional> {
   
   @Autowired
   private ProfesionalRepository profesionalRepository;
   
   @Override
   public Profesional create() {
      return null;
   }
   
   @Override
   public void save(@NotNull Profesional profesional) {
      profesionalRepository.save(profesional);
   }
   
   @Override
   public void saveAll(Iterable<Profesional> profesionales) {
      profesionalRepository.save(profesionales);
   }
   
   @Override
   public Profesional saveWithReturn(@NotNull Profesional profesional) {
      return profesionalRepository.save(profesional);
   }
   
   @Override
   public void delete(@NotNull Profesional profesional) {
      profesionalRepository.delete(profesional);
   }
   
   @Override
   public void deleteAll(Iterable<Profesional> profesionales) {
      profesionalRepository.delete(profesionales);
   }
   
   @Override
   public Collection<Profesional> findAll() {
      return profesionalRepository.findAll();
   }
   
   @Override
   @NotNull
   public Profesional findOne(@NotNull @Min(1) Integer profesionalID) {
      return profesionalRepository.findOne(profesionalID);
   }
}
