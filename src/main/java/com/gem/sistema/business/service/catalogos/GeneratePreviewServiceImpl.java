package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.GeneratePrevieReportBS;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneratePreviewServiceImpl.
 */
@Service(value = "generatePreviewService")
public class GeneratePreviewServiceImpl implements GeneratePreviewService {
	
	/** The generate preview BS. */
	@Autowired
	private GeneratePrevieReportBS generatePreviewBS;
	
	

	/**
	 * Gets the generate preview BS.
	 *
	 * @return the generate preview BS
	 */
	public GeneratePrevieReportBS getGeneratePreviewBS() {
		return generatePreviewBS;
	}



	/**
	 * Sets the generate preview BS.
	 *
	 * @param generatePreviewBS the new generate preview BS
	 */
	public void setGeneratePreviewBS(GeneratePrevieReportBS generatePreviewBS) {
		this.generatePreviewBS = generatePreviewBS;
	}



	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.GeneratePreviewService#generatePreview(java.lang.String)
	 */
	@Override
	public StringBuilder generatePreview(String pathFile) {
		return generatePreviewBS.generatePreview(pathFile);
	}

}
