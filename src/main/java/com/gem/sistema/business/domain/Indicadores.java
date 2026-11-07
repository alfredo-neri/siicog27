package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Indicadores.
 */
@Entity
@Table(name = "indicadores")
public class Indicadores extends AbstractEntity {

	/** The Constant MAX_LENGTH_nomind. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomind = 600;
	
	/** The Constant MAX_LENGTH_clvDep. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvDep = 12;
	
	/** The Constant MAX_LENGTH_clvFun. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvFun = 12;
	
	/** The Constant MAX_LENGTH_clvFin. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvFin = 6;
	
	/** The Constant MAX_LENGTH_periodo. */
	@IgnoredQuery
	static final int MAX_LENGTH_periodo = 2;
	
	/** The Constant MAX_LENGTH_descripcion. */
	@IgnoredQuery
	static final int MAX_LENGTH_descripcion = 700;

	/** The cveind. */
	@Column(name = "cveind")
	private Integer cveind;

	/** The nomind. */
	@Column(name = "nomind", length = MAX_LENGTH_nomind)
	private String nomind;

	/** The clv dep. */
	@Column(name = "clvDep", length = MAX_LENGTH_clvDep)
	private String clvDep;

	/** The clv fun. */
	@Column(name = "clvFun", length = MAX_LENGTH_clvFun)
	private String clvFun;

	/** The clv fin. */
	@Column(name = "clvFin", length = MAX_LENGTH_clvFin)
	private String clvFin;

	/** The periodo. */
	@Column(name = "periodo", length = MAX_LENGTH_periodo)
	private String periodo;

	/** The descripcion. */
	@Column(name = "descripcion", length = MAX_LENGTH_descripcion)
	private String descripcion;

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
	 * Require valid nomind.
	 *
	 * @param nomind the nomind
	 */
	public void requireValidNomind(String nomind) {
		isTrue(nomind.length() <= MAX_LENGTH_nomind, "La máxima longitud de nomind es de <%d> caracteres.",
				MAX_LENGTH_nomind);
	}

	/**
	 * Require validclv dep.
	 *
	 * @param clvDep the clv dep
	 */
	public void requireValidclvDep(String clvDep) {
		isTrue(clvDep.length() <= MAX_LENGTH_clvDep, "La máxima longitud de clvDep es de <%d> caracteres.",
				MAX_LENGTH_clvDep);
	}

	/**
	 * Require valid clv fun.
	 *
	 * @param clvFun the clv fun
	 */
	public void requireValidClvFun(String clvFun) {
		isTrue(clvFun.length() <= MAX_LENGTH_clvFun, "La máxima longitud de clvFun es de <%d> caracteres.",
				MAX_LENGTH_clvFun);
	}

	/**
	 * Require valid clv fin.
	 *
	 * @param clvFin the clv fin
	 */
	public void requireValidClvFin(String clvFin) {
		isTrue(clvFin.length() <= MAX_LENGTH_clvFin, "La máxima longitud de clvFin es de <%d> caracteres.",
				MAX_LENGTH_clvFin);
	}

	/**
	 * Require valid periodo.
	 *
	 * @param periodo the periodo
	 */
	public void requireValidPeriodo(String periodo) {
		isTrue(periodo.length() <= MAX_LENGTH_periodo, "La máxima longitud de periodo es de <%d> caracteres.",
				MAX_LENGTH_periodo);
	}

	/**
	 * Require valid descripcion.
	 *
	 * @param descripcion the descripcion
	 */
	public void requireValidDescripcion(String descripcion) {
		isTrue(descripcion.length() <= MAX_LENGTH_descripcion,
				"La máxima longitud de descripcion es de <%d> caracteres.", MAX_LENGTH_descripcion);
	}

	/**
	 * Instantiates a new indicadores.
	 *
	 * @param cveind the cveind
	 * @param nomind the nomind
	 * @param clvDep the clv dep
	 * @param clvFun the clv fun
	 * @param clvFin the clv fin
	 * @param periodo the periodo
	 * @param descripcion the descripcion
	 */
	public Indicadores(Integer cveind, String nomind, String clvDep, String clvFun, String clvFin, String periodo,
			String descripcion) {
		this.cveind = cveind;
		this.nomind = nomind;
		this.clvDep = clvDep;
		this.clvFun = clvFun;
		this.clvFin = clvFin;
		this.periodo = periodo;
		this.descripcion = descripcion;
	}

	/**
	 * Instantiates a new indicadores.
	 */
	public Indicadores() {
	}

	/**
	 * Gets the cveind.
	 *
	 * @return the cveind
	 */
	@ColumnFile(indexColumn = 1)
	public Integer getCveind() {
		return cveind;
	}

	/**
	 * Sets the cveind.
	 *
	 * @param cveind the new cveind
	 */
	public void setCveind(Integer cveind) {
		this.cveind = cveind;
	}

	/**
	 * Gets the nomind.
	 *
	 * @return the nomind
	 */
	@ColumnFile(indexColumn = 2)
	public String getNomind() {
		return nomind;
	}

	/**
	 * Sets the nomind.
	 *
	 * @param nomind the new nomind
	 */
	public void setNomind(String nomind) {
		this.nomind = nomind;
	}

	/**
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
	}

	/**
	 * Gets the clv fun.
	 *
	 * @return the clv fun
	 */
	@ColumnFile(indexColumn = 3)
	public String getClvFun() {
		return clvFun;
	}

	/**
	 * Sets the clv fun.
	 *
	 * @param clvFun the new clv fun
	 */
	public void setClvFun(String clvFun) {
		this.clvFun = clvFun;
	}

	/**
	 * Gets the clv fin.
	 *
	 * @return the clv fin
	 */
	public String getClvFin() {
		return clvFin;
	}

	/**
	 * Sets the clv fin.
	 *
	 * @param clvFin the new clv fin
	 */
	public void setClvFin(String clvFin) {
		this.clvFin = clvFin;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	@ColumnFile(indexColumn = 4)
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("index", getIndex()).append("cveind", this.cveind)
				.append("nomind", this.nomind).append("clvDep", this.clvDep).append("clvFun", this.clvFun)
				.append("clvFin", this.clvFin).append("periodo", this.periodo).append("descripcion", this.descripcion)
				.toString();
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the idRef
	 */
	public Long getIdRef() {
		return idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef            the idRef to set
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
	 * @param idsector            the idsector to set
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
	 * @param userid            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
