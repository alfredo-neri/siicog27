package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Obras1.
 */
@Entity
@Table(name = "OBRAS1")
public class Obras1 extends AbstractEntity {
	
	/** The Constant MAX_LENGTH_clvcto. */
	/*
	 * 
	 * clvcto -> Cve del Contrato
	 * 
	 * clprv -> Cve Contratista clvobr -> Cve de la Obra Feicto -> Fecha Inicial
	 * Fefcto ->Fecha Final moncto -> Monto del contrato Feccap -> Fecha del
	 * registro
	 */
	@IgnoredQuery
	static final int MAX_LENGTH_clvcto = 56;

	/** The capcto. */
	@Column(name = "capcto", length = 16)
	private String capcto;

	/** The clvcto. */
	@Column(name = "clvcto", length = MAX_LENGTH_clvcto, nullable = false)
	private String clvcto;

	/** The clvobr. */
	@Column(name = "clvobr")
	private Integer clvobr;

	/** The clvprv. */
	@Column(name = "clvprv")
	private Integer clvprv;

	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap = Calendar.getInstance().getTime();

	/** The fefcto. */
	@Column(name = "fefcto")
	private Date fefcto = Calendar.getInstance().getTime();

	/** The feicto. */
	@Column(name = "feicto")
	private Date feicto = Calendar.getInstance().getTime();

	/** The moncto. */
	@Column(name = "moncto")
	private BigDecimal moncto;

	/** The monenc. */
	@Column(name = "monenc")
	private BigDecimal monenc;

	/** The nomobr. */
	@Column(name = "nomobr", length = 304)
	private String nomobr;

	/** The stacto. */
	@Column(name = "stacto", length = 2)
	private String stacto;

	/** The fecpol. */
	@Column(name = "fecpol")
	private Date fecpol;

	/** The anopol. */
	@Column(name = "anopol")
	private Integer anopol;

	/** The tippol. */
	@Column(name = "tippol", length = 2)
	private String tippol;

	/** The mespol. */
	@Column(name = "mespol")
	private Integer mespol;

	/** The conpol. */
	@Column(name = "conpol")
	private Integer conpol;

	/** The renpol. */
	@Column(name = "renpol")
	private BigDecimal renpol;

	/** The refpol. */
	@Column(name = "refpol")
	private BigDecimal refpol;

	/** The concep. */
	@Column(name = "concep", length = 150)
	private String concep;

	/** The caopol. */
	@Column(name = "caopol", length = 2)
	private String caopol;

	/** The canpol. */
	@Column(name = "canpol")
	private BigDecimal canpol;

	/** The capsal. */
	@Column(name = "capsal", length = 16)
	private String capsal;

	/** The ctosal. */
	@Column(name = "ctosal", length = 56)
	private String ctosal;

	/**
	 * Require valid clvcto.
	 *
	 * @param clvcto the clvcto
	 */
	public void requireValidClvcto(String clvcto) {
		notNull(clvcto, "Clave contrato no puede ser nulo.");
		isTrue(clvcto.length() <= MAX_LENGTH_clvcto,
				"La máxima longitud de la clave de contrato es de <%d> caracteres.", MAX_LENGTH_clvcto);
	}

	/**
	 * Instantiates a new obras 1.
	 *
	 * @param capcto the capcto
	 * @param clvcto the clvcto
	 * @param clvobr the clvobr
	 * @param clvprv the clvprv
	 * @param feccap the feccap
	 * @param fefcto the fefcto
	 * @param feicto the feicto
	 * @param moncto the moncto
	 * @param monenc the monenc
	 * @param nomobr the nomobr
	 * @param stacto the stacto
	 * @param fecpol the fecpol
	 * @param anopol the anopol
	 * @param tippol the tippol
	 * @param mespol the mespol
	 * @param conpol the conpol
	 * @param renpol the renpol
	 * @param refpol the refpol
	 * @param concep the concep
	 * @param caopol the caopol
	 * @param canpol the canpol
	 * @param capsal the capsal
	 * @param ctosal the ctosal
	 */
	public Obras1(String capcto, String clvcto, Integer clvobr, Integer clvprv, Date feccap, Date fefcto, Date feicto,
			BigDecimal moncto, BigDecimal monenc, String nomobr, String stacto, Date fecpol, Integer anopol,
			String tippol, Integer mespol, Integer conpol, BigDecimal renpol, BigDecimal refpol, String concep,
			String caopol, BigDecimal canpol, String capsal, String ctosal) {
		this.capcto = capcto;
		this.clvcto = clvcto;
		this.clvobr = clvobr;
		this.clvprv = clvprv;
		this.feccap = feccap;
		this.fefcto = fefcto;
		this.feicto = feicto;
		this.moncto = moncto;
		this.monenc = monenc;
		this.nomobr = nomobr;
		this.stacto = stacto;
		this.fecpol = fecpol;
		this.anopol = anopol;
		this.tippol = tippol;
		this.mespol = mespol;
		this.conpol = conpol;
		this.renpol = renpol;
		this.refpol = refpol;
		this.concep = concep;
		this.caopol = caopol;
		this.canpol = canpol;
		this.capsal = capsal;
		this.ctosal = ctosal;
	}

	/**
	 * Instantiates a new obras 1.
	 */
	public Obras1() {
	}

