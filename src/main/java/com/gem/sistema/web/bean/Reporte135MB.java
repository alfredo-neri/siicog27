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
import com.gem.sistema.business.domain.Pm2711;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm2711Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm2711Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte135MB.
 */
@ManagedBean(name  = "reporte135MB")
@ViewScoped
public class Reporte135MB extends BaseDirectReport {
	
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

	/** The pm 2711. */
	private Pm2711 pm2711;
	
	/** The list pm 2711. */
	private List<Pm2711> listPm2711;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm2711> comboTri;

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

	/** The pm 2711 service. */
	@ManagedProperty("#{pm2711Service}")
	private Pm2711Service pm2711Service;

	/** The pm 2711 repository. */
	@ManagedProperty("#{pm2711Repository}")
	private Pm2711Repository pm2711Repository;

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
	 * Gets the pm 2711.
	 *
	 * @return the pm 2711
	 */
	public Pm2711 getPm2711() {
		return pm2711;
	}

	/**
	 * Sets the pm 2711.
	 *
	 * @param pm2711 the new pm 2711
	 */
	public void setPm2711(Pm2711 pm2711) {
		this.pm2711 = pm2711;
	}

	/**
	 * Gets the list pm 2711.
	 *
	 * @return the list pm 2711
	 */
	public List<Pm2711> getListPm2711() {
		return listPm2711;
	}

	/**
	 * Sets the list pm 2711.
	 *
	 * @param listPm2711 the new list pm 2711
	 */
	public void setListPm2711(List<Pm2711> listPm2711) {
		this.listPm2711 = listPm2711;
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
	 * Gets the pm 2711 service.
	 *
	 * @return the pm 2711 service
	 */
	public Pm2711Service getPm2711Service() {
		return pm2711Service;
	}

	/**
	 * Sets the pm 2711 service.
	 *
	 * @param pm2711Service the new pm 2711 service
	 */
	public void setPm2711Service(Pm2711Service pm2711Service) {
		this.pm2711Service = pm2711Service;
	}

	

	/**
	 * Gets the pm 2711 repository.
	 *
	 * @return the pm 2711 repository
	 */
	public Pm2711Repository getPm2711Repository() {
		return pm2711Repository;
	}

	/**
	 * Sets the pm 2711 repository.
	 *
	 * @param pm2711Repository the new pm 2711 repository
	 */
	public void setPm2711Repository(Pm2711Repository pm2711Repository) {
		this.pm2711Repository = pm2711Repository;
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
		jasperReporteName = "reporte135";
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

		listPm2711 = pm2711Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm2711.get(0).getUserid()) {
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

		comboTri = pm2711Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemestral().toString();
		
		if(listPm2711.size() == 2) {
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
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
	    parameters.put("firmaN1", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F30.getValue());
	    parameters.put("firmaP2", firma.getPuesto().getPuesto());
	    parameters.put("firmaN2", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F14.getValue());
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
	public List<Pm2711> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm2711> comboTri) {
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
		listPm2711.add(index, pm2711Service.add());
		if (null == listPm2711.get(1).getUserid())
			listPm2711.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm2711 = this.entitySave(index);
		if (bModificar) {
			if(pm2711.getLap() == null)
				pm2711.setLap(0);
			if(pm2711.getTloc() == null)
				pm2711.setTloc(0);
			if(pm2711.getObslap() == null)
				pm2711.setObslap("");
			if(pm2711.getObstloc() == null)
				pm2711.setObstloc("");	
			pm2711Repository.save(pm2711);
			this.pm2711Service.acumulado(this.getUserDetails().getIdSector());
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
			if(pm2711.getLap() == null)
				pm2711.setLap(0);
			if(pm2711.getTloc() == null)
				pm2711.setTloc(0);
			if(pm2711.getObslap() == null)
				pm2711.setObslap("");
			if(pm2711.getObstloc() == null)
				pm2711.setObstloc("");
			listPm2711.add(index, pm2711);
			listPm2711 = pm2711Service.save(index, listPm2711);
			if(listPm2711.get(index).isbGuardar()) {
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
		comboTri = pm2711Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if(listPm2711.size() == 2) {
			bAdd = Boolean.TRUE;
		}

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm2711 = pm2711Service.delete(index, listPm2711);
		comboTri = pm2711Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		bAdd = Boolean.FALSE;
		if(CollectionUtils.isEmpty(listPm2711)){
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
			pm2711 = pm2711Repository.findAllBysemestralAndIdsector(listPm2711.get(index).getSemestral(),
					this.getUserDetails().getIdSector());
			listPm2711.get(index).setLap(pm2711.getLap());
			listPm2711.get(index).setObslap(pm2711.getObslap());
			listPm2711.get(index).setTloc(pm2711.getTloc());
			listPm2711.get(index).setObstloc(pm2711.getObstloc());
		} else
			listPm2711 = pm2711Service.clean(index, listPm2711);
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
		bVModificar = Boolean.TRUE;
		listPm2711 = pm2711Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm2711)) {
			
			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		} 
		
		if(listPm2711.size() == 2) {
			bAdd = Boolean.TRUE;
		}
			

		
		bModificar = Boolean.FALSE;
		
		
		listPm2711 = pm2711Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 2711
	 */
	public Pm2711 entitySave(Integer index) {
		pm2711 = listPm2711.get(index);
		pm2711.setUserid(this.getUserDetails().getUsername());
		pm2711.setIdRef(0L);
		pm2711.setIdsector(this.getUserDetails().getIdSector());
		pm2711.setFeccap(Calendar.getInstance().getTime());
		pm2711.setCapturo(this.getUserDetails().getUsername());
		pm2711.setSemestral(bModificar == true ? pm2711.getSemestral() : Integer.valueOf(semestre));
		return pm2711;
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
