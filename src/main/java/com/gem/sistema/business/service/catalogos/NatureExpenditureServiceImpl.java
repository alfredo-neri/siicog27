package com.gem.sistema.business.service.catalogos;

import static com.gem.sistema.util.Constants.ONE;
import static com.gem.sistema.util.Constants.TWO;
import static com.gem.sistema.util.Constants.ZERO;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.predicates.NatgasPredicates;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.util.UtilJPA;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class NatureExpenditureServiceImpl.
 */
@Service
public class NatureExpenditureServiceImpl implements NatureExpenditureService {

	/**  Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NatureExpenditureServiceImpl.class);						
	
	/** The Constant LENGTH_REQUIRED_CLVGAS. */
	private static final int LENGTH_REQUIRED_CLVGAS = 4; 
	
	/** The Constant NO_SUCH_VALUE_ZERO. */
	private static final int NO_SUCH_VALUE_ZERO = -1;
	
	/** The Constant FILL_THREE_ZERO. */
	private static final String FILL_THREE_ZERO = "000";
	
	/** The Constant FILL_TWO_ZERO. */
	private static final String FILL_TWO_ZERO = "00";
	
	/** The Constant FILL_ONE_ZERO. */
	private static final String FILL_ONE_ZERO = "0";
	
	/** Repositorio de Cuentas.
	 */
	@Autowired
	private NatgasRepository natgasRepository;
	
	/**  Mensaje de error cuando no existen los niveles previos. */
	@Value("${catalog.message.error.natgas.previouslevel}")
	private String ERROR_PREVIOUS_LEVEL;
	
	/**  Mensaje de error cuando no existen los niveles previos. */
	@Value("${catalog.message.error.natgas.format}")
	private String ERROR_FORMAT_CLVGAS;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.NatureExpenditureService#isValidPreviosLevels(com.gem.sistema.business.domain.Natgas)
	 */
	@Override
	public boolean isValidPreviousLevels(final Natgas newRow, final StringBuilder errorMsg) {
		final boolean result;
		
		LOGGER.info(":: Validar niveles: " + newRow);
		if(newRow.getClvgas().length() < LENGTH_REQUIRED_CLVGAS || 
		   newRow.getClvgas().indexOf(String.valueOf(ZERO)) == ZERO) {
			errorMsg.append(ERROR_FORMAT_CLVGAS);
			return Boolean.FALSE;
		}
		final int indexOfZero = newRow.getClvgas().indexOf(String.valueOf(ZERO));
		final Predicate predicate;
		if(indexOfZero >= TWO) {
			
			if(indexOfZero == TWO) {
				 
				LOGGER.info(":: Antecesor 2: " + newRow.getClvgas().substring(ZERO, indexOfZero - ONE));
				
				predicate = NatgasPredicates.existNatgas(newRow.getClvgas().substring(ZERO, indexOfZero - ONE).concat(FILL_THREE_ZERO));
				result = natgasRepository.count(predicate) > ZERO;
			} else {
				
				LOGGER.info(":: Antecesor 3: " + newRow.getClvgas().substring(ZERO, indexOfZero - ONE));
				
				predicate = NatgasPredicates.existNatgas(newRow.getClvgas().substring(ZERO, indexOfZero - ONE).concat(FILL_TWO_ZERO));
				result = natgasRepository.count(predicate) > ZERO;
			} 
			
		} else if(indexOfZero == NO_SUCH_VALUE_ZERO) {
			final String valueNumericCveGas = String.valueOf((Integer.valueOf(newRow.getClvgas()) - ONE));
			
			predicate = NatgasPredicates.existNatgas(valueNumericCveGas);
			final Predicate predicateGrouper = NatgasPredicates.existNatgas(newRow.getClvgas().substring(ZERO, newRow.getClvgas().length() - ONE).concat(FILL_ONE_ZERO));
			
			LOGGER.info(":: Predecesor: " + valueNumericCveGas + "::" + newRow.getClvgas().substring(ZERO, newRow.getClvgas().length() - ONE).concat(FILL_ONE_ZERO));
			
			result = natgasRepository.count(predicate) > ZERO && natgasRepository.count(predicateGrouper) > ZERO;
		} else {
			result = Boolean.TRUE;
		}
		
		if(BooleanUtils.negate(result)) {
			errorMsg.append(ERROR_PREVIOUS_LEVEL);
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.NatureExpenditureService#isValidPreviousLevel(com.gem.sistema.business.domain.Natgas, java.lang.StringBuilder)
	 */
	public boolean isValidPreviousLevel(final Natgas catalog, final StringBuilder errorMsg) {		
		boolean result = Boolean.TRUE;		
		final List<String> parameters = UtilJPA.getParentsToValidate(catalog.getClvgas());
		for(final String parameter: parameters) {
			if(CollectionUtils.isEmpty(natgasRepository.findByClvgas(parameter))) {
				errorMsg.append("Debe existir la Clvgas: " + parameter);
				result = Boolean.FALSE;
				break;
			}			
		}		
		return result;		
	}
	

	
}
