package com.gem.sistema.web.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class ImagesFirmasDigitales {
	public static final String PATH_TMP = "../../tmp/";

	public static String createPathTempFromImg(String nombreArchivo, byte[] bytes) {
		String pathImg = null;
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		String path = context.getRealPath("") + File.separator + "tmp" + File.separator + nombreArchivo;
		File f = null;
		InputStream is = null;
		try {
			f = new File(path);
			is = new ByteArrayInputStream(bytes);
			FileOutputStream os = new FileOutputStream(f.getAbsolutePath());
			int c = 0;
			while ((c = is.read()) >= 0) {
				os.write(c);
			}
			os.flush();
			os.close();
			pathImg = ImagesFirmasDigitales.PATH_TMP + nombreArchivo;
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al cargar la imagen en el directorio temporal");
			e.printStackTrace();
		}
		return pathImg;

	}

	public static void cleanTmp() {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		File[] files = new File(context.getRealPath("") + File.separator + "tmp").listFiles();
		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}
		System.out.println("Se ha limpiado el directorio temporal correctamente");
	}

}
