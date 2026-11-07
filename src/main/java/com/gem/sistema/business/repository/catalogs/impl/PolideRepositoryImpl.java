/**
 * 
 */
package com.gem.sistema.business.repository.catalogs.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.collections4.MapUtils;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom;

// TODO: Auto-generated Javadoc
/**
 * Implementacion de {@link PolideRepositoryCustom}. Usando Querydsl.
 * 
 * @author Luis Sosa
 */

public class PolideRepositoryImpl extends QuerydslRepositorySupport implements PolideRepositoryCustom {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolideRepositoryImpl.class);

	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The jdbc call act cargo abono. */
	private SimpleJdbcCall jdbcCallActCargoAbono;

	/** The Constant queryCtaCount. */
	private static final String queryCtaCount = "SELECT COUNT(*) FROM GEMUSER.POLIDE ";

	/** The Constant queryCtaPageable_11. */
	private static final String queryCtaPageable_11 = "WITH CVIEW AS " + "(SELECT ID"
			+ ", ROW_NUMBER() OVER (ORDER BY RENPOL ASC) AS RN " + "FROM GEMUSER.POLIDE ";

	/** The Constant queryCtaPageable_12. */
	private static final String queryCtaPageable_12 = "WITH CVIEW AS " + "(SELECT ID, RENPOL"
			+ ", ROW_NUMBER() OVER (ORDER BY RENPOL ASC) AS RN " + "FROM GEMUSER.POLIDE ";
	
	/** The Constant selectFromPolide. */
	private static final String selectFromPolide = " POL.ID,POL.TIPPOL,POL.MESPOL,POL.CONPOL,POL.RENPOL,POL.CUENTA,POL.SCTA,POL.SSCTA,POL.SSSCTA,POL.SSSSCTA,POL.REFPOL,POL.CONCEP,POL.CAOPOL,POL.CANPOL,POL.ANOPOL,POL.RECPOL,POL.STACON,POL.CLVBAN,POL.NUMDET,POL.TIPCON,POL.DN,POL.RELA,POL.TIPR,POL.CANPOLH,POL.CUENTA1,POL.RENPOLR,POL.CLVCTO,POL.ANTPAG,POL.FECPOL,POL.CLVFUEN,POL.USERID,POL.IDSECTOR,POL.ID_REF, POL.NUMCTO, POL.NUMFAC, POL.NUMPROV, POL.RFC ";

	/** The Constant queryCtaPageable_2. */
	private static final String queryCtaPageable_2 = ")  " + "SELECT " + selectFromPolide
			+ ", 0 BMIN, CTA.NOMCTA NOMCTA FROM " + "(  " + "SELECT * FROM  " + "CVIEW   ";
	
	/** The Constant queryCtaPageable_3. */
	private static final String queryCtaPageable_3 = ") AS CV INNER JOIN GEMUSER.POLIDE AS POL ON (CV.ID = POL.ID) "
			+ " LEFT JOIN GEMUSER.CUENTA AS CTA ON (CTA.CUENTA = POL.CUENTA AND CTA.SCTA = NVL(POL.SCTA, '') AND CTA.SSCTA = NVL(POL.SSCTA, '') "
			+ " AND CTA.SSSCTA = NVL(POL.SSSCTA, '') AND CTA.SSSSCTA = NVL(POL.SSSSCTA, '') AND CTA.IDSECTOR = POL.IDSECTOR) ORDER BY CV.RN";

	/**
	 * Instantiates a new polide repository impl.
	 */
	public PolideRepositoryImpl() {
		super(Polide.class);
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.jdbcCallActCargoAbono = new SimpleJdbcCall(template).withProcedureName("SP_CAL_ACT_CARGO_ABONO_POL");
	}

	/**
	 * Gets the filters value.
	 *
	 * @param filters the filters
	 * @param whereFilters the where filters
	 * @return the filters value
	 */
	private static WhereFilters getFiltersValue(Map<String, Object> filters, WhereFilters whereFilters) {
		StringBuilder p = new StringBuilder();
		Object filterValue = null;
		String filterProperty = null;
		int idxVal = 0;
		boolean initAnd = true;
		String and = " ";

		if (filters != null && filters.size() > 0) {
			whereFilters.values = new Object[filters.size()];
			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				filterProperty = it.next();
				filterValue = filters.get(filterProperty);

				if ("anopol".equals(filterProperty)) {
					whereFilters.values[idxVal++] = filterValue;
					p = p.append(and + "anopol = ?" /* + idxVal */);
				} else if ("tippol".equals(filterProperty)) {
					whereFilters.values[idxVal++] = filterValue;
					p = p.append(and + "tippol = ?" /* + idxVal */);
				} else if ("mespol".equals(filterProperty)) {
					whereFilters.values[idxVal++] = filterValue;
					p = p.append(and + "mespol = ?" /* + idxVal */);
				} else if ("conpol".equals(filterProperty)) {
					whereFilters.values[idxVal++] = filterValue;
					p = p.append(and + "conpol = ?" /* + idxVal */);
				} else if ("idsector".equals(filterProperty)) {
					whereFilters.values[idxVal++] = filterValue;
					p = p.append(and + "idsector = ?" /* + idxVal */);
				}

				if (initAnd) {
					and = " and ";
					initAnd = false;
				}

			}
			whereFilters.whereQuery = " WHERE " + p.toString();
		}
		return whereFilters;
	}

	/**
	 * Gets the where by filters.
	 *
	 * @param filters the filters
	 * @return the where by filters
	 */
	public static final String getWhereByFilters(Map<String, Object> filters) {
		WhereFilters whereFilters = new WhereFilters();
		whereFilters = getFiltersValue(filters, whereFilters);
		return whereFilters.whereQuery;
	}

	/**
	 * Find all by filters internal.
	 *
	 * @param filters the filters
	 * @param pageable the pageable
	 * @param count the count
	 * @param renpol the renpol
	 * @return the page
	 */
	private Page<Polide> findAllByFiltersInternal(Map<String, Object> filters, Pageable pageable, Integer count,
			Integer renpol) {
		Long total = 0L;
		WhereFilters whereFilters = new WhereFilters();
		whereFilters = getFiltersValue(filters, whereFilters);
		if (count == null) {
			Integer countAll = countInternal(whereFilters);
			total = (Long) countAll.longValue();
		} else {
			total = count.longValue();
		}

		int offset = (int) pageable.getOffset();
		int pageSize = pageable.getPageSize();
		Integer limitMin = offset + 1;
		Integer limitMax = offset + pageSize;
		String pages = null;
		String queryCtaPageable = null;

		if (renpol == null) {
			pages = " WHERE RN BETWEEN " + limitMin + " AND " + limitMax + " FETCH FIRST " + pageSize + " ROWS ONLY ";

			queryCtaPageable = queryCtaPageable_11 + whereFilters.whereQuery + queryCtaPageable_2 + pages
					+ queryCtaPageable_3;

		} else {

			String queryCtaPageable_22 = ")  "
					+ "SELECT " + selectFromPolide + ", CV.BMIN, '' NOMCTA FROM (SELECT C.*, FOUNDED.BMIN FROM "
					+ "(SELECT (((CEILING(RN/" + pageSize + ".0)-1)*" + pageSize
					+ ")+1) BMIN FROM CVIEW WHERE RENPOL = " + renpol + " FETCH FIRST ROW ONLY) AS FOUNDED "
					+ "INNER JOIN CVIEW C ON C.RN BETWEEN FOUNDED.BMIN AND (FOUNDED.BMIN + " + pageSize
					+ "-1) FETCH FIRST " + pageSize + " ROWS ONLY ";
			queryCtaPageable = queryCtaPageable_12 + whereFilters.whereQuery + queryCtaPageable_22 + queryCtaPageable_3;

		}
		LOGGER.info("Query:::{}", queryCtaPageable);
		/*
		 * Query query =
		 * this.getEntityManager().createNativeQuery(queryCtaPageable,
		 * Polide.class);
		 * 
		 * int i = 1; if (whereFilters.values != null) { for (; i <=
		 * whereFilters.values.length; i++) { query.setParameter(i,
		 * whereFilters.values[i - 1]); } }
		 * 
		 * List<Polide> content = query.getResultList();
		 */
		List<Polide> content = (List<Polide>) template.query(queryCtaPageable, whereFilters.values,
				new PolizaRowMapper());

		return new PageImpl<Polide>(content, pageable, total);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer)
	 */
	public Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageable, Integer count) {
		return findAllByFiltersInternal(filters, pageable, count, null);
	}

	/**
	 * Count internal.
	 *
	 * @param whereFilters the where filters
	 * @return the integer
	 */
	private Integer countInternal(WhereFilters whereFilters) {
		String queryCountPageable = queryCtaCount + whereFilters.whereQuery;
		/*
		 * Query queryCnt =
		 * this.getEntityManager().createNativeQuery(queryCountPageable); int k
		 * = 1; if (whereFilters.values != null) { for (; k <=
		 * whereFilters.values.length; k++) { queryCnt.setParameter(k,
		 * whereFilters.values[k - 1]); } } Integer countAll = (Integer)
		 * queryCnt.getSingleResult();
		 */

		Integer countAll = template.queryForObject(queryCountPageable, whereFilters.values, Integer.class);

		return countAll;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom#count(java.util.Map)
	 */
	@Override
	public Integer count(Map<String, Object> filters) {
		WhereFilters whereFilters = new WhereFilters();
		whereFilters = getFiltersValue(filters, whereFilters);
		return countInternal(whereFilters);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageable, Integer count,
			Integer renpol) {
		return findAllByFiltersInternal(filters, pageable, count, renpol);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom#actCargoAbono(com.gem.sistema.business.domain.Polien, java.lang.Integer)
	 */
	@Override
	public void actCargoAbono(Polien polien, Integer idSector) {
		/*
		 * IN i_anopol INTEGER, IN i_tipo VARCHAR(1), IN i_conpol INTEGER , IN
		 * i_mespol INTEGER , IN i_idsector INTEGER , OUT o_cargog DECIMAL , OUT
		 * o_abonog DECIMAL , OUT o_cargo8 DECIMAL , OUT o_abono8 DECIMAL , OUT
		 * o_stapol VARCHAR(1) , OUT o_cod_error INTEGER , OUT o_msj_error
		 * VARCHAR(500)
		 */
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_anopol", polien.getAnopol())
				.addValue("i_mespol", polien.getMespol()).addValue("i_tipo", polien.getTippol())
				.addValue("i_conpol", polien.getConpol()).addValue("i_idsector", idSector);

		Map<String, Object> out = jdbcCallActCargoAbono.execute(in);

		polien.setCdebe((BigDecimal) MapUtils.getObject(out, "O_CARGOG"));
		polien.setChaber((BigDecimal) MapUtils.getObject(out, "O_ABONOG"));
		polien.setCta600((BigDecimal) MapUtils.getObject(out, "O_ABONO8"));
		polien.setCtc600((BigDecimal) MapUtils.getObject(out, "O_CARGO8"));
		polien.setStapol(MapUtils.getString(out, "O_STAPOL"));
	}

	/** The Constant SELECT_LAST_ROW. */
	private static final String SELECT_LAST_ROW = " SELECT " + selectFromPolide + ", 0 AS BMIN, null AS NOMCTA FROM GEMUSER.POLIDE POL WHERE renpol = "
			+ "( " + "SELECT max(renpol) FROM GEMUSER.POLIDE "
			+ "WHERE  anopol = %1$s and tippol = '%2$s' and conpol = %3$s and idsector = %4$s and mespol = %5$s ) "
			+ "AND  anopol = %1$s and tippol = '%2$s' and conpol = %3$s and idsector = %4$s and mespol = %5$s ";

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom#getLastRow(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Polide getLastRow(Integer anopol, String tippol, Integer conpol, Integer idsector, Integer mespol) {
		String queryLast = String.format(SELECT_LAST_ROW, anopol, tippol, conpol, idsector, mespol);
		return template.queryForObject(queryLast, new PolizaRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.PolideRepositoryCustom#consultaMovimientos(com.gem.sistema.business.domain.Polide)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Polide> consultaMovimientos(Polide polide)
    {
	
		StringBuffer sql = new StringBuffer();
		
		
		sql.append(" SELECT POLIDE.CANPOL \n");
		sql.append(" ,POLIDE.CANPOLH \n");
		sql.append(" ,POLIEN.CAPPOL \n");
		sql.append(" ,POLIEN.CONPOL \n");
		sql.append(" ,POLIDE.CONCEP \n");
		sql.append(" ,POLIDE.RENPOL \n");
		sql.append(" ,POLIEN.STAPOL \n");
		sql.append(" ,POLIEN.STAAFE \n");
		sql.append(" ,POLIEN.TIPPOL \n");
		sql.append(" FROM POLIDE \n");
		sql.append(" ,POLIEN \n");
		sql.append(" WHERE POLIDE.TIPPOL = POLIEN.TIPPOL \n"); 
		sql.append(" AND POLIDE.MESPOL=POLIEN.MESPOL  \n");
		sql.append(" AND POLIDE.CONPOL= POLIEN.CONPOL \n");
		sql.append(" AND POLIDE.ANOPOL=POLIEN.ANOPOL  \n");
		sql.append(" AND POLIDE.IDSECTOR = POLIEN.IDSECTOR \n");
		sql.append(" AND POLIDE.IDSECTOR = :P_ID_SECTOR \n");
		sql.append(" AND POLIDE.CUENTA = :P_CUENTA \n");
		sql.append(" AND POLIDE.MESPOL = :P_MESPOL \n");
		sql.append(" AND (:P_SCTA IS NULL OR POLIDE.SCTA = :P_SCTA) \n");
		sql.append(" AND (:P_SSCTA IS NULL OR POLIDE.SSCTA = :P_SSCTA) \n");
		sql.append(" AND (:P_SSSCTA IS NULL OR POLIDE.SSSCTA = :P_SSSCTA) \n");
		sql.append(" AND (:P_SSSSCTA IS NULL OR POLIDE.SSSSCTA = :P_SSSSCTA) \n");
		sql.append(" ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA \n");
		
        Query query = this.getEntityManager().createNativeQuery(sql.toString(), "consultaMovimientosMapping");
        
        query.setParameter("P_ID_SECTOR", polide.getIdsector());
        query.setParameter("P_CUENTA", polide.getCuenta());
        query.setParameter("P_MESPOL", polide.getMespol());
        query.setParameter("P_SCTA", polide.getScta());
        query.setParameter("P_SSCTA", polide.getSscta());
        query.setParameter("P_SSSCTA", polide.getSsscta());
        query.setParameter("P_SSSSCTA", polide.getSssscta());

        return query.getResultList();
    }
}