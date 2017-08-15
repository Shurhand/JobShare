package repositories;

import domain.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import security.Cuenta;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
   @Query("select u from Profesional u where u.cuenta = ?1")
   Profesional findProfesional(Cuenta cuenta);
   
}