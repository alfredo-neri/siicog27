package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm4011;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.predicates.Pm4011Predicate;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm4011Repository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm4011Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte155MB.
 */
@ManagedBean(name = "reporte155MB")
@ViewScoped
public class Reporte155MB extends BaseDirectReport {
	/** The Constant TRUE. */
	private static final Boolean TRUE = Boolean.TRUE;

	/** The Constant FALSE. */
	private static final Boolean FALSE = Boolean.FALSE;

	/** The b visible. */
	private Boolean bVisible;

	/** The b modificar. */
	private Boolean bModificar;

	/** The b salvar. */
	private Boolean bSalvar;

	/** The b add. */
	private Boolean bAdd;

	/** The b cancel. */
	private Boolean bCancel;

	/** The b delete. */
	private Boolean bDelete;

	/** The b update. */
	private Boolean bUpdate;

	/** The b edit. */
	private Boolean bEdit;

	/** The anio. */
	private Integer anio;

	private Integer oldClv;

	/** The pm 4011. */
	private Pm4011 pm4011;

	private Integer currentPage;

	/** The list pm 4011. */
	private List<Pm4011> listPm4011;

	/** The pm 4011 service. */
	@ManagedProperty("#{pm4011Service}")
	private Pm4011Service pm4011Service;

	/** The pm 4011 repository. */
	@ManagedProperty("#{pm4011Repository}")
	private Pm4011Repository pm4011Repository;

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
	 * Gets the pm 4011 service.
	 *
	 * @return the pm 4011 service
	 */
	public Pm4011Service getPm4011Service() {
		return pm4011Service;
	}

	/**
	 * Sets the pm 4011 service.
	 *
	 * @param pm4011Service the new pm 4011 service
	 */
	public void setPm4011Service(Pm4011Service pm4011Service) {
		this.pm4011Service = pm4011Service;
	}

	/**
	 * Gets the pm 4011 repository.
	 *
	 * @return the pm 4011 repository
	 */
	public Pm4011Repository getPm4011Repository() {
		return pm4011Repository;
	}

	/**
	 * Sets the pm 4011 repository
	 *
	 * @param pm4011Repository the new pm 4011 repository
	 */
	public void setPm4011Repository(Pm4011Repository pm4011Repository) {
		this.pm4011Repository = pm4011Repository;
	}

	/**
	 * Gets the pm 4011.
	 *
	 * @return the pm 4011
	 */
	public Pm4011 getPm4011() {
		return pm4011;
	}

	/**
	 * Sets the pm 4011.
	 *
	 * @param pm4011 the new pm 4011
	 */
	public void setPm4011(Pm4011 pm4011) {
		this.pm4011 = pm4011;
	}

	/**
	 * Gets the list pm 4011.
	 *
	 * @return the list pm 4011
	 */
	public List<Pm4011> getListPm4011() {
		return listPm4011;
	}

	/**
	 * Sets the list pm 4011.
	 *
	 * @param listPm4011 the new list pm 4011
	 */
	public void setListPm4011(List<Pm4011> listPm4011) {
		this.listPm4011 = listPm4011;
	}

	/**
	 * Gets the b visible.
	 *
	 * @return the b visible
	 */
	public Boolean getbVisible() {
		return bVisible;
	}

	/**
	 * Sets the b visible.
	 *
	 * @param bVisible the new b visible
	 */
	public void setbVisible(Boolean bVisible) {
		this.bVisible = bVisible;
	}

	/**
	 * Gets the b modificar.
	 *
	 * @return the b modificar
	 */
	public Boolean getbModificar() {
		return bModificar;
	}

	/**
	 * Sets the b modificar.
	 *
	 * @param bModificar the new b modificar
	 */
	public void setbModificar(Boolean bModificar) {
		this.bModificar = bModificar;
	}

	/**
	 * Gets the b salvar.
	 *
	 * @return the b salvar
	 */
	public Boolean getbSalvar() {
		return bSalvar;
	}

	/**
	 * Sets the b salvar.
	 *
	 * @param bSalvar the new b salvar
	 */
	public void setbSalvar(Boolean bSalvar) {
		this.bSalvar = bSalvar;
	}

	/**
	 * Gets the b add.
	 *
	 * @return the b add
	 */
	public Boolean getbAdd() {
		return bAdd;
	}

	/**
	 * Sets the b add.
	 *
	 * @param bAdd the new b add
	 */
	public void setbAdd(Boolean bAdd) {
		this.bAdd = bAdd;
	}

	/**
	 * Gets the b cancel.
	 *
	 * @return the b cancel
	 */
	public Boolean getbCancel() {
		return bCancel;
	}

