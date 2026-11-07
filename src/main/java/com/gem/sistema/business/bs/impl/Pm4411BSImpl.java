package com.gem.sistema.business.bs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4411BS;
import com.gem.sistema.business.domain.Pm4411;
import com.gem.sistema.business.repository.catalogs.Pm4411Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4411BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm4411BS")
public class Pm4411BSImpl implements Pm4411BS {

	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";

	/** The pm 4411 repository. */
	@Autowired
	private Pm4411Repository pm4411Repository;

	/**
	 * Gets the pm 4411 repository.
	 *
	 * @return the pm 4411 repository
	 */
	public Pm4411Repository getPm4411Repository() {
		return pm4411Repository;
	}

	/**
	 * Sets the pm 4411 repository.
	 *
	 * @param pm4711Repository
	 *            the new pm 4411 repository
	 */
	public void setPm4411Repository(Pm4411Repository pm4711Repository) {
		this.pm4411Repository = pm4411Repository;
	}

	/** The entity. */
	Pm4411 entity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4411BS#save(com.gem.sistema.business.domain
	 * .Pm4411)
	 */
	@Override
	public boolean save(Pm4411 pm4411) {

		entity = pm4411;

		boolean bandera = this.validateTxt(pm4411);
		if (bandera) {
			pm4411Repository.save(pm4411);
		}

		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4411BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm4411)
	 */
	@Override
	public boolean validateTxt(Pm4411 pm4411) {
		boolean bandera = Boolean.TRUE;

		if (null == pm4411.getObs1()) {
			pm4411.setObs1(EMPTY_VALUE);
		}
		if (null == pm4411.getObs2()) {
			pm4411.setObs2(EMPTY_VALUE);
		}
		if (null == pm4411.getObs3()) {
			pm4411.setObs3(EMPTY_VALUE);
		}
		if (null == pm4411.getObs4()) {
			pm4411.setObs4(EMPTY_VALUE);
		}
		if (null == pm4411.getObs5()) {
			pm4411.setObs5(EMPTY_VALUE);
		}
		if (null == pm4411.getObs6()) {
			pm4411.setObs6(EMPTY_VALUE);
		}

		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm4411BS#countByMes(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean countByMes(Integer idSector, Integer mes) {

		return pm4411Repository.countRows(idSector, mes) > 0 ? false : true;
	}
}
