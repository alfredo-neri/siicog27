package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTipoNivel;

@Repository("tcTipoNivelRepository")
public interface TcTipoNivelRepository extends PagingAndSortingRepository<TcTipoNivel, Long>, CrudRepository<TcTipoNivel, Long>{

}
