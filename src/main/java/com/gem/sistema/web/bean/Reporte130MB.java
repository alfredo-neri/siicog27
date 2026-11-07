package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.math.BigDecimal;
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
import com.gem.sistema.business.domain.Pm2611;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm2611Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm2611Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte130MB.
 */
@ManagedBean(name = "reporte130MB")
@ViewScoped
public class Reporte130MB extends BaseDirectReport {
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The semestre. */
	private String semestre;
	
	/** The listsemestre. */
	private List<String> listsemestre;

	/** The pm 2611. */
	private Pm2611 pm2611;
	
	/** The list pm 2611. */
	private List<Pm2611> listPm2611;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm2611> comboTri;

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

	/** The pm 2611 service. */
	@ManagedProperty("#{pm2611Service}")
	private Pm2611Service pm2611Service;

	/** The pm 2611 repository. */
	@ManagedProperty("#{pm2611Repository}")
	private Pm2611Repository pm2611Repository;

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
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public String getSemestre() {
		return semestre;
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
	 * Gets the pm 2611.
	 *
	 * @return the pm 2611
	 */
	public Pm2611 getPm2611() {
		return pm2611;
	}

	/**
	 * Sets the pm 2611.
	 *
	 * @param pm2611 the new pm 2611
	 */
	public void setPm2611(Pm2611 pm2611) {
		this.pm2611 = pm2611;
	}

	/**
	 * Gets the list pm 2611.
	 *
	 * @return the list pm 2611
	 */
	public List<Pm2611> getListPm2611() {
		return listPm2611;
	}

	/**
	 * Sets the list pm 2611.
	 *
	 * @param listPm2611 the new list pm 2611
	 */
	public void setListPm2611(List<Pm2611> listPm2611) {
		this.listPm2611 = listPm2611;
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
	 * Gets the pm 2611 service.
	 *
	 * @return the pm 2611 service
	 */
	public Pm2611Service getPm2611Service() {
		return pm2611Service;
	}

	/**
	 * Sets the pm 2611 service.
	 *
	 * @param pm2611Service the new pm 2611 service
	 */
	public void setPm2611Service(Pm2611Service pm2611Service) {
		this.pm2611Service = pm2611Service;
	}

	/**
	 * Gets the pm 2611 repository.
	 *
	 * @return the pm 2611 repository
	 */
	public Pm2611Repository getPm2611Repository() {
		return pm2611Repository;
	}

	/**
	 * Sets the pm 2611 repository.
	 *
	 * @param pm2611Repository the new pm 2611 repository
	 */
	public void setPm2611Repository(Pm2611Repository pm2611Repository) {
		this.pm2611Repository = pm2611Repository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte130";
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

		listPm2611 = pm2611Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

		if (null == listPm2611.get(0).getUserid()) {
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

		comboTri = pm2611Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemes().toString();

		if (listPm2611.size() == 2) {
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
		Integer idSector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		String[] mesArray = this.getMonths(Integer.valueOf(conTrim)).split(",");
		parameters.put("municipioName", firmas.getCampo1());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDayByAnoEmp(Integer.valueOf(mesArray[1]), conctb.getAnoemp()));
		parameters.put("mesInicioName", tcMesRepository.findByMes(mesArray[0]).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(mesArray[1]).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", Integer.valueOf(conTrim));
		parameters.put("idSector", getUserDetails().getIdSector());
		
		
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		
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
	public List<Pm2611> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm2611> comboTri) {
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
		listPm2611.add(index, pm2611Service.add());
		if (null == listPm2611.get(1).getUserid())
			listPm2611.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm2611 = this.entitySave(index);
		if (bModificar) {
			pm2611Repository.save(pm2611);
			this.calcularAcumulado(pm2611);
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
			listPm2611.set(index, pm2611);
			listPm2611 = pm2611Service.save(index, listPm2611);
			if (listPm2611.get(index).isbGuardar()) {
				this.calcularAcumulado(pm2611);
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
		if (listPm2611.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		comboTri = pm2611Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {

		listPm2611 = pm2611Service.delete(index, listPm2611);
		listPm2611 = pm2611Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		bAdd = Boolean.FALSE;
		if (CollectionUtils.isEmpty(listPm2611)) {
			bBorrar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
		}
		if (listPm2611.size() == 2) {
			bAdd = Boolean.TRUE;
		}
		listPm2611 = pm2611Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

		comboTri = pm2611Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm2611 = pm2611Repository.findAllBysemesAndIdsector(listPm2611.get(index).getSemes(),
					this.getUserDetails().getIdSector());
			listPm2611.get(index).setSemes(pm2611.getSemes());
			listPm2611.get(index).setVoltot(pm2611.getVoltot());
			listPm2611.get(index).setVoltra(pm2611.getVoltra());
			listPm2611.get(index).setObstot(pm2611.getObstot());
			listPm2611.get(index).setObstra(pm2611.getObstra());
		} else
			listPm2611 = pm2611Service.clean(index, listPm2611);
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
		if (listPm2611.size() == 2) {
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

		listPm2611 = pm2611Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm2611)) {
			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}

		bModificar = Boolean.FALSE;

		bAdd = Boolean.FALSE;
		listPm2611 = pm2611Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
		
		if (listPm2611.size() == 2) {
			bAdd = Boolean.TRUE;
		}
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 2611
	 */
	public Pm2611 entitySave(Integer index) {
		pm2611 = listPm2611.get(index);
		pm2611.setUserid(this.getUserDetails().getUsername());
		pm2611.setIdRef(0L);
		pm2611.setIdsector(this.getUserDetails().getIdSector());
		pm2611.setFeccap(Calendar.getInstance().getTime());
		pm2611.setCapturo(this.getUserDetails().getUsername());
		pm2611.setSemes(bModificar == true ? pm2611.getSemes() : Integer.valueOf(semestre));
		return pm2611;
	}

	/**
	 * Calcular acumulado.
	 *
	 * @param entity the entity
	 */
	public void calcularAcumulado(Pm2611 entity) {
		Integer contar = pm2611Repository.count(this.getUserDetails().getIdSector());

		// if(contar.intValue() < 2){
		// entity.setAcudi(0);
		// entity.setAcudc(0);
		// } else {
		// entity.setAcudi(Pm2611Repository.sumAcumDi(this.getUserDetails().getIdSector()));
		// entity.setAcudc(Pm2611Repository.sumAcumDc(this.getUserDetails().getIdSector()));
		// }
		// Pm2611Repository.save(entity);
	}

}
