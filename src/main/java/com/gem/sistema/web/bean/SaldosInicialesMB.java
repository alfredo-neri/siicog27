package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;
import javax.security.auth.login.AccountException;

import com.gem.sistema.business.domain.MonthlyAbstractEntity;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.domain.Paso;
import com.gem.sistema.business.service.utilerias.SaldosInicialesService;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.context.RequestContext;

// TODO: Auto-generated Javadoc
/**
 * The Class SaldosInicialesMB.
 */
@ManagedBean(name = "saldosInicialesMB")
@ViewScoped
public class SaldosInicialesMB extends AbstractMB {

	/** The cve acceso. */
	private String cveAcceso;
	
	/** The cuenta. */
	private String cuenta;
	
	/** The scta. */
	private String scta;
	
	/** The nomcta. */
	private String nomcta;
	
	/** The cuentas. */
	private List<Cuenta> cuentas;

	/** The antecesor. */
	private String antecesor;
	
	/** The sdo inicial. */
	private BigDecimal sdoInicial;
	
	/** The suma. */
	private BigDecimal suma;

	/** The mentity. */
	private MonthlyAbstractEntity mentity;
	
	/** The selected account. */
	private Cuenta selectedAccount;

	/** The sal ini btn disabled. */
	private Boolean salIniBtnDisabled = false;
	
	/** The months disabled. */
	private Boolean monthsDisabled = false;

	/** The saldos iniciales service. */
	@ManagedProperty("#{saldosInicialesService}")
	private SaldosInicialesService saldosInicialesService;

	/** The Constant cuentas821x. */
	private static final List<String> cuentas821x = Arrays
			.asList(new String[] { "8221", "8222", "8223", "8224", "8225", "8226", "8227" });

	/** The Constant noSalIniAccounts. */
	private static final List<String> noSalIniAccounts = Arrays.asList(new String[] { "8120", "8110", "8211", "8212",
			"8213", "8214", "8215", "8216", "8217", "5100", "5200", "5300", "5400", "5600", "5700",  "4100",
			"4200", "4300", "8130", "8231", "8232", "8233", "8234", "8235", "8236", "8237", "8241", "8242", "8243",
			"8244", "8245", "8246", "8247", "8251", "8252", "8253", "8254", "8255", "8256", "8257" });

	/** The Constant cuentas821xCentral. */
	private static final List<String> cuentas821xCentral = Arrays
			.asList(new String[] { "8221", "8222", "8223", "8224", "8225", "8226", "8227" });

	/** The Constant muniPasoAccounts. */
	private static final List<String> muniPasoAccounts = Arrays
			.asList(new String[] { "8211", "8212", "8213", "8214", "8215", "8216", "8217" });

	/** The Constant muniMonthlyDisabledAccounts. */
	private static final List<String> muniMonthlyDisabledAccounts = Arrays
			.asList(new String[] { "8120", "8221", "8222", "8223", "8224", "8225", "8226", "8227" });

	/**
	 * Gets the cve acceso.
	 *
	 * @return the cve acceso
	 */
	public String getCveAcceso() {
		return cveAcceso;
	}

