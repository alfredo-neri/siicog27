package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.TcContrato;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "tcContratosRepository")
public interface TcContratosRepository extends PagingAndSortingRepository<TcContrato, Long> {

	TcContrato findByNumeroContrato(String numeroContrato);
}
