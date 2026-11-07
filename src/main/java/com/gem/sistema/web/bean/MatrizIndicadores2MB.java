package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.TcFicha;
import com.gem.sistema.business.domain.TcMatriz;
import com.gem.sistema.business.domain.TcNivel;
import com.gem.sistema.business.domain.TrMatrizNivel;
import com.gem.sistema.business.repository.catalogs.TcMatrizRepository;
import com.gem.sistema.business.service.indicadores.MatrizFichasIndicadoresService;
import com.gem.sistema.util.UtilFront;

/**
 * @author Alfredo Neri
 * @Timeline <strong>Date:</strong> 19/04/2021 <strong>Changes:</strong> Se
 *           ajustan reglas para la creación de variables en automatido de
 *           acuerdo al codigo de Indicador seleccionado<br>
 *           <strong>Date:</strong> 01/12/2021 <strong>Changes:</strong> Se
 *           ajusta componente con nuevas reglas de negocio<br>
 *           <strong>Date:</strong> 05/04/2022 <strong>Changes:</strong> Se
 *           ajustan componentes y metodo de creacion de fichas
 * @Sector Municipal
 */
@Scope("view")
@Component("matrizIndicadores2MB")
public class MatrizIndicadores2MB extends AbstractMB {

	private String dependenciaSearch = StringUtils.EMPTY;
	private String programaSearch = StringUtils.EMPTY;

	private List<TcMatriz> listMatriz;
	private List<Catdgm> listGenerales;
	private List<Catdaa> listAuxiliares;
	private List<Muninep> listProyectos;
	private List<Cpd> listTemas;

	private Catdaa auxiliar = new Catdaa();
	private Integer idSector = this.getUserDetails().getIdSector();

	private int currentPage = 0;
	private int currentPageComponente = 0;
	private int currentPageActividad = 0;

	private boolean isEditingEncabezado = false;

	private TcNivel componenteSelected;

	@Value("#{matrizFichasIndicadoresService}")
	private MatrizFichasIndicadoresService matrizFichasIndicadoresService;

	@Value("#{tcMatrizRepository}")
	private TcMatrizRepository matrizRepository;

	@PostConstruct
	public void init() {
		loadListPrincipal();

		if (!listMatriz.isEmpty()) {

			this.loadSecondList(0);
		}
	}

	public void search(Boolean newSearch) {
		if (StringUtils.isNotEmpty(dependenciaSearch) || StringUtils.isNotEmpty(programaSearch)) {
			listMatriz = matrizFichasIndicadoresService.getMatrices(idSector, dependenciaSearch, programaSearch);

			if (newSearch) {
				currentPage = 0;
				currentPageActividad = 0;
				currentPageComponente = 0;
			}
		}
	}

	public void cleanSearch() {

		this.dependenciaSearch = StringUtils.EMPTY;
		this.programaSearch = StringUtils.EMPTY;
		this.init();

	}

	public void editEncabezado() {
		isEditingEncabezado = true;
	}

	public void cancel() {
		isEditingEncabezado = false;
	}

	public void changeDependenciaGen() {
		TcMatriz matriz = listMatriz.get(currentPage);

		for (Catdgm general : listGenerales) {

			if (matriz.getDependencia().getId().equals(general.getId())) {
				matriz.setDependencia(general);
				break;
			}
		}

		PrimeFaces.current().ajax().update("formIndCaptura:indTable:" + currentPage + ":desDep");
	}

	public void changeDependenciaAux() {
		TcMatriz matriz = listMatriz.get(currentPage);

		for (Catdaa auxiliar : listAuxiliares) {

			if (matriz.getDependenciaAuxiliar().getId().equals(auxiliar.getId())) {
				matriz.setDependenciaAuxiliar(auxiliar);
				break;
			}
		}

		PrimeFaces.current().ajax().update("formIndCaptura:indTable:" + currentPage + ":desDepAux");
	}

	public void changeProyecto() {
		TcMatriz matriz = listMatriz.get(currentPage);

		for (Muninep proyecto : listProyectos) {

			if (matriz.getProyecto().getId().equals(proyecto.getId())) {
				matriz.setProyecto(proyecto);
			}
		}

		PrimeFaces.current().ajax().update("formIndCaptura:indTable:" + currentPage + ":desProyecto");
		PrimeFaces.current().ajax().update("formIndCaptura:indTable:" + currentPage + ":objProyecto");
	}

	public void changeTemaDesarrollo() {
		TcMatriz matriz = listMatriz.get(currentPage);

		for (Cpd tema : listTemas) {

			if (matriz.getTema().getId().equals(tema.getId())) {
				matriz.setTema(tema);
				break;
			}
		}

		PrimeFaces.current().ajax().update("formIndCaptura:indTable:" + currentPage + ":desDesarrollo");
	}

