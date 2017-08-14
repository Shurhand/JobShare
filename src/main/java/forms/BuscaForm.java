package forms;

import domain.Etiqueta;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;

public class BuscaForm {
   public BuscaForm() {
      super();
   }
   
   private String palabraClave;
   private LocalDate fechaCaducidad;
   private Double presupuesto;
   private String provincia;
   private Collection<Etiqueta> etiquetas;
   private Boolean mayorPresupuesto;
   private Boolean mayorfechaCaducidad;
   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getPalabraClave() {
      return palabraClave;
   }
   
   public void setPalabraClave(String palabraClave) {
      this.palabraClave = palabraClave;
   }
   
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public LocalDate getFechaCaducidad() {
      return fechaCaducidad;
   }
   
   public void setFechaCaducidad(LocalDate fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }
   
   @Range(min = 1, max = 10000)
   public Double getPresupuesto() {
      return presupuesto;
   }
   
   public void setPresupuesto(Double presupuesto) {
      this.presupuesto = presupuesto;
   }
   
   @Length(max = 50)
   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   public Collection<Etiqueta> getEtiquetas() {
      return etiquetas;
   }
   
   public void setEtiquetas(Collection<Etiqueta> etiquetas) {
      this.etiquetas = etiquetas;
   }
   
   public Boolean getMayorPresupuesto() {
      return mayorPresupuesto;
   }
   
   public void setMayorPresupuesto(Boolean mayorPresupuesto) {
      this.mayorPresupuesto = mayorPresupuesto;
   }
   
   
   public Boolean getMayorfechaCaducidad() {
      return mayorfechaCaducidad;
   }
   
   public void setMayorfechaCaducidad(Boolean mayorfechaCaducidad) {
      this.mayorfechaCaducidad = mayorfechaCaducidad;
   }
}
