package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009212MB.
 */
@ManagedBean(name = "rF009212MB")
@ViewScoped
public class RF009212MB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	
	/** The mes. */
	private String mes;
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

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
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

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
		jasperReporteName = "rf009_2_1_2";
		endFilename = jasperReporteName + ".pdf";
		listMes = tcMesRepository.findAll();
		if(!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

		/* (non-Javadoc)
		 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
		 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		conctb = conctbRepository.findByIdsector(sector);
		TrPuestoFirma firma = null;
		
		parameters.put("pSql", this.generateQuery(Integer.valueOf(mes)));
		parameters.put("pAn", conctb.getAnoemp());
		parameters.put("pMes", Integer.valueOf(this.mes));
		parameters.put("pMesLetra", periodoRepositoy.findByTipoPeriodoAndPeriodo(1, Integer.valueOf(mes)).getDescripcion().toUpperCase());
		parameters.put("pDia", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pNomMunicipio", this.getUserDetails().getMunicipio());
		return parameters;
	}

	/* (non-Javadoc)
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
	 * @param mes the mes
	 * @return the string
	 */
	public String generateQuery(Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder g = new StringBuilder();
		StringBuilder cpt = new StringBuilder();
		for (int i = 1; i <= 12; i++) {
			a.append(" P.AUTO1_" + i + " +");
		}
		for (int i = 1; i <= mes; i++) {
			b.append(" P.EJPA1_" + i + " +");
			c.append(" P.EJXPA1_" + i + " +");
			d.append(" P.TOEJE1_" + i + " +");
			f.append(" P.AMPLI1_" + i + " +");
			g.append(" P.REDU1_" + i + " +");
			cpt.append(" P.COMPRO1_" + i + " +");
		}
		sSql.append(
				" SELECT  DEP , NOMBRE , PARTIDA , NOMGAS , A , B , C , D , F , G , CPT , ( ( A + F - G ) -D ) E , A1 , B1 , C1 , D1 , F1 , G1 , CPT1 , ( ( A1 + F1 - G1 ) -D1 ) E1 ")
				.append(" FROM( ").append("  SELECT  ").append("  P.PARTIDA ").append("  , CGM.CLAVE_CGM DEP ")
				.append("  , CGM.NOMBRE ").append("  , N.NOMGAS ").append("  , SUM( " + a.substring(0, a.length()-1).toString() + " ) A ")
				.append("  , SUM( " + b.substring(0, b.length()-1).toString() + " ) B ").append("  , SUM( " + c.substring(0, c.length()-1).toString() + " ) C ")
				.append("  , SUM( " + d.substring(0, d.length()-1).toString() + " ) D ").append("  , SUM( " + f.substring(0, f.length()-1).toString() + " ) F ")
				.append("  , SUM( " + g.substring(0, g.length()-1).toString() + " ) G ").append("  , SUM( " + cpt.substring(0, cpt.length()-1).toString() + " ) CPT ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + a.substring(0, a.length()-1).toString() + " ),0) ) A1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + b.substring(0, b.length()-1).toString() + " ),0 )) B1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + c.substring(0, c.length()-1).toString() + " ),0 )) C1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + d.substring(0, d.length()-1).toString() + " ),0 )) D1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + f.substring(0, f.length()-1).toString() + " ),0 )) F1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + g.substring(0, g.length()-1).toString() + " ),0 )) G1 ")
				.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + cpt.substring(0, cpt.length()-1).toString() + " ),0 )) CPT1 ")
				.append("    FROM PASO P, NATGAS N, (SELECT CLAVE CLAVE_CGM, NOMBRE FROM CATDGM) CGM ")
				.append("    WHERE P.PARTIDA=N.CLVGAS  ").append("      AND SUBSTR(P.CLAVE,1,3) = CGM.CLAVE_CGM ")
				.append("      AND P.IDSECTOR= N.IDSECTOR  ").append("      AND N.IDSECTOR=1 ")
				.append("  GROUP BY PARTIDA, CLAVE_CGM ,NOMGAS,NOMBRE ").append("  ) ORDER BY DEP, PARTIDA ");

		return sSql.toString();
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}
	
	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}
	
}
