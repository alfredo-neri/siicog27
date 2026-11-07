package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.TcAdquisicion;

public interface AdquisicionesService {
	void deleteAdquisicionAndRelations(TcAdquisicion adquisicion) throws Exception;
}
