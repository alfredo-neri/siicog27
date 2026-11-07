package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

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
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm2911;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm2911Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm2911Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte131MB.
 */
@ManagedBean(name = "reporte131MB")
@ViewScoped
public class Reporte131MB extends BaseDirectReport {
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The semestre. */
	private String semestre;
	
	/** The listsemestre. */
	private List<String> listsemestre;

	/** The pm 2911. */
	private Pm2911 pm2911;
	
	/** The list pm 2911. */
	private List<Pm2911> listPm2911;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm2911> comboTri;

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

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The pm 2911 service. */
	@ManagedProperty("#{pm2911Service}")
	private Pm2911Service pm2911Service;

	/** The pm 2911 repository. */
	@ManagedProperty("#{pm2911Repository}")
	private Pm2911Repository pm2911Repository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;


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
	 * Gets the pm 2911.
	 *
	 * @return the pm 2911
	 */
	public Pm2911 getPm2911() {
		return pm2911;
	}

	/**
	 * Sets the pm 2911.
	 *
	 * @param pm2911 the new pm 2911
	 */
	public void setPm2911(Pm2911 pm2911) {
		this.pm2911 = pm2911;
	}

	/**
	 * Gets the list pm 2911.
	 *
	 * @return the list pm 2911
	 */
	public List<Pm2911> getListPm2911() {
		return listPm2911;
	}

