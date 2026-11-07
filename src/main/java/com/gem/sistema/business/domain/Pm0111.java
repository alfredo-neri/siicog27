package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM0111 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm0111.findAll", query="SELECT p FROM Pm0111 p")
public class Pm0111 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anual. */
	private Integer anual;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The njma. */
	private Integer njma;

	/** The njmc. */
	private Integer njmc;

	/** The njmd. */
	private Integer njmd;

	/** The njml. */
	private Integer njml;

	/** The obsnjm. */
	private String obsnjm;

	/** The obstji. */
	private String obstji;

	/** The tjia. */
	private Integer tjia;

	/** The tjic. */
	private Integer tjic;

	/** The tjid. */
	private Integer tjid;

	/** The tjil. */
	private Integer tjil;

	/** The totjucon. */
	private Integer totjucon;

	/** The totjuga. */
	private Integer totjuga;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 0111.
	 */
	public Pm0111() {
	}

	/**
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return this.anual;
	}

	/**
	 * Sets the anual.
	 *
	 * @param anual the new anual
	 */
	public void setAnual(Integer anual) {
		this.anual = anual;
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
	 * Gets the njma.
	 *
	 * @return the njma
	 */
	public Integer getNjma() {
		return this.njma;
	}

	/**
	 * Sets the njma.
	 *
	 * @param njma the new njma
	 */
	public void setNjma(Integer njma) {
		this.njma = njma;
	}

	/**
	 * Gets the njmc.
	 *
	 * @return the njmc
	 */
	public Integer getNjmc() {
		return this.njmc;
	}

	/**
	 * Sets the njmc.
	 *
	 * @param njmc the new njmc
	 */
	public void setNjmc(Integer njmc) {
		this.njmc = njmc;
	}

	/**
	 * Gets the njmd.
	 *
	 * @return the njmd
	 */
	public Integer getNjmd() {
		return this.njmd;
	}

	/**
	 * Sets the njmd.
	 *
	 * @param njmd the new njmd
	 */
	public void setNjmd(Integer njmd) {
		this.njmd = njmd;
	}

	/**
	 * Gets the njml.
	 *
	 * @return the njml
	 */
	public Integer getNjml() {
		return this.njml;
	}

	/**
	 * Sets the njml.
	 *
	 * @param njml the new njml
	 */
	public void setNjml(Integer njml) {
		this.njml = njml;
	}

	/**
	 * Gets the obsnjm.
	 *
	 * @return the obsnjm
	 */
	public String getObsnjm() {
		return this.obsnjm;
	}

	/**
	 * Sets the obsnjm.
	 *
	 * @param obsnjm the new obsnjm
	 */
	public void setObsnjm(String obsnjm) {
		this.obsnjm = obsnjm;
	}

	/**
	 * Gets the obstji.
	 *
	 * @return the obstji
	 */
	public String getObstji() {
		return this.obstji;
	}

	/**
	 * Sets the obstji.
	 *
	 * @param obstji the new obstji
	 */
	public void setObstji(String obstji) {
		this.obstji = obstji;
	}

	/**
	 * Gets the tjia.
	 *
	 * @return the tjia
	 */
	public Integer getTjia() {
		return this.tjia;
	}

	/**
	 * Sets the tjia.
	 *
	 * @param tjia the new tjia
	 */
	public void setTjia(Integer tjia) {
		this.tjia = tjia;
	}

	/**
	 * Gets the tjic.
	 *
	 * @return the tjic
	 */
	public Integer getTjic() {
		return this.tjic;
	}

	/**
	 * Sets the tjic.
	 *
	 * @param tjic the new tjic
	 */
	public void setTjic(Integer tjic) {
		this.tjic = tjic;
	}

	/**
	 * Gets the tjid.
	 *
	 * @return the tjid
	 */
	public Integer getTjid() {
		return this.tjid;
	}

	/**
	 * Sets the tjid.
	 *
	 * @param tjid the new tjid
	 */
	public void setTjid(Integer tjid) {
		this.tjid = tjid;
	}

	/**
	 * Gets the tjil.
	 *
	 * @return the tjil
	 */
	public Integer getTjil() {
		return this.tjil;
	}

	/**
	 * Sets the tjil.
	 *
	 * @param tjil the new tjil
	 */
	public void setTjil(Integer tjil) {
		this.tjil = tjil;
	}

	/**
	 * Gets the totjucon.
	 *
	 * @return the totjucon
	 */
	public Integer getTotjucon() {
		return this.totjucon;
	}

	/**
	 * Sets the totjucon.
	 *
	 * @param totjucon the new totjucon
	 */
	public void setTotjucon(Integer totjucon) {
		this.totjucon = totjucon;
	}

	/**
	 * Gets the totjuga.
	 *
	 * @return the totjuga
	 */
	public Integer getTotjuga() {
		return this.totjuga;
	}

	/**
	 * Sets the totjuga.
	 *
	 * @param totjuga the new totjuga
	 */
	public void setTotjuga(Integer totjuga) {
		this.totjuga = totjuga;
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

}