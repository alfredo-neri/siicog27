package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import com.gem.sistema.business.domain.TrContratoDetail;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TrContratoDetailRepository extends PagingAndSortingRepository<TrContratoDetail, Long> {

	List<TrContratoDetail> findAllByOrderByContratoNumeroContratoAsc();

	List<TrContratoDetail> findAllByContratoRevisorUsuarioAndContratoEstadosContratoEstadoOrderByContratoNumeroContratoAsc(@Param("usuario") String usuario, @Param("estado") String estado);
}
