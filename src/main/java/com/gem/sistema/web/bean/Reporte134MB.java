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
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm2311;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm2311Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm2311Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte134MB.
 */
@ManagedBean(name = "reporte134MB")
@ViewScoped
public class Reporte134MB extends BaseDirectReport {

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

	/** The pm 2311. */
	private Pm2311 pm2311;
	
	/** The list pm 2311. */
	private List<Pm2311> listPm2311;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm2311> comboTri;

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

	/** The pm 2311 service. */
	@ManagedProperty("#{pm2311Service}")
	private Pm2311Service pm2311Service;

	/** The pm 2311 repository. */
	@ManagedProperty("#{pm2311Repository}")
	private Pm2311Repository pm2311Repository;

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
	 * Gets the pm 2311.
	 *
	 * @return the pm 2311
	 */
	public Pm2311 getPm2311() {
		return pm2311;
	}

	/**
	 * Sets the pm 2311.
	 *
	 * @param pm2311 the new pm 2311
	 */
	public void setPm2311(Pm2311 pm2311) {
		this.pm2311 = pm2311;
	}

	/**
	 * Gets the list pm 2311.
	 *
	 * @return the list pm 2311
	 */
	public List<Pm2311> getListPm2311() {
		return listPm2311;
	}

	/**
	 * Sets the list pm 2311.
	 *
	 * @param listPm2311 the new list pm 2311
	 */
	public void setListPm2311(List<Pm2311> listPm2311) {
		this.listPm2311 = listPm2311;
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
	 * Gets the pm 2311 service.
	 *
	 * @return the pm 2311 service
	 */
	public Pm2311Service getPm2311Service() {
		return pm2311Service;
	}

	/**
	 * Sets the pm 2311 service.
	 *
	 * @param pm2311Service the new pm 2311 service
	 */
	public void setPm2311Service(Pm2311Service pm2311Service) {
		this.pm2311Service = pm2311Service;
	}

	/**
	 * Gets the pm 2311 repository.
	 *
	 * @return the pm 2311 repository
	 */
	public Pm2311Repository getPm2311Repository() {
		return pm2311Repository;
	}

	/**
	 * Sets the pm 2311 repository.
	 *
	 * @param pm2311Repository the new pm 2311 repository
	 */
	public void setPm2311Repository(Pm2311Repository pm2311Repository) {
		this.pm2311Repository = pm2311Repository;
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
		jasperReporteName = "reporte134";
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

		listPm2311 = pm2311Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm2311.get(0).getUserid()) {
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

		comboTri = pm2311Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemestral().toString();

		if (listPm2311.size() == 2) {
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

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TrPuestoFirma firma = null;
		Integer sector = this.getUserDetails().getIdSector();
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
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
	    parameters.put("firmaN1", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F11.getValue());
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
	public List<Pm2311> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm2311> comboTri) {
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
		listPm2311.add(index, pm2311Service.add());
		if (null == listPm2311.get(1).getUserid())
			listPm2311.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm2311 = this.entitySave(index);
		if (bModificar) {
			
			if (pm2311.getTvm() == null)
				pm2311.setTvm(0);
			if (pm2311.getNvap() == null)
				pm2311.setNvap(0);
			if (pm2311.getNvcp() == null)
				pm2311.setNvcp(0);
			if (pm2311.getNvdre() == null)
				pm2311.setNvdre(0);
			if (pm2311.getNvrb() == null)
				pm2311.setNvrb(0);
			if (pm2311.getObsgral() == null)
				pm2311.setObsgral("");
			
			pm2311Repository.save(pm2311);
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
			listPm2311.set(index, pm2311);
			if (listPm2311.get(index).getTvm() == null)
				listPm2311.get(index).setTvm(0);
			if (listPm2311.get(index).getNvap() == null)
				listPm2311.get(index).setNvap(0);
			if (listPm2311.get(index).getNvcp() == null)
				listPm2311.get(index).setNvcp(0);
			if (listPm2311.get(index).getNvdre() == null)
				listPm2311.get(index).setNvdre(0);
			if (listPm2311.get(index).getNvrb() == null)
				listPm2311.get(index).setNvrb(0);
			if (listPm2311.get(index).getObsgral() == null)
				listPm2311.get(index).setObsgral("");
			listPm2311 = pm2311Service.save(index, listPm2311);
			if (listPm2311.get(index).isbGuardar()) {
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
		comboTri = pm2311Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (listPm2311.size() == 2) {
			bAdd = Boolean.TRUE;
		}

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm2311 = pm2311Service.delete(index, listPm2311);
		comboTri = pm2311Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		bAdd = Boolean.FALSE;
		if (listPm2311.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		if (CollectionUtils.isEmpty(
				pm2311Repository.findAllByIdsector(this.getUserDetails().getIdSector(), this.orderBySemestre()))) {
			bBorrar = Boolean.TRUE;
			bVModificar = Boolean.FALSE;

		}
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm2311 = pm2311Repository.findAllBysemestralAndIdsector(listPm2311.get(index).getSemestral(),
					this.getUserDetails().getIdSector());
			listPm2311.get(index).setTvm(pm2311.getTvm());
			listPm2311.get(index).setObsgral(pm2311.getObsgral());
			listPm2311.get(index).setNvap(pm2311.getNvap());
			listPm2311.get(index).setNvcp(pm2311.getNvcp());
			listPm2311.get(index).setNvdre(pm2311.getNvdre());
			listPm2311.get(index).setNvrb(pm2311.getNvrb());
		} else
			listPm2311 = pm2311Service.clean(index, listPm2311);
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
		bVModificar = Boolean.TRUE;
		bAdd = Boolean.FALSE;
		listPm2311 = pm2311Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm2311)) {
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}
		if (listPm2311.size() == 2) {
			bAdd = Boolean.TRUE;
		}

		bModificar = Boolean.FALSE;
		listPm2311 = pm2311Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 2311
	 */
	public Pm2311 entitySave(Integer index) {
		pm2311 = listPm2311.get(index);
		pm2311.setUserid(this.getUserDetails().getUsername());
		pm2311.setIdRef(0L);
		pm2311.setIdsector(this.getUserDetails().getIdSector());
		pm2311.setFeccap(Calendar.getInstance().getTime());
		pm2311.setCapturo(this.getUserDetails().getUsername());
		pm2311.setSemestral(bModificar == true ? pm2311.getSemestral() : Integer.valueOf(semestre));
		return pm2311;
	}

	/**
	 * Order by semestre.
	 *
	 * @return the sort
	 */
	public Sort orderBySemestre() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}
	
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
