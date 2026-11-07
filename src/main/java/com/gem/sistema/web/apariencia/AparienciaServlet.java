package com.gem.sistema.web.apariencia;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * GET /apariencia: tema vigente en JSON ({"version", "tema", "css"}).
 *
 * Lo consulta siicog.js en las paginas abiertas (al volver a la pestana y cada minuto) para aplicar
 * el tema que guarde un administrador sin que cada usuario tenga que recargar. Es publico porque el
 * login tambien lo usa y no expone nada del usuario. Se registra en WebConfig.
 */
public class AparienciaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new Gson();

	private final transient AparienciaService apariencia;

	public AparienciaServlet(AparienciaService apariencia) {
		this.apariencia = apariencia;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Cache-Control", "no-store");
		resp.getWriter().write(GSON.toJson(apariencia.paraNavegador()));
	}
}
