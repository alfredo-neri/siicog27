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

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;

import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PolizaService;

// TODO: Auto-generated Javadoc
/**
 * The Class DesafectacionUnaPolizaMB.
 */
@ManagedBean(name = "desafectacionUnaPolizaMB")
@ViewScoped
public class DesafectacionUnaPolizaMB extends AbstractMB{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DesafectacionUnaPolizaMB.class);
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The cattpo repository. */
	@ManagedProperty("#{cattpoRepository}")
	private CattpoRepository cattpoRepository;
	
	/** The mes. */
	private TcMes mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The cattpo. */
	private Cattpo cattpo;
	
	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;
	
	/** The numero. */
	private Integer numero;
	
	/** The password. */
	private String password ;
	
	/** The contrasena. */
	private String contrasena = "dxafecta2016";
	
	/** The valida pass. */
	private boolean validaPass = Boolean.FALSE;
	
	/** The activa button. */
	private boolean activaButton = Boolean.FALSE;
	
	/** The polide. */
	private Polien polide;
	
	/** The me. */
	private Integer me;
	
	/** The tipo. */
	private String tipo;
	
	
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
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

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
	 * Gets the contrasena.
	 *
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	
	/**
	 * Sets the contrasena.
	 *
	 * @param contrasena the new contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	/**
	 * Checks if is valida pass.
	 *
	 * @return true, if is valida pass
	 */
	public boolean isValidaPass() {
		return validaPass;
	}
	
	/**
	 * Sets the valida pass.
	 *
	 * @param validaPass the new valida pass
	 */
	public void setValidaPass(boolean validaPass) {
		this.validaPass = validaPass;
	}
	
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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public TcMes getMes() {
		return mes;
	}
	
	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(TcMes mes) {
		this.mes = mes;
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
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}
	
	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
	
	/**
	 * Gets the polide.
	 *
	 * @return the polide
	 */
	public Polien getPolide() {
		return polide;
	}
	
	/**
	 * Sets the polide.
	 *
	 * @param polide the new polide
	 */
	public void setPolide(Polien polide) {
		this.polide = polide;
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();
		lisCattpo = cattpoRepository.findAll();
		isValidaPass();
		activaButton = Boolean.TRUE;
	}
	
	/**
	 * Valida password.
	 */
	public void validaPassword() {
		LOGGER.info("entra a validar");
		if(contrasena.equals(password)) {
			activaButton = Boolean.FALSE;
			
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Passowrd incorrecto");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	/**
	 * Desafectar.
	 */
	public void desafectar() {
		LOGGER.info("entra a desafectar una poliza");
		LOGGER.info("tipo::: " + mes);
		LOGGER.info("tipo::: " + tipo);
		Poliza poliza = polizaService.desafectaPoliza(me, tipo, numero, this.getUserDetails().getIdSector());
		if(poliza.getErrorCode() >  0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", poliza.getMsgError());
		    FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", poliza.getMsgError());
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	/**
	 * On page load.
	 */
	public void onPageLoad(){
		activaButton = Boolean.TRUE;
	}
	
	

}
