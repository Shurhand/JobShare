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
   @Autowired
   private AdminService adminService;
   @Autowired
   private ActorService actorService;
   
   @Override
   public Valoracion create() {
      actorService.checkIfUsuarioOProfesional();
      
      Valoracion valoracion = new Valoracion();
      LocalDate fechaCreacion = LocalDate.now();
      
      valoracion.setFechaCreacion(fechaCreacion);
      
      return valoracion;
   }
   
   @Override
   public void save(@NotNull Valoracion valoracion) {
      actorService.checkIfUsuarioOProfesional();
      
      valoracionReporsitory.save(valoracion);
   }
   
   @Override
   public void saveAll(Iterable<Valoracion> valoraciones) {
      actorService.checkIfUsuarioOProfesional();
      valoracionReporsitory.save(valoraciones);
   }
   
   @Override
   public Valoracion saveWithReturn(@NotNull Valoracion valoracion) {
      actorService.checkIfUsuarioOProfesional();
      return valoracionReporsitory.save(valoracion);
   }
   
   @Override
   public void delete(@NotNull Valoracion valoracion) {
      adminService.checkIfAdmin();
      valoracion.getOferta().setValoracion(null);
      valoracion.getUsuario().getValoraciones().remove(valoracion);
      
      valoracionReporsitory.delete(valoracion);
   }
   
   @Override
   public void deleteAll(Iterable<Valoracion> valoraciones) {
      adminService.checkIfAdmin();
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
