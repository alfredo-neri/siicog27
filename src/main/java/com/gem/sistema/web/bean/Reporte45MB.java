
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
import com.gem.sistema.business.domain.Pm4411;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm4411Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm4411Service;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte45MB.
 */
@ManagedBean(name = "reporte45MB")
@ViewScoped
public class Reporte45MB extends BaseDirectReport {
	private static final String EMPTY_VALUE = "";
	private Pm4411 pm4411;
	private boolean banderaAM;
	/** The mes. */
	private String mes;

	/** The conctb. */
	private Conctb conctb;

	/** The firmas. */
	private Firmas firmas;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{pm4411Service}")
	private Pm4411Service pm4411Service;

	/** The pm 4411 repository. */
	@ManagedProperty("#{pm4411Repository}")
	private Pm4411Repository pm4411Repository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@PostConstruct
	public void init() {

		jasperReporteName = "reporte45";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		if (pm4411Repository.countRows(this.getUserDetails().getIdSector(), Integer.valueOf(mes)) > 0) {
			pm4411 = pm4411Repository.findAllByIdsectorAndMes(this.getUserDetails().getIdSector(),
					Integer.valueOf(mes));
			banderaAM = true;
		} else {
			this.add();
			banderaAM = false;
		}
	}

	/** The parametros. */
	Map<String, Object> parametros;

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		
		this.save();
		
		parametros = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		parametros.put("mes", Integer.parseInt(mes));
		parametros.put("sector", this.getUserDetails().getIdSector());
		parametros.put("No.Firmas", 3);
		parametros.put("obs1", pm4411.getObs1());
		parametros.put("obs2", pm4411.getObs2());
		parametros.put("obs3", pm4411.getObs3());
		parametros.put("obs4", pm4411.getObs4());
		parametros.put("obs5", pm4411.getObs5());
		parametros.put("obs6", pm4411.getObs6());
		parametros.put("imagen", this.getUserDetails().getPathImgCab1());
		parametros.put("firmaP1", firmas.getL4());
		parametros.put("firmaN1", firmas.getN4());
		parametros.put("firmaP2", firmas.getL5());
		parametros.put("firmaN2", firmas.getN5());
		parametros.put("firmaP3", firmas.getL3());
		parametros.put("firmaN3", firmas.getN3());
		parametros.put("anio", firmas.getCampo3());
		parametros.put("nomMunicipio", firmas.getCampo1());
		parametros.put("clveMunicipio", conctb.getClave());

		return parametros;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Pm4411 changeMes() {
		if (pm4411Repository.countRows(this.getUserDetails().getIdSector(), Integer.valueOf(mes)) > 0) {
			pm4411 = pm4411Repository.findAllByIdsectorAndMes(this.getUserDetails().getIdSector(),
					Integer.valueOf(mes));
			banderaAM = true;
		} else {
			this.add();
			banderaAM = false;
		}

		return pm4411;
	}

	public void save() {
		pm4411 = this.entitySave(pm4411);
		if (banderaAM) {
			pm4411Repository.save(pm4411);
		} else {
			pm4411Service.save(pm4411);
		}
	}

	public Pm4411 entitySave(Pm4411 entity) {
		pm4411 = new Pm4411();
		pm4411 = entity;
		pm4411.setUserid(this.getUserDetails().getUsername());
		pm4411.setIdRef(0L);
		pm4411.setIdsector(this.getUserDetails().getIdSector());
		pm4411.setFeccap(Calendar.getInstance().getTime());
		pm4411.setCapturo(this.getUserDetails().getUsername());
		pm4411.setMes(Integer.valueOf(mes));
		return pm4411;
	}

	public void add() {
		pm4411 = new Pm4411();
		pm4411.setMes(Integer.valueOf(mes));
		pm4411.setObs1(EMPTY_VALUE);
		pm4411.setObs2(EMPTY_VALUE);
		pm4411.setObs3(EMPTY_VALUE);
		pm4411.setObs4(EMPTY_VALUE);
		pm4411.setObs5(EMPTY_VALUE);
		pm4411.setObs6(EMPTY_VALUE);
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null, this.getUserDetails().getIdSector());
		}
		this.createFilePdfInline();
	}
	
	public Pm4411 getPm4411() {
		return pm4411;
	}

	public void setPm4411(Pm4411 pm4411) {
		this.pm4411 = pm4411;
	}

	public boolean isBanderaAM() {
		return banderaAM;
	}

	public void setBanderaAM(boolean banderaAM) {
		this.banderaAM = banderaAM;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	public List<TcMes> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Pm4411Service getPm4411Service() {
		return pm4411Service;
	}

	public void setPm4411Service(Pm4411Service pm4411Service) {
		this.pm4411Service = pm4411Service;
	}

	public Pm4411Repository getPm4411Repository() {
		return pm4411Repository;
	}

	public void setPm4411Repository(Pm4411Repository pm4411Repository) {
		this.pm4411Repository = pm4411Repository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
