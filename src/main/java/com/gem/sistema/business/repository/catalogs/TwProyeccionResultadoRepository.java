package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TwProyeccionResultado;

/**
 * @author Alfredo Neri
 *
 */
@Repository("twProyeccionResultadoRepository")
public interface TwProyeccionResultadoRepository extends JpaRepository<TwProyeccionResultado, Long> {

	List<TwProyeccionResultado> findAllByTipo(String tipo);
}
