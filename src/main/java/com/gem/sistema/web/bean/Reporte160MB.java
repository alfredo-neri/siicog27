package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm2411;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm2411Repository;
import com.gem.sistema.business.service.catalogos.Pm2411Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte160MB.
 */
@ManagedBean(name = "reporte160MB")
@ViewScoped
public class Reporte160MB extends BaseDirectReport {
	
	/** The conctb. */
	private Conctb conctb;

	/** The anual. */
	private Integer anual;

	/** The pm 2411. */
	private Pm2411 pm2411;
	
	/** The list pm 2411. */
	private List<Pm2411> listPm2411;

	/** The b lbl trimestre. */
	private boolean bLblTrimestre = Boolean.TRUE;
	
	/** The b combo tri. */
	private boolean bComboTri = Boolean.FALSE;
	
	/** The b lbl. */
	private boolean bLbl = Boolean.TRUE;
	
	/** The b txt. */
	private boolean bTxt = Boolean.FALSE;
	
	/** The b V save. */
	private boolean bVSave = Boolean.FALSE;
	
	/** The b V modificar. */
	private boolean bVModificar = Boolean.TRUE;
	
	/** The b btn moficar. */
	private boolean bBtnMoficar = Boolean.TRUE;
	
	/** The b modificar. */
	private boolean bModificar = Boolean.FALSE;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.TRUE;
	
	/** The b add. */
	private boolean bAdd = Boolean.FALSE;

	/** The pm 2411 service. */
	@ManagedProperty("#{pm2411Service}")
	private Pm2411Service pm2411Service;

