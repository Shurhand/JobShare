package services;

import domain.Pago;
import domain.Peticion;
import domain.Usuario;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.UsuarioRepository;
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
public class UsuarioService extends AbstractServiceImpl implements AbstractService<Usuario> {
   @Autowired
   private UsuarioRepository usuarioRepository;
   
   @Override
   public Usuario create() {
      Collection<Peticion> peticiones = new ArrayList<>();
      Collection<Pago> pagos = new ArrayList<>();
      Usuario usuario = new Usuario();
      
      usuario.setPagos(pagos);
      usuario.setPeticiones(peticiones);
      
      return usuario;
   }
   
   @Override
   public void save(@NotNull Usuario usuario) {
      usuarioRepository.save(usuario);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Usuario> usuarios) {
      usuarioRepository.save(usuarios);
   }
   
   @Override
   public Usuario saveWithReturn(@NotNull Usuario usuario) {
      return usuarioRepository.save(usuario);
   }
   
   @Override
   public void delete(@NotNull Usuario usuario) {
      usuarioRepository.delete(usuario);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Usuario> usuarios) {
      usuarioRepository.delete(usuarios);
   }
   
   @Override
   public Collection<Usuario> findAll() {
      return usuarioRepository.findAll();
   }
   
   @Override
   @NotNull
   public Usuario findOne(@NotNull @Min(1) Integer usuarioID) {
      return usuarioRepository.findOne(usuarioID);
   }
   
   public void checkIfUsuario() {
      boolean usuario = false;
      Collection<Autoridad> roles;
      roles = LoginService.getPrincipal().getAuthorities();
      for (Autoridad a : roles) {
         if (a.getAuthority().equals(Autoridad.USUARIO)) {
            usuario = true;
         }
      }
      Assert.isTrue(usuario, "No es un usuario");
      
   }
   
   public void registrarUsuario(UsuarioForm usuarioForm) {
      Usuario usuario = this.create();
      Cuenta cuenta = new Cuenta();
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      
      usuario.setNombre(usuarioForm.getNombre());
      usuario.setApellidos(usuarioForm.getApellidos());
      usuario.setCp(usuarioForm.getCp());
      usuario.setDNI(usuarioForm.getDNI());
      usuario.setTelefono(usuarioForm.getTelefono());
      usuario.setEmail(usuarioForm.getEmail());
      usuario.setFoto(usuarioForm.getFoto());
      usuario.setProvincia(usuarioForm.getProvincia());
      
      cuenta.setIsActivated(true);
      cuenta.setUsername(usuarioForm.getUsername());
      String password = md5PassWordEncoder.encodePassword(usuarioForm.getPassword(), null);
      cuenta.setPassword(password);
      
      Collection<Autoridad> auths = new ArrayList<>();
      Autoridad auth = new Autoridad();
      auth.setAuthority("USUARIO");
      auths.add(auth);
      
      cuenta.setAuthorities(auths);
      
      usuario.setCuenta(cuenta);
      
      this.save(usuario);
      
      
   }
}
