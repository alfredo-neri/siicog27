package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TwAnteproyecto;

/**
 * @author Alfredo
 *
 */
@Repository("twAnteproyectoRepository")
public interface TwAnteproyectoRepository extends JpaRepository<TwAnteproyecto, Long> {

	List<TwAnteproyecto> findAllByIdsectorOrderByClaveAscProgramaAscPartidaAsc(Integer idsector);
}
