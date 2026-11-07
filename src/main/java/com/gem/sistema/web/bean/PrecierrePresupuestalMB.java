package com.gem.sistema.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PolizaService;
// TODO: Auto-generated Javadoc

/**
 * The Class PrecierrePresupuestalMB.
 */
@ManagedBean(name = "precierrePresupuestalMB")
@ViewScoped
public class PrecierrePresupuestalMB extends AbstractMB {
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The mes. */
	private Integer mes;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The poliza service. */
	@ManagedProperty("#{polizaService}")
	private PolizaService polizaService;
	
	/** The activo. */
	private boolean activo = Boolean.FALSE;
	
	/** The Constant contrasena. */
	private static final String contrasena = "pres2002";
	
	/** The password. */
	private String password;

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
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
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
	 * Checks if is activo.
	 *
	 * @return true, if is activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Sets the activo.
	 *
	 * @param activo the new activo
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();
		activo =  Boolean.TRUE;
	}
	
	/**
	 * Checks if is validate pass.
	 */
	public void isValidatePass(){
		if(contrasena.equals(password)) {
			activo = Boolean.FALSE;
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Passowrd incorrecto");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	/**
	 * Afecta precierre.
	 */
	public void afectaPrecierre() {
		Poliza poliza = polizaService.afectacionPrecierre(mes, this.getUserDetails().getIdSector(), this.getUserDetails().getUsername());
		if(poliza.getErrorCode() >  0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", poliza.getMsgError());
		    FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", poliza.getMsgError());
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

}
