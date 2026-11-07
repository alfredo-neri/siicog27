package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the POLIFL database table.
 * 
 */
@Entity
@NamedQuery(name = "Polifl.findAll", query = "SELECT p FROM Polifl p")
public class Polifl extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new polifl.
	 *
	 * @param anopol the anopol
	 * @param campo the campo
	 * @param canflu the canflu
	 * @param clvflu the clvflu
	 * @param conpol the conpol
	 * @param idRef the id ref
	 * @param mespol the mespol
	 * @param renpol the renpol
	 * @param userid the userid
	 */
	public Polifl(Integer anopol, Integer campo, BigDecimal canflu, BigDecimal clvflu, Integer conpol, Long idRef,
			Integer mespol, BigDecimal renpol, String userid) {
		this.anopol = anopol;
		this.campo  = campo;
		this.canflu = canflu;
		this.clvflu = clvflu;
		this.conpol = conpol;
		this.idRef  = idRef;
		this.mespol = mespol;
		this.renpol = renpol;
		this.userid = userid;
	}

	/** The anopol. */
	private Integer anopol;

	/** The campo. */
	private Integer campo;

	/** The canflu. */
	private BigDecimal canflu;

	/** The clvflu. */
	private BigDecimal clvflu;

	/** The conpol. */
	private Integer conpol;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The mespol. */
	private Integer mespol;

	/** The renpol. */
	private BigDecimal renpol;

	/** The tippol. */
	private String tippol;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new polifl.
	 */
	public Polifl() {
	}

	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public Integer getAnopol() {
		return this.anopol;
	}

	/**
	 * Sets the anopol.
	 *
	 * @param anopol the new anopol
	 */
	public void setAnopol(Integer anopol) {
		this.anopol = anopol;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public Integer getCampo() {
		return this.campo;
	}

	/**
	 * Sets the campo.
	 *
	 * @param campo the new campo
	 */
	public void setCampo(Integer campo) {
		this.campo = campo;
	}

	/**
	 * Gets the canflu.
	 *
	 * @return the canflu
	 */
	public BigDecimal getCanflu() {
		return this.canflu;
	}

	/**
	 * Sets the canflu.
	 *
	 * @param canflu the new canflu
	 */
	public void setCanflu(BigDecimal canflu) {
		this.canflu = canflu;
	}

	/**
	 * Gets the clvflu.
	 *
	 * @return the clvflu
	 */
	public BigDecimal getClvflu() {
		return this.clvflu;
	}

	/**
	 * Sets the clvflu.
	 *
	 * @param clvflu the new clvflu
	 */
	public void setClvflu(BigDecimal clvflu) {
		this.clvflu = clvflu;
	}

	/**
	 * Gets the conpol.
	 *
	 * @return the conpol
	 */
	public int getConpol() {
		return this.conpol;
	}

	/**
	 * Sets the conpol.
	 *
	 * @param conpol the new conpol
	 */
	public void setConpol(Integer conpol) {
		this.conpol = conpol;
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
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public Integer getMespol() {
		return this.mespol;
	}

	/**
	 * Sets the mespol.
	 *
	 * @param mespol the new mespol
	 */
	public void setMespol(Integer mespol) {
		this.mespol = mespol;
	}

	/**
	 * Gets the renpol.
	 *
	 * @return the renpol
	 */
	public BigDecimal getRenpol() {
		return this.renpol;
	}

	/**
	 * Sets the renpol.
	 *
	 * @param renpol the new renpol
	 */
	public void setRenpol(BigDecimal renpol) {
		this.renpol = renpol;
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

	public String toString(){
		return new StringBuffer("Polifl(")
		.append("id: ")
		.append(getId())
		.append(", anopol: ")
		.append(anopol)
		.append(", campo: ")
		.append(campo)
		.append(", canflu: ")
		.append(canflu)
		.append(", clvflu: ")
		.append(clvflu)
		.append(", conpol: ")
		.append(conpol)
		.append(", idRef: ")
		.append(idRef)
		.append(", idsector: ")
		.append(idsector)
		.append(", mespol: ")
		.append(mespol)
		.append(", renpol: ")
		.append(renpol)
		.append(", tippol: ")
		.append(tippol)
		.append(", userid:" )
		.append(userid)
		.append(")").toString();
	}


}
