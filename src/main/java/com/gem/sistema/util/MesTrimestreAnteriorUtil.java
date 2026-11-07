package com.gem.sistema.util;

import com.gem.sistema.ennums.MonthEnum;

public class MesTrimestreAnteriorUtil {

	public static String getTrimestreNombreAnterior(Integer trimiestre) {
		String value = "";
		switch (trimiestre) {
		case 1:
			value = MonthEnum.ENERO.getValue();
			break;
		case 2:
			value = MonthEnum.MARZO.getValue();
			break;
		case 3:
			value = MonthEnum.JUNIO.getValue();
			break;
		case 4:
			value = MonthEnum.SEPTIEMBRE.getValue();
			break;

		default:
			break;
		}
		return value;
	}

	public static String getMesNombreAnterior(Integer mes) {
		String value = "";
		switch (mes) {
		case 1:
			value = MonthEnum.DICIEMBRE.getValue();
			break;
		case 2:
			value = MonthEnum.ENERO.getValue();
			break;
		case 3:
			value = MonthEnum.FEBRERO.getValue();
			break;
		case 4:
			value = MonthEnum.MARZO.getValue();
			break;
		case 5:
			value = MonthEnum.ABRIL.getValue();
			break;
		case 6:
			value = MonthEnum.MAYO.getValue();
			break;
		case 7:
			value = MonthEnum.JUNIO.getValue();
			break;
		case 8:
			value = MonthEnum.JULIO.getValue();
			break;
		case 9:
			value = MonthEnum.AGOSTO.getValue();
			break;
		case 10:
			value = MonthEnum.SEPTIEMBRE.getValue();
			break;
		case 11:
			value = MonthEnum.OCTUBRE.getValue();
			break;
		case 12:
			value = MonthEnum.NOVIEMRE.getValue();
			break;
		default:
			break;
		}
		return value;
	}

	public static String getNombreMes(Integer mes) {
		String value = "";
		switch (mes) {
		case 1:
			value = MonthEnum.ENERO.getValue();
			break;
		case 2:
			value = MonthEnum.FEBRERO.getValue();
			break;
		case 3:
			value = MonthEnum.MARZO.getValue();
			break;
		case 4:
			value = MonthEnum.ABRIL.getValue();
			break;
		case 5:
			value = MonthEnum.MAYO.getValue();
			break;
		case 6:
			value = MonthEnum.JUNIO.getValue();
			break;
		case 7:
			value = MonthEnum.JULIO.getValue();
			break;
		case 8:
			value = MonthEnum.AGOSTO.getValue();
			break;
		case 9:
			value = MonthEnum.SEPTIEMBRE.getValue();
			break;
		case 10:
			value = MonthEnum.OCTUBRE.getValue();
			break;
		case 11:
			value = MonthEnum.NOVIEMRE.getValue();
			break;
		case 12:
			value = MonthEnum.DICIEMBRE.getValue();
			break;
		default:
			break;
		}
		return value;
	}

	public static String validarTipo(char tipo, Integer value) {
		return tipo == 't' ? getTrimestreNombreAnterior(value) : getMesNombreAnterior(value);
	}

	public static String getMonth(char tipo, char m, Integer value) {
		String month = "";
		if (tipo == 't') {
			if (m == 'a') {
				month = getTrimestreNombreAnterior(value + 1);
			} else {
				month = getTrimestreNombreAnterior(value);
			}
		} else {
			if (m == 'a')
				month = getNombreMes(value);
			else

				month = getMesNombreAnterior(value);
		}
		return month;
	}

}
