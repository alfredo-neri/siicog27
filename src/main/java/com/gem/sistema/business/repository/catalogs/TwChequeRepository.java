package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TwCheque;

@Repository(value = "twChequeRepository" )
public interface TwChequeRepository extends PagingAndSortingRepository<TwCheque, Long> {

	TwCheque findByPolideTippolAndPolideMespolAndPolideConpolAndPolideIdsector(String tippol, Integer mespol, Integer conpol, Integer idsector);
	
	TwCheque findByNumeroCheque(Integer numeroCheque); 
}
