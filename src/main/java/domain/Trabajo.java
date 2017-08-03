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

public class Trabajo extends DomainEntity {
   private String empresa;
   private String puesto;
   private LocalDate fechaInicio;
   private LocalDate fechaFin;
   // Relaciones
   private Profesional profesional;
   
   @NotBlank
   @SafeHtml
   public String getEmpresa() {
      return empresa;
   }
   
   public void setEmpresa(String empresa) {
      this.empresa = empresa;
   }
   
   @NotBlank
   @SafeHtml
   public String getPuesto() {
      return puesto;
   }
   
   public void setPuesto(String puesto) {
      this.puesto = puesto;
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
