package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "gastoCategProgramaticaMB")
@ViewScoped
public class GastoCategProgramaticaMB extends ReportePeriodos {
	private static final String OUTPUT_REPORT_NAME = "CONAC-GastoCategoriaProgramatica";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC_EGRESOS";

	final static private String VW1 = "VI_GASTO_PROGRAMATICO_T1";
	final static private String VW2 = "VI_GASTO_PROGRAMATICO_T2";
	final static private String VW3 = "VI_GASTO_PROGRAMATICO_T3";
	final static private String VW4 = "VI_GASTO_PROGRAMATICO_T4";
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "gastoporcategoriaprogramatica";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagePathRigth", conctb.getImagePathRigth());
		parameters.put("imagePathLeft", conctb.getImagePathLeft());
		parameters.put("monthName", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("year", conctb.getAnoemp());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		String pSql = StringUtils.EMPTY;

		pSql = "SELECT PROGRAMA,CONCEPTO,APROBADO, AMP_RED,MODIFICADO,DEVENGADO,PAGADO,SUBEJERCIDO SUB_E FROM ";
		switch (periodo.getPeriodo()) {
		case 1:
			pSql = pSql + VW1;
			break;
		case 2:
			pSql = pSql + VW2;
			break;
		case 3:
			pSql = pSql + VW3;
			break;
		case 4:
			pSql = pSql + VW4;
			break;
		}
		pSql = pSql + " ORDER BY PROGRAMA ASC LIMIT 1";
		System.out.println("Query->" + pSql);
		parameters.put("pQuery", pSql);

		return parameters;
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
