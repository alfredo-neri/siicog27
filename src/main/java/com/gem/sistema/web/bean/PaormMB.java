package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.ReporteGenericoService;

@ManagedBean(name = "paormMB")
@ViewScoped
public class PaormMB extends AbstractMB {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	private static final Integer ID_REPORTE = 24;

	private String nameFile;
	private String pathName;
	private InputStream stream = null;
	private StreamedContent file;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{reporteGenericoService}")
	private ReporteGenericoService reporteGenericoService;

	@PostConstruct
	public void init() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		nameFile = "PAORM" + conctb.getClave() + conctb.getAnoemp() + ".txt";
	}

	public void downloadTxt() {
		try {
			this.generateFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService.getFileTxtWithIdSector(ID_REPORTE, this.getUserDetails().getIdSector(),
				nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
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

	public ReporteGenericoService getReporteGenericoService() {
		return reporteGenericoService;
	}

	public void setReporteGenericoService(ReporteGenericoService reporteGenericoService) {
		this.reporteGenericoService = reporteGenericoService;
	}

}
