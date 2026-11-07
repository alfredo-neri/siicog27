package com.gem.sistema.web.bean;

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
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm1711;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.Pm1711Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductividadConsultasMedicasMB.
 */
@ManagedBean
@ViewScoped
public class ProductividadConsultasMedicasMB extends BaseDirectReport {

	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The service. */
	@ManagedProperty("#{pm1711Service}")
	private Pm1711Service service;

	/** The master list. */
	private List<Pm1711> masterList;

	/** The selected. */
	private Pm1711 selected = new Pm1711();
	
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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct IndicadoresTrimestralIndiceDenunciasMB");
		jasperReporteName = "reporte114";
		endFilename = jasperReporteName + ".pdf";
		inicializar();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("ANO", firmas.getCampo3());
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("TRIMESTRE", trimestre);
		parameters.put("NOMMUNICIPIO",firmas.getCampo1());
		parameters.put("CLAVEMUNICIPIO", conctb.getClave());
		parameters.put("NoFIRMAS", 2);
		parameters.put("N1", firmas.getL4());
		parameters.put("L1", firmas.getN4());
		parameters.put("N2", firmas.getL8());
		parameters.put("L2", firmas.getN8());
		parameters.put("N3", firmas.getL5());
		parameters.put("L3", firmas.getN5());
		parameters.put("IMAGEN", getUserDetails().getPathImgCab1());

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
			masterList = new ArrayList<Pm1711>();
			Pm1711 entity = new Pm1711();
			entity.setTrimestre(0);
			entity.setIdsector(getUserDetails().getIdSector());
			entity.setCapturo(getUserDetails().getUsername());
			entity.setFeccap(new Date());
			entity.setIdRef(getUserDetails().getIdSector() - 1l);
			entity.setUserid(getUserDetails().getUsername());
			entity.setCm(0);
			entity.setMe(0);
			entity.setDias(0);
			masterList.add(entity);
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm1711 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este año crea el semestre 1
		Pm1711 entity = new Pm1711();
		entity.setTrimestre(1);

		List<Integer> currentSemesters = masterList.stream().map(Pm1711::getTrimestre).collect(Collectors.toList());
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
		Pm1711 nuevo = masterList.get(currentPage);
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
					Pm1711 currentRecord = masterList.get(i);
					calculaAcumulados(currentRecord);
					Pm1711 entity = service.save(currentRecord);
					masterList.set(i, entity);
					if (i == masterList.indexOf(selected)) {
						selected = entity;
					}
				}
			} else {
				defaultValues(nuevo);
				calculaAcumulados(nuevo);
				Pm1711 pm1711 = service.save(nuevo);
				masterList.set(currentPage, pm1711);
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
	private boolean validarNonNull(Pm1711 nuevo) {
		
		if(null == nuevo.getObsme())
			nuevo.setObsme("");
		if(null == nuevo.getObsdias())
			nuevo.setObsdias("");
		if(null == nuevo.getObscm())
			nuevo.setObscm("");
		
		return true;
	}

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Pm1711>() {
			@Override
			public int compare(Pm1711 h1, Pm1711 h2) {
				return h1.getTrimestre().compareTo(h2.getTrimestre());
			}
		});
	}

	/**
	 * Default values.
	 *
	 * @param currentRecord the current record
	 */
	private void defaultValues(Pm1711 currentRecord) {
		if (Objects.isNull(currentRecord.getCm()))
			currentRecord.setCm(0);
		if (Objects.isNull(currentRecord.getMe()))
			currentRecord.setMe(0);
		if (Objects.isNull(currentRecord.getDias()))
			currentRecord.setDias(0);
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
			selected.setCm(null);
			selected.setMe(null);
			selected.setDias(null);
			selected.setObscm("");
			selected.setObsme("");
			selected.setObsdias("");
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
			Pm1711 currentRecord = masterList.get(i);
			if (Objects.nonNull(currentRecord) && currentRecord.getId() > 0) {
				calculaAcumulados(currentRecord);
				Pm1711 entity = service.save(currentRecord);
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
	private void calculaAcumulados(Pm1711 currentRecord) {
		// Agarramos el registro anterior al registro seleccionado
		int lastIndex = masterList.indexOf(currentRecord) - 1;
		Pm1711 beforeSelected = lastIndex > -1 ? masterList.get(lastIndex) : null;
		// Si no existe anterior instanciamos un nuevo registro
		if (Objects.isNull(beforeSelected)) {
			if (Objects.isNull(beforeSelected)) {
				beforeSelected = new Pm1711();
				beforeSelected.setAcucm(0);
				beforeSelected.setAcume(0);
				beforeSelected.setAcudias(0);
			}
		}
		if (Objects.isNull(currentRecord.getCm()))
			currentRecord.setCm(0);
		if (Objects.isNull(currentRecord.getMe()))
			currentRecord.setMe(0);
		if (Objects.isNull(currentRecord.getDias()))
			currentRecord.setDias(0);
		currentRecord.setAcucm(beforeSelected.getAcucm() + currentRecord.getCm());
		currentRecord.setAcume(beforeSelected.getAcume() + currentRecord.getMe());
		currentRecord.setAcudias(beforeSelected.getAcudias() + currentRecord.getDias());

	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	// getters and setters
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
	public List<Pm1711> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm1711> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm1711Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm1711Service service) {
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
	public Pm1711 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm1711 selected) {
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

}
