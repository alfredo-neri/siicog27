package com.gem.sistema.web.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.web.bean.AbstractMB;

// TODO: Auto-generated Javadoc
/**
 * The Class PoliciesFilter.
 */
@WebFilter(filterName = "PoliciesFilter", urlPatterns = {"/*"})
public class PoliciesFilter extends AbstractMB implements Filter {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PoliciesFilter.class);
	
	/** The Constant POLICIES_LOCK_PREFIX. */
	private static final String POLICIES_LOCK_PREFIX = "POLICE";

	/** The sc. */
	ServletContext sc;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		sc = filterConfig.getServletContext();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			if (!isAjaxRequest((HttpServletRequest)request)){
				clearLocks();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	/**
	 * Clear locks.
	 */
	private void clearLocks() {
		List<String> lockedPolicies = new ArrayList<String>();
		Enumeration<String> attributeNames = sc.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String current = (String) attributeNames.nextElement();
			if (current.startsWith(POLICIES_LOCK_PREFIX)) {
				lockedPolicies.add(current);
			}
		}
		for (String lockedPolice : lockedPolicies) {
			String blockUser = (String) sc.getAttribute(lockedPolice);
			if (blockUser.equals(getUserDetails().getUsername())) {
				LOGGER.info(" ++ removing attr " + lockedPolice);
				sc.removeAttribute(lockedPolice);
			}
		}
	}

	/**
	 * Checks if is ajax request.
	 *
	 * @param request the request
	 * @return true, if is ajax request
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {}
}
