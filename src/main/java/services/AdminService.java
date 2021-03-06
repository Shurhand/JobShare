package services;

import domain.Actor;
import domain.Admin;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdminRepository;
import security.Autoridad;
import security.Cuenta;
import security.LoginService;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class AdminService extends AbstractServiceImpl implements AbstractService<Admin> {
   @Autowired
   private AdminRepository adminRepository;
   @Autowired
   private ActorService actorService;
   
   @Override
   public Admin create() {
      Admin admin = new Admin();
      
      return admin;
   }
   
   @Override
   public void save(@NotNull Admin admin) {
      adminRepository.save(admin);
   }
   
   @Override
   public void saveAll(Iterable<Admin> admins) {
      adminRepository.save(admins);
   }
   
   @Override
   public Admin saveWithReturn(@NotNull Admin admin) {
      return adminRepository.save(admin);
   }
   
   @Override
   public void delete(@NotNull Admin admin) {
      adminRepository.delete(admin);
   }
   
   @Override
   public void deleteAll(Iterable<Admin> admins) {
      adminRepository.delete(admins);
   }
   
   @Override
   public Collection<Admin> findAll() {
      return adminRepository.findAll();
   }
   
   @Override
   @NotNull
   public Admin findOne(@NotNull @Min(1) Integer adminID) {
      return adminRepository.findOne(adminID);
   }
   
   public void checkIfAdmin() {
      boolean admin = false;
      Collection<Autoridad> roles;
      roles = LoginService.getPrincipal().getAuthorities();
      for (Autoridad a : roles) {
         if (a.getAuthority().equals(Autoridad.ADMIN)) {
            admin = true;
         }
      }
      Assert.isTrue(admin, "No es un admin");
      
   }
   
   public void bloquear(Actor a) {
      this.checkIfAdmin();
      if (a.getCuenta().getIsActivated()) {
         a.getCuenta().setIsActivated(false);
      } else {
         a.getCuenta().setIsActivated(true);
      }
   }
   
   public Admin findAdmin() {
      Actor a = actorService.findPrincipal();
      return adminRepository.findAdmin(a.getCuenta());
   }
   
   public Admin findUsuarioPorCuenta(Cuenta cuenta) {
      return adminRepository.findAdmin(cuenta);
   }
   
   public void modificarPerfil(UsuarioForm usuarioForm) {
      Admin admin = this.findAdmin();
      
      Collection<String> allUsernames = actorService.getAllUsernames();
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allUsernames.remove(admin.getCuenta().getUsername());
      allEmails.remove(admin.getEmail());
      allDNIs.remove(admin.getDNI());
      
      Assert.isTrue(actorService.checkDni(usuarioForm.getDNI()));
      Assert.isTrue(actorService.checkPassword(usuarioForm));
      Assert.isTrue(! usuarioForm.getProvincia().equals("-----"));
      Assert.isTrue(! allUsernames.contains(usuarioForm.getUsername()));
      Assert.isTrue(! allDNIs.contains(usuarioForm.getDNI()));
      Assert.isTrue(! allEmails.contains(usuarioForm.getEmail()));
      
      admin.setNombre(usuarioForm.getNombre());
      admin.setApellidos(usuarioForm.getApellidos());
      admin.setCp(usuarioForm.getCp());
      admin.setTelefono(usuarioForm.getTelefono());
      admin.setEmail(usuarioForm.getEmail());
      admin.setFoto(usuarioForm.getFoto());
      admin.setProvincia(usuarioForm.getProvincia());
      admin.setDescripcion(usuarioForm.getDescripcion());
      
      admin.getCuenta().setUsername(usuarioForm.getUsername());
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      String password = md5PassWordEncoder.encodePassword(usuarioForm.getPassword(), null);
      admin.getCuenta().setPassword(password);
      this.save(admin);
   }
   
   
}
