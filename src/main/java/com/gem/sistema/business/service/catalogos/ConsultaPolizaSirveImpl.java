package com.gem.sistema.business.service.catalogos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Poliend;
import com.gem.sistema.business.domain.TcRegistraArchivoDigital;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.TcRegistraArchivoDigitalRepository;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaPolizaSirveImpl.
 */
@Service("consultaPolizaSirveImpl")
public class ConsultaPolizaSirveImpl implements ConsultaPolizaSirve {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaPolizaSirveImpl.class);
	
	/** The registra archivo. */
	@Autowired
	@Qualifier("tcRegistraArchivoDigitalRepository")
	private TcRegistraArchivoDigitalRepository registraArchivo;

	/** The polide repository. */
	@Autowired
	@Qualifier("polideRepository")
	private PolideRepository polideRepository;

	/** The cuenta repository. */
	@Autowired
	@Qualifier("cuentaRepository")
	private CuentaRepository cuentaRepository;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

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

	/** The list cuenta. */
	private List<Cuenta> listCuenta;
	
	/** The list incorectos. */
	private List<Poliend> listIncorectos;

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

	/**
	 * Gets the list incorectos.
	 *
	 * @return the list incorectos
	 */
	public List<Poliend> getListIncorectos() {
		return listIncorectos;
	}

	/**
	 * Sets the list incorectos.
	 *
	 * @param listIncorectos the new list incorectos
	 */
	public void setListIncorectos(List<Poliend> listIncorectos) {
		this.listIncorectos = listIncorectos;
	}

	/**
	 * Gets the list cuenta.
	 *
	 * @return the list cuenta
	 */
	public List<Cuenta> getListCuenta() {
		return listCuenta;
	}

	/**
	 * Sets the list cuenta.
	 *
	 * @param listCuenta the new list cuenta
	 */
	public void setListCuenta(List<Cuenta> listCuenta) {
		this.listCuenta = listCuenta;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the registra archivo.
	 *
	 * @return the registra archivo
	 */
	public TcRegistraArchivoDigitalRepository getRegistraArchivo() {
		return registraArchivo;
	}

	/**
	 * Sets the registra archivo.
	 *
	 * @param registraArchivo the new registra archivo
	 */
	public void setRegistraArchivo(TcRegistraArchivoDigitalRepository registraArchivo) {
		this.registraArchivo = registraArchivo;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findPolicy(java.lang.String, int, int, java.lang.Integer)
	 */
	@Override
	public StreamedContent findPolicy(String tipo, int mes, int numero, Integer idSector) {
		StreamedContent strContent = null;

		LOGGER.info("dentro selsrevicio find");

		try {
			TcRegistraArchivoDigital archivoDigital = registraArchivo.getPoliBby(String.valueOf(mes), numero, tipo,
					idSector);
			String fileNameContent = archivoDigital.getPathFile() + "/" + archivoDigital.getNombreArchivo();
			strContent = new DefaultStreamedContent(new FileInputStream(fileNameContent));
		} catch (FileNotFoundException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"La Poliza no se encuentra en la base");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return strContent;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findPolicy(java.lang.Long)
	 */
	@Override
	public StreamedContent findPolicy(Long idRegistraArchivo) {
		StreamedContent strContent = null;

		LOGGER.info("dentro selsrevicio find");

		try {
			TcRegistraArchivoDigital archivoDigital = registraArchivo.findOne(idRegistraArchivo);
			String fileNameContent = archivoDigital.getPathFile() + archivoDigital.getNombreArchivo();
			strContent = new DefaultStreamedContent(new FileInputStream(fileNameContent), "image/tiff");
		} catch (FileNotFoundException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"La Poliza no se encuentra en la base");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return strContent;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findPoliceBy(java.lang.String, int, int, java.lang.Integer)
	 */
	@Override
	public List<Polide> findPoliceBy(String tipo, int mes, int numero, Integer idSector) {
		List<Polide> lisPolide = polideRepository.getByAllDetail(mes, numero, tipo, idSector);
		return lisPolide;
	}

	/** The Constant WRONG_POLICIES_SQL. */
	private static final String WRONG_POLICIES_SQL = "select en.tippol,  en.mespol, en.conpol,  en.ccontrol, en.cdebe  ,  "
			+ "en.chaber, en.ctc600, en.cta600, en.cappol from polien en where en.stapol = 'I'  and  en.mespol = ? and IDSECTOR = ?   "
			+ " order by 1,2,3";

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#wrongPolicy(int, java.lang.Integer)
	 */
	@Override
	public List<Poliend> wrongPolicy(int mes, Integer idSector) {
		listIncorectos = jdbcTemplate.query(ConsultaPolizaSirveImpl.WRONG_POLICIES_SQL, new Object[] { mes, idSector },
				new PolizasIncorrectasRowMapper());
		return listIncorectos;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findCuentas(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Cuenta> findCuentas(final String cuenta, final String sCta, final String ssCta, final String sssCta,
			final String ssssCta, Integer idSector) {
		final Predicate predicate = CuentaPredicates.findPolizaByCuenta(cuenta, sCta, ssCta, sssCta, ssssCta);
		listCuenta = (List<Cuenta>) cuentaRepository.findAll(predicate);
		return listCuenta;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findRow(java.math.BigDecimal, int, int, java.lang.String, java.lang.Integer)
	 */
	public List<Polide> findRow(final BigDecimal row, final int mes, final int numero, final String tipo,
			Integer idSector) {
		LOGGER.info("ROWWWW::: " + row);
		List<Polide> list = polideRepository.findRow(mes, numero, tipo, row, idSector);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#PolizIsTrue(java.lang.String, int, int, java.lang.Integer)
	 */
	@Override
	public Integer PolizIsTrue(String tipo, int mes, int numero, Integer idSector) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#updateRegistra(java.lang.String, int, int, java.lang.Integer)
	 */
	@Override
	public void updateRegistra(String tipo, int mes, int numero, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("UPDATE TC_REGISTRA_ARCHIVO_DIGITAL").append(" SET NOMBRE_ARCHIVO = ? ")
				.append(" WHERE TIPO   = ? ").append("   AND MES    = ? ").append("   AND NUMERO = ? ");
		jdbcTemplate.update(sSql.toString(), tipo, mes, numero);

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#sumaAbono8000(java.lang.String, int, int, java.lang.Integer)
	 */
	@Override
	public String sumaAbono8000(String tipo, int mes, int numero, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT NVL(SUM(CANPOL), 0) ").append("      FROM POLIDE ").append("WHERE TIPPOL = ? ")
				.append("  AND MESPOL = ? ").append("  AND CONPOL = ? ").append("  AND CUENTA  LIKE '8%'");
		String suma = jdbcTemplate.queryForObject(sSql.toString(), new Object[] { tipo, mes, numero },
				new SumaAbonos8000RowMapper());
		return suma;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#sumaCargo8000(java.lang.String, int, int, java.lang.Integer)
	 */
	@Override
	public String sumaCargo8000(String tipo, int mes, int numero, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT NVL(SUM(CANPOLH), 0) ").append("      FROM POLIDE ").append("WHERE TIPPOL = ? ")
				.append("  AND MESPOL = ? ").append("  AND CONPOL = ? ").append("  AND CUENTA  LIKE '8%'");
		String suma = jdbcTemplate.queryForObject(sSql.toString(), new Object[] { tipo, mes, numero },
				new SumaCargos8000RowMapper());
		return suma;

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#isPoliceActive(int, int)
	 */
	@Override
	public Integer isPoliceActive(int mes, int idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT COUNT(1) ").append("     FROM CONCTB ").append("WHERE MESEMP   >= ?")
				.append("  AND IDSECTOR = ?");
		Integer bandera = jdbcTemplate.queryForObject(sSql.toString(), new Object[] { mes, idSector },
				new isPolicActiviRowMapper());
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findActiveMonth(int)
	 */
	@Override
	public Integer findActiveMonth(int idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT MESEMP ").append("     FROM CONCTB ").append(" WHERE IDSECTOR = ?");
		Integer bandera = jdbcTemplate.queryForObject(sSql.toString(), new Object[] { idSector },
				new isPolicActiviRowMapper());
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#getSumByCargo(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getSumByCargo(Integer mes, Integer numero, String tipo, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT SUM(CANPOL) ").append("      FROM POLIDE ").append("WHERE MESPOL  = ?")
				.append("  AND TIPPOL  = ?").append("  AND CONPOL  = ?");
		String suma = (String) jdbcTemplate.queryForObject(sSql.toString(), new Object[] { mes, tipo, numero },
				String.class);
		return suma;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#getSumByAbono(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getSumByAbono(Integer mes, Integer numero, String tipo, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT SUM(CANPOLH) ").append("      FROM POLIDE ").append("WHERE MESPOL  = ?")
				.append("  AND TIPPOL  = ?").append("  AND CONPOL  = ?");
		String suma = (String) jdbcTemplate.queryForObject(sSql.toString(), new Object[] { mes, tipo, numero },
				String.class);
		return suma;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#updatePoliza(java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer updatePoliza(BigDecimal cControl, BigDecimal cargo, BigDecimal aabonot, Integer mes, Integer numero,
			String tpo, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("UPDATE POLIEN ").append("   SET CCONTROL = ?, ").append("       CDEBE    = ?, ")
				.append("       CHABER   = ?  ").append("WHERE MESPOL = ? ").append("  AND TIPPOL = ? ")
				.append("  AND CONPOL = ? ");
		Object[] parameters = { cControl, cargo, aabonot, mes, tpo, numero };
		Integer rows = jdbcTemplate.update(sSql.toString(), parameters);
		return rows;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#exitPoliza(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer exitPoliza(Integer mes, Integer numero, String tipo, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT COUNT(1) ").append("     FROM POLIEN ").append("WHERE MESPOL = ? ")
				.append("  AND CONPOL = ? ").append("  AND TIPPOL = ? ");
		Integer exist = (Integer) jdbcTemplate.queryForObject(sSql.toString(), new Object[] { mes, numero, tipo },
				Integer.class);
		return exist;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConsultaPolizaSirve#findAllCounts(java.lang.Integer)
	 */
	@Override
	public List<Cuenta> findAllCounts(Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT CUENTA, ").append("       SCTA  , ").append("       SSCTA , ").append("       SSSCTA, ")
				.append("       SSSSCTA,").append("       NOMCTA  ").append("      FROM CUENTA ")
				.append("WHERE NIVCTA = 'S' ").append("  AND IDSECTOR = ?");
		List<Cuenta> listCuenta = jdbcTemplate.query(sSql.toString(), new Object[] { idSector }, new FindAllAcounts());
		return listCuenta;
	}

}

class FindAllAcounts implements RowMapper<Cuenta> {

	@Override
	public Cuenta mapRow(ResultSet rs, int arg1) throws SQLException {
		Cuenta cuenta = new Cuenta();
		cuenta.setCuenta(rs.getString(1));
		cuenta.setScuenta(rs.getString(2));
		cuenta.setSscuenta(rs.getString(3));
		cuenta.setSsscuenta(rs.getString(4));
		cuenta.setSssscuenta(rs.getString(5));
		cuenta.setNomcta(rs.getString(6));
		return cuenta;
	}

}

class PolizasIncorrectasRowMapper implements RowMapper<Poliend> {
	public Poliend mapRow(ResultSet rs, int i) throws SQLException {
		Poliend poliend = new Poliend();
		poliend.setTippol(rs.getString(1));
		poliend.setMespol(rs.getInt(2));
		poliend.setConpol(rs.getInt(3));
		poliend.setCcontro(rs.getDouble(4));
		poliend.setSdebe(rs.getDouble(5));
		poliend.setShaber(rs.getDouble(6));
		poliend.setCtc6000(rs.getDouble(7));
		poliend.setCta6000(rs.getDouble(8));
		poliend.setCappol(rs.getString(9));
		return poliend;
	}
}

class SumaAbonos8000RowMapper implements RowMapper<String> {
	public String mapRow(ResultSet rs, int i) throws SQLException {
		String suma = rs.getString(1);

		return suma;
	}
}

class SumaCargos8000RowMapper implements RowMapper<String> {
	public String mapRow(ResultSet rs, int i) throws SQLException {
		String suma = rs.getString(1);

		return suma;
	}
}

class isPolicActiviRowMapper implements RowMapper<Integer> {
	public Integer mapRow(ResultSet rs, int i) throws SQLException {
		Integer bandera = rs.getInt(1);

		return bandera;
	}
}
