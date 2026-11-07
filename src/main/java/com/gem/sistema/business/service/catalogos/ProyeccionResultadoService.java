package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.TwProyeccionResultado;

public interface ProyeccionResultadoService {

	TwProyeccionResultado save(TwProyeccionResultado proyeccionResultado);
	
	List<TwProyeccionResultado> findAllByTipo(String tipo);
	
	void delete(TwProyeccionResultado proyeccionResultado);
}
