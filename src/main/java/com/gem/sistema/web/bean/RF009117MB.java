package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009117MB.
 */
@ManagedBean
@ViewScoped
public class RF009117MB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_ESTADO_EGRESOSG";

	/** The mes. */
	private Integer mes;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009117MB ");
		jasperReporteName = "rf009_11_17";
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
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);
		
		endFilename = getNameFileReport(mes, conctb, reporte);
		
		paramsReport.put("mes", mes);
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("sQuery", generateQuery(mes, idSector));
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("mesName", periodo.getDescripcion().toUpperCase());
		paramsReport.put("idSector", idSector);
		paramsReport.put("imageLeft", conctb.getImagePathLeft());
		paramsReport.put("imageRigth", conctb.getImagePathRigth());
		paramsReport.put("entidadName", conctb.getNomDep());
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
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
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
	 * Generate query.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the string
	 */
	private static String generateQuery(Integer mes, Integer sector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder sToeje = new StringBuilder();
		StringBuilder sXampli = new StringBuilder();
		StringBuilder sXredu = new StringBuilder();
		StringBuilder sAuto = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			sXampli.append("PA.AMPLI1_" + i + " + ");
			sXredu.append("PA.REDU1_" + i + " + ");
			sToeje.append("PA.COMPRO1_" + i + " + PA.EJPA1_" + i + " + PA.EJXPA1_" + i + " + ");
			sAuto.append("PA.AUTO1_" + i + " + ");
		}

		sSql.append(" SELECT  CLVGAS || ' ' || NOMGAS CONCEPTO, PRESUPUESTO, "
				+ "		(AUTO_MES + AMPLI_MES - REDU_MES) MODIFICADO_MES, TOEJE_MES EJERCIDO_MES, "
				+ "		(AUTO_ACUM + AMPLI_ACUM - REDU_ACUM) MODIFICADO_ACUM, TOEJE_ACUM EJERCIDO_ACUM,\r\n"
				+ "		(AUTO_ACUM + AMPLI_ACUM - REDU_ACUM) - TOEJE_ACUM VAR_ABS,\r\n"
				+ " CASE WHEN (AUTO_ACUM + AMPLI_ACUM - REDU_ACUM) = 0 \r\n" + " 				THEN 0 \r\n"
				+ " 			ELSE ((((AUTO_ACUM + AMPLI_ACUM - REDU_ACUM) - TOEJE_ACUM) * 100 ) / (AUTO_ACUM + AMPLI_ACUM - REDU_ACUM)) END POR "
				+ " FROM (\r\n" + "	SELECT	NA.CLVGAS, NA.NOMGAS, \r\n"
				+ "			SUM(PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 + PA.AUTO1_6 + PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 + PA.AUTO1_12) PRESUPUESTO,\r\n"
				+ "			SUM(PA.AUTO1_").append(mes).append(") AUTO_MES,\r\n" + "			SUM(PA.AMPLI1_")
				.append(mes).append(") AMPLI_MES,\r\n" + "			SUM(PA.REDU1_").append(mes)
				.append(") REDU_MES,\r\n" + "			SUM(PA.COMPRO1_").append(mes).append(" + PA.EJPA1_").append(mes)
				.append(" + PA.EJXPA1_").append(mes).append(") TOEJE_MES,\r\n" + "			SUM(")
				.append(sAuto.substring(0, sAuto.length() - 2)).append(") AUTO_ACUM,\r\n" + "			SUM(")
				.append(sXampli.substring(0, sXampli.length() - 2)).append(") AMPLI_ACUM,\r\n" + "			SUM(")
				.append(sXredu.substring(0, sXredu.length() - 2)).append(") REDU_ACUM,\r\n" + "			SUM(")
				.append(sToeje.substring(0, sToeje.length() - 2))
				.append(") TOEJE_ACUM\r\n" + "		FROM PASO PA\r\n"
						+ "			INNER JOIN NATGAS NA ON NA.IDSECTOR = PA.IDSECTOR AND NA.CLVGAS = PA.PARTIDA\r\n"
						+ "	WHERE 	PA.IDSECTOR= " + sector + "		AND SUBSTR(PA.PARTIDA,2,3) = '000'\r\n"
						+ "	GROUP BY NA.CLVGAS, NA.NOMGAS\r\n" + "	ORDER BY NA.CLVGAS\r\n" + ") T1");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
