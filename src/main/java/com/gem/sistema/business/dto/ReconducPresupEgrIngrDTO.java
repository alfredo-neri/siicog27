/**
 * 
 */
package com.gem.sistema.business.dto;

import java.io.File;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ReconducPresupEgrIngrDTO.
 *
 * @author Gerardo CUERO
 */
public class ReconducPresupEgrIngrDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4038707469862604702L;
	
	/** The cod error. */
	Integer codError;
	
	/** The msg error. */
	String msgError;
	
	/** The path file incomes. */
	String pathFileIncomes;
	
	/** The file name incomes. */
	String fileNameIncomes;
	
	/** The path file expenses. */
	String pathFileExpenses;
	
	/** The file name expenses. */
	String fileNameExpenses;
	
	/** The result file. */
	File resultFile;

	/**
	 * Instantiates a new reconduc presup egr ingr DTO.
	 *
	 * @param codError the cod error
	 * @param msgError the msg error
	 * @param pathFileIncomes the path file incomes
	 * @param fileNameIncomes the file name incomes
	 * @param pathFileExpenses the path file expenses
	 * @param fileNameExpenses the file name expenses
	 */
	public ReconducPresupEgrIngrDTO(Integer codError, String msgError, String pathFileIncomes, String fileNameIncomes,
			String pathFileExpenses, String fileNameExpenses) {
		super();
		this.codError = codError;
		this.msgError = msgError;
		this.pathFileIncomes = pathFileIncomes;
		this.fileNameIncomes = fileNameIncomes;
		this.pathFileExpenses = pathFileExpenses;
		this.fileNameExpenses = fileNameExpenses;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the codError
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError the codError to set
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * Gets the msg error.
	 *
	 * @return the msgError
	 */
	public String getMsgError() {
		return msgError;
	}

	/**
	 * Sets the msg error.
	 *
	 * @param msgError the msgError to set
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	/**
	 * Gets the path file incomes.
	 *
	 * @return the pathFileIncomes
	 */
	public String getPathFileIncomes() {
		return pathFileIncomes;
	}

	/**
	 * Sets the path file incomes.
	 *
	 * @param pathFileIncomes the pathFileIncomes to set
	 */
	public void setPathFileIncomes(String pathFileIncomes) {
		this.pathFileIncomes = pathFileIncomes;
	}

	/**
	 * Gets the file name incomes.
	 *
	 * @return the fileNameIncomes
	 */
	public String getFileNameIncomes() {
		return fileNameIncomes;
	}

	/**
	 * Sets the file name incomes.
	 *
	 * @param fileNameIncomes the fileNameIncomes to set
	 */
	public void setFileNameIncomes(String fileNameIncomes) {
		this.fileNameIncomes = fileNameIncomes;
	}

	/**
	 * Gets the path file expenses.
	 *
	 * @return the pathFileExpenses
	 */
	public String getPathFileExpenses() {
		return pathFileExpenses;
	}

	/**
	 * Sets the path file expenses.
	 *
	 * @param pathFileExpenses the pathFileExpenses to set
	 */
	public void setPathFileExpenses(String pathFileExpenses) {
		this.pathFileExpenses = pathFileExpenses;
	}

	/**
	 * Gets the file name expenses.
	 *
	 * @return the fileNameExpenses
	 */
	public String getFileNameExpenses() {
		return fileNameExpenses;
	}

	/**
	 * Sets the file name expenses.
	 *
	 * @param fileNameExpenses the fileNameExpenses to set
	 */
	public void setFileNameExpenses(String fileNameExpenses) {
		this.fileNameExpenses = fileNameExpenses;
	}

	/**
	 * Gets the result file.
	 *
	 * @return the resultFile
	 */
	public File getResultFile() {
		return resultFile;
	}

	/**
	 * Sets the result file.
	 *
	 * @param resultFile the resultFile to set
	 */
	public void setResultFile(File resultFile) {
		this.resultFile = resultFile;
	}
	
	

}
