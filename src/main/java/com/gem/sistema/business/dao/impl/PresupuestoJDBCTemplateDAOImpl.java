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

import com.gem.sistema.business.dao.PresupuestoJDBCTemplateDAO;
import com.gem.sistema.business.dto.PresupuestoDTO;


// TODO: Auto-generated Javadoc
/**
 * The Class PresupuestoJDBCTemplateDAOImpl.
 */
@Repository(value = "presupuestoJDBCTemplateDAO")
public class PresupuestoJDBCTemplateDAOImpl implements PresupuestoJDBCTemplateDAO {

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/** The jdbc call egresos. */
	private SimpleJdbcCall jdbcCallEgresos;
	
	/** The jdbc call ingresos. */
	private SimpleJdbcCall jdbcCallIngresos;
	


	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCallEgresos = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_CARGA_ARCHIVO_EGRESOS");
		this.jdbcCallIngresos = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_CARGA_ARCHIVO_INGRESOS");
	}

	/**
	 * Instantiates a new presupuesto JDBC template DAO impl.
	 */
	public PresupuestoJDBCTemplateDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PresupuestoJDBCTemplateDAO#cargaArchivosEgresos(com.gem.sistema.business.dto.PresupuestoDTO)
	 */
	@Override
	public PresupuestoDTO cargaArchivosEgresos(PresupuestoDTO presupuesto) {
		
//		IN  i_procesar  INTEGER,
//        IN  i_id_user   VARCHAR(100),
//        IN  i_id_sector INTEGER,
//        IN  i_id_ref    INTEGER,
//        IN  i_file_name  VARCHAR(100),
//        IN  i_path_file  VARCHAR(100),
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_procesar", presupuesto.getiPprocesar())
				.addValue("i_id_user", presupuesto.getiIdUser()).addValue("i_id_sector", presupuesto.getiIdSector())
				.addValue("i_id_ref", presupuesto.getiIdRef()).addValue("i_file_name", presupuesto.getiFileName())
				.addValue("i_path_file", presupuesto.getiPathFile()).addValue("i_tipo", presupuesto.getTipo());
		Map<String, Object> out = jdbcCallEgresos.execute(in);

//		  OUT o_path_file  VARCHAR(100),
//          OUT o_fle_name   VARCHAR(100),
//          OUT o_error_file VARCHAR(100),
//          OUT o_cod_error  INTEGER     ,
//          OUT o_msg_error  VARCHAR(9000)
		presupuesto.setoPathFile(MapUtils.getString(out, "O_PATH_FILE"));
		presupuesto.setoFleName(MapUtils.getString(out, "O_FLE_NAME"));
		presupuesto.setoCodError(MapUtils.getInteger(out, "O_COD_ERROR"));
		presupuesto.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		
		return presupuesto;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PresupuestoJDBCTemplateDAO#cargaArchivosIngresos(com.gem.sistema.business.dto.PresupuestoDTO)
	 */
	@Override
	public PresupuestoDTO cargaArchivosIngresos(PresupuestoDTO presupuesto) {
		
//		IN  i_procesar  INTEGER,
//        IN  i_id_user   VARCHAR(100),
//        IN  i_id_sector INTEGER,
//        IN  i_id_ref    INTEGER,
//        IN  i_file_name  VARCHAR(100),
//        IN  i_path_file  VARCHAR(100),
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_procesar", presupuesto.getiPprocesar())
				.addValue("i_id_user", presupuesto.getiIdUser()).addValue("i_id_sector", presupuesto.getiIdSector())
				.addValue("i_file_name", presupuesto.getiFileName())
				.addValue("i_tipo", presupuesto.getTipo());
		Map<String, Object> out = jdbcCallIngresos.execute(in);

//		  OUT o_path_file  VARCHAR(100),
//          OUT o_fle_name   VARCHAR(100),
//          OUT o_error_file VARCHAR(100),
//          OUT o_cod_error  INTEGER     ,
//          OUT o_msg_error  VARCHAR(9000)
		presupuesto.setoFleName(MapUtils.getString(out, "O_FLE_NAME"));
		presupuesto.setoPathFile(MapUtils.getString(out, "O_PATH_FILE"));
		presupuesto.setoCodError(MapUtils.getInteger(out, "O_COD_ERROR"));
		presupuesto.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		
		return presupuesto;
	}

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Sets the jdbc template.
	 *
	 * @param jdbcTemplate            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Gets the data source.
	 *
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
