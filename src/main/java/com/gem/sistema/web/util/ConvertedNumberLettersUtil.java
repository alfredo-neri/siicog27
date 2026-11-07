package com.gem.sistema.web.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class ConvertedNumberLettersUtil {

	private static String centavos;
	private static Stream<String> monto;

	public static String unidades(Double numero) {

		switch (numero.intValue()) {
		case 1:
			return "UN";

		case 2:
			return "DOS";

		case 3:
			return "TRES";

		case 4:
			return "CUATRO";

		case 5:
			return "CINCO";

		case 6:
			return "SEIS";

		case 7:
			return "SIETE";

		case 8:
			return "OCHO";

		case 9:
			return "NUEVE";

		}
		return "";

	}

	public static String decenas(Double numero) {
		Double decena = Math.floor(numero / 10);
		Double unidad = numero - (decena * 10);

		switch (decena.intValue()) {
		case 1:
			switch (unidad.intValue()) {
			case 0:
				return "DIEZ";
			case 1:
				return "ONCE";
			case 2:
				return "DOCE";
			case 3:
				return "TRECE";
			case 4:
				return "CATORCE";
			case 5:
				return "QUINCE";
			default:
				return "DIECI" + unidades(unidad);
			}
		case 2:
			switch (unidad.intValue()) {
			case 0:
				return "VEINTE";
			default:
				return "VEINTI" + unidades(unidad);
			}
		case 3:
			return decenasY("TREINTA", unidad);
		case 4:
			return decenasY("CUARENTA", unidad);
		case 5:
			return decenasY("CINCUENTA", unidad);
		case 6:
			return decenasY("SESENTA", unidad);
		case 7:
			return decenasY("SETENTA", unidad);
		case 8:
			return decenasY("OCHENTA", unidad);
		case 9:
			return decenasY("NOVENTA", unidad);
		case 0:
			return unidades(unidad);
		}
		return "";
	}

	public static String decenasY(String strSin, Double numUnidades) {
		if (numUnidades > 0)
			return strSin + " Y " + unidades(numUnidades);
		return strSin;
	}

	public static String centenas(Double num) {
		Double centenas = Math.floor(num / 100);
		Double decenas = num - (centenas * 100);

		switch (centenas.intValue()) {
		case 1:
			if (decenas > 0)
				return "CIENTO " + decenas(decenas);
			return "CIEN";
		case 2:
			return "DOSCIENTOS " + decenas(decenas);
		case 3:
			return "TRESCIENTOS " + decenas(decenas);
		case 4:
			return "CUATROCIENTOS " + decenas(decenas);
		case 5:
			return "QUINIENTOS " + decenas(decenas);
		case 6:
			return "SEISCIENTOS " + decenas(decenas);
		case 7:
			return "SETECIENTOS " + decenas(decenas);
		case 8:
			return "OCHOCIENTOS " + decenas(decenas);
		case 9:
			return "NOVECIENTOS " + decenas(decenas);
		}

		return decenas(decenas);
	}

	public static String seccion(Double num, Integer divisor, String strSingular, String strPlural) {
		Double cientos = Math.floor(num / divisor);
		Double resto = num - (cientos * divisor);

		String letras = "";

		if (cientos > 0)
			if (cientos > 1)
				letras = centenas(cientos) + " " + strPlural;
			else
				letras = strSingular;

		if (resto > 0)
			letras += "";

		return letras;
	}

	public static String miles(Double num) {
		Integer divisor = 1000;
		Double cientos = (Math.floor(num / divisor));
		Double resto = num - (cientos * divisor);

		String strMiles = seccion(num, divisor, "UN MIL", "MIL");
		String strCentenas = centenas(resto);

		if (strMiles == "")
			return strCentenas;

		return strMiles + " " + strCentenas;
	}

	public static String millones(Double num) {
		Integer divisor = 1000000;
		Double cientos = Math.floor(num / divisor);
		Double resto = num - (cientos * divisor);

		String strMillones = seccion(num, divisor, "UN MILLON", "MILLONES");
		String strMiles = miles(resto);

		if (strMillones == "")
			return strMiles;

		return strMillones + " " + strMiles;
	}

	public static String numeroALetras(Double num) {
		Map<String, Object> map = new HashMap<>();
		map.put("numero", num);
		map.put("enteros", Math.floor(num));
		map.put("centavos", (((Math.round(num * 100)) - (Math.floor(num) * 100))));
		map.put("letrasCentavos", "");
		map.put("letrasMonedaPlural", "PESOS");
		map.put("letrasMonedaSingular", "PESOS");
		map.put("letrasMonedaCentavoPlural", "CENTAVOS");
		map.put("letrasMonedaCentavoSingular", "CENTAVOS");
		String centavos = "";
		if (Double.valueOf(map.get("centavos").toString()) > 0) {
			/*
			 * Stream<String> monto = map.entrySet().stream().filter(data ->
			 * data.getKey().equals("centavos")) .filter(data ->
			 * Double.valueOf(data.getValue().toString()) > 0).map(data -> { if
			 * (Double.valueOf(data.getValue().toString()) == 1) return millones((double)
			 * map.get("centavos")) + " " + data.getValue(); else return millones((double)
			 * map.get("centavos")) + " " + data.getValue(); });
			 */
			String c = map.get("centavos").toString().replace(".", "");
			centavos = " " + c.substring(0, 2) + "/100";
			if (StringUtils.isBlank(centavos))
				centavos = " 00/100";
		}
		if (Double.valueOf(map.get("enteros").toString()) == 0)
			return "CERO " + map.get("letrasMonedaPlural") + " " + map.get("letrasCentavos") + "" + centavos + " M.N";
		if (Double.valueOf(map.get("enteros").toString()) == 1)
			return millones((double) map.get("enteros")) + " " + map.get("letrasMonedaSingular") + "" + centavos + " "
					+ map.get("letrasCentavos") + " M.N";
		else
			return millones((double) map.get("enteros")) + " " + map.get("letrasMonedaPlural") + ""
					+ map.get("letrasCentavos") + " " + centavos + " M.N";
	}
}
