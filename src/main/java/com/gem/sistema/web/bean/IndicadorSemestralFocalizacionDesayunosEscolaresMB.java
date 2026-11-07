package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

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
import com.gem.sistema.business.domain.Pm1811;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm1811Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadorSemestralFocalizacionDesayunosEscolaresMB.
 */
@ManagedBean
@ViewScoped
public class IndicadorSemestralFocalizacionDesayunosEscolaresMB extends BaseDirectReport {
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The tc mes. */
	private TcMes tcMes;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;;

	/** The service. */
	@ManagedProperty("#{pm1811Service}")
	private Pm1811Service service;

	/** The master list. */
	private List<Pm1811> masterList;

	/** The selected. */
	private Pm1811 selected = new Pm1811();
	
	/** The semestre. */
	private Integer semestre;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The editando. */
	private boolean editando = false;
	
	/** The selectable semesters. */
	private List<Integer> selectableSemesters;
	
	/** The valid semesters. */
	private List<Integer> validSemesters;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	

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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct IndicadorSemesPatrullasOperacionMB");
		jasperReporteName = "RF009334";
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
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
	    parameters.put("firmaP3", firma.getPuesto().getPuesto());
	    parameters.put("firmaN3", firma.getNombre());
		
		return parameters;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		validSemesters = new ArrayList<Integer>();
		validSemesters.add(1);
		validSemesters.add(2);
		masterList = service.findAllBySector(getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Pm1811>();
			Pm1811 entity = new Pm1811();
			entity.setSemes(0);
			entity.setIdsector(getUserDetails().getIdSector());
			entity.setCapturo(getUserDetails().getUsername());
			entity.setFeccap(new Date());
			entity.setIdRef(getUserDetails().getIdSector() - 1l);
			entity.setUserid(getUserDetails().getUsername());
			entity.setNumdes(0);
			entity.setNumni(0);
			masterList.add(entity);
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm1811 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este año crea el semestre 1
		Pm1811 entity = new Pm1811();
		entity.setSemes(1);

		List<Integer> currentSemesters = masterList.stream().map(Pm1811::getSemes).collect(Collectors.toList());
		selectableSemesters = currentSemesters.isEmpty() ? validSemesters
				: validSemesters.stream().filter(p -> !currentSemesters.contains(p)).collect(Collectors.toList());

		if (selectableSemesters.isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar 2 semestres.");
			return;
		}

		editando = true;
		selected = entity;
		entity.setSemes(selectableSemesters.get(0));

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
		Pm1811 nuevo = masterList.get(currentPage);
		if (editando ) {
			editando = false;
           
			if(!StringUtils.isNotBlank(nuevo.getObsnumni()))
					nuevo.setObsnumni("");
			if(!StringUtils.isNotBlank(nuevo.getObsnumdes()))
				nuevo.setObsnumni("");
			// Actualiza algunos campos autogenerados
			nuevo.setUserid(getUserDetails().getUsername());
			nuevo.setCapturo(getUserDetails().getUsername());
			nuevo.setIdsector(getUserDetails().getIdSector());
			nuevo.setIdRef(getUserDetails().getIdSector() - 1l);
			nuevo.setFeccap(new Date());

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";
			boolean isNotLastMonth = masterList.stream().anyMatch(p->p.getSemes() > nuevo.getSemes());
			
			sortMasterList();
			currentPage = masterList.indexOf(nuevo);
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').getPaginator().setPage(" + (selected.getSemes() - 1) + ");");

			//Si esta modificando y no es el último registro se debe recalcular desde el mes modificado has el último mes
			if(isNotLastMonth){
				for (int i = masterList.indexOf(selected); i < masterList.size(); i++) {
					Pm1811 currentRecord = masterList.get(i);
						calculaAcumulados(currentRecord);
						Pm1811 entity = service.save(currentRecord);
						masterList.set(i, entity);
						if(i == masterList.indexOf(selected)){
							selected = entity;
						}
				}
			}else{
				defaultValues(nuevo);
				calculaAcumulados(nuevo);
				Pm1811 pm1811 = service.save(nuevo);
				masterList.set(currentPage, pm1811);
			}
			

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
		}else{
			selected = service.findById(selected.getId());
			masterList.set(currentPage, selected);
		}
	}

	/*  private boolean validarNonNull(Pm1811 nuevo) {
		boolean isValid = StringUtils.isNotBlank(nuevo.getObsnumni()) && StringUtils.isNotBlank(nuevo.getObsnumdes());
		if (!isValid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"Por favor complete los campos requeridos.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return isValid;
	} */

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Pm1811>() {
			@Override
			public int compare(Pm1811 h1, Pm1811 h2) {
				return h1.getSemes().compareTo(h2.getSemes());
			}
		});
	}

	/**
	 * Default values.
	 *
	 * @param currentRecord the current record
	 */
	private void defaultValues(Pm1811 currentRecord) {
		if (Objects.isNull(currentRecord.getNumdes()))
			currentRecord.setNumdes(0);
		if (Objects.isNull(currentRecord.getNumni()))
			currentRecord.setNumni(0);
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
			semestre = selected.getSemes();
		} else {
			inicializar();
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Objects.isNull(selected.getId())) {
			selected.setNumni(null);
			selected.setNumdes(null);
			selected.setObsnumni("");
			selected.setObsnumdes("");
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
		for (int i = currentPage; i >= 0 && i < masterList.size(); i++) {
			Pm1811 currentRecord = masterList.get(i);
			if(Objects.nonNull(currentRecord) && currentRecord.getId() > 0){
				calculaAcumulados(currentRecord);
				Pm1811 entity = service.save(currentRecord);
				masterList.set(i, entity);
				if(i == currentPage){
					selected = entity;
				}
			}
		}
		actualizaSeleccionado();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se eliminó de manera correcta");
	}

	/**
	 * Calcula acumulados.
	 *
	 * @param currentRecord the current record
	 */
	private void calculaAcumulados(Pm1811 currentRecord) {
		//Agarramos el registro anterior al registro seleccionado
		int lastIndex = masterList.indexOf(currentRecord)-1;
		Pm1811 beforeSelected = lastIndex > -1 ? masterList.get(lastIndex) : null;
		//Si no existe anterior instanciamos un nuevo registro
		if(Objects.isNull(beforeSelected)){
			if(Objects.isNull(beforeSelected)){
				beforeSelected = new Pm1811();
				beforeSelected.setAcumnumni(0);
			}
		}
		if(Objects.isNull(currentRecord.getNumdes())) currentRecord.setNumdes(0);
		if(Objects.isNull(currentRecord.getNumni())) currentRecord.setNumni(0);
		currentRecord.setAcumnumni(beforeSelected.getAcumnumni() + currentRecord.getNumni());
		
	}


	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<Pm1811> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm1811> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm1811Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm1811Service service) {
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
	public Pm1811 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm1811 selected) {
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
