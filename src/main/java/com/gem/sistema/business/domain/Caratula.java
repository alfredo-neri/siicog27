package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * The persistent class for the INGRESOT database table.
 * 
 */
@Entity
@Table(name = "INGRESOT")
@NamedQuery(name = "Caratula.findAll", query = "SELECT c FROM Caratula c")
public class Caratula extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal auto;

	@Column(name = "AUTOI_1")
	private BigDecimal autoi1 = BigDecimal.ZERO;

	@Column(name = "AUTOI_2")
	private BigDecimal autoi2 = BigDecimal.ZERO;

	@Column(name = "AUTOI_3")
	private BigDecimal autoi3 = BigDecimal.ZERO;

	@Column(name = "AUTOI_4")
	private BigDecimal autoi4 = BigDecimal.ZERO;

	@Column(name = "AUTOI_5")
	private BigDecimal autoi5 = BigDecimal.ZERO;

	private String cuenta;

	@Column(name = "ID_REF")
	private long idRef;

	private String nombre = StringUtils.EMPTY;

	private String scta = StringUtils.EMPTY;

	private int sectorid;

	private String sscta = StringUtils.EMPTY;

	private String ssscta = StringUtils.EMPTY;

	private String sssscta = StringUtils.EMPTY;

	@Column(name = "USERID")
	private String userid;

	public Caratula() {
	}

	public BigDecimal getAuto() {
		return this.auto;
	}

	public void setAuto(BigDecimal auto) {
		this.auto = auto;
	}

	public BigDecimal getAutoi1() {
		return this.autoi1;
	}

	public void setAutoi1(BigDecimal autoi1) {
		this.autoi1 = autoi1;
	}

	public BigDecimal getAutoi2() {
		return this.autoi2;
	}

	public void setAutoi2(BigDecimal autoi2) {
		this.autoi2 = autoi2;
	}

	public BigDecimal getAutoi3() {
		return this.autoi3;
	}

	public void setAutoi3(BigDecimal autoi3) {
		this.autoi3 = autoi3;
	}

	public BigDecimal getAutoi4() {
		return this.autoi4;
	}

	public void setAutoi4(BigDecimal autoi4) {
		this.autoi4 = autoi4;
	}

	public BigDecimal getAutoi5() {
		return this.autoi5;
	}

	public void setAutoi5(BigDecimal autoi5) {
		this.autoi5 = autoi5;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public long getIdRef() {
		return this.idRef;
	}

	public void setIdRef(long idRef) {
		this.idRef = idRef;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getScta() {
		return this.scta;
	}

	public void setScta(String scta) {
		this.scta = scta;
	}

	public int getSectorid() {
		return this.sectorid;
	}

	public void setSectorid(int sectorid) {
		this.sectorid = sectorid;
	}

	public String getSscta() {
		return this.sscta;
	}

	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	public String getSsscta() {
		return this.ssscta;
	}

	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	public String getSssscta() {
		return this.sssscta;
	}

	public void setSssscta(String sssscta) {
		this.sssscta = sssscta;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}