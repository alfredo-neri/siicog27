package com.gem.sistema.business.service.reportador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.GeneraTxtFilesDAO;

@Service(value = "generaTxtFilesService")
public class GeneraTxtFilesServiceImpl implements GeneraTxtFilesService {

	@Autowired
	private GeneraTxtFilesDAO txtFilesDAO;

	@Override
	public String generaArtivoTxtLDFObjGasto(String query, String fileName) {
		return txtFilesDAO.generaTxtLDFObjGasto(query, fileName);
	}

	@Override
	public String generaArtivoTxtLDFClasifAdminitrativa(String query, String fileName) {
		return txtFilesDAO.generaTxtLDFClasifAdministrativa(query, fileName);
	}

	@Override
	public String generaArtivoTxtLDFServicosPersonales(String query, String fileName) {
		return txtFilesDAO.generaTxtLDFServiciosPersonales(query, fileName);
	}

	@Override
	public String generaArtivoTxtLDFClasifFuncional(String query, String fileName) {
		return txtFilesDAO.generaTxtLDFClasifFuncional(query, fileName);
	}
	
	@Override
	public String generaReporteMatrizIndicadores1e(String fileName, Integer idSector) {
		return txtFilesDAO.generaReporte1e(fileName, idSector);
	}

	@Override
	public String generaReporteFichaSeguimiento8b(String filename, Integer idsector, Integer trimestre, String cveOrganismo) {
		return txtFilesDAO.generaReporte08b(filename, idsector, trimestre, cveOrganismo);
	}

	@Override
	public String generaReporteFichaDiseno01d(String filename, Integer idsector, Integer trimestre) {
		return txtFilesDAO.generaReporte01d(filename, idsector, trimestre);
	}

	public GeneraTxtFilesDAO getTxtFilesDAO() {
		return txtFilesDAO;
	}

	public void setTxtFilesDAO(GeneraTxtFilesDAO txtFilesDAO) {
		this.txtFilesDAO = txtFilesDAO;
	}

}
