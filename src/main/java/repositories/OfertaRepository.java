package repositories;

import domain.Oferta;
import domain.Peticion;
import domain.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
   @Query("select o from Oferta o where o.profesional = ?1")
   Collection<Oferta> getOfertasPorProfesional(Profesional u);
   
   @Query("select o from Oferta o where o.profesional = ?1 and o.estado = 'CONTRATADA'")
   Collection<Oferta> getOfertasContratadasPorProfesional(Profesional u);
   
   @Query("select o from Oferta o where o.profesional = ?1 and o.estado = 'ACTIVA'")
   Collection<Oferta> getOfertasActivasPorProfesional(Profesional u);
   
   @Query("select o.item.peticion from Oferta o where o.profesional = ?1")
   Collection<Peticion> getPeticionesPorOfertasPorProfesional(Profesional u);
   
   @Query("select o.item.peticion from Oferta o where o.profesional = ?1 and o.estado = 'CONTRATADA'")
   Collection<Peticion> getPeticionesPorOfertasContratadasPorProfesional(Profesional u);
   
   @Query("select o.item.peticion from Oferta o where o.profesional = ?1 and o.estado = 'ACTIVA'")
   Collection<Peticion> getPeticionesPorOfertasActivasPorProfesional(Profesional u);
}