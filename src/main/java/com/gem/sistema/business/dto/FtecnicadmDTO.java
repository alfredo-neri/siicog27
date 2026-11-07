package com.gem.sistema.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The data access object class for the FTECNICADM database table.
 * 
 */
public class FtecnicadmDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private long id;

	/** The clvdep. */
	private String clvdep;

	/** The clvfin. */
	private String clvfin;

	/** The clvfun. */
	private String clvfun;

	/** The cveind. */
	private String cveind;

	/** The cvetemas. */
	private String cvetemas;

	/** The descanual. */
	private String descanual;

	/** The descfac. */
	private String descfac;

	/** The dimension. */
	private String dimension;

	/** The elaboro. */
	private String elaboro;

	/** The factor. */
	private BigDecimal factor;

	/** The feccap. */
	private Date feccap;

	/** The formula. */
	private String formula;

	/** The frecuencia. */
	private String frecuencia;

	/** The id ref. */
	private long idRef;

	/** The idsector. */
	private int idsector;

	/** The interpretacion. */
	private String interpretacion;

	/** The medios. */
	private String medios;

	/** The metanuale. */
	private BigDecimal metanuale = new BigDecimal(0.00);

	/** The metasact. */
	private String metasact;

	/** The nomind. */
	private String nomind;

	/** The nope. */
	private String nope;

	/** The objetivo. */
	private String objetivo;

	/** The pe. */
	private String pe;

	/** The tema. */
	private String tema;

	/** The tipo. */
	private String tipo;

	/** The trim 1 e. */
	private BigDecimal trim1e = new BigDecimal(0.00);

	/** The trim 2 e. */
	private BigDecimal trim2e = new BigDecimal(0.00);

	/** The trim 3 e. */
	private BigDecimal trim3e = new BigDecimal(0.00);

	/** The trim 4 e. */
	private BigDecimal trim4e = new BigDecimal(0.00);

	/** The userid. */
	private String userid;

	/** The usuario. */
	private String usuario;

	/** The valido. */
	private String valido;

	/** The desc temas. */
	private String descTemas;

	/** The desc dependencias. */
	private String descDependencias;

	/** The desc programas. */
	private String descProgramas;

	private String lineaBase;

	/**
	 * Instantiates a new ftecnicadm DTO.
	 *
	 * @param id             the id
	 * @param clvdep         the clvdep
	 * @param clvfin         the clvfin
	 * @param clvfun         the clvfun
	 * @param cveind         the cveind
	 * @param cvetemas       the cvetemas
	 * @param descanual      the descanual
	 * @param descfac        the descfac
	 * @param dimension      the dimension
	 * @param elaboro        the elaboro
	 * @param factor         the factor
	 * @param feccap         the feccap
	 * @param formula        the formula
	 * @param frecuencia     the frecuencia
	 * @param idRef          the id ref
	 * @param idsector       the idsector
	 * @param interpretacion the interpretacion
	 * @param medios         the medios
	 * @param metanuale      the metanuale
	 * @param metasact       the metasact
	 * @param nomind         the nomind
	 * @param nope           the nope
	 * @param objetivo       the objetivo
	 * @param pe             the pe
	 * @param tema           the tema
	 * @param tipo           the tipo
	 * @param trim1e         the trim 1 e
	 * @param trim2e         the trim 2 e
	 * @param trim3e         the trim 3 e
	 * @param trim4e         the trim 4 e
	 * @param userid         the userid
	 * @param usuario        the usuario
	 * @param valido         the valido
	 */
	public FtecnicadmDTO(long id, String clvdep, String clvfin, String clvfun, String cveind, String cvetemas,
			String descanual, String descfac, String dimension, String elaboro, BigDecimal factor, Date feccap,
			String formula, String frecuencia, long idRef, int idsector, String interpretacion, String medios,
			BigDecimal metanuale, String metasact, String nomind, String nope, String objetivo, String pe, String tema,
			String tipo, BigDecimal trim1e, BigDecimal trim2e, BigDecimal trim3e, BigDecimal trim4e, String userid,
			String usuario, String valido) {
		super();
		this.id = id;
		this.clvdep = clvdep;
		this.clvfin = clvfin;
		this.clvfun = clvfun;
		this.cveind = cveind;
		this.cvetemas = cvetemas;
		this.descanual = descanual;
		this.descfac = descfac;
		this.dimension = dimension;
		this.elaboro = elaboro;
		this.factor = factor;
		this.feccap = feccap;
		this.formula = formula;
		this.frecuencia = frecuencia;
		this.idRef = idRef;
		this.idsector = idsector;
		this.interpretacion = interpretacion;
		this.medios = medios;
		this.metanuale = metanuale;
		this.metasact = metasact;
		this.nomind = nomind;
		this.nope = nope;
		this.objetivo = objetivo;
		this.pe = pe;
		this.tema = tema;
		this.tipo = tipo;
		this.trim1e = trim1e;
		this.trim2e = trim2e;
		this.trim3e = trim3e;
		this.trim4e = trim4e;
		this.userid = userid;
		this.usuario = usuario;
		this.valido = valido;
	}

	/**
	 * Instantiates a new ftecnicadm DTO.
	 */
	public FtecnicadmDTO() {
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

	/**
	 * Gets the descanual.
	 *
	 * @return the descanual
	 */
	public String getDescanual() {
		return this.descanual;
	}

	/**
	 * Sets the descanual.
	 *
	 * @param descanual the new descanual
	 */
	public void setDescanual(String descanual) {
		this.descanual = descanual;
	}

	/**
	 * Gets the descfac.
	 *
	 * @return the descfac
	 */
	public String getDescfac() {
		return this.descfac;
	}

	/**
	 * Sets the descfac.
	 *
	 * @param descfac the new descfac
	 */
	public void setDescfac(String descfac) {
		this.descfac = descfac;
	}

	/**
	 * Gets the dimension.
	 *
	 * @return the dimension
	 */
	public String getDimension() {
		return this.dimension;
	}

	/**
	 * Sets the dimension.
	 *
	 * @param dimension the new dimension
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
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
	 * Gets the factor.
	 *
	 * @return the factor
	 */
	public BigDecimal getFactor() {
		return this.factor;
	}

	/**
	 * Sets the factor.
	 *
	 * @param factor the new factor
	 */
	public void setFactor(BigDecimal factor) {
		this.factor = factor;
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
	 * Gets the formula.
	 *
	 * @return the formula
	 */
	public String getFormula() {
		return this.formula;
	}

	/**
	 * Sets the formula.
	 *
	 * @param formula the new formula
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return this.frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
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
	 * Gets the interpretacion.
	 *
	 * @return the interpretacion
	 */
	public String getInterpretacion() {
		return this.interpretacion;
	}

	/**
	 * Sets the interpretacion.
	 *
	 * @param interpretacion the new interpretacion
	 */
	public void setInterpretacion(String interpretacion) {
		this.interpretacion = interpretacion;
	}

	/**
	 * Gets the medios.
	 *
	 * @return the medios
	 */
	public String getMedios() {
		return this.medios;
	}

	/**
	 * Sets the medios.
	 *
	 * @param medios the new medios
	 */
	public void setMedios(String medios) {
		this.medios = medios;
	}

	/**
	 * Gets the metanuale.
	 *
	 * @return the metanuale
	 */
	public BigDecimal getMetanuale() {
		return this.metanuale;
	}

	/**
	 * Sets the metanuale.
	 *
	 * @param metanuale the new metanuale
	 */
	public void setMetanuale(BigDecimal metanuale) {
		this.metanuale = metanuale;
	}

	/**
	 * Gets the metasact.
	 *
	 * @return the metasact
	 */
	public String getMetasact() {
		return this.metasact;
	}

	/**
	 * Sets the metasact.
	 *
	 * @param metasact the new metasact
	 */
	public void setMetasact(String metasact) {
		this.metasact = metasact;
	}

	/**
	 * Gets the nomind.
	 *
	 * @return the nomind
	 */
	public String getNomind() {
		return this.nomind;
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
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the trim 1 e.
	 *
	 * @return the trim 1 e
	 */
	public BigDecimal getTrim1e() {
		return this.trim1e;
	}

	/**
	 * Sets the trim 1 e.
	 *
	 * @param trim1e the new trim 1 e
	 */
	public void setTrim1e(BigDecimal trim1e) {
		this.trim1e = trim1e;
	}

	/**
	 * Gets the trim 2 e.
	 *
	 * @return the trim 2 e
	 */
	public BigDecimal getTrim2e() {
		return this.trim2e;
	}

	/**
	 * Sets the trim 2 e.
	 *
	 * @param trim2e the new trim 2 e
	 */
	public void setTrim2e(BigDecimal trim2e) {
		this.trim2e = trim2e;
	}

	/**
	 * Gets the trim 3 e.
	 *
	 * @return the trim 3 e
	 */
	public BigDecimal getTrim3e() {
		return this.trim3e;
	}

	/**
	 * Sets the trim 3 e.
	 *
	 * @param trim3e the new trim 3 e
	 */
	public void setTrim3e(BigDecimal trim3e) {
		this.trim3e = trim3e;
	}

	/**
	 * Gets the trim 4 e.
	 *
	 * @return the trim 4 e
	 */
	public BigDecimal getTrim4e() {
		return this.trim4e;
	}

	/**
	 * Sets the trim 4 e.
	 *
	 * @param trim4e the new trim 4 e
	 */
	public void setTrim4e(BigDecimal trim4e) {
		this.trim4e = trim4e;
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
	 * Gets the valido.
	 *
	 * @return the valido
	 */
	public String getValido() {
		return this.valido;
	}

	/**
	 * Sets the valido.
	 *
	 * @param valido the new valido
	 */
	public void setValido(String valido) {
		this.valido = valido;
	}

	/**
	 * Gets the desc temas.
	 *
	 * @return the descTemas
	 */
	public String getDescTemas() {
		return descTemas;
	}

	/**
	 * Sets the desc temas.
	 *
	 * @param descTemas the descTemas to set
	 */
	public void setDescTemas(String descTemas) {
		this.descTemas = descTemas;
	}

	/**
	 * Gets the desc dependencias.
	 *
	 * @return the descDependencias
	 */
	public String getDescDependencias() {
		return descDependencias;
	}

	/**
	 * Sets the desc dependencias.
	 *
	 * @param descDependencias the descDependencias to set
	 */
	public void setDescDependencias(String descDependencias) {
		this.descDependencias = descDependencias;
	}

	/**
	 * Gets the desc programas.
	 *
	 * @return the descProgramas
	 */
	public String getDescProgramas() {
		return descProgramas;
	}

	/**
	 * Sets the desc programas.
	 *
	 * @param descProgramas the descProgramas to set
	 */
	public void setDescProgramas(String descProgramas) {
		this.descProgramas = descProgramas;
	}

	public String getLineaBase() {
		return lineaBase;
	}

	public void setLineaBase(String lineaBase) {
		this.lineaBase = lineaBase;
	}

}