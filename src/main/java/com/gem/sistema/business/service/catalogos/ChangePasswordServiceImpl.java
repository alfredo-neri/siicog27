package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ChangePasswordDAO;
import com.gem.sistema.business.domain.TcModulosOperacione;
import com.gem.sistema.business.domain.TcParametro;

@Service(value = "changePasswordService")
public class ChangePasswordServiceImpl implements ChangePasswordService {

	@Autowired
	private ChangePasswordDAO changePasswordDAO;

	@Override
	public void save(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione) {
		this.changePasswordDAO.save(tcParametro, tcModulosOperacione);

	}

	@Override
	public void changePassword(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione) {
		this.changePasswordDAO.changePassword(tcParametro, tcModulosOperacione);

	}

	@Override
	public boolean validatePassword(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione) {
		
		return this.changePasswordDAO.validatePassword(tcParametro, tcModulosOperacione);
	}

	public ChangePasswordDAO getChangePasswordDAO() {
		return changePasswordDAO;
	}

	public void setChangePasswordDAO(ChangePasswordDAO changePasswordDAO) {
		this.changePasswordDAO = changePasswordDAO;
	}

	@Override
	public List<TcModulosOperacione> findBySector(Integer idSector) {
		return this.changePasswordDAO.findBySector(idSector);
	}

	@Override
	public boolean passwordisTrue(String password, String keyPass) {
		
		return this.changePasswordDAO.passwordisTrue(password, keyPass);
	}

	@Override
	public String getValueParameter(String key) {
		
		return this.changePasswordDAO.getValueParameter(key);
	}

}
