package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcContratoPad;

@Repository(value = "tcContratoPadRepository")
public interface TcContratoPadRepository extends PagingAndSortingRepository<TcContratoPad, Long> {

	List<TcContratoPad> findAllByOrderByNumeroContrato();

	TcContratoPad findByNumeroContrato(String numeroContrato);
}
