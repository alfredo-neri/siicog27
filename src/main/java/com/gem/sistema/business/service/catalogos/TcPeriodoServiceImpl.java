package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.TcPeriodoDAO;
import com.gem.sistema.business.domain.TcPeriodo;

@Service(value = "tcPeriodoService")
public class TcPeriodoServiceImpl implements TcPeriodoService {
	
	@Autowired
	private TcPeriodoDAO tcPeriodoDAO;

	@Override
	public void save(TcPeriodo tcPeriodo) {
		this.tcPeriodoDAO.save(tcPeriodo);
		
	}

	@Override
	public List<TcPeriodo> findByPeriodo(Integer tipoPeriodo) {
		
		return this.tcPeriodoDAO.findByPeriodo(tipoPeriodo);
	}

	public TcPeriodoDAO getTcPeriodoDAO() {
		return tcPeriodoDAO;
	}

	public void setTcPeriodoDAO(TcPeriodoDAO tcPeriodoDAO) {
		this.tcPeriodoDAO = tcPeriodoDAO;
	}
	
	

}
