package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcEstadoContrato;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcEstadoContratoRepository.
 */
@Repository(value = "tcEstadosContratoRepository")
public interface TcEstadosContratoRepository
		extends PagingAndSortingRepository<TcEstadoContrato, Long>, CrudRepository<TcEstadoContrato, Long>, QuerydslPredicateExecutor<TcEstadoContrato> {

	List<TcEstadoContrato> findAll();
	
	TcEstadoContrato findByEstado(String estado);

}
