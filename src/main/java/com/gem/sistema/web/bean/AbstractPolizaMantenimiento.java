package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TcRetencione;
import com.gem.sistema.business.repository.catalogs.CatfluRepository;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.PoliflRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcRegistraArchivoDigitalRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve;
import com.gem.sistema.business.service.catalogos.CopomeService;
import com.gem.sistema.business.service.catalogos.CuentaAdicionalService;
import com.gem.sistema.business.service.catalogos.DataYearSystemService;
import com.gem.sistema.business.service.catalogos.PolideService;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.web.datamodel.CuentaPolizaDataModel;
import com.gem.sistema.web.datamodel.PolideDataModel;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractPolizaMantenimiento.
 */
public class AbstractPolizaMantenimiento extends AbstractMB {

	/** The Constant FOUS_JQUERY. */
	protected static final String FOUS_JQUERY = "jQuery('#cuenta').eq(%1$s).each(function(){jQuery(this).focus});";

	/** The Constant POLICIES_LOCK_PREFIX. */
	protected static final String POLICIES_LOCK_PREFIX = "POLICE";

	/** The Constant POLIZA_CARGO_ABONO_MSG. */
	protected static final String POLIZA_CARGO_ABONO_MSG = FrontProperty.getPropertyValue("poliza.cargo.abono.msg");

	/** The Constant EDIT_FLOW_ROW_JQUERY. */
	protected static final String EDIT_FLOW_ROW_JQUERY = "jQuery('#formFlujoEfectivo\\\\:ctafluTable span.ui-icon-pencil').eq(%1$s).each(function(){jQuery(this).click()});";
	// protected static final String FOCUS_FLOW_CLVFLU_ROW_JQUERY =
	/** The Constant FOCUS_FLOW_CLVFLU_ROW_JQUERY. */
	// "jQuery('#formFlujoEfectivo\\\\:ctafluTable\\\\:%1$s\\\\:oclvflu').focus();";
	protected static final String FOCUS_FLOW_CLVFLU_ROW_JQUERY = "PrimeFaces.focus('formFlujoEfectivo:ctafluTable:%1$s:oclvflu');";

	/** The Constant FOCUS2_FLOW_CLVFLU_ROW_JQUERY. */
	protected static final String FOCUS2_FLOW_CLVFLU_ROW_JQUERY = "$(document.getElementById('formFlujoEfectivo:ctafluTable:%1$s:ocanflu')).focus();";

	/** The Constant ROWEDIT_CLVFLU_ROW_JQUERY. */
	protected static final String ROWEDIT_CLVFLU_ROW_JQUERY = "jQuery('#formFlujoEfectivo\\\\:ctafluTable span.ui-icon-check').last().click();";

	/** The Constant EDIT_CANCELED. */
	protected static final String EDIT_CANCELED = FrontProperty.getPropertyValue("poliza.flujo.efectivo.edit.canceled");

	/** The Constant ERROR_MSG. */
	protected static final String ERROR_MSG = "errorMsg";

	/** The Constant AFFECTED_POLICY_ST. */
	protected static final String AFFECTED_POLICY_ST = "A";

	/** The Constant ERROR. */
	protected static final String ERROR = "Error";

	/** The Constant DEC_FORMAT. */
	protected static final DecimalFormat DEC_FORMAT = new DecimalFormat("###,###,###,###.00");

	/** The Constant PAGE_SIZE. */
	protected static final Integer PAGE_SIZE = 1;

	/** The Constant POLEIN_TIO_MES_CONT. */
	protected static final Sort POLEIN_TIO_MES_CONT = Sort.by(Sort.Direction.ASC, "tippol", "mespol", "conpol");

	/** The Constant FIND_ACC_NAME. */
	protected static final String FIND_ACC_NAME = "FIND_ACC_NAME";

	/** The Constant UPDATE_POLIDE. */
	protected static final String UPDATE_POLIDE = "UPDATE_POLIDE";

