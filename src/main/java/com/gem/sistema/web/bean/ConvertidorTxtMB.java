package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.gem.sistema.business.service.catalogos.ConvertService;

@Component("convertidorTxtMB")
@Scope("view")
public class ConvertidorTxtMB extends AbstractMB {

	@Value("#{convertService}")
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

				fullathName = this.convertService.readExcel(file.getInputStream(), file.getFileName(), outFileName);
				inputStrem = new FileInputStream(new File(fullathName));
				downloadFile = StreamedContents.of(inputStrem, "application/txt", outFileName + ".txt", null);

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
