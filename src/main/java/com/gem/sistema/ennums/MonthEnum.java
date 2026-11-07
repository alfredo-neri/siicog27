package com.gem.sistema.ennums;

public enum MonthEnum {
	
	ENERO(1, "Enero"),
	FEBRERO(2, "Febrero"),
	MARZO(3, "Marzo"),
	ABRIL(4, "Abril"),
	MAYO(5, "Mayo"),
	JUNIO(6, "Junio"),
	JULIO(7, "Julio"),
	AGOSTO(8, "Agosto"),
	SEPTIEMBRE(9, "Septeimbre"),
	OCTUBRE(10, "Octubre"),
	NOVIEMRE(11, "Noviembre"),
	DICIEMBRE(12, "Diciembre");
	private String value;
	private int key;
	
	private MonthEnum(int key, String value) {
		this.value = value;
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public int getKey() {
		return key;
	}
	
	

}