	public void replicarMatriz() {
		TcMatriz matriz = listMatriz.get(currentPage);

		TcMatriz newMatriz = new TcMatriz();

		newMatriz.setCanUpdateIndicador(Boolean.FALSE);
		newMatriz.setComponentes(matriz.getComponentes());
		newMatriz.setExistXcatpro(matriz.getExistXcatpro());
		newMatriz.setFinalidad(matriz.getFinalidad());
		newMatriz.setPrograma(matriz.getPrograma());
		newMatriz.setProgramaDescripcion(matriz.getProgramaDescripcion());
		newMatriz.setProposito(matriz.getProposito());
		newMatriz.setSector(matriz.getSector());

//		System.out.println(matriz.getTema().getCvetemas().substring(0, 6));
		Cpd tema = matrizFichasIndicadoresService.getTemaByCvetema(matriz.getTema().getCvetemas().substring(0, 6));
		newMatriz.setTema(tema);

		List<TrMatrizNivel> matrizNiveles = new ArrayList<TrMatrizNivel>();
		newMatriz = matrizFichasIndicadoresService.saveMatriz(newMatriz);

		List<TcNivel> niveles = matrizFichasIndicadoresService.getNivelesByMatrizId(matriz.getId());

		for (TcNivel nivel : niveles) {

			TrMatrizNivel matrizNivel = new TrMatrizNivel();
			matrizNivel.setMatriz(newMatriz);
			matrizNivel.setNivel(nivel);

			matrizNiveles.add(matrizNivel);
		}

		matrizFichasIndicadoresService.saveMatricesNivel(matrizNiveles);
		newMatriz.setNiveles(matrizNiveles);
		matrizFichasIndicadoresService.saveMatriz(newMatriz);

		this.loadListPrincipal();
		this.loadSecondList(currentPage);

		PrimeFaces.current().executeScript("PF('indWdg').getPaginator().setPage(" + currentPage + ")");
		PrimeFaces.current().ajax().update("formIndCaptura:indTable:" + currentPage + ":tabs");
	}

