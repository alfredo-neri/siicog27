package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ComparativoEgresosDAO;

@Service(value = "comparativoEgresosService")
public class ComparativoEgresosServiceImpl implements ComparativoEgresosService {
	
	@Autowired
	private ComparativoEgresosDAO comparativoEgresosDAO;

	@Override
	public String generaQueryCompartivo(Integer idSector, Integer mes) {
		return this.comparativoEgresosDAO.generaQueryCompartivo(idSector, mes);
	}

	public ComparativoEgresosDAO getComparativoEgresosDAO() {
		return comparativoEgresosDAO;
	}

	public void setComparativoEgresosDAO(ComparativoEgresosDAO comparativoEgresosDAO) {
		this.comparativoEgresosDAO = comparativoEgresosDAO;
	}

	
}
