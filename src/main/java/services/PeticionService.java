package services;

import com.google.common.base.Strings;
import domain.*;
import forms.BuscaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PeticionRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PeticionService extends AbstractServiceImpl implements AbstractService<Peticion> {
   @Autowired
   private PeticionRepository peticionRepository;
   @Autowired
   private ActorService actorService;
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private EtiquetaService etiquetaService;
   
   @Override
   public Peticion create() {
      actorService.checkIfUsuarioOProfesional();
      Usuario usuario = usuarioService.findUsuario();
      Collection<Item> items = new ArrayList<>();
      Set<Etiqueta> etiquetas = new HashSet<>();
      LocalDate fechaCreacion = LocalDate.now();
      Estado estado = Estado.ACTIVA;
      Peticion peticion = new Peticion();
      
      peticion.setFechaCreacion(fechaCreacion);
      peticion.setUsuario(usuario);
      peticion.setItems(items);
      peticion.setEstado(estado);
      peticion.setEtiquetas(etiquetas);
      
      return peticion;
   }
   
   @Override
   public void save(@NotNull Peticion peticion) {
      actorService.checkIfUsuarioOProfesional();
      Assert.isTrue(checkFechaCaducidad(peticion.getFechaCaducidad()));
      peticion.getEtiquetas().forEach(x -> x.getPeticiones().add(peticion));
      peticionRepository.save(peticion);
   }
   
   @Override
   public void saveAll(@NotNull Iterable<Peticion> peticiones) {
      actorService.checkIfUsuarioOProfesional();
      peticionRepository.save(peticiones);
   }
   
   @Override
   public Peticion saveWithReturn(@NotNull Peticion peticion) {
      actorService.checkIfUsuarioOProfesional();
      return peticionRepository.save(peticion);
   }
   
   @Override
   public void delete(@NotNull Peticion peticion) {
      actorService.checkIfUsuarioOProfesional();
      Assert.isTrue(usuarioService.findUsuario().equals(peticion.getUsuario()));
      for (Etiqueta e : peticion.getEtiquetas()) {
         e.getPeticiones().remove(peticion);
      }
      peticionRepository.delete(peticion);
   }
   
   @Override
   public void deleteAll(@NotNull Iterable<Peticion> peticiones) {
      actorService.checkIfUsuarioOProfesional();
      peticionRepository.delete(peticiones);
   }
   
   @Override
   public Collection<Peticion> findAll() {
      return peticionRepository.findAll();
   }
   
   @Override
   @NotNull
   public Peticion findOne(@NotNull @Min(1) Integer peticionID) {
      return peticionRepository.findOne(peticionID);
   }
   
   public Collection<Peticion> getPeticionesPorUsuario(Usuario usuario) {
      return peticionRepository.getPeticionesPorUsuario(usuario);
   }
   
   public Collection<Peticion> getPeticionesCaducadasPorUsuario(Usuario usuario) {
      return getPeticionesPorUsuario(usuario).stream().filter(x -> x.getEstado().equals(Estado.CADUCADA)).collect(Collectors.toList());
      
   }
   
   public Collection<Peticion> getPeticionesActivasPorUsuario(Usuario usuario) {
      return peticionRepository.getPeticionesActivasPorUsuario(usuario);
   }
   
   public boolean checkFechaCaducidad(LocalDate fechaCaducidad) {
      return fechaCaducidad.isAfter(LocalDate.now());
   }
   
   public void checkMisPeticiones(Peticion peticion) {
      Assert.isTrue(usuarioService.findUsuario().getPeticiones().contains(peticion));
   }
   
   public Collection<Peticion> getPeticionesBuscadas(BuscaForm buscaForm) {
   
      Collection<Peticion> res = resetPeticiones(buscaForm);
   
      Comparator<Peticion> peticionComparator = this.setComparators(buscaForm);
      
      SortedSet<Peticion> peticionesOrdenadas = new TreeSet<>(peticionComparator);
      peticionesOrdenadas.addAll(res);
      peticionesOrdenadas.removeIf(x -> x.getItems().isEmpty());
      
      return peticionesOrdenadas;
   }
   
   public Collection<Peticion> getMisPeticionesBuscadas(BuscaForm buscaForm) {
   
      Usuario usuario = usuarioService.findUsuario();
      Collection<Peticion> res = resetPeticiones(buscaForm);
   
      Comparator<Peticion> peticionComparator = this.setComparators(buscaForm);
   
      SortedSet<Peticion> peticionesOrdenadas = new TreeSet<>(peticionComparator);
   
      peticionesOrdenadas.addAll(res);
      peticionesOrdenadas.removeIf(x -> ! x.getUsuario().equals(usuario));
      peticionesOrdenadas.removeIf(x -> x.getFechaCaducidad().isBefore(LocalDate.now()));
   
      return peticionesOrdenadas;
   }
   
   public Collection<Peticion> getMisPeticionesBuscadasCaducadas(BuscaForm buscaForm) {
      Usuario usuario = usuarioService.findUsuario();
      Collection<Peticion> res = resetPeticiones(buscaForm);
      
      Comparator<Peticion> peticionComparator = this.setComparators(buscaForm);
      
      SortedSet<Peticion> peticionesOrdenadas = new TreeSet<>(peticionComparator);
      peticionesOrdenadas.addAll(res);
      peticionesOrdenadas.removeIf(x -> ! x.getUsuario().equals(usuario));
      peticionesOrdenadas.removeIf(x -> x.getFechaCaducidad().isAfter(LocalDate.now()));
      
      return peticionesOrdenadas;
   }
   
   public Comparator<Peticion> setComparators(BuscaForm buscaForm) {
      Comparator<Peticion> peticionComparator = Comparator.comparing(x -> x.getId());
      if (buscaForm.getOpcionRadio() != null) {
         if (buscaForm.getOpcionRadio().equals(1)) {
            peticionComparator = Comparator.comparingDouble(x -> Double.valueOf(x.getPresupuestoTotal()));
            peticionComparator = peticionComparator.reversed().thenComparing(Comparator.comparingInt(x -> x.getId()));
         } else if (buscaForm.getOpcionRadio().equals(2)) {
            peticionComparator = Comparator.comparingDouble(x -> Double.valueOf(x.getPresupuestoTotal()));
            peticionComparator = peticionComparator.thenComparing(Comparator.comparingInt(x -> x.getId()));
         } else if (buscaForm.getOpcionRadio().equals(3)) {
            peticionComparator = Comparator.comparing(x -> x.getFechaCaducidad());
            peticionComparator = peticionComparator.reversed().thenComparing(Comparator.comparingInt(x -> x.getId()));
         } else if (buscaForm.getOpcionRadio().equals(4)) {
            peticionComparator = Comparator.comparing(x -> x.getFechaCaducidad());
            peticionComparator = peticionComparator.thenComparing(Comparator.comparingInt(x -> x.getId()));
         }
      }
      return peticionComparator;
   }
   
   public Collection<Peticion> resetPeticiones(BuscaForm buscaForm) {
   
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      Double presupuestoForm = ! Strings.isNullOrEmpty(buscaForm.getPresupuesto()) ? Double.valueOf(buscaForm.getPresupuesto()) : 10000.0;
      LocalDate fechaCaducidadForm = ! Strings.isNullOrEmpty(buscaForm.getFechaCaducidad()) ? LocalDate.parse(buscaForm.getFechaCaducidad(), formatter) : LocalDate.MAX;
      Collection<Etiqueta> etiquetasForm = buscaForm.getEtiquetas() == null || buscaForm.getEtiquetas().isEmpty() ? etiquetaService.getEtiquetasActivas() : buscaForm.getEtiquetas();
      String provinciaForm = buscaForm.getProvincia() != null ? buscaForm.getProvincia().toLowerCase(espanyol) : "".toLowerCase(espanyol);
      String palabraClaveForm = buscaForm.getPalabraClave() != null ? buscaForm.getPalabraClave() : "";
   
   
      Collection<Peticion> res = peticionRepository.getPeticionesPorPalabraClave(palabraClaveForm);
      Set<Peticion> todasPorClave = new HashSet<>(peticionRepository.getPeticionesPorPalabraClave(palabraClaveForm));
     
      for (Peticion peticion : todasPorClave) {
         if (peticion.getMenorPresupuestoItem() > presupuestoForm || peticion.getFechaCaducidad().isAfter(fechaCaducidadForm) || Collections.disjoint(peticion.getEtiquetas(), etiquetasForm) || ! peticion.getProvincia().toLowerCase(espanyol).contains(provinciaForm)) {
                
            res.remove(peticion);
         }
      }
   
      return res;
   }
}