	/** The Constant MESSAGE_EDITION_CANCELED. */
	protected static final String MESSAGE_EDITION_CANCELED = FrontProperty
			.getPropertyValue("catalog.message.edition.canceled");

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	protected static final String REPORT_PATH_JASPER_POLICY = FrontProperty
			.getPropertyValue("view.report.path.jasper.poliza");

	/** Nombre del reporte en texto plano. */
	protected static final String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.poliza");

	/** Mensaje Error. */
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The Constant FOCUS_REFPOL. */
	protected static final String FOCUS_REFPOL = "$(document.getElementById('form1:objects:%1$s:refpol')).focus();";

	/** The Constant ACCOUNT_TYPE_8217_REGEXP. */
	protected static final String ACCOUNT_TYPE_8217_REGEXP = FrontProperty
			.getPropertyValue("accountService.account.8217.pattern");

	/** The Constant ACCOUNT_TYPE_52XX_REGEXP. */
	protected static final String ACCOUNT_TYPE_52XX_REGEXP = FrontProperty.getPropertyValue("catalog.account.type82x");

	/** Habilitar el modo de edicion. */
	protected static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = FrontProperty
			.getPropertyValue("view.edit.row.activate.icon.pencil");

	/** Habilitar el modo de edicion. */
	protected static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = FrontProperty
			.getPropertyValue("view.edit.row.activate.icon.pencil.complement");

	/** Habilitar el modo de edicion. */
	protected static final String VIEW_CATALOG_FORM1_OBJECTS = FrontProperty
			.getPropertyValue("view.catalog.form1.objects");

	/** The Constant FOCUS_BY_ROWID. */
	protected static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:objects:%1$s:cuenta');";

	/** The Constant CLVFLU_FORMATTER. */
	protected static final DecimalFormat CLVFLU_FORMATTER = new DecimalFormat("00.00");

	/** The Constant VIEW_CATALOG_FORM1. */
	protected static final String VIEW_CATALOG_FORM1 = FrontProperty.getPropertyValue("view.catalog.form1");

	/** The activa adicionar. */
	private boolean activaAdicionar = Boolean.FALSE;

	/** The activa modifcar. */
	private boolean activaModifcar = Boolean.FALSE;

	/** The editable table. */
	private boolean editableTable = Boolean.FALSE;

	/** The bloquea boton. */
	private boolean bloqueaBoton = Boolean.FALSE;

	/** The repeat concept. */
	private boolean repeatConcept = Boolean.FALSE;

	/** The bandera. */
	private boolean bandera = Boolean.FALSE;

	/** The valida flujo. */
	private boolean validaFlujo = Boolean.FALSE;

	/** The flow validation enable. */
	private boolean flowValidationEnable = Boolean.FALSE;

	/** The repetgir. */
	private boolean repetgir = Boolean.FALSE;

	/** The activ buttons. */
	private boolean activButtons = Boolean.FALSE;

	/** Longitud del primer nivel de cuentas. */
	public static final int LENGTH_FIRST_LEVEL = 4;

	/** Longitud del segundo nivel de cuentas. */
	public static final int LENGTH_SECOND_LEVEL = 15;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_THIRD_LEVEL = 18;

	/** Longitud del cuarto nivel de cuentas. */
	public static final int LENGTH_FOUR_LEVEL = 4;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_FIVE_LEVEL = 4;

	
	/** The Constant FE_ACCS. */
	protected static final List<String> FE_ACCS = new ArrayList<String>();

	static {
//		FE_ACCS.add("1111");
		FE_ACCS.add("1112");
//		FE_ACCS.add("1113");
	}

	/** The con poliz sirvice. */
	@Value("#{consultaPolizaSirveImpl}")
	private ConsultaPolizaSirve conPolizSirvice;

	/** The copome service. */
	@Value("#{copomeService}")
	private CopomeService copomeService;

	/** The polide data model. */
	@Value("#{polideDataModel}")
	private PolideDataModel polideDataModel;

