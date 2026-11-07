package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAccion;
import com.gem.sistema.business.domain.TrAreaAccion;
import com.gem.sistema.business.domain.TrPresupuestoArea;

@Repository(value = "trAreaAccionRepository")
public interface TrAreaAccionRepository extends PagingAndSortingRepository<TrAreaAccion, Long> {

	TrAreaAccion findByPresupuestoAreaAndAccion(TrPresupuestoArea presupuestoArea, TcAccion accion);
	
	TrAreaAccion findByAccion(TcAccion accion);
	
	List<TrAreaAccion> findByPresupuestoArea(TrPresupuestoArea presupuestoArea);
}
