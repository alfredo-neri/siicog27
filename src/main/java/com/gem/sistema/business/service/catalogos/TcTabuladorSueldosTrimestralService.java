package com.gem.sistema.business.service.catalogos;

import java.io.InputStream;
import java.util.List;

import com.gem.sistema.business.domain.TcTabularSueldosTrimestral;


public interface TcTabuladorSueldosTrimestralService {
	
	
	void saveFile(String fileName, InputStream input );
	
	List<TcTabularSueldosTrimestral> findAll();
	
	void saveObj(TcTabularSueldosTrimestral tabulador);
	
	void delteAll();
	
	void update(TcTabularSueldosTrimestral tabulador);
	
	void deleteById(Long id);

}
