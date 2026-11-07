package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gem.sistema.business.dao.ProteccionCivilDAO;
import com.gem.sistema.business.dto.ProteccionCivilDTO;

@Service(value = "proteccionCivilService")
public class ProteccionCivilServiceImpl implements ProteccionCivilService {

	@Autowired
	private ProteccionCivilDAO proteccionCivilDAO;

	@Override
	public List<ProteccionCivilDTO> save(ProteccionCivilDTO proteccionCivilDTO) {
		return this.proteccionCivilDAO.save(proteccionCivilDTO);
	}

	@Override
	public List<ProteccionCivilDTO> findByTableName(String tableName) {

		return this.proteccionCivilDAO.findByTableName(tableName);
	}

	@Override
	public List<ProteccionCivilDTO> modify(ProteccionCivilDTO ProteccionCivilDTO) {

		return this.proteccionCivilDAO.modify(ProteccionCivilDTO);
	}

	@Override
	public List<ProteccionCivilDTO> delete(ProteccionCivilDTO ProteccionCivilDTO) {

		return proteccionCivilDAO.delete(ProteccionCivilDTO);
	}

	public ProteccionCivilDAO getproteccionCivilDAO() {
		return proteccionCivilDAO;
	}

	public void setproteccionCivilDAO(ProteccionCivilDAO proteccionCivilDAO) {
		this.proteccionCivilDAO = proteccionCivilDAO;
	}

}