	public void saveEncabezado() {

		TcMatriz matriz = listMatriz.get(currentPage);

		if (matriz.getDependencia().getId() != null && matriz.getDependencia().getId() > 0) {
			if (matriz.getDependenciaAuxiliar().getId() != null && matriz.getDependenciaAuxiliar().getId() > 0) {
				if (matriz.getProyecto().getId() != null && matriz.getProyecto().getId() > 0) {
					if (matriz.getTema().getId() != null && matriz.getTema().getId() > 0) {

						if (matrizFichasIndicadoresService.existPrograma(matriz)) {

							if (matrizFichasIndicadoresService.validSave(matriz)) {

								Cpd cpd = matrizFichasIndicadoresService
										.getTemaDesarrolloById(matriz.getTema().getId());
								Catdaa catdaa = matrizFichasIndicadoresService
										.getDependenciaAuxiliarById(matriz.getDependenciaAuxiliar().getId());
								Catdgm catdgm = matrizFichasIndicadoresService
										.getDependenciaGeneralById(matriz.getDependencia().getId());
								Muninep muninep = matrizFichasIndicadoresService
										.getProyectoById(matriz.getProyecto().getId());

								listMatriz.get(currentPage).setDependencia(catdgm);
								listMatriz.get(currentPage).setDependenciaAuxiliar(catdaa);
								listMatriz.get(currentPage).setProyecto(muninep);
								listMatriz.get(currentPage).setTema(cpd);
								listMatriz.get(currentPage).setCanUpdateIndicador(Boolean.TRUE);
								matrizRepository.save(listMatriz.get(currentPage));
								this.cancel();

								PrimeFaces.current()
										.ajax().update("formIndCaptura:indTable:" + currentPage + ":tabs");
								UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success",
										"Se ha actualizado correctamente el encabezado");
							} else {
								UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
										"Ya existe una matriz con la misma información.");
							}
						} else {
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
									"El Programa no esta registrado en el Catálogo de Programas.");
						}
					} else {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
								"Favor de Selecionar un Tema");
					}
				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
							"Favor de Selecionar un Proyecto");
				}
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						"Favor de Selecionar una Dependencia Auxiliar");
			}
		} else {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de Selecionar una Dependencia General");
		}
	}

	public void createFicha(TrMatrizNivel matrizNivel) {

		System.out.println("IdNivel->" + matrizNivel.getId());
//		System.out.println(nivel.toString());
		try {
			TcFicha ficha = new TcFicha();

			ficha.setMatrizNivel(matrizNivel);

			matrizFichasIndicadoresService.saveFicha(ficha);

			this.loadListPrincipal();
			cancel();
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success",
					"Se creo correctamente la Ficha");
		} catch (Exception e) {
			e.printStackTrace();
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de Contactar al Administrador.");
		}

	}

	public void deleteFicha(Long idFicha) {

		try {
			matrizFichasIndicadoresService.deleteFicha(idFicha);
			this.loadListPrincipal();

			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success",
					"Se ha eliminado correctamente la Ficha");
		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Ha ocurrido un Error, Favor de Contactar al Administrador");
		}

	}

	public void changePageMatriz(PageEvent event) {
		currentPage = event.getPage();

		TcMatriz matriz = listMatriz.get(currentPage);
		System.out.println("idMAtriz _> " + matriz.getId());
		try {

			if (matriz.getExistXcatpro()) {

				this.loadSecondList(currentPage);

				if (matriz.getCanUpdateIndicador()) {
					currentPageActividad = 0;
					currentPageComponente = 0;

					PrimeFaces.current().executeScript("PF('comWdg').getPaginator().setPage(0)");
					PrimeFaces.current().executeScript("PF('actWdg').getPaginator().setPage(0)");
				}

			}

		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
					"Ha ocurrido un Error, Favor de Contactar al Administrador", e.getMessage());
		}

	}

	public void changePageComponente(PageEvent event) {
		currentPageComponente = event.getPage();
		currentPageActividad = 0;

		PrimeFaces.current().executeScript("PF('actWdg').getPaginator().setPage(0)");
	}

	public void changePageActividad(PageEvent event) {
		currentPageActividad = event.getPage();

	}

	private void loadListPrincipal() {
		if (StringUtils.isEmpty(dependenciaSearch) && StringUtils.isEmpty(programaSearch)) {
			listMatriz = matrizFichasIndicadoresService.getMatrices(idSector);
		} else {
			search(false);
		}
	}

	private void loadSecondList(Integer indexPage) {

		TcMatriz matriz = listMatriz.get(indexPage);

		listGenerales = matrizFichasIndicadoresService.listadoDependenciasGenerales(matriz.getPrograma(), idSector);
		listAuxiliares = matrizFichasIndicadoresService.listadoDependenciasAuxiliares(matriz.getPrograma(), idSector);
		listProyectos = matrizFichasIndicadoresService.listadoProyectos(matriz.getPrograma(), idSector);
		listTemas = matrizFichasIndicadoresService
				.listadoTemasDesarrollo(matriz.getTema().getCvetemas().substring(0, 6));
	}

	public String getdependenciaSearch() {
		return dependenciaSearch;
	}

	public void setdependenciaSearch(String dependenciaSearch) {
		this.dependenciaSearch = dependenciaSearch;
	}

	public List<TcMatriz> getListMatriz() {
		return listMatriz;
	}

	public void setListMatriz(List<TcMatriz> listMatriz) {
		this.listMatriz = listMatriz;
	}

	public List<Catdgm> getListGenerales() {
		return listGenerales;
	}

	public void setListGenerales(List<Catdgm> listGenerales) {
		this.listGenerales = listGenerales;
	}

	public List<Catdaa> getListAuxiliares() {
		return listAuxiliares;
	}

	public void setListAuxiliares(List<Catdaa> listAuxiliares) {
		this.listAuxiliares = listAuxiliares;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isEditingEncabezado() {
		return isEditingEncabezado;
	}

	public void setEditingEncabezado(boolean isEditingEncabezado) {
		this.isEditingEncabezado = isEditingEncabezado;
	}

	public Catdaa getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Catdaa auxiliar) {
		this.auxiliar = auxiliar;
	}

	public List<Muninep> getListProyectos() {
		return listProyectos;
	}

	public void setListProyectos(List<Muninep> listProyectos) {
		this.listProyectos = listProyectos;
	}

	public List<Cpd> getListTemas() {
		return listTemas;
	}

	public void setListTemas(List<Cpd> listTemas) {
		this.listTemas = listTemas;
	}

	public TcMatrizRepository getMatrizRepository() {
		return matrizRepository;
	}

	public void setMatrizRepository(TcMatrizRepository matrizRepository) {
		this.matrizRepository = matrizRepository;
	}

	public String getDependenciaSearch() {
		return dependenciaSearch;
	}

	public void setDependenciaSearch(String dependenciaSearch) {
		this.dependenciaSearch = dependenciaSearch;
	}

	public String getProgramaSearch() {
		return programaSearch;
	}

	public void setProgramaSearch(String programaSearch) {
		this.programaSearch = programaSearch;
	}

	public MatrizFichasIndicadoresService getMatrizFichasIndicadoresService() {
		return matrizFichasIndicadoresService;
	}

	public void setMatrizFichasIndicadoresService(MatrizFichasIndicadoresService matrizFichasIndicadoresService) {
		this.matrizFichasIndicadoresService = matrizFichasIndicadoresService;
	}

	public int getCurrentPageComponente() {
		return currentPageComponente;
	}

	public void setCurrentPageComponente(int currentPageComponente) {
		this.currentPageComponente = currentPageComponente;
	}

	public int getCurrentPageActividad() {
		return currentPageActividad;
	}

	public void setCurrentPageActividad(int currentPageActividad) {
		this.currentPageActividad = currentPageActividad;
	}

	public TcNivel getComponenteSelected() {
		return componenteSelected;
	}

	public void setComponenteSelected(TcNivel componenteSelected) {
		this.componenteSelected = componenteSelected;
	}

}