	/** The polifl repository. */
	@Value("#{poliflRepository}")
	private PoliflRepository poliflRepository;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The data year system service. */
	@Value("#{dataYearSystemService}")
	private DataYearSystemService dataYearSystemService;

	/** The polien repository. */
	@Value("#{polienRepository}")
	private PolienRepository polienRepository;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The cattpo repository. */
	@Value("#{cattpoRepository}")
	private CattpoRepository cattpoRepository;

	/** The polide repository. */
	@Value("#{polideRepository}")
	private PolideRepository polideRepository;

	/** The cuenta adicional service. */
	@Value("#{cuentaAdicionalService}")
	private CuentaAdicionalService cuentaAdicionalService;

	/** The cuenta poliza data model. */
	@Value("#{cuentaPolizaDataModel}")
	private CuentaPolizaDataModel cuentaPolizaDataModel;

	/** The poliza service. */
	@Value("#{polizaService}")
	private PolizaService polizaService;

	/** The catflu repository. */
	@Value("#{catfluRepository}")
	private CatfluRepository catfluRepository;

	/** Servicios para el modulo de centas. */
	@Value("#{accountService}")
	private AccountService accountService;

	/** The cuenta repository. */
	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** The exis digital. */
	@Value("#{tcRegistraArchivoDigitalRepository}")
	private TcRegistraArchivoDigitalRepository exisDigital;

	/** The polide service. */
	@Value("#{polideService}")
	private PolideService polideService;

