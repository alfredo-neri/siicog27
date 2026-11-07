package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the TC_REPORTES_FIRMAS database table.
 * 
 */
@Entity
@Table(name="TC_REPORTES_FIRMAS")
@NamedQuery(name="TcReportesFirma.findAll", query="SELECT t FROM TcReportesFirma t")
public class TcReportesFirma extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clave;

	private String firmas;

	@Column(name="NAME_REPORT")
	private String nameReport;
	
	@Column(name="NAME_FILE")
	private String nameFile;

	@Column(name="NAME_FORMAT")
	private String nameFormat;
	
	@Column(name="ID_SECTOR")
	private Integer idSector = 0;

	public TcReportesFirma() {
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFirmas() {
		return this.firmas;
	}

	public void setFirmas(String firmas) {
		this.firmas = firmas;
	}

	public String getNameReport() {
		return nameReport;
	}

	public void setNameReport(String nameReport) {
		this.nameReport = nameReport;
	}

	public String getNameFile() {
		return this.nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getNameFormat() {
		return this.nameFormat;
	}

	public void setNameFormat(String nameFormat) {
		this.nameFormat = nameFormat;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

}