package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class Estudio extends DomainEntity {
   private String centro;
   private String titulacion;
   private LocalDate fechaInicio;
   private LocalDate fechaFin;
   
   public Estudio() {
      super();
   }
   
   public Estudio(String centro, String titulacion, String fechaInicio, String fechaFin) {
      this.centro = centro;
      this.titulacion = titulacion;
      
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Locale espanyol = new Locale("es", "ES");
      formatter = formatter.withLocale(espanyol);
      
      this.fechaInicio = LocalDate.parse(fechaInicio, formatter);
      this.fechaFin = LocalDate.parse(fechaFin, formatter);
      
      
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getCentro() {
      return centro;
   }
   
   public void setCentro(String centro) {
      this.centro = centro;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getTitulacion() {
      return titulacion;
   }
   
   public void setTitulacion(String titulacion) {
      this.titulacion = titulacion;
   }
   
   @NotNull
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
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
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
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
