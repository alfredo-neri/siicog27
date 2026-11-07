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

import com.gem.sistema.business.dao.PresupuestoEgresosIntegradoDAO;
import com.gem.sistema.business.domain.IntegradoEgreso;

// TODO: Auto-generated Javadoc
/**
 * The Class PresupuestoIntegradoJDBCTemplateDAO.
 */
@Repository("presupuestoIntegradoJDBCTemplateDAO")
public class PresupuestoIntegradoJDBCTemplateDAO implements PresupuestoEgresosIntegradoDAO{
	
	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;
	
	/** The simple jdbc call. */
	private SimpleJdbcCall simpleJdbcCall;
	
	/** The simple jdbc call presupesto integrado. */
	private SimpleJdbcCall simpleJdbcCallPresupestoIntegrado;
	
	/** The simple jdbc call presupesto ingreso. */
	private SimpleJdbcCall simpleJdbcCallPresupestoIngreso;
	
	
	/**
	 * Gets the simple jdbc call.
	 *
	 * @return the simple jdbc call
	 */
	public SimpleJdbcCall getSimpleJdbcCall() {
		return simpleJdbcCall;
	}
	
	/**
	 * Sets the simple jdbc call.
	 *
	 * @param simpleJdbcCall the new simple jdbc call
	 */
	public void setSimpleJdbcCall(SimpleJdbcCall simpleJdbcCall) {
		this.simpleJdbcCall = simpleJdbcCall;
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
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCallPresupestoIntegrado = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_PRESUPUESTO_INTEGRADO_IMP");
		this.simpleJdbcCallPresupestoIngreso   = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_PRESUPUESTO_INTEGRADO_INGRESO");
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PresupuestoEgresosIntegradoDAO#executePresupuestoIntegrado(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long)
	 */
	@Override
	public IntegradoEgreso executePresupuestoIntegrado(String idUser, Integer mes, Integer cveFef, Long idEntidad) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_id_user", idUser)
				.addValue("i_mes", mes).addValue("i_cve_ref",cveFef).addValue("i_id_entidad",idEntidad);

				
		Map<String, Object> out = simpleJdbcCallPresupestoIntegrado.execute(in);

		IntegradoEgreso integradoE = new IntegradoEgreso();
		integradoE.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		integradoE.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		integradoE.setPathFile(MapUtils.getString(out, "O_PATH_FILE"));
		integradoE.setFileName(MapUtils.getString(out,"O_FILE_NAME"));
		return integradoE;
		
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PresupuestoEgresosIntegradoDAO#exeuctePresupuesttoIngreso(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public IntegradoEgreso exeuctePresupuesttoIngreso(String idUser, Integer mes, Integer cveFef, Long idEntidad, Integer idSector) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_id_user", idUser)
				.addValue("i_mes", mes).addValue("i_cve_ref",cveFef).addValue("i_id_entidad",idEntidad).
				addValue("i_id_sector",idSector);

				
		Map<String, Object> out = simpleJdbcCallPresupestoIngreso.execute(in);

		IntegradoEgreso integradoE = new IntegradoEgreso();
		integradoE.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		integradoE.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		integradoE.setPathFile(MapUtils.getString(out, "O_PATH_FILE"));
		integradoE.setFileName(MapUtils.getString(out,"O_FILE_NAME"));
		return integradoE;
	}

}