	/**
	 * Gets the capcto.
	 *
	 * @return the capcto
	 */
	public String getCapcto() {
		return capcto;
	}

	/**
	 * Sets the capcto.
	 *
	 * @param capcto the new capcto
	 */
	public void setCapcto(String capcto) {
		this.capcto = capcto;
	}

	/**
	 * Gets the clvcto.
	 *
	 * @return the clvcto
	 */
	@ColumnFile(indexColumn = 2)
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

	/**
	 * Gets the clvobr.
	 *
	 * @return the clvobr
	 */
	@ColumnFile(indexColumn = 4)
	public Integer getClvobr() {
		return clvobr;
	}

	/**
	 * Sets the clvobr.
	 *
	 * @param clvobr the new clvobr
	 */
	public void setClvobr(Integer clvobr) {
		this.clvobr = clvobr;
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
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	@ColumnFile(indexColumn = 8)
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
	 * Gets the fefcto.
	 *
	 * @return the fefcto
	 */
	@ColumnFile(indexColumn = 6)
	public Date getFefcto() {
		return fefcto;
	}

	/**
	 * Sets the fefcto.
	 *
	 * @param fefcto the new fefcto
	 */
	public void setFefcto(Date fefcto) {
		this.fefcto = fefcto;
	}

	/**
	 * Gets the feicto.
	 *
	 * @return the feicto
	 */
	@ColumnFile(indexColumn = 5)
	public Date getFeicto() {
		return feicto;
	}

	/**
	 * Sets the feicto.
	 *
	 * @param feicto the new feicto
	 */
	public void setFeicto(Date feicto) {
		this.feicto = feicto;
	}

	/**
	 * Gets the moncto.
	 *
	 * @return the moncto
	 */
	@ColumnFile(indexColumn = 7)
	public BigDecimal getMoncto() {
		return moncto;
	}

	/**
	 * Sets the moncto.
	 *
	 * @param moncto the new moncto
	 */
	public void setMoncto(BigDecimal moncto) {
		this.moncto = moncto;
	}

	/**
	 * Gets the monenc.
	 *
	 * @return the monenc
	 */
	public BigDecimal getMonenc() {
		return monenc;
	}

	/**
	 * Sets the monenc.
	 *
	 * @param monenc the new monenc
	 */
	public void setMonenc(BigDecimal monenc) {
		this.monenc = monenc;
	}

	/**
	 * Gets the nomobr.
	 *
	 * @return the nomobr
	 */
	@ColumnFile(indexColumn = 3)
	public String getNomobr() {
		return nomobr;
	}

	/**
	 * Sets the nomobr.
	 *
	 * @param nomobr the new nomobr
	 */
	public void setNomobr(String nomobr) {
		this.nomobr = nomobr;
	}

	/**
	 * Gets the stacto.
	 *
	 * @return the stacto
	 */
	public String getStacto() {
		return stacto;
	}

	/**
	 * Sets the stacto.
	 *
	 * @param stacto the new stacto
	 */
	public void setStacto(String stacto) {
		this.stacto = stacto;
	}

	
	/**
	 * Gets the fecpol.
	 *
	 * @return the fecpol
	 */
	public Date getFecpol() {
		return fecpol;
	}

	/**
	 * Sets the fecpol.
	 *
	 * @param fecpol the new fecpol
	 */
	public void setFecpol(Date fecpol) {
		this.fecpol = fecpol;
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
	 * Gets the refpol.
	 *
	 * @return the refpol
	 */
	public BigDecimal getRefpol() {
		return refpol;
	}

	/**
	 * Sets the refpol.
	 *
	 * @param refpol the new refpol
	 */
	public void setRefpol(BigDecimal refpol) {
		this.refpol = refpol;
	}

	/**
	 * Gets the concep.
	 *
	 * @return the concep
	 */
	public String getConcep() {
		return concep;
	}

	/**
	 * Sets the concep.
	 *
	 * @param concep the new concep
	 */
	public void setConcep(String concep) {
		this.concep = concep;
	}

	/**
	 * Gets the caopol.
	 *
	 * @return the caopol
	 */
	public String getCaopol() {
		return caopol;
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
	 * Gets the canpol.
	 *
	 * @return the canpol
	 */
	public BigDecimal getCanpol() {
		return canpol;
	}

	/**
	 * Sets the canpol.
	 *
	 * @param canpol the new canpol
	 */
	public void setCanpol(BigDecimal canpol) {
		this.canpol = canpol;
	}

	/**
	 * Gets the capsal.
	 *
	 * @return the capsal
	 */
	public String getCapsal() {
		return capsal;
	}

	/**
	 * Sets the capsal.
	 *
	 * @param capsal the new capsal
	 */
	public void setCapsal(String capsal) {
		this.capsal = capsal;
	}

	/**
	 * Gets the ctosal.
	 *
	 * @return the ctosal
	 */
	public String getCtosal() {
		return ctosal;
	}

	/**
	 * Sets the ctosal.
	 *
	 * @param ctosal the new ctosal
	 */
	public void setCtosal(String ctosal) {
		this.ctosal = ctosal;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId()).append("clvcto", this.clvcto)
				.append("clvobr", this.clvobr).append("clvprv", this.clvprv).append("fefcto", this.fefcto)
				.append("feicto", this.feicto).append("moncto", this.moncto).append("feccap", this.feccap).toString();
	}

}
