/**
 * 
 */
package com.gem.sistema.web.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.MenuModel;

import com.gem.sistema.web.security.services.MenuService;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuMB.
 *
 * @author gauss
 */
@ManagedBean
@SessionScoped
public class MenuMB extends AbstractMB {

	/** The menu model. */
	private MenuModel menuModel;
	
	//@Autowired
	/** The menu service. */
	//@Qualifier("menuServiceImpl")
    @ManagedProperty(value="#{menuServiceImpl}")
	private MenuService menuService;
	

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){

			this.menuModel = menuService.getMenuModelByUsuario(this.getUserDetails().getUsername(), this.getUserDetails().getIdSector());

	}

	/**
	 * Gets the menu model.
	 *
	 * @return the menuModel
	 */
	public MenuModel getMenuModel() {
		if (this.menuModel == null) {
			this.menuModel = menuService.getMenuModelByUsuario(this.getUserDetails().getUsername(), this.getUserDetails().getIdSector());
		}
		return this.menuModel;
	}

	/**
	 * Sets the menu model.
	 *
	 * @param menuModel the menuModel to set
	 */
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	/**
	 * Gets the menu service.
	 *
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * Sets the menu service.
	 *
	 * @param menuService the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	
	
	
}
