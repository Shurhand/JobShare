package services;

import domain.Estado;
import domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ItemRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Service
@Transactional
public class ItemService extends AbstractServiceImpl implements AbstractService<Item> {
   @Autowired
   private ItemRepository itemRepository;
   
   @Override
   public Item create() {
      LocalDate fechaCreacion = LocalDate.now();
      Estado estado = Estado.ACTIVA;
      Item item = new Item();
      
      
      item.setFechaCreacion(fechaCreacion);
      item.setEstado(estado);
      
      return item;
   }
   
   @Override
   public void save(@NotNull Item item) {
      itemRepository.save(item);
   }
   
   @Override
   public void saveAll(Iterable<Item> items) {
      itemRepository.save(items);
   }
   
   @Override
   public Item saveWithReturn(@NotNull Item item) {
      return itemRepository.save(item);
   }
   
   @Override
   public void delete(@NotNull Item item) {
      itemRepository.delete(item);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Item> items) {
      itemRepository.delete(items);
   }
   
   @Override
   public Collection<Item> findAll() {
      return itemRepository.findAll();
   }
   
   @Override
   @NotNull
   public Item findOne(@NotNull @Min(1) Integer itemID) {
      return itemRepository.findOne(itemID);
   }
}
