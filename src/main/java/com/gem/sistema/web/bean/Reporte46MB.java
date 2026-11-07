
package com.gem.sistema.web.bean;

import java.util.Calendar;
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
import com.gem.sistema.business.domain.Pm5311;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm5311Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm5311Service;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte46MB.
 */
@ManagedBean(name="reporte46MB")
@ViewScoped
public class Reporte46MB extends BaseDirectReport {
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 5311. */
	private Pm5311 pm5311;
	
	/** The bandera AM. */
	private boolean banderaAM;
	/** The mes. */
	private String mes;

	/** The firmas. */
	private Firmas firmas;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The pm 5311 service. */
	@ManagedProperty("#{pm5311Service}")
	private Pm5311Service pm5311Service;

	/** The pm 5311 repository. */
	@ManagedProperty("#{pm5311Repository}")
	private Pm5311Repository pm5311Repository;

	/** The validate policy service. */
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
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

	/**
	 * Gets the pm 5311.
	 *
	 * @return the pm 5311
	 */
	public Pm5311 getPm5311() {
		return pm5311;
	}

	/**
	 * Sets the pm 5311.
	 *
	 * @param pm5311 the new pm 5311
	 */
	public void setPm5311(Pm5311 pm5311) {
		this.pm5311 = pm5311;
	}

	/**
	 * Checks if is bandera AM.
	 *
	 * @return true, if is bandera AM
	 */
	public boolean isBanderaAM() {
		return banderaAM;
	}

	/**
	 * Sets the bandera AM.
	 *
	 * @param banderaAM the new bandera AM
	 */
	public void setBanderaAM(boolean banderaAM) {
		this.banderaAM = banderaAM;
	}

	/**
	 * Gets the pm 5311 service.
	 *
	 * @return the pm 5311 service
	 */
	public Pm5311Service getPm5311Service() {
		return pm5311Service;
	}

	/**
	 * Sets the pm 5311 service.
	 *
	 * @param pm5311Service the new pm 5311 service
	 */
	public void setPm5311Service(Pm5311Service pm5311Service) {
		this.pm5311Service = pm5311Service;
	}

	/**
	 * Gets the pm 5311 repository.
	 *
	 * @return the pm 5311 repository
	 */
	public Pm5311Repository getPm5311Repository() {
		return pm5311Repository;
	}

	/**
	 * Sets the pm 5311 repository.
	 *
	 * @param pm5311Repository the new pm 5311 repository
	 */
	public void setPm5311Repository(Pm5311Repository pm5311Repository) {
		this.pm5311Repository = pm5311Repository;
	}

	/**
	 * Gets the validate policy service.
	 *
	 * @return the validate policy service
	 */
	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	/**
	 * Sets the validate policy service.
	 *
	 * @param validatePolicyService the new validate policy service
	 */
	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		
		jasperReporteName = "reporte46";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		
		if (pm5311Repository.countRows(this.getUserDetails().getIdSector(), Integer.valueOf(mes)) > 0) {
			pm5311 = pm5311Repository.findAllByIdsectorAndMes(this.getUserDetails().getIdSector(),
					Integer.valueOf(mes));
			banderaAM = true;
		} else {
			this.add();
			banderaAM = false;
		}
	}
	
	/** The parametros. */
	Map<String, Object> parametros;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		this.save();
		parametros = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		
		parametros.put("mes", Integer.parseInt(mes));
		parametros.put("sector", this.getUserDetails().getIdSector());
		parametros.put("No.Firmas", 3);
		parametros.put("obs1", pm5311.getObs1());
		parametros.put("obs2", pm5311.getObs2());
		parametros.put("obs3", pm5311.getObs3());
		parametros.put("imagen", this.getUserDetails().getPathImgCab1());
		parametros.put("firmaP1", firmas.getL4());
		parametros.put("frimaN1", firmas.getN4());
		parametros.put("firmaP2", firmas.getL5());
		parametros.put("firmaN2", firmas.getN5());
		parametros.put("firmaP3", firmas.getL3());
		parametros.put("firmaN3", firmas.getN3());
		parametros.put("anio", firmas.getCampo3());
		parametros.put("nomMunicipio",firmas.getCampo1());
		parametros.put("clveMunicipio",conctb.getClave());
		
		return parametros;
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
	 * Change mes.
	 *
	 * @return the pm 5311
	 */
	public Pm5311 changeMes() {
		if (pm5311Repository.countRows(this.getUserDetails().getIdSector(), Integer.valueOf(mes)) > 0) {
			pm5311 = pm5311Repository.findAllByIdsectorAndMes(this.getUserDetails().getIdSector(),
					Integer.valueOf(mes));
			banderaAM = true;
		} else {
			this.add();
			banderaAM = false;
		}

		return pm5311;
	}

	/**
	 * Save.
	 */
	public void save() {
		pm5311 = this.entitySave(pm5311);
		if (banderaAM) {
			pm5311Repository.save(pm5311);
		} else {
			pm5311Service.save(pm5311);
		}
	}

	/**
	 * Entity save.
	 *
	 * @param entity the entity
	 * @return the pm 5311
	 */
	public Pm5311 entitySave(Pm5311 entity) {
		pm5311 = new Pm5311();
		pm5311 = entity;
		pm5311.setUserid(this.getUserDetails().getUsername());
		pm5311.setIdRef(0L);
		pm5311.setIdsector(this.getUserDetails().getIdSector());
		pm5311.setFeccap(Calendar.getInstance().getTime());
		pm5311.setCapturo(this.getUserDetails().getUsername());
		pm5311.setMes(Integer.valueOf(mes));
		return pm5311;
	}

	/**
	 * Adds the.
	 */
	public void add() {
		pm5311 = new Pm5311();
		pm5311.setMes(Integer.valueOf(mes));
		pm5311.setObs1(EMPTY_VALUE);
		pm5311.setObs2(EMPTY_VALUE);
		pm5311.setObs3(EMPTY_VALUE);
		pm5311.setObs4(EMPTY_VALUE);
		pm5311.setObs5(EMPTY_VALUE);
		pm5311.setObs6(EMPTY_VALUE);
	}

	/**
	 * View pdf.
	 */
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
		}
		this.createFilePdfInline();
	}
}
