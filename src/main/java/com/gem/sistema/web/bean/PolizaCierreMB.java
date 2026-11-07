package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.AccountException;
// import com.gem.sistema.business.domain.Cuenta;
// import com.gem.sistema.business.repository.catalogs.CuentaRepository;
// import com.gem.sistema.business.service.catalogos.AccountService;
// import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.business.service.polizas.PolizaCierreService;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaCierreMB.
 */
@ManagedBean(name = "polizaCierreMB")

@ViewScoped
public class PolizaCierreMB extends AbstractMB{

  /** The clave. */
  private String clave = "";
  
  /** The visible errors. */
  private Boolean visibleErrors;

  /**
   * Gets the clave.
   *
   * @return the clave
   */
  public String getClave(){
    return clave;
  }

  /**
   * Sets the clave.
   *
   * @param clave the new clave
   */
  public void setClave(String clave){
    this.clave = clave;
  }

  /**
   * Gets the visible errors.
   *
   * @return the visible errors
   */
  public Boolean getVisibleErrors(){
    return visibleErrors;
  }

  /**
   * Sets the visible errors.
   *
   * @param visibleErrors the new visible errors
   */
  public void setVisibleErrors(Boolean visibleErrors){
    this.visibleErrors = visibleErrors;
  }

  /** The poliza cierre service. */
  @ManagedProperty("#{polizaCierreService}")
  private PolizaCierreService polizaCierreService;

  /**
   * Gets the poliza cierre service.
   *
   * @return the poliza cierre service
   */
  public PolizaCierreService getPolizaCierreService(){
    return this.polizaCierreService;
  }

  /**
   * Sets the poliza cierre service.
   *
   * @param polizaCierreService the new poliza cierre service
   */
  public void setPolizaCierreService(PolizaCierreService polizaCierreService){
    this.polizaCierreService = polizaCierreService;
  }

  /**
   * Process.
   */
  public void process(){
    Boolean hasValidState = polizaCierreService.hasValidState(clave,
                                    getIdSectorForCurrentUser().intValue(),
                                    (status, message)->{
                                      switch(status){
                                        case "e":
                                          displayErrorMessage(message);
                                          break;
                                        case "i":
                                          displayInfoMessage(message);
                                          break;
                                        case "w":
                                          displayWarnMessage(message);
                                          break;
                                      }
                                    });
    if(hasValidState == null) return;

    if(hasValidState){
      runSubprocessWithValidations();
    }else{
      setVisibleErrors(Boolean.TRUE);
    }
  }

  /**
   * Run subprocess with validations.
   */
  public void runSubprocessWithValidations(){
    setVisibleErrors(Boolean.FALSE);
    polizaCierreService.runSubprocessWithValidations(getIdSectorForCurrentUser().intValue(), getUserDetails().getUsername());
    displayInfoMessage("Proceso terminado");
  }

  /**
   * Cancel process.
   */
  public void cancelProcess(){
    setVisibleErrors(Boolean.FALSE);
    displayInfoMessage("actividad cancelada");
  }

}
