package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.MediadorDTO;

public interface MediadorService {

	List<MediadorDTO> save(MediadorDTO mediadorDTO);

	List<MediadorDTO> findByTableName(String tableName);

	List<MediadorDTO> modify(MediadorDTO mediadorDTO);

	List<MediadorDTO> delete(MediadorDTO mediadorDTO);

}
