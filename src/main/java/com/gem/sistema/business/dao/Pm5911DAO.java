package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.Pm5911DTO;

public interface Pm5911DAO {

	List<Pm5911DTO> save(Pm5911DTO pm5911dto);

	List<Pm5911DTO> findByTableName(String tableName);

	List<Pm5911DTO> modify(Pm5911DTO pm5911dto);

	List<Pm5911DTO> delete(Pm5911DTO pm5911dto);

}
