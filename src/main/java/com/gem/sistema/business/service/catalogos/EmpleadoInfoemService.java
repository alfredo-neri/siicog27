package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.EmpleadoInfoem;

public interface EmpleadoInfoemService {
	
	List<EmpleadoInfoem> findAll();
	
	String covertedNumbersToLetters(Double numero);

}
