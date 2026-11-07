package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.ModificaionSolicitud;
import com.gem.sistema.business.domain.TcAdquisicion;

@Repository(value = "modificacionSolicitudRepository")
public interface ModificacionSolicitudRepository extends PagingAndSortingRepository<ModificaionSolicitud, Long> {
	List<ModificaionSolicitud> findAllByAdquisicion(TcAdquisicion adquisicion);
}
