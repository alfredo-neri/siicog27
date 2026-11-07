package com.gem.sistema.business.service.utilerias;

import com.gem.sistema.business.domain.MonthlyAbstractEntity;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.domain.Paso;

import java.util.List;
import java.util.function.Consumer;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Interface SaldosInicialesService.
 */
public interface SaldosInicialesService{
  
  /**
   * Find first cuenta.
   *
   * @param cuenta the cuenta
   * @param idSector the id sector
   * @return the cuenta
   */
  public Cuenta findFirstCuenta(String cuenta, Long idSector);
  
  /**
   * Find cuentas for.
   *
   * @param cuenta the cuenta
   * @param scta the scta
   * @param idSector the id sector
   * @return the list
   */
  public List<Cuenta> findCuentasFor(String cuenta, String scta, Long idSector);
  
  /**
   * Find first by cuenta and idsector.
   *
   * @param cuenta the cuenta
   * @param idSector the id sector
   * @return the cuenta
   */
  public Cuenta findFirstByCuentaAndIdsector(String cuenta, Long idSector);
  
  /**
   * Gets the previous account name.
   *
   * @param account the account
   * @param idSector the id sector
   * @return the previous account name
   */
  public String getPreviousAccountName(Cuenta account, Long idSector);
  
  /**
   * Gets the ingreso.
   *
   * @param account the account
   * @param cuenta the cuenta
   * @param idSector the id sector
   * @return the ingreso
   */
  public Ingreso getIngreso(Cuenta account, String cuenta, Long idSector);
  
  /**
   * Find paso for clave programa and partida.
   *
   * @param clave the clave
   * @param programa the programa
   * @param partida the partida
   * @param idSector the id sector
   * @param consumer the consumer
   * @return the paso
   */
  public Paso findPasoForClaveProgramaAndPartida(String clave, String programa,
                                                  String partida, Long idSector, Consumer<Boolean> consumer);

  /**
   * Save muni.
   *
   * @param selectedAccount the selected account
   * @param salIni the sal ini
   * @param mentity the mentity
   * @param idSector the id sector
   * @param errorMessage the error message
   * @return true, if successful
   */
  public boolean saveMuni(Cuenta selectedAccount, BigDecimal salIni, MonthlyAbstractEntity mentity, Long idSector, Consumer<String> errorMessage);
  
  /**
   * Save central.
   *
   * @param selectedAccount the selected account
   * @param salIni the sal ini
   * @param mentity the mentity
   * @param idSector the id sector
   * @param errorMessage the error message
   * @return true, if successful
   */
  public boolean saveCentral(Cuenta selectedAccount, BigDecimal salIni, MonthlyAbstractEntity mentity, Long idSector, Consumer<String> errorMessage);
}
