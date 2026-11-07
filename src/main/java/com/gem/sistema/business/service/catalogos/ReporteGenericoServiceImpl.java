package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ReporteGenericoDAO;

@Service(value = "reporteGenericoService")
public class ReporteGenericoServiceImpl implements ReporteGenericoService {

	@Autowired
	private ReporteGenericoDAO reporteGenericoDao;

	@Override
	public String getFileTxtWithIdSector(Integer idReporte, Integer idSector, String nameFile) {
		return reporteGenericoDao.generaFileTXTWithIdSector(idSector, idReporte, nameFile);
	}

	@Override
	public String getFileTxtWithSql(String sql, String nameFile) {
		return reporteGenericoDao.generateFileTxtWithSql(sql, nameFile);
	}

	public ReporteGenericoDAO getReporteGenericoDao() {
		return reporteGenericoDao;
	}

	public void setReporteGenericoDao(ReporteGenericoDAO reporteGenericoDao) {
		this.reporteGenericoDao = reporteGenericoDao;
	}

}
