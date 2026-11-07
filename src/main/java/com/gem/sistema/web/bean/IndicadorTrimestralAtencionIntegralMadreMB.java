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
import com.gem.sistema.business.domain.Pm2111;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.Pm2111Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadorTrimestralAtencionIntegralMadreMB.
 */
@ManagedBean
@ViewScoped
public class IndicadorTrimestralAtencionIntegralMadreMB extends BaseDirectReport {

	/** The conctb. */
	private Conctb conctb;
	
	/** The firmas. */
	private Firmas firmas;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The service. */
	@ManagedProperty("#{pm2111Service}")
	private Pm2111Service service;

	/** The master list. */
	private List<Pm2111> masterList;

	/** The selected. */
	private Pm2111 selected = new Pm2111();
	
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

		LOGGER.info(":: En postconsruct IndicadorTrimestralAtencionIntegralMadreMB");
		jasperReporteName = "Reporte110";
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

		parameters.put("SECTOR", getUserDetails().getIdSector());
		parameters.put("TRIMESTRE", trimestre);
		parameters.put("imagen", getUserDetails().getPathImgCab1());
		parameters.put("CAMPO1",firmas.getCampo1());
		parameters.put("CLAVE", conctb.getClave());
		parameters.put("ANO_EMP", conctb.getAnoemp().toString());
		parameters.put("N4", firmas.getL4());
		parameters.put("L4", firmas.getN4());
		parameters.put("N5", firmas.getL5());
		parameters.put("L5", firmas.getN5());
		parameters.put("N3", firmas.getL8());
		parameters.put("L3", firmas.getN8());

		parameters.put("NoFIRMAS", 3);

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
			masterList = new ArrayList<Pm2111>();
			Pm2111 entity = new Pm2111();
			//entity.setTrimestre(1);
			entity.setIdsector(getUserDetails().getIdSector());
			entity.setCapturo(getUserDetails().getUsername());
			entity.setFeccap(new Date());
			entity.setIdRef(getUserDetails().getIdSector() - 1l);
			entity.setUserid(getUserDetails().getUsername());
			entity.setAtendidas(0);
			entity.setProgramadas(0);
			masterList.add(entity);
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm2111 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este año crea el semestre 1
		Pm2111 entity = new Pm2111();
		entity.setTrimestre(1);

		List<Integer> currentSemesters = masterList.stream().map(Pm2111::getTrimestre).collect(Collectors.toList());
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
		Pm2111 nuevo = masterList.get(currentPage);

		if (editando) {
			editando = false;

			// Validaba que las observaciones no se encontraran nulas
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
					.execute("PF('dataGrid').getPaginator().setPage(" + (selected.getTrimestre() - 1) + ");");

			defaultValues(nuevo);
			Pm2111 pm2111 = service.save(nuevo);
			masterList.set(currentPage, pm2111);

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

	// Metodo de validacion
	// private boolean validarNonNull(Pm2111 nuevo) {
	// boolean isValid = StringUtils.isNotBlank(nuevo.getObsaten()) &&
	// StringUtils.isNotBlank(nuevo.getObsprog());
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
		Collections.sort(masterList, new Comparator<Pm2111>() {
			@Override
			public int compare(Pm2111 h1, Pm2111 h2) {
				return h1.getTrimestre().compareTo(h2.getTrimestre());
			}
		});
	}

	/**
	 * Default values.
	 *
	 * @param currentRecord the current record
	 */
	private void defaultValues(Pm2111 currentRecord) {
		if (Objects.isNull(currentRecord.getAtendidas()))
			currentRecord.setAtendidas(0);
		if (Objects.isNull(currentRecord.getProgramadas()))
			currentRecord.setProgramadas(0);
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
			selected.setAtendidas(null);
			selected.setProgramadas(null);
			selected.setObsaten("");
			selected.setObsprog("");
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

	// getters and setters

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
	public List<Pm2111> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm2111> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm2111Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm2111Service service) {
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
	public Pm2111 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm2111 selected) {
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
