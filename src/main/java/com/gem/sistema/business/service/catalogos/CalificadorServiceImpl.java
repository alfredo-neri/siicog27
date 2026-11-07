package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.CalificadorDAO;

import com.gem.sistema.business.dto.CalificadorDTO;

@Service(value = "calificadorService")
public class CalificadorServiceImpl implements CalificadorService {

	@Autowired
	private CalificadorDAO calificadorDAO;

	@Override
	public List<CalificadorDTO> save(CalificadorDTO CalificadorDTO) {
		return this.calificadorDAO.save(CalificadorDTO);
	}

	@Override
	public List<CalificadorDTO> findByTableName(String tableName) {

		return this.calificadorDAO.findByTableName(tableName);
	}

	@Override
	public List<CalificadorDTO> modify(CalificadorDTO CalificadorDTO) {

		return this.calificadorDAO.modify(CalificadorDTO);
	}

	@Override
	public List<CalificadorDTO> delete(CalificadorDTO CalificadorDTO) {

		return calificadorDAO.delete(CalificadorDTO);
	}

	public CalificadorDAO getCalificadorDAO() {
		return calificadorDAO;
	}

	public void setCalificadorDAO(CalificadorDAO calificadorDAO) {
		this.calificadorDAO = calificadorDAO;
	}

}
