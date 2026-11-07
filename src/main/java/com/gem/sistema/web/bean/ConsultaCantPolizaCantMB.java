package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaCantPolizaCantMB.
 */
@ManagedBean(name = "consultaCantPolizaCantMB")
@ViewScoped
public class ConsultaCantPolizaCantMB extends AbstractMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7214165758802106972L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaCantPolizaCantMB.class);

	/** The repository. */
	@ManagedProperty(value = "#{polideRepository}")
	private PolideRepository repository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The mes. */
	private Integer mes;

	/** The cantidad. */
	private BigDecimal cantidad;

	/** The resultados. */
	private List<Polide> resultados;

	/** The row selected. */
	private Polide rowSelected;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct consultaCantPolizaCantMB ");
		this.mes = 1;
		this.cantidad = null;
	}

	/**
	 * On page load.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogAccountsMB  ");
	}

	/**
	 * Consult list.
	 */
	public void consultList() {
		rowSelected = null;
		if (cantidad == null || mes == null) {
			return;
		}

		try {
			if (this.validatePolicyService.isOpenMonth(mes, null,
					this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				int idSector = getUserDetails().getIdSector();
				resultados = repository.getByMesAndCantidad(mes, cantidad, idSector);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultados = new ArrayList<Polide>();
		}
		if (resultados == null || resultados.isEmpty()) {
			LOGGER.info("no hay resultados...");
		}

	}

	/**
	 * On row select.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowSelect(SelectEvent event) {
		rowSelected = (Polide) event.getObject();
		LOGGER.info("rowSelected: " + rowSelected);
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the resultados.
	 *
	 * @return the resultados
	 */
	public List<Polide> getResultados() {
		return resultados;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository
	 *            the new repository
	 */
	public void setRepository(PolideRepository repository) {
		this.repository = repository;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the row selected
	 */
	public Polide getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected
	 *            the new row selected
	 */
	public void setRowSelected(Polide rowSelected) {
		this.rowSelected = rowSelected;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
