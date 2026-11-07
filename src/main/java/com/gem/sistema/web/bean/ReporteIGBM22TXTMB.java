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
 * The Class ReporteIGBM22TXTMB.
 */
@ManagedBean(name = "reporteIGBM22TXTMB")
@ViewScoped
public class ReporteIGBM22TXTMB extends GenericExecuteProcedure {

	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_REPORTE_IGBM_22";
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The semestre. */
	private String semestre;
	
	/** The listsemestre. */
	private List<String> listsemestre;
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/** The contb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository contbRepository;

	/**
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Sets the semestre.
	 *
	 * @param semestre the new semestre
	 */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	/**
	 * Gets the listsemestre.
	 *
	 * @return the listsemestre
	 */
	public List<String> getListsemestre() {
		return listsemestre;
	}

	/**
	 * Sets the listsemestre.
	 *
	 * @param listsemestre the new listsemestre
	 */
	public void setListsemestre(List<String> listsemestre) {
		this.listsemestre = listsemestre;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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
	 * Gets the contb repository.
	 *
	 * @return the contb repository
	 */
	public ConctbRepository getContbRepository() {
		return contbRepository;
	}

	/**
	 * Sets the contb repository.
	 *
	 * @param contbRepository the new contb repository
	 */
	public void setContbRepository(ConctbRepository contbRepository) {
		this.contbRepository = contbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++) {
			listsemestre.add("0" + i);
		}
		semestre = listsemestre.get(0);

		procedureName = NAME_PROCEDURE;

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		conctb = contbRepository.findByIdsector(getUserDetails().getIdSector());

		parameters.addValue("i_semestre", Integer.valueOf(semestre));
		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_clvemun", conctb.getClave());

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

	}

}
