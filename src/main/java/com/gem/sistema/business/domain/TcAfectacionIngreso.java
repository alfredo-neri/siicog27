package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TC_AFECTACION_INGRESO")
@NamedQuery(name = "TcAfectacionIngreso.findAll", query = "SELECT t FROM TcAfectacionIngreso t")
public class TcAfectacionIngreso extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	private int mes;
	private String status;

	public TcAfectacionIngreso() {
	}

	public String getFullStatus() {
		String salida = this.status.equals("N") ? "Afectar" : "Desafectar";
		return salida;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}