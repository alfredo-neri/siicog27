package com.gem.sistema.web.bean.cuenta;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDay;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.bean.BaseDirectReport;

@ManagedBean(name = "cpServiciosPersonalesCategoriaMB")
@ViewScoped
public class CPServiciosPersonalesCategoriaMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_INFOEM";
	private Integer trimestre;
	private Integer noDecimales;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@ManagedProperty("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;
	
	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	@PostConstruct
	public void init() {
		jasperReporteName = "servicios_personales_categoria_cuentaPublica";
		endFilename = jasperReporteName + ".pdf";
		trimestre = 4;
		noDecimales = 2;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Object[] meses = getMonthsByTrimestre(trimestre);
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(trimestre, conctb, reporte);
		parameters.put("municipio", conctb.getNomDep());
		parameters.put("imagen", idSector == 1 ? conctb.getImagePathRigth() : conctb.getImagePathLeft());
		parameters.put("imagen2", conctb.getImagePathRigth());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("mes1", meses[0]);
		parameters.put("mes2", meses[1]);
		parameters.put("dia2", meses[2]);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sql", this.generateSqlPdf(idSector, trimestre));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		int mesInicial = 1;
		int mesFinal = trimestre * 3;
		Object[] meses = { periodoRepository.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion(),
				periodoRepository.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion(), getLastDay(mesFinal) };

		return meses;
	}

	private String generateSqlPdf(Integer idSector, Integer trimestre) {
		int mesInicial = 1;
		int mesFinal = trimestre * 3;

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
		
		sSql.append("SELECT 1 PRINCIPAL, 1 ORDEN, T1.NOMBRE, T1.APROBADO, ")
		.append("(T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
		.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, ")
		.append("T1.DEVENGADO, T1.PAGADO, ")
		.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO ")
		.append("FROM ( ")
		.append("SELECT '    A. Personal Administrativo y de Servicio Público' NOMBRE, ")
		.append(aprobado).append(ampliacion).append(reduccion).append(devengado).append(pagado)
		.append("FROM PASO PA ")
		.append("INNER JOIN NATGAS NAT ")
		.append("ON NAT.CLVGAS = PA.PARTIDA AND ")
		.append("NAT.IDSECTOR = PA.IDSECTOR AND ")
		.append("SUBSTR(PA.PARTIDA,3,2) = '00' AND ")
		.append("SUBSTR(NAT.CLVGAS,2,3) = '000' ")
		.append("WHERE PA.IDSECTOR = ").append(idSector)
		.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='145' ")
		.append("AND NAT.CLVGAS = '1000' ")
		.append("GROUP BY NAT.CLVGAS, NAT.NOMGAS ")
		.append("ORDER BY NAT.CLVGAS ASC) T1 ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1,2,'    B. Magisterio',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'    C. Servicios de Salud (C=c1+c2)',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,4,'        c1) Personal Administrativo',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,5,'        c2) Personal Médico, Paramédico y afín',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,6,'    D. Seguridad Pública',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,7,'    E. Gastos asociados a la implementación de nuevas leyes federales o reformas a las mismas (E = e1 + e2)',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,8,'        e1) Nombre del Programa o Ley 1',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,9,'        e2) Nombre del Programa o Ley 2',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,10,'    F. Sentencias laborales definitivas',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,11,'    A. Personal Administrativo y de Servicio Público',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,12,'    B. Magisterio',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,13,'    C. Servicios de Salud (C=c1+c2)',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,14,'        c1) Personal Administrativo',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,15,'        c2) Personal Médico, Paramédico y afín',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,16,'    D. Seguridad Pública',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,17,'    E. Gastos asociados a la implementación de nuevas leyes federales o reformas a las mismas (E = e1 + e2)',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,18,'        e1) Nombre del Programa o Ley 1',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,19,'        e2) Nombre del Programa o Ley 2',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,20,'    F. Sentencias laborales definitivas',0.00,0.00,0.00,0.00,0.00,0.00) ")
		.append(") AS TEMP (PRINCIPAL,ORDEN,NOMBRE,APROBADO,AMPL_REDU,MODIFICADO,DEVENGADO,PAGADO,SUBEJERCICIO) ")
		.append("ORDER BY 2");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

//	public StreamedContent getFile() {
//		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
//
//		fileName = "EAEPECSPLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";
//		InputStream stream = null;
//		try {
//			stream = new FileInputStream(this.txtFilesService.generaArtivoTxtLDFServicosPersonales(
//					this.generateSqlPdf(this.getUserDetails().getIdSector(), trimestre), fileName));
//		} catch (FileNotFoundException ex) {
//
//		}
//		file = new DefaultStreamedContent(stream, Constants.APPLICCTION_TXT, fileName);
//		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generó el Archivo: " + fileName);
//		return file;
//	}

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
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

	public TcPeriodoRepositoy getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(TcPeriodoRepositoy periodoRepository) {
		this.periodoRepository = periodoRepository;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
