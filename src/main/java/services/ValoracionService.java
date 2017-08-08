package services;

import domain.Valoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ValoracionRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Service
@Transactional
public class ValoracionService extends AbstractServiceImpl implements AbstractService<Valoracion> {
   @Autowired
   private ValoracionRepository valoracionReporsitory;
   
   @Override
   public Valoracion create() {
      Valoracion valoracion = new Valoracion();
      LocalDate fechaCreacion = LocalDate.now();
      
      valoracion.setFechaCreacion(fechaCreacion);
      
      return valoracion;
   }
   
   @Override
   public void save(@NotNull Valoracion valoracion) {
      valoracionReporsitory.save(valoracion);
   }
   
   @Override
   public void saveAll(Iterable<Valoracion> valoraciones) {
      valoracionReporsitory.save(valoraciones);
   }
   
   @Override
   public Valoracion saveWithReturn(@NotNull Valoracion valoracion) {
      return valoracionReporsitory.save(valoracion);
   }
   
   @Override
   public void delete(@NotNull Valoracion valoracion) {
      valoracionReporsitory.delete(valoracion);
   }
   
   @Override
   public void deleteAll(Iterable<Valoracion> valoraciones) {
      valoracionReporsitory.delete(valoraciones);
   }
   
   @Override
   public Collection<Valoracion> findAll() {
      return valoracionReporsitory.findAll();
   }
   
   @Override
   @NotNull
   public Valoracion findOne(@NotNull @Min(1) Integer valoracionID) {
      return valoracionReporsitory.findOne(valoracionID);
   }
   
   
}
