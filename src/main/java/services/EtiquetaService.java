package services;

import domain.Etiqueta;
import domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EtiquetaRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class EtiquetaService extends AbstractServiceImpl implements AbstractService<Etiqueta> {
   @Autowired
   private EtiquetaRepository etiquetaRepository;
   
   @Override
   public Etiqueta create() {
      Collection<Item> items = new ArrayList<>();
      LocalDate fechaCreacion = LocalDate.now();
      
      
      Etiqueta etiqueta = new Etiqueta();
      etiqueta.setItems(items);
      
      return etiqueta;
   }
   
   @Override
   public void save(@NotNull Etiqueta etiqueta) {
      etiquetaRepository.save(etiqueta);
   }
   
   @Override
   public void saveAll(Iterable<Etiqueta> etiquetas) {
      etiquetaRepository.save(etiquetas);
   }
   
   @Override
   public Etiqueta saveWithReturn(@NotNull Etiqueta etiqueta) {
      return etiquetaRepository.save(etiqueta);
   }
   
   @Override
   public void delete(@NotNull Etiqueta etiqueta) {
      etiquetaRepository.delete(etiqueta);
   }
   
   @Override
   public void deleteAll(Iterable<Etiqueta> etiquetas) {
      etiquetaRepository.delete(etiquetas);
   }
   
   @Override
   public Collection<Etiqueta> findAll() {
      return etiquetaRepository.findAll();
   }
   
   @Override
   @NotNull
   public Etiqueta findOne(@NotNull @Min(1) Integer etiquetaID) {
      return etiquetaRepository.findOne(etiquetaID);
   }
}
