package com.gem.sistema.business.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.Actividad;
import com.gem.sistema.business.domain.Componente;
import com.gem.sistema.business.domain.Finalidad;
import com.gem.sistema.business.domain.Proposito;

// TODO: Auto-generated Javadoc
/**
 * The Class MatrizIndicadoresDTO.
 */
public class MatrizIndicadoresDTO {

	/** The id. */
	private long id;
	
	/** The clvdepg. */
	private String clvdepg;
	
	/** The clvdepg desc. */
	private String clvdepgDesc;
	
	/** The componente. */
	private int componente;
	
	/** The cvefin. */
	private String cvefin;
	
	/** The cveprog. */
	private String cveprog;
	
	/** The cve prog desc. */
	private String cveProgDesc;
	
	/** The cvetemas. */
	private String cvetemas;
	
	/** The cve tema desc. */
	private String cveTemaDesc;
	
	/** The feccap. */
	private Date feccap;
	
	/** The finalidad. */
	private int finalidad;
	
	/** The id ref. */
	private long idRef;
	
	/** The idsector. */
	private int idsector;
	
	/** The objetivo. */
	private String objetivo;
	
	/** The proposito. */
	private int proposito;
	
	/** The userid. */
	private String userid;
	
	/** The usuario. */
	private String usuario;
	
	/** The lista finalidad. */
	private List<Finalidad> listaFinalidad = new ArrayList<Finalidad>();
	
	/** The lista proposito. */
	private List<Proposito> listaProposito = new ArrayList<Proposito>();
	
	/** The lista componente. */
	private List<Componente> listaComponente = new ArrayList<Componente>();
	
	/** The lista actividad. */
	private List<Actividad> listaActividad = new ArrayList<Actividad>();
	
	/** The codigo indicador F. */
	private String codigoIndicadorF;
	
	/** The codigo indicador P. */
	private String codigoIndicadorP;
	
	/** The codigo indicador C. */
	private String codigoIndicadorC;
	
	/** The codigo indicador A. */
	private String codigoIndicadorA;
	
	/** The num indicador F. */
	private String numIndicadorF;
	
	/** The num indicador P. */
	private String numIndicadorP;
	
	/** The num indicador C. */
	private String numIndicadorC;
	
	/** The num indicador A. */
	private String numIndicadorA;

	/** The programas map. */
	private Map<String, String> programasMap = new HashMap<String, String>();

	/** The clvdep. */
	private String clvdep;
	
	/** The clvfun. */
	private String clvfun;
	
	/** The nombre. */
	private String nombre;
	
	/** The clave. */
	private String clave;
	
	/** The campo 7. */
	private String campo7;
	
	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new matriz indicadores DTO.
	 */
	public MatrizIndicadoresDTO() {
	}

