package com.gem.sistema.util;

import static com.gem.sistema.util.Constants.BLANK;
import static com.gem.sistema.util.Constants.LIKE;
import static com.gem.sistema.util.Constants.PREFIX_GET;
import static com.gem.sistema.util.Constants.QUALIFIER_LIKE;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilJPACuentas.isEmptyValueMethodGet;
import static com.gem.sistema.util.UtilJPACuentas.obtainMehodName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gem.sistema.annotation.ColumnFile;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilReportCuentas.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Component(value = "utilReportCuentas")
public class UtilReportCuentas {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilReportCuentas.class);

	/** The Constant SDF. */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/YYYY");

	/**
	 * Salto de linea.
	 */
	private static final String NEW_LINE = "\r\n";

	/**
	 * Extension de un archivo Excel.
	 */
	private static final String XLS_EXTENSION = ".xls";

	/**
	 * Extension de un archivo PDF.
	 */
	private static final String PDF_EXTENSION = ".pdf";

	/**
	 * Tipo de contenido web pdf.
	 */
	private static final String CONTENT_TYPE_PDF = "application/pdf";

	/**
	 * Tipo de contenido web excel.
	 */
	private static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";

	/**
	 * Clausula WHERE de SQL.
	 */
	private static final String WHERE_CLAUSE = "WHERE";

	/**
	 * Clausula AND de SQL.
	 */
	private static final String AND_CLAUSE = " AND ";

	/**
	 * Operador = de SQL.
	 */
	private static final String OPERATOR_EQUAL = "=";

	/**
	 * Operador = de SQL.
	 */
	private static final String SINGLE_QUOTE = "'";

	/**
	 * Operador = de SQL.
	 */
	private static final String DEFAULT_CLAUSE = " 1=1 ";

	/**
	 * " ".
	 */
	private static final String SPACE = " ";

	/**
	 * Constante para indicar que una cadena no es parte de otra cadena.
	 */
	private static final int NOT_INDEX_OF = -1;
	
	/**
	 * nomcta
	 */
	private static final String TITULO = "nomcta";

	/**
	 * Fuente de datos BD local.
	 */
	private DataSource dataSource;

	/**
	 * Llave del primer encabezado de los reportes en jasper.
	 */
	@Value("${view.report.general.header.one}")
	private String HEADER_ONE;

	/**
	 * Llave del segundo encabezado de los reportes en jasper.
	 */
	@Value("${view.report.general.header.two}")
	private String HEADER_TWO;

	/**
	 * Llave del tercer encabezado de los reportes en jasper.
	 */
	@Value("${view.report.general.header.three}")
	private String HEADER_THREE;

	/**
	 * Llave del cuarto encabezado de los reportes en jasper.
	 */
	@Value("${view.report.general.header.four}")
	private String HEADER_FOUR;

	/**
	 * Primer encabezado general de los reportes en jasper.
	 */
	@Value("${view.report.general.header.gob}")
	private String HEADER_GENERAL_GOB;

	/**
	 * Segundo encabezado general de los reportes en jasper.
	 */
	@Value("${view.report.general.header.secr}")
	private String HEADER_GENERAL_SECR;

	/**
	 * Tercer encabezado general de los reportes en jasper.
	 */
	@Value("${view.report.general.header.subs}")
	private String HEADER_GENERAL_SUBS;

	/**
	 * Cuarto encabezado general de los reportes en jasper.
	 */
	@Value("${view.report.general.header.cont}")
	private String HEADER_GENERAL_CONT;

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Put general parameters report.
	 *
	 * @param parameters the parameters
	 */
	public void putGeneralParametersReport(final Map<String, Object> parameters) {
		LOGGER.info(":: Agregando parametros generales ");
		parameters.put(HEADER_ONE, HEADER_GENERAL_GOB);
		parameters.put(HEADER_TWO, HEADER_GENERAL_SECR);
		parameters.put(HEADER_THREE, HEADER_GENERAL_SUBS);
		parameters.put(HEADER_FOUR, HEADER_GENERAL_CONT);
	}

	/**
	 * Generate content report pdf.
	 *
	 * @param parameters the parameters
	 * @param pathJasper the path jasper
	 * @param reportName the report name
	 * @return the streamed content
	 */
	public StreamedContent generateContentReportPdf(final Map<String, Object> parameters, final String pathJasper,
			final String reportName) {
		StreamedContent result = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			final FacesContext context = FacesContext.getCurrentInstance();
			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			final String pathReport = servletContext.getRealPath(pathJasper);
			final JasperPrint print = JasperFillManager.fillReport(pathReport, parameters, connection);
			final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, arrayOutputStream);
			final InputStream stream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
			result = new DefaultStreamedContent(stream, CONTENT_TYPE_PDF, reportName.concat(PDF_EXTENSION));
			LOGGER.info(":: Termina Exportar PDF ");
		} catch (final Exception genericExc) {
			LOGGER.error(":: Error al generar reporte de Excel ", genericExc);
		} finally {
			try {
				connection.close();
			} catch (final SQLException e) {
				LOGGER.error("", e);
			}
		}
		return result;
	}

	/**
	 * Generate content repor xls.
	 *
	 * @param parameters the parameters
	 * @param listRecords the list records
	 * @param pathJasper the path jasper
	 * @param reportName the report name
	 * @return the streamed content
	 */
	public StreamedContent generateContentReporXls(final Map<String, Object> parameters, final List<?> listRecords,
			final String pathJasper, final String reportName) {
		LOGGER.info(":: Generar reporte de Excel con Japser " + pathJasper);
		StreamedContent result = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			final FacesContext context = FacesContext.getCurrentInstance();
			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			final String pathReport = servletContext.getRealPath(pathJasper);
			final JasperPrint print = JasperFillManager.fillReport(pathReport, parameters, connection);
			final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			final JRXlsExporter exporter = new JRXlsExporter();	
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(arrayOutputStream));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setDetectCellType(true);// Set configuration as
													// you like it!!
			configuration.setCollapseRowSpan(false);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
			final InputStream stream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
			result = new DefaultStreamedContent(stream, CONTENT_TYPE_XLS, reportName.concat(XLS_EXTENSION));
			LOGGER.info(":: Finaliza exportar Excel {} ", result);
		} catch (final Exception genericExc) {
			LOGGER.error(":: Error al generar reporte de Excel ", genericExc);
		} finally {
			try {
				connection.close();
			} catch (final SQLException e) {
				LOGGER.error("", e);
			}
		}
		return result;
	}

	/**
	 * Generate content report plain text.
	 *
	 * @param list the list
	 * @param header the header
	 * @param typeContent the type content
	 * @param reportName the report name
	 * @param extension the extension
	 * @param separatorCols the separator cols
	 * @return the streamed content
	 */
	public StreamedContent generateContentReportPlainText(final List<?> list, final String header,
			final String typeContent, final String reportName, final String extension, final String separatorCols) {
		final StringBuilder text = new StringBuilder();
		text.append(header);
		text.append(NEW_LINE);
		for (final Object element : list) {
			text.append(createRowFromObject(element, separatorCols));
		}
		final InputStream stream = new ByteArrayInputStream(text.toString().getBytes(StandardCharsets.UTF_8));
		return new DefaultStreamedContent(stream, typeContent, reportName.concat(extension), "UTF-8");
	}

	/**
	 * Generate content report CSV.
	 *
	 * @param list the list
	 * @param header the header
	 * @param typeContent the type content
	 * @param reportName the report name
	 * @param extension the extension
	 * @param separatorCols the separator cols
	 * @return the streamed content
	 */
	public StreamedContent generateContentReportCSV(final List<?> list, final String header, final String typeContent,
			final String reportName, final String extension, final String separatorCols) {
		final StringBuilder text = new StringBuilder();
		text.append(header);
		text.append(NEW_LINE);
		for (final Object element : list) {
			text.append(createCSVRowFromObject(element, separatorCols));
		}
		final InputStream stream = new ByteArrayInputStream(text.toString().getBytes(StandardCharsets.ISO_8859_1));
		return new DefaultStreamedContent(stream, typeContent, reportName.concat(extension), "ISO-8859-1");
	}

	/**
	 * Creates the row from object.
	 *
	 * @param bean the bean
	 * @param separator the separator
	 * @return the string
	 */
	public String createRowFromObject(final Object bean, final String separator) {
		int maxNumberColumn = getMaxNumberColumn(bean);
		final Object[] row = getColumnValues(bean, maxNumberColumn);
		final StringBuilder result = new StringBuilder();
		int index = 0;
		for (Object col : row) {
			if (null != col && col instanceof java.util.Date) {
				col = this.formatDate((Date) col);
			}
			result.append(String.valueOf(col == null ? SPACE : col));
			++index;
			if (index < row.length) {
				result.append(separator);
			}
		}
		result.append(NEW_LINE);
		return result.toString();
	}

	/**
	 * Format date.
	 *
	 * @param date the date
	 * @return the string
	 */
	private String formatDate(Date date) {
		return SDF.format(date);
	}

	/** The Constant CSV_ENVOLVE. */
	private static final String CSV_ENVOLVE = "\"%1$s\"";

	/**
	 * Creates the CSV row from object.
	 *
	 * @param bean the bean
	 * @param separator the separator
	 * @return the string
	 */
	public String createCSVRowFromObject(final Object bean, final String separator) {
		int maxNumberColumn = getMaxNumberColumn(bean);
		final Object[] row = getColumnValues(bean, maxNumberColumn);
		final StringBuilder result = new StringBuilder();
		int index = 0;
		for (final Object col : row) {
			result.append(String.format(CSV_ENVOLVE, String.valueOf(col == null ? SPACE : col)));
			++index;
			if (index < row.length) {
				result.append(separator);
			}
		}
		result.append(NEW_LINE);
		return result.toString();
	}

	/**
	 * Obtiene el numero de columnas.
	 *
	 * @param bean            datos de la fila
	 * @return numero de columnas
	 */
	private int getMaxNumberColumn(final Object bean) {
		ColumnFile column;
		int maxNumberColumn = 0;
		for (final Method method : bean.getClass().getMethods()) {
			column = method.getAnnotation(ColumnFile.class);
			if (BooleanUtils.negate(column == null)) {
				if (column.indexColumn() > maxNumberColumn) {
					maxNumberColumn = column.indexColumn();
				}
			}
		}
		return maxNumberColumn;
	}

	/**
	 * Obtiene el valor de cada columna.
	 *
	 * @param bean the bean
	 * @param maxNumberColumn the max number column
	 * @return the column values
	 */
	private Object[] getColumnValues(final Object bean, final int maxNumberColumn) {
		final Object[] args = null;
		final Object[] columnValues = new Object[maxNumberColumn];
		ColumnFile metaDataColumn;
		for (final Method method : bean.getClass().getMethods()) {
			metaDataColumn = method.getAnnotation(ColumnFile.class);
			if (BooleanUtils.negate(metaDataColumn == null)) {
				try {
					columnValues[metaDataColumn.indexColumn() - 1] = (Object) method.invoke(bean, args);
				} catch (final Exception ignored) {
					LOGGER.error(
							":: Error al obtener los valores de las columnas para el archivo de matriz de productos ",
							ignored);
				}
			}
		}
		return columnValues;
	}

	/**
	 * Genera la clausula WHERE para los reportes en Jasper.
	 *
	 * @param entity the entity
	 * @return the string
	 */
	public String generateWhereClause(final Object entity) {
		final Class<?>[] parameterTypes = null;
		final Object[] args = null;
		Object resultMethodGet;
		final StringBuilder result = new StringBuilder(WHERE_CLAUSE);
		try {
			for (final Field field : entity.getClass().getDeclaredFields()) {
				final Column[] columns = field.getAnnotationsByType(Column.class);
				final String name = field.getName();
				LOGGER.info(":: name : {} ", name);
				LOGGER.info("::    columns : {} ", columns);
				if (BooleanUtils.negate(columns == null) && columns.length > ZERO) {
					resultMethodGet = entity.getClass()
							.getMethod(obtainMehodName(PREFIX_GET, field.getName()), parameterTypes)
							.invoke(entity, args);

					if (BooleanUtils.negate(isEmptyValueMethodGet(resultMethodGet))) {
						if (BooleanUtils.negate(result.indexOf(OPERATOR_EQUAL) == NOT_INDEX_OF)
								|| BooleanUtils.negate(result.indexOf(LIKE) == NOT_INDEX_OF)) {
							result.append(AND_CLAUSE);
							if(!TITULO.equals(columns[ZERO].name())){
								result.append(columns[ZERO].name());
								result.append(OPERATOR_EQUAL);
								if (resultMethodGet instanceof String) {
									result.append(SINGLE_QUOTE);
								}
								result.append(resultMethodGet);
								if (resultMethodGet instanceof String) {
									result.append(SINGLE_QUOTE);
									result.append(AND_CLAUSE);
								}
							}
						}
						result.append(BLANK);
						if (resultMethodGet instanceof String) {
							result.append(UtilJPACuentas.FUNCTION_LOWER);
						}
//						result.append(columns[ZERO].name());
						if (resultMethodGet instanceof String) {
							result.append(columns[ZERO].name());
							result.append(UtilJPACuentas.CLOSING_PARENTHESIS);
						}
						if (resultMethodGet instanceof String) {
							result.append(LIKE);
							result.append(UtilJPACuentas.FUNCTION_LOWER);
							result.append(SINGLE_QUOTE);
							if (!columns[ZERO].name().matches(UtilJPACuentas.EXCLUDE_LEFT_REGEX)
									&& !StringUtils.contains((String) resultMethodGet, QUALIFIER_LIKE)) {
								result.append(QUALIFIER_LIKE);
							}

							result.append(resultMethodGet);
							if (!columns[ZERO].name().matches(UtilJPACuentas.EXCLUDE_LEFT_REGEX)
									&& !StringUtils.contains((String) resultMethodGet, QUALIFIER_LIKE)) {
								result.append(QUALIFIER_LIKE);
							}
							result.append(SINGLE_QUOTE);
							result.append(UtilJPACuentas.CLOSING_PARENTHESIS);
						} else {
//							result.append(OPERATOR_EQUAL);
//							result.append(resultMethodGet);
						}
//						result.append(BLANK);
					}

				}
			}
		} catch (final Exception ignored) {
			LOGGER.error(":: Error al crear la clausula WHERE ", ignored);
		}
		if (result.indexOf(OPERATOR_EQUAL) == NOT_INDEX_OF && result.indexOf(LIKE) == NOT_INDEX_OF) {
			result.append(DEFAULT_CLAUSE);
		}
		LOGGER.info(":: Clausula WHERE: {} ", result);
		return result.toString();
	}
}
