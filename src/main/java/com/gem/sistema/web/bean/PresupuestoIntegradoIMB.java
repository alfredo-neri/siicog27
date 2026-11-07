package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Integradoi;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.IntegradoiRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PresupuestoIntegradoIMB.
 */
@ManagedBean(name = "presupuestoIntegradoIMB")
@ViewScoped
public class PresupuestoIntegradoIMB implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PresupuestoIntegradoIMB.class);
	
	/** The integradoi. */
	private Integradoi integradoi;
	
	/** The activa button. */
	private boolean activaButton = Boolean.TRUE;
	
	/** The activa txt. */
	private boolean activaTxt    = Boolean.TRUE;
	
	/** The activa cuentas. */
	private boolean activaCuentas = Boolean.TRUE;
	
	/**
	 * Checks if is activa button.
	 *
	 * @return true, if is activa button
	 */
	public boolean isActivaButton() {
		return activaButton;
	}


	/**
	 * Sets the activa button.
	 *
	 * @param activaButton the new activa button
	 */
	public void setActivaButton(boolean activaButton) {
		this.activaButton = activaButton;
	}


	/**
	 * Checks if is activa txt.
	 *
	 * @return true, if is activa txt
	 */
	public boolean isActivaTxt() {
		return activaTxt;
	}


	/**
	 * Sets the activa txt.
	 *
	 * @param activaTxt the new activa txt
	 */
	public void setActivaTxt(boolean activaTxt) {
		this.activaTxt = activaTxt;
	}


	/**
	 * Checks if is activa cuentas.
	 *
	 * @return true, if is activa cuentas
	 */
	public boolean isActivaCuentas() {
		return activaCuentas;
	}


	/**
	 * Sets the activa cuentas.
	 *
	 * @param activaCuentas the new activa cuentas
	 */
	public void setActivaCuentas(boolean activaCuentas) {
		this.activaCuentas = activaCuentas;
	}


	/**
	 * Gets the integradoi.
	 *
	 * @return the integradoi
	 */
	public Integradoi getIntegradoi() {
		return integradoi;
	}


	/**
	 * Sets the integradoi.
	 *
	 * @param integradoi the new integradoi
	 */
	public void setIntegradoi(Integradoi integradoi) {
		this.integradoi = integradoi;
	}


	/**
	 * Gets the list integradoi.
	 *
	 * @return the list integradoi
	 */
	public List<Integradoi> getListIntegradoi() {
		return listIntegradoi;
	}


	/**
	 * Sets the list integradoi.
	 *
	 * @param listIntegradoi the new list integradoi
	 */
	public void setListIntegradoi(List<Integradoi> listIntegradoi) {
		this.listIntegradoi = listIntegradoi;
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
	 * Gets the integradoi repository.
	 *
	 * @return the integradoi repository
	 */
	public IntegradoiRepository getIntegradoiRepository() {
		return integradoiRepository;
	}


	/**
	 * Sets the integradoi repository.
	 *
	 * @param integradoiRepository the new integradoi repository
	 */
	public void setIntegradoiRepository(IntegradoiRepository integradoiRepository) {
		this.integradoiRepository = integradoiRepository;
	}


	/** The list integradoi. */
	private List<Integradoi> listIntegradoi;
	
	/** The cuenta. */
	private Cuenta cuenta;
	
	/** The list cuenta. */
	private List<Cuenta> listCuenta;
	
	
	/** The integradoi repository. */
	@ManagedProperty(value= "#{integradoiRepository}")
	private IntegradoiRepository integradoiRepository;
	
	/** The cuenta repository. */
	@ManagedProperty(value= "#{cuentaRepository}")
	private CuentaRepository cuentaRepository;
	
	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}


	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}


	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		isActivaButton();
		isActivaCuentas();
		isActivaTxt();
		
		listCuenta = cuentaRepository.findAll();
		
	}
	
	/**
	 * Find cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param scta the scta
	 * @param sscta the sscta
	 * @param ssscta the ssscta
	 * @param sssscta the sssscta
	 */
	public void findCuenta(String cuenta, String scta, String sscta, String ssscta, String sssscta) {
		
	}
	

}
