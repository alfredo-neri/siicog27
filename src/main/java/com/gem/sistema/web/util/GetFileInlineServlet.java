package com.gem.sistema.web.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class GetFileInlineServlet.
 */
@WebServlet("/GetFileInline")
public class GetFileInlineServlet extends HttpServlet {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GetFileInlineServlet.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant TMP_PATH. */
	public static final String TMP_PATH = System.getProperty("java.io.tmpdir");	
	
	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * Gets the full path.
	 *
	 * @param uuid the uuid
	 * @param filename the filename
	 * @return the full path
	 */
	public static String getFullPath(String uuid, String filename){
		return TMP_PATH + FILE_SEPARATOR + uuid + filename;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String uuid = null;
		 String filename = null;		 
		Enumeration<String> parameterNames = request.getParameterNames();
		String[] paramValues;
		
		while (parameterNames.hasMoreElements()) {

			String paramName = parameterNames.nextElement();
			LOGGER.info("Param = {}", paramName);
			if ("filename".equals(paramName)){
				paramValues = request.getParameterValues(paramName);
				if(paramValues.length >= 0){
					filename = paramValues[0];
					LOGGER.info("filename = {}", filename);
				}
			}else if ("uuid".equals(paramName)){
				paramValues = request.getParameterValues(paramName);
				if(paramValues.length >= 0){
					uuid = paramValues[0];
					LOGGER.info("uuid = {}", uuid);
				}
			}
		}
		
		if(StringUtils.isNotEmpty(filename) && StringUtils.isNotEmpty(uuid)){
			File file = new File(getFullPath(uuid, filename));			
		    response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        response.setHeader("Content-Disposition", "inline; filename=\""+ filename + "\"");
	        Files.copy(file.toPath(), response.getOutputStream());
		}
		
       
    }

}
