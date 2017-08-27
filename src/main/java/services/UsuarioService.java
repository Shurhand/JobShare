package services;

import domain.*;
import forms.GoogleForm;
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
   @Autowired
   private ProfesionalService profesionalService;
   
   @Override
   public Usuario create() {
      Collection<Peticion> peticiones = new ArrayList<>();
      Collection<Valoracion> valoraciones = new ArrayList<>();
      Collection<Pago> pagos = new ArrayList<>();
      Usuario usuario = new Usuario();
      
      usuario.setPagos(pagos);
      usuario.setValoraciones(valoraciones);
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
      usuario.setDescripcion(usuarioForm.getDescripcion());
      
      cuenta.setIsActivated(true);
      cuenta.setIsGoogle(false);
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
      usuario.setDescripcion(usuarioForm.getDescripcion());
      
      usuario.getCuenta().setUsername(usuarioForm.getUsername());
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      String password = md5PassWordEncoder.encodePassword(usuarioForm.getPassword(), null);
      usuario.getCuenta().setPassword(password);
      this.save(usuario);
   }
   
   public void modificarPerfilGoogle(GoogleForm googleForm) {
      Usuario usuario = this.findUsuario();
      
      Collection<String> allEmails = actorService.getAllEmails();
      Collection<String> allDNIs = actorService.getAllDNIs();
      
      allEmails.remove(usuario.getEmail());
      allDNIs.remove(usuario.getDNI());
      
      Assert.isTrue(actorService.checkDni(googleForm.getDNI()));
      Assert.isTrue(! googleForm.getProvincia().equals("-----"));
      Assert.isTrue(! allDNIs.contains(googleForm.getDNI()));
      Assert.isTrue(! allEmails.contains(googleForm.getEmail()));
      
      usuario.setCp(googleForm.getCp());
      usuario.setTelefono(googleForm.getTelefono());
      usuario.setEmail(googleForm.getEmail());
      usuario.setProvincia(googleForm.getProvincia());
      usuario.setDescripcion(googleForm.getDescripcion());
      
      this.save(usuario);
   }
   
   
   
   public Usuario findUsuario() {
      Actor a = actorService.findPrincipal();
      return usuarioRepository.findUsuario(a.getCuenta());
   }
   
   public Usuario findUsuarioPorCuenta(Cuenta cuenta) {
      return usuarioRepository.findUsuario(cuenta);
   }
   
   public void convertirse(Usuario usuario) {
      this.checkIfUsuario();
      Profesional profesional = profesionalService.create();
      Cuenta cuenta = new Cuenta();
      Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
      
      profesional.setNombre(usuario.getNombre());
      profesional.setApellidos(usuario.getApellidos());
      profesional.setDescripcion(usuario.getDescripcion());
      profesional.setPagos(usuario.getPagos());
      profesional.setPeticiones(usuario.getPeticiones());
      profesional.setValoraciones(usuario.getValoraciones());
      profesional.setCp(usuario.getCp());
      profesional.setDNI(usuario.getDNI());
      profesional.setEmail(usuario.getEmail());
      profesional.setFoto(usuario.getFoto());
      profesional.setId(usuario.getId());
      profesional.setProvincia(usuario.getProvincia());
      profesional.setTelefono(usuario.getTelefono());
      profesional.setVersion(usuario.getVersion());
      
      cuenta.setIsActivated(true);
      cuenta.setIsGoogle(true);
      cuenta.setUsername(usuario.getCuenta().getUsername());
      String password = md5PassWordEncoder.encodePassword(usuario.getCuenta().getPassword(), null);
      cuenta.setPassword(password);
      
      Collection<Autoridad> res = new ArrayList<>();
      Autoridad autoridad = new Autoridad();
      autoridad.setAuthority("PROFESIONAL");
      res.add(autoridad);
      
      profesional.setCuenta(cuenta);
      profesional.getCuenta().setAuthorities(res);
   
      logOFF();
//      this.delete(usuario);
      profesionalService.save(profesional);
      logUsuario(profesional);
      
   }
   
   private void logON() {
      Actor a = actorService.findPrincipal();
      Cuenta cuenta = (Cuenta) loginService.loadUserByUsername(a.getCuenta().getUsername());
      Authentication authentication = new UsernamePasswordAuthenticationToken(cuenta, null, cuenta.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
   }
   
   public void logOFF() {
      SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
   }
   
   public void logUsuario(Usuario u) {
      Cuenta cuenta = (Cuenta) loginService.loadUserByUsername(u.getCuenta().getUsername());
      Authentication authentication = new UsernamePasswordAuthenticationToken(cuenta, null, cuenta.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
   }
   
   public Collection<String> getListaProvincias() {
      return Arrays.asList("Alava", "Albacete", "Alicante", "Almería", "Asturias", "Avila", "Badajoz", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "La Coruña", "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "León", "Lérida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Palencia", "Las Palmas", "Pontevedra", "La Rioja", "Salamanca", "Segovia", "Sevilla", "Soria", "Tarragona", "Santa Cruz de Tenerife", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza");

   }
   
   public Usuario findUsuarioDeGoogle(String usuario) {
      return usuarioRepository.findUsuarioDeGoogle(usuario);
   }
   
   public Usuario registrarUsuarioGoogle(GoogleForm googleForm) {
   
      String idTokenString = googleForm.getIdTokenString();
      Usuario usuario = null;
      String userId = googleForm.getSubject();
      String email = googleForm.getEmail();
      boolean emailVerified = googleForm.isEmailVerified();
      String pictureUrl = googleForm.getPictureUrl();
      String familyName = googleForm.getFamilyName();
      String givenName = googleForm.getGivenName();
      
      if (emailVerified) {
         
         usuario = this.create();
         Cuenta cuenta = new Cuenta();
         Md5PasswordEncoder md5PassWordEncoder = new Md5PasswordEncoder();
   
         usuario.setNombre(givenName);
         usuario.setApellidos(familyName);
         usuario.setEmail(email);
         usuario.setFoto(pictureUrl);
         usuario.setDNI(googleForm.getDNI());
         usuario.setProvincia(googleForm.getProvincia());
         usuario.setCp(googleForm.getCp());
         usuario.setDescripcion(googleForm.getDescripcion());
         
         cuenta.setIsActivated(true);
         cuenta.setIsGoogle(true);
         cuenta.setUsername(userId);
         String password = md5PassWordEncoder.encodePassword(idTokenString, null);
         cuenta.setPassword(password);
         
         Collection<Autoridad> auths = new ArrayList<>();
         Autoridad auth = new Autoridad();
         auth.setAuthority("USUARIO");
         auths.add(auth);
         
         cuenta.setAuthorities(auths);
         
         usuario.setCuenta(cuenta);
         
         usuario = this.saveWithReturn(usuario);
         
      }
      return usuario;
   }
   
   
}