	/** The parametros repository. */
	@Value("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/**
	 * Checks if is activa adicionar.
	 *
	 * @return true, if is activa adicionar
	 */
	public boolean isActivaAdicionar() {
		return activaAdicionar;
	}

	/**
	 * Sets the activa adicionar.
	 *
	 * @param activaAdicionar the new activa adicionar
	 */
	public void setActivaAdicionar(boolean activaAdicionar) {
		this.activaAdicionar = activaAdicionar;
	}

	/**
	 * Checks if is activa modifcar.
	 *
	 * @return true, if is activa modifcar
	 */
	public boolean isActivaModifcar() {
		return activaModifcar;
	}

	/**
	 * Sets the activa modifcar.
	 *
	 * @param activaModifcar the new activa modifcar
	 */
	public void setActivaModifcar(boolean activaModifcar) {
		this.activaModifcar = activaModifcar;
	}

	/**
	 * Checks if is editable table.
	 *
	 * @return true, if is editable table
	 */
	public boolean isEditableTable() {
		return editableTable;
	}

	/**
	 * Sets the editable table.
	 *
	 * @param editableTable the new editable table
	 */
	public void setEditableTable(boolean editableTable) {
		this.editableTable = editableTable;
	}

	/**
	 * Checks if is bloquea boton.
	 *
	 * @return true, if is bloquea boton
	 */
	public boolean isBloqueaBoton() {
		return bloqueaBoton;
	}

	/**
	 * Sets the bloquea boton.
	 *
	 * @param bloqueaBoton the new bloquea boton
	 */
	public void setBloqueaBoton(boolean bloqueaBoton) {
		this.bloqueaBoton = bloqueaBoton;
	}

	/**
	 * Checks if is repeat concept.
	 *
	 * @return true, if is repeat concept
	 */
	public boolean isRepeatConcept() {
		return repeatConcept;
	}

	/**
	 * Sets the repeat concept.
	 *
	 * @param repeatConcept the new repeat concept
	 */
	public void setRepeatConcept(boolean repeatConcept) {
		this.repeatConcept = repeatConcept;
	}

	/**
	 * Checks if is bandera.
	 *
	 * @return true, if is bandera
	 */
	public boolean isBandera() {
		return bandera;
	}

	/**
	 * Sets the bandera.
	 *
	 * @param bandera the new bandera
	 */
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	/**
	 * Checks if is valida flujo.
	 *
	 * @return true, if is valida flujo
	 */
	public boolean isValidaFlujo() {
		return validaFlujo;
	}

	/**
	 * Sets the valida flujo.
	 *
	 * @param validaFlujo the new valida flujo
	 */
	public void setValidaFlujo(boolean validaFlujo) {
		this.validaFlujo = validaFlujo;
	}

	/**
	 * Checks if is flow validation enable.
	 *
	 * @return true, if is flow validation enable
	 */
	public boolean isFlowValidationEnable() {
		return flowValidationEnable;
	}

	/**
	 * Sets the flow validation enable.
	 *
	 * @param flowValidationEnable the new flow validation enable
	 */
	public void setFlowValidationEnable(boolean flowValidationEnable) {
		this.flowValidationEnable = flowValidationEnable;
	}

	/**
	 * Checks if is repetgir.
	 *
	 * @return true, if is repetgir
	 */
	public boolean isRepetgir() {
		return repetgir;
	}

	/**
	 * Sets the repetgir.
	 *
	 * @param repetgir the new repetgir
	 */
	public void setRepetgir(boolean repetgir) {
		this.repetgir = repetgir;
	}

	/**
	 * Checks if is activ buttons.
	 *
	 * @return true, if is activ buttons
	 */
	public boolean isActivButtons() {
		return activButtons;
	}

	/**
	 * Sets the activ buttons.
	 *
	 * @param activButtons the new activ buttons
	 */
	public void setActivButtons(boolean activButtons) {
		this.activButtons = activButtons;
	}

	/**
	 * Gets the con poliz sirvice.
	 *
	 * @return the conPolizSirvice
	 */
	public ConsultaPolizaSirve getConPolizSirvice() {
		return conPolizSirvice;
	}

	/**
	 * Sets the con poliz sirvice.
	 *
	 * @param conPolizSirvice the conPolizSirvice to set
	 */
	public void setConPolizSirvice(ConsultaPolizaSirve conPolizSirvice) {
		this.conPolizSirvice = conPolizSirvice;
	}

	/**
	 * Gets the copome service.
	 *
	 * @return the copomeService
	 */
	public CopomeService getCopomeService() {
		return copomeService;
	}

	/**
	 * Sets the copome service.
	 *
	 * @param copomeService the copomeService to set
	 */
	public void setCopomeService(CopomeService copomeService) {
		this.copomeService = copomeService;
	}

	/**
	 * Gets the polide data model.
	 *
	 * @return the polideDataModel
	 */
	public PolideDataModel getPolideDataModel() {
		return polideDataModel;
	}

	/**
	 * Sets the polide data model.
	 *
	 * @param polideDataModel the polideDataModel to set
	 */
	public void setPolideDataModel(PolideDataModel polideDataModel) {
		this.polideDataModel = polideDataModel;
	}

	/**
	 * Gets the polifl repository.
	 *
	 * @return the poliflRepository
	 */
	public PoliflRepository getPoliflRepository() {
		return poliflRepository;
	}

	/**
	 * Sets the polifl repository.
	 *
	 * @param poliflRepository the poliflRepository to set
	 */
	public void setPoliflRepository(PoliflRepository poliflRepository) {
		this.poliflRepository = poliflRepository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctbRepository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the conctbRepository to set
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the data year system service.
	 *
	 * @return the dataYearSystemService
	 */
	public DataYearSystemService getDataYearSystemService() {
		return dataYearSystemService;
	}

	/**
	 * Sets the data year system service.
	 *
	 * @param dataYearSystemService the dataYearSystemService to set
	 */
	public void setDataYearSystemService(DataYearSystemService dataYearSystemService) {
		this.dataYearSystemService = dataYearSystemService;
	}

	/**
	 * Gets the polien repository.
	 *
	 * @return the polienRepository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository the polienRepository to set
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tcMesRepository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the tcMesRepository to set
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the cattpo repository.
	 *
	 * @return the cattpoRepository
	 */
	public CattpoRepository getCattpoRepository() {
		return cattpoRepository;
	}

	/**
	 * Sets the cattpo repository.
	 *
	 * @param cattpoRepository the cattpoRepository to set
	 */
	public void setCattpoRepository(CattpoRepository cattpoRepository) {
		this.cattpoRepository = cattpoRepository;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polideRepository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the polideRepository to set
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the cuenta adicional service.
	 *
	 * @return the cuentaAdicionalService
	 */
	public CuentaAdicionalService getCuentaAdicionalService() {
		return cuentaAdicionalService;
	}

	/**
	 * Sets the cuenta adicional service.
	 *
	 * @param cuentaAdicionalService the cuentaAdicionalService to set
	 */
	public void setCuentaAdicionalService(CuentaAdicionalService cuentaAdicionalService) {
		this.cuentaAdicionalService = cuentaAdicionalService;
	}

	/**
	 * Gets the cuenta poliza data model.
	 *
	 * @return the cuenta poliza data model
	 */
	public CuentaPolizaDataModel getCuentaPolizaDataModel() {
		return cuentaPolizaDataModel;
	}

	/**
	 * Sets the cuenta poliza data model.
	 *
	 * @param cuentaPolizaDataModel the new cuenta poliza data model
	 */
	public void setCuentaPolizaDataModel(CuentaPolizaDataModel cuentaPolizaDataModel) {
		cuentaPolizaDataModel.setIdSector(this.getUserDetails().getIdSector());
		this.cuentaPolizaDataModel = cuentaPolizaDataModel;
	}

	/**
	 * Gets the poliza service.
	 *
	 * @return the polizaService
	 */
	public PolizaService getPolizaService() {
		return polizaService;
	}

	/**
	 * Sets the poliza service.
	 *
	 * @param polizaService the polizaService to set
	 */
	public void setPolizaService(PolizaService polizaService) {
		this.polizaService = polizaService;
	}

	/**
	 * Gets the catflu repository.
	 *
	 * @return the catfluRepository
	 */
	public CatfluRepository getCatfluRepository() {
		return catfluRepository;
	}

	/**
	 * Sets the catflu repository.
	 *
	 * @param catfluRepository the catfluRepository to set
	 */
	public void setCatfluRepository(CatfluRepository catfluRepository) {
		this.catfluRepository = catfluRepository;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the accountService
	 */
	public AccountService getAccountService() {
		return accountService;
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService the accountService to set
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuentaRepository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the cuentaRepository to set
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Gets the exis digital.
	 *
	 * @return the exisDigital
	 */
	public TcRegistraArchivoDigitalRepository getExisDigital() {
		return exisDigital;
	}

	/**
	 * Sets the exis digital.
	 *
	 * @param exisDigital the exisDigital to set
	 */
	public void setExisDigital(TcRegistraArchivoDigitalRepository exisDigital) {
		this.exisDigital = exisDigital;
	}

	/**
	 * Gets the polide service.
	 *
	 * @return the polideService
	 */
	public PolideService getPolideService() {
		return polideService;
	}

	/**
	 * Sets the polide service.
	 *
	 * @param polideService the polideService to set
	 */
	public void setPolideService(PolideService polideService) {
		this.polideService = polideService;
	}

	/**
	 * Gets the parametros repository.
	 *
	 * @return the parametros repository
	 */
	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	/**
	 * Sets the parametros repository.
	 *
	 * @param parametrosRepository the new parametros repository
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	/**
	 * Chane value empty.
	 *
	 * @param polide the polide
	 * @return the polide
	 */
	public Polide chaneValueEmpty(Polide polide) {
		if (null == polide.getScta()) {
			polide.setScta("");
		}
		if (null == polide.getSscta()) {
			polide.setSscta("");
		}
		if (null == polide.getSsscta()) {
			polide.setSsscta("");
		}
		if (null == polide.getSssscta()) {
			polide.setSssscta("");
		}
		return polide;

	}

	

}