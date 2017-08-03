package services;

import domain.Item;
import domain.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PagoRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class PagoService implements AbstractService<Pago> {
   @Autowired
   private PagoRepository pagoRepository;
   
   @Override
   public Pago create() {
      Collection<Item> items = new ArrayList<>();
      Pago pago = new Pago();
      pago.setItems(items);
      return pago;
   }
   
   @Override
   public void save(@NotNull Pago pago) {
      pagoRepository.save(pago);
   }
   
   @Override
   public void saveAll(Iterable<Pago> pagos) {
      pagoRepository.save(pagos);
   }
   
   @Override
   public Pago saveWithReturn(@NotNull Pago pago) {
      return pagoRepository.save(pago);
   }
   
   @Override
   public void delete(@NotNull Pago pago) {
      pagoRepository.delete(pago);
   }
   
   @Override
   public void deleteAll(Iterable<Pago> pagos) {
      pagoRepository.delete(pagos);
   }
   
   @Override
   public Collection<Pago> findAll() {
      return pagoRepository.findAll();
   }
   
   @Override
   @NotNull
   public Pago findOne(@NotNull @Min(1) Integer pagoID) {
      return pagoRepository.findOne(pagoID);
   }
}
