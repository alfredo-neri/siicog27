package com.gem.sistema.web.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.GeneraTxtService;
import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Rf005723MB.
 * 
 * @author Alfredo Neri
 *
 */
@ManagedBean
@ViewScoped
public class Rf005723MB extends BaseDirectReportByPL {

	private String fileName;

	private StreamedContent file;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{generaTxtService}")
	private GeneraTxtService generaTxtService;

	@PostConstruct
	public void init() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		fileName = "IC" + conctb.getClave() + conctb.getAnoemp() + ".txt";
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */

	public StreamedContent getFile() {
		InputStream stream = null;
		try {
			stream = new FileInputStream(
					this.generaTxtService.generaArtivoTxtFichaDisenio(this.getUserDetails().getIdSector(), fileName));
		} catch (FileNotFoundException ex) {

		}
		file = new DefaultStreamedContent(stream, Constants.APPLICCTION_TXT, fileName);
		return file;
	}

//	public StreamedContent getFile() {
//	StreamedContent streamedContent = null;
//	try {
//		GemUser user = this.getUserDetails();
//
//		ParamsSpDTO params = new ParamsGenTxtFichaDisenoDTO();
//		params.setUser(user.getUsername());
//
//		callSpService.getFile("SP_GENERA_TXT_FICHA_DISENO", params);
//
//		if (params.getCodError() == 0) {
//			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
//		} else {
//			streamedContent = sendFileByStream(params.getResultFile());
//		}
//	} catch (Exception e) {
//		LOGGER.error(e.getMessage(), e);
//		generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
//	}
//	return streamedContent;
//}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public GeneraTxtService getGeneraTxtService() {
		return generaTxtService;
	}

	public void setGeneraTxtService(GeneraTxtService generaTxtService) {
		this.generaTxtService = generaTxtService;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
