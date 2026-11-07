package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm0411;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm0411Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm0411Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte112MB.
 */
@ManagedBean(name = "reporte112MB")
@ViewScoped
public class Reporte112MB extends BaseDirectReport {

	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The trimestre. */
	private String trimestre;
	
	/** The list trimestre. */
	private List<String> listTrimestre;

	/** The pm 0411. */
	private Pm0411 pm0411;
	
	/** The list pm 0411. */
	private List<Pm0411> listPm0411;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm0411> comboTri;

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

	/** The b report. */
	private boolean bReport = Boolean.FALSE;
	
	/** The pm 0411 service. */
	@ManagedProperty("#{pm0411Service}")
	private Pm0411Service pm0411Service;

	/** The pm 0411 repository. */
	@ManagedProperty("#{pm0411Repository}")
	private Pm0411Repository pm0411Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/**
	 * Checks if is b report.
	 *
	 * @return true, if is b report
	 */
	public boolean isbReport() {
		return bReport;
	}

	/**
	 * Sets the b report.
	 *
	 * @param bReport the new b report
	 */
	public void setbReport(boolean bReport) {
		this.bReport = bReport;
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
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public String getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the list trimestre.
	 *
	 * @return the list trimestre
	 */
	public List<String> getListTrimestre() {
		return listTrimestre;
	}

	/**
	 * Sets the list trimestre.
	 *
	 * @param listTrimestre the new list trimestre
	 */
	public void setListTrimestre(List<String> listTrimestre) {
		this.listTrimestre = listTrimestre;
	}

	/**
	 * Gets the pm 0411.
	 *
	 * @return the pm 0411
	 */
	public Pm0411 getPm0411() {
		return pm0411;
	}

	/**
	 * Sets the pm 0411.
	 *
	 * @param pm0411 the new pm 0411
	 */
	public void setPm0411(Pm0411 pm0411) {
		this.pm0411 = pm0411;
	}

	/**
	 * Gets the list pm 0411.
	 *
	 * @return the list pm 0411
	 */
	public List<Pm0411> getlistPm0411() {
		return listPm0411;
	}

	/**
	 * Sets the list pm 0411.
	 *
	 * @param listPm0411 the new list pm 0411
	 */
	public void setlistPm0411(List<Pm0411> listPm0411) {
		this.listPm0411 = listPm0411;
	}

	/**
	 * Gets the pm 0411 service.
	 *
	 * @return the pm 0411 service
	 */
	public Pm0411Service getPm0411Service() {
		return pm0411Service;
	}

	/**
	 * Sets the pm 0411 service.
	 *
	 * @param pm0411Service the new pm 0411 service
	 */
	public void setPm0411Service(Pm0411Service pm0411Service) {
		this.pm0411Service = pm0411Service;
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
	 * Gets the pm 0411 repository.
	 *
	 * @return the pm 0411 repository
	 */
	public Pm0411Repository getPm0411Repository() {
		return pm0411Repository;
	}

	/**
	 * Sets the pm 0411 repository.
	 *
	 * @param pm0411Repository the new pm 0411 repository
	 */
	public void setPm0411Repository(Pm0411Repository pm0411Repository) {
		this.pm0411Repository = pm0411Repository;
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
		bAdd = Boolean.FALSE;

		listPm0411 = pm0411Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm0411.get(0).getUserid()) {
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

		listTrimestre = new ArrayList<String>();
		for (int i = 1; i <= 4; i++)
			listTrimestre.add("0" + i);
		trimestre = listTrimestre.get(0);

		comboTri = pm0411Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getTrimestre().toString();
		bReport = Boolean.FALSE;
		if (comboTri.get(0).getTrimestre() == 0){
			bReport = Boolean.TRUE;
		}
		
		if(listPm0411.size() == 4 ) {
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
		
		jasperReporteName = "reporte112";
		endFilename = jasperReporteName + ".pdf";

	}

	/**
	 * Gets the months.
	 *
	 * @param trim the trim
	 * @return the months
	 */
	public String getMonths(Integer trim) {
		if (trim == 1) {
			return "01,03";
		} else if (trim == 2) {
			return "04,06";
		} else if (trim == 3) {
			return "07,09";
		} else {
			return "10,12";
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		String[] mesArray = this.getMonths(Integer.valueOf(conTrim)).split(",");

		String fecha = "DEL 1° DE " + tcMesRepository.findByMes(mesArray[0]).getDescripcion() + " AL "
				+ getLastDay(Integer.valueOf(mesArray[1])) + " DE "
				+ tcMesRepository.findByMes(mesArray[1]).getDescripcion() + " DE " + conctb.getAnoemp();

		parameters.put("TRIMESTRE", Integer.valueOf(conTrim));
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("fecha", fecha);
		parameters.put("nombreMunicipio", firmas.getCampo1());
		parameters.put("clave", conctb.getClave());
		parameters.put("N4", firmas.getN4());
		parameters.put("L4", firmas.getL4());
		parameters.put("N5", firmas.getN5());
		parameters.put("L5", firmas.getL5());

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
	public List<Pm0411> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm0411> comboTri) {
		this.comboTri = comboTri;
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
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
		listPm0411.add(0, pm0411Service.add());
		RequestContext.getCurrentInstance().execute("PF('pm0411Wdg').paginator.setPage(0);");
		if (null == listPm0411.get(1).getUserid())
			listPm0411.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm0411 = this.entitySave(index);
		
		if(null == pm0411.getDc())
			pm0411.setDc(0);
		if(null == pm0411.getDi())
			pm0411.setDi(0);
		if(null == pm0411.getObsdc())
			pm0411.setObsdc("");
		if(null == pm0411.getObsdi())
			pm0411.setObsdi("");
		
		if (bModificar) {
			pm0411Repository.save(pm0411);
			listPm0411 = pm0411Service.calculationAccumulated(pm0411.getIdsector());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modificaron los datos correctamente");

			bReport = Boolean.FALSE;
			
			bLblTrimestre = Boolean.TRUE;
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
			listPm0411.set(index, pm0411);
			
			listPm0411 = pm0411Service.save(index, listPm0411);
			if(listPm0411.get(index).isgBuardar() == Boolean.TRUE) {
				bReport = Boolean.FALSE;
				bLblTrimestre = Boolean.TRUE;
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
		
		
		if(listPm0411.size() == 4 && listPm0411.get(index).isgBuardar() == Boolean.TRUE) {
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
	
		comboTri = pm0411Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm0411 = pm0411Service.delete(index, listPm0411);
		comboTri = pm0411Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		bReport = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		listPm0411 = pm0411Repository.findAllByIdsector(this.getUserDetails().getIdSector(), this.orderByAsc());
		if(CollectionUtils.isEmpty(listPm0411)) {
			bBorrar = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bReport = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			listPm0411.add(this.pm0411Service.add());
			
		}
		
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm0411 = pm0411Repository.findAllBytrimestreAndIdsector(listPm0411.get(index).getTrimestre(),
					this.getUserDetails().getIdSector());
			listPm0411.add(index, pm0411);
		} else
			listPm0411 = pm0411Service.clean(index, listPm0411);
	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {

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
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {
		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		listPm0411 = pm0411Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm0411.get(0).getUserid()) {
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
			

		} 
		
		bReport = Boolean.FALSE;
		if (comboTri.get(0).getTrimestre() == 0){
			bReport = Boolean.TRUE;
		}
		
		if(listPm0411.size() == 4 ) {
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

		
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 0411
	 */
	public Pm0411 entitySave(Integer index) {
		pm0411 = listPm0411.get(index);
		pm0411.setUserid(this.getUserDetails().getUsername());
		pm0411.setIdRef(0L);
		pm0411.setIdsector(this.getUserDetails().getIdSector());
		pm0411.setFeccap(Calendar.getInstance().getTime());
		pm0411.setCapturo(this.getUserDetails().getUsername());
		pm0411.setTrimestre(bModificar == true ? pm0411.getTrimestre() : Integer.valueOf(trimestre));
		return pm0411;
	}
	
	/**
	 * Order by asc.
	 *
	 * @return the sort
	 */
	public Sort orderByAsc(){
		return new Sort(Sort.Direction.ASC, "trimestre");
	}

}
