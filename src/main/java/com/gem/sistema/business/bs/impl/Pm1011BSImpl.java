package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm1011BS;
import com.gem.sistema.business.domain.Pm1011;
import com.gem.sistema.business.repository.catalogs.Pm1011Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm1011BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm1011BS")
public class Pm1011BSImpl implements Pm1011BS {

	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;

	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";

	/** The pm 1011 repository. */
	@Autowired
	private Pm1011Repository pm1011Repository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Gets the pm 1011 repository.
	 *
	 * @return the pm 1011 repository
	 */
	public Pm1011Repository getPm1011Repository() {
		return pm1011Repository;
	}

	/**
	 * Sets the pm 1011 repository.
	 *
	 * @param pm1011Repository
	 *            the new pm 1011 repository
	 */
	public void setPm1011Repository(Pm1011Repository pm1011Repository) {
		this.pm1011Repository = pm1011Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1011BS#save(java.lang.Integer,
	 * java.util.List)
	 */

	/** The entity. */
	Pm1011 entity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm1011BS#save(com.gem.sistema.business.domain
	 * .Pm1011)
	 */
	@Override
	public boolean save(Pm1011 pm1011) {

		entity = pm1011;

		boolean bandera = findByAnual(pm1011.getIdsector(), pm1011.getAnual());
		if (bandera) {
			bandera = this.validateTxt(pm1011);
			if (bandera) {
				pm1011Repository.save(pm1011);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			}

		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1011BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public void delete(Pm1011 pm1011) {
		pm1011Repository.delete(pm1011);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1011BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public Pm1011 clean(Pm1011 pm1011) {
		pm1011.setAnual(NULL_VALUE);
		pm1011.setObsipm(EMPTY_VALUE);
		pm1011.setObstdr(EMPTY_VALUE);
		pm1011.setIpm(NULL_VALUE);
		pm1011.setTdr(NULL_VALUE);
		return pm1011;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1011BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public void cancel(Pm1011 pm1011) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1011BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */

	@Override
	public boolean validateTxt(Pm1011 pm1011) {
		boolean bandera = Boolean.TRUE;

		if (null == pm1011.getIpm()) {
			pm1011.setIpm(0);
		}
		if (null == pm1011.getTdr()) {
			pm1011.setTdr(0);
		}
		if (null == pm1011.getObsipm()) {
			pm1011.setObsipm(EMPTY_VALUE);
		}
		if (null == pm1011.getObstdr()) {
			pm1011.setObstdr(EMPTY_VALUE);
		}

		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1011BS#findByAnual(java.lang.Integer,
	 * java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual) {

		return pm1011Repository.count(idSector, anual) > 0 ? false : true;
	}

	@Override
	public void update(Pm1011 pm1011) {
		this.pm1011Repository.updatePm1011(pm1011.getIpm(), pm1011.getTdr(), pm1011.getObsipm(), pm1011.getObstdr(),
				pm1011.getId().longValue());

	}
}
