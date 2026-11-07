package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliend;
import com.gem.sistema.business.domain.Polifl;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcRegistraArchivoDigital;
import com.gem.sistema.business.predicates.CatfluPredicates;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.predicates.PolidePredicate;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.predicates.PoliflPredicates;
import com.gem.sistema.business.repository.catalogs.CatfluRepository;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.PoliflRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcRegistraArchivoDigitalRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve;
import com.gem.sistema.business.service.catalogos.CopomeService;
import com.gem.sistema.business.service.catalogos.CuentaAdicionalService;
import com.gem.sistema.business.service.catalogos.DataYearSystemService;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.util.UtilJPA;
import com.gem.sistema.web.datamodel.CuentaPolizaDataModel;
import com.gem.sistema.web.util.FrontProperty;
import com.gem.sistema.web.util.LockedPolice;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaMantenimientoMB.
 */
@ManagedBean(name = "polizaMantenimientoMB")
@SessionScoped
public class PolizaMantenimientoMB extends AbstractMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaMantenimientoMB.class);
	
	/** The Constant FOUS_JQUERY. */
	private static final String FOUS_JQUERY = "jQuery('#cuenta').eq(%1$s).each(function(){jQuery(this).focus});";

	/** The con poliz sirvice. */
	@ManagedProperty(value = "#{consultaPolizaSirveImpl}")
	private ConsultaPolizaSirve conPolizSirvice;
	
	/** The year. */
	private Integer year;
	
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
	
	/** The activa adicionar. */
	private boolean activaAdicionar = Boolean.FALSE;
	
	/** The activa modifcar. */
	private boolean activaModifcar = Boolean.FALSE;
	
	/** The yeat aux. */
	Integer yeatAux;
	
	/** The moth aux. */
	Integer mothAux;

	/** The suma ff. */
	private BigDecimal sumaFf = BigDecimal.ZERO;

	/** The cargoflu. */
	private BigDecimal cargoflu;
	
	/** The abonoflu. */
	private BigDecimal abonoflu;
	
	/** The editable table. */
	private boolean editableTable = Boolean.FALSE;
	
	/** The bloquea boton. */
	private boolean bloqueaBoton = Boolean.FALSE;

	/** The repeat concept. */
	private boolean repeatConcept = Boolean.FALSE;

	/** The Constant POLICIES_LOCK_PREFIX. */
	private static final String POLICIES_LOCK_PREFIX = "POLICE";
	
	/** The lock user. */
	private String lockUser;

	/** The copome service. */
	@ManagedProperty("#{copomeService}")
	private CopomeService copomeService;

	/** The Constant POLIZA_CARGO_ABONO_MSG. */
	private static final String POLIZA_CARGO_ABONO_MSG = FrontProperty.getPropertyValue("poliza.cargo.abono.msg");

	/**
	 * Gets the copome service.
	 *
	 * @return the copome service
	 */
	public CopomeService getCopomeService() {
		return copomeService;
	}

	/**
	 * Sets the copome service.
	 *
	 * @param copomeService the new copome service
	 */
	public void setCopomeService(CopomeService copomeService) {
		this.copomeService = copomeService;
	}

	/**
	 * Checks if is bloquea boton.
	 *
	 * @return true, if is bloquea boton
	 */
	public boolean isBloqueaBoton() {
		return bloqueaBoton;
	}

	/**
	 * Sets the bloquea boton.
	 *
	 * @param bloqueaBoton the new bloquea boton
	 */
	public void setBloqueaBoton(boolean bloqueaBoton) {
		this.bloqueaBoton = bloqueaBoton;
	}

	/**
	 * Checks if is activa adicionar.
	 *
	 * @return true, if is activa adicionar
	 */
	public boolean isActivaAdicionar() {
		return activaAdicionar;
	}

	/**
	 * Checks if is activa modifcar.
	 *
	 * @return true, if is activa modifcar
	 */
	public boolean isActivaModifcar() {
		return activaModifcar;
	}

	/**
	 * Sets the activa adicionar.
	 *
	 * @param activaAdicionar the new activa adicionar
	 */
	public void setActivaAdicionar(boolean activaAdicionar) {
		this.activaAdicionar = activaAdicionar;
	}

	/**
	 * Sets the activa modifcar.
	 *
	 * @param activaModifcar the new activa modifcar
	 */
	public void setActivaModifcar(boolean activaModifcar) {
		this.activaModifcar = activaModifcar;
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
	 * Checks if is editable table.
	 *
	 * @return true, if is editable table
	 */
	public boolean isEditableTable() {
		return editableTable;
	}

	/**
	 * Sets the editable table.
	 *
	 * @param editableTable the new editable table
	 */
	public void setEditableTable(boolean editableTable) {
		this.editableTable = editableTable;
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

	/** The polifl repository. */
	@ManagedProperty("#{poliflRepository}")
	private PoliflRepository poliflRepository;

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

	/** The bandera. */
	private boolean bandera = Boolean.FALSE;

	/** The valida pass. */
	private String validaPass = "xpoli2005";
	
	/** The password. */
	private String password;

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
	 * Gets the polifl repository.
	 *
	 * @return the polifl repository
	 */
	public PoliflRepository getPoliflRepository() {
		return poliflRepository;
	}

	/**
	 * Sets the polifl repository.
	 *
	 * @param poliflRepository the new polifl repository
	 */
	public void setPoliflRepository(PoliflRepository poliflRepository) {
		this.poliflRepository = poliflRepository;
	}

	/** The order list. */
	private List<Polifl> orderList = new ArrayList<Polifl>();

	/** The polifl 2. */
	private Polifl polifl2;

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
	 * @param polifl2            the polifl2 to set
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

	/** The catflu repository. */
	@ManagedProperty(value = "#{catfluRepository}")
	private CatfluRepository catfluRepository;

	/**
	 * Gets the catflu repository.
	 *
	 * @return the catfluRepository
	 */
	public CatfluRepository getCatfluRepository() {
		return catfluRepository;
	}

	/**
	 * Sets the catflu repository.
	 *
	 * @param catfluRepository            the catfluRepository to set
	 */
	public void setCatfluRepository(CatfluRepository catfluRepository) {
		this.catfluRepository = catfluRepository;
	}

	/**
	 * Check catflu.
	 */
	public void checkCatflu() {
		this.flowValidationEnable = Boolean.TRUE;
		RequestContext.getCurrentInstance().execute(ROWEDIT_CLVFLU_ROW_JQUERY);
	}

	/** The Constant EDIT_FLOW_ROW_JQUERY. */
	private static final String EDIT_FLOW_ROW_JQUERY = "jQuery('#formFlujoEfectivo\\\\:ctafluTable span.ui-icon-pencil').eq(%1$s).each(function(){jQuery(this).click()});";

	/** The Constant FOCUS_FLOW_CLVFLU_ROW_JQUERY. */
	private static final String FOCUS_FLOW_CLVFLU_ROW_JQUERY = "jQuery('#formFlujoEfectivo\\\\:ctafluTable\\\\:%1$s\\\\:oclvflu').focus();";

	/** The Constant FOCUS2_FLOW_CLVFLU_ROW_JQUERY. */
	private static final String FOCUS2_FLOW_CLVFLU_ROW_JQUERY = "$(document.getElementById('formFlujoEfectivo:ctafluTable:%1$s:ocanflu')).focus();";

	/** The Constant ROWEDIT_CLVFLU_ROW_JQUERY. */
	private static final String ROWEDIT_CLVFLU_ROW_JQUERY = "jQuery('#formFlujoEfectivo\\\\:ctafluTable span.ui-icon-check').last().click();";

	/** The flow validation enable. */
	private boolean flowValidationEnable = Boolean.FALSE;

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
		if (this.flowValidationEnable) {
			this.flowValidationEnable = Boolean.FALSE;
			if (pl != null) {
				if (!this.catfluRepository.exists(CatfluPredicates.isLocalidadUnique(
						Double.valueOf(pl.getClvflu().doubleValue()), this.getUserDetails().getIdSector()))) {
					PolizaMantenimientoMB.displayErrorMsg("Esta clave de flujo no esta registrada");
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

				if (!this.catfluRepository.exists(CatfluPredicates.isLocalidadUnique(
						Double.valueOf(pl.getClvflu().doubleValue()), this.getUserDetails().getIdSector()))) {
					PolizaMantenimientoMB.displayErrorMsg("Esta clave de flujo no esta registrada");
					RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
							+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
				} else {

					if ((null != this.poliflBuff && pl.getClvflu().compareTo(this.poliflBuff.getClvflu()) == 0)
							|| !this.poliflRepository.exists(PoliflPredicates.exist(tippolflu, mespolflu, conpolflu,
									renpolflu, pl.getClvflu(), this.getUserDetails().getIdSector()))) {

						String erroMsg = this.validateFlujo(cargoflu, abonoflu, pl.getClvflu(), pl.getCanflu());
						if (StringUtils.isEmpty(erroMsg)) {
							List<Polifl> polifls = poliflRepository.findByMespolAndTippolAndConpolAndRenpolAndIdsector(
									mespolflu, tippolflu, conpolflu, renpolflu, this.getUserDetails().getIdSector());
							BigDecimal suma = this.getSum(polifls, this.poliflBuff);
							suma = suma.add(pl.getCanflu().abs()).abs();
							if (sumaFf.abs().compareTo(suma) >= 0) {
								pl.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
								pl.setTippol(tippolflu);
								pl.setConpol(conpolflu);
								pl.setMespol(mespolflu);
								pl.setRenpol(renpolflu);
								pl.setUserid(this.getUserDetails().getUsername());
								pl.setIdsector(this.getUserDetails().getIdSector());
								poliflRepository.save(pl);
								this.sumaFfBuff = suma;
								if (sumaFf.abs().compareTo(suma) == 0) {
									activaAdicionar = Boolean.FALSE;
								}
							} else {
								PolizaMantenimientoMB.displayErrorMsg("La suma es mayor a la registrada");
								activaAdicionar = Boolean.FALSE;
								RequestContext.getCurrentInstance()
										.execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
												+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
							}
						} else {
							PolizaMantenimientoMB.displayErrorMsg(erroMsg);
							RequestContext.getCurrentInstance()
									.execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
											+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
						}
					} else {
						PolizaMantenimientoMB.displayErrorMsg("La clave de flujo ya fue previamente registrada");
						RequestContext.getCurrentInstance().execute(String.format(EDIT_FLOW_ROW_JQUERY, pl.getIndex())
								+ String.format(FOCUS_FLOW_CLVFLU_ROW_JQUERY, pl.getIndex()));
					}
				}
			} else {
				PolizaMantenimientoMB.displayErrorMsg("Los valores de Clave o Cantidad no son validos");
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
	 * @param polifls the polifls
	 * @param poliflbuff the poliflbuff
	 * @return the sum
	 */
	public BigDecimal getSum(List<Polifl> polifls, Polifl poliflbuff) {
		BigDecimal toReturn = BigDecimal.ZERO;
		for (Polifl polifl : polifls) {
			if (null == poliflbuff || poliflbuff.getId() != polifl.getId()) {
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

	/** The Constant EDIT_CANCELED. */
	private static final String EDIT_CANCELED = FrontProperty.getPropertyValue("poliza.flujo.efectivo.edit.canceled");

	/** The Constant CLVFLU_FORMATTER. */
	private static final DecimalFormat CLVFLU_FORMATTER = new DecimalFormat("00.00");

	/** The valida flujo. */
	private boolean validaFlujo = Boolean.FALSE;

	/**
	 * Checks if is valida flujo.
	 *
	 * @return true, if is valida flujo
	 */
	public boolean isValidaFlujo() {
		return validaFlujo;
	}

	/**
	 * Sets the valida flujo.
	 *
	 * @param validaFlujo the new valida flujo
	 */
	public void setValidaFlujo(boolean validaFlujo) {
		this.validaFlujo = validaFlujo;
	}

	/** The canflu. */
	private BigDecimal canflu;
	
	/** The clvflu. */
	private BigDecimal clvflu;
	
	/** The conpolflu. */
	private Integer conpolflu;
	
	/** The mespolflu. */
	private Integer mespolflu;

	/** The renpolflu. */
	private BigDecimal renpolflu;

	/** The cuentaflu. */
	private String cuentaflu;

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
	 * @param cuentaflu            the cuentaflu to set
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

	/** The list polifl. */
	private List<Polifl> listPolifl;

	/** The select polifl. */
	private Polifl selectPolifl;
	
	/** Fila seleccionada del catalogo de programas. */
	private Cuenta rowSelected;

	/** The data year system service. */
	@ManagedProperty(value = "#{dataYearSystemService}")
	private DataYearSystemService dataYearSystemService;

	/** The polien repository. */
	@ManagedProperty(value = "#{polienRepository}")
	private PolienRepository polienRepository;

	/** The polien. */
	private Polien polien;

	/** The polifl. */
	private Polifl polifl;

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

	/** The id user. */
	private String idUser;
	
	/** The id entidad. */
	private Integer idEntidad;

	/** The list meses. */
	private List<TcMes> listMeses;
	
	/** The tc mes. */
	private TcMes tcMes;

	/** The tc mes repository. */
	@ManagedProperty(value = "#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The polide. */
	private Polide polide;
	
	/** The list polide. */
	private List<Polide> listPolide;
	
	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;
	
	/** The cattpo. */
	private Cattpo cattpo;
	
	/** The list cuenta. */
	private List<Cuenta> listCuenta;
	
	/** The cuenta. */
	private Cuenta cuenta;

	/** The polide select. */
	private Polide polideSelect;

	/** The polide OK. */
	private Polide polideOK;

	/**
	 * Gets the polide OK.
	 *
	 * @return the polideOK
	 */
	public Polide getPolideOK() {
		return polideOK;
	}

	/**
	 * Sets the polide OK.
	 *
	 * @param polideOK            the polideOK to set
	 */
	public void setPolideOK(Polide polideOK) {
		this.polideOK = polideOK;
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

	/** The cattpo repository. */
	@ManagedProperty(value = "#{cattpoRepository}")
	private CattpoRepository cattpoRepository;

	/** The polide repository. */
	@ManagedProperty(value = "#{polideRepository}")
	private PolideRepository polideRepository;

	/** The cuenta adicional service. */
	@ManagedProperty(value = "#{cuentaAdicionalService}")
	private CuentaAdicionalService cuentaAdicionalService;

	/** The suma cargo. */
	private String sumaCargo;
	
	/** The suma abono. */
	private String sumaAbono;
	
	/** The suma A 8000. */
	private String sumaA8000;
	
	/** The suma B 8000. */
	private String sumaB8000;

	/** The repetgir. */
	private boolean repetgir = Boolean.FALSE;
	
	/** The activ buttons. */
	private boolean activButtons = Boolean.FALSE;
	
	/** The mes activo. */
	private Integer mesActivo;

	/** The cuenta poliza data model. */
	@ManagedProperty(value = "#{cuentaPolizaDataModel}")
	private CuentaPolizaDataModel cuentaPolizaDataModel;
	/**
	 * Mapa para guardar las filas seleccionadas del catalogo de naturaleza de
	 * gasto.
	 */
	private Map<Integer, Cuenta> mapCuentaExp;

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
	 * Checks if is repetgir.
	 *
	 * @return true, if is repetgir
	 */
	public boolean isRepetgir() {
		return repetgir;
	}

	/**
	 * Sets the repetgir.
	 *
	 * @param repetgir the new repetgir
	 */
	public void setRepetgir(boolean repetgir) {
		this.repetgir = repetgir;
	}

	/** Habilitar el modo de edicion. */
	// @Value("${view.edit.row.activate.icon.pencil}")
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = FrontProperty
			.getPropertyValue("view.edit.row.activate.icon.pencil");

	/** Habilitar el modo de edicion. */
	// @Value("${view.edit.row.activate.icon.pencil.complement}")
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = FrontProperty
			.getPropertyValue("view.edit.row.activate.icon.pencil.complement");

	/** Habilitar el modo de edicion. */
	// @Value("${view.catalog.form1.objects}")
	protected static final String VIEW_CATALOG_FORM1_OBJECTS = FrontProperty
			.getPropertyValue("view.catalog.form1.objects");

	/** The renglon. */
	private BigDecimal renglon;

	/** The streamed content. */
	private StreamedContent streamedContent;

	/** The list incorrectas. */
	private List<Poliend> listIncorrectas;

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

	/**
	 * Gets the streamed content.
	 *
	 * @return the streamed content
	 */
	public StreamedContent getStreamedContent() {
		if (streamedContent != null)
			try {
				streamedContent.getStream().reset();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		return streamedContent;
	}

	/**
	 * Sets the streamed content.
	 *
	 * @param streamedContent the new streamed content
	 */
	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

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
	 * Gets the cattpo repository.
	 *
	 * @return the cattpo repository
	 */
	public CattpoRepository getCattpoRepository() {
		return cattpoRepository;
	}

	/**
	 * Sets the cattpo repository.
	 *
	 * @param cattpoRepository the new cattpo repository
	 */
	public void setCattpoRepository(CattpoRepository cattpoRepository) {
		this.cattpoRepository = cattpoRepository;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the list polide.
	 *
	 * @return the list polide
	 */
	public List<Polide> getListPolide() {
		return listPolide;
	}

	/**
	 * Sets the list polide.
	 *
	 * @param listPolide the new list polide
	 */
	public void setListPolide(List<Polide> listPolide) {
		this.listPolide = listPolide;
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
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
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
	 * Gets the data year system service.
	 *
	 * @return the data year system service
	 */
	public DataYearSystemService getDataYearSystemService() {
		return dataYearSystemService;
	}

	/**
	 * Sets the data year system service.
	 *
	 * @param dataYearSystemService the new data year system service
	 */
	public void setDataYearSystemService(DataYearSystemService dataYearSystemService) {
		this.dataYearSystemService = dataYearSystemService;
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
	 * Gets the con poliz sirvice.
	 *
	 * @return the con poliz sirvice
	 */
	public ConsultaPolizaSirve getConPolizSirvice() {
		return conPolizSirvice;
	}

	/**
	 * Sets the con poliz sirvice.
	 *
	 * @param conPolizSirvice the new con poliz sirvice
	 */
	public void setConPolizSirvice(ConsultaPolizaSirve conPolizSirvice) {
		this.conPolizSirvice = conPolizSirvice;
	}

	/**
	 * Gets the list cuenta.
	 *
	 * @return the list cuenta
	 */
	public List<Cuenta> getListCuenta() {
		return listCuenta;
	}

	/**
	 * Sets the list cuenta.
	 *
	 * @param listCuenta the new list cuenta
	 */
	public void setListCuenta(List<Cuenta> listCuenta) {
		this.listCuenta = listCuenta;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	 * Checks if is activ buttons.
	 *
	 * @return true, if is activ buttons
	 */
	public boolean isActivButtons() {
		return activButtons;
	}

	/**
	 * Sets the activ buttons.
	 *
	 * @param activButtons the new activ buttons
	 */
	public void setActivButtons(boolean activButtons) {
		this.activButtons = activButtons;
	}

	/**
	 * Removes the list fl.
	 */
	public void removeListFl() {
		if (this.polifl2 != null) {
			if (null != this.getPolifl2().getId() && this.getPolifl2().getId() > 0) {
				this.poliflRepository.delete(this.getPolifl2());
			}
			this.orderList.remove(this.getPolifl2().getIndex());
			this.activaAdicionar = Boolean.TRUE;
			this.orderList = this.reIndexPolifls(this.orderList);

			this.sumaFfBuff = this.getSum(orderList);
		} else {
			PolizaMantenimientoMB.displayErrorMsg("Seleccione un renglon para eliminar");
		}
	}

	/**
	 * On exit flow.
	 */
	public void onExitFlow() {
		List<Polifl> polifls = poliflRepository.findByMespolAndTippolAndConpolAndRenpolAndIdsector(mespolflu, tippolflu,
				conpolflu, renpolflu, this.getUserDetails().getIdSector());
		BigDecimal suma = this.getSum(polifls);

		if (((null == polifls || polifls.isEmpty())
				&& !(this.cuentaflu.equalsIgnoreCase("1111") || this.cuentaflu.equalsIgnoreCase("1112")))
				|| (sumaFf.compareTo(suma) == 0)) {
			RequestContext.getCurrentInstance().execute("PF('flujoefectivo').hide();");
		} else {
			if ((this.cuentaflu.equalsIgnoreCase("1111") || this.cuentaflu.equalsIgnoreCase("1112"))) {
				if ((sumaFf.compareTo(suma) != 0)) {
					PolizaMantenimientoMB.displayErrorMsg(
							"La suma no coincide con el valor del concepto " + DEC_FORMAT.format(sumaFf.doubleValue()));
				} else {
					PolizaMantenimientoMB.displayErrorMsg("Favor de capturar flujo de efectivo");
				}
			} else {

				if (suma.compareTo(BigDecimal.ZERO) == 0) {
					PolizaMantenimientoMB.displayErrorMsg("Favor de capturar flujo de efectivo");
				} else {
					PolizaMantenimientoMB.displayErrorMsg(
							"La suma no coincide con el valor del concepto " + DEC_FORMAT.format(sumaFf.doubleValue()));
				}
			}
		}
	}

	/** The Constant DEC_FORMAT. */
	private static final DecimalFormat DEC_FORMAT = new DecimalFormat("###,###,###,###.00");

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		year = dataYearSystemService.getYear(this.getUserDetails().getIdSector());
		// mesActivo = conPolizSirvice.isPoliceActive(encabezado.getMespol(),
		// this.getUserDetails().getIdSector());
		// if (null == polien || (polien.getId() == null)) {
		polien = polienRepository.getByFirstMonth(this.getUserDetails().getIdSector());
		// }
		if (null != polien && null != polien.getMespol() && null != polien.getTippol() && null != polien.getConpol()) {

			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug("Mespol :" + polien.getMespol() + "    Tipopol :" + polien.getTippol() + "     Conpol :"
					+ polien.getConpol());
			LOGGER.debug(StringUtils.EMPTY);
			LOGGER.debug(StringUtils.EMPTY);
			this.actualPage = this.polienRepository.getPage(polien.getMespol(), polien.getTippol(), polien.getConpol(),
					this.getUserDetails().getIdSector()) - 1;
			listPolide = this.reIndexWithoutRenpol((conPolizSirvice.findPoliceBy(polien.getTippol(), polien.getMespol(),
					polien.getConpol(), this.getUserDetails().getIdSector())));
			if (CollectionUtils.isNotEmpty(listPolide)) {
				this.lastRow = listPolide.get(listPolide.size() - 1);
			} else {
				this.lastRow = null;
			}
			this.count = this.polienRepository.count(PolienPredicates.byTipoMes(polien.getTippol(), polien.getMespol(),
					this.getUserDetails().getIdSector()));
			this.enableButtons(polien);
			TcRegistraArchivoDigital archivoDigital = exisDigital.getPoliBby(String.valueOf(polien.getMespol()),
					polien.getConpol(), polien.getTippol(), this.getUserDetails().getIdSector());
			if (null != archivoDigital) {
				this.imageId = archivoDigital.getId();

			} else {
				this.imageId = 0l;
			}
		} else {
			polien = new Polien();
			listPolide = new ArrayList<Polide>();
			editableTable = Boolean.FALSE;
			activaAdicionar = Boolean.TRUE;
			activaModifcar = Boolean.TRUE;
			activButtons = Boolean.TRUE;
			polien.setConpol(1);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "No existen polizas.");
			this.lastRow = null;
		}
		// }
		listMeses = tcMesRepository.findByIdLessThanEqual(
				Long.valueOf(conPolizSirvice.findActiveMonth(this.getUserDetails().getIdSector())));
		lisCattpo = cattpoRepository.findAll();

		this.actualPage = 0;
		isEditableTable();
	}

	/**
	 * Re index.
	 *
	 * @param polides the polides
	 * @return the list
	 */
	private List<Polide> reIndex(List<Polide> polides) {
		int index = 0;
		for (Polide polide : polides) {
			polide.setIndex(index);
			polide.setRenpol(BigDecimal.valueOf(index).add(BigDecimal.ONE));
			;
			index++;
		}
		return polides;
	}

	/**
	 * Re index without renpol.
	 *
	 * @param polides the polides
	 * @return the list
	 */
	private List<Polide> reIndexWithoutRenpol(List<Polide> polides) {
		int index = 0;
		for (Polide polide : polides) {
			polide.setIndex(index);
			index++;
		}
		return polides;
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
		polideSelect.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
		polideSelect.setFecpol(new Date());
	}

	/**
	 * Creates the first row.
	 */
	public void createFirstRow() {
		LOGGER.info("entra a crear una nueva linea....");
		int index = 0;
		if (polien.getFecpol() != null) {
			// Polide newPolien = new Polide();

			Calendar c = Calendar.getInstance();
			c.setTime(polien.getFecpol());
			mothAux = c.get(Calendar.MONTH) + 1;
			yeatAux = c.get(Calendar.YEAR);
			if (mothAux == polien.getMespol()) {
				if (yeatAux.intValue() == year.intValue()) {
					Polide selected = null;
					if (!this.listPolide.isEmpty()) {
						selected = this.listPolide.get(listPolide.size() - 1);
					}

					if (null != selected && selected.getId().longValue() == 0) {
						activateRowEdit(selected.getIndex());
					} else {
						selected = new Polide();
						selected.setIndex(index);
						selected.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
						this.setPolideSelect(selected);
						selected.setIndex(listPolide.isEmpty() ? 0 : listPolide.size());
						selected.setRenpol(listPolide.isEmpty() ? BigDecimal.ONE : this.getLastRenpol());
						if (this.repeatConcept && this.lastRow != null) {
							selected.setCuenta(this.lastRow.getCuenta());
							selected.setScta(this.lastRow.getScta());
							selected.setSscta(this.lastRow.getSscta());
							selected.setSsscta(this.lastRow.getSsscta());
							selected.setSssscta(this.lastRow.getSssscta());
							selected.setConcep(this.lastRow.getConcep());
							selected.setRefpol(this.lastRow.getRefpol());
							selected.setDn(this.lastRow.getDn());
						}
						this.listPolide.add(selected.getIndex(), selected);
						this.listPolide = this.reIndexWithoutRenpol(listPolide);
						if (repeatConcept) {
							this.activateRowEdit(selected.getIndex(), FOCUS_REFPOL);
						} else {
							this.activateRowEdit(selected.getIndex());
						}
					}
				} else {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
							"El año de la poliza no es igual al de la fecha");
				}

			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
						"El mes de la poliza no es igual al de la fecha");
			}

		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "La fecha de generacion de poliza esta vacia.");
		}

	}

	/**
	 * Delete row.
	 */
	public void deleteRow() {
		LOGGER.info("entra a crear una nueva linea....");
		if (this.getPolideSelect() != null) {
			Polide selected = this.getPolideSelect();
			if (selected.getId() != null && selected.getId() > 0) {
				if (PolizaMantenimientoMB.isFEAcc(selected.getCuenta())) {
					List<Polifl> polifls = this.poliflRepository.findByMespolAndTippolAndConpolAndRenpolAndIdsector(
							selected.getMespol(), selected.getTippol(), selected.getConpol(), selected.getRenpol(),
							this.getUserDetails().getIdSector());
					for (Polifl polifl : polifls) {
						this.poliflRepository.delete(polifl.getId());
					}
				}
				this.polideRepository.delete(selected);

			}
			listPolide.remove(selected.getIndex());
			RequestContext.getCurrentInstance().execute("PF('polizasWdg').unselectAllRows();");
			listPolide = this.reIndexWithoutRenpol((conPolizSirvice.findPoliceBy(polien.getTippol(), polien.getMespol(),
					polien.getConpol(), this.getUserDetails().getIdSector())));
			// listPolide =
			// IteratorUtils.toList(this.polideRepository.save(listPolide).iterator());
			this.persistencePolizaOnDeleteRow(listPolide);
			generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Registro elimado correctamente ");
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Debe seleccionar un renglón para eliminar. ");
		}

	}

	/** The Constant MESSAGE_EDITION_CANCELED. */
	protected static final String MESSAGE_EDITION_CANCELED = FrontProperty
			.getPropertyValue("catalog.message.edition.canceled");

	/**
	 * Muestra un mensaje al cancelar la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowCancel(final RowEditEvent event) {
		LOGGER.info(":: Cancelar edicion ");
		if (this.listPolide.get(listPolide.size() - 1).getId() == null
				|| this.listPolide.get(listPolide.size() - 1).getId() == 0) {
			listPolide.remove(listPolide.size() - 1);
		}
		listPolide = this.reIndexWithoutRenpol(this.polideRepository.getByAllDetail(polien.getMespol(),
				polien.getConpol(), polien.getTippol(), this.getUserDetails().getIdSector()));
		this.nomSelectedConcept = StringUtils.EMPTY;
		this.nomSelectedAcc = StringUtils.EMPTY;
		this.polideSelect = null;
		// generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO,
		// MESSAGE_EDITION_CANCELED);
		// RequestContext.getCurrentInstance().execute("PF('polizasWdg').unselectAllRows();");
	}

	/**
	 * Append status.
	 *
	 * @param sb the sb
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

	/** Longitud del primer nivel de cuentas. */
	public static final int LENGTH_FIRST_LEVEL = 4;

	/** Longitud del segundo nivel de cuentas. */
	public static final int LENGTH_SECOND_LEVEL = 10;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_THIRD_LEVEL = 15;

	/** Longitud del cuarto nivel de cuentas. */
	public static final int LENGTH_FOUR_LEVEL = 4;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_FIVE_LEVEL = 3;

	/** Servicios para el modulo de centas. */
	@ManagedProperty(value = "#{accountService}")
	private AccountService accountService;

	/** The error msg. */
	private StringBuilder errorMsg;

	/** The polide buff. */
	private Polide polideBuff;

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
			} else {
				this.rowEditFlag = StringUtils.EMPTY;
			}
		}
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
			if (this.validate(catalog, errorMsg)) {
				this.polideBuff = this.polideRepository.findOne(this.polideBuff.getId());
				this.polideRepository.save(catalog);
				persistencePoliza2WithFlow(this.listPolide, catalog);
				if (this.bandera) {
					List<Polifl> polifls = this.poliflRepository.findByMespolAndTippolAndConpolAndRenpolAndIdsector(
							this.polideBuff.getMespol(), this.polideBuff.getTippol(), this.polideBuff.getConpol(),
							this.polideBuff.getRenpol(), this.getUserDetails().getIdSector());

					for (Polifl polifl : polifls) {
						polifl.setRenpol(catalog.getRenpol());
						this.poliflRepository.save(polifl);
					}

					this.orderList = poliflRepository.findByMespolAndTippolAndConpolAndRenpolAndIdsector(mespolflu,
							tippolflu, conpolflu, renpolflu, this.getUserDetails().getIdSector());
					this.sumaFfBuff = this.getSum(this.orderList);
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
				Cuenta findedAcc = this.cuentaRepository
						.findOne(CuentaPredicates.findByAccounCompositeInlcudeEmpty(this.getAccFromPolide(catalog)));
				if (null != findedAcc) {
					catalog.setConcep(findedAcc.getNomcta());
					this.polideSelect.setConcep(findedAcc.getNomcta());
					this.activateRowEdit(catalog.getIndex());
				}
			} else {

				LOGGER.info(":: Editando Fila salaryScaleManualMB  " + catalog.getIndex() + "::" + catalog);
				errorMsg = new StringBuilder();
				if (this.validate(catalog, errorMsg)) {
					createRow();
				}
				RequestContext.getCurrentInstance().execute("executeClickHideButton();");
			}
		}
		// listPolide =
		// this.reIndexWithoutRenpol((conPolizSirvice.findPoliceBy(polien.getTippol(),
		// polien.getMespol(),
		// polien.getConpol(), this.getUserDetails().getIdSector())));
	}

	/** The Constant TITLE_INSERT_REGEXP. */
	private static final String TITLE_INSERT_REGEXP = FrontProperty
			.getPropertyValue("catalog.message.title.insert.regexp");

	/**
	 * Checks if is valid account.
	 *
	 * @param polide the polide
	 * @param errorMsg the error msg
	 * @return true, if is valid account
	 */
	private boolean isValidAccount(Polide polide, StringBuilder errorMsg) {
		Boolean toReturn = !StringUtils.isEmpty(polide.getCuenta()) && !polide.getCuenta().matches("(0-9){4}");
		if (toReturn) {

			if (polide.getId() == null || polide.getId() == 0) {
				BigDecimal maxpol = getLastRenpol();
				if (polide.getRenpol().compareTo( maxpol) != 0) {
					if (polide.getRenpol().compareTo(maxpol) > 0) {
						errorMsg.append("El numero del renglon no puede ser mayor al maximo de renglones de la poliza");
					} else {
						if (polideRepository
								.exists(PolidePredicate.findOneComposite(polien.getTippol(), polien.getMespol(),
										polien.getConpol(), polide.getRenpol(), this.getUserDetails().getIdSector()))) {
							errorMsg.append("El numero del renglon ya esta asignado en otro concepto");
						}
					}
				}
			} else {
				Polide buff = this.polideRepository.findOne(polide.getId());
				if (polide.getRenpol().intValue() != buff.getRenpol().intValue()) {
					BigDecimal maxpol = getLastRenpol();
					if (polide.getRenpol().compareTo(maxpol) > 0) {
						errorMsg.append("El numero del renglon no puede ser mayor al maximo de renglones de la poliza");
					} else {
						if (polideRepository
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

	/** The cuenta repository. */
	@ManagedProperty(value = "#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/**
	 * Exist account last level.
	 *
	 * @param polide the polide
	 * @param errorMsg the error msg
	 * @return true, if successful
	 */
	private boolean existAccountLastLevel(Polide polide, StringBuilder errorMsg) {
		return this.cuentaRepository.exists(CuentaPredicates.findByPolideCompositeLastLevelInlcudeEmpty(polide,
				this.getUserDetails().getIdSector()));
	}

	/**
	 * Exist account.
	 *
	 * @param polide the polide
	 * @param errorMsg the error msg
	 * @return true, if successful
	 */
	private boolean existAccount(Polide polide, StringBuilder errorMsg) {
		return this.cuentaRepository.exists(
				CuentaPredicates.findByPolideCompositeInlcudeEmpty(polide, this.getUserDetails().getIdSector()));
	}

	/**
	 * Validate.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if successful
	 */
	private boolean validate(Polide catalog, StringBuilder errorMsg) {
		if (null != catalog) {
			if (this.isValidCapture(catalog)) {

				if (this.accountService.is5xAccount(catalog.getCuenta())) {
					catalog.setScta(accountService.fillRightZeros(catalog.getScta(), LENGTH_SECOND_LEVEL));
					catalog.setSscta(accountService.fillRightZeros(catalog.getSscta(), LENGTH_THIRD_LEVEL));
					catalog.setSsscta(accountService.fillRightZeros(catalog.getSsscta(), LENGTH_FOUR_LEVEL));
				} else {
					catalog.setScta(accountService.fillZeros(catalog.getScta(), LENGTH_SECOND_LEVEL));
					catalog.setSscta(accountService.fillZeros(catalog.getSscta(), LENGTH_THIRD_LEVEL));
					catalog.setSsscta(accountService.fillZeros(catalog.getSsscta(), LENGTH_FOUR_LEVEL));
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
			errorMsg.append("Error al editar el detalle de poliza. ");
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
	 * @param lastRow            the lastRow to set
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
		int index = listPolide.isEmpty() ? 0 : listPolide.size() - 1;
		Polide selected = listPolide.get(index);

		if (!listPolide.isEmpty()) {
			selected = this.persistencePolide3(selected);
			// listPolide.add(selected);
			List<Polide> adds = this.cuentaAdicionalService.getAdds(selected, this.getUserDetails().getIdSector());
			if (!CollectionUtils.isEmpty(adds)) {
				for (Polide polide : adds) {
					polide.setRenpol(this.getLastRenpol());
					polide = this.persistencePolide3(polide);
					listPolide.add(polide);
				}
				this.listPolide = this.reIndexWithoutRenpol(listPolide);
			}
			this.persistencePoliza2WithFlow(this.listPolide, selected);
		}

		this.lastRow = this.listPolide.get(listPolide.size() - 1);
		selected = new Polide();
		if (this.repeatConcept && this.lastRow != null) {
			selected.setCuenta(this.lastRow.getCuenta());
			selected.setScta(this.lastRow.getScta());
			selected.setSscta(this.lastRow.getSscta());
			selected.setSsscta(this.lastRow.getSsscta());
			selected.setSssscta(this.lastRow.getSssscta());
			selected.setConcep(this.lastRow.getConcep());
			selected.setRefpol(this.lastRow.getRefpol());
			selected.setDn(this.lastRow.getDn());
		}
		selected.setIndex(listPolide.isEmpty() ? 0 : listPolide.size());
		selected.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
		selected.setRenpol(listPolide.isEmpty() ? BigDecimal.ONE : this.getLastRenpol());
		this.listPolide.add(selected.getIndex(), selected);
		this.listPolide = this.reIndexWithoutRenpol(listPolide);
		// this.activateRowEdit(selected.getIndex());

	}

	/**
	 * Gets the last renpol.
	 *
	 * @return the last renpol
	 */
	private BigDecimal getLastRenpol() {
		BigDecimal maxrenpol = this.polideRepository.nextRenpol(polien.getAnopol(), polien.getMespol(), polien.getTippol(),
				polien.getConpol(), this.getUserDetails().getIdSector());
		return maxrenpol == null ? BigDecimal.ONE : maxrenpol;
	}

	/**
	 * Repetir concepto.
	 */
	public void repetirConcepto() {
		LOGGER.info("repetirConcepto...");
		if (listPolide == null || listPolide.isEmpty()) {
			LOGGER.info("No existen pólizas.");
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "No existen pólizas.");
			return;
		}
		this.depureEmptyPolides();
		int lastPolideIndex = getLastPolideIndex();
		if (lastPolideIndex == -1) {
			LOGGER.info("No existen pólizas persistidas.");
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "No existen pólizas persistidas.");
			return;
		}
		Polide pol = persistencePolide2(listPolide, lastPolideIndex, true);

		listPolide.add(lastPolideIndex + 1, pol);
		this.listPolide = this.reIndex(listPolide);
		this.persistencePoliza2(listPolide, lastPolideIndex + 1);
		activateRowEdit(listPolide.size() - 1);
	}

	/**
	 * Depure empty polides.
	 */
	public void depureEmptyPolides() {
		if (null != this.listPolide && !this.listPolide.isEmpty()) {
			if (listPolide.get(listPolide.size() - 1).getId() == null
					|| listPolide.get(listPolide.size() - 1).getId() == 0) {
				this.listPolide.remove(listPolide.size() - 1);
			}
		}
	}

	/**
	 * Gets the last polide index.
	 *
	 * @return the last polide index
	 */
	private int getLastPolideIndex() {
		for (int i = listPolide.size() - 1; i >= 0; i--) {
			Polide pol = listPolide.get(i);
			if (pol.getMespol() != null && pol.getAnopol() != null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Activa el modo de edicion en una fila.
	 * 
	 * @param index
	 *            fila a ser activada.
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
		text.append(String.format(FOUS_JQUERY, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/**
	 * Activate row edit.
	 *
	 * @param index the index
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

	/**
	 * Find byb row.
	 */
	public void findBybRow() {
		LOGGER.info("entra a leer el renglon");
		LOGGER.info("renglon:: " + renglon);
		listPolide = this.reIndexWithoutRenpol(conPolizSirvice.findRow(renglon, polien.getMespol(), polien.getConpol(),
				polien.getTippol(), this.getUserDetails().getIdSector()));
		if (listPolide.isEmpty()) {
			listPolide = polideRepository.getByAllDetail(polien.getMespol(), polien.getConpol(), polien.getTippol(),
					this.getUserDetails().getIdSector());
			this.lastRow = this.listPolide.get(listPolide.size() - 1);
		}
	}

	/**
	 * Row change listener.
	 *
	 * @param event the event
	 */
	public void rowChangeListener(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		Integer oldValue;
		if (null == event.getOldValue()) {
			oldValue = 0;
		} else {
			oldValue = Integer.valueOf(event.getOldValue().toString());
		}
		Integer newValue;
		if (null == event.getOldValue()) {
			newValue = 0;
		} else {
			newValue = Integer.valueOf(event.getNewValue().toString());
		}
		if (oldValue != newValue && newValue < listPolide.size()) {
			activateRowEdit(newValue);
		}

	}

	/**
	 * Ajax listener.
	 *
	 * @param event the event
	 */
	public void ajaxListener(AjaxBehaviorEvent event) {
		Integer newIndex = Integer.valueOf((String) ((UIOutput) event.getSource()).getValue());
		if (newIndex < listPolide.size()) {
			activateRowEdit(newIndex);
		}
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

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${view.report.path.jasper.poliza}")
	private static final String REPORT_PATH_JASPER_POLICY = FrontProperty
			.getPropertyValue("view.report.path.jasper.poliza");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.poliza}")
	private static final String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.poliza");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The poliza service. */
	@ManagedProperty("#{polizaService}")
	private PolizaService polizaService;

	/**
	 * Gets the poliza service.
	 *
	 * @return the poliza service
	 */
	public PolizaService getPolizaService() {
		return polizaService;
	}

	/**
	 * Sets the poliza service.
	 *
	 * @param polizaService the new poliza service
	 */
	public void setPolizaService(PolizaService polizaService) {
		this.polizaService = polizaService;
	}

	/**
	 * Button action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonAction(ActionEvent actionEvent) {

		if (validate()) {
			this.setRenderPdf(Boolean.TRUE);
			this.streamedContent = new DefaultStreamedContent(
					this.polizaService.getReportByTipopolMespolConpol(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
							"escudo_ecatepec.png", polien.getTippol(), polien.getMespol(), polien.getConpol(),
							polien.getConpol(), getUserDetails().getIdSector(), this.getUserDetails().getUsername()));
			try {
				this.filePdfPath = this.polizaService.savePDFFile(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
						"escudo_ecatepec.png", polien.getTippol(), polien.getMespol(), polien.getConpol(),
						polien.getConpol(), getUserDetails().getIdSector(), this.getUserDetails().getUsername());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al generar el archivo PDF.");
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al capturar los parametros.");
		}
	}

	/**
	 * Update view rows auto generated.
	 */
	public void updateViewRowsAutoGenerated() {
		LOGGER.info(":: Actualiza los registros autogenerados ");
		if (null == this.errorMsg || StringUtils.isEmpty(this.errorMsg.toString())) {
			if (!UPDATE_POLIDE.equalsIgnoreCase(this.rowEditFlag)) {
				if (this.repeatConcept) {
					this.activateRowEdit(this.listPolide.size() - 1, FOCUS_REFPOL);
				} else {
					this.activateRowEdit(this.listPolide.size() - 1);
				}
			} else {
				generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Registro editado");
			}
			this.rowEditFlag = StringUtils.EMPTY;
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMsg.toString());
			if (UPDATE_POLIDE.equalsIgnoreCase(this.rowEditFlag)) {
				this.activateRowEdit(this.polideSelect.getIndex());
			} else {
				this.activateRowEdit(this.listPolide.size() - 1);
			}
			this.rowEditFlag = StringUtils.EMPTY;
			return;
		}
		if (bandera == Boolean.TRUE) {

			RequestContext.getCurrentInstance().execute("PF('flujoefectivo').show();");
			bandera = Boolean.FALSE;
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
			listIncorrectas = conPolizSirvice.wrongPolicy(me, this.getUserDetails().getIdSector());
			if (listIncorrectas.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"No existen polizas incorrectas para el mes que intenta buscar"));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"No existen polizas incorrectas para el mes que intenta buscar"));
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

	/** The Constant AFFECTED_POLICY_ST. */
	private static final String AFFECTED_POLICY_ST = "A";

	/**
	 * Enable buttons.
	 *
	 * @param encabezado the encabezado
	 */
	public void enableButtons(Polien encabezado) {
		mesActivo = conPolizSirvice.isPoliceActive(encabezado.getMespol(), this.getUserDetails().getIdSector());

		boolean isAffectedMonth = this.polienRepository.count(PolienPredicates.byMesAfectado(encabezado.getMespol(),
				this.getUserDetails().getIdSector(), AFFECTED_POLICY_ST)) > 0;
		if (isAffectedMonth) {
			generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "El mes de la poliza ya fue afectado");
		}

		if ((this.getUserDetails().getUsername().equals(encabezado.getCappol()) && 0 < mesActivo
				&& encabezado.getStaafe().equalsIgnoreCase("N")) & checkLockPolice()) {
			// else {
			activButtons = Boolean.FALSE;
			editableTable = Boolean.TRUE;
			activaAdicionar = Boolean.TRUE;
			activaModifcar = Boolean.FALSE;
			// }
		} else {
			editableTable = Boolean.FALSE;
			activaAdicionar = Boolean.TRUE;
			activaModifcar = Boolean.TRUE;
			activButtons = Boolean.TRUE;
		}

		if (!activButtons && editableTable) {
			lockPolice();
		}

	}

	/** The exis digital. */
	@ManagedProperty("#{tcRegistraArchivoDigitalRepository}")
	private TcRegistraArchivoDigitalRepository exisDigital;

	/**
	 * Gets the exis digital.
	 *
	 * @return the exisDigital
	 */
	public TcRegistraArchivoDigitalRepository getExisDigital() {
		return exisDigital;
	}

	/**
	 * Sets the exis digital.
	 *
	 * @param exisDigital            the exisDigital to set
	 */
	public void setExisDigital(TcRegistraArchivoDigitalRepository exisDigital) {
		this.exisDigital = exisDigital;
	}

	/**
	 * Find police.
	 */
	public void findPolice() {
		LOGGER.info("entra a consulta el detalle");
		LOGGER.info("tipo::::::: " + polien.getTippol());
		LOGGER.info("mes::::::: " + polien.getMespol());
		LOGGER.info("tnumero:::: " + polien.getConpol());
		if (polien.getConpol() > 0) {
			Polien encabezado = polienRepository.getPoliza(polien.getMespol(), polien.getConpol(), polien.getTippol(),
					this.getUserDetails().getIdSector());
			if (null != encabezado) {
				listPolide = this.reIndexWithoutRenpol(polideRepository.getByAllDetail(polien.getMespol(),
						polien.getConpol(), polien.getTippol(), this.getUserDetails().getIdSector()));
				if (null != this.listPolide && !this.listPolide.isEmpty()) {
					this.lastRow = this.listPolide.get(listPolide.size() - 1);
				} else {
					this.lastRow = null;
				}
				this.enableButtons(encabezado);
				polien = encabezado;
				this.actualPage = this.polienRepository.getPage(polien.getMespol(), polien.getTippol(),
						polien.getConpol(), this.getUserDetails().getIdSector()) - 1;

				TcRegistraArchivoDigital archivoDigital = exisDigital.getPoliBby(String.valueOf(encabezado.getMespol()),
						encabezado.getConpol(), encabezado.getTippol(), this.getUserDetails().getIdSector());
				if (null != archivoDigital) {
					this.imageId = archivoDigital.getId();

				} else {
					this.imageId = 0l;
				}

			} else {
				this.lastHead();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"La poliza no se encuentra en la base");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El numero de poliza debe de ser mayor a cero");
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
				+ dataYearSystemService.getYear(this.getUserDetails().getIdSector()) + "_" + polien.getMespol() + "_"
				+ getUserDetails().getIdSector() + "_" + polien.getTippol() + "_" + polien.getConpol();
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

	/**
	 * Lock police.
	 */
	private void lockPolice() {
		ServletContext servletContext = getServletContext();
		setLockUser(getUserDetails().getUsername());
		LOGGER.info("blocking for lockUser: " + getLockUser());
		String policeKey = getPoliceKey(polien);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(policeKey,
				new LockedPolice(policeKey));
		servletContext.setAttribute(policeKey, getLockUser());
	}

	/**
	 * Check lock police.
	 *
	 * @return true, if successful
	 */
	private boolean checkLockPolice() {
		LOGGER.info("enter checkLockPolice");
		ServletContext servletContext = getServletContext();
		clearLocks();
		String policeKey = getPoliceKey(polien);
		if (servletContext.getAttribute(policeKey) != null) {
			String blockUser = (String) servletContext.getAttribute(policeKey);
			setLockUser(blockUser);
			LOGGER.info("blockUser: " + blockUser);
			if (!blockUser.equals(getUserDetails().getUsername())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"Esta Póliza está siendo ocupada por otro usuario");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return false;
			}
		} else {
			setLockUser("");
		}
		return true;
	}

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

		boolean isAffectedMonth = this.polienRepository.count(PolienPredicates.byMesAfectado(polien.getMespol(),
				this.getUserDetails().getIdSector(), AFFECTED_POLICY_ST)) > 0;

		if (!isAffectedMonth) {
			mesActivo = conPolizSirvice.isPoliceActive(polien.getMespol(), this.getUserDetails().getIdSector());
			if (mesActivo > 0) {
				listPolide.clear();
				suma1 = BigDecimal.ZERO;
				suma2 = BigDecimal.ZERO;
				suma4 = BigDecimal.ZERO;
				suma5 = BigDecimal.ZERO;
				suma6 = BigDecimal.ZERO;
				Integer conpol = 0;
				// Integer maxPol =
				// this.polienRepository.maxPolizas(polien.getMespol(),
				// polien.getTippol(),
				// this.getUserDetails().getIdSector());
				//
				// if (null == maxPol || maxPol == 0) {
				// conpol = 1;
				// } else {
				// if
				// (this.polienRepository.exists(PolienPredicates.next(polien.getTippol(),
				// polien.getMespol(),
				// polien.getConpol(), this.getUserDetails().getIdSector())) &&
				// polien.getConpol() > maxPol) {
				Copome copome = copomeService.getNextNume(polien, this.getUserDetails().getIdSector(),
						this.getUserDetails().getUsername());
				conpol = copome.getNumNex();
				// } else {
				// conpol = polien.getConpol();
				// }
				// }

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
				polien.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
				polien.setUserid(this.getUserDetails().getUsername());

				activButtons = Boolean.FALSE;
				polien.setFecpol(Calendar.getInstance().getTime());
				polien.setConpol(conpol);
				//polien.setRenpol(polien.getIndex());

				editableTable = Boolean.TRUE;
				activaAdicionar = Boolean.FALSE;
				polienRepository.save(polien);
				this.lastRow = null;
				this.nomSelectedConcept = StringUtils.EMPTY;
				this.nomSelectedAcc = StringUtils.EMPTY;
				this.actualPage = this.polienRepository.getPage(polien.getMespol(), polien.getTippol(),
						polien.getConpol(), this.getUserDetails().getIdSector()) - 1;
				lockPolice();
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
	 * @param newFecpol            the newFecpol to set
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

	// public void dateChange() {
	// System.out.println("File Date: " + this.newFecpol);
	// System.out.println("File Date polien: " + this.polien.getFecpol());
	// }

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
		LOGGER.info("entra a elimnar polizas");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::");
		Poliza poliza = polizaService.delete(polien, this.getUserDetails().getIdSector(),
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
	 * Find cuentas polizas.
	 *
	 * @param cuenta the cuenta
	 * @param sCta the s cta
	 * @param ssCta the ss cta
	 * @param sssCta the sss cta
	 * @param ssssCta the ssss cta
	 */
	public void findCuentasPolizas(String cuenta, String sCta, String ssCta, String sssCta, String ssssCta) {
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("Eenrta a revisar el caralogo de cuentas...");
		LOGGER.info("Cuenta::: " + cuenta);
		LOGGER.info("sCuenta::::::::" + sCta);
		LOGGER.info("ssCuenta:::::::" + ssCta);
		LOGGER.info("sssCuenta::::::" + sssCta);
		LOGGER.info("ssssCuenta::::::" + ssssCta);
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		listCuenta = conPolizSirvice.findCuentas(cuenta, sCta, ssCta, sssCta, ssssCta,
				this.getUserDetails().getIdSector());
	}

	/**
	 * Find all counts.
	 */
	public void findAllCounts() {
		listCuenta = conPolizSirvice.findAllCounts(this.getUserDetails().getIdSector());
	}

	// private Polide persistencePoliza(List<Polide> listPoliSelect, Integer
	// index) {
	// this.persistencePoliza2(listPoliSelect, index);
	// Polide pol = persistencePolide(listPoliSelect, index, false);
	// listPoliSelect.set(index, pol);
	// return pol;
	// }

	/** The Constant _8XXX_ACC. */
	private static final String _8XXX_ACC = "8([0-9]{3})";

	/**
	 * Persistence poliza 2.
	 *
	 * @param listPoliSelect the list poli select
	 * @param index the index
	 */
	public void persistencePoliza2(List<Polide> listPoliSelect, Integer index) {
		this.persistencePolizaOnDeleteRow(listPoliSelect);
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
			bandera = Boolean.TRUE;
			orderList.clear();
			this.sumaFfBuff = BigDecimal.ZERO;
		} else {
			bandera = Boolean.FALSE;
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
	 * @param listPoliSelect the list poli select
	 * @param polide the polide
	 */
	public void persistencePoliza2WithFlow(List<Polide> listPoliSelect, Polide polide) {
		this.persistencePolizaOnDeleteRow(listPoliSelect);
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
			bandera = Boolean.TRUE;
			this.sumaFfBuff = BigDecimal.ZERO;
			orderList.clear();

		} else {
			bandera = Boolean.FALSE;
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
	 *
	 * @param listPoliSelect the list poli select
	 */
	public void persistencePolizaOnDeleteRow(List<Polide> listPoliSelect) {
		// listPolifl.clear();
		String clvPol = polien.getTippol() + " " + StringUtils.leftPad(String.valueOf(polien.getMespol()), 2, "0") + " "
				+ StringUtils.leftPad(String.valueOf(polien.getConpol()), 4, "0");
		polien.setClvpol(clvPol);
		polien.setIdsector(this.getUserDetails().getIdSector());
		polien.setUserid(this.getUserDetails().getUsername());
		polien.setCappol(this.getUserDetails().getUsername());
		polien.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
		polien.setFecafec(Calendar.getInstance().getTime());
		polien.setFeccap(Calendar.getInstance().getTime());
		polien.setStaafe("N");
		this.suma1 = BigDecimal.ZERO;
		this.suma2 = BigDecimal.ZERO;
		this.suma5 = BigDecimal.ZERO;
		this.suma6 = BigDecimal.ZERO;
		for (Polide polide : listPoliSelect) {
			suma1 = suma1.add(polide.getCanpol() == null ? BigDecimal.ZERO : polide.getCanpol());
			suma2 = suma2.add(polide.getCanpolh() == null ? BigDecimal.ZERO : polide.getCanpolh());
			if (polide.getCuenta().matches(_8XXX_ACC)) {
				suma6 = suma6.add(polide.getCanpolh() == null ? BigDecimal.ZERO : polide.getCanpolh());
				suma5 = suma5.add(polide.getCanpol() == null ? BigDecimal.ZERO : polide.getCanpol());
			}
		}
		polien.setCta600(suma6);
		polien.setCtc600(suma5);
		//polien.setRenpol(listPoliSelect.size());
		// suma3 =
		if (suma1.compareTo(suma2) == 0 && suma5.compareTo(suma6) == 0) {
			polien.setStapol("C");
		} else {
			polien.setStapol("I");
		}
		if (listPolifl != null) {
			listPolifl.clear();
		}
		polien.setCdebe(suma1);
		polien.setChaber(suma2);
		polien = polienRepository.save(polien);

	}

	/**
	 * Persistence polide 3.
	 *
	 * @param pol the pol
	 * @return the polide
	 */
	private Polide persistencePolide3(Polide pol) {
		LOGGER.info("pol: " + ToStringBuilder.reflectionToString(pol));
		pol.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
		pol.setIdsector(this.getUserDetails().getIdSector());
		pol.setUserid(this.getUserDetails().getUsername());
		pol.setMespol(polien.getMespol());
		pol.setTippol(polien.getTippol());
		pol.setConpol(polien.getConpol());
		LOGGER.info("det: " + pol);
		pol = polideRepository.save(pol);
		return pol;
	}

	// private Polide persistencePolide(List<Polide> listPoliSelect, Integer
	// index, boolean increment) {
	// Polide pol = listPoliSelect.get(index);
	// LOGGER.info("pol: " + pol);
	// pol.setRenpol(listPoliSelect.get(index).getRenpol());
	// if (increment) {
	// pol.setRenpol(new BigDecimal(pol.getRenpol().intValue() + 1));
	// }
	// pol.setAnopol(dataYearSystemService.getYear());
	// pol.setIdsector(this.getUserDetails().getIdSector());
	// pol.setUserid(this.getUserDetails().getUsername());
	// pol.setMespol(polien.getMespol());
	// pol.setTippol(polien.getTippol());
	// pol.setConpol(polien.getConpol());
	// LOGGER.info("det: " + pol);
	// pol = polideRepository.save(pol);
	// return pol;
	// }

	/**
	 * Persistence polide 2.
	 *
	 * @param listPoliSelect the list poli select
	 * @param index the index
	 * @param increment the increment
	 * @return the polide
	 */
	private Polide persistencePolide2(List<Polide> listPoliSelect, Integer index, boolean increment) {
		Polide pol = SerializationUtils.clone(listPoliSelect.get(index));
		pol.setId(null);
		LOGGER.info("pol: " + pol);
		// pol.setRenpol(listPoliSelect.get(index).getRenpol());
		if (increment) {
			pol.setRenpol(pol.getRenpol().add(BigDecimal.ONE));
		}
		pol.setAnopol(dataYearSystemService.getYear(this.getUserDetails().getIdSector()));
		pol.setIdsector(this.getUserDetails().getIdSector());
		pol.setUserid(this.getUserDetails().getUsername());
		pol.setMespol(polien.getMespol());
		pol.setTippol(polien.getTippol());
		pol.setConpol(polien.getConpol());
		pol.setRenpol(this.getLastRenpol());
		pol.setRefpol(null);
		pol.setDn(null);
		pol.setCanpol(null);
		pol.setCanpolh(null);
		pol.setCuenta1(null);
		LOGGER.info("det: " + pol);
		pol = polideRepository.save(pol);
		return pol;
	}

	/**
	 * On count ex row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onCountExRowDblClckSelect(final SelectEvent event) {
		this.changeCountExp();
		// rest of your logic
	}

	/**
	 * Change count exp.
	 */
	/*
	 * 
	 */
	public void changeCountExp() {
		LOGGER.info(":: Cambiar Cunta ");
		rowSelected.setCuenta(mapCuentaExp.get(rowSelected.getIndex()).getCuenta());
		rowSelected.setScuenta(mapCuentaExp.get(rowSelected.getIndex()).getScuenta());
		rowSelected.setSscuenta(mapCuentaExp.get(rowSelected.getIndex()).getSscuenta());
		rowSelected.setSsscuenta(mapCuentaExp.get(rowSelected.getIndex()).getSsscuenta());
		rowSelected.setSssscuenta(mapCuentaExp.get(rowSelected.getIndex()).getSssscuenta());
		rowSelected.setNomcta(mapCuentaExp.get(rowSelected.getIndex()).getNomcta());
		activateRowEdit(rowSelected.getIndex());
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
	 * Gets the cuenta poliza data model.
	 *
	 * @return the cuenta poliza data model
	 */
	public CuentaPolizaDataModel getCuentaPolizaDataModel() {
		return cuentaPolizaDataModel;
	}

	/**
	 * Sets the cuenta poliza data model.
	 *
	 * @param cuentaPolizaDataModel the new cuenta poliza data model
	 */
	public void setCuentaPolizaDataModel(CuentaPolizaDataModel cuentaPolizaDataModel) {
		cuentaPolizaDataModel.setIdSector(this.getUserDetails().getIdSector());
		this.cuentaPolizaDataModel = cuentaPolizaDataModel;
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
		Polide selected = this.getPolideSelect();
		// listPolide.get(index);

		selected.setCuenta(buff.getCuenta());
		selected.setScta(buff.getScuenta());
		selected.setSscta(buff.getSscuenta());
		selected.setSsscta(buff.getSsscuenta());
		selected.setSssscta(buff.getSssscuenta());
		if (StringUtils.isBlank(selected.getConcep())) {
			selected.setConcep(buff.getNomcta());
		}
		activateRowEdit(selected.getIndex());
	}

	/**
	 * Cuentachanged.
	 *
	 * @param e the e
	 */
	public void cuentachanged(ValueChangeEvent e) {
		// assign new value to localeCode
		if (null != e.getNewValue()) {
			// this.getPolideSelect().setCuenta(e.getNewValue().toString());
			int index = listPolide.isEmpty() ? 0 : listPolide.size() - 1;
			// Polide newPolien = new Polide();
			Polide selected =
					// this.getPolideSelect();
					listPolide.get(index);
			selected.setCuenta1(e.getNewValue().toString());
			activateRowEdit(0);
		}
	}

	/**
	 * Gets the cuenta adicional service.
	 *
	 * @return the cuentaAdicionalService
	 */
	public CuentaAdicionalService getCuentaAdicionalService() {
		return cuentaAdicionalService;
	}

	/**
	 * Sets the cuenta adicional service.
	 *
	 * @param cuentaAdicionalService            the cuentaAdicionalService to set
	 */
	public void setCuentaAdicionalService(CuentaAdicionalService cuentaAdicionalService) {
		this.cuentaAdicionalService = cuentaAdicionalService;
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
	 * Gets the account service.
	 *
	 * @return the accountService
	 */
	public AccountService getAccountService() {
		return accountService;
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService            the accountService to set
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuentaRepository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository            the cuentaRepository to set
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
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
	 * @param suma1            the suma1 to set
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
	 * @param suma2            the suma2 to set
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
	 * @param errorMsg            the errorMsg to set
	 */
	public void setErrorMsg(StringBuilder errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Validate flujo.
	 *
	 * @param cargo the cargo
	 * @param abono the abono
	 * @param cveFlujo the cve flujo
	 * @param flujoE the flujo E
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

	/** The Constant ERROR_MSG. */
	private static final String ERROR_MSG = "errorMsg";

	/** The Constant ERROR. */
	private static final String ERROR = "Error";

	/**
	 * Display error msg.
	 *
	 * @param msg the msg
	 */
	private static void displayErrorMsg(final String msg) {
		FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, msg);
		FacesContext.getCurrentInstance().addMessage(ERROR_MSG, errorMsg);
	}

	/** The Constant FE_ACCS. */
	private static final List<String> FE_ACCS = new ArrayList<String>();

	static {
		FE_ACCS.add("1111");
		FE_ACCS.add("1112");
		FE_ACCS.add("1113");
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

	/** The Constant PAGE_SIZE. */
	private static final Integer PAGE_SIZE = 1;

	/** The Constant POLEIN_TIO_MES_CONT. */
	private static final Sort POLEIN_TIO_MES_CONT = new Sort(Sort.Direction.ASC, "tippol", "mespol", "conpol");
	
	/** The Constant FIND_ACC_NAME. */
	private static final String FIND_ACC_NAME = "FIND_ACC_NAME";
	
	/** The Constant UPDATE_POLIDE. */
	private static final String UPDATE_POLIDE = "UPDATE_POLIDE";

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
		this.count = this.polienRepository.count(PolienPredicates.byTipoMes(this.polien.getTippol(),
				this.polien.getMespol(), this.getUserDetails().getIdSector()));
		if (0 < this.count) {
			this.actualPage = this.count.intValue() - 1;
		} else {
			this.actualIndex = 0;
		}

		this.doFind(this.actualPage);
	}

	/**
	 * Do find.
	 *
	 * @param pageIndex the page index
	 */
	private void doFind(Integer pageIndex) {
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, POLEIN_TIO_MES_CONT);
		Page<Polien> page = this.polienRepository.findAll(PolienPredicates.byTipoMes(this.polien.getTippol(),
				this.polien.getMespol(), this.getUserDetails().getIdSector()), pageable);
		if (page.getContent().isEmpty()) {
			polien.setFeccap(Calendar.getInstance().getTime());
			polien.setPolclv1(StringUtils.EMPTY);
			polien.setStapol(StringUtils.EMPTY);
			polien.setStaafe(StringUtils.EMPTY);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					String.format("No existen polizas para el Tipo=%1$s y Mes=%2$s", this.polien.getTippol(),
							this.polien.getMespol()));
		} else {
			polien = page.getContent().get(0);
			listPolide = polideRepository.getByAllDetail(polien.getMespol(), polien.getConpol(), polien.getTippol(),
					this.getUserDetails().getIdSector());
			listPolide = reIndexWithoutRenpol(listPolide);
			if (null == listPolide || listPolide.isEmpty()) {
				this.lastRow = null;
			} else {
				this.lastRow = this.listPolide.get(listPolide.size() - 1);
			}
			this.count = this.polienRepository.count(PolienPredicates.byTipoMes(this.polien.getTippol(),
					this.polien.getMespol(), this.getUserDetails().getIdSector()));
			this.enableButtons(polien);
			TcRegistraArchivoDigital archivoDigital = exisDigital.getPoliBby(String.valueOf(polien.getMespol()),
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
	private Integer actualIndex;

	/**
	 * Change row.
	 */
	public void changeRow() {
		Integer row = Integer.valueOf(this.getRenglon().intValue());
		Polide detail = IterableUtils.find(this.listPolide, new Predicate<Polide>() {
			@Override
			public boolean evaluate(Polide o) {
				return o.getRenpol().intValue() == row;
			}
		});

		if (null != detail) {
			this.actualIndex = detail.getIndex();
			this.doSeekRow(this.actualIndex);
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
	 * @param actualIndex            the actualIndex to set
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
		if (this.actualIndex < this.listPolide.size() - 1) {
			this.actualIndex++;
		}
		this.doSeekRow(this.actualIndex);
	}

	/**
	 * Change end row.
	 */
	public void changeEndRow() {
		this.actualIndex = this.listPolide.size() - 1;
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
		return IterableUtils.find(this.listPolide, new Predicate<Polide>() {
			@Override
			public boolean evaluate(Polide o) {
				return o.getIndex() == row;
			}
		});
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
	 * @param nomSelectedConcept            the nomSelectedConcept to set
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
	 * @param nomSelectedAcc            the nomSelectedAcc to set
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
		this.doChangeConeptAccName(selectedPolide);

	}

	/**
	 * Do change conept acc name.
	 *
	 * @param selectedPolide the selected polide
	 */
	private void doChangeConeptAccName(Polide selectedPolide) {
		if (null != selectedPolide && (null != selectedPolide.getId() && selectedPolide.getId() > 0)) {
			this.actualIndex = selectedPolide.getIndex();
			this.nomSelectedConcept = selectedPolide.getConcep();
			Cuenta findedAcc = this.cuentaRepository
					.findOne(CuentaPredicates.findByAccounCompositeInlcudeEmpty(this.getAccFromPolide(selectedPolide)));
			if (null != findedAcc) {
				this.nomSelectedAcc = findedAcc.getNomcta();
			}
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
	 * @param rowEditFlag            the rowEditFlag to set
	 */
	public void setRowEditFlag(String rowEditFlag) {
		this.rowEditFlag = rowEditFlag;
	}

	/** The inmediato. */
	private Boolean inmediato = Boolean.FALSE;

	/** The Constant FOCUS_REFPOL. */
	private static final String FOCUS_REFPOL = "$(document.getElementById('form1:objects:%1$s:refpol')).focus();";

	/** The Constant ACCOUNT_TYPE_8217_REGEXP. */
	private static final String ACCOUNT_TYPE_8217_REGEXP = FrontProperty
			.getPropertyValue("accountService.account.8217.pattern");

	/**
	 * Checks if is 5 x or 8 x acc.
	 *
	 * @param acc the acc
	 * @return true, if is 5 x or 8 x acc
	 */
	private boolean is5xOr8xAcc(String acc) {
		return this.accountService.is5xAccount(acc) || acc.matches(ACCOUNT_TYPE_8217_REGEXP);
	}

	/**
	 * Find acc name.
	 */
	public void findAccName() {
		this.rowEditFlag = FIND_ACC_NAME;
		this.inmediato = Boolean.TRUE;
		if (!repeatConcept && StringUtils.isBlank(this.getPolideSelect().getConcep())) {
			if (this.is5xOr8xAcc(this.getPolideSelect().getCuenta())) {
				this.getPolideSelect()
						.setScta(accountService.fillRightZeros(this.getPolideSelect().getScta(), LENGTH_SECOND_LEVEL));
				this.getPolideSelect()
						.setSscta(accountService.fillRightZeros(this.getPolideSelect().getSscta(), LENGTH_THIRD_LEVEL));
				this.getPolideSelect().setSsscta(
						accountService.fillRightZeros(this.getPolideSelect().getSsscta(), LENGTH_FOUR_LEVEL));
			} else {
				this.getPolideSelect()
						.setScta(accountService.fillZeros(this.getPolideSelect().getScta(), LENGTH_SECOND_LEVEL));
				this.getPolideSelect()
						.setSscta(accountService.fillZeros(this.getPolideSelect().getSscta(), LENGTH_THIRD_LEVEL));
				this.getPolideSelect()
						.setSsscta(accountService.fillZeros(this.getPolideSelect().getSsscta(), LENGTH_FOUR_LEVEL));
			}

			Cuenta findedAcc = this.cuentaRepository.findOne(CuentaPredicates.findByAccounCompositeInlcudeEmpty(
					(Cuenta) UtilJPA.fillPropertyStringIfNull(this.getAccFromPolide(this.getPolideSelect()))));
			if (null != findedAcc) {
				this.getPolideSelect().setConcep(findedAcc.getNomcta());
			}
			this.activateRowEdit(this.getPolideSelect().getIndex(), FOCUS_REFPOL);
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
	 * @param inmediato            the inmediato to set
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
		Polien toSave = this.polienRepository.findOne(this.polien.getId());
		toSave.setPolclv1(this.polien.getPolclv1());
		this.polienRepository.save(toSave);
		generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Encabezado actualizado");
	}

	/**
	 * Save update date.
	 */
	public void saveUpdateDate() {
		Polien toSave = this.polienRepository.findOne(this.polien.getId());
		toSave.setFecpol(this.newFecpol);
		this.polienRepository.save(toSave);
		generateNotificationFront(SEVERITY_INFO, StringUtils.EMPTY, "Encabezado actualizado");
	}

	/**
	 * Cancel save update date.
	 */
	public void cancelSaveUpdateDate() {
		this.polien = this.polienRepository.findOne(this.polien.getId());
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
					" La poliza aun no esta digitalizada");
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
	 * @param imageId            the imageId to set
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * Checks if is repeat concept.
	 *
	 * @return the repeatConcept
	 */
	public boolean isRepeatConcept() {
		return repeatConcept;
	}

	/**
	 * Sets the repeat concept.
	 *
	 * @param repeatConcept            the repeatConcept to set
	 */
	public void setRepeatConcept(boolean repeatConcept) {
		this.repeatConcept = repeatConcept;
	}

	/**
	 * Cuenta change listener.
	 *
	 * @param event the event
	 */
	public void cuentaChangeListener(AjaxBehaviorEvent event) {
		if (null != ((UIOutput) event.getSource()).getValue()) {
			if (StringUtils.isEmpty(this.polideSelect.getCuenta()) && this.repeatConcept) {
				if (null != this.getLastRow()) {
					this.getPolideSelect().setCuenta(this.getLastRow().getCuenta());
				}
			}
			activateRowEdit(this.polideSelect.getIndex());
		} else {
			if (StringUtils.isEmpty(this.polideSelect.getCuenta()) && this.repeatConcept) {
				if (null != this.getLastRow()) {
					this.getPolideSelect().setCuenta(this.getLastRow().getCuenta());
				}
			}
			activateRowEdit(this.polideSelect.getIndex());
		}
	}

	/**
	 * Scuenta change listener.
	 *
	 * @param event the event
	 */
	public void scuentaChangeListener(AjaxBehaviorEvent event) {
		if (null != ((UIOutput) event.getSource()).getValue()) {
			// if (StringUtils.isEmpty(this.polideSelect.getScta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setScta(this.getLastRow().getScta());
			// }
			// } else {
			if (this.is5xOr8xAcc(this.getPolideSelect().getCuenta())) {
				this.getPolideSelect()
						.setScta(accountService.fillRightZeros(this.getPolideSelect().getScta(), LENGTH_SECOND_LEVEL));
			} else {
				this.getPolideSelect()
						.setScta(accountService.fillZeros(this.getPolideSelect().getScta(), LENGTH_SECOND_LEVEL));
			}
			// }
			activateRowEdit(this.polideSelect.getIndex());
		} else {
			// if (StringUtils.isEmpty(this.polideSelect.getScta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setScta(this.getLastRow().getScta());
			// }
			// }
			activateRowEdit(this.polideSelect.getIndex());
		}
	}

	/**
	 * Sscuenta change listener.
	 *
	 * @param event the event
	 */
	public void sscuentaChangeListener(AjaxBehaviorEvent event) {
		if (null != ((UIOutput) event.getSource()).getValue()) {
			// if (StringUtils.isEmpty(this.polideSelect.getSscta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setSscta(this.getLastRow().getSscta());
			// }
			// } else {
			if (this.is5xOr8xAcc(this.getPolideSelect().getCuenta())) {
				this.getPolideSelect()
						.setSscta(accountService.fillRightZeros(this.getPolideSelect().getSscta(), LENGTH_THIRD_LEVEL));
			} else {
				this.getPolideSelect()
						.setSscta(accountService.fillZeros(this.getPolideSelect().getSscta(), LENGTH_THIRD_LEVEL));
			}
			// }
			activateRowEdit(this.polideSelect.getIndex());
		} else {
			// if (StringUtils.isEmpty(this.polideSelect.getSscta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setSscta(this.getLastRow().getSscta());
			// }
			// }
			activateRowEdit(this.polideSelect.getIndex());
		}
	}

	/**
	 * Ssscuenta change listener.
	 *
	 * @param event the event
	 */
	public void ssscuentaChangeListener(AjaxBehaviorEvent event) {
		if (null != ((UIOutput) event.getSource()).getValue()) {
			// if (StringUtils.isEmpty(this.polideSelect.getSsscta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setSsscta(this.getLastRow().getSsscta());
			// }
			// } else {
			if (this.is5xOr8xAcc(this.getPolideSelect().getCuenta())) {
				this.getPolideSelect().setSsscta(
						accountService.fillRightZeros(this.getPolideSelect().getSsscta(), LENGTH_FOUR_LEVEL));
			} else {
				this.getPolideSelect()
						.setSsscta(accountService.fillZeros(this.getPolideSelect().getSsscta(), LENGTH_FOUR_LEVEL));
			}
			// }
			activateRowEdit(this.polideSelect.getIndex());
		} else {
			// if (StringUtils.isEmpty(this.polideSelect.getSsscta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setSsscta(this.getLastRow().getSsscta());
			// }
			// }
			activateRowEdit(this.polideSelect.getIndex());
		}
	}

	/**
	 * Sssscuenta change listener.
	 *
	 * @param event the event
	 */
	public void sssscuentaChangeListener(AjaxBehaviorEvent event) {
		if (null != ((UIOutput) event.getSource()).getValue()) {
			// if (StringUtils.isEmpty(this.polideSelect.getSssscta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setSssscta(this.getLastRow().getSssscta());
			// }
			// } else {
			if (this.is5xOr8xAcc(this.getPolideSelect().getCuenta())) {
				this.getPolideSelect().setSssscta(
						accountService.fillRightZeros(this.getPolideSelect().getSsscta(), LENGTH_FIVE_LEVEL));
			} else {
				this.getPolideSelect()
						.setSssscta(accountService.fillZeros(this.getPolideSelect().getSssscta(), LENGTH_FIVE_LEVEL));
			}
			// }
			activateRowEdit(this.polideSelect.getIndex());
		} else {
			// if (StringUtils.isEmpty(this.polideSelect.getSssscta()) &&
			// this.repeatConcept) {
			// if (null != this.getLastRow()) {
			// this.getPolideSelect().setSssscta(this.getLastRow().getSssscta());
			// }
			// }
			activateRowEdit(this.polideSelect.getIndex());
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
			if (null != polienRepository.getByFirstMonth(this.getUserDetails().getIdSector())) {
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
}
