package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the FTECNICASD database table.
 * 
 */
@Entity
@NamedQuery(name="Ftecnicasd.findAll", query="SELECT f FROM Ftecnicasd f")
public class Ftecnicasd extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The alcan 1. */
	@Column(name="ALCAN_1")
	private BigDecimal alcan1;

	/** The alcan 2. */
	@Column(name="ALCAN_2")
	private BigDecimal alcan2;

	/** The alcan 3. */
	@Column(name="ALCAN_3")
	private BigDecimal alcan3;

	/** The alcan 4. */
	@Column(name="ALCAN_4")
	private BigDecimal alcan4;

	/** The clvdep. */
	private String clvdep;

	/** The clvfin. */
	private String clvfin;

	/** The clvfun. */
	private String clvfun;

	/** The codigo. */
	private Integer codigo;

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
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The metanual. */
	private BigDecimal metanual;

	/** The nope. */
	private String nope;

	/** The operacion. */
	private String operacion;

	/** The pe. */
	private String pe;

	/** The porcalcan 1. */
	@Column(name="PORCALCAN_1")
	private BigDecimal porcalcan1;

	/** The porcalcan 2. */
	@Column(name="PORCALCAN_2")
	private BigDecimal porcalcan2;

	/** The porcalcan 3. */
	@Column(name="PORCALCAN_3")
	private BigDecimal porcalcan3;

	/** The porcalcan 4. */
	@Column(name="PORCALCAN_4")
	private BigDecimal porcalcan4;

	/** The porcprog 1. */
	@Column(name="PORCPROG_1")
	private BigDecimal porcprog1;

	/** The porcprog 2. */
	@Column(name="PORCPROG_2")
	private BigDecimal porcprog2;

	/** The porcprog 3. */
	@Column(name="PORCPROG_3")
	private BigDecimal porcprog3;

	/** The porcprog 4. */
	@Column(name="PORCPROG_4")
	private BigDecimal porcprog4;

	/** The prog 1. */
	@Column(name="PROG_1")
	private BigDecimal prog1;

	/** The prog 2. */
	@Column(name="PROG_2")
	private BigDecimal prog2;

	/** The prog 3. */
	@Column(name="PROG_3")
	private BigDecimal prog3;

	/** The prog 4. */
	@Column(name="PROG_4")
	private BigDecimal prog4;

	/** The tema. */
	private String tema;

	/** The unimed. */
	private String unimed;

	/** The userid. */
	private String userid;

	/** The usuario. */
	private String usuario;

	/** The variables. */
	private String variables;

	/**
	 * Instantiates a new ftecnicasd.
	 */
	public Ftecnicasd() {
	}

	/**
	 * Gets the alcan 1.
	 *
	 * @return the alcan 1
	 */
	public BigDecimal getAlcan1() {
		return this.alcan1;
	}

	/**
	 * Sets the alcan 1.
	 *
	 * @param alcan1 the new alcan 1
	 */
	public void setAlcan1(BigDecimal alcan1) {
		this.alcan1 = alcan1;
	}

	/**
	 * Gets the alcan 2.
	 *
	 * @return the alcan 2
	 */
	public BigDecimal getAlcan2() {
		return this.alcan2;
	}

	/**
	 * Sets the alcan 2.
	 *
	 * @param alcan2 the new alcan 2
	 */
	public void setAlcan2(BigDecimal alcan2) {
		this.alcan2 = alcan2;
	}

	/**
	 * Gets the alcan 3.
	 *
	 * @return the alcan 3
	 */
	public BigDecimal getAlcan3() {
		return this.alcan3;
	}

	/**
	 * Sets the alcan 3.
	 *
	 * @param alcan3 the new alcan 3
	 */
	public void setAlcan3(BigDecimal alcan3) {
		this.alcan3 = alcan3;
	}

	/**
	 * Gets the alcan 4.
	 *
	 * @return the alcan 4
	 */
	public BigDecimal getAlcan4() {
		return this.alcan4;
	}

	/**
	 * Sets the alcan 4.
	 *
	 * @param alcan4 the new alcan 4
	 */
	public void setAlcan4(BigDecimal alcan4) {
		this.alcan4 = alcan4;
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
	public Integer getCodigo() {
		return this.codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
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
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public String getOperacion() {
		return this.operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion the new operacion
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
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
	 * Gets the porcalcan 1.
	 *
	 * @return the porcalcan 1
	 */
	public BigDecimal getPorcalcan1() {
		return this.porcalcan1;
	}

	/**
	 * Sets the porcalcan 1.
	 *
	 * @param porcalcan1 the new porcalcan 1
	 */
	public void setPorcalcan1(BigDecimal porcalcan1) {
		this.porcalcan1 = porcalcan1;
	}

	/**
	 * Gets the porcalcan 2.
	 *
	 * @return the porcalcan 2
	 */
	public BigDecimal getPorcalcan2() {
		return this.porcalcan2;
	}

	/**
	 * Sets the porcalcan 2.
	 *
	 * @param porcalcan2 the new porcalcan 2
	 */
	public void setPorcalcan2(BigDecimal porcalcan2) {
		this.porcalcan2 = porcalcan2;
	}

	/**
	 * Gets the porcalcan 3.
	 *
	 * @return the porcalcan 3
	 */
	public BigDecimal getPorcalcan3() {
		return this.porcalcan3;
	}

	/**
	 * Sets the porcalcan 3.
	 *
	 * @param porcalcan3 the new porcalcan 3
	 */
	public void setPorcalcan3(BigDecimal porcalcan3) {
		this.porcalcan3 = porcalcan3;
	}

	/**
	 * Gets the porcalcan 4.
	 *
	 * @return the porcalcan 4
	 */
	public BigDecimal getPorcalcan4() {
		return this.porcalcan4;
	}

	/**
	 * Sets the porcalcan 4.
	 *
	 * @param porcalcan4 the new porcalcan 4
	 */
	public void setPorcalcan4(BigDecimal porcalcan4) {
		this.porcalcan4 = porcalcan4;
	}

	/**
	 * Gets the porcprog 1.
	 *
	 * @return the porcprog 1
	 */
	public BigDecimal getPorcprog1() {
		return this.porcprog1;
	}

	/**
	 * Sets the porcprog 1.
	 *
	 * @param porcprog1 the new porcprog 1
	 */
	public void setPorcprog1(BigDecimal porcprog1) {
		this.porcprog1 = porcprog1;
	}

	/**
	 * Gets the porcprog 2.
	 *
	 * @return the porcprog 2
	 */
	public BigDecimal getPorcprog2() {
		return this.porcprog2;
	}

	/**
	 * Sets the porcprog 2.
	 *
	 * @param porcprog2 the new porcprog 2
	 */
	public void setPorcprog2(BigDecimal porcprog2) {
		this.porcprog2 = porcprog2;
	}

	/**
	 * Gets the porcprog 3.
	 *
	 * @return the porcprog 3
	 */
	public BigDecimal getPorcprog3() {
		return this.porcprog3;
	}

	/**
	 * Sets the porcprog 3.
	 *
	 * @param porcprog3 the new porcprog 3
	 */
	public void setPorcprog3(BigDecimal porcprog3) {
		this.porcprog3 = porcprog3;
	}

	/**
	 * Gets the porcprog 4.
	 *
	 * @return the porcprog 4
	 */
	public BigDecimal getPorcprog4() {
		return this.porcprog4;
	}

	/**
	 * Sets the porcprog 4.
	 *
	 * @param porcprog4 the new porcprog 4
	 */
	public void setPorcprog4(BigDecimal porcprog4) {
		this.porcprog4 = porcprog4;
	}

	/**
	 * Gets the prog 1.
	 *
	 * @return the prog 1
	 */
	public BigDecimal getProg1() {
		return this.prog1;
	}

	/**
	 * Sets the prog 1.
	 *
	 * @param prog1 the new prog 1
	 */
	public void setProg1(BigDecimal prog1) {
		this.prog1 = prog1;
	}

	/**
	 * Gets the prog 2.
	 *
	 * @return the prog 2
	 */
	public BigDecimal getProg2() {
		return this.prog2;
	}

	/**
	 * Sets the prog 2.
	 *
	 * @param prog2 the new prog 2
	 */
	public void setProg2(BigDecimal prog2) {
		this.prog2 = prog2;
	}

	/**
	 * Gets the prog 3.
	 *
	 * @return the prog 3
	 */
	public BigDecimal getProg3() {
		return this.prog3;
	}

	/**
	 * Sets the prog 3.
	 *
	 * @param prog3 the new prog 3
	 */
	public void setProg3(BigDecimal prog3) {
		this.prog3 = prog3;
	}

	/**
	 * Gets the prog 4.
	 *
	 * @return the prog 4
	 */
	public BigDecimal getProg4() {
		return this.prog4;
	}

	/**
	 * Sets the prog 4.
	 *
	 * @param prog4 the new prog 4
	 */
	public void setProg4(BigDecimal prog4) {
		this.prog4 = prog4;
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