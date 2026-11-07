package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.gem.sistema.business.service.catalogos.ConvertService;

@ManagedBean(name = "convertidorTxtMB")
@ViewScoped
public class ConvertidorTxtMB extends AbstractMB {

	@ManagedProperty("#{convertService}")
	private ConvertService convertService;

	private StreamedContent downloadFile;

	private UploadedFile file;
	private InputStream inputStrem;

	private String fullathName;
	private String outFileName;

	public void handleFileUpload(FileUploadEvent event) {
		inputStrem = null;
		if (null != outFileName) {
			file = event.getFile();
			try {

				fullathName = this.convertService.readExcel(file.getInputstream(), file.getFileName(), outFileName);
				inputStrem = new FileInputStream(new File(fullathName));
				downloadFile = new DefaultStreamedContent(inputStrem, "application/txt", outFileName + ".txt");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo requeriod", "EL nombre del Archivo es Obligatorio" );
			 FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public String getOutFileName() {
		return outFileName;
	}

	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public ConvertService getConvertService() {
		return convertService;
	}

	public void setConvertService(ConvertService convertService) {
		this.convertService = convertService;
	}

	public StreamedContent getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(StreamedContent downloadFile) {
		this.downloadFile = downloadFile;
	}

}
