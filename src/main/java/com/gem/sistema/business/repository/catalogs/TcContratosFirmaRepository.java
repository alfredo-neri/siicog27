package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import com.gem.sistema.business.domain.TcContratosFirma;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "tcContratosFirmaRepository")
public interface TcContratosFirmaRepository extends PagingAndSortingRepository<TcContratosFirma, Long> {

	@Query("select d,f,u from TcContratosFirma d "
			+ "left join fetch d.firma f join d.contrato a left join fetch f.usuario u "
			+ "where a.id=:id and d.posicion<4 order by d.posicion")
	List<TcContratosFirma> getFirmasAllByContratoId(@Param("id") Long id);
}
