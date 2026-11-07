package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4111 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm4111.findAll", query="SELECT p FROM Pm4111 p")
public class Pm4111 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name="\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** The anual. */
	private Integer anual;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The nac. */
	private Integer nac;

	/** The nap. */
	private Integer nap;

	/** The obsnac. */
	private String obsnac;

	/** The obsnap. */
	private String obsnap;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 4111.
	 */
	public Pm4111() {
	}

	/**
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return this.anual;
	}

	/**
	 * Sets the anual.
	 *
	 * @param anual the new anual
	 */
	public void setAnual(Integer anual) {
		this.anual = anual;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return this.capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return this.feccap;
	}

	/**
	 * Sets the feccap.
	 *
	 * @param feccap the new feccap
	 */
	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
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
	 * Gets the nac.
	 *
	 * @return the nac
	 */
	public Integer getNac() {
		return this.nac;
	}

	/**
	 * Sets the nac.
	 *
	 * @param nac the new nac
	 */
	public void setNac(Integer nac) {
		this.nac = nac;
	}

	/**
	 * Gets the nap.
	 *
	 * @return the nap
	 */
	public Integer getNap() {
		return this.nap;
	}

	/**
	 * Sets the nap.
	 *
	 * @param nap the new nap
	 */
	public void setNap(Integer nap) {
		this.nap = nap;
	}

	/**
	 * Gets the obsnac.
	 *
	 * @return the obsnac
	 */
	public String getObsnac() {
		return this.obsnac;
	}

	/**
	 * Sets the obsnac.
	 *
	 * @param obsnac the new obsnac
	 */
	public void setObsnac(String obsnac) {
		this.obsnac = obsnac;
	}

	/**
	 * Gets the obsnap.
	 *
	 * @return the obsnap
	 */
	public String getObsnap() {
		return this.obsnap;
	}

	/**
	 * Sets the obsnap.
	 *
	 * @param obsnap the new obsnap
	 */
	public void setObsnap(String obsnap) {
		this.obsnap = obsnap;
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

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

}