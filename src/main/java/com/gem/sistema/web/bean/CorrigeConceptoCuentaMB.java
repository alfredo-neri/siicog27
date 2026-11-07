package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.web.security.model.GemUser;


// TODO: Auto-generated Javadoc
/**
 * The Class CorrigeConceptoCuentaMB.
 */
@ManagedBean(name = "corrigeConceptoCuentaMB")
@ViewScoped
public class CorrigeConceptoCuentaMB extends AbstractMB {

  /** The cta. */
  private String cta;
  
  /** The scta. */
  private String scta;
  
  /** The sscta. */
  private String sscta;
  
  /** The ssscta. */
  private String ssscta;
  
  /** The sssscta. */
  private String sssscta;
  
  /** The nomcta. */
  private String nomcta;
  
  /** The current cuenta. */
  private Cuenta currentCuenta;


  /** The account service. */
  @ManagedProperty("#{accountService}")
  private AccountService accountService;

  /**
   * Sets the cta.
   *
   * @param cta the new cta
   */
  public void setCta(String cta){
    this.cta = cta;
  }

  /**
   * Gets the cta.
   *
   * @return the cta
   */
  public String getCta(){
    return cta==null?"":cta;
  }

  /**
   * Sets the scta.
   *
   * @param scta the new scta
   */
  public void setScta(String scta){
    this.scta = scta;
  }

  /**
   * Gets the scta.
   *
   * @return the scta
   */
  public String getScta(){
    return scta==null?"":scta;
  }

  /**
   * Sets the sscta.
   *
   * @param sscta the new sscta
   */
  public void setSscta(String sscta){
    this.sscta = sscta;
  }

  /**
   * Gets the sscta.
   *
   * @return the sscta
   */
  public String getSscta(){
    return sscta==null?"":sscta;
  }

  /**
   * Sets the ssscta.
   *
   * @param ssscta the new ssscta
   */
  public void setSsscta(String ssscta){
    this.ssscta = ssscta;
  }

  /**
   * Gets the ssscta.
   *
   * @return the ssscta
   */
  public String getSsscta(){
    return ssscta==null?"":ssscta;
  }
  
  /**
   * Sets the sssscta.
   *
   * @param sssscta the new sssscta
   */
  public void setSssscta(String sssscta){
    this.sssscta = sssscta;
  }

  /**
   * Gets the sssscta.
   *
   * @return the sssscta
   */
  public String getSssscta(){
    return sssscta==null?"":sssscta;
  }
  
  /**
   * Gets the account service.
   *
   * @return the account service
   */
  public AccountService getAccountService(){
    return accountService;
  }

  /**
   * Sets the account service.
   *
   * @param accountService the new account service
   */
  public void setAccountService(AccountService accountService){
    this.accountService = accountService;
  }

  /**
   * Gets the current cuenta.
   *
   * @return the current cuenta
   */
  public Cuenta getCurrentCuenta(){
    return currentCuenta;
  }
  
  /**
   * Sets the current cuenta.
   *
   * @param currentCuenta the new current cuenta
   */
  public void setCurrentCuenta(Cuenta currentCuenta){
    this.currentCuenta = currentCuenta;
  }

  /**
   * Gets the nomcta.
   *
   * @return the nomcta
   */
  public String getNomcta(){
    return nomcta;
  }

  /**
   * Sets the nomcta.
   *
   * @param nomcta the new nomcta
   */
  public void setNomcta(String nomcta){
    this.nomcta = nomcta;
  }

  /**
   * Search account.
   */
  public void searchAccount(){
    GemUser user = getUserDetails();
    Long idSector = Long.valueOf(user.getIdSector());
    Cuenta cuenta = accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(accountService.fillZeros(getCta(),4), accountService.fillZeros(getScta(),10), accountService.fillZeros(getSscta(),15), accountService.fillZeros(getSsscta(),4), accountService.fillZeros(getSssscta(),3),idSector);
    System.out.println("Geetting cuenta with sscta="+ accountService.fillZeros(getSscta(),15));
    if(cuenta == null){
      displayInfoMessage("NO EXISTE ESTA CUENTA, VERIFIQUE");
        this.setCurrentCuenta(null);
        this.setNomcta("");
    }
    else{
      this.setNomcta(cuenta.getNomcta());
      this.setCurrentCuenta(cuenta);
    }
  }

  /**
   * Update account.
   */
  public void updateAccount(){
    if(this.getCurrentCuenta()!=null){
      this.getCurrentCuenta().setNomcta(this.getNomcta());
      accountService.saveAccount(this.getCurrentCuenta());
      displayInfoMessage("CONCEPTO DE CUENTA ACTUALIZADO");
    }
  }

}
