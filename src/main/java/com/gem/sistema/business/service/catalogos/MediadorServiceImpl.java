package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.MediadorDAO;
import com.gem.sistema.business.dto.MediadorDTO;

@Service(value = "mediadorService")
public class MediadorServiceImpl implements MediadorService {

	@Autowired
	private MediadorDAO mediadorDAO;

	@Override
	public List<MediadorDTO> save(MediadorDTO MediadorDTO) {
		return this.mediadorDAO.save(MediadorDTO);
	}

	@Override
	public List<MediadorDTO> findByTableName(String tableName) {

		return this.mediadorDAO.findByTableName(tableName);
	}

	@Override
	public List<MediadorDTO> modify(MediadorDTO MediadorDTO) {

		return this.mediadorDAO.modify(MediadorDTO);
	}

	@Override
	public List<MediadorDTO> delete(MediadorDTO MediadorDTO) {

		return mediadorDAO.delete(MediadorDTO);
	}

	public MediadorDAO getMediadorDAO() {
		return mediadorDAO;
	}

	public void setMediadorDAO(MediadorDAO mediadorDAO) {
		this.mediadorDAO = mediadorDAO;
	}

}
