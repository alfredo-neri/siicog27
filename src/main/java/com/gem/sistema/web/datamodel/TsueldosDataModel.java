/**
 * 
 */
package com.gem.sistema.web.datamodel;

import static com.gem.sistema.util.Constants.ONE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Tsueldo;
import com.gem.sistema.business.predicates.SueldosPredicate;
import com.gem.sistema.business.repository.catalogs.SueldoRepository;
import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class TsueldosDataModel.
 */
@Repository("tsueldoDataModel")
public class TsueldosDataModel extends CustomPagingModel<Tsueldo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1581409038094873988L;

	/** Componente de servicio. */
	@Autowired
	private SueldoRepository sueldoRepository;

	/**
	 * Gets the sueldo repository.
	 *
	 * @return the sueldo repository
	 */
	public SueldoRepository getSueldoRepository() {
		return sueldoRepository;
	}

	/**
	 * Sets the sueldo repository.
	 *
	 * @param sueldoRepository the new sueldo repository
	 */
	public void setSueldoRepository(SueldoRepository sueldoRepository) {
		this.sueldoRepository = sueldoRepository;
	}

	/** Bandera que determina si la respuesta del fecth data contendrá un resgistro para la inserción. */
	private boolean insert = Boolean.FALSE;

	/**
	 * Bandera de búsqueda por acercamiento.
	 */
	private boolean find = Boolean.FALSE;

	/**
	 * Bean de busqueda.
	 */
	private Tsueldo findBean;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(Tsueldo entity) {
		return entity.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		return 0;

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Tsueldo> fetchData(Pageable pageable, Map<String, Object> filters) {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Tsueldo> fetchData(Pageable pageable) {
		List<Tsueldo> toReturn = null;
		if (this.isFind()) {
			toReturn = sueldoRepository.findAll(SueldosPredicate.findByLikeComposite(this.getFindBean()), pageable)
					.getContent();
		} else {
			toReturn = sueldoRepository.findAll(pageable).getContent();
		}
		if (this.isInsert()) {
			if(toReturn == null){
				toReturn = new ArrayList<>();
			}
			Tsueldo zero = new Tsueldo();
			zero.setIndex(Constants.ZERO);
			toReturn.add(0, zero);
		}
		
		if(toReturn.size() > ONE) {
			for(final Tsueldo bean: toReturn.subList(ONE, toReturn.size())) {
				bean.setIndex(bean.getIndex() + ONE);
			}
		}
	
		return toReturn;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		return Long.valueOf(sueldoRepository.count()).intValue();
	}

	/**
	 * Checks if is insert.
	 *
	 * @return true, if is insert
	 */
	public boolean isInsert() {
		return insert;
	}

	/**
	 * Sets the insert.
	 *
	 * @param insert the new insert
	 */
	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	/**
	 * Gets the find bean.
	 *
	 * @return the find bean
	 */
	public Tsueldo getFindBean() {
		return findBean;
	}

	/**
	 * Sets the find bean.
	 *
	 * @param findBean the new find bean
	 */
	public void setFindBean(Tsueldo findBean) {
		this.findBean = findBean;
	}

	/**
	 * Checks if is find.
	 *
	 * @return true, if is find
	 */
	public boolean isFind() {
		return find;
	}

	/**
	 * Sets the find.
	 *
	 * @param find the new find
	 */
	public void setFind(boolean find) {
		this.find = find;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		// TODO Auto-generated method stub
		return null;
	}

}
