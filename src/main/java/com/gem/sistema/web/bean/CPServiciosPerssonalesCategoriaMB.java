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
 * @Timeline <strong>Date:</strong> 14/09/2021 <strong>Changes:</strong> Se
 *           crean componentes del reporte, se implementa servivio de firmas
 *           dinamico<br>
 * @Sector Municipal
 */
@Component("cpServiciosPerssonalesCategoriaMB")
@Scope("view")
public class CPServiciosPerssonalesCategoriaMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_EDO_ANA_PRESUP_EGRE_DET_LDF_CLASF_SERV_PER_CATEG";

	private Integer noDecimales;
	private Integer pesos;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "CPServiciosPersonalesCategoria";

		pesos = 1;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("imagenPathLeft", conctb.getImagePathRigth());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("claveEntidad", conctb.getClave());
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sql", this.generateSqlPdf(idSector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSqlPdf(Integer idSector) {
		int mesInicial = 1;
		int mesFinal = 12;

		StringBuilder sSql = new StringBuilder();

		String aprobado = "SUM(";
		String pagado = "SUM(";
		String reduccion = "SUM(";
		String devengado = "SUM(";
		String ampliacion = "SUM(";

		for (int y = mesInicial; y <= mesFinal; y++) {
			pagado = pagado + " PA.EJPA1_" + y + " +";
			reduccion = reduccion + " PA.REDU1_" + y + " +";
			devengado = devengado + " PA.TOEJE1_" + y + " +";
			ampliacion = ampliacion + " PA.AMPLI1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			aprobado = aprobado + " PA.AUTO1_" + y + " +";
		}

		aprobado = aprobado.substring(0, aprobado.length() - 2) + " ) APROBADO, ";
		pagado = pagado.substring(0, pagado.length() - 2) + " ) PAGADO ";
		reduccion = reduccion.substring(0, reduccion.length() - 2) + " ) REDUCCIONES, ";
		devengado = devengado.substring(0, devengado.length() - 2) + " ) DEVENGADO, ";
		ampliacion = ampliacion.substring(0, ampliacion.length() - 2) + " ) AMPLIACION, ";
		sSql.append("SELECT * FROM ( SELECT 1 GRUP, C1.CLVGAS, C1.CONCEPTO, C1.APROBADO, ")
				.append("( C1.AMPLIACION -  C1.REDUCCIONES) AMPL_REDU, ")
				.append("(( C1.APROBADO +  C1.AMPLIACION) -  C1.REDUCCIONES) AS MODIFICADO, ")
				.append("C1.DEVENGADO, C1.PAGADO, ")
				.append("( C1.APROBADO +  C1.AMPLIACION - C1.REDUCCIONES) -  C1.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT NAT.CLVGAS, CONCAT(CONCAT(NAT.CLVGAS,'  ' ),NAT.NOMGAS) AS CONCEPTO, ")
				.append(aprobado).append(ampliacion).append(reduccion).append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("WHERE  PA.IDSECTOR = ").append(idSector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) C1 UNION ALL ")
				.append("SELECT 2 GRUP, C1.CLVGAS, C1.CONCEPTO, C1.APROBADO, ")
				.append("( C1.AMPLIACION -  C1.REDUCCIONES) AMPL_REDU, ")
				.append("(( C1.APROBADO +  C1.AMPLIACION) -  C1.REDUCCIONES) AS MODIFICADO, ")
				.append("C1.DEVENGADO, C1.PAGADO, ")
				.append("( C1.APROBADO +  C1.AMPLIACION - C1.REDUCCIONES) -  C1.DEVENGADO AS SUBEJERCICIO ")
				.append("FROM (SELECT	NAT.CLVGAS, CONCAT(CONCAT(NAT.CLVGAS,'  ' ),NAT.NOMGAS) AS CONCEPTO, ")
				.append(aprobado).append(ampliacion).append(reduccion).append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("WHERE  PA.IDSECTOR = ").append(idSector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='116' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC )  C1 ")
				.append(")T3 WHERE SUBSTR(CLVGAS,1,1) = '1' AND SUBSTR(CLVGAS,4,1) <> '0' ORDER BY GRUP, CLVGAS");

		if (pesos != 1) {
			sSql.insert(0,
					"SELECT	GRUP, CLVGAS, CONCEPTO, (APROBADO/1000) APROBADO, (AMPL_REDU/1000) AMPL_REDU, (MODIFICADO/1000) MODIFICADO, (DEVENGADO/1000) DEVENGADO, (PAGADO/1000) PAGADO, (SUBEJERCICIO/1000) SUBEJERCICIO FROM (");
			sSql.append(" ) TM");
		}

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
