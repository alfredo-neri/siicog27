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

@ManagedBean(name = "cpClasificacionFuncionalMB")
@ViewScoped
public class CPClasificacionFuncionalMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_INFOEM";
	
//	private StreamedContent file;
//	private String fileName;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;
	
	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "clasificacion_funcional_cuentaPublica";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Object[] meses = getMonthsByTrimestre(4);
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(4, conctb, reporte);
		parameters.put("municipioName", conctb.getNomDep());
		parameters.put("pathImage", idSector == 1 ? conctb.getImagePathRigth() : conctb.getImagePathLeft());
		parameters.put("pathImage2", conctb.getImagePathRigth());
		parameters.put("mesInicial", meses[0]);
		parameters.put("mesFinal", meses[1]);
		parameters.put("lastDay", meses[2]);
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("sql", this.generateSQL(4, this.getUserDetails().getIdSector()));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		
		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer trimestre, Integer sector) {
		
		StringBuilder sql = new StringBuilder();
		int mesInicial = 1;
		int mesFinal = trimestre * 3;

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = mesInicial; y <= mesFinal; y++) {
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";
		
		sql.append("SELECT PRINCIPAL, ")
		.append("CASE WHEN CLVGAS = '9000' THEN 8 ELSE 1 END GRUPO, ")
		.append("NOMGAS NOMBRE, ")
		.append("SUM(APROBADO) APROBADO, ")
		.append("SUM(AMPL_REDU) AMPL_REDU, ")
		.append("SUM(MODIFICADO) MODIFICADO, ")
		.append("SUM(DEVENGADO) DEVENGADO, ")
		.append("SUM(PAGADO) PAGADO, ")
		.append("SUM(SUBEJERCICIO) SUBEJERCICIO ")
		.append("FROM ( ")
		.append("SELECT ")
		.append("CASE WHEN CLVGAS = '9000' THEN 2 ELSE 1 END PRINCIPAL, ")
		.append("CASE WHEN CLVGAS = '9000' THEN CLVGAS ELSE '1000' END CLVGAS, ")
		.append("CASE WHEN CLVGAS = '9000' THEN 'd4) Adeudos de Ejercicios Fiscales Anteriores' ELSE 'a8) Otros Servicios Generales' END NOMGAS, ")
		.append("T1.APROBADO, ")
		.append("(T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
		.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, ")
		.append("T1.DEVENGADO, T1.PAGADO, ")
		.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO ")
		.append("FROM ( ")
		.append("SELECT NAT.CLVGAS, NAT.NOMGAS, ")
		.append(auto).append(ampli).append(redu).append(ejxpa).append(ejpa)
		.append("FROM PASO PA ")
		.append("INNER JOIN NATGAS NAT ")
		.append("ON NAT.CLVGAS = PA.PARTIDA AND ")
		.append("NAT.IDSECTOR = PA.IDSECTOR AND ")
		.append("SUBSTR(PA.PARTIDA,3,2) = '00' AND ")
		.append("SUBSTR(NAT.CLVGAS,2,3) = '000' ")
		.append("WHERE PA.IDSECTOR = ").append(sector)
		.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='101' AND ")
		.append("SUBSTR(PA.PROGRAMA,13,3)<='144' ")
		.append("GROUP BY NAT.CLVGAS, NAT.NOMGAS ) T1 ")
		.append("UNION ALL ")
		.append("SELECT 1 PRINCIPAL, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, ")
		.append("(T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
		.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, ")
		.append("T1.DEVENGADO, T1.PAGADO, ")
		.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO ")
		.append("FROM ( ")
		.append("SELECT '1000' CLVGAS, 'a8) Otros Servicios Generales' NOMGAS, ")
		.append(auto).append(ampli).append(redu).append(ejxpa).append(ejpa)
		.append("FROM PASO PA ")
		.append("INNER JOIN NATGAS NAT ")
		.append("ON NAT.CLVGAS = PA.PARTIDA AND ")
		.append("NAT.IDSECTOR = PA.IDSECTOR AND ")
		.append("SUBSTR(PA.PARTIDA,3,2) = '00' AND ")
		.append("SUBSTR(NAT.CLVGAS,2,3) = '000' ")
		.append("WHERE PA.IDSECTOR = ").append(sector)
		.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='145' ")
		.append("GROUP BY NAT.CLVGAS, NAT.NOMGAS ")
		.append("ORDER BY NAT.CLVGAS ASC) T1 ")
		.append(") GROUP BY PRINCIPAL, CLVGAS, NOMGAS ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1,1,'a1) Legislación',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,1,'a2) Justicia',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,1,'a3) Coordinación de la Política de Gobierno',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,1,'a4) Relaciones Exteriores',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,1,'a5) Asuntos Financieros y Hacendarios',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,1,'a6) Seguridad Nacional',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,1,'a7) Asuntos de Orden Público y de Seguridad Interior',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b1) Protección Ambiental',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b2) Vivienda y Servicios a la Comunidad',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b3) Salud',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b4) Recreación, Cultura y Otras Manifestaciones Sociales',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b5) Educación',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b6) Protección Social',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,2,'b7) Otros Asuntos Sociales',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c1) Asuntos Económicos, Comerciales y Laborales en General',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c2) Agropecuaria, Silvicultura, Pesca y Caza',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c3) Combustibles y Energía',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c4) Minería, Manufacturas y Construcción',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c5) Transporte',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c6) Comunicaciones',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c7) Turismo',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c8) Ciencia, Tecnología e Innovación',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,3,'c9) Otras Industrias y Otros Asuntos Económicos',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,4,'d1) Transacciones de la Deuda Pública / Costo Financiero de la Deuda',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,4,'d2) Transferencias, Participaciones y Aportaciones Entre Diferentes Niveles y Órdenes de Gobierno',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,4,'d3) Saneamiento del Sistema Financiero',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(1,4,'d4) Adeudos de Ejercicios Fiscales Anteriores',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a1) Legislación',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a2) Justicia',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a3) Coordinación de la Política de Gobierno',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a4) Relaciones Exteriores',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a5) Asuntos Financieros y Hacendarios',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a6) Seguridad Nacional',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a7) Asuntos de Orden Público y de Seguridad Interior',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,5,'a8) Otros Servicios Generales',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b1) Protección Ambiental',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b2) Vivienda y Servicios a la Comunidad',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b3) Salud',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b4) Recreación, Cultura y Otras Manifestaciones Sociales',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b5) Educación',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b6) Protección Social',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,6,'b7) Otros Asuntos Sociales',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c1) Asuntos Económicos, Comerciales y Laborales en General',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c2) Agropecuaria, Silvicultura, Pesca y Caza',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c3) Combustibles y Energía',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c4) Minería, Manufacturas y Construcción',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c5) Transporte',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c6) Comunicaciones',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c7) Turismo',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c8) Ciencia, Tecnología e Innovación',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,7,'c9) Otras Industrias y Otros Asuntos Económicos',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,8,'d1) Transacciones de la Deuda Pública / Costo Financiero de la Deuda',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,8,'d2) Transferencias, Participaciones y Aportaciones Entre Diferentes Niveles y Órdenes de Gobierno',0.00,0.00,0.00,0.00,0.00,0.00), ")
		.append("(2,8,'d3) Saneamiento del Sistema Financiero',0.00,0.00,0.00,0.00,0.00,0.00) ")
		.append(") AS TEMP (PRINCIPAL,GRUPO,NOMBRE,APROBADO,AMPL_REDU,MODIFICADO,DEVENGADO,PAGADO,SUBEJERCICIO) ")
		.append("ORDER BY 1,2,3");

		System.out.println(sql.toString());
		return sql.toString();
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		int mesInicial = 1;
		int mesFinal = trimestre * 3;

		Object[] meses = { periodoRepository.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion(),
				periodoRepository.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion(), getLastDay(mesFinal) };

		return meses;
	}

//	public StreamedContent getFile() {
//		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
//
//		fileName = "EAEPECFLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + 4 + ".txt";
//		InputStream stream = null;
//		try {
//			stream = new FileInputStream(this.txtFilesService.generaArtivoTxtLDFClasifFuncional(
//					this.generateSQL(4, this.getUserDetails().getIdSector()), fileName));
//		} catch (FileNotFoundException ex) {
//
//		}
//		file = new DefaultStreamedContent(stream, Constants.APPLICCTION_TXT, fileName);
//		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generó el Archivo: " + fileName);
//		return file;
//	}
//
//	public void setFile(StreamedContent file) {
//		this.file = file;
//	}
//
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}

	public TcPeriodoRepositoy getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(TcPeriodoRepositoy periodoRepository) {
		this.periodoRepository = periodoRepository;
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

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
