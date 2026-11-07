package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010242MB.
 */
@ManagedBean(name = "rF0010242MB")
@ViewScoped
public class RF0010242MB extends GenericExecuteProcedure {
	
	/** The trim. */
	private Integer trim;
	
	/** The file txt. */
	private StreamedContent fileTxt;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the trim.
	 *
	 * @return the trim
	 */
	public Integer getTrim() {
		return trim;
	}

	/**
	 * Sets the trim.
	 *
	 * @param trim the new trim
	 */
	public void setTrim(Integer trim) {
		this.trim = trim;
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
	public void init(){
		
		procedureName="SP_GENERA_TXT_RF0010_2_4_2";
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_idsector", this.getUserDetails().getIdSector());
		parametros.addValue("i_sql", this.generateQuery(trim));
		parametros.addValue("i_trim", trim);
		parametros.addValue("i_anio", String.valueOf(firmas.getCampo3()));
		
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
		out = executePlService.callProcedure(procedureName, getParametersReports());
		
		if(Integer.valueOf(out.get("O_COD_ERROR").toString())>0){
			try {
			    stream = new FileInputStream(
			      new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			   } catch (FileNotFoundException e) {
			    
			    e.printStackTrace();
			   }
			   fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			   generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		}
			
	}
	
	/**
	 * Generate query.
	 *
	 * @param trim the trim
	 * @return the string
	 */
	public String generateQuery(Integer trim) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder avanacum = new StringBuilder().append(" P.CANT_AVAN_1 ");

		for (int i = 2; i <= trim; i++) {
			avanacum.append("+ P.CANT_AVAN_" + i + " ");
		}

		
		sSql.append(" SELECT "
				+ "  SUBSTR(CLVDEP,1,3) CLVDEP1 "
				+ "  , SUBSTR(CLVDEP,4,3) CLVDEP2 "
				+ "  , SUBSTR(CLVNEP,1,2) NEP1 "
				+ "  , SUBSTR(CLVNEP,3,2) NEP2 "
				+ "  , SUBSTR(CLVNEP,5,2) NEP3 "
				+ "  , SUBSTR(CLVNEP,7,2) NEP4 "
				+ "  , SUBSTR(CLVNEP,9,2) NEP5 "
				+ "  , SUBSTR(CLVNEP,11,2) NEP6 "
				+ "  , TO_CHAR(CLVMET) CLV_INDC "
				+ "  , NOM_IND NOM_IND , UNI_MED "
				+ "  , CAN_METI CAN_METI "
				+ "  , AVANACUM CANT_AVAN "
				+ "  , AMPLI AMPLI "
				+ "  , REDU REDU "
				+ "  , VARIACION  VARIACION "
				+ "  , CMT1 CMT1 "
				+ "  , CMT2  CMT2 "
				+ "  , CMT3 CMT3 "
				+ "  , CMT4 CMT4 "
				+ "  FROM( "
				+ "  SELECT  "
				+ "  CLVDEP "
				+ "  , CLVNEP "
				+ "  , CLVMET "
				+ "  , NOM_IND "
				+ "  , UNI_MED "
				+ "  , CAN_METI "
				+ "  , CANT_AVAN "
				+ "  , AMPLI "
				+ "  , REDU "
				+ "  , VARIACION "
				+ "  , CMT1 "
				+ "  , CMT2 "
				+ "  , CMT3 "
				+ "  , CMT4 "
				+ "  , AVANACUM "
				+ "  , CANMETIC "
				+ "  , DECODE(CAN_METI,0,0,ROUND((((CAN_METI - AVANACUM)/ CAN_METI)*100.0000),2)) PORCENTAJE "
				+ "  FROM( " + "  SELECT  " + "  P.CLVDEP " + "  , P.CLVNEP " + "  , P.CLVMET " + "  ,NVL(P.NOM_IND,'') NOM_IND "
				+ "  , P.UNI_MED " + "  , P.CAN_METI " + "  , P.CANT_AVAN_" + trim + " CANT_AVAN " + "  , P.AMPLI_"
				+ trim + " AMPLI " + "  , P.REDU_" + trim + " REDU " + "  , (P.CAN_METI + P.AMPLI_" + trim
				+ " - P.REDU_" + trim + ") VARIACION  " + "  , (P.CAN_METIC_1 + P.AMPLI_1 - P.REDU_1) CMT1 "
				+ "  , (P.CAN_METIC_2 + P.AMPLI_2 - P.REDU_2) CMT2 "
				+ "  , (P.CAN_METIC_3 + P.AMPLI_3 - P.REDU_3) CMT3 "
				+ "  , (P.CAN_METIC_4 + P.AMPLI_4 - P.REDU_4) CMT4 " + "  , (" + avanacum + ") AVANACUM "
				+ "  , DECODE((P.CAN_METIC_" + trim + " *100),0,0,ROUND(((P.CAN_METIC_" + trim + " - P.CANT_AVAN_"
				+ trim + ")/P.CAN_METIC_" + trim + " *100),2)) CANMETIC " + "   FROM PP_METT P "
				+ "  WHERE P.IDSECTOR=1 ORDER BY P.CLVDEP,P.CLVNEP ASC " + "  ) )  ");

		
		
		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
	}

}
