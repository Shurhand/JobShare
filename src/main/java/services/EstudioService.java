package services;

import domain.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EstudioRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class EstudioService extends AbstractServiceImpl implements AbstractService<Estudio> {
   @Autowired
   private EstudioRepository estudioRepository;
   
   @Override
   public Estudio create() {
      Estudio estudio = new Estudio();
      
      return estudio;
   }
   
   @Override
   public void save(@NotNull Estudio estudio) {
      estudioRepository.save(estudio);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Estudio> estudios) {
      estudioRepository.save(estudios);
   }
   
   @Override
   public Estudio saveWithReturn(@NotNull Estudio estudio) {
      return estudioRepository.save(estudio);
   }
   
   @Override
   public void delete(@NotNull Estudio estudio) {
      estudioRepository.delete(estudio);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Estudio> estudios) {
      estudioRepository.delete(estudios);
   }
   
   @Override
   public Collection<Estudio> findAll() {
      return estudioRepository.findAll();
   }
   
   @Override
   @NotNull
   public Estudio findOne(@NotNull @Min(1) Integer estudioID) {
      return estudioRepository.findOne(estudioID);
   }
}
