/**
 * 
 */
package com.gem.sistema.web.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.service.catalogos.NatgasService;

// TODO: Auto-generated Javadoc
/**
 * The Class NatgasCustomDataModel.
 *
 * @author gauss
 */
@ManagedBean(name = "natgasCustomDataModel")
@ViewScoped
public class NatgasCustomDataModel extends CustomPagingModel<Natgas> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	// /**
	// * Repositorio de Natgas.
	/** The natgas service. */
	// */
	@ManagedProperty(value = "#{natgasService}")
	private NatgasService natgasService;

	/** The selected. */
	private Natgas selected;

	/** The bean find. */
	private Natgas beanFind;

	/** The count. */
	private Integer count;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(Natgas entity) {
		return entity.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		if (null == count) {
			count = natgasService.count(this.getfilters(filters));
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {

		if (null == count) {
			count = natgasService.count(this.getfilters(null));
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Natgas> fetchData(Pageable pageable, Map<String, Object> filters) {
		Page<Natgas> page = natgasService.findAllByFilters(this.getfilters(filters), pageable,
				this.countData(this.getfilters(filters)));
		return getIfInsert(page);
	}

	/**
	 * Gets the if insert.
	 *
	 * @param page the page
	 * @return the if insert
	 */
	private List<Natgas> getIfInsert(Page<Natgas> page) {
		List<Natgas> toReturn = new ArrayList<Natgas>(page.getContent());
		if (CollectionUtils.isNotEmpty(toReturn)) {
			int index = 0;
			for (Natgas natgas : toReturn) {
				natgas.setIndex(index++);
			}
		}
		return toReturn;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Natgas> fetchData(Pageable pageable) {

		Page<Natgas> page = natgasService.findAllByFilters(this.getfilters(null), pageable,
				this.countData(this.getfilters(null)));
		return getIfInsert(page);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		return null;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public Natgas getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected            the selected to set
	 */
	public void setSelected(Natgas selected) {
		this.selected = selected;
	}

	/**
	 * Gets the bean find.
	 *
	 * @return the beanFind
	 */
	public Natgas getBeanFind() {
		return beanFind;
	}

	/**
	 * Sets the bean find.
	 *
	 * @param beanFind            the beanFind to set
	 */
	public void setBeanFind(Natgas beanFind) {
		this.beanFind = beanFind;
	}

	/**
	 * Gets the filters.
	 *
	 * @param filters the filters
	 * @return the filters
	 */
	private Map<String, Object> getfilters(Map<String, Object> filters) {
		if (null == filters) {
			filters = new HashMap<String, Object>();
		}
		if (null != beanFind.getIdsector()) {
			filters.put("idsector", beanFind.getIdsector().longValue());
		}
		return filters;
	}

	/**
	 * Gets the natgas service.
	 *
	 * @return the natgasService
	 */
	public NatgasService getNatgasService() {
		return natgasService;
	}

	/**
	 * Sets the natgas service.
	 *
	 * @param natgasService            the natgasService to set
	 */
	public void setNatgasService(NatgasService natgasService) {
		this.natgasService = natgasService;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

}
