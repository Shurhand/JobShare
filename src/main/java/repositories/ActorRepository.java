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
   
   @Query("select a from Actor a where a = ?1")
   Actor getActorByID(Integer a);
   
}