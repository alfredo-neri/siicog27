package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TrPuestoFirma;

/**
 * @author Alfredo Neri
 *
 */
@Repository(value = "trPuestoFirmaRepository")
public interface TrPuestoFirmaRepository extends PagingAndSortingRepository<TrPuestoFirma, Long> {

	@Query("SELECT f FROM TrPuestoFirma f INNER JOIN f.puesto p INNER JOIN p.clave WHERE p.idSector=:idSector AND f.idAnio =:idAnio ORDER BY p.clave ASC")
	List<TrPuestoFirma> findFirmasBySectorAndAnio(@Param("idSector") Integer idSector, @Param("idAnio") Long idAnio);

	@Query("SELECT f FROM TrPuestoFirma f INNER JOIN f.puesto p INNER JOIN p.clave c WHERE p.idSector =:idSector AND f.idAnio =:idAnio AND c.clave =:clave")
	TrPuestoFirma findFirmaBySectorAndAnioAndClave(@Param("idSector") Integer idSector, @Param("idAnio") Long idAnio,
			@Param("clave") String clave);
}
