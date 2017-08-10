package services;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ActorRepository;
import security.Cuenta;
import security.LoginService;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class ActorService extends AbstractServiceImpl implements AbstractService<Actor> {
   // Dependencias
   @Autowired
   private ActorRepository actorRepository;
   
   @Override
   public Actor create() {
      return null;
   }
   
   @Override
   public void save(@NotNull Actor actor) {
      actorRepository.save(actor);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Actor> list) {
      actorRepository.save(list);
   }
   
   @Override
   public Actor saveWithReturn(@NotNull Actor actor) {
      return actorRepository.save(actor);
   }
   
   @Override
   public void delete(@NotNull Actor actor) {
      actorRepository.delete(actor);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Actor> list) {
      actorRepository.delete(list);
   }
   
   @Override
   public Collection<Actor> findAll() {
      return actorRepository.findAll();
   }
   
   @Override
   @NotNull
   public Actor findOne(@NotNull @Min(1) Integer id) {
      return actorRepository.findOne(id);
   }
   
   @NotNull
   public Actor findPrincipal() {
      Actor result;
      Cuenta cuenta;
      
      cuenta = LoginService.getPrincipal();
      result = actorRepository.findActor(cuenta);
      
      return result;
   }
   
   private Boolean checkDniLetra(String dni) {
      // Precondicion: se ha comprobado previamente que dni tiene 8 digitos y
      // una letra
      String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
      Integer numeroDni = new Integer(dni.substring(0, 8));
      return dni.charAt(8) == letras.charAt(numeroDni % 23);
   }
   
   public void enable(Actor a) {
      a.getCuenta().setIsActivated(true);
      actorRepository.save(a);
   }
   
   public void disable(Actor a) {
      a.getCuenta().setIsActivated(false);
      actorRepository.save(a);
   }
   
  public void checkIfUsuarioOProfesional() {
      boolean usuarioOProfesional = false;
      Collection<Autoridad> roles;
      roles = LoginService.getPrincipal().getAuthorities();
      for (Autoridad a : roles) {
         if (a.getAuthority().equals(Autoridad.USUARIO) || a.getAuthority().equals(Autoridad.PROFESIONAL)) {
            usuarioOProfesional = true;
         }
      }
      Assert.isTrue(usuario, "No es un usuario o profesional");
      
   }
   
    public void checkIfAutenticado() {
      boolean autenticado = false;
      autenticado = SecurityContextHolder.getContext().getAuthentication();
      Assert.isTrue(autenticado, "No está autenticado");
      
   }
}
