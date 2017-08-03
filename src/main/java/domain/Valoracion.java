package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)

public class Valoracion extends DomainEntity {
   private Double puntuacion;
   private String comentario;
   private LocalDate fechaCreacion;
   
   @NotNull
   @SafeHtml
   @Range(min = 1, max = 5)
   public Double getPuntuacion() {
      return puntuacion;
   }
   
   public void setPuntuacion(Double puntuacion) {
      this.puntuacion = puntuacion;
   }
   
   @NotBlank
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
   
   // Relaciones
   private Oferta oferta;
   private Profesional profesional;
   
   @NotNull
   @Valid
   @OneToOne(optional = false)
   public Oferta getOferta() {
      return oferta;
   }
   
   public void setOferta(Oferta oferta) {
      this.oferta = oferta;
   }
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   public Profesional getProfesional() {
      return profesional;
   }
   
   public void setProfesional(Profesional profesional) {
      this.profesional = profesional;
   }
}
