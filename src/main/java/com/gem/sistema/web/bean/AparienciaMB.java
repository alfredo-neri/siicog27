package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gem.sistema.web.apariencia.AparienciaService;
import com.gem.sistema.web.apariencia.AparienciaService.Apariencia;
import com.gem.sistema.web.apariencia.Paleta;
import com.gem.sistema.web.apariencia.Tema;

import jakarta.annotation.PostConstruct;

/**
 * Panel Apariencia (views/administracion/apariencia.xhtml): el administrador elige el tema de colores
 * de toda la aplicacion. La pagina solo la abre ROLE_ADMIN (SecurityConfig) y {@link AparienciaService}
 * vuelve a comprobarlo al guardar.
 */
@Component("aparienciaMB")
@Scope("view")
public class AparienciaMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private final transient AparienciaService apariencia;

	/** Tema marcado en el panel (aun sin guardar). */
	private Tema tema;
	private String principal;
	private String acento;

	public AparienciaMB(AparienciaService apariencia) {
		this.apariencia = apariencia;
	}

	@PostConstruct
	public void init() {
		cargar(apariencia.getActual());
	}

	private void cargar(Apariencia a) {
		tema = a.tema();
		principal = a.principal();
		acento = a.acento();
	}

	public List<Tema> getTemas() {
		return Arrays.asList(Tema.values());
	}

	/** Al pulsar una tarjeta de tema. */
	public void elegir(Tema t) {
		tema = t;
	}

	/**
	 * Variables de la muestra del tema personalizado (atributo style). Con colores invalidos se muestra
	 * el tema Institucional y {@link #getAviso()} explica por que.
	 */
	public String getMuestraPersonalizada() {
		return Paleta.validar(principal, acento) == null ? Paleta.declaraciones(principal, acento) : "";
	}

	/** Motivo por el que los colores personalizados no se pueden guardar, o null. */
	public String getAviso() {
		return tema == Tema.PERSONALIZADO ? Paleta.validar(principal, acento) : null;
	}

	/** El tema marcado es distinto del vigente. */
	public boolean isCambios() {
		Apariencia a = apariencia.getActual();
		if (tema != a.tema()) {
			return true;
		}
		return tema == Tema.PERSONALIZADO
				&& !(a.principal().equals(Paleta.normalizar(principal)) && a.acento().equals(Paleta.normalizar(acento)));
	}

	public Apariencia getVigente() {
		return apariencia.getActual();
	}

	public void guardar() {
		try {
			Apariencia a = apariencia.guardar(tema, principal, acento, getUserDetails().getUsername());
			cargar(a);
			displayInfoMessage("Se aplicó el tema " + a.tema().getNombre()
					+ ". Los usuarios conectados lo verán en menos de un minuto, sin recargar la página.");
		} catch (IllegalArgumentException | SecurityException e) {
			displayErrorMessage(e.getMessage());
		}
	}

	/** Vuelve a marcar el tema vigente, sin guardar. */
	public void descartar() {
		cargar(apariencia.getActual());
	}

	public Tema getTema() {
		return tema;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getAcento() {
		return acento;
	}

	public void setAcento(String acento) {
		this.acento = acento;
	}
}