	/** The pm 2411 repository. */
	@ManagedProperty("#{pm2411Repository}")
	private Pm2411Repository pm2411Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

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
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return anual;
	}

	/**
	 * Sets the anual.
	 *
	 * @param anual the new anual
	 */
	public void setAnual(Integer anual) {
		this.anual = anual;
	}

	/**
	 * Gets the pm 2411.
	 *
	 * @return the pm 2411
	 */
	public Pm2411 getPm2411() {
		return pm2411;
	}

	/**
	 * Sets the pm 2411.
	 *
	 * @param pm2411 the new pm 2411
	 */
	public void setPm2411(Pm2411 pm2411) {
		this.pm2411 = pm2411;
	}

	/**
	 * Gets the list pm 2411.
	 *
	 * @return the list pm 2411
	 */
	public List<Pm2411> getListPm2411() {
		return listPm2411;
	}

	/**
	 * Sets the list pm 2411.
	 *
	 * @param listPm2411 the new list pm 2411
	 */
	public void setListPm2411(List<Pm2411> listPm2411) {
		this.listPm2411 = listPm2411;
	}

	/**
	 * Checks if is b lbl trimestre.
	 *
	 * @return true, if is b lbl trimestre
	 */
	public boolean isbLblTrimestre() {
		return bLblTrimestre;
	}

	/**
	 * Sets the b lbl trimestre.
	 *
	 * @param bLblTrimestre the new b lbl trimestre
	 */
	public void setbLblTrimestre(boolean bLblTrimestre) {
		this.bLblTrimestre = bLblTrimestre;
	}

	/**
	 * Checks if is b combo tri.
	 *
	 * @return true, if is b combo tri
	 */
	public boolean isbComboTri() {
		return bComboTri;
	}

	/**
	 * Sets the b combo tri.
	 *
	 * @param bComboTri the new b combo tri
	 */
	public void setbComboTri(boolean bComboTri) {
		this.bComboTri = bComboTri;
	}

	/**
	 * Checks if is b lbl.
	 *
	 * @return true, if is b lbl
	 */
	public boolean isbLbl() {
		return bLbl;
	}

	/**
	 * Sets the b lbl.
	 *
	 * @param bLbl the new b lbl
	 */
	public void setbLbl(boolean bLbl) {
		this.bLbl = bLbl;
	}

	/**
	 * Checks if is b txt.
	 *
	 * @return true, if is b txt
	 */
	public boolean isbTxt() {
		return bTxt;
	}

	/**
	 * Sets the b txt.
	 *
	 * @param bTxt the new b txt
	 */
	public void setbTxt(boolean bTxt) {
		this.bTxt = bTxt;
	}

	/**
	 * Checks if is b V save.
	 *
	 * @return true, if is b V save
	 */
	public boolean isbVSave() {
		return bVSave;
	}

	/**
	 * Sets the b V save.
	 *
	 * @param bVSave the new b V save
	 */
	public void setbVSave(boolean bVSave) {
		this.bVSave = bVSave;
	}

	/**
	 * Checks if is b V modificar.
	 *
	 * @return true, if is b V modificar
	 */
	public boolean isbVModificar() {
		return bVModificar;
	}

	/**
	 * Sets the b V modificar.
	 *
	 * @param bVModificar the new b V modificar
	 */
	public void setbVModificar(boolean bVModificar) {
		this.bVModificar = bVModificar;
	}

	/**
	 * Checks if is b btn moficar.
	 *
	 * @return true, if is b btn moficar
	 */
	public boolean isbBtnMoficar() {
		return bBtnMoficar;
	}

	/**
	 * Sets the b btn moficar.
	 *
	 * @param bBtnMoficar the new b btn moficar
	 */
	public void setbBtnMoficar(boolean bBtnMoficar) {
		this.bBtnMoficar = bBtnMoficar;
	}

	/**
	 * Checks if is b modificar.
	 *
	 * @return true, if is b modificar
	 */
	public boolean isbModificar() {
		return bModificar;
	}

	/**
	 * Sets the b modificar.
	 *
	 * @param bModificar the new b modificar
	 */
	public void setbModificar(boolean bModificar) {
		this.bModificar = bModificar;
	}

	/**
	 * Checks if is b borrar.
	 *
	 * @return true, if is b borrar
	 */
	public boolean isbBorrar() {
		return bBorrar;
	}

	/**
	 * Sets the b borrar.
	 *
	 * @param bBorrar the new b borrar
	 */
	public void setbBorrar(boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	/**
	 * Checks if is b add.
	 *
	 * @return true, if is b add
	 */
	public boolean isbAdd() {
		return bAdd;
	}

	/**
	 * Sets the b add.
	 *
	 * @param bAdd the new b add
	 */
	public void setbAdd(boolean bAdd) {
		this.bAdd = bAdd;
	}

	/**
	 * Gets the pm 2411 service.
	 *
	 * @return the pm 2411 service
	 */
	public Pm2411Service getPm2411Service() {
		return pm2411Service;
	}

	/**
	 * Sets the pm 2411 service.
	 *
	 * @param pm2411Service the new pm 2411 service
	 */
	public void setPm2411Service(Pm2411Service pm2411Service) {
		this.pm2411Service = pm2411Service;
	}

	/**
	 * Gets the pm 2411 repository.
	 *
	 * @return the pm 2411 repository
	 */
	public Pm2411Repository getPm2411Repository() {
		return pm2411Repository;
	}

	/**
	 * Sets the pm 2411 repository.
	 *
	 * @param pm2411Repository the new pm 2411 repository
	 */
	public void setPm2411Repository(Pm2411Repository pm2411Repository) {
		this.pm2411Repository = pm2411Repository;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.TRUE;

		pm2411 = pm2411Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (null == pm2411) {
			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
		}

		anual = conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp();
		
		jasperReporteName = "Reporte160";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		parameters.put("pImg", getUserDetails().getPathImgCab1());
		parameters.put("pNomMun", conctb.getNomDep());
		parameters.put("pClave", conctb.getClave());
		parameters.put("pAnio", anual);
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		parameters.put("pIdSector", getUserDetails().getIdSector());
		parameters.put("pNoFirmas", 3);
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

	/**
	 * Adds the.
	 */
	public void add() {
		bLblTrimestre = Boolean.FALSE;
		bComboTri = Boolean.TRUE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;
		pm2411 = new Pm2411();

	}

	/**
	 * Save.
	 */
	public void save() {
		pm2411 = this.entitySave(pm2411);
		if (bModificar) {
			pm2411Repository.save(pm2411);

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modificaron los datos correctamente");

			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.FALSE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bAdd = Boolean.TRUE;
		} else {

			boolean  bandera = pm2411Service.save(pm2411);
			
			if(bandera ){
				bLblTrimestre = Boolean.TRUE;
				bComboTri = Boolean.FALSE;
				bLbl = Boolean.TRUE;
				bTxt = Boolean.FALSE;
				bVSave = Boolean.FALSE;
				bVModificar = Boolean.TRUE;
				bBtnMoficar = Boolean.FALSE;
				bModificar = Boolean.FALSE;
				bBorrar = Boolean.FALSE;
				bAdd = Boolean.TRUE;
			}
			
		}

	}

	/**
	 * Delete.
	 */
	public void delete() {
		pm2411Service.delete(pm2411);
		pm2411 = pm2411Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if(pm2411 == null){
			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
		}
	}

	/**
	 * Clean.
	 */
	public void clean() {
		if (bModificar) {
			pm2411 = pm2411Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		} else
			pm2411 = pm2411Service.clean(pm2411);
	}

	/**
	 * Modify.
	 */
	public void modify() {

		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.TRUE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;

	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		
		pm2411 = pm2411Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		
		if (null != pm2411) {
            bBorrar = Boolean.FALSE;
            bBtnMoficar = Boolean.FALSE;
            bAdd = Boolean.TRUE;
        } else {
            bBorrar = Boolean.TRUE;
            bBtnMoficar = Boolean.TRUE;
            bAdd = Boolean.FALSE;
        }
		
		bModificar = Boolean.FALSE;
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param entity the entity
	 * @return the pm 2411
	 */
	public Pm2411 entitySave(Pm2411 entity) {
		pm2411 = new Pm2411();
		pm2411 = entity;
		pm2411.setUserid(this.getUserDetails().getUsername());
		pm2411.setIdRef(0L);
		pm2411.setIdsector(this.getUserDetails().getIdSector());
		pm2411.setFeccap(Calendar.getInstance().getTime());
		pm2411.setCapturo(this.getUserDetails().getUsername());
		pm2411.setAnual(anual);
		return pm2411;
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
