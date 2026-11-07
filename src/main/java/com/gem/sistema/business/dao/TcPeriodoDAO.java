package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.domain.TcPeriodo;

/**
 * @author mateo
 *
 */
public interface TcPeriodoDAO {
	void save(TcPeriodo tcPeriodo);
	
	List<TcPeriodo> findByPeriodo(Integer tipoPeriodo);

}
