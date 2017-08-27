package services;

import domain.*;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ProfesionalRepository;
import security.Autoridad;
import security.Cuenta;
import security.LoginService;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
      Profesional profesional = new Profesional();
      Collection<Trabajo> trabajos = new ArrayList<>();
      Collection<Estudio> estudios = new ArrayList<>();
      Collection<Oferta> ofertas = new ArrayList<>();
   
      profesional.setTrabajos(trabajos);
      profesional.setEstudios(estudios);
      profesional.setOfertas(ofertas);
   
      return profesional;
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
   
   public Profesional findProfesionalPorCuenta(Cuenta cuenta) {
      return profesionalRepository.findProfesional(cuenta);
   }
   
   public void modificarPerfil(UsuarioForm usuarioForm) {
      Profesional profesional = this.findProfesional();
      
      Collection<String> allUsernames = actorService.getAllUsernames();
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allUsernames.remove(profesional.getCuenta().getUsername());
      allEmails.remove(profesional.getEmail());
      allDNIs.remove(profesional.getDNI());
      
      Assert.isTrue(actorService.checkDni(usuarioForm.getDNI()));
      Assert.isTrue(actorService.checkPassword(usuarioForm));
      Assert.isTrue(! usuarioForm.getProvincia().equals("-----"));
      Assert.isTrue(! allUsernames.contains(usuarioForm.getUsername()));
      Assert.isTrue(! allDNIs.contains(usuarioForm.getDNI()));
      Assert.isTrue(! allEmails.contains(usuarioForm.getEmail()));
      
      profesional.setNombre(usuarioForm.getNombre());
      profesional.setApellidos(usuarioForm.getApellidos());
      profesional.setCp(usuarioForm.getCp());
      profesional.setTelefono(usuarioForm.getTelefono());
      profesional.setEmail(usuarioForm.getEmail());
      profesional.setFoto(usuarioForm.getFoto());
      profesional.setProvincia(usuarioForm.getProvincia());
      profesional.setDescripcion(usuarioForm.getDescripcion());
      
      profesional.getCuenta().setUsername(usuarioForm.getUsername());
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      String password = md5PassWordEncoder.encodePassword(usuarioForm.getPassword(), null);
      profesional.getCuenta().setPassword(password);
      this.save(profesional);
   }
   
}
