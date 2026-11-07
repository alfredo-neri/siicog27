package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_LOCALIDADES_USUARIO database table.
 * 
 */
@Entity
@Table(name="TC_LOCALIDADES_USUARIO")
@NamedQuery(name="TcLocalidadesUsuario.findAll", query="SELECT t FROM TcLocalidadesUsuario t")
public class TcLocalidadesUsuario extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The clave. */
	private String clave;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tc localidades usuario.
	 */
	public TcLocalidadesUsuario() {
	}

	

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
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}