package repositories;

import domain.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {
   @Query("select e from Etiqueta e where e.activada = true")
   Collection<Etiqueta> getEtiquetasActivas();
   
   @Query("select e.nombre from Etiqueta e")
   Collection<String> getNombreEtiquetas();
}