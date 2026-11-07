/**
 * 
 */
package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.gem.sistema.business.domain.TcImagenesEntAdmin;
import com.gem.sistema.business.domain.TcImagenesMuni;
import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;
import com.gem.sistema.business.repository.catalogs.TcImagenesEntAdminRepository;
import com.gem.sistema.business.repository.catalogs.TcImagenesMuniRepository;
import com.gem.sistema.business.service.callsp.CallSpService;
import com.gem.sistema.business.service.catalogos.TbProcesoService;
import com.gem.sistema.business.service.reportador.JasperGenericService;
import com.gem.sistema.business.service.reportador.ReporteadorDirecto;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;
import com.gem.sistema.web.util.GetFileInlineServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDirectReportByPL.
 */
@Component("baseDirectReportByPL")
@Scope("view")
public class BaseDirectReportByPL {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDirectReportByPL.class);

	/** The Constant MESSAGE_ERROR. */
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The call sp service. */
	@Value("#{callSpReportServiceImpl}")
	protected CallSpService callSpService;

	@Value("#{tbProcesoService}")
	private TbProcesoService procesoService;

	/**
	 * Gets the call sp service.
	 *
	 * @return the call sp service
	 */
	public CallSpService getCallSpService() {
		return callSpService;
	}

	/**
	 * Sets the call sp service.
	 *
	 * @param callSpService the new call sp service
	 */
	public void setCallSpService(CallSpService callSpService) {
		this.callSpService = callSpService;
	}

	/**
	 * getUserDetails Metodo para obtener el usuario logueado.
	 *
	 * @return the user details
	 */
	public GemUser getUserDetails() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			return null;
		}
	}

	/*
	 * public void sendFileToUser(File fileToSend) { FileInputStream fileInputStream
	 * = null; OutputStream output = null; try { byte[] ingresoRespFile = new
	 * byte[(int) fileToSend.length()]; fileInputStream = new
	 * FileInputStream(fileToSend); fileInputStream.read(ingresoRespFile);
	 * 
	 * FacesContext facesContext = FacesContext.getCurrentInstance();
	 * ExternalContext externalContext = facesContext.getExternalContext();
	 * HttpServletResponse response = (HttpServletResponse)
	 * externalContext.getResponse();
	 * 
	 * String fileName = fileToSend.getName(); fileToSend.delete();
	 * response.reset(); response.setContentType("application/zip");
	 * response.setHeader("Content-disposition", "attachment; filename=\"" +
	 * fileName + "\"");
	 * 
	 * output = response.getOutputStream(); output.write(ingresoRespFile);
	 * facesContext.responseComplete(); } catch (IOException e) {
	 * //LOGGER.error(e.getMessage(), e); throw new RuntimeException(e); } finally {
	 * if (null != fileInputStream) { try { fileInputStream.close(); } catch
	 * (IOException e) { LOGGER.error(e.getMessage(), e); } }
	 * 
	 * if (null != output) { try { output.close(); } catch (IOException e) {
	 * LOGGER.error(e.getMessage(), e); } } } }
	 */

	/**
	 * Send file by stream.
	 *
	 * @param fileToSend the file to send
	 * @return the streamed content
	 * @throws FileNotFoundException the file not found exception
	 */
	public StreamedContent sendFileByStream(File fileToSend) throws FileNotFoundException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return StreamedContents.of(new FileInputStream(fileToSend), externalContext.getMimeType(fileToSend.getName()), fileToSend.getName(), null);
	}

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

}
