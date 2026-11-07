package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

// TODO: Auto-generated Javadoc
/**
 * The Class REAPIMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 02/12/2021 <strong>Changes:</strong> Se
 *           ajusta reporte y se implementa firmas dinamicas
 * @Sector Municipal, Central
 */
@ManagedBean
@ViewScoped
public class REAPIMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_AVANCE_PRESUPUESTAL_INGRESOS";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EDO_AVANCE_PRESUPUESTAL_INGRESOS";

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;
	
	private StreamedContent fileTxt;

	private Integer mes;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EAPI ");
		jasperReporteName = "EAPI";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);

		if (sector == 2)
			paramsReport.put("where", this.generateWhere(mes));
		else
			paramsReport.put("where", "");
		
		paramsReport.put("mes", mes);
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("sector", sector);
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("claveEntidad", conctb.getClave());
		paramsReport.put("nombreEntidad", conctb.getNomDep());
		paramsReport.put("mesDescripcion", periodo.getDescripcion().toUpperCase());
		paramsReport.put("imagenPathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagenPathRigth", conctb.getImagePathRigth());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));
		
		return paramsReport;
	}

	/**
	 * Generate where.
	 *
	 * @param mes the mes
	 * @return the string
	 */
	public String generateWhere(Integer mes) {
		StringBuilder cargos10 = new StringBuilder();
		StringBuilder abonos10 = new StringBuilder();
		StringBuilder cargos40 = new StringBuilder();
		StringBuilder abonos40 = new StringBuilder();
		StringBuilder cargos50 = new StringBuilder();
		StringBuilder abonos50 = new StringBuilder();
		StringBuilder where = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargos10.append(" OR DI.CARGOS_" + i + " <> 0 ");
			abonos10.append(" OR DI.ABONOS_" + i + " <> 0 ");
			cargos40.append(" OR CU.CARGOS_" + i + " <> 0 ");
			abonos40.append(" OR CU.ABONOS_" + i + " <> 0 ");
			cargos50.append(" OR CI.CARGOS_" + i + " <> 0 ");
			abonos50.append(" OR CI.ABONOS_" + i + " <> 0 ");
			i++;
		}

		where.append("AND (DI.SALINI <> 0" + cargos10 + abonos10).append(" OR CU.SALINI <> 0 " + cargos40 + abonos40)
				.append(" OR CI.SALINI <> 0 " + cargos50 + abonos50).append(")");
		System.out.println(where.toString());
		return where.toString();
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

	private String generaTXTQuery() {
	
		StringBuilder cargos10 = new StringBuilder();
		StringBuilder abonos10 = new StringBuilder();
		StringBuilder cargos40 = new StringBuilder();
		StringBuilder abonos40 = new StringBuilder();
		StringBuilder cargos50 = new StringBuilder();
		StringBuilder abonos50 = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargos10.append(" DI.CARGOS_" + i + " +");
			abonos10.append(" DI.ABONOS_" + i + " +");
			cargos40.append(" CU.CARGOS_" + i + " +");
			abonos40.append(" CU.ABONOS_" + i + " +");
			cargos50.append(" CI.CARGOS_" + i + " +");
			abonos50.append(" CI.ABONOS_" + i + " +");
			i++;
		}
		
		query.append("SELECT CUENTA, SCTA,\r\n"
				+ "       SSCTA,\r\n"
				+ "       SSSCTA,\r\n"
				+ "       SSSSCTA,\r\n"
				+ "       CONCEPTO,\r\n"
				+ "       ESTIMADO,\r\n"
				+ "       AMPLIACION,\r\n"
				+ "       REDUCCION,\r\n"
				+ "       MODIFICADO,\r\n"
				+ "       DEVENGADO,\r\n"
				+ "       RECAUDADO,\r\n"
				+ "       (MODIFICADO - RECAUDADO + DEVENGADO) POR_EJECUTAR\r\n"
				+ " FROM (SELECT CUENTA,\r\n"
				+ "             SCTA,\r\n"
				+ "             SSCTA,\r\n"
				+ "             SSSCTA,\r\n"
				+ "             SSSSCTA,\r\n"
				+ "             CONCEPTO,\r\n"
				+ "             ESTIMADO,\r\n"
				+ "             AMPLIACION,\r\n"
				+ "             REDUCCION,\r\n"
				+ "             (ESTIMADO + AMPLIACION - REDUCCION) MODIFICADO,\r\n"
				+ "             (CU_ABONOS - CU_CARGOS)             DEVENGADO,\r\n"
				+ "             (CI_ABONOS - CI_CARGOS)             RECAUDADO\r\n"
				+ "      FROM (SELECT DI.CUENTA,\r\n"
				+ "                   SUBSTR(DI.SCTA, 12)                       SCTA,\r\n"
				+ "                   DI.SSCTA,\r\n"
				+ "                   DI.SSSCTA,\r\n"
				+ "                   DI.SSSSCTA,\r\n"
				+ "                   DI.NOMCTA                                 CONCEPTO,\r\n"
				+ "                   DI.SALINI                                 ESTIMADO,\r\n"
				+ "                   (").append(cargos10.substring(0, cargos10.length() -1)).append(") AMPLIACION,\r\n"
				+ "                   (").append(abonos10.substring(0, abonos10.length() -1)).append(") REDUCCION,\r\n"
				+ "                   (").append(cargos50.substring(0, cargos50.length() -1)).append(") CI_CARGOS,\r\n"
				+ "                   (").append(abonos50.substring(0, abonos50.length() -1)).append(") CI_ABONOS,\r\n"
				+ "                   (").append(cargos40.substring(0, cargos40.length() -1)).append(") CU_CARGOS,\r\n"
				+ "                   (").append(abonos40.substring(0, abonos40.length() -1)).append(") CU_ABONOS\r\n"
				+ "            FROM CUENTA AS DI\r\n"
				+ "                     INNER JOIN CUENTA AS CI ON CI.CUENTA = '8150' AND CI.SCTA = DI.SCTA AND CI.SSCTA = DI.SSCTA AND\r\n"
				+ "                                                CI.SSSCTA = DI.SSSCTA AND CI.SSSSCTA = DI.SSSSCTA AND\r\n"
				+ "                                                CI.IDSECTOR = DI.IDSECTOR\r\n"
				+ "                     INNER JOIN CUENTA AS CU ON CU.CUENTA = '8140' AND CU.SCTA = DI.SCTA AND CU.SSCTA = DI.SSCTA AND\r\n"
				+ "                                                CU.SSSCTA = DI.SSSCTA AND CU.SSSSCTA = DI.SSSSCTA AND\r\n"
				+ "                                                CU.IDSECTOR = DI.IDSECTOR\r\n"
				+ "            WHERE (DI.CUENTA = '8110')\r\n"
				+ "              AND DI.NOTCTA = 0\r\n"
				+ "              AND DI.IDSECTOR = ").append(this.getUserDetails().getIdSector()).append(
				 "           ) T1) T2\r\n"
				+ " WHERE AMPLIACION <> 0 OR REDUCCION <> 0 OR MODIFICADO <> 0 OR DEVENGADO <> 0 OR RECAUDADO <> 0\r\n"
				+ " ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA");
		
		System.out.println("Query:" + query.toString());
		return query.toString();
	}
	
	Map<String, Object> out;
	InputStream stream = null;
	public StreamedContent getFileTxt() {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(mes, conctb, reporte);
		
		parameter.addValue("i_query", this.generaTXTQuery());
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

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
