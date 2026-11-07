package com.gem.sistema.business.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.ImportarPolizaDTO;
import com.gem.sistema.business.domain.PolizaBody;
import com.gem.sistema.business.dto.PolizaExcelDTO;
import com.gem.sistema.business.dto.PolizaImportDTO;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ImportarPolizaDTOImpl.
 */
@Repository("importarPolizaDTO")
public class ImportarPolizaDTOImpl implements ImportarPolizaDTO {

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/** The data source. */
	private DataSource dataSource;

	/** The jdbc call. */
	private SimpleJdbcCall jdbcCall;

	/** The jdbc call reports. */
	private SimpleJdbcCall jdbcCallReports;

	/** The jdbc call. */
	private SimpleJdbcCall jdbcCallImport;

	/** The parametros repository. */
	@Autowired
	@Qualifier("parametrosRepository")
	private ParametrosRepository parametrosRepository;

	/**
	 * Sets the data source cobis.
	 *
	 * @param dataSource the new data source cobis
	 */
	/*
	 * Inyecta el datasource de la aplicacion.
	 * 
	 * @param dataSource
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_IMPORTA_POLIZA");
		this.jdbcCallReports = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_EXPORTA_CUENTAS");
		this.jdbcCallImport = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_CARGA_POLIZA");

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
	 * @param jdbcTemplate the jdbcTemplate to set
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
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Gets the parametros repository.
	 *
	 * @return the parametros repository
	 */
	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	/**
	 * Sets the parametros repository.
	 *
	 * @param parametrosRepository the new parametros repository
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ImportarPolizaDTO#execute(com.gem.sistema.
	 * business.dto.PolizaImportDTO, java.lang.Integer, java.lang.String)
	 */
	@Override
	public PolizaImportDTO execute(PolizaImportDTO importDTO, Integer idSector, String idUser) {
		SqlParameterSource in = null;
		Map<String, Object> out = null;
		PolizaImportDTO polizaImportDTO = new PolizaImportDTO();
		if (importDTO.getiBoxes() < 15) {
			in = new MapSqlParameterSource().addValue("i_procesar", importDTO.getiProcesar())
					.addValue("i_id_user", idUser).addValue("i_id_sector", idSector).addValue("i_id_ref", 0)
					.addValue("i_file_name", importDTO.getiFileName()).addValue("i_path_file", importDTO.getiPathFile())
					.addValue("i_momento_c", importDTO.getiBoxes()).addValue("i_mes", importDTO.getiMes())
					.addValue("i_tipo", importDTO.getiTipoL()).addValue("i_numero", importDTO.getiNumpol())
					.addValue("i_fecha", importDTO.getiFechaPol()).addValue("i_repetir", importDTO.getRepetirPoliza());
			out = jdbcCall.execute(in);
			polizaImportDTO.setoPathFile(MapUtils.getString(out, "O_PATH_FILE"));
			polizaImportDTO.setoFleName(MapUtils.getString(out, "O_FLE_NAME"));
		} else {
			in = new MapSqlParameterSource().addValue("i_id_sector", idSector)
					.addValue("i_fecha", importDTO.getiFechaPol())
					.addValue("i_file_name", importDTO.getiFileName())
					.addValue("i_user", idUser);
			out = jdbcCallImport.execute(in);
		}

		polizaImportDTO.setoCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		polizaImportDTO.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return polizaImportDTO;

	}

