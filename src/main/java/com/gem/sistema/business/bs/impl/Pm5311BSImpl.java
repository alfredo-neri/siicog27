package com.gem.sistema.business.bs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm5311BS;
import com.gem.sistema.business.domain.Pm5311;
import com.gem.sistema.business.repository.catalogs.Pm5311Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5311BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm5311BS")
public class Pm5311BSImpl implements Pm5311BS {

	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";

	/** The pm 5311 repository. */
	@Autowired
	private Pm5311Repository pm5311Repository;

	/**
	 * Gets the pm 5311 repository.
	 *
	 * @return the pm 5311 repository
	 */
	public Pm5311Repository getPm5311Repository() {
		return pm5311Repository;
	}

	/**
	 * Sets the pm 5311 repository.
	 *
	 * @param pm4711Repository
	 *            the new pm 5311 repository
	 */
	public void setPm5311Repository(Pm5311Repository pm4711Repository) {
		this.pm5311Repository = pm5311Repository;
	}

	/** The entity. */
	Pm5311 entity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm5311BS#save(com.gem.sistema.business.domain
	 * .Pm5311)
	 */
	@Override
	public boolean save(Pm5311 pm5311) {

		entity = pm5311;

		boolean bandera = this.validateTxt(pm5311);
		if (bandera) {
			pm5311Repository.save(pm5311);
		}

		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm5311BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm5311)
	 */
	@Override
	public boolean validateTxt(Pm5311 pm5311) {
		boolean bandera = Boolean.TRUE;

		if (null == pm5311.getObs1()) {
			pm5311.setObs1(EMPTY_VALUE);
		}
		if (null == pm5311.getObs2()) {
			pm5311.setObs2(EMPTY_VALUE);
		}
		if (null == pm5311.getObs3()) {
			pm5311.setObs3(EMPTY_VALUE);
		}
		if (null == pm5311.getObs4()) {
			pm5311.setObs4(EMPTY_VALUE);
		}
		if (null == pm5311.getObs5()) {
			pm5311.setObs5(EMPTY_VALUE);
		}
		if (null == pm5311.getObs6()) {
			pm5311.setObs6(EMPTY_VALUE);
		}

		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5311BS#countByMes(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean countByMes(Integer idSector, Integer mes) {

		return pm5311Repository.countRows(idSector, mes) > 0 ? false : true;
	}
}
