package com.gem.sistema.business.repository.catalogs.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Cfdmaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface CfdmasterRepository.
 */
@Repository("cfdmasterRepository")
public interface CfdmasterRepository extends PagingAndSortingRepository<Cfdmaster,Long>{
  
  /**
   * Find first by order by id.
   *
   * @return the cfdmaster
   */
  public Cfdmaster findFirstByOrderById();
  
  /**
   * Find first by order by id desc.
   *
   * @return the cfdmaster
   */
  public Cfdmaster findFirstByOrderByIdDesc();
}
