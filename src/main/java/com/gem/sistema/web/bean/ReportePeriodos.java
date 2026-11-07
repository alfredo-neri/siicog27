package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

public abstract class ReportePeriodos extends BaseDirectReport {

	protected static final String MES = "MES";
	protected static final String SEMESTRE = "SEMESTRE";
	protected static final String ANUAL = "ANUAL";
	protected static final String TRIMESTRE = "TRIMESTRE";

	protected List<String> tiposPeridos = Arrays.asList(TRIMESTRE, MES, ANUAL, SEMESTRE);
	protected String tipoPeriodo = TRIMESTRE;

	protected Boolean acumulado = false;
	/** Valida si el reporte compara mes actual contra mes anterior */
	protected Boolean mesAnterior = false;
	protected TcPeriodo periodo;
	protected List<TcPeriodo> periodos;

	@ManagedProperty(value = "#{tcPeriodoRepositoy}")
	protected TcPeriodoRepositoy periodoRepositoy;

	/**
	 * cambia la lista de peridos de acuerdo al tipo de periodo seleccionado debe de
	 * invocarse en @PostConstruct
	 */
	public void changePeriodo() {
		switch (tipoPeriodo) {
		case MES:
			periodos = periodoRepositoy.findByTipoPeriodo(1);
			break;
		case SEMESTRE:
			periodos = periodoRepositoy.findByTipoPeriodo(6);
			break;
		case TRIMESTRE:
			periodos = periodoRepositoy.findByTipoPeriodo(3);
			break;
		case ANUAL:
			periodos = new ArrayList<TcPeriodo>();
			break;
		}
		if (!periodos.isEmpty()) {
			periodo = periodos.get(0);
		}
	}

	/** Optiene el mes apartidr del periodo seleccionado */
	protected Integer getMesSelected() {
		return tipoPeriodo.equals(MES) ? periodo.getPeriodo()
				: tipoPeriodo.equals(ANUAL) ? 12 : periodo.getPeriodo() * periodo.getTipoPeriodo();
	}

	/**
	 * Optiene el mes inicial a partir del periodo seleccionado y la bandera de
	 * acumulado
	 */
	protected Integer getMesInicial() {
		if (acumulado) {
			return 1;
		} else {
			switch (tipoPeriodo) {
			case MES:
				return periodo.getPeriodo() == 1 ? 1 : mesAnterior ? periodo.getPeriodo() - 1 : periodo.getPeriodo();
			case SEMESTRE:
			case TRIMESTRE:
				return (periodo.getPeriodo() * periodo.getTipoPeriodo()) - periodo.getTipoPeriodo() + 1;
			case ANUAL:
				return 1;
			default:
				return 1;
			}
		}

	}

	/** Optiene el nombre del mes actual a partir del periodo */
	protected String getNombreMesSelected() {
		return periodoRepositoy.findByTipoPeriodoAndPeriodo(1, getMesSelected()).getDescripcion();
	}

	/** Optiene el nombre del mes inicial a partir del periodo */
	protected String getNombreMesInicial() {
		return periodoRepositoy.findByTipoPeriodoAndPeriodo(1, getMesInicial()).getDescripcion();
	}

	public List<String> getTiposPeridos() {
		return tiposPeridos;
	}

	public void setTiposPeridos(List<String> tiposPeridos) {
		this.tiposPeridos = tiposPeridos;
	}

	public List<TcPeriodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<TcPeriodo> periodos) {
		this.periodos = periodos;
	}

	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public TcPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(TcPeriodo periodo) {
		this.periodo = periodo;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public Boolean getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(Boolean acumulado) {
		this.acumulado = acumulado;
	}

	protected Boolean getMesAnterior() {
		return mesAnterior;
	}

	protected void setMesAnterior(Boolean mesAnterior) {
		this.mesAnterior = mesAnterior;
	}

}
