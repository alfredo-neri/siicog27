package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_MESES database table.
 * 
 */
@Entity
@Table(name="TC_MESES")
@NamedQuery(name="TcMes.findAll", query="SELECT t FROM TcMes t")
public class TcMes extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	

	/** The descripcion. */
	private String descripcion;

	/** The mes. */
	private String mes;

	/**
	 * Instantiates a new tc mes.
	 */
	public TcMes() {
	}
	
	/**
	 * Instantiates a new tc mes.
	 *
	 * @param descripcion the descripcion
	 * @param mes the mes
	 */
	public TcMes(String descripcion, String mes) {
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

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return this.mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

}