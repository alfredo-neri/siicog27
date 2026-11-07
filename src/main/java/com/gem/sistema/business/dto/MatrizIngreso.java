package com.gem.sistema.business.dto;

public class MatrizIngreso {
	private String nombre;
	private String status;
	private String cargo1;
	private String abono1;
	private String cargo2;
	private String abono2;
	private String cargo3;
	private String abono3;
	private String cargo4;
	private String abono4;

	public MatrizIngreso(String nombre, String status, String cargo1, String abono1, String cargo2, String abono2,
			String cargo3, String abono3, String cargo4, String abono4) {
		super();
		this.nombre = nombre;
		this.status = status;
		this.cargo1 = cargo1;
		this.abono1 = abono1;
		this.cargo2 = cargo2;
		this.abono2 = abono2;
		this.cargo3 = cargo3;
		this.abono3 = abono3;
		this.cargo4 = cargo4;
		this.abono4 = abono4;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCargo1() {
		return cargo1;
	}

	public void setCargo1(String cargo1) {
		this.cargo1 = cargo1;
	}

	public String getAbono1() {
		return abono1;
	}

	public void setAbono1(String abono1) {
		this.abono1 = abono1;
	}

	public String getCargo2() {
		return cargo2;
	}

	public void setCargo2(String cargo2) {
		this.cargo2 = cargo2;
	}

	public String getAbono2() {
		return abono2;
	}

	public void setAbono2(String abono2) {
		this.abono2 = abono2;
	}

	public String getCargo3() {
		return cargo3;
	}

	public void setCargo3(String cargo3) {
		this.cargo3 = cargo3;
	}

	public String getAbono3() {
		return abono3;
	}

	public void setAbono3(String abono3) {
		this.abono3 = abono3;
	}

	public String getCargo4() {
		return cargo4;
	}

	public void setCargo4(String cargo4) {
		this.cargo4 = cargo4;
	}

	public String getAbono4() {
		return abono4;
	}

	public void setAbono4(String abono4) {
		this.abono4 = abono4;
	}

}
