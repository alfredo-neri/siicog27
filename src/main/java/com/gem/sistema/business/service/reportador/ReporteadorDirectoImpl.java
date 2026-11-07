package com.gem.sistema.business.service.reportador;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteadorDirectoImpl.
 */
@Service("reporteadorDirectoImpl")
public class ReporteadorDirectoImpl implements ReporteadorDirecto {

	//public static final String CHARSET = "UTF-8";
	
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
/*
	@Override
	public List<String> ejecutaQueryNativo(TcReporte tcReporte) {
		List<Map<String, Object>> list;
		List<String> finalList = new LinkedList<String>();
		list = jdbcTemplate.queryForList(tcReporte.getQry1() + tcReporte.getQry2() + tcReporte.getQry3());
		StringBuffer row = new StringBuffer();
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				row.append(String.format(QUOTE_TXT,
						entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString().trim()));
			}
			finalList.add(StringUtils.substring(row.toString(), 1));
			row.delete(0, row.length());
		}
		return finalList;
	}
	*/
	
	
	/* (non-Javadoc)
 * @see com.gem.sistema.business.service.reportador.ReporteadorDirecto#ejecutaQueryNativoToFile(com.gem.sistema.business.domain.TcReporte, int, java.util.Map)
 */
public String ejecutaQueryNativoToFile(TcReporte tcReporte, int typeFile, Map<String, Object> params) {
		List<Map<String, Object>> list;
		String query =  tcReporte.getQry1() + tcReporte.getQry2() + tcReporte.getQry3();
		
		Object[] par = new Object[params.size()];
		int [] argTypes = new int[params.size()];		
		String[] tokens = query.split(":");
		String token;
		String param;
		StringBuffer queryPrepared = new StringBuffer(query.length());
		queryPrepared.append(tokens[0]);
		int index = 0;
		for (int i = 1; i < tokens.length; i++) {
			token =  tokens[i].trim();
			int hasta = 0; 			
			if(i == (tokens.length-1)){
				param = token;
				queryPrepared.append("? ");
			}else{
				hasta = token.indexOf(' ');  
						
				param = token.substring(0, hasta);	
				queryPrepared.append("? ");
				queryPrepared.append(token.substring(hasta));	
			}			
			for(Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                Object value = params.get(key);
                if( key.toUpperCase().equals(param.toUpperCase()) ) {
                	par[index] = value;                	
                	
                	 if( value instanceof Double) {
                		 argTypes[index++] = java.sql.Types.DOUBLE; 
            		  }  else if( value instanceof Integer) {
                 		 argTypes[index++] = java.sql.Types.INTEGER; 
            		  } else if( value instanceof String) {
                  		 argTypes[index++] = java.sql.Types.VARCHAR; 
             		  } else if( value instanceof Long) {
                  		 argTypes[index++] = java.sql.Types.BIGINT; 
             		  } else if( value instanceof Float) {
		             		 argTypes[index++] = java.sql.Types.FLOAT; 		        		   
             		  }else if( value instanceof Date) {
		             		 argTypes[index++] = java.sql.Types.DATE; 			        		   
             		  } else if( value instanceof java.sql.Date) {
		             		 argTypes[index++] = java.sql.Types.DATE; 			        		   
          		      } else if( value instanceof java.math.BigDecimal) {
		             		 argTypes[index++] = java.sql.Types.DECIMAL; 			        		   
          		      } else {
                  		throw new RuntimeException("Tipo de dato no soportado en los parmetros.");
             		  }
                	
                	break;
                }
            }		
					
		}
		
		list = jdbcTemplate.queryForList(queryPrepared.toString(), par, argTypes );
		
		
		String pattern = null;
		switch (typeFile) {
		case CSV:
			pattern = QUOTE_CSV ;	
			break;
		case TXT:
			pattern = QUOTE_TXT ;		
			break;
		}		
		
		StringBuffer file = new StringBuffer();
		StringBuffer row = new StringBuffer();
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				row.append(String.format(pattern,
						entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString().trim()));
			}
			row.append("\n");
			file.append(StringUtils.substring(row.toString(), 1));
			row.delete(0, row.length());
		}
		return file.toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.reportador.ReporteadorDirecto#getFileReport(com.gem.sistema.business.domain.TcReporte, java.util.Map, java.lang.String, int)
	 */
	public StreamedContent getFileReport(TcReporte tcReporte, Map<String, Object> params, String nombreCompuesto, int typeFile)  {		  
		StreamedContent file = null;
		String rowsToFile = ejecutaQueryNativoToFile(tcReporte, typeFile,  params);
		switch (typeFile) {
		case CSV:
			file = new ByteArrayContent(rowsToFile.getBytes(ENCODING_UTF8), TYPE_CONTENT_CSV, nombreCompuesto, ENCODING_UTF8.name()) ;	
			break;
		case TXT:
			file = new ByteArrayContent(rowsToFile.getBytes(ENCODING_UTF8), TYPE_CONTENT_TEXT_PLAIN, nombreCompuesto, ENCODING_UTF8.name()) ;	
			break;
		}
		
		
		return file;
	}
	
	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 */
	/*
	public File saveTxtReport(List<String> rowsToFile, String rutaArchivo, String nombreCompuesto) throws IOException {
		File file = new File(rutaArchivo + nombreCompuesto);
		FileUtils.writeLines(file, ENCODING_UTF8.name(), rowsToFile, Constants.EOL);
		return file;
	}
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
