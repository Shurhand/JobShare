package services;

import domain.Actor;
import domain.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ProfesionalRepository;
import security.Autoridad;
import security.LoginService;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class ProfesionalService extends AbstractServiceImpl implements AbstractService<Profesional> {
   @Autowired
   private ProfesionalRepository profesionalRepository;
   @Autowired
   private ActorService actorService;
   
   @Override
   public Profesional create() {
      return null;
   }
   
   @Override
   public void save(@NotNull Profesional profesional) {
      profesionalRepository.save(profesional);
   }
   
   @Override
   public void saveAll(Iterable<Profesional> profesionales) {
      profesionalRepository.save(profesionales);
   }
   
   @Override
   public Profesional saveWithReturn(@NotNull Profesional profesional) {
      return profesionalRepository.save(profesional);
   }
   
   @Override
   public void delete(@NotNull Profesional profesional) {
      profesionalRepository.delete(profesional);
   }
   
   @Override
   public void deleteAll(Iterable<Profesional> profesionales) {
      profesionalRepository.delete(profesionales);
   }
   
   @Override
   public Collection<Profesional> findAll() {
      return profesionalRepository.findAll();
   }
   
   @Override
   @NotNull
   public Profesional findOne(@NotNull @Min(1) Integer profesionalID) {
      return profesionalRepository.findOne(profesionalID);
   }
   
   public void checkIfProfesional() {
      boolean profesional = false;
      Collection<Autoridad> roles;
      roles = LoginService.getPrincipal().getAuthorities();
      for (Autoridad a : roles) {
         if (a.getAuthority().equals(Autoridad.PROFESIONAL)) {
            profesional = true;
         }
      }
      Assert.isTrue(profesional, "No es un profesional");
      
   }
   
   public Profesional findProfesional() {
      Actor a = actorService.findPrincipal();
      return profesionalRepository.findProfesional(a.getCuenta());
   }
   
    public void modificarPerfil(UsuarioForm usuarioForm){
      Profesional profesional = this.findProfesional();
      
      profesional.setNombre(usuarioForm.getNombre());
      profesional.setApellidos(usuarioForm.getApellidos());
      profesional.setCp(usuarioForm.getCp());
      profesional.setTelefono(usuarioForm.getTelefono());
      profesional.setEmail(usuarioForm.getEmail());
      profesional.setFoto(usuarioForm.getFoto());
      profesional.setProvincia(usuarioForm.getProvincia());
       
       profesional.getCuenta().setUsername(usuarioForm.getUsername());
       Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
       String password = md5PassWordEncoder.encodePassword(profesional.getPassword(), null);
       profesional.getCuenta().setPassword(password);
       this.save(profesional);
   }
}
