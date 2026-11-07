/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.validators.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;
import com.gem.sistema.load.fileupload.exceptions.ValidateLineException;
import com.gem.sistema.load.fileupload.exceptions.ValidateTokenException;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.CsvFileValidator;
import com.gem.sistema.load.fileupload.validators.FieldValidator;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> SheetValidatorImpl. </b>
 * </p>
 */

public class CsvFileValidatorImpl implements CsvFileValidator, FileContentValidator {

	/**
	 * ERROR_NO_LINEAS.
	 */
	private static final String ERROR_NO_LINEAS = " +-> Linea [%1$s] no cumple con el numero de campos definidos";

	/**
	 * <p>
	 * <b> LOGGER. </b>
	 * </p>
	 */
	private static final Logger LOGGER = Logger.getLogger(CsvFileValidatorImpl.class);

	/**
	 * <p>
	 * <b> " +-> validador [%1$s] tiempo de procesamiento [%2$s] milisegundos."
	 * . </b>
	 * </p>
	 */
	private static final String MESSAGE_01 = " +-> validador [%1$s] tiempo de procesamiento [%2$s] milisegundos.";

	/**
	 * <p>
	 * <b> "\n". </b>
	 * </p>
	 */
	private static final String EOL = "\r\n";

	/**
	 * CHARSET .
	 */
	private static final String CHARSET = "UTF-8";

	/**
	 * <p>
	 * <b> Lista de validadores. </b>
	 * </p>
	 */
	private List<FieldValidator<String[]>> validators;

	/**
	 * <p>
	 * <b> Bandera que define si omite y continua con la validacion de los
	 * tokens. </b>
	 * </p>
	 */
	private Boolean skipFail;

	/**
	 * <p>
	 * <b> caracter de Separacion del archivo. </b>
	 * </p>
	 */
	private String separator;

	/**
	 * <p>
	 * <b> Texto de separacion de los mensajes de error. </b>
	 * </p>
	 */
	private String errorSeparator;

	/**
	 * <p>
	 * <b> noTokensByLine. </b>
	 * </p>
	 */
	private int noTokensByLine;

