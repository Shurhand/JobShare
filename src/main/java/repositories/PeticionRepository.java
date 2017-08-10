package repositories;

import domain.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Integer> {
    
    @Query("select p from Peticiones p where p.usuario = ?1")
    Collection<Peticion> getPeticionesPorUsuario(Usuario u);
}