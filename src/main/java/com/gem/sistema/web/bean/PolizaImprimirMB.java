package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.predicates.PolizaPredicates;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaImprimirMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean(name = "polizaImprimirMB")
@ViewScoped
public class PolizaImprimirMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaImprimirMB.class);

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${view.report.path.jasper.poliza}")
	private static final String REPORT_PATH_JASPER_POLICY = FrontProperty
			.getPropertyValue("view.report.path.jasper.poliza");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.poliza}")
	private static final String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.poliza");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The poliza service. */
	@ManagedProperty("#{polizaService}")
	private PolizaService polizaService;

	/** The cattpo repositry. */
	@ManagedProperty("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes. */
	private String tcMes;

	/** The cattpo. */
	private String cattpo;

	/** The min conpol. */
	private Integer minConpol;

	/** The max conpol. */
	private Integer maxConpol;

	/** The render pdf. */
	private boolean renderPdf = Boolean.FALSE;

	/** The streamed content. */
	private StreamedContent streamedContent;

	/** The file pdf path. */
	private String filePdfPath = "/tmp/m-dpolizasd.pdf";

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PolizaImprimirMB ");
		lisCattpo = cattpoRepositry.findAll();
		listMes = tcMesRepository.findAll();
		for (Cattpo c : lisCattpo) {
			LOGGER.info("polizaMantenimientoMB" + c.getTippol());
		}
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina PolizaImprimirMB  ");

	}

	/**
	 * Gets the poliza service.
	 *
	 * @return the poliza service
	 */
	public PolizaService getPolizaService() {
		return polizaService;
	}

	/**
	 * Sets the poliza service.
	 *
	 * @param polizaService the new poliza service
	 */
	public void setPolizaService(PolizaService polizaService) {
		this.polizaService = polizaService;
	}

	/**
	 * Gets the cattpo repositry.
	 *
	 * @return the cattpo repositry
	 */
	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	/**
	 * Sets the cattpo repositry.
	 *
	 * @param cattpoRepositry the new cattpo repositry
	 */
	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
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
	 * Gets the lis cattpo.
	 *
	 * @return the lis cattpo
	 */
	public List<Cattpo> getLisCattpo() {
		return lisCattpo;
	}

	/**
	 * Sets the lis cattpo.
	 *
	 * @param lisCattpo the new lis cattpo
	 */
	public void setLisCattpo(List<Cattpo> lisCattpo) {
		this.lisCattpo = lisCattpo;
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
	 * Gets the min conpol.
	 *
	 * @return the min conpol
	 */
	public Integer getMinConpol() {
		return minConpol;
	}

	/**
	 * Sets the min conpol.
	 *
	 * @param minConpol the new min conpol
	 */
	public void setMinConpol(Integer minConpol) {
		this.minConpol = minConpol;
	}

	/**
	 * Gets the max conpol.
	 *
	 * @return the max conpol
	 */
	public Integer getMaxConpol() {
		return maxConpol;
	}

	/**
	 * Sets the max conpol.
	 *
	 * @param maxConpol the new max conpol
	 */
	public void setMaxConpol(Integer maxConpol) {
		this.maxConpol = maxConpol;
	}

	/**
	 * Button action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonAction(ActionEvent actionEvent) {
		String msg = this.validate();
		if (StringUtils.isEmpty(msg)) {
			this.setRenderPdf(Boolean.TRUE);
			this.streamedContent = new DefaultStreamedContent(
					this.polizaService.getReportByTipopolMespolConpol(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
							"escudo_ecatepec.png", this.cattpo, Integer.valueOf(this.tcMes), this.minConpol,
							this.maxConpol, getUserDetails().getIdSector(), this.getUserDetails().getUsername()),
					"application/pdf");
			try {
				this.filePdfPath = this.polizaService.savePDFFile(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
						"escudo_ecatepec.png", this.cattpo, Integer.valueOf(this.tcMes), this.minConpol, this.maxConpol,
						getUserDetails().getIdSector(), this.getUserDetails().getUsername());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al generar el archivo PDF.");
			}
		} else {
			this.setRenderPdf(Boolean.FALSE);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, msg);
		}
	}

	/**
	 * Gets the streamed content.
	 *
	 * @return the streamed content
	 */
	public StreamedContent getStreamedContent() {
		if (streamedContent != null)
			try {
				streamedContent.getStream().reset();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		return streamedContent;
	}

	/**
	 * Sets the streamed content.
	 *
	 * @param streamedContent the new streamed content
	 */
	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	/**
	 * Validate.
	 *
	 * @return the string
	 */
	private String validate() {
		String toReturn = null;
		if (this.minConpol > this.maxConpol) {
			toReturn = "La póliza inicial no puede ser mayor de la póliza final";
		} else {
			if (this.polizaService.countByTipopolMespolConpol(this.cattpo, Integer.valueOf(this.tcMes), this.minConpol,
					this.maxConpol, this.getUserDetails().getIdSector()) == 0) {
				toReturn = "No existen pólizas con los datos seleccionados";
			}
		}
		return toReturn;
	}

	/**
	 * Gets the user details.
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

	/**
	 * Checks if is render pdf.
	 *
	 * @return true, if is render pdf
	 */
	public boolean isRenderPdf() {
		return renderPdf;
	}

	/**
	 * Sets the render pdf.
	 *
	 * @param renderPdf the new render pdf
	 */
	public void setRenderPdf(boolean renderPdf) {
		this.renderPdf = renderPdf;
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
	 * Gets the cattpo.
	 *
	 * @return the cattpo
	 */
	public String getCattpo() {
		return cattpo;
	}

	/**
	 * Sets the cattpo.
	 *
	 * @param cattpo the new cattpo
	 */
	public void setCattpo(String cattpo) {
		this.cattpo = cattpo;
	}

	/**
	 * Gets the file pdf path.
	 *
	 * @return the file pdf path
	 */
	public String getFilePdfPath() {
		return filePdfPath;
	}

	/**
	 * Sets the file pdf path.
	 *
	 * @param filePdfPath the new file pdf path
	 */
	public void setFilePdfPath(String filePdfPath) {
		this.filePdfPath = filePdfPath;
	}

}
