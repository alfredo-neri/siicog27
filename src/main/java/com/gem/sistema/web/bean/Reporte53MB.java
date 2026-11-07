package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getMonthName;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.primefaces.model.StreamedContent;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.gem.sistema.business.bs.Pm5211BS;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm5211;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm5211Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm5211Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.util.FrontProperty;

import antlr.collections.impl.LList;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte53MB.
 */
@ManagedBean
@ViewScoped
public class Reporte53MB extends BaseDirectReport {

	/** The Constant sort. */
	private static final Sort sort = new Sort(Direction.ASC, "mensual");

	/** The Constant INACTIVO. */
	private static final Integer INACTIVO = 1;
	
	/** The Constant ACTIVO. */
	private static final Integer ACTIVO = 2;

	/** The mes. */
	private String mes;
	
	/** The mes report. */
	private String mesReport;
	
	/** The pm 5211. */
	private Pm5211 pm5211 = new Pm5211();
	
	/** The select. */
	private Pm5211 select = new Pm5211();
	
	/** The conctb. */
	private Conctb conctb;

	/** The list pm 5211. */
	private List<Pm5211> listPm5211;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The list pm report. */
	private List<Pm5211> listPmReport;
	
	/** The b lbl. */
	private Boolean bLbl;
	
	/** The b combo. */
	private Boolean bCombo;
	
	/** The b inabilitado. */
	private boolean bInabilitado = Boolean.TRUE;
	
	/** The b V modficar. */
	private boolean bVModficar = Boolean.TRUE;
	
	/** The b modificar. */
	private boolean bModificar = Boolean.FALSE;
	
	/** The b mensual. */
	private boolean bMensual = Boolean.TRUE;
	
	/** The b btn. */
	private boolean bBtn = Boolean.TRUE;
	
	/** The b visible. */
	private boolean bVisible = Boolean.TRUE;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.TRUE;
	
	/** The b add. */
	private boolean bAdd = Boolean.FALSE;
	
	/** The b V salvar. */
	private boolean bVSalvar = Boolean.FALSE;
	
	/** The b guardar. */
	private boolean bGuardar = Boolean.FALSE;

	/** The pm 5211 repository. */
	@ManagedProperty("#{pm5211Repository}")
	private Pm5211Repository pm5211Repository;

	/** The pm 5211 service. */
	@ManagedProperty("#{pm5211Service}")
	private Pm5211Service pm5211Service;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas. */
	private Firmas firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/**
	 * Gets the pm 5211.
	 *
	 * @return the pm 5211
	 */
	public Pm5211 getPm5211() {
		return pm5211;
	}

	/**
	 * Sets the pm 5211.
	 *
	 * @param pm5211 the new pm 5211
	 */
	public void setPm5211(Pm5211 pm5211) {
		this.pm5211 = pm5211;
	}

	/**
	 * Gets the list pm 5211.
	 *
	 * @return the list pm 5211
	 */
	public List<Pm5211> getListPm5211() {
		return listPm5211;
	}

	/**
	 * Sets the list pm 5211.
	 *
	 * @param listPm5211 the new list pm 5211
	 */
	public void setListPm5211(List<Pm5211> listPm5211) {
		this.listPm5211 = listPm5211;
	}

	/**
	 * Checks if is b inabilitado.
	 *
	 * @return true, if is b inabilitado
	 */
	public boolean isbInabilitado() {
		return bInabilitado;
	}

	/**
	 * Sets the b inabilitado.
	 *
	 * @param bInabilitado the new b inabilitado
	 */
	public void setbInabilitado(boolean bInabilitado) {
		this.bInabilitado = bInabilitado;
	}

	/**
	 * Gets the b lbl.
	 *
	 * @return the b lbl
	 */
	public Boolean getbLbl() {
		return bLbl;
	}

	/**
	 * Sets the b lbl.
	 *
	 * @param bLbl the new b lbl
	 */
	public void setbLbl(Boolean bLbl) {
		this.bLbl = bLbl;
	}

	/**
	 * Gets the b combo.
	 *
	 * @return the b combo
	 */
	public Boolean getbCombo() {
		return bCombo;
	}

	/**
	 * Sets the b combo.
	 *
	 * @param bCombo the new b combo
	 */
	public void setbCombo(Boolean bCombo) {
		this.bCombo = bCombo;
	}

	/**
	 * Checks if is b btn.
	 *
	 * @return true, if is b btn
	 */
	public boolean isbBtn() {
		return bBtn;
	}

	/**
	 * Sets the b btn.
	 *
	 * @param bBtn the new b btn
	 */
	public void setbBtn(boolean bBtn) {
		this.bBtn = bBtn;
	}

