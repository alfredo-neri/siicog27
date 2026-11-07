/**
 * 
 */
package com.gem.sistema.web.security.filters;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.util.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class JsfRedirectStrategy.
 *
 * @author gauss
 */
public class JsfRedirectStrategy implements InvalidSessionStrategy {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
		
	    /** The Constant FACES_REQUEST_HEADER. */
    	private static final String FACES_REQUEST_HEADER = "faces-request";
	
	    /** The invalid session url. */
    	private String invalidSessionUrl;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#onInvalidSessionDetected(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		 boolean ajaxRedirect = "partial/ajax".equals(request.getHeader(FACES_REQUEST_HEADER));
	        if(ajaxRedirect) {
	            String contextPath = request.getContextPath();
	            String redirectUrl = contextPath + invalidSessionUrl;
	            logger.debug("Session expired due to ajax request, redirecting to '{}'", redirectUrl);
	            
	            String ajaxRedirectXml = createAjaxRedirectXml(redirectUrl);
	            logger.debug("Ajax partial response to redirect: {}", ajaxRedirectXml);

	            response.setContentType("text/xml");
	            response.getWriter().write(ajaxRedirectXml);
	        } else {
	            String requestURI = getRequestUrl(request);
	            logger.debug("Session expired due to non-ajax request, starting a new session and redirect to requested url '{}'", requestURI);
	            request.getSession(true);
	            response.sendRedirect(requestURI);
	        }

	}
	
	
	 /**
 	 * Gets the request url.
 	 *
 	 * @param request the request
 	 * @return the request url
 	 */
 	private String getRequestUrl(HttpServletRequest request) {
	        StringBuffer requestURL = request.getRequestURL();

	        String queryString = request.getQueryString();
	        if (StringUtils.hasText(queryString)) {
	            requestURL.append("?").append(queryString);
	        }

	        return requestURL.toString();
	    }

	    /**
    	 * Creates the ajax redirect xml.
    	 *
    	 * @param redirectUrl the redirect url
    	 * @return the string
    	 */
    	private String createAjaxRedirectXml(String redirectUrl) {
	        return new StringBuilder()
	                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
	                        .append("<partial-response><redirect url=\"")
	                        .append(redirectUrl)
	                        .append("\"></redirect></partial-response>")
	                        .toString();
	    }

	    /**
    	 * Sets the invalid session url.
    	 *
    	 * @param invalidSessionUrl the new invalid session url
    	 */
    	public void setInvalidSessionUrl(String invalidSessionUrl) {
	        this.invalidSessionUrl = invalidSessionUrl;
	    }
	

}
