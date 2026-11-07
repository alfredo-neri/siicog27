package com.gem.sistema.web.tag.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseId;
import org.primefaces.behavior.confirm.ConfirmBehavior;
import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.menu.OverlayMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuGroup;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Separator;
import org.primefaces.renderkit.OutcomeTargetRenderer;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.SharedStringBuilder;
import org.primefaces.util.WidgetBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtendedBaseMenuRenderer.
 */
public abstract class ExtendedBaseMenuRenderer extends OutcomeTargetRenderer {
    
    /** The Constant SB_BUILD_NON_AJAX_REQUEST. */
    private static final String SB_BUILD_NON_AJAX_REQUEST = ExtendedBaseMenuRenderer.class.getName() + "#buildNonAjaxRequest";
    
    /** The Constant SEPARATOR. */
    public final static String SEPARATOR = "_";
    
    /* (non-Javadoc)
     * @see javax.faces.render.Renderer#decode(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
     */
    @Override
	public void decode(FacesContext context, UIComponent component) {
		AbstractMenu menu = (AbstractMenu) component;
        String clientId = menu.getClientId(context);
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        
        if(params.containsKey(clientId)) {
            String menuid = params.get(clientId + "_menuid");
            MenuItem menuitem = findMenuitem(menu.getElements(), menuid);
            MenuActionEvent event = new MenuActionEvent(menu, menuitem);

            if(menuitem.isImmediate())
                event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
            else
                event.setPhaseId(PhaseId.INVOKE_APPLICATION);
            
            menu.queueEvent(event);
        }
	}
    
    /**
     * Find menuitem.
     *
     * @param elements the elements
     * @param id the id
     * @return the menu item
     */
    protected MenuItem findMenuitem(List<MenuElement> elements, String id) {        
        if(elements == null || elements.isEmpty()) {
            return null;
        }
        else {
            String[] paths = id.split(SEPARATOR);
            
            if(paths.length == 0)
                return null;
            
            int childIndex = Integer.parseInt(paths[0]);
            if(childIndex >= elements.size()) 
                return null;
            
            MenuElement childElement = elements.get(childIndex);

            if(paths.length == 1) {
                return (MenuItem) childElement;
            }
            else {
                String relativeIndex = id.substring(id.indexOf(SEPARATOR) + 1);

                return findMenuitem(((MenuGroup) childElement).getElements(), relativeIndex);
            }
        }
    }
    
    /* (non-Javadoc)
     * @see javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
     */
    @Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		AbstractMenu menu = (AbstractMenu) component;
        MenuModel model = menu.getModel();
        if(model != null && menu.getElementsCount() > 0) {
            model.generateUniqueIds();
        }

