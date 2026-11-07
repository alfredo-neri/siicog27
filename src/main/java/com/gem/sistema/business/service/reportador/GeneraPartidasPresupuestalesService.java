package com.gem.sistema.business.service.reportador;

import com.gem.sistema.business.domain.GenericCatalog;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.domain.Natgas;

import java.util.function.Consumer;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneraPartidasPresupuestalesService.
 */
public interface GeneraPartidasPresupuestalesService{
  
  /**
   * Gets the dependencies.
   *
   * @param query the query
   * @param idSector the id sector
   * @return the dependencies
   */
  public List<GenericCatalog<String>> getDependencies(String query, Integer idSector);
  
  /**
   * Gets the programs.
   *
   * @param idSector the id sector
   * @return the programs
   */
  public List<GenericCatalog<String>> getPrograms(Integer idSector);
  
  /**
   * Gets the parts.
   *
   * @param idSector the id sector
   * @return the parts
   */
  public List<GenericCatalog<String>> getParts(Integer idSector);
  
  /**
   * Find dependency.
   *
   * @param idSector the id sector
   * @param clvdep the clvdep
   * @return the catdep
   */
  public Catdep findDependency(Integer idSector, String clvdep);
  
  /**
   * Find first xcatpro.
   *
   * @param idSector the id sector
   * @param clvdep the clvdep
   * @param clvfun the clvfun
   * @param clvfin the clvfin
   * @return the xcatpro
   */
  public Xcatpro findFirstXcatpro(Integer idSector, String clvdep, String clvfun, String clvfin);
  
  /**
   * Find first natgas.
   *
   * @param clvgas the clvgas
   * @param idSector the id sector
   * @return the natgas
   */
  public Natgas findFirstNatgas(String clvgas, Integer idSector);
  
  /**
   * Process.
   *
   * @param idSector the id sector
   * @param dep the dep
   * @param prog the prog
   * @param part the part
   * @param user the user
   * @param consumer the consumer
   */
  public void process(Long idSector, String dep, String prog, String part, String user, Consumer<String> consumer);
}
