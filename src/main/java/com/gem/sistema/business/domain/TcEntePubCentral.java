package com.gem.sistema.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_ENTE_PUB_CENTRAL database table.
 * 
 */
@Entity
@Table(name="TC_ENTE_PUB_CENTRAL")
public class TcEntePubCentral extends AbstractEntity  {

	/** The desc. */
	@Column(name="DESC")
	private String desc;

	/** The ente. */
	private String ente;

	/**
	 * Instantiates a new tc ente pub central.
	 */
	public TcEntePubCentral() {
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the ente.
	 *
	 * @return the ente
	 */
	public String getEnte() {
		return this.ente;
	}

	/**
	 * Sets the ente.
	 *
	 * @param ente the new ente
	 */
	public void setEnte(String ente) {
		this.ente = ente;
	}

}