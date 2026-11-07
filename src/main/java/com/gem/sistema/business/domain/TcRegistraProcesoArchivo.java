package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_REGISTRA_PROCESO_ARCHIVO database table.
 * 
 */
@Entity
@Table(name="TC_REGISTRA_PROCESO_ARCHIVO")
@NamedQuery(name="TcRegistraProcesoArchivo.findAll", query="SELECT t FROM TcRegistraProcesoArchivo t")
public class TcRegistraProcesoArchivo extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha proceso. */
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_PROCESO")
	private Date fechaProceso;

	/** The nombre archivo orginal. */
	@Column(name="NOMBRE_ARCHIVO_ORGINAL")
	private String nombreArchivoOrginal;

	/** The nombre archivo procesado. */
	@Column(name="NOMBRE_ARCHIVO_PROCESADO")
	private String nombreArchivoProcesado;

	/** The nombre archivo respuesta. */
	@Column(name="NOMBRE_ARCHIVO_RESPUESTA")
	private String nombreArchivoRespuesta;

	/** The registros correctos. */
	@Column(name="REGISTROS_CORRECTOS")
	private BigDecimal registrosCorrectos;

	/** The registros incorrectos. */
	@Column(name="REGISTROS_INCORRECTOS")
	private BigDecimal registrosIncorrectos;

	/** The registros procesados. */
	@Column(name="REGISTROS_PROCESADOS")
	private BigDecimal registrosProcesados;

	/** The ruta archivo. */
	@Column(name="RUTA_ARCHIVO")
	private String rutaArchivo;

	/** The usuario. */
	private String usuario;

	/**
	 * Instantiates a new tc registra proceso archivo.
	 */
	public TcRegistraProcesoArchivo() {
	}

	

	/**
	 * Gets the fecha proceso.
	 *
	 * @return the fecha proceso
	 */
	public Date getFechaProceso() {
		return this.fechaProceso;
	}

	/**
	 * Sets the fecha proceso.
	 *
	 * @param fechaProceso the new fecha proceso
	 */
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * Gets the nombre archivo orginal.
	 *
	 * @return the nombre archivo orginal
	 */
	public String getNombreArchivoOrginal() {
		return this.nombreArchivoOrginal;
	}

	/**
	 * Sets the nombre archivo orginal.
	 *
	 * @param nombreArchivoOrginal the new nombre archivo orginal
	 */
	public void setNombreArchivoOrginal(String nombreArchivoOrginal) {
		this.nombreArchivoOrginal = nombreArchivoOrginal;
	}

	/**
	 * Gets the nombre archivo procesado.
	 *
	 * @return the nombre archivo procesado
	 */
	public String getNombreArchivoProcesado() {
		return this.nombreArchivoProcesado;
	}

	/**
	 * Sets the nombre archivo procesado.
	 *
	 * @param nombreArchivoProcesado the new nombre archivo procesado
	 */
	public void setNombreArchivoProcesado(String nombreArchivoProcesado) {
		this.nombreArchivoProcesado = nombreArchivoProcesado;
	}

	/**
	 * Gets the nombre archivo respuesta.
	 *
	 * @return the nombre archivo respuesta
	 */
	public String getNombreArchivoRespuesta() {
		return this.nombreArchivoRespuesta;
	}

	/**
	 * Sets the nombre archivo respuesta.
	 *
	 * @param nombreArchivoRespuesta the new nombre archivo respuesta
	 */
	public void setNombreArchivoRespuesta(String nombreArchivoRespuesta) {
		this.nombreArchivoRespuesta = nombreArchivoRespuesta;
	}

	/**
	 * Gets the registros correctos.
	 *
	 * @return the registros correctos
	 */
	public BigDecimal getRegistrosCorrectos() {
		return this.registrosCorrectos;
	}

	/**
	 * Sets the registros correctos.
	 *
	 * @param registrosCorrectos the new registros correctos
	 */
	public void setRegistrosCorrectos(BigDecimal registrosCorrectos) {
		this.registrosCorrectos = registrosCorrectos;
	}

	/**
	 * Gets the registros incorrectos.
	 *
	 * @return the registros incorrectos
	 */
	public BigDecimal getRegistrosIncorrectos() {
		return this.registrosIncorrectos;
	}

	/**
	 * Sets the registros incorrectos.
	 *
	 * @param registrosIncorrectos the new registros incorrectos
	 */
	public void setRegistrosIncorrectos(BigDecimal registrosIncorrectos) {
		this.registrosIncorrectos = registrosIncorrectos;
	}

	/**
	 * Gets the registros procesados.
	 *
	 * @return the registros procesados
	 */
	public BigDecimal getRegistrosProcesados() {
		return this.registrosProcesados;
	}

	/**
	 * Sets the registros procesados.
	 *
	 * @param registrosProcesados the new registros procesados
	 */
	public void setRegistrosProcesados(BigDecimal registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}

	/**
	 * Gets the ruta archivo.
	 *
	 * @return the ruta archivo
	 */
	public String getRutaArchivo() {
		return this.rutaArchivo;
	}

	/**
	 * Sets the ruta archivo.
	 *
	 * @param rutaArchivo the new ruta archivo
	 */
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
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