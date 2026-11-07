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
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Caratula;
import com.gem.sistema.business.repository.catalogs.CaratulaRepository;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CaratulaDataModel.
 *
 * @author gauss s
 */
@ManagedBean(name = "caratulaDataModel")
@ViewScoped
public class CaratulaDataModel extends CustomPagingModel<Caratula> {

	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	
	/** Componente de servicio. */
	@ManagedProperty(value = "#{caratulaRepository}")
	private CaratulaRepository caratulaRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(Caratula entity) {
		return entity.getId().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf(caratulaRepository.count(search)).intValue();

	}

	protected List<Caratula> addRow(Pageable pageable){
		 List<Caratula> list = this.fetchData(pageable);
		 list.add(new Caratula());
		return list;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.
	 * springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Caratula> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<Caratula> page = caratulaRepository.findAll(search, pageable);
		return depureLeftTrimScta(page.getContent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Caratula> fetchData(Pageable pageable) {
		Page<Caratula> page = caratulaRepository.findAll(pageable);
		return depureLeftTrimScta(page.getContent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		return Long.valueOf(caratulaRepository.count()).intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
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
		return mainQuery;
	}

	/**
	 * Depure left trim scta.
	 *
	 * @param caratulas the caratulas
	 * @return the list
	 */
	private List<Caratula> depureLeftTrimScta(List<Caratula> caratulas) {
		for (Caratula caratula : caratulas) {
			caratula.setScta(StringUtils.stripStart(caratula.getScta(), "0"));
		}
		return caratulas;
	}

	/**
	 * Gets the caratula repository.
	 *
	 * @return the caratulaRepository
	 */
	public CaratulaRepository getCaratulaRepository() {
		return caratulaRepository;
	}

	/**
	 * Sets the caratula repository.
	 *
	 * @param caratulaRepository            the caratulaRepository to set
	 */
	public void setCaratulaRepository(CaratulaRepository caratulaRepository) {
		this.caratulaRepository = caratulaRepository;
	}

}
