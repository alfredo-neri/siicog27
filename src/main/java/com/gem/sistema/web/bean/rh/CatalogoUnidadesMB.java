package com.gem.sistema.web.bean.rh;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.dto.CatalogoUnidades;

@ManagedBean(name = "catalogoUnidadesMB")
@ViewScoped
public class CatalogoUnidadesMB {
	
	private List<CatalogoUnidades> listCatalog = Arrays.asList(new CatalogoUnidades(1, "PONENCIA COMISIONADO PRESIDENTE JMV"),
		new CatalogoUnidades(2, "PONENCIA COMISIONADA SCMM"),
		new CatalogoUnidades(3, "PONENCIA COMISIONADA GRP"),
		new CatalogoUnidades(4, "PONENCIA COMISIONADA MRMA"),
		new CatalogoUnidades(5, "PONENCIA COMISIONADA LGPN"),
		new CatalogoUnidades(6, "SECRETARÍA PARTICULAR DEL COMISIONADO PRESIDENTE"),
		new CatalogoUnidades(7, "SECRETARÍA TÉCNICA DEL PLENO"),
		new CatalogoUnidades(8, "DIRECCIÓN GENERAL DE PROTECCIÓN DE DATOS PERSONALES"));
	
	@PostConstruct
	public void init() {
		
	}

	public List<CatalogoUnidades> getListCatalog() {
		return listCatalog;
	}

	public void setListCatalog(List<CatalogoUnidades> listCatalog) {
		this.listCatalog = listCatalog;
	}
	
	
	

}
