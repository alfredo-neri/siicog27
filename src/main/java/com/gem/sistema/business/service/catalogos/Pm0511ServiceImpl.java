package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.PM0511BS;
import com.gem.sistema.business.dao.DinamicQueryDAO;
import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0511ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm0511Service")
public class Pm0511ServiceImpl implements Pm0511Service {

	/** The pm 0511 BS. */
	@Autowired
	private PM0511BS pm0511BS;

	/** The dinamic query DAO. */
	@Autowired
	private DinamicQueryDAO dinamicQueryDAO;

	/**
	 * Gets the dinamic query DAO.
	 *
	 * @return the dinamic query DAO
	 */
	public DinamicQueryDAO getDinamicQueryDAO() {
		return dinamicQueryDAO;
	}

	/**
	 * Sets the dinamic query DAO.
	 *
	 * @param dinamicQueryDAO the new dinamic query DAO
	 */
	public void setDinamicQueryDAO(DinamicQueryDAO dinamicQueryDAO) {
		this.dinamicQueryDAO = dinamicQueryDAO;
	}

	/**
	 * Gets the pm 0511 BS.
	 *
	 * @return the pm 0511 BS
	 */
	public PM0511BS getPm0511BS() {
		return pm0511BS;
	}

	/**
	 * Sets the pm 0511 BS.
	 *
	 * @param pm0511bs the new pm 0511 BS
	 */
	public void setPm0511BS(PM0511BS pm0511bs) {
		pm0511BS = pm0511bs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.Pm0511Service#add()
	 */
	@Override
	public Pm0511 add() {
		return this.pm0511BS.add();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.Pm0511Service#save(com.gem.
	 * sistema.business.domain.Pm0511)
	 */
	@Override
	public List<Pm0511> save(Pm0511 pm0511) {
		return this.pm0511BS.save(pm0511);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.Pm0511Service#modify(com.gem.
	 * sistema.business.domain.Pm0511)
	 */
	@Override
	public Pm0511 modify(Pm0511 pm0511, Integer oldValue) {
		return this.pm0511BS.modify(pm0511, oldValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.Pm0511Service#delete(com.gem.
	 * sistema.business.domain.Pm0511)
	 */
	@Override
	public List<Pm0511> delete(Pm0511 pm0511) {
		return this.pm0511BS.delete(pm0511);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.Pm0511Service#orderByAsc(java.
	 * lang.Integer)
	 */
	@Override
	public List<Pm0511> orderByAsc(Integer idSector) {
		return pm0511BS.orderByAsc(idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.Pm0511Service#getSort()
	 */
	@Override
	public Sort getSort() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0511Service#getTriMonth()
	 */
	@Override
	public List<String> getTriMonth() {
		return this.pm0511BS.getTriMonth();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0511Service#executeQuery(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Pm0511> executeQuery(Integer parameter1, String parameter2, String filter1, String filter2,
			Integer idSector) {
		return this.dinamicQueryDAO.createQuery(parameter1, parameter2, filter1, filter2, idSector);
	}

}
