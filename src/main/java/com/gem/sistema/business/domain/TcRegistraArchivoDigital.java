package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_REGISTRA_ARCHIVO_DIGITAL database table.
 * 
 */
@Entity
@Table(name="TC_REGISTRA_ARCHIVO_DIGITAL")
@NamedQuery(name="TcRegistraArchivoDigital.findAll", query="SELECT t FROM TcRegistraArchivoDigital t")
public class TcRegistraArchivoDigital extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anio. */
	@Column(name="ANIO")	
	private String anio;
	
	/** The tipo. */
	@Column(name="TIPO")
	private String tipo;
	
	/** The mes. */
	@Column(name="MES")
	private String mes;
	
	/** The numero. */
	@Column(name="NUMERO")
	private int numero;	
	
	/** The nombre archivo. */
	@Column(name="NOMBRE_ARCHIVO")	
	private String nombreArchivo;
	
	/** The path file. */
	@Column(name="PATH_FILE")
	private String pathFile;
	
	/** The user id. */
	@Column(name="USERID")
	private String userId;
	
	/** The sector id. */
	@Column(name="SECTORID")
	private int sectorId;
	
	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;
	
	
	/**
	 * Instantiates a new tc registra archivo digital.
	 */
	public TcRegistraArchivoDigital() {
	}


	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}


	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}


	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}


	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}


	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}


	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}


	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}


	/**
	 * Sets the nombre archivo.
	 *
	 * @param nombreArchivo the new nombre archivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	/**
	 * Gets the path file.
	 *
	 * @return the path file
	 */
	public String getPathFile() {
		return pathFile;
	}


	/**
	 * Sets the path file.
	 *
	 * @param pathFile the new path file
	 */
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
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
	 * Gets the sector id.
	 *
	 * @return the sector id
	 */
	public int getSectorId() {
		return sectorId;
	}


	/**
	 * Sets the sector id.
	 *
	 * @param sectorId the new sector id
	 */
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
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



}