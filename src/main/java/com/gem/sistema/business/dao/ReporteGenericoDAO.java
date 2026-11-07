package com.gem.sistema.business.dao;

public interface ReporteGenericoDAO {

	public String generaFileTXTWithIdSector(Integer idSector, Integer idReporte, String nameFile);
	
	public String generateFileTxtWithSql(String sql, String nameFile);
}
