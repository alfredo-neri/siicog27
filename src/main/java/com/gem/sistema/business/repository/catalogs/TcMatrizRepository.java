package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcMatriz;

@Repository("tcMatrizRepository")
public interface TcMatrizRepository extends PagingAndSortingRepository<TcMatriz, Long> {

	List<TcMatriz> findAll(Sort sort);

	List<TcMatriz> findAllBySectorOrderByDependenciaClaveAsc(Integer sector);

	List<TcMatriz> findAllBySectorOrderByProgramaAsc(Integer sector);

	List<TcMatriz> findAllBySectorAndDependenciaClaveStartingWithIgnoreCaseAndProgramaStartingWithOrderByDependenciaClaveAsc(
			Integer sector, String dependencia, String programa);

	List<TcMatriz> findAllBySectorAndDependenciaClaveStartingWithIgnoreCaseOrderByDependenciaClaveAsc(Integer sector,
			String dependencia);

	List<TcMatriz> findAllBySectorAndProgramaStartingWithOrderByDependenciaClaveAsc(Integer sector, String programa);

	//List<TcMatriz> finAdllBySectorAndDependenciaClaveAndDependenciaAuxiliarClaveAndProyectoCampo7AndTemaCvetemas(
	//		 Integer sector, String depGen, String depAux, String proyecto, String tema);
	
	List<TcMatriz> findAllByDependenciaClaveAndDependenciaAuxiliarClaveAndProyecto_Campo7AndTemaCvetemas(String depGen, String depAux, String proyecto, String tema);
}
