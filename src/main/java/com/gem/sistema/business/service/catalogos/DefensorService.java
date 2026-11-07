package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.DefensorDTO;

public interface DefensorService {

	List<DefensorDTO> save(DefensorDTO defensorDTO);

	List<DefensorDTO> findByTableName(String tableName);

	List<DefensorDTO> modify(DefensorDTO defensorDTO);

	List<DefensorDTO> delete(DefensorDTO defensorDTO);

}
