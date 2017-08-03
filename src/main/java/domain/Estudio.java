package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)

public class Estudio extends DomainEntity {
   private String centro;
   private String titulacion;
   private LocalDate fechaInicio;
   private LocalDate fechaFin;
   
   @NotBlank
   @SafeHtml
   public String getCentro() {
      return centro;
   }
   
   public void setCentro(String centro) {
      this.centro = centro;
   }
   
   @NotBlank
   @SafeHtml
   public String getTitulacion() {
      return titulacion;
   }
   
   public void setTitulacion(String titulacion) {
      this.titulacion = titulacion;
   }
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaInicio() {
      return fechaInicio;
   }
   
   public void setFechaInicio(LocalDate fechaInicio) {
      this.fechaInicio = fechaInicio;
   }
   
   @NotNull
   @SafeHtml
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaFin() {
      return fechaFin;
   }
   
   public void setFechaFin(LocalDate fechaFin) {
      this.fechaFin = fechaFin;
   }
   
   // Relaciones
   private Profesional profesional;
   
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
