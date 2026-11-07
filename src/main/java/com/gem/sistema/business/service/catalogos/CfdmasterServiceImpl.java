package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gem.sistema.business.domain.Cfdmaster;
import com.gem.sistema.business.repository.catalogs.impl.CfdmasterRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CfdmasterServiceImpl.
 */
@Service("cfdmasterService")
public class CfdmasterServiceImpl implements CfdmasterService{

  /** The cfdmaster repository. */
  @Autowired
  CfdmasterRepository cfdmasterRepository;

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CfdmasterService#findFirst()
   */
  public Cfdmaster findFirst(){
    return cfdmasterRepository.findFirstByOrderById();
  }
  
  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CfdmasterService#findLast()
   */
  public Cfdmaster findLast(){
    return cfdmasterRepository.findFirstByOrderByIdDesc();
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CfdmasterService#save(com.gem.sistema.business.domain.Cfdmaster)
   */
  public void save(Cfdmaster entity){
    cfdmasterRepository.save(entity);
  }
}
