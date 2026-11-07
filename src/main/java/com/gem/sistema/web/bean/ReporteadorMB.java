package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReporte;

import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.GeneraTxtService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "reporteadorMB")
@ViewScoped
public class ReporteadorMB extends BaseDirectReport {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteadorMB.class);

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{generaTxtService}")
	private GeneraTxtService generaTxtService;

	private StreamedContent file;
	/** The stream. */
	InputStream stream = null;

	private long idReporte;
	private Integer trimestre;

	private List<TcPeriodo> listPeriodo;

	@PostConstruct
	public void init() {
		listPeriodo = this.tcPeriodoRepositoy.findByTipoPeriodo(3);
		trimestre = listPeriodo.get(0).getPeriodo();

	}

	public void downloadFileTxt() {
		String filePath = generaTxtService.generatArchivoTxt(idReporte, this.getUserDetails().getIdSector(), trimestre);
		try {
			stream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file = new DefaultStreamedContent(stream, "application/txt", filePath.substring(14));
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Genero el Archivo: " + filePath.substring(14));

	}

	public void downloadTxt() {
		try {
			this.downloadFileTxt();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public void setIdReporte(long idReporte) {
		this.idReporte = idReporte;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public List<TcPeriodo> getListPeriodo() {
		return listPeriodo;
	}

	public void setListPeriodo(List<TcPeriodo> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}

	public TcReporte getTcReporte() {
		return tcReporte;
	}

	public void setTcReporte(TcReporte tcReporte) {
		this.tcReporte = tcReporte;
	}

	public GeneraTxtService getGeneraTxtService() {
		return generaTxtService;
	}

	public void setGeneraTxtService(GeneraTxtService generaTxtService) {
		this.generaTxtService = generaTxtService;
	}

}
