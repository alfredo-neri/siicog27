package com.gem.sistema.web.apariencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.gem.sistema.web.apariencia.AparienciaService.Apariencia;

/**
 * Paleta del tema personalizado y formato del parametro SIICOG_APARIENCIA, sin base de datos.
 */
class AparienciaTest {

	private static final int[] BLANCO = { 255, 255, 255 };

	@Test
	void normalizaSoloColoresHex() {
		assertEquals("#611232", Paleta.normalizar("611232"));
		assertEquals("#ABCDEF", Paleta.normalizar(" #abcdef "));
		assertNull(Paleta.normalizar("#12345"));
		assertNull(Paleta.normalizar("red;}body{display:none"));
		assertNull(Paleta.normalizar(null));
	}

	@Test
	void rechazaColoresIlegibles() {
		assertNull(Paleta.validar("#001633", "#00A6C8"));
		assertNotNull(Paleta.validar("#F0F0F0", "#00A6C8"), "principal claro: texto blanco ilegible");
		assertNotNull(Paleta.validar("#001633", "#0B2748"), "acento igual de oscuro que el principal");
		assertNotNull(Paleta.validar("#001633", "x"));
	}

	@Test
	void derivaVariablesLegibles() {
		for (String[] par : new String[][] { { "#001633", "#00A6C8" }, { "#3A0CA3", "#F72585" }, { "#1B4332", "#FFD60A" } }) {
			Map<String, String> v = Paleta.variables(par[0], par[1]);
			assertEquals(16, v.size());
			assertTrue(v.values().stream().allMatch(c -> c.matches("#[0-9A-F]{6}")));
			int[] marino = Paleta.rgb(v.get("marino"));
			assertTrue(Paleta.contraste(Paleta.rgb(v.get("cian-700")), BLANCO) >= 4.5, "enlaces sobre blanco");
			assertTrue(Paleta.contraste(Paleta.rgb(v.get("acero")), BLANCO) >= 4.5, "avatar");
			assertTrue(Paleta.contraste(Paleta.rgb(v.get("niebla")), marino) >= 4.5, "texto secundario de la barra");
		}
		String css = Paleta.css("#001633", "#00A6C8");
		assertTrue(css.startsWith("[data-sg-tema=personalizado]{--marino:#001633;"));
		assertTrue(css.chars().noneMatch(c -> c == '"' || c == '<' || c == '>' || c == '&'));
	}

	@Test
	void parametroIdaYVuelta() {
		Apariencia a = new Apariencia(Tema.PERSONALIZADO, "#3A0CA3", "#F72585", "itbmadminc",
				LocalDateTime.of(2026, 11, 1, 10, 22));
		String valor = AparienciaService.escribir(a);
		assertEquals("tema=personalizado;principal=#3A0CA3;acento=#F72585;usuario=itbmadminc;fecha=2026-11-01T10:22", valor);
		assertEquals(a, AparienciaService.interpretar(valor));
		assertEquals("itbmadminc · 01/11/2026 10:22", a.ultimoCambio());
	}

	@Test
	void valoresDanadosUsanInstitucional() {
		assertEquals(Tema.INSTITUCIONAL, AparienciaService.interpretar(null).tema());
		assertEquals(Tema.INSTITUCIONAL, AparienciaService.interpretar("tema=morado").tema());
		assertEquals(Tema.INSTITUCIONAL, AparienciaService.interpretar("tema=personalizado;principal=#FFFFFF;acento=#000000").tema());
		assertEquals(Tema.GUINDA, AparienciaService.interpretar("tema=guinda;fecha=ayer").tema());
		assertEquals("", AparienciaService.interpretar("tema=guinda").css());
	}
}
