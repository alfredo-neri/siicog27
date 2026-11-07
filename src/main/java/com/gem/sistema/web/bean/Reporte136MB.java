package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm3411;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm3411Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm3411Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte136MB.
 */
@ManagedBean(name = "reporte136MB")
@ViewScoped
public class Reporte136MB extends BaseDirectReport {

	/** The firmas. */
	private Firmas firmas;

	/** The conctb. */
	private Conctb conctb;

	/** The semestre. */
	private String semestre;

	/** The listsemestre. */
	private List<String> listsemestre;

	/** The capturo. */
	private String capturo;

	/** The fecha cap. */
	private Date fechaCap;

	/** The pm 3411. */
	private Pm3411 pm3411;

	/** The list pm 3411. */
	private List<Pm3411> listPm3411;

	/** The con trim. */
	private String conTrim;

	/** The combo tri. */
	private List<Pm3411> comboTri;

	/** The b lblsemestre. */
	private boolean bLblsemestre = Boolean.TRUE;

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

	/** The pm 3411 service. */
	@ManagedProperty("#{pm3411Service}")
	private Pm3411Service pm3411Service;

	/** The pm 3411 repository. */
	@ManagedProperty("#{pm3411Repository}")
	private Pm3411Repository pm3411Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

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
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Sets the semestre.
	 *
	 * @param semestre the new semestre
	 */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	/**
	 * Gets the listsemestre.
	 *
	 * @return the listsemestre
	 */
	public List<String> getListsemestre() {
		return listsemestre;
	}

	/**
	 * Sets the listsemestre.
	 *
	 * @param listsemestre the new listsemestre
	 */
	public void setListsemestre(List<String> listsemestre) {
		this.listsemestre = listsemestre;
	}

	/**
	 * Gets the pm 3411.
	 *
	 * @return the pm 3411
	 */
	public Pm3411 getPm3411() {
		return pm3411;
	}

	/**
	 * Sets the pm 3411.
	 *
	 * @param pm3411 the new pm 3411
	 */
	public void setPm3411(Pm3411 pm3411) {
		this.pm3411 = pm3411;
	}

	/**
	 * Gets the list pm 3411.
	 *
	 * @return the list pm 3411
	 */
	public List<Pm3411> getListPm3411() {
		return listPm3411;
	}

	/**
	 * Sets the list pm 3411.
	 *
	 * @param listPm3411 the new list pm 3411
	 */
	public void setListPm3411(List<Pm3411> listPm3411) {
		this.listPm3411 = listPm3411;
	}

	/**
	 * Checks if is b lblsemestre.
	 *
	 * @return true, if is b lblsemestre
	 */
	public boolean isbLblsemestre() {
		return bLblsemestre;
	}

