package com.gem.sistema.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_IMAGENES_MUNI database table.
 * 
 */
@Entity
@Table(name="TC_IMAGENES_MUNI")
@NamedQuery(name="TcImagenesMuni.findAll", query="SELECT t FROM TcImagenesMuni t")
public class TcImagenesMuni extends AbstractEntity  {

	/** The clvmun. */
	private int clvmun;

	/** The nombre file. */
	@Column(name="NOMBRE_FILE")
	private String nombreFile;

	/** The path file. */
	@Column(name="PATH_FILE")
	private String pathFile;

	/**
	 * Instantiates a new tc imagenes muni.
	 */
	public TcImagenesMuni() {
	}

	/**
	 * Gets the clvmun.
	 *
	 * @return the clvmun
	 */
	public int getClvmun() {
		return this.clvmun;
	}

	/**
	 * Sets the clvmun.
	 *
	 * @param clvmun the new clvmun
	 */
	public void setClvmun(int clvmun) {
		this.clvmun = clvmun;
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