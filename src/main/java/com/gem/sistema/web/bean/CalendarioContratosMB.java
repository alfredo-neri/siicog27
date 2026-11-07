package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.dto.CalendarioDTO;
import com.gem.sistema.business.repository.catalogs.TcContratosRepository;
import com.gem.sistema.business.repository.catalogs.TcEstadosContratoRepository;
import com.gem.sistema.business.repository.catalogs.TrContratoDetailRepository;

@ManagedBean(name = "calendarioContratosMB")
@ViewScoped
public class CalendarioContratosMB extends AbstractMB {
	private Boolean canlendarVisible = true;
	private Long idSeleccionado;
	private Conctb conctb = null;
	private Date fechaSeleccionada;
	private List<CalendarioDTO> eventosDomingo = new ArrayList<CalendarioDTO>();
	private List<CalendarioDTO> eventosLunes = new ArrayList<CalendarioDTO>();
	private List<CalendarioDTO> eventosMartes = new ArrayList<CalendarioDTO>();
	private List<CalendarioDTO> eventosMiercoles = new ArrayList<CalendarioDTO>();
	private List<CalendarioDTO> eventosJueves = new ArrayList<CalendarioDTO>();
	private List<CalendarioDTO> eventosViernes = new ArrayList<CalendarioDTO>();
	private List<CalendarioDTO> eventosSabado = new ArrayList<CalendarioDTO>();

	@ManagedProperty("#{tcContratosRepository}")
	private TcContratosRepository contratosRepository;

	@PostConstruct
	private void init() {

	}

	public void optenerEventos() {
		fechaSeleccionada = new Date();
		Date primerDia = optenerPrierDia(fechaSeleccionada);
		Date ultimoDia = optenerUltimoDia(fechaSeleccionada);
		// List<TcContrato> contratos =
		// contratosRepository.findAllByfechaCreacionBetween(primerDia, ultimoDia);

	}

	private Date optenerPrierDia(Date dia) {
		return null;
	}

	private Date optenerUltimoDia(Date dia) {
		return null;
	}

	public void posponer() {

	}

	public void cancelar() {

	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Date getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(Date fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	public List<CalendarioDTO> getEventosDomingo() {
		return eventosDomingo;
	}

	public void setEventosDomingo(List<CalendarioDTO> eventosDomingo) {
		this.eventosDomingo = eventosDomingo;
	}

	public List<CalendarioDTO> getEventosLunes() {
		return eventosLunes;
	}

	public void setEventosLunes(List<CalendarioDTO> eventosLunes) {
		this.eventosLunes = eventosLunes;
	}

	public List<CalendarioDTO> getEventosMartes() {
		return eventosMartes;
	}

	public void setEventosMartes(List<CalendarioDTO> eventosMartes) {
		this.eventosMartes = eventosMartes;
	}

	public List<CalendarioDTO> getEventosMiercoles() {
		return eventosMiercoles;
	}

	public void setEventosMiercoles(List<CalendarioDTO> eventosMiercoles) {
		this.eventosMiercoles = eventosMiercoles;
	}

	public List<CalendarioDTO> getEventosJueves() {
		return eventosJueves;
	}

	public void setEventosJueves(List<CalendarioDTO> eventosJueves) {
		this.eventosJueves = eventosJueves;
	}

	public List<CalendarioDTO> getEventosViernes() {
		return eventosViernes;
	}

	public void setEventosViernes(List<CalendarioDTO> eventosViernes) {
		this.eventosViernes = eventosViernes;
	}

	public List<CalendarioDTO> getEventosSabado() {
		return eventosSabado;
	}

	public void setEventosSabado(List<CalendarioDTO> eventosSabado) {
		this.eventosSabado = eventosSabado;
	}

	public Boolean getCanlendarVisible() {
		return canlendarVisible;
	}

	public void setCanlendarVisible(Boolean canlendarVisible) {
		this.canlendarVisible = canlendarVisible;
	}

	public TcContratosRepository getContratosRepository() {
		return contratosRepository;
	}

	public void setContratosRepository(TcContratosRepository contratosRepository) {
		this.contratosRepository = contratosRepository;
	}

	public Long getIdSeleccionado() {
		return idSeleccionado;
	}

	public void setIdSeleccionado(Long idSeleccionado) {
		this.idSeleccionado = idSeleccionado;
	}

}
