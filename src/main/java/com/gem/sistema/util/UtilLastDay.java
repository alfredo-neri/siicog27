package com.gem.sistema.util;

import java.util.Calendar;

public class UtilLastDay {

	public static Integer lastDayOfMonth(Integer year, Integer trimestre) {
		Integer month = 0;
		if (trimestre == 1)
			month = 2;
		else if (trimestre == 2)
			month = 5;
		else if (trimestre == 3)
			month = 8;
		else if (trimestre == 4)
			month = 11;

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer lastDayOfMonthByMonth(Integer year, Integer trimestre) {
		Integer month = trimestre-1;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static String getMonthOfYear(Integer trimestre) {
		if (trimestre == 1)
			return "Marzo";
		else if (trimestre == 2)
			return "Junio";
		else if (trimestre == 3)
			return "Septiembre";
		else if (trimestre == 4)
			return "Diciembre";
		return "";
	}
	
	public static String getMonthOfYearBefore(Integer trimestre, boolean mensual) {
		String nombre = "";
		if(mensual) {
			switch(trimestre) {
				case 1: {
					nombre = "Diciembre";
					break;
				}
				case 2: {
					nombre = "Enero";
					break;
				}
				case 3: {
					nombre = "Febrero";
					break;
				}
				case 4: {
					nombre = "Marzo";
					break;
				}
				case 5: {
					nombre = "Abril";
					break;
				}
				case 6: {
					nombre = "Mayo";
					break;
				}
				case 7: {
					nombre = "Junio";
					break;
				}
				case 8: {
					nombre = "Julio";
					break;
				}
				case 9: {
					nombre = "Agosto";
					break;
				}
				case 10: {
					nombre = "Septiembre";
					break;
				}
				case 11: {
					nombre = "Octubre";
					break;
				}
				case 12: {
					nombre = "Noviembre";
					break;
				}
			}
		} else {
			switch (trimestre) {
				case 1:{
					nombre = "Febrero";
					break;
				}
				case 2:{
					nombre = "Mayo";
					break;
				}
				case 3:{
					nombre = "Agosto";
					break;
				}
				case 4:{
					nombre = "Noviembre";
					break;
				}
			}
		}
		
		return nombre;
	}
	
	public static Integer lastDayOfMonth(Integer year, Integer trimestre, boolean mensual) {
		Integer month = 0;
		if(mensual) {
			month = trimestre -1;
		} else {
			if (trimestre == 1)
				month = 2;
			else if (trimestre == 2)
				month = 5;
			else if (trimestre == 3)
				month = 8;
			else if (trimestre == 4)
				month = 11;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static String getMonthOfYear(Integer trimestre, boolean mensual) {
		String nombre = "";
		if(mensual) {
			switch(trimestre) {
				case 1: {
					nombre = "Enero";
					break;
				}
				case 2: {
					nombre = "Febrero";
					break;
				}
				case 3: {
					nombre = "Marzo";
					break;
				}
				case 4: {
					nombre = "Abril";
					break;
				}
				case 5: {
					nombre = "Mayo";
					break;
				}
				case 6: {
					nombre = "Junio";
					break;
				}
				case 7: {
					nombre = "Julio";
					break;
				}
				case 8: {
					nombre = "Agosto";
					break;
				}
				case 9: {
					nombre = "Septiembre";
					break;
				}
				case 10: {
					nombre = "Octubre";
					break;
				}
				case 11: {
					nombre = "Noviembre";
					break;
				}
				case 12: {
					nombre = "Diciembre";
					break;
				}
			}
		} else {
			switch (trimestre) {
				case 1:{
					nombre = "Marzo";
					break;
				}
				case 2:{
					nombre = "Junio";
					break;
				}
				case 3:{
					nombre = "Septiembre";
					break;
				}
				case 4:{
					nombre = "Diciembre";
					break;
				}
			}
		}
		
		return nombre;
	}

}
