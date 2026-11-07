package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@Component("cNCEstadoActividadesMB")
@Scope("view")
public class CNCEstadoActividadesMB extends ReportePeriodos {
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC";
	private static final String OUTPUT_REPORT_NAME = "CONAC-EstadoActividades";
	
	private String fideicomiso;
	private String labelPeriodo;

	/** The listquery. */
	private List<String> listquery;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "CNC_EstadoActividades";
		endFilename = jasperReporteName + ".pdf";
		this.acumulado = true;
		tipoPeriodo = TRIMESTRE;
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

	/**
	 * Generate querys.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the list
	 */
	public List<String> generateQuerys(Integer sector, String fideicomisos) {
		List<String> list = new ArrayList<String>();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		StringBuilder queryEgresos = new StringBuilder();
		StringBuilder queryIngresos = new StringBuilder();
		Integer i = 1;

		while (i <= getMesSelected()) {
			cargos.append(" CU.CARGOS_" + i + " +");
			abonos.append(" CU.ABONOS_" + i + " +");
			cargosAnterior.append(" EA.CARGOS_" + i + " +");
			abonosAnterior.append(" EA.ABONOS_" + i + " +");
			i++;
		}

		queryEgresos.append("SELECT	CLVGAS, ").append(
				"DECODE(CLVGAS,'1000','5100','2000','5100','3000','5100','4000','5200','5000','5700','6000','5600','8000','5300','9000','5400','') ||' '||CLVGAS || ' ' || NOMGAS NOMBRE, ")
				.append(" NVL(ACUMULADO_ACTUAL,0) ACUMULADO_ACTUAL, ").append("SUM(( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(" ) - " + "  ( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append("FROM(SELECT CLVGAS, NOMGAS, CU.CUENTA, ")
				.append(" SUM((").append(cargos.substring(0, cargos.length() - 1)).append(") - (")
				.append(abonos.substring(0, abonos.length() - 1)).append(") + CU.SALINI) ACUMULADO_ACTUAL ")
				.append("FROM NATGAS N INNER JOIN CUENTA CU ON N.CLVGAS = SUBSTR(CU.SSSCTA,1,1)||'000' ")
				.append("AND CU.CUENTA IN ('5100', '5200', '5300', '5400','5600','5700') AND CU.IDSECTOR=N.IDSECTOR ")
				.append("WHERE N.IDSECTOR=" + sector + " AND SUBSTR(N.CLVGAS,2,3)='000' ")
				.append("GROUP BY CLVGAS, NOMGAS, CU.CUENTA ) T1 ")
				.append("RIGHT JOIN TW_EJERCICIO_ANTERIOR EA ON T1.CUENTA = EA.CUENTA AND SUBSTR(T1.CLVGAS,1,1) = SUBSTR(EA.SSSCTA,1,1) ")
				.append("WHERE CLVGAS <> '7000' ")
				.append("GROUP BY CLVGAS, DECODE(CLVGAS,'1000','5100','2000','5100','3000','5100','4000','5200','5000','5700','6000','5600','8000','5300','9000','5400','')||' '||CLVGAS ||' '|| NOMGAS, NVL(ACUMULADO_ACTUAL,0)")
				.append(" UNION ALL ").append("SELECT '9999', CU.CUENTA||' '||CU.NOMCTA, ").append(" SUM((")
				.append(cargos.substring(0, cargos.length() - 1)).append(") - (")
				.append(abonos.substring(0, abonos.length() - 1)).append(") + CU.SALINI) ACUMULADO_ACTUAL, ")
				.append(" SUM((").append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(") - (")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1))
				.append(") + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ")
				.append(" FROM CUENTA CU LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON CU.CUENTA = EA.CUENTA AND EA.SCTA=''")
				.append(" WHERE CU.CUENTA IN ('5500') AND CU.SCTA='' AND CU.IDSECTOR=" + sector
						+ " GROUP BY '9999', CU.CUENTA||' '||CU.NOMCTA ORDER BY CLVGAS");

