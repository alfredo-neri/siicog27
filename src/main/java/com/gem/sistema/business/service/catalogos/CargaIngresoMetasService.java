package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.TcIngresosPropio;
import com.gem.sistema.business.domain.TwMetasIngreso;

public interface CargaIngresoMetasService {

	List<TwMetasIngreso> cargaMetas(String fileName);

	List<TwMetasIngreso> consultaMetas();
	
	String validaIngresoPropios(String fileName);

}
