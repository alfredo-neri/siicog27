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
import com.gem.sistema.ennum.constans.ConstansEnum;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte263MB.
 */
@ManagedBean(name = "reporte263MB")
@ViewScoped
public class Reporte263MB extends BaseDirectReport {

	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The ceros. */
	private String ceros;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService; 
	
	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Gets the ceros.
	 *
	 * @return the ceros
	 */
	public String getCeros() {
		return ceros;
	}

	/**
	 * Sets the ceros.
	 *
	 * @param ceros the new ceros
	 */
	public void setCeros(String ceros) {
		this.ceros = ceros;
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte263";
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
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		
		
		parametrs.put("Campo1", this.getUserDetails().getMunicipio());
		parametrs.put("pImgMuni", this.getUserDetails().getPathImgCab1());
		parametrs.put("pSector", idSector);
		parametrs.put("pMes", Integer.valueOf(mes));
		parametrs.put("pAuto1", this.generateSumDinamic("auto1_", Integer.valueOf(mes)));
		parametrs.put("pToeje1", this.generateSumDinamicToeje("toeje1_", Integer.valueOf(mes)));
		parametrs.put("pAmpli1", this.generateSumDinamic("ampli1_", Integer.valueOf(mes)));
		parametrs.put("pRedu1", this.generateSumDinamic("redu1_", Integer.valueOf(mes)));
		parametrs.put("pLastDay", String.valueOf(getLastDay(Integer.valueOf(mes))));
		parametrs.put("pNomMes", tcMesRepository.findByMes(mes).getDescripcion());
		parametrs.put("pAnio", conctb.getAnoemp());
		
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parametrs.put("pL1", firma.getPuesto().getPuesto());
		parametrs.put("pN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		parametrs.put("pL2", firma.getPuesto().getPuesto());
		parametrs.put("pN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parametrs.put("pL3", firma.getPuesto().getPuesto());
		parametrs.put("pN3", firma.getNombre());
		parametrs.put("pL4", "");
		parametrs.put("pN4", "");
		parametrs.put("pNumFirmas", 3);
		parametrs.put("pCuentasCeros", ceros);
		return parametrs;
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
	 * Generate sum dinamic.
	 *
	 * @param campo the campo
	 * @param mes the mes
	 * @return the string
	 */
	public String generateSumDinamic(String campo, Integer mes) {
		StringBuilder retorna = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			if (i == 1) {
				retorna.append(campo + i);
			} else {
				retorna.append(" + " + campo + i);
			}

		}
		return retorna.toString();
	}

	/**
	 * Generate sum dinamic toeje.
	 *
	 * @param campo the campo
	 * @param mes the mes
	 * @return the string
	 */
	public String generateSumDinamicToeje(String campo, Integer mes) {
		StringBuilder retorna = new StringBuilder();

		if (mes == 1) {
			retorna.append("0");
		} else {
			for (int i = 1; i < mes; i++) {
				if (i == 1) {
					retorna.append(campo + i);
				} else {
					retorna.append(" + " + campo + i);
				}
			}
		}

		return retorna.toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

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
	
	

}
