package com.gem.sistema.web.bean;

import com.gem.sistema.web.datamodel.CustomPagingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.clearFields;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.MayctaRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.web.datamodel.CuentaDataModel;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaCuentasMB.
 */
@Component("consultaCuentasMB")
@Scope("view")
public class ConsultaCuentasMB extends AbstractMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaCuentasMB.class);

	/** Elemento form1. */
	// @Value("${view.catalog.form1}")
	protected static final String VIEW_CATALOG_FORM1 = FrontProperty.getPropertyValue("view.catalog.form1");

	/** Elemento form1. */
	// @Value("${view.catalog.objects}")
	protected static final String VIEW_CATALOG_OBJECTS = FrontProperty.getPropertyValue("view.catalog.objects");

	/** Habilitar el modo de edicion. */
	// @Value("${view.catalog.form1.objects}")
	protected static final String VIEW_CATALOG_FORM1_OBJECTS = FrontProperty
			.getPropertyValue("view.catalog.form1.objects");

	/** Longitud del primer nivel de cuentas. */
	public static final int LENGTH_FIRST_LEVEL = 4;

	/** Longitud del segundo nivel de cuentas. */
	public static final int LENGTH_SECOND_LEVEL = 15;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_THIRD_LEVEL = 20;

	/** Longitud del cuarto nivel de cuentas. */
	public static final int LENGTH_FOUR_LEVEL = 4;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_FIVE_LEVEL = 3;

	/** Servicios para el modulo de centas. */
	@Value("#{accountService}")
	private AccountService accountService;

	/** The polien repository. */
	@Value("#{polienRepository}")
	private PolienRepository polienRepository;

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository
	 *            the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Repositorio de Cuenta.
	 */
	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/**
	 * Repositorio de Maycta.
	 */
	@Value("#{mayctaRepository}")
	private MayctaRepository mayctaRepository;

	/** The cuenta data model. */
	@Value("#{cuentaDataModel}")
	private CuentaDataModel cuentaDataModel;

	/** The con poliz sirvice. */
	@Value("#{consultaPolizaSirveImpl}")
	private ConsultaPolizaSirve conPolizSirvice;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The bean find. */
	private Cuenta beanFind;

	/** The mes. */
	private Integer mes;

	/** The nivcta. */
	private String nivcta;

	/** The saldo inicial. */
	private BigDecimal saldoInicial;

	/** The cargos. */
	private BigDecimal cargos;

	/** The abonos. */
	private BigDecimal abonos;

	/** The saldo final. */
	private BigDecimal saldoFinal;

	/** Fila seleccionada del catalogo de programas. */
	private Cuenta rowSelected;

	/**
	 * Gets the maycta repository.
	 *
	 * @return the maycta repository
	 */
	public MayctaRepository getMayctaRepository() {
		return mayctaRepository;
	}

	/**
	 * Sets the maycta repository.
	 *
	 * @param mayctaRepository
	 *            the new maycta repository
	 */
	public void setMayctaRepository(MayctaRepository mayctaRepository) {
		this.mayctaRepository = mayctaRepository;
	}

	/**
	 * Gets the bean find.
	 *
	 * @return the bean find
	 */
	public Cuenta getBeanFind() {
		return beanFind;
	}

	/**
	 * Sets the bean find.
	 *
	 * @param beanFind
	 *            the new bean find
	 */
	public void setBeanFind(Cuenta beanFind) {
		this.beanFind = beanFind;
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService
	 *            the new account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository
	 *            the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogAccountsMB ");
		Cuenta cuenta2find = new Cuenta();
		cuenta2find.setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		cuenta2find.setCuenta("1000");
		this.mes = 1;
		setBeanFind(cuenta2find);
		this.cuentaDataModel.setBeanFind(cuenta2find);
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
	 * Consult list.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogAccountsMB {}", getBeanFind());
		this.getCuentaDataModel().getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		this.getCuentaDataModel().setCount(null);
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(VIEW_CATALOG_FORM1).findComponent(VIEW_CATALOG_FORM1_OBJECTS);
		// dataTable.setFirst(ZERO);
		dataTable.setFilterByAsMap(CustomPagingModel.toFilterMeta(this.findByAccountLikeComposite(this.getCuentaDataModel().getBeanFind())));
		dataTable.processUpdates(FacesContext.getCurrentInstance());

		PrimeFaces.current().executeScript("PF('tbCuentas').filter();");
	}

	/**
	 * Validate status.
	 */
	public void validateStatus() {
		if ((null != this.mes && this.mes > 0)) {
			if (this.validatePolicyService.isOpenMonth(mes, null,
					this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				// if
				// (this.conPolizSirvice.findActiveMonth(this.getUserDetails().getIdSector())
				// < this.getMes()) {
				// displayErrorMsg("El mes no está activo");
				// }
				if (CollectionUtils.isEmpty((Collection<?>) polienRepository
						.findAll(PolienPredicates.isAfect(mes, "A", this.getUserDetails().getIdSector())))) {
					displayErrorMsg("El mes no está afectado");
				}
			}
		}
	}

	/**
	 * Display error msg.
	 *
	 * @param msg
	 *            the msg
	 */
	private static void displayErrorMsg(final String msg) {
		FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", msg);
		FacesContext.getCurrentInstance().addMessage(ERROR_MSG, errorMsg);
	}

	/** The Constant ERROR_MSG. */
	private static final String ERROR_MSG = "errorMsg";

	/** The Constant ERROR. */
	private static final String ERROR = "Error";

	/**
	 * Find by account like composite.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the map
	 */
	private Map<String, Object> findByAccountLikeComposite(final Cuenta cuenta) {
		Map<String, Object> p = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p.put("accountInput", cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			p.put("saccountInput", cuenta.getScuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			p.put("ssaccountInput", cuenta.getSscuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			p.put("sssaccountInput", cuenta.getSsscuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			p.put("ssssaccountInput", cuenta.getSssscuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getNomcta())) {
			p.put("titleInput", cuenta.getNomcta());
		}
		p.put("idsector", cuenta.getIdsector());
		return p;
	}

	/**
	 * Consult all.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	protected void consultAll() {
		this.getCuentaDataModel().getBeanFind().setCuenta(StringUtils.EMPTY);
		this.getCuentaDataModel().getBeanFind().setScuenta(StringUtils.EMPTY);
		this.getCuentaDataModel().getBeanFind().setSscuenta(StringUtils.EMPTY);
		this.getCuentaDataModel().getBeanFind().setSsscuenta(StringUtils.EMPTY);
		this.getCuentaDataModel().getBeanFind().setSssscuenta(StringUtils.EMPTY);
		this.getCuentaDataModel().getBeanFind().setNomcta(StringUtils.EMPTY);
		this.getCuentaDataModel().getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		this.getCuentaDataModel().setReset(Boolean.TRUE);
		this.getCuentaDataModel().setCount(null);

		PrimeFaces.current().executeScript("PF('tbCuentas').clearFilters();");
	}

	/**
	 * Find all values catalog.
	 */
	public void findAllValuesCatalog() {
		LOGGER.info(":: En buscar todos los elementos del catalogo ");
		clearFields(this.getCuentaDataModel().getBeanFind());
		this.getCuentaDataModel().setInsert(Boolean.FALSE);
		consultAll();
	}

	/**
	 * Find values catalog.
	 */
	public void findValuesCatalog() {
		this.validateAndAddFillZeros(this.getCuentaDataModel().getBeanFind());
		consultList();
	}

	/**
	 * Validate and add fill zeros.
	 *
	 * @param account
	 *            the account
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
	 * @param rowSelected
	 *            the rowSelected to set
	 */
	public void setRowSelected(final Cuenta rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the cuenta data model.
	 *
	 * @return the cuentaDataModel
	 */
	public CuentaDataModel getCuentaDataModel() {
		return cuentaDataModel;
	}

	/**
	 * Sets the cuenta data model.
	 *
	 * @param cuentaDataModel
	 *            the cuentaDataModel to set
	 */
	public void setCuentaDataModel(CuentaDataModel cuentaDataModel) {
		Cuenta bean2find = new Cuenta();
		bean2find.setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		cuentaDataModel.setBeanFind(new Cuenta());
		this.cuentaDataModel = cuentaDataModel;
	}

	/**
	 * Restart data.
	 */
	protected void restartData() {
		this.cuentaDataModel.setInsert(Boolean.FALSE);
	}

	/**
	 * Restart data operation delete.
	 */
	protected void restartDataOperationDelete() {
		this.restartData();
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
	 * Update.
	 *
	 * @param event
	 *            the event
	 */
	public void update(PageEvent event) {
		int var = event.getPage();
		if (var > 0) {
			this.getCuentaDataModel().setInsert(Boolean.FALSE);
		} else {
			if (this.getCuentaDataModel().getInsert()) {
				PrimeFaces.current().executeScript(
						"jQuery('.ui-datatable-data tr').first().find('span.ui-icon-pencil').each(function(){jQuery(this).click()});");
			}
		}
	}

	/**
	 * On row select.
	 *
	 * @param selectEvent
	 *            the select event
	 */
	public void onRowSelect(SelectEvent selectEvent) {
		Cuenta selectedPolide = (Cuenta) selectEvent.getObject();
		this.saldoInicial = null;
		this.saldoFinal = null;
		this.cargos = null;
		this.abonos = null;
		this.nivcta = null;
		this.doChangeSaldos(selectedPolide);
	}

	/** The Constant NAT_A. */
	private static final String NAT_A = "A";

	/** The Constant NAT_D. */
	private static final String NAT_D = "D";

	/**
	 * Do change saldos.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void doChangeSaldos(Cuenta cuenta) {

		if ((null != this.mes && this.mes > 0)) {
			if (this.conPolizSirvice.findActiveMonth(this.getUserDetails().getIdSector()) < this.getMes()) {
				displayErrorMsg("El mes no está activo");
			} else {
				if (null != cuenta && (null != this.mes && this.mes > 0)) {
					if (this.mes == 1) {
						this.saldoInicial = this.notNullBigDecimal(cuenta.getSalini());
						this.cargos = this.notNullBigDecimal(cuenta.getCargos1());
						this.abonos = this.notNullBigDecimal(cuenta.getAbonos1());

						if (NAT_D.equalsIgnoreCase(cuenta.getStacta())) {
							this.saldoFinal = this.saldoInicial.add((this.cargos.subtract(this.abonos)));
						}
						if (NAT_A.equalsIgnoreCase(cuenta.getStacta())) {
							this.saldoFinal = this.saldoInicial.add((this.abonos.subtract(this.cargos)));
						}
					} else {
						this.cargos = this.notNullBigDecimal(this.getCargosByMes(cuenta, mes));
						this.abonos = this.notNullBigDecimal(this.getAbonosByMes(cuenta, mes));
						if (NAT_D.equalsIgnoreCase(cuenta.getStacta())) {
							this.saldoInicial = this.notNullBigDecimal(cuenta.getSalini())
									.add(this.notNullBigDecimal(this.getSumCargosAbonos(cuenta, mes)));
							this.saldoFinal = this.notNullBigDecimal(this.saldoInicial)
									.add((this.cargos.subtract(this.abonos)));
						}
						if (NAT_A.equalsIgnoreCase(cuenta.getStacta())) {
							this.saldoInicial = this.notNullBigDecimal(cuenta.getSalini())
									.add(this.notNullBigDecimal(this.getSumAbonosCargos(cuenta, mes)));
							this.saldoFinal = this.saldoInicial.add((this.notNullBigDecimal(this.abonos)
									.subtract(this.notNullBigDecimal(this.cargos))));
						}

					}

					List<Maycta> result = this.mayctaRepository.findByCuenta(cuenta.getCuenta());
					if (CollectionUtils.isNotEmpty(result)) {
						this.nivcta = StringUtils.EMPTY + result.get(0).getNivcta();
					}
				}
			}
		}

	}

	/**
	 * Gets the cargos by mes.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param mes
	 *            the mes
	 * @return the cargos by mes
	 */
	private BigDecimal getCargosByMes(Cuenta cuenta, Integer mes) {
		switch (mes) {
		case 1:
			return cuenta.getCargos1();
		case 2:
			return cuenta.getCargos2();
		case 3:
			return cuenta.getCargos3();
		case 4:
			return cuenta.getCargos4();
		case 5:
			return cuenta.getCargos5();
		case 6:
			return cuenta.getCargos6();
		case 7:
			return cuenta.getCargos7();
		case 8:
			return cuenta.getCargos8();
		case 9:
			return cuenta.getCargos9();
		case 10:
			return cuenta.getCargos10();
		case 11:
			return cuenta.getCargos11();
		case 12:
			return cuenta.getCargos12();
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Gets the abonos by mes.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param mes
	 *            the mes
	 * @return the abonos by mes
	 */
	private BigDecimal getAbonosByMes(Cuenta cuenta, Integer mes) {
		switch (mes) {
		case 1:
			return cuenta.getAbonos1();
		case 2:
			return cuenta.getAbonos2();
		case 3:
			return cuenta.getAbonos3();
		case 4:
			return cuenta.getAbonos4();
		case 5:
			return cuenta.getAbonos5();
		case 6:
			return cuenta.getAbonos6();
		case 7:
			return cuenta.getAbonos7();
		case 8:
			return cuenta.getAbonos8();
		case 9:
			return cuenta.getAbonos9();
		case 10:
			return cuenta.getAbonos10();
		case 11:
			return cuenta.getAbonos11();
		case 12:
			return cuenta.getAbonos12();
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Gets the sum cargos abonos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param mes
	 *            the mes
	 * @return the sum cargos abonos
	 */
	private BigDecimal getSumCargosAbonos(Cuenta cuenta, Integer mes) {
		BigDecimal toReturn = BigDecimal.ZERO;
		for (int i = 1; i < mes; i++) {
			toReturn = toReturn.add(this.getCargosByMes(cuenta, i).subtract(this.getAbonosByMes(cuenta, i)));
		}
		return toReturn;
	}

	/**
	 * Gets the sum abonos cargos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param mes
	 *            the mes
	 * @return the sum abonos cargos
	 */
	private BigDecimal getSumAbonosCargos(Cuenta cuenta, Integer mes) {
		BigDecimal toReturn = BigDecimal.ZERO;
		for (int i = 1; i < mes; i++) {
			toReturn = toReturn.add(this.getAbonosByMes(cuenta, i).subtract(this.getCargosByMes(cuenta, i)));
		}
		return toReturn;
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
	 * @param mes
	 *            the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the nivcta.
	 *
	 * @return the nivcta
	 */
	public String getNivcta() {
		return nivcta;
	}

	/**
	 * Sets the nivcta.
	 *
	 * @param nivcta
	 *            the new nivcta
	 */
	public void setNivcta(String nivcta) {
		this.nivcta = nivcta;
	}

	/**
	 * Gets the saldo inicial.
	 *
	 * @return the saldo inicial
	 */
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * Sets the saldo inicial.
	 *
	 * @param saldoInicial
	 *            the new saldo inicial
	 */
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * Gets the cargos.
	 *
	 * @return the cargos
	 */
	public BigDecimal getCargos() {
		return cargos;
	}

	/**
	 * Sets the cargos.
	 *
	 * @param cargos
	 *            the new cargos
	 */
	public void setCargos(BigDecimal cargos) {
		this.cargos = cargos;
	}

	/**
	 * Gets the abonos.
	 *
	 * @return the abonos
	 */
	public BigDecimal getAbonos() {
		return abonos;
	}

	/**
	 * Sets the abonos.
	 *
	 * @param abonos
	 *            the new abonos
	 */
	public void setAbonos(BigDecimal abonos) {
		this.abonos = abonos;
	}

	/**
	 * Gets the saldo final.
	 *
	 * @return the saldo final
	 */
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}

	/**
	 * Sets the saldo final.
	 *
	 * @param saldoFinal
	 *            the new saldo final
	 */
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	/**
	 * Gets the con poliz sirvice.
	 *
	 * @return the con poliz sirvice
	 */
	public ConsultaPolizaSirve getConPolizSirvice() {
		return conPolizSirvice;
	}

	/**
	 * Sets the con poliz sirvice.
	 *
	 * @param conPolizSirvice
	 *            the new con poliz sirvice
	 */
	public void setConPolizSirvice(ConsultaPolizaSirve conPolizSirvice) {
		this.conPolizSirvice = conPolizSirvice;
	}

	/**
	 * Not null big decimal.
	 *
	 * @param a
	 *            the a
	 * @return the big decimal
	 */
	public BigDecimal notNullBigDecimal(BigDecimal a) {
		if (a == null)
			a = BigDecimal.ZERO;
		return a;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
