package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.model.SelectItem;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ConciliacionPolizaDEService;
import com.gem.sistema.business.vo.ConciliacionPolizaDEVO;

/**
 * Managed bean de la consulta de conciliacion de polizas de Diario contra
 * Egreso por referencia, sobre cuentas de orden 82xx.
 *
 * El ejercicio se toma de la configuracion contable del sector (CONCTB) y el
 * usuario solo elige el mes. Las referencias que no concilian en ese mes se
 * completan con la poliza del mes inmediato anterior, y las polizas de Egreso que
 * cubren las cuatro etapas presupuestarias se dan por conciliadas sin Diario.
 *
 * Los filtros de estatus y referencia se aplican en memoria sobre el resultado ya
 * consultado, sin volver a la base de datos.
 *
 * @author Alfredo Neri
 */
@Component("conciliacionPolizaDEMB")
@Scope("view")
public class ConciliacionPolizaDEMB extends AbstractMB {

	/** Valor del filtro que no descarta ningun registro. */
	public static final String FILTRO_TODOS = "TODOS";

	/** Valor del filtro que deja solo las referencias repetidas. */
	public static final String FILTRO_REPETIDAS = "REPETIDAS";

	/** Tipo de periodo del catalogo TC_PERIODO que corresponde a los meses. */
	private static final Integer TIPO_PERIODO_MES = Integer.valueOf(1);

	@Value("#{conciliacionPolizaDEService}")
	private ConciliacionPolizaDEService conciliacionPolizaDEService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	private List<TcPeriodo> listaMeses;

	/** Resultado completo de la consulta, sin filtrar. */
	private List<ConciliacionPolizaDEVO> listaCompleta;

	/** Subconjunto visible y exportable, resultado de aplicar los filtros. */
	private List<ConciliacionPolizaDEVO> listaConciliacion;

	private List<SelectItem> listaEstatus;

	private Integer mes;

	private Integer anio;

	private String filtroEstatus;

	private String filtroReferencia;

	private int totalConciliadas;

	private int totalPrevios;

	private int totalPorMovimientos;

	private int totalPendientes;

	private int totalMultiples;

	private boolean consultaEjecutada;

	/**
	 * Inicializa los catalogos de la pantalla y resuelve el ejercicio contable del
	 * sector del usuario en sesion.
	 */
	@PostConstruct
	public void init() {
		this.listaMeses = periodoRepositoy.findByTipoPeriodo(TIPO_PERIODO_MES);
		if (this.listaMeses == null || this.listaMeses.isEmpty()) {
			this.listaMeses = new ArrayList<TcPeriodo>();
			displayErrorMessage("No hay periodos configurados en el catalogo TC_PERIODO. "
					+ "No es posible seleccionar el mes a consultar.");
		} else {
			this.mes = this.listaMeses.get(0).getPeriodo();
		}

		this.listaEstatus = new ArrayList<SelectItem>();
		this.listaEstatus.add(new SelectItem(FILTRO_TODOS, "Todos"));
		this.listaEstatus.add(new SelectItem(ConciliacionPolizaDEVO.ESTATUS_CONCILIADA, "Conciliadas"));
		this.listaEstatus
				.add(new SelectItem(ConciliacionPolizaDEVO.ESTATUS_CONCILIADA_PREVIO, "Conciliadas en mes previo"));
		this.listaEstatus.add(
				new SelectItem(ConciliacionPolizaDEVO.ESTATUS_CONCILIADA_MOVIMIENTOS, "Conciliadas por movimientos"));
		this.listaEstatus.add(new SelectItem(ConciliacionPolizaDEVO.ESTATUS_SIN_EGRESO, "Sin egreso"));
		this.listaEstatus.add(new SelectItem(ConciliacionPolizaDEVO.ESTATUS_SIN_DIARIO, "Sin diario"));
		this.listaEstatus.add(new SelectItem(FILTRO_REPETIDAS, "Referencias repetidas"));
		this.filtroEstatus = FILTRO_TODOS;
		this.filtroReferencia = null;

		this.listaCompleta = new ArrayList<ConciliacionPolizaDEVO>();
		this.listaConciliacion = new ArrayList<ConciliacionPolizaDEVO>();
		this.consultaEjecutada = false;

		this.anio = this.conciliacionPolizaDEService.getAnioEjercicio(this.getUserDetails().getIdSector());
		if (this.anio == null) {
			displayErrorMessage("No se encontro la configuracion contable del sector. "
					+ "Verifique el catalogo CONCTB antes de ejecutar la consulta.");
		}
	}

