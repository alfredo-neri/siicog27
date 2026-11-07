/**
 * 
 */
package com.gem.sistema.business.service.callsp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gem.sistema.business.dao.impl.CallSpDAOImpl;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class CallSpReportServiceImpl.
 */
@Service
public class CallSpReportServiceImpl implements CallSpService{
	
	/** The Constant SDF. */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("YYYYMMDDHHmmss");

	/** The call sp DAO impl. */
	@Autowired
	CallSpDAOImpl callSpDAOImpl;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.callsp.CallSpService#getFile(java.lang.String, com.gem.sistema.business.service.callsp.ParamsSpDTO)
	 */
	@Override
	public void getFile(String plName, ParamsSpDTO params) {
			Map<String, Object> result = callSpDAOImpl.call(plName, params.getSqlParameterSource());
			params.setResult(result);			
			if (params.getCodError() > 0) {
				params.setResultFile(new File(params.getPathFileOut() + "/"
						+ params.getFileNameOut()));
			}
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.callsp.CallSpService#getZippedFile(java.lang.String, java.lang.String, com.gem.sistema.business.service.callsp.ParamsSpDTO)
	 */
	@Override
	public void getZippedFile(String baseNameZip, String plName, ParamsSpDTO params) {
		try {
			Map<String, Object> result = callSpDAOImpl.call(plName, params.getSqlParameterSource());
			params.setResult(result);			
			if (params.getCodError() > 0) {
				// Initiate ZipFile object with the path/name of the zip file.
				ZipFile zipFile;
				zipFile = new ZipFile(params.getPathFileOut() + "/"
						+ String.format(baseNameZip, SDF.format(Calendar.getInstance().getTime())));
				// Initiate Zip Parameters which define various properties such
				// as compression method, etc.
				ZipParameters parameters = new ZipParameters();
				// set compression method to store compression
				parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
				// Set the compression level
				parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

				zipFile.addFile(new File(params.getPathFileOut() + "/"
						+ params.getFileNameOut()), parameters);
				params.setResultFile(zipFile.getFile());
			}
		} catch (ZipException e) {			
			throw new RuntimeException(e);
		}

	}

}
