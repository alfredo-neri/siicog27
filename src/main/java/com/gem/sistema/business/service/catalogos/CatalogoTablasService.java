package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.TcTabla;

public interface CatalogoTablasService {

	List<TcTabla> findAllTablas();
	
	List<TcTabla> saveTabla(TcTabla tabla);
}
