package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.dto.EdoSF3211DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCambiosSFBCMB.
 */
/**
 * @author Alfredo
 *
 */
@ManagedBean(name = "estadoCambiosSFBCMB")
@ViewScoped
public class EstadoCambiosSFBCMB extends ReportePeriodos {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EDO_CAMBIOS_SITUACION_FINANCIERA";
	private static final String CLAVE_REPORT = "RPT_EDO_CAMBIOS_SITUACION_FINANCIERA";

	private StreamedContent fileTxt;
	
	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@ManagedProperty(value = "#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The estado cambios SFBCMB service. */
	@ManagedProperty(value = "#{estadoCambiosSFBCMBService}")
	private EstadoCambiosSFBCMBService estadoCambiosSFBCMBService;

	@ManagedProperty("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;
	
	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.jasperReporteName = "EstadoCambiosSitFin";
		this.endFilename = this.jasperReporteName + ".pdf";
		this.mesAnterior = true;
		this.acumulado = true;
		changePeriodo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(getMesSelected(), conctb, reporte);

		String query3211 = estadoCambiosSFBCMBService.generaQuery3211(this.getUserDetails().getIdSector(),
				getMesInicial(), getMesSelected());
		System.out.println("::::->" + query3211);


		parameters.put("year", conctb.getAnoemp());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("sqlActivo",
				estadoCambiosSFBCMBService.generaQueryActivo(sector, getMesInicial(), getMesSelected()));
		parameters.put("sqlPasivo",
				estadoCambiosSFBCMBService.generaQueryPasivo(sector, getMesInicial(), getMesSelected()));
		parameters.put("sqlHacienda",
				estadoCambiosSFBCMBService.generaQueryHacienda(sector, getMesInicial(), getMesSelected(), Boolean.FALSE));
		parameters.put("sql3211", query3211);
		parameters.put("imageLeft", conctb.getImagePathLeft());
		parameters.put("imageRigth", conctb.getImagePathRigth());
		parameters.put("mesFinalName", getNombreMesSelected().toUpperCase());
		parameters.put("mesInicialName", getNombreMesInicial().toUpperCase());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.createFilePdfInline();
		}
	}

	Map<String, Object> out;
	InputStream stream = null;
	public StreamedContent getFileTxt() {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(getMesSelected(), conctb, reporte);
		
		parameter.addValue("i_query_3211", estadoCambiosSFBCMBService.generaQuery3211(sector, getMesInicial(), getMesSelected()));
		parameter.addValue("i_query_activo", estadoCambiosSFBCMBService.generaQueryActivo(sector, getMesInicial(), getMesSelected()));
		parameter.addValue("i_query_pasivo", estadoCambiosSFBCMBService.generaQueryPasivo(sector, getMesInicial(), getMesSelected()));
		parameter.addValue("i_query_hacienda", estadoCambiosSFBCMBService.generaQueryHacienda(sector, getMesInicial(), getMesSelected(), Boolean.FALSE));
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

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	/**
	 * Gets the estado cambios SFBCMB service.
	 *
	 * @return the estado cambios SFBCMB service
	 */
	public EstadoCambiosSFBCMBService getEstadoCambiosSFBCMBService() {
		return estadoCambiosSFBCMBService;
	}

	/**
	 * Sets the estado cambios SFBCMB service.
	 *
	 * @param estadoCambiosSFBCMBService the new estado cambios SFBCMB service
	 */
	public void setEstadoCambiosSFBCMBService(EstadoCambiosSFBCMBService estadoCambiosSFBCMBService) {
		this.estadoCambiosSFBCMBService = estadoCambiosSFBCMBService;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public EdoSF3211Service getEdoSF3211Service() {
		return edoSF3211Service;
	}

	public void setEdoSF3211Service(EdoSF3211Service edoSF3211Service) {
		this.edoSF3211Service = edoSF3211Service;
	}

}
