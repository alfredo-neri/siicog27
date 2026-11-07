package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.dto.ReconducPresupEgrIngrDTO;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.presupuesto.ReconducPresupEgrIngrService;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class ReconducPresupEgrIngrTxtMB.
 */
@ManagedBean(name = "reconducPresupEgrIngrTxtMB")
@ViewScoped
public class ReconducPresupEgrIngrTxtMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReconducPresupEgrIngrTxtMB.class);

	/** The Constant MESSAGE_ERROR. */
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes. */
	private String tcMes;

	/** The tc mes repository. */
	// @Autowired
	@ManagedProperty(value = "#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The reconduccion presup egr ingr service. */
	@ManagedProperty(value = "#{reconducPresupEgrIngrService}")
	private ReconducPresupEgrIngrService reconduccionPresupEgrIngrService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ReportadorTxtMB ");
		listMes = tcMesRepository.findAll();
	}

	/**
	 * metodo que genera el reporte dinamico.
	 */
	public void generaReporte() {

		GemUser user = this.getUserDetails();
		ReconducPresupEgrIngrDTO reconducPresupEgrIngrDTO = this.reconduccionPresupEgrIngrService
				.getZippedFile(Integer.valueOf(tcMes), user.getUsername());

		if (reconducPresupEgrIngrDTO.getCodError() == 0) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, reconducPresupEgrIngrDTO.getMsgError());
		} else {
			this.sendFileToUser(reconducPresupEgrIngrDTO.getResultFile());

		}
	}

	/**
	 * Send file to user.
	 *
	 * @param fileToSend the file to send
	 */
	private void sendFileToUser(File fileToSend) {
		FileInputStream fileInputStream = null;
		OutputStream output = null;
		try {
			byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
			fileInputStream = new FileInputStream(fileToSend);
			fileInputStream.read(ingresoRespFile);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			String fileName = fileToSend.getName();
			fileToSend.delete();
			response.reset();
			response.setContentType("application/zip");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

			output = response.getOutputStream();
			output.write(ingresoRespFile);
			facesContext.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}

			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the tc mes.
	 *
	 * @return the tc mes
	 */
	public String getTcMes() {
		return tcMes;
	}

	/**
	 * Sets the tc mes.
	 *
	 * @param tcMes the new tc mes
	 */
	public void setTcMes(String tcMes) {
		this.tcMes = tcMes;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the reconduccion presup egr ingr service.
	 *
	 * @return the reconduccionPresupEgrIngrService
	 */
	public ReconducPresupEgrIngrService getReconduccionPresupEgrIngrService() {
		return reconduccionPresupEgrIngrService;
	}

	/**
	 * Sets the reconduccion presup egr ingr service.
	 *
	 * @param reconduccionPresupEgrIngrService the reconduccionPresupEgrIngrService to set
	 */
	public void setReconduccionPresupEgrIngrService(ReconducPresupEgrIngrService reconduccionPresupEgrIngrService) {
		this.reconduccionPresupEgrIngrService = reconduccionPresupEgrIngrService;
	}
	
	

}
