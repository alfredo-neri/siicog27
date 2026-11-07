package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ReporteGenericoService;

@Component("aTMActividadProyecoMB")
@Scope("view")
public class ATMActividadProyecoMB extends AbstractMB {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private List<TcPeriodo> listTrimestres;
	private Integer trimestre;
	private String nameFile;
	private String pathName;
	private InputStream stream = null;
	private StreamedContent file;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepository;

	@Value("#{reporteGenericoService}")
	private ReporteGenericoService reporteGenericoService;

	@PostConstruct
	public void init() {
		listTrimestres = tcPeriodoRepository.findByTipoPeriodo(3);

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}

	}

	public void downloadTxt() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		nameFile = "AM" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";

		try {
			this.generateFile();
			PrimeFaces.current().executeScript(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService
				.getFileTxtWithSql(this.generateSql(trimestre, this.getUserDetails().getIdSector()), nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = StreamedContents.of(stream, "application/txt", pathName.substring(13), null);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
	}

	private String generateSql(Integer trimestre, Integer idSector) {

		StringBuilder sSql = new StringBuilder();
		String canAvan = "(";

		for (int y = 1; y <= trimestre; y++) {
			canAvan = canAvan + " DAT.CANT_AVAN_" + y + " +";
		}

		canAvan = canAvan.substring(0, canAvan.length() - 2) + " ) ACUMULADO ";

		sSql.append("SELECT   '\"'|| RES.CONC || '\"|\"'|| RES.RESTA || '\"|\"'|| RES.PORCENTAJE || '\"|\"'|| ")
				.append("RES.ACUMULADO || '\"|\"'|| (RES.PORCENTAJE-RES.ACUMULADO) || '\"|\"'|| ")
				.append("FN_GET_FORMAT_NUMBER(DECODE (0,RES.ACUMULADO,0,RES.PORCENTAJE,0,((RES.PORCENTAJE/RES.ACUMULADO)*100))) || '\"' ")
				.append("FROM (SELECT DAT.LOCBEN || '\"|\"'|| DAT.POBBEN || '\"|\"'|| DAT.CD1 || '\"|\"'|| DAT.CD2 || '\"|\"'|| ")
				.append("DAT.CN1 || '\"|\"'|| DAT.CN2 || '\"|\"'|| DAT.CN3 || '\"|\"'|| DAT.CN4 || '\"|\"'|| DAT.CN5 || '\"|\"'|| ")
				.append("DAT.CN6|| '\"|\"'|| DAT.CLVMET || '\"|\"'|| DAT.NOM_IND|| '\"|\"'|| DAT.UNI_MED|| '\"|\"'|| DAT.CAN_METI || '\"|\"'|| ")
				.append("DAT.CAN_METIC_2 || '\"|\"'|| DAT.CANT_AVAN_2 CONC, (DAT.CAN_METIC_2-DAT.CANT_AVAN_2) RESTA, ")
				.append("FN_GET_FORMAT_NUMBER(DECODE(DAT.CAN_METIC_2,0,0, (DAT.CANT_AVAN_2*100)/DAT.CAN_METIC_2)) PORCENTAJE, ")
				.append(canAvan)
				.append("FROM (SELECT NVL(PN.LOCBEN,'') LOCBEN , NVL(TO_CHAR(PN.POBBEN),'') POBBEN , SUBSTR(PP.CLVDEP,1,3)CD1 , ")
				.append("SUBSTR(PP.CLVDEP,4,3)CD2 , SUBSTR(PP.CLVNEP,1,2)CN1 , SUBSTR(PP.CLVNEP,3,2)CN2 , SUBSTR(PP.CLVNEP,5,2)CN3 , ")
				.append("SUBSTR(PP.CLVNEP,7,2)CN4 , SUBSTR(PP.CLVNEP,9,2)CN5 , SUBSTR(PP.CLVNEP,11,2)CN6, PP.CLVMET , PP.NOM_IND, ")
				.append("PP.UNI_MED, FN_GET_FORMAT_NUMBER(PP.CAN_METI) CAN_METI, FN_GET_FORMAT_NUMBER(PP.CAN_METIC_1) CAN_METIC_1, ")
				.append("FN_GET_FORMAT_NUMBER(PP.CAN_METIC_2) CAN_METIC_2, FN_GET_FORMAT_NUMBER(PP.CAN_METIC_3) CAN_METIC_3, ")
				.append("FN_GET_FORMAT_NUMBER(PP.CAN_METIC_4) CAN_METIC_4, FN_GET_FORMAT_NUMBER(PP.CANT_AVAN_1) CANT_AVAN_1, ")
				.append("FN_GET_FORMAT_NUMBER(PP.CANT_AVAN_2) CANT_AVAN_2, FN_GET_FORMAT_NUMBER(PP.CANT_AVAN_4) CANT_AVAN_3, ")
				.append("FN_GET_FORMAT_NUMBER(PP.CANT_AVAN_4) CANT_AVAN_4 FROM PP_METT PP LEFT OUTER JOIN PROGRAMAMUN PN ")
				.append("ON (PN.CVEDEPG||PN.CVEDEPA)=SUBSTR(PP.CLVDEP,1,6) AND PN.PROGRAMA=PP.CLVNEP AND PN.IDSECTOR=PP.IDSECTOR ")
				.append("WHERE PP.IDSECTOR = ").append(idSector).append(" ORDER BY PP.CLVDEP, PP.CLVNEP ) DAT)  RES");

		return sSql.toString();
	}

	public List<TcPeriodo> getListTrimestres() {
		return listTrimestres;
	}

	public void setListTrimestres(List<TcPeriodo> listTrimestres) {
		this.listTrimestres = listTrimestres;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
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
