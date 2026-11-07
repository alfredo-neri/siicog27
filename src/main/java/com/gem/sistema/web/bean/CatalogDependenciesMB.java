package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.Util.fillZerosToRight;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogs.CatdaaRepository;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogDependenciesMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogDependenciesMB extends CommonCatalogMB<Catdep> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant VIEW_EDIT_ROW_ACTIVATE_PENCIL. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";
	
	/** The Constant VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";
	
	/** The Constant VIEW_CANCEL_ROW_ACTIVATE_PENCIL. */
	private static final String VIEW_CANCEL_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-close').eq(";
	
	/** The Constant VIEW_CANCEL_ROW_ACTIVATE_PENCIL_COMPLEMENT. */
	private static final String VIEW_CANCEL_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";
	
	/** The Constant FOCUS_BY_ROWID. */
	protected static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:objects:%1$s:clvdgm');";

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDependenciesMB.class);

	/** Separador nombre dependencia. */
	private static final String SEPARATOR_NAME_DEP = ",";

	/** Longitud de la clave de dependencia. */
	private static final int LENGTH_CLVDEP = 10;

	/** The Constant OPERATOR_EQUAL. */
	private static final String OPERATOR_EQUAL = "=";
	
	/** The Constant CLVDEP. */
	private static final String CLVDEP = "clvdep";

	/** The not delete. */
	private Boolean notDelete;
	
	/** The b update. */
	private Boolean bUpdate;

	/** Registros filtrados. */
	private List<Catdgm> filteredDependenciesGeneral;

	/** Registros filtrados. */
	private List<Catdaa> filteredDependenciesAuxiliary;

	/**
	 * Dependencias generales.
	 */
	private List<Catdgm> dependenciesGeneral;

	/**
	 * Dependencias auxiliares.
	 */
	private List<Catdaa> dependenciesAuxiliary;

	/** Repositorio de Catdep. */
	@ManagedProperty(value = "#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** Repositorio de Catdaa. */
	@ManagedProperty(value = "#{catdaaRepository}")
	private CatdaaRepository catdaaRepository;

	/** Repositorio de Catdgm. */
	@ManagedProperty(value = "#{catdgmRepository}")
	private CatdgmRepository catdgmRepository;

	/** The xcatpro repository. */
	@ManagedProperty(value = "#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** The polide repository. */
	@ManagedProperty(value = "#{polideRepository}")
	private PolideRepository polideRepository;

	/** Registro seleccionado. */
	private Catdep rowSelected;

	/**
	 * Dependencia general seleccionada.
	 */
	private Catdgm depGeneralSelected;

	/**
	 * Dependencia auxiliar seleccionada.
	 */
	private Catdaa depAuxiliarySelected;

	/** The catdep buffer. */
	private Catdep catdepBuffer;
	
	/** The index buffer. */
	private Integer indexBuffer;

	/** Campo requerido clvdep. */
	// @Value("${catalog.message.field.required.clvdep}")
	private static final String FIELD_REQUIRED_CLVDEP = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvdep");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.dependencias}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.dependencias");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.dependencies}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.dependencies");

	/** Clvdep formato erroneo. */
	// @Value("${catalog.message.error.clvdep.zeroleft}")
	private static final String MESSAGE_ERROR_CLVDEP_ZEROLEFT = FrontProperty
			.getPropertyValue("catalog.message.error.clvdep.zeroleft");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_dependencies}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_dependencies");

	/**
	 * id sector = 1.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	// private static final int ID_SECTOR = 1;

	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Sets the catdaa repository.
	 *
	 * @param catdaaRepository the new catdaa repository
	 */
	public void setCatdaaRepository(CatdaaRepository catdaaRepository) {
		this.catdaaRepository = catdaaRepository;
	}

	/**
	 * Sets the catdgm repository.
	 *
	 * @param catdgmRepository the new catdgm repository
	 */
	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
	}

	/**
	 * Gets the not delete.
	 *
	 * @return the not delete
	 */
	public Boolean getNotDelete() {
		return notDelete;
	}

	/**
	 * Sets the not delete.
	 *
	 * @param notDelete the new not delete
	 */
	public void setNotDelete(Boolean notDelete) {
		this.notDelete = notDelete;
	}

	// Se generan getters and setters

	/**
	 * Gets the catdep buffer.
	 *
	 * @return the catdep buffer
	 */
	public Catdep getCatdepBuffer() {
		return catdepBuffer;
	}

	/**
	 * Sets the catdep buffer.
	 *
	 * @param catdepBuffer the new catdep buffer
	 */
	public void setCatdepBuffer(Catdep catdepBuffer) {
		this.catdepBuffer = catdepBuffer;
	}

	/**
	 * Gets the index buffer.
	 *
	 * @return the index buffer
	 */
	public Integer getIndexBuffer() {
		return indexBuffer;
	}

	/**
	 * Sets the index buffer.
	 *
	 * @param indexBuffer the new index buffer
	 */
	public void setIndexBuffer(Integer indexBuffer) {
		this.indexBuffer = indexBuffer;
	}

	/**
	 * Gets the b update.
	 *
	 * @return the b update
	 */
	public Boolean getbUpdate() {
		return bUpdate;
	}

	/**
	 * Sets the b update.
	 *
	 * @param bUpdate the new b update
	 */
	public void setbUpdate(Boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogDependenciesMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Catdep());
		setList(new ArrayList<Catdep>());
		setListNew(new ArrayList<Catdep>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		super.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.setbUpdate(Boolean.FALSE);
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogDependenciesMB  ");
		getBeanFind().setClvdep(null);
		getBeanFind().setNomdep(null);
		restartData();
		this.setList(this.fixClvs(this.getList()));
		this.setNotDelete(Boolean.FALSE);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */

	public void onInitRowEdit(final RowEditEvent event) {
		if (null != event.getObject()) {
			// Polide polide = (Polide) event.getObject();
			final Catdep catalog = (Catdep) event.getObject();
			if (null != catalog.getId() && catalog.getId() != 0) {
				this.catdepBuffer = catalog;
				this.setbUpdate(Boolean.TRUE);

				this.indexBuffer = catdepBuffer.getIndex();
			} else {
				this.indexBuffer = 0;

			}
			setBeanSelected(catalog);
		}
	}

	/**
	 * On row edit.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		final Catdep catalog = (Catdep) event.getObject();
		catalog.setClaveDgm(catalog.getClaveDgm().toUpperCase());
		catalog.setClaveDaa(catalog.getClaveDaa().toUpperCase());
		catalog.setCapdep(this.getUserDetails().getUsername());
		catalog.setFeccap(new Date());
		catalog.setUserid(this.getUserDetails().getUsername());
		catalog.setIdsector(this.getUserDetails().getIdSector());
		catalog.setIdRef(0L);
		catalog.setUltniv("S");
		// LOGGER.info(":: Editando Fila catalogDependenciesMB " +
		// rowSelected.getIndex() + "::" + catalog.getClaveDaa());
		final StringBuilder errorMsg = new StringBuilder();
		if (this.getbUpdate() == Boolean.TRUE
				&& xcatproRepository.findByClvdep(getBeanSelected().getClvdep()).size() > 0) {
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO,
					"No se puede editar porque tiene programas asociados");
			this.onRowCancel(event);
//			RequestContext.getCurrentInstance().execute("jQuery('span.ui-icon-close').eq(" + catalog.getIndex()
//					+ ").each(function(){jQuery(this).click()});");

		} else {
			if (isValidFieldClvdep(catalog.getClvdep(), errorMsg) && isValidCapturedData(catalog, errorMsg)
					&& isValidSaveOrUpdate(catalog, errorMsg)) {
				formatClvdepAndNomDep(catalog);

				executeOperationSaveOrUpdate(catalog, catdepRepository);
			} else {
				if (errorMsg.length() == ZERO) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
							.concat(FIELD_REQUIRED_CLVDEP).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
				} else {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
				}
				activateRowEdit(catalog.getIndex());
			}
		}
	}

	/**
	 * Checks if is valid field clvdep.
	 *
	 * @param clvdep the clvdep
	 * @param errorMsg the error msg
	 * @return true, if is valid field clvdep
	 */
	private boolean isValidFieldClvdep(final String clvdep, final StringBuilder errorMsg) {
		boolean result;
		if (StringUtils.isNotEmpty(clvdep) && clvdep.indexOf(String.valueOf(ZERO)) == ZERO) {
			errorMsg.append(MESSAGE_ERROR_CLVDEP_ZEROLEFT);
			result = Boolean.FALSE;
		} else {
			result = Boolean.TRUE;
		}
		return result;
	}

	/**
	 * Checks if is valid captured data.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final Catdep catalog, final StringBuilder errorMsg) {

		if (StringUtils.isEmpty(catalog.getClaveDgm()) || StringUtils.isEmpty(catalog.getClaveDaa())) {
			errorMsg.append("Se deben capturar los campos: Dependencia General y Dependencia Auxiliar");
			return Boolean.FALSE;
		}

		boolean result = CollectionUtils.isNotEmpty(catdgmRepository.findByClave(catalog.getClaveDgm()))
				&& CollectionUtils.isNotEmpty(catdaaRepository.findByClave(catalog.getClaveDaa()));

		if (errorMsg.length() == ZERO && BooleanUtils.negate(result)) {
			errorMsg.append(
					"La informacion capturada en Dependencia General / Dependencia Auxiliar no existe en la Base de datos.");
		}

		return result;
	}

	/**
	 * Checks if is valid save or update.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid save or update
	 */
	private boolean isValidSaveOrUpdate(final Catdep catalog, final StringBuilder errorMsg) {
		// En ambas se debe de vaidar que la concatenacion no exista
		final boolean result;
		if (StringUtils.isEmpty(catalog.getClaveDgm()) || StringUtils.isEmpty(catalog.getClaveDaa())) {
			errorMsg.append("Se deben capturar los campos: Dependencia General y Dependencia Auxiliar");
			result = Boolean.FALSE;
		} else {
			final List<Catdep> validateUnique = catdepRepository
					.findByClvdep(fillZerosToRight(catalog.getClaveDgm().concat(catalog.getClaveDaa()), LENGTH_CLVDEP));
			result = isValidSaveOrUpdate(validateUnique, catalog);
		}
		return result;
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		// System.out.println("borradooooooooooooo");
		LOGGER.info(":: Borrar registro catalogDependenciesMB " + getBeanSelected());
		if (xcatproRepository.findByClvdep(getBeanSelected().getClvdep()).size() > 0) {
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO,
					"No se puede eliminar por que tiene programas asociados");
		} else {
			if (tieneProgramas(getBeanSelected()) || tienePolizas(getBeanSelected())) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
						"Hay programas o polizas asociados a esta dependencia");
				return;
			}

			if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
				catdepRepository.delete(getBeanSelected());
			}

			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
			this.setList(this.fixClvs(this.getList()));
			restartDataOperationDelete();
			this.setList(this.fixClvs(this.getList()));
		}

	}

	/**
	 * Tiene programas.
	 *
	 * @param catalog the catalog
	 * @return the boolean
	 */
	private Boolean tieneProgramas(Catdep catalog) {
		List<Xcatpro> programas = xcatproRepository.findByClvdep(catalog.getClvdep());
		if (programas != null && programas.size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Tiene polizas.
	 *
	 * @param catalog the catalog
	 * @return the boolean
	 */
	private Boolean tienePolizas(Catdep catalog) {
		List<Polide> polizas = polideRepository.findByScta(catalog.getClvdep());
		if (polizas != null && polizas.size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogDependenciesMB {} ", getBeanFind());
		this.getBeanFind().setIdsector(getUserDetails().getIdSector());
		addList(this
				.fixClvs((List<Catdep>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), CLVDEP, OPERATOR_EQUAL)));
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogDependenciesMB {} ", getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(this.fixClvs(catdepRepository.findAllByIdsectorOrderByClvdep(this.getUserDetails().getIdSector())));
		restartFilteredList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#isValidRequest()
	 */
	@Override
	protected boolean isValidRequest() {
		return Boolean.TRUE;
	}

	/**
	 * Consult dependencies general.
	 */
	public void consultDependenciesGeneral() {
		LOGGER.info(":: Consultar Dependencias Generales ");
		dependenciesGeneral = catdgmRepository.findAll();
		restartFilteredDependenciesGenearl();
	}

	/**
	 * Restart filtered dependencies genearl.
	 */
	private void restartFilteredDependenciesGenearl() {
		if (BooleanUtils.negate(filteredDependenciesGeneral == null)
				&& BooleanUtils.negate(dependenciesGeneral == null)) {
			filteredDependenciesGeneral.clear();
			filteredDependenciesGeneral.addAll(dependenciesGeneral);
		}

	}

	/**
	 * Change dependence general.
	 */
	public void changeDependenceGeneral() {

		if (null != depGeneralSelected) {
			rowSelected.setClaveDgm(depGeneralSelected.getClave());
			activateRowEdit(indexBuffer);
			final RequestContext context = RequestContext.getCurrentInstance();
			context.update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":clvdgm");
		}
	}

	/**
	 * Consult dependencies auxiliary.
	 */
	public void consultDependenciesAuxiliary() {
		LOGGER.info(":: Consultar Dependencias Auxiliares ");
		dependenciesAuxiliary = catdaaRepository.findAll();
		restartFilteredDependenciesAuxiliary();
	}

	/**
	 * Restart filtered dependencies auxiliary.
	 */
	private void restartFilteredDependenciesAuxiliary() {
		if (BooleanUtils.negate(filteredDependenciesAuxiliary == null)
				&& BooleanUtils.negate(dependenciesAuxiliary == null)) {
			filteredDependenciesAuxiliary.clear();
			filteredDependenciesAuxiliary.addAll(dependenciesAuxiliary);
		}
	}

	/**
	 * Change dependence auxiliary.
	 */
	public void changeDependenceAuxiliary() {
		LOGGER.info(":: Cambiar Dependencia Auxiliar ");
		if (null != depAuxiliarySelected) {
			rowSelected.setClaveDaa(depAuxiliarySelected.getClave());
			activateRowEdit(rowSelected.getIndex());
			final RequestContext context = RequestContext.getCurrentInstance();
			context.update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":clvdaa");
		}

	}

	/**
	 * Format clvdep and nom dep.
	 *
	 * @param catalog the catalog
	 */
	private void formatClvdepAndNomDep(Catdep catalog) {
		// if ((depAuxiliarySelected == null) && catalog.getClaveDaa() != null)
		// {
		depAuxiliarySelected = catdaaRepository.findByClave(catalog.getClaveDaa()).get(0);
		// }

		// if (depGeneralSelected == null && catalog.getClaveDgm() != null) {
		depGeneralSelected = catdgmRepository.findByClave(catalog.getClaveDgm()).get(0);
		// }

		if (BooleanUtils.negate(depAuxiliarySelected == null) && BooleanUtils.negate(depGeneralSelected == null)) {
			rowSelected.setNomdep(
					depGeneralSelected.getNombre().concat(SEPARATOR_NAME_DEP).concat(depAuxiliarySelected.getNombre()));
			rowSelected.setClvdep(
					fillZerosToRight(rowSelected.getClaveDgm().concat(rowSelected.getClaveDaa()), LENGTH_CLVDEP));

		}
	}

	/**
	 * On clvdgm row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvdgmRowDblClckSelect(final SelectEvent event) {
		this.changeDependenceGeneral();
		// activateRowDg(indexBuffer);
		// rest of your logic
	}

	/**
	 * On clvdaa row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvdaaRowDblClckSelect(final SelectEvent event) {
		this.changeDependenceAuxiliary();
		// activateRowEdit(indexBuffer);
		// rest of your logic
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Gets the dependencies general.
	 *
	 * @return the dependenciesGeneral
	 */
	public List<Catdgm> getDependenciesGeneral() {
		return dependenciesGeneral;
	}

	/**
	 * Sets the dependencies general.
	 *
	 * @param dependenciesGeneral            the dependenciesGeneral to set
	 */
	public void setDependenciesGeneral(List<Catdgm> dependenciesGeneral) {
		this.dependenciesGeneral = dependenciesGeneral;
	}

	/**
	 * Gets the dependencies auxiliary.
	 *
	 * @return the dependenciesAuxiliary
	 */
	public List<Catdaa> getDependenciesAuxiliary() {
		return dependenciesAuxiliary;
	}

	/**
	 * Sets the dependencies auxiliary.
	 *
	 * @param dependenciesAuxiliary            the dependenciesAuxiliary to set
	 */
	public void setDependenciesAuxiliary(List<Catdaa> dependenciesAuxiliary) {
		this.dependenciesAuxiliary = dependenciesAuxiliary;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Catdep getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(Catdep rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the dep general selected.
	 *
	 * @return the depGeneralSelected
	 */
	public Catdgm getDepGeneralSelected() {
		return depGeneralSelected;
	}

	/**
	 * Sets the dep general selected.
	 *
	 * @param depGeneralSelected            the depGeneralSelected to set
	 */
	public void setDepGeneralSelected(final Catdgm depGeneralSelected) {
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(depGeneralSelected == null)) {
			this.depGeneralSelected = depGeneralSelected;
		}
	}

	/**
	 * Gets the dep auxiliary selected.
	 *
	 * @return the depAuxiliarySelected
	 */
	public Catdaa getDepAuxiliarySelected() {
		return depAuxiliarySelected;
	}

	/**
	 * Sets the dep auxiliary selected.
	 *
	 * @param depAuxiliarySelected            the depAuxiliarySelected to set
	 */
	public void setDepAuxiliarySelected(final Catdaa depAuxiliarySelected) {
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(depAuxiliarySelected == null)) {
			this.depAuxiliarySelected = depAuxiliarySelected;
		}
	}

	/**
	 * Gets the filtered dependencies general.
	 *
	 * @return the filteredDependenciesGeneral
	 */
	public List<Catdgm> getFilteredDependenciesGeneral() {
		return filteredDependenciesGeneral;
	}

	/**
	 * Sets the filtered dependencies general.
	 *
	 * @param filteredDependenciesGeneral            the filteredDependenciesGeneral to set
	 */
	public void setFilteredDependenciesGeneral(List<Catdgm> filteredDependenciesGeneral) {
		this.filteredDependenciesGeneral = filteredDependenciesGeneral;
	}

	/**
	 * Gets the filtered dependencies auxiliary.
	 *
	 * @return the filteredDependenciesAuxiliary
	 */
	public List<Catdaa> getFilteredDependenciesAuxiliary() {
		return filteredDependenciesAuxiliary;
	}

	/**
	 * Sets the filtered dependencies auxiliary.
	 *
	 * @param filteredDependenciesAuxiliary            the filteredDependenciesAuxiliary to set
	 */
	public void setFilteredDependenciesAuxiliary(List<Catdaa> filteredDependenciesAuxiliary) {
		this.filteredDependenciesAuxiliary = filteredDependenciesAuxiliary;
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());

		return super.getFileXls(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {

	}

	/**
	 * Fix clvs.
	 *
	 * @param aList the a list
	 * @return the list
	 */
	private List<Catdep> fixClvs(List<Catdep> aList) {
		if (aList != null) {
			for (Catdep c : aList) {
				c.setClaveDgm(c.getClvdep().substring(0, 3));
				c.setClaveDaa(c.getClvdep().substring(3, 6));
				// c.setNotDelete(Boolean.FALSE);
				// if
				// (CollectionUtils.isNotEmpty(this.xcatproRepository.findByClvdepAndSectorid(c.getClvdep(),
				// this.getUserDetails().getIdSector()))) {
				// c.setNotDelete(Boolean.TRUE);
				// }

			}
		}

		return aList;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Activate row dg.
	 *
	 * @param index the index
	 */
	public void activateRowDg(final int index) {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());

	}

	/**
	 * Active row.
	 */
	public void activeRow() {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(rowSelected.getIndex());
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, rowSelected.getIndex()));
		RequestContext.getCurrentInstance().execute(text.toString());

	}

	/**
	 * Cancel row.
	 *
	 * @param index the index
	 */
	public void cancelRow(int index) {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_CANCEL_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_CANCEL_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");

		RequestContext.getCurrentInstance().execute(text.toString());
	}

}
