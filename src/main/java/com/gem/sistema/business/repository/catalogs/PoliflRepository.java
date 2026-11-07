package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Polifl;
import com.gem.sistema.business.domain.TcMes;
// TODO: Auto-generated Javadoc

/**
 * The Interface PoliflRepository.
 */
@Repository(value = "poliflRepository")
public interface PoliflRepository extends PagingAndSortingRepository<Polifl, Long>, QueryDslPredicateExecutor<TcMes> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Polifl> findAll();

	/**
	 * Método que devuelve el flujo de efectivo por la llave de la poliza.
	 * 
	 * @param mespol Mes de poliza
	 * @param tippol Tipo poliza.
	 * @param conpol Numero poliza.
	 * @return Registro de flujo de efectivo.
	 */
	Polifl findByMespolAndTippolAndConpol(Integer mespol, String tippol, Integer conpol);

	/**
	 * Find by mespol and tippol and conpol and renpol and idsector.
	 *
	 * @param mespol   the mespol
	 * @param tippol   the tippol
	 * @param conpol   the conpol
	 * @param renpol   the renpol
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Polifl> findByMespolAndTippolAndConpolAndRenpolAndIdsector(Integer mespol, String tippol, Integer conpol,
			BigDecimal renpol, Integer idsector);

	List<Polifl> findByMespolAndTippolAndConpolAndRenpol(Integer mespol, String tippol, Integer conpol, BigDecimal renpol);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Polifl> S save(S entity);

	@Transactional(timeout = 10)
	<S extends Polifl> S saveAndFlush(S entity);

	/**
	 * Delete by mespol.
	 *
	 * @param mes      the mes
	 * @param tipo     the tipo
	 * @param numero   the numero
	 * @param idSector the id sector
	 * @return the long
	 */
	@Transactional
	Long deleteByMespol(Integer mes, String tipo, Integer numero, Integer idSector);

}
