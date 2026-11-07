package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.callsp.ParamsRF00914CDTO;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class RF00914CMB.
 */
@ManagedBean
@ViewScoped
public class RF00914CMB extends BaseDirectReportByPL {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/** The Constant FOCUS_PREVIEW. */
	private static final String FOCUS_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";

	/** The Constant charSetISO. */
	private static final String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private static final String charSetUFT = "UTF-8";

	/** The mes. */
	// private static final String SEPARTOR = "";
	private Integer mes;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
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
	 * Gets the file.
	 *
	 * @return the file
	 */
	public StreamedContent getFile() {
		addToBitacora(true);
		this.setbPreView(Boolean.FALSE);
		StreamedContent streamedContent = null;
		try {
			GemUser user = this.getUserDetails();

			ParamsRF00914CDTO params = new ParamsRF00914CDTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			params.setNomMun(user.getMunicipio());

			callSpService.getFile("SP_REPORTE_RF00914_BALANZA", params);

			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = sendFileByStream(params.getResultFile());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}
		addToBitacora(false);
		return streamedContent;
	}

	/**
	 * Pre view.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void preView() throws IOException {
		addToBitacora(true);
		this.setbPreView(Boolean.TRUE);
		GemUser user = this.getUserDetails();
		ParamsRF00914CDTO params = new ParamsRF00914CDTO();
		params.setIdSector(user.getIdSector());
		params.setMes(mes);
		params.setNomMun(user.getMunicipio());

		callSpService.getFile("SP_REPORTE_RF00914_BALANZA", params);

		if (params.getCodError() == 0) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
		} else {

			File source = new File(
					params.getPathFileOut().toString() + File.separator + params.getFileNameOut().toString());
			File target = new File("/gem/reportes/t.txt");
			ConvertCharsetUtils.transform(source, charSetUFT, target, charSetISO);

			txtPreview = this.generatePreviewService.generatePreview("/gem/reportes/t.txt");
			RequestContext.getCurrentInstance().execute(FOCUS_PREVIEW);

		}
		addToBitacora(false);
	}

	public void downloadTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector());
	}

	public void viewTxt() throws IOException {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.preView();
		}
	}

}
