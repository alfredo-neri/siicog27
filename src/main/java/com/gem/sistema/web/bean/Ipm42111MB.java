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
import com.gem.sistema.business.domain.Pm4211;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm4211Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm4211Service;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Ipm42111MB.
 *
 * @author Alfredo
 */
@ManagedBean
@ViewScoped
public class Ipm42111MB extends BaseDirectReport {
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The bandera AM. */
	private boolean banderaAM;
	
	/** The pm 4211. */
	private Pm4211 pm4211;
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

	/** The pm 4211 service. */
	@ManagedProperty("#{pm4211Service}")
	private Pm4211Service pm4211Service;

	/** The pm 4211 repository. */
	@ManagedProperty("#{pm4211Repository}")
	private Pm4211Repository pm4211Repository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
	 * Gets the pm 4211 service.
	 *
	 * @return the pm 4211 service
	 */
	public Pm4211Service getPm4211Service() {
		return pm4211Service;
	}

	/**
	 * Sets the pm 4211 service.
	 *
	 * @param pm4211Service the new pm 4211 service
	 */
	public void setPm4211Service(Pm4211Service pm4211Service) {
		this.pm4211Service = pm4211Service;
	}

	/**
	 * Gets the pm 4211 repository.
	 *
	 * @return the pm 4211 repository
	 */
	public Pm4211Repository getPm4211Repository() {
		return pm4211Repository;
	}

	/**
	 * Sets the pm 4211 repository.
	 *
	 * @param pm4211Repository the new pm 4211 repository
	 */
	public void setPm4211Repository(Pm4211Repository pm4211Repository) {
		this.pm4211Repository = pm4211Repository;
	}

	/**
	 * Gets the pm 4211.
	 *
	 * @return the pm 4211
	 */
	public Pm4211 getPm4211() {
		return pm4211;
	}

	/**
	 * Sets the pm 4211.
	 *
	 * @param pm4211 the new pm 4211
	 */
	public void setPm4211(Pm4211 pm4211) {
		this.pm4211 = pm4211;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "i_pm4211_1";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		if (pm4211Repository.countRows(this.getUserDetails().getIdSector(), Integer.valueOf(mes)) > 0) {
			pm4211 = pm4211Repository.findAllByIdsectorAndMes(this.getUserDetails().getIdSector(),
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

		parametros.put("Mes", Integer.parseInt(mes));
		parametros.put("obs1", pm4211.getObs1());
		parametros.put("obs2", pm4211.getObs2());
		parametros.put("obs3", pm4211.getObs3());
		parametros.put("obs4", pm4211.getObs4());
		parametros.put("obs5", pm4211.getObs5());
		parametros.put("obs6", pm4211.getObs6());
		parametros.put("obs7", pm4211.getObs7());
		parametros.put("obs8", pm4211.getObs8());
		parametros.put("obs9", pm4211.getObs9());
		parametros.put("SECTOR", this.getUserDetails().getIdSector());
		parametros.put("imagen", this.getUserDetails().getPathImgCab1());
		parametros.put("CAMPO1", firmas.getCampo1());
		parametros.put("CLAVE", conctb.getClave());
		parametros.put("L3", firmas.getL4());
		parametros.put("L4", firmas.getL5());
		parametros.put("L5", firmas.getL3());
		parametros.put("N3", firmas.getN4());
		parametros.put("N4", firmas.getN5());
		parametros.put("N5", firmas.getN3());
		parametros.put("ANO_EMP", firmas.getCampo3());
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
	 * @return the pm 4211
	 */
	public Pm4211 changeMes(){
		if (pm4211Repository.countRows(this.getUserDetails().getIdSector(), Integer.valueOf(mes)) > 0){
			pm4211 = pm4211Repository.findAllByIdsectorAndMes(this.getUserDetails().getIdSector(), Integer.valueOf(mes));
			banderaAM = true;
		}else{
			this.add();
			banderaAM = false;
		}
		
		return pm4211;
	}
	
	/**
	 * Save.
	 */
	public void save() {
		pm4211 = this.entitySave(pm4211);
		if(banderaAM){
			pm4211Repository.save(pm4211);
		}else{
			pm4211Service.save(pm4211);
		}
	}

	/**
	 * Entity save.
	 *
	 * @param entity the entity
	 * @return the pm 4211
	 */
	public Pm4211 entitySave(Pm4211 entity) {
		pm4211 = new Pm4211();
		pm4211 = entity;
		pm4211.setUserid(this.getUserDetails().getUsername());
		pm4211.setIdRef(0L);
		pm4211.setIdsector(this.getUserDetails().getIdSector());
		pm4211.setFeccap(Calendar.getInstance().getTime());
		pm4211.setCapturo(this.getUserDetails().getUsername());
		pm4211.setMes(Integer.valueOf(mes));
		return pm4211;
	}

	/**
	 * Adds the.
	 */
	public void add() {
		pm4211 = new Pm4211();
		pm4211.setMes(Integer.valueOf(mes));
		pm4211.setObs1(EMPTY_VALUE);
		pm4211.setObs2(EMPTY_VALUE);
		pm4211.setObs3(EMPTY_VALUE);
		pm4211.setObs4(EMPTY_VALUE);
		pm4211.setObs5(EMPTY_VALUE);
		pm4211.setObs6(EMPTY_VALUE);
		pm4211.setObs7(EMPTY_VALUE);
		pm4211.setObs8(EMPTY_VALUE);
		pm4211.setObs9(EMPTY_VALUE);
		pm4211.setObs10(EMPTY_VALUE);
	}
	
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
		}
		this.createFilePdfInline();
	}
}