	/**
	 * Linea a empesar a leer.
	 */
	private int lineToStart;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.validators.CsvFileValidator#isValid(java.
	 * io.File, java.lang.String)
	 */
	public boolean isValid(File file, String errorFile) throws ValidateFileException {
		Integer lineCount = 1;
		Integer validCount = 0;
		Date initDate;
		Date endDate;
		File newFile = new File(errorFile);

		boolean resp = Boolean.TRUE;

		try {
			FileUtils.write(newFile, String.format("+-> File [%1$s]", file.getName()) + EOL, Charset.forName(CHARSET),
					Boolean.TRUE);

			LineIterator rowIterator = FileUtils.lineIterator(file, "ISO-8859-1");
			String error = StringUtils.EMPTY;

			while (rowIterator.hasNext()) {
				initDate = Calendar.getInstance().getTime();
				String row = rowIterator.nextLine();
				if (StringUtils.isNotEmpty(row.trim())) {
					if (lineCount >= this.lineToStart) {
						try {
							processLine(lineCount, row);
						} catch (ValidateLineException vle) {
							vle.printStackTrace();
							LOGGER.info(vle.getMessage());
							error = vle.getMessage();
						}

					}

					if (error.length() <= 0) {
						validCount++;
					} else {
						FileUtils.write(newFile, error + EOL, Charset.forName(CHARSET), Boolean.TRUE);
						resp = Boolean.FALSE;
					}
				}
				lineCount++;
				error = StringUtils.EMPTY;
				endDate = Calendar.getInstance().getTime();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(String.format(" +-> Linea [%1$s] tiempo de procesamiento [%2$s] milisegundos.",
							lineCount, (endDate.getTime() - initDate.getTime())));
				}
			}

		} catch (IOException e) {
			throw new ValidateFileException(e);
		}
		return resp;

	}

	/**
	 * <p>
	 * .
	 * <p>
	 *
	 * @param noLinea            .
	 * @param row the row
	 * @return .
	 * @throws ValidateLineException             .
	 */
	protected final String processLine(final Integer noLinea, final String row) throws ValidateLineException {

		String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(row, this.getSeparator());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("tokens.length  " + tokens.length);
			LOGGER.debug("noTokensByLine  " + this.noTokensByLine);
		}

		if (tokens.length == this.noTokensByLine) {
			try {
				processTokens(tokens);

			} catch (ValidateTokenException vte) {
				throw new ValidateLineException(String.format(" Linea [%1$s] +-> {%2$s}", noLinea, vte.getError()),
						vte);
			}

		} else {
			throw new ValidateLineException(String.format(ERROR_NO_LINEAS, noLinea));
		}

		return row;
	}

	/**
	 * Process tokens.
	 *
	 * @param tokens the tokens
	 * @throws ValidateTokenException the validate token exception
	 */
	protected void processTokens(final String[] tokens) throws ValidateTokenException {
		final List<String> errorMessages = new ArrayList<String>();
		Date initDate;
		Date endDate;
		for (FieldValidator<String[]> fieldValidator : this.getValidators()) {
			try {
				initDate = Calendar.getInstance().getTime();
				fieldValidator.validate(tokens);
				endDate = Calendar.getInstance().getTime();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(String.format(MESSAGE_01, fieldValidator.getClass().getName(),
							(endDate.getTime() - initDate.getTime())));
				}
			} catch (ValidateTokenException e) {
				LOGGER.error(e.getMessage(), e);
				errorMessages.add(e.getError());
				if (!fieldValidator.skipOnFail()) {
					break;
				}
			}
		}
		if (!errorMessages.isEmpty()) {
			throw new ValidateTokenException(StringUtils.join(errorMessages.iterator(), this.getErrorSeparator()));
		}
	}

	/**
	 * Gets the skip fail.
	 *
	 * @return the skipFail
	 */
	public Boolean getSkipFail() {
		return this.skipFail;
	}

	/**
	 * Sets the skip fail.
	 *
	 * @param skipFail            the skipFail to set
	 */
	public void setSkipFail(final Boolean skipFail) {
		this.skipFail = skipFail;
	}

	/**
	 * Gets the separator.
	 *
	 * @return the separator
	 */
	public String getSeparator() {
		return this.separator;
	}

	/**
	 * Sets the separator.
	 *
	 * @param separator            the separator to set
	 */
	public void setSeparator(final String separator) {
		this.separator = separator;
	}

	/**
	 * Gets the no tokens by line.
	 *
	 * @return the noTokensByLine
	 */
	public int getNoTokensByLine() {
		return this.noTokensByLine;
	}

	/**
	 * Sets the no tokens by line.
	 *
	 * @param noTokensByLine            the noTokensByLine to set
	 */
	public void setNoTokensByLine(final int noTokensByLine) {
		this.noTokensByLine = noTokensByLine;
	}

	/**
	 * Gets the line to start.
	 *
	 * @return the lineToStart
	 */
	public int getLineToStart() {
		return this.lineToStart;
	}

	/**
	 * Sets the line to start.
	 *
	 * @param lineToStart            the lineToStart to set
	 */
	public void setLineToStart(final int lineToStart) {
		this.lineToStart = lineToStart;
	}

	/**
	 * Gets the error separator.
	 *
	 * @return the error separator
	 */
	public String getErrorSeparator() {
		return errorSeparator;
	}

	/**
	 * Sets the error separator.
	 *
	 * @param errorSeparator the new error separator
	 */
	public void setErrorSeparator(String errorSeparator) {
		this.errorSeparator = errorSeparator;
	}

	/**
	 * Gets the validators.
	 *
	 * @return the validators
	 */
	public List<FieldValidator<String[]>> getValidators() {
		return validators;
	}

	/**
	 * Sets the validators.
	 *
	 * @param validators the new validators
	 */
	public void setValidators(List<FieldValidator<String[]>> validators) {
		this.validators = validators;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.validators.FileContentValidator#isValid(
	 * com.gem.sistema.load.fileupload.model.FileUpload)
	 */
	public boolean isValid(FileUpload fileUpload) throws ValidateFileException {
		return isValid(fileUpload.getFile(), "/gem/errores/" + fileUpload.getFile().getName());

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.load.fileupload.validators.FileContentValidator#getErrorFile()
	 */
	@Override
	public String getErrorFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
