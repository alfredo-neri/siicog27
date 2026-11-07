package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.vo.FlujosIncorrectosVO;

public interface FlujosIncorrectosService {

	List<FlujosIncorrectosVO> findByMonth(Integer month);

	FlujosIncorrectosVO findByCount(FlujosIncorrectosVO flujo);

	String deleteFlujo(FlujosIncorrectosVO deleteFolujo);

}
