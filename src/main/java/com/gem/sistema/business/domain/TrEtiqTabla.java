package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TR_ETIQ_TABLAS database table.
 * 
 */
@Entity
@Table(name = "TR_ETIQ_TABLAS")
@NamedQuery(name = "TrEtiqTabla.findAll", query = "SELECT t FROM TrEtiqTabla t")
public class TrEtiqTabla extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ETIQUETA")
	private long idEtiqueta;

	@Column(name = "ID_TABLA")
	private long idTabla;

	@Column(name = "STATUS")
	private int status;

	public TrEtiqTabla() {
	}

	public long getIdEtiqueta() {
		return this.idEtiqueta;
	}

	public void setIdEtiqueta(long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public long getIdTabla() {
		return this.idTabla;
	}

	public void setIdTabla(long idTabla) {
		this.idTabla = idTabla;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}