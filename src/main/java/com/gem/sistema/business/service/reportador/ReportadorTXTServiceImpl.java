package com.gem.sistema.business.service.reportador;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportadorTXTServiceImpl.
 */
@Service(value = "reportadorTXTService")
public class ReportadorTXTServiceImpl implements ReportadorTXTService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportadorTXTServiceImpl.class);

	/** The Constant QUOTE. */
	private static final String QUOTE = "|\"%1$s\"";
	
	/** The Constant CHARSET. */
	public static final String CHARSET = "UTF-8";

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.reportador.ReportadorTXTService#ejecutaQueryNativo(com.gem.sistema.business.domain.TcReporte)
	 */
	@Override
	public List<String> ejecutaQueryNativo(TcReporte tcReporte) {
		List<Map<String, Object>> list;
		List<String> finalList = new LinkedList<String>();
		String query = tcReporte.getQry1() + tcReporte.getQry2() + tcReporte.getQry3();
		StringUtils.indexOfIgnoreCase(query, "from");
		String queryCount = "Select count(1) " + StringUtils.substring(query, StringUtils.indexOfIgnoreCase(query, "from"));
		
		LOGGER.debug("Query a ejecutar ::: " + query);
		
		list = jdbcTemplate.queryForList(query);
		
		StringBuffer row = new StringBuffer();
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				row.append(String.format(QUOTE,
						entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString().trim()));
			}
			finalList.add(StringUtils.substring(row.toString(), 1));
			row.delete(0, row.length());
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.reportador.ReportadorTXTService#saveTxtReport(java.util.List, java.lang.String, java.lang.String)
	 */
	public File saveTxtReport(List<String> rowsToFile, String rutaArchivo, String nombreCompuesto) throws IOException {
		File file = new File(rutaArchivo + nombreCompuesto);
		FileUtils.writeLines(file, ReportadorTXTServiceImpl.CHARSET, rowsToFile, Constants.EOL);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.reportador.ReportadorTXTService#ejecutaQueryNativo(com.gem.sistema.business.domain.TcReporte, java.lang.String, java.lang.String)
	 */
	@Override
	public File ejecutaQueryNativo(TcReporte tcReporte, String rutaArchivo, String nombreCompuesto) throws IOException {
		List<Map<String, Object>> list;
		// List<String> finalList = new LinkedList<String>();
		list = jdbcTemplate.queryForList(tcReporte.getQry1() + tcReporte.getQry2() + tcReporte.getQry3());
		StringBuffer row = new StringBuffer();
		File file = new File(rutaArchivo + nombreCompuesto);
		if (file.exists()) {
			FileUtils.deleteQuietly(file);
		}
		if(CollectionUtils.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					row.append(String.format(QUOTE,
							entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString().trim()));
				}
				// finalList.add(StringUtils.substring(row.toString(), 1));
				row.append(Constants.EOL);
				FileUtils.write(file, StringUtils.substring(row.toString(), 1), ReportadorTXTServiceImpl.CHARSET,
						Boolean.TRUE);
				row.delete(0, row.length());
			}
		} else {
			FileUtils.write(file, StringUtils.substring(row.toString(), 1), ReportadorTXTServiceImpl.CHARSET,
					Boolean.TRUE);
		}
		return file;
	}

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Sets the jdbc template.
	 *
	 * @param jdbcTemplate the new jdbc template
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
