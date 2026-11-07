package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.Pm3611DAO;
import com.gem.sistema.business.dto.Pm3611DTO;

@Service(value = "pm3611Service")
public class Pm3611ServiceImpl implements Pm3611Service {

	@Autowired
	private Pm3611DAO pm3611dao;

	@Override
	public List<Pm3611DTO> save(Pm3611DTO pm3611DTO) {
		return this.pm3611dao.save(pm3611DTO);
	}

	@Override
	public List<Pm3611DTO> findByTableName(String tableName) {

		return this.pm3611dao.findByTableName(tableName);
	}

	@Override
	public List<Pm3611DTO> modify(Pm3611DTO pm3611DTO) {

		return this.pm3611dao.modify(pm3611DTO);
	}

	@Override
	public List<Pm3611DTO> delete(Pm3611DTO pm3611DTO) {

		return pm3611dao.delete(pm3611DTO);
	}

	public Pm3611DAO getPm3611dao() {
		return pm3611dao;
	}

	public void setPm3611dao(Pm3611DAO pm3611dao) {
		this.pm3611dao = pm3611dao;
	}

}
