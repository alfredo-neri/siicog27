package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_ROLES database table.
 * 
 */
@Entity
@Table(name="TC_ROLES")
@NamedQuery(name="TcRole.findAll", query="SELECT t FROM TcRole t")
public class TcRole extends AbstractEntity implements  Serializable  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;



	/** The clave. */
	private String clave;

	/** The nombre. */
	private String nombre;

	

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return this.clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}