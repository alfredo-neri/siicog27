package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcNivel;

@Repository("tcNivelRepository")
public interface TcNivelRepository extends PagingAndSortingRepository<TcNivel, Long> {

	List<TcNivel> findAllByNivelesMatrizIdAndTipoNivelClaveAndPadre(Long idMatriz, String claveNivel, Long idPadre);

	List<TcNivel> findAllByNivelesMatrizId(Long idMatriz);
}
