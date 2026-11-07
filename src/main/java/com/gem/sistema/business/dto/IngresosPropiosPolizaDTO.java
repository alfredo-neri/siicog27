package com.gem.sistema.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IngresosPropiosPolizaDTO implements Serializable {
	private static final long serialVersionUID = 7902769915722607532L;

	private String tippol;
	private Integer conpol;
	private Integer mespol;
	private BigDecimal renpol;
	private String cuenta;
	private String scta;
	private String sscta;
	private String ssscta;
	private BigDecimal canpolh;
	private BigDecimal ingreso;

	public IngresosPropiosPolizaDTO() {

	}

	public IngresosPropiosPolizaDTO(String tippol, Integer conpol, Integer mespol, BigDecimal renpol, String cuenta,
			String scta, String sscta, String ssscta, BigDecimal canpolh, BigDecimal ingreso) {
		super();
		this.tippol = tippol;
		this.conpol = conpol;
		this.mespol = mespol;
		this.renpol = renpol;
		this.cuenta = cuenta;
		this.scta = scta;
		this.sscta = sscta;
		this.ssscta = ssscta;
		this.canpolh = canpolh;
		this.ingreso = ingreso;
	}

	public String getTippol() {
		return tippol;
	}

	public void setTippol(String tippol) {
		this.tippol = tippol;
	}

	public Integer getConpol() {
		return conpol;
	}

	public void setConpol(Integer conpol) {
		this.conpol = conpol;
	}

	public Integer getMespol() {
		return mespol;
	}

	public void setMespol(Integer mespol) {
		this.mespol = mespol;
	}

	public BigDecimal getRenpol() {
		return renpol;
	}

	public void setRenpol(BigDecimal renpol) {
		this.renpol = renpol;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getScta() {
		return scta;
	}

	public void setScta(String scta) {
		this.scta = scta;
	}

	public String getSscta() {
		return sscta;
	}

	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	public String getSsscta() {
		return ssscta;
	}

	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	public BigDecimal getCanpolh() {
		return canpolh;
	}

	public void setCanpolh(BigDecimal canpolh) {
		this.canpolh = canpolh;
	}

	public BigDecimal getIngreso() {
		return ingreso;
	}

	public void setIngreso(BigDecimal ingreso) {
		this.ingreso = ingreso;
	}

}
