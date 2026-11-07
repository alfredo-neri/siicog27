package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the TC_STATUS_PAGO database table.
 * 
 */
@Entity
@Table(name = "TC_STATUS_PAGO")
@NamedQuery(name = "TcStatusPago.findAll", query = "SELECT t FROM TcStatusPago t")
public class TcStatusPago extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clave;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_AT")
	private Date createAt;

	private String descripcion;

	public TcStatusPago() {
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}