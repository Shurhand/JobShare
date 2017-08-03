package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity {
   private String nombre;
   private String descripcion;
   private Double presupuesto;
   private LocalDate fechaCreacion;
   private Estado estado;
   
   @NotBlank
   @SafeHtml
   public String getNombre() {
      return nombre;
   }
   
   public void setNombre(String nombre) {
      this.nombre = nombre;
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
   @Range(min = 1, max = 10000)
   public Double getPresupuesto() {
      return presupuesto;
   }
   
   public void setPresupuesto(Double presupuesto) {
      this.presupuesto = presupuesto;
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
   
   @Enumerated
   @NotNull
   public Estado getEstado() {
      return estado;
   }
   
   public void setEstado(Estado estado) {
      this.estado = estado;
   }
   
   @NotNull
   @NotEmpty
   @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                            CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "items")
   public Collection<Etiqueta> getEtiquetas() {
      return etiquetas;
   }
   
   public void setEtiquetas(Collection<Etiqueta> etiquetas) {
      this.etiquetas = etiquetas;
   }
   
   // Relaciones
   private Collection<Etiqueta> etiquetas;
   private Peticion peticion;
   private Pago pago;
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   public Peticion getPeticion() {
      return peticion;
   }
   
   public void setPeticion(Peticion peticion) {
      this.peticion = peticion;
   }
   
   @NotNull
   @Valid
   @ManyToOne(optional = false)
   public Pago getPago() {
      return pago;
   }
   
   public void setPago(Pago pago) {
      this.pago = pago;
   }
}
