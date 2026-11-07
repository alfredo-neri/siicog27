package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm1511;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm1511Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm1511Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte133MB.
 */
@ManagedBean(name = "reporte133MB")
@ViewScoped
public class Reporte133MB extends BaseDirectReport {

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

	/** The pm 1511. */
	private Pm1511 pm1511;

	/** The list pm 1511. */
	private List<Pm1511> listPm1511;

	/** The con trim. */
	private String conTrim;

	/** The combo tri. */
	private List<Pm1511> comboTri;

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

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The pm 1511 service. */
	@ManagedProperty("#{pm1511Service}")
	private Pm1511Service pm1511Service;

	/** The pm 1511 repository. */
	@ManagedProperty("#{pm1511Repository}")
	private Pm1511Repository pm1511Repository;

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
	 * Gets the pm 1511.
	 *
	 * @return the pm 1511
	 */
	public Pm1511 getPm1511() {
		return pm1511;
	}

	/**
	 * Sets the pm 1511.
	 *
	 * @param pm1511 the new pm 1511
	 */
	public void setPm1511(Pm1511 pm1511) {
		this.pm1511 = pm1511;
	}

	/**
	 * Gets the list pm 1511.
	 *
	 * @return the list pm 1511
	 */
	public List<Pm1511> getListPm1511() {
		return listPm1511;
	}

	/**
	 * Sets the list pm 1511.
	 *
	 * @param listPm1511 the new list pm 1511
	 */
	public void setListPm1511(List<Pm1511> listPm1511) {
		this.listPm1511 = listPm1511;
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
	 * Gets the pm 1511 service.
	 *
	 * @return the pm 1511 service
	 */
	public Pm1511Service getPm1511Service() {
		return pm1511Service;
	}

	/**
	 * Sets the pm 1511 service.
	 *
	 * @param pm1511Service the new pm 1511 service
	 */
	public void setPm1511Service(Pm1511Service pm1511Service) {
		this.pm1511Service = pm1511Service;
	}

	/**
	 * Gets the pm 1511 repository.
	 *
	 * @return the pm 1511 repository
	 */
	public Pm1511Repository getPm1511Repository() {
		return pm1511Repository;
	}

	/**
	 * Sets the pm 1511 repository.
	 *
	 * @param pm1511Repository the new pm 1511 repository
	 */
	public void setPm1511Repository(Pm1511Repository pm1511Repository) {
		this.pm1511Repository = pm1511Repository;
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

		jasperReporteName = "reporte133";
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

		listPm1511 = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm1511.get(0).getUserid()) {
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

			listPm1511.get(0).setCapturo(this.getUserDetails().getUsername());
			listPm1511.get(0).setFeccap(Calendar.getInstance().getTime());

		}

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++)
			listsemestre.add("0" + i);
		semestre = listsemestre.get(0);

		comboTri = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemestral().toString();

