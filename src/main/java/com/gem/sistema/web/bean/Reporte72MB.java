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

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte72MB.
 */
@ManagedBean(name = "reporte72MB")
@ViewScoped
public class Reporte72MB extends BaseDirectReport {
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The secretaria. */
	private String secretaria;
	
	/** The list clvdep. */
	private List<String> listClvdep;



	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

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
	 * Gets the secretaria.
	 *
	 * @return the secretaria
	 */
	public String getSecretaria() {
		return secretaria;
	}

	/**
	 * Sets the secretaria.
	 *
	 * @param secretaria the new secretaria
	 */
	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
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
	 * Gets the list clvdep.
	 *
	 * @return the list clvdep
	 */
	public List<String> getListClvdep() {
		return listClvdep;
	}

	/**
	 * Sets the list clvdep.
	 *
	 * @param listClvdep the new list clvdep
	 */
	public void setListClvdep(List<String> listClvdep) {
		this.listClvdep = listClvdep;
	}

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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "reporte72";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		listClvdep = catdepRepository.getClvdep3ByIdsector(this.getUserDetails().getIdSector());
		if (!CollectionUtils.isEmpty(listClvdep)) {
			secretaria = listClvdep.get(0);
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();

		parametrs.put("imagen", this.getUserDetails().getPathImgCab1());
		parametrs.put("mes", Integer.valueOf(mes));
		parametrs.put("sector", this.getUserDetails().getIdSector());
		parametrs.put("secretaria", secretaria);
		parametrs.put("anio", conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());

		return parametrs;

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {

		return null;
	}

}
