package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.FlujosIncorrectosDAO;
import com.gem.sistema.business.vo.FlujosIncorrectosVO;

@Service(value = "flujosIncorrectosService")
public class FlujosIncorrectosServiceImpl implements FlujosIncorrectosService {

	@Autowired
	private FlujosIncorrectosDAO flujosIncorrectosDAO;

	@Override
	public List<FlujosIncorrectosVO> findByMonth(Integer month) {
		return flujosIncorrectosDAO.findByMonth(month);
	}

	public FlujosIncorrectosDAO getFlujosIncorrectosDAO() {
		return flujosIncorrectosDAO;
	}

	public void setFlujosIncorrectosDAO(FlujosIncorrectosDAO flujosIncorrectosDAO) {
		this.flujosIncorrectosDAO = flujosIncorrectosDAO;
	}

	@Override
	public FlujosIncorrectosVO findByCount(FlujosIncorrectosVO flujo) {

		return this.flujosIncorrectosDAO.findByCount(flujo);
	}

	@Override
	public String deleteFlujo(FlujosIncorrectosVO deleteFolujo) {
		 return this.flujosIncorrectosDAO.deleteFlujo(deleteFolujo);
	}

}
