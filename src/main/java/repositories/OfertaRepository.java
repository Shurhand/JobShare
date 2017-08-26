package repositories;

import domain.Oferta;
import domain.Peticion;
import domain.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
   @Query("select o from Oferta o where o.profesional = ?1")
   Collection<Oferta> getOfertasPorProfesional(Profesional profesional);
   
   @Query("select o from Oferta o where o.profesional = ?1 and o.estado = 'CONTRATADA'")
   Collection<Oferta> getOfertasContratadasPorProfesional(Profesional profesional);
   
   @Query("select o from Oferta o where o.profesional = ?1 and o.estado = 'ACTIVA'")
   Collection<Oferta> getOfertasActivasPorProfesional(Profesional profesional);
   
   @Query("select o.item.peticion from Oferta o where o.profesional = ?1")
   Set<Peticion> getPeticionesPorOfertasPorProfesional(Profesional profesional);
   
   @Query("select o.item.peticion from Oferta o where o.profesional = ?1 and o.estado = 'CONTRATADA'")
   Set<Peticion> getPeticionesPorOfertasContratadasPorProfesional(Profesional profesional);
   
   @Query("select o.item.peticion from Oferta o where o.profesional = ?1 and o.estado = 'ACTIVA'")
   Set<Peticion> getPeticionesPorOfertasActivasPorProfesional(Profesional profesional);
}