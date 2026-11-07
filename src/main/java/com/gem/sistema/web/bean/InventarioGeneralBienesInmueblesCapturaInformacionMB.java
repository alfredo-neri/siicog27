package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

import com.gem.sistema.business.domain.Bienesinm;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.BienesinmRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioGeneralBienesInmueblesCapturaInformacionMB.
 */
@ManagedBean
@ViewScoped
public class InventarioGeneralBienesInmueblesCapturaInformacionMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The repository. */
	@ManagedProperty("#{bienesinmRepository}")
	private BienesinmRepository repository;

	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	/** The master list. */
	private List<Bienesinm> masterList;
	
	private List<TcPeriodo> listMeses;

	/** The selected. */
	private Bienesinm selected = new Bienesinm();
	
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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listMeses = periodoRepositoy.findByTipoPeriodo(1);
		
		LOGGER.info(":: En postconsruct InventarioGeneralBienesInmueblesCapturaInformacionMB ");
		inicializar();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		return null;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		for (int i = 1; i <= 12; i++) {
			validMonths.add(i);
		}
		masterList = repository.findAllByIdsectorOrderByMesAsc(getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Bienesinm>();
			masterList.add(initializedEntity());
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Initialized entity.
	 *
	 * @return the bienesinm
	 */
	private Bienesinm initializedEntity() {
		Bienesinm entity = new Bienesinm();
		entity.setMes(0);
		entity.setIdsector(getUserDetails().getIdSector());
		entity.setCapturo(getUserDetails().getUsername());
		entity.setFeccap(new Date());
		entity.setIdRef(getUserDetails().getIdSector() - 1l);
		entity.setUserid(getUserDetails().getUsername());
		entity.setConsec(0);
		entity.setConpol(0);
		entity.setConsec(0);
		entity.setDepreperiodo(BigDecimal.ZERO);
		entity.setDepreacum(BigDecimal.ZERO);
		return entity;
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Bienesinm last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este años crea el mes 1
		Bienesinm entity = initializedEntity();

		List<Integer> currentMonths = masterList.stream().map(Bienesinm::getMes).collect(Collectors.toList());
		selectableMonth = currentMonths.isEmpty() ? validMonths
				: validMonths.stream().filter(p -> !currentMonths.contains(p)).collect(Collectors.toList());

		if (selectableMonth.isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar hasta el mes 12.");
			return;
		}

		selected = entity;
		editando = true;
		entity.setMes(selectableMonth.get(0));

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
		Bienesinm nuevo = masterList.get(currentPage);
		if (editando && validarCampos(nuevo)) {
			editando = false;

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";

			sortMasterList();

			nuevo = checkNullsAndSetDefaultValues(nuevo);
			Bienesinm bienesinm = repository.save(nuevo);
			masterList.set(currentPage, bienesinm);
			selected = bienesinm;
			RequestContext.getCurrentInstance().execute("PF('dataGrid').getPaginator().setPage(" + currentPage + ");");

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

	/**
	 * Validar campos.
	 *
	 * @param nuevo the nuevo
	 * @return true, if successful
	 */
	private boolean validarCampos(Bienesinm nuevo) {
		boolean isValid = false;
		Bienesinm bienesinm = repository.findByIdsectorAndMesAndConsec(getUserDetails().getIdSector(), nuevo.getMes(),
				nuevo.getConsec());
		if (Objects.nonNull(nuevo.getConsec()) && nuevo.getConsec() < 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"Capturar consecutivo diferente de cero.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance()
					.execute("focusEl('form1:dataGrid:" + currentPage + ":inputConsecutivo');");
		} else if (Objects.nonNull(bienesinm) && Objects.isNull(nuevo.getId())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, "El consecutivo "
					+ bienesinm.getConsec() + " para el mes " + bienesinm.getMes() + " ya existe en la base de datos.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		masterList.sort(Comparator.comparing(Bienesinm::getMes).thenComparing(Bienesinm::getConsec));
		final Bienesinm comparator = selected;
		currentPage = IntStream.range(0, masterList.size()).filter(p -> {
			Bienesinm obj = masterList.get(p);
			return obj.getMes().equals(comparator.getMes()) && obj.getConsec().equals(comparator.getConsec());
		}).findFirst().orElse(-1);
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
			mes = selected.getMes();
		} else {
			inicializar();
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Objects.isNull(selected.getId())) {
			// selected.setNcpip(null);
			// selected.setTcrpip(null);
			// selected.setObsncpip("");
			// selected.setObstcrpip("");
		} else {
			selected = repository.findOne(selected.getId());
			masterList.set(currentPage, selected);
		}
	}

	/**
	 * Delete.
	 */
	public void delete() {
		repository.delete(selected.getId());
		masterList.remove(currentPage);
		currentPage = currentPage > 0 ? currentPage - 1 : 0;
		actualizaSeleccionado();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se eliminó de manera correcta");
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = selected.getScta();
		if (StringUtils.isNotEmpty(value)) {
			selected.setScta(StringUtils.leftPad(value, 10, StringUtils.EMPTY + ZERO));
		}

		value = selected.getSscta();
		if (StringUtils.isNotEmpty(value)) {
			selected.setSscta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = selected.getSsscta();
		if (StringUtils.isNotEmpty(value)) {
			selected.setSsscta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = selected.getSssscta();
		if (StringUtils.isNotEmpty(value)) {
			selected.setSssscta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}
		RequestContext.getCurrentInstance().update("form1:panel-filtros");
	}

	/**
	 * Check nulls and set default values.
	 *
	 * @param <T> the generic type
	 * @param object the object
	 * @return the t
	 */
	public static <T> T checkNullsAndSetDefaultValues(T object) {
		try {
			Class<?> clazz = object.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				boolean isGetterMhetodType = method.getName().startsWith("get") || method.getName().startsWith("is");
				if (isGetterMhetodType) {
					Object fieldValue = method.invoke(object);
					if (Objects.isNull(fieldValue)) {
						String setMethodName = method.getName().replace("get", "");
						Field field = clazz.getDeclaredField(setMethodName.toLowerCase());
						field.setAccessible(true);
						if (method.getReturnType().equals(Integer.class)) {
							field.set(object, 0);
						} else if (method.getReturnType().equals(String.class)) {
							field.set(object, "");
						} else if (method.getReturnType().equals(BigDecimal.class)) {
							field.set(object, BigDecimal.ZERO);
						} else if (method.getReturnType().equals(Long.class)) {
							field.set(object, 0l);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	// getters and setters

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
	public List<Bienesinm> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Bienesinm> masterList) {
		this.masterList = masterList;
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
	public Bienesinm getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Bienesinm selected) {
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

	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public BienesinmRepository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(BienesinmRepository repository) {
		this.repository = repository;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	/**
	 * Find acount.
	 *
	 * @param index the index
	 */
	public void findAcount(Integer index) {
		Cuenta cuenta = this.getCuentaRepository().findAcountByAcount(masterList.get(index).getCuenta(),
				this.getUserDetails().getIdSector());
		if (null != cuenta) {
			this.getSelected().setNomcta(cuenta.getNomcta());
			masterList.set(index, this.getSelected());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La cuenta no existe en la base");
		}
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}
}
