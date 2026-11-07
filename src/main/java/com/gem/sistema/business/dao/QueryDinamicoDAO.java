package com.gem.sistema.business.dao;

public interface QueryDinamicoDAO {

	String getData(Integer mes, String fileName, Integer programa);
	
	String generaTxtFichaDisenio(Integer sector, String fileName);
}
