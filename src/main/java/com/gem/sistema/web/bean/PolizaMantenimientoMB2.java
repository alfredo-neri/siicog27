package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.PostRemove;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliend;
import com.gem.sistema.business.domain.Polifl;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcRegistraArchivoDigital;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.dto.SelectPagePolideDTO;
import com.gem.sistema.business.predicates.CatfluPredicates;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.predicates.PolidePredicate;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.predicates.PoliflPredicates;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcRegistraArchivoDigitalRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.ConvertToTiffPdfService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.TbProcesoService;
import com.gem.sistema.business.service.reportador.JasperGenericService;
import com.gem.sistema.util.ConstantsClaveEnnum;
import com.gem.sistema.util.UtilJPA;
import com.gem.sistema.web.datamodel.PolideDataModel;
import com.gem.sistema.web.semaphore.ObjectUnlock;
import com.gem.sistema.web.semaphore.PolienUK;
import com.gem.sistema.web.semaphore.Semaphores;
import com.gem.sistema.web.util.FrontProperty;
//import com.gem.sistema.web.util.LockedPolice;
import com.gem.sistema.web.util.GetFileInlineServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaMantenimientoMB2.
 */
@ManagedBean(name = "polizaMantenimientoMB2")
@ViewScoped
public class PolizaMantenimientoMB2 extends AbstractPolizaMantenimiento implements Serializable {

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	@ManagedProperty(value = "#{tbProcesoService}")
	private TbProcesoService procesoService;
	
