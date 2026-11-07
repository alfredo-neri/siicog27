/**
 * 
 */
package com.gem.sistema.web.datamodel;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pasot;
import com.gem.sistema.business.repository.catalogs.PasotRepository;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PasotDataModel.
 *
 * @author gauss
 * s
 */
/**
 * @author Gerardo CUERO
 *
 */
@ManagedBean(name = "pasotDataModel")
@ViewScoped
public class PasotDataModel extends CustomPagingModel<Pasot> {

	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	
	/** Componente de servicio. */
	// @Autowired
	@ManagedProperty(value = "#{pasotRepository}")
	private PasotRepository pasotRepository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(Pasot entity) {
		return entity.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf(pasotRepository.count(search)).intValue();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Pasot> fetchData(Pageable pageable, Map<String, Object> filters) {

		Predicate search = constructSearchQuery(filters);
		Page<Pasot> page = pasotRepository.findAll(search, pageable);
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Pasot> fetchData(Pageable pageable) {
		Page<Pasot> page = pasotRepository.findAll(pageable);
		int index = 0;
		for (Pasot pasot : page) {
			pasot.setIndex(index++);
		}
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		return Long.valueOf(pasotRepository.count()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Construct search query.
	 *
	 * @param filters the filters
	 * @return the predicate
	 */
	private Predicate constructSearchQuery(Map<String, Object> filters) {

		BooleanBuilder mainQuery = new BooleanBuilder();

		if (MapUtils.isEmpty(filters)) {
			return null;
		}

		// String name = MapUtils.getString(filters, "name");;
		// if (isNotBlank(name)) {
		// mainQuery.and(findByUsuario(trimToEmpty(name)));
		// }

		return mainQuery;
	}

	/**
	 * Gets the pasot repository.
	 *
	 * @return the pasotRepository
	 */
	public PasotRepository getPasotRepository() {
		return pasotRepository;
	}

	/**
	 * Sets the pasot repository.
	 *
	 * @param pasotRepository            the pasotRepository to set
	 */
	public void setPasotRepository(PasotRepository pasotRepository) {
		this.pasotRepository = pasotRepository;
	}

}
