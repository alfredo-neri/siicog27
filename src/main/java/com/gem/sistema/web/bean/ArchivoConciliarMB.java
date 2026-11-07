package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.AccountException;

import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.reportador.ArchivoConciliarService;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.TcMes;


import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ArchivoConciliarMB.
 */
@ManagedBean(name = "archivoConciliarMB")
@ViewScoped
public class ArchivoConciliarMB extends AbstractMB{

  /** The cta. */
  private String cta;
  
  /** The scta. */
  private String scta;
  
  /** The sscta. */
  private String sscta;
  
  /** The month. */
  private Integer month;
  
  /** The months. */
  private List<TcMes> months;
  
  /** The filecharge. */
  private String filecharge;
  
  /** The fileout. */
  private String fileout;
	
	/** The download. */
	private StreamedContent download;

  /** The temp. */
  private File temp;

  /** The account service. */
  @ManagedProperty("#{accountService}")
  private AccountService accountService;

  /** The archivo conciliar service. */
  @ManagedProperty("#{archivoConsiliarService}")
  private ArchivoConciliarService archivoConciliarService;

  /**
   * Gets the cta.
   *
   * @return the cta
   */
  public String getCta(){
    return cta;
  }
  
  /**
   * Sets the cta.
   *
   * @param cta the new cta
   */
  public void setCta(String cta){
    this.cta = cta;
  }
  
  /**
   * Gets the scta.
   *
   * @return the scta
   */
  public String getScta(){
    return scta;
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
   * Gets the sscta.
   *
   * @return the sscta
   */
  public String getSscta(){
    return sscta;
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
   * Gets the filecharge.
   *
   * @return the filecharge
   */
  public String getFilecharge(){
    return filecharge;
  }
  
  /**
   * Sets the filecharge.
   *
   * @param filecharge the new filecharge
   */
  public void setFilecharge(String filecharge){
    this.filecharge = filecharge;
  }
  
  /**
   * Gets the fileout.
   *
   * @return the fileout
   */
  public String getFileout(){
    return fileout;
  }
  
  /**
   * Sets the fileout.
   *
   * @param fileout the new fileout
   */
  public void setFileout(String fileout){
    this.fileout = fileout;
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
   * Gets the archivo conciliar service.
   *
   * @return the archivo conciliar service
   */
  public ArchivoConciliarService getArchivoConciliarService(){
    return archivoConciliarService;
  }
  
  /**
   * Sets the archivo conciliar service.
   *
   * @param archivoConciliarService the new archivo conciliar service
   */
  public void setArchivoConciliarService(ArchivoConciliarService archivoConciliarService){
    this.archivoConciliarService = archivoConciliarService;
  }

	/**
	 * Gets the download.
	 *
	 * @return the download
	 */
	public StreamedContent getDownload() {
		return download;
	}

	/**
	 * Sets the download.
	 *
	 * @param download the new download
	 */
	public void setDownload(StreamedContent download) {
		this.download = download;
	}

  /**
   * Gets the months.
   *
   * @return the months
   */
  public List<TcMes> getMonths(){
    if(months == null || months.isEmpty()){
      months = archivoConciliarService.getMonths();
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
   * Process.
   */
  public void process(){
    System.out.println("processing ");
    setScta(accountService.fillZeros(getScta(), 10));
    setSscta(accountService.fillZeros(getSscta(), 15));

    Cuenta cuenta =
      accountService.findFirstByCuentaAndScuentaAndSscuentaAndIdsector(getCta(), getScta(),
                                                                       getSscta(),
                                                                       getIdSectorForCurrentUser());

    System.out.println("cuenta found "+cuenta);
    if(cuenta == null){
      displayErrorMessage("No existe esta cuenta, verifique");
      return;
    }

    try{
      temp = File.createTempFile("archivo","conciliar");
      System.out.println(temp);
      BufferedWriter out = new BufferedWriter(new FileWriter(temp));

      archivoConciliarService.findRecordsForFile(getMonth(), getCta(),
                                     getScta(), getSscta(),
                                     getIdSectorForCurrentUser(),
                                     (l) -> {
                                       l.add(getFilecharge());
                                       l = l.stream().map((e) -> { return e == null ? "" : e; }).collect(Collectors.toList());
                                       try{
                                         out.write(StringUtils.join(l, " ")+"\n");
                                       }catch(IOException e){
                                        e.printStackTrace();
                                       }
                                     });
      out.close();
      setDownload(new DefaultStreamedContent(new FileInputStream(temp), "text/plain", StringUtils.defaultIfBlank(getFileout(), "ArchivoConciliar.txt")));
    }catch(IOException e){
      e.printStackTrace();
    }
    System.out.println("ended ");
  }
}
