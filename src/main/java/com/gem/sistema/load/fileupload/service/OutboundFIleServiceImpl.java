/**
 * 
 */
package com.gem.sistema.load.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.predicates.FuentefPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.load.IngresoRepository;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.impl.CsvFileValidatorImpl;
import com.gem.sistema.load.fileupload.validators.results.IngreResultBean;

// TODO: Auto-generated Javadoc
/**
 * The Class OutboundFIleServiceImpl.
 *
 * @author Cesar Ocampo
 */

@Service
public class OutboundFIleServiceImpl implements OutboundFIleService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CsvFileValidatorImpl.class);

	/** The Constant HEADER. */
	private static final String HEADER = "\n\t      Reng cuenta SCTA      SSCTA           SSSCTA SSSSCTA F.FIN E1 E2 E3 E4 E5 E6 E7 E8               suma              total st\n"
			+ "---------- ------ --------- --------------- ------ ------- ----- -- -- -- -- -- -- -- -- ------------------ ------------------ --";

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = ",";

	/** The Constant OUTPUT_SEPARATOR. */
	private static final String OUTPUT_SEPARATOR = " ";

	/** The Constant CHARSET. */
	private static final String CHARSET = "ISO-8859-1";

	/** The ok. */
	@Value("${egreso.result.ok}")
	private String OK;
	
	/** The nok. */
	@Value("${egreso.result.nok}")
	private String NOK;

	/** The results. */
	private ArrayList<Ingreso> results = new ArrayList<Ingreso>();

	/**
	 * SEPARATOR .
	 */

	/**
	 * Linea a empezar a leer.
	 */
	private int lineToStart;

	/**
	 * <p>
	 * <b> "\n". </b>
	 * </p>
	 */
	private static final String EOL = "\n";

	/** The cuentae1. */
	private final String CUENTAE1 = "4000";

	/** The cuentae2. */
	private final String CUENTAE2 = "8110";
	
	/** The cuentae3. */
	private final String CUENTAE3 = "8120";
	
	/** The cuentae4. */
	private final String CUENTAE4 = "8130";
	
	/** The cuentae5. */
	private final String CUENTAE5 = "8140";
	
	/** The cuentae6. */
	private final String CUENTAE6 = "8150";

	/** The cuenta repository. */
	@Autowired
	CuentaRepository cuentaRepository;

	/** The fuente repository. */
	@Autowired
	FuentefRepository fuenteRepository;

	/** The ingreso repositoty. */
	@Autowired
	IngresoRepository ingresoRepositoty;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.service.OutboundFIleService#validaCuenta(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean validaCuenta(String cta, Long idsector) {
		// TODO Auto-generated method stub
		return this.cuentaRepository.exists(CuentaPredicates.existsOneLevelsAccount(cta,idsector));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.service.OutboundFIleService#validaFuenteF
	 * (java.lang.String)
	 */
	@Override
	public boolean validaFuenteF(String ffin) {
		// TODO Auto-generated method stub
		return this.fuenteRepository.exists(FuentefPredicates.existLiga(ffin));
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.load.fileupload.service.OutboundFIleService#validateContent(com.gem.sistema.load.fileupload.model.FileUpload, java.lang.Long)
	 */
	@Override
	public boolean validateContent(FileUpload fileUpload, Long idsector) throws Exception {
		Integer lineCount = 1;
		// Integer validCount = 0;
		Date initDate;
		Date endDate;
		File newFile = new File("/tmp/outIngre/".concat(fileUpload.getFile().getName()));
		File file = fileUpload.getFile();
		boolean resp = Boolean.TRUE;
		List<Object> out = null;

		try {
			FileUtils.write(newFile, HEADER + EOL, Charset.forName(CHARSET), Boolean.FALSE);

			LineIterator rowIterator = FileUtils.lineIterator(file, "UTF-8");
			// String error = StringUtils.EMPTY;

			while (rowIterator.hasNext()) {
				initDate = Calendar.getInstance().getTime();
				String row = rowIterator.nextLine();
				if (lineCount >= this.lineToStart) {
					try {
						out = processLine(lineCount, row, idsector);

						if (out.get(0) == Boolean.FALSE) {

							resp = false;
						}

						FileUtils.write(newFile, out.get(1) + EOL, Charset.forName(CHARSET), Boolean.TRUE);

					} catch (Exception vle) {
						LOGGER.error(vle.getMessage(), vle);
						// error = vle.getMessage();
					}

				}

				// if (error.length() <= 0) {
				// validCount++;
				// } else {
				// FileUtils.write(newFile, error + EOL,
				// Charset.forName(CHARSET),
				// Boolean.TRUE);
				// resp = Boolean.FALSE;
				// }
				// lineCount++;
				// error = StringUtils.EMPTY;
				endDate = Calendar.getInstance().getTime();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(String.format(" +-> Linea [%1$s] tiempo de procesamiento [%2$s] milisegundos.",
							lineCount, (endDate.getTime() - initDate.getTime())));
				}
			}

		} catch (IOException e) {
			throw e;
		}

		return resp;
	}

	/**
	 * Process line.
	 *
	 * @param lineCount the line count
	 * @param row the row
	 * @param idsector the idsector
	 * @return the list
	 */
	private List<Object> processLine(Integer lineCount, String row, Long idsector) {

		IngreResultBean result = new IngreResultBean();
		Ingreso ingreso = null;
		Boolean bres = false;
		ArrayList<Object> response = new ArrayList<Object>();

		String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(row, SEPARATOR);

		result.setReng(lineCount);
		result.setCuenta(tokens[0]);
		result.setScta(tokens[1]);
		result.setSscta(tokens[2]);
		result.setSsscta(tokens[3]);
		result.setSssscta(tokens[4]);
		result.setFfin(tokens[5]);
		result.setE1(BooleanUtils.toInteger(!this.validaCuenta(CUENTAE1, idsector)));
		result.setE2(BooleanUtils.toInteger(!this.validaCuenta(CUENTAE2, idsector)));
		result.setE3(BooleanUtils.toInteger(!this.validaCuenta(CUENTAE3, idsector)));
		result.setE4(BooleanUtils.toInteger(!this.validaCuenta(CUENTAE4, idsector)));
		result.setE5(BooleanUtils.toInteger(!this.validaCuenta(CUENTAE5, idsector)));
		result.setE6(BooleanUtils.toInteger(!this.validaCuenta(CUENTAE6, idsector)));
		result.setE7(BooleanUtils.toInteger(!this.validaFuenteF(tokens[5])));
		result.setE8(0);
		result.setSuma(Double.valueOf(tokens[7]));
		result.setTotal(Double.valueOf(tokens[7]));

		bres = this.validaResult(result);

		if (bres) {
			result.setSt(this.OK);
			ingreso = new Ingreso();

			ingreso.setCuenta(result.getCuenta());
			ingreso.setScta(result.getScta());
			ingreso.setSscta(result.getSscta());
			ingreso.setSsscta(result.getSsscta());
			ingreso.setSssscta(result.getSssscta());
//			ingreso.setAutoi1(Double.parseDouble(tokens[8]));
//			ingreso.setAutoi2(Double.parseDouble(tokens[9]));
//			ingreso.setAutoi3(Double.parseDouble(tokens[10]));
//			ingreso.setAutoi4(Double.parseDouble(tokens[11]));
//			ingreso.setAutoi5(Double.parseDouble(tokens[12]));
//			ingreso.setAutoi6(Double.parseDouble(tokens[13]));
//			ingreso.setAutoi7(Double.parseDouble(tokens[14]));
//			ingreso.setAutoi8(Double.parseDouble(tokens[15]));
//			ingreso.setAutoi9(Double.parseDouble(tokens[16]));
//			ingreso.setAutoi10(Double.parseDouble(tokens[17]));
//			ingreso.setAutoi11(Double.parseDouble(tokens[18]));
//			ingreso.setAutoi12(Double.parseDouble(tokens[19]));

			this.results.add(ingreso);
			result.setSt(this.OK);
		} else {
			result.setSt(this.NOK);

		}

		response.add(bres);
		response.add(result.toString(OUTPUT_SEPARATOR));

		return response;

	}

	/**
	 * Valida result.
	 *
	 * @param result the result
	 * @return the boolean
	 */
	private Boolean validaResult(IngreResultBean result) {
		if (result.getE1() == 1 || result.getE2() == 1 || result.getE3() == 1 || result.getE4() == 1
				|| result.getE5() == 1 || result.getE6() == 1 || result.getE7() == 1 || result.getE8() == 1) {
			return false;
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.load.fileupload.service.OutboundFIleService#loadIngresoToTable()
	 */
	public void loadIngresoToTable() {

		this.ingresoRepositoty.save(this.results);

	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public ArrayList<Ingreso> getResults() {
		return results;
	}

	/**
	 * Sets the results.
	 *
	 * @param results            the results to set
	 */
	public void setResults(ArrayList<Ingreso> results) {
		this.results = results;
	}

}
