package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Programamun;
import com.gem.sistema.business.domain.TcParametro;

import javax.security.auth.login.AccountException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.load.IngresoRepository;
import com.gem.sistema.business.repository.load.PasoRepository;
import com.gem.sistema.business.repository.catalogs.PasotRepository;
import com.gem.sistema.business.repository.catalogs.PreprograRepository;
import com.gem.sistema.business.repository.catalogs.IngresotRepository;

import com.gem.sistema.business.domain.Programamun;
import com.gem.sistema.business.domain.Pasot;
import com.gem.sistema.business.domain.Preprogra;
import com.gem.sistema.business.service.callsp.CallSpService;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.ChangePasswordService;
import com.gem.sistema.ennum.constans.ConstansEnum;
import com.gem.sistema.business.repository.catalogs.ProgramamunRepository;
import com.gem.sistema.web.security.model.GemUser;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class AcumulacionSaldosMB.
 */
@ManagedBean(name = "acumulacionSaldosMB")
@ViewScoped
public class AcumulacionSaldosMB extends AbstractMB {
	
	private static final String PROCEDURE_NAME = "SP_PISTOV409_M";

	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** The ingreso repository. */
	@ManagedProperty("#{ingresoRepository}")
	private IngresoRepository ingresoRepository;

	/** The ingresot repository. */
	@ManagedProperty("#{ingresotRepository}")
	private IngresotRepository ingresotRepository;

	/** The paso repository. */
	@ManagedProperty("#{pasoRepository}")
	private PasoRepository pasoRepository;

	/** The pasot repository. */
	@ManagedProperty("#{pasotRepository}")
	private PasotRepository pasotRepository;

	/** The preprogra repository. */
	@ManagedProperty("#{preprograRepository}")
	private PreprograRepository preprograRepository;

	/** The programamun repository. */
	@ManagedProperty("#{programamunRepository}")
	private ProgramamunRepository programamunRepository;

	/** The account service. */
	@ManagedProperty("#{accountService}")
	private AccountService accountService;

	private TcParametro tcParametro;
	private String password;

	@ManagedProperty("#{changePasswordService}")
	private ChangePasswordService changePasswordService;
	
	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;

	/** The disabled. */
	private Boolean disabled = Boolean.FALSE;
	private Boolean bDisable = Boolean.TRUE;

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
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the ingreso repository.
	 *
	 * @param ingresoRepository
	 *            the new ingreso repository
	 */
	public void setIngresoRepository(IngresoRepository ingresoRepository) {
		this.ingresoRepository = ingresoRepository;
	}

	/**
	 * Gets the ingreso repository.
	 *
	 * @return the ingreso repository
	 */
	public IngresoRepository getIngresoRepository() {
		return ingresoRepository;
	}

	/**
	 * Sets the ingresot repository.
	 *
	 * @param ingresotRepository
	 *            the new ingresot repository
	 */
	public void setIngresotRepository(IngresotRepository ingresotRepository) {
		this.ingresotRepository = ingresotRepository;
	}

	/**
	 * Gets the ingresot repository.
	 *
	 * @return the ingresot repository
	 */
	public IngresotRepository getIngresotRepository() {
		return ingresotRepository;
	}

	/**
	 * Sets the programamun repository.
	 *
	 * @param programamunRepository
	 *            the new programamun repository
	 */
	public void setProgramamunRepository(ProgramamunRepository programamunRepository) {
		this.programamunRepository = programamunRepository;
	}

	/**
	 * Gets the programamun repository.
	 *
	 * @return the programamun repository
	 */
	public ProgramamunRepository getProgramamunRepository() {
		return programamunRepository;
	}

	/**
	 * Sets the paso repository.
	 *
	 * @param pasoRepository
	 *            the new paso repository
	 */
	public void setPasoRepository(PasoRepository pasoRepository) {
		this.pasoRepository = pasoRepository;
	}

	/**
	 * Gets the paso repository.
	 *
	 * @return the paso repository
	 */
	public PasoRepository getPasoRepository() {
		return pasoRepository;
	}

	/**
	 * Sets the pasot repository.
	 *
	 * @param pasotRepository
	 *            the new pasot repository
	 */
	public void setPasotRepository(PasotRepository pasotRepository) {
		this.pasotRepository = pasotRepository;
	}

	/**
	 * Gets the pasot repository.
	 *
	 * @return the pasot repository
	 */
	public PasotRepository getPasotRepository() {
		return pasotRepository;
	}

	/**
	 * Sets the preprogra repository.
	 *
	 * @param preprograRepository
	 *            the new preprogra repository
	 */
	public void setPreprograRepository(PreprograRepository preprograRepository) {
		this.preprograRepository = preprograRepository;
	}