	/**
	 * Checks if is b visible.
	 *
	 * @return true, if is b visible
	 */
	public boolean isbVisible() {
		return bVisible;
	}

	/**
	 * Sets the b visible.
	 *
	 * @param bVisible the new b visible
	 */
	public void setbVisible(boolean bVisible) {
		this.bVisible = bVisible;
	}

	/**
	 * Gets the pm 5211 repository.
	 *
	 * @return the pm 5211 repository
	 */
	public Pm5211Repository getPm5211Repository() {
		return pm5211Repository;
	}

	/**
	 * Sets the pm 5211 repository.
	 *
	 * @param pm5211Repository the new pm 5211 repository
	 */
	public void setPm5211Repository(Pm5211Repository pm5211Repository) {
		this.pm5211Repository = pm5211Repository;
	}

	/**
	 * Gets the select.
	 *
	 * @return the select
	 */
	public Pm5211 getSelect() {
		return select;
	}

	/**
	 * Sets the select.
	 *
	 * @param select the new select
	 */
	public void setSelect(Pm5211 select) {
		this.select = select;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
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
	 * Gets the pm 5211 service.
	 *
	 * @return the pm 5211 service
	 */
	public Pm5211Service getPm5211Service() {
		return pm5211Service;
	}

	/**
	 * Sets the pm 5211 service.
	 *
	 * @param pm5211Service the new pm 5211 service
	 */
	public void setPm5211Service(Pm5211Service pm5211Service) {
		this.pm5211Service = pm5211Service;
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
	 * Checks if is b V modficar.
	 *
	 * @return true, if is b V modficar
	 */
	public boolean isbVModficar() {
		return bVModficar;
	}

	/**
	 * Sets the b V modficar.
	 *
	 * @param bVModficar the new b V modficar
	 */
	public void setbVModficar(boolean bVModficar) {
		this.bVModficar = bVModficar;
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
	 * Checks if is b mensual.
	 *
	 * @return true, if is b mensual
	 */
	public boolean isbMensual() {
		return bMensual;
	}

	/**
	 * Sets the b mensual.
	 *
	 * @param bMensual the new b mensual
	 */
	public void setbMensual(boolean bMensual) {
		this.bMensual = bMensual;
	}

	/**
	 * Checks if is b V salvar.
	 *
	 * @return true, if is b V salvar
	 */
	public boolean isbVSalvar() {
		return bVSalvar;
	}

	/**
	 * Sets the b V salvar.
	 *
	 * @param bVSalvar the new b V salvar
	 */
	public void setbVSalvar(boolean bVSalvar) {
		this.bVSalvar = bVSalvar;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
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
	 * Gets the mes report.
	 *
	 * @return the mes report
	 */
	public String getMesReport() {
		return mesReport;
	}

	/**
	 * Sets the mes report.
	 *
	 * @param mesReport the new mes report
	 */
	public void setMesReport(String mesReport) {
		this.mesReport = mesReport;
	}

	/**
	 * Gets the list pm report.
	 *
	 * @return the list pm report
	 */
	public List<Pm5211> getListPmReport() {
		return listPmReport;
	}

	/**
	 * Sets the list pm report.
	 *
	 * @param listPmReport the new list pm report
	 */
	public void setListPmReport(List<Pm5211> listPmReport) {
		this.listPmReport = listPmReport;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		pm5211 = new Pm5211();
		listPm5211 = pm5211Service.orderByMendsualAsc(this.getUserDetails().getIdSector());
		this.bBorrar = Boolean.FALSE;
		this.banderas(INACTIVO);

		bVModficar = Boolean.TRUE;
		bModificar = Boolean.FALSE;
		bMensual = Boolean.TRUE;
		bVSalvar = Boolean.FALSE;
		this.setbCombo(Boolean.FALSE);
		this.setbLbl(Boolean.TRUE);

		if (CollectionUtils.isEmpty(listPm5211)) {
			pm5211.setIpr(BigDecimal.ZERO);
			pm5211.setImp(BigDecimal.ZERO);
			pm5211.setDer(BigDecimal.ZERO);
			pm5211.setProd(BigDecimal.ZERO);
			pm5211.setAprov(BigDecimal.ZERO);
			pm5211.setApmej(BigDecimal.ZERO);
			pm5211.setAcc(BigDecimal.ZERO);
			pm5211.setIpf(BigDecimal.ZERO);
			pm5211.setTing(BigDecimal.ZERO);
			pm5211.setMensual(0);
			listPm5211.add(pm5211);
			this.bBorrar = Boolean.TRUE;
		}
		this.jasperReporteName = "reporte53";
		this.endFilename = jasperReporteName;

		listMes = tcMesRepository.findAll();
		mes = listMes.get(0).getMes();

		listPmReport = pm5211Service.orderByMendsualAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isNotEmpty(listPmReport)) {
			mesReport = listPmReport.get(0).getMensual().toString();
		}

	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {
		bVModficar = Boolean.FALSE;
		bModificar = Boolean.TRUE;
		bMensual = Boolean.TRUE;
		bVSalvar = Boolean.TRUE;
		bBorrar = Boolean.TRUE;
		this.setbCombo(Boolean.TRUE);
		this.setbLbl(Boolean.FALSE);
		this.banderas(ACTIVO);
	}

	/**
	 * Banderas.
	 *
	 * @param opt the opt
	 */
	public void banderas(int opt) {
		switch (opt) {
		case 1:
			bBtn = Boolean.TRUE;
			bInabilitado = Boolean.TRUE;
			this.isbBorrar();
			this.isbAdd();
			break;
		case 2:
			bBtn = Boolean.FALSE;
			bInabilitado = Boolean.FALSE;
			bAdd = Boolean.TRUE;
			break;
		case 3:
			bBorrar = Boolean.FALSE;
		default:
			break;
		}
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		this.banderas(ACTIVO);

		bVModficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bMensual = Boolean.FALSE;
		bVSalvar = Boolean.TRUE;
		bBorrar = Boolean.TRUE;
		this.setbCombo(Boolean.TRUE);
		this.setbLbl(Boolean.FALSE);
		listPm5211.add(index, new Pm5211());
	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		this.setbCombo(Boolean.TRUE);
		this.setbLbl(Boolean.FALSE);
		listPm5211 = this.noEmpty(listPm5211, index);
		listPm5211.get(index).setUserid(this.getUserDetails().getUsername());
		listPm5211.get(index).setIdRef(0L);
		listPm5211.get(index).setIdsector(this.getUserDetails().getIdSector());
		listPm5211.get(index).setCapturo(this.getUserDetails().getUsername());
		listPm5211.get(index).setMensual(Integer.valueOf(mes));

		if (bModificar) {

			pm5211Repository.save(listPm5211.get(index));
			listPm5211 = this.pm5211Service.aculation(this.getUserDetails().getIdSector());
			listPm5211.get(index).setbGuardra(Boolean.TRUE);
			this.setbCombo(Boolean.FALSE);
			this.setbLbl(Boolean.TRUE);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modifico la información correctamente");
			
		} else {

			listPm5211 = this.pm5211Service.save(listPm5211, index);

			listPmReport = pm5211Service.orderByMendsualAsc(this.getUserDetails().getIdSector());

			this.setbCombo(Boolean.FALSE);
			this.setbLbl(Boolean.TRUE);
			RequestContext.getCurrentInstance().execute("PF('pm5011Wdg').paginator.setPage("
					+ (this.pm5211Repository.findByIdSectorOderByMensual(this.getUserDetails().getIdSector()).size()
							- 1)
					+ ")");

		}

		if (listPm5211.get(index).isbGuardra()) {
			this.banderas(INACTIVO);
			this.bBorrar = Boolean.FALSE;
			this.bAdd = Boolean.FALSE;
			
			bVModficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bMensual = Boolean.TRUE;
			bVSalvar = Boolean.FALSE;

		} else {
			this.banderas(ACTIVO);
			this.bBorrar = Boolean.TRUE;
			this.bAdd = Boolean.TRUE;
			bVModficar = Boolean.FALSE;
			bModificar = Boolean.FALSE;
			this.setbCombo(Boolean.TRUE);
			this.setbLbl(Boolean.FALSE);
			bVSalvar = Boolean.TRUE;

		}

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {

		this.pm5211Service.delete(listPm5211, index);
		listPm5211 = this.pm5211Service.aculation(this.getUserDetails().getIdSector());
		listPmReport = pm5211Service.orderByMendsualAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm5211)) {
			pm5211 = new Pm5211();
			pm5211.setIpr(BigDecimal.ZERO);
			pm5211.setImp(BigDecimal.ZERO);
			pm5211.setDer(BigDecimal.ZERO);
			pm5211.setProd(BigDecimal.ZERO);
			pm5211.setAprov(BigDecimal.ZERO);
			pm5211.setApmej(BigDecimal.ZERO);
			pm5211.setAcc(BigDecimal.ZERO);
			pm5211.setIpf(BigDecimal.ZERO);
			pm5211.setTing(BigDecimal.ZERO);
			pm5211.setMensual(0);
			listPm5211.add(pm5211);

			this.banderas(INACTIVO);
			this.bBorrar = Boolean.TRUE;
			this.bAdd = Boolean.FALSE;

			bVModficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bMensual = Boolean.TRUE;
			bVSalvar = Boolean.FALSE;
		}

	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm5211 = pm5211Repository.getMensual(this.getUserDetails().getIdSector(),
					listPm5211.get(index).getMensual());
			listPm5211.get(index).setIpr(pm5211.getIpr());
			listPm5211.get(index).setImp(pm5211.getImp());
			listPm5211.get(index).setDer(pm5211.getDer());
			listPm5211.get(index).setProd(pm5211.getProd());
			listPm5211.get(index).setAprov(pm5211.getAprov());
			listPm5211.get(index).setApmej(pm5211.getApmej());
			listPm5211.get(index).setAcc(pm5211.getAcc());
			listPm5211.get(index).setIpf(pm5211.getIpf());
			listPm5211.get(index).setTing(pm5211.getTing());
			listPm5211.get(index).setMensual(pm5211.getMensual());
			listPm5211.get(index).setObsgral(pm5211.getObsgral());
		} else {

			listPm5211 = this.pm5211Service.clean(listPm5211, index);
		}

	}

	/**
	 * Cancel.
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {
		listPm5211 = this.pm5211Service.cancel(listPm5211, index);
		this.banderas(INACTIVO);

		this.bAdd = Boolean.FALSE;

		bVModficar = Boolean.TRUE;
		bModificar = Boolean.FALSE;
		bMensual = Boolean.TRUE;
		bVSalvar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		this.setbCombo(Boolean.FALSE);
		this.setbLbl(Boolean.TRUE);

		if (this.pm5211Repository.findByIdSectorOderByMensual(this.getUserDetails().getIdSector()).size() == 0) {
			bModificar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}
	}

	/** The parameters. */
	Map<String, Object> parameters;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("MES", Integer.valueOf(mesReport));
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("nomMunicipio", firmas.getCampo1());
		parameters.put("clave", conctb.getClave());
		parameters.put("fecha", "DEL 1 AL " + getLastDayByAnoEmp(Integer.valueOf(mesReport), conctb.getAnoemp())
				+ " DE " + getMonthName(Integer.valueOf(mesReport)) + " DE " + conctb.getAnoemp().toString());
		parameters.put("L3", firmas.getL3());
		parameters.put("N3", firmas.getN3());
		parameters.put("L4", firmas.getL4());
		parameters.put("N4", firmas.getN4());
		parameters.put("L5", firmas.getL5());
		parameters.put("N5", firmas.getN5());
		return parameters;
	}

	/**
	 * Creates the PDF.
	 */
	public void createPDF() {
		if (pm5211Repository.findByIdSectorOderByMensual(this.getUserDetails().getIdSector()).isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "No hay reportes guardados");
		} else {
			this.createFilePdfInline();
		}

	}

