package com.gem.sistema.business.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class FlujosIncorrectosVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4977263638061562760L;

	private Long id;
	private String usuario;
	private String cuenta;
	private String scta;
	private String sscta;
	private String ssscta;
	private String sssscta;
	private String tippo;
	private Integer mespol;
	private Integer conpol;
	private BigDecimal renpol;
	private BigDecimal ca;
	private BigDecimal canflu;

	public FlujosIncorrectosVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getSssscta() {
		return sssscta;
	}

	public void setSssscta(String sssscta) {
		this.sssscta = sssscta;
	}

	public String getTippo() {
		return tippo;
	}

	public Integer getMespol() {
		return mespol;
	}

	public Integer getConpol() {
		return conpol;
	}

	public BigDecimal getRenpol() {
		return renpol;
	}

	public BigDecimal getCa() {
		return ca;
	}

	public BigDecimal getCanflu() {
		return canflu;
	}

	public void setTippo(String tippo) {
		this.tippo = tippo;
	}

	public void setMespol(Integer mespol) {
		this.mespol = mespol;
	}

	public void setConpol(Integer conpol) {
		this.conpol = conpol;
	}

	public void setRenpol(BigDecimal renpol) {
		this.renpol = renpol;
	}

	public void setCa(BigDecimal ca) {
		this.ca = ca;
	}

	public void setCanflu(BigDecimal canflu) {
		this.canflu = canflu;
	}

}
