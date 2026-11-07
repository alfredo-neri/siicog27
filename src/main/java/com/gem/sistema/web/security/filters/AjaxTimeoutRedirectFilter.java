/**
 * 
 */
package com.gem.sistema.web.security.filters;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.web.filter.GenericFilterBean;

// TODO: Auto-generated Javadoc
/**
 * The Class AjaxTimeoutRedirectFilter.
 *
 * @author gauss
 */
public class AjaxTimeoutRedirectFilter extends GenericFilterBean {
	
	 /** The Constant logger. */
 	private static final Logger logger = LoggerFactory.getLogger(AjaxTimeoutRedirectFilter.class);
	 
	    /** The throwable analyzer. */
    	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
	    
    	/** The authentication trust resolver. */
    	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
	 
	    /** The custom session expired error code. */
    	private int customSessionExpiredErrorCode = 901;
	 
	    /* (non-Javadoc)
    	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
    	 */
    	@Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	    {
	        try
	        {
	            chain.doFilter(request, response);
	 
	            logger.debug("Chain processed normally");
	        }
	        catch (IOException ex)
	        {
	            throw ex;
	        }
	        catch (Exception ex)
	        {
	            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
	            RuntimeException ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
	 
	            if (ase == null)
	            {
	                ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
	            }
	 
	            if (ase != null)
	            {
	                if (ase instanceof AuthenticationException)
	                {
	                    throw ase;
	                }
	                else if (ase instanceof AccessDeniedException)
	                {
	 
	                    if (authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication()))
	                    {
	                        logger.info("User session expired or not logged in yet");
	                        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
	 
	                        if ("XMLHttpRequest".equals(ajaxHeader))
	                        {
	                            logger.info("Ajax call detected, send {} error code", this.customSessionExpiredErrorCode);
	                            HttpServletResponse resp = (HttpServletResponse) response;
	                            resp.sendError(this.customSessionExpiredErrorCode);
	                        }
	                        else
	                        {
	                            logger.info("Redirect to login page");
	                            throw ase;
	                        }
	                    }
	                    else
	                    {
	                        throw ase;
	                    }
	                }
	            }
	 
	        }
	    }
	 
	    /**
    	 * The Class DefaultThrowableAnalyzer.
    	 */
    	private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer
	    {
	        
        	/**
        	 * Inits the extractor map.
        	 *
        	 * @see org.springframework.security.web.util.ThrowableAnalyzer#initExtractorMap()
        	 */
	        protected void initExtractorMap()
	        {
	            super.initExtractorMap();
	 
	            registerExtractor(ServletException.class, new ThrowableCauseExtractor() 
	            {
	                public Throwable extractCause(Throwable throwable)
	                {
	                    ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
	                    return ((ServletException) throwable).getRootCause();
	                }
	            });
	        }
	 
	    }
	 
	    /**
    	 * Sets the custom session expired error code.
    	 *
    	 * @param customSessionExpiredErrorCode the new custom session expired error code
    	 */
    	public void setCustomSessionExpiredErrorCode(int customSessionExpiredErrorCode)
	    {
	        this.customSessionExpiredErrorCode = customSessionExpiredErrorCode;
	    }
}
