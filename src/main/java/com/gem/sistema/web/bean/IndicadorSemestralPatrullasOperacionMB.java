package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm0611;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm0611Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadorSemestralPatrullasOperacionMB.
 */
@ManagedBean
@ViewScoped
public class IndicadorSemestralPatrullasOperacionMB extends BaseDirectReport {

	/** The conctb. */
	private Conctb conctb;
	
	/** The tc mes. */
	private TcMes tcMes;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The service. */
	@ManagedProperty("#{pm0611Service}")
	private Pm0611Service service;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/** The master list. */
	private List<Pm0611> masterList;

	/** The selected. */
	private Pm0611 selected = new Pm0611();
	
	/** The semestre. */
	private Integer semestre;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The editando. */
	private boolean editando = false;
	
	/** The selectable semesters. */
	private List<Integer> selectableSemesters;
	
	/** The valid semesters. */
	private List<Integer> validSemesters = new ArrayList<Integer>();

	/**
	 * Gets the tc mes.
	 *
	 * @return the tc mes
	 */
	public TcMes getTcMes() {
		return tcMes;
	}

	/**
	 * Sets the tc mes.
	 *
	 * @param tcMes the new tc mes
	 */
	public void setTcMes(TcMes tcMes) {
		this.tcMes = tcMes;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct IndicadorSemestralPatrullasOperacionMB");
		jasperReporteName = "RF009331";
		endFilename = jasperReporteName + ".pdf";
		inicializar();
	}

	/**
	 * Gets the mes inicial.
	 *
	 * @param semes the semes
	 * @return the mes inicial
	 */
	public String getMesInicial(Integer semes) {
		if (semes == 1) {
			return "01";
		} else {
			return "07";
		}

	}

	/**
	 * Gets the mes final.
	 *
	 * @param semes the semes
	 * @return the mes final
	 */
	public String getMesFinal(Integer semes) {
		if (semes == 1) {
			return "06";
		} else {
			return "12";
		}

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		parameters.put("municipioName", this.getUserDetails().getMunicipio());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDay(Integer.valueOf(getMesFinal(semestre))));
		parameters.put("mesInicioName", tcMesRepository.findByMes(getMesInicial(semestre)).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(getMesFinal(semestre)).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", semestre);
		parameters.put("idSector", getUserDetails().getIdSector());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F17.getValue());
	    parameters.put("firmaP3", firma.getPuesto().getPuesto());
	    parameters.put("firmaN3", firma.getNombre());
		parameters.put("firmaP2", "");
		parameters.put("firmaN2", "");

		return parameters;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		validSemesters.add(1);
		validSemesters.add(2);
		masterList = service.findAllBySector(getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Pm0611>();
			Pm0611 entity = new Pm0611();
			//entity.setSemestral(1);
			entity.setIdsector(getUserDetails().getIdSector());
			entity.setCapturo(getUserDetails().getUsername());
			entity.setFeccap(new Date());
			entity.setIdRef(getUserDetails().getIdSector() - 1l);
			entity.setUserid(getUserDetails().getUsername());
			entity.setParve(0);
			entity.setPatru(0);
			entity.setHabi(0);
			masterList.add(entity);
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm0611 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este año crea el semestre 1
		Pm0611 entity = new Pm0611();
		entity.setSemestral(1);

		List<Integer> currentSemesters = masterList.stream().map(Pm0611::getSemestral).collect(Collectors.toList());
		selectableSemesters = currentSemesters.isEmpty() ? validSemesters
				: validSemesters.stream().filter(p -> !currentSemesters.contains(p)).collect(Collectors.toList());

		if (selectableSemesters.isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar 2 semestres.");
			return;
		}

		editando = true;
		selected = entity;
		entity.setSemestral(selectableSemesters.get(0));

		masterList.add(entity);
		RequestContext.getCurrentInstance()
				.execute("PF('dataGrid').getPaginator().setPage(" + (masterList.size() - 1) + ");");
		if (currentPage == -1) {
			currentPage = 0;
			actualizaSeleccionado();
		}
	}

	/**
	 * Modificar.
	 */
	public void modificar() {
		editando = true;
	}

