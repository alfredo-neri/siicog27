package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the MATIND database table.
 * 
 */
@Entity
@NamedQuery(name="Matind.findAll", query="SELECT m FROM Matind m")
public class Matind implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"")
	private long id;

	/** The clvdepg. */
	private String clvdepg;

	/** The componente. */
	private int componente;

	/** The cvefin. */
	private String cvefin;

	/** The cveprog. */
	private String cveprog;

	/** The cvetemas. */
	private String cvetemas;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The finalidad. */
	private int finalidad;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The idsector. */
	private int idsector;

	/** The objetivo. */
	private String objetivo;

	/** The proposito. */
	private int proposito;

	/** The userid. */
	@Column(name="\"USERID\"")
	private String userid;

	/** The usuario. */
	private String usuario;

	/**
	 * Instantiates a new matind.
	 */
	public Matind() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the clvdepg.
	 *
	 * @return the clvdepg
	 */
	public String getClvdepg() {
		return this.clvdepg;
	}

	/**
	 * Sets the clvdepg.
	 *
	 * @param clvdepg the new clvdepg
	 */
	public void setClvdepg(String clvdepg) {
		this.clvdepg = clvdepg;
	}

	/**
	 * Gets the componente.
	 *
	 * @return the componente
	 */
	public int getComponente() {
		return this.componente;
	}

	/**
	 * Sets the componente.
	 *
	 * @param componente the new componente
	 */
	public void setComponente(int componente) {
		this.componente = componente;
	}

	/**
	 * Gets the cvefin.
	 *
	 * @return the cvefin
	 */
	public String getCvefin() {
		return this.cvefin;
	}

	/**
	 * Sets the cvefin.
	 *
	 * @param cvefin the new cvefin
	 */
	public void setCvefin(String cvefin) {
		this.cvefin = cvefin;
	}

	/**
	 * Gets the cveprog.
	 *
	 * @return the cveprog
	 */
	public String getCveprog() {
		return this.cveprog;
	}

	/**
	 * Sets the cveprog.
	 *
	 * @param cveprog the new cveprog
	 */
	public void setCveprog(String cveprog) {
		this.cveprog = cveprog;
	}

	/**
	 * Gets the cvetemas.
	 *
	 * @return the cvetemas
	 */
	public String getCvetemas() {
		return this.cvetemas;
	}

	/**
	 * Sets the cvetemas.
	 *
	 * @param cvetemas the new cvetemas
	 */
	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
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
	 * Gets the finalidad.
	 *
	 * @return the finalidad
	 */
	public int getFinalidad() {
		return this.finalidad;
	}

	/**
	 * Sets the finalidad.
	 *
	 * @param finalidad the new finalidad
	 */
	public void setFinalidad(int finalidad) {
		this.finalidad = finalidad;
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
	 * Gets the objetivo.
	 *
	 * @return the objetivo
	 */
	public String getObjetivo() {
		return this.objetivo;
	}

	/**
	 * Sets the objetivo.
	 *
	 * @param objetivo the new objetivo
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * Gets the proposito.
	 *
	 * @return the proposito
	 */
	public int getProposito() {
		return this.proposito;
	}

	/**
	 * Sets the proposito.
	 *
	 * @param proposito the new proposito
	 */
	public void setProposito(int proposito) {
		this.proposito = proposito;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}