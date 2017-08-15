package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Access(AccessType.PROPERTY)
public class Oferta extends DomainEntity {
   private Double precio;
   private String comentario;
   private LocalDate fechaCreacion;
   private LocalDate fechaCaducidad;
   private Estado estado;
   
   public Oferta() {
      super();
   }
   
   public Oferta(Double precio, String comentario, String fechaCreacion, String fechaCaducidad, Estado estado) {
      this.precio = precio;
      this.comentario = comentario;
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaCreacion = LocalDate.parse(fechaCreacion, formatter);
      this.fechaCaducidad = LocalDate.parse(fechaCaducidad, formatter);
      this.estado = estado;
   }
   
   @NotNull
   @Range(min = 1, max = 10000)
   public Double getPrecio() {
      return precio;
   }
   
   public void setPrecio(Double precio) {
      this.precio = precio;
   }
   
   @NotNull
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getComentario() {
      return comentario;
   }
   
   public void setComentario(String comentario) {
      this.comentario = comentario;
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
   
   @NotNull
   @Enumerated
   public Estado getEstado() {
      return estado;
   }
   
   public void setEstado(Estado estado) {
      this.estado = estado;
   }
   
   // Relaciones
   private Profesional profesional;
   private Valoracion valoracion;
   private Item item;
   
   @JsonBackReference
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   public Profesional getProfesional() {
      return profesional;
   }
   
   public void setProfesional(Profesional profesional) {
      this.profesional = profesional;
   }
   
   @Valid
   @JsonIgnore
   @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "oferta")
   public Valoracion getValoracion() {
      return valoracion;
   }
   
   public void setValoracion(Valoracion valoracion) {
      this.valoracion = valoracion;
   }
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   @JsonIgnore
   public Item getItem() {
      return item;
   }
   
   public void setItem(Item item) {
      this.item = item;
   }
}
