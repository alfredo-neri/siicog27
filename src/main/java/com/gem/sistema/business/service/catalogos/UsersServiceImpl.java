package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.UsersDAO;
import com.gem.sistema.business.domain.TcUsuario;

/**
 * @author Mateo
 *
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDAO usersDAO;
	
	

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}



	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}



	@Override
	public void update(TcUsuario tcUsuario) {
		this.usersDAO.update(tcUsuario);
		
	}

}
