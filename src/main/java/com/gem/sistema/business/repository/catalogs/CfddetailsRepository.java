package com.gem.sistema.business.repository.catalogs;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Cfddetails;

// TODO: Auto-generated Javadoc
/**
 * The Interface CfddetailsRepository.
 */
@Repository("cfddetailsRepository")
public interface CfddetailsRepository extends PagingAndSortingRepository<Cfddetails,Long>{
  
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
