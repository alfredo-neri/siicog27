package com.gem.sistema.business.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class Poliza.
 */
@Entity
@Table(name = "polidex")
public class Poliza extends AbstractEntity {

	/** The tippol. */
	@Column(name = "tippol")
	private String tippol;

	/** The mespol. */
	@Column(name = "mespol")
	private Integer mespol;

	/** The conpol. */
	@Column(name = "conpol")
	private Integer conpol;

	/** The renpol. */
	@Column(name = "renpol")
	private BigDecimal renpol = BigDecimal.ZERO;

	/** The cuenta. */
	@Column(name = "cuenta")
	private String cuenta;

	/** The scuenta. */
	@Column(name = "scta")
	private String scuenta;

	/** The sscuenta. */
	@Column(name = "sscta")
	private String sscuenta;

	/** The ssscuenta. */
	@Column(name = "ssscta")
	private String ssscuenta;

	/** The sssscuenta. */
	@Column(name = "sssscta")
	private String sssscuenta;

	/** The anopol. */
	@Column(name = "anopol")
	private Integer anopol;

	/** The clvcto. */
	@Column(name = "clvcto")
	private String clvcto;

	/** The error code. */
	@Transient
	private int errorCode;

	/** The msg error. */
	@Transient
	private String msgError;

	/** The conpol string. */
	@Transient
	private String conpolString;
	
	/** The file name. */
	@Transient
	private String fileName;
	
	/** The path name. */
	@Transient
	private String pathName;

	/**
	 * Gets the path name.
	 *
	 * @return the path name
	 */
	public String getPathName() {
		return pathName;
	}

	/**
	 * Sets the path name.
	 *
	 * @param pathName the new path name
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Instantiates a new poliza.
	 */
	public Poliza() {
		super();
	}

	/**
	 * Instantiates a new poliza.
	 *
	 * @param tippol the tippol
	 * @param mespol the mespol
	 * @param conpol the conpol
	 * @param renpol the renpol
	 * @param cuenta the cuenta
	 * @param scuenta the scuenta
	 * @param sscuenta the sscuenta
	 * @param ssscuenta the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param anopol the anopol
	 * @param clvcto the clvcto
	 */
	public Poliza(String tippol, Integer mespol, Integer conpol, BigDecimal renpol, String cuenta, String scuenta,
			String sscuenta, String ssscuenta, String sssscuenta, Integer anopol, String clvcto) {
		super();
		this.tippol = tippol;
		this.mespol = mespol;
		this.conpol = conpol;
		this.renpol = renpol;
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
		this.sssscuenta = sssscuenta;
		this.anopol = anopol;
		this.clvcto = clvcto;
	}

	/**
	 * Gets the tippol.
	 *
	 * @return the tippol
	 */
	public String getTippol() {
		return tippol;
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
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public Integer getMespol() {
		return mespol;
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
	 * Gets the conpol.
	 *
	 * @return the conpol
	 */
	public Integer getConpol() {
		return conpol;
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
	 * Gets the renpol.
	 *
	 * @return the renpol
	 */
	public BigDecimal getRenpol() {
		return renpol;
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
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
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
	 * Gets the scuenta.
	 *
	 * @return the scuenta
	 */
	public String getScuenta() {
		return scuenta;
	}

	/**
	 * Sets the scuenta.
	 *
	 * @param scuenta the new scuenta
	 */
	public void setScuenta(String scuenta) {
		this.scuenta = scuenta;
	}

	/**
	 * Gets the sscuenta.
	 *
	 * @return the sscuenta
	 */
	public String getSscuenta() {
		return sscuenta;
	}

	/**
	 * Sets the sscuenta.
	 *
	 * @param sscuenta the new sscuenta
	 */
	public void setSscuenta(String sscuenta) {
		this.sscuenta = sscuenta;
	}

	/**
	 * Gets the ssscuenta.
	 *
	 * @return the ssscuenta
	 */
	public String getSsscuenta() {
		return ssscuenta;
	}

	/**
	 * Sets the ssscuenta.
	 *
	 * @param ssscuenta the new ssscuenta
	 */
	public void setSsscuenta(String ssscuenta) {
		this.ssscuenta = ssscuenta;
	}

	/**
	 * Gets the sssscuenta.
	 *
	 * @return the sssscuenta
	 */
	public String getSssscuenta() {
		return sssscuenta;
	}

	/**
	 * Sets the sssscuenta.
	 *
	 * @param sssscuenta the new sssscuenta
	 */
	public void setSssscuenta(String sssscuenta) {
		this.sssscuenta = sssscuenta;
	}

	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public Integer getAnopol() {
		return anopol;
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
	 * Gets the clvcto.
	 *
	 * @return the clvcto
	 */
	public String getClvcto() {
		return clvcto;
	}

	/**
	 * Sets the clvcto.
	 *
	 * @param clvcto the new clvcto
	 */
	public void setClvcto(String clvcto) {
		this.clvcto = clvcto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the msg error.
	 *
	 * @return the msgError
	 */
	public String getMsgError() {
		return msgError;
	}

	/**
	 * Sets the msg error.
	 *
	 * @param msgError            the msgError to set
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	/**
	 * Gets the conpol string.
	 *
	 * @return the conpolString
	 */
	public String getConpolString() {
		return conpolString;
	}

	/**
	 * Sets the conpol string.
	 *
	 * @param conpolString            the conpolString to set
	 */
	public void setConpolString(String conpolString) {
		this.conpolString = conpolString;
	}

}
