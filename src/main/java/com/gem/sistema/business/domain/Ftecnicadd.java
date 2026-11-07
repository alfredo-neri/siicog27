package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the FTECNICADD database table.
 * 
 */
@Entity
@NamedQuery(name="Ftecnicadd.findAll", query="SELECT f FROM Ftecnicadd f")
public class Ftecnicadd implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name="\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The clvdep. */
	private String clvdep;

	/** The clvfin. */
	private String clvfin;

	/** The clvfun. */
	private String clvfun;

	/** The codigo. */
	private int codigo;

	/** The cveind. */
	private String cveind;

	/** The cvetemas. */
	private String cvetemas;

	private Integer numvar;
	
	/** The cvevar. */
	private String cvevar;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The idsector. */
	private int idsector;

	/** The metanual. */
	private BigDecimal metanual;

	/** The nope. */
	private String nope;

	/** The pe. */
	private String pe;

	/** The tema. */
	private String tema;

	/** The tipoper. */
	private String tipoper;

	/** The trim 1. */
	private BigDecimal trim1;

	/** The trim 2. */
	private BigDecimal trim2;

	/** The trim 3. */
	private BigDecimal trim3;

	/** The trim 4. */
	private BigDecimal trim4;

	/** The unimed. */
	private String unimed;

	/** The userid. */
	@Column(name="\"USERID\"")
	private String userid;

	/** The usuario. */
	private String usuario;

	/** The variables. */
	private String variables;

	/**
	 * Instantiates a new ftecnicadd.
	 */
	public Ftecnicadd() {
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
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
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
	 * Gets the clvfin.
	 *
	 * @return the clvfin
	 */
	public String getClvfin() {
		return this.clvfin;
	}

	/**
	 * Sets the clvfin.
	 *
	 * @param clvfin the new clvfin
	 */
	public void setClvfin(String clvfin) {
		this.clvfin = clvfin;
	}

	/**
	 * Gets the clvfun.
	 *
	 * @return the clvfun
	 */
	public String getClvfun() {
		return this.clvfun;
	}

	/**
	 * Sets the clvfun.
	 *
	 * @param clvfun the new clvfun
	 */
	public void setClvfun(String clvfun) {
		this.clvfun = clvfun;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return this.codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the cveind.
	 *
	 * @return the cveind
	 */
	public String getCveind() {
		return this.cveind;
	}

	/**
	 * Sets the cveind.
	 *
	 * @param cveind the new cveind
	 */
	public void setCveind(String cveind) {
		this.cveind = cveind;
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

	public Integer getNumvar() {
		return numvar;
	}

	public void setNumvar(Integer numvar) {
		this.numvar = numvar;
	}

	/**
	 * Gets the cvevar.
	 *
	 * @return the cvevar
	 */
	public String getCvevar() {
		return this.cvevar;
	}

	/**
	 * Sets the cvevar.
	 *
	 * @param cvevar the new cvevar
	 */
	public void setCvevar(String cvevar) {
		this.cvevar = cvevar;
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
	 * Gets the metanual.
	 *
	 * @return the metanual
	 */
	public BigDecimal getMetanual() {
		return this.metanual;
	}

	/**
	 * Sets the metanual.
	 *
	 * @param metanual the new metanual
	 */
	public void setMetanual(BigDecimal metanual) {
		this.metanual = metanual;
	}

	/**
	 * Gets the nope.
	 *
	 * @return the nope
	 */
	public String getNope() {
		return this.nope;
	}

	/**
	 * Sets the nope.
	 *
	 * @param nope the new nope
	 */
	public void setNope(String nope) {
		this.nope = nope;
	}

	/**
	 * Gets the pe.
	 *
	 * @return the pe
	 */
	public String getPe() {
		return this.pe;
	}

	/**
	 * Sets the pe.
	 *
	 * @param pe the new pe
	 */
	public void setPe(String pe) {
		this.pe = pe;
	}

	/**
	 * Gets the tema.
	 *
	 * @return the tema
	 */
	public String getTema() {
		return this.tema;
	}

	/**
	 * Sets the tema.
	 *
	 * @param tema the new tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Gets the tipoper.
	 *
	 * @return the tipoper
	 */
	public String getTipoper() {
		return this.tipoper;
	}

	/**
	 * Sets the tipoper.
	 *
	 * @param tipoper the new tipoper
	 */
	public void setTipoper(String tipoper) {
		this.tipoper = tipoper;
	}

	/**
	 * Gets the trim 1.
	 *
	 * @return the trim 1
	 */
	public BigDecimal getTrim1() {
		return this.trim1;
	}

	/**
	 * Sets the trim 1.
	 *
	 * @param trim1 the new trim 1
	 */
	public void setTrim1(BigDecimal trim1) {
		this.trim1 = trim1;
	}

	/**
	 * Gets the trim 2.
	 *
	 * @return the trim 2
	 */
	public BigDecimal getTrim2() {
		return this.trim2;
	}

	/**
	 * Sets the trim 2.
	 *
	 * @param trim2 the new trim 2
	 */
	public void setTrim2(BigDecimal trim2) {
		this.trim2 = trim2;
	}

	/**
	 * Gets the trim 3.
	 *
	 * @return the trim 3
	 */
	public BigDecimal getTrim3() {
		return this.trim3;
	}

	/**
	 * Sets the trim 3.
	 *
	 * @param trim3 the new trim 3
	 */
	public void setTrim3(BigDecimal trim3) {
		this.trim3 = trim3;
	}

	/**
	 * Gets the trim 4.
	 *
	 * @return the trim 4
	 */
	public BigDecimal getTrim4() {
		return this.trim4;
	}

	/**
	 * Sets the trim 4.
	 *
	 * @param trim4 the new trim 4
	 */
	public void setTrim4(BigDecimal trim4) {
		this.trim4 = trim4;
	}

	/**
	 * Gets the unimed.
	 *
	 * @return the unimed
	 */
	public String getUnimed() {
		return this.unimed;
	}

	/**
	 * Sets the unimed.
	 *
	 * @param unimed the new unimed
	 */
	public void setUnimed(String unimed) {
		this.unimed = unimed;
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

	/**
	 * Gets the variables.
	 *
	 * @return the variables
	 */
	public String getVariables() {
		return this.variables;
	}

	/**
	 * Sets the variables.
	 *
	 * @param variables the new variables
	 */
	public void setVariables(String variables) {
		this.variables = variables;
	}

}