package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcPuesto;
import com.gem.sistema.business.domain.TcReportesFirma;

@Repository(value = "tcReportesFirmasRepository")
public interface TcReportesFirmasRepository extends PagingAndSortingRepository<TcReportesFirma, Long>, CrudRepository<TcReportesFirma, Long> {

	@Query("SELECT c FROM TcClave c  INNER JOIN c.puestos p  WHERE p.idSector =:idSector ORDER BY c.clave")
	List<TcPuesto> findClavesBySector(@Param("idSector") Integer idSector);
	
	TcReportesFirma findByIdSectorAndClave(Integer idSector, String clave);
	
}
