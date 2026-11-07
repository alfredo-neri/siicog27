package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

/**
 * The persistent class for the TC_ARTICULOS_ADQUISICIONES database table.
 * 
 */
@Entity
@Table(name = "TC_ARTICULOS_ADQUISICIONES")
@NamedQuery(name = "TcArticulosSA.findAll", query = "SELECT t FROM TcArticulosSA t")
public class TcArticulosSA extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1868637895748115259L;

	private Integer renglon;
	private String descripcion;
	@Column(name = "DESCRIPCION_TECNICA")
	private String descripcionTecnica;
	/**
	 * Copia desnormalizada de la descripcion de la unidad. NO se elimina: el
	 * reporte SolicitudArticulos.jrxml hace SELECT * y lee esta columna, y seis
	 * puntos de contratos.xhtml / solicitudesAdquisicion.xhtml la pintan directo.
	 * Se mantiene sincronizada desde {@link #setUnidad(TcUnidadMedida)}.
	 */
	@Column(name = "UNIDAD_MEDIDA")
	private String unidadMedida;

	/** Unidad de medida del catalogo TC_UNIDADES_MEDIDA */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_UNIDAD_MEDIDA", nullable = true)
	private TcUnidadMedida unidad;

	private String clave;
	private Integer cantidad;
	private BigDecimal precio;
	@Transient
	private BigDecimal subTotal;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ADQUISICION")
	private TcAdquisicion adquisicion;

	public TcArticulosSA() {
	}

	public Integer getRenglon() {
		return renglon;
	}

	public void setRenglon(Integer renglon) {
		this.renglon = renglon;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public TcUnidadMedida getUnidad() {
		return unidad;
	}

	/**
	 * Asigna la unidad del catalogo y copia su descripcion al texto que leen los
	 * reportes y las vistas.
	 */
	public void setUnidad(TcUnidadMedida unidad) {
		this.unidad = unidad;
		if (unidad != null && unidad.getDescripcion() != null) {
			this.unidadMedida = unidad.getDescripcion();
		}
	}

	/**
	 * Red de seguridad. Si la relacion se fijo por merge o por reflexion sin pasar
	 * por el setter, el texto se vuelve a copiar antes de grabar.
	 */
	@PrePersist
	@PreUpdate
	public void sincronizaUnidadMedida() {
		if (unidad != null && unidad.getDescripcion() != null) {
			this.unidadMedida = unidad.getDescripcion();
		}
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getSubTotal() {
		return precio.multiply(new BigDecimal(cantidad));
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public TcAdquisicion getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(TcAdquisicion adquisicion) {
		this.adquisicion = adquisicion;
	}

	public String getDescripcionTecnica() {
		return descripcionTecnica;
	}

	public void setDescripcionTecnica(String descripcionTecnica) {
		this.descripcionTecnica = descripcionTecnica;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}