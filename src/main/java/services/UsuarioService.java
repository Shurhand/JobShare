package services;

import domain.Actor;
import domain.Pago;
import domain.Peticion;
import domain.Usuario;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
   @Autowired
   private ActorService actorService;
   @Autowired
   private LoginService loginService;
   
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
   
      Assert.isTrue(usuarioForm.getPassword().equals(usuarioForm.getConfirmarPassword()), "usuario.coincidenciaPasswords");
      
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
   
   public Usuario findUsuario() {
      Actor a = actorService.findPrincipal();
      return usuarioRepository.findUsuario(a.getCuenta());
   }
   
   public void convertirse() {
      this.checkIfUsuario();
      Collection<Autoridad> res = new ArrayList<>();
      Autoridad autoridad = new Autoridad();
      autoridad.setAuthority("PROFESIONAL");
      res.add(autoridad);
      
      Actor actor = actorService.findPrincipal();
      Cuenta miCuenta = actor.getCuenta();
      miCuenta.getAuthorities().clear();
      miCuenta.setAuthorities(res);
      
      logOFF();
      logON();
      
   }
   
   private void logON() {
      Actor a = actorService.findPrincipal();
      Cuenta cuenta = (Cuenta) loginService.loadUserByUsername(a.getCuenta().getUsername());
      Authentication authentication = new UsernamePasswordAuthenticationToken(cuenta, null, cuenta.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
   }
   
   private void logOFF() {
      SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
   }
   
   public List<String> getListaProvincias(){
       Collection<String> provincias = new ArrayList<>();
       return provincias;
   }
  
}