	/**
	 * Ejecuta la consulta para el mes seleccionado y el ejercicio del sector.
	 */
	public void buscar() {
		this.limpiarResultados();

		if (this.anio == null) {
			displayErrorMessage("No es posible consultar: el sector no tiene ejercicio contable configurado.");
			return;
		}
		if (this.mes == null) {
			displayWarnMessage("Seleccione el mes que desea consultar.");
			return;
		}

		this.listaCompleta = this.conciliacionPolizaDEService.findByPeriodo(this.anio, this.mes,
				this.getUserDetails().getIdSector());
		this.consultaEjecutada = true;
		this.calcularTotales();
		this.aplicarFiltros();

		if (this.listaCompleta.isEmpty()) {
			displayInfoMessage("No se encontraron referencias en cuentas de orden 82 para el mes seleccionado.");
			return;
		}
		if (this.totalMultiples > 0) {
			displayWarnMessage("Se encontraron " + this.totalMultiples
					+ " referencia(s) repetidas en mas de una poliza del mismo tipo. "
					+ "En esos renglones se muestra solo una de ellas y requieren revision manual.");
		}
	}

	/**
	 * Aplica los filtros de estatus y referencia sobre el resultado ya consultado.
	 *
	 * Se invoca desde la pantalla al cambiar cualquiera de los dos filtros. La
	 * lista resultante es la que se muestra y la que se exporta, de modo que la
	 * descarga siempre coincide con lo que el usuario esta viendo.
	 */
	public void aplicarFiltros() {
		List<ConciliacionPolizaDEVO> filtrada = new ArrayList<ConciliacionPolizaDEVO>();
		String referencia = this.filtroReferencia == null ? "" : this.filtroReferencia.trim().toUpperCase();

		for (ConciliacionPolizaDEVO conciliacion : this.listaCompleta) {
			if (!this.cumpleFiltroEstatus(conciliacion)) {
				continue;
			}
			if (referencia.length() > 0) {
				String refpol = conciliacion.getRefpol() == null ? "" : conciliacion.getRefpol().toUpperCase();
				if (refpol.indexOf(referencia) < 0) {
					continue;
				}
			}
			filtrada.add(conciliacion);
		}
		this.listaConciliacion = filtrada;
	}

	/**
	 * Evalua el filtro de estatus para un registro.
	 *
	 * @param conciliacion
	 *            registro a evaluar
	 * @return true cuando el registro debe mostrarse
	 */
	private boolean cumpleFiltroEstatus(ConciliacionPolizaDEVO conciliacion) {
		if (this.filtroEstatus == null || FILTRO_TODOS.equals(this.filtroEstatus)) {
			return true;
		}
		if (FILTRO_REPETIDAS.equals(this.filtroEstatus)) {
			return conciliacion.isMultiple();
		}
		return this.filtroEstatus.equals(conciliacion.getEstatus());
	}

	/**
	 * Limpia los resultados de una consulta previa.
	 */
	public void limpiarResultados() {
		this.listaCompleta = new ArrayList<ConciliacionPolizaDEVO>();
		this.listaConciliacion = new ArrayList<ConciliacionPolizaDEVO>();
		this.totalConciliadas = 0;
		this.totalPrevios = 0;
		this.totalPorMovimientos = 0;
		this.totalPendientes = 0;
		this.totalMultiples = 0;
		this.consultaEjecutada = false;
	}

	/**
	 * Acumula los totales por estatus para el resumen de la pantalla. Se calculan
	 * sobre el resultado completo, no sobre el filtrado.
	 */
	private void calcularTotales() {
		for (ConciliacionPolizaDEVO conciliacion : this.listaCompleta) {
			if (conciliacion.isTieneD() && conciliacion.isTieneE()) {
				if (conciliacion.isConciliadaEnMesPrevio()) {
					this.totalPrevios++;
				} else {
					this.totalConciliadas++;
				}
			} else if (conciliacion.isConciliadaPorMovimientos()) {
				this.totalPorMovimientos++;
			} else {
				this.totalPendientes++;
			}
			if (conciliacion.isMultiple()) {
				this.totalMultiples++;
			}
		}
	}

