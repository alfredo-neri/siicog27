package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAreaAdministrativa;
import com.gem.sistema.business.domain.TcPersonalAdministrativo;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcPersonalAdministrativoRepository.
 */
@Repository(value = "tcPersonalAdministrativoRepository")
public interface TcPersonalAdministrativoRepository extends PagingAndSortingRepository<TcPersonalAdministrativo, Long>,
		QueryDslPredicateExecutor<TcPersonalAdministrativo> {

	@Query("SELECT p,a FROM TcPersonalAdministrativo p join fetch p.administrativa a ")
	List<TcPersonalAdministrativo> getAllWithArea();

	Long countByAdministrativa(TcAreaAdministrativa administrativa);

	TcPersonalAdministrativo findFirstByEmail(String email);

	TcPersonalAdministrativo findFirstByTelefono(String telefono);

	List<TcPersonalAdministrativo> findAll();

}