	/**
	 * Instantiates a new matriz indicadores DTO.
	 *
	 * @param id the id
	 * @param clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param nombre the nombre
	 * @param clave the clave
	 * @param campo7 the campo 7
	 * @param descripcion the descripcion
	 * @param cvetemas the cvetemas
	 */
	public MatrizIndicadoresDTO(Long id, String clvdep, String clvfun, String nombre, String clave, String campo7,
			String descripcion, String cvetemas) {
		this.id = id;
		this.clvdep = clvdep;
		this.clvfun = clvfun;
		this.nombre = nombre;
		this.clave = clave;
		this.campo7 = campo7;
		this.descripcion = descripcion;
		this.cvetemas = cvetemas;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
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
		return clvdepg;
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
		return componente;
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
		return cvefin;
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
		return cveprog;
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
		return cvetemas;
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
	 * Gets the finalidad.
	 *
	 * @return the finalidad
	 */
	public int getFinalidad() {
		return finalidad;
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
		return idRef;
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
	 * Gets the objetivo.
	 *
	 * @return the objetivo
	 */
	public String getObjetivo() {
		return objetivo;
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
		return proposito;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
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
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
	public String getClvdep() {
		return clvdep;
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
	 * Gets the clvfun.
	 *
	 * @return the clvfun
	 */
	public String getClvfun() {
		return clvfun;
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the campo 7.
	 *
	 * @return the campo 7
	 */
	public String getCampo7() {
		return campo7;
	}

	/**
	 * Sets the campo 7.
	 *
	 * @param campo7 the new campo 7
	 */
	public void setCampo7(String campo7) {
		this.campo7 = campo7;
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

	/**
	 * Gets the clvdepg desc.
	 *
	 * @return the clvdepg desc
	 */
	public String getClvdepgDesc() {
		return clvdepgDesc;
	}

	/**
	 * Sets the clvdepg desc.
	 *
	 * @param clvdepgDesc the new clvdepg desc
	 */
	public void setClvdepgDesc(String clvdepgDesc) {
		this.clvdepgDesc = clvdepgDesc;
	}

	/**
	 * Gets the cve prog desc.
	 *
	 * @return the cve prog desc
	 */
	public String getCveProgDesc() {
		return cveProgDesc;
	}

	/**
	 * Sets the cve prog desc.
	 *
	 * @param cveProgDesc the new cve prog desc
	 */
	public void setCveProgDesc(String cveProgDesc) {
		this.cveProgDesc = cveProgDesc;
	}

	/**
	 * Gets the cve tema desc.
	 *
	 * @return the cve tema desc
	 */
	public String getCveTemaDesc() {
		return cveTemaDesc;
	}

	/**
	 * Sets the cve tema desc.
	 *
	 * @param cveTemaDesc the new cve tema desc
	 */
	public void setCveTemaDesc(String cveTemaDesc) {
		this.cveTemaDesc = cveTemaDesc;
	}

	/**
	 * Gets the lista finalidad.
	 *
	 * @return the lista finalidad
	 */
	public List<Finalidad> getListaFinalidad() {
		return listaFinalidad;
	}

	/**
	 * Sets the lista finalidad.
	 *
	 * @param listaFinalidad the new lista finalidad
	 */
	public void setListaFinalidad(List<Finalidad> listaFinalidad) {
		this.listaFinalidad = listaFinalidad;
	}

	/**
	 * Gets the lista proposito.
	 *
	 * @return the lista proposito
	 */
	public List<Proposito> getListaProposito() {
		return listaProposito;
	}

	/**
	 * Sets the lista proposito.
	 *
	 * @param listaProposito the new lista proposito
	 */
	public void setListaProposito(List<Proposito> listaProposito) {
		this.listaProposito = listaProposito;
	}

	/**
	 * Gets the lista componente.
	 *
	 * @return the lista componente
	 */
	public List<Componente> getListaComponente() {
		return listaComponente;
	}

	/**
	 * Sets the lista componente.
	 *
	 * @param listaComponente the new lista componente
	 */
	public void setListaComponente(List<Componente> listaComponente) {
		this.listaComponente = listaComponente;
	}

	/**
	 * Gets the lista actividad.
	 *
	 * @return the lista actividad
	 */
	public List<Actividad> getListaActividad() {
		return listaActividad;
	}

	/**
	 * Sets the lista actividad.
	 *
	 * @param listaActividad the new lista actividad
	 */
	public void setListaActividad(List<Actividad> listaActividad) {
		this.listaActividad = listaActividad;
	}

	/**
	 * Gets the codigo indicador F.
	 *
	 * @return the codigo indicador F
	 */
	public String getCodigoIndicadorF() {
		return codigoIndicadorF;
	}

	/**
	 * Sets the codigo indicador F.
	 *
	 * @param codigoIndicadorF the new codigo indicador F
	 */
	public void setCodigoIndicadorF(String codigoIndicadorF) {
		this.codigoIndicadorF = codigoIndicadorF;
	}

	/**
	 * Gets the programas map.
	 *
	 * @return the programas map
	 */
	public Map<String, String> getProgramasMap() {
		return programasMap;
	}

	/**
	 * Sets the programas map.
	 *
	 * @param programasMap the programas map
	 */
	public void setProgramasMap(Map<String, String> programasMap) {
		this.programasMap = programasMap;
	}

	/**
	 * Gets the codigo indicador P.
	 *
	 * @return the codigo indicador P
	 */
	public String getCodigoIndicadorP() {
		return codigoIndicadorP;
	}

	/**
	 * Sets the codigo indicador P.
	 *
	 * @param codigoIndicadorP the new codigo indicador P
	 */
	public void setCodigoIndicadorP(String codigoIndicadorP) {
		this.codigoIndicadorP = codigoIndicadorP;
	}

	/**
	 * Gets the codigo indicador C.
	 *
	 * @return the codigo indicador C
	 */
	public String getCodigoIndicadorC() {
		return codigoIndicadorC;
	}

	/**
	 * Sets the codigo indicador C.
	 *
	 * @param codigoIndicadorC the new codigo indicador C
	 */
	public void setCodigoIndicadorC(String codigoIndicadorC) {
		this.codigoIndicadorC = codigoIndicadorC;
	}

	/**
	 * Gets the codigo indicador A.
	 *
	 * @return the codigo indicador A
	 */
	public String getCodigoIndicadorA() {
		return codigoIndicadorA;
	}

	/**
	 * Sets the codigo indicador A.
	 *
	 * @param codigoIndicadorA the new codigo indicador A
	 */
	public void setCodigoIndicadorA(String codigoIndicadorA) {
		this.codigoIndicadorA = codigoIndicadorA;
	}

	/**
	 * Gets the num indicador F.
	 *
	 * @return the num indicador F
	 */
	public String getNumIndicadorF() {
		return numIndicadorF;
	}

	/**
	 * Sets the num indicador F.
	 *
	 * @param numIndicadorF the new num indicador F
	 */
	public void setNumIndicadorF(String numIndicadorF) {
		this.numIndicadorF = numIndicadorF;
	}

	/**
	 * Gets the num indicador P.
	 *
	 * @return the num indicador P
	 */
	public String getNumIndicadorP() {
		return numIndicadorP;
	}

	/**
	 * Sets the num indicador P.
	 *
	 * @param numIndicadorP the new num indicador P
	 */
	public void setNumIndicadorP(String numIndicadorP) {
		this.numIndicadorP = numIndicadorP;
	}

	/**
	 * Gets the num indicador C.
	 *
	 * @return the num indicador C
	 */
	public String getNumIndicadorC() {
		return numIndicadorC;
	}

	/**
	 * Sets the num indicador C.
	 *
	 * @param numIndicadorC the new num indicador C
	 */
	public void setNumIndicadorC(String numIndicadorC) {
		this.numIndicadorC = numIndicadorC;
	}

	/**
	 * Gets the num indicador A.
	 *
	 * @return the num indicador A
	 */
	public String getNumIndicadorA() {
		return numIndicadorA;
	}

	/**
	 * Sets the num indicador A.
	 *
	 * @param numIndicadorA the new num indicador A
	 */
	public void setNumIndicadorA(String numIndicadorA) {
		this.numIndicadorA = numIndicadorA;
	}

}
