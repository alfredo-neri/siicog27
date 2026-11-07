package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.TcMatrizIngreso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Interface TcMatrizIngresoRepository.
 */
@Repository(value = "tcMatrizIngresoRepository")
public interface TcMatrizIngresoRepository extends PagingAndSortingRepository<TcMatrizIngreso, Long> {

	List<TcMatrizIngreso> findAll();

	List<TcMatrizIngreso> findAllByTipoIngresoOrderByConsecutivoAsc(String  tipoIngreso);

}