	/**
	 * Sets the cve acceso.
	 *
	 * @param cveAcceso the new cve acceso
	 */
	public void setCveAcceso(String cveAcceso) {
		this.cveAcceso = cveAcceso;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return StringUtils.defaultIfBlank(cuenta, "");
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the scta.
	 *
	 * @return the scta
	 */
	public String getScta() {
		return StringUtils.defaultIfBlank(scta, "");
	}

	/**
	 * Sets the scta.
	 *
	 * @param scta the new scta
	 */
	public void setScta(String scta) {
		this.scta = scta;
	}

	/**
	 * Gets the nomcta.
	 *
	 * @return the nomcta
	 */
	public String getNomcta() {
		return nomcta;
	}

	/**
	 * Sets the nomcta.
	 *
	 * @param nomcta the new nomcta
	 */
	public void setNomcta(String nomcta) {
		this.nomcta = nomcta;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<Cuenta> getCuentas() {
		if (cuentas == null) {
			return new ArrayList<>();
		}
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas the new cuentas
	 */
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the antecesor.
	 *
	 * @return the antecesor
	 */
	public String getAntecesor() {
		return antecesor;
	}

	/**
	 * Sets the antecesor.
	 *
	 * @param antecesor the new antecesor
	 */
	public void setAntecesor(String antecesor) {
		this.antecesor = antecesor;
	}

	/**
	 * Gets the sdo inicial.
	 *
	 * @return the sdo inicial
	 */
	public BigDecimal getSdoInicial() {
		return sdoInicial;
	}

	/**
	 * Sets the sdo inicial.
	 *
	 * @param sdoInicial the new sdo inicial
	 */
	public void setSdoInicial(BigDecimal sdoInicial) {
		this.sdoInicial = sdoInicial;
	}

	/**
	 * Gets the suma.
	 *
	 * @return the suma
	 */
	public BigDecimal getSuma() {
		return suma;
	}

	/**
	 * Sets the suma.
	 *
	 * @param suma the new suma
	 */
	public void setSuma(BigDecimal suma) {
		this.suma = suma;
	}

	/**
	 * Gets the mentity.
	 *
	 * @return the mentity
	 */
	public MonthlyAbstractEntity getMentity() {
		return mentity == null ? MonthlyAbstractEntity.getEmptyInstance() : mentity;
	}

	/**
	 * Sets the mentity.
	 *
	 * @param mentity the new mentity
	 */
	public void setMentity(MonthlyAbstractEntity mentity) {
		this.mentity = mentity;
	}

	/**
	 * Gets the selected account.
	 *
	 * @return the selected account
	 */
	public Cuenta getSelectedAccount() {
		return selectedAccount;
	}

	/**
	 * Sets the selected account.
	 *
	 * @param selectedAccount the new selected account
	 */
	public void setSelectedAccount(Cuenta selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	/**
	 * Sets the sal ini btn disabled.
	 *
	 * @param salIniBtnDisabled the new sal ini btn disabled
	 */
	public void setSalIniBtnDisabled(Boolean salIniBtnDisabled) {
		this.salIniBtnDisabled = salIniBtnDisabled;
	}

	/**
	 * Gets the sal ini btn disabled.
	 *
	 * @return the sal ini btn disabled
	 */
	public Boolean getSalIniBtnDisabled() {
		return salIniBtnDisabled;
	}

	/**
	 * Gets the months disabled.
	 *
	 * @return the months disabled
	 */
	public Boolean getMonthsDisabled() {
		return monthsDisabled;
	}

	/**
	 * Sets the months disabled.
	 *
	 * @param monthsDisabled the new months disabled
	 */
	public void setMonthsDisabled(Boolean monthsDisabled) {
		this.monthsDisabled = monthsDisabled;
	}

	/**
	 * Gets the saldos iniciales service.
	 *
	 * @return the saldos iniciales service
	 */
	public SaldosInicialesService getSaldosInicialesService() {
		return saldosInicialesService;
	}

	/**
	 * Sets the saldos iniciales service.
	 *
	 * @param saldosInicialesService the new saldos iniciales service
	 */
	public void setSaldosInicialesService(SaldosInicialesService saldosInicialesService) {
		this.saldosInicialesService = saldosInicialesService;
	}

	/**
	 * se selecciona la cuenta del data Table.
	 *
	 * @param event the event
	 */
	public void accountSelect(SelectEvent event) {
		mentity = MonthlyAbstractEntity.getEmptyInstance();
		System.out.println("event," + event.getObject());
		Cuenta cuenta = (Cuenta) event.getObject();
		String res = saldosInicialesService.getPreviousAccountName(cuenta, getIdSectorForCurrentUser());
		setAntecesor(res);
		setSdoInicial(cuenta.getSalini());

		if (getIdSectorForCurrentUser().equals(1l)) {
			accountSelectMunicipal(cuenta);
		} else {
			accountSelectCentral(cuenta);
		}
	}

	/**
	 * Account select municipal.
	 *
	 * @param account the account
	 */
	public void accountSelectMunicipal(Cuenta account) {
		RequestContext context = RequestContext.getCurrentInstance();

		if (account.getCuenta().equals("8110")) {
			mentity = saldosInicialesService.getIngreso(account, "8110", getIdSectorForCurrentUser());
			System.out.println("adding ingreso: " + mentity);
			context.addCallbackParam("details", true);
		} else if (muniPasoAccounts.contains(account.getCuenta())) {
			mentity = saldosInicialesService.findPasoForClaveProgramaAndPartida(account.getScuenta(),
					account.getSscuenta(), account.getSsscuenta(), getIdSectorForCurrentUser(), (isNull) -> {
						displayErrorMessage("no existe egreso 6004");
					});
			context.addCallbackParam("details", false);
		} else if (muniMonthlyDisabledAccounts.contains(account.getCuenta())) {
			context.addCallbackParam("details", false);
		} else {
			context.addCallbackParam("details", false);
		}
	}

	/**
	 * Account select central.
	 *
	 * @param cuenta the cuenta
	 */
	public void accountSelectCentral(Cuenta cuenta) {
		RequestContext context = RequestContext.getCurrentInstance();

		if ("6003".equals(cuenta.getCuenta()) || "6007".equals(cuenta.getCuenta())) {
			setSalIniBtnDisabled(true);
			context.addCallbackParam("details", false);
		} else {
			setSalIniBtnDisabled(false);
		}
		if (!("6001".equals(cuenta.getCuenta()) || "6004".equals(cuenta.getCuenta()))) {
			setMonthsDisabled(true);
			context.addCallbackParam("details", false);
		} else {
			context.addCallbackParam("details", true);
			if ("6001".equals(cuenta.getCuenta())) {
				mentity = saldosInicialesService.getIngreso(cuenta, "6001", getIdSectorForCurrentUser());
			} else if ("6004".equals(cuenta.getCuenta())) {
				mentity = saldosInicialesService.findPasoForClaveProgramaAndPartida(cuenta.getScuenta(),
						cuenta.getSscuenta(), cuenta.getSsscuenta(), getIdSectorForCurrentUser(), (isNull) -> {
							displayErrorMessage("no existe egreso 6004");
						});
				context.addCallbackParam("details", false);
			} else if (!("6003".equals(cuenta.getCuenta()) || "6007".equals(cuenta.getCuenta()))) {
				context.addCallbackParam("details", true);
				setMonthsDisabled(false);
			}
		}
	}

	/**
	 * Validate pass.
	 */
	public void validatePass() {
		if (!"wsalinixz".equalsIgnoreCase(getCveAcceso())) {
			displayWarnMessage("Clave incorrecta");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context.getResponse();
			response.setStatus(500);
			return;
		} else if(null == this.getCveAcceso()) {
			displayWarnMessage("Introdusca la clave de acceso");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context.getResponse();
			response.setStatus(500);
		}
		System.out.println("valid password");
	}

	/**
	 * Validate account.
	 */
	public void validateAccount() {
		mentity = MonthlyAbstractEntity.getEmptyInstance();
		System.out.println("validating account: " + getCuenta());

		if ((getIdSectorForCurrentUser().equals(1L) && validateAccountsMunicipal())
				|| (getIdSectorForCurrentUser().equals(2L) && validateAccountsCentral())) {
			loadData();
		}

	}

	/**
	 * Validate scta.
	 */
	public void validateScta() {
		mentity = MonthlyAbstractEntity.getEmptyInstance();

		if ((getIdSectorForCurrentUser().equals(1L) && validateAccountsMunicipal())
				|| (getIdSectorForCurrentUser().equals(2L) && validateSctaCentral())) {
			loadData();
		}

	}

	/**
	 * Validate accounts municipal.
	 *
	 * @return true, if successful
	 */
	public boolean validateAccountsMunicipal() {
		if ("8120".equals(getCuenta())) {
			displayErrorMessage("Esta cuenta se actualiza con la 8110");
			return false;
		} else if (cuentas821x.contains(getCuenta())) {
			displayErrorMessage("Esta cuenta se actualiza con la 821X");
			return false;
		}
		return true;
	}

	/**
	 * Validate accounts central.
	 *
	 * @return true, if successful
	 */
	public boolean validateAccountsCentral() {
		if (noSalIniAccounts.contains(getCuenta())) {
			displayErrorMessage("Esta cuenta no carga saldos iniciales");
			return false;
		} else if (cuentas821xCentral.contains(getCuenta())) {
			displayErrorMessage("Esta cuenta se actualiza con la 8211,8212,8213,8214,8215,8216,8217");
			return false;
		}
		return true;
	}

	/**
	 * Validate scta central.
	 *
	 * @return true, if successful
	 */
	public boolean validateSctaCentral() {
		if ("6003".equals(getCuenta())) {
			displayErrorMessage("Esta cuenta se actualiza con la 6001");
			return false;
		} else if ("6007".equals(getCuenta())) {
			displayErrorMessage("Esta cuenta se actualiza con la 6004");
			return false;
		}
		return true;
	}

	/**
	 * Load data.
	 */
	public void loadData() {
		Cuenta firstCuenta = saldosInicialesService.findFirstCuenta(getCuenta(), getIdSectorForCurrentUser());
		if (firstCuenta == null) {
			displayWarnMessage("No existe esta cuenta");
		} else {
			setNomcta(firstCuenta.getNomcta());
		}

		List<Cuenta> cuentas = saldosInicialesService.findCuentasFor(getCuenta(), getScta(),
				getIdSectorForCurrentUser());
		System.out.println("Cuentas: " + cuentas);
		setCuentas(cuentas);

		Cuenta cuenta = saldosInicialesService.findFirstByCuentaAndIdsector(getCuenta(), getIdSectorForCurrentUser());

		if (cuenta == null) {
			displayErrorMessage("No existe ninguna cuenta que inicie con estos caracteres");
		} else {
			System.out.println("La cuenta existe, continuado");
			setSdoInicial(cuenta.getSalini());
		}
	}

	/**
	 * Save.
	 */
	public void save() {
		System.out.println("Saving data");

		if (getIdSectorForCurrentUser().equals(1l)) {
			if (saldosInicialesService.saveMuni(selectedAccount, getSdoInicial(), mentity, getIdSectorForCurrentUser(),
					(msg) -> {
						displayErrorMessage(msg);
					})) {

				mentity = MonthlyAbstractEntity.getEmptyInstance();
				setCuentas(null);
				setSelectedAccount(null);
				setSdoInicial(BigDecimal.ZERO);
				setAntecesor("");
				setCuenta("");
				RequestContext context = RequestContext.getCurrentInstance();
				context.addCallbackParam("details", false);
				displayInfoMessage("Actualizado");
			}
		} else {
			if (saldosInicialesService.saveCentral(selectedAccount, getSdoInicial(), mentity,
					getIdSectorForCurrentUser(), (msg) -> {
						displayErrorMessage(msg);
					})) {

				mentity = MonthlyAbstractEntity.getEmptyInstance();
				setCuentas(null);
				setSelectedAccount(null);
				setSdoInicial(BigDecimal.ZERO);
				setAntecesor("");
				setCuenta("");
				RequestContext context = RequestContext.getCurrentInstance();
				context.addCallbackParam("details", false);
				displayInfoMessage("Actualizado");
			}
		}
	}
}
