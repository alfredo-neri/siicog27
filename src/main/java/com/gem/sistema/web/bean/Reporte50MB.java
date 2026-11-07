package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm5611;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.dto.RF0092164DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm5611Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.Pm5611Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte50MB.
 */
@ManagedBean
@ViewScoped
public class Reporte50MB extends BaseDirectReport {
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_REPORTE50";
	/** The Constant ACTIVOS. */
	private static final Integer ACTIVOS = 1;
	
	/** The Constant INACTIVOS. */
	private static final Integer INACTIVOS = 2;
	
	/** The Constant SORT_ORDER. */
	private static final Sort SORT_ORDER = new Sort(Direction.ASC, "mensual");
	
	/** The mes. */
	private String mes;
	
	/** The tc mes. */
	private TcMes tcMes;
	
	/** The list tc mes. */
	private List<TcMes> listTcMes;

	/** The conctb. */
	private Conctb conctb;
	
	/** The pm 5611. */
	private Pm5611 pm5611;
	
	/** The select pm 5611. */
	private Pm5611 selectPm5611;

	/** The lis reports. */
	private List<Pm5611> lisReports;

	/** The reporte id. */
	private Integer reporteId;

	/** The b save. */
	private boolean bSave;
	
	/** The b reset. */
	private boolean bReset;
	
	/** The b adicionar. */
	private boolean bAdicionar;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.FALSE;
	
	/** The b cancelar. */
	private boolean bCancelar;
	
	/** The b visible. */
	private boolean bVisible = Boolean.TRUE;
	
	/** The b btn modify. */
	private boolean bBtnModify = Boolean.TRUE;
	
	/** The b modifcar. */
	private boolean bModifcar = Boolean.FALSE;
	
	/** The b lbl mensula. */
	private Boolean bLblMensula;
	
	/** The b mensul. */
	private Boolean bMensul;

	/** The b reset btn. */
	private boolean bResetBtn = Boolean.TRUE;
	
	/** The b mensual. */
	private boolean bMensual = Boolean.TRUE;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;
	
	/**
	 * Gets the select pm 5611.
	 *
	 * @return the select pm 5611
	 */
	public Pm5611 getSelectPm5611() {
		return selectPm5611;
	}

	/**
	 * Sets the select pm 5611.
	 *
	 * @param selectPm5611 the new select pm 5611
	 */
	public void setSelectPm5611(Pm5611 selectPm5611) {
		this.selectPm5611 = selectPm5611;
	}

	/** The listpm 5611. */
	private List<Pm5611> listpm5611;
	
	/** The r F 0092164 DTO. */
	@ManagedProperty("#{rF0092164DTO}")
	private RF0092164DTO rF0092164DTO;

	/** The list RF 0092164 DTO. */
	private List<RF0092164DTO> listRF0092164DTO;

	/** The pm 5611 repository. */
	@ManagedProperty("#{pm5611Repository}")
	private Pm5611Repository pm5611Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

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

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The pm 5611 service. */
	@ManagedProperty("#{pm5611Service}")
	private Pm5611Service pm5611Service;

	/**
	 * Gets the pm 5611 service.
	 *
	 * @return the pm 5611 service
	 */
	public Pm5611Service getPm5611Service() {
		return pm5611Service;
	}

	/**
	 * Sets the pm 5611 service.
	 *
	 * @param pm5611Service the new pm 5611 service
	 */
	public void setPm5611Service(Pm5611Service pm5611Service) {
		this.pm5611Service = pm5611Service;
	}

	/**
	 * Gets the tc mes.
	 *
	 * @return the tc mes
	 */
	public TcMes getTcMes() {
		return tcMes;
	}

	/**
	 * Sets the tc mes.
	 *
	 * @param tcMes the new tc mes
	 */
	public void setTcMes(TcMes tcMes) {
		this.tcMes = tcMes;
	}

	/**
	 * Gets the list tc mes.
	 *
	 * @return the list tc mes
	 */
	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	/**
	 * Sets the list tc mes.
	 *
	 * @param listTcMes the new list tc mes
	 */
	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	/**
	 * Gets the pm 5611.
	 *
	 * @return the pm 5611
	 */
	public Pm5611 getPm5611() {
		return pm5611;
	}

	/**
	 * Sets the pm 5611.
	 *
	 * @param pm5611 the new pm 5611
	 */
	public void setPm5611(Pm5611 pm5611) {
		this.pm5611 = pm5611;
	}

	/**
	 * Gets the listpm 5611.
	 *
	 * @return the listpm 5611
	 */
	public List<Pm5611> getListpm5611() {
		return listpm5611;
	}

