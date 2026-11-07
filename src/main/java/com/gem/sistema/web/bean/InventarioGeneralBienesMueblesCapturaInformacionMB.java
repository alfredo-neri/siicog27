package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
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

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Bienesm;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.repository.catalogs.BienesmRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioGeneralBienesMueblesCapturaInformacionMB.
 */
@Component("inventarioGeneralBienesMueblesCapturaInformacionMB")
@Scope("view")
public class InventarioGeneralBienesMueblesCapturaInformacionMB extends BaseDirectReport {

	/** The firmas repository. */
	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The repository. */
	@Value("#{bienesmRepository}")
	private BienesmRepository repository;

	/** The cuenta repository. */
	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** The master list. */
	private List<Bienesm> masterList;

	/** The selected. */
	private Bienesm selected = new Bienesm();

	/** The mes. */
	private Integer mes;

	/** The current page. */
	private int currentPage = 0;

	/** The editando. */
	private boolean editando = false;

	/** The b btn modificar. */
	private Boolean bBtnModificar;

	/** The b salvar. */
	private Boolean bSalvar;

	/** The selectable month. */
	private List<Integer> selectableMonth;

	/** The valid months. */
	private List<Integer> validMonths = new ArrayList<Integer>();

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct InventarioGeneralBienesMueblesCapturaInformacionMB ");
		inicializar();

	}

	/*
	 * (non-Javadoc)
	 * 
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
			masterList = new ArrayList<Bienesm>();
			masterList.add(initializedEntity());
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Initialized entity.
	 *
	 * @return the bienesm
	 */
	private Bienesm initializedEntity() {
		Bienesm entity = new Bienesm();
		entity.setMes(0);
		entity.setIdsector(getUserDetails().getIdSector());
		entity.setCapturo(getUserDetails().getUsername());
		entity.setFeccap(new Date());
		entity.setIdRef(getUserDetails().getIdSector() - 1l);
		entity.setUserid(getUserDetails().getUsername());
		entity.setConsec(0);
		entity.setConpol(0);
		entity.setCosto(BigDecimal.ZERO);
		entity.setDepreperiodo(BigDecimal.ZERO);
		entity.setDepreacum(BigDecimal.ZERO);
		return entity;
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Bienesm last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este años crea el mes 1
		Bienesm entity = initializedEntity();

		List<Integer> currentMonths = masterList.stream().map(Bienesm::getMes).collect(Collectors.toList());
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
		PrimeFaces.current()
				.executeScript("PF('dataGrid').getPaginator().setPage(" + (masterList.size() - 1) + ");");
		if (currentPage == -1) {
			currentPage = 0;
			actualizaSeleccionado();
		}
		this.setbBtnModificar(Boolean.FALSE);
		this.setbSalvar(Boolean.TRUE);
	}

	/**
	 * Modificar.
	 */
	public void modificar() {
		editando = true;
		this.setbBtnModificar(Boolean.FALSE);
		this.setbSalvar(Boolean.TRUE);
	}

	/**
	 * Salvar.
	 */
	public void salvar() {
		Bienesm nuevo = masterList.get(currentPage);
		if (editando && validarCampos(nuevo)) {
			editando = false;

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";

			sortMasterList();

			nuevo = checkNullsAndSetDefaultValues(nuevo);
			Bienesm bienesm = repository.save(nuevo);
			masterList.set(currentPage, bienesm);
			selected = bienesm;
			PrimeFaces.current().executeScript("PF('dataGrid').getPaginator().setPage(" + currentPage + ");");

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
	private boolean validarCampos(Bienesm nuevo) {
		boolean isValid = false;
		Bienesm bienesm = repository.findByIdsectorAndMesAndConsec(getUserDetails().getIdSector(), nuevo.getMes(),
				nuevo.getConsec());
		if (Objects.nonNull(nuevo.getConsec()) && nuevo.getConsec() < 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"Capturar consecutivo diferente de cero.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			PrimeFaces.current()
					.executeScript("focusEl('form1:dataGrid:" + currentPage + ":inputConsecutivo');");
		} else if (Objects.nonNull(bienesm) && Objects.isNull(nuevo.getId())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, "El consecutivo "
					+ bienesm.getConsec() + " para el mes " + bienesm.getMes() + " ya existe en la base de datos.");
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
		masterList.sort(Comparator.comparing(Bienesm::getMes).thenComparing(Bienesm::getConsec));
		final Bienesm comparator = selected;
		currentPage = IntStream.range(0, masterList.size()).filter(p -> {
			Bienesm obj = masterList.get(p);
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
			selected = repository.findById(selected.getId()).orElse(null);
			masterList.set(currentPage, selected);
		}
	}

	/**
	 * Delete.
	 */
	public void delete() {
		repository.deleteById(selected.getId());
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
		PrimeFaces.current().ajax().update("form1:panel-filtros");
	}

	/**
	 * Check nulls and set default values.
	 *
	 * @param <T>    the generic type
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
	public List<Bienesm> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Bienesm> masterList) {
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
	public Bienesm getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Bienesm selected) {
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
	public BienesmRepository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(BienesmRepository repository) {
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

	/*
	 * (non-Javadoc)
	 * 
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
		Cuenta cuenta = this.getCuentaRepository().findFirstByCuentaAndScuentaAndIdsector(
				masterList.get(index).getCuenta(), masterList.get(index).getScta(),
				Long.valueOf(this.getUserDetails().getIdSector()));
		if (null != cuenta) {
			this.getSelected().setNomcta(cuenta.getNomcta());
			masterList.set(index, this.getSelected());
		} else {
			this.getSelected().setNomcta(StringUtils.EMPTY);
			masterList.set(index, this.getSelected());
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La cuenta no existe en la base");
		}
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
	 * @param bBtnModificar the new b btn modificar
	 */
	public void setbBtnModificar(Boolean bBtnModificar) {
		this.bBtnModificar = bBtnModificar;
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
	 * @param bSalvar the new b salvar
	 */
	public void setbSalvar(Boolean bSalvar) {
		this.bSalvar = bSalvar;
	}

}
