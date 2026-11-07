package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

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

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.Constants;

@ManagedBean(name = "clasificacionAdministrativaMB")
@ViewScoped
public class ClasificacionAdministrativaMB extends ReportePeriodos {
	private static final String CLAVE_REPORT = "RPT_LDF_GENERIC_EGRESOS";
	private static final String OUTPUT_REPORT_NAME = "LDF-ClasificacionAdministrativa_";
	
	private String fileName;
	private StreamedContent file;
	private Integer noDecimales = 2;
	private Integer pesos = 1;
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;
	
	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "ClasificacionAdministrativa";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("firstMonth", getNombreMesInicial().toUpperCase());
		parameters.put("lastMonth", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("pPesos", pesos);
		parameters.put("sql", this.generateSql(this.getUserDetails().getIdSector()));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	private String generateSql(Integer idSector) {
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();
		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";
		String ampliGrup = "";
		String reduGrup = "";
		String autoGrup="SUM(%s";

		for (int y = 1; y <= getMesSelected(); y++) {
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
			ampliGrup = ampliGrup + " PA.AMPLI1_" + y + " +";
			reduGrup = reduGrup + " PA.REDU1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			autoGrup=autoGrup + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		autoGrup = autoGrup.substring(0, autoGrup.length() - 2) + " )) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION ";
		
		/*autoGrup=String.format(autoGrup, "DECODE (SUBSTR(PA.PARTIDA,1,1),'4',("+ampliGrup.substring(0, ampliGrup.length() - 2) +") - ("+reduGrup.substring(0, reduGrup.length() - 2)+"),");
*/
		sql.append(" SELECT GRUP,  	DECODE(APROBADO,NULL,0,APROBADO)APROBADO,  ")
				.append(" 	DECODE(AMPL_REDU,NULL,0,AMPL_REDU)AMPL_REDU,  ")
				.append(" 	 DECODE(MODIFICADO,NULL,0,MODIFICADO)MODIFICADO,  ")
				.append(" 	DECODE(DEVENGADO,NULL,0,DEVENGADO)DEVENGADO,  ")
				.append(" 	DECODE(PAGADO,NULL,0,PAGADO)PAGADO,  ")
				.append(" 	DECODE(SUBEJERCICIO,NULL,0, SUBEJERCICIO) SUBEJERCICIO ")
				.append(" FROM( SELECT GRUP,	APROBADO,  	(AMPLIACION -REDUCCIONES) AMPL_REDU,  ")
				.append(" 	(APROBADO + AMPLIACION -REDUCCIONES) MODIFICADO,   	DEVENGADO,	PAGADO,  ")
				.append(" 	(APROBADO + AMPLIACION -REDUCCIONES) - DEVENGADO SUBEJERCICIO   FROM ((  ")
				.append(" SELECT 2 GRUP,   ").append(auto).append(ejpa).append(redu).append(ejxpa).append(ampli)
				.append(" FROM CATDEP D   		LEFT JOIN PASO PA  ")
				.append(" 		ON PA.CLAVE = D.CLVDEP AND  	D.IDSECTOR = PA.IDSECTOR AND  ")
				.append(" 	PA.IDSECTOR = 2 AND  ")
				.append("      SUBSTR(PA.PROGRAMA,13) IN ('25010109','25020707') AND PA.PROGRAMA <> '04040101010125020707' ")
//				.append(" 	AND	SUBSTR(PA.PROGRAMA,	15,	1)<='5')  AND PA.PROGRAMA<>'040401010101265'     ")
				.append(" WHERE SUBSTR(PA.PARTIDA,	2,	3) = '000')   UNION ALL ( SELECT 1 GRUP,  ").append(auto)
				.append(ejpa).append(redu).append(ejxpa).append(ampli).append(" FROM CATDEP D  ")
				.append(" 		LEFT JOIN PASO PA 	ON PA.CLAVE = D.CLVDEP AND  ")
				.append(" 		D.IDSECTOR = PA.IDSECTOR AND 	PA.IDSECTOR = ").append(idSector).append(" AND  ")
//				.append(" 	SUBSTR(PA.PROGRAMA, 15, 1) IN ('1','2','5','6','7','8','9')  ")
				.append(" SUBSTR(PA.PROGRAMA, 13) IN ('11020501', '15010114', '25020707', '14010201','14010101','15010114', '14010203', '11010102') ")
				.append(" WHERE SUBSTR(PA.PARTIDA,2,3) = '000') )   )  ");
		if (pesos != 1) {
			sqlMiles.append(
					"SELECT T2.GRUP,	(T2.APROBADO /1000) APROBADO,(T2.AMPL_REDU /1000) AMPL_REDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sql.insert(0, sqlMiles);
			sql.append(")T2");
		}
		System.out.println(sql);

		return sql.toString();
	}

	public StreamedContent getFile() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		fileName = "EAEPECALDF" + conctb.getClave() + conctb.getAnoemp() + "0" + periodo.getPeriodo() + ".txt";
		InputStream stream = null;
		try {
			stream = new FileInputStream(this.txtFilesService.generaArtivoTxtLDFClasifAdminitrativa(
					this.generateSql(this.getUserDetails().getIdSector()), fileName));
		} catch (FileNotFoundException ex) {

		}
		file = new DefaultStreamedContent(stream, Constants.APPLICCTION_TXT, fileName);
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
