package services;

import domain.Estudio;
import domain.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EstudioRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Service
@Transactional
public class EstudioService extends AbstractServiceImpl implements AbstractService<Estudio> {
   @Autowired
   private EstudioRepository estudioRepository;
   @Autowired
   private ProfesionalService profesionalService;
   
   @Override
   public Estudio create() {
      profesionalService.checkIfProfesional();
      Profesional profesional = profesionalService.findProfesional();
      Estudio estudio = new Estudio();
      estudio.setProfesional(profesional);
      
      return estudio;
   }
   
   @Override
   public void save(@NotNull Estudio estudio) {
      profesionalService.checkIfProfesional();
      estudioRepository.save(estudio);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Estudio> estudios) {
      profesionalService.checkIfProfesional();
      estudioRepository.save(estudios);
   }
   
   @Override
   public Estudio saveWithReturn(@NotNull Estudio estudio) {
      profesionalService.checkIfProfesional();
      return estudioRepository.save(estudio);
   }
   
   @Override
   public void delete(@NotNull Estudio estudio) {
      profesionalService.checkIfProfesional();
      estudioRepository.delete(estudio);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Estudio> estudios) {
      profesionalService.checkIfProfesional();
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
   
   public boolean checkFechas(LocalDate fechaInicio, LocalDate fechaFin) {
      return fechaInicio.isAfter(fechaFin);
   }
   
   public boolean checkFechaFin(LocalDate fechaFin) {
      return fechaFin.isAfter(LocalDate.now());
   }
}
