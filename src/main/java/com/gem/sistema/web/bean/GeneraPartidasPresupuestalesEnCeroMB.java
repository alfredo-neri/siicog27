package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.faces.application.FacesMessage;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesEnCeroService;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraPartidasPresupuestalesEnCeroMB.
 */
@Component("genPartPresEnCeroMB")
@Scope("view")
public class GeneraPartidasPresupuestalesEnCeroMB extends AbstractMB{

  /** The genera partidas presupuestales en cero service. */
  @Value("#{generaPartidasPresupuestalesEnCeroService}")
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
            this.setDownload(StreamedContents.of(new FileInputStream(zipFile), "zip", "egre.zip", null));
          }
        }catch(Exception e){
          e.printStackTrace();
        }
      generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Proceso Conluido.");
  }

}
