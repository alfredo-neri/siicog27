package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PRE_TSUELDOS database table.
 * 
 */
@Entity
@Table(name="PRE_TSUELDOS")
@NamedQuery(name="PreTsueldo.findAll", query="SELECT p FROM PreTsueldo p")
public class PreTsueldo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private PreTsueldoPK id;

	/**
	 * Instantiates a new pre tsueldo.
	 */
	public PreTsueldo() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public PreTsueldoPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(PreTsueldoPK id) {
		this.id = id;
	}

}