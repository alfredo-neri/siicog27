package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.dto.EdoSF3211DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ESFComparativo184.
 */
@Component("eSFComparativo184MB")
@Scope("view")
public class ESFComparativo184 extends ReportePeriodos {

	private static final String OUTPUT_REPORT_NAME = "LDF-ESFC";
	private static final String CLAVE_REPORT = "RPT_LDF_GENERIC";

	private Integer noDecimales;
	private Integer pesos;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		noDecimales = 2;
		pesos = 1;
		jasperReporteName = "ESFComparativo184";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
		this.acumulado = Boolean.TRUE;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		EdoSF3211DTO edoSF3211DTO = edoSF3211Service.executeQuery(sector, getMesInicial(), getMesSelected());
		System.out.println(":::->" + edoSF3211DTO.getTotalAct());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagePathRigth", conctb.getImagePathRigth());
		parameters.put("imagePathLeft", conctb.getImagePathLeft());
		parameters.put("mesFinal", getNombreMesSelected().toUpperCase());
		parameters.put("mesInicial", getNombreMesInicial().toUpperCase());
		parameters.put("dia", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("pSsql1", this.generaActivo());
		parameters.put("pSsql2", this.generaPasivo());
		parameters.put("pSsql3", this.generaPatrimonio());
		parameters.put("p3211Actual", edoSF3211DTO.getTotalAct());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	public String generaActivo() {
		return getQuery("1000", "1399");
	}

	public String generaPasivo() {
		return getQuery("2000", "2399");
	}

	public String generaPatrimonio() {
		return getQuery("3000", "3321");
	}

	public String getQuery(String cInicial, String cFinal) {

		StringBuilder sSql3 = new StringBuilder();
		String cargos = "";
		String abonos = "";
		for (int i = 1; i <= getMesSelected(); i++) {
			cargos += "CARGOS_" + i + " +";
			abonos += "ABONOS_" + i + " +";
		}

		sSql3.append("SELECT CUENTA, NOMCTA, ANIO_ANTERIOR, ANIO_ACTUAL, ANIO_ACTUAL - ANIO_ANTERIOR VARIACION FROM( ")
				.append("SELECT CUENTA, NOMCTA, ")
				.append(" CASE WHEN CUENTA IN ('1261', '1262', '1263') THEN (SALINIANT * -1) ELSE SALINIANT END ANIO_ANTERIOR,")
				.append(" CASE WHEN CUENTA IN ('1261', '1262', '1263') THEN (ANIO_ACTUAL * -1) ELSE ANIO_ACTUAL END ANIO_ACTUAL  FROM(")
				.append("SELECT 	CUENTA, STACTA, NOMCTA, SALINIANT, ")
				.append("DECODE(STACTA, 'D', SALINI + CARGOS - ABONOS, 'A', SALINI - CARGOS + ABONOS, 0) ANIO_ACTUAL ")
				.append("FROM( ")
				.append("SELECT 	SUBSTR(CUENTA,1,2) GRUPO, CUENTA, STACTA, NOMCTA, SALINIANT, SALINI, ")
				.append(cargos.substring(0, cargos.length() - 1)).append(" CARGOS, ")
				.append(abonos.substring(0, abonos.length() - 1)).append(" ABONOS ").append("FROM CUENTA ")
				.append("WHERE	IDSECTOR = ").append(this.getUserDetails().getIdSector()).append(" AND SCTA = '' ")
				.append("AND CUENTA BETWEEN '" + cInicial + "' AND '" + cFinal + "' ")
				.append("AND (SUBSTR(CUENTA,3,2) ='00' OR SUBSTR(CUENTA,4,1) <>'0') ")
				.append(")T1 )T2 )T3 WHERE SUBSTR(CUENTA,4,1) <> '0' ORDER BY CUENTA ");

		if (pesos != 1) {
			sSql3.insert(0,
					"SELECT CUENTA, NOMCTA, (ANIO_ANTERIOR / 1000) ANIO_ANTERIOR, (ANIO_ACTUAL/1000) ANIO_ACTUAL, (VARIACION/1000) VARIACION FROM ( ");
			sSql3.append(")T3");
		}

		System.out.println(sSql3.toString());
		return sSql3.toString();

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

	public EdoSF3211Service getEdoSF3211Service() {
		return edoSF3211Service;
	}

	public void setEdoSF3211Service(EdoSF3211Service edoSF3211Service) {
		this.edoSF3211Service = edoSF3211Service;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
