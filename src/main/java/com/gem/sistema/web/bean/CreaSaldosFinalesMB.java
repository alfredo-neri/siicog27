package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CreaSaldosFinalesMB.
 */
@ManagedBean(name = "creaSaldosFinalesMB")
@ViewScoped
public class CreaSaldosFinalesMB extends GenericExecuteProcedure {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreaSaldosFinalesMB.class);
	private static final String DOWNLOAD_TXT = " jQuery('#saldosFinales\\\\:downTXT').click();";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_SALDOS_FINALES";
	private static final String VALID_PASSWORD = "ycreasal";

	private String enteredPassword;
	private StreamedContent fileTxt;

	@PostConstruct
	public void init() {
		LOGGER.info("Crea Saldos Finales MB");
	}

	public void validaPassword() {

		if (StringUtils.isBlank(enteredPassword)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El campo password esta vació.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else if (VALID_PASSWORD.equals(enteredPassword)) {
			try {
				this.downLoadFile();
				RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
			} catch (ReportValidationException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Ha ocurrido un error generando el archivo", "Favor de Contactar al Adminitrador");
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
			}

		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El password es incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());

		return parameter;
	}

	Map<String, Object> out;
	InputStream stream = null;

	@Override
	public void downLoadFile() throws ReportValidationException {
		out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
	}

	public String getEnteredPassword() {
		return enteredPassword;
	}

	public void setEnteredPassword(String enteredPassword) {
		this.enteredPassword = enteredPassword;
	}

	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

}
