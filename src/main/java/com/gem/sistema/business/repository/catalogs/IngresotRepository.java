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


// TODO: Auto-generated Javadoc
/**
 * The Interface IngresotRepository.
 */
@Repository("ingresotRepository")
public interface IngresotRepository extends PagingAndSortingRepository<Ingresot, Long>, QueryDslPredicateExecutor<Ingresot> {



   /* (non-Javadoc)
   * @see org.springframework.data.repository.CrudRepository#save(S)
   */
  @Transactional(timeout = 10)
  <S extends Ingresot> S save(S entity);


    /**
     * Execute acumula ingresot.
     *
     * @param idSector the id sector
     * @param userName the user name
     */
    @Procedure(	name = "acumula_ingresot")
   	void executeAcumulaIngresot(@Param("ID_SECTOR") Integer idSector, @Param("USER_ID") String userName );


}
