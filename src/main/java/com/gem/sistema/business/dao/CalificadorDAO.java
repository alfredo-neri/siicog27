package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.CalificadorDTO;

public interface CalificadorDAO {

	List<CalificadorDTO> save(CalificadorDTO calificadorDTO);

	List<CalificadorDTO> findByTableName(String tableName);

	List<CalificadorDTO> modify(CalificadorDTO calificadorDTO);

	List<CalificadorDTO> delete(CalificadorDTO calificadorDTO);

}
