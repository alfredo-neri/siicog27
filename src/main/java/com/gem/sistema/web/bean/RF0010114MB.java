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
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010114MB.
 */
@ManagedBean
@ViewScoped
public class RF0010114MB extends BaseDirectReport {

	/** The Constant MESSAGE_ERROR. */
	private static final String MESSAGE_ERROR = "El Mes Inical debe ser menor o igual al Mes Final";

	/** The mes inicial. */
	private String mesInicial;

	/** The mes final. */
	private String mesFinal;

	/** The list mes ini. */
	private List<TcMes> listMesIni;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
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

	/**
	 * Gets the list mes ini.
	 *
	 * @return the list mes ini
	 */
	public List<TcMes> getListMesIni() {
		return listMesIni;
	}

	/**
	 * Sets the list mes ini.
	 *
	 * @param listMesIni the new list mes ini
	 */
	public void setListMesIni(List<TcMes> listMesIni) {
		this.listMesIni = listMesIni;
	}

	/**
	 * Gets the list mes fin.
	 *
	 * @return the list mes fin
	 */
	public List<TcMes> getListMesFin() {
		return listMesFin;
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

	/** The list mes fin. */
	private List<TcMes> listMesFin;

	/**
	 * Gets the mes inicial.
	 *
	 * @return the mes inicial
	 */
	public String getMesInicial() {
		return mesInicial;
	}

	/**
	 * Sets the mes inicial.
	 *
	 * @param mesInicial the new mes inicial
	 */
	public void setMesInicial(String mesInicial) {
		this.mesInicial = mesInicial;
	}

	/**
	 * Gets the mes final.
	 *
	 * @return the mes final
	 */
	public String getMesFinal() {
		return mesFinal;
	}

	/**
	 * Sets the mes final.
	 *
	 * @param mesFinal the new mes final
	 */
	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
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
		jasperReporteName = "RF0010_1_14";
		endFilename = jasperReporteName + ".pdf";

		listMesIni = tcMesRepository.findAll();
		listMesFin = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMesIni)) {
			mesInicial = listMesIni.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listMesFin)) {
			mesFinal = listMesFin.get(0).getMes();
		}

	}

	/** The parametros. */
	Map<String, Object> parametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parametros = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();

		parametros.put("mesInicial", Integer.parseInt(mesInicial));
		parametros.put("mesFinal", Integer.parseInt(mesFinal));
		parametros.put("lastdayMesFinal", getLastDayByAnoEmp(Integer.parseInt(mesFinal), conctb.getAnoemp()));
		parametros.put("year", conctb.getAnoemp());
		parametros.put("idSector", this.getUserDetails().getIdSector());
		parametros.put("municipioName",conctb.getNomDep());
		parametros.put("imagePath", idSector==1? conctb.getImagePathRigth(): conctb.getImagePathLeft());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F01.getValue());
		parametros.put("firmaP1", firma.getPuesto().getPuesto());
		parametros.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F02.getValue());
		parametros.put("firmaP2", firma.getPuesto().getPuesto());
		parametros.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parametros.put("firmaP3", firma.getPuesto().getPuesto());
		parametros.put("firmaN3", firma.getNombre());

		return parametros;
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
	 * On change valida meses.
	 */
	public void onChangeValidaMeses() {
		if (Integer.valueOf(mesInicial) > Integer.valueOf(mesFinal)) {
			generateNotificationFront(SEVERITY_INFO, "Info", MESSAGE_ERROR);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesInicial), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}

		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesFinal), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}
	}

}
