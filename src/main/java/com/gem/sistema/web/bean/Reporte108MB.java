package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm0511;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm0511Repository;
import com.gem.sistema.business.service.catalogos.Pm0511Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.datamodel.Pm0511DataModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte108MB.
 */
@ManagedBean(name = "reporte108MB")
@ViewScoped
public class Reporte108MB extends BaseDirectReport {

	/** The Constant VIEW_EDIT_ROW_ACTIVATE_PENCIL. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";

	/** The Constant VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:object:%1$s:trim');";

	/** The Constant UPDATE_OBJETS. */
	private static final String UPDATE_OBJETS = "jQuery('#form1\\\\:hiddenUpdate').click();";

	/** The Constant GO_LAST_PAGE. */
	private static final String GO_LAST_PAGE = "jQuery('#form1\\\\:lasPage').click();";

	/** The Constant PDF. */
	private static final String PDF = ".pdf";

	/** The pm data model. */
	private Pm0511DataModel pmDataModel;

	/** The list pm 0511. */
	private List<Pm0511> listPm0511;

	/** The lis. */
	private List<Integer> lis;

	/** The t pm 0511. */
	private Integer tPm0511;

	private Integer oldValue;

	/** The select pm 0511. */
	private Pm0511 selectPm0511;

	/** The l trimestres. */
	private List<String> lTrimestres;

	/** The trimestre. */
	private String trimestre;

	/** The emergencia. */
	private String emergencia;

	/** The filter 1. */
	private String filter1;

	/** The filter 2. */
	private String filter2;

	/** The b add. */
	private Boolean bAdd;

	/** The b salvar. */
	private Boolean bSalvar;

	/** The b modifcar. */
	private Boolean bModifcar;

	/** The b btn modificar. */
	private Boolean bBtnModificar;

	/** The b borrar. */
	private Boolean bBorrar;

	/** The b cancelar. */
	private Boolean bCancelar;

	/** The b limpiar. */
	private Boolean bLimpiar;

	private Boolean bEdition;

	/** The firmas. */
	private Firmas firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The pm 0511 service. */
	@ManagedProperty("#{pm0511Service}")
	private Pm0511Service pm0511Service;

	/** The pm 0511 repository. */
	@ManagedProperty("#{pm0511Repository}")
	private Pm0511Repository pm0511Repository;

	/**
	 * Gets the pm data model.
	 *
	 * @return the pm data model
	 */
	public Pm0511DataModel getPmDataModel() {
		return pmDataModel;
	}

	/**
	 * Sets the pm data model.
	 *
	 * @param pmDataModel
	 *            the new pm data model
	 */
	public void setPmDataModel(Pm0511DataModel pmDataModel) {
		this.pmDataModel = pmDataModel;
	}

	/**
	 * Gets the select pm 0511.
	 *
	 * @return the select pm 0511
	 */
	public Pm0511 getSelectPm0511() {
		return selectPm0511;
	}

	/**
	 * Sets the select pm 0511.
	 *
	 * @param selectPm0511
	 *            the new select pm 0511
	 */
	public void setSelectPm0511(Pm0511 selectPm0511) {
		this.selectPm0511 = selectPm0511;
	}

	/**
	 * Gets the l trimestres.
	 *
	 * @return the l trimestres
	 */
	public List<String> getlTrimestres() {
		return lTrimestres;
	}

	/**
	 * Sets the l trimestres.
	 *
	 * @param lTrimestres
	 *            the new l trimestres
	 */
	public void setlTrimestres(List<String> lTrimestres) {
		this.lTrimestres = lTrimestres;
	}

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public String getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre
	 *            the new trimestre
	 */
	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the pm 0511 service.
	 *
	 * @return the pm 0511 service
	 */
	public Pm0511Service getPm0511Service() {
		return pm0511Service;
	}

	/**
	 * Sets the pm 0511 service.
	 *
	 * @param pm0511Service
	 *            the new pm 0511 service
	 */
	public void setPm0511Service(Pm0511Service pm0511Service) {
		this.pm0511Service = pm0511Service;
	}

	/**
	 * Gets the list pm 0511.
	 *
	 * @return the list pm 0511
	 */
	public List<Pm0511> getListPm0511() {
		return listPm0511;
	}

