package com.gem.sistema.business.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.UsersDAO;
import com.gem.sistema.business.domain.TcUsuario;

/**
 * @author Mateo
 *
 */
@Repository
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.UsersDAO#update(com.gem.sistema.business.
	 * domain.TcUsuario)
	 */
	@Override
	public void update(TcUsuario tcUsuario) {
		String sSql = "update tc_usuario set nombe = ?, contrasenia = ?, id_localidad = ?, id_entidad_admini = ?, where usuario = ?";
		jdbcTemplate.update(sSql, tcUsuario.getNombre(), tcUsuario.getContrasenia(), tcUsuario.getIdLocalidad(),
				tcUsuario.getIdEntidadAdmini(), tcUsuario.getUsuario());

	}

}