		if (listPm1511.size() == 2) {
			bAdd = Boolean.TRUE;
		}

	}

	/**
	 * Gets the mes inicial.
	 *
	 * @param semes the semes
	 * @return the mes inicial
	 */
	public String getMesInicial(Integer semes) {
		if (semes == 1) {
			return "01";
		} else {
			return "07";
		}

	}

	/**
	 * Gets the mes final.
	 *
	 * @param semes the semes
	 * @return the mes final
	 */
	public String getMesFinal(Integer semes) {
		if (semes == 1) {
			return "06";
		} else {
			return "12";
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

		parameters.put("municipioName", firmas.getCampo1());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDay(Integer.valueOf(getMesFinal(Integer.valueOf(conTrim)))));
		parameters.put("mesInicioName",
				tcMesRepository.findByMes(getMesInicial(Integer.valueOf(conTrim))).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(getMesFinal(Integer.valueOf(conTrim))).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", Integer.valueOf(conTrim));
		parameters.put("idSector", getUserDetails().getIdSector());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F22.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
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
	public List<Pm1511> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm1511> comboTri) {
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
		listPm1511.add(index, pm1511Service.add());
		listPm1511.get(index).setCapturo(this.getUserDetails().getUsername());
		listPm1511.get(index).setFeccap(Calendar.getInstance().getTime());
		if (null == listPm1511.get(1).getUserid())
			listPm1511.remove(1);

	}

	/** The sum acumcs. */
	Integer sumAcumcs;

	/** The sum acumc. */
	Integer sumAcumc;

	/** The sum acucpd. */
	Integer sumAcucpd;

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm1511 = this.entitySave(index);
		sumAcumcs = 0;
		sumAcumc = 0;
		sumAcucpd = 0;
		if (bModificar) {
			pm1511.setAcunccm(0);
			pm1511.setAcunccms(0);
			pm1511.setAcutcpd(0);

			pm1511Repository.save(pm1511);

			this.calcularAcumulados();

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
			listPm1511.set(index, pm1511);
			listPm1511 = pm1511Service.save(index, listPm1511);
			if (listPm1511.get(index).isbGuardar()) {
				this.calcularAcumulados();
				// this.calcularAcumulado(pm1511);
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
		if (listPm1511.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		comboTri = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		pm1511 = listPm1511.get(index);

		listPm1511 = pm1511Service.delete(index, listPm1511);
		// pm1511 = listPm1511.get(index);
//		if (null != pm1511.getCapturo())
//			pm1511Repository.save(pm1511);

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
		listPm1511 = pm1511Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm1511)) {

			bLblsemestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
			listPm1511 = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
			listPm1511.get(0).setCapturo(this.getUserDetails().getUsername());
			listPm1511.get(0).setFeccap(Calendar.getInstance().getTime());

		}
		comboTri = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm1511 = pm1511Repository.findAllBysemestralAndIdsector(listPm1511.get(index).getSemestral(),
					this.getUserDetails().getIdSector());
			listPm1511.set(index, pm1511);

		} else
			listPm1511 = pm1511Service.clean(index, listPm1511);
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
		bBorrar = Boolean.FALSE;
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
		bVModificar = Boolean.TRUE;
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.FALSE;

		listPm1511 = pm1511Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm1511)) {
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			listPm1511.get(0).setCapturo(this.getUserDetails().getUsername());
			listPm1511.get(0).setFeccap(Calendar.getInstance().getTime());

		}

		if (listPm1511.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		bModificar = Boolean.FALSE;

		listPm1511 = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (listPm1511.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 1511
	 */
	public Pm1511 entitySave(Integer index) {
		pm1511 = listPm1511.get(index);
		pm1511.setUserid(this.getUserDetails().getUsername());
		pm1511.setIdRef(0L);
		pm1511.setIdsector(this.getUserDetails().getIdSector());
		pm1511.setFeccap(Calendar.getInstance().getTime());
		pm1511.setCapturo(this.getUserDetails().getUsername());
		pm1511.setSemestral(bModificar == true ? pm1511.getSemestral() : Integer.valueOf(semestre));
		return pm1511;
	}

	/**
	 * Calcular acumulado.
	 *
	 * @param entity the entity
	 */
	public void calcularAcumulado(Pm1511 entity) {
		Integer contar = pm1511Repository.count(this.getUserDetails().getIdSector());

		if (contar.intValue() < 2) {
			entity.setAcunccm(0);
			entity.setAcunccms(0);
			entity.setAcutcpd(0);
		} else {
			entity.setAcunccm(pm1511Repository.sumAcunccm(this.getUserDetails().getIdSector(), entity.getSemestral()));
			entity.setAcutcpd(pm1511Repository.sumAcutcpd(this.getUserDetails().getIdSector(), entity.getSemestral()));
			entity.setAcunccms(pm1511Repository.sumAcutcpd(this.getUserDetails().getIdSector(), entity.getSemestral()));
		}
		pm1511Repository.save(entity);
	}

	/**
	 * Calcular acumulados.
	 */
	public void calcularAcumulados() {
		listPm1511 = pm1511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		for (int i = 0; i < listPm1511.size(); i++) {
			sumAcumcs = sumAcumcs + listPm1511.get(i).getNccms();
			sumAcumc = sumAcumc + listPm1511.get(i).getNccm();
			sumAcucpd = sumAcucpd + listPm1511.get(i).getTcpd();
			listPm1511.get(i).setAcunccms(sumAcumcs);
			listPm1511.get(i).setAcunccm(sumAcumc);
			listPm1511.get(i).setAcutcpd(sumAcucpd);
			Pm1511 saveEntity = listPm1511.get(i);
			pm1511Repository.save(saveEntity);
		}

	}

}