	/**
	 * Sets the list pm 0511.
	 *
	 * @param listPm0511
	 *            the new list pm 0511
	 */
	public void setListPm0511(List<Pm0511> listPm0511) {
		this.listPm0511 = listPm0511;
	}

	/**
	 * Gets the filter 1.
	 *
	 * @return the filter 1
	 */
	public String getFilter1() {
		return filter1;
	}

	/**
	 * Sets the filter 1.
	 *
	 * @param filter1
	 *            the new filter 1
	 */
	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}

	/**
	 * Gets the filter 2.
	 *
	 * @return the filter 2
	 */
	public String getFilter2() {
		return filter2;
	}

	/**
	 * Sets the filter 2.
	 *
	 * @param filter2
	 *            the new filter 2
	 */
	public void setFilter2(String filter2) {
		this.filter2 = filter2;
	}

	/**
	 * Gets the emergencia.
	 *
	 * @return the emergencia
	 */
	public String getEmergencia() {
		return emergencia;
	}

	/**
	 * Sets the emergencia.
	 *
	 * @param emergencia
	 *            the new emergencia
	 */
	public void setEmergencia(String emergencia) {
		this.emergencia = emergencia;
	}

	/**
	 * Gets the b add.
	 *
	 * @return the b add
	 */
	public Boolean getbAdd() {
		return bAdd;
	}

	/**
	 * Sets the b add.
	 *
	 * @param bAdd
	 *            the new b add
	 */
	public void setbAdd(Boolean bAdd) {
		this.bAdd = bAdd;
	}

	/**
	 * Gets the b salvar.
	 *
	 * @return the b salvar
	 */
	public Boolean getbSalvar() {
		return bSalvar;
	}

	/**
	 * Sets the b salvar.
	 *
	 * @param bSalvar
	 *            the new b salvar
	 */
	public void setbSalvar(Boolean bSalvar) {
		this.bSalvar = bSalvar;
	}

	/**
	 * Gets the b modifcar.
	 *
	 * @return the b modifcar
	 */
	public Boolean getbModifcar() {
		return bModifcar;
	}

	/**
	 * Sets the b modifcar.
	 *
	 * @param bModifcar
	 *            the new b modifcar
	 */
	public void setbModifcar(Boolean bModifcar) {
		this.bModifcar = bModifcar;
	}

	/**
	 * Gets the b btn modificar.
	 *
	 * @return the b btn modificar
	 */
	public Boolean getbBtnModificar() {
		return bBtnModificar;
	}

	/**
	 * Sets the b btn modificar.
	 *
	 * @param bBtnModificar
	 *            the new b btn modificar
	 */
	public void setbBtnModificar(Boolean bBtnModificar) {
		this.bBtnModificar = bBtnModificar;
	}

	/**
	 * Gets the b borrar.
	 *
	 * @return the b borrar
	 */
	public Boolean getbBorrar() {
		return bBorrar;
	}

	/**
	 * Sets the b borrar.
	 *
	 * @param bBorrar
	 *            the new b borrar
	 */
	public void setbBorrar(Boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	/**
	 * Gets the b cancelar.
	 *
	 * @return the b cancelar
	 */
	public Boolean getbCancelar() {
		return bCancelar;
	}

	/**
	 * Sets the b cancelar.
	 *
	 * @param bCancelar
	 *            the new b cancelar
	 */
	public void setbCancelar(Boolean bCancelar) {
		this.bCancelar = bCancelar;
	}

	/**
	 * Gets the b limpiar.
	 *
	 * @return the b limpiar
	 */
	public Boolean getbLimpiar() {
		return bLimpiar;
	}

	/**
	 * Sets the b limpiar.
	 *
	 * @param bLimpiar
	 *            the new b limpiar
	 */
	public void setbLimpiar(Boolean bLimpiar) {
		this.bLimpiar = bLimpiar;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository
	 *            the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the lis.
	 *
	 * @return the lis
	 */
	public List<Integer> getLis() {
		return lis;
	}

	/**
	 * Sets the lis.
	 *
	 * @param lis
	 *            the new lis
	 */
	public void setLis(List<Integer> lis) {
		this.lis = lis;
	}

	/**
	 * Gets the t pm 0511.
	 *
	 * @return the t pm 0511
	 */
	public Integer gettPm0511() {
		return tPm0511;
	}

	/**
	 * Sets the t pm 0511.
	 *
	 * @param tPm0511
	 *            the new t pm 0511
	 */
	public void settPm0511(Integer tPm0511) {
		this.tPm0511 = tPm0511;
	}

	/**
	 * Gets the pm 0511 repository.
	 *
	 * @return the pm 0511 repository
	 */
	public Pm0511Repository getPm0511Repository() {
		return pm0511Repository;
	}

	/**
	 * Sets the pm 0511 repository.
	 *
	 * @param pm0511Repository
	 *            the new pm 0511 repository
	 */
	public void setPm0511Repository(Pm0511Repository pm0511Repository) {
		this.pm0511Repository = pm0511Repository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.setbEdition(Boolean.FALSE);
		jasperReporteName = "reporte108";
		endFilename = jasperReporteName + PDF;
		this.setbAdd(Boolean.FALSE);
		this.setbBorrar(Boolean.TRUE);
		this.setbBtnModificar(Boolean.TRUE);
		this.setbModifcar(Boolean.FALSE);
		this.setbSalvar(Boolean.FALSE);
		this.setbCancelar(Boolean.TRUE);
		this.setbLimpiar(Boolean.TRUE);
		listPm0511 = pm0511Service.orderByAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm0511)) {
			this.setbAdd(Boolean.FALSE);
			this.setbBorrar(Boolean.TRUE);
			this.setbBtnModificar(Boolean.TRUE);
			this.setbModifcar(Boolean.FALSE);
			this.setbCancelar(Boolean.TRUE);
			this.setbLimpiar(Boolean.TRUE);
		}

		lTrimestres = this.pm0511Service.getTriMonth();
		trimestre = lTrimestres.get(0);

		lis = pm0511Repository.findByidSectordistinc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isNotEmpty(lis)) {
			tPm0511 = lis.get(0);
		}

	}

	/** The parameters. */
	Map<String, Object> parameters;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("ANO", firmas.getCampo3());
		parameters.put("TRIMESTRE", tPm0511);
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("NOMMUNICIPIO", firmas.getCampo1());
		parameters.put("NoFIRMAS", 2);
		parameters.put("N1", firmas.getN4());
		parameters.put("L1", firmas.getL4());
		parameters.put("N2", firmas.getN8());
		parameters.put("L2", firmas.getL8());
		parameters.put("N3", firmas.getN3());
		parameters.put("L3", firmas.getL3());
		parameters.put("IMAGEN", this.getUserDetails().getPathImgCab1());
		parameters.put("CLAVEMUNICIPIO",
				conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave());
		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds the element.
	 */
	public void addElement() {
		this.setbAdd(Boolean.TRUE);
		this.setbBorrar(Boolean.TRUE);
		this.setbBtnModificar(Boolean.FALSE);
		this.setbModifcar(Boolean.FALSE);
		this.setbSalvar(Boolean.TRUE);
		this.setbCancelar(Boolean.FALSE);
		this.setbLimpiar(Boolean.FALSE);
		this.setbEdition(Boolean.TRUE);
		if (CollectionUtils.isEmpty(pm0511Service.orderByAsc(this.getUserDetails().getIdSector()))) {
			listPm0511.add(pm0511Service.add());
		} else {
			listPm0511 = this.pm0511Service.orderByAsc(this.getUserDetails().getIdSector());
			listPm0511.add(pm0511Service.add());
		}

		// listPm0511.get(listPm0511.size()).setConse(Integer.valueOf(listPm0511.size()).toString());
		//
		activateRowEdit(listPm0511.size() - 1);
		RequestContext.getCurrentInstance().execute("PF('pm0511Wdg').paginator.setPage(" + (this.getPage() - 1) + ");");

		if (listPm0511.size() > 20) {
			Integer indexOf = this.getRowCurrent(listPm0511.size());
			this.activateRowEdit(indexOf);

			RequestContext.getCurrentInstance().execute(String.format(FOCUS_BY_ROWID, (listPm0511.size() - 1)));
		}

		// if (listPm0511.size() > 20) {
		// RequestContext.getCurrentInstance().execute(GO_LAST_PAGE);
		// }

	}

	/**
	 * Delete.
	 *
	 * @param index
	 *            the index
	 */
	public void delete(Integer index) {
		selectPm0511 = listPm0511.get(index);

		if (null != selectPm0511.getId()) {
			listPm0511 = pm0511Service.delete(selectPm0511);
		}

		if (CollectionUtils.isNotEmpty(listPm0511)) {
			lis = pm0511Repository.findByidSectordistinc(this.getUserDetails().getIdSector());
			listPm0511 = pm0511Service.orderByAsc(this.getUserDetails().getIdSector());
		}

	}

	/**
	 * On row edit.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowEdit(RowEditEvent event) {

		selectPm0511 = new Pm0511();
		selectPm0511 = (Pm0511) event.getObject();
		selectPm0511.setIdsector(this.getUserDetails().getIdSector());
		selectPm0511.setCapturo(this.getUserDetails().getUsername());
		selectPm0511.setFeccap(Calendar.getInstance().getTime());
		selectPm0511.setIdRef(0L);
		selectPm0511.setUserid(this.getUserDetails().getUsername());
		int lastIndex = listPm0511.size() - 1;
		int lastSize = listPm0511.size();

		if (bModifcar == Boolean.TRUE) {
			selectPm0511 = pm0511Service.modify(selectPm0511, oldValue);
			if (selectPm0511.isbGuardar() == Boolean.TRUE) {
				List<Pm0511> list = this.pm0511Service.orderByAsc(this.getUserDetails().getIdSector());
				
				for(int i = 0; i < list.size(); i++) {
					
					if(list.get(i).getId().equals(selectPm0511.getId())) {
						listPm0511.get(i).setbGuardar(Boolean.TRUE);
						lastIndex = i;
						break;
						
					}
					
				}
			}
		} else {
			selectPm0511.setIndex(lastIndex);
			listPm0511 = this.pm0511Service.save(selectPm0511);
		}
		if (CollectionUtils.isEmpty(listPm0511)) {
			this.activateRowEdit(listPm0511.size());
			listPm0511.add(lastIndex, selectPm0511);
			// RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
		} else {
			if (listPm0511.get(lastIndex).isbGuardar() == Boolean.TRUE) {
				listPm0511 = pm0511Service.orderByAsc(this.getUserDetails().getIdSector());
				lis = pm0511Repository.findByidSectordistinc(this.getUserDetails().getIdSector());
				this.setbAdd(Boolean.FALSE);
				this.setbBorrar(Boolean.FALSE);
				this.setbBtnModificar(Boolean.TRUE);
				this.setbModifcar(Boolean.FALSE);
				this.setbSalvar(Boolean.FALSE);
				this.setbCancelar(Boolean.TRUE);
				this.setbLimpiar(Boolean.TRUE);
				this.setbEdition(Boolean.FALSE);
				RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
			} else {
				this.activateRowEdit(lastIndex);
				if (listPm0511.size() > 20) {
					int index = this.getRowCurrent(listPm0511.size());
					this.activateRowEdit(index);
				}
				listPm0511.set(lastIndex, selectPm0511);
				// RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
			}

			// this.activateRowEdit(listPm0511.size());
			// RequestContext.getCurrentInstance().execute("jQuery('#form1\\\\:hiddenUpdate').click();");
		}
		// if
		// (listPm0511.get(selectPm0511.getIndex()).isbGuardar()==Boolean.FALSE)
		// {
		//
		// this.activateRowEdit(listPm0511.size() - 1);
		//
		// RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
		// } else {
		//
		// }

	}

	/**
	 * On row cancel.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowCancel(RowEditEvent event) {
		listPm0511 = this.pm0511Service.orderByAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edición Cancelada");
	}

	/**
	 * On cell edit.
	 *
	 * @param event
	 *            the event
	 */
	public void onCellEdit(CellEditEvent event) {
		Object oldValue1 = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue1)) {
			selectPm0511 = (Pm0511) newValue;
			selectPm0511 = this.pm0511Service.modify(selectPm0511, oldValue);

		}
	}

	/**
	 * Activate row edit.
	 *
	 * @param index
	 *            the index
	 */
	public void activateRowEdit(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/**
	 * Fortmat index.
	 *
	 * @param index
	 *            the index
	 * @return the string
	 */
	public String fortmatIndex(Integer index) {
		return StringUtils.leftPad(index.toString(), 6);
	}

	/**
	 * Find data.
	 */
	public void findData() {
		if (trimestre.trim().equals("00") && null == emergencia) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Warning!", "Seleccione un filtro");
		} else {
			listPm0511 = this.pm0511Service.executeQuery(Integer.valueOf(trimestre.trim()), emergencia, filter1,
					filter2, this.getUserDetails().getIdSector());
		}

	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event
	 *            the event
	 */
	public void onInitRowEdit(final RowEditEvent event) {
		selectPm0511 = (Pm0511) event.getObject();
		if (null != event.getObject()) {

			if (null != selectPm0511.getId() && selectPm0511.getId() != 0) {
				this.setbModifcar(Boolean.TRUE);
				this.setbAdd(Boolean.TRUE);
				this.setbBorrar(Boolean.FALSE);
				this.setbBtnModificar(Boolean.TRUE);
				this.setbModifcar(Boolean.TRUE);
				this.setbSalvar(Boolean.TRUE);
				this.setbCancelar(Boolean.FALSE);
				this.setbLimpiar(Boolean.FALSE);
				oldValue = this.selectPm0511.getTrimestre();
			} else {
				this.setbModifcar(Boolean.FALSE);
			}
		}
	}

	/**
	 * Activa el modo de edicion en una fila.
	 * 
	 * @param index
	 *            fila a ser activada.
	 */
	/**
	 * @param index
	 */
	public void activateRowEditOnInsert(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		// text.append("PF('polizasWdg').filter();PF('polizasWdg').paginator.setPage(PF('polizasWdg').paginator.cfg.pageCount
		// - 1);");
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public int getPage() {
		int rows = listPm0511.size();
		int de = 1;
		double maxRow = 20;
		double pageActua = (rows * de) / maxRow;
		String page = "" + Math.ceil(pageActua);
		return Integer.parseInt(this.getValue(page)[0]);
	}

	/**
	 * Gets the value.
	 *
	 * @param data
	 *            the data
	 * @return the value
	 */
	public String[] getValue(String data) {
		return data.split("\\.");
	}

	/**
	 * Go to last page.
	 */
	public void goToLastPage() {
		if (this.getbEdition() == Boolean.TRUE)
			if (this.listPm0511.size() <= 20) {
				this.activateRowEditOnInsert(this.getListPm0511().size());

			} else {
				// RequestContext.getCurrentInstance()
				// .execute("PF('pm0511Wdg').paginator.setPage(" +
				// (this.getPage() -
				// 1) + ");");
				this.activateRowEdit(listPm0511.size() - 1);
				// RequestContext.getCurrentInstance()
				// .execute("PF('pm0511Wdg').paginator.setPage(PF('polizasWdg').paginator.cfg.pageCount
				// - 1);");
			}
	}

	/**
	 * Update data.
	 */
	public void updateData() {
		listPm0511 = this.getPm0511Service().orderByAsc(this.getUserDetails().getIdSector());
	}

	/**
	 * Clean form.
	 */
	public void cleanForm() {
		filter1 = "00";
		filter2 = " ";
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas
	 *            the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository
	 *            the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public void changePage(PageEvent event) {
		if (bEdition == Boolean.TRUE)
			this.activateRowEdit(listPm0511.size() - 1);

	}

	public Boolean getbEdition() {
		return bEdition;
	}

	public void setbEdition(Boolean bEdition) {
		this.bEdition = bEdition;
	}

	public Integer getRowCurrent(Integer index) {
		Double rows = 20.0;
		Double size = index.doubleValue();
		Double row = (size % rows);
		Integer filas = row.intValue() - 1;
		if (filas < 0) {
			filas = 19;
		}

		return filas;
	}

	public Integer getOldValue() {
		return oldValue;
	}

	public void setOldValue(Integer oldValue) {
		this.oldValue = oldValue;
	}

}
