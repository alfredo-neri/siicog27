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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.predicates.FuentefPredicates;
import com.gem.sistema.business.predicates.NatgasPredicates;
import com.gem.sistema.business.predicates.XcatproPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.impl.CsvFileValidatorImpl;
import com.gem.sistema.load.fileupload.validators.results.EgresResultBean;

// TODO: Auto-generated Javadoc
/**
 * The Class InboundFileServiceImpl.
 *
 * @author Cesar Ocampo
 */
@Service
public class InboundFileServiceImpl implements InboundFileService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CsvFileValidatorImpl.class);

	/** CHARSET. */
	private static final String CHARSET = "ISO-8859-1";

	/** The Constant HEADER. */
	private static final String HEADER = "\n\t       Ren DegDea FuPgPy       Ff  Part E1 E2 E3 E4 E5 E6 E7 E8               suma              total E9 E10 E11 E12 E13 E14 st"
			+ "\n---------- ------ ------------ --- ---- -- -- -- -- -- -- -- -- ------------------ ------------------ -- -- -- -- -- -- ---";

	/** The results. */
	private ArrayList<EgresResultBean> results = new ArrayList<EgresResultBean>();

	/** The ok. */
	@Value("${egreso.result.ok}")
	private String OK;
	
	/** The nok. */
	@Value("${egreso.result.nok}")
	private String NOK;

	/**
	 * SEPARATOR .
	 */
	private static final String SEPARATOR = ",";
	
	/**
	 * SEPARATOR .
	 */
	private static final String OUTPUT_SEPARATOR = " ";

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

	/** The e 9 acc. */
	private Integer[] e9Acc = { 5100, 5200, 5300, 5400, 5600, 5700 };
	
	/** The e 10 acc. */
	private Integer[] e10Acc = { 8211, 8212, 8213, 8214, 8215, 8216, 8217 };
	
	/** The e 11 acc. */
	private Integer[] e11Acc = { 8251, 8252, 8253, 8254, 8255, 8256, 8257 };
	
	/** The e 12 acc. */
	private Integer[] e12Acc = { 8241, 8242, 8243, 8244, 8245, 8246, 8247 };
	
	/** The e 13 acc. */
	private Integer[] e13Acc = { 8221, 8222, 8223, 8224, 8225, 8226, 8227 };
	
	/** The e 14 acc. */
	private Integer[] e14Acc = { 8231, 8232, 8233, 8234, 8235, 8236, 8237 };

	/** The xcatpro repository. */
	@Autowired
	private XcatproRepository xcatproRepository;
	
	/** The natgas repository. */
	@Autowired
	private NatgasRepository natgasRepository;
	
	/** The cuenta repository. */
	@Autowired
	CuentaRepository cuentaRepository;
	
	/** The fuente repository. */
	@Autowired
	FuentefRepository fuenteRepository;
	
	/** The account service. */
	@Autowired
	AccountService accountService;

	/*
	 * (non-Javadoc)
	 * 
	 * Clave de dependencia existe en xcatpro
	 * 
	 * @see com.gem.sistema.load.fileupload.service.InboundFileService#
	 * existDependency(java.lang.String)
	 */
	@Override
	public boolean existDependency(String clvdep) {
		return this.xcatproRepository.exists(XcatproPredicates.existClvDep(clvdep));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.service.InboundFileService#existNatGas(
	 * java.lang.String)
	 */
	@Override
	public boolean existNatGas(String natgas, Integer idSector) {
		return this.natgasRepository.exists(NatgasPredicates.existNatgas(natgas, idSector));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.load.fileupload.service.InboundFileService#
	 * validateFuenteFin(java.lang.String)
	 */
	@Override
	public boolean validateFuenteFin(String fuenteFin) {
		// TODO Auto-generated method stub
		return this.fuenteRepository.exists(FuentefPredicates.existLiga(fuenteFin));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.service.InboundFileService#existClvPro(
	 * java.lang.String)
	 */
	@Override
	public boolean existClvPro(String clvpro, Integer idSector) {
		return this.xcatproRepository
				.exists(XcatproPredicates.existClvPro(clvpro.substring(0, 12), clvpro.substring(12, 15),idSector));
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.load.fileupload.service.InboundFileService#validateAccounts(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public boolean validateAccounts(List accounts, String scta, String sscta, String ssscta, Long idsector) {

		Iterator i = accounts.iterator();
		Boolean result = Boolean.TRUE;

		while (i.hasNext()) {
			String account = i.next().toString();

			result = this.cuentaRepository
					.exists(CuentaPredicates.existsFourLevelsAccount(account, scta, sscta, ssscta, idsector ));

			if (result == Boolean.FALSE) {
				break;
			}

		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.load.fileupload.service.InboundFileService#validateContent(com.gem.sistema.load.fileupload.model.FileUpload, boolean, java.lang.Integer)
	 */
	@Override
	public boolean validateContent(FileUpload fileUpload, boolean processA, Integer idSector) throws Exception {
		// TODO Auto-generated method stub

		Integer lineCount = 1;
		Integer validCount = 0;
		Date initDate;
		Date endDate;
		File newFile = new File("/tmp/outEgre/".concat(fileUpload.getFile().getName()));
		File file = fileUpload.getFile();
		boolean resp = Boolean.TRUE;
		String out = "";

		try {
			FileUtils.write(newFile, HEADER + EOL, Charset.forName(CHARSET), Boolean.FALSE);

			LineIterator rowIterator = FileUtils.lineIterator(file, "UTF-8");
			String error = StringUtils.EMPTY;

			while (rowIterator.hasNext()) {
				initDate = Calendar.getInstance().getTime();
				String row = rowIterator.nextLine();
				if (lineCount >= this.lineToStart) {
					try {
						out = processLine(lineCount, row, processA, idSector);

						if (out == null)
							continue;
						else {
							FileUtils.write(newFile,  out + EOL, Charset.forName(CHARSET),
									Boolean.TRUE);
							resp = false;
						}

					} catch (Exception vle) {
						LOGGER.error(vle.getMessage(), vle);
						error = vle.getMessage();
					}

				}

				if (error.length() <= 0) {
					validCount++;
				} else {
					FileUtils.write(newFile, error + EOL, Charset.forName(CHARSET), Boolean.TRUE);
					resp = Boolean.FALSE;
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
			throw e;
		}

		return false;
	}

	/**
	 * Process line.
	 *
	 * @param lineCount the line count
	 * @param row the row
	 * @param processA the process A
	 * @param idSector the id sector
	 * @return the string
	 */
	private String processLine(Integer lineCount, String row, boolean processA, Integer idSector) {

		EgresResultBean result = new EgresResultBean();
		boolean bres = false;
		Cuenta cuenta = null;
		String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(row, SEPARATOR);
		result.setRow(lineCount);
		result.setDependencia(tokens[0]);
		result.setFinun(tokens[1]);
		result.setPartida(tokens[2]);
		result.setSuma(Double.valueOf(tokens[4]));
		result.setTotal(Double.valueOf(tokens[4]));
		result.setE3(BooleanUtils.toInteger(!this.existDependency(tokens[0])));
		result.setE4(BooleanUtils.toInteger(!this.existClvPro(tokens[1], idSector)));
		result.setE5(BooleanUtils.toInteger(!this.validateFuenteFin(tokens[1].substring(11, 14))));
		result.setE6(BooleanUtils.toInteger(!this.existNatGas(tokens[2], idSector)));
		result.setE7(BooleanUtils.toInteger(!this.existClvPro(tokens[1], idSector)));
		result.setE8(0);
		result.setSuma2(Double.valueOf(tokens[4]));
		result.setTotal2(Double.valueOf(tokens[4]));

		cuenta = this.accountService.getAccountbyChapter(this.getChapter(tokens[2]));

		this.accountService.validate(result, cuenta);

		bres = validaResult(result);
		if (bres) {
			result.setSt(this.OK);
			result.setSt2("0");
			result.setEg1(Double.parseDouble(tokens[5]));
			result.setEg2(Double.parseDouble(tokens[6]));
			result.setEg3(Double.parseDouble(tokens[7]));
			result.setEg4(Double.parseDouble(tokens[8]));
			result.setEg5(Double.parseDouble(tokens[9]));
			result.setEg6(Double.parseDouble(tokens[10]));
			result.setEg7(Double.parseDouble(tokens[11]));
			result.setEg8(Double.parseDouble(tokens[12]));
			result.setEg9(Double.parseDouble(tokens[13]));
			result.setEg10(Double.parseDouble(tokens[14]));
			result.setEg11(Double.parseDouble(tokens[15]));
			result.setEg12(Double.parseDouble(tokens[16]));

			this.results.add(result);
			return null;
		} else {
			result.setSt(this.NOK);
			result.setSt2("1");
			this.accountService.getAutogeneratedBudgetAccount(cuenta);

		}
		result.setE14(0);

		return result.toString(OUTPUT_SEPARATOR);

	}

	/**
	 * Valida result.
	 *
	 * @param result the result
	 * @return true, if successful
	 */
	private boolean validaResult(EgresResultBean result) {
		if (result.getE3() == 1 || result.getE4() == 1 || result.getE5() == 1 || result.getE6() == 1
				|| result.getE7() == 1 || result.getE8() == 1 || result.getE9() == 1 || result.getE10() == 1
				|| result.getE11() == 1 || result.getE12() == 1 || result.getE13() == 1 || result.getE14() == 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets the chapter.
	 *
	 * @param partida the partida
	 * @return the chapter
	 */
	private int getChapter(String partida) {

		int chapter = 0;

		if (partida != null) {
			chapter = Integer.parseInt(partida.substring(0, 1));
		}

		return chapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.service.InboundFileService#getResults()
	 */
	@Override
	public List<EgresResultBean> getResults() {
		// TODO Auto-generated method stub
		return this.results;
	}

}
