package com.gem.sistema.web.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;

import com.gem.sistema.business.domain.TcFicha;
import com.gem.sistema.business.domain.TcFichaTrimestre;
import com.gem.sistema.business.domain.TcFichaVariable;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcVariablesTrimestre;
import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.service.indicadores.MatrizFichasIndicadoresService;
import com.gem.sistema.util.UtilFront;

/**
 * @author Alfredo
 * @Timeline <strong>Date: </strong>01/12/2021 <strong>Changes: </strong>Se
 *           ajusta componentes a nueva reglas de negocio.<br>
 *           <strong>Date: </strong>14/03/2022 <strong>Changes: </strong>Se
 *           agrega busqueda por dependencia General<br>
 * 
 * @sector Municipal
 */
@ViewScoped
@ManagedBean(name = "fichaSeguimientoMB")
public class FichaSeguimientoMB extends AbstractMB {

	private String generalSearch = StringUtils.EMPTY;
	private String codigoSearch = StringUtils.EMPTY;

	private Boolean editing = Boolean.FALSE;
	private Boolean editingVariable = Boolean.FALSE;
	private List<TcFicha> listFichas;
	private List<Variables> listVariables;
	private List<Variables> filteredVariables;
	private Variables variableSelected;

	private Integer currentPage = 0;
	private Integer currentPageVariable = 0;

	@ManagedProperty("#{matrizFichasIndicadoresService}")
	private MatrizFichasIndicadoresService matrizFichasIndicadoresService;

	@PostConstruct
	public void init() {
		listFichas = matrizFichasIndicadoresService.listadoFichas(this.getUserDetails().getIdSector());
		if (!CollectionUtils.isEmpty(listFichas)) {
			Collections.sort(listFichas.get(currentPage).getVariables(), compareByCodigo);
		}

		listVariables = matrizFichasIndicadoresService.getListadoVariables();

	}

	public void search() {
		if (StringUtils.isNotEmpty(generalSearch) || StringUtils.isNotEmpty(codigoSearch)) {
			listFichas = matrizFichasIndicadoresService.listadoFichas(generalSearch, codigoSearch);
			if (!CollectionUtils.isEmpty(listFichas)) {
				currentPage = 0;
				currentPageVariable = 0;
				Collections.sort(listFichas.get(currentPage).getVariables(), compareByCodigo);
			}

		}
	}

	public void porcentajes(Integer indexTrimestre) {
		TcFichaTrimestre trimestre = listFichas.get(currentPage).getTrimestres().get(indexTrimestre);

		if (trimestre.getProgramada().compareTo(BigDecimal.ZERO) != 0) {
			listFichas.get(currentPage).getTrimestres().get(indexTrimestre).setPorcentaje(trimestre.getAlcanzada()
					.multiply(new BigDecimal("100.0")).divide(trimestre.getProgramada(), 4, RoundingMode.CEILING));
		}

		BigDecimal sumProgramado = BigDecimal.ZERO;
		BigDecimal sumAlcanzada = BigDecimal.ZERO;
		for (int i = 0; i <= indexTrimestre; i++) {
			sumProgramado = sumProgramado.add(listFichas.get(currentPage).getTrimestres().get(i).getProgramada());
			sumAlcanzada = sumAlcanzada.add(listFichas.get(currentPage).getTrimestres().get(i).getAlcanzada());
		}

		if (sumProgramado.compareTo(BigDecimal.ZERO) != 0) {
			listFichas.get(currentPage).getTrimestres().get(indexTrimestre).setPorcentajeAlcanzada(
					sumAlcanzada.multiply(new BigDecimal("100.0").divide(sumProgramado, 4, RoundingMode.CEILING)));
		}
	}

	public void calcularPorcentaje(boolean programado, Integer indexTrimestre) {
		TcFichaVariable variable = listFichas.get(currentPage).getVariables().get(currentPageVariable);

		if (programado) {
			if (variable.getMetaAnual().compareTo(BigDecimal.ZERO) != 0) {
				listFichas.get(currentPage).getVariables().get(currentPageVariable).getTrimestres().get(indexTrimestre)
						.setPorcentaje(variable.getTrimestres().get(indexTrimestre).getProgramada()
								.multiply(new BigDecimal("100.0"))
								.divide(variable.getMetaAnual(), 4, RoundingMode.CEILING));
			}
		} else {
			if (variable.getMetaAnual().compareTo(BigDecimal.ZERO) != 0) {
				listFichas.get(currentPage).getVariables().get(currentPageVariable).getTrimestres().get(indexTrimestre)
						.setPorcentajeAlcanzada(variable.getTrimestres().get(indexTrimestre).getAlcanzada()
								.multiply(new BigDecimal("100.0"))
								.divide(variable.getMetaAnual(), 4, RoundingMode.CEILING));
			}
		}
	}

