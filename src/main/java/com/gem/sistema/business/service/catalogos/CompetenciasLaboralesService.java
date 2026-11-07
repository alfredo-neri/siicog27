package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.CompetenciaLaboralGenericDTO;


public interface CompetenciasLaboralesService {

	List<CompetenciaLaboralGenericDTO> findAll(String table);
	
	Integer countSemestres(String table, Integer semestre);
	
	void deleteRow(String table, Integer idRow);
	
	void save(String table, CompetenciaLaboralGenericDTO competenciaLaboralGenericDTO);
	
	void update(String table, CompetenciaLaboralGenericDTO competenciaLaboralGenericDTO);
	
	void generateJSON(String fileName, String jsonText);
	
}
