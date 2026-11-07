package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Cfdmaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface CfdmasterService.
 */
public interface CfdmasterService{
  
  /**
   * Find first.
   *
   * @return the cfdmaster
   */
  public Cfdmaster findFirst();
  
  /**
   * Find last.
   *
   * @return the cfdmaster
   */
  public Cfdmaster findLast();
  
  /**
   * Save.
   *
   * @param entity the entity
   */
  public void save(Cfdmaster entity);
}
