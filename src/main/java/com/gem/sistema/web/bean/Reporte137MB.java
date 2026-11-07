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
import com.gem.sistema.business.domain.Pm3911;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm3911Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm3911Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte137MB.
 */
@ManagedBean(name = "reporte137MB")
@ViewScoped
public class Reporte137MB extends BaseDirectReport {
	
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

	/** The pm 3911. */
	private Pm3911 pm3911;
	
	/** The list pm 3911. */
	private List<Pm3911> listPm3911;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm3911> comboTri;

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

	/** The pm 3911 service. */
	@ManagedProperty("#{pm3911Service}")
	private Pm3911Service pm3911Service;

	/** The pm 3911 repository. */
	@ManagedProperty("#{pm3911Repository}")
	private Pm3911Repository pm3911Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
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
	 * Gets the pm 3911.
	 *
	 * @return the pm 3911
	 */
	public Pm3911 getPm3911() {
		return pm3911;
	}

	/**
	 * Sets the pm 3911.
	 *
	 * @param pm3911 the new pm 3911
	 */
	public void setPm3911(Pm3911 pm3911) {
		this.pm3911 = pm3911;
	}

	/**
	 * Gets the list pm 3911.
	 *
	 * @return the list pm 3911
	 */
	public List<Pm3911> getListPm3911() {
		return listPm3911;
	}

	/**
	 * Sets the list pm 3911.
	 *
	 * @param listPm3911 the new list pm 3911
	 */
	public void setListPm3911(List<Pm3911> listPm3911) {
		this.listPm3911 = listPm3911;
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
	 * Gets the pm 3911 service.
	 *
	 * @return the pm 3911 service
	 */
	public Pm3911Service getPm3911Service() {
		return pm3911Service;
	}

	/**
	 * Sets the pm 3911 service.
	 *
	 * @param pm3911Service the new pm 3911 service
	 */
	public void setPm3911Service(Pm3911Service pm3911Service) {
		this.pm3911Service = pm3911Service;
	}

	/**
	 * Gets the pm 3911 repository.
	 *
	 * @return the pm 3911 repository
	 */
	public Pm3911Repository getPm3911Repository() {
		return pm3911Repository;
	}

	/**
	 * Sets the pm 3911 repository.
	 *
	 * @param pm3911Repository the new pm 3911 repository
	 */
	public void setPm3911Repository(Pm3911Repository pm3911Repository) {
		this.pm3911Repository = pm3911Repository;
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
		jasperReporteName = "reporte137";
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

		capturo = this.getUserDetails().getUsername();
		fechaCap = Calendar.getInstance().getTime();

		listPm3911 = pm3911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm3911.get(0).getUserid()) {
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
		if (listPm3911.size() == 2)
			bAdd = Boolean.TRUE;

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++)
			listsemestre.add("0" + i);
		semestre = listsemestre.get(0);

		comboTri = pm3911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemes().toString();

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

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		String[] mesArray = this.getMonths(Integer.valueOf(conTrim)).split(",");

		parameters.put("municipioName", this.getUserDetails().getMunicipio());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDay(Integer.valueOf(mesArray[1])));
		parameters.put("mesInicioName", tcMesRepository.findByMes(mesArray[0]).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(mesArray[1]).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", Integer.valueOf(conTrim));
		parameters.put("idSector", getUserDetails().getIdSector());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("pL4", firma.getPuesto().getPuesto());
	    parameters.put("pN4", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
	    parameters.put("pL5", firma.getPuesto().getPuesto());
	    parameters.put("pN5", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F24.getValue());
	    parameters.put("pL3", firma.getPuesto().getPuesto());
	    parameters.put("pN3", firma.getNombre());
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
	public List<Pm3911> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm3911> comboTri) {
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
		listPm3911.add(index, pm3911Service.add());
		if (null == listPm3911.get(1).getUserid())
			listPm3911.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm3911 = this.entitySave(index);
		if (bModificar) {
//			if (pm3911.getManorgpro() == null)
//				pm3911.setManorgpro("0");
//			if (pm3911.getOrgpro() == null)
//				pm3911.setOrgpro("0");
//			if (pm3911.getRegpro() == null)
//				pm3911.setRegpro("0");
//			if (pm3911.getManpropro() == null)
//				pm3911.setManpropro("0");

			pm3911Repository.save(pm3911);
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
			if (pm3911Repository.count(this.getUserDetails().getIdSector()) == 2)
				bAdd = Boolean.TRUE;

		} else {
//			if (pm3911.getManorgpro() == null)
//				pm3911.setManorgpro("0");
//			if (pm3911.getOrgpro() == null)
//				pm3911.setOrgpro("0");
//			if (pm3911.getRegpro() == null)
//				pm3911.setRegpro("0");
//			if (pm3911.getManpropro() == null)
//				pm3911.setManpropro("0");
			listPm3911.set(index, pm3911);
			listPm3911 = pm3911Service.save(index, listPm3911);

			if (listPm3911.get(index).isbGuardar()) {
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
				if (listPm3911.size() == 2)
					bAdd = Boolean.TRUE;
			}

		}

		comboTri = pm3911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm3911 = pm3911Service.delete(index, listPm3911);
		comboTri = pm3911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		bAdd = Boolean.FALSE;
		if (CollectionUtils
				.isEmpty(pm3911Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector()))) {
			bBorrar = Boolean.TRUE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
		}
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm3911 = pm3911Repository.findAllBysemesAndIdsector(listPm3911.get(index).getSemes(),
					this.getUserDetails().getIdSector());
			listPm3911.set(index, pm3911);
		} else
			listPm3911 = pm3911Service.clean(index, listPm3911);
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
		listPm3911 = pm3911Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm3911)) {

			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}

		bModificar = Boolean.FALSE;

		bAdd = Boolean.FALSE;
		listPm3911 = pm3911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 3911
	 */
	public Pm3911 entitySave(Integer index) {
		pm3911 = listPm3911.get(index);

		pm3911.setUserid(this.getUserDetails().getUsername());
		pm3911.setIdRef(0L);
		pm3911.setIdsector(this.getUserDetails().getIdSector());
		pm3911.setFeccap(Calendar.getInstance().getTime());
		pm3911.setCapturo(this.getUserDetails().getUsername());
		pm3911.setSemes(bModificar == true ? pm3911.getSemes() : Integer.valueOf(semestre));
		return pm3911;
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Default values.
	 *
	 * @param entity the entity
	 * @return the pm 3911
	 */
//	public Pm3911 defaultValues(Pm3911 entity) {
//		entity.setManorgsi("X");
//		entity.setOrgsi("X");
//		entity.setRegsi("X");
//		entity.setManprosi("X");
//
//		return entity;
//	}

}
