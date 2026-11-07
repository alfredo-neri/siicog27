package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author alfredo
 *
 */
@ManagedBean(name = "polizasByUsuarioMB")
@ViewScoped
public class PolizasByUsuarioMB extends GenericExecuteProcedure {

	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";
	public static final String NAME_PROCEDURE = "SP_GENERA_TXT_POLIZAS_BY_USER";

	private Integer mes;

	private String user;

	private List<TcPeriodo> listMes;

	private StringBuilder txtPreview;
	private Boolean bPreView = Boolean.FALSE;

	private StreamedContent fileTxt;

	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@PostConstruct
	public void init() {
		procedureName = NAME_PROCEDURE;

		listMes = periodoRepositoy.findByTipoPeriodo(1);
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getPeriodo();
		}

	}

	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		parameters.addValue("i_mes", mes);
		parameters.addValue("i_sector", this.getUserDetails().getIdSector());
		parameters.addValue("i_usuario", user);

		return parameters;
	}

	Map<String, Object> outParameters;
	InputStream stream = null;

	@Override
	public void downLoadFile() throws ReportValidationException {
		addToBitacora(true);
		try {
			outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());
		} catch (ReportValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		addToBitacora(false);
	}

	public void preView() {
		addToBitacora(true);
		this.setbPreView(Boolean.TRUE);

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
			txtPreview = this.generatePreviewService.generatePreview(
					outParameters.get("O_RUTA_FILE").toString() + "/" + outParameters.get("O_NAME_FILE").toString());
			RequestContext.getCurrentInstance().execute(FOCUS_IN_PREVIEW);
		}
		addToBitacora(false);
	}

	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	public Boolean getbPreView() {
		return bPreView;
	}

	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<TcPeriodo> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcPeriodo> listMes) {
		this.listMes = listMes;
	}

	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

}
