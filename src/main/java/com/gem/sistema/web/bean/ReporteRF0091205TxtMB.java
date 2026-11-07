package com.gem.sistema.web.bean;

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
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0091205TxtMB.
 */
@ManagedBean(name = "reporteRF0091205TxtMB")
@ViewScoped
public class ReporteRF0091205TxtMB extends GenericExecuteProcedure {

	/** The n firmas. */
	private String nFirmas; 
	
	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The file txt. */
	private StreamedContent fileTxt;
	
	/** The clv dep. */
	private String clvDep;

	/** The list catdep. */
	private List<Catdep> listCatdep;
	
	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	
	
	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

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
	 * Gets the n firmas.
	 *
	 * @return the n firmas
	 */
	public String getnFirmas() {
		return nFirmas;
	}

	/**
	 * Sets the n firmas.
	 *
	 * @param nFirmas the new n firmas
	 */
	public void setnFirmas(String nFirmas) {
		this.nFirmas = nFirmas;
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
	 * On page load.
	 */
	public void onPageLoad() {
		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes))
			mes = listMes.get(0).getMes();
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		procedureName = "SP_GENERA_TXT_RF_009_1_20_5";
		listMes = tcMesRepository.findAll();
		listCatdep = catdepRepository.findAllByIdsectorOrderByClvdep(this.getUserDetails().getIdSector());
	}

	/**
	 * Execute procedure.
	 */
	public void executeProcedure() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		// this.callSpService.getFile("SP_GENERA_TXT_RF_009_1_20_10",
		// parameters);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_mes", Integer.valueOf(mes));
		parameters.addValue("i_anio", String.valueOf(firmas.getCampo3()));
		parameters.addValue("i_lastday", getLastDay(Integer.valueOf(mes)));
		parameters.addValue("i_clvdep", clvDep);
		parameters.addValue("i_l1", firmas.getL1());
		parameters.addValue("i_l2", firmas.getL2());
		parameters.addValue("i_l3", firmas.getL3());
		parameters.addValue("i_n1", firmas.getN1());
		parameters.addValue("i_n2", firmas.getN2());
		parameters.addValue("i_n3", firmas.getN3());
		parameters.addValue("i_sql", this.generateQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector(), clvDep));
		parameters.addValue("i_nfirmas", Integer.valueOf(nFirmas));
		
		return parameters;
	}

	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {

		out = executePlService.callProcedure(procedureName, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			// final FacesContext context = FacesContext.getCurrentInstance();
			// final ServletContext servletContext = (ServletContext)
			// context.getExternalContext().getContext();
			// final String pathFile =
			// servletContext.getRealPath(out.get("O_RUTA_FILE").toString() +
			// File.separator + out.get("O_NAME_FILE").toString() );
			// streamContent = new DefaultStreamedContent(stream, pathFile);
			// fileTxt = streamContent;
			
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}

	}



	/**
	 * Generate query.
	 *
	 * @param mes the mes
	 * @param sector the sector
	 * @param clvdep the clvdep
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer sector, String clvdep) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder g = new StringBuilder();
		StringBuilder cpt = new StringBuilder();
		a.append(" P.AUTO1_1 ");
		b.append(" P.EJPA1_1 ");
		c.append(" P.EJXPA1_1 ");
		d.append(" P.TOEJE1_1 ");
		f.append(" P.AMPLI1_1 ");
		g.append(" P.REDU1_1 ");
		cpt.append(" P.COMPRO1_1 ");

		for (int i = 2; i < 13; i++) {
			a.append("+ P.AUTO1_" + i + " ");
		}
		for (int i = 2; i <= mes; i++) {
			b.append("+ P.EJPA1_" + i + " ");
			c.append("+ P.EJXPA1_" + i + " ");
			d.append("+ P.TOEJE1_" + i + " ");
			f.append("+ P.AMPLI1_" + i + " ");
			g.append("+ P.REDU1_" + i + " ");
			cpt.append("+ P.COMPRO1_" + i + " ");
		}
		sSql.append(
				" SELECT PARTIDA,  NOMGAS , A , B , C , D , F , G , CPT , ( ( A + F - G ) -D ) E , A1 , B1 , C1 , D1 , F1 , G1 , CPT1  , ( ( A1 + F1 - G1 ) -D1 ) E1 ");
		sSql.append(" FROM( ");
		sSql.append("  SELECT  ");
		sSql.append("  P.PARTIDA ");
		sSql.append("  , NOMGAS ");
		sSql.append("  , SUM( " + a + " ) A ");
		sSql.append("  , SUM( " + b + " ) B ");
		sSql.append("  , SUM( " + c + " ) C ");
		sSql.append("  , SUM( " + d + " ) D ");
		sSql.append("  , SUM( " + f + " ) F ");
		sSql.append("  , SUM( " + g + " ) G ");
		sSql.append("  , SUM( " + cpt + " ) CPT ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + a + " ), 0) ) A1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + b + " ),0 )) B1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + c + " ),0 )) C1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + d + " ),0 )) D1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + f + " ),0 )) F1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + g + " ),0 )) G1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + cpt + " ),0 )) CPT1 ");
		sSql.append("    FROM PASO P, NATGAS N, CATDEP CDP ");
		sSql.append("    WHERE P.PARTIDA=N.CLVGAS  ");
		sSql.append("      AND P.CLAVE = CDP.CLVDEP ");
		sSql.append("      AND CDP.IDSECTOR= P.IDSECTOR ");
		sSql.append("      AND P.IDSECTOR= N.IDSECTOR  ");
		sSql.append("      AND N.IDSECTOR=" + sector + " ");
		sSql.append("      AND CDP.CLVDEP = '" + clvdep + "' ");
		sSql.append("  GROUP BY PARTIDA , NOMGAS 	 ");
		sSql.append("  ) ORDER BY PARTIDA ");

		return sSql.toString();
	}

	/**
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
	}

	/**
	 * Gets the list catdep.
	 *
	 * @return the list catdep
	 */
	public List<Catdep> getListCatdep() {
		return listCatdep;
	}

	/**
	 * Sets the list catdep.
	 *
	 * @param listCatdep the new list catdep
	 */
	public void setListCatdep(List<Catdep> listCatdep) {
		this.listCatdep = listCatdep;
	}

}
