package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_REPORTES database table.
 * 
 */
@Entity
@Table(name = "TC_REPORTES")
@NamedQuery(name = "TcReporte.findAll", query = "SELECT t FROM TcReporte t")
public class TcReporte implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id reporte. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REPORTE")
	private long idReporte;

	/** The boton label. */
	@Column(name = "BOTON_LABEL")
	private String botonLabel;

	/** The nombre archivo. */
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;

	/** The nombre reporte. */
	@Column(name = "NOMBRE_REPORTE")
	private String nombreReporte;

	/** The qry 1. */
	private String qry1;

	/** The qry 2. */
	private String qry2;

	/** The qry 3. */
	private String qry3;

	/** The ruta archivo. */
	@Column(name = "RUTA_ARCHIVO")
	private String rutaArchivo;

	@Column(name = "TIPO_ARCHIVO")
	private String tipoArchivo;

	/** The tr reportes logs. */
	// bi-directional many-to-one association to TrReportesLog
	@OneToMany(mappedBy = "tcReporte")
	private List<TrReportesLog> trReportesLogs;

	/**
	 * Instantiates a new tc reporte.
	 */
	public TcReporte() {
	}

	/**
	 * Gets the id reporte.
	 *
	 * @return the id reporte
	 */
	public long getIdReporte() {
		return this.idReporte;
	}

	/**
	 * Sets the id reporte.
	 *
	 * @param idReporte the new id reporte
	 */
	public void setIdReporte(long idReporte) {
		this.idReporte = idReporte;
	}

	/**
	 * Gets the boton label.
	 *
	 * @return the boton label
	 */
	public String getBotonLabel() {
		return this.botonLabel;
	}

	/**
	 * Sets the boton label.
	 *
	 * @param botonLabel the new boton label
	 */
	public void setBotonLabel(String botonLabel) {
		this.botonLabel = botonLabel;
	}

	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombreArchivo() {
		return this.nombreArchivo;
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
	 * Gets the nombre reporte.
	 *
	 * @return the nombre reporte
	 */
	public String getNombreReporte() {
		return this.nombreReporte;
	}

	/**
	 * Sets the nombre reporte.
	 *
	 * @param nombreReporte the new nombre reporte
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	/**
	 * Gets the qry 1.
	 *
	 * @return the qry 1
	 */
	public String getQry1() {
		return this.qry1;
	}

	/**
	 * Sets the qry 1.
	 *
	 * @param qry1 the new qry 1
	 */
	public void setQry1(String qry1) {
		this.qry1 = qry1;
	}

	/**
	 * Gets the qry 2.
	 *
	 * @return the qry 2
	 */
	public String getQry2() {
		return this.qry2;
	}

	/**
	 * Sets the qry 2.
	 *
	 * @param qry2 the new qry 2
	 */
	public void setQry2(String qry2) {
		this.qry2 = qry2;
	}

	/**
	 * Gets the qry 3.
	 *
	 * @return the qry 3
	 */
	public String getQry3() {
		return this.qry3;
	}

	/**
	 * Sets the qry 3.
	 *
	 * @param qry3 the new qry 3
	 */
	public void setQry3(String qry3) {
		this.qry3 = qry3;
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
	 * Gets the tr reportes logs.
	 *
	 * @return the tr reportes logs
	 */
	public List<TrReportesLog> getTrReportesLogs() {
		return this.trReportesLogs;
	}

	/**
	 * Sets the tr reportes logs.
	 *
	 * @param trReportesLogs the new tr reportes logs
	 */
	public void setTrReportesLogs(List<TrReportesLog> trReportesLogs) {
		this.trReportesLogs = trReportesLogs;
	}

	/**
	 * Adds the tr reportes log.
	 *
	 * @param trReportesLog the tr reportes log
	 * @return the tr reportes log
	 */
	public TrReportesLog addTrReportesLog(TrReportesLog trReportesLog) {
		getTrReportesLogs().add(trReportesLog);
		trReportesLog.setTcReporte(this);

		return trReportesLog;
	}

	/**
	 * Removes the tr reportes log.
	 *
	 * @param trReportesLog the tr reportes log
	 * @return the tr reportes log
	 */
	public TrReportesLog removeTrReportesLog(TrReportesLog trReportesLog) {
		getTrReportesLogs().remove(trReportesLog);
		trReportesLog.setTcReporte(null);

		return trReportesLog;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

}