package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.Constants;

@Component("servPersByCategoriaMB")
@Scope("view")
public class ServPersByCategoriaMB extends ReportePeriodos {
	
	private static final String CLAVE_REPORT = "RPT_LDF_GENERIC_EGRESOS";
	private static final String OUTPUT_REPORT_NAME = "LDF-ClasificacionServiciosPersonales_";
	
	private Integer noDecimales = 2;
	private Integer pesos = 1;
	private String fileName;
	private StreamedContent file;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	@Value("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;

	@PostConstruct
	public void init() {
		jasperReporteName = "servPersonalesCategoria";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("mes1", getNombreMesInicial().toUpperCase());
		parameters.put("mes2", getNombreMesSelected().toUpperCase());
		parameters.put("dia2", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("pPesos", pesos);
		parameters.put("sql", this.generateSqlPdf(idSector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	private String generateSqlPdf(Integer idSector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();

		String aprobado = "SUM(";
		String pagado = "SUM(";
		String reduccion = "SUM(";
		String devengado = "SUM(";
		String ampliacion = "SUM(";

		for (int y = getMesInicial(); y <= getMesSelected(); y++) {
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
				.append(" SUBSTR(PA.PROGRAMA,13) IN ('11020501', '15010114', '25010109', '25020707') " )
//				.append("SUBSTR(PA.PROGRAMA,13,3)>='261' AND SUBSTR(PA.PROGRAMA,13,3)<='262' OR ")
//				.append("SUBSTR(PA.PROGRAMA,13,3)>='264' AND SUBSTR(PA.PROGRAMA,13,3)<='265' ")
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
				.append(" SUBSTR(PA.PROGRAMA,13) IN ('11020501', '15010114', '25010109', '25020707') ")
//				.append("SUBSTR(PA.PROGRAMA,13,3)>='261' AND SUBSTR(PA.PROGRAMA,13,3)<='263' OR ")
//				.append("SUBSTR(PA.PROGRAMA,13,3)>='264' AND SUBSTR(PA.PROGRAMA,13,3)<='265' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC )  C1 ")
				.append(")T3 WHERE SUBSTR(CLVGAS,1,1) = '1' AND SUBSTR(CLVGAS,4,1) <> '0' ORDER BY GRUP, CLVGAS");

		if (pesos != 1) {
			sqlMiles.append(
					"SELECT T2.GRUP,T2.CONCEPTO , T2.CLVGAS, 	(T2.APROBADO /1000) APROBADO,(T2.AMPL_REDU /1000) AMPL_REDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sSql.insert(0, sqlMiles);
			sSql.append(")T2");
		}

		System.out.println("query:::" + sSql.toString());
		return sSql.toString();
	}

	public StreamedContent getFile() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		fileName = "EAEPECSPLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + periodo.getPeriodo() + ".txt";
		InputStream stream = null;
		try {
			stream = new FileInputStream(this.txtFilesService.generaArtivoTxtLDFServicosPersonales(
					this.generateSqlPdf(this.getUserDetails().getIdSector()), fileName));
		} catch (FileNotFoundException ex) {

		}
		file = StreamedContents.of(stream, Constants.APPLICCTION_TXT, fileName, null);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generó el Archivo: " + fileName);
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
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
