package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Pasot;
import com.gem.sistema.business.repository.catalogs.PasotRepository;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@ManagedBean
@ViewScoped
public class CaratulaPresupuestoEgresoMB implements Serializable {	

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CaratulaPresupuestoEgresoMB.class);

	/** The selected pasot. */
	private Pasot selectedPasot;

	
	private List<Pasot> listEgresos;

	private DataModelGeneric<Pasot> egresosDataModel;
	
	private Boolean bEdicion;

	/** The pasot repository. */
	@ManagedProperty(value = "#{pasotRepository}")
	private PasotRepository pasotRepository;

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		listEgresos = this.reloadData();
		egresosDataModel = new DataModelGeneric<Pasot>(listEgresos);
		LOGGER.info(":: En postconsruct Pasot ");
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina CaratulaPresupuestoEgresoMB  ");
		this.egresosDataModel.setListT(reloadData());
	}
	
	public List<Pasot> reloadData() {
		listEgresos = this.pasotRepository.findAll();
		return listEgresos;
	}
	
	public void onRowCancel(RowEditEvent event) {
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Información!", "¡Edición cancelada!");
		listEgresos = this.reloadData();
		this.egresosDataModel.setListT(listEgresos);
	}

	public void onInitRowEdit(final RowEditEvent event) {
		selectedPasot = (Pasot) event.getObject();
		if (null != event.getObject()) {

			if (null != selectedPasot.getId() && selectedPasot.getId() != 0) {
				this.bEdicion = Boolean.TRUE;
			} else {
				this.bEdicion = Boolean.FALSE;
			}
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		selectedPasot = (Pasot) event.getObject();
		selectedPasot.setIdRef(BigInteger.ZERO.longValue());
		selectedPasot.setIdsector(BigInteger.ONE.intValue());

		if (bEdicion) {
			this.pasotRepository.save(selectedPasot);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Información!",
					"¡El registro se ha actualizado correctamente!");
		} 

	}


	/**
	 * Gets the selected pasot.
	 *
	 * @return the selected pasot
	 */
	public Pasot getSelectedPasot() {
		return selectedPasot;
	}

	/**
	 * Sets the selected pasot.
	 *
	 * @param selectedPasot the new selected pasot
	 */
	public void setSelectedPasot(Pasot selectedPasot) {
		this.selectedPasot = selectedPasot;
	}

	/**
	 * Gets the pasot repository.
	 *
	 * @return the pasot repository
	 */
	public PasotRepository getPasotRepository() {
		return pasotRepository;
	}

	/**
	 * Sets the pasot repository.
	 *
	 * @param pasotRepository the new pasot repository
	 */
	public void setPasotRepository(PasotRepository pasotRepository) {
		this.pasotRepository = pasotRepository;
	}

	public DataModelGeneric<Pasot> getEgresosDataModel() {
		return egresosDataModel;
	}

	public List<Pasot> getListEgresos() {
		return listEgresos;
	}

	public void setListEgresos(List<Pasot> listEgresos) {
		this.listEgresos = listEgresos;
	}

	public void setEgresosDataModel(DataModelGeneric<Pasot> egresosDataModel) {
		this.egresosDataModel = egresosDataModel;
	}

	public Boolean getbEdicion() {
		return bEdicion;
	}

	public void setbEdicion(Boolean bEdicion) {
		this.bEdicion = bEdicion;
	}
	
}
