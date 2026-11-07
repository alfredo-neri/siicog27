
package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.apache.commons.collections4.CollectionUtils;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * The Class ExportaInformacionMB.
 * 
 * @author Alfredo Neri
 *
 */
@Component("exportaInformacionMB")
@Scope("view")
public class ExportaInformacionMB extends GenericExecuteProcedure {

	public static final String NAME_PROCEDURE = "SP_EXPORTA_INFORMACION_SIFE";

	private Integer mes;

	private List<TcPeriodo> listMes;

	private StreamedContent fileTxt;

	@Value("#{tcPeriodoRepositoy}")
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

		parameters.addValue("i_idSector", this.getUserDetails().getIdSector());
		parameters.addValue("i_mes", mes);

		return parameters;
	}

	@Override
	public void downLoadFile() throws ReportValidationException {
		Map<String, Object> outParameters = null;
		InputStream stream = null;

		try {
			outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());
		} catch (ReportValidationException e) {
			e.printStackTrace();
		}

		if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(outParameters.get("O_PATH_FILE").toString() + "/"
						+ outParameters.get("O_FILE_NAME").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = StreamedContents.of(stream, "application/txt", outParameters.get("O_FILE_NAME").toString(), null);

		} else {
			generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());

		}
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcPeriodo> listMes) {
		this.listMes = listMes;
	}

	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}
}
