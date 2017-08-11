package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;
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

public class Valoracion extends DomainEntity {
   private Double puntuacion;
   private String comentario;
   private LocalDate fechaCreacion;
   
   public Valoracion() {
      super();
   }
   
   public Valoracion(Double puntuacion, String comentario, String fechaCreacion) {
      this.puntuacion = puntuacion;
      this.comentario = comentario;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaCreacion = LocalDate.parse(fechaCreacion, formatter);
   }
   
   public Valoracion(Double puntuacion, String comentario, String fechaCreacion, Oferta oferta) {
      this.puntuacion = puntuacion;
      this.comentario = comentario;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaCreacion = LocalDate.parse(fechaCreacion, formatter);
      this.oferta = oferta;
   }
   
   @NotNull
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Range(min = 1, max = 5)
   public Double getPuntuacion() {
      return puntuacion;
   }
   
   public void setPuntuacion(Double puntuacion) {
      this.puntuacion = puntuacion;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
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
   
   // Relaciones
   private Oferta oferta;
   private Profesional profesional;
   
   //   @NotNull
//   @Valid
   @OneToOne(optional = true)
   public Oferta getOferta() {
      return oferta;
   }
   
   public void setOferta(Oferta oferta) {
      this.oferta = oferta;
   }
   
   @NotNull
   @JsonBackReference
   @Valid
   @ManyToOne(optional = false)
   public Profesional getProfesional() {
      return profesional;
   }
   
   public void setProfesional(Profesional profesional) {
      this.profesional = profesional;
   }
}
