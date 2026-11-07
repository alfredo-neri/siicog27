/**
 * 
 */
package com.gem.sistema.web.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.sorts.CuentaSort;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaPolizaDataModel.
 *
 * @author gauss
 */
@ManagedBean(name = "cuentaPolizaDataModel")
@SessionScoped
public class CuentaPolizaDataModel extends CustomPagingModel<Cuenta> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * Repositorio de Cuenta.
	 */
	@ManagedProperty(value = "#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** Servicios para el modulo de centas. */

	@ManagedProperty(value = "#{accountService}")
	private AccountService accountService;

	/** The insert. */
	private Boolean insert = Boolean.FALSE;

	/** The selected. */
	private Cuenta selected;

	/** The id sector. */
	private Integer idSector;

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

		if (null != this.getIdSector()) {
			if (null == filters) {
				filters = new HashMap<String, Object>();
			}
			filters.put("idsector", this.getIdSector().longValue());
		}

		Predicate search = constructSearchQuery(filters);
		int count = Long.valueOf(cuentaRepository.count(search)).intValue();
		return this.insert ? count + 1 : count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		Map<String, Object> filters = new HashMap<String, Object>();
		if (null != this.getIdSector()) {
			filters.put("idsector", this.getIdSector().longValue());
		}
		int count = Long.valueOf(cuentaRepository.count(constructSearchQuery(filters))).intValue();
		return this.insert ? count + 1 : count;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Cuenta> fetchData(Pageable pageable, Map<String, Object> filters) {
		if (null != this.getIdSector()) {
			if (null == filters) {
				filters = new HashMap<String, Object>();
			}
			filters.put("idsector", this.getIdSector().longValue());
		}
		Predicate search = constructSearchQuery(filters);
		Page<Cuenta> page = cuentaRepository.findAll(search, pageable);
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Cuenta> fetchData(Pageable pageable) {
		Map<String, Object> filters = new HashMap<String, Object>();
		if (null != this.getIdSector()) {
			filters.put("idsector", this.getIdSector().longValue());
		}
		Page<Cuenta> page = cuentaRepository.findAll(constructSearchQuery(filters), pageable);
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		return CuentaSort.sortByAll();
	}

	/**
	 * Construct search query.
	 *
	 * @param filters the filters
	 * @return the predicate
	 */
	private Predicate constructSearchQuery(Map<String, Object> filters) {
		if (MapUtils.isEmpty(filters)) {
			return null;
		} else {

			Cuenta cuenta = new Cuenta();
			for (Entry<String, Object> entry : filters.entrySet()) {
				if (entry.getKey().equalsIgnoreCase("cuenta")) {
					cuenta.setCuenta(entry.getValue().toString() + "%");
				}
				if (entry.getKey().equalsIgnoreCase("scuenta")) {
					cuenta.setScuenta(entry.getValue().toString());
				}
				if (entry.getKey().equalsIgnoreCase("sscuenta")) {
					cuenta.setSscuenta(entry.getValue().toString());
				}
				if (entry.getKey().equalsIgnoreCase("ssscuenta")) {
					cuenta.setSsscuenta(entry.getValue().toString());
				}
				if (entry.getKey().equalsIgnoreCase("sssscuenta")) {
					cuenta.setSssscuenta(entry.getValue().toString());
				}
				if (entry.getKey().equalsIgnoreCase("idsector")) {
					cuenta.setIdsector((Long) entry.getValue());
				}
			}

			return CuentaPredicates.findByAccountsLikeLastLevelComposite(cuenta);
		}
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuentaRepository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository            the cuentaRepository to set
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the accountService
	 */
	public AccountService getAccountService() {
		return accountService;
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService            the accountService to set
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
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
	 * Gets the id sector.
	 *
	 * @return the id sector
	 */
	public Integer getIdSector() {
		return idSector;
	}

	/**
	 * Sets the id sector.
	 *
	 * @param idSector the new id sector
	 */
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

}
