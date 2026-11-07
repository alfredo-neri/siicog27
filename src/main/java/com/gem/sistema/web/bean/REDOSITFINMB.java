
package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/*

Object: Edo_SitFin.jrxml y Orden.jrxml

Fecha de Creación                    Autor                             Descripcion                      Versión                        Linea de Codigo

---------------       ---------------------------------     -----------------------------          -----------                     -------------------

10/03/2021            Luis Enrique Longino Nicolas. 		Se ajusta reporte para mostrar firmas     	1.0                             	
															en la misma hoja de cuentas de orden 
															
       
*/
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.dto.EdoSF3211DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class REDOSITFINMB.
 */
@ManagedBean
@ViewScoped
public class REDOSITFINMB extends ReportePeriodos {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private static final String OUTPUT_REPORT_NAME = "ESF";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EDO_SITUACION_FINANCIERA";
	private static final String CLAVE_REPORT = "RPT_EDOSITFIN";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(REDOSITFINMB.class);

	private StreamedContent fileTxt;

	/** The orden. */
	private String orden = "S";
	private Integer numeroFirmas = 4;
	/** The edo SF 3211 DTO. */
	private EdoSF3211DTO edoSF3211DTO;

	/** The edo SF 3211 service. */
	@ManagedProperty("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct rEDOSITFINMB ");
		jasperReporteName = "Edo_SitFin";
		endFilename = jasperReporteName + ".pdf";
		this.mesAnterior = true;
		if (numeroFirmas == 4) {
			tipoPeriodo = MES;
			acumulado = true;
		}
		changePeriodo();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);
		edoSF3211DTO = edoSF3211Service.executeQuery(idSector, getMesInicial(), getMesSelected());

		paramsReport.put("pDia", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		paramsReport.put("p_mes", getMesSelected());
		paramsReport.put("p_nom", conctb.getNomDep());
		paramsReport.put("pImg1", conctb.getImagePathLeft());
		paramsReport.put("pImg2", conctb.getImagePathRigth());
		paramsReport.put("pCampo3", conctb.getAnoemp());
		paramsReport.put("pMesLetra", getNombreMesSelected().toUpperCase());
		paramsReport.put("p_ctaOrden", orden);
		paramsReport.put("p_Idsector", this.getUserDetails().getIdSector());
		paramsReport.put("queryOrden", getQueryOrden(idSector));
		paramsReport.put("queryActivo", getQueryActivo(idSector));
		paramsReport.put("queryPasivo", getQueryPasivo(idSector));
		paramsReport.put("pTotalAnt3211", edoSF3211DTO.getTotalAnt());
		paramsReport.put("pTotalAct3211", edoSF3211DTO.getTotalAct());

		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		System.out.println(paramsReport);
		return paramsReport;
	}

	public void downloadTxt() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(getMesSelected(), null, this.getUserDetails().getIdSector());
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}

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
		String abonoAnterior = getSumaCargoOAbono(true, "C.ABONOS_");
		String abonoActual = getSumaCargoOAbono(false, "C.ABONOS_");
		String cargoAnterior = getSumaCargoOAbono(true, "C.CARGOS_");
		String cargoActual = getSumaCargoOAbono(false, "C.CARGOS_");

		query.append(" SELECT * FROM ( ").append(
				"	SELECT  SUBSTR(C.CUENTA,1,2)  GRUPO, C.STACTA NATCTA, C.NOMCTA, C.CUENTA,  C.SALINI, C.SALINIANT, ")
				.append(cargoAnterior).append(" CARGOS_ANT,	 ").append(abonoAnterior).append(" ABONOS_ANT, ")
				.append(abonoActual).append("  ABONOS_ACT, ").append(cargoActual).append(" CARGOS_ACT ")
				.append(" FROM  CUENTA C ")
				.append(" WHERE C.CUENTA BETWEEN '7600' AND '8999' AND C.SCTA = '' AND C.NOTCTA = 0 AND C.IDSECTOR =")
				.append(sector)
				.append("    GROUP BY C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, C.SALINIANT, SUBSTR(C.CUENTA,1,2) ) T1 ")
				.append(" WHERE SALINIANT <> 0 OR  SALINI <> 0 OR CARGOS_ANT<>0 OR ABONOS_ANT <>0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0 ")
				.append(" ORDER BY CUENTA ");
		System.out.println("orden: " + query.toString());
		return query.toString();
	}

	private String getQueryPasivo(Integer sector) {
		StringBuilder query = new StringBuilder();
		String abonoAnterior = getSumaCargoOAbono(true, "C.ABONOS_");
		String abonoActual = getSumaCargoOAbono(false, "C.ABONOS_");
		String cargoAnterior = getSumaCargoOAbono(true, "C.CARGOS_");
		String cargoActual = getSumaCargoOAbono(false, "C.CARGOS_");
		query.append(" SELECT * FROM ( ")
				.append("	SELECT SUBSTR(C.CUENTA,1,2) GRUPO,  MA.NATCTA, C.NOMCTA, C.CUENTA, C.SALINI, ")
				.append(cargoAnterior).append("		CARGOS_ANT, ").append(abonoAnterior).append("		ABONOS_ANT, ")
				.append(abonoActual).append("		ABONOS_ACT, ").append(cargoActual).append("		CARGOS_ACT ")
				.append("    FROM   MAYCTA MA INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA  ")
				.append("    WHERE  C.CUENTA >= '2000' AND C.CUENTA <= '3321' AND C.SCTA ='' AND C.IDSECTOR = ")
				.append(sector).append("    	AND (SUBSTR(C.CUENTA,3,2) ='00' OR SUBSTR(C.CUENTA,4,1) <>'0') ")
				.append("    GROUP BY MA.NATCTA, C.NOMCTA, C.CUENTA, C.SALINI, SUBSTR(C.CUENTA,1,2) ) T1 ")
				.append(" WHERE (SALINI <> 0 OR CARGOS_ANT<>0 OR ABONOS_ANT <>0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0 ) OR CUENTA = '3211' ")
				.append(" ORDER BY CUENTA ");
		System.out.println("pasivo: " + query.toString());
		return query.toString();
	}

	private String getQueryActivo(Integer sector) {
		StringBuilder query = new StringBuilder();
		String abonoAnterior = getSumaCargoOAbono(true, "C.ABONOS_");
		String abonoActual = getSumaCargoOAbono(false, "C.ABONOS_");
		String cargoAnterior = getSumaCargoOAbono(true, "C.CARGOS_");
		String cargoActual = getSumaCargoOAbono(false, "C.CARGOS_");

		query.append(" SELECT * FROM ( ")
				.append(" 	SELECT SUBSTR(C.CUENTA,1,2)GRUPO,MA.NATCTA, C.NOMCTA, C.CUENTA, C.SALINI, ")
				.append(cargoAnterior).append(" CARGOS_ANT, ").append(abonoAnterior).append(" 		ABONOS_ANT, ")
				.append(abonoActual).append(" 		ABONOS_ACT, ").append(cargoActual).append(" 		CARGOS_ACT ")
				.append("	FROM MAYCTA MA	INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA  ")
				.append(" 	WHERE  C.CUENTA >= '1000' AND C.CUENTA <= '1293' AND C.SCTA = '' AND C.IDSECTOR = ")
				.append(sector).append(" 		AND (SUBSTR(C.CUENTA,3,2) ='00' OR SUBSTR(C.CUENTA,4,1) <>'0') ")
				.append(" 	GROUP BY MA.NATCTA, C.NOMCTA, C.CUENTA,  C.SALINI, SUBSTR(C.CUENTA,1,2)) T1 ")
				.append(" WHERE SALINI <> 0 OR CARGOS_ANT<>0 OR ABONOS_ANT <>0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0 ")
				.append(" ORDER BY CUENTA ");
		System.out.println("activo: " + query.toString());
		return query.toString();
	}

	private String getSumaCargoOAbono(Boolean anterior, String nombre) {
		String suma = "";
		if (anterior) {
			if (getMesSelected() == 1)
				return " 0 ";
			else {
				for (int i = getMesInicial(); i < getMesSelected(); i++) {
					suma += "SUM(DECIMAL (" + nombre + i + ",20,2)) +";
				}
			}
		} else {
			for (int i = getMesInicial(); i <= getMesSelected(); i++) {
				suma += "SUM(DECIMAL (" + nombre + i + ",20,2)) +";
			}
		}
		return suma.substring(0, suma.length() - 1);
	}

	Map<String, Object> out;
	InputStream stream = null;
	public StreamedContent getFileTxt() {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameter.addValue("i_query_activo", this.getQueryActivo(sector));
		parameter.addValue("i_query_pasivo", this.getQueryPasivo(sector));
		parameter.addValue("i_query_orden", this.getQueryOrden(sector));
		parameter.addValue("i_query_3211", this.getQueryActivo(sector));
		parameter.addValue("i_filename", filename.substring(0, filename.length() - 4));

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		
		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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

	public Integer getNumeroFirmas() {
		return numeroFirmas;
	}

	public void setNumeroFirmas(Integer numeroFirmas) {
		this.numeroFirmas = numeroFirmas;
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

}
