/**
 * 
 */
package com.gem.sistema.web.security.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcMenu;
import com.gem.sistema.business.domain.TcMenuItem;
import com.gem.sistema.business.domain.TcMenuNiveles;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.dto.MenuNivelesDTO;
import com.gem.sistema.business.predicates.TcUsuarioPredicates;
import com.gem.sistema.business.repository.catalogs.TcMenuNivelesRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuServiceImpl.
 *
 * @author gauss
 */
@Repository
public class MenuServiceImpl implements MenuService {

	/** The tc usuario repository. */
	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;
	
	/** The tc menu niveles repository. */
	@Autowired
	private TcMenuNivelesRepository tcMenuNivelesRepository;
	
	
	
	/**
	 * Gets the tc usuario repository.
	 *
	 * @return the tc usuario repository
	 */
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	/**
	 * Sets the tc usuario repository.
	 *
	 * @param tcUsuarioRepository the new tc usuario repository
	 */
	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	/**
	 * Gets the tc menu niveles repository.
	 *
	 * @return the tc menu niveles repository
	 */
	public TcMenuNivelesRepository getTcMenuNivelesRepository() {
		return tcMenuNivelesRepository;
	}

	/**
	 * Sets the tc menu niveles repository.
	 *
	 * @param tcMenuNivelesRepository the new tc menu niveles repository
	 */
	public void setTcMenuNivelesRepository(TcMenuNivelesRepository tcMenuNivelesRepository) {
		this.tcMenuNivelesRepository = tcMenuNivelesRepository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.security.services.MenuService#getMenuModelByPerfil(java.lang.String)
	 */
	@Override
	public MenuModel getMenuModelByUsuario(String user, int idSector) {
		
		final Map<Long, DefaultSubMenu> menus = new  HashMap<Long, DefaultSubMenu>();
		
		System.out.println("Se recibe user" + user);
	 	
		TcUsuario usuario = tcUsuarioRepository.findOne(TcUsuarioPredicates.findByUsuario(user));
		
		
		MenuModel menuModel = new DefaultMenuModel();
		

		for (TcMenuItem menuItem : usuario.getMenuItems()) {
			TcMenu tcMenu =  menuItem.getMenu();
			
			DefaultSubMenu submenu = (DefaultSubMenu) MapUtils.getObject(menus, tcMenu.getId());
			if( submenu== null) {
				submenu = new DefaultSubMenu(tcMenu.getLabel());
				if(StringUtils.isNotEmpty(tcMenu.getStyleclass())){
					submenu.setStyleClass(tcMenu.getStyleclass());
				}
				menus.put( menuItem.getMenu().getId(),  submenu);
			}
			
			DefaultMenuItem item = new DefaultMenuItem(menuItem.getLabel());
			item.setUrl(menuItem.getUrl());
			submenu.addElement(item);
			
		}
		for( Map.Entry<Long, DefaultSubMenu> entry : menus.entrySet()){
			menuModel.addElement(entry.getValue());
		}
		
		
		return createMenu(usuario, idSector);
	}

	/**
	 * Creates the menu.
	 *
	 * @param usuario the usuario
	 * @param idSector the id sector
	 * @return the menu model
	 */
	// CODIGO RECURSIVO INIT
		public MenuModel createMenu(TcUsuario usuario, int idSector) {
			MenuModel model = new DefaultMenuModel();
	        List<TcMenuNiveles> menu = new ArrayList<TcMenuNiveles>();
	        List<MenuNivelesDTO> listaMenuNivelesDTO = new ArrayList<MenuNivelesDTO>();
			for (Object o : tcMenuNivelesRepository.findMenu(idSector, usuario.getId())) {
				Object obj[] = (Object[]) o;
				MenuNivelesDTO dto = new MenuNivelesDTO((Integer) obj[0], (Integer) obj[1], (String) obj[2], (String) obj[3],null,null,null,(Integer) obj[4]);
				listaMenuNivelesDTO.add(dto);
			}
	        
	        for (MenuNivelesDTO menuNiveles : listaMenuNivelesDTO) {
				if(Integer.valueOf(idSector).equals(menuNiveles.getIdSector())){
					TcMenuNiveles tcMenuNiveles = new TcMenuNiveles();
						tcMenuNiveles.setClaveMenu(menuNiveles.getClaveMenu());
						tcMenuNiveles.setIdMenuPadre(menuNiveles.getIdMenuPadre());
						tcMenuNiveles.setLabel(menuNiveles.getLabel());
						tcMenuNiveles.setUrl(menuNiveles.getUrl());
					menu.add(tcMenuNiveles);
				}
			}
	        
	        for (TcMenuNiveles men : menu) {
	            if (men.getIdMenuPadre().intValue() == BigDecimal.ZERO.intValue()) {
	                DefaultSubMenu menuPadre = new DefaultSubMenu(men.getLabel());
	                menuPadre = validarHijos(menu, men.getClaveMenu(), menuPadre);

	                model.addElement(menuPadre);
	            }
	        }
	        return model;
	    }
		
		/**
		 * Validar hijos.
		 *
		 * @param menu the menu
		 * @param claveMenu the clave menu
		 * @param item the item
		 * @return the default sub menu
		 */
		private DefaultSubMenu validarHijos(List<TcMenuNiveles> menu, int claveMenu, DefaultSubMenu item) {

            List<TcMenuNiveles> menuTemp = new ArrayList<TcMenuNiveles>();
            for (TcMenuNiveles men : menu) {
                if (men.getIdMenuPadre().intValue() == claveMenu) {
                    menuTemp.add(men);
                }
            }
            /**
             * Cuando el tamano del arreglo sea mayor a uno debe ser submenu, en otro
             * caso debera ser un SubMenuItem
             */
            if (menuTemp.size() >= 1) {
                for (TcMenuNiveles men : menuTemp) {
                    DefaultSubMenu submenu0 = new DefaultSubMenu(men.getLabel());
                    DefaultMenuItem adminSubItem = null;
                    submenu0 = validarHijos(menu, men.getClaveMenu(), submenu0);
                    if (submenu0.getElements().size() == 0) {
                        adminSubItem = new DefaultMenuItem(men.getLabel());
                        adminSubItem.setUrl(men.getUrl());                                   
                        item.addElement(adminSubItem);
                    } else {
                        submenu0.setStyleClass("dropdown-submenu");
                        item.addElement(submenu0);
                    }
                }
            }

            return item;
        }
}
