/**
 * 
 */
package com.gem.sistema.web.datamodel;

import static com.gem.sistema.business.predicates.NatgasPredicates.findByClvgas;
import static com.gem.sistema.business.predicates.NatgasPredicates.findByIdSector;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class NatgasDataModel.
 *
 * @author gauss
 * s
 */
@Service
@Scope("prototype")
public class NatgasDataModel extends CustomPagingModel<Natgas> {
	
	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	
	/** Componente de servicio. */
	@Autowired
	private NatgasRepository natgasRepository;
	
	/** Default filtro. */
	private int idSector;
	
	/**
	 * Instantiates a new natgas data model.
	 */
	public NatgasDataModel() {
		super();
	           
	}
	
	
	/**
	 * Instantiates a new natgas data model.
	 *
	 * @param keyword the keyword
	 */
	public NatgasDataModel(final int keyword ) {
		super();
	    this.idSector = keyword;
	           
	}
	

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
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf( natgasRepository.count(search) ).intValue();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<Natgas> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<Natgas> page = natgasRepository.findAll(search,pageable);
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<Natgas> fetchData(Pageable pageable) {
		Page<Natgas> page = natgasRepository.findAll(pageable);
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		return Long.valueOf( natgasRepository.count() ).intValue();
	}
	
	/**
	 * Construct search query.
	 *
	 * @param filters the filters
	 * @return the predicate
	 */
	private Predicate constructSearchQuery(Map<String, Object> filters) {
        BooleanBuilder mainQuery = new BooleanBuilder();
        
        mainQuery.and(findByIdSector(getIdSector()));
       
        String clvgas = MapUtils.getString(filters, "clvgas");;
        if (isNotBlank(clvgas)) {
            mainQuery.and(findByClvgas(trimToEmpty(clvgas)));
        }

        return mainQuery;
    }

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		return new Sort(Sort.Direction.ASC, "clvgas");
	}


	/**
	 * Gets the id sector.
	 *
	 * @return the id sector
	 */
	public int getIdSector() {
		return idSector;
	}


	/**
	 * Sets the id sector.
	 *
	 * @param idSector the new id sector
	 */
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}



	
	
	
}
