package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity {
   private String nombre;
   private String descripcion;
   private Double presupuesto;
   private String foto;
   
   public Item() {
      super();
   }
   
   
   @NotBlank(message = "{error.notblank}")
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 50)
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
   @Range(min = 1, max = 10000)
   public Double getPresupuesto() {
      return presupuesto;
   }
   
   public void setPresupuesto(Double presupuesto) {
      this.presupuesto = presupuesto;
   }
   
   @SafeHtml
   @Length(max = 250)
   @URL
   public String getFoto() {
      return foto;
   }
   
   public void setFoto(String foto) {
      this.foto = foto;
   }
   
   // Relaciones
   private Peticion peticion;
   private Pago pago;
   private Collection<Oferta> ofertas;
   
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
   
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
   @JsonIgnore
   public Collection<Oferta> getOfertas() {
      return ofertas;
   }
   
   public void setOfertas(Collection<Oferta> ofertas) {
      this.ofertas = ofertas;
   }
   
   @Transient
   public boolean estaContratado() {
      return this.getOfertas().stream().anyMatch(x -> x.getEstado().equals(Estado.CONTRATADA));
   }
}
