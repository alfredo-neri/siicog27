package com.gem.sistema.business.service.catalogos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.PolizaJDBCTemplateDAO;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcRetencione;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.predicates.TcRetencionesPredicate;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcRetencionesRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
// TODO: Auto-generated Javadoc

/**
 * The Class PolizaServiceImpl.
 */
@Service("polizaService")
public class PolizaServiceImpl implements PolizaService {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaServiceImpl.class);

	/** The Constant PATH_IMAGE. */
	private static final String PATH_IMAGE = "pImg";

	/** The Constant TIPO_POLIZA. */
	private static final String TIPO_POLIZA = "pTipoPoliza";

	/** The Constant MES_POLIZA. */
	private static final String MES_POLIZA = "pMesPoliza";

	/** The Constant NUM_POL_MIN. */
	private static final String NUM_POL_MIN = "pConsecutivoPolizaMin";

	/** The Constant NUM_POL_MAX. */
	private static final String NUM_POL_MAX = "pConsecutivoPolizaMax";

	/** The Constant SECTOR. */
	private static final String SECTOR = "pSector";

	/** The Constant USUARIO. */
	private static final String USUARIO = "pUser";

	/** Ruta donde se encuentra el archivo jasper del reporte de programas. */
	@Value("${view.report.path.jasper}")
	protected String REPORT_PATH_JASPER;

	/** The polien repository. */
	@Autowired
	private PolienRepository polienRepository;

	/** The poliza JDBC template DAO. */
	@Autowired
	private PolizaJDBCTemplateDAO polizaJDBCTemplateDAO;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 */
	@Autowired
	private TcRetencionesRepository tcRetencionesRepository;

	/** The jdbc call. */
	private SimpleJdbcCall jdbcCall;

	/**
	 * Fuente de datos BD local.
	 */
	private DataSource dataSource;

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_ORDENA_DETALLES_POLIDE");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.PolizaService#
	 * countByTipopolMespolConpol(java.lang.String, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Long countByTipopolMespolConpol(String tipopol, Integer mespol, Integer minConpol, Integer maxConpol,
			Integer idSector) {
		return this.polienRepository
				.count(PolienPredicates.elegibleToPrograms(tipopol, mespol, minConpol, maxConpol, idSector));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.PolizaService#
	 * getReportByTipopolMespolConpol(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, int, java.lang.String)
	 */
	@Override
	public InputStream getReportByTipopolMespolConpol(String pathJasper, String reportName, String pathLogo,
			String tipopol, Integer mespol, Integer minConpol, Integer maxConpol, int idSector, String user) {
		Connection connection = null;
		InputStream stream = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put(PATH_IMAGE, pathLogo);
			parameter.put(TIPO_POLIZA, tipopol);
			parameter.put(MES_POLIZA, mespol);
			parameter.put(NUM_POL_MIN, minConpol);
			parameter.put(NUM_POL_MAX, maxConpol);
			parameter.put(SECTOR, idSector);
			parameter.put(USUARIO, user);
			connection = dataSource.getConnection();
			final FacesContext context = FacesContext.getCurrentInstance();
			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			final String pathReport = servletContext.getRealPath(REPORT_PATH_JASPER.concat(pathJasper));
			final JasperPrint print = JasperFillManager.fillReport(pathReport, parameter, connection);
			final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, arrayOutputStream);
			stream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
			LOGGER.info(":: Termina Exportar PDF ");
		} catch (final Exception genericExc) {
			LOGGER.error(":: Error al generar reporte de Excel ", genericExc);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return stream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#savePDFFile(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer, int,
	 * java.lang.String)
	 */
	@Override
	public String savePDFFile(String pathJasper, String reportName, String pathLogo, String tipopol, Integer mespol,
			Integer minConpol, Integer maxConpol, int idSector, String user) throws IOException {
		final FacesContext context = FacesContext.getCurrentInstance();
		final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String fileName = UUID.randomUUID() + "_Poliza.pdf";

		final String pathReport = servletContext.getRealPath("/tmp");
		;
		FileUtils.copyInputStreamToFile(this.getReportByTipopolMespolConpol(pathJasper, reportName, pathLogo, tipopol,
				mespol, minConpol, maxConpol, idSector, user), new File(pathReport + "/" + fileName));
		return "/tmp/" + fileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#copyPoliza(com.gem.
	 * sistema.business.domain.Poliza, com.gem.sistema.business.domain.Poliza,
	 * java.lang.String, int)
	 */
	@Override
	public Poliza copyPoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector) {
		return this.polizaJDBCTemplateDAO.copyPoliza(poliza, polizaDest, userId, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#inversaPoliza(com.
	 * gem.sistema.business.domain.Poliza, com.gem.sistema.business.domain.Poliza,
	 * java.lang.String, int)
	 */
	@Override
	public Poliza inversaPoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector) {
		return this.polizaJDBCTemplateDAO.inversePoliza(poliza, polizaDest, userId, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.PolizaService#delete(com.gem.
	 * sistema.business.domain.Polien, int, java.lang.String)
	 */
	@Override
	public Poliza delete(Polien polien, int idSector, String idUser) {
		LOGGER.info("Entra a elimiar la poliza");
		LOGGER.info("mes:::" + polien.getMespol());
		LOGGER.info("tipo::" + polien.getTippol());
		LOGGER.info("numero::" + polien.getConpol());
		return this.polizaJDBCTemplateDAO.deletePoliza(polien, idSector, idUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#negativePoliza(com.
	 * gem.sistema.business.domain.Poliza, com.gem.sistema.business.domain.Poliza,
	 * java.lang.String, int)
	 */
	@Override
	public Poliza negativePoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector) {

		return this.polizaJDBCTemplateDAO.negativePoliza(poliza, polizaDest, userId, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#cierreMensul(java.
	 * lang.Integer, java.lang.String, int)
	 */
	@Override
	public Poliza cierreMensul(Integer mes, String idUser, int idSector) {

		return this.polizaJDBCTemplateDAO.cierreMensual(mes, idUser, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#desafectaPoliza(java
	 * .lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Poliza desafectaPoliza(Integer mes, String tipo, Integer numero, Integer idSector) {
		// TODO Auto-generated method stub
		return this.polizaJDBCTemplateDAO.desfaPoliza(mes, tipo, numero, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#desafecta(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public Poliza desafecta(Integer mes, Integer idSector) {
		// TODO Auto-generated method stub
		return this.polizaJDBCTemplateDAO.desafectacion(mes, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.PolizaService#
	 * desafectacionPrecierre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Poliza desafectacionPrecierre(Integer mes, Integer idSector) {
		// TODO Auto-generated method stub
		return this.polizaJDBCTemplateDAO.desafectacionPreciere(mes, idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#afectacionPrecierre(
	 * java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Poliza afectacionPrecierre(Integer mes, Integer idSector, String idUser) {
		// TODO Auto-generated method stub
		return this.polizaJDBCTemplateDAO.afectacionPreCierre(mes, idSector, idUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.PolizaService#getMaxPolienConpol(
	 * java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer getMaxPolienConpol(String tippol, Integer mespol, Integer idSector) {
		return this.polienRepository.maxPolizas(mespol, tippol, idSector);
	}

	/**
	 *
	 */
	@Override
	public TcRetencione saveRetenciones(TcRetencione tcRetencione) {
		return this.tcRetencionesRepository.save(tcRetencione);
	}

	@Override
	public List<TcRetencione> findRetenciones(TcRetencione tcRetencione) {
		return (List<TcRetencione>) this.tcRetencionesRepository
				.findAll(TcRetencionesPredicate.findByIdSector(tcRetencione.getIdSector()));
	}

	@Override
	public TcRetencione findOneRetenciones(TcRetencione tcRetencione) {
		return tcRetencionesRepository.findOne(
				TcRetencionesPredicate.findByIdSectorAndPolide(tcRetencione.getIdSector(), tcRetencione.getIdPolide()));
	}

	public TcRetencionesRepository getTcRetencionesRepository() {
		return tcRetencionesRepository;
	}

	public void setTcRetencionesRepository(TcRetencionesRepository tcRetencionesRepository) {
		this.tcRetencionesRepository = tcRetencionesRepository;
	}

	@Override
	public void executeReoder(Integer idSector, String tipo, Integer mes, Integer conpol) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_id_sector", idSector).addValue("i_tipo", tipo)
				.addValue("i_mes", mes).addValue("i_conpol", conpol);
		Map<String, Object> out = jdbcCall.execute(in);

	}

	public SimpleJdbcCall getJdbcCall() {
		return jdbcCall;
	}

	public void setJdbcCall(SimpleJdbcCall jdbcCall) {
		this.jdbcCall = jdbcCall;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void deleteRetenciones(TcRetencione tcRetencione) {
		tcRetencione = this.tcRetencionesRepository.findOne(
				TcRetencionesPredicate.findByIdSectorAndPolide(tcRetencione.getIdSector(), tcRetencione.getIdPolide()));
		tcRetencionesRepository.delete(tcRetencione);

	}

}
