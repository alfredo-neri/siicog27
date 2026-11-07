package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ParamsRF009118DTO;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009118MB.
 */
@ManagedBean
@ViewScoped
public class RF009118MB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_ESTADO_EGRESOSD";

	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Integer firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The report by PL. */
	@ManagedProperty(value = "#{baseDirectReportByPL}")
	private BaseDirectReportByPL reportByPL;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Gets the report by PL.
	 *
	 * @return the report by PL
	 */
	public BaseDirectReportByPL getReportByPL() {
		return reportByPL;
	}

	/**
	 * Sets the report by PL.
	 *
	 * @param reportByPL the new report by PL
	 */
	public void setReportByPL(BaseDirectReportByPL reportByPL) {
		this.reportByPL = reportByPL;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009118MB ");
		jasperReporteName = "RF009_1_18";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		GemUser user = this.getUserDetails();
		TrPuestoFirma firma = null;
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		paramsReport.put("USUARIO", user.getUsername());
		paramsReport.put("mes", mes);
		paramsReport.put("nFirmas", firmas);
		paramsReport.put("imagePath", conctb.getImagePathLeft());

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		paramsReport.put("pImg1", conctb.getImagePathRigth());

		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		endFilename = getNameFileReport(mes, conctb, reporte);
		paramsReport.put("entidadName", this.getUserDetails().getMunicipio());
		paramsReport.put("entidadRfc", conctb.getRfc());
		paramsReport.put("year", conctb.getAnoemp());

		return paramsReport;
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
	 * Gets the file txt using link.
	 *
	 * @return the file txt using link
	 */
	public StreamedContent getFileTxtUsingLink() {
		StreamedContent streamedContent = null;
		try {
			GemUser user = getUserDetails();

			ParamsRF009118DTO params = new ParamsRF009118DTO();
			params.setUser(user.getUsername());
			params.setFirmas(firmas);
			params.setMes(mes);
			Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
			params.setMunicipio(conctb.getNomDep());

			reportByPL.callSpService.getFile("SP_GENERA_TXT_RF_009_1_18", params);

			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = reportByPL.sendFileByStream(params.getResultFile());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Ha ocurrido un error al generar el reporte");
		}
		return streamedContent;
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
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Integer getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Integer firmas) {
		this.firmas = firmas;
	}

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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
