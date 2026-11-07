
package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.dto.EdoSF3211DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Class CPLDFEstadoSituacionFinancieraMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date: </strong>20/03/2022 <strong> Changes: </strong> Se
 *           crea reporte<br>
 * @Sector Municipal
 */
@ManagedBean(name = "cPLDFEstadoSituacionFinancieraMB")
@ViewScoped
public class CPLDFEstadoSituacionFinancieraMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_LDF_EDOSITFIN";

	private static final Logger LOGGER = LoggerFactory.getLogger(CPLDFEstadoSituacionFinancieraMB.class);

	private Integer noDecimales;
	private Integer pesos;

	private String orden = "S";

	private EdoSF3211DTO edoSF3211DTO;

	@ManagedProperty("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct rEDOSITFINMB ");

		noDecimales = 2;
		pesos = 1;
		jasperReporteName = "CP_LDF_EstadoSituacionFinanciera";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		// int year = getCurrentYear();

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		edoSF3211DTO = null;//edoSF3211Service.executeQuery(sector, 12);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		paramsReport.put("ctasOrden", orden);
		paramsReport.put("sector", sector);
		paramsReport.put("claveMunicipio", conctb.getClave());
		paramsReport.put("nombreMunicipio", conctb.getNomDep());
		paramsReport.put("pTotalAnt3211", edoSF3211DTO.getTotalAnt());
		paramsReport.put("pTotalAct3211", edoSF3211DTO.getTotalAct());
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("decimalFormat", "%,." + noDecimales + "f");
		paramsReport.put("pesos", pesos);
		paramsReport.put("imagenPathLeft", conctb.getImagePathRigth());
		paramsReport.put("imagenPathRigth", StringUtils.EMPTY);
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return paramsReport;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Integer getNoDecimales() {
		return noDecimales;
	}

	public void setNoDecimales(Integer noDecimales) {
		this.noDecimales = noDecimales;
	}

	public Integer getPesos() {
		return pesos;
	}

	public void setPesos(Integer pesos) {
		this.pesos = pesos;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public EdoSF3211DTO getEdoSF3211DTO() {
		return edoSF3211DTO;
	}

	public void setEdoSF3211DTO(EdoSF3211DTO edoSF3211DTO) {
		this.edoSF3211DTO = edoSF3211DTO;
	}

	public EdoSF3211Service getEdoSF3211Service() {
		return edoSF3211Service;
	}

	public void setEdoSF3211Service(EdoSF3211Service edoSF3211Service) {
		this.edoSF3211Service = edoSF3211Service;
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

}
