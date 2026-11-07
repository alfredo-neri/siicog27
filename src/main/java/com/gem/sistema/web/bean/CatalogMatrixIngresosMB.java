package com.gem.sistema.web.bean;

import com.gem.sistema.business.domain.TcMatrizIngreso;
import com.gem.sistema.business.repository.catalogs.TcMatrizIngresoRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "catalogMatrixIngresosMB")
@ViewScoped
public class CatalogMatrixIngresosMB {

	private String tipoMatriz;
	private String titleTable;
	private List<TcMatrizIngreso> listMatrizIngresos;

	@ManagedProperty("#{tcMatrizIngresoRepository}")
	private TcMatrizIngresoRepository matrizIngresoRepository;

	@PostConstruct
	public void init() {

		tipoMatriz = "D";
	}

	public void generateMatriz(){
		System.out.println("Generando matriz + " +  tipoMatriz);

		if (tipoMatriz.equals("D")){
			titleTable = "MATRIZ INGRESOS DEVENGADOS";
		} else if (tipoMatriz.equals("R")) {
			titleTable = " MATRIZ INGRESOS RECAUDADOS";
		}

		listMatrizIngresos = matrizIngresoRepository.findAllByTipoIngresoOrderByConsecutivoAsc(tipoMatriz);
	}

	public String getTipoMatriz() {
		return tipoMatriz;
	}

	public void setTipoMatriz(String tipoMatriz) {
		this.tipoMatriz = tipoMatriz;
	}

	public String getTitleTable() {
		return titleTable;
	}

	public void setTitleTable(String titleTable) {
		this.titleTable = titleTable;
	}

	public TcMatrizIngresoRepository getMatrizIngresoRepository() {
		return matrizIngresoRepository;
	}

	public void setMatrizIngresoRepository(TcMatrizIngresoRepository matrizIngresoRepository) {
		this.matrizIngresoRepository = matrizIngresoRepository;
	}

	public List<TcMatrizIngreso> getListMatrizIngresos() {
		return listMatrizIngresos;
	}

	public void setListMatrizIngresos(List<TcMatrizIngreso> listMatrizIngresos) {
		this.listMatrizIngresos = listMatrizIngresos;
	}
}
