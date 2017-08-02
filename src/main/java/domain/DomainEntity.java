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

import lombok.Data;

import javax.persistence.*;


@Access(AccessType.PROPERTY)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class DomainEntity {
   
   @Id
   @GeneratedValue(strategy = GenerationType.TABLE)
   private long id;
   
   @Version
   private long version;
   
   private long hibernate;
 
}
