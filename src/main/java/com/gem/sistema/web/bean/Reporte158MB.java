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
import com.gem.sistema.business.domain.Pm0111;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm0111Repository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm0111Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte158MB.
 */
@ManagedBean(name = "reporte158MB")
@ViewScoped
public class Reporte158MB extends BaseDirectReport {

	/** The conctb. */
	private Conctb conctb;

	/** The anual. */
	private Integer anual;

	/** The pm 0111. */
	private Pm0111 pm0111;

	/** The list pm 0111. */
	private List<Pm0111> listPm0111;

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

	/** The pm 0111 service. */
	@ManagedProperty("#{pm0111Service}")
	private Pm0111Service pm0111Service;

	/** The pm 0111 repository. */
	@ManagedProperty("#{pm0111Repository}")
	private Pm0111Repository pm0111Repository;

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
	 * Gets the pm 0111.
	 *
	 * @return the pm 0111
	 */
	public Pm0111 getPm0111() {
		return pm0111;
	}

	/**
	 * Sets the pm 0111.
	 *
	 * @param pm0111 the new pm 0111
	 */
	public void setPm0111(Pm0111 pm0111) {
		this.pm0111 = pm0111;
	}

	/**
	 * Gets the list pm 0111.
	 *
	 * @return the list pm 0111
	 */
	public List<Pm0111> getListPm0111() {
		return listPm0111;
	}

	/**
	 * Sets the list pm 0111.
	 *
	 * @param listPm0111 the new list pm 0111
	 */
	public void setListPm0111(List<Pm0111> listPm0111) {
		this.listPm0111 = listPm0111;
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
	 * Gets the pm 0111 service.
	 *
	 * @return the pm 0111 service
	 */
	public Pm0111Service getPm0111Service() {
		return pm0111Service;
	}

	/**
	 * Sets the pm 0111 service.
	 *
	 * @param pm0111Service the new pm 0111 service
	 */
	public void setPm0111Service(Pm0111Service pm0111Service) {
		this.pm0111Service = pm0111Service;
	}

	/**
	 * Gets the pm 0111 repository.
	 *
	 * @return the pm 0111 repository
	 */
	public Pm0111Repository getPm0111Repository() {
		return pm0111Repository;
	}

	/**
	 * Sets the pm 0111 repository.
	 *
	 * @param pm0111Repository the new pm 0111 repository
	 */
	public void setPm0111Repository(Pm0111Repository pm0111Repository) {
		this.pm0111Repository = pm0111Repository;
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

		pm0111 = pm0111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (null == pm0111) {
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

		jasperReporteName = "Reporte158";
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

		parameters.put("pNomMun", conctb.getNomDep());
		parameters.put("pClave", conctb.getClave());
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("pImg", getUserDetails().getPathImgCab1());
		parameters.put("pIdSector", getUserDetails().getIdSector());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());

		parameters.put("pNoFirmas", 3);

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
	 * Calcula sumas.
	 */
	public void calculaSumas() {
		pm0111.setTotjuga(this.sumaCampos1());

		pm0111.setTotjucon(this.sumaCampos2());
	}

	/** The sum. */
	Integer sum = 0;

	/**
	 * Suma campos 1.
	 *
	 * @return the integer
	 */
	public Integer sumaCampos1() {
		sum = 0;

		if (pm0111.getNjml() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getNjml();
		}
		if (pm0111.getNjmc() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getNjmc();
		}
		if (pm0111.getNjma() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getNjma();
		}
		if (pm0111.getNjmd() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getNjmd();
		}
		return sum;
	}

	/**
	 * Suma campos 2.
	 *
	 * @return the integer
	 */
	public Integer sumaCampos2() {
		sum = 0;

		if (pm0111.getTjil() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getTjil();
		}
		if (pm0111.getTjic() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getTjic();
		}
		if (pm0111.getTjia() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getTjia();
		}
		if (pm0111.getTjid() == null) {
			sum = sum + 0;
		} else {
			sum = sum + pm0111.getTjid();
		}
		return sum;
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
		pm0111 = new Pm0111();

	}

	/**
	 * Save.
	 */
	public void save() {
		pm0111 = this.entitySave(pm0111);
		if (bModificar) {
			pm0111Repository.save(pm0111);

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

			boolean bandera = pm0111Service.save(pm0111);

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
		pm0111Service.delete(pm0111);
		pm0111 = pm0111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (pm0111 == null) {
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
			pm0111 = pm0111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		} else
			pm0111 = pm0111Service.clean(pm0111);
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
		bAdd = Boolean.FALSE;
		pm0111 = pm0111Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (null != pm0111) {
			bBorrar = Boolean.FALSE;
			bBtnMoficar = Boolean.FALSE;
			bAdd = Boolean.TRUE;

		} else {
			bBorrar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
		}

		bModificar = Boolean.FALSE;

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param entity the entity
	 * @return the pm 0111
	 */
	public Pm0111 entitySave(Pm0111 entity) {
		pm0111 = new Pm0111();
		pm0111 = entity;
		pm0111.setUserid(this.getUserDetails().getUsername());
		pm0111.setIdRef(0L);
		pm0111.setIdsector(this.getUserDetails().getIdSector());
		pm0111.setFeccap(Calendar.getInstance().getTime());
		pm0111.setCapturo(this.getUserDetails().getUsername());
		pm0111.setAnual(anual);
		return pm0111;
	}

}
