package com.gem.sistema.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_IMAGENES_ENT_ADMIN database table.
 * 
 */
@Entity
@Table(name = "TC_IMAGENES_ENT_ADMIN")
@NamedQuery(name = "TcImagenesEntAdmin.findAll", query = "SELECT t FROM TcImagenesEntAdmin t")
public class TcImagenesEntAdmin extends AbstractEntity {
	private Integer clvmun;

	/** The id entidad admin. */
	@Column(name = "ID_ENTIDAD_ADMIN")
	private long idEntidadAdmin;

	/** The nombre file. */
	@Column(name = "NOMBRE_FILE")
	private String nombreFile;

	/** The path file. */
	@Column(name = "PATH_FILE")
	private String pathFile;
	
	

	/**
	 * Instantiates a new tc imagenes ent admin.
	 */
	public TcImagenesEntAdmin() {
	}

/**
 * Gets the id entidad admin.
 *
 * @return the id entidad admin
 */

	public Integer getClvmun() {
		return this.clvmun;
	}

	public void setClvmun(Integer clvmun) {
		this.clvmun = clvmun;
	}

	public long getIdEntidadAdmin() {
		return this.idEntidadAdmin;
	}

	/**
	 * Sets the id entidad admin.
	 *
	 * @param idEntidadAdmin the new id entidad admin
	 */
	public void setIdEntidadAdmin(long idEntidadAdmin) {
		this.idEntidadAdmin = idEntidadAdmin;
	}

	/**
	 * Gets the nombre file.
	 *
	 * @return the nombre file
	 */
	public String getNombreFile() {
		return this.nombreFile;
	}

	/**
	 * Sets the nombre file.
	 *
	 * @param nombreFile the new nombre file
	 */
	public void setNombreFile(String nombreFile) {
		this.nombreFile = nombreFile;
	}

	/**
	 * Gets the path file.
	 *
	 * @return the path file
	 */
	public String getPathFile() {
		return this.pathFile;
	}

	/**
	 * Sets the path file.
	 *
	 * @param pathFile the new path file
	 */
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

}