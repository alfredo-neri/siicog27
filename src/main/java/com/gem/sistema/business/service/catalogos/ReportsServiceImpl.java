package com.gem.sistema.business.service.catalogos;


import java.io.InputStream;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ReportsDAO;
import com.gem.sistema.business.dto.ReportsDTO;

import net.sf.jasperreports.engine.JasperPrint;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportsServiceImpl.
 */
@Service(value = "reportsService")
public class ReportsServiceImpl implements ReportsService {
	
	
	
	/** The reports DAO. */
	@Autowired
	private ReportsDAO reportsDAO;
	
	
	/**
	 * Gets the reports DAO.
	 *
	 * @return the reports DAO
	 */
	public ReportsDAO getReportsDAO() {
		return reportsDAO;
	}
	
	/**
	 * Sets the reports DAO.
	 *
	 * @param reportsDAO the new reports DAO
	 */
	public void setReportsDAO(ReportsDAO reportsDAO) {
		this.reportsDAO = reportsDAO;
	}
	
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ReportsService#executeReport(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ReportsDTO executeReport(Integer mes, Integer idSector, Integer firmas) {
		return this.reportsDAO.executeReport(mes, idSector, firmas);
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ReportsService#getPath(java.lang.String)
	 */
	@Override
	public String getPath(String cvePath) {
		return this.reportsDAO.getPath(cvePath);
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ReportsService#generatePDF(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public JasperPrint generatePDF(Integer mes, Integer idSector) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ReportsService#createReportPdftoFs(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void createReportPdftoFs(Integer mes, Integer idSector, String pathFile) {
		this.reportsDAO.createReportPdftoFs( mes, idSector, pathFile);
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ReportsService#getInternalReport(java.util.Map, java.lang.String)
	 */
	@Override
	public InputStream getInternalReport(Map<String, Object> params, String jasperReportName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
