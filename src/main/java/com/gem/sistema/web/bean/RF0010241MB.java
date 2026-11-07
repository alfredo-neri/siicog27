package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010241MB.
 */
@ManagedBean(name = "rF0010241MB")
@ViewScoped
public class RF0010241MB extends GenericExecuteProcedure {
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The file txt. */
	private StreamedContent fileTxt;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
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
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		
		procedureName="SP_GENERA_TXT_RF0010_2_4_1";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_idsector", this.getUserDetails().getIdSector());
		parametros.addValue("i_sql", this.generateQuery(Integer.valueOf(mes)));
		parametros.addValue("i_mes", Integer.valueOf(mes));
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
	 * @param mes the mes
	 * @return the string
	 */
	public String generateQuery(Integer mes){
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder().append(" P.AUTO1_1 ");
		StringBuilder b = new StringBuilder().append(" P.EJPA1_1 ");
		StringBuilder c = new StringBuilder().append(" P.EJXPA1_1 ");
		StringBuilder d = new StringBuilder().append(" P.TOEJE1_1 ");
		StringBuilder f = new StringBuilder().append(" P.AMPLI1_1 ");
		StringBuilder g = new StringBuilder().append(" P.REDU1_1 ");
		StringBuilder cpt = new StringBuilder().append(" P.COMPRO1_1 ");
		StringBuilder ma0 = new StringBuilder().append(" P.AUTO1_1 ");
		StringBuilder f0 = new StringBuilder().append(" P.AMPLI1_1 ");
		StringBuilder g0 = new StringBuilder().append(" P.REDU1_1 ");
		StringBuilder ma = new StringBuilder().append(" P.AUTO1_1 ");

		for(int i=2; i<13; i++){
			a.append("+ P.AUTO1_" + i + " ");
		}
		for(int i=2; i<=mes; i++){			
			b.append("+ P.EJPA1_" + i + " ");
			c.append("+ P.EJXPA1_" + i + " ");
			d.append("+ P.TOEJE1_" + i + " ");
			f.append("+ P.AMPLI1_" + i + " ");
			g.append("+ P.REDU1_" + i + " ");
			cpt.append("+ P.COMPRO1_" + i + " ");
			ma.append("+ P.AUTO1_" + i + " ");
			
			if(i < mes){
				ma0.append("+ P.AUTO1_" + i + " ");
				 f0.append("+ P.AMPLI1_" + i + " ");
				 g0.append("+ P.REDU1_" + i + " ");
				
			}
			
		}
		
		sSql.append(" SELECT " 
				+ "  SUBSTR(CLAVE,1,3) DG "
				+ "  , SUBSTR(CLAVE,4,3) DA "
				+ "  , SUBSTR(PROGRAMA,1,2) FUN "
				+ "  , SUBSTR(PROGRAMA,3,2) SUBFUN "
				+ "  , SUBSTR(PROGRAMA,5,2) PROG "
				+ "  , SUBSTR(PROGRAMA,7,2) SUBPROG "
				+ "  , SUBSTR(PROGRAMA,9,2) PROY "
				+ "  , SUBSTR(PROGRAMA,11,2) FF "
				+ "  , CLVFTE CLVFTE "
				+ "  , SUBSTR(PARTIDA,1,1) || '000' PART_AGRUP "
				+ "  , PARTIDA "
				+ "  , A APROBADO "
				+ "  , (A - B) POR_EJERCER "
				+ "  , F AMPLIACION "
				+ "  , G REDUCCION  "
				+ "  , (A + F - G) MODIF_MES  "
				+ "  FROM ( "
				+ "  SELECT "
				+ "       P.CLAVE "
				+ "     , P.PROGRAMA  "
				+ "     , P.PARTIDA  "
				+ "     , F.CLVFTE  "
				+ "     , SUM( " + a + " ) A "
				+ "     , SUM( " + ma0 + " ) MA0 "    
				+ "     , SUM( " + f0 + " ) F0  " 
				+ "     , SUM( " + g0 + " )G0 "     
				+ "     , SUM( " + ma + " ) MA "    
				+ "     , SUM( " + b + "  ) B "
				+ "     , SUM( " + c + " ) C "
				+ "     , SUM( " + d + " ) D "
				+ "     , SUM( " + f + " ) F "
				+ "     , SUM( " + g + " ) G "
				+ "     , SUM( " + cpt + " ) CPT "
				+ "  FROM PASO P, NATGAS N, FUENTEF F "
				+ "  WHERE P.PARTIDA=N.CLVGAS AND  "
				+ "  SUBSTR(P.PARTIDA, 4,1)<>0 AND  "
				+ "  F.LIGA = SUBSTR(P.PROGRAMA,13,3) "
				+ "  AND F.IDSECTOR=N.IDSECTOR "
				+ "  AND N.IDSECTOR= P.IDSECTOR "
				+ "  AND P.IDSECTOR=1  "
				+ "  GROUP BY CLAVE,PROGRAMA,PARTIDA,CLVFTE "
				+ "  ORDER BY CLAVE,PROGRAMA,PARTIDA  ) ");
		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
	}
	

}
