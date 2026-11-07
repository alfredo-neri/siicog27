package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.vo.FlujosIncorrectosVO;

public interface FlujosIncorrectosDAO {

	List<FlujosIncorrectosVO> findByMonth(Integer month);

	FlujosIncorrectosVO findByCount(FlujosIncorrectosVO flujo);

	String deleteFlujo(FlujosIncorrectosVO deleteFolujo);

}
