package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultasTodasReferenciasMB.
 */
/**
 * @author Alfredo
 *
 */
@ManagedBean(name = "consultasTodasReferenciasMB")
@ViewScoped
public class ConsultasTodasReferenciasMB extends GenericExecuteProcedure {
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_ALL_REFERENCIAS";

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";

	/** The mes. */
	private String mes;

	/** The direccion. */
	private String direccion;

	/** The partida ini. */
	private String partidaIni;

	/** The partida fin. */
	private String partidaFin;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", Integer.valueOf(mes));
		parameter.addValue("i_direccion", direccion);
		parameter.addValue("i_partidaI", partidaIni);
		parameter.addValue("i_partidaF", partidaFin);
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());

		return parameter;
	}

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		addToBitacora(true);
		if (validatePartidas()) {
			out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
			if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
				try {
					stream = new FileInputStream(
							new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
			}
		}
		addToBitacora(false);
	}

	/**
	 * Pre view.
	 */
	public void preView() {
		addToBitacora(true);
		if (validatePartidas()) {
			File newFile;
			File target;
			String fileNameOut = "";
			this.setbPreView(Boolean.TRUE);
			try {
				out = this.executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
			} catch (ReportValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, out.get("O_MSG_ERROR").toString());
			} else {
				fileNameOut = out.get("O_RUTA_FILE").toString() + UUID.randomUUID() + ".txt";
				newFile = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
				target = new File(fileNameOut);

				try {
					ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PREVIEW);
			}
		}
		addToBitacora(false);
	}

	/**
	 * Validate partidas.
	 *
	 * @return true, if successful
	 */
	public boolean validatePartidas() {
		if (partidaIni == null || partidaFin == null) {
			return true;
		} else if (Integer.valueOf(partidaIni) > Integer.valueOf(partidaFin)) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"La Partida Inical debe ser menor que la Partida Final");
			return false;
		} else {
			return true;
		}
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
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the partida ini.
	 *
	 * @return the partida ini
	 */
	public String getPartidaIni() {
		return partidaIni;
	}

	/**
	 * Sets the partida ini.
	 *
	 * @param partidaIni the new partida ini
	 */
	public void setPartidaIni(String partidaIni) {
		this.partidaIni = partidaIni;
	}

	/**
	 * Gets the partida fin.
	 *
	 * @return the partida fin
	 */
	public String getPartidaFin() {
		return partidaFin;
	}

	/**
	 * Sets the partida fin.
	 *
	 * @param partidaFin the new partida fin
	 */
	public void setPartidaFin(String partidaFin) {
		this.partidaFin = partidaFin;
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

}
