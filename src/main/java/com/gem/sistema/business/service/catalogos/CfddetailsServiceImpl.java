package com.gem.sistema.business.service.catalogos;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gem.sistema.business.domain.Cfddetails;
import com.gem.sistema.business.repository.catalogs.CfddetailsRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CfddetailsServiceImpl.
 */
@Service("cfddetailsService")
public class CfddetailsServiceImpl implements CfddetailsService{

  /** The cfddetails repository. */
  @Autowired
  CfddetailsRepository cfddetailsRepository;

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CfddetailsService#save(com.gem.sistema.business.domain.Cfddetails)
   */
  public Cfddetails save(Cfddetails entity){
    return cfddetailsRepository.save(entity);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CfddetailsService#findBySerieAndFolioAndIdSector(java.lang.String, java.lang.Long, java.lang.Long)
   */
  public Collection<Cfddetails> findBySerieAndFolioAndIdSector(String serie, Long folio, Long idSector){
    return cfddetailsRepository.findBySerieAndFolioAndIdSector(serie,folio, idSector);
  }
}
