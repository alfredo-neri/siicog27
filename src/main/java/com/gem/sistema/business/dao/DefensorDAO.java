package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.DefensorDTO;

public interface DefensorDAO {

	List<DefensorDTO> save(DefensorDTO defensorDTO);

	List<DefensorDTO> findByTableName(String tableName);

	List<DefensorDTO> modify(DefensorDTO defensorDTO);

	List<DefensorDTO> delete(DefensorDTO defensorDTO);

}
