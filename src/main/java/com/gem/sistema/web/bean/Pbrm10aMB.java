package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.Map;
import jakarta.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class Pbrm10aMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 02/12/2021 <strong>Changes:</strong> Se
 *           ajusta reporte y se implementa firmas dinamicas
 * @Sector Municipal
 */
@Component("pbrm10aMB")
@Scope("view")
public class Pbrm10aMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String CLAVE_REPORT = "RPT_AVANCE_PRESUPUESTAL_EGRESOS_DETALLADO";

	private Integer mes;
	private Integer saldo0;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct pbrm10aMB");
		jasperReporteName = "pbrm10a";
		endFilename = jasperReporteName + ".pdf";
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

		paramsReport.put("mes", mes);
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("query", this.generateQuery());
		paramsReport.put("usuario", getUserDetails().getUsername());
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("claveEntidad", conctb.getClave());
		paramsReport.put("nombreEntidad", conctb.getNomDep());
		paramsReport.put("mesDescripcion", periodo.getDescripcion().toUpperCase());
		paramsReport.put("imagenPathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagenPathRigth", conctb.getImagePathRigth());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return paramsReport;
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}
	
	private String generateQuery() {
		StringBuilder sql = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder ejpa = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder compro = new StringBuilder();
		
		for (int i = 1; i <= mes; i++) {
			ampli.append(" P.AMPLI1_"+ i+" +");
			redu.append(" P.REDU1_"+ i +" +");
			ejpa.append(" P.EJPA1_" + i +" +");
			ejxpa.append(" P.EJXPA1_" + i +" +");
			compro.append(" P.COMPRO1_" + i +" +");
			toeje.append(" P.TOEJE1_" + i +" +");
		}
		
		sql.append("SELECT 	T2.CLAVEDEP, T2.PROGRAMA, T2.PARTIDA, T2.NOMGAS, T2.FF2016, \r\n"
				+ "		T2.AUTORIZADO_ANUAL, T2.AMPLI_ACUMULADO, T2.REDU_ACUMULADO,\r\n"
				+ "		T2.MODIFICADO_ACUMULADO, T2.PAGADO, T2.DEVENGADO, T2.COMPROMETIDO, \r\n"
				+ "		T2.MODIFICADO_MES, T2.EJERCIDO_ACUM, T2.POR_EJERCER\r\n"
				+ "FROM (\r\n"
				+ "		SELECT 	T1.CLAVEDEP, T1.PROGRAMA, T1.PARTIDA, T1.NOMGAS,\r\n"
				+ "				T1.FF2016, \r\n"
				+ "				T1.AUTORIZADO_ANUAL, T1.AMPLI_ACUMULADO, T1.REDU_ACUMULADO,\r\n"
				+ "				(T1.AUTORIZADO_ANUAL + T1.AMPLI_ACUMULADO - T1.REDU_ACUMULADO) MODIFICADO_ACUMULADO,\r\n"
				+ "				T1.PAGADO, T1.DEVENGADO, T1.COMPROMETIDO, \r\n"
				+ "				(T1.AUTO_MES + T1.AMPLI_MES - T1.REDU_MES) MODIFICADO_MES,\r\n"
				+ "				EJERCIDO_ACUM,\r\n"
				+ "				(T1.AUTORIZADO_ANUAL + T1.AMPLI_ACUMULADO - T1.REDU_ACUMULADO - EJERCIDO_ACUM) POR_EJERCER\r\n"
				+ "			FROM (\r\n"
				+ "				SELECT 	P.CLAVE CLAVEDEP,\r\n"
				+ "						P.PROGRAMA,\r\n"
				+ "						P.PARTIDA,\r\n"
				+ "						NG.NOMGAS,\r\n"
				+ "						NVL(FF.CLVFTE,'') FF2016,\r\n"
				+ "						(P.AUTO1_1 + P.AUTO1_2 + P.AUTO1_3 + P.AUTO1_4 +\r\n"
				+ "						 P.AUTO1_5 + P.AUTO1_6 + P.AUTO1_7 + P.AUTO1_8 +\r\n"
				+ "						 P.AUTO1_9 + P.AUTO1_10+ P.AUTO1_11+ P.AUTO1_12) AUTORIZADO_ANUAL,\r\n").append(
				 ampli.substring(0, ampli.length() -2 ) ).append( " AMPLI_ACUMULADO,\r\n").append(
				 redu.substring(0, redu.length() -2 ) ).append( " REDU_ACUMULADO,		\r\n").append(
				 ejpa.substring(0, ejpa.length() -2 ) ).append( " PAGADO,		\r\n").append(
				 ejxpa.subSequence(0, ejxpa.length() -2) ).append( " DEVENGADO,		\r\n").append(
				 compro.substring(0, compro.length() -2) ).append( " COMPROMETIDO,\r\n"
				+ "						P.AUTO1_"+mes+ "  AUTO_MES,\r\n"
				+ "						P.AMPLI1_"+mes+ " AMPLI_MES,\r\n"
				+ "						P.REDU1_"+mes+ "  REDU_MES,\r\n").append(
				 toeje.substring(0, toeje.length() -2) ).append( " EJERCIDO_ACUM\r\n"
				+ "				\r\n"
				+ "				FROM PASO P INNER JOIN FUENTEF FF 	 ON SUBSTR(P.PROGRAMA,13,3) = FF.LIGA AND FF.IDSECTOR=P.IDSECTOR\r\n"
				+ "							INNER JOIN NATGAS NG 	 ON P.IDSECTOR = NG.IDSECTOR AND P.PARTIDA = NG.CLVGAS\r\n"
				+ "				WHERE P.IDSECTOR = " + this.getUserDetails().getIdSector()
				+ "				)T1\r\n"
				+ "		ORDER BY T1.CLAVEDEP, T1.PROGRAMA, T1.PARTIDA\r\n"
				+ ")T2\r\n");
		
		if (saldo0 == 0) {
			sql.append("WHERE 	T2.AUTORIZADO_ANUAL <> 0 \r\n"
					+ "	OR 	T2.AMPLI_ACUMULADO <> 0 \r\n"
					+ "	OR 	T2.REDU_ACUMULADO <> 0 \r\n"
					+ "	OR	T2. MODIFICADO_ACUMULADO <> 0 \r\n"
					+ "	OR 	T2.PAGADO <> 0 \r\n"
					+ "	OR 	T2.DEVENGADO <> 0 \r\n"
					+ "	OR	T2.COMPROMETIDO <> 0 \r\n"
					+ "	OR T2.MODIFICADO_MES <> 0 \r\n"
					+ "	OR T2.EJERCIDO_ACUM <> 0 \r\n"
					+ "	OR T2.POR_EJERCER <> 0 ");
		}
		
		System.out.println("query->" + sql.toString());
		return sql.toString();
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public Integer getSaldo0() {
		return saldo0;
	}

	public void setSaldo0(Integer saldo0) {
		this.saldo0 = saldo0;
	}

}