	public void cleanSearch() {
		this.init();
		this.generalSearch = StringUtils.EMPTY;
		this.codigoSearch = StringUtils.EMPTY;
	}

	public void changeFichaPage(PageEvent event) {
		currentPage = event.getPage();
		Collections.sort(listFichas.get(currentPage).getVariables(), compareByCodigo);
	}

	public void changeVariablePage(PageEvent event) {
		currentPageVariable = event.getPage();
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":pnlBotonesDetalle");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":tablaDetalle");
	}

	public void editFicha() {
		editing = Boolean.TRUE;
	}

	public void cancelEdition() {
		editing = Boolean.FALSE;
	}

	public void saveFicha() {
		matrizFichasIndicadoresService.saveFicha(listFichas.get(currentPage));
		editing = Boolean.FALSE;
		UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Correcto !!!", "Información Actualizada");
	}

	public void changeVariable() {
		listFichas.get(currentPage).getVariables().get(currentPageVariable).setVariable(variableSelected);
		RequestContext.getCurrentInstance().execute("PF('variableWdg').unselectAllRows();");
		RequestContext.getCurrentInstance().execute("PF('variableWdg').clearFilters();");
		RequestContext.getCurrentInstance().execute("PF('catalogVariablesDlg').hide();");
	}

	public void addVariable() {
		List<TcPeriodo> periodos = matrizFichasIndicadoresService.getTrimestres();
		List<TcVariablesTrimestre> trimestres = new ArrayList<>();
		TcFichaVariable fichaVariable = new TcFichaVariable();

		for (TcPeriodo tcPeriodo : periodos) {
			trimestres.add(new TcVariablesTrimestre(tcPeriodo));
		}

		fichaVariable.setTrimestres(trimestres);
		listFichas.get(currentPage).getVariables().add(fichaVariable);
		editingVariable = Boolean.TRUE;

		currentPageVariable = listFichas.get(currentPage).getVariables().size() - 1;
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":pnlBotonesDetalle");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":tablaDetalle");
		RequestContext.getCurrentInstance()
				.execute("PF('detalleWdg').getPaginator().setPage(" + currentPageVariable + ")");

		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("growl");
	}

	List<Integer> codes = null;
	List<Integer> variables = null;
	Integer variableSearched = null;

	private void validateVariableAndCodigo() {

		codes = new ArrayList<>();
		variables = new ArrayList<>();
		variableSearched = null;

		for (TcFichaVariable variable : listFichas.get(currentPage).getVariables()) {
			if ((variable.getId() != null && variable.getId() > 0) || variable.getIndex() != currentPageVariable) {
				codes.add(variable.getCodigo());
				variables.add(variable.getVariable().getNumvar());
			} else {
				variableSearched = variable.getVariable().getNumvar();
			}
		}
	}

	public void saveVariable() {

		Boolean validVariable = Boolean.FALSE;
		if (listFichas.get(currentPage).getVariables().get(currentPageVariable).getVariable().getNumvar() != 0) {
			if (listFichas.get(currentPage).getVariables().get(currentPageVariable).getCodigo() == 0) {

				if (listFichas.get(currentPage).getVariables().size() == 1) {
					listFichas.get(currentPage).getVariables().get(currentPageVariable)
							.setCodigo(BigInteger.ONE.intValue());
					validVariable = Boolean.TRUE;
				} else {

					this.validateVariableAndCodigo();
					Integer nextCode = searchMaxCode(codes) + 1;
					validVariable = !existVariableCode(variables, variableSearched);

					listFichas.get(currentPage).getVariables().get(currentPageVariable).setCodigo(nextCode);

				}
			} else {
				this.validateVariableAndCodigo();
				validVariable = !existVariableCode(variables, variableSearched);
			}

			if (validVariable) {
				matrizFichasIndicadoresService.saveFicha(listFichas.get(currentPage));

				listFichas = matrizFichasIndicadoresService.listadoFichas(this.getUserDetails().getIdSector());
				Collections.sort(listFichas.get(currentPage).getVariables(), compareByCodigo);
				editingVariable = Boolean.FALSE;
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Correcto !!!",
						"Variable guardada correctamente.");

				PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
						.add("formCaptura:fichasTable:" + currentPage + ":tablaDetalle");

			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error !!!",
						"Ya existe un registro con la misma Variable, Favor de Selecionar una diferente.");
			}
		} else {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error !!!",
					"Favor de selecionar una Variable.");
		}

		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("growl");
	}

	public void cancelVariable() {
		listFichas = matrizFichasIndicadoresService.listadoFichas(this.getUserDetails().getIdSector());
		editingVariable = Boolean.FALSE;

		UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edición Cancelada");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":tablaDetalle");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("growl");
	}

	public void updateVariable() {
		editingVariable = Boolean.TRUE;

		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":tablaDetalle");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":pnlBotonesDetalle");
	}

	public void deleteVariable() {

		matrizFichasIndicadoresService
				.deleteFichaVariable(listFichas.get(currentPage).getVariables().get(currentPageVariable));
		listFichas = matrizFichasIndicadoresService.listadoFichas(this.getUserDetails().getIdSector());

		UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Variable Eliminada Correctamente");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":tablaDetalle");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("formCaptura:fichasTable:" + currentPage + ":pnlBotonesDetalle");
		PrimeFacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("growl");
	}

	private Integer searchMaxCode(List<Integer> integers) {

		return Collections.max(integers);
	}

	private Boolean existVariableCode(List<Integer> integers, Integer searchedValue) {
		return integers.contains(searchedValue);
	}

	Comparator<TcFichaVariable> compareByVariableNumvar = (TcFichaVariable fichaVariable1,
			TcFichaVariable fichaVariable2) -> fichaVariable1.getVariable().getNumvar()
					.compareTo(fichaVariable2.getVariable().getNumvar());

	Comparator<TcFichaVariable> compareByCodigo = (TcFichaVariable fichaVariable1,
			TcFichaVariable fichaVariable2) -> fichaVariable1.getCodigo().compareTo(fichaVariable2.getCodigo());

	public String getCodigoSearch() {
		return codigoSearch;
	}

	public void setCodigoSearch(String codigoSearch) {
		this.codigoSearch = codigoSearch;
	}

	public Boolean getEditing() {
		return editing;
	}

	public void setEditing(Boolean editing) {
		this.editing = editing;
	}

	public Boolean getEditingVariable() {
		return editingVariable;
	}

	public void setEditingVariable(Boolean editingVariable) {
		this.editingVariable = editingVariable;
	}

	public List<TcFicha> getListFichas() {
		return listFichas;
	}

	public void setListFichas(List<TcFicha> listFichas) {
		this.listFichas = listFichas;
	}

	public List<Variables> getListVariables() {
		return listVariables;
	}

	public void setListVariables(List<Variables> listVariables) {
		this.listVariables = listVariables;
	}

	public Variables getVariableSelected() {
		return variableSelected;
	}

	public void setVariableSelected(Variables variableSelected) {
		this.variableSelected = variableSelected;
	}

	public List<Variables> getFilteredVariables() {
		return filteredVariables;
	}

	public void setFilteredVariables(List<Variables> filteredVariables) {
		this.filteredVariables = filteredVariables;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getCurrentPageVariable() {
		return currentPageVariable;
	}

	public void setCurrentPageVariable(Integer currentPageVariable) {
		this.currentPageVariable = currentPageVariable;
	}

	public MatrizFichasIndicadoresService getMatrizFichasIndicadoresService() {
		return matrizFichasIndicadoresService;
	}

	public void setMatrizFichasIndicadoresService(MatrizFichasIndicadoresService matrizFichasIndicadoresService) {
		this.matrizFichasIndicadoresService = matrizFichasIndicadoresService;
	}

	public String getGeneralSearch() {
		return generalSearch;
	}

	public void setGeneralSearch(String generalSearch) {
		this.generalSearch = generalSearch;
	}

}
