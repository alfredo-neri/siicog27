package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

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
import com.gem.sistema.business.domain.Pm5411;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm5411Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadorMensualInformacionEvaluarDesempenoMensualFismMB.
 */
@ManagedBean
@ViewScoped
public class IndicadorMensualInformacionEvaluarDesempenoMensualFismMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The service. */
	@ManagedProperty("#{pm5411Service}")
	private Pm5411Service service;

	/** The master list. */
	private List<Pm5411> masterList;

	/** The selected. */
	private Pm5411 selected = new Pm5411();
	
	/** The mes. */
	private Integer mes;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The editando. */
	private boolean editando = false;
	
	/** The selectable month. */
	private List<Integer> selectableMonth;
	
	/** The valid months. */
	private List<Integer> validMonths = new ArrayList<Integer>();
	
	/** The firmas. */
	Firmas firmas;
	
	/** The conctb. */
	Conctb conctb;

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

		LOGGER.info(":: En postconsruct IndicadorMensualIndicadorCoberturaSeguridadPublicaMunicipalMB");
		jasperReporteName = "reporte49";
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

		parameters.put("MES", mes);
		parameters.put("nomMes",
				tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, '0')).getDescripcion());
		parameters.put("lastDayOfMonth", getLastDayByAnoEmp(Integer.valueOf(mes), firmas.getCampo3()));
		parameters.put("SECTOR", getUserDetails().getIdSector());

		parameters.put("imagen", getUserDetails().getPathImgCab1());
		parameters.put("nomMunicipio", firmas.getCampo1());
		parameters.put("ANO", conctb.getAnoemp());
		parameters.put("L4", firmas.getL4());
		parameters.put("N4", firmas.getN4());
		parameters.put("L5", firmas.getL5());
		parameters.put("N5", firmas.getN5());

		return parameters;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		for (int i = 1; i <= 12; i++) {
			validMonths.add(i);
		}
		masterList = service.findAllBySector(getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Pm5411>();
			masterList.add(inicializarEntity());
		} else {
			masterList.stream().map(p -> {
				p.setPartciuOther(p.getPartciu());
				return p;
			}).collect(Collectors.toList());
			masterList.stream().map(p -> {
				p.setPartciu(!p.getPartciu().equals(String.valueOf("COPACI"))
						? (!p.getPartciu().equals(String.valueOf("CODEMUNB")) ? String.valueOf("OTRO") : p.getPartciu())
						: p.getPartciu());
				return p;
			}).collect(Collectors.toList());
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Inicializar entity.
	 *
	 * @return the pm 5411
	 */
	private Pm5411 inicializarEntity() {
		Pm5411 entity = new Pm5411();
		entity.setMasimun(BigDecimal.ZERO);
		entity.setMminmes(BigDecimal.ZERO);
		entity.setObyacmes(0);
		entity.setMautobyac(BigDecimal.ZERO);
		entity.setMejemes(BigDecimal.ZERO);
		entity.setMautob(BigDecimal.ZERO);
		entity.setSesiones(0);
		entity.setMensual(0);
		entity.setIdsector(getUserDetails().getIdSector());
		entity.setCapturo(getUserDetails().getUsername());
		entity.setFeccap(new Date());
		entity.setIdRef(getUserDetails().getIdSector() - 1l);
		entity.setUserid(getUserDetails().getUsername());
		entity.setPartciu("COPACI");
		entity.setPartciuOther("COPACI");
		return entity;
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm5411 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este años crea el mes 1
		Pm5411 entity = new Pm5411();
		entity = inicializarEntity();

		List<Integer> currentMonths = masterList.stream().map(Pm5411::getMensual).collect(Collectors.toList());
		selectableMonth = currentMonths.isEmpty() ? validMonths
				: validMonths.stream().filter(p -> !currentMonths.contains(p)).collect(Collectors.toList());

		if (selectableMonth.isEmpty() || masterList.size() >= 12) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar hasta el mes 12.");
			return;
		}

		selected = entity;
		editando = true;

		entity.setMensual(selectableMonth.get(0));

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
		Pm5411 nuevo = masterList.get(currentPage);
		defaultValues(nuevo);
		if (editando && validarNonNull(nuevo)) {
			editando = false;

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";

			sortMasterList();

			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').getPaginator().setPage(" + (selected.getMensual() - 1) + ");");

			Pm5411 Pm5411 = service.save(nuevo);
			masterList.set(currentPage, Pm5411);

			if (!(masterList.get(currentPage).getPartciu().equals(String.valueOf("COPACI"))
					|| masterList.get(currentPage).getPartciu().equals(String.valueOf("CODEMUNB")))) {

				if (modificando) {
					masterList.get(currentPage).setPartciuOther(masterList.get(currentPage).getPartciu());
				}
				masterList.get(currentPage).setPartciu(String.valueOf("OTRO"));
			} else {
				if (modificando) {
					masterList.get(currentPage).setPartciuOther(masterList.get(currentPage).getPartciu());
				}
			}

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, msg);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		actualizaSeleccionado();

	}

	/**
	 * Default values.
	 *
	 * @param current the current
	 */
	private void defaultValues(Pm5411 current) {
		current.setUserid(getUserDetails().getUsername());
		current.setCapturo(getUserDetails().getUsername());
		current.setIdsector(getUserDetails().getIdSector());
		current.setIdRef(getUserDetails().getIdSector() - 1l);
		current.setFeccap(new Date());

		if (Objects.isNull(current.getMasimun()))
			current.setMasimun(BigDecimal.ZERO);
		if (Objects.isNull(current.getMminmes()))
			current.setMminmes(BigDecimal.ZERO);
		if (Objects.isNull(current.getObserva()))
			current.setObserva("");
		if (Objects.isNull(current.getObyacmes()))
			current.setObyacmes(0);
		if (Objects.isNull(current.getMautob()))
			current.setMautob(BigDecimal.ZERO);
		if (Objects.isNull(current.getMautobyac()))
			current.setMautobyac(BigDecimal.ZERO);
		if (Objects.isNull(current.getMejemes()))
			current.setMejemes(BigDecimal.ZERO);
		if (Objects.isNull(current.getSesiones()))
			current.setSesiones(0);
		if (Objects.nonNull(current.getPartciu()) && current.getPartciu().equals("OTRO")
				&& Objects.nonNull(current.getPartciuOther())) {
			if (current.getPartciuOther() == null)
				current.setPartciuOther("");

			current.setPartciu(current.getPartciuOther());
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

		} else {
			selected = service.findById(selected.getId());
			selected.setPartciuOther(selected.getPartciu());
			masterList.set(currentPage, selected);
			if (!masterList.get(currentPage).getPartciu().equals(String.valueOf("COPACI"))
					&& !masterList.get(currentPage).getPartciu().equals(String.valueOf("CODEMUNB")))
				masterList.get(currentPage).setPartciu(String.valueOf("OTRO"));
		}
		actualizaSeleccionado();

	}

	/**
	 * Validar non null.
	 *
	 * @param nuevo the nuevo
	 * @return true, if successful
	 */
	private boolean validarNonNull(Pm5411 nuevo) {
		boolean isValid = ((nuevo.getPartciu().equals("OTRO") && null == nuevo.getPartciuOther())
				|| !nuevo.getPartciu().equals("OTRO"));
		if (!isValid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"Por favor complete los campos requeridos.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return isValid;
	}

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Pm5411>() {
			@Override
			public int compare(Pm5411 h1, Pm5411 h2) {
				return h1.getMensual().compareTo(h2.getMensual());
			}
		});
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
			mes = selected.getMensual();
		} else {
			inicializar();
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Objects.isNull(selected.getId())) {
			selected.setMasimun(BigDecimal.ZERO);
			selected.setMminmes(BigDecimal.ZERO);
			selected.setObyacmes(0);
			selected.setMautobyac(BigDecimal.ZERO);
			selected.setMejemes(BigDecimal.ZERO);
			selected.setMautob(BigDecimal.ZERO);
			selected.setSesiones(0);
			selected.setPartciu(String.valueOf("COPACI"));
			selected.setPartciuOther(String.valueOf("COPACI"));
			selected.setObserva("");
		} else {
			selected = service.findById(selected.getId());
			selected.setPartciuOther(selected.getPartciu());
			masterList.set(currentPage, selected);
			if (!(masterList.get(currentPage).getPartciu().equals(String.valueOf("COPACI"))
					|| masterList.get(currentPage).getPartciu().equals(String.valueOf("CODEMUNB"))))
				masterList.get(currentPage).setPartciu(String.valueOf("OTRO"));

			actualizaSeleccionado();
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
	 * Partciu change.
	 */
	public void partciuChange() {
		Pm5411 current = masterList.get(currentPage);
		if (!current.getPartciu().equals("OTRO")) {
			current.setPartciuOther(current.getPartciu());
		} else {
			current.setPartciuOther("");
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Especifica cual");
		}
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
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
	public List<Pm5411> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm5411> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm5411Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm5411Service service) {
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
	public Pm5411 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm5411 selected) {
		this.selected = selected;
	}

	/**
	 * Gets the selectable month.
	 *
	 * @return the selectable month
	 */
	public List<Integer> getSelectableMonth() {
		return selectableMonth;
	}

	/**
	 * Sets the selectable month.
	 *
	 * @param selectableMonth the new selectable month
	 */
	public void setSelectableMonth(List<Integer> selectableMonth) {
		this.selectableMonth = selectableMonth;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
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
	 * Lbl input text.
	 *
	 * @return the boolean
	 */
	public Boolean lblInputText() {
		Boolean validate = new Boolean(Boolean.TRUE);

		if (masterList.get(currentPage).getPartciu().equals(String.valueOf("OTRO")) && editando == Boolean.TRUE) {
			validate = Boolean.FALSE;
		}

		return validate;
	}

}
