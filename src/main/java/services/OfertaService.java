package services;

import domain.Estado;
import domain.Oferta;
import domain.Peticion;
import domain.Profesional;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.OfertaRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class OfertaService extends AbstractServiceImpl implements AbstractService<Oferta> {
   @Autowired
   private OfertaRepository ofertaRepository;
   @Autowired
   private ProfesionalService profesionalService;
   @Autowired
   private PeticionService peticionService;
   @Autowired
   private ActorService actorService;
   
   @Override
   public Oferta create() {
      profesionalService.checkIfProfesional();
      Profesional profesional = profesionalService.findProfesional();
      LocalDate fechaCreacion = LocalDate.now();
      Estado estado = Estado.ACTIVA;
      Oferta oferta = new Oferta();
   
      oferta.setProfesional(profesional);
      oferta.setEstado(estado);
      oferta.setFechaCreacion(fechaCreacion);
      
      return oferta;
   }
   
   @Override
   public void save(@NotNull Oferta oferta) {
      ofertaRepository.save(oferta);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Oferta> ofertas) {
      profesionalService.checkIfProfesional();

      ofertaRepository.save(ofertas);
   }
   
   @Override
   public Oferta saveWithReturn(@NotNull Oferta oferta) {
      profesionalService.checkIfProfesional();

      return ofertaRepository.save(oferta);
   }
   
   @Override
   public void delete(@NotNull Oferta oferta) {
      profesionalService.checkIfProfesional();

      ofertaRepository.delete(oferta);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Oferta> ofertas) {
      profesionalService.checkIfProfesional();
      ofertaRepository.delete(ofertas);
   }
   
   @Override
   public Collection<Oferta> findAll() {
      return ofertaRepository.findAll();
   }
   
   @Override
   @NotNull
   public Oferta findOne(@NotNull @Min(1) Integer ofertaID) {
      return ofertaRepository.findOne(ofertaID);
   }
   
   public Collection<Oferta> getOfertasPorProfesional(Profesional profesional) {
      return ofertaRepository.getOfertasPorProfesional(profesional);
   }
   
   public Collection<Oferta> getOfertasContratadasPorProfesional(Profesional profesional) {
      return ofertaRepository.getOfertasContratadasPorProfesional(profesional);
   }
   
   public Collection<Oferta> getOfertasActivasPorProfesional(Profesional profesional) {
      return ofertaRepository.getOfertasActivasPorProfesional(profesional);
   }
   
   public Set<Peticion> getPeticionesPorOfertasPorProfesional(Profesional profesional) {
      return ofertaRepository.getPeticionesPorOfertasPorProfesional(profesional);
   }
   
   public Set<Peticion> getPeticionesPorOfertasContratadasPorProfesional() {
      
      Set<Peticion> res = new HashSet<>();
      for (Oferta o : findAll()) {
         if (o.getProfesional().equals(profesionalService.findProfesional()) && o.getEstado().equals(Estado.CONTRATADA)) {
            res.add(o.getItem().getPeticion());
         }
      }
      return res;
   }
   
   public Set<Peticion> getPeticionesPorOfertasActivasPorProfesional() {
      Set<Peticion> res = new HashSet<>();
      for (Oferta o : findAll()) {
         if (o.getProfesional().equals(profesionalService.findProfesional()) && o.getEstado().equals(Estado.ACTIVA)) {
            res.add(o.getItem().getPeticion());
         }
      }
      return res;
   }
   
   public Collection<Peticion> getMisOfertasActivas(BuscaForm buscaForm) {
      Collection<Peticion> res = peticionService.resetPeticiones(buscaForm);
      
      Comparator<Peticion> peticionComparator = peticionService.setComparators(buscaForm);
      
      SortedSet<Peticion> peticionesOrdenadas = new TreeSet<>(peticionComparator);
      peticionesOrdenadas.addAll(res);
      peticionesOrdenadas.retainAll(getPeticionesPorOfertasActivasPorProfesional());
      
      return peticionesOrdenadas;
   }
   
   public Collection<Peticion> getMisOfertasContratadas(BuscaForm buscaForm) {
      Collection<Peticion> res = peticionService.resetPeticiones(buscaForm);
      
      Comparator<Peticion> peticionComparator = peticionService.setComparators(buscaForm);
      
      SortedSet<Peticion> peticionesOrdenadas = new TreeSet<>(peticionComparator);
      peticionesOrdenadas.addAll(res);
      peticionesOrdenadas.retainAll(getPeticionesPorOfertasContratadasPorProfesional());
      
      return peticionesOrdenadas;
   }
}
