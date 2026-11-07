package com.gem.sistema.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Expande el contenido web del WAR (vistas, reportes, recursos) a una carpeta para que Tomcat embebido
 * la use como raiz.
 *
 * Con {@code java -jar} el contenido web se sirve desde dentro del WAR y
 * {@code ServletContext.getRealPath} devuelve null. Los reportes (.jasper y sus subreportes) y los
 * temporales se manejan con rutas fisicas, como en difem26, asi que sin esto no se genera ningun
 * reporte. WildFly y Tomcat externo ya expanden el WAR, y desde el IDE la raiz es src/main/webapp.
 *
 * Solo se expande cuando cambia el WAR (tamano y fecha guardados en la carpeta). Las clases y
 * bibliotecas (WEB-INF/classes, WEB-INF/lib) no se copian: se siguen cargando desde el WAR.
 */
final class ContenidoWebExpandido {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContenidoWebExpandido.class);

	private static final String MARCA = ".war-expandido";

	private ContenidoWebExpandido() {
	}

	static File expandir(File war, Path destino) throws IOException {
		Path raiz = destino.toAbsolutePath().normalize();
		Path marca = raiz.resolve(MARCA);
		String firma = war.getAbsolutePath() + "|" + war.length() + "|" + war.lastModified();
		if (Files.isRegularFile(marca) && Files.readString(marca).equals(firma)) {
			return raiz.toFile();
		}
		LOGGER.info("Expandiendo el contenido web de {} en {}", war, raiz);
		int archivos = 0;
		try (ZipFile zip = new ZipFile(war)) {
			Enumeration<? extends ZipEntry> entradas = zip.entries();
			while (entradas.hasMoreElements()) {
				ZipEntry entrada = entradas.nextElement();
				if (entrada.isDirectory() || !esContenidoWeb(entrada.getName())) {
					continue;
				}
				Path archivo = raiz.resolve(entrada.getName()).normalize();
				if (!archivo.startsWith(raiz)) {
					continue;
				}
				Files.createDirectories(archivo.getParent());
				try (InputStream in = zip.getInputStream(entrada)) {
					Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
				}
				archivos++;
			}
		}
		Files.writeString(marca, firma);
		LOGGER.info("Contenido web expandido: {} archivos", archivos);
		return raiz.toFile();
	}

	/** Todo menos clases, bibliotecas y el lanzador de Spring Boot (org/springframework/boot/loader). */
	private static boolean esContenidoWeb(String nombre) {
		return !nombre.startsWith("WEB-INF/classes/") && !nombre.startsWith("WEB-INF/lib/")
				&& !nombre.startsWith("WEB-INF/lib-provided/") && !nombre.startsWith("META-INF/")
				&& !nombre.startsWith("org/");
	}
}
