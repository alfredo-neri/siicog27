package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.ConvertToTiffPdfBS;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvertToTiffPdfServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "convertToTiffPdfService")
public class ConvertToTiffPdfServiceImpl implements ConvertToTiffPdfService {

	/** The convert to tiff pdf BS. */
	@Autowired
	private ConvertToTiffPdfBS convertToTiffPdfBS;
	
	
	/**
	 * Gets the convert to tiff pdf BS.
	 *
	 * @return the convert to tiff pdf BS
	 */
	public ConvertToTiffPdfBS getConvertToTiffPdfBS() {
		return convertToTiffPdfBS;
	}


	/**
	 * Sets the convert to tiff pdf BS.
	 *
	 * @param convertToTiffPdfBS the new convert to tiff pdf BS
	 */
	public void setConvertToTiffPdfBS(ConvertToTiffPdfBS convertToTiffPdfBS) {
		this.convertToTiffPdfBS = convertToTiffPdfBS;
	}


	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ConvertToTiffPdfService#getPdfConvenrt(java.lang.String)
	 */
	@Override
	public String convertFilesToPDF(String fileName, String uuid) throws Exception {
		return this.convertToTiffPdfBS.convertFilesToPDF(fileName,  uuid);
	}

}
