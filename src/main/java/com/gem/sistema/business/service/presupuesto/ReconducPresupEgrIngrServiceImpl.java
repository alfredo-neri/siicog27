/**
 * 
 */
package com.gem.sistema.business.service.presupuesto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ReconducPresupEgrIngrDAO;
import com.gem.sistema.business.dto.ReconducPresupEgrIngrDTO;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ReconducPresupEgrIngrServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service("reconducPresupEgrIngrService")
public class ReconducPresupEgrIngrServiceImpl implements ReconducPresupEgrIngrService {

	/** The Constant BASE_NAME. */
	private static final String BASE_NAME = "RP%1$s_I_E.zip";

	/** The Constant SDF. */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("YYYYMMDDHHmmss");

	/** The reconduc presup egr ingr DAO. */
	@Autowired
	ReconducPresupEgrIngrDAO reconducPresupEgrIngrDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.presupuesto.ReconducPresupEgrIngrService
	 * #getZippedFile(int, java.lang.String)
	 */
	@Override
	public ReconducPresupEgrIngrDTO getZippedFile(int mes, String user) {

		try {
			ReconducPresupEgrIngrDTO reconducPresupEgrIngrDTO = this.reconducPresupEgrIngrDAO.getByMonthUser(mes, user);

			if (reconducPresupEgrIngrDTO.getCodError() > 0) {
				// Initiate ZipFile object with the path/name of the zip file.
				ZipFile zipFile;
				zipFile = new ZipFile(reconducPresupEgrIngrDTO.getPathFileIncomes() + "/"
						+ String.format(BASE_NAME, SDF.format(Calendar.getInstance().getTime())));
				// Initiate Zip Parameters which define various properties such
				// as compression method, etc.
				ZipParameters parameters = new ZipParameters();
				// set compression method to store compression
				parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
				// Set the compression level
				parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

				zipFile.addFile(new File(reconducPresupEgrIngrDTO.getPathFileIncomes() + "/"
						+ reconducPresupEgrIngrDTO.getFileNameIncomes()), parameters);
				zipFile.addFile(new File(reconducPresupEgrIngrDTO.getPathFileExpenses() + "/"
						+ reconducPresupEgrIngrDTO.getFileNameExpenses()), parameters);
				reconducPresupEgrIngrDTO.setResultFile(zipFile.getFile());
			}
			return reconducPresupEgrIngrDTO;
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
