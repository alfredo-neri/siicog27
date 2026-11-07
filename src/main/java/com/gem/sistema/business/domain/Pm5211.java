package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM5211 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm5211.findAll", query="SELECT p FROM Pm5211 p")
public class Pm5211 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The acc. */
	private BigDecimal acc;

	/** The acuacc. */
	private BigDecimal acuacc;

	/** The acuapmej. */
	private BigDecimal acuapmej;

	/** The acuaprov. */
	private BigDecimal acuaprov;

	/** The acuder. */
	private BigDecimal acuder;

	/** The acuimp. */
	private BigDecimal acuimp;

	/** The acuipf. */
	private BigDecimal acuipf;

	/** The acuipr. */
	private BigDecimal acuipr;

	/** The acuprod. */
	private BigDecimal acuprod;

	/** The acuting. */
	private BigDecimal acuting;

	/** The apmej. */
	private BigDecimal apmej;

	/** The aprov. */
	private BigDecimal aprov;

	/** The capturo. */
	private String capturo;

	/** The der. */
	private BigDecimal der;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The imp. */
	private BigDecimal imp;

	/** The ipf. */
	private BigDecimal ipf;

	/** The ipr. */
	private BigDecimal ipr;

	/** The mensual. */
	private Integer mensual;

	/** The obsgral. */
	private String obsgral;

	/** The prod. */
	private BigDecimal prod;

	/** The ting. */
	private BigDecimal ting;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardra. */
	@Transient
	private boolean bGuardra;

	/**
	 * Instantiates a new pm 5211.
	 */
	public Pm5211() {
	}

	/**
	 * Gets the acc.
	 *
	 * @return the acc
	 */
	public BigDecimal getAcc() {
		return this.acc;
	}

	/**
	 * Sets the acc.
	 *
	 * @param acc the new acc
	 */
	public void setAcc(BigDecimal acc) {
		this.acc = acc;
	}

	/**
	 * Gets the acuacc.
	 *
	 * @return the acuacc
	 */
	public BigDecimal getAcuacc() {
		return this.acuacc;
	}

	/**
	 * Sets the acuacc.
	 *
	 * @param acuacc the new acuacc
	 */
	public void setAcuacc(BigDecimal acuacc) {
		this.acuacc = acuacc;
	}

	/**
	 * Gets the acuapmej.
	 *
	 * @return the acuapmej
	 */
	public BigDecimal getAcuapmej() {
		return this.acuapmej;
	}

	/**
	 * Sets the acuapmej.
	 *
	 * @param acuapmej the new acuapmej
	 */
	public void setAcuapmej(BigDecimal acuapmej) {
		this.acuapmej = acuapmej;
	}

	/**
	 * Gets the acuaprov.
	 *
	 * @return the acuaprov
	 */
	public BigDecimal getAcuaprov() {
		return this.acuaprov;
	}

	/**
	 * Sets the acuaprov.
	 *
	 * @param acuaprov the new acuaprov
	 */
	public void setAcuaprov(BigDecimal acuaprov) {
		this.acuaprov = acuaprov;
	}

	/**
	 * Gets the acuder.
	 *
	 * @return the acuder
	 */
	public BigDecimal getAcuder() {
		return this.acuder;
	}

	/**
	 * Sets the acuder.
	 *
	 * @param acuder the new acuder
	 */
	public void setAcuder(BigDecimal acuder) {
		this.acuder = acuder;
	}

	/**
	 * Gets the acuimp.
	 *
	 * @return the acuimp
	 */
	public BigDecimal getAcuimp() {
		return this.acuimp;
	}

	/**
	 * Sets the acuimp.
	 *
	 * @param acuimp the new acuimp
	 */
	public void setAcuimp(BigDecimal acuimp) {
		this.acuimp = acuimp;
	}

	/**
	 * Gets the acuipf.
	 *
	 * @return the acuipf
	 */
	public BigDecimal getAcuipf() {
		return this.acuipf;
	}

	/**
	 * Sets the acuipf.
	 *
	 * @param acuipf the new acuipf
	 */
	public void setAcuipf(BigDecimal acuipf) {
		this.acuipf = acuipf;
	}

	/**
	 * Gets the acuipr.
	 *
	 * @return the acuipr
	 */
	public BigDecimal getAcuipr() {
		return this.acuipr;
	}

	/**
	 * Sets the acuipr.
	 *
	 * @param acuipr the new acuipr
	 */
	public void setAcuipr(BigDecimal acuipr) {
		this.acuipr = acuipr;
	}

	/**
	 * Gets the acuprod.
	 *
	 * @return the acuprod
	 */
	public BigDecimal getAcuprod() {
		return this.acuprod;
	}

	/**
	 * Sets the acuprod.
	 *
	 * @param acuprod the new acuprod
	 */
	public void setAcuprod(BigDecimal acuprod) {
		this.acuprod = acuprod;
	}

	/**
	 * Gets the acuting.
	 *
	 * @return the acuting
	 */
	public BigDecimal getAcuting() {
		return this.acuting;
	}

	/**
	 * Sets the acuting.
	 *
	 * @param acuting the new acuting
	 */
	public void setAcuting(BigDecimal acuting) {
		this.acuting = acuting;
	}

	/**
	 * Gets the apmej.
	 *
	 * @return the apmej
	 */
	public BigDecimal getApmej() {
		return this.apmej;
	}

	/**
	 * Sets the apmej.
	 *
	 * @param apmej the new apmej
	 */
	public void setApmej(BigDecimal apmej) {
		this.apmej = apmej;
	}

	/**
	 * Gets the aprov.
	 *
	 * @return the aprov
	 */
	public BigDecimal getAprov() {
		return this.aprov;
	}

	/**
	 * Sets the aprov.
	 *
	 * @param aprov the new aprov
	 */
	public void setAprov(BigDecimal aprov) {
		this.aprov = aprov;
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
	 * Gets the der.
	 *
	 * @return the der
	 */
	public BigDecimal getDer() {
		return this.der;
	}

	/**
	 * Sets the der.
	 *
	 * @param der the new der
	 */
	public void setDer(BigDecimal der) {
		this.der = der;
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
	 * Gets the imp.
	 *
	 * @return the imp
	 */
	public BigDecimal getImp() {
		return this.imp;
	}

	/**
	 * Sets the imp.
	 *
	 * @param imp the new imp
	 */
	public void setImp(BigDecimal imp) {
		this.imp = imp;
	}

	/**
	 * Gets the ipf.
	 *
	 * @return the ipf
	 */
	public BigDecimal getIpf() {
		return this.ipf;
	}

	/**
	 * Sets the ipf.
	 *
	 * @param ipf the new ipf
	 */
	public void setIpf(BigDecimal ipf) {
		this.ipf = ipf;
	}

	/**
	 * Gets the ipr.
	 *
	 * @return the ipr
	 */
	public BigDecimal getIpr() {
		return this.ipr;
	}

	/**
	 * Sets the ipr.
	 *
	 * @param ipr the new ipr
	 */
	public void setIpr(BigDecimal ipr) {
		this.ipr = ipr;
	}

	/**
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return this.mensual;
	}

	/**
	 * Sets the mensual.
	 *
	 * @param mensual the new mensual
	 */
	public void setMensual(Integer mensual) {
		this.mensual = mensual;
	}

	/**
	 * Gets the obsgral.
	 *
	 * @return the obsgral
	 */
	public String getObsgral() {
		return this.obsgral;
	}

	/**
	 * Sets the obsgral.
	 *
	 * @param obsgral the new obsgral
	 */
	public void setObsgral(String obsgral) {
		this.obsgral = obsgral;
	}

	/**
	 * Gets the prod.
	 *
	 * @return the prod
	 */
	public BigDecimal getProd() {
		return this.prod;
	}

	/**
	 * Sets the prod.
	 *
	 * @param prod the new prod
	 */
	public void setProd(BigDecimal prod) {
		this.prod = prod;
	}

	/**
	 * Gets the ting.
	 *
	 * @return the ting
	 */
	public BigDecimal getTing() {
		return this.ting;
	}

	/**
	 * Sets the ting.
	 *
	 * @param ting the new ting
	 */
	public void setTing(BigDecimal ting) {
		this.ting = ting;
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
	 * Checks if is b guardra.
	 *
	 * @return true, if is b guardra
	 */
	public boolean isbGuardra() {
		return bGuardra;
	}

	/**
	 * Sets the b guardra.
	 *
	 * @param bGuardra the new b guardra
	 */
	public void setbGuardra(boolean bGuardra) {
		this.bGuardra = bGuardra;
	}
	

}