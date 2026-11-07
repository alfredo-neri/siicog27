package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.TcMes;

import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoAvancePresupuestalEgresosFinalidadFuncionProgramaProyectoFfMB.
 */
@ManagedBean(name = "estadoAvancePresupuestalEgresosFinalidadFuncionProgramaProyectoFfMB")
@ViewScoped
public class EstadoAvancePresupuestalEgresosFinalidadFuncionProgramaProyectoFfMB extends GenericExecuteProcedure {
	
	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:preViewTxt');";

	/** The Constant DOWNLOAD_TXT. */
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	
	/** The Constant SP_NAME. */
	private static final String SP_NAME = "SP_GENERA_TXT_FINFUNPROGPROY";
	
	/** The mes. */
	private String mes;
	
	/** The programa. */
	private String programa;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The list programa. */
	private List<String> listPrograma;
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;
	
	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;
	
	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

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
	 * Gets the tcmes repository.
	 *
	 * @return the tcmes repository
	 */
	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	/**
	 * Sets the tcmes repository.
	 *
	 * @param tcmesRepository the new tcmes repository
	 */
	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}

	/**
	 * Gets the programa.
	 *
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * Sets the programa.
	 *
	 * @param programa the new programa
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * Gets the list programa.
	 *
	 * @return the list programa
	 */
	public List<String> getListPrograma() {
		return listPrograma;
	}

	/**
	 * Sets the list programa.
	 *
	 * @param listPrograma the new list programa
	 */
	public void setListPrograma(List<String> listPrograma) {
		this.listPrograma = listPrograma;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listMes = tcmesRepository.findAll();
		listPrograma = xcatproRepository.getClvfunclvfinByIdSector(this.getUserDetails().getIdSector());

		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listPrograma)) {
			programa = listPrograma.get(0);
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_programa", programa);
		parametros.addValue("i_idsector", this.getUserDetails().getIdSector());
		parametros.addValue("i_mes", Integer.valueOf(mes));
		parametros.addValue("i_municipio", this.getUserDetails().getMunicipio());

		return parametros;
	}

	/** The stream. */
	InputStream stream = null;
	
	/** The out. */
	Map<String, Object> out;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(SP_NAME, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			String srcFiles = out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString();
			try {
				stream = new FileInputStream(new File(srcFiles));
				fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			} catch (IOException ioe) {
				System.out.println("Error creating file: " + ioe);
			}
		}
	}
	
	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";
	
	/** The Constant charSetUFT. */
	private final static String charSetUFT = "UTF-8";
	
	/**
	 * Pre view.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void preView() throws IOException {

		this.setbPreView(Boolean.TRUE);

		try {
			out = this.executePlService.callProcedure(SP_NAME, this.getParametersReports());
		} catch (ReportValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, out.get("O_MSG_ERROR").toString());
		} else {
			
			File source = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
			File target = new File(out.get("O_RUTA_FILE").toString() + "/test.txt");
			ConvertCharsetUtils.transform(source, charSetUFT, target, charSetISO);
			
			fileTxt = new DefaultStreamedContent(stream, "application/txt", "test.txt");
			
			txtPreview = this.generatePreviewService
					.generatePreview(out.get("O_RUTA_FILE").toString() + "/test.txt");
			RequestContext.getCurrentInstance().execute(FOCUS_BY_ROWID);
		}

	}
	
	public void downloadTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.downLoadFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}

	}
	
	public void preViewTxt() throws IOException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.preView();
		}
	}
	
	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) ;
	}
	
	
}