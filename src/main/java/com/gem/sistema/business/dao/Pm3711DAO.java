package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.Pm3711DTO;

public interface Pm3711DAO {

	List<Pm3711DTO> findAll();

	List<Pm3711DTO> delete(Pm3711DTO pm3711dto);

	List<Pm3711DTO> save(Pm3711DTO pm3711dto);
	
	List<Pm3711DTO> modificar(Pm3711DTO pm3711dto);
	

}
