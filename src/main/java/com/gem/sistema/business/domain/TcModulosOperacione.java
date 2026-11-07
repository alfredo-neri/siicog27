package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_MODULOS_OPERACIONES database table.
 * 
 */
@Entity
@Table(name = "TC_MODULOS_OPERACIONES")
@NamedQuery(name = "TcModulosOperacione.findAll", query = "SELECT t FROM TcModulosOperacione t")
public class TcModulosOperacione extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CLV_PROCESO")
	private String clvProceso;

	@Column(name = "ID_SECTOR")
	private int idSector;

	@Column(name = "NOMBRE_PROCESO")
	private String nombreProceso;

	public TcModulosOperacione() {
	}

	public String getClvProceso() {
		return this.clvProceso;
	}

	public void setClvProceso(String clvProceso) {
		this.clvProceso = clvProceso;
	}

	public int getIdSector() {
		return this.idSector;
	}

	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

	public String getNombreProceso() {
		return this.nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

}