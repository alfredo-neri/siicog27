package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the TW_METAS_INGRESO database table.
 * 
 */
@Entity
@Table(name = "TW_METAS_INGRESO")
@NamedQuery(name = "TwMetasIngreso.findAll", query = "SELECT t FROM TwMetasIngreso t")
public class TwMetasIngreso extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AUTORIZADO_1")
	private BigDecimal autorizado1;

	@Column(name = "AUTORIZADO_10")
	private BigDecimal autorizado10;

	@Column(name = "AUTORIZADO_11")
	private BigDecimal autorizado11;

	@Column(name = "AUTORIZADO_12")
	private BigDecimal autorizado12;

	@Column(name = "AUTORIZADO_2")
	private BigDecimal autorizado2;

	@Column(name = "AUTORIZADO_3")
	private BigDecimal autorizado3;

	@Column(name = "AUTORIZADO_4")
	private BigDecimal autorizado4;

	@Column(name = "AUTORIZADO_5")
	private BigDecimal autorizado5;

	@Column(name = "AUTORIZADO_6")
	private BigDecimal autorizado6;

	@Column(name = "AUTORIZADO_7")
	private BigDecimal autorizado7;

	@Column(name = "AUTORIZADO_8")
	private BigDecimal autorizado8;

	@Column(name = "AUTORIZADO_9")
	private BigDecimal autorizado9;

	@Transient
	private BigDecimal total;

	// bi-directional many-to-one association to TcIngresosPropio
	@ManyToOne
	@JoinColumn(name = "CLAVE_INGRESO", referencedColumnName = "CLAVE")
	private TcIngresosPropio tcIngresosPropio;

	public TwMetasIngreso() {
	}

	public BigDecimal getAutorizado1() {
		return this.autorizado1;
	}

	public void setAutorizado1(BigDecimal autorizado1) {
		this.autorizado1 = autorizado1;
	}

	public BigDecimal getAutorizado10() {
		return this.autorizado10;
	}

	public void setAutorizado10(BigDecimal autorizado10) {
		this.autorizado10 = autorizado10;
	}

	public BigDecimal getAutorizado11() {
		return this.autorizado11;
	}

	public void setAutorizado11(BigDecimal autorizado11) {
		this.autorizado11 = autorizado11;
	}

	public BigDecimal getAutorizado12() {
		return this.autorizado12;
	}

	public void setAutorizado12(BigDecimal autorizado12) {
		this.autorizado12 = autorizado12;
	}

	public BigDecimal getAutorizado2() {
		return this.autorizado2;
	}

	public void setAutorizado2(BigDecimal autorizado2) {
		this.autorizado2 = autorizado2;
	}

	public BigDecimal getAutorizado3() {
		return this.autorizado3;
	}

	public void setAutorizado3(BigDecimal autorizado3) {
		this.autorizado3 = autorizado3;
	}

	public BigDecimal getAutorizado4() {
		return this.autorizado4;
	}

	public void setAutorizado4(BigDecimal autorizado4) {
		this.autorizado4 = autorizado4;
	}

	public BigDecimal getAutorizado5() {
		return this.autorizado5;
	}

	public void setAutorizado5(BigDecimal autorizado5) {
		this.autorizado5 = autorizado5;
	}

	public BigDecimal getAutorizado6() {
		return this.autorizado6;
	}

	public void setAutorizado6(BigDecimal autorizado6) {
		this.autorizado6 = autorizado6;
	}

	public BigDecimal getAutorizado7() {
		return this.autorizado7;
	}

	public void setAutorizado7(BigDecimal autorizado7) {
		this.autorizado7 = autorizado7;
	}

	public BigDecimal getAutorizado8() {
		return this.autorizado8;
	}

	public void setAutorizado8(BigDecimal autorizado8) {
		this.autorizado8 = autorizado8;
	}

	public BigDecimal getAutorizado9() {
		return this.autorizado9;
	}

	public void setAutorizado9(BigDecimal autorizado9) {
		this.autorizado9 = autorizado9;
	}

	public TcIngresosPropio getTcIngresosPropio() {
		return this.tcIngresosPropio;
	}

	public BigDecimal getTotal() {
		total.add(autorizado1.add(autorizado2).add(autorizado3).add(autorizado4).add(autorizado5).add(autorizado6)
				.add(autorizado7).add(autorizado8).add(autorizado9).add(autorizado10).add(autorizado11)
				.add(autorizado12));
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setTcIngresosPropio(TcIngresosPropio tcIngresosPropio) {
		this.tcIngresosPropio = tcIngresosPropio;
	}

}