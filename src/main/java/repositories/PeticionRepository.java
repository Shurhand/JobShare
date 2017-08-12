package repositories;

import domain.Peticion;
import domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Integer> {
   @Query("select p from Peticion p where p.usuario = ?1")
    Collection<Peticion> getPeticionesPorUsuario(Usuario u);
   
   @Query("select p from Peticion p where p.usuario = ?1 and p.estado = 'CADUCADA'")
   Collection<Peticion> getPeticionesCaducadasPorUsuario(Usuario u);
   
   @Query("select p from Peticion p where p.usuario = ?1 and p.estado = 'ACTIVA'")
   Collection<Peticion> getPeticionesActivasPorUsuario(Usuario u);
}