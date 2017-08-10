package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import security.Cuenta;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
   @Query("select a from Actor a where a.cuenta = ?1")
   Actor findActor(Cuenta cuenta);
   
   @Query("select a from Actor a where a.cuenta.username = ?1")
   Actor findActorPorUsername(String username);
   
   @Query("select a from Actor a where a.DNI = ?1")
   Actor findActorPorDNI(String dni);
   
   @Query("select a from Actor a where a.email = ?1")
   Actor findActorPorEmail(String email);
 
   
}