	/**
	 * Salvar.
	 */
	public void salvar() {
		Pm0611 nuevo = masterList.get(currentPage);

		if (editando) {
			editando = false;

			// Validacion de campos nulos

			// if (editando && validarNonNull(nuevo)) {
			// editando = false;

			// Actualiza algunos campos autogenerados
			nuevo.setUserid(getUserDetails().getUsername());
			nuevo.setCapturo(getUserDetails().getUsername());
			nuevo.setIdsector(getUserDetails().getIdSector());
			nuevo.setIdRef(getUserDetails().getIdSector() - 1l);
			nuevo.setFeccap(new Date());

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";

			sortMasterList();
			currentPage = masterList.indexOf(nuevo);
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').getPaginator().setPage(" + (selected.getSemestral() - 1) + ");");

			defaultValues(nuevo);
			Pm0611 Pm0611 = service.save(nuevo);
			masterList.set(currentPage, Pm0611);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, msg);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	/**
	 * Cancelar.
	 */
	public void cancelar() {
		editando = false;
		if (selected.getId() == null) {
			masterList.remove(selected);
			currentPage--;
			actualizaSeleccionado();
		}
	}

	// Metodo para validacion de nulos
	// private boolean validarNonNull(Pm0611 nuevo) {
	// boolean isValid = StringUtils.isNotBlank(nuevo.getObsparve()) &&
	// StringUtils.isNotBlank(nuevo.getObspatru())
	// && StringUtils.isNotBlank(nuevo.getObshabi());
	// if (!isValid) {
	// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
	// StringUtils.EMPTY,
	// "Por favor complete los campos requeridos.");
	// FacesContext.getCurrentInstance().addMessage(null, message);
	// }
	// return isValid;
	// }

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Pm0611>() {
			@Override
			public int compare(Pm0611 h1, Pm0611 h2) {
				return h1.getSemestral().compareTo(h2.getSemestral());
			}
		});
	}

	/**
	 * Default values.
	 *
	 * @param currentRecord the current record
	 */
	private void defaultValues(Pm0611 currentRecord) {
		if (Objects.isNull(currentRecord.getParve()))
			currentRecord.setParve(0);
		if (Objects.isNull(currentRecord.getPatru()))
			currentRecord.setPatru(0);
		if (Objects.isNull(currentRecord.getHabi()))
			currentRecord.setHabi(0);
	}

	/**
	 * Cambiar pagina.
	 *
	 * @param event the event
	 */
	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		actualizaSeleccionado();
	}

	/**
	 * Actualiza seleccionado.
	 */
	private void actualizaSeleccionado() {
		if (!masterList.isEmpty()) {
			selected = masterList.get(currentPage);
			semestre = selected.getSemestral();
		} else {
			inicializar();
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Objects.isNull(selected.getId())) {
			selected.setParve(null);
			selected.setPatru(null);
			selected.setHabi(null);
			selected.setObsparve("");
			selected.setObspatru("");
			selected.setObshabi("");
		} else {
			selected = service.findById(selected.getId());
			masterList.set(currentPage, selected);
		}
	}

	/**
	 * Delete.
	 */
	public void delete() {
		service.delete(selected.getId());
		masterList.remove(currentPage);
		currentPage = currentPage > 0 ? currentPage - 1 : 0;
		actualizaSeleccionado();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se eliminó de manera correcta");
	}

	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<Pm0611> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm0611> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm0611Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm0611Service service) {
		this.service = service;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Checks if is editando.
	 *
	 * @return true, if is editando
	 */
	public boolean isEditando() {
		return editando;
	}

	/**
	 * Sets the editando.
	 *
	 * @param editando the new editando
	 */
	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public Pm0611 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm0611 selected) {
		this.selected = selected;
	}

	/**
	 * Gets the selectable month.
	 *
	 * @return the selectable month
	 */
	public List<Integer> getSelectableMonth() {
		return selectableSemesters;
	}

	/**
	 * Sets the selectable month.
	 *
	 * @param selectableMonth the new selectable month
	 */
	public void setSelectableMonth(List<Integer> selectableMonth) {
		this.selectableSemesters = selectableMonth;
	}

	/**
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public Integer getSemestre() {
		return semestre;
	}

	/**
	 * Sets the semestre.
	 *
	 * @param semestre the new semestre
	 */
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
}
