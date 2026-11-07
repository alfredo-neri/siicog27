package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_TIPO_PODER database table.
 * 
 */
/**
 * @author Mateo
 *
 */
@Entity
@Table(name="TC_TIPO_PODER")
@NamedQuery(name="TcTipoPoder.findAll", query="SELECT t FROM TcTipoPoder t")
public class TcTipoPoder extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The descripcion. */
	private String descripcion;

	

	/** The id poder. */
	@Column(name="ID_PODER")
	private Integer idPoder;

	/**
	 * Instantiates a new tc tipo poder.
	 */
	public TcTipoPoder() {
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
	 * Gets the id poder.
	 *
	 * @return the id poder
	 */
	public Integer getIdPoder() {
		return this.idPoder;
	}

	/**
	 * Sets the id poder.
	 *
	 * @param idPoder the new id poder
	 */
	public void setIdPoder(Integer idPoder) {
		this.idPoder = idPoder;
	}

}