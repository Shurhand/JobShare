package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

@Entity
@Access(AccessType.PROPERTY)
public class Peticion extends DomainEntity {
   private String titulo;
   private String descripcion;
   private LocalDate fechaCreacion;
   private LocalDate fechaCaducidad;
   private String provincia;
   private URL foto;
   private Estado estado;
   
   public Peticion() {
      super();
   }
   
   public Peticion(String titulo, String descripcion, String fechaCreacion, String fechaCaducidad, String provincia, URL foto, Estado estado) {
      this.titulo = titulo;
      this.descripcion = descripcion;
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaCreacion = LocalDate.parse(fechaCreacion, formatter);
      this.fechaCaducidad = LocalDate.parse(fechaCaducidad, formatter);
      this.provincia = provincia;
      this.foto = foto;
      this.estado = estado;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 50)
   public String getTitulo() {
      return titulo;
   }
   
   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Lob
   @Length(max = 1000)
   public String getDescripcion() {
      return descripcion;
   }
   
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
   
   @NotNull
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaCreacion() {
      return fechaCreacion;
   }
   
   public void setFechaCreacion(LocalDate fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }
   
   @NotNull
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaCaducidad() {
      return fechaCaducidad;
   }
   
   public void setFechaCaducidad(LocalDate fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }
   
   public URL getFoto() {
      return foto;
   }
   
   public void setFoto(URL foto) {
      this.foto = foto;
   }
   
   @NotNull
   @Enumerated
   public Estado getEstado() {
      return estado;
   }
   
   public void setEstado(Estado estado) {
      this.estado = estado;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 50)
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   // Relaciones
   private Usuario usuario;
   private Collection<Item> items;
   private Set<Etiqueta> etiquetas;
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   @JsonBackReference
   public Usuario getUsuario() {
      return usuario;
   }
   
   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "peticion")
   @JsonManagedReference
   public Collection<Item> getItems() {
      return items;
   }
   
   public void setItems(Collection<Item> items) {
      this.items = items;
   }
   
   @NotNull
   @JsonBackReference
   @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "peticiones")
   public Set<Etiqueta> getEtiquetas() {
      return etiquetas;
   }
   
   public void setEtiquetas(Set<Etiqueta> etiquetas) {
      this.etiquetas = etiquetas;
   }
   
   @Transient
   public String getPresupuestoTotal() {
      String presupuesto = String.valueOf(this.getItems().stream().mapToDouble(x -> x.getPresupuesto()).sum());
      if (presupuesto.endsWith("0")) {
         presupuesto = presupuesto.substring(0, presupuesto.length() - 2);
      }
      return presupuesto;
   }
}
