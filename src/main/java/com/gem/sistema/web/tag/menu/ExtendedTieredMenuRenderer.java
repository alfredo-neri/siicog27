package com.gem.sistema.web.tag.menu;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.tieredmenu.TieredMenu;
import org.primefaces.component.tieredmenu.TieredMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.WidgetBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtendedTieredMenuRenderer.
 */
public class ExtendedTieredMenuRenderer extends ExtendedBaseMenuRenderer {
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.tag.menu.ExtendedBaseMenuRenderer#encodeScript(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu)
	 */
	@Override
	protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException{
        TieredMenu menu = (TieredMenu) abstractMenu;
		String clientId = menu.getClientId(context);
        
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady("TieredMenu", menu.resolveWidgetVar(), clientId)
            .attr("autoDisplay", menu.isAutoDisplay())                
            .attr("toggleEvent", menu.getToggleEvent(), null);
        
        if(menu.isOverlay()) {
            encodeOverlayConfig(context, menu, wb);
        }

        wb.finish();
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.tag.menu.ExtendedBaseMenuRenderer#encodeMarkup(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu)
	 */
	@Override
	protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        TieredMenu menu = (TieredMenu) abstractMenu;
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();
        String defaultStyleClass = menu.isOverlay() ? TieredMenu.DYNAMIC_CONTAINER_CLASS : TieredMenu.STATIC_CONTAINER_CLASS;
        styleClass = styleClass == null ? defaultStyleClass : defaultStyleClass + " " + styleClass;

        encodeMenu(context, menu, style, styleClass, "menu");
	}
	
    /**
     * Encode menu.
     *
     * @param context the context
     * @param menu the menu
     * @param style the style
     * @param styleClass the style class
     * @param role the role
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeMenu(FacesContext context, AbstractMenu menu, String style, String styleClass, String role) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIComponent optionsFacet = menu.getFacet("options");

      // StringBuilder headMenu = bootstrapHeader(); //uncomment this line if you want the component to render the full bootstrap menu       		
       // writer.write(headMenu.toString());          
        encodeKeyboardTarget(context, menu);

		//writer.startElement("ul", null);
        //writer.writeAttribute("class", "nav navbar-nav", null);

        if(menu.getElementsCount() > 0) {
            encodeElements(context, menu, menu.getElements());
        }
        
        if(optionsFacet != null) {
            writer.startElement("li", null);
            writer.writeAttribute("class","", null);
            optionsFacet.encodeAll(context);
            writer.endElement("li");
        }		
		// writer.endElement("ul");		 
		// StringBuilder headMenuEnd = bootstrapHeaderEnd();   //uncomment this line if you want the component to render the full bootstrap menu 
		// writer.write(headMenuEnd.toString());

    }
	
	/**
	 * Bootstrap header end.
	 *
	 * @return the string builder
	 */
	private StringBuilder bootstrapHeaderEnd() {
		StringBuilder headMenuEnd = new StringBuilder();
		 headMenuEnd.append("</div>")
 					.append("<div class='ui-grid-col-1'>")
 					.append("<ul class='nav navbar-nav'>")
 					.append("<li class='btn-danger'>")
 					.append("<a href='/GEM/logout' style='color: white'><span class='glyphicon glyphicon-share' aria-hidden='true'></span> Salir</a>")
 					.append("</li>")
 					.append("</ul>")
 					.append("</div>")
 					.append("</div>")
 					.append("</div>")
 					.append("</div>")      
 					.append("</nav>")
 					.append("</div>");
		return headMenuEnd;
	}
	
	/**
	 * Bootstrap header.
	 *
	 * @return the string builder
	 */
	private StringBuilder bootstrapHeader() {
		StringBuilder headMenu = new StringBuilder();
        headMenu.append("<div id='navv' data-spy='affix' data-offset-top='154'>")
        		.append("<nav class='navbar navbar-default navbar-static'>")
        		.append("<div class='container-fluid'>")
        		.append("<div class='navbar-header'>")
        		.append("<button type='button' class='navbar-toggle collapsed'	data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'>")
        		.append("<span class='sr-only'>Toggle navigation</span> <span class='icon-bar'></span> <span class='icon-bar'></span> <span	class='icon-bar'></span>")
        		.append("</button>")
        		.append("</div>")
        		.append("<div id='navbar' class='navbar-collapse collapse'>")
        		.append("<div class='ui-grid ui-grid-responsive'>")
        		.append("<div class='ui-grid-row'>")      
        		.append("<div class='ui-grid-col-12'>");
		return headMenu;
	}
	
    /**
     * Encode elements.
     *
     * @param context the context
     * @param menu the menu
     * @param elements the elements
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        for(MenuElement element : elements) {
            if(element.isRendered()) {
                if(element instanceof MenuItem) {
                    MenuItem menuItem = (MenuItem) element;
                    String containerStyle = menuItem.getContainerStyle();
                    String containerStyleClass = menuItem.getContainerStyleClass();                   
                    if(containerStyleClass==null){containerStyleClass="";}
                    writer.startElement("li", null);
                    writer.writeAttribute("class", "dropdown" +" "+ containerStyleClass, null);
                    if(containerStyle != null) {
                        writer.writeAttribute("style", containerStyle, null);
                    }
                    encodeMenuItem(context, menu, menuItem);
                    writer.endElement("li");
                }
                else if(element instanceof Submenu) {
                    Submenu submenu = (Submenu) element;
                    String style = submenu.getStyle();
                    String styleClass = submenu.getStyleClass();
                    if(styleClass==null){styleClass="";}
                    writer.startElement("li", null);
                    if(shouldRenderId(submenu)) {
                        writer.writeAttribute("id", submenu.getClientId(), null);
                    }
                    writer.writeAttribute("class", "dropdown"+" "+styleClass, null);
                    if(style != null) {
                        writer.writeAttribute("style", style, null);
                    }
                    encodeSubmenu(context, menu, submenu);
                    writer.endElement("li");
                } 
                else if(element instanceof Separator) {
                    encodeSeparator(context, (Separator) element);
                }
            }
        }
    }
	
    /**
     * Encode submenu.
     *
     * @param context the context
     * @param menu the menu
     * @param submenu the submenu
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
        String icon = submenu.getIcon();
        String label = submenu.getLabel();

        //title
        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("class", "dropdown-toggle", null);
        writer.writeAttribute("data-toggle", "dropdown", null);
        writer.writeAttribute("role", "button", null);
        writer.writeAttribute("aria-haspopup", "true", null);

        if(icon != null) {
            writer.startElement("span", null);
            writer.writeAttribute("class", " " + icon, null);
            writer.endElement("span");
        }

        if(label != null) {
            writer.startElement("span", null);
            writer.writeText(submenu.getLabel(), "value");
            writer.endElement("span");
        }
        
        encodeSubmenuIcon(context, submenu);

        writer.endElement("a");

        //submenus and menuitems
        if(submenu.getElementsCount() > 0) {
            writer.startElement("ul", null);
            writer.writeAttribute("class", "dropdown-menu", null);
			encodeElements(context, menu, submenu.getElements());
			writer.endElement("ul");
        }
	}
	
    /**
     * Encode submenu icon.
     *
     * @param context the context
     * @param submenu the submenu
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeSubmenuIcon(FacesContext context, Submenu submenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.startElement("span", null);
        writer.writeAttribute("class", Menu.SUBMENU_RIGHT_ICON_CLASS, null);
        writer.endElement("span");
    }
}
