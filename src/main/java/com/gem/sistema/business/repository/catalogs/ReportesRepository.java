package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.domain.Xcatpro;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportesRepository.
 *
 * @author 
 */
@Repository(value = "reportesRepository")
public interface ReportesRepository extends PagingAndSortingRepository<TcReporte, Long>, QueryDslPredicateExecutor<TcReporte>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcReporte> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcReporte> S save(S entity);
	
	/**
	 * Find by id reporte.
	 *
	 * @param reportid the reportid
	 * @return reporte
	 */
	List<TcReporte> findByIdReporte(long reportid);
	
}
