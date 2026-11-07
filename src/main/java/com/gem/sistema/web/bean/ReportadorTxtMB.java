package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.domain.TrReportesLog;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.ReportesLogRepository;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;
import com.gem.sistema.business.service.reportador.ReportadorTXTService;
import com.gem.sistema.business.service.reportador.ReportadorTXTServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportadorTxtMB.
 */
@ManagedBean(name = "reportadortxtMB")
@ViewScoped
public class ReportadorTxtMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportadorTxtMB.class);

	/** The reportid. */
	private int reportid;

	private Boolean dTxt;

	/** The nombre reporte. */
	private String nombreReporte;

	/** The boton label. */
	private String botonLabel;

	/** The tc reporte. */
	private TcReporte tcReporte;

	/** The reportador TXT service. */
	@ManagedProperty("#{reportadorTXTService}")
	private ReportadorTXTService reportadorTXTService;

	/** The reportes repository. */
	@ManagedProperty("#{reportesRepository}")
	private ReportesRepository reportesRepository;

	/** The reportes log repository. */
	@ManagedProperty("#{reportesLogRepository}")
	private ReportesLogRepository reportesLogRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ReportadorTxtMB ");
	}

	/**
	 * metodo que pone el nombre del titulo dinamico y el boton a mostrar.
	 */
	public void initNombreDinamico() {
		long reporteId = reportid;
		this.tcReporte = this.reportesRepository.findOne(reporteId);
		if (null == this.tcReporte) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("El id del reporte no se encuentra en la base de datos"));
		} else {

			this.setNombreReporte(this.tcReporte.getNombreReporte());
			this.setBotonLabel(this.tcReporte.getBotonLabel());

		}

	}

	/**
	 * metodo que genera el reporte dinamico.
	 */
	public void generaReporte() {
		TrReportesLog reportLog = new TrReportesLog();
		reportLog.setTcReporte(tcReporte);
		reportLog.setFechaIni(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		reportLog.setUsuario(getUserDetails().getUsername());
		reportesLogRepository.save(reportLog);
		String nombreArchivoFormato = this.obtieneNombreArchivoComplete();
		// List<String> lista = reportadorTXTService.ejecutaQueryNativo(this.tcReporte);
		/*
		 * if (lista.size() == 0) { FacesContext context =
		 * FacesContext.getCurrentInstance(); context.addMessage(null, new
		 * FacesMessage("No existen registros para generar el reporte")); } else {
		 */
		try {
			// guarda el archivo
			// reportLog.setCantRegistros(lista.size());
			// this.sendFileToUser(
			// reportadorTXTService.saveTxtReport(lista,
			// tcReporte.getRutaArchivo(), nombreArchivoFormato));
			String fileNameBuff = FilenameUtils.getBaseName(nombreArchivoFormato) + UUID.randomUUID()
					+ FilenameUtils.EXTENSION_SEPARATOR_STR + FilenameUtils.getExtension(nombreArchivoFormato);
			this.sendFileToUser(
					reportadorTXTService.ejecutaQueryNativo(tcReporte, tcReporte.getRutaArchivo(), fileNameBuff),
					nombreArchivoFormato);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error al generar reporte"));
		}
		// }
		reportLog.setFechaFin(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		// guarda en el log
		reportesLogRepository.save(reportLog);
	}

	/**
	 * Genera reporte 2.
	 */
	public void generaReporte2() {

		List<String> lista = reportadorTXTService.ejecutaQueryNativo(this.tcReporte);
		if (lista.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("No existen registros para generar el reporte"));
		} else {
			RequestContext.getCurrentInstance().execute("jQuery('#formTable\\\\:hidden2).click();");
		}

	}

	/**
	 * Gets the reporte.
	 *
	 * @return the reporte
	 */
	public StreamedContent getReporte() {
		TrReportesLog reportLog = new TrReportesLog();
		reportLog.setTcReporte(tcReporte);
		reportLog.setFechaIni(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		reportLog.setUsuario(getUserDetails().getUsername());
		reportesLogRepository.save(reportLog);
		String nombreArchivoFormato = this.obtieneNombreArchivoComplete();
		List<String> lista = reportadorTXTService.ejecutaQueryNativo(this.tcReporte);
		StreamedContent toReturn = new DefaultStreamedContent();
		if (lista.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("No existen registros para generar el reporte"));
		} else {
			FileInputStream fileInputStream = null;
			OutputStream output = null;
			try {
				// guarda el archivo
				reportLog.setCantRegistros(lista.size());
				String fileNameBuff = FilenameUtils.getBaseName(nombreArchivoFormato) + UUID.randomUUID()
						+ FilenameUtils.EXTENSION_SEPARATOR_STR + FilenameUtils.getExtension(nombreArchivoFormato);

				File fileToSend = reportadorTXTService.ejecutaQueryNativo(tcReporte, tcReporte.getRutaArchivo(),
						fileNameBuff);

				byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
				fileInputStream = new FileInputStream(fileToSend);
				fileInputStream.read(ingresoRespFile);
				toReturn = new DefaultStreamedContent(fileInputStream, "text/plain", nombreArchivoFormato,
						ReportadorTXTServiceImpl.CHARSET);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error al generar reporte"));
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
		reportLog.setFechaFin(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		// guarda en el log
		reportesLogRepository.save(reportLog);
		return toReturn;
	}

	/**
	 * Send file to user.
	 *
	 * @param fileToSend the file to send
	 * @param fileName   the file name
	 */
	private void sendFileToUser(File fileToSend, String fileName) {
		FileInputStream fileInputStream = null;
		OutputStream output = null;
		try {
			byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
			fileInputStream = new FileInputStream(fileToSend);
			fileInputStream.read(ingresoRespFile);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			// String fileName = fileToSend.getName();
			fileToSend.delete();
			response.reset();
			response.setContentType("text/plain");
			response.setCharacterEncoding(ReportadorTXTServiceImpl.CHARSET);
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
	 * Obtiene nombre archivo complete.
	 *
	 * @return the string
	 */
	public String obtieneNombreArchivoComplete() {
		Calendar fecha = Calendar.getInstance();
		String nombreArchivo = this.tcReporte.getNombreArchivo();
		String cveEntidad = "";// this.getUserDetails().getIdManagementAdmin().toString().substring(0, 1);
		String entidadMunicipal = conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave();// StringUtils.leftPad(this.getUserDetails().getIdMunicipio().toString(),
																													// 3,
																													// "0");
		String anio = Integer
				.toString(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
		return String.format(nombreArchivo, entidadMunicipal, cveEntidad, anio);
	}

	/**
	 * Gets the reportid.
	 *
	 * @return the reportid
	 */
	public int getReportid() {
		return reportid;
	}

	/**
	 * Sets the reportid.
	 *
	 * @param reportid the new reportid
	 */
	public void setReportid(int reportid) {
		this.reportid = reportid;
	}

	/**
	 * Gets the nombre reporte.
	 *
	 * @return the nombre reporte
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}

	/**
	 * Sets the nombre reporte.
	 *
	 * @param nombreReporte the new nombre reporte
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	/**
	 * Gets the boton label.
	 *
	 * @return the boton label
	 */
	public String getBotonLabel() {
		return botonLabel;
	}

	/**
	 * Sets the boton label.
	 *
	 * @param botonLabel the new boton label
	 */
	public void setBotonLabel(String botonLabel) {
		this.botonLabel = botonLabel;
	}

	/**
	 * Gets the reportador TXT service.
	 *
	 * @return the reportador TXT service
	 */
	public ReportadorTXTService getReportadorTXTService() {
		return reportadorTXTService;
	}

	/**
	 * Sets the reportador TXT service.
	 *
	 * @param reportadorTXTService the new reportador TXT service
	 */
	public void setReportadorTXTService(ReportadorTXTService reportadorTXTService) {
		this.reportadorTXTService = reportadorTXTService;
	}

	/**
	 * Gets the reportes repository.
	 *
	 * @return the reportes repository
	 */
	public ReportesRepository getReportesRepository() {
		return reportesRepository;
	}

	/**
	 * Sets the reportes repository.
	 *
	 * @param reportesRepository the new reportes repository
	 */
	public void setReportesRepository(ReportesRepository reportesRepository) {
		this.reportesRepository = reportesRepository;
	}

	/**
	 * Gets the reportes log repository.
	 *
	 * @return the reportes log repository
	 */
	public ReportesLogRepository getReportesLogRepository() {
		return reportesLogRepository;
	}

	/**
	 * Sets the reportes log repository.
	 *
	 * @param reportesLogRepository the new reportes log repository
	 */
	public void setReportesLogRepository(ReportesLogRepository reportesLogRepository) {
		this.reportesLogRepository = reportesLogRepository;
	}

	/**
	 * Gets the tc reporte.
	 *
	 * @return the tc reporte
	 */
	public TcReporte getTcReporte() {
		return tcReporte;
	}

	/**
	 * Sets the tc reporte.
	 *
	 * @param tcReporte the new tc reporte
	 */
	public void setTcReporte(TcReporte tcReporte) {
		this.tcReporte = tcReporte;
	}

	public Boolean getdTxt() {
		return dTxt;
	}

	public void setdTxt(Boolean dTxt) {
		this.dTxt = dTxt;
	}

}
