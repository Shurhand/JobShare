package services;

import domain.Estado;
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
public class PeticionService extends AbstractServiceImpl implements AbstractService<Peticion> {
   @Autowired
   private PeticionRepository peticionRepository;
    @Autowired
   private ActorService actorService;
    @Autowired
   private UsuarioService usuarioService;
   
   @Override
   public Peticion create() {
      actorService.checkIfUsuarioOProfesional();
      Collection<Item> items = new ArrayList<>();
      LocalDate fechaCreacion = LocalDate.now();
      Estado estado = Estado.ACTIVA;
      Peticion peticion = new Peticion();
      
      peticion.setFechaCreacion(fechaCreacion);
      peticion.setItems(items);
      peticion.setEstado(estado);
      
      return peticion;
   }
   
   @Override
   public void save(@NotNull Peticion peticion) {
      actorService.checkIfUsuarioOProfesional();
      peticionRepository.save(peticion);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Peticion> peticiones) {
      actorService.checkIfUsuarioOProfesional();
      peticionRepository.save(peticiones);
   }
   
   @Override
   public Peticion saveWithReturn(@NotNull Peticion peticion) {
      actorService.checkIfUsuarioOProfesional();
      return peticionRepository.save(peticion);
   }
   
   @Override
   public void delete(@NotNull Peticion peticion) {
      actorService.checkIfUsuarioOProfesional();
      peticionRepository.delete(peticion);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Peticion> peticiones) {
      actorService.checkIfUsuarioOProfesional();
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
   
   public Collection<Peticion> getMisPeticiones(Usuario usuario){
       return peticionRepository.getMisPeticiones(usuario)
   }
}
