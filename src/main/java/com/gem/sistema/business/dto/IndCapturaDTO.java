package com.gem.sistema.business.dto;

import java.math.BigInteger;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class IndCapturaDTO.
 */
public class IndCapturaDTO {

	/** The clv dep. */
	private String clvDep;

	/** The clvnep. */
	private String clvnep;

	/** The des dep. */
	private String desDep;

	/** The clv programas. */
	private String clvProgramas;

	/** The des programas. */
	private String desProgramas;

	/** The des problemas oportunidades. */
	private String desProblemasOportunidades;

	/** The objetivos. */
	private String objetivos;

	/** The estrategias. */
	private String estrategias;

	/** The meta list. */
	private List<IndMetaDTO> metaList;

	/** The programas. */
	private List<IndCapturaDTO> programas;

	/** The clave dep. */
	private int claveDep;

	/** The anio cap. */
	private int anioCap;

	/** The num ver. */
	private int numVer;

	/** The clvfuen. */
	private String clvfuen;

	/** The user id. */
	private String userId;

	/** The idsector. */
	private int idsector;

	/** The id. */
	private BigInteger id;

	private String dimensionc;

	private String objPrograma;

	private String campo0;
	private String campo1;
	private String campo2;
	private String campo3;
	private String campo4;
	private String campo5;

	/**
	 * Instantiates a new ind captura DTO.
	 */
	public IndCapturaDTO() {
	}

	/**
	 * Instantiates a new ind captura DTO.
	 *
	 * @param clvDep                    the clv dep
	 * @param desDep                    the des dep
	 * @param desProblemasOportunidades the des problemas oportunidades
	 * @param objetivos                 the objetivos
	 * @param estrategias               the estrategias
	 * @param clvnep                    the clvnep
	 * @param id                        the id
	 */
	public IndCapturaDTO(String clvDep, String desDep, String desProblemasOportunidades, String objetivos,
			String estrategias, String clvnep, BigInteger id) {
		super();
		this.clvDep = clvDep;
		this.desDep = desDep;
		this.desProblemasOportunidades = desProblemasOportunidades;
		this.objetivos = objetivos;
		this.estrategias = estrategias;
		this.clvnep = clvnep;
		this.id = id;

	}

	public IndCapturaDTO(String clvDep, String desDep, String desProblemasOportunidades, String objetivos,
			String estrategias, String clvnep, BigInteger id, String dimension) {
		super();
		this.clvDep = clvDep;
		this.desDep = desDep;
		this.desProblemasOportunidades = desProblemasOportunidades;
		this.objetivos = objetivos;
		this.estrategias = estrategias;
		this.clvnep = clvnep;
		this.id = id;
		this.dimensionc = dimension;

	}

	public IndCapturaDTO(String clvDep, String desDep, String desProblemasOportunidades, String objetivos,
			String estrategias, String clvnep, BigInteger id, String dimension, String campo0, String campo1,
			String campo2, String campo3, String campo4, String campo5) {
		super();
		this.campo0 = campo0;
		this.campo1 = campo1;
		this.campo2 = campo2;
		this.campo3 = campo3;
		this.campo4 = campo4;
		this.campo5 = campo5;
		this.clvDep = clvDep;
		this.desDep = desDep;
		this.desProblemasOportunidades = desProblemasOportunidades;
		this.objetivos = objetivos;
		this.estrategias = estrategias;
		this.clvnep = clvnep;
		this.id = id;
		this.dimensionc = dimension;

	}

