package com.gem.sistema.business.service.catalogos;

public interface ReporteGenericoService {

	public String getFileTxtWithIdSector(Integer idReporte, Integer idSector, String nameFile);
	
	public String getFileTxtWithSql(String sql, String nameFile);
}
