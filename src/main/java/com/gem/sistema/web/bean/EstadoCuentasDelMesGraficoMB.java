package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.google.common.base.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCuentasDelMesGraficoMB.
 */
@ManagedBean
@ViewScoped
public class EstadoCuentasDelMesGraficoMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The mes. */
	private Integer mes;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The order by. */
	private String orderBy;

	/** The selected cuenta. */
	private Cuenta selectedCuenta;

	/** The cuenta mayor. */
	private String cuentaMayor;

	/** The nombre cuenta mayor. */
	private String nombreCuentaMayor;

	/** The Constant SECTOR_MUNICIPAL. */
	private static final Integer SECTOR_MUNICIPAL = 1;

	/** The Constant SECTOR_CENTRAL. */
	private static final Integer SECTOR_CENTRAL = 2;

	/** The bandera M. */
	private boolean banderaM = Boolean.FALSE;

	/** The bandera C. */
	private boolean banderaC = Boolean.FALSE;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct EstadoCuentasMesGraficoMB ");
		jasperReporteName = "EstadoCuentasDelMesGrafico";
		endFilename = jasperReporteName + ".pdf";

		cuenta = new Cuenta();
		orderBy = "FECPOL";

		if (this.getUserDetails().getIdSector() == SECTOR_MUNICIPAL) {
			banderaM = Boolean.TRUE;
		} else if (this.getUserDetails().getIdSector() == SECTOR_CENTRAL) {
			banderaM = Boolean.FALSE;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Firmas firmas = firmasRepository.findAllByIdsector(idSector);
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		if (null == cuenta.getScuenta())
			cuenta.setScuenta(StringUtils.EMPTY);

		if (null == cuenta.getSscuenta())
			cuenta.setSscuenta(StringUtils.EMPTY);

		if (null == cuenta.getSsscuenta())
			cuenta.setSsscuenta(StringUtils.EMPTY);

		if (null == cuenta.getSssscuenta())
			cuenta.setSssscuenta(StringUtils.EMPTY);

		parameters.put("pMes", mes);
		parameters.put("pImagenPath", getUserDetails().getPathImgCab1());
		parameters.put("pCampo1", firmas.getCampo1());
		parameters.put("pCampo2", firmas.getCampo2());
		parameters.put("pAnio", firmas.getCampo3());
		parameters.put("pL3", idSector == 1 ? firmas.getL3() : firmas.getL27());
		parameters.put("pN3", idSector == 1 ? firmas.getN3() : firmas.getN27());
		parameters.put("pL4", firmas.getL4());
		parameters.put("pN4", firmas.getN4());
		parameters.put("pL5", firmas.getL5());
		parameters.put("pN5", firmas.getN5());
		parameters.put("pOrderByExtenso",
				orderBy.equals("FECPOL") ? "FECHA" : (orderBy.equals("REFPOL") ? "REFERENCIA" : "MES"));
		parameters.put("pQuery",
				this.generateSQL(cuenta.getCuenta(), cuenta.getScuenta(), cuenta.getSscuenta(), cuenta.getSsscuenta(),
						cuenta.getSssscuenta(), this.generateCargosAbonos(mes), orderBy, firmas.getCampo3(), mes,
						idSector));

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	private String generateSQL(String cuenta, String scta, String sscta, String ssscta, String sssscta,
			String cargosAbonos, String order, Integer anio, Integer mes, Integer sector) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT DISTINCT CU.NOMCTA, CU.XNICTA, CU.CUENTA, CU.SCTA, CU.SSCTA, CU.SSSCTA, CU.SSSSCTA, ")
				.append(cargosAbonos).append(" CU.STACTA, DECODE(PO.MESPOL, 1, 1, PO.MESPOL - 1) MESPOL, 1 CONPOL, ")
				.append(anio)
				.append(" ANOPOL, 1 RENPOL, CU.SALINI CANPOL, 0 CANPOLH, '*-*-*-*' CONCEP, '' DN, 1 REFPOL, '01/01/'||")
				.append(anio)
				.append(" FECPOL,'E' TIPPOL ,'' CAPPOL FROM CUENTA CU LEFT JOIN POLIDE PO ON CU.CUENTA = NVL(PO.CUENTA,'') ")
				.append("AND CU.SCTA = NVL(PO.SCTA,'') AND CU.SSCTA = NVL(PO.SSCTA,'') AND CU.SSSCTA = NVL(PO.SSSCTA,'') ")
				.append("AND CU.SSSSCTA = NVL(PO.SSSSCTA,'') AND CU.IDSECTOR = PO.IDSECTOR AND PO.MESPOL = ")
				.append(mes).append(" WHERE 1=1 AND	CU.CUENTA = ").append(cuenta)
				.append(" AND CU.NIVCTA = 'S' AND CU.IDSECTOR = ").append(sector).append(" AND ('").append(scta)
				.append("' IS NULL OR '").append(scta).append("' = '' OR CU.SCTA = '").append(scta)
				.append("') " + " AND ('").append(sscta).append("' IS NULL OR '").append(sscta)
				.append("' = '' OR CU.SSCTA = '").append(sscta).append("') " + " AND ('").append(ssscta)
				.append("' IS NULL OR '").append(ssscta).append("' = '' OR CU.SSSCTA = '").append(ssscta)
				.append("') " + "	AND ('").append(sssscta).append("' IS NULL OR '").append(sssscta)
				.append("' = '' OR CU.SSSSCTA = '").append(sssscta).append("') UNION ALL ")
				.append("SELECT CUENTA.NOMCTA, CUENTA.XNICTA, CUENTA.CUENTA, CUENTA.SCTA, CUENTA.SSCTA, ")
				.append("CUENTA.SSSCTA, CUENTA.SSSSCTA, CUENTA.SALINI, CUENTA.STACTA, POLIDE.MESPOL, ")
				.append("POLIDE.CONPOL, POLIDE.ANOPOL, POLIDE.RENPOL, NVL(POLIDE.CANPOL, 0) CANPOL, ")
				.append("NVL(POLIDE.CANPOLH, 0) CANPOLH, POLIDE.CONCEP, POLIDE.DN, POLIDE.REFPOL, ")
				.append("POLIEN.FECPOL, POLIDE.TIPPOL,POLIEN.CAPPOL FROM CUENTA ")
				.append("LEFT JOIN POLIDE ON CUENTA.CUENTA  = POLIDE.CUENTA AND CUENTA.SCTA = NVL(POLIDE.SCTA,'') ")
				.append("AND CUENTA.SSCTA   = NVL(POLIDE.SSCTA,'') AND CUENTA.SSSCTA  = NVL(POLIDE.SSSCTA,'') ")
				.append("AND CUENTA.SSSSCTA = NVL(POLIDE.SSSSCTA,'') AND CUENTA.IDSECTOR = POLIDE.IDSECTOR ")
				.append("AND POLIDE.MESPOL = ").append(mes).append(" LEFT  JOIN POLIEN ON POLIDE.TIPPOL=POLIEN.TIPPOL ")
				.append("AND POLIDE.MESPOL=POLIEN.MESPOL AND POLIDE.CONPOL= POLIEN.CONPOL AND POLIDE.ANOPOL=POLIEN.ANOPOL ")
				.append("AND POLIDE.IDSECTOR = POLIEN.IDSECTOR WHERE 1 = 1  AND CUENTA.CUENTA = ").append(cuenta)
				.append(" AND CUENTA.NIVCTA = 'S' AND CUENTA.IDSECTOR = ").append(sector).append("	AND ('")
				.append(scta).append("' IS NULL OR '").append(scta).append("' = '' OR CUENTA.SCTA = '").append(scta)
				.append("') " + "	AND ('").append(sscta).append("' IS NULL OR '").append(sscta)
				.append("' = '' OR CUENTA.SSCTA = '").append(sscta).append("') " + "	AND ('").append(ssscta)
				.append("' IS NULL OR '").append(ssscta).append("' = '' OR CUENTA.SSSCTA = '").append(ssscta)
				.append("') " + "	AND ('").append(sssscta).append("' IS NULL OR '").append(sssscta)
				.append("' = '' OR CUENTA.SSSSCTA = '").append(sssscta)
				.append("') " + "ORDER BY  CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, ").append(order).append(", DN");

		return sql.toString();
	}

	/**
	 * Generate cargos abonos.
	 *
	 * @param mes the mes
	 * @return the string
	 */
	public String generateCargosAbonos(Integer mes) {
		StringBuilder cadena = new StringBuilder();
		StringBuilder salini = new StringBuilder();
		StringBuilder acredora = new StringBuilder();
		Integer i = 1;

		if (mes != 1) {
			salini.append("DECODE(STACTA,'D' ,CU.SALINI ");
			while (i < mes) {
				cadena.append(" + CU.CARGOS_" + i + " - CU.ABONOS_" + i);
				acredora.append(" + CU.ABONOS_" + i + " - CU.CARGOS_" + i);

				i++;
			}
			salini.append(cadena).append(" ,'A',CU.SALINI ").append(acredora).append(") SALINI, ");
		} else {
			salini.append("CU.SALINI + 0 - 0 SALINI, ");
		}

		return salini.toString();
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = cuenta.getScuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setScuenta(StringUtils.leftPad(value, 10, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSscuenta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSsscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSsscuenta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSssscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSssscuenta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}
	}

	/**
	 * Todos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean todosVacios() {
		return Strings.isNullOrEmpty(cuenta.getCuenta()) && Strings.isNullOrEmpty(cuenta.getScuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta()) && Strings.isNullOrEmpty(cuenta.getSscuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta());
	}

	/**
	 * Validate cuenta.
	 *
	 * @return true, if successful
	 */
	private boolean validateCuenta() {
		List<Cuenta> lista = todosVacios() ? new ArrayList<Cuenta>()
				: cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuenta.getCuenta(), cuenta.getScuenta(),
						cuenta.getSscuenta(), cuenta.getSsscuenta(), cuenta.getSssscuenta(),
						((Integer) getUserDetails().getIdSector()).longValue());
		return !lista.isEmpty();
	}

	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		/*
		 * if (!validateCuenta()) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
		 * "La cuenta no existe"); FacesContext.getCurrentInstance().addMessage(null,
		 * message);
		 * RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();")
		 * ; } else {
		 */if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			createFilePdfInline();

			RequestContext.getCurrentInstance()
					.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '45em');");
		}
		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		// }
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	 * Gets the selected cuenta.
	 *
	 * @return the selected cuenta
	 */
	public Cuenta getSelectedCuenta() {
		return selectedCuenta;
	}

	/**
	 * Sets the selected cuenta.
	 *
	 * @param selectedCuenta the new selected cuenta
	 */
	public void setSelectedCuenta(Cuenta selectedCuenta) {
		this.selectedCuenta = selectedCuenta;
	}

	/**
	 * Gets the cuenta mayor.
	 *
	 * @return the cuenta mayor
	 */
	public String getCuentaMayor() {
		return cuentaMayor;
	}

	/**
	 * Sets the cuenta mayor.
	 *
	 * @param cuentaMayor the new cuenta mayor
	 */
	public void setCuentaMayor(String cuentaMayor) {
		this.cuentaMayor = cuentaMayor;
	}

	/**
	 * Gets the nombre cuenta mayor.
	 *
	 * @return the nombre cuenta mayor
	 */
	public String getNombreCuentaMayor() {
		return nombreCuentaMayor;
	}

	/**
	 * Sets the nombre cuenta mayor.
	 *
	 * @param nombreCuentaMayor the new nombre cuenta mayor
	 */
	public void setNombreCuentaMayor(String nombreCuentaMayor) {
		this.nombreCuentaMayor = nombreCuentaMayor;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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

	/**
	 * Checks if is bandera M.
	 *
	 * @return true, if is bandera M
	 */
	public boolean isBanderaM() {
		return banderaM;
	}

	/**
	 * Sets the bandera M.
	 *
	 * @param banderaM the new bandera M
	 */
	public void setBanderaM(boolean banderaM) {
		this.banderaM = banderaM;
	}

	/**
	 * Checks if is bandera C.
	 *
	 * @return true, if is bandera C
	 */
	public boolean isBanderaC() {
		return banderaC;
	}

	/**
	 * Sets the bandera C.
	 *
	 * @param banderaC the new bandera C
	 */
	public void setBanderaC(boolean banderaC) {
		this.banderaC = banderaC;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