	/**
	 * Instantiates a new ind captura DTO.
	 *
	 * @param clvProgramas the clv programas
	 * @param desProgramas the des programas
	 */
	public IndCapturaDTO(String clvProgramas, String desProgramas) {
		super();
		this.clvProgramas = clvProgramas;
		this.desProgramas = desProgramas;
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
	 * Gets the des dep.
	 *
	 * @return the des dep
	 */
	public String getDesDep() {
		return desDep;
	}

	/**
	 * Sets the des dep.
	 *
	 * @param desDep the new des dep
	 */
	public void setDesDep(String desDep) {
		this.desDep = desDep;
	}

	/**
	 * Gets the clv programas.
	 *
	 * @return the clv programas
	 */
	public String getClvProgramas() {
		return clvProgramas;
	}

	/**
	 * Sets the clv programas.
	 *
	 * @param clvProgramas the new clv programas
	 */
	public void setClvProgramas(String clvProgramas) {
		this.clvProgramas = clvProgramas;
	}

	/**
	 * Gets the des programas.
	 *
	 * @return the des programas
	 */
	public String getDesProgramas() {
		return desProgramas;
	}

	/**
	 * Sets the des programas.
	 *
	 * @param desProgramas the new des programas
	 */
	public void setDesProgramas(String desProgramas) {
		this.desProgramas = desProgramas;
	}

	/**
	 * Gets the des problemas oportunidades.
	 *
	 * @return the des problemas oportunidades
	 */
	public String getDesProblemasOportunidades() {
		return desProblemasOportunidades;
	}

	/**
	 * Sets the des problemas oportunidades.
	 *
	 * @param desProblemasOportunidades the new des problemas oportunidades
	 */
	public void setDesProblemasOportunidades(String desProblemasOportunidades) {
		this.desProblemasOportunidades = desProblemasOportunidades;
	}

	/**
	 * Gets the objetivos.
	 *
	 * @return the objetivos
	 */
	public String getObjetivos() {
		return objetivos;
	}

	/**
	 * Sets the objetivos.
	 *
	 * @param objetivos the new objetivos
	 */
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	/**
	 * Gets the estrategias.
	 *
	 * @return the estrategias
	 */
	public String getEstrategias() {
		return estrategias;
	}

	/**
	 * Sets the estrategias.
	 *
	 * @param estrategias the new estrategias
	 */
	public void setEstrategias(String estrategias) {
		this.estrategias = estrategias;
	}

	/**
	 * Gets the meta list.
	 *
	 * @return the meta list
	 */
	public List<IndMetaDTO> getMetaList() {
		return metaList;
	}

	/**
	 * Sets the meta list.
	 *
	 * @param metaList the new meta list
	 */
	public void setMetaList(List<IndMetaDTO> metaList) {
		this.metaList = metaList;
	}

	/**
	 * Gets the programas.
	 *
	 * @return the programas
	 */
	public List<IndCapturaDTO> getProgramas() {
		return programas;
	}

	/**
	 * Sets the programas.
	 *
	 * @param programas the new programas
	 */
	public void setProgramas(List<IndCapturaDTO> programas) {
		this.programas = programas;
	}

	/**
	 * Gets the clvnep.
	 *
	 * @return the clvnep
	 */
	public String getClvnep() {
		return clvnep;
	}

	/**
	 * Sets the clvnep.
	 *
	 * @param clvnep the new clvnep
	 */
	public void setClvnep(String clvnep) {
		this.clvnep = clvnep;
	}

	/**
	 * Gets the clave dep.
	 *
	 * @return the clave dep
	 */
	public int getClaveDep() {
		return claveDep;
	}

	/**
	 * Sets the clave dep.
	 *
	 * @param claveDep the new clave dep
	 */
	public void setClaveDep(int claveDep) {
		this.claveDep = claveDep;
	}

	/**
	 * Gets the anio cap.
	 *
	 * @return the anio cap
	 */
	public int getAnioCap() {
		return anioCap;
	}

	/**
	 * Sets the anio cap.
	 *
	 * @param anioCap the new anio cap
	 */
	public void setAnioCap(int anioCap) {
		this.anioCap = anioCap;
	}

	/**
	 * Gets the num ver.
	 *
	 * @return the num ver
	 */
	public int getNumVer() {
		return numVer;
	}

	/**
	 * Sets the num ver.
	 *
	 * @param numVer the new num ver
	 */
	public void setNumVer(int numVer) {
		this.numVer = numVer;
	}

	/**
	 * Gets the clvfuen.
	 *
	 * @return the clvfuen
	 */
	public String getClvfuen() {
		return clvfuen;
	}

	/**
	 * Sets the clvfuen.
	 *
	 * @param clvfuen the new clvfuen
	 */
	public void setClvfuen(String clvfuen) {
		this.clvfuen = clvfuen;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IndCapturaDTO [clvDep=" + clvDep + ", clvnep=" + clvnep + ", desDep=" + desDep + ", clvProgramas="
				+ clvProgramas + ", desProgramas=" + desProgramas + ", desProblemasOportunidades="
				+ desProblemasOportunidades + ", objetivos=" + objetivos + ", estrategias=" + estrategias
				+ ", metaList=" + metaList + ", programas=" + programas + ", claveDep=" + claveDep + ", anioCap="
				+ anioCap + ", numVer=" + numVer + ", clvfuen=" + clvfuen + ", userId=" + userId + ", idsector="
				+ idsector + ", id=" + id + "]";
	}

	public String getDimensionc() {
		return dimensionc;
	}

	public void setDimensionc(String dimensionc) {
		this.dimensionc = dimensionc;
	}

	public String getObjPrograma() {
		return objPrograma;
	}

	public void setObjPrograma(String objPrograma) {
		this.objPrograma = objPrograma;
	}

	public String getCampo0() {
		return campo0;
	}

	public void setCampo0(String campo0) {
		this.campo0 = campo0;
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getCampo3() {
		return campo3;
	}

	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	public String getCampo4() {
		return campo4;
	}

	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	public String getCampo5() {
		return campo5;
	}

	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}

}
