package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the POLIDU database table.
 * 
 */
@Entity
@NamedQuery(name="Polidu.findAll", query="SELECT p FROM Polidu p")
public class Polidu extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The anodeu. */
	private int anodeu;

	/** The anopol. */
	private int anopol;

	/** The caopol. */
	private String caopol;

	/** The conpol. */
	private int conpol;

	/** The cuenta. */
	private String cuenta;

	/** The fecpag. */
	private String fecpag;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The idsector. */
	private int idsector;

	/** The mesdeu. */
	private int mesdeu;

	/** The mespol. */
	private int mespol;

	/** The mondeu. */
	private BigDecimal mondeu;

	/** The numnpr. */
	private int numnpr;

	/** The numpag. */
	private int numpag;

	/** The ordpol. */
	private BigDecimal ordpol;

	/** The renpol. */
	private Integer renpol;

	/** The scta. */
	private String scta;

	/** The sscta. */
	private String sscta;

	/** The ssscta. */
	private String ssscta;

	/** The sssscta. */
	private String sssscta;

	/** The tippag. */
	private int tippag;

	/** The tippol. */
	private String tippol;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/** The wtipo. */
	private String wtipo;

	/**
	 * Instantiates a new polidu.
	 */
	public Polidu() {
	}

	
	/**
	 * Gets the anodeu.
	 *
	 * @return the anodeu
	 */
	public int getAnodeu() {
		return this.anodeu;
	}

	/**
	 * Sets the anodeu.
	 *
	 * @param anodeu the new anodeu
	 */
	public void setAnodeu(int anodeu) {
		this.anodeu = anodeu;
	}

	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public int getAnopol() {
		return this.anopol;
	}

	/**
	 * Sets the anopol.
	 *
	 * @param anopol the new anopol
	 */
	public void setAnopol(int anopol) {
		this.anopol = anopol;
	}

	/**
	 * Gets the caopol.
	 *
	 * @return the caopol
	 */
	public String getCaopol() {
		return this.caopol;
	}

	/**
	 * Sets the caopol.
	 *
	 * @param caopol the new caopol
	 */
	public void setCaopol(String caopol) {
		this.caopol = caopol;
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
	public void setConpol(int conpol) {
		this.conpol = conpol;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return this.cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the fecpag.
	 *
	 * @return the fecpag
	 */
	public String getFecpag() {
		return this.fecpag;
	}

	/**
	 * Sets the fecpag.
	 *
	 * @param fecpag the new fecpag
	 */
	public void setFecpag(String fecpag) {
		this.fecpag = fecpag;
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
	 * Gets the mesdeu.
	 *
	 * @return the mesdeu
	 */
	public int getMesdeu() {
		return this.mesdeu;
	}

	/**
	 * Sets the mesdeu.
	 *
	 * @param mesdeu the new mesdeu
	 */
	public void setMesdeu(int mesdeu) {
		this.mesdeu = mesdeu;
	}

	/**
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public int getMespol() {
		return this.mespol;
	}

	/**
	 * Sets the mespol.
	 *
	 * @param mespol the new mespol
	 */
	public void setMespol(int mespol) {
		this.mespol = mespol;
	}

	/**
	 * Gets the mondeu.
	 *
	 * @return the mondeu
	 */
	public BigDecimal getMondeu() {
		return this.mondeu;
	}

	/**
	 * Sets the mondeu.
	 *
	 * @param mondeu the new mondeu
	 */
	public void setMondeu(BigDecimal mondeu) {
		this.mondeu = mondeu;
	}

	/**
	 * Gets the numnpr.
	 *
	 * @return the numnpr
	 */
	public int getNumnpr() {
		return this.numnpr;
	}

	/**
	 * Sets the numnpr.
	 *
	 * @param numnpr the new numnpr
	 */
	public void setNumnpr(int numnpr) {
		this.numnpr = numnpr;
	}

	/**
	 * Gets the numpag.
	 *
	 * @return the numpag
	 */
	public int getNumpag() {
		return this.numpag;
	}

	/**
	 * Sets the numpag.
	 *
	 * @param numpag the new numpag
	 */
	public void setNumpag(int numpag) {
		this.numpag = numpag;
	}

	/**
	 * Gets the ordpol.
	 *
	 * @return the ordpol
	 */
	public BigDecimal getOrdpol() {
		return this.ordpol;
	}

	/**
	 * Sets the ordpol.
	 *
	 * @param ordpol the new ordpol
	 */
	public void setOrdpol(BigDecimal ordpol) {
		this.ordpol = ordpol;
	}

	/**
	 * Gets the renpol.
	 *
	 * @return the renpol
	 */
	public Integer getRenpol() {
		return this.renpol;
	}

	/**
	 * Sets the renpol.
	 *
	 * @param renpol the new renpol
	 */
	public void setRenpol(Integer renpol) {
		this.renpol = renpol;
	}

	/**
	 * Gets the scta.
	 *
	 * @return the scta
	 */
	public String getScta() {
		return this.scta;
	}

	/**
	 * Sets the scta.
	 *
	 * @param scta the new scta
	 */
	public void setScta(String scta) {
		this.scta = scta;
	}

	/**
	 * Gets the sscta.
	 *
	 * @return the sscta
	 */
	public String getSscta() {
		return this.sscta;
	}

	/**
	 * Sets the sscta.
	 *
	 * @param sscta the new sscta
	 */
	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	/**
	 * Gets the ssscta.
	 *
	 * @return the ssscta
	 */
	public String getSsscta() {
		return this.ssscta;
	}

	/**
	 * Sets the ssscta.
	 *
	 * @param ssscta the new ssscta
	 */
	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	/**
	 * Gets the sssscta.
	 *
	 * @return the sssscta
	 */
	public String getSssscta() {
		return this.sssscta;
	}

	/**
	 * Sets the sssscta.
	 *
	 * @param sssscta the new sssscta
	 */
	public void setSssscta(String sssscta) {
		this.sssscta = sssscta;
	}

	/**
	 * Gets the tippag.
	 *
	 * @return the tippag
	 */
	public int getTippag() {
		return this.tippag;
	}

	/**
	 * Sets the tippag.
	 *
	 * @param tippag the new tippag
	 */
	public void setTippag(int tippag) {
		this.tippag = tippag;
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

	/**
	 * Gets the wtipo.
	 *
	 * @return the wtipo
	 */
	public String getWtipo() {
		return this.wtipo;
	}

	/**
	 * Sets the wtipo.
	 *
	 * @param wtipo the new wtipo
	 */
	public void setWtipo(String wtipo) {
		this.wtipo = wtipo;
	}

}