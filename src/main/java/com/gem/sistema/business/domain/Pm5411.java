package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM5411 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm5411.findAll", query = "SELECT p FROM Pm5411 p")
public class Pm5411 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The capturo. */
	private String capturo;
	
	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;
	
	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;
	
	/** The idsector. */
	private int idsector;
	
	/** The mensual. */
	private Integer mensual;
	
	/** The userid. */
	private String userid;
	
	/** The masimun. */
	private BigDecimal masimun;
	
	/** The mminmes. */
	private BigDecimal mminmes;
	
	/** The obyacmes. */
	private Integer obyacmes;
	
	/** The mautobyac. */
	private BigDecimal mautobyac;
	
	/** The mejemes. */
	private BigDecimal mejemes;
	
	/** The mautob. */
	private BigDecimal mautob;
	
	/** The sesiones. */
	private Integer sesiones;
	
	/** The partciu. */
	private String partciu;
	
	/** The observa. */
	private String observa;

	/** The partciu other. */
	@Transient
	private String partciuOther;

	/**
	 * Instantiates a new pm 5411.
	 */
	public Pm5411() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return capturo;
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
		return feccap;
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
		return idRef;
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
		return idsector;
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
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return mensual;
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
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
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
	 * Gets the masimun.
	 *
	 * @return the masimun
	 */
	public BigDecimal getMasimun() {
		return masimun;
	}

	/**
	 * Sets the masimun.
	 *
	 * @param masimun the new masimun
	 */
	public void setMasimun(BigDecimal masimun) {
		this.masimun = masimun;
	}

	/**
	 * Gets the mminmes.
	 *
	 * @return the mminmes
	 */
	public BigDecimal getMminmes() {
		return mminmes;
	}

	/**
	 * Sets the mminmes.
	 *
	 * @param mminmes the new mminmes
	 */
	public void setMminmes(BigDecimal mminmes) {
		this.mminmes = mminmes;
	}

	/**
	 * Gets the obyacmes.
	 *
	 * @return the obyacmes
	 */
	public Integer getObyacmes() {
		return obyacmes;
	}

	/**
	 * Sets the obyacmes.
	 *
	 * @param obyacmes the new obyacmes
	 */
	public void setObyacmes(Integer obyacmes) {
		this.obyacmes = obyacmes;
	}

	/**
	 * Gets the mautobyac.
	 *
	 * @return the mautobyac
	 */
	public BigDecimal getMautobyac() {
		return mautobyac;
	}

	/**
	 * Sets the mautobyac.
	 *
	 * @param mautobyac the new mautobyac
	 */
	public void setMautobyac(BigDecimal mautobyac) {
		this.mautobyac = mautobyac;
	}

	/**
	 * Gets the mejemes.
	 *
	 * @return the mejemes
	 */
	public BigDecimal getMejemes() {
		return mejemes;
	}

	/**
	 * Sets the mejemes.
	 *
	 * @param mejemes the new mejemes
	 */
	public void setMejemes(BigDecimal mejemes) {
		this.mejemes = mejemes;
	}

	/**
	 * Gets the mautob.
	 *
	 * @return the mautob
	 */
	public BigDecimal getMautob() {
		return mautob;
	}

	/**
	 * Sets the mautob.
	 *
	 * @param mautob the new mautob
	 */
	public void setMautob(BigDecimal mautob) {
		this.mautob = mautob;
	}

	/**
	 * Gets the sesiones.
	 *
	 * @return the sesiones
	 */
	public Integer getSesiones() {
		return sesiones;
	}

	/**
	 * Sets the sesiones.
	 *
	 * @param sesiones the new sesiones
	 */
	public void setSesiones(Integer sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * Gets the partciu.
	 *
	 * @return the partciu
	 */
	public String getPartciu() {
		return partciu;
	}

	/**
	 * Sets the partciu.
	 *
	 * @param partciu the new partciu
	 */
	public void setPartciu(String partciu) {
		this.partciu = partciu;
	}

	/**
	 * Gets the observa.
	 *
	 * @return the observa
	 */
	public String getObserva() {
		return observa;
	}

	/**
	 * Sets the observa.
	 *
	 * @param observa the new observa
	 */
	public void setObserva(String observa) {
		this.observa = observa;
	}

	/**
	 * Gets the partciu other.
	 *
	 * @return the partciu other
	 */
	public String getPartciuOther() {
		return partciuOther;
	}

	/**
	 * Sets the partciu other.
	 *
	 * @param partciuOther the new partciu other
	 */
	public void setPartciuOther(String partciuOther) {
		this.partciuOther = partciuOther;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pm5411 other = (Pm5411) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}