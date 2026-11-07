/**
 * 
 */
package com.gem.sistema.business.service.catalogos;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.repository.catalogs.ParametrosRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ParametroServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service
public class ParametroServiceImpl implements ParametroService {

	/** The Constant DEFAULT_SEPARATOR. */
	public static final String DEFAULT_SEPARATOR = "|";

	/** The parametros repository. */
	@Autowired
	private ParametrosRepository parametrosRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.ParametroService#getValuesList
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getValuesList(String parmName, String separator) {
		String value = this.parametrosRepository.getValorByCv(parmName);
		if (null != value) {
			return Arrays.asList(StringUtils.split(value, separator));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.ParametroService#getValuesList
	 * (java.lang.String)
	 */
	@Override
	public List<String> getValuesList(String parmName) {
		return this.getValuesList(parmName, DEFAULT_SEPARATOR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.ParametroService#isInValues(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isInValues(String parmName, String separator, String value) {
		List<String> values = this.getValuesList(parmName, separator);
		return CollectionUtils.exists(values, PredicateUtils.equalPredicate(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.ParametroService#isInValues(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isInValues(String parmName, String value) {
		return this.isInValues(parmName, DEFAULT_SEPARATOR, value);
	}

	/**
	 * Gets the parametros repository.
	 *
	 * @return the parametrosRepository
	 */
	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	/**
	 * Sets the parametros repository.
	 *
	 * @param parametrosRepository            the parametrosRepository to set
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

}
