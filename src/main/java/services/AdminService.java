package services;

import domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdminRepository;
import security.LoginService;
import security.Rol;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
@Transactional
public class AdminService implements AbstractService<Admin> {
   
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
      Collection<Rol> roles;
      roles = LoginService.getPrincipal().getAuthorities();
      for (Rol a : roles) {
         if (a.getAuthority().equals(Rol.ADMIN)) {
            admin = true;
         }
      }
      Assert.isTrue(admin, "No es un admin");
      
   }
}
