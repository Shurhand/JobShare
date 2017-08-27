package services;

import domain.Actor;
import forms.GoogleForm;
import forms.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import repositories.ActorRepository;
import security.Autoridad;
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
   
   public boolean isAnonimo() {
      boolean esAnonimo = false;
      for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
         if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {
            esAnonimo = true;
         }
      }
      return esAnonimo;
   }
   
   public boolean isUsuario() {
      boolean esUsuario = false;
      for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
         
         if (ga.getAuthority().equals("USUARIO")) {
            esUsuario = true;
         }
      }
      return esUsuario;
   }
   
   public boolean isProfesional() {
      boolean esProfesional = false;
      for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
         
         if (ga.getAuthority().equals("PROFESIONAL")) {
            esProfesional = true;
         }
      }
      return esProfesional;
   }
   
   public boolean isAdmin() {
      boolean esAdmin = false;
      for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
         System.out.println(ga.getAuthority());
         if (ga.getAuthority().equals("PROFESIONAL")) {
            esAdmin = true;
         }
      }
      return esAdmin;
   }
   
   public Boolean checkDni(String dni) {
      return checkDniCaracteres(dni) && checkDniLetra(dni);
   }
   
   private Boolean checkDniCaracteres(String dni) {
      return dni.length() == 9 && Character.isDigit(dni.charAt(0)) && Character.isDigit(dni.charAt(1)) && Character.isDigit(dni.charAt(2)) && Character.isDigit(dni.charAt(3)) && Character.isDigit(dni.charAt(4)) && Character.isDigit(dni.charAt(5)) && Character.isDigit(dni.charAt(6)) && Character.isDigit(dni.charAt(7)) && Character.isLetter(dni.charAt(8));
   }
   
   private Boolean checkDniLetra(String dni) {
      String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
      Integer numeroDni = new Integer(dni.substring(0, 8));
      return dni.charAt(8) == letras.charAt(numeroDni % 23);
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
      Assert.isTrue(usuarioOProfesional, "No es un usuario o profesional");
      
   }
   
   public void checkIfAutenticado() {
      boolean autenticado;
      autenticado = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
      Assert.isTrue(autenticado, "No está autenticado");
   }
   
   public Actor findActorPorUsername(String username) {
      return actorRepository.findActorPorUsername(username);
   }
   
   public Actor findActorPorEmail(String email) {
      return actorRepository.findActorPorEmail(email);
   }
   
   public Actor findActorPorDNI(String DNI) {
      return actorRepository.findActorPorDNI(DNI);
   }
   
   public Boolean checkPassword(UsuarioForm usuarioForm) {
      return usuarioForm.getPassword().equals(usuarioForm.getConfirmarPassword());
   }
   
   public UsuarioForm convertirActor(Actor a) {
      UsuarioForm usuarioForm = new UsuarioForm();
      usuarioForm.setNombre(a.getNombre());
      usuarioForm.setApellidos(a.getApellidos());
      usuarioForm.setDNI(a.getDNI());
      usuarioForm.setCp(a.getCp());
      usuarioForm.setTelefono(a.getTelefono());
      usuarioForm.setEmail(a.getEmail());
      usuarioForm.setProvincia(a.getProvincia());
      usuarioForm.setFoto(a.getFoto());
      usuarioForm.setUsername(a.getCuenta().getUsername());
      usuarioForm.setCheckTerminos(true);
      usuarioForm.setDescripcion(a.getDescripcion());
      
      return usuarioForm;
   }
   
   public GoogleForm convertirActorGoogle(Actor a) {
      GoogleForm googleForm = new GoogleForm();
      googleForm.setDNI(a.getDNI());
      googleForm.setCp(a.getCp());
      googleForm.setTelefono(a.getTelefono());
      googleForm.setEmail(a.getEmail());
      googleForm.setProvincia(a.getProvincia());
      googleForm.setSubject(a.getCuenta().getUsername());
      googleForm.setGivenName(a.getNombre());
      googleForm.setFamilyName(a.getApellidos());
      googleForm.setPictureUrl(a.getFoto());
      googleForm.setDescripcion(a.getDescripcion());
      
      return googleForm;
   }
   
   public Collection<String> getAllUsernames() {
      return actorRepository.getAllUsernames();
   }
   
   public Collection<String> getAllDNIs() {
      return actorRepository.getAllDNIs();
   }
   
   public Collection<String> getAllEmails() {
      return actorRepository.getAllEmails();
   }
   
   public void addNombre(ModelAndView vista) {
      if (! isAnonimo()) {
         Actor actorAutenticado = findPrincipal();
         vista.addObject("actorAutenticado", actorAutenticado);
      }
   }
}
