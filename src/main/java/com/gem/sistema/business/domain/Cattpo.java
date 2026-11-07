package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the CATTPO database table.
 * 
 */
@Entity
@NamedQuery(name="Cattpo.findAll", query="SELECT c FROM Cattpo c")
public class Cattpo extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipnom. */
	private String tipnom;

	/** The tippol. */
	private String tippol;

	/**
	 * Instantiates a new cattpo.
	 */
	public Cattpo() {
	}

	

	/**
	 * Gets the tipnom.
	 *
	 * @return the tipnom
	 */
	public String getTipnom() {
		return this.tipnom;
	}

	/**
	 * Sets the tipnom.
	 *
	 * @param tipnom the new tipnom
	 */
	public void setTipnom(String tipnom) {
		this.tipnom = tipnom;
	}

	/**
	 * Gets the tippol.
	 *
	 * @return the tippol
	 */
	public String getTippol() {
		return this.tippol;
	}

	/**
	 * Sets the tippol.
	 *
	 * @param tippol the new tippol
	 */
	public void setTippol(String tippol) {
		this.tippol = tippol;
	}

}