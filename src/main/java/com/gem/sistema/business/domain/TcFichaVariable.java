package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

/**
 * The persistent class for the TC_FICHA_VARIABLES database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TC_FICHA_VARIABLES")
@NamedQuery(name = "TcFichaVariable.findAll", query = "SELECT t FROM TcFichaVariable t")
public class TcFichaVariable extends AbstractEntity implements Comparator<TcFichaVariable>, Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_VARIABLE", nullable = false)
	private Variables variable;

	@Column(name = "META_ANUAL")
	private BigDecimal metaAnual;

	private String operacion;

	@Column(name = "UNIDAD_MEDIDA")
	private String unidadMedida;

	@OneToMany(targetEntity = TcVariablesTrimestre.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ID_FICHA_VARIABLE", referencedColumnName = "id", nullable = false)
	private List<TcVariablesTrimestre> trimestres;

	public TcFichaVariable() {
		this.codigo = BigInteger.ZERO.intValue();
		this.metaAnual = BigDecimal.ZERO;
		this.operacion = StringUtils.EMPTY;
		this.unidadMedida = StringUtils.EMPTY;
		this.variable = new Variables();
	}
	
	@Override
	public int compare(TcFichaVariable o1, TcFichaVariable o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Variables getVariable() {
		return variable;
	}

	public void setVariable(Variables variable) {
		this.variable = variable;
	}

	public List<TcVariablesTrimestre> getTrimestres() {
		return trimestres;
	}

	public void setTrimestres(List<TcVariablesTrimestre> trimestres) {
		this.trimestres = trimestres;
	}

	public BigDecimal getMetaAnual() {
		return this.metaAnual;
	}

	public void setMetaAnual(BigDecimal metaAnual) {
		this.metaAnual = metaAnual;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public List<TcVariablesTrimestre> getTcVariablesTrimestres() {
		return this.getTcVariablesTrimestres();
	}

}