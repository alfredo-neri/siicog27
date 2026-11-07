package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ReporteRF009114DAO;
import com.gem.sistema.business.dto.ReportsParamDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class ReporteRF009114ServiceImpl.
 *
 * @author buser
 */
@Service(value = "reporteRF009114Service")
public class ReporteRF009114ServiceImpl implements ReporteRF009114Service {

	/** The reporte DAO. */
	@Autowired
	private ReporteRF009114DAO reporteDAO;

	/**
	 * Gets the reporte DAO.
	 *
	 * @return the reporte DAO
	 */
	public ReporteRF009114DAO getReporteDAO() {
		return reporteDAO;
	}

	/**
	 * Sets the reporte DAO.
	 *
	 * @param reporteDAO the new reporte DAO
	 */
	public void setReporteDAO(ReporteRF009114DAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.ReporteRF009114Service#
	 * executeProcedure(com.gem.sistema.business.dto.ReportsParamDTO)
	 */
	@Override
	public String executeQuery(Integer mes, Integer idSecor, Integer niveles) {
		return this.reporteDAO.executeQuery(mes, idSecor, niveles);
	}

	@Override
	public ReportsParamDTO executeProcedure(ReportsParamDTO reportDTO, Integer tipo) {
		return this.reporteDAO.executeProcedure(reportDTO, tipo);
	}

}
