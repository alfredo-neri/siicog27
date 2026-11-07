package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.service.presupuesto.PresIngresoCalendarizadoService;

// TODO: Auto-generated Javadoc
/**
 * The Class IngresosCalendarizadosTxtMB.
 *
 * @author Sam
 */
@Component(value = "ingresosCalendarizadosTxtMB")
@ViewScoped
public class IngresosCalendarizadosTxtMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IngresosCalendarizadosTxtMB.class);

	/** The pres ingreso calendarizado service. */
	@Autowired
	private PresIngresoCalendarizadoService presIngresoCalendarizadoService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct IngresosCalendarizadosTxtMB ");
	}

	/**
	 * Procesar txt ingresos.
	 */
	public void procesarTxtIngresos() {

		String nomUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

		String fileToSend = presIngresoCalendarizadoService.generarArchivoIngresos8110(nomUsuario);
		LOGGER.debug("proceso procesarTxtIngresos: " + fileToSend);
		sendFileToUser(fileToSend, nomUsuario);

	}

	/**
	 * Send file to user.
	 *
	 * @param fileToSend the file to send
	 * @param nomUsuario the nom usuario
	 */
	private void sendFileToUser(String fileToSend, String nomUsuario) {
		try {
			File ingresoResp = new File(fileToSend);
			byte[] ingresoRespFile = new byte[(int) ingresoResp.length()];
			FileInputStream fileInputStream = new FileInputStream(ingresoResp);
			fileInputStream.read(ingresoRespFile);
			fileInputStream.close();

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			String fileName = presIngresoCalendarizadoService.getNombreArchivo(nomUsuario);
			ingresoResp.delete();
			response.reset();
			response.setContentType("text/plain");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + ".txt\"");

			OutputStream output = response.getOutputStream();
			output.write(ingresoRespFile);
			output.close();
			facesContext.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the pres ingreso calendarizado service.
	 *
	 * @return the pres ingreso calendarizado service
	 */
	public PresIngresoCalendarizadoService getPresIngresoCalendarizadoService() {
		return presIngresoCalendarizadoService;
	}

	/**
	 * Sets the pres ingreso calendarizado service.
	 *
	 * @param presIngresoCalendarizadoService the new pres ingreso calendarizado service
	 */
	public void setPresIngresoCalendarizadoService(PresIngresoCalendarizadoService presIngresoCalendarizadoService) {
		this.presIngresoCalendarizadoService = presIngresoCalendarizadoService;
	}

}
