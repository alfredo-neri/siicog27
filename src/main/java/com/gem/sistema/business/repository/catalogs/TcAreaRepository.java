package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcArea;

@Repository(value = "tcAreaRepository")
public interface TcAreaRepository extends PagingAndSortingRepository<TcArea, Long>, QueryDslPredicateExecutor<TcArea> {

	List<TcArea> findAllByClave(Integer clave);

	List<TcArea> findAllByOrderByClave();
	
	@Query("select a from TcArea a where a.clave > 0 order by a.clave")
	List<TcArea> findAllByExcept0OrderByClave();
	
	@Query("select max(a.clave) + 1  from TcArea a")
	Integer getNewClave();
	
	TcArea findOneByClave(Integer clave);

}
