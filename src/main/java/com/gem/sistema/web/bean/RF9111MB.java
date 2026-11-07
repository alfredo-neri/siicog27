package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ParamsRF9111DTO;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.web.security.model.GemUser;
import com.roonin.utils.UtilDate;

// TODO: Auto-generated Javadoc
/**
 * The Class RF9111MB.
 */
@Component("rF9111MB")
@Scope("view")
public class RF9111MB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_FLUJO_EFECTIVO";

	/** The mes. */
	private Integer mes;

	/** The descrip mes. */
	private TcMes descripMes;

	/** The report by PL. */
	@Value("#{baseDirectReportByPL}")
	private BaseDirectReportByPL reportByPL;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	private static final String NAME_PROCEDURE = "SP_GENERA_XLS_FLUJO_EFECTIVO";

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

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
	 * Gets the descrip mes.
	 *
	 * @return the descrip mes
	 */
	public TcMes getDescripMes() {
		return descripMes;
	}

	/**
	 * Sets the descrip mes.
	 *
	 * @param descripMes the new descrip mes
	 */
	public void setDescripMes(TcMes descripMes) {
		this.descripMes = descripMes;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF9111MB ");
		jasperReporteName = "rf9111";
		endFilename = jasperReporteName + ".pdf";
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
		
		endFilename = getNameFileReport(mes, conctb, reporte);
		descripMes = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0"));
		
		paramsReport.put("DIA", UtilDate.getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("MES", descripMes.getDescripcion());
		paramsReport.put("ANIO", conctb.getAnoemp());
		paramsReport.put("CAMPO1", conctb.getNomDep());
		paramsReport.put("SECTOR", idSector);
		paramsReport.put("imageLeft", conctb.getImagePathLeft());
		paramsReport.put("imageRigth", conctb.getImagePathRigth());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

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
	 * Gets the file xls using link.
	 *
	 * @return the file xls using link
	 */
	public StreamedContent getFileXlsUsingLink() {
		StreamedContent streamedContent = null;
		try {
			GemUser user = getUserDetails();
			ParamsRF9111DTO params = new ParamsRF9111DTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			reportByPL.callSpService.getFile(NAME_PROCEDURE, params);
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = this.getFileXls();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}
		return streamedContent;
	}

	/**
	 * File pdf using link.
	 */
	public void filePdfUsingLink() {
		try {
			GemUser user = getUserDetails();
			ParamsRF9111DTO params = new ParamsRF9111DTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			reportByPL.callSpService.getFile(NAME_PROCEDURE, params);
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				this.createFilePdfInline();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}

	}

	public StreamedContent getFileTxtUsingLink() {
		StreamedContent streamedContent = null;
		try {
			GemUser user = getUserDetails();
			ParamsRF9111DTO params = new ParamsRF9111DTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			reportByPL.callSpService.getFile(NAME_PROCEDURE, params);
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = this.getFileTxt();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
