package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.predicates.ParametrosPredicate;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.business.service.catalogos.PolizaServiceImpl;
import com.gem.sistema.business.service.reportador.ReportadorTXTServiceImpl;
//import com.ibm.icu.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class AfectacionPolizaMB.
 */
@ManagedBean(name = "afectacionPolizaMB")
@ViewScoped
public class AfectacionPolizaMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AfectacionPolizaMB.class);

	private static final String DATA_TYPE = "PASSWORD";
	private static final String KEY_PASS = "PASS_AFECTA";
	private static final String KEY_PASS_1 = "PASS_AFECTA1";

	/** The mes. */
	private Integer mes;

	/** The lis mes. */
	private List<TcMes> lisMes;

	@ManagedProperty("#{passwordEncoder}")
	private PasswordEncoder passwordEncoder;

	/** The tc mes repository. */
	@ManagedProperty(value = "#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The poliza service. */
	@ManagedProperty(value = "#{polizaService}")
	private PolizaService polizaService;

	/** The polien repository. */
	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	@ManagedProperty("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/** The download file. */
	private StreamedContent downloadFile;

	/** The activar button. */
	private boolean activarButton = Boolean.TRUE;

	/** The active dowload. */
	private boolean activeDowload = Boolean.TRUE;

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Checks if is active dowload.
	 *
	 * @return true, if is active dowload
	 */
	public boolean isActiveDowload() {
		return activeDowload;
	}

	/**
	 * Sets the active dowload.
	 *
	 * @param activeDowload the new active dowload
	 */
	public void setActiveDowload(boolean activeDowload) {
		this.activeDowload = activeDowload;
	}

	/** The parametros repo. */
	private ParametrosRepository parametrosRepo;

	/** The file name. */
	private String fileName;

	/** The path file. */
	private String pathFile;

	/** The poliza. */
	private Poliza poliza;

	/** The pass afectacion. */
	private String passAfectacion;

	/** The pass. */
	private String pass;

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass the new pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Gets the pass afectacion.
	 *
	 * @return the pass afectacion
	 */
	public String getPassAfectacion() {
		return passAfectacion;
	}

	/**
	 * Sets the pass afectacion.
	 *
	 * @param passAfectacion the new pass afectacion
	 */
	public void setPassAfectacion(String passAfectacion) {
		this.passAfectacion = passAfectacion;
	}

	/**
	 * Gets the poliza.
	 *
	 * @return the poliza
	 */
	public Poliza getPoliza() {
		return poliza;
	}

	/**
	 * Sets the poliza.
	 *
	 * @param poliza the new poliza
	 */
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	/**
	 * Gets the path file.
	 *
	 * @return the path file
	 */
	public String getPathFile() {
		return pathFile;
	}

	/**
	 * Sets the path file.
	 *
	 * @param pathFile the new path file
	 */
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
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
	 * Gets the parametros repo.
	 *
	 * @return the parametros repo
	 */
	public ParametrosRepository getParametrosRepo() {
		return parametrosRepo;
	}

	/**
	 * Sets the parametros repo.
	 *
	 * @param parametrosRepo the new parametros repo
	 */
	public void setParametrosRepo(ParametrosRepository parametrosRepo) {
		this.parametrosRepo = parametrosRepo;
	}

	/**
	 * Checks if is activar button.
	 *
	 * @return true, if is activar button
	 */
	public boolean isActivarButton() {
		return activarButton;
	}

	/**
	 * Sets the activar button.
	 *
	 * @param activarButton the new activar button
	 */
	public void setActivarButton(boolean activarButton) {
		this.activarButton = activarButton;
	}

	/**
	 * Gets the download file.
	 *
	 * @return the download file
	 */
	public StreamedContent getDownloadFile() {
		return downloadFile;
	}

	/**
	 * Sets the download file.
	 *
	 * @param downloadFile the new download file
	 */
	public void setDownloadFile(StreamedContent downloadFile) {
		this.downloadFile = downloadFile;
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
	 * Gets the lis mes.
	 *
	 * @return the lis mes
	 */
	public List<TcMes> getLisMes() {
		return lisMes;
	}

	/**
	 * Sets the lis mes.
	 *
	 * @param lisMes the new lis mes
	 */
	public void setLisMes(List<TcMes> lisMes) {
		this.lisMes = lisMes;
	}

	/**
	 * Instantiates a new afectacion poliza MB.
	 */
	public AfectacionPolizaMB() {
		activarButton = Boolean.TRUE;
		getPassAfectacion();
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		lisMes = tcMesRepository.findAll();
		activarButton = Boolean.TRUE;
		isActivarButton();
		String keyPass = this.getUserDetails().getIdSector() == 1 ? KEY_PASS_1 : KEY_PASS;
		TcParametro param = this.parametrosRepository.findOne(ParametrosPredicate.findByDataType(keyPass, DATA_TYPE));
		passAfectacion = param.getValor();
	}

	/**
	 * Checks if is true pass.
	 */
	public void isTruePass() {
		if (pass != null) {
			boolean bandera = this.passwordEncoder.matches(pass, passAfectacion);
			if (bandera) {
				activarButton = Boolean.FALSE;
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"El passwor es incorrecto");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El campo password esta vació.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Cierre poliza.
	 */
	public void cierrePoliza() {
		LOGGER.info("Entra a generar la afectación");
		Integer bandera = polienRepository.xisteMont(mes, this.getUserDetails().getIdSector());
		if (bandera > 0) {
			Date fIni = Calendar.getInstance().getTime();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("F Iniciio: " + fIni);
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			poliza = polizaService.cierreMensul(mes, this.getUserDetails().getUsername(),
					this.getUserDetails().getIdSector());
			Date fFin = Calendar.getInstance().getTime();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("F. Fin:: " + fFin);
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			long tiempo = fFin.getTime() - fIni.getTime();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("tiempo::" + tiempo);

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			if (poliza.getErrorCode() > 0) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", poliza.getMsgError());
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {

				if (poliza.getFileName() != null) {
					activeDowload = Boolean.FALSE;
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", poliza.getMsgError());
					FacesContext.getCurrentInstance().addMessage(null, message);
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", poliza.getMsgError());
					FacesContext.getCurrentInstance().addMessage(null, message);
				}

			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El mes que intenta afectar no se encuentra en la base");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Down load.
	 */
	public void downLoad() {
		LOGGER.info("entra a descargar");
		LOGGER.info("fileName: " + fileName);
		LOGGER.info("pathFile: " + pathFile);

		try {

			InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream(poliza.getPathName() + "/" + poliza.getFileName());
			downloadFile = new DefaultStreamedContent(stream); // "application/octet-stream",
																// fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Send file to user.
	 */
	public void sendFileToUser() {
		FileInputStream fileInputStream = null;
		OutputStream output = null;
		try {
			makeDir(new File(poliza.getPathName()));
			File fileToSend = new File(poliza.getPathName() + "/" + poliza.getFileName());
			byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
			fileInputStream = new FileInputStream(fileToSend);
			fileInputStream.read(ingresoRespFile);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			String fileName = fileToSend.getName();
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
	 * Make dir.
	 *
	 * @param file the file
	 */
	public void makeDir(File file) {
		String outError = "/tmp/polizasincorrectas";
		String outDontHead = "/tmp/encabezadonot";
		String outCuentas = "/tmp/cuentas";
		File fileErrror = new File(outError);
		File fileHEad = new File(outDontHead);
		File fileCuentas = new File(outCuentas);
		fileErrror.mkdirs();
		fileHEad.mkdirs();
		fileCuentas.mkdirs();
	}

	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
