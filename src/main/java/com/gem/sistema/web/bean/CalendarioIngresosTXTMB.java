package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CalendarioIngresosTXTMB.
 */
@ManagedBean(name = "calendarioIngresosTXTMB")
@ViewScoped
public class CalendarioIngresosTXTMB extends GenericExecuteProcedure {

	/** The Constant NAME_PROCEDURE. */
	public static final String NAME_PROCEDURE = "SP_GENERA_CALENDARIO_INGRESO_TXT";

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";

	/** The firmas. */
	private Integer firmas;

	/** The list firmas. */
	private List<Integer> listFirmas;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Integer getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Integer firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the list firmas.
	 *
	 * @return the list firmas
	 */
	public List<Integer> getListFirmas() {
		return listFirmas;
	}

	/**
	 * Sets the list firmas.
	 *
	 * @param listFirmas the new list firmas
	 */
	public void setListFirmas(List<Integer> listFirmas) {
		this.listFirmas = listFirmas;
	}

	/**
	 * Gets the file txt.
	 *
	 * @return the file txt
	 */
	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the txt preview.
	 *
	 * @return the txt preview
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * Sets the txt preview.
	 *
	 * @param txtPreview the new txt preview
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	/**
	 * Gets the b pre view.
	 *
	 * @return the b pre view
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * Sets the b pre view.
	 *
	 * @param bPreView the new b pre view
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * Gets the generate preview service.
	 *
	 * @return the generate preview service
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * Sets the generate preview service.
	 *
	 * @param generatePreviewService the new generate preview service
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		procedureName = NAME_PROCEDURE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		parameters.addValue("i_idsector", idSector);
		parameters.addValue("i_firmas", firmas);
		parameters.addValue("i_municipio", conctb.getNomDep());
		return parameters;
	}

	/** The out parameters. */
	Map<String, Object> outParameters;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		addToBitacora(true);
		outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());

		if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(outParameters.get("O_RUTA_FILE").toString() + "/"
						+ outParameters.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt",
					outParameters.get("O_NAME_FILE").toString());

		} else {
			generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());

		}
		addToBitacora(false);
	}

	/**
	 * Pre view.
	 */
	public void preView() {
		addToBitacora(true);
		File newFile;
		File target;
		String fileNameOut = "";
		this.setbPreView(Boolean.TRUE);

		try {
			outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());
		} catch (ReportValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) == 0) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, outParameters.get("O_MSG_ERROR").toString());
		} else {
			fileNameOut = outParameters.get("O_RUTA_FILE").toString() + UUID.randomUUID() + ".txt";
			newFile = new File(
					outParameters.get("O_RUTA_FILE").toString() + "/" + outParameters.get("O_NAME_FILE").toString());
			target = new File(fileNameOut);

			try {
				ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
			RequestContext.getCurrentInstance().execute(FOCUS_IN_PREVIEW);
		}
		addToBitacora(false);
	}

}
