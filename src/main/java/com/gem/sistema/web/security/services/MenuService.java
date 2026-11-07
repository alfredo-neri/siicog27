/**
 * 
 */
package com.gem.sistema.web.security.services;

import org.primefaces.model.menu.MenuModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface MenuService.
 *
 * @author gauss
 */
public interface MenuService {
	
	 /**
 	 * Recupera el panel de menu, en base al usuario.
 	 *
 	 * @param usuario the usuario
 	 * @param idSector the id sector
 	 * @return MenuModel
 	 */
    MenuModel getMenuModelByUsuario(String usuario, int idSector);

}
