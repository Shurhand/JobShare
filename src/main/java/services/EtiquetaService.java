package services;

import domain.Etiqueta;
import domain.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EtiquetaRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class EtiquetaService extends AbstractServiceImpl implements AbstractService<Etiqueta> {
   @Autowired
   private EtiquetaRepository etiquetaRepository;
   @Autowired
   private ActorService actorService;
   
   @Override
   public Etiqueta create() {
      actorService.checkIfAutenticado();
      Set<Peticion> peticiones = new HashSet<>();
      Etiqueta etiqueta = new Etiqueta();
      etiqueta.setPeticiones(peticiones);
      etiqueta.setActivada(true);
      
      return etiqueta;
   }
   
   @Override
   public void save(@NotNull Etiqueta etiqueta) {
      actorService.checkIfAutenticado();
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
   
   public Collection<Etiqueta> getEtiquetasActivas() {
      return etiquetaRepository.getEtiquetasActivas();
   }
   
   @Override
   @NotNull
   public Etiqueta findOne(@NotNull @Min(1) Integer etiquetaID) {
      return etiquetaRepository.findOne(etiquetaID);
   }
   
   public void activar(Etiqueta etiqueta) {
      if (etiqueta.isActivada()) {
         etiqueta.setActivada(false);
      } else {
         etiqueta.setActivada(true);
      }
   }
   
   public Collection<String> getNombreEtiquetas() {
      return etiquetaRepository.getNombreEtiquetas();
   }
}
