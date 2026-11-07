package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Integradoe;
import com.gem.sistema.business.domain.Integradoi;

// TODO: Auto-generated Javadoc
/**
 * The Interface IntegradoiRepository.
 */
@Repository(value="integradoiRepository")
public interface IntegradoiRepository extends PagingAndSortingRepository<Integradoi, Long>, QueryDslPredicateExecutor<Integradoi>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Integradoi> findAll();
		
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Integradoi> S save(S entity);
	
	/**
	 * Find by cuenta.
	 *
	 * @param <S> the generic type
	 * @param cuenta the cuenta
	 * @param scta the scta
	 * @param sscta the sscta
	 * @param ssscta the ssscta
	 * @param sssscta the sssscta
	 * @return the s
	 */
	@Transactional(timeout = 10)
	<S extends Integradoi> S findByCuenta( String cuenta, String scta, String sscta, String ssscta, String sssscta);

}
