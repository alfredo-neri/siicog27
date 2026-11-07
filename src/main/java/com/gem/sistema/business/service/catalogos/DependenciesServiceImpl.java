package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.util.UtilJPA;

// TODO: Auto-generated Javadoc
/**
 * The Class DependenciesServiceImpl.
 *
 * @author Juan Carlos
 */
@Service
public class DependenciesServiceImpl implements DependenciesService {

	
	/**  Repositorio de dependencias. */ 
	@Autowired
	private CatdepRepository catdepRepository;
	
	/** The muninep repository. */
	@Autowired
	private MuniNepRepository muninepRepository;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DependenciesServiceImpl.class);
	
	/**  Mensaje de error cuando no existen los niveles previos. */
	@Value("${catalog.message.error.catdep.previouslevel}")
	private String ERROR_PREVIOUS_LEVEL;
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.DependenciesService#isValidPreviousLevel(com.gem.sistema.business.domain.Catdep)
	 */
	@Override
	public boolean isValidPreviousLevel(final Catdep catalog, final StringBuilder errorMsg) {
		boolean result = Boolean.TRUE;		
		final List<String> parameters = UtilJPA.getParentsToValidateDependencia(catalog.getClvdep()); 
		LOGGER.info(":: Valores regresados parentToValidate-> "+ parameters);
		for(final String parameter: parameters) {
			List<Catdep> antecesor = catdepRepository.findByClvdep(parameter);
			if(CollectionUtils.isEmpty(antecesor)) {
				errorMsg.append("Debe existir la Clave: " + parameter);
				result = Boolean.FALSE;
				break;
			}
			if(antecesor.get(0).getUltniv()==null || antecesor.get(0).getUltniv().toUpperCase().equals("S")){
				errorMsg.append("La clave antecesora: " + parameter + "es de ultimo nivel");
				result = Boolean.FALSE;
				break;
			}
		}				
		return result;
	}
	
	

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.DependenciesService#isProgramDepen(com.gem.sistema.business.domain.Catdep)
	 */
	@Override
	public boolean isProgramDepen(Catdep catdep) {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.DependenciesService#tieneSucesores(com.gem.sistema.business.domain.Catdep, java.util.List)
	 */
	@Override
	public boolean tieneSucesores(Catdep catdep, List<Catdep> lista) {
		
		for(Catdep c:lista){
			final List<String> parameters = UtilJPA.getParentsToValidateDependencia(c.getClvdep());
			for(final String parameter: parameters) {
				if(catdep.getClvdep().equals(parameter)){
					return Boolean.TRUE;
				}
			}	
		}
		return Boolean.FALSE;
	}

	
	/*public boolean isProgramDepen(Catdep catdep) {
		boolean bandera = Boolean.FALSE;
		//List<Programamun> lis = programamunRepository.findByCveDpgCvePa(catdep.getClaveDgm(), catdep.getClaveDaa());
		if(lis.isEmpty()) {
			bandera = Boolean.TRUE;
		}
		return bandera;
	}
	*/
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.DependenciesService#isValidPreviousLevel(com.gem.sistema.business.domain.Muninep, java.lang.StringBuilder, java.lang.Integer)
	 */
	@Override
	public boolean isValidPreviousLevel(final Muninep catalog, final StringBuilder errorMsg, Integer idsector) {
		boolean result = Boolean.TRUE;
		final List<String> parameters = UtilJPA.getParentsToValidateMuninep(catalog.getCampo7());
		LOGGER.info(":: Valores regresados parentToValidate-> "+ parameters);
		for(final String parameter: parameters) {
			if (parameter.equals(catalog.getCampo7())) {
				continue;
			}
			if(CollectionUtils.isEmpty(muninepRepository.findByCampo7AndIdsector(parameter, idsector))) {
				errorMsg.append("Debe existir el registro antecesor: " + parameter);
				result = Boolean.FALSE;
				break;
			}
		}
		return result;
	}
}
