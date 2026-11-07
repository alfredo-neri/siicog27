package com.gem.sistema.web.tag.menu;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.tieredmenu.TieredMenuRenderer;
import org.primefaces.model.menu.BaseMenuModel;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.WidgetBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtendedMenuRenderer.
 */
public class ExtendedMenuRenderer extends ExtendedTieredMenuRenderer {
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.tag.menu.ExtendedTieredMenuRenderer#encodeScript(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu)
	 */
	@Override
	protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException{
        Menubar menubar = (Menubar) abstractMenu;
		String clientId = menubar.getClientId(context);
        
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("Menubar", menubar.resolveWidgetVar(), clientId)
            .attr("autoDisplay", menubar.isAutoDisplay())
            .attr("toggleEvent", menubar.getToggleEvent(), null);

        wb.finish();
	}

    /* (non-Javadoc)
     * @see com.gem.sistema.web.tag.menu.ExtendedTieredMenuRenderer#encodeMarkup(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu)
     */
    @Override
	protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        Menubar menubar = (Menubar) abstractMenu;
        String style = menubar.getStyle();
        String styleClass = menubar.getStyleClass();
        styleClass = styleClass == null ? Menubar.CONTAINER_CLASS : Menubar.CONTAINER_CLASS + " " + styleClass;
       
        //encodeMenu(context, menubar, style, styleClass, "");
        encodeMenu(context, menubar, "", "", "");
	}
    
    /* (non-Javadoc)
     * @see com.gem.sistema.web.tag.menu.ExtendedTieredMenuRenderer#encodeSubmenuIcon(javax.faces.context.FacesContext, org.primefaces.model.menu.Submenu)
     */
    @Override
    protected void encodeSubmenuIcon(FacesContext context, Submenu submenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Object parent = submenu.getParent();
        String icon = null;
        
        if(parent == null)
            icon = (submenu.getId().indexOf(BaseMenuModel.ID_SEPARATOR) == -1) ? Menu.SUBMENU_DOWN_ICON_CLASS : Menu.SUBMENU_RIGHT_ICON_CLASS;
        else
            icon = (parent instanceof Menubar) ? Menu.SUBMENU_DOWN_ICON_CLASS : Menu.SUBMENU_RIGHT_ICON_CLASS;
        	
        writer.startElement("span", null);
        writer.writeAttribute("class", "caret", null);
        writer.endElement("span");
   
    }
}
