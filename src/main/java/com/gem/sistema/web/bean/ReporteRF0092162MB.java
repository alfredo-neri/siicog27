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

import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0092162MB.
 */
@ManagedBean(name = "reporteRF0092162MB")
@ViewScoped
public class ReporteRF0092162MB extends BaseDirectReport{
	
	/** The clv dep. */
	private String clvDep;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** The list catdgm. */
	private List<Catdgm> listCatdgm;
	
	
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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

	/**
	 * Gets the catdgm repository.
	 *
	 * @return the catdgm repository
	 */
	public CatdgmRepository getCatdgmRepository() {
		return catdgmRepository;
	}

	/**
	 * Sets the catdgm repository.
	 *
	 * @param catdgmRepository the new catdgm repository
	 */
	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
	}

	/** The catdgm repository. */
	@ManagedProperty("#{catdgmRepository}")
	private CatdgmRepository catdgmRepository;
	
	/**
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
	}

	/**
	 * Gets the list catdgm.
	 *
	 * @return the list catdgm
	 */
	public List<Catdgm> getListCatdgm() {
		return listCatdgm;
	}

	/**
	 * Sets the list catdgm.
	 *
	 * @param listCatdgm the new list catdgm
	 */
	public void setListCatdgm(List<Catdgm> listCatdgm) {
		this.listCatdgm = listCatdgm;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){      
		jasperReporteName = "RF009_2_1_6_2";
		endFilename = jasperReporteName + ".pdf";
		listCatdgm = catdgmRepository.findAll();
		if(!CollectionUtils.isEmpty(listCatdgm)){
			clvDep = listCatdgm.get(0).getClave();
			
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		
		
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		Integer idSector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = new TrPuestoFirma();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		  
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("municipio", conctb.getNomDep());
		parameters.put("pathImg", idSector==1? conctb.getImagePathRigth(): conctb.getImagePathLeft());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("Dependencia", this.clvDep.substring(0,3) );
		parameters.put("DependenciaName", this.clvDep );
		
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());
		
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}