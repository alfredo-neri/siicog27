package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcPuesto;

/**
 * @author Alfredo Neri
 *
 */
@Repository(value = "tcPuestoRepository")
public interface TcPuestoRepository extends PagingAndSortingRepository<TcPuesto, Long> {

	@Query("SELECT p FROM TrPuestoFirma f RIGHT JOIN f.puesto p ON f.idAnio =:idAnio INNER JOIN p.clave c WHERE p.idSector =:idSector ORDER BY c.clave")
	List<TcPuesto> findPuestosBySectorAndAnio(@Param("idSector") Integer idSector, @Param("idAnio") Long idAnio);
}
