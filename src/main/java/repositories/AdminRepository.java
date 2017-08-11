package repositories;

import domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select a from Admin a where a.cuenta = ?1")
   Admin findAdmin(Cuenta cuenta);
}