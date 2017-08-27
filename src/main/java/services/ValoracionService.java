package services;

import domain.Oferta;
import domain.Usuario;
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
   @Autowired
   private UsuarioService usuarioService;
   
   @Override
   public Valoracion create() {
      actorService.checkIfUsuarioOProfesional();
      Usuario usuario = usuarioService.findUsuario();
      
      Valoracion valoracion = new Valoracion();
      LocalDate fechaCreacion = LocalDate.now();
      
      valoracion.setFechaCreacion(fechaCreacion);
      valoracion.setUsuario(usuario);
      
      
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
      Oferta ofertaUsuario = valoracion.getOferta();
      valoracion.getOferta().setValoracion(null);
      Collection<Oferta> ofertas = valoracion.getOferta().getProfesional().getOfertas();
      for (Oferta f : ofertas) {
         if (f.equals(ofertaUsuario)) {
            f.setValoracion(null);
         }
      }
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
