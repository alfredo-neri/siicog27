package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TcTabularSueldosTrimestral;



public interface TcTabuladorSueldosTrimestralRepository
		extends PagingAndSortingRepository<TcTabularSueldosTrimestral, Long>, CrudRepository<TcTabularSueldosTrimestral, Long> {

}
