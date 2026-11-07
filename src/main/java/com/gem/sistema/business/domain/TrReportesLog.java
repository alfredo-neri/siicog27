package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TR_REPORTES_LOG database table.
 * 
 */
@Entity
@Table(name="TR_REPORTES_LOG")
@NamedQuery(name="TrReportesLog.findAll", query="SELECT t FROM TrReportesLog t")
public class TrReportesLog implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id proceso. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROCESO")
	private long idProceso;

	/** The cant registros. */
	@Column(name="CANT_REGISTROS")
	private long cantRegistros;

	/** The fecha fin. */
	@Column(name="FECHA_FIN")
	private Timestamp fechaFin;

	/** The fecha ini. */
	@Column(name="FECHA_INI")
	private Timestamp fechaIni;

	/** The usuario. */
	private String usuario;

	/** The tc reporte. */
	//bi-directional many-to-one association to TcReporte
	@ManyToOne
	@JoinColumn(name="ID_REPORTE")
	private TcReporte tcReporte;

	/**
	 * Instantiates a new tr reportes log.
	 */
	public TrReportesLog() {
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return the id proceso
	 */
	public long getIdProceso() {
		return this.idProceso;
	}

	/**
	 * Sets the id proceso.
	 *
	 * @param idProceso the new id proceso
	 */
	public void setIdProceso(long idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * Gets the cant registros.
	 *
	 * @return the cant registros
	 */
	public long getCantRegistros() {
		return this.cantRegistros;
	}

	/**
	 * Sets the cant registros.
	 *
	 * @param cantRegistros the new cant registros
	 */
	public void setCantRegistros(long cantRegistros) {
		this.cantRegistros = cantRegistros;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Gets the fecha ini.
	 *
	 * @return the fecha ini
	 */
	public Timestamp getFechaIni() {
		return this.fechaIni;
	}

	/**
	 * Sets the fecha ini.
	 *
	 * @param fechaIni the new fecha ini
	 */
	public void setFechaIni(Timestamp fechaIni) {
		this.fechaIni = fechaIni;
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
	 * Gets the tc reporte.
	 *
	 * @return the tc reporte
	 */
	public TcReporte getTcReporte() {
		return this.tcReporte;
	}

	/**
	 * Sets the tc reporte.
	 *
	 * @param tcReporte the new tc reporte
	 */
	public void setTcReporte(TcReporte tcReporte) {
		this.tcReporte = tcReporte;
	}

}