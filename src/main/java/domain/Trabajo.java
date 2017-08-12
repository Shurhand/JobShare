package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import utilities.LocalDateDeserializer;
import utilities.internal.LocalDateSerializer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Access(AccessType.PROPERTY)

public class Trabajo extends DomainEntity {
   private String empresa;
   private String puesto;
   private LocalDate fechaInicio;
   private LocalDate fechaFin;
   
   public Trabajo() {
      super();
   }
   
   public Trabajo(String empresa, String puesto, String fechaInicio, String fechaFin) {
      this.empresa = empresa;
      this.puesto = puesto;
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaInicio = LocalDate.parse(fechaInicio, formatter);
      this.fechaFin = LocalDate.parse(fechaFin, formatter);
   }
   
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 20)
   public String getEmpresa() {
      return empresa;
   }
   
   public void setEmpresa(String empresa) {
      this.empresa = empresa;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 20)
   public String getPuesto() {
      return puesto;
   }
   
   public void setPuesto(String puesto) {
      this.puesto = puesto;
   }
   
   @NotNull
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   @JsonDeserialize(using = LocalDateDeserializer.class)
   @JsonSerialize(using = LocalDateSerializer.class)
   public LocalDate getFechaInicio() {
      return fechaInicio;
   }
   
   public void setFechaInicio(LocalDate fechaInicio) {
      this.fechaInicio = fechaInicio;
   }
   
   @NotNull
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   @JsonDeserialize(using = LocalDateDeserializer.class)
   @JsonSerialize(using = LocalDateSerializer.class)
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
   @JsonBackReference
   public Profesional getProfesional() {
      return profesional;
   }
   
   public void setProfesional(Profesional profesional) {
      this.profesional = profesional;
   }
}
