package services;

import domain.Item;
import domain.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PeticionRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class PeticionService implements AbstractService<Peticion> {
   
   @Autowired
   private PeticionRepository peticionRepository;
   
   @Override
   public Peticion create() {
      Collection<Item> items = new ArrayList<>();
      LocalDate fechaCreacion = LocalDate.now();
      Peticion peticion = new Peticion();
      
      peticion.setFechaCreacion(fechaCreacion);
      peticion.setItems(items);
      
      return peticion;
   }
   
   @Override
   public void save(@NotNull Peticion peticion) {
      peticionRepository.save(peticion);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Peticion> peticiones) {
      peticionRepository.save(peticiones);
   }
   
   @Override
   public Peticion saveWithReturn(@NotNull Peticion peticion) {
      return peticionRepository.save(peticion);
   }
   
   @Override
   public void delete(@NotNull Peticion peticion) {
      peticionRepository.delete(peticion);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Peticion> peticiones) {
      peticionRepository.delete(peticiones);
   }
   
   @Override
   public Collection<Peticion> findAll() {
      return peticionRepository.findAll();
   }
   
   @Override
   @NotNull
   public Peticion findOne(@NotNull @Min(1) Integer peticionID) {
      return peticionRepository.findOne(peticionID);
   }
}
