package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "TB_PROCESOS")
@NamedQuery(name = "TbProceso.findAll", query = "SELECT p FROM TbProceso p")
public class TbProceso extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1823379693415362498L;

	@Column(name = "NOMBRE_PROCESO")
	private String nombreProceso;

	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;
	private String ruta;
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	private String detalle;

	public String getTiempoEjecucion() {
		if (fechaFin == null) {
			long diff = new Date().getTime() - fechaInicio.getTime();
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(diff),
					TimeUnit.MILLISECONDS.toMinutes(diff)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)),
					TimeUnit.MILLISECONDS.toSeconds(diff)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));

			return "Ejecutandose " + hms;

		} else {
			long diff = fechaFin.getTime() - fechaInicio.getTime();
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(diff),
					TimeUnit.MILLISECONDS.toMinutes(diff)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)),
					TimeUnit.MILLISECONDS.toSeconds(diff)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));
			return hms;
		}
	}

	public TbProceso() {

	}

	public TbProceso(String nombreProceso, String nombreUsuario, String ruta, String detalle) {
		this.nombreProceso = nombreProceso;
		this.nombreUsuario = nombreUsuario;
		this.ruta = ruta;
		this.detalle = detalle;
	}

	public TbProceso(String nombreProceso, String nombreUsuario, String ruta) {
		this.nombreProceso = nombreProceso;
		this.nombreUsuario = nombreUsuario;
		this.ruta = ruta;
		this.fechaInicio = new Date();
	}

	public TbProceso(String nombreProceso, String nombreUsuario, String ruta, Date fechaInicio, Date fechaFin) {
		this.nombreProceso = nombreProceso;
		this.nombreUsuario = nombreUsuario;
		this.ruta = ruta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}