package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.HashMap;
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
 * @author Alfredo Neri
 * @Timeline <strong>Date:</strong> 21/09/2021 <strong>Changes:</strong> Se
 *           implementa servivio de firmas dinamico<br>
 * @Sector Municipal
 */
@Component("clasificacionObjetoGastoMB")
@Scope("view")
public class ClasificacionObjetoGastoMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_EDO_ANA_PRESUP_EGRE_CLASF_OBJ_GASTO";

	private Integer noDecimales;
	private Integer pesos;

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
		noDecimales = 2;
		pesos = 1;

		jasperReporteName = "clasificacionObjetoDelGasto";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("query", this.generaQuery());
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaQuery() {
		StringBuilder query = new StringBuilder();

		query.append(
				"SELECT T1.PARTIDA, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION - T1.REDUCCION) AMPLI_REDU, (T1.APROBADO + T1.AMPLIACION - T1.REDUCCION ) MODIFICADO, T1.COMPROMETIDO, T1.DEVENGADO, T1.EJERCIDO, T1.PAGADO, ((T1.APROBADO + T1.AMPLIACION - T1.REDUCCION ) - T1.EJERCIDO) SUBEJERCICIO\r\n"
						+ "	FROM(\r\n" + "		SELECT	PA.PARTIDA, NA.NOMGAS, \r\n"
						+ "				SUM(PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 + PA.AUTO1_6 + PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 + PA.AUTO1_12) APROBADO,\r\n"
						+ "				SUM(PA.AMPLI1_1 + PA.AMPLI1_2 + PA.AMPLI1_3 + PA.AMPLI1_4 + PA.AMPLI1_5 + PA.AMPLI1_6 + PA.AMPLI1_7 + PA.AMPLI1_8 + PA.AMPLI1_9 + PA.AMPLI1_10 + PA.AMPLI1_11 + PA.AMPLI1_12) AMPLIACION, \r\n"
						+ "				SUM(PA.REDU1_1 + PA.REDU1_2 + PA.REDU1_3 + PA.REDU1_4 + PA.REDU1_5 + PA.REDU1_6 + PA.REDU1_7 + PA.REDU1_8 + PA.REDU1_9 + PA.REDU1_10 + PA.REDU1_11 + PA.REDU1_12) REDUCCION,\r\n"
						+ "				SUM(PA.COMPRO1_1 + PA.COMPRO1_2 + PA.COMPRO1_3 + PA.COMPRO1_4 + PA.COMPRO1_5 + PA.COMPRO1_6 + PA.COMPRO1_7 + PA.COMPRO1_8 + PA.COMPRO1_9 + PA.COMPRO1_10 + PA.COMPRO1_11 + PA.COMPRO1_12) COMPROMETIDO,\r\n"
						+ "				SUM(PA.EJXPA1_1 + PA.EJXPA1_2 + PA.EJXPA1_3 + PA.EJXPA1_4 + PA.EJXPA1_5 + PA.EJXPA1_6 + PA.EJXPA1_7 + PA.EJXPA1_8 + PA.EJXPA1_9 + PA.EJXPA1_10 + PA.EJXPA1_11 + PA.EJXPA1_12 ) DEVENGADO,\r\n"
						+ "				SUM(PA.TOEJE1_1 + PA.TOEJE1_2 + PA.TOEJE1_3 + PA.TOEJE1_4 + PA.TOEJE1_5 + PA.TOEJE1_6 + PA.TOEJE1_7 + PA.TOEJE1_8 + PA.TOEJE1_9 + PA.TOEJE1_10 + PA.TOEJE1_11 + PA.TOEJE1_12) EJERCIDO,\r\n"
						+ "				SUM(PA.EJPA1_1 + PA.EJPA1_2 + PA.EJPA1_3 + PA.EJPA1_4 + PA.EJPA1_5 + PA.EJPA1_6 + PA.EJPA1_7 + PA.EJPA1_8 + PA.EJPA1_9 + PA.EJPA1_10 + PA.EJPA1_11 + PA.EJPA1_12) PAGADO\r\n"
						+ "			FROM PASO PA\r\n"
						+ "				INNER JOIN NATGAS NA ON PA.PARTIDA = NA.CLVGAS AND PA.IDSECTOR = NA.IDSECTOR\r\n"
						+ "		WHERE PA.IDSECTOR = " + this.getUserDetails().getIdSector()
						+ "		GROUP BY PA.PARTIDA, NA.NOMGAS\r\n" + "	)T1\r\n" + "ORDER BY T1.PARTIDA");

		if (pesos != 1) {
			query.insert(0,
					"SELECT PARTIDA, NOMGAS, (APROBADO/1000) APROBADO, (AMPLI_REDU/1000) AMPLI_REDU, (MODIFICADO/1000) MODIFICADO, (COMPROMETIDO/1000) COMPROMETIDO, (DEVENGADO/1000) DEVENGADO, (EJERCIDO/1000) EJERCIDO, (PAGADO/1000) PAGADO, (SUBEJERCICIO/1000) SUBEJERCICIO FROM( ");
			query.append(" )T2");
		}

		return query.toString();
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
