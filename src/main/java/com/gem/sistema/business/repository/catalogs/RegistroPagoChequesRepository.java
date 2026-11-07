package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TwRegistroPagoCheque;

public interface RegistroPagoChequesRepository extends PagingAndSortingRepository<TwRegistroPagoCheque, Long>, CrudRepository<TwRegistroPagoCheque, Long>,
		QuerydslPredicateExecutor<TwRegistroPagoCheque>{
	
	TwRegistroPagoCheque findByDestinario(String destinario);
	
	TwRegistroPagoCheque findByFolio(Integer folio);
	
	@Query("select t from TwRegistroPagoCheque t where t.tcPeriodo.id = :monthId")
	TwRegistroPagoCheque findByMonth(Integer monthId);

}
