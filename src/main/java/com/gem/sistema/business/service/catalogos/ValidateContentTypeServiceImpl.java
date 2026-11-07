package com.gem.sistema.business.service.catalogos;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidateContentTypeServiceImpl.
 */
public class ValidateContentTypeServiceImpl implements ValidateContentTypeService{
	
	/** The Constant content. */
	private static final String content = "application/octet-stream";
	
	/** The Constant mimeTypeFile. */
	private static final MimetypesFileTypeMap mimeTypeFile = null;
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(ValidateContentTypeServiceImpl.class);
	
	/**
	 * Gets the mimetypefile.
	 *
	 * @return the mimetypefile
	 */
	public static MimetypesFileTypeMap getMimetypefile() {
		return mimeTypeFile;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public static String getContent() {
		return content;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ValidateContentTypeService#isValidateContentType(java.io.File)
	 */
	@Override
	public boolean isValidateContentType(final File file) {
		
		LOGGER.info("file:::: " + file);
	    if(mimeTypeFile.getContentType(file).matches(getContent())){
	    	
	    	return true;
	    } else {
		    return false;
	    }
	}

}