		encodeMarkup(context, menu);
		encodeScript(context, menu);
	}

    /**
     * Encode markup.
     *
     * @param context the context
     * @param abstractMenu the abstract menu
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected abstract void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException;

    /**
     * Encode script.
     *
     * @param context the context
     * @param abstractMenu the abstract menu
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected abstract void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException;
    
    /**
     * Gets the link style class.
     *
     * @param menuItem the menu item
     * @return the link style class
     */
    protected String getLinkStyleClass(MenuItem menuItem) {
        String styleClass = menuItem.getStyleClass();
        
        return (styleClass == null) ? AbstractMenu.MENUITEM_LINK_CLASS: AbstractMenu.MENUITEM_LINK_CLASS + " " + styleClass;
    }

    /**
     * Encode menu item.
     *
     * @param context the context
     * @param menu the menu
     * @param menuitem the menuitem
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();

		if(menuitem.shouldRenderChildren()) {
			renderChildren(context, (UIComponent) menuitem);
		}
        else {
            boolean disabled = menuitem.isDisabled();
            String style = menuitem.getStyle();
            
            writer.startElement("a", null);
            if(shouldRenderId(menuitem)) {
                writer.writeAttribute("id", menuitem.getClientId(), null);
            }
            if(title != null) {
                writer.writeAttribute("title", title, null);
            }
            
            String styleClass = this.getLinkStyleClass(menuitem);
            if(disabled) {
                styleClass = styleClass + " ";
            }
            
            writer.writeAttribute("class", "", null);
            
            if(style != null) {
                writer.writeAttribute("style", style, null);
            }
                  
            if(disabled) {
                writer.writeAttribute("href", "#", null);
                writer.writeAttribute("onclick", "return false;", null);
            }
            else {
                setConfirmationScript(context, menuitem);
                String onclick = menuitem.getOnclick();
                
                //GET
                if(menuitem.getUrl() != null || menuitem.getOutcome() != null) {                
                    String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                    writer.writeAttribute("href", targetURL, null);

                    if(menuitem.getTarget() != null) {
                        writer.writeAttribute("target", menuitem.getTarget(), null);
                    }
                }
                //POST
                else {
                    writer.writeAttribute("href", "#", null);

                    UIComponent form = ComponentUtils.findParentForm(context, menu);
                    if(form == null) {
                        throw new FacesException("MenuItem must be inside a form element");
                    }

                    String command;
                    if(menuitem.isDynamic()) {
                        String menuClientId = menu.getClientId(context);
                        Map<String,List<String>> params = menuitem.getParams();
                        if(params == null) {
                            params = new LinkedHashMap<String, List<String>>();
                        }
                        List<String> idParams = new ArrayList<String>();
                        idParams.add(menuitem.getId());
                        params.put(menuClientId + "_menuid", idParams);

                        command = menuitem.isAjax() ? buildAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                    } 
                    else {
                        command = menuitem.isAjax() ? buildAjaxRequest(context, (AjaxSource) menuitem, form) : buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
                    }

                    onclick = (onclick == null) ? command : onclick + ";" + command;
                }
                
                if(onclick != null) {
                    if(menuitem.requiresConfirmation()) {
                        writer.writeAttribute("data-pfconfirmcommand", onclick, null);
                        writer.writeAttribute("onclick", menuitem.getConfirmationScript(), "onclick");
                    }
                    else {
                        writer.writeAttribute("onclick", onclick, null);
                    }
                }
            }

            encodeMenuItemContent(context, menu, menuitem);

            writer.endElement("a");
		}
	}
    
    /**
     * Sets the confirmation script.
     *
     * @param context the context
     * @param item the item
     */
    protected void setConfirmationScript(FacesContext context, MenuItem item) {
        if(item instanceof ClientBehaviorHolder) {
            Map<String,List<ClientBehavior>> behaviors = ((ClientBehaviorHolder) item).getClientBehaviors();
            List<ClientBehavior> clickBehaviors = (behaviors == null) ? null : behaviors.get("click");
            
            if(clickBehaviors != null && !clickBehaviors.isEmpty()) {
                for(int i = 0; i < clickBehaviors.size(); i++) {
                    ClientBehavior clientBehavior = clickBehaviors.get(i);
                    if(clientBehavior instanceof ConfirmBehavior) {
                        ClientBehaviorContext cbc = ClientBehaviorContext.createClientBehaviorContext(context, (UIComponent) item, "click", item.getClientId(), Collections.EMPTY_LIST);
                        clientBehavior.getScript(cbc);
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Should render id.
     *
     * @param element the element
     * @return true, if successful
     */
    protected boolean shouldRenderId(MenuElement element) {
        if(element instanceof UIComponent)
            return shouldWriteId((UIComponent) element);
        else
            return false;
    }
    
    /**
     * Encode menu item content.
     *
     * @param context the context
     * @param menu the menu
     * @param menuitem the menuitem
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = menuitem.getIcon();
        Object value = menuitem.getValue();
        
        if(icon != null) {
            writer.startElement("span", null);
            writer.writeAttribute("class", AbstractMenu.MENUITEM_ICON_CLASS + " " + icon, null);
            writer.endElement("span");
        }

        if(value != null) {
            writer.startElement("span", null);
            writer.writeAttribute("class", AbstractMenu.MENUITEM_TEXT_CLASS, null);
            writer.writeText(value, "value");
            writer.endElement("span");
        }
    }

    /**
     * Encode separator.
     *
     * @param context the context
     * @param separator the separator
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeSeparator(FacesContext context, Separator separator) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String style = separator.getStyle();
        String styleClass = separator.getStyleClass();
        styleClass = styleClass == null ? Menu.SEPARATOR_CLASS : Menu.SEPARATOR_CLASS + " " + styleClass;

        //title
        writer.startElement("li", null);
        writer.writeAttribute("class", styleClass, null);
        if(style != null) {
            writer.writeAttribute("style", style, null);
        }
        
        writer.endElement("li");
	}
    
    /**
     * Encode overlay config.
     *
     * @param context the context
     * @param menu the menu
     * @param wb the wb
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeOverlayConfig(FacesContext context, OverlayMenu menu, WidgetBuilder wb) throws IOException {
        wb.attr("overlay", true)
            .attr("my", menu.getMy())
            .attr("at", menu.getAt());
        
        String trigger = menu.getTrigger();
        if (trigger != null) {
            wb.attr("trigger", SearchExpressionFacade.resolveClientIds(context, (UIComponent) menu, trigger))
                .attr("triggerEvent", menu.getTriggerEvent());
        }
    }
 
    /* (non-Javadoc)
     * @see javax.faces.render.Renderer#encodeChildren(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
     */
    @Override
	public void encodeChildren(FacesContext facesContext, UIComponent component) throws IOException {
		//Do nothing
	}

    /* (non-Javadoc)
     * @see javax.faces.render.Renderer#getRendersChildren()
     */
    @Override
	public boolean getRendersChildren() {
		return true;
	}
    
    /**
     * Builds the ajax request.
     *
     * @param context the context
     * @param menu the menu
     * @param source the source
     * @param form the form
     * @param params the params
     * @return the string
     */
    protected String buildAjaxRequest(FacesContext context, AbstractMenu menu, AjaxSource source, UIComponent form, Map<String,List<String>> params) {
        String clientId = menu.getClientId(context);
        
        AjaxRequestBuilder builder = RequestContext.getCurrentInstance().getAjaxRequestBuilder();
        
        builder.init()
                .source(clientId)
                .process(menu, source.getProcess())
                .update(menu, source.getUpdate())
                .async(source.isAsync())
                .global(source.isGlobal())
                .delay(source.getDelay())
                .timeout(source.getTimeout())
                .partialSubmit(source.isPartialSubmit(), source.isPartialSubmitSet(), source.getPartialSubmitFilter())
                .resetValues(source.isResetValues(), source.isResetValuesSet())
                .ignoreAutoUpdate(source.isIgnoreAutoUpdate())
                .onstart(source.getOnstart())
                .onerror(source.getOnerror())
                .onsuccess(source.getOnsuccess())
                .oncomplete(source.getOncomplete())
                .params(params);
        
        if(form != null) {
            builder.form(form.getClientId(context));
        }
        
        builder.preventDefault();
                
        return builder.build();
    }
    
    /**
     * Builds the non ajax request.
     *
     * @param context the context
     * @param component the component
     * @param form the form
     * @param decodeParam the decode param
     * @param parameters the parameters
     * @param submit the submit
     * @return the string
     */
    protected String buildNonAjaxRequest(FacesContext context, UIComponent component, UIComponent form, String decodeParam, Map<String,List<String>> parameters, boolean submit) {		
        StringBuilder request = SharedStringBuilder.get(context, SB_BUILD_NON_AJAX_REQUEST);
        String formId = form.getClientId(context);
        Map<String,Object> params = new HashMap<String, Object>();
        
        if(decodeParam != null) {
            params.put(decodeParam, decodeParam);
        }
        
		for(UIComponent child : component.getChildren()) {
			if(child instanceof UIParameter && child.isRendered()) {
                UIParameter param = (UIParameter) child;
                params.put(param.getName(), param.getValue());
			}
		}
        
        if(parameters != null && !parameters.isEmpty()) {
            for(Iterator<String> it = parameters.keySet().iterator(); it.hasNext();) {
                String paramName = it.next();
                params.put(paramName, parameters.get(paramName).get(0));                
            }
        }
        
        //append params
        if(!params.isEmpty()) {
            request.append("PrimeFaces.addSubmitParam('").append(formId).append("',{");
            
            for(Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                Object value = params.get(key);

                request.append("'").append(key).append("':'").append(value).append("'");

                if(it.hasNext())
                    request.append(",");
            }
            
            request.append("})");
        }
        
        if(submit) {
            request.append(".submit('").append(formId).append("');return false;");
        }
		
		return request.toString();
	}
    
    /**
     * Encode keyboard target.
     *
     * @param context the context
     * @param menu the menu
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeKeyboardTarget(FacesContext context, AbstractMenu menu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.startElement("div", null);
        writer.writeAttribute("tabindex", menu.getTabindex(), null);
        writer.writeAttribute("class", "ui-helper-hidden-accessible", null);
        writer.endElement("div");
    }
}
