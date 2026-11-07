package com.gem.sistema.business.repository.catalogs;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.gem.sistema.business.domain.Ingresot;

import com.gem.sistema.business.domain.Preprogra;

// TODO: Auto-generated Javadoc
/**
 * The Interface PreprograRepository.
 */
@Repository("preprograRepository")
public interface PreprograRepository extends PagingAndSortingRepository<Preprogra, Long>, QueryDslPredicateExecutor<Preprogra> {



   /* (non-Javadoc)
   * @see org.springframework.data.repository.CrudRepository#save(S)
   */
  @Transactional(timeout = 10)
  <S extends Preprogra> S save(S entity);



  /**
   * Execute acumula preprogra.
   *
   * @param idSector the id sector
   * @param userName the user name
   */
  @Procedure(	name = "acumula_preprogra")
 	void executeAcumulaPreprogra(@Param("ID_SECTOR") Integer idSector, @Param("USER_ID") String userName );



}
