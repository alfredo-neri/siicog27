package com.gem.sistema.business.service.catalogos;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.CargaCuentas58DAO;

@Service(value = "cargaCuentas58Service")
public class CargaCuentas58ServiceImpl implements CargaCuentas58Service {

	@Autowired
	private CargaCuentas58DAO cargaCuentas58DAO;

	@Override
	public Map<String, Object> cargaCueta(String fullPathName, String userName) {
		return this.cargaCuentas58DAO.cargaCueta(fullPathName, userName);
	}

	@Override
	public Map<String, Object> cargaCuetaTmp(String fullPathName) {
		return this.cargaCuentas58DAO.cargaCuetaTmp(fullPathName);
	}

	public CargaCuentas58DAO getCargaCuentas58DAO() {
		return cargaCuentas58DAO;
	}

	public void setCargaCuentas58DAO(CargaCuentas58DAO cargaCuentas58DAO) {
		this.cargaCuentas58DAO = cargaCuentas58DAO;
	}

}
