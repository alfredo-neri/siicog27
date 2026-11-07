package com.gem.sistema.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.DinamicQueryDAO;
import com.gem.sistema.business.domain.Pm0511;
import com.gem.sistema.ennum.constans.QueryEnum;
import com.gem.sistemas.row.mapper.Pm0511RowMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class DinamicQueryDAOImpl.
 */
@Repository
public class DinamicQueryDAOImpl implements DinamicQueryDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DinamicQueryDAOImpl.class);

	/** The Constant MAYOR_QUE. */
	private static final String MAYOR_QUE = "mayor_que";
	
	/** The Constant MAYOR_IGUAL. */
	private static final String MAYOR_IGUAL = "mayor_igual";
	
	/** The Constant IGUAL_QUE. */
	private static final String IGUAL_QUE = "igual_que";
	
	/** The Constant INICIE. */
	private static final String INICIE = "inicie_con";
	
	/** The Constant MENOR_QUE. */
	private static final String MENOR_QUE = "menor_que";
	
	/** The Constant MENOR_IGUAL. */
	private static final String MENOR_IGUAL = "menor_igual";
	
	/** The Constant ESPACE. */
	private static final String ESPACE = " ";

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Sets the jdbc template.
	 *
	 * @param jdbcTemplate the new jdbc template
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/** The s sql. */
	StringBuilder sSql = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.DinamicQueryDAO#createQuery(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Pm0511> createQuery(Integer parameter1, String parameter2, String filter1, String filter2,
			Integer idSector) {
		sSql = new StringBuilder();
		sSql.append(QueryEnum.SELETC.getsSql().toString()).append(QueryEnum.WHERE.getsSql().toString()).append(ESPACE);
		if (parameter1 > 0) {
			sSql.append(QueryEnum.TRIMESTRE.toString()).append(ESPACE);
			if (MAYOR_QUE.equals(filter1)) {
				sSql.append(QueryEnum.MAY_.getsSql()).append(ESPACE).append(parameter1);
			} else if (MAYOR_IGUAL.equals(filter1)) {
				sSql.append(QueryEnum.MAY_EQ.getsSql()).append(ESPACE).append(parameter1);
			} else if (IGUAL_QUE.equals(filter1)) {
				sSql.append(QueryEnum.EQ.getsSql()).append(ESPACE).append(parameter1);
			} else if (INICIE.equals(filter1)) {
				sSql.append(QueryEnum.INI.getsSql()).append(ESPACE).append(QueryEnum.COMILLAS.getsSql())
						.append(parameter1).append(QueryEnum.PORCIENTO.getsSql()).append(QueryEnum.COMILLAS.getsSql());
			} else if (MENOR_IGUAL.equals(filter1)) {
				sSql.append(QueryEnum.MEN_EQ.getsSql()).append(ESPACE).append(parameter1);
			} else if (MENOR_QUE.equals(filter1)) {
				sSql.append(QueryEnum.MEN_.getsSql()).append(ESPACE).append(parameter1);
			}

			if (null != parameter2) {
				sSql.append(ESPACE).append(QueryEnum.AND).append(ESPACE);
			}

		}

		if (null != parameter2) {
			sSql.append(QueryEnum.EMERGENCIA.getsSql().toString()).append(ESPACE);
			if (MAYOR_QUE.equals(filter2)) {
				sSql.append(QueryEnum.MAY_.getsSql()).append(ESPACE).append("'").append(parameter2).append("'");
			} else if (MAYOR_IGUAL.equals(filter2)) {
				sSql.append(QueryEnum.MAY_EQ.getsSql()).append(ESPACE).append("'").append(parameter2).append("'");
			} else if (IGUAL_QUE.equals(filter2)) {
				sSql.append(QueryEnum.EQ.getsSql()).append(ESPACE).append("'").append(parameter2).append("'");
			} else if (INICIE.equals(filter2)) {
				sSql.append(QueryEnum.INI.getsSql()).append(ESPACE).append(QueryEnum.COMILLAS.getsSql())
						.append(parameter2).append(QueryEnum.PORCIENTO.getsSql()).append(QueryEnum.COMILLAS.getsSql());
			} else if (MENOR_IGUAL.equals(filter2)) {
				sSql.append(QueryEnum.MEN_EQ.getsSql()).append(ESPACE).append("'").append(parameter2).append("'");
			} else if (MENOR_QUE.equals(filter2)) {
				sSql.append(QueryEnum.MEN_.getsSql()).append(ESPACE).append("'").append(parameter2).append("'");
			}
			
		}
		sSql.append(QueryEnum.AND.getsSql()).append(ESPACE).append(QueryEnum.ID_SECTOR.getsSql()).append(ESPACE)
				.append(QueryEnum.EQ.getsSql()).append(ESPACE).append(idSector).append(ESPACE)
				.append(QueryEnum.ORDER_BY.getsSql()).append(ESPACE).append(QueryEnum.TRIMESTRE);

		LOGGER.info(":: Query Dinamic, {}", sSql);
		return this.executeQuery(sSql.toString());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.DinamicQueryDAO#executeQuery(java.lang.String)
	 */
	@Override
	public List<Pm0511> executeQuery(String query) {
		return jdbcTemplate.query(query, new Pm0511RowMapper());
	}

}
