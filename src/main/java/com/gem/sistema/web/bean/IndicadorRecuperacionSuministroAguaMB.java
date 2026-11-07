package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
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
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm5111;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.Pm5111Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadorRecuperacionSuministroAguaMB.
 */
@ManagedBean
@ViewScoped
public class IndicadorRecuperacionSuministroAguaMB extends BaseDirectReport {

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The service. */
	@ManagedProperty("#{pm5111Service}")
	private Pm5111Service service;

	/** The master list. */
	private List<Pm5111> masterList;

	/** The selected. */
	private Pm5111 selected = new Pm5111();
	
	/** The trimestre. */
	private Integer trimestre;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The editando. */
	private boolean editando = false;
	
	/** The selectable trimesters. */
	private List<Integer> selectableTrimesters;
	
	/** The valid trimesters. */
	private List<Integer> validTrimesters;

	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct IndicadoresTrimestralIndiceDenunciasMB reporte 117");
		jasperReporteName = "reporte117";
		endFilename = jasperReporteName + ".pdf";
		inicializar();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());

		parameters.put("TRIMESTRE", trimestre);
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("IMAGEN", this.getUserDetails().getPathImgCab1());
		parameters.put("CAMPO1", firmas.getCampo1());
		parameters.put("CLAVE", conctb.getClave());
		parameters.put("L3", firmas.getL3());
		parameters.put("L4", firmas.getL4());
		parameters.put("L5", firmas.getL5());
		parameters.put("ANO_EMP", conctb.getAnoemp());
		parameters.put("N3", firmas.getN3());
		parameters.put("N4", firmas.getN4());
		parameters.put("N5", firmas.getN5());

		return parameters;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		validTrimesters = new ArrayList<Integer>();
		validTrimesters.add(1);
		validTrimesters.add(2);
		validTrimesters.add(3);
		validTrimesters.add(4);
		masterList = service.findAllBySector(getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Pm5111>();
			Pm5111 entity = new Pm5111();
			entity.setTrimestre(1);
			entity.setIdsector(getUserDetails().getIdSector());
			entity.setCapturo(getUserDetails().getUsername());
			entity.setFeccap(new Date());
			entity.setIdRef(getUserDetails().getIdSector() - 1l);
			entity.setUserid(getUserDetails().getUsername());
			entity.setItsa(BigDecimal.ZERO);
			entity.setTeca(BigDecimal.ZERO);
			masterList.add(entity);
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm5111 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este año crea el semestre 1
		Pm5111 entity = new Pm5111();
		entity.setTrimestre(1);

		List<Integer> currentSemesters = masterList.stream().map(Pm5111::getTrimestre).collect(Collectors.toList());
		selectableTrimesters = currentSemesters.isEmpty() ? validTrimesters
				: validTrimesters.stream().filter(p -> !currentSemesters.contains(p)).collect(Collectors.toList());

		if (selectableTrimesters.isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar 4 semestres.");
			return;
		}

		editando = true;
		selected = entity;
		entity.setTrimestre(selectableTrimesters.get(0));

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
		Pm5111 nuevo = masterList.get(currentPage);
		if (editando && validarNonNull(nuevo)) {
			editando = false;

			// Actualiza algunos campos autogenerados
			nuevo.setUserid(getUserDetails().getUsername());
			nuevo.setCapturo(getUserDetails().getUsername());
			nuevo.setIdsector(getUserDetails().getIdSector());
			nuevo.setIdRef(getUserDetails().getIdSector() - 1l);
			nuevo.setFeccap(new Date());

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";
			boolean isNotLastMonth = masterList.stream().anyMatch(p -> p.getTrimestre() > nuevo.getTrimestre());

			sortMasterList();
			currentPage = masterList.indexOf(nuevo);
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').getPaginator().setPage(" + (selected.getTrimestre() - 1) + ");");

			// Si esta modificando y no es el último registro se debe recalcular
			// desde el mes modificado has el último mes
			if (isNotLastMonth) {
				for (int i = masterList.indexOf(selected); i < masterList.size(); i++) {
					Pm5111 currentRecord = masterList.get(i);
					calculaAcumulados(currentRecord);
					Pm5111 entity = service.save(currentRecord);
					masterList.set(i, entity);
					if (i == masterList.indexOf(selected)) {
						selected = entity;
					}
				}
			} else {
				defaultValues(nuevo);
				calculaAcumulados(nuevo);
				Pm5111 pm5111 = service.save(nuevo);
				masterList.set(currentPage, pm5111);
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
		} else {
			selected = service.findById(selected.getId());
			masterList.set(currentPage, selected);
		}
	}

	/**
	 * Validar non null.
	 *
	 * @param nuevo the nuevo
	 * @return true, if successful
	 */
	private boolean validarNonNull(Pm5111 nuevo) {
		boolean isValid = StringUtils.isNotBlank(nuevo.getObsitsa()) && StringUtils.isNotBlank(nuevo.getObsteca());
		if (!isValid) {
			nuevo.setObsitsa("");
			nuevo.setObsteca("");
			// FacesMessage message = new
			// FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
			// "Por favor complete los campos requeridos.");
			// FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return true;
	}

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Pm5111>() {
			@Override
			public int compare(Pm5111 h1, Pm5111 h2) {
				return h1.getTrimestre().compareTo(h2.getTrimestre());
			}
		});
	}

	/**
	 * Default values.
	 *
	 * @param currentRecord the current record
	 */
	private void defaultValues(Pm5111 currentRecord) {
		if (Objects.isNull(currentRecord.getItsa()))
			currentRecord.setItsa(BigDecimal.ZERO);
		if (Objects.isNull(currentRecord.getTeca()))
			currentRecord.setTeca(BigDecimal.ZERO);
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
			trimestre = selected.getTrimestre();
		} else {
			inicializar();
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Objects.isNull(selected.getId())) {
			selected.setItsa(null);
			selected.setTeca(null);
			selected.setObsitsa("");
			selected.setObsteca("");
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
			Pm5111 currentRecord = masterList.get(i);
			if (Objects.nonNull(currentRecord) && currentRecord.getId() > 0) {
				calculaAcumulados(currentRecord);
				Pm5111 entity = service.save(currentRecord);
				masterList.set(i, entity);
				if (i == currentPage) {
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
	private void calculaAcumulados(Pm5111 currentRecord) {
		// Agarramos el registro anterior al registro seleccionado
		int lastIndex = masterList.indexOf(currentRecord) - 1;
		Pm5111 beforeSelected = lastIndex > -1 ? masterList.get(lastIndex) : null;
		// Si no existe anterior instanciamos un nuevo registro
		if (Objects.isNull(beforeSelected)) {
			if (Objects.isNull(beforeSelected)) {
				beforeSelected = new Pm5111();
				beforeSelected.setAcuitsa(BigDecimal.ZERO);
				beforeSelected.setAcuteca(BigDecimal.ZERO);
			}
		}
		if (Objects.isNull(currentRecord.getItsa()))
			currentRecord.setItsa(BigDecimal.ZERO);
		if (Objects.isNull(currentRecord.getTeca()))
			currentRecord.setTeca(BigDecimal.ZERO);
		currentRecord.setAcuitsa(beforeSelected.getAcuitsa().add(currentRecord.getItsa()));
		currentRecord.setAcuteca(beforeSelected.getAcuteca().add(currentRecord.getTeca()));

	}

	// getters and setters

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
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<Pm5111> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm5111> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm5111Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm5111Service service) {
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
	public Pm5111 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm5111 selected) {
		this.selected = selected;
	}

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public Integer getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the selectable trimesters.
	 *
	 * @return the selectable trimesters
	 */
	public List<Integer> getSelectableTrimesters() {
		return selectableTrimesters;
	}

	/**
	 * Sets the selectable trimesters.
	 *
	 * @param selectableTrimesters the new selectable trimesters
	 */
	public void setSelectableTrimesters(List<Integer> selectableTrimesters) {
		this.selectableTrimesters = selectableTrimesters;
	}

	/**
	 * Gets the valid trimesters.
	 *
	 * @return the valid trimesters
	 */
	public List<Integer> getValidTrimesters() {
		return validTrimesters;
	}

	/**
	 * Sets the valid trimesters.
	 *
	 * @param validTrimesters the new valid trimesters
	 */
	public void setValidTrimesters(List<Integer> validTrimesters) {
		this.validTrimesters = validTrimesters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
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
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
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

}
