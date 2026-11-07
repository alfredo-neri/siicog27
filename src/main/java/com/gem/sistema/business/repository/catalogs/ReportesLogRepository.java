package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TrReportesLog;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportesLogRepository.
 *
 * @author 
 */
@Repository(value = "reportesLogRepository")
public interface ReportesLogRepository extends PagingAndSortingRepository<TrReportesLog, Long>, QueryDslPredicateExecutor<TrReportesLog>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TrReportesLog> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TrReportesLog> S save(S entity);	
	
}
