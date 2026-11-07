package com.gem.sistema.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PolizaService;

// TODO: Auto-generated Javadoc
/**
 * The Class DesafectacionPrecierreMB.
 */
@ManagedBean(name = "desafectacionPrecierreMB")
@ViewScoped
public class DesafectacionPrecierreMB extends AbstractMB{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DesafectacionPrecierreMB.class);

	/** The tc mes. */
	private TcMes tcMes;
	
	/** The mes. */
	private Integer mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The activa. */
	private boolean activa = Boolean.FALSE;
	
	/** The Constant contrasena. */
	private static final String contrasena = "xpres2002";
	
	/** The password. */
	private String password ;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
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
	 * Checks if is activa.
	 *
	 * @return true, if is activa
	 */
	public boolean isActiva() {
		return activa;
	}
	
	/**
	 * Sets the activa.
	 *
	 * @param activa the new activa
	 */
	public void setActiva(boolean activa) {
		this.activa = activa;
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
	 * Gets the contrasena.
	 *
	 * @return the contrasena
	 */
	public static String getContrasena() {
		return contrasena;
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		activa  = Boolean.TRUE;
		listMes = tcMesRepository.findAll();
		
	}
	
	/**
	 * Valida password.
	 */
	public void validaPassword() {
		if(contrasena.equals(password)) {
			activa = Boolean.FALSE;
			
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Passowrd incorrecto");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	/**
	 * Desafecta.
	 */
	public void desafecta() {
		Poliza poliza = polizaService.desafectacionPrecierre(mes,  this.getUserDetails().getIdSector());
		if(poliza.getErrorCode() >  0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", poliza.getMsgError());
		    FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", poliza.getMsgError());
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
