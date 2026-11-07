/**
 * 
 */
package com.gem.sistema.business.service.reportador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import com.gem.sistema.business.domain.TcImagenesEntAdmin;
//import com.gem.sistema.business.domain.TcImagenesMuni;
//import com.gem.sistema.business.repository.catalogs.TcImagenesEntAdminRepository;
//import com.gem.sistema.business.repository.catalogs.TcImagenesMuniRepository;
//import com.gem.sistema.util.UtilReport;
//import com.gem.sistema.web.security.model.GemUser;
//import com.gem.sistema.web.util.FrontProperty;
//
//import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
//import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
//import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class JasperGenericServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service("jasperGenericServiceImpl")
public class JasperGenericServiceImpl implements JasperGenericService {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JasperGenericServiceImpl.class);

	/** The Constant REPORT_PATH_JASPER. */
	protected static final String REPORT_PATH_JASPER = "reports";

	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/** The char width. */
	public static Float CHAR_WIDTH;

	/** The char height. */
	public static Float CHAR_HEIGHT;

	/*
	 * @Autowired
	 * 
	 * @Qualifier(value = "tcImagenesEntAdminRepository") private
	 * TcImagenesEntAdminRepository tcImagenesEntAdminRepository;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value = "tcImagenesMuniRepository") private
	 * TcImagenesMuniRepository tcImagenesMuniRepository;
	 */
	/**
	 * Fuente de datos BD local.
	 */
	private DataSource dataSource;

	/**
	 * Gets the data source.
	 *
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.reportador.JasperGenericService#getReport(
	 * java.util.Map, java.lang.String, int)
	 */
	public StreamedContent getReport(Map<String, Object> params, String jasperReportName, int type) {
		final InputStream stream = getInternalReport(params, jasperReportName, type);
		switch (type) {
		case JasperGenericService.PDF:
			return new DefaultStreamedContent(stream, CONTENT_TYPE_PDF, jasperReportName.concat(PDF_EXTENSION));
		case JasperGenericService.XLS:
			return new DefaultStreamedContent(stream, CONTENT_TYPE_XLS, jasperReportName.concat(XLS_EXTENSION));
		case JasperGenericService.CSV:
			return new DefaultStreamedContent(stream, TYPE_CONTENT_CSV, jasperReportName.concat(CSV_EXTENSION),
					"ISO-8859-1");
		case JasperGenericService.TXT:
			return new DefaultStreamedContent(stream, TYPE_CONTENT_TEXT_PLAIN, jasperReportName.concat(TXT_EXTENSION),
					"UTF-8");
		default:
			return new DefaultStreamedContent(stream, CONTENT_TYPE_PDF, jasperReportName.concat(PDF_EXTENSION));
		}
	}
	
	public StreamedContent getReport(Map<String, Object> params, String jasperReportName, String reportaEndName, int type) {
		final InputStream stream = getInternalReport(params, jasperReportName, type);
		switch (type) {
		case JasperGenericService.PDF:
			return new DefaultStreamedContent(stream, CONTENT_TYPE_PDF, reportaEndName.concat(PDF_EXTENSION));
		case JasperGenericService.XLS:
			return new DefaultStreamedContent(stream, CONTENT_TYPE_XLS, reportaEndName.concat(XLS_EXTENSION));
		case JasperGenericService.CSV:
			return new DefaultStreamedContent(stream, TYPE_CONTENT_CSV, reportaEndName.concat(CSV_EXTENSION),
					"ISO-8859-1");
		case JasperGenericService.TXT:
			return new DefaultStreamedContent(stream, TYPE_CONTENT_TEXT_PLAIN, reportaEndName.concat(TXT_EXTENSION),
					"UTF-8");
		default:
			return new DefaultStreamedContent(stream, CONTENT_TYPE_PDF, reportaEndName.concat(PDF_EXTENSION));
		}
	}

	/**
	 * Gets the jasper report.
	 *
	 * @param params           the params
	 * @param jasperReportName the jasper report name
	 * @return the jasper report
	 */
	private JasperPrint getJasperReport(Map<String, Object> params, String jasperReportName) {
		Connection connection = null;
		JasperPrint print = null;
		try {
			LOGGER.info(":: Inicia generacion JasperReport, params: {}", params);
			connection = dataSource.getConnection();
			final FacesContext context = FacesContext.getCurrentInstance();
			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			final String jasperPathReport = servletContext
					.getRealPath(REPORT_PATH_JASPER + FILE_SEPARATOR + jasperReportName + ".jasper");
			print = JasperFillManager.fillReport(jasperPathReport, params, connection);
			LOGGER.info(":: Finaliza generacion JasperReport ");
		} catch (JRException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return print;
	}

	private JasperPrint getJasperReport(Map<String, Object> params, String jasperReportName, File fileJson) {
		JasperPrint print = null;

		try {
			LOGGER.info(":: Inicia generacion JasperReport, params: {}", params);

			params.put("JSON_INPUT_STREAM", new FileInputStream(fileJson));
			final FacesContext context = FacesContext.getCurrentInstance();
			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			final String jasperPathReport = servletContext
					.getRealPath(REPORT_PATH_JASPER + FILE_SEPARATOR + jasperReportName + ".jasper");
			print = JasperFillManager.fillReport(jasperPathReport, params);
			
			LOGGER.info(":: Finaliza generacion JasperReport ");
		} catch (JRException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return print;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.reportador.JasperGenericService#
	 * getReport(java.util.Map, java.lang.String)
	 */
	@Override
	public InputStream getInternalReport(Map<String, Object> params, String jasperReportName, int type) {
		InputStream stream = null;

		try {

			final JasperPrint print = getJasperReport(params, jasperReportName);
			final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

			switch (type) {
			case JasperGenericService.PDF:
				JasperExportManager.exportReportToPdfStream(print, arrayOutputStream);
				break;
			case JasperGenericService.XLS:
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(arrayOutputStream));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setDetectCellType(true);// Set configuration as
														// you like it!!
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				break;
			case JasperGenericService.CSV:
				JRCsvExporter cvsExporter = new JRCsvExporter();
				cvsExporter.setExporterInput(new SimpleExporterInput(print));
				cvsExporter.setExporterOutput(new SimpleWriterExporterOutput(arrayOutputStream));
				cvsExporter.exportReport();
				break;
			case JasperGenericService.TXT:
				JRTextExporter txtExporter = new JRTextExporter();
				txtExporter.setExporterInput(new SimpleExporterInput(print));
				txtExporter.setExporterOutput(new SimpleWriterExporterOutput(arrayOutputStream));
				SimpleTextReportConfiguration configuration2 = new SimpleTextReportConfiguration();
				configuration2.setCharWidth(CHAR_WIDTH);
				configuration2.setCharHeight(CHAR_HEIGHT);
				txtExporter.setConfiguration(configuration2);
				txtExporter.exportReport();
				break;

			default:
				JasperExportManager.exportReportToPdfStream(print, arrayOutputStream);
				break;
			}

			if (JasperGenericService.PDF == type) {
				stream = new BByteArrayInputStream(arrayOutputStream.toByteArray());
			} else {
				stream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
			}

			LOGGER.info(":: Termina Exportar ");
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
		return stream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.reportador.JasperGenericService#
	 * createReportPdftoFs(java.util.Map, java.lang.String, java.lang.String)
	 */
	public void createReportPdftoFs(Map<String, Object> params, String jasperReportName, String pathFileName) {
		try {
			final JasperPrint print = getJasperReport(params, jasperReportName);
			JasperExportManager.exportReportToPdfFile(print, pathFileName);
			LOGGER.info(":: Termina Exportar PDF ");
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	public void createReportPdftoFs(Map<String, Object> params, String jasperReportName, String pathFileName,
			File jsonFile) {
		try {
			final JasperPrint print = getJasperReport(params, jasperReportName, jsonFile);
			JasperExportManager.exportReportToPdfFile(print, pathFileName);
			LOGGER.info(":: Termina Exportar PDF ");
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
