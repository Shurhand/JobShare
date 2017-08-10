package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

@Entity
@Access(AccessType.PROPERTY)
public class Peticion extends DomainEntity {
   private String titulo;
   private String descripcion;
   private LocalDate fechaCreacion;
   private LocalDate fechaCaducidad;
   private URL foto;
   private Estado estado;
   
   public Peticion() {
      super();
   }
   
   public Peticion(String titulo, String descripcion, String fechaCreacion, String fechaCaducidad, URL foto, Estado estado) {
      this.titulo = titulo;
      this.descripcion = descripcion;
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaCreacion = LocalDate.parse(fechaCreacion, formatter);
      this.fechaCaducidad = LocalDate.parse(fechaCaducidad, formatter);
      this.foto = foto;
      this.estado = estado;
   }
   
   @NotBlank
   @SafeHtml
   public String getTitulo() {
      return titulo;
   }
   
   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
   
   @NotBlank
   @SafeHtml
   public String getDescripcion() {
      return descripcion;
   }
   
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaCreacion() {
      return fechaCreacion;
   }
   
   public void setFechaCreacion(LocalDate fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }
   
   @NotNull
   @SafeHtml
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
   
   // Relaciones
   private Usuario usuario;
   private Collection<Item> items;
   
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
   @NotEmpty
   @Valid
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "peticion")
   public Collection<Item> getItems() {
      return items;
   }
   
   public void setItems(Collection<Item> items) {
      this.items = items;
   }
}
