package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TrMatrizNivel;

@Repository("trMatrizNivelRepository")
public interface TrMatrizNivelRepository extends PagingAndSortingRepository<TrMatrizNivel, Long> {

	List<TrMatrizNivel> findAllByMatrizIdAndNivelPadre(Long idMatriz, Long idPadre);
}
