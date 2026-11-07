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
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class EAPI_GlobalMB.
 */
@ManagedBean(name = "eAPI_GlobalMB")
@ViewScoped
public class EAPI_GlobalMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "EAPI_Global";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcmesRepository.findAll();

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
		Map<String, Object> parameters = new HashMap<String, Object>();
		TrPuestoFirma firma = new TrPuestoFirma();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		parameters.put("idSector", idSector);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("mes", Integer.valueOf(mes));
		parameters.put("dependenciaClave", conctb.getClave());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagePath", conctb.getImagePathRigth());

		if (idSector == 1) {
			switch (conctb.getClave().substring(0, 1)) {
			case "0":
				parameters.put("noFirmas", 4);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F01.getValue());
				parameters.put("firmaP1", firma.getPuesto().getPuesto());
				parameters.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F25.getValue());
				parameters.put("firmaP2", firma.getPuesto().getPuesto());
				parameters.put("firmaN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
				parameters.put("firmaP3", firma.getPuesto().getPuesto());
				parameters.put("firmaN3", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F02.getValue());
				parameters.put("firmaP4", firma.getPuesto().getPuesto());
				parameters.put("firmaN4", firma.getNombre());
			break;
			case "2":
				parameters.put("noFirmas", 2);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F10.getValue());
				parameters.put("firmaP1", firma.getPuesto().getPuesto());
				parameters.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F11.getValue());
				parameters.put("firmaP3", firma.getPuesto().getPuesto());
				parameters.put("firmaN3", firma.getNombre());
			break;
			case "3":
				parameters.put("noFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F07.getValue());
				parameters.put("firmaP1", firma.getPuesto().getPuesto());
				parameters.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
				parameters.put("firmaP2", firma.getPuesto().getPuesto());
				parameters.put("firmaN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F09.getValue());
				parameters.put("firmaP3", firma.getPuesto().getPuesto());
				parameters.put("firmaN3", firma.getNombre());
			break;
			case "4":
				parameters.put("noFirmas", 2);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F20.getValue());
				parameters.put("firmaP1", firma.getPuesto().getPuesto());
				parameters.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F21.getValue());
				parameters.put("firmaP3", firma.getPuesto().getPuesto());
				parameters.put("firmaN3", firma.getNombre());
			break;
			}
		}
		
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

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
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
	 * @param tcmesRepository the new tcmes repository
	 */
	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
