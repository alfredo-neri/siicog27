package com.gem.sistema.ennum.constans;

public enum ConstansEnum {
	TYPE_PASS("PASSWORD"),
	KEY_PASS_ACUM ("PASS_ACUMULACION"),
	KEY_PASS_ACUM_1 ("PASS_ACUMULACION1");
	
	
	private String value;
	
	private ConstansEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
