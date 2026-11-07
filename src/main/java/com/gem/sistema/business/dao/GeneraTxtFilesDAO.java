package com.gem.sistema.business.dao;

public interface GeneraTxtFilesDAO {

	String generaTxtLDFObjGasto(String query, String fileName);
	
	String generaTxtLDFClasifAdministrativa(String query, String fileName);
	
	String generaTxtLDFServiciosPersonales(String query, String fileName);
	
	String generaTxtLDFClasifFuncional(String query, String fileName);
	
	String generaReporte1e(String fileName, Integer idSector);
	
	String generaReporte08b(String filename, Integer idsector, Integer trimestre, String cveOrganismo);
	
	String generaReporte01d(String filename, Integer idsector, Integer trimestre);
}