	/**
	 * Sets the b cancel.
	 *
	 * @param bCancel the new b cancel
	 */
	public void setbCancel(Boolean bCancel) {
		this.bCancel = bCancel;
	}

	/**
	 * Gets the b delete.
	 *
	 * @return the b delete
	 */
	public Boolean getbDelete() {
		return bDelete;
	}

	/**
	 * Sets the b delete.
	 *
	 * @param bDelete the new b delete
	 */
	public void setbDelete(Boolean bDelete) {
		this.bDelete = bDelete;
	}

	/**
	 * Gets the b update.
	 *
	 * @return the b update
	 */
	public Boolean getbUpdate() {
		return bUpdate;
	}

	/**
	 * Sets the b update.
	 *
	 * @param bUpdate the new b update
	 */
	public void setbUpdate(Boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	/**
	 * Gets the b edit.
	 *
	 * @return the b edit
	 */
	public Boolean getbEdit() {
		return bEdit;
	}

	/**
	 * Sets the b edit.
	 *
	 * @param bEdit the new b edit
	 */
	public void setbEdit(Boolean bEdit) {
		this.bEdit = bEdit;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.setAnio(this.getPm4011Service().loadData(this.getUserDetails().getIdSector()).getAnual());
		this.setbVisible(TRUE);
		this.setbModificar(FALSE);
		this.setbSalvar(FALSE);
		this.setbAdd(FALSE);
		this.setbCancel(TRUE);
		this.setbDelete(FALSE);
		this.setbUpdate(FALSE);
		this.setbEdit(FALSE);
		listPm4011 = this.getPm4011Service().orderByClaveAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm4011)) {
			this.setbDelete(TRUE);
			this.setbModificar(TRUE);
			listPm4011.add(this.getPm4011Service().loadData(this.getUserDetails().getIdSector()));
		}

		Calendar calendar = Calendar.getInstance();
		calendar.get(Calendar.MONTH);
		this.jasperReporteName = "reporte155";
		this.endFilename = "reporte155.pdf";

