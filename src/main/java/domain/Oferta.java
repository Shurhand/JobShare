package domain;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class Oferta extends DomainEntity {
   private Double precio;
   private String comentario;
   private LocalDate fechaCreacion;
   private LocalDate fechaCaducidad;
   private URL foto;
   private Estado estado;
   
   @NotNull
   @SafeHtml
   @Range(min = 1, max = 10000)
   public Double getPrecio() {
      return precio;
   }
   
   public void setPrecio(Double precio) {
      this.precio = precio;
   }
   
   @NotNull
   @SafeHtml
   public String getComentario() {
      return comentario;
   }
   
   public void setComentario(String comentario) {
      this.comentario = comentario;
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
   private Profesional profesional;
   private Valoracion valoracion;
   private Item item;
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   public Profesional getProfesional() {
      return profesional;
   }
   
   public void setProfesional(Profesional profesional) {
      this.profesional = profesional;
   }
   
   @NotNull
   @Valid
   @OneToOne(optional = true)
   public Valoracion getValoracion() {
      return valoracion;
   }
   
   public void setValoracion(Valoracion valoracion) {
      this.valoracion = valoracion;
   }
   
   @NotNull
   @Valid
   @OneToOne(optional = false)
   public Item getItem() {
      return item;
   }
   
   public void setItem(Item item) {
      this.item = item;
   }
}
