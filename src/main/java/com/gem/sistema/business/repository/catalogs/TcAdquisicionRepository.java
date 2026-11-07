package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcPersonalAdministrativo;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcAdquisicionRepository.
 */
@Repository(value = "tcAdquisicionRepository")
public interface TcAdquisicionRepository
		extends PagingAndSortingRepository<TcAdquisicion, Long>, QueryDslPredicateExecutor<TcAdquisicion> {

	@Query("SELECT (DECODE(MAX(a.numeroControl),NULL,0,MAX(a.numeroControl)))+1 from TcAdquisicion a")
	Integer getNextConsecutivo();

	@Query("SELECT a FROM TcAdquisicion a 				" + " left join fetch a.bienServicio b "
			+ " left join fetch a.formaPago f 			" + " left join fetch a.origenRecurso o "
			+ " left join fetch a.tipoGasto g 			" + " left join fetch a.tramite t "
			+ " left join fetch a.administrativo ad 	" + " left join fetch a.estado e "
			+ " join fetch ad.administrativa area 		" + " left join fetch a.giro gc ")
	List<TcAdquisicion> getAllAdquisicionesWithRelations();

	Long countByAdministrativo(TcPersonalAdministrativo administrativo);

	List<TcAdquisicion> findAll();

	@Query("SELECT a  FROM TcAdquisicion a      		" + " left join fetch a.bienServicio b    "
			+ " left join fetch a.formaPago f   		" + " left join fetch a.origenRecurso o "
			+ " left join fetch a.tipoGasto g   		" + " left join fetch a.tramite t "
			+ " left join fetch a.administrativo ad 	" + " left join fetch a.estado e "
			+ " join fetch ad.administrativa area  		" + "  left join fetch a.giro gc "
			+ "where a.numeroControl=:numeroControl ")
	TcAdquisicion findFirstByNumeroControl(@Param("numeroControl") Integer numeroControl);

	@Transactional
	void delete(Long id);

}
