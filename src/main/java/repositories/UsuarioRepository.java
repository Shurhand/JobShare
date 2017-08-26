package repositories;

import domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import security.Cuenta;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   @Query("select u from Usuario u where u.cuenta = ?1")
   Usuario findUsuario(Cuenta cuenta);
   
   @Query("select u from Usuario u where u.cuenta.username = ?1")
   Usuario findUsuarioDeGoogle(String usuario);
}