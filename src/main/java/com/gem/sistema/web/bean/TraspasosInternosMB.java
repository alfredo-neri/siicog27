package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
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
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Timeline <strong>Date: </strong>27/04/2022 <strong>Changes: </strong>Se
 *          crea reporte.<br>
 * @Sector Municipal
 */
@Component("traspasosInternosMB")
@Scope("view")
public class TraspasosInternosMB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_TRASPASOS_INTERNOS";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_TRASPASOS_INTERNOS";

	private Integer trimestre;

	private List<TcPeriodo> periodos;

	private StreamedContent txt;

	@Value("#{executePlService}")
	private ExecutePlService executePlService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "traspasosInternos";

		periodos = periodoRepositoy.findByTipoPeriodo(3);

		if (!CollectionUtils.isEmpty(periodos)) {
			trimestre = periodos.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		Conctb conctb = conctbRepository.findByIdsector(sector);

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("query", this.generateQueryPDF(trimestre));
		paramsReport.put("sector", sector);
		paramsReport.put("mesInicial", (trimestre * 3) - 2);
		paramsReport.put("imagenPathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagenPathRigth", conctb.getImagePathRigth());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		endFilename = getNameFileReport(trimestre, conctb, reporte);

		return paramsReport;
	}

	private String generateQueryPDF(Integer trimestre) {

		Integer mesfinal = trimestre * 3;
		String query = "SELECT 	P.PROGRAMA, P.CLAVE, G.CLAVE DEP_GEN, G.NOMBRE NAME_GENERAL, "
				+ "A.CLAVE DEP_AUX, A.NOMBRE NAME_AUXILIAR, M1.CAMPO7 PROGR, "
				+ "M1.CAMPO6 NAME_PROGRAMA, M1.CAMPO8 OBJETIVO, M2.CAMPO7 PROYECTO, "
				+ "M2.CAMPO6 NAME_PROYECTO FROM PASO P "
				+ "INNER JOIN MUNINEP M1 ON SUBSTR(P.PROGRAMA,1,8) = M1.CAMPO7 AND P.IDSECTOR = M1.IDSECTOR "
				+ "INNER JOIN MUNINEP M2 ON SUBSTR(P.PROGRAMA,1,12) = M2.CAMPO7 AND P.IDSECTOR = M2.IDSECTOR "
				+ "INNER JOIN CATDGM G ON SUBSTR(P.CLAVE,1,3) = G.CLAVE "
				+ "INNER JOIN CATDAA A ON SUBSTR(P.CLAVE,4,3) = A.CLAVE "
				+ "WHERE SUBSTR(P.PARTIDA,4,1) <> '0' AND P.IDSECTOR = "
				+ this.getUserDetails().getIdSector()
				+ " AND (P.AMPLI1_%1$s <> 0 OR  P.AMPLI1_%2$s <> 0 OR P.AMPLI1_%3$s <> 0 OR "
				+ "P.REDU1_%1$s  <> 0 OR P.REDU1_%2$s  <> 0 OR P.REDU1_%3$s <> 0 ) " + "ORDER BY P.CLAVE, P.PROGRAMA";
		System.out.println(String.format(query, mesfinal - 2, mesfinal - 1, mesfinal));
		return String.format(query, mesfinal - 2, mesfinal - 1, mesfinal);
	}

	private String generateQueryTXT(Integer mes) {
		StringBuilder sSql = new StringBuilder();
		
		sSql.append("SELECT 	SUBSTR(T1.CLAVE,1,3) DEPGEN, SUBSTR(T1.CLAVE,4,3) DEPAUX,\r\n"
				+ "		SUBSTR(T1.PROGRAMA,1,12) PROGRAMA, F.CLVFTE FUENTE, \r\n"
				+ "		SUBSTR(T1.PARTIDA,1,1)||'000' CAPITULO, T1.PARTIDA, \r\n"
				+ "		P.DESCRIPCION MES_NAME, T1.AUTORIZADO, T1.REDUCCION, T1.AMPLIACION\r\n"
				+ "FROM\r\n"
				+ "	(\r\n"
				+ "SELECT\r\n"
				+ "	P.CLAVE, P.PROGRAMA,  P.PARTIDA, %1$s MES, P.AUTO1_13 AUTORIZADO, P.REDU1_%1$s REDUCCION, P.AMPLI1_%1$s AMPLIACION\r\n"
				+ "FROM\r\n"
				+ "	PASO P\r\n"
				+ "WHERE\r\n"
				+ "	SUBSTR(P.PARTIDA,4,1) <> '0' AND P.IDSECTOR = %4$s\r\n"
				+ "UNION\r\n"
				+ "SELECT\r\n"
				+ "	P.CLAVE, P.PROGRAMA, P.PARTIDA, %2$s , P.AUTO1_13, P.REDU1_%2$s, P.AMPLI1_%2$s\r\n"
				+ "FROM\r\n"
				+ "	PASO P\r\n"
				+ "WHERE\r\n"
				+ "	SUBSTR(P.PARTIDA,4,1) <> '0' AND P.IDSECTOR = %4$s\r\n"
				+ "UNION\r\n"
				+ "SELECT\r\n"
				+ "	P.CLAVE, P.PROGRAMA, P.PARTIDA, %3$s , P.AUTO1_13, P.REDU1_%3$s, P.AMPLI1_%3$s\r\n"
				+ "FROM\r\n"
				+ "	PASO P\r\n"
				+ "WHERE\r\n"
				+ "	SUBSTR(P.PARTIDA,4,1) <> '0' AND P.IDSECTOR = %4$s\r\n"
				+ ") T1\r\n"
				+ "		INNER JOIN	TC_PERIODO P ON	T1.MES = P.PERIODO AND	P.TIPO_PERIODO = 1\r\n"
				+ "		INNER JOIN	FUENTEF F	ON	SUBSTR(T1.PROGRAMA,13,3) = F.LIGA AND	F.IDSECTOR = %4$s\r\n"
				+ "WHERE\r\n"
				+ "	T1.REDUCCION <> 0 OR T1.AMPLIACION <> 0\r\n"
				+ "ORDER BY\r\n"
				+ "	T1.CLAVE, T1.PROGRAMA,T1.MES");
		System.out.println("QUERY: "+ String.format(sSql.toString(), mes -2, mes -1, mes, this.getUserDetails().getIdSector()));
		return String.format(sSql.toString(), mes-2, mes -1, mes, this.getUserDetails().getIdSector());
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public StreamedContent getTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Map<String, Object> out;
		InputStream stream = null;
		
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(this.getUserDetails().getIdSector(), CLAVE_REPORT);
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		

		parameter.addValue("i_query", this.generateQueryTXT(trimestre * 3));
		parameter.addValue("i_trimestre", trimestre);
		parameter.addValue("i_fileName", getNameFileReport(trimestre, conctb, reporte));

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}

		return txt;
	}

	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public List<TcPeriodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<TcPeriodo> periodos) {
		this.periodos = periodos;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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
