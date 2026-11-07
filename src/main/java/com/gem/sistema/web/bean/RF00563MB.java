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
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDay;
import static com.roonin.utils.UtilDate.getDateOfSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class RF00563MB.
 */
@ManagedBean(name = "rF00563MB")
@ViewScoped
public class RF00563MB extends BaseDirectReport {

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The firmas. */
	private Firmas firmas;

	/** The conctb. */
	private Conctb conctb;

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

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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
	 * @param firmas
	 *            the new firmas
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
	 * @param firmasRepository
	 *            the new firmas repository
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
	 * @param mes
	 *            the new mes
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
	 * @param listMes
	 *            the new list mes
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
	 * @param tcMesRepository
	 *            the new tc mes repository
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
	 * @param conctb
	 *            the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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
		jasperReporteName = "RF00563";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();

		parametrs.put("mesFin", getLastDay(Integer.valueOf(mes)));
		parametrs.put("mesDescripcion", tcMesRepository.findByMes(mes).getDescripcion());
		parametrs.put("anio", conctb.getAnoemp());
		parametrs.put("municipio", firmas.getCampo1());
		parametrs.put("pathImg", this.getUserDetails().getPathImgCab1());
		
		
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F27.getValue());
		parametrs.put("lblPresidente", firma.getPuesto().getPuesto());
		parametrs.put("txtPresidente", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F05.getValue());
		parametrs.put("lblSecretario", firma.getPuesto().getPuesto());
		parametrs.put("txtSecretario", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F03.getValue());
		parametrs.put("lblTesorero", firma.getPuesto().getPuesto());
		parametrs.put("txtTesorero", firma.getNombre());
		
		parametrs.put("fecha", getDateOfSystem());
		parametrs.put("sSql", this.generateQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector()));
		return parametrs;
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
	 * @param mes
	 *            the mes
	 * @param idSector
	 *            the id sector
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder auAnu = new StringBuilder();
		StringBuilder preAmp = new StringBuilder();
		StringBuilder totRec = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			auAnu.append(" CU.ABONOS_" + i + " +");
			preAmp.append(" CU.CARGOS_" + i + " +");
			totRec.append(" (C.ABONOS_" + i + " - C.CARGOS_" + i + ") +");
		}
		sSql.append(
				" SELECT T2.CUENTA , SUBSTR(T2.SCTA, 7, 4) SCTA , T2.SSCTA, T2.SSSCTA, T2.SSSSCTA, T2.NOMCTA, T2.AUANU, T2.PREAMP, T2.PRERED, T2.AUANUM, T2.TOTREC, (T2.AUANUM - T2.TOTREC ) TOTXREC "
						+ " FROM ( "
						+ " SELECT T1.CUENTA , T1.SCTA , T1.SSCTA , T1.SSSCTA , T1.SSSSCTA , T1.NOMCTA , T1.AUANU , T1.PREAMP , T1.PRERED , DECODE(T1.CUENTA, '8110' , (T1.AUANU + (T1.PREAMP - T1.PRERED )) , 0) AUANUM , T1.TOTREC "
						+ " FROM ( "
						+ " SELECT CU.CUENTA,CU.SCTA,CU.SSCTA,CU.SSSCTA,CU.SSSSCTA, CU.NOMCTA, SUM(CU.SALINI) AUANU "
						+ " , SUM( " + auAnu.substring(0, auAnu.length() - 1) + " ) PRERED " + " , SUM( "
						+ preAmp.substring(0, preAmp.length() - 1) + " ) PREAMP " + " , SUM( (SELECT  ( "
						+ totRec.substring(0, totRec.length() - 1) + " ) A FROM CUENTA C WHERE C.IDSECTOR = " + idSector
						+ " AND C.CUENTA = '8150' AND C.SCTA = CU.SCTA AND C.SSCTA = CU.SSCTA AND C.SSSCTA = CU.SSSCTA AND C.SSSSCTA = CU.SSSSCTA ) ) TOTREC "
						+ " FROM CUENTA CU " + " WHERE CU.CUENTA = '8110' AND CU.NOTCTA = 0 AND CU.IDSECTOR = "
						+ idSector + " " + " GROUP BY CU.CUENTA, CU.SCTA, CU.SSCTA, CU.SSSCTA, CU.SSSSCTA, CU.NOMCTA "
						+ " ORDER BY CU.SCTA, CU.SSCTA, CU.SSSCTA, CU.SSSSCTA, CU.CUENTA ASC ) T1 ) T2 ");
		return sSql.toString();
	}

}