	/**
	 * Sets the b lblsemestre.
	 *
	 * @param bLblsemestre the new b lblsemestre
	 */
	public void setbLblsemestre(boolean bLblsemestre) {
		this.bLblsemestre = bLblsemestre;
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
	 * Gets the pm 3411 service.
	 *
	 * @return the pm 3411 service
	 */
	public Pm3411Service getPm3411Service() {
		return pm3411Service;
	}

	/**
	 * Sets the pm 3411 service.
	 *
	 * @param pm3411Service the new pm 3411 service
	 */
	public void setPm3411Service(Pm3411Service pm3411Service) {
		this.pm3411Service = pm3411Service;
	}

	/**
	 * Gets the pm 3411 repository.
	 *
	 * @return the pm 3411 repository
	 */
	public Pm3411Repository getPm3411Repository() {
		return pm3411Repository;
	}

	/**
	 * Sets the pm 3411 repository.
	 *
	 * @param pm3411Repository the new pm 3411 repository
	 */
	public void setPm3411Repository(Pm3411Repository pm3411Repository) {
		this.pm3411Repository = pm3411Repository;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the fecha cap.
	 *
	 * @return the fecha cap
	 */
	public Date getFechaCap() {
		return fechaCap;
	}

	/**
	 * Sets the fecha cap.
	 *
	 * @param fechaCap the new fecha cap
	 */
	public void setFechaCap(Date fechaCap) {
		this.fechaCap = fechaCap;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte136";
		endFilename = jasperReporteName + ".pdf";

		bLblsemestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.FALSE;

		capturo = this.getUserDetails().getUsername();
		fechaCap = Calendar.getInstance().getTime();

		listPm3411 = pm3411Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm3411.get(0).getUserid()) {
			bLblsemestre = Boolean.TRUE;
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

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++)
			listsemestre.add("0" + i);
		semestre = listsemestre.get(0);

		comboTri = pm3411Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemestral().toString();
		if (listPm3411.size() == 2) {
			bLblsemestre = Boolean.TRUE;
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

	/**
	 * Gets the months.
	 *
	 * @param semes the semes
	 * @return the months
	 */
	public String getMonths(Integer semes) {
		if (semes == 1) {
			return "01,06";
		} else {
			return "07,12";
		}
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
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		String[] mesArray = this.getMonths(Integer.valueOf(conTrim)).split(",");

		parameters.put("municipioName", firmas.getCampo1());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDay(Integer.valueOf(mesArray[1])));
		parameters.put("mesInicioName", tcMesRepository.findByMes(mesArray[0]).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(mesArray[1]).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", Integer.valueOf(conTrim));
		parameters.put("idSector", getUserDetails().getIdSector());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F30.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());

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
	 * Gets the con trim.
	 *
	 * @return the con trim
	 */
	public String getConTrim() {
		return conTrim;
	}

	/**
	 * Sets the con trim.
	 *
	 * @param conTrim the new con trim
	 */
	public void setConTrim(String conTrim) {
		this.conTrim = conTrim;
	}

	/**
	 * Gets the combo tri.
	 *
	 * @return the combo tri
	 */
	public List<Pm3411> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm3411> comboTri) {
		this.comboTri = comboTri;
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		bLblsemestre = Boolean.FALSE;
		bComboTri = Boolean.TRUE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;
		listPm3411.add(index, pm3411Service.add());
		if (null == listPm3411.get(1).getUserid())
			listPm3411.remove(1);

	}

	/** The acumulado. */
	Integer acumulado = 0;

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm3411 = this.entitySave(index);

		pm3411.setSpcam(null == pm3411.getSpcam() ? 0 : pm3411.getSpcam());
		pm3411.setTspaem(null == pm3411.getTspaem() ? 0 : pm3411.getTspaem());
		if (bModificar) {

			pm3411Repository.save(pm3411);
			this.pm3411Service.getAcumulado(this.getUserDetails().getIdSector());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modificaron los datos correctamente");

			bLblsemestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.FALSE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bAdd = Boolean.FALSE;
		} else {
			listPm3411.set(index, pm3411);
			listPm3411 = pm3411Service.save(index, listPm3411);

			if (listPm3411.get(index).isbGuardar()) {
				bLblsemestre = Boolean.TRUE;
				bComboTri = Boolean.FALSE;
				bLbl = Boolean.TRUE;
				bTxt = Boolean.FALSE;
				bVSave = Boolean.FALSE;
				bVModificar = Boolean.TRUE;
				bBtnMoficar = Boolean.FALSE;
				bModificar = Boolean.FALSE;
				bBorrar = Boolean.FALSE;
				bAdd = Boolean.FALSE;
			}
		}

		if (listPm3411.size() == 2 && listPm3411.get(index).isbGuardar()) {
			bLblsemestre = Boolean.TRUE;
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
		comboTri = pm3411Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm3411 = pm3411Service.delete(index, listPm3411);

		comboTri = pm3411Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(comboTri)) {
			bBorrar = Boolean.TRUE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			listPm3411.add(new Pm3411());
		}
		if (comboTri.size() > 0) {
			bLblsemestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.FALSE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bAdd = Boolean.FALSE;
		}
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm3411 = pm3411Repository.findAllBysemestralAndIdsector(listPm3411.get(index).getSemestral(),
					this.getUserDetails().getIdSector());
			listPm3411.get(index).setSpcam(pm3411.getSpcam());
			listPm3411.get(index).setObsspcam(pm3411.getObsspcam());
			listPm3411.get(index).setTspaem(pm3411.getTspaem());
			listPm3411.get(index).setObstspaem(pm3411.getObstspaem());
		} else
			listPm3411 = pm3411Service.clean(index, listPm3411);
	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {

		bLblsemestre = Boolean.TRUE;
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
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {
		bLblsemestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bVModificar = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		listPm3411 = pm3411Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm3411)) {

			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}
		if (listPm3411.size() == 2) {
			bLblsemestre = Boolean.TRUE;
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
		bModificar = Boolean.FALSE;

		bAdd = Boolean.FALSE;
		listPm3411 = pm3411Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 3411
	 */
	public Pm3411 entitySave(Integer index) {
		pm3411 = listPm3411.get(index);
		pm3411.setUserid(this.getUserDetails().getUsername());
		pm3411.setIdRef(0L);
		pm3411.setIdsector(this.getUserDetails().getIdSector());
		pm3411.setFeccap(Calendar.getInstance().getTime());
		pm3411.setCapturo(this.getUserDetails().getUsername());
		pm3411.setSemestral(bModificar == true ? pm3411.getSemestral() : Integer.valueOf(semestre));
		return pm3411;
	}

}
