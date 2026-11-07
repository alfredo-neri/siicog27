package com.gem.sistema.load.fileupload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catsector;
import com.gem.sistema.business.domain.Obra;
import com.gem.sistema.business.domain.Paso;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.ObrasCargaAutomaticaDTO;
import com.gem.sistema.business.repository.catalogs.ObrasRepository;
import com.gem.sistema.business.repository.catalogs.PasoRepositoryObras;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.web.bean.AbstractMB;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadCSVServiceImpl.
 *
 * @author DOOM
 */
@Service(value = "csvService")
public class ReadCSVServiceImpl extends AbstractMB implements ReadCSVService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReadCSVServiceImpl.class);

	/** The xcatpro repository. */
	@Autowired
	@Qualifier("xcatproRepository")
	private XcatproRepository xcatproRepository;

	/** The paso repository. */
	@Autowired
	private PasoRepositoryObras pasoRepository;

	/** The obras repository. */
	@Autowired
	private ObrasRepository obrasRepository;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.load.fileupload.ReadCSVService#processCsvFile(java.io.
	 * InputStream, java.lang.StringBuilder)
	 */
	public ObrasCargaAutomaticaDTO processCsvFile(InputStream input, StringBuilder errorMsg) {
		ObrasCargaAutomaticaDTO obrasCargaAutomaticaDTO = new ObrasCargaAutomaticaDTO();
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT;
		InputStreamReader in = null;

		String suffix = getSuffix();
		File dirFile = new File(System.getProperty("java.io.tmpdir").concat("/inObras/"));
		dirFile.mkdirs();
		File dirErrorFile = new File(System.getProperty("java.io.tmpdir").concat("/outObras/"));
		dirErrorFile.mkdirs();
		String fileName = "/obras_".concat(suffix).concat(".csv");
		String fileNameErrors = "/obras_".concat(suffix.concat("_resp.txt"));
		LOGGER.info("Archivo Carga Automatica Obras a procesar: " + dirFile.getAbsolutePath().concat(fileName));

		writeFileToServer(input, dirFile.getAbsolutePath().concat(fileName));

		int ren;
		int e1;
		int e2;
		int e3;
		int e4;
		int e5;

		try {

			in = new InputStreamReader(new FileInputStream(dirFile.getAbsolutePath().concat(fileName)));
			csvFileParser = new CSVParser(in, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			writeToErrors("       Ren E1 E2 E3 E4 E5     ST\n---------- -- -- -- -- -- ------",
					dirErrorFile.getAbsolutePath().concat(fileNameErrors));

			Boolean errorGeneral = Boolean.FALSE;
			for (int i = 0; i < csvRecords.size(); i++) {
				CSVRecord rec = csvRecords.get(i);
				Obra o = new Obra();
				// Finalidad
				o.setFn(rec.get(0));
				// Funcion
				o.setFun(rec.get(1));
				// Subfunción
				o.setSubfun(rec.get(2));
				// Programa
				o.setProg(rec.get(3));
				// Subprograma
				o.setSubprog(rec.get(4));
				// Proyecto
				o.setProy(rec.get(5));
				// Fuente de Financiamiento
				o.setFfin(rec.get(6));
				// Numero de Control de la Obra
				o.setNcontrol(Double.valueOf(rec.get(7)).intValue());
				// Nombre de la obra
				o.setNomobra(rec.get(8).trim());
				// Tipo de Ejecucion
				o.setTipoejec(rec.get(9).trim());
				// Ubicacion
				o.setUbicacion(rec.get(10).trim());
				// Justificacion
				o.setJustificacion(rec.get(11).trim());
				// Número de habitantes beneficiados
				o.setPoblacion(Double.valueOf(rec.get(12)).intValue());
				// Tipo asignacion
				o.setTipoasig(rec.get(13).trim());
				// Presupuesto de enero

				o.setAuto1(BigDecimal.valueOf(Double.valueOf(rec.get(14))));
				// Presupuesto de febrero
				o.setAuto2(BigDecimal.valueOf(Double.valueOf(rec.get(15))));
				// Presupuesto de marzo
				o.setAuto3(BigDecimal.valueOf(Double.valueOf(rec.get(16))));
				// Presupuesto de abril
				o.setAuto4(BigDecimal.valueOf(Double.valueOf(rec.get(17))));
				// Presupuesto de mayo
				o.setAuto5(BigDecimal.valueOf(Double.valueOf(rec.get(18))));
				// Presupuesto de junio
				o.setAuto6(BigDecimal.valueOf(Double.valueOf(rec.get(19))));
				// Presupuesto de julio
				o.setAuto7(BigDecimal.valueOf(Double.valueOf(rec.get(20))));
				// Presupuesto de agosto
				o.setAuto8(BigDecimal.valueOf(Double.valueOf(rec.get(21))));
				// Presupuesto de septiembre
				o.setAuto9(BigDecimal.valueOf(Double.valueOf(rec.get(22))));
				// Presupuesto de octubre
				o.setAuto10(BigDecimal.valueOf(Double.valueOf(rec.get(23))));
				// Presupuesto de noviembre
				o.setAuto11(BigDecimal.valueOf(Double.valueOf(rec.get(24))));
				// Presupuesto de diciembre
				o.setAuto12(BigDecimal.valueOf(Double.valueOf(rec.get(25))));
				// Presupuesto TOTAL
				o.setAuto13(BigDecimal.valueOf(Double.valueOf(rec.get(26))));

				Catsector cat = new Catsector();
				cat.setIdsector(this.getUserDetails().getIdSector());
				cat.setUserid(this.getUserDetails().getUsername());
				o.setCatsector(cat);

				ren = i + 1;
				System.out.println("Procesando Linea :::::::::::::::::::::::::: " + ren);
				e1 = validaPrograma(o);
				e2 = validaPresupuesto(o);
				e3 = validaTipoEjecucion(o.getTipoejec());
				e4 = validaTipoAsignacion(o.getTipoasig());
				e5 = validaTotalObra(o);

				// Si todas las validaciones son correctas se inserta en OBRAS
				if (e1 == 0 && e2 == 0 && e3 == 0 && e4 == 0 && e5 == 0) {
					o.setCapturo(SecurityContextHolder.getContext().getAuthentication().getName());
					o.setFeccap(new Date());

					if (obrasRepository.countByCatsector_IdsectorAndFnAndFunAndSubfunAndProgAndSubprogAndProyAndFfinAndNcontrol(
							o.getCatsector().getIdsector(), o.getFn(), o.getFun(), o.getSubfun(), o.getProg(),
							o.getSubprog(), o.getProy(), o.getFfin(), o.getNcontrol()) < 1) {
						obrasRepository.save(o);
					} else {
						writeToErrors(
								"Es posible que el registro del archivo de carga automatica de Obras ya haya sido procesado anteriormente, NCONTROL: "
										+ o.getNcontrol(),
								dirErrorFile.getAbsolutePath().concat(fileNameErrors));
						errorMsg.append("1" + o.getNcontrol());
						System.out.println("Linea No procesada :::::::::::::::::::::::::: " + ren);
					}

//					try {
//						obrasRepository.save(o);
//					} catch (org.springframework.dao.DataIntegrityViolationException ex) {
//						ex.printStackTrace();
//						LOGGER.error(
//								"Es posible que el registro del archivo de carga automatica de Obras ya haya sido procesado anteriormente, NCONTROL: "
//										+ o.getNcontrol());
//						writeToErrors(
//								"Es posible que el registro del archivo de carga automatica de Obras ya haya sido procesado anteriormente, NCONTROL: "
//										+ o.getNcontrol(),
//								dirErrorFile.getAbsolutePath().concat(fileNameErrors));
//						errorMsg.append("1"	+ o.getNcontrol());
//						System.out.println("Linea No procesada :::::::::::::::::::::::::: " + ren);
//
//					}
				} else {
					errorGeneral = Boolean.TRUE;
				}

				writeToErrors(formatError(ren, e1, e2, e3, e4, e5),
						dirErrorFile.getAbsolutePath().concat(fileNameErrors));
			}

			if (errorGeneral) {
				errorMsg.append("La información contenida en el archivo presenta errores");
			}

		} catch (Exception e) {
			LOGGER.error("Error al procesar el archivo de carga automatica de Obras: "
					+ dirFile.getAbsolutePath().concat(fileName));
			errorMsg.append("El Formato del archivo no es válido");
			e.printStackTrace();
		} finally {
			try {
				in.close();
				csvFileParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		obrasCargaAutomaticaDTO.setFileName(dirErrorFile.getAbsolutePath().concat(fileNameErrors));
		obrasCargaAutomaticaDTO.setErrorMsg(errorMsg.toString());
		return obrasCargaAutomaticaDTO;
	}

	/**
	 * Gets the suffix.
	 *
	 * @return the suffix
	 */
	private String getSuffix() {

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String pattern = "%04d%02d%02d%02d%02d%02d";

		return String.format(pattern, year, month, day, hour, minute, second);
	}

	/**
	 * Write file to server.
	 *
	 * @param input    the input
	 * @param fileName the file name
	 */
	private void writeFileToServer(InputStream input, String fileName) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = input;
			out = new FileOutputStream(new File(fileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			LOGGER.info("Archivo guardado en servidor: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * Write to errors.
	 *
	 * @param content        the content
	 * @param fileNameErrors the file name errors
	 */
	private void writeToErrors(String content, String fileNameErrors) {
		try {

			File file = new File(fileNameErrors);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\r\n".concat(content));
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Format error.
	 *
	 * @param ren the ren
	 * @param e1  the e 1
	 * @param e2  the e 2
	 * @param e3  the e 3
	 * @param e4  the e 4
	 * @param e5  the e 5
	 * @return the string
	 */
	private String formatError(int ren, int e1, int e2, int e3, int e4, int e5) {

		String pattern2 = "%10d % d % d % d % d % d %6s";

		if (e1 == 1 || e2 == 1 || e3 == 1 || e4 == 1 || e5 == 1) {
			return String.format(pattern2, ren, e1, e2, e3, e4, e5, "NO");
		} else
			return String.format(pattern2, ren, e1, e2, e3, e4, e5, "OK");
	}

	// E1 |Valida la existencia de: concatenar de obra
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.ReadCSVService#validaPrograma(com.gem.sistema
	 * .business.domain.Obra)
	 */
	// (FN+FUN+SUBFUN+PROG+SUBPROG+PROY+FFIN) = xcatpro.clvfun
	public int validaPrograma(Obra obra) {
		int returnValue = 1;
		String clvFun = obra.getFn().concat(obra.getFun()).concat(obra.getSubfun()).concat(obra.getProg())
				.concat(obra.getSubprog()).concat(obra.getProy());
		String fFin = obra.getFfin();
		LOGGER.debug("Buscando en XCATPRO: " + clvFun);
//		List<Xcatpro> xcatpros = xcatproRepository.findByClvfun(clvFun);

		if (xcatproRepository.countBySectoridAndClvfunAndClvfin(obra.getCatsector().getIdsector(), clvFun, fFin) > 0) {
//			String xcatproClvfun = xcatpros.get(0).getClvfun();
//			String clvFin = xcatpros.get(0).getClvfin();
//			if (clvFun.equals(xcatproClvfun) && fFin.equals(clvFin)) {
			returnValue = 0;

		}
		return returnValue;
	}

	// E2 Valida la existencia de (FN+FUN+SUBFUN+PROG+SUBPROG+PROY+FFIN en
	// PASO.PROGRAMA)
	// Hace la validación que exista el programa
	// capturado en la tabla paso.programa, y que en paso.partida existan claves
	// que tengan 6xxx con presupuesto autorizado es decir que paso.auto1[mes]
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.ReadCSVService#validaPresupuesto(com.gem.
	 * sistema.business.domain.Obra)
	 */
	// <> 0, donde mes tiene el valor de 1 a 12.
	public int validaPresupuesto(Obra obra) {
		int returnValue = 1;
		String prog = obra.getFn().concat(obra.getFun()).concat(obra.getSubfun()).concat(obra.getProg())
				.concat(obra.getSubprog()).concat(obra.getProy().concat(obra.getFfin()));
		LOGGER.info("Buscando en PASO: " + prog);

		List<Paso> programas = pasoRepository
				.findByCatsector_IdsectorAndProgramaAndPartida(obra.getCatsector().getIdsector(), prog, "6000");

//		List<Paso> programas = pasoRepository.findAllByPrograma(prog);

		// Se verifica si el programa existe en PASO.PROGRAMA y
		if (!programas.isEmpty()) {

			for (Paso paso : programas) {
				if (validaPasoVsObra(paso, obra)) {
					returnValue = 0;
					break;
				}
			}

			// Paso paso = validaPartida6xxx(programas);

//			if (paso != null && validaPasoMayorCero(paso) && validaPasoVsObra(paso, obra)) {

		}
		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.load.fileupload.ReadCSVService#validaPresupuestoManual(com.
	 * gem.sistema.business.domain.Obra)
	 */
	public String validaPresupuestoManual(Obra obra) {
		String returnValue = "OK";
		String prog = obra.getFn().concat(obra.getFun()).concat(obra.getSubfun()).concat(obra.getProg())
				.concat(obra.getSubprog()).concat(obra.getProy().concat(obra.getFfin()));
		LOGGER.info("Buscando en PASO: " + prog);
		List<Paso> programas = pasoRepository.findAllByPrograma(prog);

		// Se verifica si el programa existe en PASO.PROGRAMA y
		if (!programas.isEmpty()) {
			Paso paso = validaPartida6xxx(programas);

			if (paso == null) {
				returnValue = "Partida 6xxx no encontrada";
			} else if (!validaPasoVsObra(paso, obra)) {
				returnValue = "No hay suficiente presupuesto";
			} /*
				 * else if (!validaPasoMayorCero(paso)) { returnValue =
				 * "Los valores deben ser mayores a cero"; }
				 */
		} else {
			returnValue = "Programa no encontrado";
		}
		return returnValue;
	}

	/**
	 * Valida partida 6 xxx.
	 *
	 * @param programas the programas
	 * @return the paso
	 */
	private Paso validaPartida6xxx(List<Paso> programas) {
		Paso paso = null;
		for (Paso p : programas) {
			if (p.getPartida().startsWith("6")) {
				paso = p;
				LOGGER.debug(">>>>>>>>>>Programa Encontrado, inicia con 6: " + "PARTIDA: " + paso.getPartida()
						+ " CLAVE:" + paso.getClave() + " PROGRAMA: " + paso.getPrograma());
				break;
			}
		}

		return paso;
	}

	/**
	 * Valida paso mayor cero.
	 *
	 * @param paso the paso
	 * @return true, if successful
	 */
	private boolean validaPasoMayorCero(Paso paso) {
		if ((paso.getAuto11().intValue() > 0) && (paso.getAuto12().intValue() > 0) && (paso.getAuto13().intValue() > 0)
				&& (paso.getAuto14().intValue() > 0) && (paso.getAuto15().intValue() > 0)
				&& (paso.getAuto16().intValue() > 0) && (paso.getAuto17().intValue() > 0)
				&& (paso.getAuto18().intValue() > 0) && (paso.getAuto19().intValue() > 0)
				&& (paso.getAuto110().intValue() > 0) && (paso.getAuto111().intValue() > 0)
				&& (paso.getAuto112().intValue() > 0)) {
			return true;
		} else
			LOGGER.debug(">>>>>>> validaPasoMayorCero:: Alguno de los montos en Paso son 0");
		return false;
	}

	/**
	 * Valida paso vs obra.
	 *
	 * @param paso the paso
	 * @param obra the obra
	 * @return true, if successful
	 */
	private boolean validaPasoVsObra(Paso paso, Obra obra) {
		if (BigDecimal.ZERO.compareTo(paso.getAuto113()) < 0) {
			LOGGER.debug(">>>>>>> validaPasoVsObra:: Presupuesto valido");
			return true;
		} else
			LOGGER.debug(">>>>>>> validaPasoVsObra:: No coinciden montos entre Paso y Obra");
		return false;
	}

	/**
	 * Valida tipo ejecucion.
	 *
	 * @param tipoEjecucion the tipo ejecucion
	 * @return the int
	 */
	// E3 Los datos en el campo Tipo de Ejecución son incorrectos
	private int validaTipoEjecucion(String tipoEjecucion) {
		// CONTRATO, por ADMINISTRACIÓN o será MIXTA

		if (tipoEjecucion.trim().equals("CONTRATO") || tipoEjecucion.trim().equals("ADMINISTRACION")
				|| tipoEjecucion.trim().equals("MIXTA")) {
			return 0;
		} else
			return 1;
	}

	/**
	 * Valida tipo asignacion.
	 *
	 * @param tipoAsignacion the tipo asignacion
	 * @return the int
	 */
	// E4 Los datos en el campo Tipo de Asignación son incorrectos
	private int validaTipoAsignacion(String tipoAsignacion) {
		// LP(Licitación Pública), IR(Invitación Restringida) o AD(Adjudicación
		// Directa)
		if (tipoAsignacion.equals("LP") || tipoAsignacion.equals("IR") || tipoAsignacion.equals("AD")) {
			return 0;
		} else
			return 1;
	}

	// E5 Valida si el total es diferente de la suma de los meses
	/**
	 * Valida total obra.
	 *
	 * @param obra the obra
	 * @return the int
	 */
	// Corregir el total o algún valor de los meses
	private int validaTotalObra(Obra obra) {
		BigDecimal sumaMeses = obra.getAuto1().add(obra.getAuto2()).add(obra.getAuto3()).add(obra.getAuto4())
				.add(obra.getAuto5()).add(obra.getAuto6()).add(obra.getAuto7()).add(obra.getAuto8())
				.add(obra.getAuto9()).add(obra.getAuto10()).add(obra.getAuto11()).add(obra.getAuto12());

		if (sumaMeses.compareTo(obra.getAuto13()) == 0) {
			return 0;
		} else
			return 1;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the obras repository.
	 *
	 * @return the obras repository
	 */
	public ObrasRepository getObrasRepository() {
		return obrasRepository;
	}

	/**
	 * Sets the obras repository.
	 *
	 * @param obrasRepository the new obras repository
	 */
	public void setObrasRepository(ObrasRepository obrasRepository) {
		this.obrasRepository = obrasRepository;
	}

	/**
	 * Gets the paso repository.
	 *
	 * @return the paso repository
	 */
	public PasoRepositoryObras getPasoRepository() {
		return pasoRepository;
	}

	/**
	 * Sets the paso repository.
	 *
	 * @param pasoRepository the new paso repository
	 */
	public void setPasoRepository(PasoRepositoryObras pasoRepository) {
		this.pasoRepository = pasoRepository;
	}

}
