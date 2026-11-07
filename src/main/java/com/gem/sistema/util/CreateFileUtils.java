package com.gem.sistema.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Alfredo Neri
 *Utileria paara la creacion de archivo JSON
 */
public class CreateFileUtils {

	private static final String PATH_FILE = "/gem/reportes/";

	/**
	 * @param nameFile
	 * @param jsonText
	 * @return
	 */
	public static File createJsonFile(String nameFile, String jsonText) {

		String fullPath = PATH_FILE + nameFile;
		File file = new File(fullPath);
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
			writer.write(jsonText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