	/**
	 * Nombre del archivo de exportacion, con el periodo consultado.
	 *
	 * @return nombre base del archivo, sin extension
	 */
	public String getNombreArchivo() {
		return "conciliacion_polizas_DE_" + this.anio + "_" + this.mes;
	}

	/**
	 * Indica si hay resultados visibles que exportar.
	 *
	 * @return true cuando la lista filtrada tiene registros
	 */
	public boolean isHayResultados() {
		return this.listaConciliacion != null && !this.listaConciliacion.isEmpty();
	}

	/**
	 * Numero de registros visibles tras aplicar los filtros.
	 *
	 * @return cantidad de renglones mostrados
	 */
	public int getTotalMostrados() {
		return this.listaConciliacion == null ? 0 : this.listaConciliacion.size();
	}

	/**
	 * Numero de registros que devolvio la consulta antes de filtrar.
	 *
	 * @return cantidad total de referencias del periodo
	 */
	public int getTotalRegistros() {
		return this.listaCompleta == null ? 0 : this.listaCompleta.size();
	}

	public ConciliacionPolizaDEService getConciliacionPolizaDEService() {
		return conciliacionPolizaDEService;
	}

	public void setConciliacionPolizaDEService(ConciliacionPolizaDEService conciliacionPolizaDEService) {
		this.conciliacionPolizaDEService = conciliacionPolizaDEService;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public List<TcPeriodo> getListaMeses() {
		return listaMeses;
	}

	public void setListaMeses(List<TcPeriodo> listaMeses) {
		this.listaMeses = listaMeses;
	}

	public List<ConciliacionPolizaDEVO> getListaCompleta() {
		return listaCompleta;
	}

	public void setListaCompleta(List<ConciliacionPolizaDEVO> listaCompleta) {
		this.listaCompleta = listaCompleta;
	}

	public List<ConciliacionPolizaDEVO> getListaConciliacion() {
		return listaConciliacion;
	}

	public void setListaConciliacion(List<ConciliacionPolizaDEVO> listaConciliacion) {
		this.listaConciliacion = listaConciliacion;
	}

	public List<SelectItem> getListaEstatus() {
		return listaEstatus;
	}

	public void setListaEstatus(List<SelectItem> listaEstatus) {
		this.listaEstatus = listaEstatus;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getFiltroEstatus() {
		return filtroEstatus;
	}

	public void setFiltroEstatus(String filtroEstatus) {
		this.filtroEstatus = filtroEstatus;
	}

	public String getFiltroReferencia() {
		return filtroReferencia;
	}

	public void setFiltroReferencia(String filtroReferencia) {
		this.filtroReferencia = filtroReferencia;
	}

	public int getTotalConciliadas() {
		return totalConciliadas;
	}

	public void setTotalConciliadas(int totalConciliadas) {
		this.totalConciliadas = totalConciliadas;
	}

	public int getTotalPrevios() {
		return totalPrevios;
	}

	public void setTotalPrevios(int totalPrevios) {
		this.totalPrevios = totalPrevios;
	}

	public int getTotalPorMovimientos() {
		return totalPorMovimientos;
	}

	public void setTotalPorMovimientos(int totalPorMovimientos) {
		this.totalPorMovimientos = totalPorMovimientos;
	}

	public int getTotalPendientes() {
		return totalPendientes;
	}

	public void setTotalPendientes(int totalPendientes) {
		this.totalPendientes = totalPendientes;
	}

	public int getTotalMultiples() {
		return totalMultiples;
	}

	public void setTotalMultiples(int totalMultiples) {
		this.totalMultiples = totalMultiples;
	}

	public boolean isConsultaEjecutada() {
		return consultaEjecutada;
	}

	public void setConsultaEjecutada(boolean consultaEjecutada) {
		this.consultaEjecutada = consultaEjecutada;
	}

}
