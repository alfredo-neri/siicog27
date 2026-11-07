package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_TIPO_CONTRATOS database table.
 * 
 */
@Entity
@Table(name = "TC_TIPO_CONTRATOS")
@NamedQuery(name = "TcTipoContrato.findAll", query = "SELECT t FROM TcTipoContrato t")
public class TcTipoContrato extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tipo;

	public TcTipoContrato() {
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}