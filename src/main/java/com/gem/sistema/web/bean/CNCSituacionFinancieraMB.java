
package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.dto.EdoSF3211DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

@ManagedBean
@ViewScoped
public class CNCSituacionFinancieraMB extends ReportePeriodos {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final Logger LOGGER = LoggerFactory.getLogger(CNCSituacionFinancieraMB.class);
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC";

	private String labelPeriodo;
	private String orden = "S";
	private EdoSF3211DTO edoSF3211DTO;

	/** The edo SF 3211 service. */
	@ManagedProperty("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct CNCSituacionFinancieraMB ");
		jasperReporteName = "CNC_SituacionFinanciera";
		endFilename = jasperReporteName + ".pdf";
//		this.tiposPeridos.remove(2);
//		this.tiposPeridos.remove(3);
		this.mesAnterior = true;
		tipoPeriodo = TRIMESTRE;
		acumulado = true;
		changePeriodo();

	}

	@Override
	public void changePeriodo() {
		super.changePeriodo();
		switch (this.periodos.size()) {
		case 12:
			this.labelPeriodo = "Mes: ";
			break;
		case 2:
			this.labelPeriodo = "Semestre: ";
			break;
		case 4:
			this.labelPeriodo = "Trimestre: ";
			break;
		default:
			this.labelPeriodo = "Periodo: ";
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport("CONAC-EstadoSitFin", getMesSelected(), conctb, reporte);
		edoSF3211DTO = edoSF3211Service.executeQuery(idSector, getMesInicial(), getMesSelected());

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		paramsReport.put("mesLetra", getNombreMesSelected().toUpperCase());
		paramsReport.put("ctasOrden", orden);
		paramsReport.put("imagenPathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagenPathRight", conctb.getImagePathRigth());
		paramsReport.put("nombreDependencia", conctb.getNomDep());
		paramsReport.put("periodoActual3211", edoSF3211DTO.getTotalAct());
		paramsReport.put("queryActivo", getQueryActivo(idSector));
		paramsReport.put("queryPasivo", getQueryPasivo(idSector));
		paramsReport.put("queryOrden", getQueryOrden(idSector));
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		
		return paramsReport;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(getMesSelected(), null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

	private String getQueryOrden(Integer sector) {
		StringBuilder query = new StringBuilder();
		String abonoActual = getSumaCargoOAbono(false, "C.ABONOS_");
		String cargoActual = getSumaCargoOAbono(false, "C.CARGOS_");
		String abonoAnterior = getSumaCargoOAbono(false, "A.ABONOS_");
		String cargoAnterior = getSumaCargoOAbono(false, "A.CARGOS_");

		query.append(" SELECT * FROM ( ").append(
				"	SELECT  SUBSTR(C.CUENTA,1,2)  GRUPO, C.STACTA, C.NOMCTA, C.CUENTA,  C.SALINI, A.SDO_INICIAL,  ")
				.append(abonoAnterior).append(" ABONOS_ANT, ").append(cargoAnterior).append(" CARGOS_ANT, ")
				.append(abonoActual).append(" ABONOS_ACT, ").append(cargoActual).append(" CARGOS_ACT ")
				.append("	FROM CUENTA C ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR A ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND ")
				.append(" C.SSCTA = A.SSCTA AND C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA ")
				.append(" WHERE C.CUENTA BETWEEN '7600' AND '8999' AND C.SCTA = '' AND C.NOTCTA = 0 AND C.IDSECTOR =")
				.append(sector).append(" GROUP BY C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, A.SDO_INICIAL, SUBSTR(C.CUENTA,1,2) ) T1 ")
				.append(" WHERE SDO_INICIAL <> 0 OR CARGOS_ANT <> 0 OR ABONOS_ANT <> 0 OR SALINI <> 0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0 ")
				.append(" ORDER BY CUENTA ");
		System.out.println("Orden: " + query.toString());
		return query.toString();
	}

	private String getQueryPasivo(Integer sector) {
		StringBuilder query = new StringBuilder();
		String abonoActual = getSumaCargoOAbono(false, "C.ABONOS_");
		String cargoActual = getSumaCargoOAbono(false, "C.CARGOS_");
		String abonoAnterior = getSumaCargoOAbono(false, "A.ABONOS_");
		String cargoAnterior = getSumaCargoOAbono(false, "A.CARGOS_");

		query.append(" SELECT * FROM ( ").append(
				"	SELECT DECODE(SUBSTR(C.CUENTA,1,1) , 2, SUBSTR(C.CUENTA,1,2), SUBSTR(C.CUENTA,1,1)) GRUPO,  C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, A.SDO_INICIAL, ")
				.append(abonoAnterior).append(" ABONOS_ANT, ").append(cargoAnterior).append(" CARGOS_ANT, ")
				.append(abonoActual).append(" ABONOS_ACT, ").append(cargoActual).append(" CARGOS_ACT ")
				.append("	FROM CUENTA C ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR A ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND ")
				.append(" C.SSCTA = A.SSCTA AND C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA ")
				.append(" WHERE  C.CUENTA >= '2000' AND C.CUENTA <= '3321' AND C.SCTA ='' AND C.IDSECTOR = ")
				.append(sector).append(" AND SUBSTR(C.CUENTA,4,1) <>'0' ")
				.append(" GROUP BY C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, A.SDO_INICIAL, SUBSTR(C.CUENTA,1,2) ) T1 ")
				.append(" WHERE (SALINI <> 0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0 ) OR CUENTA = '3211' ")
				.append(" ORDER BY CUENTA ");
		System.out.println("Pasivo: " + query.toString());
		return query.toString();
	}

	private String getQueryActivo(Integer sector) {
		StringBuilder query = new StringBuilder();
		String abonoActual = getSumaCargoOAbono(false, "C.ABONOS_");
		String cargoActual = getSumaCargoOAbono(false, "C.CARGOS_");
		String abonoAnterior = getSumaCargoOAbono(false, "A.ABONOS_");
		String cargoAnterior = getSumaCargoOAbono(false, "A.CARGOS_");

		query.append(" SELECT * FROM ( ")
				.append(" SELECT SUBSTR(C.CUENTA,1,2)GRUPO, C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, A.SDO_INICIAL, ")
				.append(abonoAnterior).append(" ABONOS_ANT, ").append(cargoAnterior).append(" CARGOS_ANT, ")
				.append(abonoActual).append(" ABONOS_ACT, ").append(cargoActual).append(" CARGOS_ACT ")
				.append(" FROM CUENTA C ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR A ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND ")
				.append(" C.SSCTA = A.SSCTA AND C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA ")
				.append(" WHERE  C.CUENTA >= '1000' AND C.CUENTA <= '1293' AND C.SCTA = '' AND C.IDSECTOR = ")
				.append(sector).append(" AND SUBSTR(C.CUENTA,4,1) <>'0' ")
				.append(" GROUP BY C.STACTA, C.NOMCTA, C.CUENTA,  C.SALINI, A.SDO_INICIAL, SUBSTR(C.CUENTA,1,2)) T1 ")
				.append(" WHERE SALINI <> 0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0 ").append(" ORDER BY CUENTA ");
		System.out.println("Activo: " + query.toString());
		return query.toString();
	}

	private String getSumaCargoOAbono(Boolean anterior, String nombre) {
		String suma = "";
		if (anterior) {
			if (getMesSelected() == 1)
				return " 0 ";
			else {
				for (int i = getMesInicial(); i < getMesSelected(); i++) {
					suma += " " + nombre + i + " +";
				}
			}
		} else {
			for (int i = getMesInicial(); i <= getMesSelected(); i++) {
				suma += " " + nombre + i + " +";
			}
		}
		return " SUM(" + suma.substring(0, suma.length() - 1) + ") ";
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden the new orden
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the edo SF 3211 service.
	 *
	 * @return the edo SF 3211 service
	 */
	public EdoSF3211Service getEdoSF3211Service() {
		return edoSF3211Service;
	}

	/**
	 * Sets the edo SF 3211 service.
	 *
	 * @param edoSF3211Service the new edo SF 3211 service
	 */
	public void setEdoSF3211Service(EdoSF3211Service edoSF3211Service) {
		this.edoSF3211Service = edoSF3211Service;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public String getLabelPeriodo() {
		return labelPeriodo;
	}

	public void setLabelPeriodo(String labelPeriodo) {
		this.labelPeriodo = labelPeriodo;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
