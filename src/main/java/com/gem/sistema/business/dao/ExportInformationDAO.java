package com.gem.sistema.business.dao;

import java.io.File;

public interface ExportInformationDAO {

	String exportCuentas(Integer idSector, Integer mes);

	String exportPaso(Integer idSector, Integer mes);

	String exportPoliza(Integer idSecotr, Integer mes);
	
	String exportCuentaMes(Integer idSector, Integer mes);

	String generaQueryExportCuenaMes(Integer idSector, Integer mes, String ctaCero);

	String generaQueryExportPolizasMes(Integer idSector, Integer mes, Integer anio);
	
	String zipFile(File[] inputFile, String zipFilePath);

}
