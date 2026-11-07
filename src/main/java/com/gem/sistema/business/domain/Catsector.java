package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the CATSECTOR database table.
 * 
 */
@Entity
@NamedQuery(name="Catsector.findAll", query="SELECT c FROM Catsector c")
public class Catsector implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idsector. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idsector;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The nomsector. */
	private String nomsector;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new catsector.
	 */
	public Catsector() {
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public int getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(int idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the nomsector.
	 *
	 * @return the nomsector
	 */
	public String getNomsector() {
		return this.nomsector;
	}

	/**
	 * Sets the nomsector.
	 *
	 * @param nomsector the new nomsector
	 */
	public void setNomsector(String nomsector) {
		this.nomsector = nomsector;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

}