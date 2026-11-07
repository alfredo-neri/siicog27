package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Class REstadoAvancePresupuestalEgresosDgDaFunProgProyFfMB.
 */
@ManagedBean(name = "rEstadoAvancePresupuestalEgresosDgDaFunProgProyFfMB")
@ViewScoped
public class REstadoAvancePresupuestalEgresosDgDaFunProgProyFfMB extends BaseDirectReport {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	/** The mes. */
	private String mes;

	/** The programa. */
	private String programa;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The list programa. */
	private List<String> listPrograma;

	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

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
	 * @param programa
	 *            the new programa
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
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
	 * Gets the list programa.
	 *
	 * @return the list programa
	 */
	public List<String> getListPrograma() {
		return listPrograma;
	}

	/**
	 * Sets the list programa.
	 *
	 * @param listPrograma
	 *            the new list programa
	 */
	public void setListPrograma(List<String> listPrograma) {
		this.listPrograma = listPrograma;
	}

	/**
	 * Gets the tcmes repository.
	 *
	 * @return the tcmes repository
	 */
	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	/**
	 * Sets the tcmes repository.
	 *
	 * @param tcmesRepository
	 *            the new tcmes repository
	 */
	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
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
	 * @param xcatproRepository
	 *            the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TrPuestoFirma firma = null;
		parameters.put("P_MES", Integer.valueOf(mes));
		parameters.put("P_SECTOR", sector);
		parameters.put("P_RESTA_ANO", 0);
		parameters.put("P_DIRE", programa.substring(0, 25));
		parameters.put("P_CAMPO1", conctb.getNomDep());
		parameters.put("P_NOMPRO", programa.substring(26));
		parameters.put("P_IMG", conctb.getImagePathLeft());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("P_L1", firma.getPuesto().getPuesto());
		parameters.put("P_N1", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("P_L2", firma.getPuesto().getPuesto());
		parameters.put("P_N2", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("P_L3", firma.getPuesto().getPuesto());
		parameters.put("P_N3", firma.getNombre());
			
		parameters.put("year", conctb.getAnoemp());

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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "EstadoAvancePresupuestalEgresosDgDaFunProgProyFf";
		endFilename = jasperReporteName + ".pdf";

		// llenar la lista
		listMes = tcmesRepository.findAll();
		listPrograma = xcatproRepository.getClvproByIdSector(this.getUserDetails().getIdSector());

		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		if (!CollectionUtils.isEmpty(listPrograma)) {
			programa = listPrograma.get(0);
		}

	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.createFilePdfInline();
		}
	}

	public void downloadTxt() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}

	}
	
	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
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

}
