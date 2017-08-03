/* DomainEntity.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */
package domain;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DomainEntity {
   // Constructors -----------------------------------------------------------
   // Identification ---------------------------------------------------------
   private int id;
   private int version;
   
   public DomainEntity() {
      super();
   }
   
   @Id
   @GeneratedValue(strategy = GenerationType.TABLE)
   public int getId() {
      return id;
   }
   
   public void setId(int id) {
      this.id = id;
   }
   
   @Version
   public int getVersion() {
      return version;
   }
   
   public void setVersion(int version) {
      this.version = version;
   }
   
   // Equality ---------------------------------------------------------------
   
   @Override
   public int hashCode() {
      return this.getId();
   }
   
   @Override
   public boolean equals(Object other) {
      boolean result;
      
      if (this == other)
         result = true;
      else if (other == null)
         result = false;
      else if (other instanceof Integer)
         result = (this.getId() == (Integer) other);
      else if (! this.getClass().isInstance(other))
         result = false;
      else
         result = (this.getId() == ((DomainEntity) other).getId());
      
      return result;
   }
   
}
