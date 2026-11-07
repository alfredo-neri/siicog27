package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gem.sistema.business.domain.TcFirmaDigital;

@Repository("tcFirmaDigitalRepository")
public interface TcFirmaDigitalRepository
		extends PagingAndSortingRepository<TcFirmaDigital, Long>, QueryDslPredicateExecutor<TcFirmaDigital> {
	@Query("SELECT f FROM TcFirmaDigital f join f.usuario u WHERE u.usuario=:username")
	TcFirmaDigital findByUsername(@Param("username") String username);

	TcFirmaDigital findById(Long id);

	List<TcFirmaDigital> findAll();

	@Query("SELECT f,u FROM TcFirmaDigital f join fetch f.usuario u ")
	List<TcFirmaDigital> findAllFirmas();

}