	/**
	 * Export reports.
	 *
	 * @param importDTO the import DTO
	 * @param tipo      the tipo
	 * @return the poliza import DTO
	 */
	@Override
	public PolizaImportDTO exportReports(PolizaImportDTO importDTO, String tipo) {

		String sSql = "SELECT 'Non. POliza:'|| EN.CLVPOL ||' Status Poliza: ' || EN.STAPOL||EN.STAAFE ||"
				+ "      ' Mes: ' ||EN.MESPOL || ' Fecha Poliza: ' || EN.FECPOL FROM POLIEN EN "
				+ " WHERE EN.MESPOL   = " + importDTO.getiMes() + "   AND EN.TIPPOL   = '" + importDTO.getiTipoL()
				+ " '" + "   AND EN.CONPOL   = " + importDTO.getiNumpol() + "   AND EN.IDSECTOR = "
				+ importDTO.getiIdSector();
		List<String> head = jdbcTemplate.query(sSql, new HeadQuery());

		String sSqlBody = " SELECT TRIM(TO_CHAR(RENPOL, '999999999')) ||'  ' || CUENTA || '  ' || SCTA ||'  ' || SSCTA ||'  ' || SSSCTA || '  ' || SSSSCTA || "
				+ "  CONCEP || '  ' || REFPOL || ' '|| TRIM(TO_CHAR(CANPOL, '999,999,999,990.99'))  || '  ' || TRIM (TO_CHAR(CANPOLH, '999,999,999,990.99')) "
				+ "  FROM POLIDE " + " WHERE MESPOL  = " + importDTO.getiMes() + "   AND CONPOL   = "
				+ importDTO.getiNumpol() + "   AND TIPPOL   = '" + importDTO.getiTipoL() + " '" + "   AND IDSECTOR = "
				+ importDTO.getiIdSector();

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_header", head.get(0))
				.addValue("i_query", sSqlBody).addValue("i_file_name", "polizas." + tipo);

		Map<String, Object> out = jdbcCallReports.execute(in);

		PolizaImportDTO polizaImportDTO = new PolizaImportDTO();
		polizaImportDTO.setoFleName(MapUtils.getString(out, "O_FULL_FILE_PATH"));
		polizaImportDTO.setoCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		polizaImportDTO.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return polizaImportDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ImportarPolizaDTO#generateExcel(com.gem.sistema.
	 * business.dto.PolizaImportDTO)
	 */
	@Override
	public PolizaImportDTO generateExcel(PolizaImportDTO importDTO) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Polizas");
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		List<PolizaExcelDTO> lisHead = this.generateHead(importDTO);
		List<PolizaBody> lisBody = generateBody(importDTO);
		Object[] bodyArray = new Object[lisBody.size()];
		int contador = 3;
		data.put("1",
				new Object[] { "No. Póliza;", lisHead.get(0).getNoPoliza(), "Status Pólilza:",
						lisHead.get(0).getStatus(), "Mes:", lisHead.get(0).getMes(), "Fecha Poliza:",
						lisHead.get(0).getFecha(), "Capturó:", lisHead.get(0).getCapturo() });

		data.put("2", new Object[] { "Renglon", "Cuenta", "Scta", "Sscta", "Ssscta", "Sssscta", "Ref", "DN", "Concepto",
				"Cargo", "Abono", "" });

