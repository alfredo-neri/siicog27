package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.FuentefGobierno;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcAdquisicionPrograma;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcAdquisicionProgramaRepository.
 */
@Repository(value = "tcAdquisicionProgramaRepository")
public interface TcAdquisicionProgramasRepository extends PagingAndSortingRepository<TcAdquisicionPrograma, Long>,
		QueryDslPredicateExecutor<TcAdquisicionPrograma> {

	Long countByAdquisicion(TcAdquisicion adquisicion);

	@Query("Select  ap from TcAdquisicionPrograma ap " + " right join fetch ap.adquisicion a "
			+ " join fetch ap.cuenta p " + " join fetch ap.muninep m " + "join fetch ap.natgas n "
			+ " join fetch ap.catdep d " + " join fetch ap.fuentefGobierno f " + " where ap.adquisicion=:adquisicion")
	List<TcAdquisicionPrograma> findAllByAdquisicion(@Param("adquisicion") TcAdquisicion adquisicion);

	@Query("Select  ap from TcAdquisicionPrograma ap " + "right join fetch ap.adquisicion a "
			+ " join fetch ap.cuenta p " + " join fetch ap.muninep m " + " join fetch ap.natgas n "
			+ " join fetch ap.catdep d " + " join fetch ap.fuentefGobierno f " + " where ap.adquisicion=:adquisicion "
			+ " and ap.cuenta=:cuenta " + " and ap.muninep=:muninep " + " and ap.natgas=:natgas " + " and ap.catdep=:catdep "
			+ " and ap.fuentefGobierno=:fuentefGobierno")
	List<TcAdquisicionPrograma> findAllByAdquisicionAndCuentaAndCatdepAndMuninepAndNatgasAndFuentef(
			@Param("adquisicion") TcAdquisicion adquisicion, @Param("cuenta") Cuenta cuenta,
			@Param("catdep") Catdep catdep, @Param("muninep") Muninep muninep, @Param("natgas") Natgas natgas,
			@Param("fuentefGobierno") FuentefGobierno fuentefGobierno);

}
