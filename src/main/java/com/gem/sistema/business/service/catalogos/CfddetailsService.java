package com.gem.sistema.business.service.catalogos;

import java.util.Collection;
import com.gem.sistema.business.domain.Cfddetails;

// TODO: Auto-generated Javadoc
/**
 * The Interface CfddetailsService.
 */
public interface CfddetailsService{
  
  /**
   * Save.
   *
   * @param entity the entity
   * @return the cfddetails
   */
  public Cfddetails save(Cfddetails entity);
  
  /**
   * Find by serie and folio and id sector.
   *
   * @param serie the serie
   * @param folio the folio
   * @param idSector the id sector
   * @return the collection
   */
  public Collection<Cfddetails> findBySerieAndFolioAndIdSector(String serie, Long folio, Long idSector);
}
