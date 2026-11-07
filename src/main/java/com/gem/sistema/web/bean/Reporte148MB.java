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
import com.gem.sistema.business.domain.Pm3111;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm3111Repository;
import com.gem.sistema.business.service.catalogos.Pm3111Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte148MB.
 */
@ManagedBean(name = "reporte148MB")
@ViewScoped
public class Reporte148MB extends BaseDirectReport {
	
	/** The conctb. */
	private Conctb conctb;

	/** The anual. */
	private Integer anual;

	/** The pm 3111. */
	private Pm3111 pm3111;
	
	/** The list pm 3111. */
	private List<Pm3111> listPm3111;

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

	/** The pm 3111 service. */
	@ManagedProperty("#{pm3111Service}")
	private Pm3111Service pm3111Service;

	/** The pm 3111 repository. */
	@ManagedProperty("#{pm3111Repository}")
	private Pm3111Repository pm3111Repository;

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
	 * Gets the pm 3111.
	 *
	 * @return the pm 3111
	 */
	public Pm3111 getPm3111() {
		return pm3111;
	}

	/**
	 * Sets the pm 3111.
	 *
	 * @param pm3111 the new pm 3111
	 */
	public void setPm3111(Pm3111 pm3111) {
		this.pm3111 = pm3111;
	}

	/**
	 * Gets the list pm 3111.
	 *
	 * @return the list pm 3111
	 */
	public List<Pm3111> getListPm3111() {
		return listPm3111;
	}

	/**
	 * Sets the list pm 3111.
	 *
	 * @param listPm3111 the new list pm 3111
	 */
	public void setListPm3111(List<Pm3111> listPm3111) {
		this.listPm3111 = listPm3111;
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
	 * Gets the pm 3111 service.
	 *
	 * @return the pm 3111 service
	 */
	public Pm3111Service getPm3111Service() {
		return pm3111Service;
	}

	/**
	 * Sets the pm 3111 service.
	 *
	 * @param pm3111Service the new pm 3111 service
	 */
	public void setPm3111Service(Pm3111Service pm3111Service) {
		this.pm3111Service = pm3111Service;
	}

	/**
	 * Gets the pm 3111 repository.
	 *
	 * @return the pm 3111 repository
	 */
	public Pm3111Repository getPm3111Repository() {
		return pm3111Repository;
	}

	/**
	 * Sets the pm 3111 repository.
	 *
	 * @param pm3111Repository the new pm 3111 repository
	 */
	public void setPm3111Repository(Pm3111Repository pm3111Repository) {
		this.pm3111Repository = pm3111Repository;
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

		pm3111 = pm3111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (null == pm3111) {
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
		
		jasperReporteName = "reporte148";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		Integer idSector = this.getUserDetails().getIdSector();
		conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();

		parameters.put("municipioName", conctb.getNomDep());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("year", anual);
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("idSector", idSector);
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F22.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F06.getValue());
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
		pm3111 = new Pm3111();

	}

	/**
	 * Save.
	 */
	public void save() {
		pm3111 = this.entitySave(pm3111);
		if (bModificar) {
			pm3111 = this.notEmpty(pm3111);
			pm3111Repository.save(pm3111);

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

			boolean  bandera = pm3111Service.save(pm3111);
			
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
		pm3111Service.delete(pm3111);
		pm3111 = pm3111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if(pm3111 == null){
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
			pm3111 = pm3111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		} else
			pm3111 = pm3111Service.clean(entitySave(pm3111));
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
		bAdd = Boolean.TRUE;
		if (null == pm3111.getAnual()) {
            bBorrar = Boolean.TRUE;
            bBtnMoficar = Boolean.TRUE;
            bAdd = Boolean.FALSE;

        } else {
            bBorrar = Boolean.FALSE;
            bBtnMoficar = Boolean.FALSE;
        }
		
		bModificar = Boolean.FALSE;
		
		pm3111 = pm3111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param entity the entity
	 * @return the pm 3111
	 */
	public Pm3111 entitySave(Pm3111 entity) {
		pm3111 = new Pm3111();
		pm3111 = entity;
		pm3111.setUserid(this.getUserDetails().getUsername());
		pm3111.setIdRef(0L);
		pm3111.setIdsector(this.getUserDetails().getIdSector());
		pm3111.setFeccap(Calendar.getInstance().getTime());
		pm3111.setCapturo(this.getUserDetails().getUsername());
		pm3111.setAnual(anual);
		return pm3111;
	}
	
	/**
	 * Not empty.
	 *
	 * @param pm3111 the pm 3111
	 * @return the pm 3111
	 */
	public Pm3111 notEmpty(Pm3111 pm3111) {
		if (null == pm3111.getCpci()) {
			pm3111.setCpci(0);
		}
		if (null == pm3111.getObscpci()) {
			pm3111.setObscpci("");
		}
		if (null == pm3111.getNtloc()) {
			pm3111.setNtloc(0);
		}
		if (null == pm3111.getObsntloc()) {
			pm3111.setObsntloc("");
		}
		return pm3111;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	
	
}
