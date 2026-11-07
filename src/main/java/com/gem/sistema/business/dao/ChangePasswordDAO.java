package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.domain.TcModulosOperacione;
import com.gem.sistema.business.domain.TcParametro;

public interface ChangePasswordDAO {
	void save(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione);

	void changePassword(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione);

	boolean validatePassword(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione);

	List<TcModulosOperacione> findBySector(Integer idSector);

	boolean passwordisTrue(String password, String keyPass);
	
	String getValueParameter(String key);

}
