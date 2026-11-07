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

import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.TcMes;

import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

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

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoAvancePresupuestalEgresosFinalidadFuncionSubFuncionMB.
 *
 * @author Alfredo
 */
@ManagedBean(name = "estadoAvancePresupuestalEgresosFinalidadFuncionSubFuncion")
@ViewScoped
public class EstadoAvancePresupuestalEgresosFinalidadFuncionSubFuncionMB extends GenericExecuteProcedure {
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EAPE_FINFUNSUB";

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	
	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";
	
	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";
	
	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";
	
	/** The mes. */
	private String mes;
	
	/** The finfunsub. */
	private String finfunsub;
	
	/** The name muninep. */
	private String nameMuninep;
	
	/** The concatmuninep. */
	private Muninep concatmuninep;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The list concat muninep. */
	private List<Muninep> listConcatMuninep;
	
	/** The file txt. */
	private StreamedContent fileTxt;
	
	/** The txt preview. */
	private StringBuilder txtPreview;
	
	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	/** The muni nep repository. */
	@ManagedProperty("#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcmesRepository.findAll();
		listConcatMuninep = muniNepRepository.getByIdsector(this.getUserDetails().getIdSector());

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listConcatMuninep)) {
			concatmuninep = listConcatMuninep.get(0);
			nameMuninep = this.getMuniNepRepository()
					.findFirstByCampo7AndIdsector(concatmuninep.getCampo7(), this.getUserDetails().getIdSector())
					.getCampo6();
			finfunsub = concatmuninep.getCampo7();
		}
	}

	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", Integer.valueOf(mes));
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameter.addValue("i_finfunsub", finfunsub);
		parameter.addValue("i_municipio", this.getUserDetails().getMunicipio());

		return parameter;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
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

	/**
	 * Pre view.
	 */
	public void preView() {
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

	/**
	 * Update name.
	 */
	public void updateName() {
		nameMuninep = this.getMuniNepRepository()
				.findFirstByCampo7AndIdsector(finfunsub, this.getUserDetails().getIdSector()).getCampo6();
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
	 * Gets the name muninep.
	 *
	 * @return the name muninep
	 */
	public String getNameMuninep() {
		return nameMuninep;
	}

	/**
	 * Sets the name muninep.
	 *
	 * @param nameMuninep the new name muninep
	 */
	public void setNameMuninep(String nameMuninep) {
		this.nameMuninep = nameMuninep;
	}

	/**
	 * Gets the concatmuninep.
	 *
	 * @return the concatmuninep
	 */
	public Muninep getConcatmuninep() {
		return concatmuninep;
	}

	/**
	 * Sets the concatmuninep.
	 *
	 * @param concatmuninep the new concatmuninep
	 */
	public void setConcatmuninep(Muninep concatmuninep) {
		this.concatmuninep = concatmuninep;
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
	 * Gets the list concat muninep.
	 *
	 * @return the list concat muninep
	 */
	public List<Muninep> getListConcatMuninep() {
		return listConcatMuninep;
	}

	/**
	 * Sets the list concat muninep.
	 *
	 * @param listConcatMuninep the new list concat muninep
	 */
	public void setListConcatMuninep(List<Muninep> listConcatMuninep) {
		this.listConcatMuninep = listConcatMuninep;
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
	 * Gets the muni nep repository.
	 *
	 * @return the muni nep repository
	 */
	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
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
	 * Gets the finfunsub.
	 *
	 * @return the finfunsub
	 */
	public String getFinfunsub() {
		return finfunsub;
	}

	/**
	 * Sets the finfunsub.
	 *
	 * @param finfunsub the new finfunsub
	 */
	public void setFinfunsub(String finfunsub) {
		this.finfunsub = finfunsub;
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
	public void downloadTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.downLoadFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
	}

	public void viewTxt()  {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.preView();
		}
	}
}
