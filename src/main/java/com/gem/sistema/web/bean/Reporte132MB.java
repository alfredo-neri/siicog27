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
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm0811;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm0811Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm0811Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte132MB.
 */
@ManagedBean(name = "reporte132MB")
@ViewScoped
public class Reporte132MB extends BaseDirectReport {

	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The semestre. */
	private String semestre;
	
	/** The listsemestre. */
	private List<String> listsemestre;

	/** The pm 0811. */
	private Pm0811 pm0811;
	
	/** The list pm 0811. */
	private List<Pm0811> listPm0811;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm0811> comboTri;

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

	/** The pm 0811 service. */
	@ManagedProperty("#{pm0811Service}")
	private Pm0811Service pm0811Service;

	/** The pm 0811 repository. */
	@ManagedProperty("#{pm0811Repository}")
	private Pm0811Repository pm0811Repository;
	
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
	 * Gets the pm 0811.
	 *
	 * @return the pm 0811
	 */
	public Pm0811 getPm0811() {
		return pm0811;
	}

	/**
	 * Sets the pm 0811.
	 *
	 * @param pm0811 the new pm 0811
	 */
	public void setPm0811(Pm0811 pm0811) {
		this.pm0811 = pm0811;
	}

	/**
	 * Gets the list pm 0811.
	 *
	 * @return the list pm 0811
	 */
	public List<Pm0811> getListPm0811() {
		return listPm0811;
	}

	/**
	 * Sets the list pm 0811.
	 *
	 * @param listPm0811 the new list pm 0811
	 */
	public void setListPm0811(List<Pm0811> listPm0811) {
		this.listPm0811 = listPm0811;
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
	 * Gets the pm 0811 service.
	 *
	 * @return the pm 0811 service
	 */
	public Pm0811Service getPm0811Service() {
		return pm0811Service;
	}

	/**
	 * Sets the pm 0811 service.
	 *
	 * @param pm0811Service the new pm 0811 service
	 */
	public void setPm0811Service(Pm0811Service pm0811Service) {
		this.pm0811Service = pm0811Service;
	}

	/**
	 * Gets the pm 0811 repository.
	 *
	 * @return the pm 0811 repository
	 */
	public Pm0811Repository getPm0811Repository() {
		return pm0811Repository;
	}

	/**
	 * Sets the pm 0811 repository.
	 *
	 * @param pm0811Repository the new pm 0811 repository
	 */
	public void setPm0811Repository(Pm0811Repository pm0811Repository) {
		this.pm0811Repository = pm0811Repository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "reporte132";
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

		listPm0811 = pm0811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm0811.get(0).getUserid()) {
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

		comboTri = pm0811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemestral().toString();

		if (listPm0811.size() == 2) {
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

	/* (non-Javadoc)
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
		parameters.put("firmaP1", firmas.getL4());
		parameters.put("firmaP2", "");
		parameters.put("firmaP3", firmas.getL7());
		parameters.put("firmaN1", firmas.getN4());
		parameters.put("firmaN2", "");
		parameters.put("firmaN3", firmas.getN7());
		
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F07.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());

		return parameters;
	}

	/* (non-Javadoc)
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
	public List<Pm0811> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm0811> comboTri) {
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
		listPm0811.add(index, pm0811Service.add());
		if (null == listPm0811.get(1).getUserid())
			listPm0811.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm0811 = this.entitySave(index);
		if (bModificar) {
			pm0811Repository.save(pm0811);
			// this.calcularAcumulado(pm0811);

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
			listPm0811.set(index, pm0811);
			listPm0811 = pm0811Service.save(index, listPm0811);
			if (listPm0811.get(index).isbGuarda()) {
				// this.calcularAcumulado(pm0811);
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
		if (listPm0811.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		comboTri = pm0811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm0811 = pm0811Service.delete(index, listPm0811);
		listPm0811 = pm0811Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		if (CollectionUtils.isEmpty(listPm0811)) {
			listPm0811 = pm0811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
			bBorrar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
		}

		comboTri = pm0811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm0811 = pm0811Repository.findAllBysemestralAndIdsector(listPm0811.get(index).getSemestral(),
					this.getUserDetails().getIdSector());
			listPm0811.set(index, pm0811);
		} else
			listPm0811 = pm0811Service.clean(index, listPm0811);
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
		
		if (listPm0811.size() == 2) {
			bAdd = Boolean.TRUE;
		}

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
		bBtnMoficar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		listPm0811 = pm0811Repository.findAllByIdsectorOrderBySemestralAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm0811)) {
			bBorrar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			
			
			listPm0811 = pm0811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		}

		bModificar = Boolean.FALSE;
		if (listPm0811.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 0811
	 */
	public Pm0811 entitySave(Integer index) {
		pm0811 = listPm0811.get(index);
		pm0811.setUserid(this.getUserDetails().getUsername());
		pm0811.setIdRef(0L);
		pm0811.setIdsector(this.getUserDetails().getIdSector());
		pm0811.setFeccap(Calendar.getInstance().getTime());
		pm0811.setCapturo(this.getUserDetails().getUsername());
		pm0811.setSemestral(bModificar == true ? pm0811.getSemestral() : Integer.valueOf(semestre));
		
		
		return pm0811;
	}

	/**
	 * Calcular acumulado.
	 *
	 * @param entity the entity
	 */
	public void calcularAcumulado(Pm0811 entity) {
		Integer contar = pm0811Repository.count(this.getUserDetails().getIdSector());

		// if(contar.intValue() < 2){
		// entity.setAcudi(0);
		// entity.setAcudc(0);
		// } else {
		// entity.setAcudi(Pm0811Repository.sumAcumDi(this.getUserDetails().getIdSector()));
		// entity.setAcudc(Pm0811Repository.sumAcumDc(this.getUserDetails().getIdSector()));
		// }
		// Pm0811Repository.save(entity);
	}

}
