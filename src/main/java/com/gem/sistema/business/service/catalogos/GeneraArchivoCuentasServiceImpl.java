/**
 * 
 */
package com.gem.sistema.business.service.catalogos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.GeneraArchivoCuentasDAO;
import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.dto.ArchivoCuentas;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraArchivoCuentasServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service("generaArchivoCuentasService")
public class GeneraArchivoCuentasServiceImpl implements GeneraArchivoCuentasService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneraArchivoCuentasServiceImpl.class);

	/** The Constant CUENTAS_TXT_HEAD. */
	private static final String CUENTAS_TXT_HEAD = FrontProperty.getPropertyValue("catalog.account.txt.head");

	/** The Constant CUENTAS_CSV_HEAD. */
	private static final String CUENTAS_CSV_HEAD = FrontProperty.getPropertyValue("catalog.account.csv.head");

	/** The Constant TYPE_CONTENT_TEXT_PLAIN. */
	private static final String TYPE_CONTENT_TEXT_PLAIN = FrontProperty.getPropertyValue("type.content.text.plain");

	/** The Constant TYPE_CONTENT_CSV. */
	private static final String TYPE_CONTENT_CSV = FrontProperty.getPropertyValue("type.content.csv");

	/** The Constant POLIZAS_TXT_FILENAME. */
	private static final String POLIZAS_TXT_FILENAME = FrontProperty.getPropertyValue("catalog.account.txt.filename");

	/** The Constant POLIZAS_CSV_FILENAME. */
	private static final String POLIZAS_CSV_FILENAME = FrontProperty.getPropertyValue("catalog.account.csv.filename");

	/** The Constant UTF_8. */
	private static final String UTF_8 = "UTF-8";

	/** The Constant ISO_8859_1. */
	private static final String ISO_8859_1 = "ISO-8859-1";

	/** The Constant REPORT_TXT_ID. */
	private static final Long REPORT_TXT_ID = 19L;

	/** The Constant REPORT_CSV_ID. */
	private static final Long REPORT_CSV_ID = 20L;

	/** The reportes repository. */
	@Autowired
	private ReportesRepository reportesRepository;

	/** The tc reporte txt. */
	private TcReporte tcReporteTxt;

	/** The tc reporte csv. */
	private TcReporte tcReporteCsv;

	/** The genera archivo cuentas DAO. */
	@Autowired
	private GeneraArchivoCuentasDAO generaArchivoCuentasDAO;

	/** The cuenta service. */
	@Autowired
	private CuentaService cuentaService;

	/**
	 * Instantiates a new genera archivo cuentas service impl.
	 */
	public GeneraArchivoCuentasServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.GeneraArchivoCuentasService#
	 * getTxtFile(java.util.Map)
	 */
	@Override
	public StreamedContent getTxtFile(Map<String, Object> filters) {
		this.tcReporteTxt = this.reportesRepository.findOne(REPORT_TXT_ID);
		ArchivoCuentas archivoCuentas = this.generaArchivoCuentasDAO.generateFile(CUENTAS_TXT_HEAD,
				String.format(this.tcReporteTxt.getQry1(), this.cuentaService.getWhereFromFilters(filters)),
				String.format(this.tcReporteTxt.getNombreArchivo(), UUID.randomUUID().toString()));
		return this.getStreamedContent(archivoCuentas, POLIZAS_TXT_FILENAME, TYPE_CONTENT_TEXT_PLAIN, UTF_8);
	}

	/**
	 * Gets the streamed content.
	 *
	 * @param archivoCuentas the archivo cuentas
	 * @param fileName the file name
	 * @param typeContent the type content
	 * @param encoding the encoding
	 * @return the streamed content
	 */
	private StreamedContent getStreamedContent(ArchivoCuentas archivoCuentas, String fileName, String typeContent,
			String encoding) {
		FileInputStream fis = null;
		StreamedContent toReturn = new DefaultStreamedContent();
		try {
			File out = new File(archivoCuentas.getFullFilePath() + "_result");
			GeneraArchivoCuentasServiceImpl.transform(new File(archivoCuentas.getFullFilePath()), UTF_8, out, encoding);
			fis = new FileInputStream(out);
			toReturn = new DefaultStreamedContent(fis, typeContent, fileName, encoding);
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return toReturn;
	}

	/**
	 * Transform.
	 *
	 * @param source the source
	 * @param srcEncoding the src encoding
	 * @param target the target
	 * @param tgtEncoding the tgt encoding
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void transform(File source, String srcEncoding, File target, String tgtEncoding) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), srcEncoding));
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(target), tgtEncoding));) {
			IOUtils.copy(br, bw);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.GeneraArchivoCuentasService#
	 * getCsvFile(java.util.Map)
	 */
	@Override
	public StreamedContent getCsvFile(Map<String, Object> filters) {
		this.tcReporteCsv = this.reportesRepository.findOne(REPORT_CSV_ID);
		ArchivoCuentas archivoCuentas = this.generaArchivoCuentasDAO.generateFile(CUENTAS_CSV_HEAD,
				String.format(this.tcReporteCsv.getQry1(), this.cuentaService.getWhereFromFilters(filters)),
				String.format(this.tcReporteCsv.getNombreArchivo(), UUID.randomUUID().toString()));
		return this.getStreamedContent(archivoCuentas, POLIZAS_CSV_FILENAME, TYPE_CONTENT_CSV, ISO_8859_1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.GeneraArchivoCuentasService#
	 * countByFilters(java.util.Map)
	 */
	@Override
	public Long countByFilters(Map<String, Object> filters) {
		return this.cuentaService.count(filters).longValue();
	}

}
