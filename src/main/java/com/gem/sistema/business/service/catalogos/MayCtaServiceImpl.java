package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.repository.catalogs.MayctaRepository;
import com.gem.sistema.util.UtilJPA;

// TODO: Auto-generated Javadoc
/**
 * The Class MayCtaServiceImpl.
 */
@Service("mayctaService")
public class MayCtaServiceImpl implements MayCtaService {


	/** The maycta repository. */
	@Autowired
	private MayctaRepository mayctaRepository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.MayCtaService#isValidPreviousLevel(com.gem.sistema.business.domain.Maycta, java.lang.StringBuilder)
	 */
	public boolean isValidPreviousLevel(final Maycta catalog, final StringBuilder errorMsg) {
		boolean result = Boolean.TRUE;
		final List<String> parameters = UtilJPA.getParentsToValidate(catalog.getCuenta());
		for(final String parameter: parameters) {
			if(CollectionUtils.isEmpty(mayctaRepository.findByCuenta(parameter))) {
				errorMsg.append("Debe existir la Cuenta: " + parameter);
				result = Boolean.FALSE;
				break;
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.MayCtaService#findFirstByCuenta(java.lang.String)
	 */
	public Maycta findFirstByCuenta(String cuenta){
		return mayctaRepository.findFirstByCuenta(cuenta);

	}

}
