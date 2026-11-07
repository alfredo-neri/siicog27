package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.TcAccion;
import com.gem.sistema.business.domain.TcArea;

public interface AccionesService {

	List<TcArea> listadoDirecciones();

	List<TcAccion> listadoAcciones();

	List<TcAccion> searchAcciones(String criterio);

	TcAccion saveAccion(TcAccion accion);

	Boolean deleteAccion(TcAccion accion);
}
