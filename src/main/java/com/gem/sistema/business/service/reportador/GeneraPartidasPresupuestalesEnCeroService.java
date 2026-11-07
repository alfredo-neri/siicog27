package com.gem.sistema.business.service.reportador;
import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneraPartidasPresupuestalesEnCeroService.
 */
public interface GeneraPartidasPresupuestalesEnCeroService{

  /**
   * Generate partidas pres pasos.
   *
   * @param idSector the id sector
   * @param outputFile the output file
   * @param userName the user name
   */
  public void generatePartidasPresPasos(Integer idSector, File outputFile, String userName);

}
