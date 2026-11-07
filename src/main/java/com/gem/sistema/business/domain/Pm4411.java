package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4411 database table.
 * 
 * @author Alfredo
 *
 */
@Entity
@NamedQuery(name="Pm4411.findAll", query="SELECT p FROM Pm4411 p")
public class Pm4411 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The mes. */
	private Integer mes;

	/** The obs 1. */
	private String obs1;

	/** The obs 2. */
	private String obs2;

	/** The obs 3. */
	private String obs3;

	/** The obs 4. */
	private String obs4;

	/** The obs 5. */
	private String obs5;

	/** The obs 6. */
	private String obs6;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 4411.
	 */
	public Pm4411() {
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
	public Integer getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return this.mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the obs 1.
	 *
	 * @return the obs 1
	 */
	public String getObs1() {
		return this.obs1;
	}

	/**
	 * Sets the obs 1.
	 *
	 * @param obs1 the new obs 1
	 */
	public void setObs1(String obs1) {
		this.obs1 = obs1;
	}

	/**
	 * Gets the obs 2.
	 *
	 * @return the obs 2
	 */
	public String getObs2() {
		return this.obs2;
	}

	/**
	 * Sets the obs 2.
	 *
	 * @param obs2 the new obs 2
	 */
	public void setObs2(String obs2) {
		this.obs2 = obs2;
	}

	/**
	 * Gets the obs 3.
	 *
	 * @return the obs 3
	 */
	public String getObs3() {
		return this.obs3;
	}

	/**
	 * Sets the obs 3.
	 *
	 * @param obs3 the new obs 3
	 */
	public void setObs3(String obs3) {
		this.obs3 = obs3;
	}

	/**
	 * Gets the obs 4.
	 *
	 * @return the obs 4
	 */
	public String getObs4() {
		return this.obs4;
	}

	/**
	 * Sets the obs 4.
	 *
	 * @param obs4 the new obs 4
	 */
	public void setObs4(String obs4) {
		this.obs4 = obs4;
	}

	/**
	 * Gets the obs 5.
	 *
	 * @return the obs 5
	 */
	public String getObs5() {
		return this.obs5;
	}

	/**
	 * Sets the obs 5.
	 *
	 * @param obs5 the new obs 5
	 */
	public void setObs5(String obs5) {
		this.obs5 = obs5;
	}

	/**
	 * Gets the obs 6.
	 *
	 * @return the obs 6
	 */
	public String getObs6() {
		return this.obs6;
	}

	/**
	 * Sets the obs 6.
	 *
	 * @param obs6 the new obs 6
	 */
	public void setObs6(String obs6) {
		this.obs6 = obs6;
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