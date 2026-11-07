package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001023MB.
 */
@ManagedBean(name = "rF001023MB")
@ViewScoped
public class RF001023MB extends GenericExecuteProcedure {

	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_REPORTE_RF001023_METAS";// FrontProperty.getPropertyValue("procedure.name.report.txt");

	/** The trimestre. */
	private String trimestre;

	/** The list trim. */
	private List<String> listTrim;

	/** The file txt. */
	private StreamedContent fileTxt;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public String getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the list trim.
	 *
	 * @return the list trim
	 */
	public List<String> getListTrim() {
		return listTrim;
	}

	/**
	 * Sets the list trim.
	 *
	 * @param listTrim the new list trim
	 */
	public void setListTrim(List<String> listTrim) {
		this.listTrim = listTrim;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listTrim = new ArrayList<String>();
		for (int i = 1; i <= 4; i++) {
			listTrim.add("0" + i);
		}
		trimestre = listTrim.get(0);
		procedureName = NAME_PROCEDURE;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		
		parameters.addValue("i_trim", Integer.valueOf(trimestre));
		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_clvemun", conctb.getClave());
		parameters.addValue("i_anio", conctb.getAnoemp());

		return parameters;
	}

	/** The out parameters. */
	Map<String, Object> outParameters;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());

		if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(outParameters.get("O_RUTA_FILE").toString() + "/"
						+ outParameters.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt",
					outParameters.get("O_NAME_FILE").toString());

		} else {
			generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());

		}

	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
