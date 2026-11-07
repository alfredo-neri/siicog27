package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.gem.sistema.business.service.catalogos.FlujosIncorrectosService;
import com.gem.sistema.business.vo.FlujosIncorrectosVO;

@ManagedBean(name = "flujosIncorrectosMB")
@ViewScoped
public class FlujosIncorrectosMB {

	@ManagedProperty(value = "#{flujosIncorrectosService}")
	private FlujosIncorrectosService flujosService;

	private List<Integer> listMont;

	private List<FlujosIncorrectosVO> listIncorrectos;
	private Integer month;

	private FlujosIncorrectosVO flujoefectivoIncorrecto;

	@PostConstruct
	public void init() {

		listMont = new ArrayList<Integer>();
		listIncorrectos = new ArrayList<FlujosIncorrectosVO>();
		for (int i = 1; i <= 12; i++) {
			listMont.add(i);
		}
		month = listMont.get(0);
	}

	public void findAll() {
		listIncorrectos = this.flujosService.findByMonth(month);
	}

	public void findByCount(FlujosIncorrectosVO flujo) {
		flujoefectivoIncorrecto = this.flujosService.findByCount(flujo);
	}

	public void delete(FlujosIncorrectosVO flujoWrong) {
		String msg = this.flujosService.deleteFlujo(flujoWrong);
		displayErrorMsg(msg, FacesMessage.SEVERITY_INFO, "Info");

	}

	private void displayErrorMsg(String msg, Severity severityInfo, String typeError) {
		FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, typeError, msg);
		FacesContext.getCurrentInstance().addMessage(msg, errorMsg);

	}

	public FlujosIncorrectosService getFlujosService() {
		return flujosService;
	}

	public void setFlujosService(FlujosIncorrectosService flujosService) {
		this.flujosService = flujosService;
	}

	public List<Integer> getListMont() {
		return listMont;
	}

	public void setListMont(List<Integer> listMont) {
		this.listMont = listMont;
	}

	public List<FlujosIncorrectosVO> getListIncorrectos() {
		return listIncorrectos;
	}

	public void setListIncorrectos(List<FlujosIncorrectosVO> listIncorrectos) {
		this.listIncorrectos = listIncorrectos;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public FlujosIncorrectosVO getFlujoefectivoIncorrecto() {
		return flujoefectivoIncorrecto;
	}

	public void setFlujoefectivoIncorrecto(FlujosIncorrectosVO flujoefectivoIncorrecto) {
		this.flujoefectivoIncorrecto = flujoefectivoIncorrecto;
	}

}