	/**
	 * Gets the preprogra repository.
	 *
	 * @return the preprogra repository
	 */
	public PreprograRepository getPreprograRepository() {
		return preprograRepository;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the account service
	 */
	public AccountService getAccountService() {
		return accountService;
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
	 * Gets the disabled.
	 *
	 * @return the disabled
	 */
	public Boolean getDisabled() {
		return disabled;
	}

	/**
	 * Process.
	 */
	public void process() {
		disabled = Boolean.TRUE;
		if (getIdSectorForCurrentUser() == 1) {
			processMunicipal(getIdSectorForCurrentUser().intValue());
		} else {
			processCentral(getIdSectorForCurrentUser());
		}
		displayInfoMessage("ACUMULACION DE SALDOS CONCLUIDA");
		disabled = Boolean.FALSE;
		bDisable = true;
	}

	/**
	 * Process municipal.
	 *
	 * @param idSector
	 *            the id sector
	 */
	public void processMunicipal(Integer idSector) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("i_id_sector", idSector).addValue("i_user", this.getUserDetails().getUsername());
		Map<String, Object> out = this.executePlService.callProcedure(PROCEDURE_NAME, parameter);
		
		salSum1(idSector.longValue());
		
		cuentaRepository.updateNameByCuentaAndSctaAndIdsector("LEY DE INGRESOS POR EJECUTAR", idSector.longValue(),
				"8120", "");

	}

	/**
	 * Process central.
	 *
	 * @param idSector
	 *            the id sector
	 */
	public void processCentral(Long idSector) {
		cuentaRepository.executeAcumulacionSaldos(idSector.intValue());
		salSum1(idSector);
	}

	/**
	 * Acumula pasot.
	 *
	 * @param idSector
	 *            the id sector
	 * @param userId
	 *            the user id
	 */
	private void acumulaPasot(Integer idSector, String userId) {
		List<Object[]> results = pasoRepository.findPartidaPrimerNivelByIdSector(idSector);
		for (Object[] res : results) {
			if (pasotRepository.findFirstByPartidaAndIdsector((String) res[0], idSector) == null) {
				BigDecimal total = new BigDecimal(0);
				for (int i = 1; i <= 12; i++)
					total = total.add((BigDecimal) res[i]);
				Pasot pasot = new Pasot();
				pasot.setPartida((String) res[0]);
				pasot.setAuto11(total);
				pasot.setUserid(userId);
				pasot.setIdsector(idSector);
				pasot.setIdRef(0l);
				pasotRepository.save(pasot);
			}
		}

	}

	/**
	 * Progmun.
	 *
	 * @param idSector
	 *            the id sector
	 * @param userId
	 *            the user id
	 */
	private void progmun(Integer idSector, String userId) {
		List<Object[]> results = pasoRepository.findClaveAndProgramaByIdSector(idSector);
		for (Object[] res : results) {
			String clave = (String) res[0];
			String cvdepg = clave.substring(0, 3);
			String cvdepa = clave.substring(3, 6);
			if (programamunRepository.findFirstByCvedepgAndCvedepaAndProgramaAndIdsector(cvdepg, cvdepa,
					(String) res[1], idSector) == null) {
				Programamun prog = new Programamun();
				prog.setCvedepg(cvdepg);
				prog.setCvedepa(cvdepa);
				prog.setPrograma((String) res[1]);
				prog.setIdsector(idSector);
				prog.setIdRef(0l);
				prog.setUserid(userId);
				prog.setPobben(0);
				programamunRepository.save(prog);
			}
		}

	}

	/**
	 * Sal sum 1.
	 *
	 * @param idSector
	 *            the id sector
	 */
	private void salSum1(Long idSector) {
		update3DigitAccounts(idSector);
		updateAccountRangeSalini(idSector, "4100", "4900", "4000", "", "No existe cuenta 4000 de mayor");
		updateAccountRangeSalini(idSector, "5100", "5900", "5000", "", "No existe cuenta 5000 de mayor");
		updateAccountRangeSalini(idSector, "6000", "6900", "6000", "", "No existe cuenta 6000 de mayor");
		updateAccountRangeSalini(idSector, "7111", "7119", "7110", "", "No existe cuenta 7110");
		updateAccountRangeSalini(idSector, "7211", "7219", "7210", "", "No existe cuenta 7210");
		updateAccountRangeSalini(idSector, "7311", "7319", "7310", "", "No existe cuenta 7310");
		updateAccountRangeSalini(idSector, "7411", "7419", "7410", "", "No existe cuenta 7410");
		updateAccountRangeSalini(idSector, "7511", "7519", "7510", "", "No existe cuenta 7510");
		updateAccountRangeSalini(idSector, "7601", "7654", "7600", "", "No existe cuenta 7600");
		if (idSector == 2) {
			updateAccountRangeSalini(idSector, "8110", "8130", "8100", "", "No existe cuenta 8100");
		} else {
			updateAccountRangeSalini(idSector, "8110", "8150", "8100", "", "No existe cuenta 8100");
		}

		updateAccountRangeSalini(idSector, "8211", "8219", "8210", "", "No existe cuenta 8210");
		updateAccountRangeSalini(idSector, "8221", "8229", "8220", "", "No existe cuenta 8220");
		if (idSector == 2) {
			updateAccountRangeSalini(idSector, "8231", "8239", "8230", "", "No existe cuenta 8230");
		}
		updateAccountRangeSalini(idSector, "8241", "8249", "8240", "", "No existe cuenta 8240");
		updateAccountRangeSalini(idSector, "8251", "8259", "8250", "", "No existe cuenta 8250");
		if (idSector == 1) {
			updateAccountRangeSalini(idSector, "8271", "8279", "8270", "", "No existe cuenta 8250");
		}
		updateAccountRangeSalini(idSector, "9100", "9100", "9000", "", "No existe cuenta 9100");
		updateDigitAccounts(idSector);
	}

	/**
	 * Find and update.
	 *
	 * @param idSector
	 *            the id sector
	 * @param account
	 *            the account
	 * @param salini
	 *            the salini
	 * @param message
	 *            the message
	 */
	private void findAndUpdate(Long idSector, String account, Double salini, String message) {
		Cuenta c = cuentaRepository.findFirstByIdsectorAndCuenta(idSector, account);
		if (c == null) {
			displayInfoMessage(message);
		} else {
			accountService.updateSaliniToAccounts(Arrays.asList(c), salini);
		}
	}

	/**
	 * Update 3 digit accounts.
	 *
	 * @param idSector
	 *            the id sector
	 */
	private void update3DigitAccounts(Long idSector) {
		List<Object[]> results = cuentaRepository.getSum3DigitsAccount(idSector, "4000", 0);
		results.stream().forEach((res) -> {
			String account = res[0] == null ? null : res[0] + "0";
			findAndUpdate(idSector, account, ((BigDecimal) res[1]).doubleValue(), "NO EXISTE EL NIVEL 3");
		});
	}

	/**
	 * Update digit accounts.
	 *
	 * @param idSector
	 *            the id sector
	 */
	private void updateDigitAccounts(Long idSector) {
		List<Object[]> results = cuentaRepository.getSum2DigitsAccount(idSector, 3);
		results.stream().forEach((res) -> {
			String account = res[0] == null ? null : res[0] + "00";
			findAndUpdate(idSector, account, ((BigDecimal) res[1]).doubleValue(), "NO EXISTE CUENTA DOBLE CERO");
		});

		results = cuentaRepository.getSum1DigitsAccount(idSector, 2);

		results.stream().forEach((res) -> {
			String account = res[0] == null ? null : res[0] + "000";
			findAndUpdate(idSector, account, ((BigDecimal) res[1]).doubleValue(),
					"NO EXISTE CUENTA DE MAYOR CON TRES CEROS " + account);
		});
	}

	/**
	 * Update account range salini.
	 *
	 * @param idSector
	 *            the id sector
	 * @param minCta
	 *            the min cta
	 * @param maxCta
	 *            the max cta
	 * @param searchCuenta
	 *            the search cuenta
	 * @param scta
	 *            the scta
	 * @param message
	 *            the message
	 */
	private void updateAccountRangeSalini(Long idSector, String minCta, String maxCta, String searchCuenta, String scta,
			String message) {
		BigDecimal salIni = (BigDecimal) cuentaRepository.getSumSalini(idSector, minCta, maxCta, scta)[0];
		Cuenta c = cuentaRepository.findFirstByCuentaAndScuentaAndIdsector(searchCuenta, scta, idSector);
		if (c == null) {
			displayInfoMessage(message);
		} else {
			List<Cuenta> cuentas = new ArrayList<Cuenta>();
			cuentas.add(c);

			accountService.updateSaliniToAccounts(cuentas, salIni.doubleValue());
		}

	}

	public void validatePassword() {
		String keyPas = this.getUserDetails().getIdSector() == 1 ? ConstansEnum.KEY_PASS_ACUM_1.getValue()
				: ConstansEnum.KEY_PASS_ACUM.getValue();
		boolean bandera = this.changePasswordService.passwordisTrue(password, keyPas);
		bDisable = true;
		if (bandera)
			bDisable = false;
		else
			displayInfoMessage("Password incorrecto");
	}

	public TcParametro getTcParametro() {
		return tcParametro;
	}

	public void setTcParametro(TcParametro tcParametro) {
		this.tcParametro = tcParametro;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getbDisable() {
		return bDisable;
	}

	public void setbDisable(Boolean bDisable) {
		this.bDisable = bDisable;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}
	
	

}
