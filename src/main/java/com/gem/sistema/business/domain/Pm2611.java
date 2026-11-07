package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM2611 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm2611.findAll", query="SELECT p FROM Pm2611 p")
public class Pm2611 extends AbstractEntity implements Serializable {
	
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
	private int idsector;

	/** The obstot. */
	private String obstot;

	/** The obstra. */
	private String obstra;

	/** The semes. */
	private Integer semes;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/** The voltot. */
	private BigDecimal voltot = BigDecimal.ZERO;

	/** The voltra. */
	private BigDecimal voltra = BigDecimal.ZERO;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;
	

	/**
	 * Instantiates a new pm 2611.
	 */
	public Pm2611() {
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
	 * Gets the obstot.
	 *
	 * @return the obstot
	 */
	public String getObstot() {
		return this.obstot;
	}

	/**
	 * Sets the obstot.
	 *
	 * @param obstot the new obstot
	 */
	public void setObstot(String obstot) {
		this.obstot = obstot;
	}

	/**
	 * Gets the obstra.
	 *
	 * @return the obstra
	 */
	public String getObstra() {
		return this.obstra;
	}

	/**
	 * Sets the obstra.
	 *
	 * @param obstra the new obstra
	 */
	public void setObstra(String obstra) {
		this.obstra = obstra;
	}

	/**
	 * Gets the semes.
	 *
	 * @return the semes
	 */
	public Integer getSemes() {
		return this.semes;
	}

	/**
	 * Sets the semes.
	 *
	 * @param semes the new semes
	 */
	public void setSemes(Integer semes) {
		this.semes = semes;
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
	 * Gets the voltot.
	 *
	 * @return the voltot
	 */
	public BigDecimal getVoltot() {
		return this.voltot;
	}

	/**
	 * Sets the voltot.
	 *
	 * @param voltot the new voltot
	 */
	public void setVoltot(BigDecimal voltot) {
		this.voltot = voltot;
	}

	/**
	 * Gets the voltra.
	 *
	 * @return the voltra
	 */
	public BigDecimal getVoltra() {
		return this.voltra;
	}

	/**
	 * Sets the voltra.
	 *
	 * @param voltra the new voltra
	 */
	public void setVoltra(BigDecimal voltra) {
		this.voltra = voltra;
	}

	/**
	 * Checks if is b guardar.
	 *
	 * @return true, if is b guardar
	 */
	public boolean isbGuardar() {
		return bGuardar;
	}

	/**
	 * Sets the b guardar.
	 *
	 * @param bGuardar the new b guardar
	 */
	public void setbGuardar(boolean bGuardar) {
		this.bGuardar = bGuardar;
	}
	
	

}