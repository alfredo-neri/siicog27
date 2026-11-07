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

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ReporteGenericoService;

@ManagedBean(name = "eAEPEMB")
@ViewScoped
public class EAEPEMB extends AbstractMB {

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
		listMeses = tcPeriodoRepository.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}

	}

	public void downloadTxt() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		nameFile = "EAEPE" + conctb.getClave() + conctb.getAnoemp() + StringUtils.leftPad(mes.toString(), 2, "0")
				+ ".txt";

		try {
			this.generateFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService
				.getFileTxtWithSql(this.generateSql(mes, this.getUserDetails().getIdSector()), nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
	}

	private String generateSql(Integer mes, Integer idSector) {

		StringBuilder sSql = new StringBuilder();
		String auto = "SUM(";
		String redu = "(";
		String ejpa = "SUM(";
		String ampli = "(";
		String ejxpa = "SUM(";
//		String toeje = "SUM(";

		for (int y = 1; y <= mes; y++) {
//			auto = auto + " P.AUTO1_" + y + " +";
			redu = redu + " P.REDU1_" + y + " +";
			ejpa = ejpa + " P.EJPA1_" + y + " +";
			ampli = ampli + " P.AMPLI1_" + y + " +";
			ejxpa = ejxpa + " P.EJXPA1_" + y + " +";
//			toeje = toeje + " P.TOEJE1_" + y + " +";
		}
		
		for (int y = 1; y <= 12; y++) {
			auto = auto + " P.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) EGRESO_APROBADO, ";
		redu = redu.substring(0, redu.length() - 2) + " ) ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
//		toeje = toeje.substring(0, toeje.length() - 2) + " ) SUBEJERCIDO ";

		sSql.append("SELECT '\"' || RES.PARTIDA||'\"|\"'|| RES.NOMGAS||'\"|\"'|| FN_GET_FORMAT_NUMBER(RES.EGRESO_APROBADO)||'\"|\"'|| ").append("FN_GET_FORMAT_NUMBER( RES.AMP_RDC)||'\"|\"'||")
				.append("FN_GET_FORMAT_NUMBER(RES.EGRESO_APROBADO+RES.AMP_RDC)||'\"|\"'|| ")
				.append("FN_GET_FORMAT_NUMBER(RES.DEVENGADO)||'\"|\"'|| FN_GET_FORMAT_NUMBER(RES.PAGADO)||'\"|\"'|| FN_GET_FORMAT_NUMBER((RES.EGRESO_APROBADO+RES.AMP_RDC) - RES.DEVENGADO)	||'\"' ")
				.append("FROM (SELECT P.PARTIDA, N.NOMGAS, ").append(auto).append("SUM(").append(ampli).append("-")
				.append(redu).append(") AMP_RDC, ").append(ejxpa).append(ejpa)
				.append("FROM PASO P, NATGAS N WHERE ")
				.append("P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR= ").append(idSector)
				.append(" GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2	) RES");

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