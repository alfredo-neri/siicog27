package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.AccountException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.CattpoService;
import com.gem.sistema.business.service.catalogos.CopomeService;
// import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Copome;

import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class CambioMesMB.
 */
@ManagedBean(name = "cambioMesMB")
@ViewScoped
public class CambioMesMB extends AbstractMB {

  /** The cattpo service. */
  @ManagedProperty(value = "#{cattpoService}")
  private CattpoService cattpoService;

  /** The copome service. */
  @ManagedProperty(value = "#{copomeService}")
  private CopomeService copomeService;

  /** The polien repository. */
  @ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

  /** The conctb repository. */
  @ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;


  /**
   * Sets the cattpo service.
   *
   * @param cattpoService the new cattpo service
   */
  public void setCattpoService(CattpoService cattpoService){
    this.cattpoService = cattpoService;
  }

  /**
   * Gets the cattpo service.
   *
   * @return the cattpo service
   */
  public CattpoService getCattpoService(){
    return cattpoService;
  }

  /**
   * Sets the copome service.
   *
   * @param copomeService the new copome service
   */
  public void setCopomeService(CopomeService copomeService){
    this.copomeService = copomeService;
  }

  /**
   * Gets the copome service.
   *
   * @return the copome service
   */
  public CopomeService getCopomeService(){
    return copomeService;
  }

  /**
   * Sets the polien repository.
   *
   * @param polienRepository the new polien repository
   */
  public void setPolienRepository(PolienRepository polienRepository){
    this.polienRepository = polienRepository;
  }

  /**
   * Gets the polien repository.
   *
   * @return the polien repository
   */
  public PolienRepository getPolienRepository(){
    return polienRepository;
  }

  /**
   * Sets the conctb repository.
   *
   * @param conctbRepository the new conctb repository
   */
  public void setConctbRepository(ConctbRepository conctbRepository){
    this.conctbRepository = conctbRepository;
  }

  /**
   * Gets the conctb repository.
   *
   * @return the conctb repository
   */
  public ConctbRepository getConctbRepository(){
    return conctbRepository;
  }

  /**
   * Process.
   */
  public void process(){
    GemUser user = getUserDetails();
    Long idSector = Long.valueOf(user.getIdSector());
    Integer count = getPolienRepository().countRegPolicies(idSector);
    if(count == null || Integer.valueOf(0).equals(count)){
      displayErrorMessage("NO EXISTEN POLIZAS REGISTRADAS. NO SE PUEDE ACTUALIZAR EL MES");
      return;
    }

    Conctb conctb = getConctbRepository().findByClvempAndIdsector("1", idSector.intValue());
    if(conctb == null){
      displayErrorMessage("NO EXISTE REGISTRO DE CONTROL");
      return;
    }

    conctb.setMesemp(conctb.getMesemp()+1);
    getConctbRepository().save(conctb);

    for(Cattpo cattpo : getCattpoService().getAll()){
      Copome copome = getCopomeService().findByTpcpme(cattpo.getTippol(), idSector.intValue());
      if(copome == null){
        displayErrorMessage("ERROR FATAL");
      }else{
        // insert new copome
        getCopomeService().save(buildCopome(conctb, cattpo, idSector, user.getUsername()));
      }
    }
    displayInfoMessage("MES ACTUALIZADO");
  }

  /**
   * Builds the copome.
   *
   * @param conctb the conctb
   * @param cattpo the cattpo
   * @param idSector the id sector
   * @param userId the user id
   * @return the copome
   */
  private Copome buildCopome(final Conctb conctb, final Cattpo cattpo, Long idSector, String userId){
    Copome copome = new Copome();
    copome.setAnopme(conctb.getAnoemp());
    copome.setMecpme(conctb.getMesemp());
    copome.setTpcpme(cattpo.getTippol());
    copome.setIdsector(idSector.intValue());
    copome.setIdRef(0);
    copome.setUserid(userId);
    copome.setCocpme(0);
    return copome;
  }

}
