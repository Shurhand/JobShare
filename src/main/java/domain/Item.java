package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity {
   private String nombre;
   private String descripcion;
   private Double presupuesto;
   private URL foto;
   private Estado estado;
   
   public Item() {
      super();
   }
   
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 30)
   public String getNombre() {
      return nombre;
   }
   
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getDescripcion() {
      return descripcion;
   }
   
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
   
   @NotNull
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Range(min = 1, max = 10000)
   public Double getPresupuesto() {
      return presupuesto;
   }
   
   public void setPresupuesto(Double presupuesto) {
      this.presupuesto = presupuesto;
   }
   
   @Enumerated
   @NotNull
//   @Pattern(regexp = "^" + "ACTIVA" + "|" + "INACTIVA" + "$")
   public Estado getEstado() {
      return estado;
   }
   
   public void setEstado(Estado estado) {
      this.estado = estado;
   }
   
   public URL getFoto() {
      return foto;
   }
   
   public void setFoto(URL foto) {
      this.foto = foto;
   }
   
   // Relaciones
   private Peticion peticion;
   private Pago pago;
   
   @NotNull
   @Valid
   @JsonBackReference
   @ManyToOne(optional = false)
   public Peticion getPeticion() {
      return peticion;
   }
   
   public void setPeticion(Peticion peticion) {
      this.peticion = peticion;
   }
   
   @Valid
   @ManyToOne(optional = true)
   public Pago getPago() {
      return pago;
   }
   
   public void setPago(Pago pago) {
      this.pago = pago;
   }
}
