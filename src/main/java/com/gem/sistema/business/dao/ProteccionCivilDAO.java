package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.ProteccionCivilDTO;


public interface ProteccionCivilDAO {

	List<ProteccionCivilDTO> save(ProteccionCivilDTO proteccionCivilDTO);

	List<ProteccionCivilDTO> findByTableName(String tableName);

	List<ProteccionCivilDTO> modify(ProteccionCivilDTO proteccionCivilDTO);

	List<ProteccionCivilDTO> delete(ProteccionCivilDTO proteccionCivilDTO);

}
