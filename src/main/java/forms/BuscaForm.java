package forms;

import domain.Etiqueta;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;

public class BuscaForm {
   public BuscaForm() {
      super();
   }
   
   private String palabraClave;
   private String fechaCaducidad;
   private String presupuesto;
   private String provincia;
   private Collection<Etiqueta> etiquetas;
   private Integer opcionRadio;

   
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   @Length(max = 250)
   public String getPalabraClave() {
      return palabraClave;
   }
   
   public void setPalabraClave(String palabraClave) {
      this.palabraClave = palabraClave;
   }
   
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   public String getFechaCaducidad() {
      return fechaCaducidad;
   }
   
   public void setFechaCaducidad(String fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }
   
   @Length(max = 5)
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
   public String getPresupuesto() {
      return presupuesto;
   }
   
   public void setPresupuesto(String presupuesto) {
      this.presupuesto = presupuesto;
   }
   
   @Length(max = 50)
   @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
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
   
   public Integer getOpcionRadio() {
      return opcionRadio;
   }
   
   public void setOpcionRadio(Integer opcionRadio) {
      this.opcionRadio = opcionRadio;
   }
}
