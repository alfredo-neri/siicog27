package com.gem.sistema.business.dto;

import java.math.BigInteger;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MetasFisicasDTO.
 */
public class MetasFisicasDTO {

	/** The id. */
	private BigInteger id;
	
	/** The clv dep. */
	private String clvDep;
	
	/** The nompro. */
	private String nompro;
	
	/** The clvnep. */
	private String clvnep;
	
	/** The des proyecto. */
	private String desProyecto;
	
	/** The clvfuen. */
	private String clvfuen;
	
	/** The desfuen. */
	private String desfuen;
	
	/** The objetivos. */
	private String objetivos;
	
	/** The detalle. */
	private List<MetasFisicasDetalleDTO> detalle;
	
	/** The clave dep. */
	private int claveDep;
	
	/** The anio cap. */
	private int anioCap;
	
	/** The num ver. */
	private int numVer;
	
	/** The user id. */
	private String userId;
	
	/** The idsector. */
	private int idsector;
	
	/**
	 * Instantiates a new metas fisicas DTO.
	 */
	public MetasFisicasDTO() {
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
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public List<MetasFisicasDetalleDTO> getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle the new detalle
	 */
	public void setDetalle(List<MetasFisicasDetalleDTO> detalle) {
		this.detalle = detalle;
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

	/**
	 * Gets the des proyecto.
	 *
	 * @return the des proyecto
	 */
	public String getDesProyecto() {
		return desProyecto;
	}

	/**
	 * Sets the des proyecto.
	 *
	 * @param desProyecto the new des proyecto
	 */
	public void setDesProyecto(String desProyecto) {
		this.desProyecto = desProyecto;
	}

	/**
	 * Gets the desfuen.
	 *
	 * @return the desfuen
	 */
	public String getDesfuen() {
		return desfuen;
	}

	/**
	 * Sets the desfuen.
	 *
	 * @param desFuen the new desfuen
	 */
	public void setDesfuen(String desFuen) {
		this.desfuen = desFuen;
	}

	/**
	 * Gets the nompro.
	 *
	 * @return the nompro
	 */
	public String getNompro() {
		return nompro;
	}

	/**
	 * Sets the nompro.
	 *
	 * @param nompro the new nompro
	 */
	public void setNompro(String nompro) {
		this.nompro = nompro;
	}

}
