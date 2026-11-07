package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;


/**
 * The persistent class for the TR_PUESTO_FIRMA database table.
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name="TR_PUESTO_FIRMA")
@NamedQuery(name="TrPuestoFirma.findAll", query="SELECT t FROM TrPuestoFirma t")
public class TrPuestoFirma extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ID_ANIO")
	private Long idAnio =0L;

	private String nombre = StringUtils.EMPTY;
	
	@ManyToOne
	@JoinColumn(name = "ID_PUESTOS")
	private TcPuesto puesto;

	public TrPuestoFirma() {
	}

	public Long getIdAnio() {
		return this.idAnio;
	}

	public void setIdAnio(Long idAnio) {
		this.idAnio = idAnio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TcPuesto getPuesto() {
		return puesto;
	}

	public void setPuesto(TcPuesto puesto) {
		this.puesto = puesto;
	}

}