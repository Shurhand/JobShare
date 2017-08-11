package services;

import domain.Profesional;
import domain.Trabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TrabajoRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Service
@Transactional
public class TrabajoService extends AbstractServiceImpl implements AbstractService<Trabajo> {
   @Autowired
   private TrabajoRepository trabajoRepository;
   @Autowired
   private ProfesionalService profesionalService;
   
   @Override
   public Trabajo create() {
      profesionalService.checkIfProfesional();
      Profesional profesional = profesionalService.findProfesional();
      Trabajo trabajo = new Trabajo();
      trabajo.setProfesional(profesional);
      
      return trabajo;
   }
   
   @Override
   public void save(@NotNull Trabajo trabajo) {
      profesionalService.checkIfProfesional();
      this.checkFechaFin(trabajo.getFechaFin());
      this.checkFechas(trabajo.getFechaInicio(), trabajo.getFechaFin());
      trabajoRepository.save(trabajo);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Trabajo> trabajos) {
      profesionalService.checkIfProfesional();
      trabajoRepository.save(trabajos);
   }
   
   @Override
   public Trabajo saveWithReturn(@NotNull Trabajo trabajo) {
      profesionalService.checkIfProfesional();
      return trabajoRepository.save(trabajo);
   }
   
   @Override
   public void delete(@NotNull Trabajo trabajo) {
      profesionalService.checkIfProfesional();
      trabajoRepository.delete(trabajo);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Trabajo> trabajos) {
      profesionalService.checkIfProfesional();
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
   
   public boolean checkFechas(LocalDate fechaInicio, LocalDate fechaFin) {
      return fechaInicio.isAfter(fechaFin);
   }
   
   public boolean checkFechaFin(LocalDate fechaFin) {
      return fechaFin.isAfter(LocalDate.now());
   }
}