		currentPage = 0;
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		this.setbVisible(FALSE);
		this.setbModificar(TRUE);
		this.setbSalvar(TRUE);
		this.setbAdd(TRUE);
		this.setbCancel(FALSE);
		this.setbDelete(TRUE);
		this.setbUpdate(FALSE);
		this.setbEdit(TRUE);
		pm4011 = new Pm4011();
		int indexof = 0;
		listPm4011.add(indexof, pm4011);
		RequestContext.getCurrentInstance().execute("PF('pm4011Wdg').paginator.setPage(" + indexof + ");");
	}

	/**
	 * Update.
	 *
	 * @param index the index
	 */
	public void update(Integer index) {
		this.setbVisible(FALSE);
		this.setbModificar(TRUE);
		this.setbSalvar(TRUE);
		this.setbAdd(TRUE);
		this.setbCancel(FALSE);
		this.setbDelete(TRUE);
		this.setbUpdate(TRUE);
		this.setbEdit(TRUE);
		pm4011 = listPm4011.get(index);
		oldClv = pm4011.getClvreq();
		if (pm4011.getCumple().equals("SI")) {
			pm4011.setCumple("1");
		} else {
			pm4011.setCumple("0");
		}
		listPm4011.set(index, pm4011);
	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {

		boolean flag = false;
		boolean flag2 = false;

		this.setbVisible(TRUE);
		this.setbModificar(FALSE);
		this.setbSalvar(FALSE);
		this.setbAdd(FALSE);
		// this.setbCancel(TRUE);
		this.setbDelete(FALSE);
		// this.setbUpdate(FALSE);
		this.setbEdit(FALSE);

		pm4011 = listPm4011.get(index);
		this.getPm4011().setAnual(this.getAnio());
		this.getPm4011().setCapturo(this.getUserDetails().getUsername());
		this.getPm4011().setIdsector(this.getUserDetails().getIdSector());
		this.getPm4011().setFeccap(Calendar.getInstance().getTime());
		this.getPm4011().setUserid(this.getUserDetails().getUsername());
		this.getPm4011().setIdRef(0L);
		this.getPm4011().setIndex(currentPage);
		if (pm4011.getCumple().equals("1")) {
			this.getPm4011().setCumple("SI");
		} else {
			this.getPm4011().setCumple("NO");
		}
		if (TRUE == this.getbUpdate()) {
			if (oldClv == pm4011.getClvreq()) {
				listPm4011 = this.getPm4011Service().update(pm4011);
				listPm4011.get(index).setbGuardar(TRUE);
			} else {
				if (FALSE == this.pm4011Service.findByClave(pm4011.getClvreq(), this.getUserDetails().getIdSector())) {
					listPm4011.get(index).setbGuardar(FALSE);
					generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La clave ya existe.");
					flag = true;
					flag2 = true;
				} else {
					listPm4011 = this.getPm4011Service().update(pm4011);
					listPm4011.get(index).setbGuardar(TRUE);
				}
			}

		} else {

			listPm4011 = this.getPm4011Service().save(pm4011);
			flag2 = true;
			flag = true;
		}

		if (listPm4011.get(index).getbGuardar() == TRUE) {
			this.setbVisible(TRUE);
			this.setbModificar(FALSE);
			this.setbSalvar(FALSE);
			this.setbAdd(FALSE);
			this.setbCancel(TRUE);
			this.setbDelete(FALSE);
			this.getbUpdate();
			this.setbEdit(FALSE);
			listPm4011 = this.getPm4011Service().orderByClaveAsc(this.getUserDetails().getIdSector());
		} else {

			this.setbVisible(FALSE);

			this.setbModificar(TRUE);
			this.setbSalvar(TRUE);
			this.setbAdd(TRUE);
			this.setbCancel(FALSE);
			this.setbDelete(TRUE);
			this.getbUpdate();
			this.setbEdit(TRUE);

			if (listPm4011.get(index).getCumple().equals("SI")) {
				listPm4011.get(index).setCumple("1");
			} else {
				listPm4011.get(index).setCumple("0");
			}
		}

	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		this.setbVisible(TRUE);
		this.setbModificar(FALSE);
		this.setbSalvar(FALSE);
		this.setbAdd(FALSE);
		this.setbCancel(TRUE);
		this.setbDelete(FALSE);
		this.setbUpdate(FALSE);
		this.setbEdit(FALSE);
		listPm4011 = this.getPm4011Service().orderByClaveAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm4011)) {
			this.setbVisible(TRUE);
			this.setbModificar(TRUE);
			this.setbSalvar(FALSE);
			this.setbAdd(FALSE);
			this.setbCancel(TRUE);
			this.setbDelete(TRUE);
			this.setbUpdate(FALSE);
			pm4011 = this.getPm4011Service().loadData(this.getUserDetails().getIdSector());
			listPm4011.add(pm4011);
		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada.");
	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		pm4011 = listPm4011.get(index);
		this.setbVisible(TRUE);
		this.setbModificar(FALSE);
		this.setbSalvar(FALSE);
		this.setbAdd(FALSE);
		this.setbCancel(TRUE);
		this.setbDelete(FALSE);
		this.setbUpdate(FALSE);
		this.setbEdit(FALSE);
		listPm4011 = this.pm4011Service.delete(pm4011);
		if (CollectionUtils.isEmpty(listPm4011)) {
			this.setbVisible(TRUE);
			this.setbModificar(TRUE);
			this.setbSalvar(FALSE);
			this.setbAdd(FALSE);
			this.setbCancel(TRUE);
			this.setbDelete(TRUE);
			this.setbUpdate(FALSE);
			listPm4011.add(this.getPm4011Service().loadData(this.getUserDetails().getIdSector()));
		}
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {

		if (TRUE == this.getbUpdate()) {
			pm4011 = this.getPm4011Repository()
					.findOne(Pm4011Predicate.findByClaveAndIdSector(oldClv, this.getUserDetails().getIdSector()));
			listPm4011.set(index, pm4011);
			if (pm4011.getCumple().equals("SI")) {
				listPm4011.get(index).setCumple("1");
			} else {
				listPm4011.get(index).setCumple("0");
			}
		} else {
			listPm4011.set(index, this.pm4011Service.loadData(this.getUserDetails().getIdSector()));
		}
	}

	/** The paraameters. */
	Map<String, Object> paraameters = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		paraameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		paraameters.put("pAnio", conctb.getAnoemp());
		paraameters.put("pClaveMunic", conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave());
		paraameters.put("pImgMuni", this.getUserDetails().getPathImgCab1());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F13.getValue());
		paraameters.put("pL3", firma.getPuesto().getPuesto());
		paraameters.put("pN3", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		paraameters.put("pL4", firma.getPuesto().getPuesto());
		paraameters.put("pN4", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F24.getValue());
		paraameters.put("pL5", firma.getPuesto().getPuesto());
		paraameters.put("pN5", firma.getNombre());

		paraameters.put("pNomMuni", conctb.getNomDep());
		paraameters.put("pSector", this.getUserDetails().getIdSector());
		return paraameters;
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

	public void chagePage(PageEvent event) {
		currentPage = event.getPage();
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getOldClv() {
		return oldClv;
	}

	public void setOldClv(Integer oldClv) {
		this.oldClv = oldClv;
	}

}
