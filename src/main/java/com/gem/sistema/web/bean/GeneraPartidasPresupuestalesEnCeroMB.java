package com.gem.sistema.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesEnCeroService;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraPartidasPresupuestalesEnCeroMB.
 */
@ManagedBean(name = "genPartPresEnCeroMB")
@ViewScoped
public class GeneraPartidasPresupuestalesEnCeroMB extends AbstractMB{

  /** The genera partidas presupuestales en cero service. */
  @ManagedProperty("#{generaPartidasPresupuestalesEnCeroService}")
  private GeneraPartidasPresupuestalesEnCeroService generaPartidasPresupuestalesEnCeroService;

  /** The download. */
  private StreamedContent download;
  
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
   * Sets the genera partidas presupuestales en cero service.
   *
   * @param generaPartidasPresupuestalesEnCeroService the new genera partidas presupuestales en cero service
   */
  public void setGeneraPartidasPresupuestalesEnCeroService(GeneraPartidasPresupuestalesEnCeroService generaPartidasPresupuestalesEnCeroService){
    this.generaPartidasPresupuestalesEnCeroService = generaPartidasPresupuestalesEnCeroService;
  }

  /**
   * Gets the genera partidas presupuestales en cero service.
   *
   * @return the genera partidas presupuestales en cero service
   */
  public  GeneraPartidasPresupuestalesEnCeroService getGeneraPartidasPresupuestalesEnCeroService(){
    return generaPartidasPresupuestalesEnCeroService;
  }
  
  /**
   * Process.
   */
  public void process(){
    System.out.println("Starting");

      try{
        File zipFile = File.createTempFile("egre.zip","info");
         generaPartidasPresupuestalesEnCeroService.generatePartidasPresPasos(getIdSectorForCurrentUser().intValue(),zipFile, getUserDetails().getUsername());

        if(zipFile!=null){
            this.setDownload(new DefaultStreamedContent(new FileInputStream(zipFile), "zip", "egre.zip"));
          }
        }catch(Exception e){
          e.printStackTrace();
        }
      generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Proceso Conluido.");
  }

}
