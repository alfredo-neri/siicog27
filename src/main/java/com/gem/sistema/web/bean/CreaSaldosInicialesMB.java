package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.AccountException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.CargaCuentas58Service;
import com.gem.sistema.util.CopyFileUtil;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Conctb;
import java.io.StringWriter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.gem.sistema.web.security.model.GemUser;
import java.util.function.BiConsumer;

// TODO: Auto-generated Javadoc
/**
 * The Class CreaSaldosInicialesMB.
 */
@ManagedBean(name = "creaSaldosInicialesMB")
@ViewScoped
public class CreaSaldosInicialesMB extends AbstractMB {
	private static final String PATH_FILE = "/gem/upfiles/";
	/** The Constant uploadURL. */
	private static final String uploadURL = "/views/utilerias/creaSaldosInicialesUF.xhtml";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CreaSaldosInicialesMB.class);

	/** The Constant _INNER_PASS. */
	private static final String _INNER_PASS = "ycargasal";

	/** The account service. */
	@ManagedProperty(value = "#{accountService}")
	private AccountService accountService;

	/** The conctb repository. */
	@ManagedProperty(value = "#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{cargaCuentas58Service}")
	private CargaCuentas58Service cargaCuentas58Service;

	/** The password. */
	private String password;

	/** The file. */
	private UploadedFile file;

	private Boolean btnDowload = Boolean.FALSE;

	/** The download. */
	private StreamedContent download;
	String fileName = StringUtils.EMPTY;
	Map<String, Object> outParameters = null;

	InputStream inputStrem = null;

	public void handleFileUpload(FileUploadEvent event) {
		fileName = UUID.randomUUID() + event.getFile().getFileName();
		try {
			String pathFillFullName = CopyFileUtil.copyFile(fileName, event.getFile().getInputstream(), PATH_FILE);
			outParameters = this.cargaCuentas58Service.cargaCuetaTmp(pathFillFullName);

			try {
				displayWarnMessage(outParameters.get("O_MSG_ERROR").toString() + "\n");

				if(Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) == 0) {
					inputStrem = new FileInputStream(new File(outParameters.get("O_FULL_NAME").toString()));
					download = new DefaultStreamedContent(inputStrem, "application/csv", "erroresSalini.csv");
					btnDowload = Boolean.TRUE;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				displayErrorMessage("Se ha producido un error, favor de contactar al administrador.");
			}

		} catch (IOException e) {
			displayErrorMessage("Error al cargar el archivo");
		}
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * Gets the download.
	 *
	 * @return the download
	 */
	public StreamedContent getDownload() {
		return download;
	}

	/**
	 * Sets the download.
	 *
	 * @param download the new download
	 */
	public void setDownload(StreamedContent download) {
		this.download = download;
	}

	/**
	 * Actions.
	 *
	 * @return the string
	 */

	public String validatePassword() {
		if (StringUtils.isBlank(password)) {
			displayMessage("El campo password esta vació.");
			return null;
		} else if (!_INNER_PASS.equals(password)) {
			displayMessage("El password es incorrecto");
			return null;
		} else {
			return uploadURL;
		}
	}

	/**
	 * Display message.
	 *
	 * @param str the str
	 */
	private void displayMessage(String str) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", str);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Upload.
	 */
	public void upload() {
		Conctb conctb = conctbRepository.findFirstByIdsectorOrderByIdAsc(getIdSectorForCurrentUser().intValue());
		String filename = "sdos" + (conctb.getAnoemp() - 1) + ".txt";

		if (file != null && file.getFileName().equals(filename)) {
			accountService.resetAccounts(((Integer) getUserDetails().getIdSector()).longValue());
			StringWriter writer = new StringWriter();
			processFile(writer);
			try {
				setDownload(new DefaultStreamedContent(IOUtils.toInputStream(writer.toString(), "UTF-8"), "text/csv",
						"cargasal.csv"));
			} catch (IOException e) {
				LOGGER.error("Error Descargando Archivo {}", e);
				e.printStackTrace();
			}
			displayInfoMessage("Proceso concluido");
		} else {
			displayErrorMessage("No se encuentra el archivo " + filename);
		}
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService the new account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the account service
	 */
	public AccountService getAccountService() {
		return accountService;
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
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Process file.
	 *
	 * @param writer the writer
	 */
	private void processFile(StringWriter writer) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputstream()));
			for (String line = null; (line = reader.readLine()) != null;) {
				final String l = line;
				findAndUpdate(cleanColumns(line.replaceAll("\"", "").split(",")), (account, salini) -> {
					if (!(account.startsWith("4") || account.startsWith("8") || salini.equals(0)))
						writer.write(l);
					writer.write("\n");
				});
			}
			writer.write("Proceso concluido");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clean columns.
	 *
	 * @param cols the cols
	 * @return the list
	 */
	private List<String> cleanColumns(final String... cols) {
		return new ArrayList<String>() {
			{
				if (cols != null) {
					for (String col : cols) {
						add(col.trim());
					}
				}
			}
		};
	}

	/**
	 * Find and update.
	 *
	 * @param columns  the columns
	 * @param consumer the consumer
	 */
	private void findAndUpdate(List<String> columns, BiConsumer<String, Double> consumer) {
		if (columns.size() >= 6) {
			List<Cuenta> cuentas = accountService.findToUpdateForFileReset(columns.get(0), columns.get(1),
					columns.get(2), columns.get(3), columns.get(4),
					((Integer) getUserDetails().getIdSector()).longValue());
			if (cuentas != null && !cuentas.isEmpty()) {
				accountService.updateSaliniToAccounts(cuentas, new Double(columns.get(5)));
				return;
			} else {
				consumer.accept(columns.get(0), new Double(columns.get(5)));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.AbstractMB#getUserDetails()
	 */
	public GemUser getUserDetails() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return null;
	}

	public CargaCuentas58Service getCargaCuentas58Service() {
		return cargaCuentas58Service;
	}

	public void setCargaCuentas58Service(CargaCuentas58Service cargaCuentas58Service) {
		this.cargaCuentas58Service = cargaCuentas58Service;
	}

	public static String getUploadurl() {
		return uploadURL;
	}

	public Boolean getBtnDowload() {
		return btnDowload;
	}

	public void setBtnDowload(Boolean btnDowload) {
		this.btnDowload = btnDowload;
	}

}