		queryIngresos.append("SELECT T1.GRUPO, T1.CUENTA, MA.NOMMAY, T1.ACUMULADO_ACTUAL, T1.ACUMULADO_ANTERIOR FROM( ")
				.append("SELECT	2 GRUPO, SUBSTR(CU.CUENTA,1,3)||'0' CUENTA, SUM((")
				.append(abonos.substring(0, abonos.length() - 1)).append(") - (")
				.append(cargos.substring(0, cargos.length() - 1)).append(") + CU.SALINI) ACUMULADO_ACTUAL, SUM((")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(") - (")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(") + EA.SDO_INICIAL) ")
				.append(" ACUMULADO_ANTERIOR FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON CU.CUENTA = EA.CUENTA AND CU.SCTA = EA.SCTA AND ")
				.append(" CU.SSCTA = EA.SSCTA AND CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA  ")
				.append(" WHERE SUBSTR(CU.CUENTA,1,4) = '4151' AND CU.SCTA = '' AND CU.IDSECTOR= " + sector
						+ " GROUP BY SUBSTR(CU.CUENTA,1,3)||'0' ")
				.append("UNION SELECT 2, SUBSTR(CU.CUENTA,1,3)||'0', SUM((")
				.append(abonos.substring(0, abonos.length() - 1)).append(") - (")
				.append(cargos.substring(0, cargos.length() - 1)).append(") + CU.SALINI) ACUMULADO_ACTUAL, SUM((")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(") - (")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(") + EA.SDO_INICIAL) ")
				.append(" ACUMULADO_ANTERIOR FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON CU.CUENTA = EA.CUENTA AND SUBSTR(CU.SCTA,6) = EA.SCTA AND ")
				.append(" SUBSTR(CU.SSCTA,6) = EA.SSCTA AND CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA  ")
				.append(" WHERE SUBSTR(CU.CUENTA,1,2) = '43' AND CU.SSSCTA <> '' AND CU.IDSECTOR= " + sector
						+ " GROUP BY SUBSTR(CU.CUENTA,1,3)||'0' ")
				.append("UNION SELECT 3,SUBSTR(CU.CUENTA,1,3)||'0', SUM((")
				.append(abonos.substring(0, abonos.length() - 1)).append(") - (")
				.append(cargos.substring(0, cargos.length() - 1)).append(") + CU.SALINI) ACUMULADO_ACTUAL, SUM((")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(") - (")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(") + EA.SDO_INICIAL) ")
				.append(" ACUMULADO_ANTERIOR FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON CU.CUENTA = EA.CUENTA AND SUBSTR(CU.SCTA,6) = EA.SCTA AND ")
				.append(" SUBSTR(CU.SSCTA,6) = EA.SSCTA AND CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA ")
				.append(" WHERE SUBSTR(CU.CUENTA,1,2) = '42' AND CU.SSSCTA <> '' AND CU.IDSECTOR= " + sector
						+ " GROUP BY SUBSTR(CU.CUENTA,1,3)||'0'	")
				.append(" ) T1 INNER JOIN MAYCTA MA ON T1.CUENTA = MA.CUENTA ").append(" ORDER BY 1,2");

		System.out.println("Egresos: " + queryEgresos.toString());
		System.out.println("Ingresos: " + queryIngresos.toString());

		list.add(queryEgresos.toString());
		list.add(queryIngresos.toString());

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);
		listquery = generateQuerys(this.getUserDetails().getIdSector(), fideicomiso);

		parameters.put("lastDayOfMonth", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("mesName", getNombreMesSelected().toUpperCase());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRight", conctb.getImagePathRigth());
		parameters.put("queryEgresos", listquery.get(0));
		parameters.put("queryIngresos", listquery.get(1));
		parameters.put("usuario", getUserDetails().getUsername());
		parameters.put("entidadName", conctb.getNomDep());
		parameters.put("entidadRfc", conctb.getRfc());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {

		return null;
	}

	public String getFideicomiso() {
		return fideicomiso;
	}

	public void setFideicomiso(String fideicomiso) {
		this.fideicomiso = fideicomiso;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Gets the listquery.
	 *
	 * @return the listquery
	 */
	public List<String> getListquery() {
		return listquery;
	}

	/**
	 * Sets the listquery.
	 *
	 * @param listquery the new listquery
	 */
	public void setListquery(List<String> listquery) {
		this.listquery = listquery;
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
