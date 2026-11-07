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

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class EdocoegrdepgMB.
 */
@ManagedBean(name = "edocoegrdepgMB")
@ViewScoped
public class EdocoegrdepgMB extends BaseDirectReport {

	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;
	 

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}
	
	

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "medocoegrdepg";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = new TrPuestoFirma();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		
		parametrs.put("pNomMun", this.getUserDetails().getMunicipio());
		
		parametrs.put("pImgMuni", conctb.getImagePathRigth());
		parametrs.put("pNomMes", tcMesRepository.findByMes(mes).getDescripcion());
		parametrs.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parametrs.put("pAnio", conctb.getAnoemp());
		parametrs.put("pSql", generateQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector()));
		
		if (idSector == 1) {
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
			parametrs.put("pL1", firma.getPuesto().getPuesto());
			parametrs.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
			parametrs.put("pL2", firma.getPuesto().getPuesto());
			parametrs.put("pN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
			parametrs.put("pL3", firma.getPuesto().getPuesto());
			parametrs.put("pN3", firma.getNombre());
		}else {
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parametrs.put("pL1", firma.getPuesto().getPuesto());
			parametrs.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parametrs.put("pL2", firma.getPuesto().getPuesto());
			parametrs.put("pN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parametrs.put("pL3", firma.getPuesto().getPuesto());
			parametrs.put("pN3", firma.getNombre());
		}
		
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
	
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}
	
	/**
	 * Generate query.
	 *
	 * @param mes the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String generateQuery( Integer mes, Integer sector) {
		StringBuilder sSql   = new StringBuilder();
		StringBuilder auto   = new StringBuilder().append(" P.AUTO1_1 ");
		StringBuilder auto2  = new StringBuilder().append(" P.AUTO1_1 ");
		StringBuilder toeje  = new StringBuilder().append(" P.TOEJE1_1 ");
		StringBuilder ampli  = new StringBuilder().append(" P.AMPLI1_1 ");
		StringBuilder redu   = new StringBuilder().append(" P.REDU1_1 ");
		
		for (int i = 2; i < 13; i++) {
			auto.append("+ P.AUTO1_" + i + " ");
		}
		
		for (int i = 1 + 1; i <= mes; i++) {
			auto2.append("+ P.AUTO1_" + i + " ");
			ampli.append("+ P.AMPLI1_" + i + " ");
			redu.append("+ P.REDU1_" + i + " ");			
			toeje.append("+ P.TOEJE1_" + i + " ");
		}

		sSql.append(" SELECT  "
		+ " GRUPO "
		+ " , PARTIDA "
		+ " , NOMGAS "
		+ " , NOMBRE "
		+ " , APROBADO "
		+ " , MODIFICADO_DEL_MES "
		+ " , EJERCIDO_DEL_MES "
		+ " , MODIFICADO_ACUMULADO "
		+ " , EJERCIDO_ACUMULADO "
		+ " , (MODIFICADO_ACUMULADO - EJERCIDO_ACUMULADO) ABSOLUTA "
		+ " , DECODE(MODIFICADO_ACUMULADO,0,0.00,(((MODIFICADO_ACUMULADO - EJERCIDO_ACUMULADO) * 100.0000)  / MODIFICADO_ACUMULADO)) PORCENTAJE "
		+ "  FROM  "
		+ "  ( "
		+ " SELECT  "
		+ " SUBSTR(P.CLAVE,1,3) GRUPO "
		+ " , P.PARTIDA "
		+ " , N.NOMGAS "
		+ " , C.NOMBRE "
		+ " , SUM(( " + auto + " )) APROBADO "
		+ " , SUM(( P.AUTO1_" + mes + " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + " )) MODIFICADO_DEL_MES "
		+ " , SUM(  P.TOEJE1_" + mes + ") EJERCIDO_DEL_MES  "
		+ " , SUM((( " + auto2 + ") + ( " + ampli + ") - ( " + redu + "))) MODIFICADO_ACUMULADO "
		+ " , SUM((  " + toeje + " )) EJERCIDO_ACUMULADO "
		+ "  FROM "
		+ " PASO P, NATGAS N, CATDGM C "
		+ " WHERE  "
		+ " N.IDSECTOR=P.IDSECTOR "
		+ " AND P.PARTIDA = N.CLVGAS AND SUBSTR(P.CLAVE,1,3)= C.CLAVE "
		+ " AND P.IDSECTOR=" + sector + " "
		+ " GROUP BY SUBSTR(P.CLAVE,1,3),P.PARTIDA,N.NOMGAS,C.NOMBRE "
		+ "  ORDER BY GRUPO,PARTIDA "
		+ " ) ");
		//+ " ) WHERE APROBADO <> 0 OR MODIFICADO_DEL_MES <> 0 OR EJERCIDO_DEL_MES <> 0 OR MODIFICADO_ACUMULADO <> 0 OR EJERCIDO_ACUMULADO <> 0");

		return sSql.toString();
	}


}