	/**
	 * Sets the listpm 5611.
	 *
	 * @param listpm5611 the new listpm 5611
	 */
	public void setListpm5611(List<Pm5611> listpm5611) {
		this.listpm5611 = listpm5611;
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
	 * Gets the pm 5611 repository.
	 *
	 * @return the pm 5611 repository
	 */
	public Pm5611Repository getPm5611Repository() {
		return pm5611Repository;
	}

	/**
	 * Sets the pm 5611 repository.
	 *
	 * @param pm5611Repository the new pm 5611 repository
	 */
	public void setPm5611Repository(Pm5611Repository pm5611Repository) {
		this.pm5611Repository = pm5611Repository;
	}

	/**
	 * Gets the r F 0092164 DTO.
	 *
	 * @return the r F 0092164 DTO
	 */
	public RF0092164DTO getrF0092164DTO() {
		return rF0092164DTO;
	}

	/**
	 * Sets the r F 0092164 DTO.
	 *
	 * @param rF0092164DTO the new r F 0092164 DTO
	 */
	public void setrF0092164DTO(RF0092164DTO rF0092164DTO) {
		this.rF0092164DTO = rF0092164DTO;
	}

	/**
	 * Gets the list RF 0092164 DTO.
	 *
	 * @return the list RF 0092164 DTO
	 */
	public List<RF0092164DTO> getListRF0092164DTO() {
		return listRF0092164DTO;
	}

	/**
	 * Sets the list RF 0092164 DTO.
	 *
	 * @param listRF0092164DTO the new list RF 0092164 DTO
	 */
	public void setListRF0092164DTO(List<RF0092164DTO> listRF0092164DTO) {
		this.listRF0092164DTO = listRF0092164DTO;
	}

	/**
	 * Checks if is b save.
	 *
	 * @return true, if is b save
	 */
	public boolean isbSave() {
		return bSave;
	}

	/**
	 * Sets the b save.
	 *
	 * @param bSave the new b save
	 */
	public void setbSave(boolean bSave) {
		this.bSave = bSave;
	}

	/**
	 * Checks if is b reset.
	 *
	 * @return true, if is b reset
	 */
	public boolean isbReset() {
		return bReset;
	}

	/**
	 * Sets the b reset.
	 *
	 * @param bReset the new b reset
	 */
	public void setbReset(boolean bReset) {
		this.bReset = bReset;
	}

	/**
	 * Checks if is b adicionar.
	 *
	 * @return true, if is b adicionar
	 */
	public boolean isbAdicionar() {
		return bAdicionar;
	}

	/**
	 * Sets the b adicionar.
	 *
	 * @param bAdicionar the new b adicionar
	 */
	public void setbAdicionar(boolean bAdicionar) {
		this.bAdicionar = bAdicionar;
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
	 * Checks if is b cancelar.
	 *
	 * @return true, if is b cancelar
	 */
	public boolean isbCancelar() {
		return bCancelar;
	}

	/**
	 * Sets the b cancelar.
	 *
	 * @param bCancelar the new b cancelar
	 */
	public void setbCancelar(boolean bCancelar) {
		this.bCancelar = bCancelar;
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
	 * Checks if is b btn modify.
	 *
	 * @return true, if is b btn modify
	 */
	public boolean isbBtnModify() {
		return bBtnModify;
	}

	/**
	 * Sets the b btn modify.
	 *
	 * @param bBtnModify the new b btn modify
	 */
	public void setbBtnModify(boolean bBtnModify) {
		this.bBtnModify = bBtnModify;
	}

	/**
	 * Gets the reporte id.
	 *
	 * @return the reporte id
	 */
	public Integer getReporteId() {
		return reporteId;
	}

	/**
	 * Sets the reporte id.
	 *
	 * @param reporteId the new reporte id
	 */
	public void setReporteId(Integer reporteId) {
		this.reporteId = reporteId;
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
	 * Checks if is b reset btn.
	 *
	 * @return true, if is b reset btn
	 */
	public boolean isbResetBtn() {
		return bResetBtn;
	}

	/**
	 * Sets the b reset btn.
	 *
	 * @param bResetBtn the new b reset btn
	 */
	public void setbResetBtn(boolean bResetBtn) {
		this.bResetBtn = bResetBtn;
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
	 * Gets the lis reports.
	 *
	 * @return the lis reports
	 */
	public List<Pm5611> getLisReports() {
		return lisReports;
	}

	/**
	 * Sets the lis reports.
	 *
	 * @param lisReports the new lis reports
	 */
	public void setLisReports(List<Pm5611> lisReports) {
		this.lisReports = lisReports;
	}

	/**
	 * Gets the b lbl mensula.
	 *
	 * @return the b lbl mensula
	 */
	public Boolean getbLblMensula() {
		return bLblMensula;
	}

	/**
	 * Sets the b lbl mensula.
	 *
	 * @param bLblMensula the new b lbl mensula
	 */
	public void setbLblMensula(Boolean bLblMensula) {
		this.bLblMensula = bLblMensula;
	}

	/**
	 * Gets the b mensul.
	 *
	 * @return the b mensul
	 */
	public Boolean getbMensul() {
		return bMensul;
	}

	/**
	 * Sets the b mensul.
	 *
	 * @param bMensul the new b mensul
	 */
	public void setbMensul(Boolean bMensul) {
		this.bMensul = bMensul;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte50";
		endFilename = jasperReporteName + ".pdf";
		findAllByidsectorDatils();
		validate(INACTIVOS);
		bMensual = Boolean.TRUE;
		selectPm5611 = new Pm5611();
		lisReports = pm5611Repository.findAllByidsector(this.getUserDetails().getIdSector(), SORT_ORDER);
		if (CollectionUtils.isNotEmpty(lisReports)) {
			mes = lisReports.get(0).getMensual().toString();
		} else {
			mes = "00";
		}

	}

	/**
	 * Find all byidsector datils.
	 */
	public void findAllByidsectorDatils() {
		pm5611 = new Pm5611();
		bBtnModify = Boolean.FALSE;
		bVisible = Boolean.TRUE;
		this.setbLblMensula(Boolean.TRUE);
		this.setbMensul(Boolean.FALSE);
		listpm5611 = pm5611Repository.findAllByidsector(this.getUserDetails().getIdSector(), SORT_ORDER);
		this.isbResetBtn();
		this.isbBorrar();

		if (CollectionUtils.isEmpty(listpm5611)) {
			pm5611.setObsfism("");
			pm5611.setObsfor("");

			pm5611.setMensual(0);
			listpm5611.add(pm5611);
			bBorrar = Boolean.TRUE;
			bBtnModify = Boolean.FALSE;
			bVisible = Boolean.TRUE;
		}

		listTcMes = tcMesRepository.findAll();
		tcMes = listTcMes.get(0);
		bMensual = Boolean.TRUE;
	}

	/**
	 * Modify.
	 */
	public void modify(Integer index) {
		this.isbResetBtn();
		validate(ACTIVOS);
		tcMes = new TcMes();
		tcMes.setMes(StringUtils.leftPad(listpm5611.get(index).getMensual().toString(), 2 ,"0"));
		this.getListpm5611().get(index).setMes(listpm5611.get(index).getMes());
		this.setbAdicionar(Boolean.TRUE);
		bBtnModify = Boolean.TRUE;
		bVisible = Boolean.FALSE;
		bMensual = Boolean.TRUE;
		bModifcar = Boolean.TRUE;
		this.setbLblMensula(Boolean.FALSE);
		this.setbMensul(Boolean.TRUE);
		this.setbBorrar(Boolean.TRUE);
	}

	/** The pm 5611 entity. */
	Pm5611 pm5611Entity;
	
	/** The mes destino. */
	String mesDestino;
	
	/** The tc. */
	TcMes tc;

	/**
	 * Save.
	 *
	 * @param row the row
	 */
	public void save(Integer row) {
		pm5611 = listpm5611.get(row);

		pm5611.setIdsector(this.getUserDetails().getIdSector());
		pm5611.setCapturo(this.getUserDetails().getUsername());
		pm5611.setFeccap(Calendar.getInstance().getTime());

		pm5611.setIdRef(0l);
		pm5611.setUserid(this.getUserDetails().getUsername());
		pm5611.setMensual(Integer.valueOf(tcMes.getMes()));
		boolean validate = validateTexBox(row);
		pm5611.setObsfism(null == pm5611.getObsfism()?"":pm5611.getObsfism());
		pm5611.setObsfor(null == pm5611.getObsfor()?"":pm5611.getObsfor());
		if (validate) {

			validate = existeMonth(row);
			if (bModifcar) {
				pm5611Repository.save(pm5611);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"La información se modifico correctamente");
				this.validate(INACTIVOS);
				bBtnModify = Boolean.FALSE;
				bVisible = Boolean.TRUE;
				bBorrar = Boolean.FALSE;
				bModifcar = Boolean.FALSE;
				bMensual = Boolean.TRUE;
				this.setbLblMensula(Boolean.TRUE);
				this.setbMensul(Boolean.FALSE);
				this.isbResetBtn();

			} else if (!validate) {
				mesDestino = listpm5611.get(row).getMensual().toString();
				tc = tcMesRepository.findByMes(StringUtils.leftPad(mesDestino, 2, "0"));
				pm5611Entity = new Pm5611();
				

				pm5611Repository.save(pm5611);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Los datos se guardaron de manera correcta");
				this.validate(INACTIVOS);
				bBtnModify = Boolean.FALSE;
				bVisible = Boolean.TRUE;
				bBorrar = Boolean.FALSE;
				bMensual = Boolean.TRUE;
				lisReports = pm5611Repository.findAllByidsector(this.getUserDetails().getIdSector(), SORT_ORDER);
				if (CollectionUtils.isNotEmpty(lisReports)) {
					mes = lisReports.get(0).getMensual().toString();
				} else {
					mes = "00";
				}

				this.setbLblMensula(Boolean.TRUE);
				this.setbMensul(Boolean.FALSE);
				this.isbResetBtn();
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"El mes que intenta guardar ya fue registrado");
			}
		}

	}

	/**
	 * Validate.
	 *
	 * @param opt the opt
	 */
	public void validate(Integer opt) {
		switch (opt) {
		case 1:
			bAdicionar = Boolean.TRUE;
			isbMensual();
			bReset = Boolean.FALSE;

			break;
		case 2:
			bAdicionar = Boolean.FALSE;
			bMensual = Boolean.FALSE;
			bReset = Boolean.TRUE;
			break;
		default:
			break;
		}
	}

	/**
	 * Find month.
	 *
	 * @param row the row
	 */
	public void findMonth(Integer row) {
		if (tcMes.getMes().equals("00") || Integer.valueOf(tcMes.getMes()) > 12) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El mes que intenta ingresar no existe, El mes de ser mayor o igual a 1 y menor o igual a 12");
			RequestContext.getCurrentInstance()
					.execute("$(document.getElementById('form1:objects:" + row + ":mensual')).focus();");
		} else {
			String mh = tcMesRepository.findByMes(tcMes.getMes()).getDescripcion();
			listpm5611.get(row).setMes(mh);
		}

	}

	/**
	 * Delete.
	 *
	 * @param row the row
	 */
	public void delete(Integer row) {
		pm5611 = new Pm5611();
		pm5611 = listpm5611.get(row);
		listpm5611.remove(row);

		pm5611Repository.delete(listpm5611.get(row).getId());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información");
		this.validate(INACTIVOS);
		bBtnModify = Boolean.FALSE;
		bVisible = Boolean.TRUE;
		this.findAllByidsectorDatils();
		lisReports = pm5611Repository.findAllByidsector(this.getUserDetails().getIdSector(), SORT_ORDER);
		if (CollectionUtils.isNotEmpty(lisReports)) {
			mes = lisReports.get(0).getMensual().toString();
		} else {
			mes = "00";
		}
		RequestContext.getCurrentInstance().execute("PF('pm5611Wdg').paginator.setPage(0)");
		isbBorrar();

	}

	/**
	 * Clean.
	 *
	 * @param row the row
	 */
	public void clean(Integer row) {

		if (bModifcar) {
			pm5611 = pm5611Repository.getByIdSectorAndMensual(this.getUserDetails().getIdSector(),
					listpm5611.get(row).getMensual());
			listpm5611.set(row, pm5611);

		} else {

			listpm5611.get(row).setMensual(0);
			listpm5611.get(row).setMes("");
			listpm5611.get(row).setObsfism("");
			listpm5611.get(row).setObsfor("");
			listpm5611.get(row).setFecdep(null);
			listpm5611.get(row).setFecdepfor(null);
			listpm5611.get(row).setMminfism(null);
			listpm5611.get(row).setMminfor(null);
			listpm5611.get(row).setNcbfism(null);
			listpm5611.get(row).setNcbfor(null);

		}

		this.validate(ACTIVOS);

	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		listpm5611 = pm5611Repository.findAllByidsector(this.getUserDetails().getIdSector(), SORT_ORDER);
		bBtnModify = Boolean.FALSE;
		bVisible = Boolean.TRUE;
		bMensual = Boolean.TRUE;
		validate(INACTIVOS);

		this.isbResetBtn();
		bMensual = Boolean.TRUE;
		this.setbLblMensula(Boolean.TRUE);
		this.setbMensul(Boolean.FALSE);
		this.setbBorrar(Boolean.FALSE);
		//RequestContext.getCurrentInstance().execute("PF('pm5611Wdg').paginator.setPage(1)");

	}

	/**
	 * Adds the.
	 *
	 * @param row the row
	 */
	public void add(Integer row) {

		validate(ACTIVOS);
		bMensual = Boolean.FALSE;
		pm5611 = new Pm5611();
		listpm5611.add(row, pm5611);
		this.setbBorrar(Boolean.TRUE);
		bBtnModify = Boolean.TRUE;
		bVisible = Boolean.FALSE;
		this.bResetBtn = Boolean.FALSE;
		this.setbLblMensula(Boolean.FALSE);
		this.setbMensul(Boolean.TRUE);

		listpm5611.get(row).setMes("ENERO");

		RequestContext.getCurrentInstance().execute("PF('pm5611Wdg').paginator.setPage(" + row + ")");
	}

	/** The parameters. */
	Map<String, Object> parameters;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parameters = new HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parameters.put("MES", Integer.valueOf(mes));
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("NoFIRMAS", 3);
		parameters.put("IMAGEN", this.getUserDetails().getPathImgCab1());
		parameters.put("ANO", firmas.getCampo3());
		parameters.put("NOMMUNICIPIO",firmas.getCampo1());
		parameters.put("CLAVE", conctb.getClave());
		parameters.put("N4", firmas.getL4());
		parameters.put("L4", firmas.getN4());
		parameters.put("N5", firmas.getL5());
		parameters.put("L5", firmas.getN5());

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
	 * Generate pdf.
	 */
	public void generatePdf() {
		jasperReporteName = "reporte50";
		endFilename = jasperReporteName + ".pdf";
		this.createFilePdfInline();
	}

	/** The txt. */
	private StreamedContent txt;

	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;
	/**
	 * Gets the txt.
	 *
	 * @return the txt
	 */
	public StreamedContent getTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		parameter.addValue("i_mes", mes);
		parameter.addValue("i_sector", this.getUserDetails().getIdSector());

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return txt;
	}

	/**
	 * Sets the txt.
	 *
	 * @param txt the new txt
	 */
	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	/**
	 * Download txt.
	 */
//	public void downloadTxt() {
//		jasperReporteName = "reporte50_txt";
//		endFilename = jasperReporteName + ".pdf";
//		txt = this.getFileTxt();
//	}

	/**
	 * Validate tex box.
	 *
	 * @param row the row
	 * @return true, if successful
	 */
	public boolean validateTexBox(Integer row) {
		pm5611 = new Pm5611();
		pm5611 = listpm5611.get(row);
		boolean badera = Boolean.TRUE;
		if (null == pm5611.getMensual()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo mensual no debe de estar vacio");
			badera = Boolean.FALSE;
		} else if (pm5611.getMensual().intValue() > 12) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo mensual no debe de ser mayor a 12");
			badera = Boolean.FALSE;
		} else if (!pm5611.getMensual().toString().matches("^[0-9]{1,2}$")) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo mensual solo acepta numero del 1 al 12");
			badera = Boolean.FALSE;
		}
//		} else if (null == pm5611.getNcbfism()) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo No de cuenta FISM es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getNcbfism().intValue() == 0) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo No de cuenta FISM debe de ser mayo a cero");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getNcbfor() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo No de cuenta FORTAMUNDF es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getNcbfism().intValue() == 0) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo No de cuenta FORTAMUNDF es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getMminfism() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Monto ministrado FISM es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getMminfism().compareTo(BigDecimal.ZERO) == 0) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Monto ministrado FISM debe de ser mayor a cero");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getMminfor() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Monto ministrado FORTAMUNDF es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getMminfor().compareTo(BigDecimal.ZERO) == 0) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Monto ministrado FORTAMUNDF debe de ser mayor a cero");
//			badera = Boolean.FALSE;
//		}
//		} else if (pm5611.getFecdep() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo fecha FISM es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getFecdepfor() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo fecha FISM es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getObsfism() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Observaciones FISM es obligatorio");
//			badera = Boolean.FALSE;
//		} else if (pm5611.getObsfor() == null) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Observaciones FORTAMUNDF es obligatorio");
//			badera = Boolean.FALSE;
//		}
		return badera;
	}

	/** The mes find. */
	Integer mesFind;
	
	/** The bandera mes. */
	Integer banderaMes;

	/**
	 * Existe month.
	 *
	 * @param row the row
	 * @return true, if successful
	 */
	public boolean existeMonth(Integer row) {
		mesFind = listpm5611.get(row).getMensual();
		banderaMes = pm5611Repository.existMonth(mesFind, this.getUserDetails().getIdSector());
		if (banderaMes.intValue() > 0) {
			return true;
		} else
			return false;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

}
