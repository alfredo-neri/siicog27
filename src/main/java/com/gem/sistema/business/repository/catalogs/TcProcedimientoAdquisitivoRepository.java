package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.TcProcedimientoAdquisitivo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "tcProcedimientoAdquisitivoRepository")
public interface TcProcedimientoAdquisitivoRepository
		extends PagingAndSortingRepository<TcProcedimientoAdquisitivo, Long> {

}
