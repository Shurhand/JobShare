package services;

import domain.Pago;
import domain.Peticion;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.UsuarioRepository;
import security.LoginService;
import security.Rol;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UsuarioService implements AbstractService<Usuario> {
   
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
      Collection<Rol> roles;
      roles = LoginService.getPrincipal().getAuthorities();
      for (Rol a : roles) {
         if (a.getAuthority().equals(Rol.USUARIO)) {
            usuario = true;
         }
      }
      Assert.isTrue(usuario, "No es un usuario");
      
   }
}
