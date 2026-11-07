package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 16/08/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas
 * @Sector Municipal
 */
@Component("pbrm06MB")
@Scope("view")
public class Pbrm06MB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_PROGRAMA_ANUAL_ADQUISICIONES";

	private Integer saldoCero;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		saldoCero = 0;
		jasperReporteName = "pbrm06";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		String whereSaldoCero = " WHERE T1.ENERO <> 0 OR T1.FEBRERO <> 0 OR T1.MARZO <> 0 OR T1.ABRIL <> 0 OR T1.MAYO <> 0 OR T1.JUNIO <> 0 OR T1.JULIO <> 0 OR T1.AGOSTO <> 0 OR T1.SEPTIEMBRE <> 0 OR T1.OCTUBRE <> 0 	OR T1.NOVIEMBRE <> 0 OR T1.DICIEMBRE <> 0 ";
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		paramsReport.put("pImage", conctb.getImagePathLeft());
		paramsReport.put("pImage2", conctb.getImagePathRigth());
		paramsReport.put("pYear", conctb.getAnoemp());
		paramsReport.put("pEntePublico", conctb.getNomDep());
		paramsReport.put("pNumero", conctb.getClave());
		paramsReport.put("saldoCero", saldoCero == 1 ? StringUtils.EMPTY : whereSaldoCero);
		paramsReport.put("pIdSector", sector);
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
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

	public Integer getSaldoCero() {
		return saldoCero;
	}

	public void setSaldoCero(Integer saldoCero) {
		this.saldoCero = saldoCero;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
