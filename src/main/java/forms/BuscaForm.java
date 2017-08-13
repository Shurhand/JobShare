package forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BuscaForm {
   public BuscaForm() {
      super();
   }
   
   private String palabraClave;
   private LocalDate fechaCaducidad;
   private Double presupuesto;
   private String provincia;
   
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
}
