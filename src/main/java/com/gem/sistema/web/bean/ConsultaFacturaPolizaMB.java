package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.AccountException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.gem.sistema.business.service.reportador.ConsultaFacturaPolizasService;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.CattpoService;
import com.gem.sistema.business.service.catalogos.CopomeService;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.TcMes;

import com.gem.sistema.web.security.model.GemUser;
import org.primefaces.model.StreamedContent;
import java.io.File;
import org.primefaces.model.DefaultStreamedContent;
import java.io.FileInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaFacturaPolizaMB.
 */
@ViewScoped
@ManagedBean(name = "consultaFacturaPolizaMB")
public class ConsultaFacturaPolizaMB extends AbstractMB {

	/** The file. */
	private String filename;

  /** The months. */
  private List<TcMes> months;
  
  /** The month. */
  private Integer month;
  
  /** The type. */
  private String type;
  
  /** The num poliza. */
  private Integer numPoliza;


  /** The main service. */
  @ManagedProperty("#{consultaFacturaPolizasService}")
  private ConsultaFacturaPolizasService mainService;

  /**
   * Gets the months.
   *
   * @return the months
   */
  public List<TcMes> getMonths(){
    if(months == null || months.isEmpty()){
      months = mainService.getMonths();
    }
    return months;
  }

  /**
   * Sets the months.
   *
   * @param months the new months
   */
  public void setMonths(List<TcMes> months){
    this.months = months;
  }

  /**
   * Gets the month.
   *
   * @return the month
   */
  public Integer getMonth(){
    return month;
  }
  
  /**
   * Sets the month.
   *
   * @param month the new month
   */
  public void setMonth(Integer month){
    this.month = month;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType(){
    return type;
  }
  
  /**
   * Sets the type.
   *
   * @param type the new type
   */
  public void setType(String type){
    this.type = type;
  }

  /**
   * Gets the num poliza.
   *
   * @return the num poliza
   */
  public Integer getNumPoliza(){
    return numPoliza;
  }
  
  /**
   * Sets the num poliza.
   *
   * @param numPoliza the new num poliza
   */
  public void setNumPoliza(Integer numPoliza){
    this.numPoliza = numPoliza;
  }

  /**
   * Gets the file.
   *
   * @return the file
   */
	public String getFilename(){
		return filename;
	}

  /**
   * Sets the file.
   *
   * @param file the new file
   */

	public void setFilename(String filename){
		this.filename = filename;
	}

  /**
   * Gets the main service.
   *
   * @return the main service
   */
  public ConsultaFacturaPolizasService getMainService(){
    return mainService;
  }

  /**
   * Sets the main service.
   *
   * @param mainService the new main service
   */
  public void setMainService(ConsultaFacturaPolizasService mainService){
    this.mainService = mainService;
  }

  /**
   * Process.
   */
  public void process(){
    if(getMainService().polizaExists(month, getNumPoliza(), getType(), getIdSectorForCurrentUser().intValue())){
      String tempFileName = month+type+numPoliza;
      if (getMainService().getFactura(tempFileName) != null){
				setFilename(tempFileName);
      }else{
				setFilename(null);
        displayErrorMessage("FACTURAS DE LA POLIZA NO EXISTE...");
      }

    }else{
			setFilename(null);
      displayErrorMessage("LA POLIZA NO SE ENCUENTRA");
      System.out.println("no se euencuentra la poliza: ");
    }
		setNumPoliza(null);
		setMonth(null);
		setType(null);
  }
}
