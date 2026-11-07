package com.gem.sistema.ennum.constans;

/**
 * @author Alfredo Neri
 *
 */
public enum RespaldoEnnum {

	COMAND_LIST_DIR ("cd \"C:\\gem\\bk\" && dir BKP_*_* /B"),
	PATH_BAKUPS("c:\\gem\\bk\\"),
	CMD_BK ("cmd /c start /I /MIN C:\\\\gem\\\\BK.bat "),
	NAME_ZIP("Respaldo.zip"),
	EXE("cmd.exe");
	private String value;

	public String getValue() {
		return value;
	}
	
	private RespaldoEnnum(String value) {
		this.value = value;
	}
}
