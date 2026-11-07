package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notEmpty;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Catprv.
 */
@Entity
@Table(name = "catprv")
public class Catprv extends AbstractEntity {

	/** The Constant MAX_LENGTH_nomprv. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomprv = 150;
	
	/** The Constant MAX_LENGTH_tipprv. */
	@IgnoredQuery
	static final int MAX_LENGTH_tipprv = 4;
	
	/** The Constant MAX_LENGTH_capprv. */
	@IgnoredQuery
	static final int MAX_LENGTH_capprv = 16;

	/** The clvprv. */
	@Column(name = "clvprv")
	private Integer clvprv;

	/** The nomprv. */
	@Column(name = "nomprv", nullable = false, length = MAX_LENGTH_nomprv)
	private String nomprv;

	/** The tipprv. */
	@Column(name = "tipprv", length = MAX_LENGTH_tipprv)
	private String tipprv;

	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap;

	/** The capprv. */
	@Column(name = "capprv", length = MAX_LENGTH_capprv)
	private String capprv;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Integer idsector;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new catprv.
	 *
	 * @param clvprv the clvprv
	 * @param nomprv the nomprv
	 * @param tipprv the tipprv
	 * @param feccap the feccap
	 * @param capprv the capprv
	 */
	public Catprv(Integer clvprv, String nomprv, String tipprv, Date feccap, String capprv) {
		this.clvprv = clvprv;
		this.nomprv = nomprv;
		this.tipprv = tipprv;
		this.feccap = feccap;
		this.capprv = capprv;
	}

	/**
	 * Instantiates a new catprv.
	 */
	public Catprv() {

	}

	/**
	 * Gets the clvprv.
	 *
	 * @return the clvprv
	 */
	@ColumnFile(indexColumn = 1)
	public Integer getClvprv() {
		return clvprv;
	}

	/**
	 * Sets the clvprv.
	 *
	 * @param clvprv the new clvprv
	 */
	public void setClvprv(Integer clvprv) {
		this.clvprv = clvprv;
	}

	/**
	 * Gets the nomprv.
	 *
	 * @return the nomprv
	 */
	@ColumnFile(indexColumn = 3)
	public String getNomprv() {
		return nomprv;
	}

	/**
	 * Sets the nomprv.
	 *
	 * @param nomprv the new nomprv
	 */
	public void setNomprv(String nomprv) {
		this.nomprv = nomprv;
	}

	/**
	 * Gets the tipprv.
	 *
	 * @return the tipprv
	 */
	@ColumnFile(indexColumn = 2)
	public String getTipprv() {
		return tipprv;
	}

	/**
	 * Sets the tipprv.
	 *
	 * @param tipprv the new tipprv
	 */
	public void setTipprv(String tipprv) {
		this.tipprv = tipprv;
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
	 * Gets the capprv.
	 *
	 * @return the capprv
	 */
	public String getCapprv() {
		return capprv;
	}

	/**
	 * Sets the capprv.
	 *
	 * @param capprv the new capprv
	 */
	public void setCapprv(String capprv) {
		this.capprv = capprv;
	}

	/**
	 * Require valid nomprv.
	 *
	 * @param nomprv the nomprv
	 */
	public void requireValidNomprv(String nomprv) {
		notNull(nomprv, "Clave no puede ser nulo.");
		notEmpty(nomprv, "Clave no puede ser vacio.");
		isTrue(nomprv.length() <= MAX_LENGTH_nomprv, "La máxima longitud del nombre es de <%d> caracteres.",
				MAX_LENGTH_nomprv);
	}

	/**
	 * Require valid tipprv.
	 *
	 * @param tipprv the tipprv
	 */
	public void requireValidTipprv(String tipprv) {
		notNull(tipprv, "Tipo no puede ser nulo.");
		notEmpty(tipprv, "Tipo no puede ser vacio.");
		isTrue(tipprv.length() <= MAX_LENGTH_tipprv, "La máxima longitud del tipo es de <%d> caracteres.",
				MAX_LENGTH_tipprv);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("clvprv", this.clvprv)
				.append("nomprv", this.nomprv).append("tipprv", this.tipprv).append("feccap", this.feccap)
				.append("capprv", this.capprv).toString();
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
	public Integer getIdsector() {
		return idsector;
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

}
