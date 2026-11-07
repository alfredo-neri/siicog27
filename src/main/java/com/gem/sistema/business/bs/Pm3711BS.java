package com.gem.sistema.business.bs;

import java.util.List;

import com.gem.sistema.business.dto.Pm3711DTO;

public interface Pm3711BS {

	List<Pm3711DTO> findAll();
	
	List<Pm3711DTO> deletePm3711(Pm3711DTO pm3711dto);
	
	List<Pm3711DTO> save(Pm3711DTO pm3711dto);
	
	List<Pm3711DTO> modificar(Pm3711DTO pm3711dto);
}