	private String tipoReporte = "A";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaMantenimientoMB2.class);

	/** The year. */
	private Integer year;

	/** The yeat aux. */
	private Integer yeatAux;

	/** The moth aux. */
	private Integer mothAux;

	/** The conpolflu. */
	private Integer conpolflu;

	/** The mespolflu. */
	private Integer mespolflu;

	/** The renpolflu. */
	private BigDecimal renpolflu;

	/** The id entidad. */
	private Integer idEntidad;

	/** The mes activo. */
	private Integer mesActivo;

	/** The firmas. */
	private Firmas firmas;

	/** The suma 1. */
	private BigDecimal suma1 = BigDecimal.ZERO;

	/** The suma 2. */
	private BigDecimal suma2 = BigDecimal.ZERO;

	/** The suma 3. */
	private BigDecimal suma3 = BigDecimal.ZERO;

	/** The suma 4. */
	private BigDecimal suma4 = BigDecimal.ZERO;

	/** The suma 5. */
	private BigDecimal suma5 = BigDecimal.ZERO;

	/** The suma 6. */
	private BigDecimal suma6 = BigDecimal.ZERO;

	/** The cargoflu. */
	private BigDecimal cargoflu;

	/** The abonoflu. */
	private BigDecimal abonoflu;

	/** The suma ff. */
	private BigDecimal sumaFf = BigDecimal.ZERO;

	/** The canflu. */
	private BigDecimal canflu;

	/** The clvflu. */
	private BigDecimal clvflu;

	/** The renglon. */
	private BigDecimal renglon;

	/** The suma cargo. */
	private String sumaCargo;

	/** The suma abono. */
	private String sumaAbono;

	/** The suma A 8000. */
	private String sumaA8000;

	/** The suma B 8000. */
	private String sumaB8000;

	/** The lock user. */
	private String lockUser;

	/** The valida pass. */
	private String validaPass = "xpoli2005";

	/** The password. */
	private String password;

	/** The cuentaflu. */
	private String cuentaflu;

	/** The id user. */
	private String idUser;

	/** The object unlock. */
	@ManagedProperty(value = "#{objectUnlock}")
	ObjectUnlock objectUnlock;

	/** The semaphores. */
	@ManagedProperty(value = "#{semaphores}")
	private Semaphores semaphores;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
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
	 * Gets the object unlock.
	 *
	 * @return the object unlock
	 */
	public ObjectUnlock getObjectUnlock() {
		return objectUnlock;
	}

	/**
	 * Sets the object unlock.
	 *
	 * @param objectUnlock the new object unlock
	 */
	public void setObjectUnlock(ObjectUnlock objectUnlock) {
		this.objectUnlock = objectUnlock;
	}

	/**
	 * Gets the semaphores.
	 *
	 * @return the semaphores
	 */
	public Semaphores getSemaphores() {
		return semaphores;
	}

	/**
	 * Sets the semaphores.
	 *
	 * @param semaphores the new semaphores
	 */
	public void setSemaphores(Semaphores semaphores) {
		this.semaphores = semaphores;
	}

	/** The order list. */
	private List<Polifl> orderList = new ArrayList<Polifl>();

	/** The polifl 2. */
	private Polifl polifl2;

	/** The list polifl. */
	private List<Polifl> listPolifl;

	/** The select polifl. */
	private Polifl selectPolifl;

	/** Fila seleccionada del catalogo de cuentas. */
	private Cuenta rowSelected;

	/** The polien. */
	private Polien polien;

	/** The polifl. */
	private Polifl polifl;

	/** The list meses. */
	private List<TcMes> listMeses;

	/** The tc mes. */
	private TcMes tcMes;

	/** The polide. */
	private Polide polide;

	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;

	/** The cattpo. */
	private Cattpo cattpo;

	/** The polide select. */
	private Polide polideSelect;

	/**
	 * Mapa para guardar las filas seleccionadas del catalogo de naturaleza de
	 * gasto.
	 */
	private Map<Integer, Cuenta> mapCuentaExp;

	// private StreamedContent streamedContent;

	/** The list incorrectas. */
	private List<Poliend> listIncorrectas;

	/** The convert to tiff pdf service. */
	@ManagedProperty("#{convertToTiffPdfService}")
	private ConvertToTiffPdfService convertToTiffPdfService;

	/**
	 * Gets the convert to tiff pdf service.
	 *
	 * @return the convert to tiff pdf service
	 */
	public ConvertToTiffPdfService getConvertToTiffPdfService() {
		return convertToTiffPdfService;
	}

	/**
	 * Sets the convert to tiff pdf service.
	 *
	 * @param convertToTiffPdfService the new convert to tiff pdf service
	 */
	public void setConvertToTiffPdfService(ConvertToTiffPdfService convertToTiffPdfService) {
		this.convertToTiffPdfService = convertToTiffPdfService;
	}

	/**
	 * Gets the suma 5.
	 *
	 * @return the suma 5
	 */
	public BigDecimal getSuma5() {
		return suma5;
	}

	/**
	 * Gets the suma 6.
	 *
	 * @return the suma 6
	 */
	public BigDecimal getSuma6() {
		return suma6;
	}

	/**
	 * Sets the suma 5.
	 *
	 * @param suma5 the new suma 5
	 */
	public void setSuma5(BigDecimal suma5) {
		this.suma5 = suma5;
	}

	/**
	 * Sets the suma 6.
	 *
	 * @param suma6 the new suma 6
	 */
	public void setSuma6(BigDecimal suma6) {
		this.suma6 = suma6;
	}

	/**
	 * Gets the lock user.
	 *
	 * @return the lock user
	 */
	public String getLockUser() {
		LOGGER.debug(StringUtils.EMPTY);
		LOGGER.debug(StringUtils.EMPTY);
		LOGGER.debug("Lockuser :::  " + lockUser + "   User ::: " + this.getUserDetails().getUsername());
		LOGGER.debug(StringUtils.EMPTY);
		LOGGER.debug(StringUtils.EMPTY);

		return StringUtils.isNotBlank(lockUser) ? lockUser : getUserDetails().getUsername();
	}

	/**
	 * Sets the lock user.
	 *
	 * @param lockUser the new lock user
	 */
	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	/**
	 * Gets the suma 3.
	 *
	 * @return the suma 3
	 */
	public BigDecimal getSuma3() {
		return suma3;
	}

	/**
	 * Gets the suma 4.
	 *
	 * @return the suma 4
	 */
	public BigDecimal getSuma4() {
		return suma4;
	}

	/**
	 * Sets the suma 3.
	 *
	 * @param suma3 the new suma 3
	 */
	public void setSuma3(BigDecimal suma3) {
		this.suma3 = suma3;
	}

	/**
	 * Sets the suma 4.
	 *
	 * @param suma4 the new suma 4
	 */
	public void setSuma4(BigDecimal suma4) {
		this.suma4 = suma4;
	}

	/** The delte bandera. */
	private boolean delteBandera = Boolean.FALSE;

	/**
	 * Gets the cargoflu.
	 *
	 * @return the cargoflu
	 */
	public BigDecimal getCargoflu() {
		return cargoflu;
	}

	/**
	 * Sets the cargoflu.
	 *
	 * @param cargoflu the new cargoflu
	 */
	public void setCargoflu(BigDecimal cargoflu) {
		this.cargoflu = cargoflu;
	}

	/**
	 * Gets the abonoflu.
	 *
	 * @return the abonoflu
	 */
	public BigDecimal getAbonoflu() {
		return abonoflu;
	}

	/**
	 * Sets the abonoflu.
	 *
	 * @param abonoflu the new abonoflu
	 */
	public void setAbonoflu(BigDecimal abonoflu) {
		this.abonoflu = abonoflu;
	}

	/**
	 * Checks if is delte bandera.
	 *
	 * @return true, if is delte bandera
	 */
	public boolean isDelteBandera() {
		return delteBandera;
	}

	/**
	 * Sets the delte bandera.
	 *
	 * @param delteBandera the new delte bandera
	 */
	public void setDelteBandera(boolean delteBandera) {
		this.delteBandera = delteBandera;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the polifl 2.
	 *
	 * @return the polifl2
	 */
	public Polifl getPolifl2() {
		return polifl2;
	}

	/**
	 * Sets the polifl 2.
	 *
	 * @param polifl2 the polifl2 to set
	 */
	public void setPolifl2(Polifl polifl2) {
		this.polifl2 = polifl2;
	}

	/**
	 * Gets the orderlist.
	 *
	 * @return the orderlist
	 */
	public List<Polifl> getOrderlist() {
		return orderList;
	}

	/**
	 * Check catflu.
	 */
	public void checkCatflu() {
		this.setFlowValidationEnable(Boolean.TRUE);
		RequestContext.getCurrentInstance().execute(ROWEDIT_CLVFLU_ROW_JQUERY);
	}

	/**
	 * Adds the action.
	 */
	public void addAction() {

		Polifl lasPolifl = null;

		if (this.orderList != null && !this.orderList.isEmpty()) {
			lasPolifl = this.orderList.get(this.orderList.size() - 1);
		}

		if (lasPolifl == null || (lasPolifl.getId() != null && lasPolifl.getId() > 0)) {
			orderList.add(new Polifl());
			orderList = this.reIndexPolifls(orderList);
		}

		RequestContext.getCurrentInstance()
				.execute(String.format(EDIT_FLOW_ROW_JQUERY, orderList.get(orderList.size() - 1).getIndex())
						+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, orderList.get(orderList.size() - 1).getIndex()));
	}

	/**
	 * Re index polifls.
	 *
	 * @param polifls the polifls
	 * @return the list
	 */
	private List<Polifl> reIndexPolifls(List<Polifl> polifls) {
		int index = 0;
		for (Polifl polifl : polifls) {
			polifl.setIndex(index++);
		}
		return polifls;
	}

	/** The polifl buff. */
	private Polifl poliflBuff;

	/**
	 * Gets the polifl buff.
	 *
	 * @return the polifl buff
	 */
	public Polifl getPoliflBuff() {
		return poliflBuff;
	}

	/**
	 * Sets the polifl buff.
	 *
	 * @param poliflBuff the new polifl buff
	 */
	public void setPoliflBuff(Polifl poliflBuff) {
		this.poliflBuff = poliflBuff;
	}

	/**
	 * On edit init.
	 *
	 * @param event the event
	 */
	public void onEditInit(RowEditEvent event) {
		if (null != event.getObject()) {
			Polifl pl = (Polifl) event.getObject();
			if (null != pl.getId() && pl.getId() != 0) {
				this.poliflBuff = pl;
			}
		}
	}

	/**
	 * On edit.
	 *
	 * @param event the event
	 */
	public void onEdit(RowEditEvent event) {
		Polifl pl = (Polifl) event.getObject();
		if (this.isFlowValidationEnable()) {
			this.setFlowValidationEnable(Boolean.FALSE);
			if (pl != null) {
				if (!this.getCatfluRepository().exists(CatfluPredicates.isLocalidadUnique(
						Double.valueOf(pl.getClvflu().doubleValue()), this.getUserDetails().getIdSector()))) {
					PolizaMantenimientoMB2.displayErrorMsg("Esta clave de flujo no esta registrada");
					RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
							+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
				} else {
					RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
							+ String.format(FOCUS2_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
				}
			}
		} else {
			this.doInsert(pl);
		}
	}

	/**
	 * Do insert.
	 *
	 * @param pl the pl
	 */
	private void doInsert(Polifl pl) {
		if (pl != null) {
			if (pl.getClvflu() != null && pl.getCanflu() != null) {

				if (!this.getCatfluRepository().exists(CatfluPredicates.isLocalidadUnique(
						Double.valueOf(pl.getClvflu().doubleValue()), this.getUserDetails().getIdSector()))) {
					PolizaMantenimientoMB2.displayErrorMsg("Esta clave de flujo no esta registrada");
					RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
							+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
				} else {

					if ((null != this.poliflBuff && pl.getClvflu().compareTo(this.poliflBuff.getClvflu()) == 0)
							|| !this.getPoliflRepository().exists(PoliflPredicates.exist(tippolflu, mespolflu,
									conpolflu, renpolflu, pl.getClvflu(), this.getUserDetails().getIdSector()))) {

						String erroMsg = this.validateFlujo(cargoflu, abonoflu, pl.getClvflu(), pl.getCanflu());
						if (StringUtils.isEmpty(erroMsg)) {
							List<Polifl> polifls = getPoliflRepository()
									.findByMespolAndTippolAndConpolAndRenpolAndIdsector(mespolflu, tippolflu, conpolflu,
											renpolflu, this.getUserDetails().getIdSector());
							BigDecimal suma = this.getSum(polifls, this.poliflBuff);
							suma = suma.add(pl.getCanflu().abs()).abs();
							if (sumaFf.abs().compareTo(suma) >= 0) {
								pl.setAnopol(getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
								pl.setTippol(tippolflu);
								pl.setConpol(conpolflu);
								pl.setMespol(mespolflu);
								pl.setRenpol(renpolflu);
								pl.setUserid(this.getUserDetails().getUsername());
								pl.setIdsector(this.getUserDetails().getIdSector());
								getPoliflRepository().save(pl);
								this.sumaFfBuff = suma;
								if (sumaFf.abs().compareTo(suma) == 0) {
									this.setActivaAdicionar(Boolean.FALSE);
								}
							} else {
								PolizaMantenimientoMB2.displayErrorMsg("La suma es mayor a la registrada");
								this.setActivaAdicionar(Boolean.FALSE);
								RequestContext.getCurrentInstance()
										.execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
												+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
							}
						} else {
							PolizaMantenimientoMB2.displayErrorMsg(erroMsg);
							RequestContext.getCurrentInstance()
									.execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
											+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
						}
					} else {
						PolizaMantenimientoMB2.displayErrorMsg("La clave de flujo ya fue previamente registrada");
						RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
								+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
					}
				}
			} else {
				PolizaMantenimientoMB2.displayErrorMsg("Los valores de Clave o Cantidad no son validos");
				RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
						+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
			}
		}
	}

	/**
	 * Gets the sum.
	 *
	 * @param polifls the polifls
	 * @return the sum
	 */
	public BigDecimal getSum(List<Polifl> polifls) {
		return this.getSum(polifls, null);
	}

	/**
	 * Gets the sum.
	 *
	 * @param polifls    the polifls
	 * @param poliflbuff the poliflbuff
	 * @return the sum
	 */
	public BigDecimal getSum(List<Polifl> polifls, Polifl poliflbuff) {
		BigDecimal toReturn = BigDecimal.ZERO;
		for (Polifl polifl : polifls) {
			if (null == poliflbuff || poliflbuff.getId().longValue() != polifl.getId().longValue()) {
				toReturn = toReturn.add(polifl.getCanflu().abs());
			}
		}
		return toReturn;
	}

	/**
	 * On cell edit.
	 *
	 * @param event the event
	 */
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * On cancel.
	 *
	 * @param event the event
	 */
	public void onCancel(RowEditEvent event) {
		Polifl polifl = (Polifl) event.getObject();

		if (polifl.getId() == null || polifl.getId() == 0) {
			orderList.remove(polifl.getIndex());
		} else {
			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					EDIT_CANCELED + CLVFLU_FORMATTER.format(((Polifl) event.getObject()).getClvflu()));
			FacesContext.getCurrentInstance().addMessage("errorMsg", errorMsg);
		}
	}

	/**
	 * Gets the cuentaflu.
	 *
	 * @return the cuentaflu
	 */
	public String getCuentaflu() {
		return cuentaflu;
	}

	/**
	 * Sets the cuentaflu.
	 *
	 * @param cuentaflu the cuentaflu to set
	 */
	public void setCuentaflu(String cuentaflu) {
		this.cuentaflu = cuentaflu;
	}

	/** The tippolflu. */
	private String tippolflu;

	/**
	 * Gets the canflu.
	 *
	 * @return the canflu
	 */
	public BigDecimal getCanflu() {
		return canflu;
	}

	/**
	 * Sets the canflu.
	 *
	 * @param canflu the new canflu
	 */
	public void setCanflu(BigDecimal canflu) {
		this.canflu = canflu;
	}

	/**
	 * Gets the clvflu.
	 *
	 * @return the clvflu
	 */
	public BigDecimal getClvflu() {
		return clvflu;
	}

	/**
	 * Sets the clvflu.
	 *
	 * @param clvflu the new clvflu
	 */
	public void setClvflu(BigDecimal clvflu) {
		this.clvflu = clvflu;
	}

	/**
	 * Gets the conpolflu.
	 *
	 * @return the conpolflu
	 */
	public Integer getConpolflu() {
		return conpolflu;
	}

	/**
	 * Sets the conpolflu.
	 *
	 * @param conpolflu the new conpolflu
	 */
	public void setConpolflu(Integer conpolflu) {
		this.conpolflu = conpolflu;
	}

	/**
	 * Gets the mespolflu.
	 *
	 * @return the mespolflu
	 */
	public Integer getMespolflu() {
		return mespolflu;
	}

	/**
	 * Sets the mespolflu.
	 *
	 * @param mespolflu the new mespolflu
	 */
	public void setMespolflu(Integer mespolflu) {
		this.mespolflu = mespolflu;
	}

	/**
	 * Gets the renpolflu.
	 *
	 * @return the renpolflu
	 */
	public BigDecimal getRenpolflu() {
		return renpolflu;
	}

	/**
	 * Sets the renpolflu.
	 *
	 * @param renpolflu the new renpolflu
	 */
	public void setRenpolflu(BigDecimal renpolflu) {
		this.renpolflu = renpolflu;
	}

	/**
	 * Gets the tippolflu.
	 *
	 * @return the tippolflu
	 */
	public String getTippolflu() {
		return tippolflu;
	}

	/**
	 * Sets the tippolflu.
	 *
	 * @param tippolflu the new tippolflu
	 */
	public void setTippolflu(String tippolflu) {
		this.tippolflu = tippolflu;
	}

	/**
	 * Gets the polifl.
	 *
	 * @return the polifl
	 */
	public Polifl getPolifl() {
		return polifl;
	}

	/** The bandera fl. */
	private boolean banderaFl;

	/**
	 * Checks if is bandera fl.
	 *
	 * @return true, if is bandera fl
	 */
	public boolean isBanderaFl() {
		return banderaFl;
	}

	/**
	 * Sets the bandera fl.
	 *
	 * @param banderaFl the new bandera fl
	 */
	public void setBanderaFl(boolean banderaFl) {
		this.banderaFl = banderaFl;
	}

	/**
	 * Gets the suma ff.
	 *
	 * @return the suma ff
	 */
	public BigDecimal getSumaFf() {
		return sumaFf;
	}

	/**
	 * Sets the suma ff.
	 *
	 * @param sumaFf the new suma ff
	 */
	public void setSumaFf(BigDecimal sumaFf) {
		this.sumaFf = sumaFf;
	}

	/**
	 * Polifl.
	 *
	 * @return the polifl
	 */
	public Polifl polifl() {
		return polifl;
	}

	/**
	 * Sets the polifl.
	 *
	 * @param polifl the new polifl
	 */
	public void setPolifl(Polifl polifl) {
		this.polifl = polifl;
	}

	/**
	 * Gets the select polifl.
	 *
	 * @return the select polifl
	 */
	public Polifl getSelectPolifl() {
		return selectPolifl;
	}

	/**
	 * Sets the select polifl.
	 *
	 * @param selectPolifl the new select polifl
	 */
	public void setSelectPolifl(Polifl selectPolifl) {
		this.selectPolifl = selectPolifl;
	}

	/**
	 * Gets the list polifl.
	 *
	 * @return the list polifl
	 */
	public List<Polifl> getListPolifl() {
		return listPolifl;
	}

	/**
	 * Sets the list polifl.
	 *
	 * @param listPolifl the new list polifl
	 */
	public void setListPolifl(List<Polifl> listPolifl) {
		this.listPolifl = listPolifl;
	}

	/**
	 * Gets the map cuenta exp.
	 *
	 * @return the map cuenta exp
	 */
	public Map<Integer, Cuenta> getMapCuentaExp() {
		return mapCuentaExp;
	}

	/**
	 * Sets the map cuenta exp.
	 *
	 * @param mapCuentaExp the map cuenta exp
	 */
	public void setMapCuentaExp(Map<Integer, Cuenta> mapCuentaExp) {
		this.mapCuentaExp = mapCuentaExp;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the row selected
	 */
	public Cuenta getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected the new row selected
	 */
	public void setRowSelected(Cuenta rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the list incorrectas.
	 *
	 * @return the list incorrectas
	 */
	public List<Poliend> getListIncorrectas() {
		return listIncorrectas;
	}

	/**
	 * Sets the list incorrectas.
	 *
	 * @param listIncorrectas the new list incorrectas
	 */
	public void setListIncorrectas(List<Poliend> listIncorrectas) {
		this.listIncorrectas = listIncorrectas;
	}
	/*
	 * 
	 * public StreamedContent getStreamedContent() { if (streamedContent != null)
	 * try { streamedContent.getStream().reset(); } catch (IOException e) {
	 * LOGGER.error(e.getMessage(), e); } return streamedContent; }
	 * 
	 * public void setStreamedContent(StreamedContent streamedContent) {
	 * this.streamedContent = streamedContent; }
	 */

	/**
	 * Gets the renglon.
	 *
	 * @return the renglon
	 */
	public BigDecimal getRenglon() {
		return renglon;
	}

	/**
	 * Sets the renglon.
	 *
	 * @param renglon the new renglon
	 */
	public void setRenglon(BigDecimal renglon) {
		this.renglon = renglon;
	}

	/**
	 * Gets the suma cargo.
	 *
	 * @return the suma cargo
	 */
	public String getSumaCargo() {
		return sumaCargo;
	}

	/**
	 * Sets the suma cargo.
	 *
	 * @param sumaCargo the new suma cargo
	 */
	public void setSumaCargo(String sumaCargo) {
		this.sumaCargo = sumaCargo;
	}

	/**
	 * Gets the suma abono.
	 *
	 * @return the suma abono
	 */
	public String getSumaAbono() {
		return sumaAbono;
	}

	/**
	 * Sets the suma abono.
	 *
	 * @param sumaAbono the new suma abono
	 */
	public void setSumaAbono(String sumaAbono) {
		this.sumaAbono = sumaAbono;
	}

	/**
	 * Gets the suma A 8000.
	 *
	 * @return the suma A 8000
	 */
	public String getSumaA8000() {
		return sumaA8000;
	}

	/**
	 * Sets the suma A 8000.
	 *
	 * @param sumaA8000 the new suma A 8000
	 */
	public void setSumaA8000(String sumaA8000) {
		this.sumaA8000 = sumaA8000;
	}

	/**
	 * Gets the suma B 8000.
	 *
	 * @return the suma B 8000
	 */
	public String getSumaB8000() {
		return sumaB8000;
	}

	/**
	 * Sets the suma B 8000.
	 *
	 * @param sumaB8000 the new suma B 8000
	 */
	public void setSumaB8000(String sumaB8000) {
		this.sumaB8000 = sumaB8000;
	}

	/**
	 * Gets the lis cattpo.
	 *
	 * @return the lis cattpo
	 */
	public List<Cattpo> getLisCattpo() {
		return lisCattpo;
	}

	/**
	 * Sets the lis cattpo.
	 *
	 * @param lisCattpo the new lis cattpo
	 */
	public void setLisCattpo(List<Cattpo> lisCattpo) {
		this.lisCattpo = lisCattpo;
	}

	/**
	 * Gets the cattpo.
	 *
	 * @return the cattpo
	 */
	public Cattpo getCattpo() {
		return cattpo;
	}

	/**
	 * Sets the cattpo.
	 *
	 * @param cattpo the new cattpo
	 */
	public void setCattpo(Cattpo cattpo) {
		this.cattpo = cattpo;
	}

	/**
	 * Gets the list meses.
	 *
	 * @return the list meses
	 */
	public List<TcMes> getListMeses() {
		return listMeses;
	}

	/**
	 * Sets the list meses.
	 *
	 * @param listMeses the new list meses
	 */
	public void setListMeses(List<TcMes> listMeses) {
		this.listMeses = listMeses;
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
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the id entidad.
	 *
	 * @return the id entidad
	 */
	public Integer getIdEntidad() {
		return idEntidad;
	}

	/**
	 * Sets the id entidad.
	 *
	 * @param idEntidad the new id entidad
	 */
	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * Gets the polien.
	 *
	 * @return the polien
	 */
	public Polien getPolien() {
		return polien;
	}

	/**
	 * Sets the polien.
	 *
	 * @param polien the new polien
	 */
	public void setPolien(Polien polien) {
		this.polien = polien;
	}

	/**
	 * Gets the polide.
	 *
	 * @return the polide
	 */
	public Polide getPolide() {
		return polide;
	}

	/**
	 * Sets the polide.
	 *
	 * @param polide the new polide
	 */
	public void setPolide(Polide polide) {
		this.polide = polide;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets the mes activo.
	 *
	 * @return the mes activo
	 */
	public Integer getMesActivo() {
		return mesActivo;
	}

	/**
	 * Sets the mes activo.
	 *
	 * @param mesActivo the new mes activo
	 */
	public void setMesActivo(Integer mesActivo) {
		this.mesActivo = mesActivo;
	}

	/**
	 * Removes the list fl.
	 */
	public void removeListFl() {
		if (this.polifl2 != null) {
			if (null != this.getPolifl2().getId() && this.getPolifl2().getId() > 0) {
				this.getPoliflRepository().delete(this.getPolifl2());
			}
			this.orderList.remove(this.getPolifl2().getIndex());
			this.setActivaAdicionar(Boolean.TRUE);
			this.orderList = this.reIndexPolifls(this.orderList);

			this.sumaFfBuff = this.getSum(orderList);
		} else {
			PolizaMantenimientoMB2.displayErrorMsg("Seleccione un renglon para eliminar");
		}
	}

	/**
	 * On exit flow.
	 */
	public void onExitFlow() {
		List<Polifl> polifls = getPoliflRepository().findByMespolAndTippolAndConpolAndRenpolAndIdsector(mespolflu,
				tippolflu, conpolflu, renpolflu, this.getUserDetails().getIdSector());
		BigDecimal suma = this.getSum(polifls);

		if (((null == polifls || polifls.isEmpty())
				&& !(this.cuentaflu.equalsIgnoreCase("1111") || this.cuentaflu.equalsIgnoreCase("1112")))
				|| (sumaFf.compareTo(suma) == 0)) {
			RequestContext.getCurrentInstance().execute("PF('flujoefectivo').hide();");
		} else {
			if ((this.cuentaflu.equalsIgnoreCase("1111") || this.cuentaflu.equalsIgnoreCase("1112"))) {
				if ((sumaFf.compareTo(suma) != 0)) {
					PolizaMantenimientoMB2.displayErrorMsg(
							"La suma no coincide con el valor del concepto " + DEC_FORMAT.format(sumaFf.doubleValue()));
				} else {
					PolizaMantenimientoMB2.displayErrorMsg("Favor de capturar flujo de efectivo");
				}
			} else {

				if (suma.compareTo(BigDecimal.ZERO) == 0) {
					PolizaMantenimientoMB2.displayErrorMsg("Favor de capturar flujo de efectivo");
				} else {
					PolizaMantenimientoMB2.displayErrorMsg(
							"La suma no coincide con el valor del concepto " + DEC_FORMAT.format(sumaFf.doubleValue()));
				}
			}
		}
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		objectUnlock.registerSession();
		year = getDataYearSystemService().getYear(this.getUserDetails().getIdSector());
		polien = getPolienRepository().getByFirstMonth(this.getUserDetails().getIdSector());
		if (null != polien && null != polien.getMespol() && null != polien.getTippol() && null != polien.getConpol()) {

			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug("Mespol :" + polien.getMespol() + "    Tipopol :" + polien.getTippol() + "     Conpol :"
					+ polien.getConpol());
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			this.actualPage = this.getPolienRepository().getPage(polien.getMespol(), polien.getTippol(),
					polien.getConpol(), this.getUserDetails().getIdSector()) - 1;
			//
			this.getPolideDataModel().setPolien(polien);
			this.getPolideDataModel().setInsert(Boolean.FALSE);
			this.getPolideDataModel().setCount(null);
			//
			this.count = this.getPolienRepository().count(PolienPredicates.byTipoMes(polien.getTippol(),
					polien.getMespol(), this.getUserDetails().getIdSector()));
			this.enableButtons(polien);
			TcRegistraArchivoDigital archivoDigital = getExisDigital().getPoliBby(String.valueOf(polien.getMespol()),
					polien.getConpol(), polien.getTippol(), this.getUserDetails().getIdSector());
			if (null != archivoDigital) {
				this.imageId = archivoDigital.getId();

			} else {
				this.imageId = 0l;
			}
			RequestContext.getCurrentInstance().execute("PF('polizasWdg').paginator.setPage(0);");
		} else {
			polien = new Polien();
			this.setEditableTable(Boolean.FALSE);
			this.setActivaAdicionar(Boolean.TRUE);
			this.setActivaModifcar(Boolean.TRUE);
			this.setActivButtons(Boolean.TRUE);
			polien.setConpol(1);
			polien.setAnopol(getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
			polien.setIdsector(this.getUserDetails().getIdSector());
			this.getPolideDataModel().setPolien(polien);
			this.getPolideDataModel().setInsert(Boolean.FALSE);
			this.getPolideDataModel().setCount(null);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "No existen pólizas.");
			this.lastRow = null;
		}
		// }
		listMeses = getTcMesRepository().findByIdLessThanEqual(
				Long.valueOf(getConPolizSirvice().findActiveMonth(this.getUserDetails().getIdSector())));
		lisCattpo = getCattpoRepository().findAll();

		this.actualPage = 0;
		this.isEditableTable();
	}

	/**
	 * On pega load.
	 */
	public void onPegaLoad() {
		idUser = this.getUserDetails().getUsername();
		LOGGER.info("id Users:::: " + idUser);
	}

	/**
	 * Llenalist selec.
	 */
	public void llenalistSelec() {
		polideSelect.setIndex(1);
		polideSelect.setAnopol(getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
		polideSelect.setFecpol(new Date());
	}

	/**
	 * Creates the first row.
	 */
	public void createFirstRow() {
		LOGGER.info("entra a crear una nueva linea....");
		PolienUK cuentaUK = new PolienUK(polien.getAnopol(), polien.getTippol(), polien.getMespol(), polien.getConpol(),
				polien.getIdsector());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		boolean allowToUse = semaphores.lock(cuentaUK, session.getId(), this.getUserDetails().getUsername());
		if (allowToUse) {
			LOGGER.debug("Se puede usar la póliza.");

			if (polien.getFecpol() != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(polien.getFecpol());
				mothAux = c.get(Calendar.MONTH) + 1;
				yeatAux = c.get(Calendar.YEAR);
				if (mothAux == polien.getMespol()) {
					if (yeatAux.intValue() == year.intValue()) {
						Polide selected = null;
						// TODO obtener el renglon dummy de edición del
						// datamodel
						if (this.getPolideDataModel().getCount() > 0) {
							selected = this.getPolideDataModel().getSelected();
						}

						if (null != selected && selected.getId().longValue() == 0) {
							activateRowEdit(selected.getIndex());
						} else {
							selected = new Polide();
							selected.setIndex(this.getPolideDataModel().getLastIndex());
							selected.setAnopol(getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
							selected.setRenpol(this.getPolideDataModel().getLastRenpol());
							this.setPolideSelect(selected);
							if (this.isRepeatConcept()) {
								this.lastRow = this.getPolideDataModel().getLastRow();
								if (this.lastRow != null) {
									selected.setCuenta(this.lastRow.getCuenta());
									selected.setScta(this.lastRow.getScta());
									selected.setSscta(this.lastRow.getSscta());
									selected.setSsscta(this.lastRow.getSsscta());
									selected.setSssscta(this.lastRow.getSssscta());
									selected.setConcep(this.lastRow.getConcep());
									selected.setRefpol(this.lastRow.getRefpol());
									selected.setDn(this.lastRow.getDn());
								}
							}
							// if (this.isRepeatConcept()) {
							// this.activateRowEdit(selected.getIndex(),
							// FOCUS_REFPOL);
							// } else {
							// this.activateRowEdit(selected.getIndex());
							// }

						}
						this.getPolideDataModel().setSelected(selected);
						this.getPolideDataModel().setInsert(Boolean.TRUE);
						RequestContext.getCurrentInstance().execute(
								"PF('polizasWdg').paginator.setPage(PF('polizasWdg').paginator.cfg.pageCount - 1);");
					} else {
						generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
								"El año de la póliza no es igual al de la fecha");
					}

				} else {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
							"El mes de la póliza no es igual al de la fecha");
				}

			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
						"La fecha de generacion de póliza esta vacia.");
			}
		} else {
			LOGGER.debug("No se puede usar la póliza, porque alguien mas la esta usando.");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"No se puede usar la póliza, porque alguien mas la esta usando.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	/**
	 * Delete row.
	 */
	public void deleteRow() {
		LOGGER.info("entra a crear una nueva linea....");
		if (this.getPolideSelect() != null) {
			Polide selected = this.getPolideSelect();
			StringBuilder js = new StringBuilder();
			if (selected.getId() != null && selected.getId() > 0) {

				if (PolizaMantenimientoMB2.isFEAcc(selected.getCuenta())) {
					List<Polifl> polifls = this.getPoliflRepository()
							.findByMespolAndTippolAndConpolAndRenpolAndIdsector(selected.getMespol(),
									selected.getTippol(), selected.getConpol(), selected.getRenpol(),
									this.getUserDetails().getIdSector());
					for (Polifl polifl : polifls) {
						this.getPoliflRepository().delete(polifl.getId());
					}
				}

				this.getPolideRepository().delete(selected);
				this.getPolideDataModel().getListPolide().remove(selected.getIndex());
				this.getPolideDataModel().setCount(this.getPolideDataModel().getCount() - 1);
				if (CollectionUtils.isEmpty(this.getPolideDataModel().getListPolide())) {
					this.getPolideDataModel().setCount(null);
					this.getPolideDataModel().setSelected(null);
					this.getPolideDataModel().setGoToPage(PolideDataModel.PREV_PAGE);
					js.append("PF('polizasWdg').filter();");
					// RequestContext.getCurrentInstance().execute();
				}

			}
			js.append("PF('polizasWdg').unselectAllRows();");
			RequestContext.getCurrentInstance().execute(js.toString());

			this.persistencePolizaOnDeleteRow();
			generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Registro elimado correctamente ");
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Debe seleccionar un renglón para eliminar. ");
		}

	}

	/**
	 * Muestra un mensaje al cancelar la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowCancel(final RowEditEvent event) {
		LOGGER.info(":: Cancelar edicion ");
		// TODO CALCELA LA EDICION y quita el renglon dummy
		this.getPolideDataModel().setInsert(Boolean.FALSE);
		this.getPolideDataModel().setSelected(null);
		this.getPolideDataModel().setCount(null);
		this.nomSelectedConcept = StringUtils.EMPTY;
		this.nomSelectedAcc = StringUtils.EMPTY;
		this.polideSelect = null;
		if (this.getPolideDataModel().getActualPage().isFirst()
				|| (this.getPolideDataModel().getListPolide().size() == 1
						&& ((Polide) event.getObject()).getId() == 0)) {
			RequestContext.getCurrentInstance().execute("PF('polizasWdg').filter();");
		}
	}

	/**
	 * Append status.
	 *
	 * @param sb    the sb
	 * @param field the field
	 * @return the string builder
	 */
	private StringBuilder appendStatus(StringBuilder sb, String field) {
		if (StringUtils.isNotEmpty(field)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		return sb;
	}

	/**
	 * Checks if is valid capture.
	 *
	 * @param account the account
	 * @return true, if is valid capture
	 */
	private boolean isValidCapture(Polide account) {
		StringBuilder sb = new StringBuilder();
		sb = this.appendStatus(sb, account.getCuenta());
		sb = this.appendStatus(sb, account.getScta());
		sb = this.appendStatus(sb, account.getSscta());
		sb = this.appendStatus(sb, account.getSsscta());
		sb = this.appendStatus(sb, account.getSssscta());
		sb = this.appendStatus(sb, account.getConcep());
		return sb.toString().matches("1((00001)|(10001)|(11001)|(11101)|(11111))");
	}

	/** The error msg. */
	private StringBuilder errorMsg;

	/** The polide buff. */
	private Polide polideBuff;

	/** The id buff udp. */
	private Integer idBuffUdp;

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onInitRowEdit(final RowEditEvent event) {
		if (null != event.getObject()) {
			Polide polide = (Polide) event.getObject();
			if (null != polide.getId() && polide.getId() != 0) {
				this.polideBuff = polide;
				this.rowEditFlag = UPDATE_POLIDE;
				this.idBuffUdp = polide.getIndex();
			} else {
				this.rowEditFlag = StringUtils.EMPTY;
			}
		}
	}

	/** The error stack. */
	private List<String> errorStack = new ArrayList<String>();

	/** The Constant DN_REGEXP. */
	private static final String DN_REGEXP = "^[ETSFIPGCDANRVOBLZYWX]$";

	/**
	 * Validate format.
	 *
	 * @param polide the polide
	 * @return true, if successful
	 */
	private boolean validateFormat(Polide polide) {
		if (StringUtils.isBlank(polide.getConcep())) {
			this.errorStack.add("El campo Concepto es requerido");
		}
		if (StringUtils.isBlank(polide.getRefpol())) {
			this.errorStack.add("El campo RefPol es requerido y sólo permite números");
		}
		if (StringUtils.isBlank(polide.getDn()) || !polide.getDn().matches(DN_REGEXP)) {
			this.errorStack.add("El campo Dn sólo permite E,T,S,F,I,P,G,C,D,A,N,R,V,O,B,L,Z,Y,W,X");
		}
		return CollectionUtils.isEmpty(this.errorStack);
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		Polide catalog = (Polide) event.getObject();
		if (UPDATE_POLIDE.equalsIgnoreCase(this.rowEditFlag)) {

			errorMsg = new StringBuilder();
			if (this.validateFormat(catalog) && this.validate(catalog, errorMsg)) {
				this.polideBuff = this.getPolideRepository().findOne(this.polideBuff.getId());
				this.getPolideRepository().save(catalog);
				// TODO RECALCULA LAS SUMATORIAS Y ESTATUS
				persistencePoliza2WithFlow(catalog);
				if (this.isBandera()) {
					List<Polifl> polifls = this.getPoliflRepository()
							.findByMespolAndTippolAndConpolAndRenpolAndIdsector(this.polideBuff.getMespol(),
									this.polideBuff.getTippol(), this.polideBuff.getConpol(),
									this.polideBuff.getRenpol(), this.getUserDetails().getIdSector());

					for (Polifl polifl : polifls) {
						polifl.setRenpol(catalog.getRenpol());
						this.getPoliflRepository().save(polifl);
					}

					this.orderList = getPoliflRepository().findByMespolAndTippolAndConpolAndRenpolAndIdsector(mespolflu,
							tippolflu, conpolflu, renpolflu, this.getUserDetails().getIdSector());
					this.sumaFfBuff = this.getSum(this.orderList);
					this.orderList = this.reIndexPolifls(this.orderList);
					RequestContext.getCurrentInstance().execute("PF('ffConfirmWdt').show();");
				} else {
					RequestContext.getCurrentInstance().execute("executeClickHideButton();");
				}
			} else {
				RequestContext.getCurrentInstance().execute("executeClickHideButton();");
			}
		} else {
			if (FIND_ACC_NAME.equalsIgnoreCase(this.rowEditFlag)) {
				this.inmediato = Boolean.FALSE;
				this.rowEditFlag = StringUtils.EMPTY;
				Cuenta findedAcc = this.getCuentaRepository()
						.findOne(CuentaPredicates.findByAccounCompositeInlcudeEmpty(this.getAccFromPolide(catalog)));
				if (null != findedAcc) {
					catalog.setConcep(findedAcc.getNomcta());
					this.polideSelect.setConcep(findedAcc.getNomcta());
					this.activateRowEdit(catalog.getIndex());
				}
			} else {
				LOGGER.info(":: Editando Fila salaryScaleManualMB  " + catalog.getIndex() + "::" + catalog);
				errorMsg = new StringBuilder();
				if (this.validateFormat(catalog) && this.validate(catalog, errorMsg)) {
					createRow();
					RequestContext.getCurrentInstance().execute("jQuery('#form1\\\\:hideButton3').click();");
					return;
				}
				RequestContext.getCurrentInstance().execute("executeClickHideButton();");
			}
		}
	}

	/** The Constant TITLE_INSERT_REGEXP. */
	private static final String TITLE_INSERT_REGEXP = FrontProperty
			.getPropertyValue("catalog.message.title.insert.regexp");

	/**
	 * Checks if is valid account.
	 *
	 * @param polide   the polide
	 * @param errorMsg the error msg
	 * @return true, if is valid account
	 */
	private boolean isValidAccount(Polide polide, StringBuilder errorMsg) {
		Boolean toReturn = !StringUtils.isEmpty(polide.getCuenta()) && !polide.getCuenta().matches("(0-9){4}");
		if (toReturn) {

			if (polide.getId() == null || polide.getId() == 0) {
				BigDecimal maxpol = this.getPolideDataModel().getLastRenpol();
				if (polide.getRenpol().compareTo(maxpol) != 0) {
					if (polide.getRenpol().compareTo(maxpol) > 0) {
						errorMsg.append("El número del renglón no puede ser mayor al máximo de renglones de la póliza");
					} else {
						if (getPolideRepository()
								.exists(PolidePredicate.findOneComposite(polien.getTippol(), polien.getMespol(),
										polien.getConpol(), polide.getRenpol(), this.getUserDetails().getIdSector()))) {
							errorMsg.append("El número del renglón ya esta asignado en otro concepto");
						}
					}
				}
			} else {
				Polide buff = this.getPolideRepository().findOne(polide.getId());
				if (polide.getRenpol().intValue() != buff.getRenpol().intValue()) {
					BigDecimal maxpol = this.getPolideDataModel().getLastRenpol();
					if (polide.getRenpol().compareTo(maxpol) > 0) {
						errorMsg.append("El número del renglón no puede ser mayor al máximo de renglones de la póliza");
					} else {
						if (getPolideRepository()
								.exists(PolidePredicate.findOneComposite(polien.getTippol(), polien.getMespol(),
										polien.getConpol(), polide.getRenpol(), this.getUserDetails().getIdSector()))) {
							errorMsg.append("El numero del renglon ya esta asignado en otro concepto");
						}
					}
				}
			}

			if (!StringUtils.isEmpty(polide.getScta())) {
				if (!polide.getScta().matches("([0-9a-zA-Z]){10}")) {
					errorMsg.append("La Scta no cumple con el formato. ");
				}
			}

			if (!StringUtils.isEmpty(polide.getSscta())) {
				if (!polide.getSscta().matches("([0-9a-zA-Z]){15}")) {
					errorMsg.append("La Sscta no cumple con el formato. ");
				}
			}

			if (!StringUtils.isEmpty(polide.getSsscta())) {
				if (!polide.getSsscta().matches("([0-9a-zA-Z]){4}")) {
					errorMsg.append("La Ssscta no cumple con el formato. ");
				}
			}

			if (!StringUtils.isEmpty(polide.getSssscta())) {
				if (!polide.getSssscta().matches("([0-9a-zA-Z]){3}")) {
					errorMsg.append("La Sssscta no cumple con el formato. ");
				}
			}
			if (!(!StringUtils.isEmpty(polide.getConcep()) && polide.getConcep().matches(TITLE_INSERT_REGEXP))) {
				errorMsg.append("El Concepto no puede ser vacío o no cumple con el formato. ");
			}

			if (!((null != polide.getCanpol() && null != polide.getCanpolh())
					&& (polide.getCanpolh().doubleValue() != 0 || polide.getCanpol().doubleValue() != 0))) {
				errorMsg.append("El cargo o el abono no son valores válidos. ");
			} else {
				if (!((polide.getCanpolh().doubleValue() != 0 && polide.getCanpol().doubleValue() == 0)
						|| (polide.getCanpolh().doubleValue() == 0 && polide.getCanpol().doubleValue() != 0))) {
					errorMsg.append(POLIZA_CARGO_ABONO_MSG);
				}
			}

		} else {
			errorMsg.append("La Cuenta no cumple con el formato. ");
		}

		return !(errorMsg.length() > 0);
	}

	/**
	 * Exist account last level.
	 *
	 * @param polide   the polide
	 * @param errorMsg the error msg
	 * @return true, if successful
	 */
	private boolean existAccountLastLevel(Polide polide, StringBuilder errorMsg) {
		return this.getCuentaRepository().exists(CuentaPredicates.findByPolideCompositeLastLevelInlcudeEmpty(polide,
				this.getUserDetails().getIdSector()));
	}

	/**
	 * Exist account.
	 *
	 * @param polide   the polide
	 * @param errorMsg the error msg
	 * @return true, if successful
	 */
	private boolean existAccount(Polide polide, StringBuilder errorMsg) {
		return this.getCuentaRepository().exists(
				CuentaPredicates.findByPolideCompositeInlcudeEmpty(polide, this.getUserDetails().getIdSector()));
	}

	/**
	 * Validate.
	 *
	 * @param catalog  the catalog
	 * @param errorMsg the error msg
	 * @return true, if successful
	 */
	private boolean validate(Polide catalog, StringBuilder errorMsg) {
		if (null != catalog) {
			if (this.isValidCapture(catalog)) {

				if (this.getAccountService().is5xAccount(catalog.getCuenta())) {
					catalog.setScta(getAccountService().fillRightZeros(catalog.getScta(), LENGTH_SECOND_LEVEL));
					catalog.setSscta(getAccountService().fillRightZeros(catalog.getSscta(), LENGTH_THIRD_LEVEL));
					catalog.setSsscta(getAccountService().fillRightZeros(catalog.getSsscta(), LENGTH_FOUR_LEVEL));
				} else {
					catalog.setScta(getAccountService().fillZeros(catalog.getScta(), LENGTH_SECOND_LEVEL));
					catalog.setSscta(getAccountService().fillZeros(catalog.getSscta(), LENGTH_THIRD_LEVEL));
					catalog.setSsscta(getAccountService().fillZeros(catalog.getSsscta(), LENGTH_FOUR_LEVEL));
				}

				if (null == catalog.getCanpol()) {
					catalog.setCanpol(BigDecimal.ZERO);
				}
				if (null == catalog.getCanpolh()) {
					catalog.setCanpolh(BigDecimal.ZERO);
				}

				if (this.isValidAccount(catalog, errorMsg)) {

					if (!this.existAccount(catalog, errorMsg)) {
						errorMsg.append("No existe la cuenta del concepto capturado.");
						return Boolean.FALSE;
					} else {
						if (!this.existAccountLastLevel(catalog, errorMsg)) {
							errorMsg.append("La cuenta capturada no es de ultimo nivel.");
							return Boolean.FALSE;
						}
					}
				} else {
					return Boolean.FALSE;
				}
			} else {
				errorMsg.append("Favor de capturar los datos en orden de Cuenta, Scta, Sscta, Ssscta, Sssscta.");
				return Boolean.FALSE;
			}
		} else {
			errorMsg.append("Error al editar el detalle de póliza. ");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/** The last row. */
	Polide lastRow;

	/**
	 * Gets the last row.
	 *
	 * @return the lastRow
	 */
	public Polide getLastRow() {
		return lastRow;
	}

	/**
	 * Sets the last row.
	 *
	 * @param lastRow the lastRow to set
	 */
	public void setLastRow(Polide lastRow) {
		this.lastRow = lastRow;
	}

	/**
	 * Creates the row.
	 */
	public void createRow() {
		LOGGER.info("entra a crear una nueva linea....");
		LOGGER.info("entra a crear una nueva linea....");
		// int index = listPolide.isEmpty() ? 0 : listPolide.size() - 1;
		Polide selected = this.getPolideDataModel().getSelected();
		// int rowsAdded = 0;
		if (CollectionUtils.isNotEmpty(this.getPolideDataModel().getListPolide())) {
			selected = this.persistencePolide3(selected);
			// rowsAdded++;
			// listPolide.add(selected);
			List<Polide> adds = this.getCuentaAdicionalService().getAdds(selected, this.getUserDetails().getIdSector());
			if (!CollectionUtils.isEmpty(adds)) {
				for (Polide polide : adds) {
					polide.setRenpol(this.getPolideDataModel().getLastRenpol());
					polide = this.persistencePolide3(polide);
					// rowsAdded++;
					this.getPolideDataModel().getListPolide().add(polide);
				}
				// this.listPolide = this.reIndexWithoutRenpol(listPolide);
			}
			this.persistencePoliza2WithFlow(selected);
		}

		this.lastRow = this.getPolideDataModel().getLastRow();
		selected = new Polide();
		if (this.isRepeatConcept() && this.lastRow != null) {
			selected.setCuenta(this.lastRow.getCuenta());
			selected.setScta(this.lastRow.getScta());
			selected.setSscta(this.lastRow.getSscta());
			selected.setSsscta(this.lastRow.getSsscta());
			selected.setSssscta(this.lastRow.getSssscta());
			selected.setConcep(this.lastRow.getConcep());
			selected.setRefpol(this.lastRow.getRefpol());
			selected.setDn(this.lastRow.getDn());
			// selected.setRenpol(this.getPolideDataModel().getSelected().getIndex() + 1);
		}
		selected.setIndex(this.getPolideDataModel().getListPolide().isEmpty() ? 0
				: this.getPolideDataModel().getListPolide().size());
		selected.setAnopol(this.getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
		selected.setRenpol(this.getPolideDataModel().getListPolide().isEmpty() ? BigDecimal.ONE
				: this.getPolideDataModel().getLastRenpol());
		// this.listPolide.add(selected.getIndex(), selected);
		// this.listPolide = this.reIndexWithoutRenpol(listPolide);
		this.getPolideDataModel().setSelected(selected);
		this.getPolideDataModel().getListPolide().add(selected);
		if ((this.getPolideDataModel().getListPolide().size()) > 20) {
			this.getPolideDataModel().setCount(null);
			this.getPolideDataModel().setGoToPage(PolideDataModel.NEXT_PAGE);
			this.lastIndex = (this.getPolideDataModel().getListPolide().size() - 20) - 1;
			final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("form1:objects");
			int first = 1;
			if (d.getRowCount() % 20 == 0) {
				first = (d.getRowCount() - 20);
			} else {
				first = (d.getRowCount() / 20) * 20;
			}
			d.setFirst(first);
		}
	}

	/** The last index. */
	int lastIndex = 0;

	/**
	 * Persistence polide 3.
	 *
	 * @param pol the pol
	 * @return the polide
	 */
	private Polide persistencePolide3(Polide pol) {
		LOGGER.info("pol: " + ToStringBuilder.reflectionToString(pol));
		pol.setAnopol(this.getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
		pol.setIdsector(this.getUserDetails().getIdSector());
		pol.setUserid(this.getUserDetails().getUsername());
		pol.setMespol(polien.getMespol());
		pol.setTippol(polien.getTippol());
		pol.setConpol(polien.getConpol());
		LOGGER.info("det: " + pol);
		pol = this.getPolideRepository().save(pol);
		return pol;
	}

	/**
	 * Activa el modo de edicion en una fila.
	 * 
	 * @param index fila a ser activada.
	 */
	/**
	 * @param index
	 */
	public void activateRowEdit(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		// text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/**
	 * Activa el modo de edicion en una fila.
	 * 
	 * @param index fila a ser activada.
	 */
	/**
	 * @param index
	 */
	public void activateRowEditOnInsert(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		// text.append("PF('polizasWdg').filter();PF('polizasWdg').paginator.setPage(PF('polizasWdg').paginator.cfg.pageCount
		// - 1);");
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/**
	 * Activate row edit.
	 *
	 * @param index   the index
	 * @param focusJs the focus js
	 */
	public void activateRowEdit(final int index, String focusJs) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(focusJs, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/** The render pdf. */
	private boolean renderPdf = Boolean.FALSE;

	/**
	 * Checks if is render pdf.
	 *
	 * @return true, if is render pdf
	 */
	public boolean isRenderPdf() {
		return renderPdf;
	}

	/**
	 * Sets the render pdf.
	 *
	 * @param renderPdf the new render pdf
	 */
	public void setRenderPdf(boolean renderPdf) {
		this.renderPdf = renderPdf;
	}

	/** The file pdf path. */
	private String filePdfPath = "/tmp/m-dpolizasd.pdf";

	/**
	 * Gets the file pdf path.
	 *
	 * @return the file pdf path
	 */
	public String getFilePdfPath() {
		return filePdfPath;
	}

	/**
	 * Sets the file pdf path.
	 *
	 * @param filePdfPath the new file pdf path
	 */
	public void setFilePdfPath(String filePdfPath) {
		this.filePdfPath = filePdfPath;
	}

	/**
	 * Validate.
	 *
	 * @return true, if successful
	 */
	private boolean validate() {
		return Boolean.TRUE;
	}

	/*
	 * public void buttonAction(ActionEvent actionEvent) { if (validate()) {
	 * this.setRenderPdf(Boolean.TRUE);
	 * 
	 * try { this.filePdfPath =
	 * this.getPolizaService().savePDFFile(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
	 * "escudo_ecatepec.png", polien.getTippol(), polien.getMespol(),
	 * polien.getConpol(), polien.getConpol(), getUserDetails().getIdSector(),
	 * this.getUserDetails().getUsername()); } catch (NumberFormatException |
	 * IOException e) { generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
	 * "Error al generar el archivo PDF."); } } else {
	 * generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
	 * "Error al capturar los parametros."); } }
	 */

	/** The uuid. */
	protected String uuid = null;

	/** The end filename. */
	protected String endFilename = null;

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the uuid.
	 *
	 * @param uuid the new uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gets the end filename.
	 *
	 * @return the end filename
	 */
	public String getEndFilename() {
		return endFilename;
	}

	/**
	 * Sets the end filename.
	 *
	 * @param endFilename the new end filename
	 */
	public void setEndFilename(String endFilename) {
		this.endFilename = endFilename;
	}

	/** The jasper generic service. */
	@ManagedProperty(value = "#{jasperGenericServiceImpl}")
	protected JasperGenericService jasperGenericService;

	/**
	 * Gets the jasper generic service.
	 *
	 * @return the jasper generic service
	 */
	public JasperGenericService getJasperGenericService() {
		return jasperGenericService;
	}

	/**
	 * Sets the jasper generic service.
	 *
	 * @param jasperGenericService the new jasper generic service
	 */
	public void setJasperGenericService(JasperGenericService jasperGenericService) {
		this.jasperGenericService = jasperGenericService;
	}

	/**
	 * Creates the file pdf.
	 */
	public void createFilePdf() {
		if (validate() && this.getPolideDataModel().getRowCount() > 0) {
			TrPuestoFirma firma = null;
			Integer idSector = this.getUserDetails().getIdSector();
			uuid = UUID.randomUUID().toString() + "_";
			String jasperReporteName = "";
			if (tipoReporte.equals("A"))
				jasperReporteName = "print_policy";
			else
				jasperReporteName = "print_policyRef";
			endFilename = "Poliza.pdf";
			Cattpo cattpo = cattpoRepositry.findByTippol(polien.getTippol());
			Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

			Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();

			paramsReport.put("pImg", this.getUserDetails().getPathImgCab1());
			paramsReport.put("pImg2", this.getUserDetails().getPathImgCab2());
			paramsReport.put("pTipoPolizaName", cattpo.getTipnom().toUpperCase());
			paramsReport.put("pTipoPoliza", polien.getTippol());
			paramsReport.put("pMesPoliza", polien.getMespol());
			paramsReport.put("pConsecutivoPolizaMin", polien.getConpol());
			paramsReport.put("pConsecutivoPolizaMax", polien.getConpol());
			paramsReport.put("pSector", getUserDetails().getIdSector());
			paramsReport.put("pUser", this.getUserDetails().getUsername());
			String CLAVE_REPORT = "RPT_IMPR_POL";

			TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

			endFilename = getNameFileReport(cattpo.getTipnom().toUpperCase() + " ", polien.getMespol(), conctb,
					reporte);

			paramsReport.put("pImg", conctb.getImagePathRigth());
			paramsReport.put("pImg2", conctb.getImagePathLeft());
			paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

			if (this.getUserDetails().getIdSector() == 2) {
				// Optiene el path de la vista que invoco la construcion del reporte
				HttpServletRequest servletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
						.getExternalContext().getRequest();
				this.procesoService.start("POLIZAS DE CHEQUES", this.getUserDetails().getUsername(),
						servletRequest.getRequestURI());
			}

			try {
				jasperGenericService.createReportPdftoFs(paramsReport, jasperReporteName,
						GetFileInlineServlet.getFullPath(uuid, endFilename));
			} catch (final Exception genericExc) {
				uuid = null;
				LOGGER.error("createFilePdfInline", genericExc);
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al generar el archivo PDF.");
			}
		} else {
			uuid = null;
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al capturar los parametros.");
		}

	}

	/**
	 * Poliza digital.
	 */
	public void polizaDigital() {
		if (validate() && this.getPolideDataModel().getRowCount() > 0) {
			uuid = UUID.randomUUID().toString() + "_";

			endFilename = "Poliza.pdf";

			try {
				GetFileInlineServlet.getFullPath(uuid, endFilename);
			} catch (final Exception genericExc) {
				uuid = null;
				LOGGER.error("createFilePdfInline", genericExc);
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al generar el archivo PDF.");
			}
		} else {
			uuid = null;
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al capturar los parametros.");
		}

	}

	/**
	 * Update view rows auto generated.
	 */
	public void updateViewRowsAutoGenerated() {
		LOGGER.info(":: Actualiza los registros autogenerados ");
		if ((null == this.errorMsg || StringUtils.isEmpty(this.errorMsg.toString()))
				&& CollectionUtils.isEmpty(errorStack)) {
			if (!UPDATE_POLIDE.equalsIgnoreCase(this.rowEditFlag)) {
				if (this.isRepeatConcept()) {
					this.activateRowEdit(this.getPolideDataModel().getListPolide().size() - 1, FOCUS_REFPOL);
				} else {
					this.activateRowEditOnInsert(this.getPolideDataModel().getSelected().getIndex());
				}
			} else {
				generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Registro editado");
			}
			this.rowEditFlag = StringUtils.EMPTY;
		} else {
			if (CollectionUtils.isNotEmpty(errorStack)) {
				for (String string : this.errorStack) {
					generateNotificationFront(SEVERITY_ERROR, string, string);
				}
				this.errorStack.clear();
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMsg.toString());
			}
			if (UPDATE_POLIDE.equalsIgnoreCase(this.rowEditFlag)) {
				this.activateRowEdit(this.polideSelect.getIndex());
			} else {
				this.activateRowEdit(this.getPolideDataModel().getListPolide().size() - 1);
			}

			this.rowEditFlag = StringUtils.EMPTY;
			return;
		}
		if (isBandera() == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute("PF('flujoefectivo').show();");
			setBandera(Boolean.FALSE);
		}

	}

	/** The me. */
	private Integer me;

	/**
	 * Gets the me.
	 *
	 * @return the me
	 */
	public Integer getMe() {
		return me;
	}

	/**
	 * Sets the me.
	 *
	 * @param me the new me
	 */
	public void setMe(Integer me) {
		this.me = me;
	}

	/**
	 * Find wrong policy.
	 */
	public void findWrongPolicy() {
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("Consulta Polizas incorrectas" + me);
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		try {
			listIncorrectas = getConPolizSirvice().wrongPolicy(me, this.getUserDetails().getIdSector());
			if (listIncorrectas.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"No existen pólizas incorrectas para el mes que intenta buscar"));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"No existen pólizas incorrectas para el mes que intenta buscar"));
		}

	}

	/**
	 * Default zero.
	 */
	public void defaultZero() {
		if (polien.getCdebe() == null) {
			polien.setCdebe(BigDecimal.ZERO);
		} else if (polien.getChaber() == null) {
			polien.setChaber(BigDecimal.ZERO);
		}
	}

	/**
	 * Enable buttons.
	 *
	 * @param encabezado the encabezado
	 */
	public void enableButtons(Polien encabezado) {
		mesActivo = getConPolizSirvice().isPoliceActive(encabezado.getMespol(), this.getUserDetails().getIdSector());

		boolean isAffectedMonth = this.getPolienRepository().count(PolienPredicates
				.byMesAfectado(encabezado.getMespol(), this.getUserDetails().getIdSector(), AFFECTED_POLICY_ST)) > 0;
		if (isAffectedMonth) {
			generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "El mes de la póliza ya fue afectado");
		}

		PolienUK polienUK = new PolienUK(encabezado.getAnopol(), encabezado.getTippol(), encabezado.getMespol(),
				encabezado.getConpol(), encabezado.getIdsector());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		boolean allowToUse = semaphores.isAllowedToUse(polienUK, this.getUserDetails().getUsername());

		if (!allowToUse) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Esta Póliza está siendo ocupada por otro usuario");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		if ((this.getUserDetails().getUsername().equals(encabezado.getCappol()) && 0 < mesActivo
				&& encabezado.getStaafe().equalsIgnoreCase("N")) && allowToUse /* & checkLockPolice() */) {
			this.setActivButtons(Boolean.FALSE);
			this.setEditableTable(Boolean.TRUE);
			this.setActivaAdicionar(Boolean.TRUE);
			this.setActivaModifcar(Boolean.FALSE);
		} else {

			this.setEditableTable(Boolean.FALSE);
			this.setActivaAdicionar(Boolean.TRUE);
			this.setActivaModifcar(Boolean.TRUE);
			this.setActivButtons(Boolean.TRUE);

		}

		/*
		 * if (!isActivButtons() && isEditableTable()) { lockPolice(); }
		 */

	}

	/**
	 * Find police.
	 */
	public void findPolice() {
		LOGGER.info("entra a consulta el detalle");
		LOGGER.info("tipo::::::: " + polien.getTippol());
		LOGGER.info("mes::::::: " + polien.getMespol());
		LOGGER.info("tnumero:::: " + polien.getConpol());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		semaphores.cleanSession(session.getId());
		if (polien.getConpol() > 0) {
			Polien encabezado = getPolienRepository().getPoliza(polien.getMespol(), polien.getConpol(),
					polien.getTippol(), this.getUserDetails().getIdSector());
			if (null != encabezado) {
				// TODO Busca el detalle y selecciona el último renglon

				// listPolide =
				// this.reIndexWithoutRenpol(polideRepository.getByAllDetail(polien.getMespol(),
				// polien.getConpol(), polien.getTippol(),
				// this.getUserDetails().getIdSector()));
				// if (null != this.listPolide && !this.listPolide.isEmpty()) {
				// this.lastRow = this.listPolide.get(listPolide.size() - 1);
				// } else {
				// this.lastRow = null;
				// }
				this.enableButtons(encabezado);
				polien = encabezado;
				this.actualPage = this.getPolienRepository().getPage(polien.getMespol(), polien.getTippol(),
						polien.getConpol(), this.getUserDetails().getIdSector()) - 1;
				this.getPolideDataModel().setPolien(polien);
				this.getPolideDataModel().setInsert(Boolean.FALSE);
				this.getPolideDataModel().setCount(null);
				RequestContext.getCurrentInstance().execute("PF('polizasWdg').paginator.setPage(0);");
				TcRegistraArchivoDigital archivoDigital = getExisDigital().getPoliBby(
						String.valueOf(encabezado.getMespol()), encabezado.getConpol(), encabezado.getTippol(),
						this.getUserDetails().getIdSector());
				if (null != archivoDigital) {
					this.imageId = archivoDigital.getId();

				} else {
					this.imageId = 0l;
				}

			} else {
				this.lastHead();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"La póliza no se encuentra en la base");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El numero de póliza debe de ser mayor a cero");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		this.nomSelectedConcept = StringUtils.EMPTY;
		this.nomSelectedAcc = StringUtils.EMPTY;
	}

	/**
	 * Gets the police key.
	 *
	 * @param polien the polien
	 * @return the police key
	 */
	private String getPoliceKey(Polien polien) {
		String policeKey = POLICIES_LOCK_PREFIX + "_"
				+ getDataYearSystemService().getYear(this.getUserDetails().getIdSector()) + "_" + polien.getMespol()
				+ "_" + getUserDetails().getIdSector() + "_" + polien.getTippol() + "_" + polien.getConpol();
		LOGGER.info(policeKey);
		return policeKey;
	}

	/**
	 * Clear locks.
	 */
	private void clearLocks() {
		ServletContext servletContext = getServletContext();
		List<String> lockedPolicies = new ArrayList<String>();
		Enumeration<String> attributeNames = servletContext.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String current = (String) attributeNames.nextElement();
			if (current.startsWith(POLICIES_LOCK_PREFIX)) {
				lockedPolicies.add(current);
			}
		}
		for (String lockedPolice : lockedPolicies) {
			String blockUser = (String) servletContext.getAttribute(lockedPolice);
			if (blockUser.equals(getUserDetails().getUsername())) {
				servletContext.removeAttribute(lockedPolice);
			}
		}
	}

	/*
	 * private void lockPolice() { ServletContext servletContext =
	 * getServletContext(); setLockUser(getUserDetails().getUsername());
	 * LOGGER.info("blocking for lockUser: " + getLockUser()); String policeKey =
	 * getPoliceKey(polien);
	 * FacesContext.getCurrentInstance().getExternalContext().getSessionMap().
	 * put(policeKey, new LockedPolice(policeKey));
	 * servletContext.setAttribute(policeKey, getLockUser()); }
	 * 
	 * private boolean checkLockPolice() { LOGGER.info("enter checkLockPolice");
	 * ServletContext servletContext = getServletContext(); clearLocks(); String
	 * policeKey = getPoliceKey(polien); if (servletContext.getAttribute(policeKey)
	 * != null) { String blockUser = (String)
	 * servletContext.getAttribute(policeKey); setLockUser(blockUser);
	 * LOGGER.info("blockUser: " + blockUser); if
	 * (!blockUser.equals(getUserDetails().getUsername())) { FacesMessage message =
	 * new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
	 * "Esta Póliza está siendo ocupada por otro usuario");
	 * FacesContext.getCurrentInstance().addMessage(null, message); return false; }
	 * } else { setLockUser(""); } return true; }
	 */

	/**
	 * Gets the servlet context.
	 *
	 * @return the servlet context
	 */
	private ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	/**
	 * Creates the policy.
	 */
	public void createPolicy() {
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("entra a createPolicy");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info(":::::::::" + this.getUserDetails().getIdSector());
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");

		boolean isAffectedMonth = this.getPolienRepository().count(PolienPredicates.byMesAfectado(polien.getMespol(),
				this.getUserDetails().getIdSector(), AFFECTED_POLICY_ST)) > 0;

		if (!isAffectedMonth) {
			mesActivo = getConPolizSirvice().isPoliceActive(polien.getMespol(), this.getUserDetails().getIdSector());
			if (mesActivo > 0) {
				// listPolide.clear();
				suma1 = BigDecimal.ZERO;
				suma2 = BigDecimal.ZERO;
				suma4 = BigDecimal.ZERO;
				suma5 = BigDecimal.ZERO;
				suma6 = BigDecimal.ZERO;
				Integer conpol = 0;
				Copome copome = getCopomeService().getNextNume(polien, this.getUserDetails().getIdSector(),
						this.getUserDetails().getUsername());
				conpol = copome.getNumNex();
				String tipopol = new String(polien.getTippol());
				Integer mesPol = polien.getMespol();
				this.polien = new Polien();
				polien.setMespol(mesPol);
				polien.setTippol(tipopol);
				// polide.setConpol(null);
				polien.setFecafec(Calendar.getInstance().getTime());
				polien.setStaafe("N");
				polien.setStapol("I");
				polien.setCcontrol(BigDecimal.valueOf(0.0));
				polien.setPolclv1(null);
				polien.setFecpol(Calendar.getInstance().getTime());
				// listPolifl.clear();
				String clvPol = polien.getTippol() + " "
						+ StringUtils.leftPad(String.valueOf(polien.getMespol()), 2, "0") + " "
						+ StringUtils.leftPad(String.valueOf(conpol), 4, "0");
				polien.setClvpol(clvPol);
				polien.setCdebe(BigDecimal.valueOf(0.0));
				polien.setChaber(BigDecimal.valueOf(0.0));
				polien.setIdsector(this.getUserDetails().getIdSector());
				polien.setCappol(this.getUserDetails().getUsername());
				polien.setCdebe(BigDecimal.ZERO);
				polien.setChaber(BigDecimal.ZERO);
				polien.setCtc600(BigDecimal.ZERO);
				polien.setCta600(BigDecimal.ZERO);
				polien.setAnopol(getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
				polien.setUserid(this.getUserDetails().getUsername());
				this.setActivButtons(Boolean.FALSE);
				polien.setFecpol(Calendar.getInstance().getTime());
				polien.setConpol(conpol);
				// polien.setRenpol(polien.getIndex());

				PolienUK cuentaUK = new PolienUK(polien.getAnopol(), polien.getTippol(), polien.getMespol(),
						polien.getConpol(), polien.getIdsector());
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				boolean allowToUse = semaphores.lock(cuentaUK, session.getId(), this.getUserDetails().getUsername());
				if (allowToUse) {
					LOGGER.debug("Se puede usar la póliza.");

					this.setEditableTable(Boolean.TRUE);
					this.setActivaAdicionar(Boolean.FALSE);
					getPolienRepository().save(polien);
					this.lastRow = null;
					this.nomSelectedConcept = StringUtils.EMPTY;
					this.nomSelectedAcc = StringUtils.EMPTY;
					this.actualPage = this.getPolienRepository().getPage(polien.getMespol(), polien.getTippol(),
							polien.getConpol(), this.getUserDetails().getIdSector()) - 1;
					this.getPolideDataModel().setPolien(this.polien);
					this.getPolideDataModel().setInsert(Boolean.FALSE);
					this.getPolideDataModel().setCount(null);
					RequestContext.getCurrentInstance().execute("PF('polizasWdg').filter();");

				} else {
					LOGGER.debug("No se puede usar la poliza, porque alguien mas la esta usando.");
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
							"No se puede usar la póliza, porque alguien mas la esta usando.");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
				// lockPolice();
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El mes no esta activo");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El mes ya fue afectado");
		}
	}

	/**
	 * Cuentahanged.
	 *
	 * @param e the e
	 */
	public void cuentahanged(ValueChangeEvent e) {
		// assign new value to localeCode
		if (null != e.getNewValue()) {
			this.getPolideSelect();
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "");
		}
		activateRowEdit(0);
	}

	/** The new fecpol. */
	private Date newFecpol;

	/**
	 * Gets the new fecpol.
	 *
	 * @return the newFecpol
	 */
	public Date getNewFecpol() {
		return newFecpol;
	}

	/**
	 * Sets the new fecpol.
	 *
	 * @param newFecpol the newFecpol to set
	 */
	public void setNewFecpol(Date newFecpol) {
		this.newFecpol = newFecpol;
	}

	/**
	 * On date select.
	 *
	 * @param event the event
	 */
	public void onDateSelect(SelectEvent event) {
		this.newFecpol = (Date) event.getObject();
		if (null == this.polien.getFecpol()) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Favor de capturar una fecha valida");
			RequestContext.getCurrentInstance().execute("errorChangeDate();");
		} else {
			RequestContext.getCurrentInstance().execute("PF('saveConfirmDateWdt').show();");
		}
	}

	/**
	 * Date change.
	 *
	 * @param event the event
	 */
	public void dateChange(AjaxBehaviorEvent event) {
		System.out.println("File Date: " + this.polien.getFecpol());
		if (null == this.polien.getFecpol()) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Favor de capturar una fecha valida");
			RequestContext.getCurrentInstance().execute("errorChangeDate();");
		} else {
			this.newFecpol = this.polien.getFecpol();
			RequestContext.getCurrentInstance().execute("PF('saveConfirmDateWdt').show();");
		}
		System.out.println("Hello... I am in DateChange");
	}

	/** The Constant MESSAGE_INFO. */
	// @Value("${catalog.message.info}")
	protected static final String MESSAGE_INFO = FrontProperty.getPropertyValue("catalog.message.info");

	/**
	 * Eliminar poliza.
	 */
	public void eliminarPoliza() {
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("entra a elimnar pólizas");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		Poliza poliza = getPolizaService().delete(polien, this.getUserDetails().getIdSector(),
				this.getUserDetails().getUsername());

		if (poliza.getErrorCode() > 0) {
			this.firstHead();

			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, poliza.getMsgError());
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, poliza.getMsgError());
		}
	}

	/**
	 * Gets the polide select.
	 *
	 * @return the polide select
	 */
	public Polide getPolideSelect() {
		return polideSelect;
	}

	/**
	 * Sets the polide select.
	 *
	 * @param polideSelect the new polide select
	 */
	public void setPolideSelect(Polide polideSelect) {
		this.polideSelect = polideSelect;
	}

	/**
	 * Persistence poliza 2.
	 *
	 * @param listPoliSelect the list poli select
	 * @param index          the index
	 */
	public void persistencePoliza2(List<Polide> listPoliSelect, Integer index) {
		this.persistencePolizaOnDeleteRow();
		if (isFEAcc(listPoliSelect.get(index).getCuenta())) {
			conpolflu = polien.getConpol();
			mespolflu = polien.getMespol();
			tippolflu = polien.getTippol();
			cuentaflu = listPoliSelect.get(index).getCuenta();
			cargoflu = listPoliSelect.get(index).getCanpol();
			abonoflu = listPoliSelect.get(index).getCanpolh();
			renpolflu = listPoliSelect.get(index).getRenpol();
			if (listPoliSelect.get(index).getCanpol().compareTo(BigDecimal.ZERO) != 0) {
				sumaFf = listPoliSelect.get(index).getCanpol().abs();
			} else if (listPoliSelect.get(index).getCanpolh().compareTo(BigDecimal.ZERO) != 0) {
				sumaFf = listPoliSelect.get(index).getCanpolh().abs();
			}
			this.setBandera(Boolean.TRUE);
			orderList.clear();
			this.sumaFfBuff = BigDecimal.ZERO;
		} else {
			this.setBandera(Boolean.FALSE);
			orderList.clear();
			conpolflu = null;
			mespolflu = null;
			tippolflu = null;
			cargoflu = null;
			abonoflu = null;
			renpolflu = null;
			cuentaflu = null;
			sumaFf = null;
			this.sumaFfBuff = null;
		}

	}

	/**
	 * Persistence poliza 2 with flow.
	 *
	 * @param polide the polide
	 */
	public void persistencePoliza2WithFlow(Polide polide) {
		this.persistencePolizaOnDeleteRow();
		if (isFEAcc(polide.getCuenta())) {
			conpolflu = polien.getConpol();
			mespolflu = polien.getMespol();
			tippolflu = polien.getTippol();
			cargoflu = polide.getCanpol();
			abonoflu = polide.getCanpolh();
			renpolflu = polide.getRenpol();
			cuentaflu = polide.getCuenta();
			if (null != polide.getCanpol() && polide.getCanpol().compareTo(BigDecimal.ZERO) != 0) {
				sumaFf = polide.getCanpol().abs();
			} else if (null != polide.getCanpolh() && polide.getCanpolh().compareTo(BigDecimal.ZERO) != 0) {
				sumaFf = polide.getCanpolh().abs();
			}
			this.setBandera(Boolean.TRUE);
			this.sumaFfBuff = BigDecimal.ZERO;
			orderList.clear();

		} else {
			this.setBandera(Boolean.FALSE);
			orderList.clear();
			conpolflu = null;
			mespolflu = null;
			tippolflu = null;
			cargoflu = null;
			abonoflu = null;
			renpolflu = null;
			cuentaflu = null;
			sumaFf = null;
			this.sumaFfBuff = null;
		}

	}

	/**
	 * Persistence poliza on delete row.
	 */
	public void persistencePolizaOnDeleteRow() {
		// listPolifl.clear();
		String clvPol = polien.getTippol() + " " + StringUtils.leftPad(String.valueOf(polien.getMespol()), 2, "0") + " "
				+ StringUtils.leftPad(String.valueOf(polien.getConpol()), 4, "0");
		polien.setClvpol(clvPol);
		polien.setIdsector(this.getUserDetails().getIdSector());
		polien.setUserid(this.getUserDetails().getUsername());
		polien.setCappol(this.getUserDetails().getUsername());
		polien.setAnopol(getDataYearSystemService().getYear(this.getUserDetails().getIdSector()));
		polien.setFecafec(Calendar.getInstance().getTime());
		polien.setFeccap(Calendar.getInstance().getTime());
		polien.setStaafe("N");
		polien.setRenpol(this.getPolideDataModel().getLastRenpol().subtract(BigDecimal.ONE));
		this.getPolideService().actCargoAbono(polien, polien.getIdsector());
		this.suma1 = polien.getCdebe();
		this.suma2 = polien.getChaber();
		this.suma5 = polien.getCtc600();
		this.suma6 = polien.getCta600();
		polien = getPolienRepository().save(polien);

	}

	/**
	 * Checks if is date not null.
	 *
	 * @param polien the polien
	 * @return true, if is date not null
	 */
	public boolean isDateNotNull(Polien polien) {
		return null != polien.getFecpol();
	}

	/**
	 * On cuenta row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onCuentaRowDblClckSelect(final SelectEvent event) {
		Cuenta buff = (Cuenta) event.getObject();
		// int index = listPolide.isEmpty() ? 0 : listPolide.size() - 1;
		// Polide newPolien = new Polide();
		// Polide selected = this.getPolideSelect();##
		Polide selected = null;
		if (this.getPolideDataModel().getInsert() && this.getPolideDataModel().getSelected().getId().longValue() == 0) {
			selected = this.getPolideDataModel().getSelected();
		} else {
			if (UPDATE_POLIDE.equals(this.rowEditFlag)) {
				selected = this.getPolideDataModel().getListPolide().get(this.idBuffUdp.intValue());
			}
		}
		// listPolide.get(index);

		selected.setCuenta(buff.getCuenta());
		selected.setScta(buff.getScuenta());
		selected.setSscta(buff.getSscuenta());
		selected.setSsscta(buff.getSsscuenta());
		selected.setSssscta(buff.getSssscuenta());
		if (StringUtils.isBlank(selected.getConcep())) {
			selected.setConcep(buff.getNomcta());
		}
		this.getPolideDataModel().setSelected(selected);
		activateRowEdit(selected.getIndex());
	}

	/**
	 * Checks if is passvalidate.
	 */
	public void isPassvalidate() {
		if (validaPass.equals(password)) {
			eliminarPoliza();
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "La contraseña es incorrecta");
		}
	}

	/**
	 * Validate flugo efectivo.
	 *
	 * @param event the event
	 */
	public void validateFlugoEfectivo(RowEditEvent event) {

	}

	/**
	 * Gets the suma 1.
	 *
	 * @return the suma1
	 */
	public BigDecimal getSuma1() {
		return suma1;
	}

	/**
	 * Sets the suma 1.
	 *
	 * @param suma1 the suma1 to set
	 */
	public void setSuma1(BigDecimal suma1) {
		this.suma1 = suma1;
	}

	/**
	 * Gets the suma 2.
	 *
	 * @return the suma2
	 */
	public BigDecimal getSuma2() {
		return suma2;
	}

	/**
	 * Sets the suma 2.
	 *
	 * @param suma2 the suma2 to set
	 */
	public void setSuma2(BigDecimal suma2) {
		this.suma2 = suma2;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the errorMsg
	 */
	public StringBuilder getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(StringBuilder errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Validate flujo.
	 *
	 * @param cargo    the cargo
	 * @param abono    the abono
	 * @param cveFlujo the cve flujo
	 * @param flujoE   the flujo E
	 * @return the string
	 */
	public String validateFlujo(BigDecimal cargo, BigDecimal abono, BigDecimal cveFlujo, BigDecimal flujoE) {
		StringBuilder toReturn = new StringBuilder();
		if (cargo.compareTo(BigDecimal.ZERO) != 0) {
			if (cveFlujo.compareTo(BigDecimal.valueOf(20l)) < 0) {
				if (flujoE.compareTo(BigDecimal.ZERO) < 0) {
					toReturn.append("El saldo debe de ser porsitivo");
				}
			} else if (cveFlujo.compareTo(BigDecimal.valueOf(20l)) > 0) {
				if (flujoE.compareTo(BigDecimal.ZERO) > 0) {
					toReturn.append("El saldo debe de ser negativo");
				}
			}
		} else if (abono.compareTo(BigDecimal.ZERO) != 0) {
			if (cveFlujo.compareTo(BigDecimal.valueOf(20l)) < 0) {
				if (flujoE.compareTo(BigDecimal.ZERO) > 0) {
					toReturn.append("El saldo debe de ser negativo");
				}
			} else if (cveFlujo.compareTo(BigDecimal.valueOf(20l)) > 0) {
				if (flujoE.compareTo(BigDecimal.ZERO) < 0) {
					toReturn.append("El saldo debe de ser positivo");
				}
			}
		}

		return toReturn.toString();
	}

	/**
	 * Display error msg.
	 *
	 * @param msg the msg
	 */
	private static void displayErrorMsg(final String msg) {
		FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, msg);
		FacesContext.getCurrentInstance().addMessage(ERROR_MSG, errorMsg);
	}

	/**
	 * Checks if is FE acc.
	 *
	 * @param cuenta the cuenta
	 * @return true, if is FE acc
	 */
	public static boolean isFEAcc(String cuenta) {
		return org.apache.commons.collections.CollectionUtils.exists(FE_ACCS, PredicateUtils.equalPredicate(cuenta));
	}

	/** The count. */
	private Long count;

	/** The actual page. */
	private Integer actualPage;

	/**
	 * First head.
	 */
	public void firstHead() {
		this.actualPage = 0;
		this.doFind(this.actualPage);
	}

	/**
	 * Next head.
	 */
	public void nextHead() {
		if ((this.actualPage + 1) < this.count) {
			this.actualPage = this.actualPage + 1;
		}
		this.doFind(this.actualPage);
	}

	/**
	 * Previus head.
	 */
	public void previusHead() {
		if ((this.actualPage) > 0) {
			this.actualPage = this.actualPage - 1;
		}
		this.doFind(this.actualPage);
	}

	/**
	 * Last head.
	 */
	public void lastHead() {
		this.count = this.getPolienRepository().count(PolienPredicates.byTipoMes(this.polien.getTippol(),
				this.polien.getMespol(), this.getUserDetails().getIdSector()));
		if (0 < this.count) {
			this.actualPage = this.count.intValue() - 1;
		} else {
			this.actualIndex = 0;
		}

		this.doFind(this.actualPage);
	}

	/**
	 * Clean.
	 */
	@PostRemove
	private void clean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		semaphores.cleanSession(session.getId());
	}

	/**
	 * Do find.
	 *
	 * @param pageIndex the page index
	 */
	private void doFind(Integer pageIndex) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		semaphores.cleanSession(session.getId());

		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, POLEIN_TIO_MES_CONT);
		Page<Polien> page = this.getPolienRepository().findAll(PolienPredicates.byTipoMes(this.polien.getTippol(),
				this.polien.getMespol(), this.getUserDetails().getIdSector()), pageable);
		if (page.getContent().isEmpty()) {
			polien.setFeccap(Calendar.getInstance().getTime());
			polien.setPolclv1(StringUtils.EMPTY);
			polien.setStapol(StringUtils.EMPTY);
			polien.setStaafe(StringUtils.EMPTY);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					String.format("No existen pólizas para el Tipo=%1$s y Mes=%2$s", this.polien.getTippol(),
							this.polien.getMespol()));
			this.getPolideDataModel().setPolien(polien);
		} else {
			polien = page.getContent().get(0);
			// TODO Busqueda por controles de paginación de cabecero
			this.getPolideDataModel().setPolien(polien);
			this.getPolideDataModel().setInsert(Boolean.FALSE);
			this.getPolideDataModel().setCount(null);
			RequestContext.getCurrentInstance().execute("PF('polizasWdg').paginator.setPage(0);");
			this.count = this.getPolienRepository().count(PolienPredicates.byTipoMes(this.polien.getTippol(),
					this.polien.getMespol(), this.getUserDetails().getIdSector()));
			this.enableButtons(polien);
			TcRegistraArchivoDigital archivoDigital = getExisDigital().getPoliBby(String.valueOf(polien.getMespol()),
					polien.getConpol(), polien.getTippol(), this.getUserDetails().getIdSector());
			if (null != archivoDigital) {
				this.imageId = archivoDigital.getId();

			} else {
				this.imageId = 0l;
			}
		}
		this.nomSelectedConcept = StringUtils.EMPTY;
		this.nomSelectedAcc = StringUtils.EMPTY;

	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * Gets the actual page.
	 *
	 * @return the actual page
	 */
	public Integer getActualPage() {
		return actualPage;
	}

	/**
	 * Sets the actual page.
	 *
	 * @param actualPage the new actual page
	 */
	public void setActualPage(Integer actualPage) {
		this.actualPage = actualPage;
	}

	/** The actual index. */
	private Integer actualIndex = -1;

	/**
	 * Change row.
	 */
	public void changeRow() {
		Integer row = Integer.valueOf(this.getRenglon().intValue());
		SelectPagePolideDTO selecDTO = this.getPolideDataModel().pageSelect(row);
		if (selecDTO.isBandera()) {
			this.actualIndex = selecDTO.getPosicion();
			RequestContext.getCurrentInstance()
					.execute("PF('polizasWdg').paginator.setPage(" + selecDTO.getPage()
							+ ");PF('polizasWdg').unselectAllRows();PF('polizasWdg').selectRow("
							+ selecDTO.getPosicion() + ")");
		} else {
			RequestContext.getCurrentInstance().execute("PF('polizasWdg').paginator.setPage(" + selecDTO.getPage()
					+ ");PF('polizasWdg').unselectAllRows();");
			generateNotificationFront(SEVERITY_ERROR, "Error", "El renglon no existe en la póliza");
		}
	}

	/**
	 * Gets the actual index.
	 *
	 * @return the actualIndex
	 */
	public Integer getActualIndex() {
		return actualIndex;
	}

	/**
	 * Sets the actual index.
	 *
	 * @param actualIndex the actualIndex to set
	 */
	public void setActualIndex(Integer actualIndex) {
		this.actualIndex = actualIndex;
	}

	/**
	 * Change first row.
	 */
	public void changeFirstRow() {
		this.actualIndex = 0;
		this.doSeekRow(this.actualIndex);
	}

	/**
	 * Change prev row.
	 */
	public void changePrevRow() {
		this.actualIndex = this.checkIfNull(this.actualIndex);
		if (this.actualIndex > 0) {
			this.actualIndex--;
		}
		this.doSeekRow(this.actualIndex);
	}

	/**
	 * Change next row.
	 */
	public void changeNextRow() {
		this.actualIndex = this.checkIfNull(this.actualIndex);
		// if (this.actualIndex < this.listPolide.size() - 1) {
		// this.actualIndex++;
		// }
		this.doSeekRow(this.actualIndex);
	}

	/**
	 * Change end row.
	 */
	public void changeEndRow() {
		// this.actualIndex = this.listPolide.size() - 1;
		this.doSeekRow(this.actualIndex);
	}

	/**
	 * Do seek row.
	 *
	 * @param row the row
	 */
	private void doSeekRow(Integer row) {
		Integer scrollSize = row * 10;
		if (row > 20) {
			scrollSize = row * 25;
		}
		Polide toSelect = this.findByRowIndex(row);
		this.doChangeConeptAccName(toSelect);
		RequestContext.getCurrentInstance().execute("PF('polizasWdg').unselectAllRows(); PF('polizasWdg').selectRow("
				+ row + ", true);" + "jQuery('div.ui-datatable-scrollable-body').scrollTop(" + scrollSize + ");");
	}

	/**
	 * Find by row index.
	 *
	 * @param row the row
	 * @return the polide
	 */
	private Polide findByRowIndex(Integer row) {
		// return IterableUtils.find(this.listPolide, new Predicate<Polide>() {
		// @Override
		// public boolean evaluate(Polide o) {
		// return o.getIndex() == row;
		// }
		// });
		return null;
	}

	/**
	 * Check if null.
	 *
	 * @param indexRow the index row
	 * @return the integer
	 */
	private Integer checkIfNull(Integer indexRow) {
		if (null == indexRow) {
			indexRow = 0;
		}
		return indexRow;
	}

	/** The nom selected concept. */
	private String nomSelectedConcept;

	/** The nom selected acc. */
	private String nomSelectedAcc;

	/**
	 * Gets the nom selected concept.
	 *
	 * @return the nomSelectedConcept
	 */
	public String getNomSelectedConcept() {
		return nomSelectedConcept;
	}

	/**
	 * Sets the nom selected concept.
	 *
	 * @param nomSelectedConcept the nomSelectedConcept to set
	 */
	public void setNomSelectedConcept(String nomSelectedConcept) {
		this.nomSelectedConcept = nomSelectedConcept;
	}

	/**
	 * Gets the nom selected acc.
	 *
	 * @return the nomSelectedAcc
	 */
	public String getNomSelectedAcc() {
		return nomSelectedAcc;
	}

	/**
	 * Sets the nom selected acc.
	 *
	 * @param nomSelectedAcc the nomSelectedAcc to set
	 */
	public void setNomSelectedAcc(String nomSelectedAcc) {
		this.nomSelectedAcc = nomSelectedAcc;
	}

	/**
	 * On row select.
	 *
	 * @param selectEvent the select event
	 */
	public void onRowSelect(SelectEvent selectEvent) {
		Polide selectedPolide = (Polide) selectEvent.getObject();
		if (null != selectedPolide) {
			boolean val = !((this.getPolideDataModel().getInsert()
					&& this.getPolideDataModel().getSelected().getId() != null
					&& this.getPolideDataModel().getSelected().getId().longValue() == 0)
					|| (UPDATE_POLIDE.equals(this.rowEditFlag)
							&& selectedPolide.getIndex() == this.idBuffUdp.intValue()));
			if (val) {
				this.doChangeConeptAccName(selectedPolide);
			}
		}
	}

	/**
	 * Do change conept acc name.
	 *
	 * @param selectedPolide the selected polide
	 */
	private void doChangeConeptAccName(Polide selectedPolide) {
		if ((null != selectedPolide.getId() && selectedPolide.getId().longValue() > 0)) {
			this.actualIndex = selectedPolide.getIndex();
			this.nomSelectedConcept = selectedPolide.getConcep();
			this.nomSelectedAcc = selectedPolide.getNomcta();
			this.polideSelect = selectedPolide;
		}
	}

	/**
	 * Gets the acc from polide.
	 *
	 * @param polide the polide
	 * @return the acc from polide
	 */
	private Cuenta getAccFromPolide(Polide polide) {
		Cuenta toFind = new Cuenta();
		toFind.setCuenta(polide.getCuenta());
		toFind.setScuenta(polide.getScta());
		toFind.setSscuenta(polide.getSscta());
		toFind.setSsscuenta(polide.getSsscta());
		toFind.setSssscuenta(polide.getSssscta());
		toFind.setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		return toFind;
	}

	/** The row edit flag. */
	private String rowEditFlag = StringUtils.EMPTY;

	/**
	 * Gets the row edit flag.
	 *
	 * @return the rowEditFlag
	 */
	public String getRowEditFlag() {
		return rowEditFlag;
	}

	/**
	 * Sets the row edit flag.
	 *
	 * @param rowEditFlag the rowEditFlag to set
	 */
	public void setRowEditFlag(String rowEditFlag) {
		this.rowEditFlag = rowEditFlag;
	}

	/** The inmediato. */
	private Boolean inmediato = Boolean.FALSE;

	/**
	 * Checks if is 5 x or 8 x acc.
	 *
	 * @param acc the acc
	 * @return true, if is 5 x or 8 x acc
	 */
	private boolean is5xOr8xAcc(String acc) {
		return this.getAccountService().is5xAccount(acc) || acc.matches(ACCOUNT_TYPE_8217_REGEXP);
	}

	/**
	 * Find acc name.
	 */
	public void findAccName() {
		// this.rowEditFlag = FIND_ACC_NAME;
		this.inmediato = Boolean.TRUE;
		Polide polide = null;
		if (this.getPolideDataModel().getInsert() && this.getPolideDataModel().getSelected().getId().longValue() == 0) {
			polide = this.getPolideDataModel().getSelected();
		} else {
			if (UPDATE_POLIDE.equals(this.rowEditFlag)) {
				polide = this.getPolideDataModel().getListPolide().get(this.idBuffUdp.intValue());
			}
		}
		if (!isRepeatConcept() && StringUtils.isBlank(polide.getConcep())) {
			if (this.is5xOr8xAcc(polide.getCuenta())) {
				polide.setScta(getAccountService().fillRightZeros(polide.getScta(), LENGTH_SECOND_LEVEL));
				polide.setSscta(getAccountService().fillRightZeros(polide.getSscta(), LENGTH_THIRD_LEVEL));
				polide.setSsscta(getAccountService().fillRightZeros(polide.getSsscta(), LENGTH_FOUR_LEVEL));
			} else {
				polide.setScta(getAccountService().fillZeros(polide.getScta(), LENGTH_SECOND_LEVEL));
				polide.setSscta(getAccountService().fillZeros(polide.getSscta(), LENGTH_THIRD_LEVEL));
				polide.setSsscta(getAccountService().fillZeros(polide.getSsscta(), LENGTH_FOUR_LEVEL));
			}

			Cuenta findedAcc = this.getCuentaRepository().findOne(CuentaPredicates.findByAccounCompositeInlcudeEmpty(
					(Cuenta) UtilJPA.fillPropertyStringIfNull(this.getAccFromPolide(polide))));
			if (null != findedAcc) {
				polide.setConcep(findedAcc.getNomcta());
			}
			this.activateRowEdit(polide.getIndex(), FOCUS_REFPOL);
		}
	}

	/**
	 * Gets the inmediato.
	 *
	 * @return the inmediato
	 */
	public Boolean getInmediato() {
		return inmediato;
	}

	/**
	 * Sets the inmediato.
	 *
	 * @param inmediato the inmediato to set
	 */
	public void setInmediato(Boolean inmediato) {
		this.inmediato = inmediato;
	}

	/**
	 * Clean wron policies.
	 */
	public void cleanWronPolicies() {
		this.me = null;
		this.listIncorrectas = null;
	}

	/**
	 * Save update.
	 */
	public void saveUpdate() {
		Polien toSave = this.getPolienRepository().findOne(this.polien.getId());
		toSave.setPolclv1(this.polien.getPolclv1());
		this.getPolienRepository().save(toSave);
		generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Encabezado actualizado");
	}

	/**
	 * Save update date.
	 */
	public void saveUpdateDate() {
		Polien toSave = this.getPolienRepository().findOne(this.polien.getId());
		toSave.setFecpol(this.newFecpol);
		this.getPolienRepository().save(toSave);
		generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Encabezado actualizado");
	}

	/**
	 * Cancel save update date.
	 */
	public void cancelSaveUpdateDate() {
		this.polien = this.getPolienRepository().findOne(this.polien.getId());
		;
	}

	/**
	 * Gets the min date.
	 *
	 * @return the min date
	 */
	public Date getMinDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -1);
		return c.getTime();
	}

	/**
	 * Gets the max date.
	 *
	 * @return the max date
	 */
	public Date getMaxDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		return c.getTime();
	}

	/**
	 * Find digital poli.
	 */
	public void findDigitalPoli() {
		if (this.imageId > 0) {
			RequestContext.getCurrentInstance().execute("PF('digitalPoliza').show();");

		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					" La póliza aun no esta digitalizada");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/** The image id. */
	private Long imageId = 0l;

	/**
	 * Gets the image id.
	 *
	 * @return the imageId
	 */
	public Long getImageId() {
		return imageId;
	}

	/**
	 * Sets the image id.
	 *
	 * @param imageId the imageId to set
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * Scuenta change listener.
	 *
	 * @param event the event
	 */
	public void scuentaChangeListener(AjaxBehaviorEvent event) {
		Polide polide = null;
		if (this.getPolideDataModel().getInsert() && this.getPolideDataModel().getSelected().getId().longValue() == 0) {
			polide = this.getPolideDataModel().getSelected();
		} else {
			if (UPDATE_POLIDE.equals(this.rowEditFlag)) {
				polide = this.getPolideDataModel().getListPolide().get(this.idBuffUdp.intValue());
			}
		}
		if (null != ((UIOutput) event.getSource()).getValue() && null != polide) {
			if (this.is5xOr8xAcc(polide.getCuenta())) {
				polide.setScta(getAccountService().fillRightZeros(polide.getScta(), LENGTH_SECOND_LEVEL));
			} else {
				polide.setScta(getAccountService().fillZeros(polide.getScta(), LENGTH_SECOND_LEVEL));
			}
			activateRowEdit(polide.getIndex());
		}

	}

	/**
	 * Sscuenta change listener.
	 *
	 * @param event the event
	 */
	public void sscuentaChangeListener(AjaxBehaviorEvent event) {
		Polide polide = null;
		if (this.getPolideDataModel().getInsert() && this.getPolideDataModel().getSelected().getId().longValue() == 0) {
			polide = this.getPolideDataModel().getSelected();
		} else {
			if (UPDATE_POLIDE.equals(this.rowEditFlag)) {
				polide = this.getPolideDataModel().getListPolide().get(this.idBuffUdp.intValue());
			}
		}
		if (null != ((UIOutput) event.getSource()).getValue() && null != polide) {

			if (this.is5xOr8xAcc(polide.getCuenta())) {
				polide.setSscta(getAccountService().fillRightZeros(polide.getSscta(), LENGTH_THIRD_LEVEL));
			} else {
				polide.setSscta(getAccountService().fillZeros(polide.getSscta(), LENGTH_THIRD_LEVEL));
			}
			activateRowEdit(polide.getIndex());
		}
	}

	/**
	 * Ssscuenta change listener.
	 *
	 * @param event the event
	 */
	public void ssscuentaChangeListener(AjaxBehaviorEvent event) {
		Polide polide = null;
		if (this.getPolideDataModel().getInsert() && this.getPolideDataModel().getSelected().getId().longValue() == 0) {
			polide = this.getPolideDataModel().getSelected();
		} else {
			if (UPDATE_POLIDE.equals(this.rowEditFlag)) {
				polide = this.getPolideDataModel().getListPolide().get(this.idBuffUdp.intValue());
			}
		}
		if (null != ((UIOutput) event.getSource()).getValue() && null != polide) {

			if (this.is5xOr8xAcc(polide.getCuenta())) {
				polide.setSsscta(getAccountService().fillRightZeros(polide.getSsscta(), LENGTH_FOUR_LEVEL));
			} else {
				polide.setSsscta(getAccountService().fillZeros(polide.getSsscta(), LENGTH_FOUR_LEVEL));
			}
			activateRowEdit(polide.getIndex());
		}
	}

	/**
	 * Sssscuenta change listener.
	 *
	 * @param event the event
	 */
	public void sssscuentaChangeListener(AjaxBehaviorEvent event) {
		Polide polide = null;
		if (this.getPolideDataModel().getInsert() && this.getPolideDataModel().getSelected().getId().longValue() == 0) {
			polide = this.getPolideDataModel().getSelected();
		} else {
			if (UPDATE_POLIDE.equals(this.rowEditFlag)) {
				polide = this.getPolideDataModel().getListPolide().get(this.idBuffUdp.intValue());
			}
		}
		if (null != ((UIOutput) event.getSource()).getValue() && null != polide) {

			if (this.is5xOr8xAcc(polide.getCuenta())) {
				polide.setSssscta(getAccountService().fillRightZeros(polide.getSsscta(), LENGTH_FIVE_LEVEL));
			} else {
				polide.setSssscta(getAccountService().fillZeros(polide.getSssscta(), LENGTH_FIVE_LEVEL));
			}

			activateRowEdit(polide.getIndex());
		}
		findAccName();
	}

	/** The suma ff buff. */
	private BigDecimal sumaFfBuff;

	/**
	 * Gets the suma ff buff.
	 *
	 * @return the suma ff buff
	 */
	public BigDecimal getSumaFfBuff() {
		return sumaFfBuff;
	}

	/**
	 * Sets the suma ff buff.
	 *
	 * @param sumaFfBuff the new suma ff buff
	 */
	public void setSumaFfBuff(BigDecimal sumaFfBuff) {
		this.sumaFfBuff = sumaFfBuff;
	}

	/** The previous page. */
	private String previousPage = null;

	/**
	 * Check F 5.
	 */
	public void checkF5() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		String id = viewRoot.getViewId();
		if (previousPage != null && (previousPage.equals(id))) {
			init();
			if (null != getPolienRepository().getByFirstMonth(this.getUserDetails().getIdSector())) {
				RequestContext.getCurrentInstance().execute("refresh();");
			}
		} else {

		}
		previousPage = id;
	}

	/**
	 * Succes cancel edition.
	 */
	public void succesCancelEdition() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
		RequestContext.getCurrentInstance().execute("PF('polizasWdg').unselectAllRows();");
	}

	/**
	 * Change page.
	 *
	 * @param event the event
	 */
	public void changePage(PageEvent event) {
		int var = event.getPage();
		if (var < this.getPolideDataModel().getActualPage().getTotalPages() - 1) {
			this.getPolideDataModel().setInsert(Boolean.FALSE);
			this.getPolideDataModel().setSelected(null);
			this.nomSelectedConcept = StringUtils.EMPTY;
			this.nomSelectedAcc = StringUtils.EMPTY;
			this.polideSelect = null;
			if (this.actualIndex > -1) {
				RequestContext.getCurrentInstance()
						.execute("PF('polizasWdg').unselectAllRows();PF('polizasWdg').selectRow(" + actualIndex + ")");
				this.actualIndex = -1;
			}
		} else {
			if (this.getPolideDataModel().getInsert()) {
				this.activateRowEditOnInsert(this.getPolideDataModel().getListPolide()
						.get(this.getPolideDataModel().getListPolide().size() - 1).getIndex());
				if (isBandera() == Boolean.TRUE) {
					RequestContext.getCurrentInstance().execute("PF('flujoefectivo').show();");
					setBandera(Boolean.FALSE);
				}
			}
		}
	}

	/**
	 * Go to last page.
	 */
	public void goToLastPage() {
		if (this.getPolideDataModel().getListPolide().size() <= 20) {
			this.activateRowEditOnInsert(this.getPolideDataModel().getListPolide().size() - 1);
			if (isBandera() == Boolean.TRUE) {
				RequestContext.getCurrentInstance().execute("PF('flujoefectivo').show();");
				setBandera(Boolean.FALSE);
			}
		} else {
			RequestContext.getCurrentInstance()
					.execute("PF('polizasWdg').paginator.setPage(PF('polizasWdg').paginator.cfg.pageCount - 1);");
		}

	}

	/** The tc registra archivo digital repository. */
	@ManagedProperty("#{tcRegistraArchivoDigitalRepository}")
	private TcRegistraArchivoDigitalRepository tcRegistraArchivoDigitalRepository;

	/**
	 * Gets the tc registra archivo digital repository.
	 *
	 * @return the tc registra archivo digital repository
	 */
	public TcRegistraArchivoDigitalRepository getTcRegistraArchivoDigitalRepository() {
		return tcRegistraArchivoDigitalRepository;
	}

	/**
	 * Sets the tc registra archivo digital repository.
	 *
	 * @param tcRegistraArchivoDigitalRepository the new tc registra archivo digital
	 *                                           repository
	 */
	public void setTcRegistraArchivoDigitalRepository(
			TcRegistraArchivoDigitalRepository tcRegistraArchivoDigitalRepository) {
		this.tcRegistraArchivoDigitalRepository = tcRegistraArchivoDigitalRepository;
	}

	/** The patg file name. */
	String patgFileName;

	/** The bandera E. */
	Integer banderaE;

	/** The Constant TMP_PATH. */
	public static final String TMP_PATH = System.getProperty("jboss.home.dir") + File.separator + "welcome-content";

	/** The Constant CVE_TIFF_VIEW. */
	public static final String CVE_TIFF_VIEW = "TIFF_VIEW";

	/** The path image tiff. */
	public String pathImageTiff = "";

	/**
	 * Digitaliza poliza.
	 */
	public void digitalizaPoliza() {
		uuid = UUID.randomUUID().toString() + "_";
		// fileName = this.convertToTiffPdfService.getPdfConvenrt(patgFileName)
		LOGGER.info(":: Generar reporte de Pdf, {}", uuid);
		try {
			banderaE = tcRegistraArchivoDigitalRepository.isPoliTrue(polien.getMespol().toString(), polien.getConpol(),
					polien.getTippol(), this.getUserDetails().getIdSector());
			if (banderaE > 0) {
				TcRegistraArchivoDigital tcRegistraArchivoDigital = tcRegistraArchivoDigitalRepository.getFileDigital(
						polien.getMespol().toString(), polien.getConpol().intValue(), polien.getTippol(),
						this.getUserDetails().getIdSector(), polien.getAnopol().toString());
				// patgFileName = tcRegistraArchivoDigital.getPathFile() +
				// tcRegistraArchivoDigital.getNombreArchivo();
				endFilename = tcRegistraArchivoDigital.getNombreArchivo();

				pathImageTiff = this.getParametrosRepository().getValorByCv(CVE_TIFF_VIEW).toString();

//				this.copyFile(tcRegistraArchivoDigital.getPathFile(), endFilename, uuid, TMP_PATH);
//				patgFileName = copyFile(tcRegistraArchivoDigital.getPathFile(), endFilename, uuid, pathImageTiff);
//				RequestContext.getCurrentInstance().execute("openDialgoTiff('"+ patgFileName +"')");

				RequestContext.getCurrentInstance().execute("openDialgoTiff('" + endFilename + "')");
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"La póliza no ha sido digitalizada.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (final Exception e) {
			uuid = null;
			e.printStackTrace();
			LOGGER.error("createFilePdfInline: Report = {} ", endFilename);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Hubo un error al generar el reporte, contacte a su administrador.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		// try{
		// jasperGenericService.createReportPdftoFs(getParametersReports(),
		// jasperReporteName, GetFileInlineServlet.getFullPath(uuid,
		// endFilename));
		// } catch (final ReportValidationException params) {
		// uuid = null;
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		// StringUtils.EMPTY, params.getMessage());
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// } catch (final Exception genericExc) {
		// uuid = null;
		// genericExc.printStackTrace();
		// LOGGER.error ("createFilePdfInline: Report = {} ", jasperReporteName,
		// genericExc);
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		// StringUtils.EMPTY, "Hubo un error al generar el reporte, contacte a
		// su administrador.");
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// }
	}

	/** The stream tiff. */
	private StreamedContent streamTiff;

	/**
	 * Gets the stream tiff.
	 *
	 * @return the stream tiff
	 */
	public StreamedContent getStreamTiff() {
		return streamTiff;
	}

	/**
	 * Sets the stream tiff.
	 *
	 * @param streamTiff the new stream tiff
	 */
	public void setStreamTiff(StreamedContent streamTiff) {
		this.streamTiff = streamTiff;
	}

	/** The input. */
	InputStream input = null;

	/**
	 * Down load tiff.
	 */
	public void downLoadTiff() {
		try {
			streamTiff = null;
			banderaE = tcRegistraArchivoDigitalRepository.isPoliTrue(polien.getMespol().toString(), polien.getConpol(),
					polien.getTippol(), this.getUserDetails().getIdSector());
			if (banderaE > 0) {
				TcRegistraArchivoDigital tcRegistraArchivoDigital = tcRegistraArchivoDigitalRepository.getFileDigital(
						polien.getMespol().toString(), polien.getConpol().intValue(), polien.getTippol(),
						this.getUserDetails().getIdSector(), polien.getAnopol().toString());
				// patgFileName = tcRegistraArchivoDigital.getPathFile() +
				// tcRegistraArchivoDigital.getNombreArchivo();
				endFilename = tcRegistraArchivoDigital.getNombreArchivo();
				input = new FileInputStream(new File(tcRegistraArchivoDigital.getPathFile() + "/" + endFilename));
				streamTiff = new DefaultStreamedContent(input, "application/image", endFilename);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", endFilename);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"La póliza no ha sido digitalizada.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Copy file.
	 *
	 * @param path     the path
	 * @param filename the filename
	 * @param uuid     the uuid
	 * @param pathTmp  the path tmp
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String copyFile(String path, String filename, String uuid, String pathTmp) throws IOException {
		InputStream inputStream = new FileInputStream(new File(path + filename));
		OutputStream output = new FileOutputStream(new File(pathTmp, uuid + filename));

		try {
			IOUtils.copy(inputStream, output);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(output);
		}
		return uuid + filename;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public TbProcesoService getProcesoService() {
		return procesoService;
	}

	public void setProcesoService(TbProcesoService procesoService) {
		this.procesoService = procesoService;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

}
