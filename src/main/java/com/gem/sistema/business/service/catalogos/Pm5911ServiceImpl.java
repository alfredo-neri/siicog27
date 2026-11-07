package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.Pm5911DAO;
import com.gem.sistema.business.dto.Pm5911DTO;

@Service(value = "pm5911Service")
public class Pm5911ServiceImpl implements Pm5911Service {

	@Autowired
	private Pm5911DAO pm5911DAO;

	@Override
	public List<Pm5911DTO> save(Pm5911DTO pm5911dto) {
		return this.pm5911DAO.save(pm5911dto);
	}

	@Override
	public List<Pm5911DTO> findByTableName(String tableName) {

		return this.pm5911DAO.findByTableName(tableName);
	}

	@Override
	public List<Pm5911DTO> modify(Pm5911DTO pm5911dto) {

		return this.pm5911DAO.modify(pm5911dto);
	}

	@Override
	public List<Pm5911DTO> delete(Pm5911DTO pm5911dto) {

		return pm5911DAO.delete(pm5911dto);
	}

	public Pm5911DAO getPm5911DAO() {
		return pm5911DAO;
	}

	public void setPm5911DAO(Pm5911DAO pm5911dao) {
		pm5911DAO = pm5911dao;
	}

}
