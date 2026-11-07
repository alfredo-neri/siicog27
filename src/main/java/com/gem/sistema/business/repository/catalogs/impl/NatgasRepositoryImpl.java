/**
 * 
 */
package com.gem.sistema.business.repository.catalogs.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.repository.catalogs.NatgasRepositoryCustom;

// TODO: Auto-generated Javadoc
/**
 * Implementacion de {@link NATGASRepositoryCustom}. Usando Querydsl.
 * 
 * @author Luis Sosa
 */

public class NatgasRepositoryImpl extends QueryDslRepositorySupport implements NatgasRepositoryCustom {

	/** The Constant queryCtaCount. */
	private static final String queryCtaCount = "SELECT COUNT(*) FROM GEMUSER.NATGAS ";

	/** The Constant queryCtaPageable_1. */
	private static final String queryCtaPageable_1 = "WITH CVIEW AS " + "(SELECT ID "
			+ ",ROW_NUMBER() OVER (ORDER BY CLVGAS ASC) AS RN " + "FROM GEMUSER.NATGAS ";
	
	/** The Constant queryCtaPageable_2. */
	private static final String queryCtaPageable_2 = ")  " + "SELECT CTA.* FROM " + "(  " + "SELECT * FROM  "
			+ "CVIEW   ";
	
	/** The Constant queryCtaPageable_3. */
	private static final String queryCtaPageable_3 = ") AS CV INNER JOIN GEMUSER.NATGAS AS CTA ON (CV.ID = CTA.ID) "
			+ "ORDER BY CV.RN";

	/**
	 * Instantiates a new natgas repository impl.
	 */
	public NatgasRepositoryImpl() {
		super(Natgas.class);
	}

	/**
	 * The Class WhereFilters.
	 */
	class WhereFilters {
		
		/** The where query. */
		String whereQuery = "";
		
		/** The values. */
		Object[] values = null;
	}

	/**
	 * Gets the filters value.
	 *
	 * @param filters the filters
	 * @return the filters value
	 */
	private WhereFilters getFiltersValue(Map<String, Object> filters) {
		StringBuilder p = new StringBuilder();
		Object filterValue = null;
		String filterProperty = null;
		int idxVal = 0;
		boolean initAnd = true;
		String and = " ";

		WhereFilters whereFilters = new WhereFilters();

		if (filters != null && filters.size() > 0) {
			whereFilters.values = new Object[filters.size()];
			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				filterProperty = it.next();
				filterValue = filters.get(filterProperty);

				if ("idsector".equals(filterProperty)) {
					whereFilters.values[idxVal++] = filterValue;
					p = p.append(and + "idsector = ?" + idxVal);
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
		StringBuilder p = new StringBuilder();
		Object filterValue = null;
		String filterProperty = null;
		boolean initAnd = true;
		String and = " ";

		if (filters != null && filters.size() > 0) {
			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				filterProperty = it.next();
				filterValue = filters.get(filterProperty);

				if ("accountInput".equals(filterProperty)) {
					p = p.append(and + "NATGAS LIKE '" + filterValue + "%'");
				} else if ("saccountInput".equals(filterProperty)) {
					p = p.append(and + "lower(scta) LIKE '" + ((String) filterValue).toLowerCase() + "%'");
				} else if ("ssaccountInput".equals(filterProperty)) {
					p = p.append(and + "sscta LIKE '" + filterValue + "%'");
				} else if ("sssaccountInput".equals(filterProperty)) {
					p = p.append(and + "ssscta LIKE '" + filterValue + "%'");
				} else if ("ssssaccountInput".equals(filterProperty)) {
					p = p.append(and + "sssscta LIKE '" + filterValue + "%'");
				} else if ("titleInput".equals(filterProperty)) {
					p = p.append(and + "lower(nomcta) LIKE '%" + ((String) filterValue).toLowerCase() + "%'");
				} else if ("idsector".equals(filterProperty)) {
					p = p.append(and + "idsector = " + filterValue);
				}

				if (initAnd) {
					and = " and ";
					initAnd = false;
				}

			}
		}
		return " WHERE " + p.toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.NatgasRepositoryCustom#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Page<Natgas> findAllByFilters(Map<String, Object> filters, Pageable pageable, Integer count) {

		Long total = 0L;
		WhereFilters whereFilters = getFiltersValue(filters);
		if (count == null) {
			String queryCountPageable = queryCtaCount + whereFilters.whereQuery;
			Query queryCnt = this.getEntityManager().createNativeQuery(queryCountPageable);
			int k = 1;
			if (whereFilters.values != null) {
				for (; k <= whereFilters.values.length; k++) {
					queryCnt.setParameter(k, whereFilters.values[k - 1]);
				}
			}
			Integer countAll = (Integer) queryCnt.getSingleResult();
			total = (Long) countAll.longValue();
		} else {
			total = count.longValue();
		}

		int offset = pageable.getOffset();
		Integer limitMin = offset + 1;
		Integer limitMax = offset + pageable.getPageSize();

		String pages = " WHERE RN BETWEEN " + limitMin + " AND " + limitMax + " FETCH FIRST " + pageable.getPageSize()
				+ " ROWS ONLY ";

		String queryCtaPageable = queryCtaPageable_1 + whereFilters.whereQuery + queryCtaPageable_2 + pages
				+ queryCtaPageable_3;
		Query query = this.getEntityManager().createNativeQuery(queryCtaPageable, Natgas.class);

		int i = 1;
		if (whereFilters.values != null) {
			for (; i <= whereFilters.values.length; i++) {
				query.setParameter(i, whereFilters.values[i - 1]);
			}
		}

		List<Natgas> content = query.getResultList();

		return new PageImpl<Natgas>(content, pageable, total);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.NatgasRepositoryCustom#count(java.util.Map)
	 */
	@Override
	public Integer count(Map<String, Object> filters) {
		WhereFilters whereFilters = getFiltersValue(filters);
		String queryCountPageable = queryCtaCount + whereFilters.whereQuery;
		Query queryCnt = this.getEntityManager().createNativeQuery(queryCountPageable);
		int k = 1;
		if (whereFilters.values != null) {
			for (; k <= whereFilters.values.length; k++) {
				queryCnt.setParameter(k, whereFilters.values[k - 1]);
			}
		}
		Integer countAll = (Integer) queryCnt.getSingleResult();
		return ((Long) countAll.longValue()).intValue();
	}

}
