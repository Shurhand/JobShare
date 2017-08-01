/* UserAccount.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package security;

import domain.DomainEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Cuenta extends DomainEntity implements UserDetails {

	// Constructors -----------------------------------------------------------

	private static final long serialVersionUID = 7254823034213841482L;

	public Cuenta() {
		super();

		this.authorities = new ArrayList<Rol>();
	}

	// Attributes -------------------------------------------------------------

	// UserDetails interface --------------------------------------------------

	private String username;
	private String password;
	private Collection<Rol> authorities;

	@Size(min = 5, max = 32)
	@Column(unique = true)
	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	@Valid
	@ElementCollection
	@Override
	public Collection<Rol> getAuthorities() {
		// WARNING: Should return an unmodifiable copy, but it's not possible with hibernate!
		return authorities;
	}

	public void setAuthorities(Collection<Rol> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Rol autoridad) {
		Assert.notNull(autoridad);
		Assert.isTrue(!authorities.contains(autoridad));

		authorities.add(autoridad);
	}

	public void removeAuthority(Rol autoridad) {
		Assert.notNull(autoridad);
		Assert.isTrue(authorities.contains(autoridad));
		
		authorities.remove(autoridad);
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

}
