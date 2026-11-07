package com.gem.sistema.business.dao.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CallSpDAO;
import com.gem.sistema.business.dao.ReporteGenericoDAO;
import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.predicates.TcReportesPredicate;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;

@Repository
public class ReporteGenericoDAOImpl implements ReporteGenericoDAO {

	private static final String NAME_PROCEDURE = "SP_EXPORTA_CUENTAS";

	@Autowired
	private CallSpDAO callSpDAO;

	@Autowired
	private ReportesRepository reportesRepository;

	SqlParameterSource parameters;
	Map<String, Object> out;

	@Override
	public String generaFileTXTWithIdSector(Integer idSector, Integer idReporte, String nameFile) {
		TcReporte reporte = reportesRepository.findOne(TcReportesPredicate.findById(idReporte));
		String sSql = String.format(reporte.getQry1(), idSector);

		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY).addValue("i_query", sSql)
				.addValue("i_file_name", nameFile);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String generateFileTxtWithSql(String sql, String nameFile) {
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY).addValue("i_query", sql)
				.addValue("i_file_name", nameFile);
		
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	public CallSpDAO getCallSpDAO() {
		return callSpDAO;
	}

	public void setCallSpDAO(CallSpDAO callSpDAO) {
		this.callSpDAO = callSpDAO;
	}

	public ReportesRepository getReportesRepository() {
		return reportesRepository;
	}

	public void setReportesRepository(ReportesRepository reportesRepository) {
		this.reportesRepository = reportesRepository;
	}

}
