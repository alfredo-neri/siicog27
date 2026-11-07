package com.gem.sistema.business.bs.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.ExtraeArchivoCuentaBS;

@Repository
public class ExtraeArchivoCuentaBSImpl implements ExtraeArchivoCuentaBS {

	@Override
	public String getNewFile(String fileName) {
		StringBuilder newCad = new StringBuilder();

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {

			archivo = new File("/gem/upfiles/" + fileName);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {

				newCad.append(replaceComillas(linea)).append("\n");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return newCad.toString();
	}

	private String replaceComillas(String cad) {
		String newCad = cad.trim().replaceAll("\" \" ", "|");
		return newCad.replaceAll("\" ", "|").replaceAll("\"", "").replaceAll("  ", "|");
	}

	@Override
	public String generateNewFile(String newCad, String usuario) {
		String file = "cuenta_" + usuario + ".txt";
		String cad = this.getNewFile(newCad);

		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("/gem/upfiles/" + file);
			pw = new PrintWriter(fichero);

			pw.println(cad);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return file;

	}

}
