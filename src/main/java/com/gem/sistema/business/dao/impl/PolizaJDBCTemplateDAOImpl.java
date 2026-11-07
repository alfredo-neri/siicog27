/**
 * 
 */
package com.gem.sistema.business.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.PolizaImportJDBCTemplateDAO;
import com.gem.sistema.business.dto.PolizaImportDTO;


// TODO: Auto-generated Javadoc
/**
 * The Class PolizaJDBCTemplateDAOImpl.
 */
@Repository
public class PolizaJDBCTemplateDAOImpl implements PolizaImportJDBCTemplateDAO {
	//@Autowired
	//private JdbcTemplate jdbcTemplate;

	/** The data source. */
	private DataSource dataSource;

	/** The jdbc call importar. */
	private SimpleJdbcCall jdbcCallImportar;


	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcCallImportar = new SimpleJdbcCall(dataSource).withProcedureName("SP_IMPORTAR_POLIZA");
	}

	/**
	 * Instantiates a new poliza JDBC template DAO impl.
	 */
	public PolizaJDBCTemplateDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaImportJDBCTemplateDAO#cargaArchivosPoliza(com.gem.sistema.business.dto.PolizaImportDTO)
	 */
	@Override
	public PolizaImportDTO cargaArchivosPoliza(PolizaImportDTO poliza) {
		
//		IN  i_procesar  INTEGER,
//        IN  i_id_user   VARCHAR(100),
//        IN  i_id_sector INTEGER,
//        IN  i_id_ref    INTEGER,
//        IN  i_file_name  VARCHAR(100),
//        IN  i_path_file  VARCHAR(100),
		
		//1 = cargar. 0 = revisar
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_procesar", "C".equals(poliza.getiProcesar())?1:0 ) 
				.addValue("i_id_user", poliza.getiIdUser()).addValue("i_id_sector", poliza.getiIdSector())
				.addValue("i_id_ref", poliza.getiIdRef()).addValue("i_file_name", poliza.getiFileName())
				.addValue("i_path_file", poliza.getiPathFile())
				.addValue("i_mes", poliza.getiMes())
				.addValue("i_tipol", poliza.getiTipoL())
				.addValue("i_numpol", poliza.getiNumpol())
				.addValue("i_fechapol", poliza.getiFechaPol())
				.addValue("i_boxes", poliza.getiBoxes());
				
		Map<String, Object> out = jdbcCallImportar.execute(in);

//		  OUT o_path_file  VARCHAR(100),
//          OUT o_fle_name   VARCHAR(100),
//          OUT o_error_file VARCHAR(100),
//          OUT o_cod_error  INTEGER     ,
//          OUT o_msg_error  VARCHAR(9000)
		poliza.setoPathFile(MapUtils.getString(out, "O_PATH_FILE"));
		poliza.setoFleName(MapUtils.getString(out, "O_FLE_NAME"));
		poliza.setoCodError(MapUtils.getInteger(out, "O_COD_ERROR"));
		poliza.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		
		return poliza;
	}
	
	

	/**
	 * Gets the data source.
	 *
	 * @return the jdbcTemplate
	 */
	/*
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
*/
	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */	
	/*
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
*/
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
