package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_VALORES database table.
 * 
 */
@Entity
@Table(name = "TC_VALORES")
@NamedQuery(name = "TcValores.findAll", query = "SELECT t FROM TcValores t")
public class TcValores extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ETIQ_TABLA")
	private long idEtiqTabla;

	@Column(name = "ID_ROW")
	private Integer idRow;

	private String valor;

	public TcValores() {
	}

	public long getIdEtiqTabla() {
		return this.idEtiqTabla;
	}

	public void setIdEtiqTabla(long idEtiqTabla) {
		this.idEtiqTabla = idEtiqTabla;
	}

	public Integer getIdRow() {
		return this.idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}