package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.domain.TrContratoSolicitud;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "trContratoSolicitudRepository")
public interface TrContratoSolicitudRepository extends PagingAndSortingRepository<TrContratoSolicitud, Long>{

	List<TrContratoSolicitud> findAllByContrato(TcContrato contrato);
	
	TrContratoSolicitud findByContratoAndSolicitud(TcContrato contrato, TcAdquisicion adquisicion);
}
