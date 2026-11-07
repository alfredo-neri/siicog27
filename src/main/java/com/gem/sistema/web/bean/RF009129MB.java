package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.CatdaaRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009129MB.
 */
@ManagedBean(name = "rF009129MB")
@ViewScoped
public class RF009129MB extends BaseDirectReport {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	/** The mes. */
	private String mes;

	/** The dep aux. */
	private String depAux;

	/** The finan. */
	private String fFinan;

	/** The year. */
	private Integer year;

	/** The list tc mes. */
	private List<TcMes> listTcMes;

	/** The list xcatpro. */
	private List<String> listXcatpro;

	/** The list fuente. */
	private List<String> listFuente;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The catdaa repository. */
	@ManagedProperty("#{catdaaRepository}")
	private CatdaaRepository catdaaRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;


	/** The fuentef repository. */
	@ManagedProperty("#{fuentefRepository}")
	private FuentefRepository fuentefRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository
	 *            the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the fuentef repository.
	 *
	 * @return the fuentef repository
	 */
	public FuentefRepository getFuentefRepository() {
		return fuentefRepository;
	}

	/**
	 * Sets the fuentef repository.
	 *
	 * @param fuentefRepository
	 *            the new fuentef repository
	 */
	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
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
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the dep aux.
	 *
	 * @return the dep aux
	 */
	public String getDepAux() {
		return depAux;
	}

	/**
	 * Sets the dep aux.
	 *
	 * @param depAux
	 *            the new dep aux
	 */
	public void setDepAux(String depAux) {
		this.depAux = depAux;
	}

	/**
	 * Gets the f finan.
	 *
	 * @return the f finan
	 */
	public String getfFinan() {
		return fFinan;
	}

	/**
	 * Sets the f finan.
	 *
	 * @param fFinan
	 *            the new f finan
	 */
	public void setfFinan(String fFinan) {
		this.fFinan = fFinan;
	}

	/**
	 * Gets the list tc mes.
	 *
	 * @return the list tc mes
	 */
	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	/**
	 * Sets the list tc mes.
	 *
	 * @param listTcMes
	 *            the new list tc mes
	 */
	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	/**
	 * Gets the list xcatpro.
	 *
	 * @return the list xcatpro
	 */
	public List<String> getListXcatpro() {
		return listXcatpro;
	}

	/**
	 * Sets the list xcatpro.
	 *
	 * @param listXcatpro
	 *            the new list xcatpro
	 */
	public void setListXcatpro(List<String> listXcatpro) {
		this.listXcatpro = listXcatpro;
	}

	/**
	 * Gets the list fuente.
	 *
	 * @return the list fuente
	 */
	public List<String> getListFuente() {
		return listFuente;
	}

	/**
	 * Sets the list fuente.
	 *
	 * @param listFuente
	 *            the new list fuente
	 */
	public void setListFuente(List<String> listFuente) {
		this.listFuente = listFuente;
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
	 * @param tcMesRepository
	 *            the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the catdaa repository.
	 *
	 * @return the catdaa repository
	 */
	public CatdaaRepository getCatdaaRepository() {
		return catdaaRepository;
	}

	/**
	 * Sets the catdaa repository.
	 *
	 * @param catdaaRepository
	 *            the new catdaa repository
	 */
	public void setCatdaaRepository(CatdaaRepository catdaaRepository) {
		this.catdaaRepository = catdaaRepository;
	}

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
		jasperReporteName = "rf009_1_2_9";
		endFilename = jasperReporteName + ".pdf";
		listTcMes = tcMesRepository.findAll();
		listXcatpro = catdaaRepository.getCvlPro();

		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}

		if (!CollectionUtils.isEmpty(listXcatpro)) {
			depAux = listXcatpro.get(0);
			listFuente = fuentefRepository.getFuenteByClave(listXcatpro.get(0));
		}

		if (!CollectionUtils.isEmpty(listFuente))
			fFinan = listFuente.get(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<>();
		Conctb conctb = conctbRepository.findFirstByOrderByIdAsc();
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		
		parameters.put("pAn", conctb.getAnoemp());
		parameters.put("pMes", tcMesRepository.findByMes(mes).getDescripcion());
		parameters.put("pDepAux", depAux);
		parameters.put("pFuenF", fFinan.substring(0, 3));
		parameters.put("pNomFuenteF", fFinan.substring(3));
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		parameters.put("pSql", this.generateQuery(Integer.valueOf(mes), depAux, fFinan.substring(0, 3)));
		parameters.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parameters.put("pNomMunicipio", this.getUserDetails().getMunicipio());
		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generate query.
	 *
	 * @param mes
	 *            the mes
	 * @param depAux
	 *            the dep aux
	 * @param fuenteF
	 *            the fuente F
	 * @return the string
	 */
	public String generateQuery(Integer mes, String depAux, String fuenteF) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder().append(" P.AUTO1_1 ");
		StringBuilder b = new StringBuilder().append(" P.EJPA1_1 ");
		StringBuilder c = new StringBuilder().append(" P.EJXPA1_1 ");
		StringBuilder d = new StringBuilder().append(" P.TOEJE1_1 ");
		StringBuilder f = new StringBuilder().append(" P.AMPLI1_1 ");
		StringBuilder g = new StringBuilder().append(" P.REDU1_1 ");
		StringBuilder cpt = new StringBuilder().append(" P.COMPRO1_1 ");
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
				" SELECT PARTIDA,  NOMGAS , A , B , C , D , F , G , CPT , ( ( A + F - G ) -D ) E , A1 , B1 , C1 , D1 , F1 , G1 , CPT1  , ( ( A1 + F1 - G1 ) -D1 ) E1 ")
				.append(" FROM( " + "  SELECT  " + "  P.PARTIDA " + "  , N.NOMGAS " + "  , SUM( " + a + " ) A ")
				.append("  , SUM( " + b + " ) B " + "  , SUM( " + c + " ) C " + "  , SUM( " + d + " ) D ")
				.append("  , SUM( " + f + " ) F " + "  , SUM( " + g + " ) G " + "  , SUM( " + cpt + " ) CPT ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + a + " ),0) ) A1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + b + " ),0 )) B1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + c + " ),0 )) C1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + d + " ),0 )) D1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + f + " ),0 )) F1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + g + " ),0 )) G1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + cpt + " ),0 )) CPT1 ")
				.append("  FROM PASO P, NATGAS N, CATDAA CDA, FUENTEF F " + "  WHERE P.PARTIDA=N.CLVGAS ")
				.append("  AND SUBSTR(P.CLAVE,4,3) = CDA.CLAVE" + "  AND SUBSTR(P.PROGRAMA,13,3)= F.LIGA ")
				.append("  AND P.IDSECTOR= N.IDSECTOR  " + "  AND F.IDSECTOR= P.IDSECTOR " + "  AND N.IDSECTOR=1 ")
				.append("  AND CDA.CLAVE= '" + depAux + "' " + "  AND F.LIGA= '" + fuenteF + "' ")
				.append("  GROUP BY PARTIDA , NOMGAS " + "  ) ORDER BY PARTIDA ");
		return sSql.toString();
	}

	/**
	 * Change combo box.
	 */
	public void changeComboBox() {
		// listFuente = catdaaRepository.getByClave(depAux);

		listFuente = fuentefRepository.getFuenteByClave(depAux);
	}

	public void downloadTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
	}

	public void viewTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.createFilePdfInline();
		}
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
}
