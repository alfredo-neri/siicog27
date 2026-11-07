package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TC_FOLIOS_CONTRARECIBO")
@NamedQuery(name = "FolioContrarecibo.findAll", query = "SELECT p FROM FolioContrarecibo p")
public class FolioContrarecibo extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8025532550989838722L;
	private Integer anio;
	@Column(name = "FOLIO_INICIAL")
	private Integer folioInicial;
	@Column(name = "FOLIO_ACTUAL")
	private Integer folioActual;
	@Column(name = "FOLIO_IDENTIFICACION")
	private String folioIdentificacion;

	public FolioContrarecibo() {

	}

	public FolioContrarecibo(Integer anio, Integer folioInicial, Integer folioActual, String folioIdentificacion) {
		this.anio = anio;
		this.folioInicial = folioInicial;
		this.folioActual = folioActual;
		this.folioIdentificacion = folioIdentificacion;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getFolioInicial() {
		return folioInicial;
	}

	public void setFolioInicial(Integer folioInicial) {
		this.folioInicial = folioInicial;
	}

	public Integer getFolioActual() {
		return folioActual;
	}

	public void setFolioActual(Integer folioActual) {
		this.folioActual = folioActual;
	}

	public String getFolioIdentificacion() {
		return folioIdentificacion;
	}

	public void setFolioIdentificacion(String folioIdentificacion) {
		this.folioIdentificacion = folioIdentificacion;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (this != null) {
			if (folioInicial == null || folioInicial == 0)
				errors.add("Debe de indicar el folio inicial");
			if (folioIdentificacion == null || folioIdentificacion.equals(""))
				errors.add("Debe de indicar el folio de identificacion para los contrarecibos");
		}
		return errors;
	}

}