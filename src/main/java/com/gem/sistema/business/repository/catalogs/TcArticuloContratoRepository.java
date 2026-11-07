package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import com.gem.sistema.business.domain.TcArticulosContrato;
import com.gem.sistema.business.domain.TcContrato;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "tcArticuloContratoRepository")
public interface TcArticuloContratoRepository extends PagingAndSortingRepository<TcArticulosContrato, Long> {

	@Query("Select a from TcArticulosContrato a right join fetch a.contrato c where a.contrato=:contrato")
	List<TcArticulosContrato> findAllByContrato(@Param("contrato") TcContrato contrato);
	
}
