package com.gem.sistema.business.service.catalogos;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dto.ObrasDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ObrasServiceImpl.
 *
 * @author DOOM
 */
@Service("obrasService")
public class ObrasServiceImpl implements ObrasService {

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/** The pass obras. */
	@Value("${catalog.obras.pass}")
	private String PASS_OBRAS;

	/**
	 * Inyecta el datasource de la aplicación.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ObrasService#buscarObras(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ObrasDTO> buscarObras(String programatica, String numObra, String nombreObra) {
		String where = StringUtils.EMPTY;
		String sql = "SELECT O.ID,O.FN,O.FUN,O.SUBFUN,O.PROG,O.SUBPROG,O.PROY,O.FFIN,O.NCONTROL,"
				+ "O.NOMOBRA,O.TIPOEJEC,O.UBICACION,O.JUSTIFICACION,O.POBLACION,O.TIPOASIG,"
				+ "O.AUTO_1 AS auto1,O.AUTO_2 AS auto2,O.AUTO_3 AS auto3,O.AUTO_4 AS auto4,"
				+ "O.AUTO_5 AS auto5,O.AUTO_6 AS auto6,O.AUTO_7 AS auto7,O.AUTO_8 AS auto8,"
				+ "O.AUTO_9 AS auto9,O.AUTO_10 AS auto10,O.AUTO_11 AS auto11,O.AUTO_12 AS auto12,"
				+ "O.AUTO_13 AS auto13,O.TOEJE_1 AS toeje1,O.TOEJE_2 AS toeje2,O.TOEJE_3 AS toeje3,"
				+ "O.TOEJE_4 AS toeje4,O.TOEJE_5 AS toeje5,O.TOEJE_6 AS toeje6,O.TOEJE_7 AS toeje7,"
				+ "O.TOEJE_8 AS toeje8,O.TOEJE_9 AS toeje9,O.TOEJE_10 AS toeje10,O.TOEJE_11 AS toeje11,"
				+ "O.TOEJE_12 AS toeje12,O.TOEJE_13 AS toeje13,O.CAPTURO,O.FECCAP,O.CLVDEP,O.AVANCEF,"
				+ "O.AVANCEFA,O.USERID,O.IDSECTOR,O.ID_REF AS idref FROM OBRAS O";

		if (null != numObra && !numObra.isEmpty()) {
			where += (where == StringUtils.EMPTY) ? " WHERE CONCAT(O.NCONTROL,'') LIKE '%" + numObra  + "%'": " AND " + "CONCAT(O.NCONTROL,'') LIKE '%" + numObra + "%'";
		}
		if (null != programatica && !programatica.isEmpty()) {
			where += (where == StringUtils.EMPTY)
					? " WHERE CONCAT(O.FN,CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(O.FUN,O.SUBFUN),O.PROG),O.SUBPROG),O.PROY), O.FFIN)) LIKE '%"
							+ programatica + "%'"
					: " AND "
							+ "CONCAT(O.FN,CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(O.FUN,O.SUBFUN),O.PROG),O.SUBPROG),O.PROY), O.FFIN)) LIKE '%"
							+ programatica + "%'";
		}
		if (null != nombreObra && !nombreObra.isEmpty()) {
			where += (where == StringUtils.EMPTY) ? " WHERE upper(O.NOMOBRA) LIKE '%" + nombreObra.toUpperCase() + "%'"
					: " AND " + "upper(O.NOMOBRA) LIKE '%" + nombreObra.toUpperCase() + "%'";
		}

		List<ObrasDTO> obrasList = jdbcTemplate.query(sql.concat(where),
				new BeanPropertyRowMapper<ObrasDTO>(ObrasDTO.class));

		return obrasList;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ObrasService#validarPasswordBorrar(java.lang.String)
	 */
	@Override
	public boolean validarPasswordBorrar(String pass) {
		return PASS_OBRAS.equals(pass);
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
