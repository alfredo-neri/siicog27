package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import com.gem.sistema.business.domain.TbProceso;
import com.gem.sistema.business.repository.catalogs.TbProcesoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Component("registroReportesMB")
@Scope("view")
public class RegistroReportesMB extends AbstractMB {

	private List<String> listReportes = new ArrayList<String>();
	private List<TbProceso> listMaster = new ArrayList<TbProceso>();
	private Date fecha = new Date();
	private String nombreReporte = "";

	@Value("#{tbProcesoRepository}")
	private TbProcesoRepository procesoRepository;

	@PostConstruct
	private void init() {
		listReportes.add("Seleccione...");
		procesoRepository.getNombresReportes().forEach(listReportes::add);
		upList();
	}

	@SuppressWarnings("deprecation")
	public void upList() {
		if (nombreReporte.equals("Seleccione...") || nombreReporte.equals("")) {
			listMaster = procesoRepository.findByFechaInicio(fecha.getDate(), fecha.getMonth() + 1).stream().map(r -> {
				r.setRuta(r.getRuta().replace("/", " /"));
				return r;
			}).collect(Collectors.toList());
			;
		} else {
			listMaster = procesoRepository.findByFechaInicioAndNombreProceso(nombreReporte, fecha);
		}
	}

	public Date getMaxDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		return c.getTime();
	}

	public List<String> getListReportes() {
		return listReportes;
	}

	public void setListReportes(List<String> listReportes) {
		this.listReportes = listReportes;
	}

	public List<TbProceso> getListMaster() {
		return listMaster;
	}

	public void setListMaster(List<TbProceso> listMaster) {
		this.listMaster = listMaster;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public TbProcesoRepository getProcesoRepository() {
		return procesoRepository;
	}

	public void setProcesoRepository(TbProcesoRepository procesoRepository) {
		this.procesoRepository = procesoRepository;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
