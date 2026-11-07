package com.gem.sistema.business.service.reportador;

import java.util.List;
import java.util.function.Consumer;
import com.gem.sistema.business.domain.TcMes;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConsultaFacturaPolizasService.
 */
public interface ConsultaFacturaPolizasService{

  /**
   * Gets the months.
   *
   * @return the months
   */
  public List<TcMes> getMonths();

  /**
   * Poliza exists.
   *
   * @param month the month
   * @param conpol the conpol
   * @param tippol the tippol
   * @param idsector the idsector
   * @return true, if successful
   */
  public boolean polizaExists(Integer month, Integer conpol, String tippol,
                              Integer idsector);

  /**
   * Gets the factura.
   *
   * @param filename the filename
   * @return the factura
   */
  public File getFactura(String filename);
}
