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
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.service.catalogos.CuentaService;
import com.gem.sistema.business.sorts.CuentaSort;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaDataModel.
 *
 * @author gauss
 */
@ManagedBean(name = "cuentaDataModel")
@ViewScoped
public class CuentaDataModel extends CustomPagingModel<Cuenta> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	// /**
	// * Repositorio de Cuenta.
	/** The cuenta service. */
	// */
	@ManagedProperty(value = "#{cuentaService}")
	private CuentaService cuentaService;

	/** The insert. */
	private Boolean insert = Boolean.FALSE;

	/** The reset. */
	private Boolean reset = Boolean.FALSE;

	/** The selected. */
	private Cuenta selected;

	/** The bean find. */
	private Cuenta beanFind;

	/** The count. */
	private Integer count;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(Cuenta entity) {
		return entity.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		if (!insert && null == count) {
			count = cuentaService.count(this.getfilters(filters));
		}
		return this.insert ? count + 1 : count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {

		if (!insert && null == count) {
			count = cuentaService.count(this.getfilters(null));
		}
		return this.insert ? count + 1 : count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Cuenta> fetchData(Pageable pageable, Map<String, Object> filters) {
		Page<Cuenta> page = cuentaService.findAllByFilters(this.getfilters(filters), pageable,
				this.countData(this.getfilters(filters)));
		return getIfInsert(page);
	}

	/**
	 * Gets the if insert.
	 *
	 * @param page the page
	 * @return the if insert
	 */
	private List<Cuenta> getIfInsert(Page<Cuenta> page) {
		List<Cuenta> toReturn = new ArrayList<Cuenta>(page.getContent());
		if (insert) {
			if (null == this.selected) {
				this.selected = new Cuenta();
			}
			this.selected.setId(0L);
			this.selected.setEditable(Boolean.TRUE);
			toReturn.add(0, this.selected);

		}
		if (CollectionUtils.isNotEmpty(toReturn)) {
			int index = 0;
			for (Cuenta cuenta : toReturn) {
				cuenta.setIndex(index++);
			}
		}
		return toReturn;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Cuenta> fetchData(Pageable pageable) {

		Page<Cuenta> page = cuentaService.findAllByFilters(this.getfilters(null), pageable,
				this.countData(this.getfilters(null)));
		return getIfInsert(page);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		return CuentaSort.sortByAll();
	}

	/**
	 * Gets the insert.
	 *
	 * @return the insert
	 */
	public Boolean getInsert() {
		return insert;
	}

	/**
	 * Sets the insert.
	 *
	 * @param insert            the insert to set
	 */
	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public Cuenta getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected            the selected to set
	 */
	public void setSelected(Cuenta selected) {
		this.selected = selected;
	}

	/**
	 * Gets the bean find.
	 *
	 * @return the beanFind
	 */
	public Cuenta getBeanFind() {
		return beanFind;
	}

	/**
	 * Sets the bean find.
	 *
	 * @param beanFind            the beanFind to set
	 */
	public void setBeanFind(Cuenta beanFind) {
		this.beanFind = beanFind;
	}

	/**
	 * Gets the reset.
	 *
	 * @return the reset
	 */
	public Boolean getReset() {
		return reset;
	}

	/**
	 * Sets the reset.
	 *
	 * @param reset            the reset to set
	 */
	public void setReset(Boolean reset) {
		this.reset = reset;
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
		if (!StringUtils.isEmpty(beanFind.getCuenta())) {
			filters.put("accountInput", beanFind.getCuenta());
		}
		if (!StringUtils.isEmpty(beanFind.getScuenta())) {
			filters.put("saccountInput", beanFind.getScuenta());
		}
		if (!StringUtils.isEmpty(beanFind.getSscuenta())) {
			filters.put("ssaccountInput", beanFind.getSscuenta());
		}
		if (!StringUtils.isEmpty(beanFind.getSsscuenta())) {
			filters.put("sssaccountInput", beanFind.getSsscuenta());
		}
		if (!StringUtils.isEmpty(beanFind.getSssscuenta())) {
			filters.put("ssssaccountInput", beanFind.getSssscuenta());
		}
		if (!StringUtils.isEmpty(beanFind.getNomcta())) {
			filters.put("titleInput", beanFind.getNomcta());
		}
		if (null != beanFind.getIdsector()) {
			filters.put("idsector", beanFind.getIdsector().longValue());
		}
		return filters;
	}

	/**
	 * Gets the cuenta service.
	 *
	 * @return the cuentaService
	 */
	public CuentaService getCuentaService() {
		return cuentaService;
	}

	/**
	 * Sets the cuenta service.
	 *
	 * @param cuentaService            the cuentaService to set
	 */
	public void setCuentaService(CuentaService cuentaService) {
		this.cuentaService = cuentaService;
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
