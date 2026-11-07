package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.DefensorDAO;
import com.gem.sistema.business.dto.DefensorDTO;

@Service(value = "defensorService")
public class DefensorServiceImpl implements DefensorService {

	@Autowired
	private DefensorDAO defensorDAO;

	@Override
	public List<DefensorDTO> save(DefensorDTO DefensorDTO) {
		return this.defensorDAO.save(DefensorDTO);
	}

	@Override
	public List<DefensorDTO> findByTableName(String tableName) {

		return this.defensorDAO.findByTableName(tableName);
	}

	@Override
	public List<DefensorDTO> modify(DefensorDTO defensorDTO) {

		return this.defensorDAO.modify(defensorDTO);
	}

	@Override
	public List<DefensorDTO> delete(DefensorDTO defensorDTO) {

		return defensorDAO.delete(defensorDTO);
	}

	public DefensorDAO getdefensorDAO() {
		return defensorDAO;
	}

	public void setdefensorDAO(DefensorDAO defensorDAO) {
		defensorDAO = defensorDAO;
	}

}
