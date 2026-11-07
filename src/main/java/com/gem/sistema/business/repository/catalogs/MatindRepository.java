package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Matind;
import com.gem.sistema.business.dto.MatrizIndicadoresDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MatindRepository.
 */
@Repository
public interface MatindRepository extends PagingAndSortingRepository<Matind, Long>, QueryDslPredicateExecutor<Matind> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Matind> findAll();

	/**
	 * Find all dep matriz ind.
	 *
	 * @return the list
	 */
	@Query("SELECT NEW com.gem.sistema.business.dto.MatrizIndicadoresDTO(ma.id, x.clvdep, x.clvfun, c.nombre, c.clave, m.campo7, cp.descripcion, cp.cvetemas) "
			+ "FROM Xcatpro x, Catdgm c, Muninep m, Cpd cp, Matind ma WHERE SUBSTRING(x.clvdep, 1, 3)=c.clave "
			+ "AND SUBSTRING(x.clvfun, 1, 8)=m.campo8 AND ma.cvetemas=cp.cvetemas AND LENGTH(cp.cvetemas)=6 "
			+ "GROUP BY ma.id, x.clvdep, x.clvfun, c.nombre, c.clave, m.campo7, cp.descripcion, cp.cvetemas "
			+ "ORDER BY x.clvdep ASC")
	List<MatrizIndicadoresDTO> findAllDepMatrizInd();
	
	/**
	 * Find all by order by id asc.
	 *
	 * @return the list
	 */
	List<Matind> findAllByOrderByIdAsc();

	List<Matind> findAllByOrderByClvdepgAscCveprogAscCvetemasAsc();
	
	Integer countByIdsectorAndCvetemas(Integer idsector, String cvetemas);
}
