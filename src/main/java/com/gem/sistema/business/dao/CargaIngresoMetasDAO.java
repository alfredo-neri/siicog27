package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.domain.TwMetasIngreso;

public interface CargaIngresoMetasDAO {
	
	List<TwMetasIngreso>  cargaMetas(String fileName);
	
	List<TwMetasIngreso> consultaMetas();
	
	String validaIngresoPropios(String fileName);

}
