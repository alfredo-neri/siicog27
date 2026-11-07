package com.gem.sistema.business.service.polizas;

import com.gem.sistema.business.domain.Conctb;
import java.util.function.BiConsumer;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolizaCierreService.
 */
public interface PolizaCierreService{

  /**
   * Checks for valid state.
   *
   * @param clave the clave
   * @param idSector the id sector
   * @param consumer the consumer
   * @return the boolean
   */
  public Boolean hasValidState(String clave, Integer idSector, BiConsumer<String, String> consumer);
  
  /**
   * Checks if is in invalid state.
   *
   * @param idSector the id sector
   * @return the boolean
   */
  public Boolean isInInvalidState(Integer idSector);
  
  /**
   * Validate clave.
   *
   * @param clave the clave
   * @return the boolean
   */
  public Boolean validateClave(String clave);
  
  /**
   * Run subprocess with validations.
   *
   * @param idsector the idsector
   * @param username the username
   */
  public void runSubprocessWithValidations(Integer idsector, String username);
}