		for (int i = 0; i < lisBody.size(); i++) {

			data.put("" + contador,
					new Object[] { lisBody.get(i).getRenglon(), lisBody.get(i).getCuenta(), lisBody.get(i).getScta(),
							lisBody.get(i).getSscta(), lisBody.get(i).getSsscta(), lisBody.get(i).getSssscta(),
							lisBody.get(i).getReferencia(), lisBody.get(i).getDn(), lisBody.get(i).getConcepto(),
							lisBody.get(i).getCargo(), lisBody.get(i).getAbonos() });
			contador++;
		}

		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);

			}
		}
		PolizaImportDTO dto = new PolizaImportDTO();
		String fileName = this.getPath("TMP_POLIZAS") + File.separator + "polizas.xls";
		try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			dto.setoFleName(fileName);
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ImportarPolizaDTO#generateHead(com.gem.sistema.
	 * business.dto.PolizaImportDTO)
	 */
	@Override
	public List<PolizaExcelDTO> generateHead(PolizaImportDTO importDTO) {
		String sHeader = "SELECT EN.TIPPOL|| '' || EN.CONPOL POL,  EN.STAPOL||EN.STAAFE STA,  EN.MESPOL MES, "
				+ " EN.FECPOL, " + " EN.CAPPOL  FROM POLIEN EN  WHERE EN.MESPOL   = ? "
				+ "   AND EN.TIPPOL   = ?    AND EN.CONPOL   = ?    AND EN.IDSECTOR = ? ";

		List<PolizaExcelDTO> listHeader = this.jdbcTemplate.query(sHeader, new HeadExcel(), new Object[] {
				importDTO.getiMes(), importDTO.getiTipoL(), importDTO.getiNumpol(), importDTO.getiIdSector() });

		return listHeader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ImportarPolizaDTO#generateBody(com.gem.sistema.
	 * business.dto.PolizaImportDTO)
	 */
	@Override
	public List<PolizaBody> generateBody(PolizaImportDTO importDTO) {
		String sBody = "SELECT DE.RENPOL, " + " DE.CUENTA, " + " DE.SCTA," + " DE.SSCTA," + " DE.SSSCTA,"
				+ " DE.SSSSCTA," + " DE.REFPOL," + " DE.DN," + " DE.CONCEP," + " DE.CANPOL," + " DE.CANPOLH"
				+ " FROM POLIDE  DE" + " WHERE DE.MESPOL   = ?" + "   AND DE.TIPPOL   = ?" + "   AND DE.CONPOL   = ?"
				+ "   AND DE.IDSECTOR = ?";
		List<PolizaBody> listBody = this.jdbcTemplate.query(sBody, new BodyExcel(), new Object[] { importDTO.getiMes(),
				importDTO.getiTipoL(), importDTO.getiNumpol(), importDTO.getiIdSector() });
		return listBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ImportarPolizaDTO#getPath(java.lang.String)
	 */
	@Override
	public String getPath(String cvePath) {
		return parametrosRepository.getValorByCv(cvePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ImportarPolizaDTO#getMesActivo(java.lang.
	 * Integer)
	 */
	@Override
	public List<String> getMesActivo(Integer idSector) {
		String sSql = "SELECT TC.MES FROM TC_MESES TC, CONCTB CO WHERE TC.MES <= LPAD(CO.MESEMP, 2, '0') "
				+ " AND CO.IDSECTOR = ? ";
		return this.jdbcTemplate.query(sSql, new ListMes(), new Object[] { idSector });
	}

}

class HeadQuery implements RowMapper<String> {
	public String mapRow(ResultSet rs, int i) throws SQLException {

		String head = rs.getString(1);

		return head;
	}
}

class BodyQuery implements RowMapper<String> {
	public String mapRow(ResultSet rs, int i) throws SQLException {

		String body = rs.getString(1);

		return body;
	}
}

class HeadExcel implements RowMapper<PolizaExcelDTO> {
	public PolizaExcelDTO mapRow(ResultSet rs, int i) throws SQLException {

		PolizaExcelDTO head = new PolizaExcelDTO();
		head.setNoPoliza(rs.getString(1));
		head.setStatus(rs.getString(2));
		head.setMes(rs.getInt(3));
		head.setFecha(rs.getDate(4));
		head.setCapturo(rs.getString(5));
		return head;
	}
}

class BodyExcel implements RowMapper<PolizaBody> {
	public PolizaBody mapRow(ResultSet rs, int i) throws SQLException {

		PolizaBody body = new PolizaBody();// rs.getString(1);
		body.setRenglon(rs.getBigDecimal(1));
		body.setCuenta(rs.getString(2));
		body.setScta(rs.getString(3));
		body.setSscta(rs.getString(4));
		body.setSsscta(rs.getString(5));
		body.setSssscta(rs.getString(6));
		body.setReferencia(rs.getBigDecimal(7));
		body.setDn(rs.getString(8));
		body.setConcepto(rs.getString(9));
		body.setCargo(rs.getBigDecimal(10));
		body.setAbonos(rs.getBigDecimal(11));
		return body;
	}
}

class ListMes implements RowMapper<String> {
	public String mapRow(ResultSet rs, int i) throws SQLException {
		return rs.getString(1);

	}
}