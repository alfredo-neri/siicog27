package com.gem.sistema.business.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.TcPeriodoDAO;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;

/**
 * @author mateo
 *
 */
@Repository
public class TcPeriodoDAOImpl implements TcPeriodoDAO {

	@Autowired
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.TcPeriodoDAO#save(com.gem.sistema.business.
	 * domain.TcPeriodo)
	 */
	@Override
	public void save(TcPeriodo tcPeriodo) {
		this.tcPeriodoRepositoy.save(tcPeriodo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.TcPeriodoDAO#findByPeriodo(com.gem.sistema.
	 * business.domain.TcPeriodo)
	 */
	@Override
	public List<TcPeriodo> findByPeriodo(Integer tipoPeriodo) {

		return this.tcPeriodoRepositoy.findByTipoPeriodo(tipoPeriodo);
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

}
