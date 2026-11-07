package com.gem.sistema.business.dto;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0091202DTO.
 */
public class ReporteRF0091202DTO {
	
	/** The partida. */
	private String partida;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The aprobado. */
	private BigDecimal aprobado;
	
	/** The ampliacion. */
	private BigDecimal ampliacion;
	
	/** The reduccion. */
	private BigDecimal reduccion;
	
	/** The modifcacion. */
	private BigDecimal modifcacion;
	
	/** The comprometido. */
	private BigDecimal comprometido;
	
	/** The devengado. */
	private BigDecimal devengado;
	
	/** The pagado. */
	private BigDecimal pagado;
	
	/** The total pagado. */
	private BigDecimal totalPagado;
	
	/** The por ejercer. */
	private BigDecimal porEjercer;
	
	/**
	 * Instantiates a new reporte RF 0091202 DTO.
	 */
	public ReporteRF0091202DTO(){}
	
	/**
	 * Instantiates a new reporte RF 0091202 DTO.
	 *
	 * @param partida the partida
	 * @param descripcion the descripcion
	 * @param aprobado the aprobado
	 * @param ampliacion the ampliacion
	 * @param reduccion the reduccion
	 * @param modifcacion the modifcacion
	 * @param comprometido the comprometido
	 * @param devengado the devengado
	 * @param pagado the pagado
	 * @param totalPagado the total pagado
	 * @param porEjercer the por ejercer
	 */
	public ReporteRF0091202DTO(String partida,
			                   String descripcion,
	                           BigDecimal aprobado,
	                           BigDecimal ampliacion,
	                           BigDecimal reduccion,
	                           BigDecimal modifcacion,
	                           BigDecimal comprometido,
	                           BigDecimal devengado,
	                           BigDecimal pagado,
	                           BigDecimal totalPagado,
	                           BigDecimal porEjercer){
		this.partida     = partida;
		this.descripcion = descripcion;
		this.aprobado    = aprobado;
		this.ampliacion  = ampliacion;
		this.reduccion   = reduccion;
		this.modifcacion = modifcacion;
		this.comprometido = comprometido;
		this.devengado    = devengado;
		this.pagado       = pagado;
		this.totalPagado  = totalPagado;
		this.porEjercer   = porEjercer;
	}
	
	/**
	 * Gets the partida.
	 *
	 * @return the partida
	 */
	public String getPartida() {
		return partida;
	}
	
	/**
	 * Sets the partida.
	 *
	 * @param partida the new partida
	 */
	public void setPartida(String partida) {
		this.partida = partida;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the aprobado.
	 *
	 * @return the aprobado
	 */
	public BigDecimal getAprobado() {
		return aprobado;
	}
	
	/**
	 * Sets the aprobado.
	 *
	 * @param aprobado the new aprobado
	 */
	public void setAprobado(BigDecimal aprobado) {
		this.aprobado = aprobado;
	}
	
	/**
	 * Gets the ampliacion.
	 *
	 * @return the ampliacion
	 */
	public BigDecimal getAmpliacion() {
		return ampliacion;
	}
	
	/**
	 * Sets the ampliacion.
	 *
	 * @param ampliacion the new ampliacion
	 */
	public void setAmpliacion(BigDecimal ampliacion) {
		this.ampliacion = ampliacion;
	}
	
	/**
	 * Gets the reduccion.
	 *
	 * @return the reduccion
	 */
	public BigDecimal getReduccion() {
		return reduccion;
	}
	
	/**
	 * Sets the reduccion.
	 *
	 * @param reduccion the new reduccion
	 */
	public void setReduccion(BigDecimal reduccion) {
		this.reduccion = reduccion;
	}
	
	/**
	 * Gets the modifcacion.
	 *
	 * @return the modifcacion
	 */
	public BigDecimal getModifcacion() {
		return modifcacion;
	}
	
	/**
	 * Sets the modifcacion.
	 *
	 * @param modifcacion the new modifcacion
	 */
	public void setModifcacion(BigDecimal modifcacion) {
		this.modifcacion = modifcacion;
	}
	
	/**
	 * Gets the comprometido.
	 *
	 * @return the comprometido
	 */
	public BigDecimal getComprometido() {
		return comprometido;
	}
	
	/**
	 * Sets the comprometido.
	 *
	 * @param comprometido the new comprometido
	 */
	public void setComprometido(BigDecimal comprometido) {
		this.comprometido = comprometido;
	}
	
	/**
	 * Gets the devengado.
	 *
	 * @return the devengado
	 */
	public BigDecimal getDevengado() {
		return devengado;
	}
	
	/**
	 * Sets the devengado.
	 *
	 * @param devengado the new devengado
	 */
	public void setDevengado(BigDecimal devengado) {
		this.devengado = devengado;
	}
	
	/**
	 * Gets the pagado.
	 *
	 * @return the pagado
	 */
	public BigDecimal getPagado() {
		return pagado;
	}
	
	/**
	 * Sets the pagado.
	 *
	 * @param pagado the new pagado
	 */
	public void setPagado(BigDecimal pagado) {
		this.pagado = pagado;
	}
	
	/**
	 * Gets the total pagado.
	 *
	 * @return the total pagado
	 */
	public BigDecimal getTotalPagado() {
		return totalPagado;
	}
	
	/**
	 * Sets the total pagado.
	 *
	 * @param totalPagado the new total pagado
	 */
	public void setTotalPagado(BigDecimal totalPagado) {
		this.totalPagado = totalPagado;
	}
	
	/**
	 * Gets the por ejercer.
	 *
	 * @return the por ejercer
	 */
	public BigDecimal getPorEjercer() {
		return porEjercer;
	}
	
	/**
	 * Sets the por ejercer.
	 *
	 * @param porEjercer the new por ejercer
	 */
	public void setPorEjercer(BigDecimal porEjercer) {
		this.porEjercer = porEjercer;
	}
	
	

}
