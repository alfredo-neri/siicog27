package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm3211;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm3211Repository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm3211Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte154MB.
 */
@ManagedBean(name = "reporte154MB")
@ViewScoped
public class Reporte154MB extends BaseDirectReport {

	/** The conctb. */
	private Conctb conctb;

	/** The anual. */
	private Integer anual;

	/** The user. */
	private String user;

	/** The fecha. */
	private Date fecha;

	/** The pm 3211. */
	private Pm3211 pm3211;

	/** The list pm 3211. */
	private List<Pm3211> listPm3211;

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

	/** The pm 3211 service. */
	@ManagedProperty("#{pm3211Service}")
	private Pm3211Service pm3211Service;

	/** The pm 3211 repository. */
	@ManagedProperty("#{pm3211Repository}")
	private Pm3211Repository pm3211Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * Gets the pm 3211.
	 *
	 * @return the pm 3211
	 */
	public Pm3211 getPm3211() {
		return pm3211;
	}

	/**
	 * Sets the pm 3211.
	 *
	 * @param pm3211 the new pm 3211
	 */
	public void setPm3211(Pm3211 pm3211) {
		this.pm3211 = pm3211;
	}

	/**
	 * Gets the list pm 3211.
	 *
	 * @return the list pm 3211
	 */
	public List<Pm3211> getListPm3211() {
		return listPm3211;
	}

	/**
	 * Sets the list pm 3211.
	 *
	 * @param listPm3211 the new list pm 3211
	 */
	public void setListPm3211(List<Pm3211> listPm3211) {
		this.listPm3211 = listPm3211;
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
	 * Gets the pm 3211 service.
	 *
	 * @return the pm 3211 service
	 */
	public Pm3211Service getPm3211Service() {
		return pm3211Service;
	}

	/**
	 * Sets the pm 3211 service.
	 *
	 * @param pm3211Service the new pm 3211 service
	 */
	public void setPm3211Service(Pm3211Service pm3211Service) {
		this.pm3211Service = pm3211Service;
	}

	/**
	 * Gets the pm 3211 repository.
	 *
	 * @return the pm 3211 repository
	 */
	public Pm3211Repository getPm3211Repository() {
		return pm3211Repository;
	}

	/**
	 * Sets the pm 3211 repository.
	 *
	 * @param pm3211Repository the new pm 3211 repository
	 */
	public void setPm3211Repository(Pm3211Repository pm3211Repository) {
		this.pm3211Repository = pm3211Repository;
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

		pm3211 = pm3211Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (null == pm3211) {
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

		user = this.getUserDetails().getUsername();
		fecha = Calendar.getInstance().getTime();
		anual = conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp();

		pm3211 = pm3211Repository.findAllByIdsectorAndAnual(this.getUserDetails().getIdSector(), anual);
		if (pm3211 != null) {
			fecha = pm3211.getFeccap();
			user = pm3211.getUserid();

		}

		jasperReporteName = "reporte154";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();

		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("pClaveMunic", conctb.getClave());
		parameters.put("pImgMuni", getUserDetails().getPathImgCab1());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL4", firma.getPuesto().getPuesto());
		parameters.put("pN4", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F14.getValue());
		parameters.put("pL5", firma.getPuesto().getPuesto());
		parameters.put("pN5", firma.getNombre());

		parameters.put("pNomMuni", conctb.getNomDep());
		parameters.put("pSector", getUserDetails().getIdSector());

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
		pm3211 = new Pm3211();

	}

	/**
	 * Save.
	 */
	public void save() {
		pm3211 = this.entitySave(pm3211);
		if (bModificar) {
			pm3211Repository.save(pm3211);

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

			boolean bandera = pm3211Service.save(pm3211);

			if (bandera) {
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
		pm3211Service.delete(pm3211);
		pm3211 = pm3211Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (pm3211 == null) {
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
			pm3211 = pm3211Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		} else
			pm3211 = pm3211Service.clean(pm3211);
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
		bBorrar = Boolean.FALSE;
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
		bBorrar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;

		bAdd = Boolean.FALSE;

		pm3211 = pm3211Repository.findAllByIdsector(this.getUserDetails().getIdSector());

		if (null == pm3211) {
			bBorrar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
		}

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param entity the entity
	 * @return the pm 3211
	 */
	public Pm3211 entitySave(Pm3211 entity) {
		pm3211 = new Pm3211();
		pm3211 = entity;
		pm3211.setUserid(this.getUserDetails().getUsername());
		pm3211.setIdRef(0L);
		pm3211.setIdsector(this.getUserDetails().getIdSector());
		pm3211.setFeccap(Calendar.getInstance().getTime());
		pm3211.setCapturo(this.getUserDetails().getUsername());
		pm3211.setAnual(anual);
		return pm3211;
	}

}
