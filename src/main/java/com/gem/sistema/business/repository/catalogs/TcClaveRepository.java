package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcClave;

/**
 * @author Alfredo Neri
 *
 */
@Repository(value = "tcClaveRepository")
public interface TcClaveRepository extends PagingAndSortingRepository<TcClave, Long> {

	@Query("SELECT c FROM TcClave c LEFT JOIN c.puestos p ON p.idSector =:idSector WHERE p.clave.id IS NULL ORDER BY c.clave ")
	List<TcClave> findAvailableKeys(@Param("idSector") Integer idSector);
}
