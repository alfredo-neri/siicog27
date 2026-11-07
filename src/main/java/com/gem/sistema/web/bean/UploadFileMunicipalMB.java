package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static java.util.stream.Collectors.toList;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.gem.sistema.business.domain.Tsueldo;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.TsueldoRespository;
import com.gem.sistema.business.service.catalogos.TabuladorSueldosService;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class UploadFileMunicipalMB.
 */
@ManagedBean(name = "uploadFileMunicipalMB")
@ViewScoped
public class UploadFileMunicipalMB extends AbstractMB {
	/** The Constant charSetISO. */
	private final static String ISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String UTF_8 = "UTF-8";
	/** The parametros repository. */
	@ManagedProperty("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/** The tsueldo respository. */
	@ManagedProperty(value = "#{tsueldoRespository}")
	private TsueldoRespository tsueldoRespository;

	/** The tabulador. */
	@ManagedProperty("#{tabuladorSueldosServiceImpl}")
	private TabuladorSueldosService tabulador;

	/** The egresos csv validator. */
	@ManagedProperty("#{informacionMunicipalCsvValidator}")
	private FileContentValidator egresosCsvValidator;

	/** The validate. */
	private boolean validate;

	/** The size. */
	private boolean size;

	/** The b valida csv. */
	private static Boolean bValidaCsv = Boolean.FALSE;

	/** The lista from CSV. */
	private List<Tsueldo> listaFromCSV;

	/** The log. */
	private static String log;

	/** The archivo. */
	private UploadedFile archivo;

	/**
	 * Checks if is size.
	 *
	 * @return true, if is size
	 */
	public boolean isSize() {
		return size;
	}

	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * Sets the archivo.
	 *
	 * @param archivo
	 *            the new archivo
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * Sets the size.
	 *
	 * @param size
	 *            the new size
	 */
	public void setSize(boolean size) {
		this.size = size;
	}

	/**
	 * Checks if is validate.
	 *
	 * @return true, if is validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * Gets the lista from CSV.
	 *
	 * @return the lista from CSV
	 */
	public List<Tsueldo> getListaFromCSV() {
		return listaFromCSV;
	}

	/**
	 * Sets the lista from CSV.
	 *
	 * @param listaFromCSV
	 *            the new lista from CSV
	 */
	public void setListaFromCSV(List<Tsueldo> listaFromCSV) {
		this.listaFromCSV = listaFromCSV;
	}

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public static String getLog() {
		return log;
	}

	/**
	 * Sets the log.
	 *
	 * @param log
	 *            the new log
	 */
	public static void setLog(String log) {
		UploadFileMunicipalMB.log = log;
	}

	/**
	 * Gets the map to object.
	 *
	 * @return the map to object
	 */
	public static Function<String, Tsueldo> getMapToObject() {
		return mapToObject;
	}

	/**
	 * Sets the map to object.
	 *
	 * @param mapToObject
	 *            the map to object
	 */
	public static void setMapToObject(Function<String, Tsueldo> mapToObject) {
		UploadFileMunicipalMB.mapToObject = mapToObject;
	}

	/**
	 * Sets the validate.
	 *
	 * @param validate
	 *            the new validate
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * Gets the parametros repository.
	 *
	 * @return the parametros repository
	 */
	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	/**
	 * Sets the parametros repository.
	 *
	 * @param parametrosRepository
	 *            the new parametros repository
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	/**
	 * Gets the tsueldo respository.
	 *
	 * @return the tsueldo respository
	 */
	public TsueldoRespository getTsueldoRespository() {
		return tsueldoRespository;
	}

	/**
	 * Sets the tsueldo respository.
	 *
	 * @param tsueldoRespository
	 *            the new tsueldo respository
	 */
	public void setTsueldoRespository(TsueldoRespository tsueldoRespository) {
		this.tsueldoRespository = tsueldoRespository;
	}

	/**
	 * Gets the tabulador.
	 *
	 * @return the tabulador
	 */
	public TabuladorSueldosService getTabulador() {
		return tabulador;
	}

	/**
	 * Sets the tabulador.
	 *
	 * @param tabulador
	 *            the new tabulador
	 */
	public void setTabulador(TabuladorSueldosService tabulador) {
		this.tabulador = tabulador;
	}

	/**
	 * Gets the egresos csv validator.
	 *
	 * @return the egresos csv validator
	 */
	public FileContentValidator getEgresosCsvValidator() {
		return egresosCsvValidator;
	}

	/**
	 * Sets the egresos csv validator.
	 *
	 * @param egresosCsvValidator
	 *            the new egresos csv validator
	 */
	public void setEgresosCsvValidator(FileContentValidator egresosCsvValidator) {
		this.egresosCsvValidator = egresosCsvValidator;
	}

	/**
	 * Handle file upload.
	 *
	 * @param event
	 *            the event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		File source = null;
		File target = null;
		String fileName1 = "";
		String fileName2 = "";
		String pathFiles = "";
		try {
			inputFile(event.getFile().getFileName(), event.getFile().getInputstream());
			final FileUpload fileUpload = new FileUpload();
			this.archivo = event.getFile();
			File newFile = new File(getPath("CARG_ARCHIVO") + File.separator + event.getFile().getFileName());
			fileUpload.setFile(newFile);
			size = siZeFile(newFile);
			File newFile2 = null;
			BufferedInputStream bis;
			if (this.size) {
				try {

					pathFiles = getPath("CARG_ARCHIVO");
					fileName1 = event.getFile().getFileName();
					fileName2 = UUID.randomUUID()+ "-"+ event.getFile().getFileName();
					source  = new File(pathFiles+"/"+fileName1);
					target  = new File(pathFiles+"/"+fileName2);
					inputFile(fileName1, event.getFile().getInputstream());
					inputFile(fileName2,  event.getFile().getInputstream());
					ConvertCharsetUtils.transform(source, ISO, target, UTF_8);
					newFile2 = new File(getPath("CARG_ARCHIVO") + File.separator + fileName2);
					InputStream  input = new FileInputStream(newFile2);
					bis = new BufferedInputStream(input);
					listaFromCSV = csvReadingAndParse(bis);
					

				} catch (IOException e) {
					e.printStackTrace();
				}
				if (this.bValidaCsv == Boolean.TRUE) {
					Tsueldo tsuldo = tabulador.execuet(newFile2.getName() , getPath("CARG_ARCHIVO"),
							this.getUserDetails().getUsername(), this.getUserDetails().getIdSector());
					if (tsuldo.getoCodError() > 0) {
						generateNotificationFront(SEVERITY_INFO, "", tsuldo.getoMsgError());
					} else {
						generateNotificationFront(SEVERITY_INFO, "", tsuldo.getoMsgError());
					}
				} else {
					generateNotificationFront(SEVERITY_INFO, "",
							parametrosRepository.getValorByCv("TABULADOR_MSG_ERROR"));
				}
			} else {
				generateNotificationFront(SEVERITY_INFO, "", parametrosRepository.getValorByCv("FILE_SIZE"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	/**
	 * Gets the path.
	 *
	 * @param cvePath
	 *            the cve path
	 * @return the path
	 */
	public String getPath(String cvePath) {
		return parametrosRepository.getValorByCv(cvePath);
	}

	/**
	 * Input file.
	 *
	 * @param filename
	 *            the filename
	 * @param input
	 *            the input
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void inputFile(String filename, InputStream input) throws IOException {
		OutputStream output = new FileOutputStream(new File(getPath("CARG_ARCHIVO"), filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	/**
	 * Borrar.
	 */
	public void borrar() {

		try {
			tsueldoRespository.truncate();

			generateNotificationFront(SEVERITY_INFO, "", "Se han eliminado todos lo registros satisfactoriamente");

		} catch (Exception e) {
			generateNotificationFront(SEVERITY_INFO, "Error", e.getMessage());
		}

	}

	/**
	 * Si ze file.
	 *
	 * @param file
	 *            the file
	 * @return true, if successful
	 */
	public boolean siZeFile(File file) {
		if (file.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Csv reading and parse.
	 *
	 * @param is
	 *            the is
	 * @return the list
	 */
	public List<Tsueldo> csvReadingAndParse(InputStream is) {
		List<Tsueldo> retorno = new ArrayList<Tsueldo>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
			retorno = br.lines()/* .skip(1) */.map(mapToObject).filter(p -> {
				p.setSectorId(getUserDetails().getIdSector());
				p.setCapturo(getUserDetails().getUsername());
				p.setFeccap(new Date());
				p.setIdRef(getUserDetails().getIdSector() - 1l);
				p.setIdUser(getUserDetails().getUsername());
				return Objects.nonNull(p.getCvepuesto()) && Objects.nonNull(p.getNompuesto());
			}).collect(toList());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/** The map to object. */
	private static Function<String, Tsueldo> mapToObject = (line) -> {
		Tsueldo retorno = new Tsueldo();
		String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		if (p.length == 15) {
			retorno.setCvepuesto(toString(p[0]));
			retorno.setNivel(toString(p[1]));
			retorno.setNompuesto(toString(p[2]));
			retorno.setNconfianza(toInteger(p[3]));
			retorno.setNsindicalizados(toInteger(p[4]));
			retorno.setNeventual(toInteger(p[5]));
			retorno.setDietas(toDouble(p[6]));
			retorno.setSbase(toDouble(p[7]));
			retorno.setSbaseeven(toDouble(p[8]));
			retorno.setCompensacion(toDouble(p[9]));
			retorno.setGratificacion(toDouble(p[10]));
			retorno.setOpercepciones(toDouble(p[11]));
			retorno.setAguinaldo(toDouble(p[12]));
			retorno.setAguinaldoeven(toDouble(p[13]));
			retorno.setPv(toDouble(p[14]));
			bValidaCsv = Boolean.TRUE;
		} else {
			bValidaCsv = Boolean.FALSE;
		}
		// Si son nulls asigna valores default
		retorno = checkNullsAndSetDefaultValues(retorno);

		return retorno;
	};

	/**
	 * To string.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	private static String toString(String value) {
		String retorno = "";
		try {
			if (Objects.nonNull(value)) {
				retorno = value.trim().replaceAll("\"", "");
			}
		} catch (NumberFormatException e) {
			log += " Campo entero: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * To integer.
	 *
	 * @param value
	 *            the value
	 * @return the integer
	 */
	private static Integer toInteger(String value) {
		Integer retorno = 0;
		try {
			if (Objects.nonNull(value)) {
				retorno = Integer.parseInt(value.replaceAll(" ", ""));
			}
		} catch (NumberFormatException e) {
			log += " Campo entero: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * To double.
	 *
	 * @param value
	 *            the value
	 * @return the double
	 */
	private static Double toDouble(String value) {
		Double retorno = Double.valueOf(0);
		try {
			if (Objects.nonNull(value)) {
				retorno = new Double(value.replaceAll(" ", ""));
			}
		} catch (Exception e) {
			log += " Campo double: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * Check nulls and set default values.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the t
	 */
	/*
	 * Si son nulls asigna valores default
	 */
	private static <T> T checkNullsAndSetDefaultValues(T object) {
		try {
			Class<?> clazz = object.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				boolean isGetterMhetodType = method.getName().startsWith("get") || method.getName().startsWith("is");
				if (isGetterMhetodType) {
					Object fieldValue = method.invoke(object);
					if (Objects.isNull(fieldValue)) {
						String setMethodName = method.getName().replace("get", "");
						setMethodName = setMethodName.substring(0, 1).toLowerCase()
								+ setMethodName.substring(1, setMethodName.length());
						Field field = clazz.getDeclaredField(setMethodName);
						field.setAccessible(true);
						if (method.getReturnType().equals(Integer.class)) {
							field.set(object, 0);
						} else if (method.getReturnType().equals(String.class)) {
							field.set(object, "");
						} else if (method.getReturnType().equals(BigDecimal.class)) {
							field.set(object, BigDecimal.ZERO);
						} else if (method.getReturnType().equals(Long.class)) {
							field.set(object, 0l);
						} else if (method.getReturnType().equals(Date.class)) {
							field.set(object, new Date());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * Gets the b valida csv.
	 *
	 * @return the b valida csv
	 */
	public static Boolean getbValidaCsv() {
		return bValidaCsv;
	}

	/**
	 * Sets the b valida csv.
	 *
	 * @param bValidaCsv
	 *            the new b valida csv
	 */
	public static void setbValidaCsv(Boolean bValidaCsv) {
		UploadFileMunicipalMB.bValidaCsv = bValidaCsv;
	}

}
