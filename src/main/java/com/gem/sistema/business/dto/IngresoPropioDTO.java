/**
 * 
 */
package com.gem.sistema.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gem.sistema.business.domain.TwIngresoPropiosDetalle;

public class IngresoPropioDTO implements Serializable {
	private static final long serialVersionUID = 7902769915722607532L;

	private Integer codError;
	private List<String> msgsError = new ArrayList<String>();
	private String fullFilePath;
	private BigDecimal total;
	private List<TwIngresoPropiosDetalle> cargados = new ArrayList<TwIngresoPropiosDetalle>();

	public IngresoPropioDTO() {
	}

	public IngresoPropioDTO(Integer codError, List<String> msgsError, String fullFilePath, BigDecimal total) {
		this.codError = codError;
		this.msgsError = msgsError;
		this.fullFilePath = fullFilePath;
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getCodError() {
		return codError;
	}

	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	public String getFullFilePath() {
		return fullFilePath;
	}

	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}

	public List<String> getMsgsError() {
		return msgsError;
	}

	public void setMsgsError(List<String> msgsError) {
		this.msgsError = msgsError;
	}

	public void addMsn(String msn) {
		this.msgsError.add(msn);
	}

	public List<TwIngresoPropiosDetalle> getCargados() {
		return cargados;
	}

	public void setCargados(List<TwIngresoPropiosDetalle> cargados) {
		this.cargados = cargados;
	};

}
