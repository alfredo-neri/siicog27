package com.gem.sistema.business.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CargaCuentas58DAO;
import com.gem.sistema.business.service.callsp.ExecutePlService;

@Repository
public class CargaCuentas58DAOImpl implements CargaCuentas58DAO {

	private static final String SP_NAME = "SP_CARGA_CUENTAS_58";
	private static final String SP_NAME_LOAD_SALINI = "SP_ACTUALIZA_SALINI";

	@Autowired
	private ExecutePlService execuPlsq;

	@Override
	public Map<String, Object> cargaCueta(String fullPathName, String userName) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_full_path_name", fullPathName);
		parameter.addValue("i_user", userName);

		return this.execuPlsq.callProcedure(SP_NAME, parameter);

	}

	@Override
	public Map<String, Object> cargaCuetaTmp(String fullPathName) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_file_name", fullPathName);

		return this.execuPlsq.callProcedure(SP_NAME_LOAD_SALINI, parameter);
	}


	public ExecutePlService getExecuPlsq() {
		return execuPlsq;
	}

	public void setExecuPlsq(ExecutePlService execuPlsq) {
		this.execuPlsq = execuPlsq;
	}

}
