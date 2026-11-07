package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The primary key class for the PRE_TSUELDOS database table.
 * 
 */
@Embeddable
public class PreTsueldoPK implements Serializable {
	
	/** The Constant serialVersionUID. */
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/** The cvepuesto. */
	private String cvepuesto;

	/** The nompuesto. */
	private String nompuesto;

	/**
	 * Instantiates a new pre tsueldo PK.
	 */
	public PreTsueldoPK() {
	}
	
	/**
	 * Gets the cvepuesto.
	 *
	 * @return the cvepuesto
	 */
	public String getCvepuesto() {
		return this.cvepuesto;
	}
	
	/**
	 * Sets the cvepuesto.
	 *
	 * @param cvepuesto the new cvepuesto
	 */
	public void setCvepuesto(String cvepuesto) {
		this.cvepuesto = cvepuesto;
	}
	
	/**
	 * Gets the nompuesto.
	 *
	 * @return the nompuesto
	 */
	public String getNompuesto() {
		return this.nompuesto;
	}
	
	/**
	 * Sets the nompuesto.
	 *
	 * @param nompuesto the new nompuesto
	 */
	public void setNompuesto(String nompuesto) {
		this.nompuesto = nompuesto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PreTsueldoPK)) {
			return false;
		}
		PreTsueldoPK castOther = (PreTsueldoPK)other;
		return 
			this.cvepuesto.equals(castOther.cvepuesto)
			&& this.nompuesto.equals(castOther.nompuesto);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cvepuesto.hashCode();
		hash = hash * prime + this.nompuesto.hashCode();
		
		return hash;
	}
}