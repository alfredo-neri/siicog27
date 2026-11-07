package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.google.common.base.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class MovimientosDeudaMB.
 */
@ManagedBean
@ViewScoped
public class MovimientosDeudaMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** The mes. */
	private Integer mes;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The clasificacion. */
	private String clasificacion;

	/** The selected cuenta. */
	private Cuenta selectedCuenta;

	/** The cuentas. */
	private List<Cuenta> cuentas;

	/** The cuenta mayor. */
	private String cuentaMayor;

	/** The nombre cuenta mayor. */
	private String nombreCuentaMayor;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct MovimientosDeudaMB ");
		// reportId = 2;
		jasperReporteName = "MovimientosDeuda";
		endFilename = jasperReporteName + ".pdf";

		cuenta = new Cuenta();
		clasificacion = "DEL_MES";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("pQuery", this.generateQuery());
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

	/**
	 * Buscar.
	 */
	public void buscar() {
		cuentas = cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuentaMayor, null, null, null, null,
				((Integer) getUserDetails().getIdSector()).longValue());
	}

	private String generateQuery() {
		StringBuilder sql = new StringBuilder();
		String periodo = StringUtils.EMPTY;
		String niveles = StringUtils.EMPTY;

		if (clasificacion.equals("AL_MES")) {
			periodo = " AND MESPOL <= " + mes;
		} else {
			periodo = " AND MESPOL = " + mes;
		}

		if (cuenta.getSssscuenta() != null) {
			niveles = "AND SCTA = '" + cuenta.getScuenta() + "' AND SSCTA = '" + cuenta.getSscuenta()
					+ "' AND SSSCTA = '" + cuenta.getSsscuenta() + "' AND SSSSCTA = '" + cuenta.getSssscuenta() + "'";
		} else if (cuenta.getSsscuenta() != null) {
			niveles = "AND SCTA = '" + cuenta.getScuenta() + "' AND SSCTA = '" + cuenta.getSscuenta()
					+ "' AND SSSCTA = '" + cuenta.getSsscuenta() + "'";
		} else if (cuenta.getSscuenta() != null) {
			niveles = "AND SCTA = '" + cuenta.getScuenta() + "' AND SSCTA = '" + cuenta.getSscuenta() + "' ";
		} else if (cuenta.getScuenta() != null) {
			niveles = "AND SCTA = '" + cuenta.getScuenta() + "' ";
		}

		sql.append("SELECT CUENTA||SCTA||SSCTA||SSSCTA||SSSSCTA GRUPO, CUENTA, ")
				.append("SCTA, SSCTA, SSSCTA, SSSSCTA, CONCEP, TIPPOL, CONPOL, MESPOL, ")
				.append("ANOPOL, RENPOL, REFPOL, CANPOL, CANPOLH").append("	FROM POLIDE ").append("WHERE IDSECTOR = ")
				.append(this.getUserDetails().getIdSector()).append(" AND CUENTA = '").append(cuenta.getCuenta())
				.append("' ").append(niveles).append(periodo).append(" ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA");

		return sql.toString();
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = cuenta.getScuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setScuenta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSscuenta(StringUtils.leftPad(value, 20, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSsscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSsscuenta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSssscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSssscuenta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}

		obtenerDatosCuentaInformada();
	}

	/**
	 * Obtener datos cuenta informada.
	 */
	private void obtenerDatosCuentaInformada() {
		if (!todosVacios()) {
			List<Cuenta> listaCuenta = cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuenta.getCuenta(),
					cuenta.getScuenta(), cuenta.getSscuenta(), cuenta.getSsscuenta(), cuenta.getSssscuenta(), 
					((Integer) getUserDetails().getIdSector()).longValue());
			Cuenta cta = listaCuenta.isEmpty() ? null : listaCuenta.get(0);
			if (Objects.nonNull((cta))) {
				cuenta.setNomcta(cta.getNomcta());
				;
			} else {
				cuenta.setNomcta("");
			}
		}
	}

	/**
	 * On row select.
	 */
	public void onRowSelect() {
		if (Objects.nonNull(selectedCuenta)) {
			this.cuenta.setCuenta(selectedCuenta.getCuenta());
			this.cuenta.setScuenta(selectedCuenta.getScuenta());
			this.cuenta.setSscuenta(selectedCuenta.getSscuenta());
			this.cuenta.setSsscuenta(selectedCuenta.getSsscuenta());
			this.cuenta.setSssscuenta(selectedCuenta.getSssscuenta());
		}
	}

	/**
	 * Todos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean todosVacios() {
		return Strings.isNullOrEmpty(cuenta.getCuenta()) || Strings.isNullOrEmpty(cuenta.getScuenta());
	}

	/**
	 * Creates the file txt inline validate.
	 */
	public void createFileTxtInlineValidate() {
		RequestContext.getCurrentInstance().execute("$(PrimeFaces.escapeClientId('form1:donwloadTxt')).click();");
	}


	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		// if (validateCuenta()) {
		createFilePdfInline();
		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		RequestContext.getCurrentInstance()
				.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '57em');");
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
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<Cuenta> getCuentas() {
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
	 * Gets the clasificacion.
	 *
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * Sets the clasificacion.
	 *
	 * @param clasificacion the new clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
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

}
