package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the CATDEP database table.
 * 
 */
@Entity
@NamedQuery(name = "Catdep.findAll", query = "SELECT c FROM Catdep c")
public class Catdep extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	@IgnoredQuery
	private static final long serialVersionUID = 1L;
	
	/** The Constant MAX_LENGTH_clvdep. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvdep = 20;
	
	/** The Constant MAX_LENGTH_nomdep. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomdep = 150;
	
	/** The Constant MAX_LENGTH_capdep. */
	@IgnoredQuery
	static final int MAX_LENGTH_capdep = 16;
	
	/** The Constant MAX_LENGTH_ultniv. */
	@IgnoredQuery
	static final int MAX_LENGTH_ultniv = 2;

	/** The capdep. */
	private String capdep;

	/** The cargo E. */
	@Column(name = "CARGO_E")
	private String cargoE;

	/** The cargo T. */
	@Column(name = "CARGO_T")
	private String cargoT;

	/** The clvdep. */
	@Column(name = "clvdep", length = MAX_LENGTH_clvdep)
	private String clvdep;

	/** The elaboro. */
	private String elaboro;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;
	
	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Integer idsector;

	/** The lautorizo. */
	private String lautorizo;

	/** The lelaboro. */
	private String lelaboro;

	/** The lreviso. */
	private String lreviso;

	/** The ltitulo 1. */
	private String ltitulo1;

	/** The ltitulo 2. */
	private String ltitulo2;

	/** The nautorizo. */
	private String nautorizo;

	/** The nelaboro. */
	private String nelaboro;

	/** The nomdep. */
	@Column(name = "nomdep", length = MAX_LENGTH_nomdep)
	private String nomdep;

	/** The nreviso. */
	private String nreviso;

	/** The ntitulo 1. */
	private String ntitulo1;

	/** The ntitulo 2. */
	private String ntitulo2;

	/** The titular. */
	private String titular;

	/** The ultniv. */
	@Column(name = "ultniv", length = MAX_LENGTH_ultniv)
	private String ultniv;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/** The clave dgm. */
	@Transient
	private String claveDgm;

	/** The clave daa. */
	@Transient
	private String claveDaa;

	/** The not delete. */
	@Transient
	@IgnoredQuery
	private Boolean notDelete = Boolean.TRUE;

	/**
	 * Instantiates a new catdep.
	 */
	public Catdep() {
	}

	/**
	 * Gets the capdep.
	 *
	 * @return the capdep
	 */
	public String getCapdep() {
		return this.capdep;
	}

	/**
	 * Sets the capdep.
	 *
	 * @param capdep the new capdep
	 */
	public void setCapdep(String capdep) {
		this.capdep = capdep;
	}

	/**
	 * Gets the cargo E.
	 *
	 * @return the cargo E
	 */
	public String getCargoE() {
		return this.cargoE;
	}

	/**
	 * Sets the cargo E.
	 *
	 * @param cargoE the new cargo E
	 */
	public void setCargoE(String cargoE) {
		this.cargoE = cargoE;
	}

	/**
	 * Gets the cargo T.
	 *
	 * @return the cargo T
	 */
	public String getCargoT() {
		return this.cargoT;
	}

	/**
	 * Sets the cargo T.
	 *
	 * @param cargoT the new cargo T
	 */
	public void setCargoT(String cargoT) {
		this.cargoT = cargoT;
	}

	/**
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
	@ColumnFile(indexColumn = 1)
	public String getClvdep() {
		return this.clvdep;
	}

	/**
	 * Sets the clvdep.
	 *
	 * @param clvdep the new clvdep
	 */
	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
	}

	/**
	 * Gets the elaboro.
	 *
	 * @return the elaboro
	 */
	public String getElaboro() {
		return this.elaboro;
	}

	/**
	 * Sets the elaboro.
	 *
	 * @param elaboro the new elaboro
	 */
	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
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
	 * Gets the lautorizo.
	 *
	 * @return the lautorizo
	 */
	public String getLautorizo() {
		return this.lautorizo;
	}

	/**
	 * Sets the lautorizo.
	 *
	 * @param lautorizo the new lautorizo
	 */
	public void setLautorizo(String lautorizo) {
		this.lautorizo = lautorizo;
	}

	/**
	 * Gets the lelaboro.
	 *
	 * @return the lelaboro
	 */
	public String getLelaboro() {
		return this.lelaboro;
	}

	/**
	 * Sets the lelaboro.
	 *
	 * @param lelaboro the new lelaboro
	 */
	public void setLelaboro(String lelaboro) {
		this.lelaboro = lelaboro;
	}

	/**
	 * Gets the lreviso.
	 *
	 * @return the lreviso
	 */
	public String getLreviso() {
		return this.lreviso;
	}

	/**
	 * Sets the lreviso.
	 *
	 * @param lreviso the new lreviso
	 */
	public void setLreviso(String lreviso) {
		this.lreviso = lreviso;
	}

	/**
	 * Gets the ltitulo 1.
	 *
	 * @return the ltitulo 1
	 */
	public String getLtitulo1() {
		return this.ltitulo1;
	}

	/**
	 * Sets the ltitulo 1.
	 *
	 * @param ltitulo1 the new ltitulo 1
	 */
	public void setLtitulo1(String ltitulo1) {
		this.ltitulo1 = ltitulo1;
	}

	/**
	 * Gets the ltitulo 2.
	 *
	 * @return the ltitulo 2
	 */
	public String getLtitulo2() {
		return this.ltitulo2;
	}

	/**
	 * Sets the ltitulo 2.
	 *
	 * @param ltitulo2 the new ltitulo 2
	 */
	public void setLtitulo2(String ltitulo2) {
		this.ltitulo2 = ltitulo2;
	}

	/**
	 * Gets the nautorizo.
	 *
	 * @return the nautorizo
	 */
	public String getNautorizo() {
		return this.nautorizo;
	}

	/**
	 * Sets the nautorizo.
	 *
	 * @param nautorizo the new nautorizo
	 */
	public void setNautorizo(String nautorizo) {
		this.nautorizo = nautorizo;
	}

	/**
	 * Gets the nelaboro.
	 *
	 * @return the nelaboro
	 */
	public String getNelaboro() {
		return this.nelaboro;
	}

	/**
	 * Sets the nelaboro.
	 *
	 * @param nelaboro the new nelaboro
	 */
	public void setNelaboro(String nelaboro) {
		this.nelaboro = nelaboro;
	}

	/**
	 * Gets the nomdep.
	 *
	 * @return the nomdep
	 */
	@ColumnFile(indexColumn = 4)
	public String getNomdep() {
		return this.nomdep;
	}

	/**
	 * Sets the nomdep.
	 *
	 * @param nomdep the new nomdep
	 */
	public void setNomdep(String nomdep) {
		this.nomdep = nomdep;
	}

	/**
	 * Gets the nreviso.
	 *
	 * @return the nreviso
	 */
	public String getNreviso() {
		return this.nreviso;
	}

	/**
	 * Sets the nreviso.
	 *
	 * @param nreviso the new nreviso
	 */
	public void setNreviso(String nreviso) {
		this.nreviso = nreviso;
	}

	/**
	 * Gets the ntitulo 1.
	 *
	 * @return the ntitulo 1
	 */
	public String getNtitulo1() {
		return this.ntitulo1;
	}

	/**
	 * Sets the ntitulo 1.
	 *
	 * @param ntitulo1 the new ntitulo 1
	 */
	public void setNtitulo1(String ntitulo1) {
		this.ntitulo1 = ntitulo1;
	}

	/**
	 * Gets the ntitulo 2.
	 *
	 * @return the ntitulo 2
	 */
	public String getNtitulo2() {
		return this.ntitulo2;
	}

	/**
	 * Sets the ntitulo 2.
	 *
	 * @param ntitulo2 the new ntitulo 2
	 */
	public void setNtitulo2(String ntitulo2) {
		this.ntitulo2 = ntitulo2;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return this.titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the ultniv.
	 *
	 * @return the ultniv
	 */
	public String getUltniv() {
		return this.ultniv;
	}

	/**
	 * Sets the ultniv.
	 *
	 * @param ultniv the new ultniv
	 */
	public void setUltniv(String ultniv) {
		this.ultniv = ultniv;
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
	 * Gets the clave dgm.
	 *
	 * @return the clave dgm
	 */
	public String getClaveDgm() {
		return claveDgm;
	}

	/**
	 * Sets the clave dgm.
	 *
	 * @param claveDgm            the claveDgm to set
	 */
	public void setClaveDgm(String claveDgm) {
		this.claveDgm = claveDgm;
	}

	/**
	 * Gets the clave daa.
	 *
	 * @return the claveDaa
	 */
	public String getClaveDaa() {
		return claveDaa;
	}

	/**
	 * Sets the clave daa.
	 *
	 * @param claveDaa            the claveDaa to set
	 */
	public void setClaveDaa(String claveDaa) {
		this.claveDaa = claveDaa;
	}

	/**
	 * Require valid clvdep.
	 *
	 * @param nomflu the nomflu
	 */
	public void requireValidClvdep(String nomflu) {
		isTrue(clvdep.length() <= MAX_LENGTH_clvdep, "La máxima longitud de la clave es de <%d> caracteres.",
				MAX_LENGTH_clvdep);
	}

	/**
	 * Require valid nomdep.
	 *
	 * @param nomdep the nomdep
	 */
	public void requireValidNomdep(String nomdep) {
		isTrue(nomdep.length() <= MAX_LENGTH_nomdep, "La máxima longitud del nombre es de <%d> caracteres.",
				MAX_LENGTH_nomdep);
	}

	/**
	 * Require valid capdep.
	 *
	 * @param capdep the capdep
	 */
	public void requireValidCapdep(String capdep) {
		isTrue(capdep.length() <= MAX_LENGTH_capdep,
				"La máxima longitud de la persona que captura es de <%d> caracteres.", MAX_LENGTH_capdep);
	}

	/**
	 * Require valid ultniv.
	 *
	 * @param ultniv the ultniv
	 */
	public void requireValidUltniv(String ultniv) {
		isTrue(ultniv.length() <= MAX_LENGTH_ultniv, "La máxima longitud del campo que captura es de <%d> caracteres.",
				MAX_LENGTH_ultniv);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("index", getIndex()).append("clvdep", this.clvdep)
				.append("clvdgm", this.claveDgm).append("clvdaa", this.claveDaa).append("nomdep", this.nomdep)
				.append("capdep", this.capdep).append("feccap", this.feccap).append("ultniv", this.ultniv).toString();
	}

	/**
	 * Gets the clave dgm exp.
	 *
	 * @return the clave dgm exp
	 */
	@ColumnFile(indexColumn = 2)
	public String getClaveDgmExp() {
		if (this.getClvdep() != null && this.getClvdep().length() >= 3) {
			return this.getClvdep().substring(0, 3);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Gets the clave daa exp.
	 *
	 * @return the clave daa exp
	 */
	@ColumnFile(indexColumn = 3)
	public String getClaveDaaExp() {
		if (this.getClvdep() != null && this.getClvdep().length() >= 6) {
			return this.getClvdep().substring(3, 6);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Gets the not delete.
	 *
	 * @return the not delete
	 */
	public Boolean getNotDelete() {
		return notDelete;
	}

	/**
	 * Sets the not delete.
	 *
	 * @param notDelete the new not delete
	 */
	public void setNotDelete(Boolean notDelete) {
		this.notDelete = notDelete;
	}

}