	/**
	 * Sets the list pm 2911.
	 *
	 * @param listPm2911 the new list pm 2911
	 */
	public void setListPm2911(List<Pm2911> listPm2911) {
		this.listPm2911 = listPm2911;
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
	 * Gets the pm 2911 service.
	 *
	 * @return the pm 2911 service
	 */
	public Pm2911Service getPm2911Service() {
		return pm2911Service;
	}

	/**
	 * Sets the pm 2911 service.
	 *
	 * @param pm2911Service the new pm 2911 service
	 */
	public void setPm2911Service(Pm2911Service pm2911Service) {
		this.pm2911Service = pm2911Service;
	}

	/**
	 * Gets the pm 2911 repository.
	 *
	 * @return the pm 2911 repository
	 */
	public Pm2911Repository getPm2911Repository() {
		return pm2911Repository;
	}

	/**
	 * Sets the pm 2911 repository.
	 *
	 * @param pm2911Repository the new pm 2911 repository
	 */
	public void setPm2911Repository(Pm2911Repository pm2911Repository) {
		this.pm2911Repository = pm2911Repository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
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

		listPm2911 = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm2911.get(0).getUserid()) {
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

		if (listPm2911.size() == 2)
			bAdd = Boolean.TRUE;

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++)
			listsemestre.add("0" + i);
		semestre = listsemestre.get(0);

		comboTri = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemes().toString();

		jasperReporteName = "reporte131";
		endFilename = jasperReporteName + ".pdf";
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
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		parameters.put("municipioName", this.getUserDetails().getMunicipio());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDay(Integer.valueOf(getMesFinal(Integer.valueOf(conTrim)))));
		parameters.put("mesInicioName",
				tcMesRepository.findByMes(getMesInicial(Integer.valueOf(conTrim))).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(getMesFinal(Integer.valueOf(conTrim))).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", Integer.valueOf(conTrim));
		parameters.put("idSector", getUserDetails().getIdSector());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
	    parameters.put("firmaN1", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F22.getValue());
	    parameters.put("firmaP2", firma.getPuesto().getPuesto());
	    parameters.put("firmaN2", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
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
	public List<Pm2911> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm2911> comboTri) {
		this.comboTri = comboTri;
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		if (listPm2911.size() == 2) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"Ya existen dos semestre en la base, solo se puede modifcar la información");
		} else {
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
			listPm2911.add(index, noEmpty(pm2911Service.add()));

			if (null == listPm2911.get(1).getUserid())
				listPm2911.remove(1);
		}

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm2911 = this.entitySave(index);
		if (pm2911.getGastot() == null)
			pm2911.setGastot(BigDecimal.ZERO);
		if (pm2911.getObsgas() == null)
			pm2911.setObsgas("");
		if (pm2911.getTotton() == null)
			pm2911.setTotton(BigDecimal.ZERO);
		if (pm2911.getObstot() == null)
			pm2911.setObstot("");
		if (bModificar) {

			pm2911Repository.save(pm2911);

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
			RequestContext.getCurrentInstance()
					.execute("PF('pm2511Wdg').paginator.setPage("
							+ (this.pm2911Repository
									.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector()).size() - 1)
							+ ")");
			calcularAcumulado();
		} else {

			listPm2911.set(index, pm2911);
			listPm2911 = pm2911Service.save(index, listPm2911);
			// this.calcularAcumulado(pm2911);
			if (listPm2911.get(index).isbGuardar()) {
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
				RequestContext.getCurrentInstance()
						.execute("PF('pm2511Wdg').paginator.setPage(" + (this.pm2911Repository
								.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector()).size() - 1)
								+ ")");
				calcularAcumulado();
			}
		}

		if (listPm2911.size() == 2 && listPm2911.get(index).isbGuardar()) {
			bAdd = Boolean.TRUE;
		}
		comboTri = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm2911 = pm2911Service.delete(index, listPm2911);
		listPm2911 = pm2911Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm2911)) {
			bLblsemestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;

		}
		bAdd = Boolean.FALSE;
		listPm2911 = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		comboTri = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		RequestContext.getCurrentInstance().execute("PF('pm2511Wdg').paginator.setPage("
				+ (this.pm2911Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector()).size()
						- 1)
				+ ")");
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm2911 = pm2911Repository.findAllBysemesAndIdsector(listPm2911.get(index).getSemes(),
					this.getUserDetails().getIdSector());
			listPm2911.set(index, pm2911);
		} else
			listPm2911 = pm2911Service.clean(index, listPm2911);
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
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		listPm2911 = pm2911Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		if (listPm2911.isEmpty()) {
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			listPm2911 = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		}

		if (listPm2911.size() == 2)
			bAdd = Boolean.TRUE;

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 2911
	 */
	public Pm2911 entitySave(Integer index) {
		pm2911 = listPm2911.get(index);
		pm2911.setUserid(this.getUserDetails().getUsername());
		pm2911.setIdRef(0L);
		pm2911.setIdsector(this.getUserDetails().getIdSector());
		pm2911.setFeccap(Calendar.getInstance().getTime());
		pm2911.setCapturo(this.getUserDetails().getUsername());
		pm2911.setSemes(bModificar == true ? pm2911.getSemes() : Integer.valueOf(semestre));
		return pm2911;
	}

	/**
	 * Calcular acumulado.
	 */
	public void calcularAcumulado() {
		Double acumgas = 0.00;
		Double acumtot = 0.00;
		listPm2911 = pm2911Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		for (int i = 0; i < listPm2911.size(); i++) {
			acumgas = acumgas + listPm2911.get(i).getGastot().doubleValue();
			acumtot = acumtot + listPm2911.get(i).getTotton().doubleValue();
			listPm2911.get(i).setAcumgas(BigDecimal.valueOf(acumgas));
			listPm2911.get(i).setAcumtot(BigDecimal.valueOf(acumtot));
			Pm2911 saveEntity = listPm2911.get(i);
			pm2911Repository.save(saveEntity);
		}

	}

	/**
	 * No empty.
	 *
	 * @param entity the entity
	 * @return the pm 2911
	 */
	public Pm2911 noEmpty(Pm2911 entity) {
		entity.setTotton(entity.getTotton() == null ? BigDecimal.ZERO : entity.getTotton());
		entity.setGastot(entity.getGastot() == null ? BigDecimal.ZERO : entity.getGastot());
		entity.setObstot(entity.getObstot() == null ? "" : entity.getObstot());
		entity.setObsgas(entity.getObsgas() == null ? "" : entity.getObsgas());

		return entity;
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
}
