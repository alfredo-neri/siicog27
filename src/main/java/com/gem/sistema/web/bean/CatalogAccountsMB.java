package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ONE;
import static com.gem.sistema.util.Constants.PREFIX_ACCOUNT_MAJOR;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.ValidateAccountDTO;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.predicates.DependencePredicates;
import com.gem.sistema.business.predicates.MayctaPredicates;
import com.gem.sistema.business.predicates.MuniNepPredicates;
import com.gem.sistema.business.predicates.NatgasPredicates;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.MayctaRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.sorts.CuentaSort;
import com.gem.sistema.util.Constants;
import com.gem.sistema.util.UtilJPACuentas;
import com.gem.sistema.web.util.FrontProperty;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogAccountsMB.
 */
@ManagedBean(name = "catalogAccountsMB")
@ViewScoped
public class CatalogAccountsMB extends CommonCatalogCuentasMB<Cuenta> implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogAccountsMB.class);

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

	/**
	 * Mapa para guardar las filas seleccionadas del catalogo de naturaleza de
	 * gasto.
	 */
	private Map<Integer, Natgas> mapNatureExp;

	/**
	 * Mapa para guardar las filas seleccionadas del catalogo de cuentas de
	 * mayor.
	 */
	private Map<Integer, Maycta> mapAccountsMajor;

	/**
	 * Mapa para guardar las filas seleccionadas del catalogo de programas.
	 */
	private Map<Integer, Xcatpro> mapProgram;

	/**
	 * Mapa para guardar las filas seleccionadas del catalogo de dependencias.
	 */
	private Map<Integer, Catdep> mapDependencies;

	/**
	 * Lista de cuentas de mayor.
	 */
	private List<Maycta> accountsMajor;

	/**
	 * Almacena los registros filtrados del catalogo de cuentas de mayor.
	 */
	private List<Maycta> filteredAccountsMajor = new ArrayList<Maycta>();

	/**
	 * Lista de dependencias.
	 */
	private List<Catdep> dependencies;

	/**
	 * Almacena los registros filtrados del catalogo de dependencias.
	 */
	private List<Catdep> filteredDependencies;

	/**
	 * Lista de programas.
	 */
	private List<Xcatpro> programs;

	/**
	 * Almacena los registros filtrados del catalogo de programas.
	 */
	private List<Xcatpro> filteredPrograms;

	/**
	 * Lista de naturaleza de gasto.
	 */
	private List<Natgas> listNatureExp;

	/**
	 * Almacena los registros filtrados del catalogo de naturaleza de gasto.
	 */
	private List<Natgas> filteredNatureExp;

	/**
	 * Valores originales del campo cta.
	 */
	private List<String> originalCta;

	/**
	 * Valores originales del campo scta.
	 */
	private List<String> originalScta;

	/**
	 * Valores originales del campo sscta.
	 */
	private List<String> originalSscta;

	/**
	 * Valores originales del campo ssscta.
	 */
	private List<String> originalSsscta;

	/**
	 * Naturaleza de gasto seleccionada.
	 */
	private Natgas natureExpSelected;

	/**
	 * Programa seleccionado.
	 */
	private Xcatpro programSelected;

	/**
	 * Dependencia seleccionada.
	 */
	private Catdep dependenceSelected;

	/**
	 * Cuenta de mayor seleccionada.
	 */
	private Maycta accountMajorSelected;

	/** Servicios para el modulo de centas. */
	@ManagedProperty(value = "#{accountService}")
	private AccountService accountService;

	/**
	 * Repositorio de Cuenta.
	 */
	@ManagedProperty(value = "#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/**
	 * Repositorio de Dependencias.
	 */

	@ManagedProperty(value = "#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/**
	 * Repositorio de Programas.
	 */
	@ManagedProperty(value = "#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/**
	 * Repositorio de Maycta.
	 */
	@ManagedProperty(value = "#{mayctaRepository}")
	private MayctaRepository mayctaRepository;

	/**
	 * Repositorio de Maycta.
	 */
	@ManagedProperty(value = "#{natgasRepository}")
	private NatgasRepository natgasRepository;

	/** Fila seleccionada del catalogo de programas. */
	private Cuenta rowSelected;

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.cuentas}")
	private static final String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.cuentas");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.cuentas}")
	private static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty.getPropertyValue("header.text.plain.cuentas");

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${view.report.path.jasper.cuentas}")
	private static final String REPORT_PATH_JASPER_ACCOUNTS = FrontProperty
			.getPropertyValue("view.report.path.jasper.cuentas");

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${catalog.message.data.required.save}")
	private static final String REPORT_MESSAGE_DATA_REQUIRED = FrontProperty
			.getPropertyValue("catalog.message.data.required.save");

	/** The Constant ACCOUNT_TYPE_5X_REGEXP. */
	// @Value("${catalog.account.type5x}")
	private static final String ACCOUNT_TYPE_5X_REGEXP = FrontProperty.getPropertyValue("catalog.account.type5x");

	/** The Constant ACCOUNT_TYPE_52_REGEXP. */
	// @Value("${catalog.account.type52}")
	private static final String ACCOUNT_TYPE_52_REGEXP = FrontProperty.getPropertyValue("catalog.account.type52");
	
	/** The Constant ACCOUNT_TYPE_53_REGEXP. */
	// @Value("${catalog.account.type53}")
	private static final String ACCOUNT_TYPE_53_REGEXP = FrontProperty.getPropertyValue("catalog.account.type53");
	
	/** The Constant ACCOUNT_TYPE_54_REGEXP. */
	// @Value("${catalog.account.type54}")
	private static final String ACCOUNT_TYPE_54_REGEXP = FrontProperty.getPropertyValue("catalog.account.type54");
	
	/** The Constant ACCOUNT_TYPE_55_REGEXP. */
	// @Value("${catalog.account.type55}")
	private static final String ACCOUNT_TYPE_55_REGEXP = FrontProperty.getPropertyValue("catalog.account.type55");
	
	/** The Constant ACCOUNT_TYPE_56_REGEXP. */
	// @Value("${catalog.account.type56}")
	private static final String ACCOUNT_TYPE_56_REGEXP = FrontProperty.getPropertyValue("catalog.account.type56");
	
	/** The Constant ACCOUNT_TYPE_57_REGEXP. */
	// @Value("${catalog.account.type57}")
	private static final String ACCOUNT_TYPE_57_REGEXP = FrontProperty.getPropertyValue("catalog.account.type57");
	
	/** The Constant ACCOUNT_TYPE_51_REGEXP. */
	// @Value("${catalog.account.type51}")
	private static final String ACCOUNT_TYPE_51_REGEXP = FrontProperty.getPropertyValue("catalog.account.type51");

	/** The Constant ACCOUNT_TYPE_51_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type51}")
	private static final String ACCOUNT_TYPE_51_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type51");
	
	/** The Constant ACCOUNT_TYPE_52_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type52}")
	private static final String ACCOUNT_TYPE_52_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type52");
	
	/** The Constant ACCOUNT_TYPE_53_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type53}")
	private static final String ACCOUNT_TYPE_53_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type53");
	
	/** The Constant ACCOUNT_TYPE_54_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type54}")
	private static final String ACCOUNT_TYPE_54_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type54");
	
	/** The Constant ACCOUNT_TYPE_55_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type55}")
	private static final String ACCOUNT_TYPE_55_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type55");
	
	/** The Constant ACCOUNT_TYPE_56_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type56}")
	private static final String ACCOUNT_TYPE_56_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type56");
	
	/** The Constant ACCOUNT_TYPE_57_PRESUP_REGEXP. */
	// @Value("${catalog.account.presup.type57}")
	private static final String ACCOUNT_TYPE_57_PRESUP_REGEXP = FrontProperty
			.getPropertyValue("catalog.account.presup.type57");

	/** The Constant ACCOUNT_TYPE_8217_REGEXP. */
	// @Value("${accountService.account.8217.pattern}")
	private static final String ACCOUNT_TYPE_8217_REGEXP = FrontProperty
			.getPropertyValue("accountService.account.8217.pattern");

	/** The Constant ACCOUNT_PRESUP_8217. */
	// @Value("${catalog.account.presup.type8217}")
	private static final String ACCOUNT_PRESUP_8217 = FrontProperty.getPropertyValue("catalog.account.presup.type8217");

	/** The Constant ACC_INSERT_REGEXP. */
	// @Value("${catalog.message.account.insert.regexp}")
	private static final String ACC_INSERT_REGEXP = FrontProperty
			.getPropertyValue("catalog.message.account.insert.regexp");

	/** The Constant ACC_INSERT_2_REGEXP. */
	// @Value("${catalog.message.account.insert.regexp}")
	private static final String ACC_INSERT_2_REGEXP = FrontProperty
			.getPropertyValue("catalog.message.account.presup.regexp");

	/** The Constant TITLE_INSERT_REGEXP. */
	private static final String TITLE_INSERT_REGEXP = FrontProperty
			.getPropertyValue("catalog.message.title.insert.regexp");

	/** Mostrar cuadro de dialogo del catalogo de dependencias. */
	// @Value("${view.catalog.dependencies.dlg}")
	private static final String VIEW_DEPENDENCIES_DLG = FrontProperty.getPropertyValue("view.catalog.dependencies.dlg");

	/** Mostrar cuadro de dialogo del catalogo de programas. */
	// @Value("${view.catalog.programs.dlg}")
	private static final String VIEW_PROGRAMS_DLG = FrontProperty.getPropertyValue("view.catalog.programs.dlg");

	/** Mostrar cuadro de dialogo de naturaleza de gasto. */
	// @Value("${view.catalog.natureexp.dlg}")
	private static final String VIEW_NATUREEXP_DLG = FrontProperty.getPropertyValue("view.catalog.natureexp.dlg");

	/** Mensaje para indicar que la cuenta de egresos no es válida. */
	// @Value("${catalog.message.error.insert.account.expenditure}")
	private static final String ACCOUNT_EXPENDITURE_INVALID = FrontProperty
			.getPropertyValue("catalog.message.error.insert.account.expenditure");

	/** Mensaje para indicar que la cuenta presupuestal de egresos no es válida. */
	// @Value("${catalog.message.error.insert.account.expenditure.budget}")
	private static final String ACCOUNT_EXPENDITURE_BUDGET_INVALID = FrontProperty
			.getPropertyValue("catalog.message.error.insert.account.expenditure.budget");

	/** Mensaje de error, validacion de tipo numerico. */
	// @Value("${catalog.message.error.numeric.type}")
	private static final String ERROR_MESSAGE_NUMERIC_TYPE = FrontProperty
			.getPropertyValue("catalog.message.error.numeric.type");

	/** Mensaje de error, validacion de tipo numerico. */
	// @Value("${catalog.message.error.numeric.type.complement}")
	private static final String ERROR_MESSAGE_NUMERIC_TYPE_COMPLEMENT = " "
			+ FrontProperty.getPropertyValue("catalog.message.error.numeric.type.complement");

	/** Campo Scuenta. */
	// @Value("${catalog.message.field.required.scuenta}")
	private static final String FIELD_SCUENTA = FrontProperty
			.getPropertyValue("catalog.message.field.required.scuenta");

	/** Campo Sscuenta. */
	// @Value("${catalog.message.field.required.sscuenta}")
	private static final String FIELD_SSCUENTA = FrontProperty
			.getPropertyValue("catalog.message.field.required.sscuenta");

	/** Campo Ssscuenta. */
	// @Value("${catalog.message.field.required.ssscuenta}")
	private static final String FIELD_SSSCUENTA = FrontProperty
			.getPropertyValue("catalog.message.field.required.ssscuenta");

	/** Campo Sssscuenta. */
	// @Value("${catalog.message.field.required.sssscuenta}")
	private static final String FIELD_SSSSCUENTA = FrontProperty
			.getPropertyValue("catalog.message.field.required.sssscuenta");

	/** The Constant SCTA_REGEXP_EXP. */
	// @Value("${accountService.contable.scta}")
	private static final String SCTA_REGEXP_EXP = FrontProperty.getPropertyValue("accountService.contable.scta");

	/** The Constant DISPLAY_HELP_EXP. */
	// @Value("${accountService.display.help.pattern}")
	private static final String DISPLAY_HELP_EXP = FrontProperty
			.getPropertyValue("accountService.display.help.pattern");

	/** The Constant REG_EXP_NUMERIC_TYPES. */
	private static final String REG_EXP_NUMERIC_TYPES = "[0-9]*?";

	/** Campo Sssscuenta. */
	// @Value("${catalog.message.account.mayor.notfound}")
	private static final String MESSAGE_ACC_MAY_NOTFOUD = FrontProperty
			.getPropertyValue("catalog.message.account.mayor.notfound");

	/** Campo Sssscuenta. */
	// @Value("${catalog.message.account.budget.exclude}")
	private static final String ACC_BUDGET_EXC_REGEX = FrontProperty
			.getPropertyValue("catalog.message.account.budget.exclude");

	/** Campo Sssscuenta. */
	// @Value("${catalog.message.account.budget.nosave}")
	private static final String MESSAGE_ACC_BUDGET_NOSAVE = FrontProperty
			.getPropertyValue("catalog.message.account.budget.nosave");

	/** Define si se muestra o no la ventana de ayuda. */
	private boolean isShowHelpWindow;

	/** Define si la operacion es insertar o actualizar. */
	private boolean isUpdateFrontOperationSave;

	/**
	 * Contiene el mensaje de error.
	 */
	private StringBuilder errorMsg;

	/** The title active. */
	private boolean titleActive = Boolean.FALSE;

	/** The insert active. */
	private boolean insertActive = Boolean.TRUE;

	/** The muni nep repository. */
	@ManagedProperty(value = "#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/** The help active. */
	private boolean helpActive = Boolean.TRUE;

	/**
	 * Sets the account service.
	 *
	 * @param accountService the new account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
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
	 * Sets the maycta repository.
	 *
	 * @param mayctaRepository the new maycta repository
	 */
	public void setMayctaRepository(MayctaRepository mayctaRepository) {
		this.mayctaRepository = mayctaRepository;
	}

	/**
	 * Sets the natgas repository.
	 *
	 * @param natgasRepository the new natgas repository
	 */
	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
	}

	/** The Constant MSG_PARTIDA_MAP. */
	private static final Map<String, String> MSG_PARTIDA_MAP = new HashMap<String, String>();

	static {
		MSG_PARTIDA_MAP.put("51", "1xxx,2xxx,3xxx");
		MSG_PARTIDA_MAP.put("52", "4xxx");
		MSG_PARTIDA_MAP.put("53", "8xxx");
		MSG_PARTIDA_MAP.put("54", "9xxx");
		MSG_PARTIDA_MAP.put("56", "6xxx");
		MSG_PARTIDA_MAP.put("57", "5xxx");
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogAccountsMB ");
		setBeanFind(new Cuenta());
		setList(new ArrayList<Cuenta>());
		setListNew(new ArrayList<Cuenta>());
		this.mapAccountsMajor = new HashMap<Integer, Maycta>();
		this.mapNatureExp = new HashMap<Integer, Natgas>();
		this.mapProgram = new HashMap<Integer, Xcatpro>();
		this.mapDependencies = new HashMap<Integer, Catdep>();
		this.originalCta = new ArrayList<String>();
		this.originalScta = new ArrayList<String>();
		this.originalSscta = new ArrayList<String>();
		this.originalSsscta = new ArrayList<String>();
		this.reportNameTextPlain = REPORT_NAME;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.errorMsg = new StringBuilder();
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogAccountsMB  ");
		getBeanFind().setCuenta(null);
		getBeanFind().setScuenta(null);
		getBeanFind().setSscuenta(null);
		getBeanFind().setSsscuenta(null);
		getBeanFind().setSssscuenta(null);
		restartData();
	}

	/**
	 * Append status.
	 *
	 * @param sb the sb
	 * @param field the field
	 * @return the string builder
	 */
	private StringBuilder appendStatus(StringBuilder sb, String field) {
		if (StringUtils.isNotEmpty(field)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		return sb;
	}

	/**
	 * Checks if is valid capture.
	 *
	 * @param account the account
	 * @return true, if is valid capture
	 */
	private boolean isValidCapture(Cuenta account) {
		StringBuilder sb = new StringBuilder();
		sb = this.appendStatus(sb, account.getCuenta());
		sb = this.appendStatus(sb, account.getScuenta());
		sb = this.appendStatus(sb, account.getSscuenta());
		sb = this.appendStatus(sb, account.getSsscuenta());
		sb = this.appendStatus(sb, account.getSssscuenta());

		return sb.toString().matches("1((0000)|(1000)|(1100)|(1110)|(1111))");
	}

	/**
	 * Validate fields.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateFields(Cuenta catalog) {

		Boolean toReturn = catalog == null;
		if (!toReturn) {
			if (catalog.getId().intValue() == ZERO) {
				toReturn = catalog.getCuenta().matches(ACC_INSERT_REGEXP);
				if (!toReturn) {

					if (catalog.getCuenta().matches(ACC_INSERT_2_REGEXP)) {
						errorMsg.append("El origen debe ser la cuenta 8217");
					} else {
						errorMsg.append("El campo Cuenta no cumple con el formato válido.");
					}

				} else {
					toReturn = StringUtils.isNotEmpty(catalog.getNomcta());
					if (toReturn) {
						toReturn = catalog.getNomcta().matches(TITLE_INSERT_REGEXP);
						if (!toReturn) {
							errorMsg.append("El campo Título no cumple con el formato.");
						}
					} else {
						errorMsg.append("El campo Título es requerido.");
					}
				}
			} else {
				return true;
			}
		} else {
			errorMsg.append("El campo Cuenta es requerido");
		}
		return toReturn;
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogAccountsMB  ");
		Cuenta catalog = (Cuenta) event.getObject();
		if (catalog.getCuenta() != null) {
			if (validateFields(catalog)) {
				if (this.isValidCapture(catalog)) {
					catalog.setUserid(this.getUserDetails().getUsername());
					catalog.setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
					catalog.setIdRef(0l);
					catalog.setCapcta(this.getUserDetails().getUsername());
					catalog = (Cuenta) UtilJPACuentas.fillPropertyStringIfNull(catalog);
					
					if (isValidGeneralFeatures(catalog) && isValidFunctionalData(catalog)
							&& accountService.isMayorAccount(catalog) && !this.isBudgetAcc(catalog)) {

						final ValidateAccountDTO validateAccount = new ValidateAccountDTO();
						validateAccount.setAccountValid(Boolean.TRUE);

						if (isIncomeAccount(catalog)) {
							if (this.validateGeneralFlow(catalog, validateAccount)) {
								executeFlowIncomeAccount(catalog);
							}
						} else {
							if (isExpenditureAccount(catalog, validateAccount)) {
								executeFlowExpenditureAccount(catalog);
							} else {
								if (isValidExpenditureBudgetAccount(catalog, validateAccount)) {
									executeFlowExpenditureBudgetAccount(catalog);
								} else {
									if (validateAccount.isAccountValid()
											&& this.validateGeneralFlow(catalog, validateAccount)) {
										executeGeneralFlow(catalog);
									}
								}
							}
						}
						if (validateAccount.isAccountValid()) {
							initializeMapsRowsSelected();
						} else {
							if (errorMsg.length() == ZERO) {
								errorMsg.append(REPORT_MESSAGE_DATA_REQUIRED);
								this.titleActive = Boolean.FALSE;

							}
						}
					} else {

						if (errorMsg.length() == ZERO) {
							errorMsg.append(REPORT_MESSAGE_DATA_REQUIRED);
							this.titleActive = Boolean.FALSE;

						}

					}
				} else {
					errorMsg.append("Favor de capturar en orden de Cuenta, Scuenta, Sscuenta, Ssscuenta, Sssscuenta");

				}
			} else {

				this.titleActive = Boolean.FALSE;
			}
		} else {
			errorMsg.append("Favor de capturar en orden de Cuenta, Scuenta, Sscuenta, Ssscuenta, Sssscuenta");
		}

		if (StringUtils.isEmpty(errorMsg)) {
			this.titleActive = Boolean.FALSE;
		}
	}

	/**
	 * Checks if is budget acc.
	 *
	 * @param cuenta the cuenta
	 * @return true, if is budget acc
	 */
	private boolean isBudgetAcc(Cuenta cuenta) {
		Boolean toReturn = cuenta.getCuenta().matches(ACC_BUDGET_EXC_REGEX);
		if (toReturn) {
			errorMsg.append(MESSAGE_ACC_BUDGET_NOSAVE);
		}
		return toReturn;
	}

	/**
	 * Checks if is valid general features.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid general features
	 */
	private boolean isValidGeneralFeatures(final Cuenta catalog) {
		Boolean result = Boolean.TRUE;
		Pattern pattern = Pattern.compile(REG_EXP_NUMERIC_TYPES);
		Matcher matcher;

		if (StringUtils.isNotEmpty(catalog.getScuenta())) {
			matcher = pattern.matcher(catalog.getScuenta());
			if (BooleanUtils.negate(
					accountService.fillZeros(catalog.getScuenta(), LENGTH_SECOND_LEVEL).matches(SCTA_REGEXP_EXP))) {
				getNumericTypeErrorMessage(FIELD_SCUENTA);
				result = Boolean.FALSE;
			}
		} else if (StringUtils.isNotEmpty(catalog.getSscuenta())) {
			matcher = pattern.matcher(catalog.getSscuenta());
			if (BooleanUtils.negate(matcher.matches())) {
				getNumericTypeErrorMessage(FIELD_SSCUENTA);
				result = Boolean.FALSE;
			}
		} else if (StringUtils.isNotEmpty(catalog.getSsscuenta())) {
			matcher = pattern.matcher(catalog.getSsscuenta());
			if (BooleanUtils.negate(matcher.matches())) {
				getNumericTypeErrorMessage(FIELD_SSSCUENTA);
				result = Boolean.FALSE;
			}
		} else if (StringUtils.isNotEmpty(catalog.getSssscuenta())) {
			matcher = pattern.matcher(catalog.getSssscuenta());
			if (BooleanUtils.negate(matcher.matches())) {
				getNumericTypeErrorMessage(FIELD_SSSSCUENTA);
				result = Boolean.FALSE;
			}
		}
		// if (catalog.getSalini() == null) {
		// errorMsg.append(FIELD_SALDO_INI_REQUIRED);
		// result = Boolean.FALSE;
		// }
		return result;
	}

	/**
	 * Gets the numeric type error message.
	 *
	 * @param field the field
	 * @return the numeric type error message
	 */
	private void getNumericTypeErrorMessage(final String field) {
		errorMsg.append(ERROR_MESSAGE_NUMERIC_TYPE);
		errorMsg.append(field);
		errorMsg.append(ERROR_MESSAGE_NUMERIC_TYPE_COMPLEMENT);
	}

	/**
	 * Execute flow income account.
	 *
	 * @param original the original
	 */
	private void executeFlowIncomeAccount(final Cuenta original) {
		validateAndAddFillZeros(original);
		LOGGER.info(":: Probando la construcción de isIncomeAccount 1  " + original.toString());

		LOGGER.info(":: Probando la construcción de isIncomeAccount 2  " + original.toString());

		isUpdateFrontOperationSave = original.getId().intValue() == ZERO ? Boolean.TRUE : Boolean.FALSE;
		if (BooleanUtils.negate(isUpdateFrontOperationSave)) {
			List<Cuenta> cuentass = accountService.getListAutogeneratedAccountBudgetByIncome(original);
			for (Cuenta cuenta : cuentass) {
				Cuenta finded = this.cuentaRepository
						.findOne((CuentaPredicates.findByAccounCompositeInlcudeEmpty(cuenta)));
				if (null != finded) {
					finded.setNomcta(cuenta.getNomcta());
					this.cuentaRepository.save(finded);
				}
			}
			executeOperationSaveOrUpdate(original, cuentaRepository);
			restartData();
		} else {
			final List<Cuenta> listSave = accountService.getListAutogeneratedAccountBudgetByIncome(original);
			// for (final Cuenta tempAccount : listSave) {
			// catalogToBudgetAccount(original, tempAccount);
			// }
			saveGeneratedRows(listSave, original);
		}
	}

	/**
	 * Execute flow expenditure account.
	 *
	 * @param original the original
	 */
	private void executeFlowExpenditureAccount(final Cuenta original) {
		isUpdateFrontOperationSave = original.getId().intValue() == ZERO ? Boolean.TRUE : Boolean.FALSE;
		if (BooleanUtils.negate(isUpdateFrontOperationSave)) {
			List<Cuenta> cuentass = accountService.getAutogeneratedBudgetAccount(original);
			for (Cuenta cuenta : cuentass) {
				Cuenta finded = this.cuentaRepository
						.findOne((CuentaPredicates.findByAccounCompositeInlcudeEmpty(cuenta)));
				if (null != finded) {
					finded.setNomcta(original.getNomcta());
					this.cuentaRepository.save(finded);
				}
			}
			executeOperationSaveOrUpdate(original, cuentaRepository);
		} else {
			final List<Cuenta> listSave = this.accountService.getAutogeneratedBudgetAccount(original);
			saveGeneratedRows(listSave, original);
		}
	}

	/**
	 * Execute flow expenditure budget account.
	 *
	 * @param original the original
	 */
	private void executeFlowExpenditureBudgetAccount(final Cuenta original) {
		isUpdateFrontOperationSave = original.getId().intValue() == ZERO ? Boolean.TRUE : Boolean.FALSE;
		if (BooleanUtils.negate(isUpdateFrontOperationSave)) {
			List<Cuenta> cuentass = accountService.getListAutogeneratedAccountBudgetByExpenditure(original);
			for (Cuenta cuenta : cuentass) {
				Cuenta finded = this.cuentaRepository
						.findOne((CuentaPredicates.findByAccounCompositeInlcudeEmpty(cuenta)));
				if (null != finded) {
					finded.setNomcta(original.getNomcta());
					this.cuentaRepository.save(finded);
				}
			}
			executeOperationSaveOrUpdate(original, cuentaRepository);
		} else {
			final List<Cuenta> listSave = accountService.getListAutogeneratedAccountBudgetByExpenditure(original);
			saveGeneratedRows(listSave, original);
		}
	}

	/**
	 * Update view rows auto generated.
	 */
	public void updateViewRowsAutoGenerated() {
		LOGGER.info(":: Actualiza los registros autogenerados " + isUpdateFrontOperationSave);
		if (errorMsg.length() > ZERO) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_INFO, errorMsg.toString());
			errorMsg.delete(ZERO, errorMsg.length());
			activateRowEdit();
		} else {
			this.insertActive = Boolean.TRUE;
			this.titleActive = Boolean.FALSE;
		}
		if (BooleanUtils.negate(isUpdateFrontOperationSave)) {
			return;
		}
		updateRowsMaintenanceAccount();
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
		isUpdateFrontOperationSave = Boolean.FALSE;
	}

	/**
	 * Save generated rows.
	 *
	 * @param listSave the list save
	 * @param original the original
	 */
	private void saveGeneratedRows(final List<Cuenta> listSave, final Cuenta original) {
		listSave.add(ZERO, accountService.populateAcc(original, original));

		for (Cuenta cuenta : listSave) {
			if (!this.cuentaRepository.exists(CuentaPredicates.findByAccounComposite(cuenta))) {
				this.cuentaRepository.save(cuenta);
			}
		}
		// cuentaRepository.save(listSave);
		getListNew().addAll(ZERO, listSave);
		restartData();

	}

	/**
	 * Execute general flow.
	 *
	 * @param catalog the catalog
	 */
	private void executeGeneralFlow(final Cuenta catalog) {
		if (!catalog.getCuenta().matches(ACCOUNT_TYPE_5X_REGEXP)
				&& !catalog.getCuenta().matches(ACCOUNT_TYPE_8217_REGEXP)) {
			validateAndAddFillZeros(catalog);
		}
		executeOperationSaveOrUpdate(accountService.populateAcc(catalog, catalog), cuentaRepository);
		isUpdateFrontOperationSave = Boolean.FALSE;
		restartData();
	}

	/**
	 * Validate general flow.
	 *
	 * @param account the account
	 * @param validateAccountDTO the validate account DTO
	 * @return true, if successful
	 */
	private boolean validateGeneralFlow(final Cuenta account, ValidateAccountDTO validateAccountDTO) {
		validateAndAddFillZeros(account);
		Boolean toReturn = Boolean.TRUE;
		if (StringUtils.isNotEmpty(account.getScuenta())) {
			toReturn = account.getScuenta().matches("([0-9]{10})");
			if (!toReturn) {
				this.errorMsg.append("La Scuenta debe ser numérica");
			}
		}
		if (StringUtils.isNotEmpty(account.getSscuenta())) {
			toReturn = account.getSscuenta().matches("([0-9]{15})");
			if (!toReturn) {
				this.errorMsg.append("La Sscuenta debe ser numérica");
			}
		}
		if (StringUtils.isNotEmpty(account.getSsscuenta())) {
			toReturn = account.getSsscuenta().matches("([0-9]{4})");
			if (!toReturn) {
				this.errorMsg.append("La Ssscuenta debe ser numérica");
			}
		}

		if (StringUtils.isNotEmpty(account.getSssscuenta())) {
			toReturn = account.getSssscuenta().matches("([0-9]{3})");
			if (!toReturn) {
				this.errorMsg.append("La Sssscuenta debe ser numérica");
			}
		}
		validateAccountDTO.setAccountValid(toReturn);
		return toReturn;
	}

	/**
	 * Checks if is valid functional data.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid functional data
	 */
	private boolean isValidFunctionalData(final Cuenta catalog) {
		final Boolean isSave = catalog.getId().intValue() == ZERO ? Boolean.TRUE : Boolean.FALSE;
		return (BooleanUtils.negate(isShowHelpWindow) || (isShowHelpWindow && isValidCapturedData(catalog)))
				&& (BooleanUtils.negate(isSave) || (isSave
						&& accountService.isValidPreviousLevelAccount(catalog, errorMsg,
								Long.valueOf(this.getUserDetails().getIdSector()))
						&& accountService.existAccount(catalog, errorMsg,
								Long.valueOf(this.getUserDetails().getIdSector()))));
	}

	/**
	 * Validate and add fill zeros.
	 *
	 * @param account the account
	 */
	private void validateAndAddFillZeros(final Cuenta account) {
		if (StringUtils.isNotEmpty(account.getScuenta())) {
			account.setScuenta(accountService.fillZeros(account.getScuenta(), LENGTH_SECOND_LEVEL));
		} else {
			account.setScuenta(StringUtils.EMPTY);
		}
		if (StringUtils.isNotEmpty(account.getSscuenta())) {
			account.setSscuenta(accountService.fillZeros(account.getSscuenta(), LENGTH_THIRD_LEVEL));
		} else {
			account.setSscuenta(StringUtils.EMPTY);
		}
		if (StringUtils.isNotEmpty(account.getSsscuenta())) {
			account.setSsscuenta(accountService.fillZeros(account.getSsscuenta(), LENGTH_FOUR_LEVEL));
		} else {
			account.setSsscuenta(StringUtils.EMPTY);
		}

		if (StringUtils.isNotEmpty(account.getSssscuenta())) {
			account.setSssscuenta(accountService.fillZeros(account.getSssscuenta(), LENGTH_FIVE_LEVEL));
		} else {
			account.setSssscuenta(StringUtils.EMPTY);
		}

	}

	/**
	 * Checks if is valid captured data.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final Cuenta catalog) {
		return (BooleanUtils.negate(catalog.getId() == null)
		// && isDataSelectedValid(catalog)
		) || (catalog.getId() == null && isDataSelectedValidNewRegister(catalog));
	}

	/**
	 * Checks if is expenditure account.
	 *
	 * @param catalog the catalog
	 * @param validateAccount the validate account
	 * @return true, if is expenditure account
	 */
	private boolean isExpenditureAccount(final Cuenta catalog, final ValidateAccountDTO validateAccount) {
		Boolean toReturn = catalog.getCuenta().matches(ACCOUNT_TYPE_5X_REGEXP);

		if (toReturn) {
			if (Constants.FOUR == accountService.getNivCta(catalog)) {
				toReturn = (catalog.getCuenta().matches(ACCOUNT_TYPE_51_REGEXP)
						&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_51_PRESUP_REGEXP))
						|| (catalog.getCuenta().matches(ACCOUNT_TYPE_52_REGEXP)
								&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_52_PRESUP_REGEXP))
						|| (catalog.getCuenta().matches(ACCOUNT_TYPE_53_REGEXP)
								&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_53_PRESUP_REGEXP))
						|| (catalog.getCuenta().matches(ACCOUNT_TYPE_54_REGEXP)
								&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_54_PRESUP_REGEXP))
						|| (catalog.getCuenta().matches(ACCOUNT_TYPE_55_REGEXP)
								&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_55_PRESUP_REGEXP))
						|| (catalog.getCuenta().matches(ACCOUNT_TYPE_56_REGEXP)
								&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_56_PRESUP_REGEXP))
						|| (catalog.getCuenta().matches(ACCOUNT_TYPE_57_REGEXP)
								&& catalog.getSsscuenta().matches(ACCOUNT_TYPE_57_PRESUP_REGEXP));
				if (!toReturn) {
					errorMsg.append(
							String.format(ACCOUNT_EXPENDITURE_INVALID,
									MapUtils.getString(MSG_PARTIDA_MAP,
											StringUtils.substring(catalog.getCuenta(), 0, 2)) == null
													? StringUtils.EMPTY
													: MapUtils.getString(MSG_PARTIDA_MAP,
															StringUtils.substring(catalog.getCuenta(), 0, 2))));
					validateAccount.setAccountValid(Boolean.FALSE);

				}
			}
			if (StringUtils.isNotEmpty(catalog.getScuenta())) {
				catalog.setScuenta(accountService.fillRightZeros(catalog.getScuenta(), LENGTH_SECOND_LEVEL));

				if (!this.catdepRepository.exists(DependencePredicates.getPredicatetNivCta(catalog.getScuenta(),
						this.getUserDetails().getIdSector()))) {
					errorMsg.append(
							String.format("La clave de dependencia [%1$s] no es válida\n\r", catalog.getScuenta()));
					toReturn = Boolean.FALSE;
					validateAccount.setAccountValid(toReturn);
				}
			}
			if (StringUtils.isNotEmpty(catalog.getSscuenta())) {
				if (!this.muniNepRepository.exists(MuniNepPredicates.getPredicateToPrograms(
						StringUtils.substring(catalog.getSscuenta(), ZERO, 12), catalog.getIdsector().intValue()))) {
					errorMsg.append(String.format("La clave de programa [%1$s] no está registrada en los catálogos\n\r",
							catalog.getSscuenta()));
					toReturn = Boolean.FALSE;
					validateAccount.setAccountValid(toReturn);
				}
			}
			if (StringUtils.isNotEmpty(catalog.getSsscuenta())) {
				if (catalog.getSsscuenta().matches("([0-9]{3})0")) {
					errorMsg.append(String.format(
							"La clave de naturaleza [%1$s] no cumple con el formato, o no es de último nivel\n\r",
							catalog.getSsscuenta()));
					toReturn = Boolean.FALSE;
					validateAccount.setAccountValid(toReturn);
				} else {
					if (!natgasRepository.exists(NatgasPredicates.existNatgas(catalog.getSsscuenta(),
							this.getUserDetails().getIdSector()))) {
						errorMsg.append(String.format("La clave de naturaleza [%1$s] no existe en catálogos\n\r",
								catalog.getSsscuenta()));
						toReturn = Boolean.FALSE;
						validateAccount.setAccountValid(toReturn);
					}
				}
			}
		}
		return toReturn;
	}

	/**
	 * Checks if is valid expenditure budget account.
	 *
	 * @param catalog the catalog
	 * @param validateAccount the validate account
	 * @return true, if is valid expenditure budget account
	 */
	private boolean isValidExpenditureBudgetAccount(final Cuenta catalog, final ValidateAccountDTO validateAccount) {
		// if (StringUtils.isEmpty(catalog.getSsscuenta()) ||
		// catalog.getCuenta().length() < TWO) {
		// return Boolean.FALSE;
		// }
		boolean isValidExpenditureBudgetAccount;

		if (catalog.getCuenta().matches(ACCOUNT_TYPE_8217_REGEXP)) {
			isValidExpenditureBudgetAccount = Boolean.TRUE;
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug("catalog.getSsscuenta() ====" + catalog.getSsscuenta());
			LOGGER.debug("catalog.getSsscuenta().matches(ACCOUNT_PRESUP_8217) = "
					+ catalog.getSsscuenta().matches(ACCOUNT_PRESUP_8217));
			LOGGER.debug("ACCOUNT_PRESUP_8217 ==== " + ACCOUNT_PRESUP_8217);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);

			if (StringUtils.isNotEmpty(catalog.getScuenta())) {
				catalog.setScuenta(accountService.fillRightZeros(catalog.getScuenta(), LENGTH_SECOND_LEVEL));

				if (!this.catdepRepository.exists(DependencePredicates.getPredicatetNivCta(catalog.getScuenta(),
						this.getUserDetails().getIdSector()))) {
					errorMsg.append(
							String.format("La clave de dependencia [%1$s] no es válida\n\r", catalog.getScuenta()));
					isValidExpenditureBudgetAccount = Boolean.FALSE;
					validateAccount.setAccountValid(isValidExpenditureBudgetAccount);
				}
			}
			if (StringUtils.isNotEmpty(catalog.getSscuenta())) {
				if (!this.muniNepRepository.exists(MuniNepPredicates.getPredicateToPrograms(
						StringUtils.substring(catalog.getSscuenta(), ZERO, 12), catalog.getIdsector().intValue()))) {
					errorMsg.append(String.format("La clave de programa [%1$s] no está registrada en los catálogos\n\r",
							catalog.getSscuenta()));
					isValidExpenditureBudgetAccount = Boolean.FALSE;
					validateAccount.setAccountValid(isValidExpenditureBudgetAccount);
				}
			}
			if (StringUtils.isNotEmpty(catalog.getSsscuenta())) {
				if (catalog.getSsscuenta().matches("([0-9]{3})0")) {
					errorMsg.append(String.format(
							"La clave de naturaleza [%1$s] no cumple con el formato, o no es de último nivel\n\r",
							catalog.getSsscuenta()));
					isValidExpenditureBudgetAccount = Boolean.FALSE;
					validateAccount.setAccountValid(isValidExpenditureBudgetAccount);
				} else {
					if (!natgasRepository.exists(NatgasPredicates.existNatgas(catalog.getSsscuenta(),
							this.getUserDetails().getIdSector()))) {
						errorMsg.append(String.format("La clave de naturaleza [%1$s] no existe en catálogos\n\r",
								catalog.getSsscuenta()));
						isValidExpenditureBudgetAccount = Boolean.FALSE;
						validateAccount.setAccountValid(isValidExpenditureBudgetAccount);
					}
				}
			}

			if (!StringUtils.isEmpty(catalog.getSsscuenta()) && !catalog.getSsscuenta().matches(ACCOUNT_PRESUP_8217)) {
				errorMsg.append(ACCOUNT_EXPENDITURE_BUDGET_INVALID);
				isValidExpenditureBudgetAccount = Boolean.FALSE;
				validateAccount.setAccountValid(Boolean.FALSE);
			}
		} else {
			isValidExpenditureBudgetAccount = Boolean.FALSE;
		}
		return isValidExpenditureBudgetAccount;
	}

	/**
	 * Checks if is income account.
	 *
	 * @param catalog the catalog
	 * @return true, if is income account
	 */
	private boolean isIncomeAccount(final Cuenta catalog) {
		return catalog.getCuenta().substring(ZERO, ONE).equalsIgnoreCase(PREFIX_ACCOUNT_MAJOR);
	}

	/**
	 * Checks if is data selected valid new register.
	 *
	 * @param catalog the catalog
	 * @return true, if is data selected valid new register
	 */
	private boolean isDataSelectedValidNewRegister(final Cuenta catalog) {
		return (BooleanUtils.negate(mapAccountsMajor.get(catalog.getIndex()) == null)
				&& catalog.getCuenta().equals(mapAccountsMajor.get(catalog.getIndex()).getCuenta()))
				&& (BooleanUtils.negate(mapDependencies.get(catalog.getIndex()) == null)
						&& catalog.getScuenta().equals(mapDependencies.get(catalog.getIndex()).getClvdep()))
				&& (BooleanUtils.negate(mapProgram.get(catalog.getIndex()) == null) && catalog.getSscuenta()
						.equals(mapProgram.get(catalog.getIndex()).getClvfun()
								.concat(mapProgram.get(catalog.getIndex()).getClvfin())))
				&& (BooleanUtils.negate(mapNatureExp.get(catalog.getIndex()) == null)
						&& catalog.getSsscuenta().equals(mapNatureExp.get(catalog.getIndex()).getClvgas()));
	}

	/**
	 * Initialize maps rows selected.
	 */
	// private boolean isDataSelectedValid(final Cuenta catalog) {
	// Boolean toRetunr = accountService.isMayorAccount(catalog);
	// if (toRetunr &&
	// BooleanUtils.negate(StringUtils.isEmpty(catalog.getScuenta()))
	// && accountService.isContableAccount(catalog.getCuenta())) {
	// toRetunr = accountService.existDependency(catalog.getScuenta());
	// if (toRetunr &&
	// BooleanUtils.negate(StringUtils.isEmpty(catalog.getSscuenta()))) {
	// toRetunr = accountService.existXcatpro(catalog.getSscuenta(),
	// catalog.getIdsector().intValue());
	// if (toRetunr &&
	// BooleanUtils.negate(StringUtils.isEmpty(catalog.getSsscuenta()))) {
	// toRetunr = accountService.existNatgas(catalog.getSsscuenta(),
	// catalog.getIdsector().intValue());
	// }
	// }
	// }
	// return toRetunr;
	// }

	/**
	 * 
	 */
	private void initializeMapsRowsSelected() {
		mapAccountsMajor.remove(rowSelected.getIndex());
		mapDependencies.remove(rowSelected.getIndex());
		mapProgram.remove(rowSelected.getIndex());
		mapNatureExp.remove(rowSelected.getIndex());
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogAccountsMB " + getBeanSelected());
		String errorMsg = this.accountService.delete(getBeanSelected());
		if (StringUtils.isEmpty(errorMsg)) {
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
			restartDataOperationDelete();
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogAccountsMB {}", getBeanFind());
		getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		setList(this.setEditable(IteratorUtils.toList(this.cuentaRepository
				.findAll(CuentaPredicates.findByAccountLikeComposite(getBeanFind()), CuentaSort.sortByAll())
				.iterator())));
		// this.cleanFilters(getBeanFind());

		UtilJPACuentas.clearQualifiersLikeFieldsString(getBeanFind());
		// addOriginalValues();

		LOGGER.info(":: Resultado de busqueda catalogAccountsMB {}", getList().size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(this.setEditable(cuentaRepository.findAllByIdsector(Long.valueOf(this.getUserDetails().getIdSector()),
				CuentaSort.sortByAll())));
		addOriginalValues();
		// LOGGER.info(":: Resultado de busqueda catalogAccountsMB-All " +
		// getList());
		LOGGER.info(":: Resultado de busqueda catalogAccountsMB-All {}", getList().size());
	}

	/**
	 * Sets the editable.
	 *
	 * @param cuentas the cuentas
	 * @return the list
	 */
	private List<Cuenta> setEditable(List<Cuenta> cuentas) {
		// for (Cuenta cuenta : cuentas) {
		// if (!accountService.isLastLevelAcc(cuenta)) {
		// cuenta.setEditable(!this.accountService.hasChilds(cuenta));
		// } else {
		// cuenta.setEditable(Boolean.FALSE);
		// }
		// }
		return cuentas;
	}

	/**
	 * Adds the original values.
	 */
	private void addOriginalValues() {
		if (CollectionUtils.isEmpty(getList())) {
			return;
		}
		originalCta.clear();
		originalScta.clear();
		originalSscta.clear();
		originalSsscta.clear();
		for (final Cuenta account : getList()) {
			originalCta.add(account.getCuenta());
			originalScta.add(account.getScuenta());
			originalSscta.add(account.getSscuenta());
			originalSsscta.add(account.getSsscuenta());
		}
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
	 * Consult accounts major.
	 */
	public void consultAccountsMajor() {
		LOGGER.info(":: Consultar cuentas de mayor ");
		accountsMajor = mayctaRepository.findAll();
		restartFilteredAccountMajor();
	}

	/**
	 * Restart filtered account major.
	 */
	private void restartFilteredAccountMajor() {
		filteredAccountsMajor.clear();
		filteredAccountsMajor.addAll(accountsMajor);
	}

	/**
	 * Change account major.
	 */
	public void changeAccountMajor() {
		if (null != mapAccountsMajor.get(rowSelected.getIndex())) {
			LOGGER.info(":: Cambiar cuenta de mayor ");
			rowSelected.setCuenta(mapAccountsMajor.get(rowSelected.getIndex()).getCuenta());
			rowSelected.setNomcta(mapAccountsMajor.get(rowSelected.getIndex()).getNommay());
			rowSelected.setStacta(mapAccountsMajor.get(rowSelected.getIndex()).getNatcta());
			rowSelected.setNotcta(mapAccountsMajor.get(rowSelected.getIndex()).getNotcta());
			// rowSelected.setNivcta(mapAccountsMajor.get(rowSelected.getIndex()).getNivcta());
			this.titleActive = (accountService.is5xAccount(rowSelected.getCuenta().toString())
					|| rowSelected.getCuenta().toString().matches(ACCOUNT_TYPE_8217_REGEXP));
			this.helpActive = !titleActive;
		}
		activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Consult dependencies.
	 */
	public void consultDependencies() {
		validateAccountFirstLevel();
		LOGGER.info(":: Consultar dependencias " + isShowHelpWindow + "::" + VIEW_DEPENDENCIES_DLG);
		if (isShowHelpWindow) {
			final Predicate predicate = DependencePredicates.getPredicatetNivCta(this.getUserDetails().getIdSector());
			dependencies = catdepRepository.findAll(predicate);
			restartFilteredDependencies();
			RequestContext.getCurrentInstance().execute(VIEW_DEPENDENCIES_DLG);
		}
	}

	/**
	 * Restart filtered dependencies.
	 */
	private void restartFilteredDependencies() {
		if (filteredDependencies == null) {
			return;
		}
		filteredDependencies.clear();
		filteredDependencies.addAll(dependencies);
	}

	/**
	 * Change dependence.
	 */
	public void changeDependence() {
		if (null != mapDependencies.get(rowSelected.getIndex())) {
			LOGGER.info(":: Cambiar Dependencia " + rowSelected);
			rowSelected.setScuenta(mapDependencies.get(rowSelected.getIndex()).getClvdep());
		}
		activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Consult programs.
	 */
	public void consultPrograms() {
		validateAccountFirstLevel();
		LOGGER.info(":: Consultar Programas " + isShowHelpWindow);
		if (isShowHelpWindow) {
			if (StringUtils.isNotEmpty(rowSelected.getScuenta())) {
				programs = IteratorUtils.toList(xcatproRepository
						.findCustomDistinct(this.getUserDetails().getIdSector(), rowSelected.getScuenta()).iterator());
			} else {
				programs = (List<Xcatpro>) CollectionUtils.EMPTY_COLLECTION;
			}
			// addConcatClvfunAndClvfin(programs);
			restartFilteredPrograms();
			RequestContext.getCurrentInstance().execute(VIEW_PROGRAMS_DLG);
		}
	}

	/**
	 * Restart filtered programs.
	 */
	private void restartFilteredPrograms() {
		if (filteredPrograms == null) {
			return;
		}
		filteredPrograms.clear();
		filteredPrograms.addAll(programs);
	}

	/**
	 * Change program.
	 */
	public void changeProgram() {
		if (null != mapProgram.get(rowSelected.getIndex())) {
			LOGGER.info(":: Cambiar Programa ");
			rowSelected.setSscuenta(mapProgram.get(rowSelected.getIndex()).getClvpro());
		}
		activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Consult nature exp.
	 */
	public void consultNatureExp() {
		LOGGER.info(":: Consultar Naturaleza de Gasto ");
		validateAccountFirstLevel();
		if (isShowHelpWindow) {
			listNatureExp = natgasRepository.findByClvgasNotLikeAndIdsector("%0", this.getUserDetails().getIdSector());
			restartFilteredNatureExp();
			RequestContext.getCurrentInstance().execute(VIEW_NATUREEXP_DLG);
		}
	}

	/**
	 * Restart filtered nature exp.
	 */
	private void restartFilteredNatureExp() {
		if (filteredNatureExp == null) {
			return;
		}
		filteredNatureExp.clear();
		filteredNatureExp.addAll(listNatureExp);
	}

	/**
	 * Change nature exp.
	 */
	public void changeNatureExp() {
		if (null != mapNatureExp.get(rowSelected.getIndex())) {
			LOGGER.info(":: Cambiar Naturaleza de Gasto ");
			rowSelected.setSsscuenta(mapNatureExp.get(rowSelected.getIndex()).getClvgas());
		}
		activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Validate account first level.
	 */
	private void validateAccountFirstLevel() {
		isShowHelpWindow = rowSelected.getCuenta().matches(DISPLAY_HELP_EXP);
		LOGGER.info(":: Validar Tipo de Cuenta de Primer Nivel:" + rowSelected.getCuenta() + "::" + isShowHelpWindow);
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		this.validateAndAddFillZeros(this.getBeanFind());
		if (StringUtils.isNotEmpty(this.getBeanFind().getCuenta())) {
			this.getBeanFind().setCuenta(this.getBeanFind().getCuenta() + Constants.QUALIFIER_LIKE);
		}
		if (StringUtils.isNotEmpty(this.getBeanFind().getNomcta())) {
			this.getBeanFind()
					.setNomcta(Constants.QUALIFIER_LIKE + this.getBeanFind().getNomcta() + Constants.QUALIFIER_LIKE);
		}
		this.getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		StreamedContent toReturn = super.getFilePdf(REPORT_PATH_JASPER_ACCOUNTS, REPORT_NAME);
		UtilJPACuentas.clearQualifiersLikeFieldsString(this.getBeanFind());
		return toReturn;
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		this.validateAndAddFillZeros(this.getBeanFind());
		if (StringUtils.isNotEmpty(this.getBeanFind().getCuenta())) {
			this.getBeanFind().setCuenta(this.getBeanFind().getCuenta() + Constants.QUALIFIER_LIKE);
		}
		if (StringUtils.isNotEmpty(this.getBeanFind().getNomcta())) {
			this.getBeanFind()
					.setNomcta(Constants.QUALIFIER_LIKE + this.getBeanFind().getNomcta() + Constants.QUALIFIER_LIKE);
		}
		this.getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		StreamedContent toReturn = super.getFileXls(REPORT_PATH_JASPER_ACCOUNTS, REPORT_NAME);
		UtilJPACuentas.clearQualifiersLikeFieldsString(this.getBeanFind());
		return toReturn;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Cuenta getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(final Cuenta rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the accounts major.
	 *
	 * @return the accountsMajor
	 */
	public List<Maycta> getAccountsMajor() {
		return accountsMajor;
	}

	/**
	 * Sets the accounts major.
	 *
	 * @param accountsMajor            the accountsMajor to set
	 */
	public void setAccountsMajor(List<Maycta> accountsMajor) {
		this.accountsMajor = accountsMajor;
	}

	/**
	 * Gets the account major selected.
	 *
	 * @return the accountMajorSelected
	 */
	public Maycta getAccountMajorSelected() {
		return accountMajorSelected;
	}

	/**
	 * Sets the account major selected.
	 *
	 * @param accountMajorSelected            the accountMajorSelected to set
	 */
	public void setAccountMajorSelected(final Maycta accountMajorSelected) {
		if (BooleanUtils.negate(mapAccountsMajor == null) && BooleanUtils.negate(rowSelected == null)) {
			mapAccountsMajor.put(rowSelected.getIndex(), accountMajorSelected);
		}
	}

	/**
	 * Gets the dependencies.
	 *
	 * @return the dependencies
	 */
	public List<Catdep> getDependencies() {
		return dependencies;
	}

	/**
	 * Sets the dependencies.
	 *
	 * @param dependencies            the dependencies to set
	 */
	public void setDependencies(List<Catdep> dependencies) {
		this.dependencies = dependencies;
	}

	/**
	 * Gets the dependence selected.
	 *
	 * @return the dependenceSelected
	 */
	public Catdep getDependenceSelected() {
		return dependenceSelected;
	}

	/**
	 * Sets the dependence selected.
	 *
	 * @param dependenceSelected            the dependenceSelected to set
	 */
	public void setDependenceSelected(final Catdep dependenceSelected) {
		if (BooleanUtils.negate(mapDependencies == null) && BooleanUtils.negate(rowSelected == null)) {
			mapDependencies.put(rowSelected.getIndex(), dependenceSelected);
		}
	}

	/**
	 * Gets the programs.
	 *
	 * @return the programs
	 */
	public List<Xcatpro> getPrograms() {
		return programs;
	}

	/**
	 * Sets the programs.
	 *
	 * @param programs            the programs to set
	 */
	public void setPrograms(List<Xcatpro> programs) {
		this.programs = programs;
	}

	/**
	 * Gets the program selected.
	 *
	 * @return the programSelected
	 */
	public Xcatpro getProgramSelected() {
		return programSelected;
	}

	/**
	 * Sets the program selected.
	 *
	 * @param programSelected            the programSelected to set
	 */
	public void setProgramSelected(final Xcatpro programSelected) {
		if (BooleanUtils.negate(mapProgram == null) && BooleanUtils.negate(rowSelected == null)) {
			mapProgram.put(rowSelected.getIndex(), programSelected);
		}
	}

	/**
	 * Gets the list nature exp.
	 *
	 * @return the listNatureExp
	 */
	public List<Natgas> getListNatureExp() {
		return listNatureExp;
	}

	/**
	 * Sets the list nature exp.
	 *
	 * @param listNatureExp            the listNatureExp to set
	 */
	public void setListNatureExp(List<Natgas> listNatureExp) {
		this.listNatureExp = listNatureExp;
	}

	/**
	 * Gets the nature exp selected.
	 *
	 * @return the natureExpSelected
	 */
	public Natgas getNatureExpSelected() {
		return natureExpSelected;
	}

	/**
	 * Sets the nature exp selected.
	 *
	 * @param natureExpSelected            the natureExpSelected to set
	 */
	public void setNatureExpSelected(final Natgas natureExpSelected) {
		if (BooleanUtils.negate(mapNatureExp == null) && BooleanUtils.negate(rowSelected == null)) {
			mapNatureExp.put(rowSelected.getIndex(), natureExpSelected);
		}
	}

	/**
	 * Gets the filtered accounts major.
	 *
	 * @return the filteredAccountsMajor
	 */
	public List<Maycta> getFilteredAccountsMajor() {
		return filteredAccountsMajor;
	}

	/**
	 * Sets the filtered accounts major.
	 *
	 * @param filteredAccountsMajor            the filteredAccountsMajor to set
	 */
	public void setFilteredAccountsMajor(List<Maycta> filteredAccountsMajor) {
		this.filteredAccountsMajor = filteredAccountsMajor;
	}

	/**
	 * Gets the filtered dependencies.
	 *
	 * @return the filteredDependencies
	 */
	public List<Catdep> getFilteredDependencies() {
		return filteredDependencies;
	}

	/**
	 * Sets the filtered dependencies.
	 *
	 * @param filteredDependencies            the filteredDependencies to set
	 */
	public void setFilteredDependencies(List<Catdep> filteredDependencies) {
		this.filteredDependencies = filteredDependencies;
	}

	/**
	 * Gets the filtered programs.
	 *
	 * @return the filteredPrograms
	 */
	public List<Xcatpro> getFilteredPrograms() {
		return filteredPrograms;
	}

	/**
	 * Sets the filtered programs.
	 *
	 * @param filteredPrograms            the filteredPrograms to set
	 */
	public void setFilteredPrograms(List<Xcatpro> filteredPrograms) {
		this.filteredPrograms = filteredPrograms;
	}

	/**
	 * Gets the filtered nature exp.
	 *
	 * @return the filteredNatureExp
	 */
	public List<Natgas> getFilteredNatureExp() {
		return filteredNatureExp;
	}

	/**
	 * Sets the filtered nature exp.
	 *
	 * @param filteredNatureExp            the filteredNatureExp to set
	 */
	public void setFilteredNatureExp(List<Natgas> filteredNatureExp) {
		this.filteredNatureExp = filteredNatureExp;
	}

	/**
	 * Validate name.
	 *
	 * @param event the event
	 */
	public void validateName(AjaxBehaviorEvent event) {

		System.err.println("OnBlur Action" + event.getSource().toString());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogCuentasMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {
		// TODO Auto-generated method stub

	}

	/**
	 * Checks if is title active.
	 *
	 * @return true, if is title active
	 */
	public boolean isTitleActive() {
		return titleActive;
	}

	/**
	 * Sets the title active.
	 *
	 * @param titleActive the new title active
	 */
	public void setTitleActive(boolean titleActive) {
		this.titleActive = titleActive;
	}

	/**
	 * Cuentahanged.
	 *
	 * @param e the e
	 */
	public void cuentahanged(ValueChangeEvent e) {
		// assign new value to localeCode
		if (null != e.getNewValue()) {
			this.titleActive = (accountService.is5xAccount(e.getNewValue().toString())
					|| e.getNewValue().toString().matches(ACCOUNT_TYPE_8217_REGEXP));
			this.helpActive = !titleActive;
			Maycta maycta = this.mayctaRepository.findOne(MayctaPredicates.existAccount(e.getNewValue().toString()));
			if (maycta != null) {
				// rowSelected.setCuenta(maycta.getCuenta());
				rowSelected.setCuenta("AAAA");
				rowSelected.setNomcta(maycta.getNommay());
				rowSelected.setStacta(maycta.getNatcta());
				rowSelected.setNotcta(maycta.getNotcta());
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ACC_MAY_NOTFOUD);
			}
			activateRowEdit(0);
		}
	}

	/**
	 * Checks if is help active.
	 *
	 * @return true, if is help active
	 */
	public boolean isHelpActive() {
		return helpActive;
	}

	/**
	 * Sets the help active.
	 *
	 * @param helpActive the new help active
	 */
	public void setHelpActive(boolean helpActive) {
		this.helpActive = helpActive;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogCuentasMB#insertRow()
	 */
	@Override
	public void insertRow() throws InstantiationException, IllegalAccessException {
		this.titleActive = Boolean.TRUE;
		this.insertActive = Boolean.FALSE;
		super.insertRow();
		this.getList().get(ZERO).setEditable(Boolean.TRUE);
		this.rowSelected = this.getList().get(ZERO);
	}

	/**
	 * On accounts major row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onAccountsMajorRowDblClckSelect(final SelectEvent event) {
		this.changeAccountMajor();
		// rest of your logic
	}

	/**
	 * On dependencies row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onDependenciesRowDblClckSelect(final SelectEvent event) {
		this.changeDependence();
		// rest of your logic
	}

	/**
	 * On programs row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onProgramsRowDblClckSelect(final SelectEvent event) {
		this.changeProgram();
		// rest of your logic
	}

	/**
	 * On nature ex row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onNatureExRowDblClckSelect(final SelectEvent event) {
		this.changeNatureExp();
		// rest of your logic
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogCuentasMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		this.insertActive = Boolean.TRUE;
		this.titleActive = Boolean.FALSE;
		restartData();
		super.onRowCancel(event);
	}

	/**
	 * On row cancel.
	 */
	public void onRowCancel() {
		this.insertActive = Boolean.TRUE;
		this.titleActive = Boolean.FALSE;
		restartData();
		LOGGER.info(":: Cancelar edicion ");
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

	/**
	 * Checks if is insert active.
	 *
	 * @return true, if is insert active
	 */
	public boolean isInsertActive() {
		return insertActive;
	}

	/**
	 * Sets the insert active.
	 *
	 * @param insertActive the new insert active
	 */
	public void setInsertActive(boolean insertActive) {
		this.insertActive = insertActive;
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
	 * Gets the cuenta repository.
	 *
	 * @return the cuentaRepository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdepRepository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatproRepository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Gets the maycta repository.
	 *
	 * @return the mayctaRepository
	 */
	public MayctaRepository getMayctaRepository() {
		return mayctaRepository;
	}

	/**
	 * Gets the natgas repository.
	 *
	 * @return the natgasRepository
	 */
	public NatgasRepository getNatgasRepository() {
		return natgasRepository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogCuentasMB#findValuesCatalog()
	 */
	@Override
	public void findValuesCatalog() {
		// this.setBeanFind((Cuenta)
		// UtilJPA.addRightQualifiersLikeFieldsString(this.getBeanFind()));
		this.validateAndAddFillZeros(this.getBeanFind());
		if (StringUtils.isNotEmpty(this.getBeanFind().getCuenta())) {
			this.getBeanFind().setCuenta(this.getBeanFind().getCuenta() + Constants.QUALIFIER_LIKE);
		}
		if (StringUtils.isNotEmpty(this.getBeanFind().getNomcta())) {
			this.getBeanFind()
					.setNomcta(Constants.QUALIFIER_LIKE + this.getBeanFind().getNomcta() + Constants.QUALIFIER_LIKE);
		}
		this.getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		super.findValuesCatalog();
	}

	/**
	 * Gets the muni nep repository.
	 *
	 * @return the muni nep repository
	 */
	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	/**
	 * Scuenta change listener.
	 *
	 * @param event the event
	 */
	public void scuentaChangeListener(AjaxBehaviorEvent event) {

		if (null != ((UIOutput) event.getSource()).getValue()) {
			if (this.accountService.is5xAccount(rowSelected.getCuenta())
					|| rowSelected.getCuenta().toString().matches(ACCOUNT_TYPE_8217_REGEXP)) {
				rowSelected.setScuenta(
						accountService.fillRightZeros(rowSelected.getScuenta(), LENGTH_SECOND_LEVEL).toUpperCase());

			} else {
				rowSelected.setScuenta(
						accountService.fillZeros(rowSelected.getScuenta(), LENGTH_SECOND_LEVEL).toUpperCase());
			}
			activateRowEdit(0);
		}
	}

	/**
	 * Sscuenta change listener.
	 *
	 * @param event the event
	 */
	public void sscuentaChangeListener(AjaxBehaviorEvent event) {

		if (null != ((UIOutput) event.getSource()).getValue()) {
			if (this.accountService.is5xAccount(rowSelected.getCuenta())
					|| rowSelected.getCuenta().toString().matches(ACCOUNT_TYPE_8217_REGEXP)) {
				rowSelected.setSscuenta(
						accountService.fillRightZeros(rowSelected.getSscuenta(), LENGTH_THIRD_LEVEL).toUpperCase());

			} else {
				rowSelected.setSscuenta(
						accountService.fillZeros(rowSelected.getSscuenta(), LENGTH_THIRD_LEVEL).toUpperCase());
			}
			activateRowEdit(0);
		}
	}

	/**
	 * On init row edit.
	 *
	 * @param event the event
	 */
	public void onInitRowEdit(final RowEditEvent event) {
		this.rowSelected = (Cuenta) event.getObject();
	}
}
