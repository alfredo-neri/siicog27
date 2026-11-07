package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDay;


// TODO: Auto-generated Javadoc
/**
 * The Class RF009126GMB.
 */
@ManagedBean(name = "rF009126GMB")
@ViewScoped
public class RF009126GMB extends BaseDirectReport {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RF009126MB.class);
	
	/** The id. */
	String id;
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The clave. */
	private String clave;
	
	/** The programa. */
	private String programa;
	
	/** The id reporte. */
	private Integer idReporte;
	
	/** The bandera. */
	private boolean bandera = Boolean.TRUE;
	
	/** The list clave. */
	private List<String> listClave;
	
	/** The list programa. */
	private List<Xcatpro> listPrograma;
	
	/** The conctb. */
	private Conctb conctb;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;
	
	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
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
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	
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
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}
	
	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the programa.
	 *
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * Sets the programa.
	 *
	 * @param programa the new programa
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * Gets the id reporte.
	 *
	 * @return the id reporte
	 */
	public Integer getIdReporte() {
		return idReporte;
	}

	/**
	 * Sets the id reporte.
	 *
	 * @param idReporte the new id reporte
	 */
	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}

	/**
	 * Checks if is bandera.
	 *
	 * @return true, if is bandera
	 */
	public boolean isBandera() {
		return bandera;
	}

	/**
	 * Sets the bandera.
	 *
	 * @param bandera the new bandera
	 */
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	/**
	 * Gets the list clave.
	 *
	 * @return the list clave
	 */
	public List<String> getListClave() {
		return listClave;
	}

	/**
	 * Sets the list clave.
	 *
	 * @param listClave the new list clave
	 */
	public void setListClave(List<String> listClave) {
		this.listClave = listClave;
	}

	/**
	 * Gets the list programa.
	 *
	 * @return the list programa
	 */
	public List<Xcatpro> getListPrograma() {
		return listPrograma;
	}

	/**
	 * Sets the list programa.
	 *
	 * @param listPrograma the new list programa
	 */
	public void setListPrograma(List<Xcatpro> listPrograma) {
		this.listPrograma = listPrograma;
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
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
		jasperReporteName = "rf009126";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		listClave =xcatproRepository.getClaveById(this.getUserDetails().getIdSector());
		listPrograma =xcatproRepository.getClave(listClave.get(0), this.getUserDetails().getIdSector());
		
		// llenar la lista
		listMes = tcMesRepository.findAll();
		listClave =xcatproRepository.getClaveById(this.getUserDetails().getIdSector());
		listPrograma =xcatproRepository.getClave(listClave.get(0), this.getUserDetails().getIdSector());
		
		
		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		
		if (!CollectionUtils.isEmpty(listClave)) {
			clave = listClave.get(0);
			programa=listPrograma.get(0).getClvfun()+listPrograma.get(0).getClvfin();
		}
	}
	
	/**
	 * Union box.
	 */
	public void unionBox(){
		listPrograma = xcatproRepository.getClave(clave, this.getUserDetails().getIdSector());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();

		Integer idSector = this.getUserDetails().getIdSector();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		firmas = firmasRepository.findAllByIdsector(sector);
		TrPuestoFirma firma = new TrPuestoFirma();
		
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parametrs.put("pNomMun", firmas.getCampo1());
		parametrs.put("pClvDep", clave.substring(0,6));
		parametrs.put("pClvProgram", programa);
		parametrs.put("pImgMuni", this.getUserDetails().getPathImgCab1());
		parametrs.put("pNomMes", tcMesRepository.findByMes(mes).getDescripcion());
		parametrs.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parametrs.put("pAnio", conctb.getAnoemp());
		parametrs.put("pNomDepen", this.getUserDetails().getManagementAdmin());
		parametrs.put("pL1", firmas.getL27());
		parametrs.put("pL2", firmas.getL5());
		parametrs.put("pL3", firmas.getL3());
		parametrs.put("pN1", firmas.getN27());
		parametrs.put("pN2", firmas.getN5());
		parametrs.put("pN3", firmas.getN3());
		
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F04.getValue());
		parametrs.put("pL1", firma.getPuesto().getPuesto());
		parametrs.put("pN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F05.getValue());
		parametrs.put("pL2", firma.getPuesto().getPuesto());
		parametrs.put("pN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F27.getValue());
		parametrs.put("pL3", firma.getPuesto().getPuesto());
		parametrs.put("pN3", firma.getNombre());
		
		parametrs.put("pSql", this.generateQuery(Integer.valueOf(mes), clave.substring(0,6), programa, this.getUserDetails().getIdSector()));
		
		return parametrs;
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
		 * @param clave the clave
		 * @param programa the programa
		 * @param sector the sector
		 * @return the string
		 */
		public String generateQuery( Integer mes, String clave, String programa, Integer sector) {
			StringBuilder sSql   = new StringBuilder();
			StringBuilder auto   = new StringBuilder().append(" P.AUTO1_1 ");
			StringBuilder ejpa   = new StringBuilder().append(" P.EJPA1_1 ");
			StringBuilder ejxpa  = new StringBuilder().append(" P.EJXPA1_1 ");
			StringBuilder toeje  = new StringBuilder().append(" P.TOEJE1_1 ");
			StringBuilder ampli  = new StringBuilder().append(" P.AMPLI1_1 ");
			StringBuilder redu   = new StringBuilder().append(" P.REDU1_1 ");
			StringBuilder compro = new StringBuilder().append(" P.COMPRO1_1 ");

			for (int i = 2; i < 13; i++) {
				auto.append("+ P.AUTO1_" + i + " ");
			}
			for (int i = 1 + 1; i <= mes; i++) {
				ampli.append("+ P.AMPLI1_" + i + " ");
				redu.append("+ P.REDU1_" + i + " ");
				ejpa.append("+ P.EJPA1_" + i + " ");
				ejxpa.append("+ P.EJXPA1_" + i + " ");
				toeje.append("+ P.TOEJE1_" + i + " ");
				compro.append("+ P.COMPRO1_" + i + " ");
			}

			sSql.append(" SELECT  "
			+ " SUBSTR(C.CLVDEP,1,6) DEP "
			+ " , P.PARTIDA  "
			+ " , N.NOMGAS "
			+ " , ( " + auto + " ) AUTO1 "
			+ " , ( " + ampli + ") AMPLI "
			+ " , ( " + redu + " ) REDU "
			+ " , ( " + ejpa + " ) EJPA "
			+ " , ( " + ejxpa + " ) EJXPA "
			+ " , ( " + toeje + " ) TOEJE "
			+ " , ( " + compro + ") COMPRO "
			+ " , C.NOMDEP "
			+ " , X.NOMPRO "
			+ " FROM NATGAS N, PASO P, CATDEP C, XCATPRO X "
			+ " WHERE SUBSTR(X.CLVPRO,11,15) = SUBSTR(P.PROGRAMA,1,15) "
			+ " AND SUBSTR(C.CLVDEP,1,6) = SUBSTR(P.CLAVE,1,6) "
			+ " AND P.PARTIDA=N.CLVGAS "
			+ " AND C.IDSECTOR= P.IDSECTOR "
			+ " AND N.IDSECTOR= P.IDSECTOR "
			+ " AND X.SECTORID= P.IDSECTOR "
			+ " AND P.IDSECTOR="+ sector +" "
			+ " AND SUBSTR(P.CLAVE,1,6)='" + clave +"' "
			+ " AND SUBSTR(P.PROGRAMA,1,15)= '" + programa + "' "
			+ " ORDER BY DEP,PARTIDA ASC ");
			LOGGER.info("QUERY,{}", sSql.toString());
			return sSql.toString();
		}
		public void viewPdf() {
			if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
				this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
				this.createFilePdfInline();
				;
			}
		}
}
