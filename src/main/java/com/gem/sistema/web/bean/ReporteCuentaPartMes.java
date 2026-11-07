package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteCuentaPartMes.
 */
@ManagedBean(name = "reporteCuentaPartMesMB")
@ViewScoped
public class ReporteCuentaPartMes extends GenericExecuteProcedure {
	
	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";
	
	/** The Constant NAME_PROCEDURE. */
	public static final String NAME_PROCEDURE = "SP_GENERA_TXT_CUENTA_PATIDA_ALMES";
	
	/** The Constant charSetISO. */
	private static final String charSetISO = "ISO-8859-1";
	
	/** The Constant charSetUFT. */
	private static final String charSetUFT = "UTF-8";

	/** The mes ini. */
	private String mesIni;
	
	/** The mes fin. */
	private String mesFin;
	
	/** The listmes ini. */
	private List<TcMes> listmesIni;
	
	/** The listmes fin. */
	private List<TcMes> listmesFin;
	
	/** The partida ini. */
	private Integer partidaIni;
	
	/** The partida fin. */
	private Integer partidaFin;
	
	/** The cuenta. */
	private Integer cuenta;
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	// Generacion de getters and setters
	

	/**
	 * Gets the mes ini.
	 *
	 * @return the mes ini
	 */
	public String getMesIni() {
		return mesIni;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Sets the mes ini.
	 *
	 * @param mesIni the new mes ini
	 */
	public void setMesIni(String mesIni) {
		this.mesIni = mesIni;
	}

	/**
	 * Gets the mes fin.
	 *
	 * @return the mes fin
	 */
	public String getMesFin() {
		return mesFin;
	}

	/**
	 * Sets the mes fin.
	 *
	 * @param mesFin the new mes fin
	 */
	public void setMesFin(String mesFin) {
		this.mesFin = mesFin;
	}

	/**
	 * Gets the listmes ini.
	 *
	 * @return the listmes ini
	 */
	public List<TcMes> getListmesIni() {
		return listmesIni;
	}

	/**
	 * Sets the listmes ini.
	 *
	 * @param listmesIni the new listmes ini
	 */
	public void setListmesIni(List<TcMes> listmesIni) {
		this.listmesIni = listmesIni;
	}

	/**
	 * Gets the listmes fin.
	 *
	 * @return the listmes fin
	 */
	public List<TcMes> getListmesFin() {
		return listmesFin;
	}

	/**
	 * Sets the listmes fin.
	 *
	 * @param listmesFin the new listmes fin
	 */
	public void setListmesFin(List<TcMes> listmesFin) {
		this.listmesFin = listmesFin;
	}

	/**
	 * Gets the partida ini.
	 *
	 * @return the partida ini
	 */
	public Integer getPartidaIni() {
		return partidaIni;
	}

	/**
	 * Sets the partida ini.
	 *
	 * @param partidaIni the new partida ini
	 */
	public void setPartidaIni(Integer partidaIni) {
		this.partidaIni = partidaIni;
	}

	/**
	 * Gets the partida fin.
	 *
	 * @return the partida fin
	 */
	public Integer getPartidaFin() {
		return partidaFin;
	}

	/**
	 * Sets the partida fin.
	 *
	 * @param partidaFin the new partida fin
	 */
	public void setPartidaFin(Integer partidaFin) {
		this.partidaFin = partidaFin;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listmesIni = tcMesRepository.findAll();
		listmesFin = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listmesIni)) {
			mesIni = listmesIni.get(0).getMes();
		}

		if (!CollectionUtils.isEmpty(listmesFin)) {
			mesFin = listmesFin.get(0).getMes();
		}

		procedureName = NAME_PROCEDURE;

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		if (partidaIni == null)
			partidaIni = 0;
		if (partidaFin == null)
			partidaFin = 0;
		
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_mes_ini", Integer.valueOf(mesIni));
		parameters.addValue("i_mes_fin", Integer.valueOf(mesFin));
		parameters.addValue("i_cuenta", cuenta.toString());
		parameters.addValue("i_partida_ini", partidaIni.toString());
		parameters.addValue("i_partida_fin", partidaFin.toString());
		parameters.addValue("i_firmas", 4);
		parameters.addValue("i_municipio", conctb.getNomDep());

		return parameters;
	}

	/** The out parameters. */
	Map<String, Object> outParameters;
	
	/** The stream. */
	InputStream stream = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {

		if (partidaIni <= partidaFin) {
			outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());

			if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) > 0) {
				try {
					stream = new FileInputStream(new File(outParameters.get("O_RUTA_FILE").toString() + "/"
							+ outParameters.get("O_NAME_FILE").toString()));
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}
				fileTxt = new DefaultStreamedContent(stream, "application/txt",
						outParameters.get("O_NAME_FILE").toString());

			} else {
				generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());

			}
		} else {
			generateNotificationFront(SEVERITY_INFO, "Info", "La partida Final debe ser mayor a la Partida Inicial");
		}

	}
	
	
	/**
	 * Pre view.
	 */
	public void preView() {
		this.setbPreView(Boolean.TRUE);

		if (partidaIni <= partidaFin) {

			try {
				outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());
			} catch (ReportValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, outParameters.get("O_MSG_ERROR").toString());
			} else {
				// streamedContent = sendFileByStream(params.getResultFile());
				
				File source = new File(outParameters.get("O_RUTA_FILE").toString()
						+ "/" + outParameters.get("O_NAME_FILE").toString());
				File target = new File("/gem/reportes/"+UUID.randomUUID()+".txt");
				
				try {
					ConvertCharsetUtils.transform(source, charSetUFT, target, charSetISO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				txtPreview = this.generatePreviewService.generatePreview(target.toString());
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PREVIEW);
			}
		} else {
			generateNotificationFront(SEVERITY_INFO, "Info", "La partida Final debe ser mayor a la Partida Inicial");
		}
	}

	/**
	 * On change valida meses.
	 */
	public void onChangeValidaMeses() {
		if (Integer.valueOf(mesIni) > Integer.valueOf(mesFin)) {
			generateNotificationFront(SEVERITY_INFO, "Info", "El Mes Final debe ser mayor al Mes Inicial");
		}

	}
	

}
