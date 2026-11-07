package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import com.gem.sistema.business.repository.catalogs.ActividadRepository;
import com.gem.sistema.business.repository.catalogs.ComponenteRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FinalidadRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDmRepository;
import com.gem.sistema.business.repository.catalogs.MatindRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.PropositoRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;


// TODO: Auto-generated Javadoc
/**
 * The Class TxtMatrizIndMB.
 */
@ManagedBean(name = "txtMatrizIndMB")
@ViewScoped
public class TxtMatrizIndMB extends GenericExecuteProcedure {
	
	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_GENERA_TXT_MATRIZ_IND";
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The file txt. */
	private StreamedContent fileTxt;

  /** The fullpath. */
  private String fullpath;
  
  /** The filename. */
  private String filename;


	/** The contb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository contbRepository;

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
		procedureName = NAME_PROCEDURE;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		conctb = contbRepository.findByIdsector(getUserDetails().getIdSector());

		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_clvemun", conctb.getClave());
		parameters.addValue("i_anio", conctb.getAnoemp().toString());

		return parameters;

	}

  /**
   * Process.
   *
   * @throws ReportValidationException the report validation exception
   */
  public void process() throws ReportValidationException{
    Map<String, Object> outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());

    if (new Integer(outParameters.get("O_COD_ERROR").toString()) > 0) {
      filename = outParameters.get("O_NAME_FILE").toString();
      fullpath = outParameters.get("O_RUTA_FILE").toString() + "/"
                 + filename;

      sendMessages(outParameters.get("O_MSGFINALIDAD").toString(), outParameters.get("O_MSGPROPOSITO").toString(),
      outParameters.get("O_MSGCOMPONENTE").toString(), outParameters.get("O_MSGACTIVIDAD").toString());

    }else{
      generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());
    }
  }

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile(){
    try{
      fileTxt = new DefaultStreamedContent(
      new FileInputStream(new File(fullpath)), "application/txt",filename);
    }catch(FileNotFoundException e){
      generateNotificationFront(SEVERITY_INFO, "Info!", "No se puede descargar el archivo");
    }
	}

	/**
	 * Send messages.
	 *
	 * @param msg1 the msg 1
	 * @param msg2 the msg 2
	 * @param msg3 the msg 3
	 * @param msg4 the msg 4
	 */
	public void sendMessages(String msg1, String msg2, String msg3, String msg4) {
		if (!msg1.equals("")) {
			generateNotificationFront(SEVERITY_INFO, "Info!", msg1);
		}

		if (!msg2.equals("")) {
			generateNotificationFront(SEVERITY_INFO, "Info!", msg2);
		}
		if (!msg3.equals("")) {
			generateNotificationFront(SEVERITY_INFO, "Info!", msg3);
		}
		if (!msg4.equals("")) {
			generateNotificationFront(SEVERITY_INFO, "Info!", msg4);
		}

	}

}
