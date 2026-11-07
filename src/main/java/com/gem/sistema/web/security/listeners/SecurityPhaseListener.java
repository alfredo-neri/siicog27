/**
 * 
 */
package com.gem.sistema.web.security.listeners;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving securityPhase events.
 * The class that is interested in processing a securityPhase
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addSecurityPhaseListener<code> method. When
 * the securityPhase event occurs, that object's appropriate
 * method is invoked.
 *
 * @author gauss
 */
public class SecurityPhaseListener implements PhaseListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SecurityPhaseListener.class);

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		;

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent event) {
		  FacesContext fc = event.getFacesContext();
		  
	        String loginPage = (String) fc.getExternalContext().getRequestMap().
	            get("web.secfilter.authenticator.showLogin");
	        if (StringUtils.isNotBlank(loginPage)) {
	            doRedirect(fc, loginPage);
	        }

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Does a regular or ajax redirect.
	 *
	 * @param fc the fc
	 * @param redirectPage the redirect page
	 * @throws FacesException the faces exception
	 */
    public void doRedirect(FacesContext fc, String redirectPage) 
        throws FacesException {
        ExternalContext ec = fc.getExternalContext();
 
        try {
            if (ec.isResponseCommitted()) {
                return;
            }
 
            // fix for renderer kit (Mojarra's and PrimeFaces's ajax redirect)
            if ((RequestContext.getCurrentInstance().isAjaxRequest()
                || fc.getPartialViewContext().isPartialRequest())
                && fc.getResponseWriter() == null
                && fc.getRenderKit() == null) {
                    ServletResponse response = (ServletResponse) ec.getResponse();
                    ServletRequest request = (ServletRequest) ec.getRequest();
                    response.setCharacterEncoding(request.getCharacterEncoding());
 
                    RenderKitFactory factory = (RenderKitFactory)  
                     FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
 
                    RenderKit renderKit = factory.getRenderKit(fc,
                     fc.getApplication().getViewHandler().calculateRenderKitId(fc));
 
                    ResponseWriter responseWriter =
                        renderKit.createResponseWriter(
                        response.getWriter(), null, request.getCharacterEncoding());
                        fc.setResponseWriter(responseWriter);
            }
 
            ec.redirect(ec.getRequestContextPath() + 
                (redirectPage != null ? redirectPage : ""));
        } catch (IOException e) {
            LOG.error("Redirect to the specified page '" + 
                redirectPage + "' failed");
            throw new FacesException(e);
        }
    }

}
