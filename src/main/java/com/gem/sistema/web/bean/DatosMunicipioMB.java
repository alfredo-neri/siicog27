package com.gem.sistema.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Catmun;
import com.gem.sistema.business.repository.catalogs.CatmunRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.DatosMunicipioService;

@ManagedBean(name = "datosMunicipioMB")
@ViewScoped
public class DatosMunicipioMB extends AbstractMB {

	private List<String> lisMuni;
	private String clave;
	private String clvEntidad = "0";
	private String clvMuni;

	@ManagedProperty("#{catmunRepository}")
	private CatmunRepository catmunRepository;

	@ManagedProperty("#{datosMunicipioService}")
	private DatosMunicipioService datosMunicipioService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		String clvmuni = conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave(); 
		lisMuni = catmunRepository.findMuniClave();
		clvMuni = catmunRepository.findMuniClave(Long.valueOf(clvmuni.substring(1, 4)).intValue());
		clvEntidad = clvmuni.substring(0, 1);

	}

	public void update() {
		clave = clvEntidad + this.getClaveMunicipio(clvMuni);
		this.datosMunicipioService.update(clave, this.getUserDetails().getIdSector(),
				this.getUserDetails().getUsername());
	}

	public String getClvMuni() {
		return clvMuni;
	}

	public void setClvMuni(String clvMuni) {
		this.clvMuni = clvMuni;
	}

	public DatosMunicipioService getDatosMunicipioService() {
		return datosMunicipioService;
	}

	public void setDatosMunicipioService(DatosMunicipioService datosMunicipioService) {
		this.datosMunicipioService = datosMunicipioService;
	}

	public List<String> getLisMuni() {
		return lisMuni;
	}

	public void setLisMuni(List<String> lisMuni) {
		this.lisMuni = lisMuni;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClvEntidad() {
		return clvEntidad;
	}

	public void setClvEntidad(String clvEntidad) {
		this.clvEntidad = clvEntidad;
	}

	public CatmunRepository getCatmunRepository() {
		return catmunRepository;
	}

	public void setCatmunRepository(CatmunRepository catmunRepository) {
		this.catmunRepository = catmunRepository;
	}

	public String getClaveMunicipio(String cat) {
		String[] clv = cat.split("-");
		return clv[1].trim();
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
