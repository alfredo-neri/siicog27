package com.gem.sistema.business.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.GeneratePrevieReportBS;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneratePrevieReportBSImpl.
 *
 * @author Mateo
 */
@Repository
public class GeneratePrevieReportBSImpl implements GeneratePrevieReportBS {
	
	/** The Constant JUMP_LINEA. */
	private static final String JUMP_LINEA = "\n";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GeneratePrevieReportBSImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.GeneratePrevieReportBS#
	 * generatePreview(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public StringBuilder generatePreview(String pathFile) {
		StringBuilder preViewTxt = new StringBuilder();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(pathFile);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {
				preViewTxt.append(linea).append(JUMP_LINEA);
				// System.out.println(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return preViewTxt;
	}

}
