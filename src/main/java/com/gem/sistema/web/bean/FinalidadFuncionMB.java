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
 * @Timeline <strong>Date:</strong> 22/09/2021 <strong>Changes:</strong> Se
 *           implementa servivio de firmas dinamico<br>
 * @Sector Municipal
 */
@Component("finalidadFuncionMB")
@Scope("view")
public class FinalidadFuncionMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_LDF_GENERIC_EGRESOS";
	private static final String OUTPUT_REPORT_NAME = "LDF-ClasificacionFuncional_";

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
		jasperReporteName = "ClasificacionFinalidadFuncion";

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

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, 12, conctb, reporte);

		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("imagenPathLeft", conctb.getImagePathRigth());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("sector", sector);
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pSsql", this.generaQuery(sector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	public String generaQuery(Integer idsector) {

		StringBuilder sSql1 = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();

		sSql1.append("SELECT T1.CAMPO7, ").append("       T1.CAMPO6 , ").append("       T1.APROBADO, ")
				.append("       (T1.AMPLIACIONES -  T1.REDUCCIONES) APMLIREDU, ")
				.append("       (T1.APROBADO - T1.AMPLIACIONES) MODIFICADO, ").append("         T1.COMPROMETIDO, ")
				.append("         T1.DEVENGADO, ").append("         T1.PAGADO, ").append("         T1.EJERCIDO,  ")
				.append("       ((T1.APROBADO - T1.AMPLIACIONES) - T1.EJERCIDO) SUBEJERCICIO ")
				.append("     FROM ( SELECT NA.CAMPO0||NA.CAMPO1 CAMPO7, ").append("                   NA.CAMPO6, ")
				.append("       				SUM(PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + ")
				.append("				           PA.AUTO1_4 + PA.AUTO1_5 + PA.AUTO1_6 + ")
				.append("				           PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + ")
				.append("				           PA.AUTO1_10 + PA.AUTO1_11 + PA.AUTO1_12) APROBADO, ")
				.append("				       SUM(PA.AMPLI1_1 + PA.AMPLI1_2 + PA.AMPLI1_3 +  ")
				.append("				           PA.AMPLI1_4 + PA.AMPLI1_5 + PA.AMPLI1_6 +  ")
				.append("				           PA.AMPLI1_7 + PA.AMPLI1_8 + PA.AMPLI1_9 +  ")
				.append("				           PA.AMPLI1_10 + PA.AMPLI1_11 + PA.AMPLI1_12 ")
				.append("				           ) AMPLIACIONES, ")
				.append("				       SUM(PA.REDU1_1 + PA.REDU1_2 + PA.REDU1_3 +  ")
				.append("				           PA.REDU1_4 + PA.REDU1_5 + PA.REDU1_6 +  ")
				.append("				           PA.REDU1_7 + PA.REDU1_8 + PA.REDU1_9 +  ")
				.append("				           PA.REDU1_10 + PA.REDU1_11 + PA.REDU1_12 ")
				.append("				           ) REDUCCIONES, ")
				.append("				       SUM(PA.COMPRO1_1 + PA.COMPRO1_2 + PA.COMPRO1_3 + ")
				.append("				           PA.COMPRO1_4 + PA.COMPRO1_5 + PA.COMPRO1_6 + ")
				.append("				           PA.COMPRO1_7 + PA.COMPRO1_8 + PA.COMPRO1_9 + ")
				.append("				           PA.COMPRO1_10 + PA.COMPRO1_11 + PA.COMPRO1_12 ")
				.append("				           ) COMPROMETIDO, ")
				.append("				       SUM(PA.EJPA1_1 + PA.EJPA1_2 + PA.EJPA1_3 + ")
				.append("				           PA.EJPA1_4 + PA.EJPA1_5 + PA.EJPA1_6 + ")
				.append("				           PA.EJPA1_7 + PA.EJPA1_8 + PA.EJPA1_9 + ")
				.append("				           PA.EJPA1_10 + PA.EJPA1_11 + PA.EJPA1_12  ")
				.append("				           ) DEVENGADO, ")
				.append("				       SUM(PA.EJXPA1_1 + PA.EJXPA1_2 + PA.EJXPA1_3 + ")
				.append("				           PA.EJXPA1_4 + PA.EJXPA1_5 + PA.EJXPA1_6 + ")
				.append("				           PA.EJXPA1_7 + PA.EJXPA1_8 + PA.EJXPA1_9 + ")
				.append("				           PA.EJXPA1_10 + PA.EJXPA1_11 + PA.EJXPA1_12 ")
				.append("				           ) PAGADO,")
				.append("				       SUM(PA.TOEJE1_1 + PA.TOEJE1_2 + PA.TOEJE1_3 + ")
				.append("				           PA.TOEJE1_4 + PA.TOEJE1_5 + PA.TOEJE1_6 + ")
				.append("				           PA.TOEJE1_7 + PA.TOEJE1_8 + PA.TOEJE1_9 + ")
				.append("				           PA.TOEJE1_10 + PA.TOEJE1_11 + PA.TOEJE1_12   ")
				.append("				           ) EJERCIDO  ").append("				     FROM PASO PA, ")
				.append("				          MUNINEP NA ")
				.append("				WHERE (NA.CAMPO7  = SUBSTR(PA.PROGRAMA,1,2) ")
				.append("				  OR NA.CAMPO7  = SUBSTR(PA.PROGRAMA,1,4) ) ")
				.append("				  AND SUBSTR(PA.PARTIDA,4) <> '0' AND NA.ID <> 0 ")
				.append("          AND NA.IDSECTOR = PA.IDSECTOR  ")
				.append("				GROUP BY NA.CAMPO0||NA.CAMPO1, ").append("				         NA.CAMPO6 ")
				.append("				ORDER BY 1 ASC ").append(") T1");

		if (pesos != 1) {
			sqlMiles.append(
					"SELECT T2.CAMPO7 , T2.CAMPO6, 	(T2.APROBADO /1000) APROBADO,(T2.APMLIREDU /1000) APMLIREDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.COMPROMETIDO/1000) COMPROMETIDO,	(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.EJERCIDO /1000) EJERCIDO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sSql1.insert(0, sqlMiles);
			sSql1.append(")T2");
		}

		System.out.println(sSql1.toString());
		return sSql1.toString();

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
