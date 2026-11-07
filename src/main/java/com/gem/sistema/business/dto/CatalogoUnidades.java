package com.gem.sistema.business.dto;

public class CatalogoUnidades {
	private Integer id;
	private String dependencia;
	
	

	public CatalogoUnidades(Integer id, String dependencia) {
		super();
		this.id = id;
		this.dependencia = dependencia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

}
