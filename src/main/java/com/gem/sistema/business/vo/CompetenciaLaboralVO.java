package com.gem.sistema.business.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompetenciaLaboralVO {

	@Id
	private Integer idRow;
	private Integer idEtiqueta;
	private String nombre;
	private String valor;

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public Integer getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(Integer idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