	/**
	 * No empty.
	 *
	 * @param entity the entity
	 * @param index the index
	 * @return the list
	 */
	public List<Pm5211> noEmpty(List<Pm5211> entity, Integer index) {
		entity.get(index).setIpr(null == entity.get(index).getIpr() ? BigDecimal.ZERO : entity.get(index).getIpr());
		entity.get(index).setImp(null == entity.get(index).getImp() ? BigDecimal.ZERO : entity.get(index).getImp());
		entity.get(index).setDer(null == entity.get(index).getDer() ? BigDecimal.ZERO : entity.get(index).getDer());
		entity.get(index).setProd(null == entity.get(index).getProd() ? BigDecimal.ZERO : entity.get(index).getProd());
		entity.get(index)
				.setAprov(null == entity.get(index).getAprov() ? BigDecimal.ZERO : entity.get(index).getAprov());
		entity.get(index)
				.setApmej(null == entity.get(index).getApmej() ? BigDecimal.ZERO : entity.get(index).getApmej());
		entity.get(index).setAcc(null == entity.get(index).getAcc() ? BigDecimal.ZERO : entity.get(index).getAcc());
		entity.get(index).setIpf(null == entity.get(index).getIpf() ? BigDecimal.ZERO : entity.get(index).getIpf());
		entity.get(index).setTing(null == entity.get(index).getTing() ? BigDecimal.ZERO : entity.get(index).getTing());
		entity.get(index).setIpr(null == entity.get(index).getIpr() ? BigDecimal.ZERO : entity.get(index).getIpr());
		entity.get(index).setObsgral(null == entity.get(index).getObsgral() ? "" : entity.get(index).getObsgral());

		return entity;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
