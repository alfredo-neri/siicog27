package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte91MB.
 */
@ManagedBean
@ViewScoped
public class Reporte91MB extends GenericExecuteProcedure {

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:preViewTxt');";

	/** The mes ini. */
	private Integer mesIni;

	/** The mes fin. */
	private Integer mesFin;

	/** The cuenta. */
	private Integer cuenta;

	/** The partida inicial. */
	private Integer partidaInicial;

	/** The partida final. */
	private Integer partidaFinal;

	/** The num firmas. */
	private Integer numFirmas;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	/**
	 * Gets the file txt.
	 *
	 * @return the file txt
	 */
	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the mes ini.
	 *
	 * @return the mes ini
	 */
	public Integer getMesIni() {
		return mesIni;
	}

	/**
	 * Sets the mes ini.
	 *
	 * @param mesIni the new mes ini
	 */
	public void setMesIni(Integer mesIni) {
		this.mesIni = mesIni;
	}

	/**
	 * Gets the mes fin.
	 *
	 * @return the mes fin
	 */
	public Integer getMesFin() {
		return mesFin;
	}

	/**
	 * Sets the mes fin.
	 *
	 * @param mesFin the new mes fin
	 */
	public void setMesFin(Integer mesFin) {
		this.mesFin = mesFin;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Integer getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the partida inicial.
	 *
	 * @return the partida inicial
	 */
	public Integer getPartidaInicial() {
		return partidaInicial;
	}

	/**
	 * Sets the partida inicial.
	 *
	 * @param partidaInicial the new partida inicial
	 */
	public void setPartidaInicial(Integer partidaInicial) {
		this.partidaInicial = partidaInicial;
	}

	/**
	 * Gets the partida final.
	 *
	 * @return the partida final
	 */
	public Integer getPartidaFinal() {
		return partidaFinal;
	}

	/**
	 * Sets the partida final.
	 *
	 * @param partidaFinal the new partida final
	 */
	public void setPartidaFinal(Integer partidaFinal) {
		this.partidaFinal = partidaFinal;
	}

	/**
	 * Gets the num firmas.
	 *
	 * @return the num firmas
	 */
	public Integer getNumFirmas() {
		return numFirmas;
	}

	/**
	 * Sets the num firmas.
	 *
	 * @param numFirmas the new num firmas
	 */
	public void setNumFirmas(Integer numFirmas) {
		this.numFirmas = numFirmas;
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
	 * Gets the stream.
	 *
	 * @return the stream
	 */
	public InputStream getStream() {
		return stream;
	}

	/**
	 * Sets the stream.
	 *
	 * @param stream the new stream
	 */
	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * Gets the out.
	 *
	 * @return the out
	 */
	public Map<String, Object> getOut() {
		return out;
	}

	/**
	 * Sets the out.
	 *
	 * @param out the out
	 */
	public void setOut(Map<String, Object> out) {
		this.out = out;
	}

	/**
	 * Gets the input file.
	 *
	 * @return the input file
	 */
	public InputStream getInputFile() {
		return inputFile;
	}

	/**
	 * Sets the input file.
	 *
	 * @param inputFile the new input file
	 */
	public void setInputFile(InputStream inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * Gets the input file 2.
	 *
	 * @return the input file 2
	 */
	public InputStream getInputFile2() {
		return inputFile2;
	}

	/**
	 * Sets the input file 2.
	 *
	 * @param inputFile2 the new input file 2
	 */
	public void setInputFile2(InputStream inputFile2) {
		this.inputFile2 = inputFile2;
	}

	/**
	 * Gets the new file.
	 *
	 * @return the new file
	 */
	public File getNewFile() {
		return newFile;
	}

	/**
	 * Sets the new file.
	 *
	 * @param newFile the new new file
	 */
	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public File getTarget() {
		return target;
	}

	/**
	 * Sets the target.
	 *
	 * @param target the new target
	 */
	public void setTarget(File target) {
		this.target = target;
	}

	/**
	 * Gets the file name out.
	 *
	 * @return the file name out
	 */
	public String getFileNameOut() {
		return fileNameOut;
	}

	/**
	 * Sets the file name out.
	 *
	 * @param fileNameOut the new file name out
	 */
	public void setFileNameOut(String fileNameOut) {
		this.fileNameOut = fileNameOut;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct reporte  91");
		procedureName = "SP_GENERA_TXT91";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		if (cuenta == null)
			cuenta = 0;
		if (partidaInicial == null)
			partidaInicial = 0;
		if (partidaFinal == null)
			partidaFinal = 0;
		if (mesIni == null)
			mesIni = 0;
		if (mesFin == null)
			mesFin = 0;

		if (partidaInicial > partidaFinal) {
			throw new ReportValidationException("La partida inicial no puede ser mayor que la partida final.");
		}
		if (mesIni > mesFin) {
			throw new ReportValidationException("El mes inicial no puede ser mayor que el mes final.");
		}

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_idsector", this.getUserDetails().getIdSector());
		parametros.addValue("i_cuenta", cuenta);
		parametros.addValue("i_mes_inicial", mesIni);
		parametros.addValue("i_mes_final", mesFin);
		parametros.addValue("i_partida_inicial", partidaInicial);
		parametros.addValue("i_partida_final", partidaFinal);
		parametros.addValue("i_num_final", numFirmas);
		return parametros;
	}

	/** The stream. */
	InputStream stream = null;

	/** The out. */
	Map<String, Object> out;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		addToBitacora(true);
		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(procedureName, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		}
		addToBitacora(false);
	}

	/** The input file. */
	InputStream inputFile = null;

	/** The input file 2. */
	InputStream inputFile2 = null;

	/** The new file. */
	File newFile = null;

	/** The target. */
	File target = null;

	/** The file name out. */
	String fileNameOut = "";

	/**
	 * Pre view.
	 *
	 * @throws ReportValidationException the report validation exception
	 */
	public void preView() throws ReportValidationException {
		addToBitacora(true);
		this.setbPreView(Boolean.TRUE);
		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(procedureName, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			fileNameOut = "/gem/reportes/" + UUID.randomUUID() + ".txt";
			newFile = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
			target = new File(fileNameOut);
			try {
				ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
			RequestContext.getCurrentInstance().execute(FOCUS_BY_ROWID);
		}
		addToBitacora(false);
	}

}
