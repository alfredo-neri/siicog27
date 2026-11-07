package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.ProteccionCivilDTO;

public interface ProteccionCivilService {

	List<ProteccionCivilDTO> save(ProteccionCivilDTO proteccionCivilDTO);

	List<ProteccionCivilDTO> findByTableName(String tableName);

	List<ProteccionCivilDTO> modify(ProteccionCivilDTO proteccionCivilDTO);

	List<ProteccionCivilDTO> delete(ProteccionCivilDTO proteccionCivilDTO);

}
