package services;

import domain.Estado;
import domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ItemRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class ItemService extends AbstractServiceImpl implements AbstractService<Item> {
   @Autowired
   private ItemRepository itemRepository;
   @Autowired
   private ActorService actorService;
   
   @Override
   public Item create() {
      actorService.checkIfUsuarioOProfesional();
      Estado estado = Estado.ACTIVA;
      Item item = new Item();
   
      item.setEstado(estado);
      
      return item;
   }
   
   @Override
   public void save(@NotNull Item item) {
      actorService.checkIfUsuarioOProfesional();
      itemRepository.save(item);
   }
   
   @Override
   public void saveAll(Iterable<Item> items) {
      actorService.checkIfUsuarioOProfesional();
      itemRepository.save(items);
   }
   
   @Override
   public Item saveWithReturn(@NotNull Item item) {
      actorService.checkIfUsuarioOProfesional();
      return itemRepository.save(item);
   }
   
   @Override
   public void delete(@NotNull Item item) {
      actorService.checkIfUsuarioOProfesional();
      itemRepository.delete(item);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Item> items) {
      actorService.checkIfUsuarioOProfesional();
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
   
   public boolean checkDentroDelLimitePeticion(Item item) {
      return item.getPeticion().getItems().size() <= 4;
   }
}

