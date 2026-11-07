package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.CopomeService;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaCopiarMB.
 */

@Component("polizaCopiarMB")
@Scope("view")
public class PolizaCopiarMB extends AbstractMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaCopiarMB.class);

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${view.copy.policy.pass}")
	private static final String CLAVE_COPY_POLICY = FrontProperty.getPropertyValue("view.copy.policy.pass");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** Mensaje Info. */
	// @Value("${catalog.message.info}")
	protected static final String MESSAGE_INFO = FrontProperty.getPropertyValue("catalog.message.info");

	/** The Constant MESSAGE_INFO2. */
	protected static final String MESSAGE_INFO2 = FrontProperty.getPropertyValue("catalog.message.info2");

	/** The Constant MESSAGE_INFO3. */
	protected static final String MESSAGE_INFO3 = FrontProperty.getPropertyValue("catalog.message.info4");

	/** The poliza service. */
	@Value("#{polizaService}")
	private PolizaService polizaService;

	/** The cattpo repositry. */
	@Value("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The polien repository. */
	@Value("#{polienRepository}")
	private PolienRepository polienRepository;

	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes. */
	private String tcMes;

	/** The cattpo. */
	private String cattpo;

	/** The tc mes dest. */
	private String tcMesDest;

	/** The cattpo dest. */
	private String cattpoDest;

	/** The cve pass. */
	private String cvePass;

	/** The ori conpol. */
	private Integer oriConpol;

	/** The dest conpol. */
	private Integer destConpol;

	/** The render. */
	private boolean render = Boolean.TRUE;

	/** The copome service. */
	@Value("#{copomeService}")
	private CopomeService copomeService;

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PolizaCopiarMB ");
		lisCattpo = cattpoRepositry.findAll();
		listMes = tcMesRepository.findAll();
		for (Cattpo c : lisCattpo) {
			LOGGER.info("polizaMantenimientoMB" + c.getTippol());
		}
		if (!CollectionUtils.isEmpty(lisCattpo)) {
			this.cattpo = lisCattpo.get(0).getTippol();
			this.cattpoDest = lisCattpo.get(0).getTippol();
		}

		if (!CollectionUtils.isEmpty(lisCattpo)) {
			this.tcMes = listMes.get(0).getMes();
			this.tcMesDest = listMes.get(0).getMes();
		}

		if (StringUtils.isNotBlank(this.tcMesDest) && StringUtils.isNotBlank(this.cattpoDest)) {
			this.destConpol = this.getConpol(Integer.valueOf(this.tcMesDest), this.cattpoDest);
		}
		this.setCvePass(StringUtils.EMPTY);
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina PolizaCopiarMB  ");
		render = Boolean.TRUE;
	}

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
	 * Gets the cattpo repositry.
	 *
	 * @return the cattpo repositry
	 */
	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	/**
	 * Sets the cattpo repositry.
	 *
	 * @param cattpoRepositry the new cattpo repositry
	 */
	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
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
	 * Gets the ori conpol.
	 *
	 * @return the oriConpol
	 */
	public Integer getOriConpol() {
		return oriConpol;
	}

	/**
	 * Sets the ori conpol.
	 *
	 * @param oriConpol            the oriConpol to set
	 */
	public void setOriConpol(Integer oriConpol) {
		this.oriConpol = oriConpol;
	}

	/**
	 * Gets the dest conpol.
	 *
	 * @return the destConpol
	 */
	public Integer getDestConpol() {
		return destConpol;
	}

	/**
	 * Sets the dest conpol.
	 *
	 * @param destConpol            the destConpol to set
	 */
	public void setDestConpol(Integer destConpol) {
		this.destConpol = destConpol;
	}

	/**
	 * Validate pass.
	 */
	public void validatePass() {
		this.render = !CLAVE_COPY_POLICY.equals(this.cvePass);
		if (render) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Clave incorrecta.");
		} else {
			this.oriConpol = null;
		}
		this.cvePass = StringUtils.EMPTY;

	}

	/**
	 * Checks if is render.
	 *
	 * @return true, if is render
	 */
	public boolean isRender() {
		return render;
	}

	/**
	 * Sets the render.
	 *
	 * @param render the new render
	 */
	public void setRender(boolean render) {
		this.render = render;
	}

	/**
	 * Gets the tc mes.
	 *
	 * @return the tc mes
	 */
	public String getTcMes() {
		return tcMes;
	}

	/**
	 * Sets the tc mes.
	 *
	 * @param tcMes the new tc mes
	 */
	public void setTcMes(String tcMes) {
		this.tcMes = tcMes;
	}

	/**
	 * Gets the cattpo.
	 *
	 * @return the cattpo
	 */
	public String getCattpo() {
		return cattpo;
	}

	/**
	 * Sets the cattpo.
	 *
	 * @param cattpo the new cattpo
	 */
	public void setCattpo(String cattpo) {
		this.cattpo = cattpo;
	}

	/**
	 * Gets the tc mes dest.
	 *
	 * @return the tc mes dest
	 */
	public String getTcMesDest() {
		return tcMesDest;
	}

	/**
	 * Sets the tc mes dest.
	 *
	 * @param tcMesDest the new tc mes dest
	 */
	public void setTcMesDest(String tcMesDest) {
		this.tcMesDest = tcMesDest;
	}

	/**
	 * Gets the cattpo dest.
	 *
	 * @return the cattpo dest
	 */
	public String getCattpoDest() {
		return cattpoDest;
	}

	/**
	 * Sets the cattpo dest.
	 *
	 * @param cattpoDest the new cattpo dest
	 */
	public void setCattpoDest(String cattpoDest) {
		this.cattpoDest = cattpoDest;
	}

	/**
	 * Gets the cve pass.
	 *
	 * @return the cve pass
	 */
	public String getCvePass() {
		return cvePass;
	}

	/**
	 * Sets the cve pass.
	 *
	 * @param cvePass the new cve pass
	 */
	public void setCvePass(String cvePass) {
		this.cvePass = cvePass;
	}

	/**
	 * Button action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonAction(ActionEvent actionEvent) {

		String msg = this.validate();
		if (StringUtils.isEmpty(msg)) {

			Poliza poliza = new Poliza();
			poliza.setTippol(this.cattpo);
			poliza.setMespol(Integer.valueOf(this.tcMes));
			poliza.setConpol(this.oriConpol);

			Poliza polizaDest = new Poliza();
			polizaDest.setTippol(this.cattpoDest);
			polizaDest.setMespol(Integer.valueOf(this.tcMesDest));

			polizaDest.setConpol(this.getConpol(polizaDest.getMespol(), polizaDest.getTippol()));
			// GemUser user = this.getUserDetails();
			polizaDest = polizaService.copyPoliza(poliza, polizaDest, this.getUserDetails().getUsername(),
					this.getUserDetails().getIdSector());

			if (polizaDest.getErrorCode() > 0) {
				this.destConpol = this.getConpol(polizaDest.getMespol(), polizaDest.getTippol());
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, polizaDest.getMsgError());
			} else {
				this.destConpol = null;
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, polizaDest.getMsgError());
			}
		} else {
			this.destConpol = null;
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, msg);
		}

		findConpol();
	}

	/**
	 * Gets the conpol.
	 *
	 * @param mespol the mespol
	 * @param tipopol the tipopol
	 * @return the conpol
	 */
	private int getConpol(Integer mespol, String tipopol) {
		Integer conpol = 0;
		Integer maxPol = this.polienRepository.maxPolizas(mespol, tipopol, this.getUserDetails().getIdSector());

		if (null == maxPol || maxPol == 0) {
			conpol = 1;
		} else {
			// Polien polien = new Polien();
			// polien.setTippol(tipopol);
			// polien.setMespol(mespol);
			// polien.setConpol(maxPol);
			// Copome copome = copomeService.getNextNume(polien,
			// this.getUserDetails().getIdSector(),
			// this.getUserDetails().getUsername());
			conpol = maxPol + 1;
		}
		return conpol;
	}

	/**
	 * Button inverse action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonInverseAction(ActionEvent actionEvent) {
		String msg = this.validate();
		if (StringUtils.isEmpty(msg)) {
			Poliza poliza = new Poliza();
			poliza.setTippol(this.cattpo);
			poliza.setMespol(Integer.valueOf(this.tcMes));
			poliza.setConpol(this.oriConpol);
			Poliza polizaDest = new Poliza();
			polizaDest.setTippol(this.cattpoDest);
			polizaDest.setMespol(Integer.valueOf(this.tcMesDest));
			polizaDest.setConpol(this.getConpol(polizaDest.getMespol(), polizaDest.getTippol()));
			polizaDest = polizaService.inversaPoliza(poliza, polizaDest, this.getUserDetails().getUsername(),
					this.getUserDetails().getIdSector());
			if (poliza.getErrorCode() > 0) {
				this.destConpol = this.getConpol(polizaDest.getMespol(), polizaDest.getTippol());
				this.cattpoDest = polizaDest.getTippol();
				this.tcMesDest = String.valueOf(poliza.getMespol());
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, poliza.getMsgError());
			} else {
				this.destConpol = null;
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, poliza.getMsgError());
			}
		} else {
			this.destConpol = null;
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, msg);
		}
		findConpol();
	}

	/**
	 * Button negative action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonNegativeAction(ActionEvent actionEvent) {
		String msg = this.validate();
		if (StringUtils.isEmpty(msg)) {
			Poliza poliza = new Poliza();
			poliza.setTippol(this.cattpo);
			poliza.setMespol(Integer.valueOf(this.tcMes));
			poliza.setConpol(this.oriConpol);
			Poliza polizaDest = new Poliza();
			polizaDest.setTippol(this.cattpoDest);
			polizaDest.setMespol(Integer.valueOf(this.tcMesDest));
			polizaDest.setConpol(this.getConpol(polizaDest.getMespol(), polizaDest.getTippol()));
			polizaDest = polizaService.negativePoliza(poliza, polizaDest, this.getUserDetails().getUsername(),
					this.getUserDetails().getIdSector());
			if (poliza.getErrorCode() > 0) {
				this.destConpol = this.getConpol(polizaDest.getMespol(), polizaDest.getTippol());
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, poliza.getMsgError());
			} else {
				this.destConpol = null;
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, poliza.getMsgError());
			}
		} else {
			this.destConpol = null;
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, msg);
		}
		findConpol();
	}

	/** The Constant AFFECTED_POLICY_ST. */
	private static final String AFFECTED_POLICY_ST = "A";

	// private static final String MESSAGE_INFO3 =
	// FrontProperty.getPropertyValue("catalog.message.info3");

	/**
	 * Validate.
	 *
	 * @return the string
	 */
	private String validate() {
		// Integer conpol = this.getDestConpol();
		// if (conpol >
		// this.polizaService.getMaxPolienConpol(this.getCattpoDest(),
		// Integer.valueOf(this.getTcMesDest()),
		// this.getUserDetails().getIdSector())) {
		// return MESSAGE_INFO3;
		// } else {
		if (this.polienRepository.count(PolienPredicates.byMesAfectado(Integer.valueOf(this.getTcMesDest()),
				this.getUserDetails().getIdSector(), AFFECTED_POLICY_ST)) > 0) {
			return MESSAGE_INFO2;
		} else {
			if (!this.polienRepository.exists(PolienPredicates.next(this.getCattpo(), Integer.valueOf(this.getTcMes()),
					this.getOriConpol(), this.getUserDetails().getIdSector()))) {
				return MESSAGE_INFO3;
			}
		}
		// }
		return StringUtils.EMPTY;
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
	 * Find conpol.
	 */
	public void findConpol() {
		if (StringUtils.isNotBlank(this.tcMesDest) && StringUtils.isNotBlank(this.cattpoDest)) {
			this.destConpol = this.getConpol(Integer.valueOf(this.tcMesDest), this.cattpoDest);
		} else {
			this.destConpol = null;
		}
	}
}
