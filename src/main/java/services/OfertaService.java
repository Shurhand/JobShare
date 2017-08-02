package services;

import domain.Estado;
import domain.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.OfertaRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Service
@Transactional
public class OfertaService implements AbstractService<Oferta> {
   
   @Autowired
   private OfertaRepository ofertaRepository;
   
   
   @Override
   public Oferta create() {
      LocalDate fechaCreacion = LocalDate.now();
      Estado estado = Estado.ACTIVA;
      Oferta oferta = new Oferta();
      
      oferta.setEstado(estado);
      oferta.setFechaCreacion(fechaCreacion);
      
      return oferta;
   }
   
   @Override
   public void save(@NotNull Oferta oferta) {
      ofertaRepository.save(oferta);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Oferta> ofertas) {
      ofertaRepository.save(ofertas);
   }
   
   @Override
   public Oferta saveWithReturn(@NotNull Oferta oferta) {
      return ofertaRepository.save(oferta);
   }
   
   @Override
   public void delete(@NotNull Oferta oferta) {
      ofertaRepository.delete(oferta);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Oferta> ofertas) {
      ofertaRepository.delete(ofertas);
   }
   
   @Override
   public Collection<Oferta> findAll() {
      return ofertaRepository.findAll();
   }
   
   @Override
   @NotNull
   public Oferta findOne(@NotNull @Min(1) Integer ofertaID) {
      return ofertaRepository.findOne(ofertaID);
   }
}
