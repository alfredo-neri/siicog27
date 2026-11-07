package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcFicha;

@Repository("tcFichaRepository")
public interface TcFichaRepository extends PagingAndSortingRepository<TcFicha, Long> {

	List<TcFicha> findAllByMatrizNivelMatrizDependenciaClaveStartingWithAndMatrizNivelNivelCodigoIndicadorStartingWith(
			String dependencia, String codigo);

	List<TcFicha> findAllByMatrizNivelMatrizDependenciaClaveStartingWith(String dependencia);

	List<TcFicha> findAllByMatrizNivelNivelCodigoIndicadorStartingWith(String codigo);

	List<TcFicha> findAllByMatrizNivelMatrizSector(Integer sector);
	
	List<TcFicha> findAllByMatrizNivelMatrizSectorOrderByMatrizNivelMatrizDependenciaClaveAscMatrizNivelMatrizDependenciaAuxiliarClaveAscMatrizNivelMatrizProgramaAscMatrizNivelNivelTipoNivelIdSubNivelAsc(Integer sector);

	List<TcFicha> findAll(Sort sort);
}
