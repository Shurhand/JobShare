package repositories;

import domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

//   @Query("select i.ofertas from Item i  where i = ?1")
//   Collection<Oferta> getOfertasBy
}