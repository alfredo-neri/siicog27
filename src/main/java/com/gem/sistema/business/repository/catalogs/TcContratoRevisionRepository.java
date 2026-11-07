package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.TcContratosRevison;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "tcContratoRevisionRepository")
public interface TcContratoRevisionRepository extends PagingAndSortingRepository<TcContratosRevison, Long> {

//	@Query("Select r from TcContratosRevison r inner join fetch r.contrato c where c.contrato=:contrato")
//	List<TcContratosRevison> findAllByContrato(@Param("contrato") TcContrato contrato);
}
