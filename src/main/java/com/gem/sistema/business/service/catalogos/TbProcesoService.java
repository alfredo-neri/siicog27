package com.gem.sistema.business.service.catalogos;

public interface TbProcesoService {

	void start(String nombre, String username, String ruta);

	void end();

	void end(String detalle);

}
