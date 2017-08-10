package services;

import domain.Admin;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdminRepository;
import security.Autoridad;
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
   
   public void bloquear(Usuario u) {
      this.checkIfAdmin();
      if (u.getCuenta().getIsActivated()) {
         u.getCuenta().setIsActivated(false);
      } else {
         u.getCuenta().setIsActivated(true);
      }
      
   }
}
