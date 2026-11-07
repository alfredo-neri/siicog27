package com.gem.sistema.business.service.catalogos;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ExportInformationDAO;

@Service(value = "exportInformationService")
public class ExportInformationServiceImpl implements ExportInformationService{

	@Autowired
	private ExportInformationDAO exportInformationDAO;
	@Override
	public String exportCuentas(Integer idSector, Integer mes) {
		
		return this.exportInformationDAO.exportCuentas(idSector, mes);
	}

	@Override
	public String exportPaso(Integer idSector, Integer mes) {
		
		return this.exportInformationDAO.exportPaso(idSector, mes);
	}

	@Override
	public String exportPoliza(Integer idSecotr, Integer mes) {
		
		return this.exportInformationDAO.exportPoliza(idSecotr, mes);
	}

	public ExportInformationDAO getExportInformationDAO() {
		return exportInformationDAO;
	}

	public void setExportInformationDAO(ExportInformationDAO exportInformationDAO) {
		this.exportInformationDAO = exportInformationDAO;
	}

	@Override
	public String exportCuentaMes(Integer idSector, Integer mes) {
		
		return this.exportInformationDAO.exportCuentaMes(idSector, mes);
	}

	@Override
	public String zipFile(File[] inputFile, String zipFilePath) {
		return this.exportInformationDAO.zipFile(inputFile, zipFilePath);
	}

	@Override
	public String generaQueryCuentaMes(Integer sector, Integer mes, String ctaCero) {
		return this.exportInformationDAO.generaQueryExportCuenaMes(sector, mes, ctaCero);
	}

	@Override
	public String generaQueryPolizas(Integer sector, Integer mes, Integer anio) {
		return this.exportInformationDAO.generaQueryExportPolizasMes(sector, mes, anio);
	}
}
