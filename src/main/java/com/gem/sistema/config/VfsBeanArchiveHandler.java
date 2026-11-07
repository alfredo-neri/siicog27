package com.gem.sistema.config;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import org.jboss.weld.environment.deployment.discovery.BeanArchiveBuilder;
import org.jboss.weld.environment.deployment.discovery.BeanArchiveHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Permite que el Weld incluido en el WAR encuentre los bean archives cuando la aplicacion se despliega
 * en WildFly.
 *
 * WildFly entrega los JAR de WEB-INF/lib con URLs {@code vfs:} (su sistema de archivos virtual), que
 * Weld no sabe leer: sin esto no registra los beans de OmniFaces ni de PrimeFaces y OmniFaces no
 * arranca. Las clases se listan con el propio VFS: el archivo fisico que WildFly expone para un JAR
 * dentro del WAR es una carpeta que solo se llena bajo demanda, asi que no sirve para recorrerla.
 *
 * JBoss VFS se usa por reflexion para no depender de WildFly al compilar. Con {@code java -jar} o
 * Tomcat no hay referencias {@code vfs:} y este manejador no interviene. Se registra en
 * META-INF/services/org.jboss.weld.environment.deployment.discovery.BeanArchiveHandler.
 */
public class VfsBeanArchiveHandler implements BeanArchiveHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(VfsBeanArchiveHandler.class);

	private static final String VFS = "vfs:";
	private static final String CLASS = ".class";
	private static final String BEANS_XML = "/META-INF/beans.xml";
	private static final String WEB_INF_BEANS_XML = "/WEB-INF/beans.xml";
	private static final String WEB_INF_CLASSES = "/WEB-INF/classes";

	@Override
	public BeanArchiveBuilder handle(String beanArchiveReference) {
		if (beanArchiveReference == null || !beanArchiveReference.startsWith(VFS)) {
			return null;
		}
		String raiz = raizDelArchivo(beanArchiveReference);
		try {
			BeanArchiveBuilder builder = new BeanArchiveBuilder();
			for (String clase : clases(raiz)) {
				builder.addClass(clase);
			}
			LOGGER.debug("Bean archive {}: {} clases", raiz, builder.getClasses().size());
			return builder;
		} catch (ReflectiveOperationException | RuntimeException e) {
			LOGGER.warn("No se pudo leer el bean archive {}: {}", raiz, e.toString());
			return null;
		}
	}

	/** Raiz del bean archive: el JAR (sin META-INF/beans.xml) o WEB-INF/classes del WAR. */
	static String raizDelArchivo(String referencia) {
		if (referencia.endsWith(BEANS_XML)) {
			return referencia.substring(0, referencia.length() - BEANS_XML.length());
		}
		if (referencia.endsWith(WEB_INF_BEANS_XML)) {
			return referencia.substring(0, referencia.length() - WEB_INF_BEANS_XML.length()) + WEB_INF_CLASSES;
		}
		return referencia;
	}

	/** Nombres de las clases bajo la raiz: {@code VFS.getChild(uri).getChildrenRecursively()}. */
	private static List<String> clases(String raiz) throws ReflectiveOperationException {
		ClassLoader cl = VfsBeanArchiveHandler.class.getClassLoader();
		Class<?> vfs = Class.forName("org.jboss.vfs.VFS", false, cl);
		Object virtual = vfs.getMethod("getChild", URI.class).invoke(null, URI.create(raiz.replace(" ", "%20")));
		Method relativa = virtual.getClass().getMethod("getPathNameRelativeTo", virtual.getClass());
		List<?> hijos = (List<?>) virtual.getClass().getMethod("getChildrenRecursively").invoke(virtual);
		List<String> clases = new java.util.ArrayList<>();
		for (Object hijo : hijos) {
			String ruta = (String) relativa.invoke(hijo, virtual);
			if (ruta.endsWith(CLASS) && !ruta.startsWith("META-INF/")) {
				clases.add(ruta.substring(0, ruta.length() - CLASS.length()).replace('/', '.'));
			}
		}
		return clases;
	}
}
