package com.gem.sistema.web.bean;


import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ReporteGenericoService;

@ManagedBean(name = "edoAnaIngresosMB")
@ViewScoped
public class EdoAnaIngresosMB extends AbstractMB {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private List<TcPeriodo> listMeses;
	private Integer mes;
	private String nameFile;
	private String pathName;
	private InputStream stream = null;
	private StreamedContent file;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepository;

	@ManagedProperty("#{reporteGenericoService}")
	private ReporteGenericoService reporteGenericoService;
	
	@PostConstruct
	public void init() {
		listMeses  = tcPeriodoRepository.findByTipoPeriodo(1);
		
		if(!listMeses.isEmpty()) {
			mes =  listMeses.get(0).getPeriodo();
		}
	}

	public void downloadTxt() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		nameFile = "EAI" + conctb.getClave() + conctb.getAnoemp()
				+ org.apache.commons.lang3.StringUtils.leftPad(mes.toString(), 2, "0") + ".txt";

		try {
			this.generateFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService
				.getFileTxtWithSql(this.generateSql(this.getUserDetails().getIdSector(), mes), nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
	}

	private String generateSql(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();

		String cargos = "(";
		String abonos = "(";

		for (int y = 1; y <= mes; y++) {
			cargos = cargos + " CU.CARGOS_" + y + " +";
			abonos = abonos + " CU.ABONOS_" + y + " +";
		}

		cargos = cargos.substring(0, cargos.length() - 2) + " ) ";
		abonos = abonos.substring(0, abonos.length() - 2) + " ) ";

		sSql.append("SELECT '\"'||T2.CUENTA||'\"|\"'||\n").append(
				"			TRIM(SUBSTR(T2.SCTA,9,2))||'\"|\"'||\n").append(
				"			TRIM(SUBSTR(T2.SSCTA,14,2))||'\"|\"'||\n").append(
				"			TRIM(SUBSTR(T2.SSSCTA,3,2))||'\"|\"'||\n").append(
				"			T2.NOMCTA||'\"|\"'||\n").append(
				"			FN_GET_FORMAT_NUMBER(T2.ESTIMADO)||'\"|\"'||\n").append(
				"			FN_GET_FORMAT_NUMBER(T2.AMPLI_REDU)||'\"|\"'||\n").append(
				"			FN_GET_FORMAT_NUMBER(T2.MODIFICADO)||'\"|\"'||\n").append(
				"			FN_GET_FORMAT_NUMBER(T2.DEVENGADO)||'\"|\"'||\n").append(
				"			FN_GET_FORMAT_NUMBER(T2.RECAUDADO)||'\"|\"'||\n").append(
				"			FN_GET_FORMAT_NUMBER(T2.DIFERENCIA)||'\"'\n").append(
				"	FROM (\n").append(
				"			SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, \n").append(
				"					MAX(T1.NOMCTA) NOMCTA, \n").append(
				"					SUM(T1.ESTIMADO) ESTIMADO,\n").append(
				"					SUM(T1.AMPLI_REDU) AMPLI_REDU,\n").append(
				"					SUM(T1.MODIFICADO) MODIFICADO,\n").append(
				"					SUM(T1.DEVENGADO) DEVENGADO,\n").append(
				"					SUM(T1.RECAUDADO) RECAUDADO,\n").append(
				"					SUM(T1.RECAUDADO - T1.MODIFICADO) DIFERENCIA\n").append(
				"				FROM (\n").append(
				"						SELECT 	DECODE(SUBSTR(CU.CUENTA,1,1),'4',CU.CUENTA, SUBSTR(CU.SCTA,7)) CUENTA, \n").append(
				"								DECODE(SUBSTR(CU.CUENTA,1,1),'4',CU.SCTA, SUBSTR(CU.SSCTA,6)) SCTA, \n").append(
				"								DECODE(SUBSTR(CU.CUENTA,1,1),'4',CU.SSCTA,DECODE(CU.SSSCTA,'','',LPAD(CU.SSSCTA,15,'0')))SSCTA, \n").append(
				"								DECODE(SUBSTR(CU.CUENTA,1,1),'4',CU.SSSCTA,DECODE(CU.SSSSCTA,'','',LPAD(CU.SSSSCTA,4,'0')))SSSCTA, \n").append(
				"								DECODE(SUBSTR(CU.CUENTA,1,1),'4',CU.SSSSCTA,'')SSSSCTA, \n").append(
				"								DECODE(SUBSTR(CU.CUENTA,1,1),'4',CU.NOMCTA,'')NOMCTA, --CU.STACTA,\n").append(
				"								DECODE(CU.CUENTA, '8110',CU.SALINI,0) ESTIMADO,\n").append(
				"								DECODE(CU.CUENTA, '8110',( ").append(cargos).append(" - ").append(abonos).append(" ), 0) AMPLI_REDU,\n").append(
				"								DECODE(CU.CUENTA, '8110',(CU.SALINI + ").append(cargos).append(" - ").append(abonos).append(" ),0) MODIFICADO,\n").append(
				"								DECODE(CU.CUENTA, '8140',( ").append(abonos).append(" - ").append(cargos).append(" ),0) DEVENGADO,\n").append(
				"								DECODE(CU.CUENTA, '8150',( ").append(abonos).append(" - ").append(cargos).append(" ),0) RECAUDADO\n").append(
				"							FROM CUENTA CU\n").append(
				"						WHERE CU.IDSECTOR= ").append(this.getUserDetails().getIdSector()).append(
				"							AND(SUBSTR(CU.CUENTA,1,1) = 4 OR\n").append(
				"								SUBSTR(CU.CUENTA,1,2) = '81' )\n").append(
				"					)T1\n").append(
				"			GROUP BY T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA\n").append(
				"	)T2\n").append(
				"WHERE CUENTA <> ''\n").append(
				"ORDER BY T2.CUENTA, T2.SCTA, T2.SSCTA, T2.SSSCTA");
		
		return sSql.toString();
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getTcPeriodoRepository() {
		return tcPeriodoRepository;
	}

	public void setTcPeriodoRepository(TcPeriodoRepositoy tcPeriodoRepository) {
		this.tcPeriodoRepository = tcPeriodoRepository;
	}

	public ReporteGenericoService getReporteGenericoService() {
		return reporteGenericoService;
	}

	public void setReporteGenericoService(ReporteGenericoService reporteGenericoService) {
		this.reporteGenericoService = reporteGenericoService;
	}

}