package com.gem.sistema.business.service.catalogos;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ReporteRF001027DAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF001027ServiceImpl.
 *
 * @author buser
 */
@Service(value = "reporteRF001027Service")
public class ReporteRF001027ServiceImpl implements ReporteRF001027Service{
	
	
	/** The reporte RF 001027 DAO. */
	@Autowired
	@Qualifier("reporteRF001027DAO")
	private ReporteRF001027DAO reporteRF001027DAO;

	/**
	 * Gets the reporte RF 001027 DAO.
	 *
	 * @return the reporte RF 001027 DAO
	 */
	public ReporteRF001027DAO getReporteRF001027DAO() {
		return reporteRF001027DAO;
	}

	/**
	 * Sets the reporte RF 001027 DAO.
	 *
	 * @param reporteRF001027DAO the new reporte RF 001027 DAO
	 */
	public void setReporteRF001027DAO(ReporteRF001027DAO reporteRF001027DAO) {
		this.reporteRF001027DAO = reporteRF001027DAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ReporteRF001027Service#getParameters(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> getParameters(Integer mes) {
		return this.reporteRF001027DAO.getParameters(mes);
	}

}
