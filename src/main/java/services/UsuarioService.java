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
import java.util.Arrays;
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
   
      Assert.isTrue(actorService.checkPassword(usuarioForm), "usuario.coincidenciaPasswords");
      
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
   
   public void modificarPerfil(UsuarioForm usuarioForm) {
      Usuario usuario = this.findUsuario();
      
      Collection<String> allUsernames = actorService.getAllUsernames();
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allUsernames.remove(usuario.getCuenta().getUsername());
      allEmails.remove(usuario.getEmail());
      allDNIs.remove(usuario.getDNI());
      
      Assert.isTrue(actorService.checkDni(usuarioForm.getDNI()));
      Assert.isTrue(actorService.checkPassword(usuarioForm));
      Assert.isTrue(! usuarioForm.getProvincia().equals("-----"));
      Assert.isTrue(! allUsernames.contains(usuarioForm.getUsername()));
      Assert.isTrue(! allDNIs.contains(usuarioForm.getDNI()));
      Assert.isTrue(! allEmails.contains(usuarioForm.getEmail()));
      
      usuario.setNombre(usuarioForm.getNombre());
      usuario.setApellidos(usuarioForm.getApellidos());
      usuario.setCp(usuarioForm.getCp());
      usuario.setTelefono(usuarioForm.getTelefono());
      usuario.setEmail(usuarioForm.getEmail());
      usuario.setFoto(usuarioForm.getFoto());
      usuario.setProvincia(usuarioForm.getProvincia());
      
      usuario.getCuenta().setUsername(usuarioForm.getUsername());
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      String password = md5PassWordEncoder.encodePassword(usuarioForm.getPassword(), null);
      usuario.getCuenta().setPassword(password);
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
   
   public Collection<String> getListaProvincias() {
      return Arrays.asList("Alava", "Albacete", "Alicante", "Almería", "Asturias", "Avila", "Badajoz", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "La Coruña", "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "León", "Lérida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Palencia", "Las Palmas", "Pontevedra", "La Rioja", "Salamanca", "Segovia", "Sevilla", "Soria", "Tarragona", "Santa Cruz de Tenerife", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza");

   }
   
   
}

