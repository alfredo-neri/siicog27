package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;


/**
 * The persistent class for the TC_PUESTOS database table.
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name="TC_PUESTOS")
@NamedQuery(name="TcPuesto.findAll", query="SELECT t FROM TcPuesto t")
public class TcPuesto extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer estatus = 0;

	@Column(name="ID_REF")
	private Integer idRef = 0;

	@Column(name="ID_SECTOR")
	private Integer idSector = 0;

	private String puesto = StringUtils.EMPTY;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLAVE")
	private TcClave clave;
	
	@OneToMany(mappedBy = "puesto")
	private List<TrPuestoFirma> firmas;

	public TcPuesto() {
	}

	public Integer getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Integer getIdRef() {
		return this.idRef;
	}

	public void setIdRef(Integer idRef) {
		this.idRef = idRef;
	}

	public Integer getIdSector() {
		return this.idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public TcClave getClave() {
		return clave;
	}

	public void setClave(TcClave clave) {
		this.clave = clave;
	}

	public List<TrPuestoFirma> getFirmas() {
		return firmas;
	}

	public void setFirmas(List<TrPuestoFirma> firmas) {
		this.firmas = firmas;
	}

}