package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.TbProcesoService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.util.FrontProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class GenericExecuteProcedure.
 */
@Component("genericExecuteProcedure")
@Scope("view")
public abstract class GenericExecuteProcedure extends AbstractMB {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDirectReport.class);

	/** The Constant MESSAGE_ERROR. */
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The procedure name. */
	protected String procedureName;

	/** The file name. */
	protected String fileName;

	/** The execute pl service. */
	@Value("#{executePlService}")
	protected ExecutePlService executePlService;

	@Value("#{tbProcesoService}")
	private TbProcesoService procesoService;

	/**
	 * Gets the procedure name.
	 *
	 * @return the procedure name
	 */
	public String getProcedureName() {
		return procedureName;
	}

	/**
	 * Sets the procedure name.
	 *
	 * @param procedureName the new procedure name
	 */
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	/**
	 * Gets the parameters reports.
	 *
	 * @return the parameters reports
	 * @throws ReportValidationException the report validation exception
	 */
	abstract public SqlParameterSource getParametersReports() throws ReportValidationException;

	/**
	 * Down load file.
	 *
	 * @throws ReportValidationException the report validation exception
	 */
	abstract public void downLoadFile() throws ReportValidationException;

	/**
	 * Registra el tiempo de constrcucion del reporte
	 * <li>true para iniciar</i>
	 * <li>false para terminar</i>
	 */
	protected void addToBitacora(Boolean endOrStart) {
		if (this.getUserDetails().getIdSector() == 2) {
			if (endOrStart) {
				// Optiene el path de la vista que invoco la construcion del reporte
				HttpServletRequest servletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
						.getExternalContext().getRequest();
				// Optiene el titulo del reporte del front
				UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
				UIComponent component = viewRoot.findComponent("form1:panelReporte");
				if (component == null) {
					component = viewRoot.findComponent("form1:panel");
				}

				String titulo = component == null ? "" : (String) component.getAttributes().get("header");
				this.procesoService.start(titulo, this.getUserDetails().getUsername(), servletRequest.getRequestURI());
			} else {
				this.procesoService.end();
			}
		}
	}

	public TbProcesoService getProcesoService() {
		return procesoService;
	}

	public void setProcesoService(TbProcesoService procesoService) {
		this.procesoService = procesoService;
	}

//	public StreamedContent getFileTxt() {
//		StreamedContent streamedContent = null;
//		LOGGER.info(":: Generando reporte de texto plano getFileTxt");
//		// media = null;
//		try {
//			//streamedContent =  generaReporteSimple(ReporteadorDirecto.TXT);
//			final FacesContext context = FacesContext.getCurrentInstance();
//			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
//			Map<String, Object> out = executePlService.callProcedure(procedureName, getParametersReports());
//			
//			final String pathFile = servletContext.getRealPath(REPORT_PATH_JASPER +  fileName  );
//			streamedContent =  downLoadFile();
//		} catch (final Exception genericExc) {
//			LOGGER.error("getFileTxt", genericExc);
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, "Hubo un error al generar el reporte, contacte a su administrador.");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//			genericExc.printStackTrace();
//		}
//		return streamedContent;
//	}
//	
//	public InputStream getOutPutFile(String pathFile){
//		InputStream stream = null;
//		final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//		
//		stream = new BByteArrayInputStream(arrayOutputStream.toByteArray());
//		return stream;
//	}

}
