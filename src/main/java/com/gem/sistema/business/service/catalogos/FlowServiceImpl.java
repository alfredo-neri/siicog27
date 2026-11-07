package com.gem.sistema.business.service.catalogos;

import static com.gem.sistema.util.Constants.ONE;
import static com.gem.sistema.util.Constants.TWO;
import static com.gem.sistema.util.Constants.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catflu;
import com.gem.sistema.business.predicates.CatfluPredicates;
import com.gem.sistema.business.repository.catalogs.CatfluRepository;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class FlowServiceImpl.
 *
 * @author 
 */
@Service
public class FlowServiceImpl implements FlowService {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FlowServiceImpl.class);

	/** The catflu repository. */
	@Autowired
	private CatfluRepository catfluRepository;
	
	/** The error previous catflu exist. */
	@Value("${catalog.message.error.catflow.ant}")
	private String ERROR_PREVIOUS_CATFLU_EXIST;
	
	/** The Constant DF. */
	private static final DecimalFormat DF = new DecimalFormat("00.00");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.FlowService#
	 * isValidPreviousLevel( com.gem.sistema.business.domain.Catflu,
	 * java.lang.StringBuilder)
	 */
	public boolean isValidPreviousLevel(final Catflu newRow, final StringBuilder errorMsg) {
		final boolean result;

		LOGGER.info(":: VALIDA NIVELES: " + newRow);

//		if (newRow.getClvflu().toString().length() < LENGTH_REQUIRED_CLVFLU
//				|| newRow.getClvflu().toString().indexOf(".") == -1) {
//			errorMsg.append("El formato de clvflu es invalido (Formato: ##.##)");
//			return Boolean.FALSE;
//		}
		
		String formated =DF.format(newRow.getClvflu());
		

		final String level = StringUtils.substringAfter(formated, ".");

		if (level.equals("00") || "0".equals(level)) {
			result = Boolean.TRUE;
		} else {
			
			String grouper = newRow.getClvflu().toString().substring(ZERO, newRow.getClvflu().doubleValue() < 10 ? ONE : TWO).concat(".00");
			final BigDecimal aux = new BigDecimal(newRow.getClvflu()).subtract(new BigDecimal("0.01"),
					MathContext.DECIMAL64);
			String predeccesor = aux.toString().length() == 4 ? "0" + aux.toString() : aux.toString();
			LOGGER.info(":: AGRUPADOR: " + grouper + "::" + predeccesor);

			final Predicate predicate = CatfluPredicates.isLocalidadUnique(Double.valueOf(grouper), newRow.getIdsector());
			final Predicate predicatePredeccesor = CatfluPredicates.isLocalidadUnique(Double.valueOf(predeccesor), newRow.getIdsector());
			result = catfluRepository.count(predicate) > ZERO && catfluRepository.count(predicatePredeccesor) > ZERO;
		}

		if (BooleanUtils.negate(result)) {
			errorMsg.append(ERROR_PREVIOUS_CATFLU_EXIST);
			newRow.setGruflu(StringUtils.EMPTY);
			newRow.setSguflu(StringUtils.EMPTY);
		}

		return result;
	}

}
