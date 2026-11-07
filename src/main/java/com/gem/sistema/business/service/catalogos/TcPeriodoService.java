package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.TcPeriodo;

/**
 * @author mateo
 *
 */
public interface TcPeriodoService {
	
    /**
     * @param tcPeriodo
     */
    void save(TcPeriodo tcPeriodo);
	
	/**
	 * @param tcPeriodo
	 * @return
	 */
	List<TcPeriodo> findByPeriodo(Integer tipoPeriodo);


}
